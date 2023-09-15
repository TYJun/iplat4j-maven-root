package com.baosight.wilp.fa.cf.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.fa.cf.domain.FaSplitVO;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定资产拆分管理类.
 * 固定资产拆分管理初始化方法.
 * 固定资产拆分管理查询方法.
 * 固定资产拆分管理移除方法.
 * @author zhaowei
 * @date 2022年07月13日 9:51
 */
public class ServiceFACF01 extends ServiceBase {
	/**
	 * 固定资产拆分管理初始化方法.
	 * 1.调用固定资产拆分管理查询方法.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:30
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return confirmedQuery(info);
	}

	/**
	 * 固定资产拆分管理查询方法.
	 * 1.权限判断.
	 * 2.构建分页参数.
	 * 3.查询固定资产拆分查询方法.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:30
	 */
	@Override
	public EiInfo query(EiInfo info) {
		// 1.权限判断
		String deptName = OneSelfUtils.specifyDept();
		if (StringUtils.isNotEmpty(deptName)) {
			info.set("deptName", deptName);
		}
		// 2.构建分页参数
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 15);
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		info.set("split", "true");
		// 3.查询固定资产拆分查询方法
		List resultList = dao.query("FADA01.query", info.getAttr(), (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("FADA01.query", info.getAttr());
		pageMap.put("count", count);
		info.setRows("result", resultList);
		info.setAttr(pageMap);
		return info;
	}

	/**
	 * 固定资产拆分管理移除方法.
	 * 1.删除固定资产拆分管理信息，删除固定资产已拆分管理信息，修改固定资产被拆分信息
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:30
	 */
	public EiInfo remove(EiInfo info) {
		// 1.删除固定资产拆分管理信息，删除固定资产已拆分管理信息，修改固定资产被拆分信息
		dao.delete("FACF01.remove", info.getAttr());
		dao.delete("FADA01.deleteBySplit",info.getAttr());
		dao.update("FADA01.modificationBySplit", info.getAttr());
		return info;
	}

	/**
	 * 资产信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 15:51
	 */
	public EiInfo confirmedQuery(EiInfo info) {
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
		EiInfo outInfo = super.query(info, "FACF01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultA", "resultA");
		// 1.获取参数,处理参数
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
		// 2.调用微服务接口S_AC_FW_05，获取科室信息
		map.remove("limit");
		List<Map<String, String>> maps = dao.query("FADA01.queryDept", map);
		outInfo.setRows("dept", maps);
		return outInfo;
	}

	public EiInfo splitApplyQuery(EiInfo info){
		info.setCell("inqu_status", 0, "statusCode", "apply");
		EiInfo outInfo = super.query(info, "FACF01.queryFaSplitVOInfo", new FaSplitVO(), false, null, null, "resultB", "resultB");
		return outInfo;
	}


	public EiInfo splitRecordQuery(EiInfo info){
		EiInfo outInfo = super.query(info, "FACF01.queryFaSplitVOInfo", new FaSplitVO(), false, null, null, "resultC", "resultC");
		return outInfo;
	}
}
