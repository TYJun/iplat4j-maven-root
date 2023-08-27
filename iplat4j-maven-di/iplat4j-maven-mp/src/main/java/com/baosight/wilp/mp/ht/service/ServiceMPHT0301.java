package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.SerialNoUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContInvoice;
import com.baosight.wilp.mp.lj.domain.MpContPay;
import com.baosight.wilp.mp.lj.service.MpContPayService;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpInvoiceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * 采购付款管理子页面
 * <p>1.初始化查询 initLoad
 * @Title: ServiceMPHT0301.java
 * @ClassName: ServiceMPHT0301
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 * 1.页面加载
 * 2.保存付款信息
 */

public class ServiceMPHT0301 extends ServiceBase {

	@Autowired
	private MpContPayService payService;

	@Autowired
	private MpInvoiceService invoiceService;
	
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
		if(!MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
			//获取付款信息
			List<MpContPay> list = dao.query("MPLJ07.query", new HashMap(2) {{
				put("id", inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
			}});
			inInfo.setRows(MpConstant.QUERY_BLOCK, list);
			//获取发票信息
			List<MpContInvoice> invoices = dao.query("MPLJ05.query", new HashMap(2) {{
				put("payId", inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
			}});
			inInfo.setRows(MpConstant.RESULT_BLOCK, invoices);
		}
		return inInfo;
    }

	/**
	 * 保存付款信息
	 * @Title: save
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo save(EiInfo info) {
		//参数处理
		MpContPay pay = new MpContPay();
		pay.fromMap(info.getRow(MpConstant.QUERY_BLOCK, 0));
		List<String> invoiceIds = MpUtils.toList(info.get("list"), String.class);
		//参数校验
		EiInfo validateInfo = ValidatorUtils.validateEntity(pay);
		if(validateInfo.getStatus() == -1) { return validateInfo; }
		if(CollectionUtils.isEmpty(invoiceIds)) {
			return ValidatorUtils.errorInfo("发票信息不能为空");
		}
		//判断是新增还是编辑
		if(MpConstant.OPERATE_TYPE_ADD.equals(info.getString(MpConstant.OPERATE_NAME))){
			pay.setId(UUID.randomUUID().toString());
			pay.setPayNo(SerialNoUtils.generateSerialNo("mp_cont_pay", "FK"));
			Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
			pay.setDeptNum(MpUtils.toString(deptMap.get("deptNum")));
			pay.setDeptName(MpUtils.toString(deptMap.get("deptName")));
			pay.setStatusCode(MpConstant.PAY_STATUS_NEW);
			pay.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.cont.payStatus", MpConstant.PAY_STATUS_NEW));
			pay.setRecCreator(UserSession.getLoginName());
			pay.setRecCreatorName(UserSession.getLoginCName());
			pay.setRecCreateTime(new Date());
			payService.insert(pay);
		} else {
			pay.setRecRevisor(UserSession.getLoginName());
			pay.setRecReviseTime(new Date());
			payService.update(pay);
			invoiceService.clearPayId(pay.getId());
		}
		//在发票表中插入付款ID
		invoiceService.addPayId(pay.getId(), invoiceIds);
		return info;
	}

}
