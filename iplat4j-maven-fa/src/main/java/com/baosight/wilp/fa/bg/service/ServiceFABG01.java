package com.baosight.wilp.fa.bg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.fa.bg.domain.FaModificationBatchDetailVO;
import com.baosight.wilp.fa.common.CompareUtils;
import com.baosight.wilp.fa.common.ComparisonResult;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 固定资产变更管理API.
 * 固定资产变更初始化方法.
 * 固定资产变更管理查询方法.
 * 固定资产管理审批方法.
 * 固定资产变更撤销方法.
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022年07月13日 9:47
 */
public class ServiceFABG01 extends ServiceBase {
	/**
	 * 固定资产变更初始化方法.
	 * 1.调用本地查询方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 14:53
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return confirmedQuery(info);
	}

	/**
	 * 固定资产变更管理查询方法.
	 * 1.权限判断.
	 * 2.构建分页参数.
	 * 3.通过前端条件查询固定资产信息.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 14:53
	 * @version v5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		// 1.权限判断
		String deptName = OneSelfUtils.specifyDept();
		if (StringUtils.isNotEmpty(deptName)) {
			info.set("deptName", deptName);
		} else {
			EiBlock eiBlock = info.getBlock("inqu_status");
			if (eiBlock != null) {
				Map<String, String> row = eiBlock.getRow(0);
				String deptNameSplit = row.get("deptName");
				if (StringUtils.isNotEmpty(deptNameSplit)) {
					String[] split = deptNameSplit.split(",");
					for (int i = 0; i < split.length; i++) {
						split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
					}
					String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
					info.setCell("inqu_status", 0, "deptNameSplit", param);
				}
			}
		}
		// 2.构建分页参数
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 500);
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		info.set("modify", "true");
		// 3.通过前端条件查询固定资产信息.
		List resultList = dao.query("FADA01.query", info.getAttr(), (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("FADA01.query", info.getAttr());
		pageMap.put("count", count);
		info.setRows("result", resultList);
		info.setAttr(pageMap);
		return info;
	}

	/**
	 * 固定资产管理审批方法.
	 * 1.设置固定资产审批状态，查询符合要求的数据
	 * 2.更新固定资产变更信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 14:54
	 */
	public EiInfo approval(EiInfo info) {
		// 1.设置固定资产审批状态，查询符合要求的数据
		info.set("isByFaInfoId", true);
		List<Map<String, Object>> faInfoList = dao.query("FADA01.query", info.getAttr());
		List<Map<String, Object>> faModificationList = dao.query("FABG01.query", info.getAttr());
		// 2.更新固定资产变更信息
		dao.update("FABG01.editFaModification", faInfoList.get(0));
		dao.update("FADA01.editFaModification", faModificationList.get(0));
		return info;
	}

	/**
	 * 固定资产变更撤销方法.
	 * 1.删除固定资产变更记录，修改固定资产变更状态
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 14:54
	 */
	public EiInfo remove(EiInfo info) {
		// 1.删除固定资产变更记录，修改固定资产变更状态
		dao.delete("FABG01.remove", info.getAttr());
		dao.update("FADA01.modification", info.getAttr());
		return info;
	}

	// 另起炉灶

	/**
	 * 资产信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/5 11:48
	 */
	public EiInfo confirmedQuery(EiInfo info) {
		// 1.权限判断
//		String deptName = OneSelfUtils.specifyDept();
//		if (StringUtils.isNotEmpty(deptName)) {
//			info.setCell("inqu_status", 0, "deptName", deptName);
//		}
		EiBlock eiBlock = info.getBlock("inqu_status");
		if (eiBlock != null) {
			Map<String, String> row = eiBlock.getRow(0);
			String deptNameSplit = row.get("deptName");
			if (StringUtils.isNotEmpty(deptNameSplit)) {
				String[] split = deptNameSplit.split(",");
				for (int i = 0; i < split.length; i++) {
					split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
				}
				String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
				info.setCell("inqu_status", 0, "deptNameSplit", param);
			}
		}
		info.setCell("inqu_status", 0, "inAccountStatus", "confirmed");
		EiInfo outInfo = super.query(info, "FABG01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultA", "resultA");
		// 1.获取参数,处理参数
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
		// 2.调用微服务接口S_AC_FW_05，获取科室信息
		map.remove("limit");
		List<Map<String, String>> maps = dao.query("FADA01.queryDept", map);
		outInfo.setRows("dept", maps);
		return outInfo;
	}

	/**
	 * 变更记录
	 * @param info
	 * @return
	 */
	public EiInfo changeRecord(EiInfo info){
		// 1.权限判断
//		String deptName = OneSelfUtils.specifyDept();
//		if (StringUtils.isNotEmpty(deptName)) {
//			info.setCell("inqu_status", 0, "deptName", deptName);
//		}
		EiBlock eiBlock = info.getBlock("inqu_status");
		if (eiBlock != null) {
			Map<String, String> row = eiBlock.getRow(0);
			String deptNameSplit = row.get("deptName");
			if (StringUtils.isNotEmpty(deptNameSplit)) {
				String[] split = deptNameSplit.split(",");
				for (int i = 0; i < split.length; i++) {
					split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
				}
				String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
				info.setCell("inqu_status", 0, "deptNameSplit", param);
			}
		}
		EiInfo eiInfo = super.query(info, "FABG01.queryChangeRecord", new FaModificationBatchDetailVO(), false, null, null, "resultC", "resultC");
		return eiInfo;
	}

	/**
	 * 资产变更待审批
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/5 11:47
	 */
	public EiInfo unconfirmedQuery(EiInfo info) {
		info.setCell("inqu_status", 0, "inAccountStatus", "unconfirmed");
		EiInfo outInfo = super.query(info, "FABG01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultB", "resultB");
		return outInfo;
	}

	/**
	 * 批量审批
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/7 14:04
	 */
	public EiInfo batchApproval(EiInfo info) throws Exception {
		if (info.get("idsList") != null) {
			List<String> idsList = (List<String>) info.get("idsList");
			// 查询主表中对应的资产信息
			List<FaModificationBatchDetailVO> faInfoTOFaModificationVOList = dao.query("FABG01.queryFaInfoTOFaModification", idsList);
			// 查询变更表中对应的资产信息
			List<FaModificationBatchDetailVO> faModificationBatchDetailVOList = dao.query("FABG01.queryFaModificationBatchDetail", idsList);
			if (CollectionUtils.isNotEmpty(idsList)) {
				for (int i = 0; i < idsList.size(); i++) {
					String id = idsList.get(i);
					// 过滤出主表对应的实体
					List<FaModificationBatchDetailVO> faInfo = faInfoTOFaModificationVOList.stream().filter(map -> map.getFaInfoId().equals(id)).collect(Collectors.toList());
					// 过滤出变更表中的实体
					List<FaModificationBatchDetailVO> faModification = faModificationBatchDetailVOList.stream().filter(map -> map.getFaInfoId().equals(id)).collect(Collectors.toList());
					// 对比两个实体中不同的值
					List<ComparisonResult> results = CompareUtils.compareFields(faInfo.get(0), faModification.get(0), FaModificationBatchDetailVO.class, id);
					dao.insert("FABG01.saveComparisonResult", results);
					// 修改主表的信息以及变更状态
					dao.update("FABG01.updateFaInfoByModify", faModification.get(0));
					dao.update("FABG01.updateFaModifyApprovalResult", faModification.get(0));
				}
				info.setMsg("成功审批" + idsList.size() + "条");
			} else {
				info.setMsg("请联系管理员排查数据");
			}
		} else {
			info.setMsg("请选择一条记录");
		}
		return info;
	}

	/**
	 * 批量撤销
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/7 14:03
	 */
	public EiInfo batchRevocation(EiInfo info) {
		if (info.get("idsList") != null) {
			List<String> idsList = (List<String>) info.get("idsList");
			List<String> lastIdList = dao.query("FABG01.queryLastId", idsList);
			int updateModify = dao.update("FABG01.updateModifyInfoByRevocation", lastIdList);
			int updateFaInfo = dao.update("FABG01.updateFaInfoByRevocation", idsList);
			if (updateModify == updateFaInfo) {
				info.setMsg("批量撤销" + updateModify + "条");
			} else {
				info.setStatus(-1);
				info.setMsg("请联系管理员");
			}
		}
		return info;
	}
}
