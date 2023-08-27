package com.baosight.wilp.cm.zb.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhaowei
 * @date 2022年01月24日 16:42
 * 1.合同招标管理页面初始化方法
 * 2.合同招标管理查询方法
 * 3.合同招标管理保存招标文件
 * 4.合同招标管理删除招标文件
 * 5.合同招标管理删除方法
 * 6.合同招标管理生成合同
 */
public class ServiceCMZB01 extends ServiceBase {
	/**
	 * 合同招标管理页面初始化方法
	 * 1.调用当前query方法
	 * @author zhaowei
	 * @date 2022/2/7 13:12
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.调用当前query方法
		 */
		return this.query(info);
	}

	/**
	 * 合同招标管理查询方法
	 * 1.分页处理
	 * 2.数据处理
	 * 3.调用查询方法
	 * 4.参数封装返回
	 * @author zhaowei
	 * @date 2022/2/7 13:15
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo query(EiInfo info) {
		/*
		 * 1.分页处理
		 * 实例化起始条目，每页大小
		 */
		int offset, limit;
		// 判断是否存在分页
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
		String bidName = info.getString("bidName");
		map.put("bidName", bidName);
		/*
		 * 3.调用查询方法
		 * 调用查询方法
		 * 返回查询结果和条目数
		 */
		List<Map<String, Object>> query = dao.query("CMZB01.queryBid", map, offset, limit);
		int count = dao.count("CMZB01.queryBid", map);
		/*
		 * 4.参数封装返回
		 * 将查询结果绑定result
		 * 将分页参数重新绑定
		 */
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
	 * 合同招标管理保存招标文件
	 * 1.获取当前登录人基础信息
	 * @author zhaowei
	 * @date 2022/2/7 13:25
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo saveContentBidFile(EiInfo info) {
		/*
		 * 1.获取当前登录人基础信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 获取人员姓名
		String loginName = (String) staffByUserId.get("name");
		// 获取人员工号
		String workNo = (String) staffByUserId.get("workNo");
		/*
		 * 2.封装查询条件参数
		 */
		// 实例化map集合
		Map<String, Object> map = new HashMap();
		// 获取参数
		String uploadId = info.getString("uploadId");
		String fileName = info.getString("fileName");
		String filePath = info.getString("filePath");
		String fileSize = info.getString("fileSize");
		// 封装参数
		map.put("uploadId", uploadId);
		map.put("fileName", fileName);
		map.put("filePath", filePath);
		map.put("fileSize", fileSize);
		map.put("recCreatorNo", workNo);
		map.put("recCreator", loginName);
		map.put("recCreateTime", DateUtils.curDateTimeStr19());
		/*
		 * 3.调用插入方法
		 */
		// 将封装的参数持久化保存
		dao.insert("CMZB01.saveContentBidFile", map);
		return info;
	}

	/**
	 * 合同招标管理删除招标文件
	 * 1.处理查询参数
	 * 2.调用删除方法
	 * @author zhaowei
	 * @date 2022/2/7 13:28
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo deleteContentBidFile(EiInfo info) {
		/*
		 * 1.处理查询参数
		 */
		// 实例化map
		Map<String, Object> map = new HashMap();
		String fileId = info.getString("fileId");
		String filePath = info.getString("filePath");
		map.put("fileId", fileId);
		/*
		 * 2.合同招标管理删除方法
		 */
		dao.delete("CMZB01.deleteContentBidFile", map);
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

	/**
	 * 合同招标管理删除方法
	 * 1.查询参数处理
	 * 2.调用查询方法
	 * 3.进行关联处理
	 * @author zhaowei
	 * @date 2022/2/7 13:41
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo deleter(EiInfo info) {
		/*
		 * 1.查询参数处理
		 */
		// 实例化map
		Map<String, Object> map = new HashMap<>();
		String bidId = info.getString("bidId");
		map.put("bidId", bidId);
		/*
		 * 2.调用查询方法
		 * 获取当前记录对应的上传文件
		 */
		List<Map<String, Object>> list = dao.query("CMZB01.queryResultBList", map);
		// 判断是否关联文件
		if (CollectionUtils.isNotEmpty(list)){
			Map<String, Object> result = list.get(0);
			result.put("fileId", result.get("uploadId"));
			info.setAttr(list.get(0));
		}
		/*
		 * 3.进行关联处理
		 * 关联文件删除
		 * 关联参标单位删除
		 * 招标记录删除
		 */
		deleteContentBidFile(info);
		dao.delete("CMZB01.deleteContentBidBidder", map);
		dao.delete("CMZB01.deleteContentBid", map);
		return info;
	}

	/**
	 * 合同招标管理生成合同
	 * 1.获取当前登录人基础信息
	 * 2.查询参数处理
	 * 3.调用方法持久化数据
	 * @author zhaowei
	 * @date 2022/2/7 14:13
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo createContract(EiInfo info) {
		/*
		 * 1.获取当前登录人基础信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 获取人员姓名
		String loginName = (String) staffByUserId.get("name");
		// 获取人员工号
		String workNo = (String) staffByUserId.get("workNo");
		/*
		 * 2.查询参数处理
		 */
		// 实例化map参数
		Map<String, Object> map = new HashMap();
		String bidId = info.getString("bidId");
		String bidWinnerNo = info.getString("bidWinnerNo");
		String bidWinner = info.getString("bidWinner");
		String bidName = info.getString("bidName");
		String tendereeNo = info.getString("tendereeNo");
		String tenderee = info.getString("tenderee");
		String undertakeDeptNo = info.getString("undertakeDeptNo");
		String undertakeDeptName = info.getString("undertakeDeptName");
		String budget = info.getString("budget");
		map.put("id", UUID.randomUUID().toString());
		map.put("bidId", bidId);
		map.put("contNo", CreateNoUtils.createNo("HT"));
		map.put("contAdmin", tendereeNo);
		map.put("contAdminName", tenderee);
		map.put("contName", bidName);
		map.put("surpNum", bidWinnerNo);
		map.put("surpName", bidWinner);
		map.put("contDeptNum", undertakeDeptNo);
		map.put("contDeptName", undertakeDeptName);
		map.put("budget", budget);
		map.put("recCreator", loginName);
		map.put("recCreateNo",workNo);
		map.put("recCreateTime", DateUtils.curDateTimeStr19());
		/*
		 * 3.调用方法持久化数据
		 */
		// 生成合同记录
		dao.insert("CMZB01.createContract",map);
		// 更新招标记录
		dao.update("CMZB01.updateContract",map);
		return info;
	}
}