package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContInvoice;
import com.baosight.wilp.mp.lj.domain.MpContPay;
import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;
import com.baosight.wilp.mp.lj.service.MpContPayService;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpInvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 采购付款管理页面
 * <p>1.初始化查询 initLoad
 * @Title: ServiceMPHT03.java
 * @ClassName: ServiceMPHT03
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除付款信息
 * 4.付款完成
 */

public class ServiceMPHT03 extends ServiceBase {

	@Autowired
	private MpContPayService payService;

	@Autowired
	private MpInvoiceService invoiceService;

	@Autowired
	private MpContractService contractService;
	
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
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
		inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
		return query(inInfo);
    }

	/**
	 * 页面数据查询
	 * @Title: query
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo query(EiInfo inInfo) {
		return super.query(inInfo, "MPLJ07.query", new MpContPay());
	}

	/**
	 * 删除
	 * <p>Title: payment</p>
	 * <p>Description: </p>
	 * @param
	 * @return
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		// 1.参数处理
		String id = inInfo.getString("id");
		if(StringUtils.isBlank(id)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//删除, 如果删除成功，更新发票表, 将发票表付款Id清空
		int delete = payService.delete(id);
		if(delete > 0) {
			invoiceService.clearPayId(id);
		}
		return inInfo;
	}

	/**
	 * 付款完成
	 * <p>1.参数处理
	 * 	  2.更新付款状态
	 * 	  3.更新发票状态
	 * 	  4.更新合同中的已付款数量和已付款金额
	 * </p>
	 * @param
	 * @return
	 */
	public EiInfo payment(EiInfo inInfo) {
		/**1.参数处理**/
		String id = inInfo.getString("id");
		if(StringUtils.isBlank(id)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		/**2.数据校验**/
		MpContPay pay = payService.queryContPay(id);
		if(pay == null || !MpConstant.PAY_STATUS_NEW.equals(pay.getStatusCode())) {
			return ValidatorUtils.errorInfo("已付款完成,无需再次完成付款");
		}
		//校验发票金额是否等于付款金额
		List<MpContInvoice> invoices = invoiceService.queryContInvoiceByPayId(id);
		BigDecimal totalCost = invoices.stream().map(MpContInvoice::getInvoiceCost).reduce(BigDecimal.ZERO, BigDecimal::add);
		if(totalCost.compareTo(pay.getPayCost()) != 0) {
			return ValidatorUtils.errorInfo("付款金额与发票总金额不等。付款金额:"+pay.getPayCost() +"元,发票总金额:"+totalCost+"元");
		}
		/**3.更新付款状态**/
		payService.update(MpContPay.getStatusInstance(id, MpConstant.PAY_STATUS_OVER));
		/**4.更新发票状态**/
		invoiceService.updateByPayId(id);
		/**5.更新合同中的已付款数量和已付款金额**/
		List<MpInvoiceDetail> details = invoiceService.queryInvoiceDetailByPayId(id);
		contractService.updatePayNum(details);
		return inInfo;
	}



}
