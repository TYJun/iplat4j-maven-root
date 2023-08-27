package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContract;
import com.baosight.wilp.mp.lj.domain.MpContractDetail;
import com.baosight.wilp.mp.lj.domain.MpContractFile;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 该页面为采购合同子页面
 * 主要包含对采购合同的新增、编辑功能
 * @Title: ServiceMPHT0101.java
 * @ClassName: ServiceMPHT0101
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月17日 下午1:23:38
 * @version: V5.0.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceMPHT0101 extends ServiceBase {

	@Autowired
	private MpContractService contractService;

	@Autowired
	private MpProcurementPlanService purchasePlanService;

	/**
	 * 页面加载
	 * <p>编辑或查看时,回显数据</p>
	 * @Title: initLoad
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
			Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
			inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "manageDeptNum", deptMap.get("deptNum"));
			inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "manageDeptName", deptMap.get("deptName"));
			inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "managerNum", UserSession.getLoginName());
			inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "managerName", UserSession.getLoginCName());
		} else {
			//获取合同信息
			String id = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id");
			List<MpContract> list = dao.query("MPLJ02.query", inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
			inInfo.setRows(MpConstant.QUERY_BLOCK, list);
			//获取合同明细及关联的采购计划明细
			List<Map<String, Object>> details = dao.query("MPLJ02.queryDetailUnionPurchaseDetail", id);
			inInfo.setRows("detail", details);
			//获取合同附件
			List<MpContractFile> files = contractService.queryFileList(id);
			inInfo.setRows("file", files);
		}
		inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
		inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
		return inInfo;
	}

	/**
	 * 合同保存
	 * <p>
	 *     1.参数处理
	 *     2.参数校验。页面必填参数非空校验、合同明细非空校验
	 *     3.判断是新增还是编辑
	 *     	3.1.新增, 保存合同、合同明细、合同附件
	 *     		3.1.1校验合同是否已存在.当合同号、合同名称、供应商相同时,则为合同存在
	 *     		3.1.2参数赋值
	 *     		3.1.3保存合同主单据信息
	 *      3.2.编辑, 更新合同、合同明细、合同附件
	 *      	3.2.1校验合同是否可以编辑,合同生效后不可编辑
	 *      	3.2.2参数赋值
	 *      	3.2.3合同更新及删除明细和附件
	 *    4.保存明细及附件
	 *    5.更新采购计划明细中已生成合同数量
	 * </p>
	 * @Title: save
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo save(EiInfo inInfo) {
		/**1.参数处理**/
		MpContract contract = new MpContract();
		contract.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
		List<MpContractDetail> details = MpUtils.toList(inInfo.get("detailList"), MpContractDetail.class);
		List<MpContractFile> files = MpUtils.toList(inInfo.get("fileList"), MpContractFile.class);

		/**2.参数校验**/
		EiInfo validateInfo = ValidatorUtils.validateEntity(contract);
		if(validateInfo.getStatus() < 0) {
			return validateInfo;
		}
		details = details.stream().filter(detail -> detail.getNum() != null && detail.getNum() > 0).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(details)) {
			return ValidatorUtils.errorInfo("合同明细不能为空或明细数量不能全部为0");
		}

		/**3.判断是新增还是编辑**/
		List<MpContractDetail> detailList = new ArrayList<>();
		if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
			/**3.1.新增, 保存合同、合同明细、合同附件**/
			/**3.1.1校验合同是否已存在, 当合同号、合同名称、供应商相同时,则为合同存在**/
			if(contractService.contractIsExisted(contract)){
				return ValidatorUtils.errorInfo("合同已存在");
			}
			/**3.1.2参数赋值**/
			contract.setId(UUID.randomUUID().toString());
			contract.setStatusCode(MpConstant.CONTRACT_STATUS_NEW);
			contract.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.contract.Status", contract.getStatusCode()));
			contract.setRecCreateTime(new Date());
			contract.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
			/**3.1.3保存**/
			contractService.insert(contract);
		} else {
			/**3.2.编辑, 更新合同、合同明细、合同附件**/
			/**3.2.1校验合同是否可以编辑,合同生效后不可编辑**/
			MpContract mpContract = contractService.queryContract(contract.getId());
			detailList = contractService.queryContractDetailList(contract.getId());
			if(mpContract == null || !MpConstant.CONTRACT_STATUS_NEW.equals(mpContract.getStatusCode())) {
				return ValidatorUtils.errorInfo("此合同无法编辑");
			}
			/**3.2.2参数赋值**/
			contract.setRecRevisor(UserSession.getLoginName());
			contract.setRecReviseTime(new Date());
			/**3.2.3合同更新及删除明细和附件**/
			contractService.update(contract);
			contractService.deleteDetail(contract.getId());
			contractService.deleteContFile(contract.getId());
		}
		/**4.保存明细及附件**/
		completeContract(contract, details, files);
		contractService.insertDetail(details);
		if(CollectionUtils.isNotEmpty(files)) {
			contractService.insertContFile(files);
		}
		/**5.更新采购计划明细中已生成合同数量**/
		if(!updatePurchasePlan(detailList, details, inInfo.getString(MpConstant.OPERATE_NAME))) {
			return ValidatorUtils.errorInfo("本次合同数量不能大于采购计划数减去已生成合同数量");
		};
		return inInfo;
	}

	/**
	 * 完善合同信息
	 * @Title: completeContract
	 * @param contract contract : 合同对象
	 * @param details details : 合同明细集合
	 * @param files files : 合同附件集合
	 * @return void
	 * @throws
	 **/
	private void completeContract(MpContract contract, List<MpContractDetail> details, List<MpContractFile> files) {
		details.forEach(detail -> {
			detail.setId(UUID.randomUUID().toString());
			detail.setContId(contract.getId());
			detail.setContNo(contract.getContNo());
			detail.calTotalCost(detail.getPrice(), detail.getNum());
		});
		files.forEach(file -> {
			file.setId(UUID.randomUUID().toString());
			file.setContId(contract.getId());
		});
	}

	/**
	 * 更新采购计划明细中已生成合同数量
	 * @Title: updatePurchasePlan
	 * @param oldDetails oldDetails : 原先的合同明细
	 * @param details details : 现在的合同明细
	 * @param type type : 类型, reduce=累减采购计划中的已生成合同数量; add=累加采购计划中的已生成合同数量
	 * @return void
	 * @throws
	 **/
	private boolean updatePurchasePlan(List<MpContractDetail> oldDetails, List<MpContractDetail> details, String type) {
		if(MpConstant.OPERATE_TYPE_ADD.equals(type)) {
			return validateNumber(details) && purchasePlanService.updateContNum(details, "add") == 0;
		} else {
			//数量转成负数
			oldDetails.forEach(detail -> detail.setNum(-detail.getNum()));
			//合并两次明细
			details.addAll(oldDetails);
			//分组
			Map<String, List<MpContractDetail>> collect = details.stream().collect(Collectors.groupingBy(detail ->
					detail.getMatNum() + detail.getMatTypeId() + detail.getPurchasePlanId()));
			//遍历,数量累加
			List<MpContractDetail> detailList = new ArrayList<>();
			collect.forEach((key, value) -> {
				if(value.size() == 1) {
					detailList.addAll(value);
				} else {
					MpContractDetail detail = value.remove(0);
					value.forEach(d -> detail.setNum(MpUtils.doubleAdd(detail.getNum(), d.getNum())));
					detailList.add(detail);
				}
			});
			return validateNumber(detailList) && purchasePlanService.updateContNum(detailList,"add") == 0;
		}
	}

	/**
	 * 校验合同数量是否大于采购计划减去已生成合同数量
	 * @Title: validateNumber
	 * @param details details : 合同明细
	 * @return boolean
	 * @throws
	 **/
	private boolean validateNumber(List<MpContractDetail> details) {
		boolean result = true;
		for (MpContractDetail detail : details) {
			result = purchasePlanService.hasEnoughNum(detail);
			if (!result) {break;}
		}
		return result;
	}
}
