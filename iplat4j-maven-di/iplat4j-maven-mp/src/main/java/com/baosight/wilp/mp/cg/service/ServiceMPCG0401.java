package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConfigCache;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpApproval;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;
import com.baosight.wilp.mp.lj.service.MpProcurementApprovalHistoryService;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *  采购计划审批子页面Service
 * <p>页面加载</p>
 * <p>采购计划通过</p>
 * <p>采购计划驳回</p>
 * <p>采购计划通过子方法</p>
 * <p>采购计划驳回子方法</p>
 * <p>采购计划批量通过</p>
 * <p>采购计划批量驳回</p>
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG0401.java
 * @ClassName:  ServiceMP0401
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月19日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG0401 extends ServiceBase {

	@Autowired
	private MpProcurementPlanService procurementPlanService;

	@Autowired
	private MpProcurementApprovalHistoryService approvalHistoryService;


	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * 		id  主键
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		//获取ID
		String id = info.getCellStr(MpConstant.QUERY_BLOCK, 0, "id");
		//回显采购计划数据
		List<MpPurchasePlan> list = dao.query("MPLJ01.query", info.getRow(MpConstant.QUERY_BLOCK, 0));
		info.setRows(MpConstant.QUERY_BLOCK, list);
		//回显明细数据
		List<MpPurchasePlanDetail> detailList = procurementPlanService.queryDetailList(id);
		info.setRows(MpConstant.RESULT_BLOCK, detailList);
		return info;
	}


	/**
	 * 审批通过
	 * @Title: pass
	 * @param inInfo inInfo
	 *      planIds : 需求计划ID集合
	 *      workNo : 工号
	 *      name : 姓名
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo pass(EiInfo inInfo) {
		List<String> planIds = MpUtils.toList(inInfo.get("planIds"), String.class);
		if(CollectionUtils.isEmpty(planIds)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//遍历,通过
		for (String planId : planIds) {
			approval(planId, inInfo.getString("rejectReason"), null, inInfo.getString("workNo"), inInfo.getString("name"));
		}
		return inInfo;
	}


	/**
	 * 审批驳回
	 * @Title: reject
	 * @param inInfo inInfo
	 *     planIds : 需求计划ID集合
	 *     rejectReason : 驳回原因
	 *     workNo : 工号
	 *     name : 姓名
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo reject(EiInfo inInfo) {
		List<String> planIds = MpUtils.toList(inInfo.get("planIds"), String.class);
		if(CollectionUtils.isEmpty(planIds)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//遍历,驳回
		for (String planId : planIds) {
			approval(planId, MpConstant.PROCUREMENT_STATUS_REJECT, inInfo.getString("rejectReason"), inInfo.getString("workNo"), inInfo.getString("name"));
		}
		return inInfo;
	}

	/**
	 * 采购计划审批
	 * @Title: approval
	 * @param planId planId : 需求计划ID
	 * @param approvalResult approvalResult : 审批结果
	 * @param rejectReason rejectReason : 驳回原因
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	private EiInfo approval(String planId, String approvalResult, String rejectReason, String workNo, String name) {
		//是否可以审批校验
		MpPurchasePlan plan = procurementPlanService.queryProcurementPlan(planId);
		String newApprovalResult = getApprovalResult(plan);
		if(StringUtils.isBlank(newApprovalResult)) {
			return ValidatorUtils.errorInfo("采购计划已审批或无法审批");
		}

		//更新采购计划状态
		approvalResult = StringUtils.isBlank(approvalResult) ? newApprovalResult : approvalResult;
		MpPurchasePlan instant = MpPurchasePlan.getStatusInstant(planId, approvalResult);
		procurementPlanService.updateProcurementPlan(instant);
		//保存审批履历
		MpApproval approval = MpApproval.getInstance(planId, approvalResult, instant.getStatusName(), rejectReason);
		approval.setApprover(MpUtils.toString(approval.getApprover(), workNo));
		approval.setApproverName(MpUtils.toString(approval.getApproverName(), name));
		approvalHistoryService.approval(approval);
		return null;
	}

	/**
	 * 获取审批后的状态
	 * <p>不可审批时返回空或null,并在调用方法中提示错误信息</p>
	 * @Title: getApprovalResult
	 * @param plan plan : 采购计划对象
	 * @return java.lang.String
	 * @throws
	 **/
	public String getApprovalResult(MpPurchasePlan plan) {
		if(plan == null) { return null; }
		//获取配置
		String leaderApproval = MpConfigCache.getConfigRadioValue(null, MpConfigCache.MP_CONFIG_OA);
		String deanApproval = MpConfigCache.getConfigRadioValue(null, MpConfigCache.MP_CONFIG_DEAN);
		String returnStatus = "";
		switch (plan.getStatusCode()) {
			//待审批(科室领导审批)
			case MpConstant.PROCUREMENT_STATUS_UN_APPROVAL:
				returnStatus = MpUtils.toBoolean(leaderApproval) ? MpConstant.PROCUREMENT_STATUS_SECOND :
						MpUtils.toBoolean(deanApproval) ? MpConstant.PROCUREMENT_STATUS_THREE : MpConstant.PROCUREMENT_STATUS_PASS;
				break;
			//待分管领导审批
			case MpConstant.PROCUREMENT_STATUS_SECOND:
				returnStatus = MpUtils.toBoolean(deanApproval) ? MpConstant.PROCUREMENT_STATUS_THREE :
						MpConstant.PROCUREMENT_STATUS_PASS;
				break;
			//待院长审批
			case MpConstant.PROCUREMENT_STATUS_THREE:
				returnStatus = MpConstant.PROCUREMENT_STATUS_PASS;
				break;
			default:
		}
		return returnStatus;
	}

	/**
	 * 批量通过
	 * @Title: batchThrough
	 **/
	/*public EiInfo batchThrough(EiInfo inInfo) {
		//获取配置项
		String configValue = "N";
		String configValueRadio = MpConfigCache.getConfigRadioValue(null, MpConfigCache.MP_CONFIG_OA);
		//参数处理
		EiBlock result = inInfo.getBlock("result");
		if(result!=null){
			List<Map<String, Object>> rows = result.getRows();
			for(Map<String, Object> row :rows){
				inInfo.set("planId", row.get("id"));
				if(configValue.equals(configValueRadio)){
					//审批通过
					approval(inInfo, MpConstant.PROCUREMENT_STATUS_PASS);
				}else {
					//待领导审批
					approval(inInfo, MpConstant.PROCUREMENT_STATUS_SECOND);
				}
			}
		}else {
			inInfo.setMsg("请选择采购计划进行审批");
			inInfo.setStatus(-1);
		}
		return inInfo;
	}*/

	/**
	 * 批量驳回
	 * @Title: bulkRejection
	 **/
	/*public EiInfo bulkRejection(EiInfo inInfo) {
		EiBlock result = inInfo.getBlock("result");
		if(result!=null){
			List<Map<String, Object>> rows = result.getRows();
			for(Map<String, Object> row : rows){
				inInfo.set("planId", row.get("id"));
				approval(inInfo, MpConstant.PROCUREMENT_STATUS_REJECT);
			}
		}else {
			inInfo.setMsg("请选择采购计划进行审批");
			inInfo.setStatus(-1);
		}
		return inInfo;
	}*/
}
