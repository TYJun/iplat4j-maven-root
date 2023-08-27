package com.baosight.wilp.fa.db.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.db.domain.FaTransferVO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产调拨管理类.
 * 固定资产调拨管理初始化方法
 * 固定资产调拨查询方法
 *
 * @author zhaowei
 * @date 2022年05月30日 11:49
 */
public class ServiceFADB01 extends ServiceBase {
	/**
	 * 固定资产调拨管理初始化方法.
	 * 1.调用固定资产调拨查询方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 12:02
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 固定资产调拨查询方法.
	 * 权限判断.
	 * 构造分页参数.
	 * 查询固定资产调拨信息.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 12:02
	 */
	@Override
	public EiInfo query(EiInfo info) {
		return this.confirmedQuery(info);
	}

	// 另起炉灶

	/**
	 * 资产信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:51
	 */
	public EiInfo confirmedQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<String> deptName = OneSelfUtils.specifyDept((String) staffByUserId.get("workNo"));
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		info.setCell("inqu_status", 0, "inAccountStatus", "confirmed");
		EiInfo outInfo = super.query(info, "FADB01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultA", "resultA");
		if (info.getBlocks().size() > 0) {
			if (info.getBlock("resultA") != null) {
				if ("20".equals(info.getBlock("resultA").get("limit"))) {
					outInfo.addBlock("resultA").set(EiConstant.limitStr, 15);
				}
			}
		}
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 科室调拨申请
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:54
	 */
	public EiInfo transferApplyQuery(EiInfo info) {
		info.setCell("inqu_status", 0, "tabStatus", "apply");
		EiInfo outInfo = super.query(info, "FADB01.transferRecordQuery", new FaTransferVO(), false, null, null, "resultB", "resultB");
		return outInfo;
	}

	/**
	 * 科室调拨确认
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:55
	 */
	public EiInfo transferConfirmQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<String> deptName = OneSelfUtils.specifyDept((String) staffByUserId.get("workNo"));
		info.setCell("inqu_status", 0, "tabStatus", "confirm");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FADB01.transferRecordQuery", new FaTransferVO(), false, null, null, "resultC", "resultC");
		if (info.getBlocks().size() > 0) {
			if (info.getBlock("resultC") != null) {
				if ("20".equals(info.getBlock("resultC").get("limit"))) {
					outInfo.addBlock("resultC").set(EiConstant.limitStr, 20);
				}
			}
		}
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 科室审批
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:56
	 */
	public EiInfo transferAuditQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<String> deptName = OneSelfUtils.specifyDept((String) staffByUserId.get("workNo"));
		info.setCell("inqu_status", 0, "tabStatus", "audit");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FADB01.transferRecordQuery", new FaTransferVO(), false, null, null, "resultD", "resultD");
		if (info.getBlocks().size() > 0) {
			if (info.getBlock("resultD") != null) {
				if ("20".equals(info.getBlock("resultD").get("limit"))) {
					outInfo.addBlock("resultD").set(EiConstant.limitStr, 20);
				}
			}
		}
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 调拨记录
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:56
	 */
	public EiInfo transferRecordQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<String> deptName = OneSelfUtils.specifyDept((String) staffByUserId.get("workNo"));
		info.setCell("inqu_status", 0, "tabStatus", "all");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FADB01.transferRecordQuery", new FaTransferVO(), false, null, null, "resultE", "resultE");
		if (info.getBlocks().size() > 0) {
			if (info.getBlock("resultE") != null) {
				if ("20".equals(info.getBlock("resultE").get("limit"))) {
					outInfo.addBlock("resultE").set(EiConstant.limitStr, 20);
				}
			}
		}
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 资产调拨批量撤销
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/11 10:19
	 */
	public EiInfo batchRevocation(EiInfo info) {
		List<String> transferNoList = (List<String>) info.get("transferNoList");
		// 更新资产状态
		dao.update("FADB01.revocationFaInfoStatus", transferNoList);
		// 删除调拨详情
		dao.delete("FADB01.deleteFaTransferDetail", transferNoList);
		// 删除调拨主表
		int delete = dao.delete("FADB01.deleteFaTransfer", transferNoList);
		info.setMsg("撤销调拨" + delete + "条");
		return info;
	}

	/**
	 * 固定资产资产科批量审批通过
	 * 固定资产资产科批量审批拒绝
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/1 20:37
	 */
	public EiInfo batchApproval(EiInfo info) {
		String type = info.getString("type");
		String auditReason = info.getString("auditReason");
		String auditFileCode = info.getString("auditFileCode");
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		if (info.get("transferNoList") != null) {
			List<String> transferNoList = (List<String>) info.get("transferNoList");
			if (CollectionUtils.isNotEmpty(transferNoList)) {
				List<Map<String, Object>> applyDeptList = (List<Map<String, Object>>) info.get("applyDeptList");
				List<Map<String, Object>> confirmDeptList = (List<Map<String, Object>>) info.get("confirmDeptList");
				Map<String, Object> map = new HashMap<>();
				map.put("auditReason", auditReason);
				map.put("auditFileCode", auditFileCode);
				map.put("auditDeptNum", staffByUserId.get("deptNum"));
				map.put("auditDeptName", staffByUserId.get("deptName"));
				map.put("auditPerson", staffByUserId.get("name"));
				map.put("auditTime", DateUtils.toDateTimeStr19(new Date()));
				map.put("transferNoList", transferNoList);
				switch (type) {
					case "pass":
						map.put("transferStatus", "100");
						StringBuilder stringBuilder = new StringBuilder();
						HashSet<Map<String, Object>> confirmDeptSet = new HashSet();
						HashSet<Map<String, Object>> applyDeptSet = new HashSet();
						confirmDeptList.stream().filter(i -> confirmDeptSet.add(i)).collect(Collectors.toList());
						for (Map<String, Object> confirmDept : confirmDeptSet) {
							int outCount = dao.count("FADB01.queryOutFaInventoryInfo", confirmDept.get("applyDeptNum"));
							if (outCount > 0) {
								stringBuilder.append("调出科室:" + confirmDept.get("applyDeptName") + "正在进行盘库操作;");
								info.setStatus(-1);
								info.setMsg(stringBuilder.toString());
							}
						}
						applyDeptList.stream().filter(i -> applyDeptSet.add(i)).collect(Collectors.toList());
						for (Map<String, Object> applyDept : applyDeptSet) {
							int inCount = dao.count("FADB01.queryOutFaInventoryInfo", applyDept.get("confirmDeptNum"));
							if (inCount > 0) {
								stringBuilder.append("调入科室" + applyDept.get("confirmDeptName") + "正在进行盘库操作;");
								info.setStatus(-1);
								info.setMsg(stringBuilder.toString());
							}
						}
						break;
					case "reject":
						map.put("transferStatus", "40");
						break;
				}
				// 1.更新资产信息（fa_info）的资产状态
				dao.update("FADB01.updateFaInfoStatusByList", map);
				// 2.更新资产调拨单的状态
				dao.update("FADB01.updateFaTransferStatusByList", map);
				// 保存图片
				info.set(EiConstant.serviceName, "XQMS03");
				info.set(EiConstant.methodName, "verifySignData");
				info.set("fileCode", auditFileCode);
				info.set("isBackSignatureImg", 1);
				EiInfo outInfo = XLocalManager.call(info);
				info.set(EiConstant.serviceName, "XQMS03");
				info.set(EiConstant.methodName, "verifySignData");
				for (int i = 0; i < transferNoList.size(); i++) {
					OneSelfUtils.savePicInfo(auditFileCode, transferNoList.get(i), "transfer", (Map<String, Object>) outInfo.getAttr().get("data"), "audit");
				}
			}
		}
		return info;
	}
}
