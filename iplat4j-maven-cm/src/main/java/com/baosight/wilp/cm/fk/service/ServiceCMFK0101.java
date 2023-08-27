package com.baosight.wilp.cm.fk.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.CmPayment;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServiceCMFK0101 extends ServiceBase {
	/**
	 * 初始化方法
	 * @author zhaowei
	 * @date 2022/2/11 13:57
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		String type = (String) info.getAttr().get("type");
		String paymentAutoNo = (String) info.getAttr().get("paymentAutoNo");
		// 判读是否是新增
		if (type.equals("new")) {
			return info;
		} else {
			// 判断是否是查看
			if (type.equals("look")) {
				info.set("type", "look");
			}
			// 调用查询方法
			List<Map<String, Object>> query = dao.query("CMFK01.queryPaymentMsgByPaymentAutoNo", paymentAutoNo);
			if (CollectionUtils.isNotEmpty(query)) {
				Map<String, Object> paymentMsg = query.get(0);
				info.set("contPk", paymentMsg.get("contPk"));
				info.set("paymentNo", paymentMsg.get("paymentNo"));
				info.set("paymentContent", paymentMsg.get("paymentContent"));
				// 构建Block
				EiBlock block = new EiBlock("inqu_status");
				// 特殊数据处理
				EiColumn column1 = new EiColumn("contNo_textField");
				EiColumn column2 = new EiColumn("contNo");
				EiColumn column3 = new EiColumn("invoiceAutoNo_textField");
				EiColumn column4 = new EiColumn("invoiceAutoNo");
				block.addMeta(column1);
				block.addMeta(column2);
				block.addMeta(column3);
				block.addMeta(column4);
				block.setCell(0, "contNo_textField", (String) paymentMsg.get("contName") + "-" + paymentMsg.get("contNo"));
				block.setCell(0, "contNo", paymentMsg.get("contNo"));
				block.setCell(0, "invoiceAutoNo_textField", paymentMsg.get("invoiceNo") + "-" + paymentMsg.get("invoiceAutoNo"));
				block.setCell(0, "invoiceAutoNo", paymentMsg.get("invoiceAutoNo"));
				info.addBlock(block);
				info.set("invoiceType", paymentMsg.get("invoiceType"));
				info.set("paymentTime", paymentMsg.get("paymentTime"));
				info.set("currType", paymentMsg.get("currType"));
				info.set("paymentTaxExcludeAmount", paymentMsg.get("paymentTaxExcludeAmount"));
				info.set("paymentTaxAmount", paymentMsg.get("paymentTaxAmount"));
				info.set("remark", paymentMsg.get("remark"));
			}
			return info;
		}
	}

	/**
	 * 付款信息保存
	 * @author zhaowei
	 * @date 2022/2/11 13:57
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo save(EiInfo info) {
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//获取表单参数
		EiBlock block = info.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		//保存项目
		CmPayment cmPayment = new CmPayment();
		cmPayment.fromMap(param);
		/*
		 * 判断是否拥有付款管理号
		 * 如果存在付款管理号说明是编辑
		 * 如果不存在付款合同管理号说明是新增
		 */
		if (StringUtils.isEmpty((String) info.getAttr().get("paymentAutoNo"))) {
			cmPayment.setId(UUID.randomUUID().toString());
			cmPayment.setPaymentNo((String) info.getAttr().get("paymentNo"));
			cmPayment.setPaymentContent((String) info.getAttr().get("paymentContent"));
			cmPayment.setInvoiceAutoNo((String) param.get("invoiceAutoNo"));
			cmPayment.setContNo((String) param.get("contNo"));
			cmPayment.setPaymentAutoNo(CreateNoUtils.createNo("FK"));
			cmPayment.setContPk((String) info.getAttr().get("contPk"));
			cmPayment.setCurrType((String) info.getAttr().get("currType"));
			cmPayment.setPaymentType((String) info.getAttr().get("paymentType"));
			cmPayment.setPaymentTaxAmount(Double.valueOf(StringUtils.isNotEmpty((String)info.getAttr().get("paymentTaxAmount"))?(String)info.getAttr().get("paymentTaxAmount"):"0.0"));
			cmPayment.setPaymentTaxExcludeAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("paymentTaxExcludeAmount"))?(String) info.getAttr().get("paymentTaxExcludeAmount"):"0.0"));
			cmPayment.setPaymentTime((String) info.getAttr().get("paymentTime"));
			cmPayment.setRecCreator((String) staffByUserId.get("name"));
			cmPayment.setRecCreatorNo((String) staffByUserId.get("workNo"));
			cmPayment.setRecCreateTime(DateUtils.curDateTimeStr19());
			cmPayment.setRemark((String) info.getAttr().get("remark"));
			cmPayment.setPaymentStatus("1");
			// 调用保存方法
			dao.insert("CMFK01.save", cmPayment);
		} else {
			cmPayment.setInvoiceAutoNo((String) param.get("invoiceAutoNo"));
			cmPayment.setPaymentNo((String) info.getAttr().get("paymentNo"));
			cmPayment.setPaymentContent((String) info.getAttr().get("paymentContent"));
			cmPayment.setPaymentAutoNo((String) info.getAttr().get("paymentAutoNo"));
			cmPayment.setContPk((String) info.getAttr().get("contPk"));
			cmPayment.setCurrType((String) info.getAttr().get("currType"));
			cmPayment.setPaymentType((String) info.getAttr().get("paymentType"));
			cmPayment.setPaymentTaxAmount(Double.valueOf(StringUtils.isNotEmpty((String)info.getAttr().get("paymentTaxAmount"))?(String)info.getAttr().get("paymentTaxAmount"):"0.0"));
			cmPayment.setPaymentTaxExcludeAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("paymentTaxExcludeAmount"))?(String) info.getAttr().get("paymentTaxExcludeAmount"):"0.0"));
			cmPayment.setPaymentTime((String) info.getAttr().get("paymentTime"));
			cmPayment.setRecRevisor((String) staffByUserId.get("name"));
			cmPayment.setRecRevisorNo((String) staffByUserId.get("workNo"));
			cmPayment.setRecReviseTime(DateUtils.curDateTimeStr19());
			cmPayment.setRemark((String) info.getAttr().get("remark"));
			cmPayment.setPaymentStatus("2");
			// 调用更新方法
			dao.insert("CMFK01.update", cmPayment);
		}
		return info;
	}
}
