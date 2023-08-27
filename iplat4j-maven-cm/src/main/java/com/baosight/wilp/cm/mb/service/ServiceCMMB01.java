package com.baosight.wilp.cm.mb.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合同模板管理
 * 1.合同模板管理初始化方法
 * 2.合同模板管理查询方法
 * 3.合同模板管理保存模板文件
 * 4.合同模板管理启用方法
 * 5.合同模板管理停用方法
 * 6.合同招标管理模板删除
 * @author zhaowei
 * @date 2022年01月21日 15:17
 */
public class ServiceCMMB01 extends ServiceBase {
	/**
	 * 合同模板管理初始化方法
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/1/21 17:09
	 */
	public EiInfo initLoad(EiInfo info) {
		// 调用当前query方法
		return this.query(info);
	}

	/**
	 * 合同模板管理查询方法
	 * 1.分页处理
	 * 2.数据处理
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/1/21 17:37
	 */
	public EiInfo query(EiInfo info) {
		/*
		 * 1.分页处理
		 * 实例化起始条目，每页大小
		 */
		int offset, limit;
		// 判断是否分页
		if (info.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			// 获取分页组件中的起始条目与每页大小
			EiBlock result = (EiBlock) info.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		/*
		 * 2.数据处理
		 * 获取查询条件
		 * 存入map集合中
		 */
		Map<String, Object> map = new HashMap();
		String fileName = info.getString("fileName");
		map.put("fileName", fileName);
		List<Map<String, Object>> query = dao.query("CMMB01.queryContentTemplateFile", map, offset, limit);
		int count = dao.count("CMMB01.queryContentTemplateFile", map);
		info.setRows("result", query);
		// 处理分页
		EiBlock result = (EiBlock) info.getBlocks().get("result");
		result.setAttr(new HashMap<>());
		// 判断返回结果是否存在attr
		if (result.getAttr().isEmpty()) {
			// 重新封装分页参数
			Map<String, Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			// 添加分页总数
			result.getAttr().put("count", count);
		}
		return info;
	}

	/**
	 * 合同模板管理保存模板文件
	 * @author zhaowei
	 * @date 2022/2/7 15:07
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo saveContentTemplateFile(EiInfo info) {
		// 获取当前登录人基础信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		String workNo = (String) staffByUserId.get("workNo");
		// 构建一个map集合用于存储查询信息
		Map<String, Object> map = new HashMap();
		String uploadId = info.getString("uploadId");
		String fileName = info.getString("fileName");
		String filePath = info.getString("filePath");
		String fileSizeStr = info.getString("fileSize");
		BigDecimal fileSize = new BigDecimal(fileSizeStr);
		map.put("uploadId", uploadId);
		map.put("filePath", filePath);
		map.put("fileName", fileName);
		map.put("fileSize", fileSize);
		map.put("fileStatus", 1);
		map.put("recCreatorNo", workNo);
		map.put("recCreator", loginName);
		map.put("recCreateTime", DateUtils.curDateTimeStr19());
		// 调用保存方法
		dao.insert("CMMB01.saveContentTemplateFile", map);
		return info;
	}

	/**
	 * 合同模板管理启用方法
	 * @author zhaowei
	 * @date 2022/2/7 15:08
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo startTemplateFile(EiInfo info) {
		// 获取人员信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		String workNo = (String) staffByUserId.get("workNo");
		// 实例化map集合存储信息
		Map<String, Object> map = new HashMap();
		String fileId = info.getString("fileId");
		map.put("fileId", fileId);
		map.put("recRevisorNo", workNo);
		map.put("recRevisor", loginName);
		map.put("recReviseTime", DateUtils.curDateTimeStr19());
		// 调用更新方法
		dao.update("CMMB01.startTemplateFile", map);
		return info;
	}

	/**
	 * 合同模板管理停用方法
	 * @author zhaowei
	 * @date 2022/2/7 15:09
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo stopTemplateFile(EiInfo info) {
		// 获取人员信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		String workNo = (String) staffByUserId.get("workNo");
		// 实例化map集合存储信息
		Map<String, Object> map = new HashMap();
		String fileId = info.getString("fileId");
		map.put("fileId", fileId);
		map.put("recRevisorNo", workNo);
		map.put("recRevisor", loginName);
		map.put("recReviseTime", DateUtils.curDateTimeStr19());
		// 调用更新方法
		dao.update("CMMB01.stopTemplateFile", map);
		return info;
	}

	/**
	 * 合同招标管理模板删除
	 * @author zhaowei
	 * @date 2022/2/7 15:10
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo deleterTemplateFile(EiInfo info) {
		// 实例化map
		Map<String, Object> map = new HashMap();
		String fileId = info.getString("fileId");
		String filePath = info.getString("filePath");
		map.put("fileId", fileId);
		// 调用删除方法
		dao.delete("CMMB01.deleterTemplateFile", map);
		// 物理删除，获取文件
		if (StringUtils.isNotEmpty(filePath)){
			File file = new File(filePath);
			//判断是否存在
			if (file.exists()) {
				file.delete();
			}
		}
		return info;
	}
}
