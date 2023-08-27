package com.baosight.wilp.sq.wj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时开始开始任务结束任务逻辑处理类，定时生成问卷和结束问卷，返回当前时间后一天时间
 * <p>定时生成问卷和结束问卷 timeTask
 * <p>返回当前时间后一天时间 time
 *
 * @Title: ServiceTimeTask.java
 * @ClassName: ServiceTimeTask
 * @Package：com.baosight.wilp.sq.wj.service
 * @author: zhangjiaqian
 * @date: 2021年8月19日 下午5:19:39
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQWJTimeTask extends ServiceBase {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory.getLogger(ServiceSQWJTimeTask.class);


	/**
	 * 定时生成问卷和结束问卷
	 *
	 * @Title: timeTask
	 * @return: void
	 */
	//@Scheduled(cron = "0 0/1 * * * ?")
	public EiInfo timeTask(EiInfo inInfo) {
		//获取当前时间
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//查询还未开始的问卷
		List<Map<String,Object>> query = dao.query("SQWJ01.queryBeginManage", time);
		//遍历比对时间
		if (query != null || query.size() > 0) {
			for (Map<String,Object> queryMap : query) {
				dao.update("SQWJ01.updateBeginManage", queryMap);
			}
		}
		// 获取昨天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.add(Calendar.DAY_OF_MONTH, -1);
		String yesterDay = sdf.format(now.getTime());
		//查询还未结束的问卷
		List<Map<String,Object>> endQuery = dao.query("SQWJ01.queryEndManage", yesterDay);
		//遍历比对时间
		if (endQuery != null || endQuery.size() > 0) {
			for (Map<String,Object> endQueryMap : endQuery) {
				dao.update("SQWJ01.updateEndManage", endQueryMap);
			}
		}
		return inInfo;
	}


	/**
	 * 返回当前时间后一天时间
	 *
	 * @return
	 * @Title: time
	 * @return: Date
	 */
	public String time() {
		//获取当前时间
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//简单日期格式
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = null;
		try {
			sDate = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//获取指定时间点
		Calendar c = Calendar.getInstance();
		c.setTime(sDate);
		//时间加一天
		c.add(Calendar.DAY_OF_MONTH, 1);
		//时间数
		sDate = c.getTime();
		//设置时间格式
		String format = simpleDateFormat.format(sDate);
		//返回
		return format;
	}


	/**
	 * 下次自动生成问卷定时方法
	 *
	 * @author zhaowei
	 * @date 2022/3/28 11:34
	 */
	public EiInfo autoCreateStandard(EiInfo info) {
		//
		Map<String, Object> map = new HashMap<>();
		map.put("day","1");
		// 查询需要自动生成的问卷编号
		List<Map<String, Object>> autoCreateStandardCodeList = dao.query("SQWJ01.queryIscycleAutoCreateStandard", map);
		// 如果存在
		if (CollectionUtils.isNotEmpty(autoCreateStandardCodeList)) {
			// 将编号进行遍历
			for (Map<String, Object> autoCreateStandardCodeMap : autoCreateStandardCodeList) {
				// 生成批次号
				autoCreateStandardCodeMap.put("manageId", UUID.randomUUID().toString());
				autoCreateStandardCodeMap.put("batchNo", createNewBatchNo(autoCreateStandardCodeMap));
				autoCreateStandardCodeMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				dao.insert("SQWJ02.autoCreateManage", autoCreateStandardCodeMap);
				// 将之前的问卷封存
				dao.insert("SQWJ02.autoCreateExamStandard", autoCreateStandardCodeMap);
				dao.insert("SQWJ02.autoCreateExamProject", autoCreateStandardCodeMap);
				dao.insert("SQWJ02.autoCreateExamInstance", autoCreateStandardCodeMap);
				dao.insert("SQWJ02.autoCreateExamItem", autoCreateStandardCodeMap);
				// 更新下次生成时间
				Map<String, Object> updateMap = updateNextDate(autoCreateStandardCodeMap);
				dao.update("SQWJ02.updateAutoCreateManage", updateMap);
			}
		}
		return info;
	}

	// 生成新的子批次号
	public String createNewBatchNo(Map<String, Object> map) {
		String newBatchNo = "";
		List<Map<String, Object>> newBatchNoList = dao.query("SQWJ01.createNewBatchNo", map);
		if (CollectionUtils.isNotEmpty(newBatchNoList)) {
			String batchNo = (String) newBatchNoList.get(0).get("batchNo");
			if (batchNo.length() > 12) {
				newBatchNo = batchNo.substring(0, 12) + "-" + String.format("%03d", (Integer.valueOf(batchNo.substring(13)) + 1));
			} else {
				newBatchNo = batchNo + "-001";
			}
		}
		return newBatchNo;
	}

	// 生成子问卷生成新的生成时间
	public Map<String, Object> updateNextDate(Map<String, Object> map) {
		Date beginDate = (Date) map.get("nextBeginDate");
		Date endDate = (Date) map.get("nextEndDate");
		String cycleTime = (String) map.get("cycleTime");
		String cycleTimeUnit = (String) map.get("cycleTimeUnit");
		try {
			// 格式转换
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 创建日历类
			Calendar beginCalendar = new GregorianCalendar();
			Calendar endCalendar = new GregorianCalendar();
			// 设置日历类时间
			beginCalendar.setTime(beginDate);
			endCalendar.setTime(endDate);
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
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return map;
	}
}
