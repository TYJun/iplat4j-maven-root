package com.baosight.wilp.cm.fp.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.CmInvoice;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public class ServiceCMFP0101 extends ServiceBase {
	/**
	 * 合同发票管理初始化
	 * @author zhaowei
	 * @date 2022/2/11 13:58
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		String type = (String) info.getAttr().get("type");
		String invoiceAutoNo = (String) info.getAttr().get("invoiceAutoNo");
		if (type.equals("new")) {
			return info;
		} else {
			if (type.equals("look")) {
				info.set("type", "look");
			}
			List<Map<String, Object>> query = dao.query("CMFP01.queryInvoiceMsgByInvoiceAutoNo", invoiceAutoNo);
			if (CollectionUtils.isNotEmpty(query)) {
				Map<String, Object> invoiceMsg = query.get(0);
				info.set("contPk", invoiceMsg.get("contPk"));
				info.set("invoiceNo", invoiceMsg.get("invoiceNo"));
				info.set("invoiceAutoNo", invoiceMsg.get("invoiceAutoNo"));
				// 构建Block
				EiBlock block = new EiBlock("inqu_status");
				// 特殊数据处理
				EiColumn column1 = new EiColumn("contNo_textField");
				EiColumn column2 = new EiColumn("contNo");
				EiColumn column3 = new EiColumn("surpNum_textField");
				EiColumn column4 = new EiColumn("surpNum");
				block.addMeta(column1);
				block.addMeta(column2);
				block.addMeta(column3);
				block.addMeta(column4);
				block.setCell(0, "contNo_textField", (String) invoiceMsg.get("contName") + "-" + invoiceMsg.get("contNo"));
				block.setCell(0, "contNo", invoiceMsg.get("contNo"));
				block.setCell(0, "surpNum_textField", invoiceMsg.get("surpName"));
				block.setCell(0, "surpNum", invoiceMsg.get("surpNum"));
				info.addBlock(block);
				info.set("invoiceType", invoiceMsg.get("invoiceType"));
				info.set("invoiceIssuingTime", invoiceMsg.get("invoiceIssuingTime"));
				info.set("currType", invoiceMsg.get("currType"));
				info.set("invoiceTaxExcludeAmount", invoiceMsg.get("invoiceTaxExcludeAmount"));
				info.set("invoiceTaxAmount", invoiceMsg.get("invoiceTaxAmount"));
				info.set("remark", invoiceMsg.get("remark"));
			}
			return info;
		}
	}

	/**
	 * 查询合同模块中所有的合同
	 * @author zhaowei
	 * @date 2022/2/11 13:58
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryCmMsg(EiInfo info) {
		int offset, limit;
		// 判断是否分页
		if (info.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) info.getBlocks().get("resultA");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		HashMap<String, Object> map = new HashMap<>();
		String contNo = (String) info.get("inqu_status-0-contNoWindow");
		String contName = (String) info.get("inqu_status-0-contNameWindow");
		map.put("contNo", contNo);
		map.put("contName", contName);
		// 查询合同模块中所有的合同
		List<Map<String, Object>> queryCmMsgList = dao.query("CMFP01.queryCmMsg", map, offset, limit);
		int count = dao.count("CMFP01.queryCmMsg", map);
		info.addRows("resultA", queryCmMsgList);
		// 处理分页
		EiBlock result = (EiBlock) info.getBlocks().get("resultA");
		result.setAttr(new HashMap<>());
		if (result.getAttr().isEmpty()) {
			Map<String, Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return info;
	}

	/**
	 * 查看合同模块中所有发票
	 * @author zhaowei
	 * @date 2022/2/11 13:59
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryCmFPMsg(EiInfo info) {
		int offset, limit;
		// 判断是否分页
		if (info.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) info.getBlocks().get("resultB");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		HashMap<String, Object> map = new HashMap<>();
		String invoiceAutoNo = (String) info.get("inqu_status-0-invoiceAutoNoWindow");
		String invoiceNo = (String) info.get("inqu_status-0-invoiceNoWindow");
		map.put("invoiceAutoNo", invoiceAutoNo);
		map.put("invoiceNo", invoiceNo);
		// 查询合同模块中所有的合同
		List<Map<String, Object>> queryCmFPMsgList = dao.query("CMFP01.queryCmFPMsg", map, offset, limit);
		int count = dao.count("CMFP01.queryCmFPMsg", map);
		info.addRows("resultB", queryCmFPMsgList);
		// 处理分页
		EiBlock result = (EiBlock) info.getBlocks().get("resultB");
		result.setAttr(new HashMap<>());
		if (result.getAttr().isEmpty()) {
			Map<String, Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return info;
	}


	/**
	 * 保存合同发票信息
	 * @author zhaowei
	 * @date 2022/2/11 13:59
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
		CmInvoice cmInvoice = new CmInvoice();
		cmInvoice.fromMap(param);
		if (StringUtils.isEmpty((String) info.getAttr().get("invoiceAutoNo"))) {
			cmInvoice.setId(UUID.randomUUID().toString());
			cmInvoice.setInvoiceNo((String) info.getAttr().get("invoiceNo"));
			cmInvoice.setInvoiceAutoNo(CreateNoUtils.createNo("FP"));
			cmInvoice.setContPk((String) info.getAttr().get("contPk"));
			cmInvoice.setCurrType((String) info.getAttr().get("currType"));
			cmInvoice.setInvoiceType((String) info.getAttr().get("invoiceType"));
			cmInvoice.setSurpName((String) param.get("surpNum_textField"));
			cmInvoice.setInvoiceTaxAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("invoiceTaxAmount")) ? (String) info.getAttr().get("invoiceTaxAmount") : "0.0"));
			cmInvoice.setInvoiceTaxExcludeAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("invoiceTaxExcludeAmount")) ? (String) info.getAttr().get("invoiceTaxExcludeAmount") : "0.0"));
			cmInvoice.setInvoiceIssuingTime((String) info.getAttr().get("invoiceIssuingTime"));
			cmInvoice.setRecCreator((String) staffByUserId.get("name"));
			cmInvoice.setRecCreatorNo((String) staffByUserId.get("workNo"));
			cmInvoice.setRecCreateTime(DateUtils.curDateTimeStr19());
			cmInvoice.setRemark((String) info.getAttr().get("remark"));
			cmInvoice.setInvoiceStatus("0");
			dao.insert("CMFP01.save", cmInvoice);
		} else {
			cmInvoice.setInvoiceNo((String) info.getAttr().get("invoiceNo"));
			cmInvoice.setInvoiceAutoNo((String) info.getAttr().get("invoiceAutoNo"));
			cmInvoice.setContPk((String) info.getAttr().get("contPk"));
			cmInvoice.setCurrType((String) info.getAttr().get("currType"));
			cmInvoice.setInvoiceType((String) info.getAttr().get("invoiceType"));
			cmInvoice.setSurpName((String) param.get("surpNum_textField"));
			cmInvoice.setInvoiceTaxAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("invoiceTaxAmount")) ? (String) info.getAttr().get("invoiceTaxAmount") : "0.0"));
			cmInvoice.setInvoiceTaxExcludeAmount(Double.valueOf(StringUtils.isNotEmpty((String) info.getAttr().get("invoiceTaxExcludeAmount")) ? (String) info.getAttr().get("invoiceTaxExcludeAmount") : "0.0"));
			cmInvoice.setInvoiceIssuingTime((String) info.getAttr().get("invoiceIssuingTime"));
			cmInvoice.setRecRevisor((String) staffByUserId.get("name"));
			cmInvoice.setRecRevisorNo((String) staffByUserId.get("workNo"));
			cmInvoice.setRecReviseTime(DateUtils.curDateTimeStr19());
			cmInvoice.setRemark((String) info.getAttr().get("remark"));
			cmInvoice.setInvoiceStatus("0");
			dao.insert("CMFP01.update", cmInvoice);
		}
		return info;
	}

	/**
	 * 删除
	 * @author zhaowei
	 * @date 2022/2/11 13:59
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo deleter(EiInfo info) {
		String invoiceAutoNo = (String) info.getAttr().get("invoiceAutoNo");
		dao.delete("CMFP01.deleter", invoiceAutoNo);
		return info;
	}

	/**
	 * 申请付款
	 * @author zhaowei
	 * @date 2022/2/11 13:59
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo reqPayment(EiInfo info) {
		String invoiceAutoNo = (String) info.getAttr().get("invoiceAutoNo");
		dao.update("CMFP01.reqPayment", invoiceAutoNo);
		return info;
	}
}
