package com.baosight.wilp.fa.bf.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.bf.domian.FaScrapVO;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定资产报废页面接口.
 * 1.固定资产报废初始化界面方法.
 * 2.固定资产报废查询方法.
 *
 * @author zhaowei
 * @version V5.0.0
 * @date 2022/8/25 18:50
 */
public class ServiceFABF01 extends ServiceBase {
	/**
	 * 固定资产报废初始化方法.
	 * 1.固定资产报废调用本类中的query方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/7 14:46
	 * @version V5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return confirmedQuery(info);
	}


	/**
	 * 固定资产报废查询方法.
	 * 1.构建分页参数集合.
	 * 2.判断是否存在Block块.
	 * 3.进行数据库查询操作.
	 * 4.将查询记录返回并加上分页参数.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/7 14:47
	 * @version V5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		return info;
	}

	// 另起炉灶

	/**
	 * 查询资产信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/8 9:45
	 */
	public EiInfo confirmedQuery(EiInfo info) {
		EiBlock eiBlock = info.getBlock("inqu_status");
		if (eiBlock != null) {
			Map<String, String> row = eiBlock.getRow(0);
			String deptName = row.get("deptName");
			if (StringUtils.isNotEmpty(deptName)) {
				String[] split = deptName.split(",");
				for (int i = 0; i < split.length; i++) {
					split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
				}
				String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
				info.setCell("inqu_status", 0, "deptName", param);
			}
		}
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultA", "resultA");
		if (info.getBlocks().size() > 0) {
			if (info.getBlock("resultA") != null) {
				if ("50".equals(info.getBlock("resultA").get("limit"))) {
					outInfo.addBlock("resultA").set(EiConstant.limitStr, 50);
				}  else if ("100".equals(info.getBlock("resultA").get("limit"))) {
					outInfo.addBlock("resultA").set(EiConstant.limitStr, 100);
				} else if ("500".equals(info.getBlock("resultA").get("limit"))) {
					outInfo.addBlock("resultA").set(EiConstant.limitStr, 500);
				} else if ("1000".equals(info.getBlock("resultA").get("limit"))) {
					outInfo.addBlock("resultA").set(EiConstant.limitStr, 1000);
				}
			}
		}
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		outInfo.set("deptName", staffByUserId.get("deptName"));
		List<Map<String, String>> list = OneSelfUtils.queryDeptsByWorkNo((String) staffByUserId.get("workNo"));
		outInfo.setRows("dept", list);
		return outInfo;
	}

	/**
	 * 科室负责人审批
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/11/25 17:13
	 */
	public EiInfo ApplyDeptQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "applyDept");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultG", "resultG");
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 科室申请
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/8 17:13
	 */
	public EiInfo ApplyQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "apply");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultB", "resultB");
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 技术鉴定
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/1/12 1:23
	 */
	public EiInfo identifyQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "identify");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultC", "resultC");
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 归口管理
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/9 19:38
	 */
	public EiInfo functionQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "function");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultD", "resultD");
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 资产科审核
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/9 19:38
	 */
	public EiInfo assetQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "asset");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultE", "resultE");
		outInfo.set("workNo", staffByUserId.get("workNo"));
		outInfo.set("name", staffByUserId.get("name"));
		return outInfo;
	}

	/**
	 * 查询报废审批记录
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/6 11:55
	 */
	public EiInfo allQuery(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> rolesMap = OneSelfUtils.queryRolesByWorkNo((String) staffByUserId.get("workNo"));
		List<String> deptName = null;
		if (rolesMap.containsKey("role") && rolesMap.containsKey("lookDeptName")) {
			deptName = (List<String>) rolesMap.get("lookDeptName");
		}
		info.setCell("inqu_status", 0, "scrapStatus", "all");
		if (CollectionUtils.isNotEmpty(deptName)) {
			info.setCell("inqu_status", 0, "role", "user");
			info.setCell("inqu_status", 0, "lookDeptName", deptName);
		}
		EiInfo outInfo = super.query(info, "FABF01.queryFaScrapInfo", new FaScrapVO(), false, null, null, "resultF", "resultF");
		return outInfo;
	}

	/**
	 * 通过报废单号查询资产编码
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/6 11:56
	 */
	public EiInfo queryFaInfoIdByScrappedNo(EiInfo info) {
		List<String> scrappedNoList = (List<String>) info.get("scrappedNoList");
		List<String> faInfoId = dao.query("FABF01.queryFaInfoIdByScrappedNo", scrappedNoList);
		info.set("faInfoIdList", faInfoId);
		return info;
	}

	/**
	 * 科室报废审批
	 * @param info
	 * @return
	 */
	public EiInfo batchDeptApproval(EiInfo info){
		// 根据报废详情的id进行审批提交
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			// 更新资产主表的状态
			int count = dao.update("FABF01.approvalScarpStatusById", new HashMap<String, Object>() {{
				put("deptPerson", staffByUserId.get("name"));
				put("deptTime", DateUtils.toDateTimeStr19(new Date()));
				put("deptFileCode", info.getString("deptFileCode"));
				put("scrapDetailStatus", "05");
				put("detailIdList", detailIdList);
			}});
			// 删除资产报废表信息
			info.setMsg("成功提交" + count + "条");
			info.set(EiConstant.serviceName, "XQMS03");
			info.set(EiConstant.methodName, "verifySignData");
			info.set("fileCode", info.getString("deptFileCode"));
			info.set("isBackSignatureImg", 1);
			EiInfo outInfo = XLocalManager.call(info);
			for (int i = 0; i < detailIdList.size(); i++) {
				OneSelfUtils.savePicInfo(info.getString("deptFileCode"), detailIdList.get(i), "scrap", (Map<String, Object>) outInfo.getAttr().get("data"), "dept");
			}
		}
		return info;
	}

	/**
	 * 科室报废申请批量取消
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 16:42
	 */
	public EiInfo batchRevocation(EiInfo info) {
		// 根据报废单号进行撤销报废记录
		if (info.get("scrappedNosList") != null) {
			List<String> scrappedNosList = (List<String>) info.get("scrappedNosList");
			// 更新资产主表的状态
			dao.update("FABF01.revocationFaInfoStatus", new HashMap<String, Object>() {{
				put("statusCode", "020");
				put("scrappedNosList", scrappedNosList);
			}});
			// 删除资产报废明细表信息
			dao.delete("FABF01.deleteFaScrapDetailsByNo", scrappedNosList);
			// 删除资产报废表信息
			int deleteFaScrapsByNo = dao.delete("FABF01.deleteFaScrapsByNo", scrappedNosList);
			info.setMsg("成功撤销" + deleteFaScrapsByNo + "条");
		}
		// 根据报废详情的id进行撤销
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			// 更新资产主表的状态
			dao.update("FABF01.revocationFaInfoStatusById", new HashMap<String, Object>() {{
				put("statusCode", "020");
				put("detailIdList", detailIdList);
			}});
			// 删除资产报废明细表信息
			int deleteFaScrapDetailsByNo = dao.delete("FABF01.deleteFaScrapDetailsById", detailIdList);
			// 删除资产报废表信息
			info.setMsg("成功撤销" + deleteFaScrapDetailsByNo + "条");
			// 查询报废表和报废明细表如果不存在明细删除报废表
			List<String> scrappedNosList = dao.query("FABF01.queryEmptyScrappedNos", new HashMap<>());
			if (CollectionUtils.isNotEmpty(scrappedNosList)){
				dao.delete("FABF01.deleteEmptyScrappedNos",scrappedNosList);
			}
		}
		return info;
	}

	/**
	 * 资产科分配批量驳回不合理需求
	 */
	public EiInfo batchAssignmentReason(EiInfo info){
		// 根据报废详情的id进行撤销
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			String assignmentReason = info.getString("assignmentReason");
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			// 更新资产主表的状态
			int i = dao.update("FABF01.batchAssignmentReasonById", new HashMap<String, Object>() {{
				put("assignmentPerson", staffByUserId.get("name"));
				put("assignmentTime", DateUtils.toDateTimeStr19(new Date()));
				put("scrapDetailStatus", "01");
				put("assignmentReason", assignmentReason);
				put("detailIdList", detailIdList);
			}});
			info.setMsg("成功驳回" + i + "条");
		}
		return info;
	}

	/**
	 * 批量鉴定
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/3/13 0:25
	 */
	public EiInfo batchIdentify(EiInfo info) {
		String type = info.getString("type");
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			if (CollectionUtils.isNotEmpty(detailIdList)) {
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				Map<String, Object> map = new HashMap<>(8);
				map.put("identifyPerson", staffByUserId.get("name"));
				map.put("identifyTime", DateUtils.toDateTimeStr19(new Date()));
				map.put("identifyReason", info.getString("identifyReason"));
				map.put("identifyFileCode", info.getString("identifyFileCode"));
				map.put("detailIdList", detailIdList);
				switch (type) {
					case "pass":
						// 1.更新报废单状态
						map.put("scrapDetailStatus", "20");
						break;
					case "reject":
						map.put("scrapDetailStatus", "90");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatusById", new HashMap<String, Object>() {{
							put("statusCode", "020");
							put("detailIdList", detailIdList);
						}});
						break;
				}
				// 1.更新报废单状态
				dao.update("FABF01.batchIdentify", map);
				info.set(EiConstant.serviceName, "XQMS03");
				info.set(EiConstant.methodName, "verifySignData");
				info.set("fileCode", info.getString("identifyFileCode"));
				info.set("isBackSignatureImg", 1);
				EiInfo outInfo = XLocalManager.call(info);
				for (int i = 0; i < detailIdList.size(); i++) {
					OneSelfUtils.savePicInfo(info.getString("identifyFileCode"), detailIdList.get(i), "scrap", (Map<String, Object>) outInfo.getAttr().get("data"), "identify");
				}
			}
		}
		return info;
	}

	/**
	 * 批量归口
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/3/13 0:26
	 */
	public EiInfo batchFunction(EiInfo info) {
		String type = info.getString("type");
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			if (CollectionUtils.isNotEmpty(detailIdList)) {
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				Map<String, Object> map = new HashMap<>(8);
				map.put("functionPerson", staffByUserId.get("name"));
				map.put("functionTime", DateUtils.toDateTimeStr19(new Date()));
				map.put("functionReason", info.getString("functionReason"));
				map.put("functionFileCode", info.getString("functionFileCode"));
				map.put("detailIdList", detailIdList);
				switch (type) {
					case "pass":
						// 1.更新报废单状态
						map.put("scrapDetailStatus", "30");
						break;
					case "reject":
						map.put("scrapDetailStatus", "80");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatusById", new HashMap<String, Object>() {{
							put("statusCode", "020");
							put("detailIdList", detailIdList);
						}});
						break;
				}
				// 1.更新报废单状态
				dao.update("FABF01.batchFunction", map);
				info.set(EiConstant.serviceName, "XQMS03");
				info.set(EiConstant.methodName, "verifySignData");
				info.set("fileCode", info.getString("functionFileCode"));
				info.set("isBackSignatureImg", 1);
				EiInfo outInfo = XLocalManager.call(info);
				for (int i = 0; i < detailIdList.size(); i++) {
					OneSelfUtils.savePicInfo(info.getString("functionFileCode"), detailIdList.get(i), "scrap", (Map<String, Object>) outInfo.getAttr().get("data"), "function");
				}
			}
		}
		return info;
	}

	/**
	 * 资产科批量审批报废
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/2/7 13:11
	 */
	public EiInfo batchApproval(EiInfo info) {
		String type = info.getString("type");
		if (info.get("scrappedNoList") != null) {
			List<String> scrappedNoList = (List<String>) info.get("scrappedNoList");
			if (CollectionUtils.isNotEmpty(scrappedNoList)) {
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				Map<String, Object> map = new HashMap<>(8);
				map.put("assetDeptName", staffByUserId.get("deptName"));
				map.put("assetPerson", staffByUserId.get("name"));
				map.put("assetTime", DateUtils.toDateTimeStr19(new Date()));
				map.put("assetReason", info.getString("assetReason"));
				map.put("assetFileCode", info.getString("assetFileCode"));
				map.put("scrappedNosList", scrappedNoList);
				switch (type) {
					case "pass":
						// 1.更新报废单状态
						map.put("scrapStatus", "100");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatus", new HashMap<String, Object>() {{
							put("statusCode", "040");
							put("scrappedNosList", scrappedNoList);
						}});
						break;
					case "reject":
						map.put("scrapStatus", "70");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatus", new HashMap<String, Object>() {{
							put("statusCode", "020");
							put("scrappedNosList", scrappedNoList);
						}});
						break;
				}
				// 1.更新报废单状态
				dao.update("FABF01.batchApproval", map);
			}
		}
		if (info.get("detailIdList") != null) {
			List<String> detailIdList = (List<String>) info.get("detailIdList");
			if (CollectionUtils.isNotEmpty(detailIdList)) {
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				Map<String, Object> map = new HashMap<>(8);
				map.put("assetDeptName", staffByUserId.get("deptName"));
				map.put("assetPerson", staffByUserId.get("name"));
				map.put("assetTime", DateUtils.toDateTimeStr19(new Date()));
				map.put("assetReason", info.getString("assetReason"));
				map.put("assetFileCode", info.getString("assetFileCode"));
				map.put("detailIdList", detailIdList);
				switch (type) {
					case "pass":
						// 1.更新报废单状态
						map.put("scrapDetailStatus", "100");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatusById", new HashMap<String, Object>() {{
							put("statusCode", "040");
							put("detailIdList", detailIdList);
						}});
						break;
					case "reject":
						map.put("scrapDetailStatus", "70");
						// 2.更新资产状态
						dao.update("FABF01.revocationFaInfoStatusById", new HashMap<String, Object>() {{
							put("statusCode", "020");
							put("detailIdList", detailIdList);
						}});
						break;
				}
				// 1.更新报废单状态
				dao.update("FABF01.batchApprovalById", map);
				info.set(EiConstant.serviceName, "XQMS03");
				info.set(EiConstant.methodName, "verifySignData");
				info.set("fileCode", info.getString("assetFileCode"));
				info.set("isBackSignatureImg", 1);
				EiInfo outInfo = XLocalManager.call(info);
				for (int i = 0; i < detailIdList.size(); i++) {
					OneSelfUtils.savePicInfo(info.getString("assetFileCode"), detailIdList.get(i), "scrap", (Map<String, Object>) outInfo.getAttr().get("data"), "asset");
				}
			}
		}
		return info;
	}
}

