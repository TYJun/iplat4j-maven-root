package com.baosight.wilp.ci.ck.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCICK01.java
 * @ClassName:  ServiceCICK01
 * @Package com.baosight.wilp.ci.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:03 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCICK01 extends ServiceBase {
	
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
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 100);
		inInfo.addBlock("processsResult").set(EiConstant.limitStr, 100);
		return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		outBillNo:出库单号
	 * 		outType:出库类别
	 * 		userDeptName:领用科室
	 * 		beginTime:制单日期起(>=)
	 * 		endTime:制单日期止(<=)
	 * @return
	 * 		id : 主键
	 *		outBillNo : 出库单号
	 *		outTypeName : 出库类别
	 *		originBillNo : 来源单据号
	 *		wareHouseName : 仓库
	 *		userDeptName : 领用科室
	 *		billMakeTime : 制单日期
	 *		billMakerName : 制单人员   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.setCell(EiConstant.queryBlock, 0, "isCheck", 0);
    	inInfo.set("inqu_status-0-dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "CICK01.query", new CiOut());
		List<Map<String, Object>> list = dao.query("CICK01.queryItem", new HashMap(2) {{
			put("outBillNo", inInfo.getAttr().get("mainOutBillNo"));
		}});
		System.out.println("outBillNo:"+inInfo.getAttr().get("mainOutBillNo"));
		outInfo.setRows("processsResult", list);
//		System.out.println("list:"+list);
        return outInfo;
    }

}
