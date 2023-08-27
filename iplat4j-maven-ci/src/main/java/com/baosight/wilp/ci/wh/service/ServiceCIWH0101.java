package com.baosight.wilp.ci.wh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.wh.domain.CiWarehouse;
import com.baosight.xservices.xs.util.UserSession;

/**
 *  仓库管理子页面Service
 * 
 * <p>页面加载</p>
 * <p>新增/编辑仓库</p>
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
public class ServiceCIWH0101 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		if("see".equals(inInfo.getString("type"))){
			Map<String, String> map = new HashMap<>();
			map.put("id", inInfo.getString("id"));
			List<CiWarehouse> list = dao.query("CIWH01.query", map);
			CiWarehouse warehouse = list.get(0);
			inInfo.getAttr().putAll(warehouse.toMap());
		}
        return inInfo;
    }

	/**
	 * 新增/编辑仓库
	 * 
	 * <p>1.获取参数,判断是新增还是编辑操作</br>
     * 	  2.新增操作，判断仓库号是否存在</br>
     * 	  3.仓库号存在，提示错误信息</br>
     * 	  4.仓库号不存在，保存仓库信息</br>
     *    5.编辑操作，更新仓库信息
     * </p>
	 * 
	 * <p>Title: insert</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		id:主键
	 * 		type:操作类型 add=新增，edit=编辑
	 * 		wareHouseNo:仓库号
	 * 		wareHouseName:仓库名称
	 * 		wareHouseType:仓库类型
	 * 		priceType:计价方式
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo insert(EiInfo inInfo) {
   	 	//封装参数
   	 	CiWarehouse kcst01 = new CiWarehouse();
		//仓库名称
        kcst01.setWareHouseName(inInfo.getString("wareHouseName"));
		//仓库类型（小代码）
        kcst01.setWareHouseType(inInfo.getString("wareHouseType"));
		//计价方式
        kcst01.setPriceType(inInfo.getString("priceType"));
        //新增
        if ("add".equals(inInfo.getString("type"))) {

			//调用服务生成仓库编码
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pix", "CK");
			paramMap.put("leng", "6");
			paramMap.put("serialNum", "warehouse");
			EiInfo call = CiUtils.call("CIWHTyTserial", "getSerialIndex", paramMap);
			if(call.getStatus() < 0) {
				inInfo.setMsg("获取食堂编码失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}else {
				//保存仓库信息
				//获取仓库编码
				String wareHouseNo = call.getAttr().get("serialIndex").toString();;
				kcst01.setWareHouseNo(wareHouseNo);
				kcst01.setId(UUID.randomUUID().toString());
				//冻结标记
				kcst01.setFreezeFlag("N");
				kcst01.setRecCreateTime(DateUtils.curDateTimeStr19());
				kcst01.setRecCreator(UserSession.getUser().getUsername());
				kcst01.setDataGroupCode(CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
				dao.insert("CIWH01.insert", kcst01);
				return inInfo;
			}
        } else {
			//编辑
        	kcst01.setId(inInfo.getString("id"));
            kcst01.setRecReviseTime(DateUtils.curDateTimeStr19());
            kcst01.setRecRevisor(UserSession.getUser().getUsername());
            dao.update("CIWH01.update", kcst01);
            return inInfo;
        }
	}

}
