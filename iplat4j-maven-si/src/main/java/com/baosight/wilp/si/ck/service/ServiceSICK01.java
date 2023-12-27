package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 出库管理页面Service
 *
 * <p>页面加载</p>
 * <p>页面查询</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSICK01.java
 * @ClassName:  ServiceSICK01
 * @Package com.baosight.wilp.si.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:03
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSICK01 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
        SiUtils.initQueryTime(inInfo, false);
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
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
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
    	inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
        EiInfo outInfo = super.query(inInfo, "SICK01.query", new SiOut());
        return outInfo;
    }

	/**
	 * 更新打印标记
	 * @Title: updatePrintFlag
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo updatePrintFlag(EiInfo inInfo) {
		String outBillNos = inInfo.getString("outBillNos");
		if(StringUtils.isNotBlank(outBillNos)) {
			List<String> list = Arrays.asList(outBillNos.split(","));
			dao.update("SICK01.updatePrintFlag", list);
		}
		return inInfo;
	}

	/**
	 * 更新出库单合计金额
	 * @Title: updateTotalAmount
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo updateTotalAmount(EiInfo inInfo) {
		if(StringUtils.isNotBlank(inInfo.getString("outBillNo"))) {
			dao.update("SICK01.updateTotalAmount", inInfo.getString("outBillNo"));
		}
		return inInfo;
	}
}
