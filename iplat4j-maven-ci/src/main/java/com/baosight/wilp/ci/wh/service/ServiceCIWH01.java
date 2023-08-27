package com.baosight.wilp.ci.wh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.wh.domain.CiWarehouse;
import com.baosight.xservices.xs.util.UserSession;

/**
 *  仓库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>查询仓库（无分页）</p>
 * <p>删除仓库</p>
 * <p>冻结/解冻仓库</p>
 * <p>校验仓库是否正常</p>
 * <p>获取仓库的计价方式</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIWH01.java
 * @ClassName:  ServiceCIWH01
 * @Package com.baosight.wilp.ci.wh.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 上午9:08:37 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIWH01 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		wareHouseName：仓库名称
	 * 		freezeFlag： 冻结标记
	 * @return 
	 * 		id ：主键
	 *		wareHouseNo : 仓库号
	 *		wareHouseName : 仓库名称
	 *		wareHouseType : 仓库类型
	 *		freezeFlag : 冻结
	 *		recCreator : 创建人
	 *		recCreateTime : 创建时间  
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
    	inInfo.set("inqu_status-0-dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "CIWH01.query", new CiWarehouse());
        return outInfo;

    }
    
    /**
     * 查询仓库（无分页）
     * @Title: queryWareHouse
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wareHouseName：仓库名称
	 * 		freezeFlag： 冻结标记
     * @param:  @return
     * @return: EiInfo 
     * 		id ：主键
	 *		wareHouseNo : 仓库号
	 *		wareHouseName : 仓库名称
	 *		wareHouseType : 仓库类型
	 *		freezeFlag : 冻结
	 *		recCreator : 创建人
	 *		recCreateTime : 创建时间 
     * @throws
     */
    @SuppressWarnings("unchecked")
	public EiInfo queryWareHouse(EiInfo inInfo) {
    	Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, "inqu_status", "result");
    	map.put("dataGroupCode",  CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        List<CiWarehouse> list = dao.query("CIWH01.query", map);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiWarehouse().eiMetadata, list, list.size());
    }

    /**
     * 删除仓库
     * 
     * <p>1.获取参数,判断仓库中是否存在物资</br>
     * 	  2.存在，提示错误信息;不存在，删除
     * </p>
     * 
     * <p>Title: delete</p>   
     * <p>Description: </p>   
     * @param inInfo
     * 		id:主键
     * 		wareHouseNo ： 仓库号
     * @return   
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo delete(EiInfo inInfo) {
		//获取参数
    	EiBlock block = inInfo.getBlock("result");
    	 Map<String, Object> pMap = (Map<String, Object>) block.getRows().get(0);
    	 //判断仓库中是否存在物资
    	 int count = super.count("CIWH01.isExistMat",pMap);
    	 if(count > 0){//存在，提示错误信息
    		 inInfo.setMsg("仓库中存在耗材，无法删除");
    		 return inInfo;
    	 }
    	 //不存在，删除
        return super.delete(inInfo, "CIWH01.delete");
    }
    
	/**
	 * 冻结/解冻仓库
	 * 
	 * <p>1.获取参数,判断是冻结操作还是解冻操作</br>
     * 	  2.冻结操作，将冻结标记设为"Y";解冻操作,将冻结标记设为"N"
     * </p>
	 * 
	 * @Title: djOrJd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		id:主键
	 * 		type ： 操作类型，dj=冻结;jd=解冻
	 * @param:  @return
	 * @return: EiInfo : 无 
	 * @throws
	 */
    public EiInfo djOrJd(EiInfo inInfo) {
    	//获取参数
         CiWarehouse kcst01 = new CiWarehouse();
         kcst01.setRecReviseTime(DateUtils.curDateTimeStr19());
         kcst01.setRecCreator(UserSession.getUser().getUsername());
         kcst01.setId(inInfo.getString("id"));
         kcst01.setWareHouseNo(inInfo.getString("wareHouseNo"));
        //冻结
        if(inInfo.getAttr().get("type").toString().equals("dj")) {
            kcst01.setFreezeFlag("Y");
        }else {//解冻
            kcst01.setFreezeFlag("N");
        }
        dao.update("CIWH01.updateWarehouse", kcst01);
        return inInfo;
    }
    
    /**
     * 校验仓库是否正常
     * @Title: isCheckWareHouse
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wareHouseNo:仓库号
     * @param:  @return
     * @return: EiInfo 
     * 		isCheck : 是否正常标记，true=正常，false=不正常（不存在、冻结）
     * @throws
     */
    public EiInfo isCheckWareHouse(EiInfo inInfo) {
    	String wareHouseNo = inInfo.getString("wareHouseNo");
    	int count = super.count("CIWH01.isCheckWareHouse", wareHouseNo);
    	if(count > 0){
    		inInfo.set("isCheck", "true");
    	} else {
    		inInfo.set("isCheck", "false");
    	}
    	return inInfo;
    }
    
    /**
     * 获取仓库的计价方式
     * @Title: getPriceType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wareHouseNo:仓库号
     * @param:  @return
     * @return: EiInfo
     * 		priceType : 计价方式  
     * @throws
     */
    @SuppressWarnings("unchecked")
	public EiInfo getPriceType (EiInfo inInfo){
    	Map<String, String> pMap = new HashMap<String, String>();
    	pMap.put("wareHouseNo", inInfo.getString("wareHouseNo"));
    	List<CiWarehouse> list = dao.query("CIWH01.query", pMap);
    	CiWarehouse warehouse = list.get(0);
    	inInfo.set("priceType", warehouse.getPriceType());
    	return inInfo;
    }


	/**
	 * 手持机端是否显示食堂进销存的入口
	 * @Title  handSetShowCI
	 * @author liu
	 * @date 2022-09-23 17:47
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo handSetShowCI(EiInfo inInfo) {
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId, "S_AC_FW_21");
		eiInfo.set("fkey", "ci_handset_show");
		return XServiceManager.call(eiInfo);
	}
    

}
