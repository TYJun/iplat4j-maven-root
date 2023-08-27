package com.baosight.wilp.ci.db.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.db.domain.CiTrans;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 库存调拨页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>保存调拨主表</p>
 * <p>更新调拨主表</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIDB01.java
 * @ClassName:  ServiceCIDB01
 * @Package com.baosight.wilp.ci.db.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月20日 上午10:36:16 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIDB01 extends ServiceBase {
	
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
	 * 		outWareHouseNo:调入仓库
	 * 		inWareHouseNo:调出仓库
	 * 		beginTime:制单日期起
	 * 		endTime:制单日期止 
	 * @return 
	 * 		transBillNo : 调拨单号
	 *		inWareHouseName : 调出仓库
	 *		outWareHouseName : 调入仓库
	 *		billMakeTime : 制单日期
	 *		billMaker : 制单人员  
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
    	inInfo.set("inqu_status-0-dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "CIDB01.query", new CiTrans());
        return outInfo;
    }
	
	/**
	 * 保存调拨主表
	 * @Title: insertTrans
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		trans ： 调拨主表实体对象
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo insertTrans(EiInfo inInfo) {
		//获取参数
		CiTrans trans = inInfo.get("trans") == null ? null : (CiTrans)inInfo.get("trans");
		//判断参数是否为空
		if(trans == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("插入调拨数据失败"); 
			return inInfo;
		} else {
			dao.insert("CIDB01.insert", trans);
		}
        return inInfo;
	}
	
	/**
	 * 更新调拨主表
	 * @Title: updateTrans
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		trans ： 调拨主表实体对象
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo updateTrans(EiInfo inInfo) {
		//获取参数
		CiTrans trans = inInfo.get("trans") == null ? null : (CiTrans)inInfo.get("trans");
		//判断参数是否为空
		if(trans == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("更新调拨数据失败");
			return inInfo;
		} else {
			dao.update("CIDB01.update", trans);
		}
        return inInfo;
	}

}
