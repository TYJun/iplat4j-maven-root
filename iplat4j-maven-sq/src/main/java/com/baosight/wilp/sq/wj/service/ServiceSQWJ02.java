package com.baosight.wilp.sq.wj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.sq.common.TyepCode;
import com.baosight.wilp.sq.common.UtilCode;
import com.baosight.wilp.sq.wj.domain.SQWJ01;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新增问卷逻辑处理层，页面初始化方法，查询方法 ，保存方法,问卷范围下拉框,问卷范围二级联动下拉框
 * <p>页面初始化方法 initload
 * <p>查询方法 query
 * <p>保存方法 add
 * <p>问卷范围下拉框query2
 * <p>问卷范围二级联动下拉框query3
 *
 * @Title: ServiceSQWJ02.java
 * @ClassName: ServiceSQWJ02
 * @Package：com.baosight.wilp.sq.wj.service
 * @author: zhangjiaqian
 * @date: 2021年7月28日 下午4:00:41
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQWJ02 extends ServiceBase {
	/**
	 * 页面初始化方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		String manageId = inInfo.getString("manageId");
		String type = inInfo.getString("type");
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new EiBlockMeta());
		if ("add".equals(type)) {
			inInfo.set("manageId", manageId);
			inInfo.set("type", type);
			return inInfo;
		} else if ("edit".equals(type)) {
			map.put("manageId", manageId);
			List<Map<String, Object>> manageList = dao.query("SQWJ02.queryManage", map);
			if (CollectionUtils.isNotEmpty(manageList)) {
				block.addRows(manageList);
				EiColumn column2 = new EiColumn("workNameLeader");
				EiColumn column1 = new EiColumn("workNameLeader_textField");
				block.setCell(0, "workNameLeader_textField", manageList.get(0).get("workNameLeader"));
				block.setCell(0, "workNameLeader", manageList.get(0).get("workNoLeader"));
				block.addMeta(column1);
				block.addMeta(column2);
				inInfo.addBlock(block);
				inInfo.set("inqu_status-0-standardName", manageList.get(0).get("standardCode"));
				return inInfo;
			}
		} else if ("plan".equals(type)) {
			map.put("manageId", manageId);
			List<Map<String, Object>> manageList = dao.query("SQWJ02.queryManage", map);
			if (CollectionUtils.isNotEmpty(manageList)) {
				block.addRows(manageList);
				EiColumn column2 = new EiColumn("workNameLeader");
				EiColumn column1 = new EiColumn("workNameLeader_textField");
				block.setCell(0, "workNameLeader_textField", manageList.get(0).get("workNameLeader"));
				block.setCell(0, "workNameLeader", manageList.get(0).get("workNoLeader"));
				block.addMeta(column1);
				block.addMeta(column2);
				inInfo.addBlock(block);
				inInfo.set("inqu_status-0-standardName", manageList.get(0).get("standardCode"));
				return inInfo;
			}
		}
		return inInfo;
	}

	/**
	 * 问卷登记考试
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/21 16:04
	 */
	public EiInfo insertSQManage(EiInfo inInfo) {
		String type = inInfo.getString("type");
		String manageId = inInfo.getString("manageId");
		// 生成批次号
		String standardCode = inInfo.getString("standardCode");
		String standardName = inInfo.getString("standardName");
		String beginDate = inInfo.getString("beginDate");
		String endDate = inInfo.getString("endDate");
		String workNo = inInfo.getString("workNo");
		String workName = inInfo.getString("workName");
		String canteenNum = inInfo.getString("canteenNum");
		String mealNum = inInfo.getString("mealNum");
		String mealName = inInfo.getString("mealName");
		String isCycle = inInfo.getString("isCycle");
		String cycleTime = inInfo.getString("cycleTime");
		String cycleTimeUnit = inInfo.getString("cycleTimeUnit");
		//获取院区编码
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		//获取当前时间
		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//构建保存参数
		Map<String, Object> map = new HashMap();
		map.put("standardCode", standardCode);
		map.put("standardName", standardName);
		map.put("workNo", workNo);
		map.put("workName", workName);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("assessRange", canteenNum);
		map.put("projectcode", mealNum);
		map.put("projectName", mealName);
		map.put("isCycle", isCycle);
		map.put("cycleTime", cycleTime);
		map.put("cycleTimeUnit", cycleTimeUnit);
		map.put("creator", staffByUserId.get("name"));
		map.put("createTime", createTime);
		// 新增状态
		map.put("statusCode", "00");
		map.put("datagroupCode", datagroupCode);
		Map<String, Object> cycle = isCycle(map);
		// 判断是新增还是编辑
		if ("add".equals(type)) {
			// 查询问卷中是否存在空的明细
			List<Map<String, Object>> projectList = dao.query("SQWJ02.queryProject", standardCode);
			if (CollectionUtils.isNotEmpty(projectList)) {
				for (Map<String, Object> projectMap : projectList) {
					String projectId = (String) projectMap.get("projectId");
					List<Map<String, Object>> projectCodeList = dao.query("SQWJ02.queryInstance", projectId);
					if (CollectionUtils.isEmpty(projectCodeList)) {
						inInfo.setMsg("问卷明细不完整，请先完善明细，再新增问卷！");
						inInfo.setStatus(-1);
						return inInfo;
					}
				}
			}
			cycle.put("manageId", UUID.randomUUID().toString());
			cycle.put("batchNo", UtilCode.manageCode());
			dao.insert("SQWJ02.insertManage", cycle);
			// 生成问卷
			createExam(cycle);
		} else if ("edit".equals(type)) {
			cycle.put("manageId", manageId);
			dao.update("SQWJ02.updateManage", cycle);
		} else if ("plan".equals(type)) {
			cycle.put("manageId", manageId);
			dao.update("SQWJ02.updateCyclePlan", cycle);
		}
		return inInfo;
	}

	/**
	 * 创建问卷
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/24 11:26
	 */
	public void createExam(Map<String, Object> map) {
		// 将当前的问卷封存
		dao.insert("SQWJ02.insertExamStandard", map);
		dao.insert("SQWJ02.insertExamProject", map);
		dao.insert("SQWJ02.insertExamInstance", map);
		dao.insert("SQWJ02.insertExamItem", map);
	}


	/**
	 * 问卷范围下拉框
	 *
	 * @param inInfo
	 * @return
	 * @Title: query2
	 * @return: EiInfo
	 */
	public EiInfo query2(EiInfo inInfo) {
		List<Map<String, String>> param = new ArrayList<Map<String, String>>();
		try {
			param = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.sq");
		} catch (Exception e) {

		}
		inInfo.addBlock("canteenData").addRows(param);
		inInfo.getBlock("canteenData").setBlockMeta(new SQWJ01().eiMetadata);
		return inInfo;
	}


	/**
	 * 问卷范围二级联动下拉框
	 *
	 * @param inInfo canteenNum      手机号
	 * @return perGroupName   人员分组名称
	 * perGroupNo     人员分组编号
	 * @Title: query2
	 * @return: EiInfo
	 */
	public EiInfo query3(EiInfo inInfo) {
		EiBlock block = inInfo.getBlock("inqu_status");
		Map row = block.getRow(0);
		Object object = row.get("canteenNum");
		if (object.equals("0")) {
			inInfo.addBlock("mealNum").addRows(null);
			inInfo.getBlock("mealNum").setBlockMeta(new SQWJ01().eiMetadata);
			return inInfo;
		}
		List query = dao.query("SQWJ02.queryName", null);
		inInfo.addBlock("mealNum").addRows(query);
		inInfo.getBlock("mealNum").setBlockMeta(new SQWJ01().eiMetadata);
		return inInfo;
	}

	/**
	 * 是否按周期生成下次开始时间和结束时间
	 * 1.通过获取是否周期生成的标记位
	 * 2.进行逻辑判断
	 * a.按照周期生成
	 * 下次开始时间与下次结束时间+对应周期
	 * b.不按周期生成
	 * 将下次开始时间与下次结束时间置空
	 *
	 * @param map
	 * @return Map
	 * @author zhaowei
	 * @date 2022/3/28 10:00
	 */
	public Map<String, Object> isCycle(Map<String, Object> map) {
		// 获取参数
		String isCycle = (String) map.get("isCycle");
		String cycleTime = (String) map.get("cycleTime");
		String cycleTimeUnit = (String) map.get("cycleTimeUnit");
		String beginString = (String) map.get("beginDate");
		String endString = (String) map.get("endDate");
		try {
			// 格式转换
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// String格式转化为Date格式
			Date beginDate = simpleDateFormat.parse(beginString);
			Date endDate = simpleDateFormat.parse(endString);
			// 创建日历类
			Calendar beginCalendar = new GregorianCalendar();
			Calendar endCalendar = new GregorianCalendar();
			// 设置日历类时间
			beginCalendar.setTime(beginDate);
			endCalendar.setTime(endDate);
			// 是
			if ("1".equals(isCycle)) {
				// 判断下次生成单位
				switch (cycleTimeUnit) {
					// 年
					case "year":
						// 增加单位为年
						beginCalendar.add(Calendar.YEAR, Integer.parseInt(cycleTime));
						endCalendar.add(Calendar.YEAR, Integer.parseInt(cycleTime));
						break;
					// 月
					case "month":
						// 增加单位为月
						beginCalendar.add(Calendar.MONTH, Integer.parseInt(cycleTime));
						endCalendar.add(Calendar.MONTH, Integer.parseInt(cycleTime));
						break;
					// 日
					case "day":
						// 增加单位为日
						beginCalendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(cycleTime));
						endCalendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(cycleTime));
						break;
					default:
						// 抛出异常
						throw new IllegalStateException("Unexpected value: " + cycleTimeUnit);
				}
				// 日历类转化成Date转化为日历类
				map.put("nextBeginDate", simpleDateFormat.format(beginCalendar.getTime()));
				map.put("nextEndDate", simpleDateFormat.format(endCalendar.getTime()));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return map;
	}

}
