package com.baosight.wilp.si.wh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.wh.domain.SiWarehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @Title:  ServiceSIWH01.java
 * @ClassName:  ServiceSIWH01
 * @Package com.baosight.wilp.si.wh.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 上午9:08:37 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIWH01 extends ServiceBase {
	
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
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
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
		//inInfo.set("inqu_status-0-workNo", UserSession.getUser().getUsername());
    	inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
        EiInfo outInfo = super.query(inInfo, "SIWH01.query", new SiWarehouse());
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
    	map.put("dataGroupCode",  SiUtils.getDataGroupCode(UserSession.getLoginName()));
		map.put("workNo", SiUtils.isEmpty(inInfo.getString("workNo"), UserSession.getLoginName()));
        List<SiWarehouse> list = dao.query("SIWH01.query", map);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new SiWarehouse().eiMetadata, list, list.size());
    }



	/**
	 *  查询仓库:过滤冻结的
	 * @Title: selectUseWareHouse

 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo selectUseWareHouse(EiInfo inInfo) {
		inInfo.setCell(EiConstant.queryBlock, 0, "freezeFlag", "N");
		return  queryWareHouse(inInfo);
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
    	 int count = super.count("SIWH01.isExistMat",pMap);
		//存在，提示错误信息
    	 if(count > 0){
    		 inInfo.setMsg("仓库中存在耗材，无法删除");
    		 return inInfo;
    	 }
    	 //不存在，删除
        return super.delete(inInfo, "SIWH01.delete");
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
         SiWarehouse kcst01 = new SiWarehouse();
         kcst01.setRecReviseTime(DateUtils.curDateTimeStr19());
         kcst01.setRecCreator(UserSession.getLoginName());
         kcst01.setId(inInfo.getString("id"));
         kcst01.setWareHouseNo(inInfo.getString("wareHouseNo"));
        //冻结
        if(inInfo.getAttr().get("type").toString().equals("dj")) {
            kcst01.setFreezeFlag("Y");
        }else {//解冻
            kcst01.setFreezeFlag("N");
        }
        dao.update("SIWH01.updateWarehouse", kcst01);
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
    	int count = super.count("SIWH01.isCheckWareHouse", wareHouseNo);
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
    	List<SiWarehouse> list = dao.query("SIWH01.query", pMap);
    	SiWarehouse warehouse = list.get(0);
    	inInfo.set("priceType", warehouse.getPriceType());
    	return inInfo;
    }
    

}
