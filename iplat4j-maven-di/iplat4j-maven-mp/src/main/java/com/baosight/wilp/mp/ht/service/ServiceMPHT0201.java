package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.*;
import com.baosight.wilp.mp.lj.service.MpContractHistoryService;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * 该页面为采购合同变更页面
 * 主要包含对采购合同的变更功能
 * @Title: ServiceMPHT0201.java
 * @ClassName: ServiceMPHT0201
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月17日 下午1:23:38
 * @version: V5.0.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceMPHT0201 extends ServiceBase {

	@Autowired
	private MpContractService contractService;

	@Autowired
	private MpProcurementPlanService purchasePlanService;

	@Autowired
	private MpContractHistoryService historyService;

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
		return MpUtils.invoke(inInfo, "MPHT0101", "initLoad");
	}

	/**
	 * 合同保存变更
	 * <p>
	 *    1.参数处理
	 *    2.参数校验
	 *    3.合同变更
	 *    	3.1更新合同信息
	 *    	3.2更新合同明细
	 *    	3.3更新合同附件信息
	 *   4.更新采购计划明细中已生成合同数量
	 *   5.保存变更履历
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
		List<MpContractDetail> collect = details.stream().filter(detail -> detail.getNum() != null && detail.getNum() > 0).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(collect)) {
			return ValidatorUtils.errorInfo("合同明细数量不能全部为0");
		}
		/**3.合同变更**/
		/**3.1更新合同信息**/
		contract.setRecRevisor(UserSession.getLoginName());
		contract.setRecReviseTime(new Date());
		contractService.update(contract);
		//构建合同履历
		MpContractHistory history = MpContractHistory.newInstance(contract.getId(), "合同变更");
		BeanUtils.copyProperties(contract,history);
		history.setId(UUID.randomUUID().toString());

		/**3.2更新合同明细**/
		List<MpContractDetail> changeDetails = new ArrayList<>();
		List<MpContractHistoryDetail> historyDetails = updateDetail(contract, history.getId(), details, changeDetails);

		/**3.3更新合同附件信息**/
		List<MpContractHistoryFile> historyFiles = null;
		if(CollectionUtils.isNotEmpty(files)) {
			historyFiles = updateFile(contract.getId(), files, history.getId());
		}

		/**4.更新采购计划明细中已生成合同数量**/
		if(!updatePurchasePlan(changeDetails)) {
			return ValidatorUtils.errorInfo("本次合同数量不能大于采购计划数减去已生成合同数量,且合同数量不能小于已生成订单数量");
		};
		/**5.保存变更履历**/
		historyService.saveChangeHistory(history, historyDetails, historyFiles);
		return inInfo;
	}

	/**
	 * 更新合同明细
	 * @Title: updateDetail
	 * @param contract contract : 合同对象
	 * @param historyId historyId : 合同履历ID
	 * @param curDetails curDetails : 变更后明细集合
	 * @param changeDetails changeDetails : 数量发生变化的合同明细集合
	 * @return List<MpContractHistoryDetail>
	 * @throws
	 **/
	private List<MpContractHistoryDetail> updateDetail(MpContract contract, String historyId, List<MpContractDetail> curDetails, List<MpContractDetail> changeDetails) {
		List<MpContractHistoryDetail> historyDetails = new ArrayList<>();
		//获取原先的合同明细
		List<MpContractDetail> lastDetails = contractService.queryContractDetailList(contract.getId());
		//遍历现在的合同明细,构建合同明细履历
		for(MpContractDetail detail : curDetails) {
			//部分属性赋值
			detail.setId(UUID.randomUUID().toString());
			detail.setContId(contract.getId());
			detail.setContNo(contract.getContNo());
			detail.calTotalCost(detail.getPrice(), detail.getNum());
			//构建合同明细履历
			MpContractHistoryDetail historyDetail = MpContractHistoryDetail.newEffectInstance(historyId);
			BeanUtils.copyProperties(detail,historyDetail);
			historyDetail.setId(UUID.randomUUID().toString());
			//变更后
			historyDetail.setAfterPrice(detail.getPrice());
			historyDetail.setAfterNum(detail.getNum());
			historyDetail.setAfterTotalCost(detail.getTotalCost());
			//变更前
			MpContractDetail lastDetail = lastDetails.stream().filter(d -> detail.getPurchasePlanId().equals(d.getPurchasePlanId())
					&& detail.getMatNum().equals(d.getMatNum()) && detail.getMatTypeId().equals(d.getMatTypeId())).findFirst().orElse(null);
			if(lastDetail != null) {
				historyDetail.setBeforePrice(lastDetail.getPrice());
				historyDetail.setBeforeNum(lastDetail.getNum());
				historyDetail.setBeforeTotalCost(lastDetail.getTotalCost());
				contractService.updateDetail(detail);
			} else {
				contractService.insertDetail(detail);
			}
			historyDetails.add(historyDetail);
			//获取合同数量发生变化的合同明细
			buildChangeDetail(historyDetail, lastDetail, changeDetails);
		}
		return historyDetails;
	}

	/**
	 * 获取合同数量发生变化的合同明细
	 * @Title: buildChangeDetail
	 * @param historyDetail historyDetail
	 * @param lastDetail lastDetail
	 * @param changeDetails changeDetails
	 * @return void
	 * @throws
	 **/
	private void buildChangeDetail(MpContractHistoryDetail historyDetail, MpContractDetail lastDetail, List<MpContractDetail> changeDetails) {
		//数量没有发生变化
		if(MpUtils.doubleSub(historyDetail.getAfterNum(), historyDetail.getBeforeNum()) == 0) {
			return;
		}
		//数量发生变化
		if(lastDetail == null) {
			MpContractDetail detail = new MpContractDetail();
			BeanUtils.copyProperties(historyDetail,detail);
			detail.setNum(MpUtils.doubleSub(historyDetail.getAfterNum(), historyDetail.getBeforeNum()));
			//临时作为变更后(当前)的合同数量使用
			detail.setNoTaxPrice(historyDetail.getAfterNum());
			changeDetails.add(detail);
		} else {
			lastDetail.setNum(MpUtils.doubleSub(historyDetail.getAfterNum(), historyDetail.getBeforeNum()));
			//临时作为变更后(当前)的合同数量使用
			lastDetail.setNoTaxPrice(historyDetail.getAfterNum());
			changeDetails.add(lastDetail);
		}
	}

	/**
	 * 更新合同附件
	 * @Title: updateFile
	 * @param contId contId : 合同ID
	 * @param files files : 变更后附件集合
	 * @param historyId historyId : 合同履历ID
	 * @return void
	 * @throws
	 **/
	private List<MpContractHistoryFile> updateFile(String contId, List<MpContractFile> files, String historyId) {
		List<MpContractHistoryFile> historyFiles = new ArrayList<>();
		//获取新增的合同附件
		List<MpContractFile> addFiles = files.stream().filter(file -> StringUtils.isBlank(file.getContId())).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(addFiles)) {
			//遍历新增的合同附件
			for (MpContractFile file : addFiles) {
				file.setId(UUID.randomUUID().toString());
				file.setContId(contId);
				//构建附件履历
				MpContractHistoryFile historyFile = MpContractHistoryFile.newInstance(historyId, 0);
				BeanUtils.copyProperties(file,historyFile);
				historyFile.setId(UUID.randomUUID().toString());
				historyFiles.add(historyFile);
			}
			//保存附件
			contractService.insertContFile(addFiles);
		}
		return historyFiles;
	}

	/**
	 * 更新采购计划明细中已生成合同数量
	 * @Title: updatePurchasePlan
	 * @param changeDetails changeDetails : 合同明细集合
	 * @return boolean
	 * @throws
	 **/
	private boolean updatePurchasePlan(List<MpContractDetail> changeDetails) {
		if(CollectionUtils.isEmpty(changeDetails)) {
			return true;
		}
		//遍历、校验
		for (MpContractDetail detail : changeDetails) {
			/**
			 * 1.校验合同数量是否大于或等于已生成订单数量
			 * 2.校验合同数量是否小于或等于采购计划数量减去已生成合同数量
			 * 说明：detail.getNoTaxPrice() 取的是buildChangeDetail方法中临时存储的变更后合同数量
			 **/
			boolean result = MpUtils.doubleSub(detail.getNoTaxPrice(), detail.getOrderNum()) >= 0
					&& purchasePlanService.hasEnoughNum(detail);
			if (!result) {return false; }
		}
		purchasePlanService.updateContNum(changeDetails, "add");
		return true;
	}
}
