package com.baosight.wilp.cm.zb.service;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.cm.domain.CmBidBidder;
import com.baosight.wilp.cm.domain.CmBidFile;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author zhaowei
 * @date 2022年01月25日 13:51
 * 1.合同招标管理子界面初始化方法
 * 2.合同招标管理子页面参标单位查询
 * 3.合同招标管理子页面项目附件查询
 * 4.招标文件子界面保存招标信息
 * 5.保存参标单位
 * 6.保存项目附件
 * 7.查询tab页封装方法
 */
public class ServiceCMZB0101 extends ServiceBase {
	/**
	 * 合同招标管理子界面初始化方法
	 * 1.查询参数处理
	 * 2.判断是新增操作还是编辑操作
	 * @author zhaowei
	 * @date 2022/2/7 13:12
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.查询参数处理
		 */
		// 实例化map集合
		Map<String, Object> map = new HashMap();
		String type = info.getString("type");
		String bidId = info.getString("bidId");
		map.put("bidId", bidId);
		// 调用查询方法
		List<Map<String, Object>> query = dao.query("CMZB01.queryBid", map);
		/*
		 * 2.判断是新增操作还是编辑操作
		 */
		if ("new".equals(type)){
			return info;
		}else {
			info.set("type",type);
			info.set("bidId", query.get(0).get("id"));
			info.set("bidNo",query.get(0).get("bidNo"));
			info.set("bidName",query.get(0).get("bidName"));
			info.set("budget",query.get(0).get("budget"));
			info.set("bidPrice",query.get(0).get("bidPrice"));
			info.set("bidContent",query.get(0).get("bidContent"));
			info.set("bidRemark",query.get(0).get("bidRemark"));
			info.set("tenderee", query.get(0).get("tendereeNo"));
			info.set("tenderee_textField", query.get(0).get("tenderee"));
			info.set("undertakeDeptName", query.get(0).get("undertakeDeptNo"));
			info.set("undertakeDeptName_textField", query.get(0).get("undertakeDeptName"));
			info.set("bidWinner", query.get(0).get("bidWinnerNo"));
			info.set("bidWinner_textField", query.get(0).get("bidWinner"));
			return info;
		}
	}

	/**
	 * 合同招标管理子页面参标单位查询
	 * @author zhaowei
	 * @date 2022/2/7 14:21
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryResultA(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "CMZB01.queryResultAList", "CMZB01.queryResultAList", "resultA", new CmBidBidder().eiMetadata);
	}

	/**
	 * 合同招标管理子页面项目附件查询
	 * @author zhaowei
	 * @date 2022/2/7 14:21
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryResultB(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "CMZB01.queryResultBList", "CMZB01.queryResultBList", "resultB", new CmBidFile().eiMetadata);
	}

	/**
	 * 招标文件子界面保存招标信息
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/1/28 9:14
	 * 1.获取人员信息
	 * 2.判断新增还是编辑
	 * 3.调用保存tab页方法
	 * 4.判断新增保存还是编辑保存
	 */
	public EiInfo saveContentBid(EiInfo info) {
		/*
		 * 1.获取人员信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 实例化map集合
		Map<String, Object> map = new HashMap();
		String id;
		String type = info.getString("type");
		String bidNo = StringUtils.isNotEmpty(info.getString("bidNo"))?info.getString("bidNo"): CreateNoUtils.createNo("ZB");
		String bidName = info.getString("bidName");
		String tendereeNo = info.getString("tenderee");
		String tenderee = info.getString("tenderee_textField");
		BigDecimal budget = new BigDecimal(StringUtils.isNotEmpty(info.getString("budget"))?info.getString("budget"):"0");
		String undertakeDeptNo = info.getString("undertakeDeptName");
		String undertakeDeptName = info.getString("undertakeDeptName_textField");
		BigDecimal bidPrice = new BigDecimal(StringUtils.isNotEmpty(info.getString("bidPrice"))?info.getString("bidPrice"):"0");
		String bidWinnerNo = info.getString("bidWinner");
		String bidWinner = info.getString("bidWinner_textField");
		String bidContent = info.getString("bidContent");
		String bidRemark = info.getString("bidRemark");
		// 获取tab页中的数据
		Object resultA = info.get("resultA");
		Object resultB = info.get("resultB");
		/*
		 * 2.判断新增还是编辑
		 */
		if ("new".equals(type)){
			// 新增生成UUID
			id = UUID.randomUUID().toString();
		}else {
			// 编辑获取已有id
			id = info.getString("bidId");
		}
		/*
		 * 3.调用保存tab页方法
		 * 保存参标单位
		 * 保存项目附件
		 */
		saveHta(resultA, id);
		saveHtb(resultB, id);
		map.put("id", id);
		map.put("bidNo", bidNo);
		map.put("bidName", bidName);
		map.put("tendereeNo", tendereeNo);
		map.put("tenderee", tenderee);
		map.put("budget", budget);
		map.put("undertakeDeptNo", undertakeDeptNo);
		map.put("undertakeDeptName", undertakeDeptName);
		map.put("bidPrice", bidPrice);
		map.put("bidWinnerNo", bidWinnerNo);
		map.put("bidWinner", bidWinner);
		map.put("bidContent", bidContent);
		map.put("bidRemark", bidRemark);
		map.put("recCreatorNo", staffByUserId.get("workNo"));
		map.put("recCreator", staffByUserId.get("name"));
		map.put("recCreateTime", DateUtils.curDateTimeStr19());
		/*
		 * 4.判断新增保存还是编辑保存
		 */
		if ("new".equals(type)){
			// 进行新增
			dao.insert("CMZB01.saveContentBid", map);
		}else {
			// 进行编辑
			map.put("recRevisorNo", staffByUserId.get("workNo"));
			map.put("recRevisor", staffByUserId.get("name"));
			map.put("recReviseTime", DateUtils.curDateTimeStr19());
			dao.update("CMZB01.updateContentBid", map);
		}
		return info;
	}

	/**
	 * 保存参标单位
	 * 1.获取人员信息
	 * 2.获取列表中所有的参数单位进行遍历
	 * @author zhaowei
	 * @date 2022/2/7 14:21
	 * @param htaObj
	 * @param id 
	 */
	private void saveHta(Object htaObj, String id) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		/*
		 * 1.获取人员信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 如果入参不为空
		if (htaObj != null) {
			Map<String, Object> map = new HashMap();
			map.put("id", id);
			// 参数赋值
			list = (List<Map<String, Object>>) htaObj;
			// 调用删除方法
			dao.delete("CMZB01.deleteResultA", map);
		}
		int i = 0;
		/*
		 * 2.获取列表中所有的参数单位进行遍历
		 */
		for (Map<String, Object> map : list) {
			// 实例化map
			Map<String, Object> att = new HashMap();
			// 实体转换为参数
			att.put("id", UUID.randomUUID().toString());
			att.put("bidPk", id);
			att.put("bidderNo", map.get("surpNum"));
			att.put("bidderName", map.get("surpName"));
			att.put("sort", i);
			att.put("recCreatorNo", staffByUserId.get("workNo"));
			att.put("recCreator", staffByUserId.get("name"));
			att.put("recCreateTime", DateUtils.curDateTimeStr19());
			// 调用插入方法
			dao.insert("CMZB01.insertResultA", att);
			i++;
		}
	}

	/**
	 * 保存项目附件
	 * 1.获取列表中所有的项目附件进行遍历
	 * @author zhaowei
	 * @date 2022/2/7 14:22
	 * @param htbObj
	 * @param id 
	 */
	private void saveHtb(Object htbObj, String id) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (htbObj != null) {
			// 参数赋值
			list = (List<Map<String, Object>>) htbObj;
		}
		/*
		 * 1.获取列表中所有的项目附件进行遍历
		 */
		for (Map<String, Object> map : list) {
			Map<String, Object> att = new HashMap();
			// 实体转换为参数
			att.put("id", map.get("uploadId"));
			att.put("fileRemark", map.get("fileRemark"));
			att.put("bidPk", id);
			// 调用插入方法
			dao.update("CMZB01.updateResultB", att);
		}
	}

	/**
	 * 查询tab页封装方法
	 * @param inInfo querySql 查询sql
	 *               countSql 统计sql
	 *               resultBlock blockId
	 *               blockMeta EiBlockMeta
	 * @Title: queryTabGrid
	 * @Description: TabGrid查询方法
	 * @return: EiInfo
	 */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
								EiBlockMeta blockMeta) {
		// 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		map.put("bidId",inInfo.getString("bidId"));
		// 查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		// 获取总数
		int count = dao.count(countSql, map);
		// 数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
}
