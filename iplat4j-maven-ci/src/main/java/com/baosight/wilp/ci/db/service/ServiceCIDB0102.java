package com.baosight.wilp.ci.db.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;

/**
 * 库存调拨物资选择子页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIDB0102.java
 * @ClassName:  ServiceCIDB0102
 * @Package com.baosight.wilp.ci.db.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月20日 下午1:32:51 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIDB0102 extends ServiceBase {
	
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
	 * 		outWareHouseNo：仓库号
	 * 		matTypeNum： 物资分类
	 * 		matNum：物资编码
	 * 		matName：物资名称
	 * 		isNot0 ： 是否显示0库存
	 * @return  
	 * 		wareHouseName : 仓库
	 *		wareHouseNo : 仓库号
	 *		matTypeName : 物资分类名称
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unitName : 计量单位
	 *		totalNum : 库存总量
	 *		totalAmount : 库存总价
	 *		minNum : 最低存量
	 *		maxNum : 最高存量 
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		if(!"app".equals(inInfo.getString("mark"))){
			inInfo.set("inqu_status-0-wareHouseNo", inInfo.get("inqu_status-0-outWareHouseNo"));
			inInfo.set("inqu_status-0-matNumEQ", inInfo.get("inqu_status-0-matNumEQ"));
		}else {
			inInfo.set("inqu_status-0-wareHouseNo", inInfo.get("outWareHouseNo"));
			inInfo.set("inqu_status-0-matNumEQ", inInfo.get("matNumEQ"));
		}
		EiInfo outInfo = CiUtils.invoke(inInfo, "CIKC01", "query", null);
        return outInfo;
    }

}
