package com.baosight.wilp.cm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cm.domain.TpmSmsConfig;
import com.baosight.wilp.common.util.BaseDockingUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 合同项目：发送短信
 * <p>1.发送 sendCmMsg
 *
 * @Title: ServiceCMsendMsg.java
 * @ClassName: ServiceCMsendMsg
 * @Package：com.baosight.wilp.pm.service
 * @author: gao
 * @date: 2021年11月19日 下午5:17:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMsendMsg extends ServiceBase {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @param inInfo
	 * @return inInfo
	 * @Title: sendCmMsg
	 * @Description: 发送
	 * <p>1.获取当前时间
	 * <p>2.获取当前时间点
	 * <p>3.查询短信配置数据
	 * <p>4.如果短信配置不为空，则循环获取值
	 * <p>5.循环获取值
	 * <p>6.赋值开始时间和结束时间
	 * <p>7.判断没有配置付款超期并且是执行状态  4代表付款超期配置,
	 * <p>8.循环合同发送短信
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo sendCmMsg(EiInfo inInfo) {
		//1.获取当前时间
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		//2.获取当前时间点
		String nowTime = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String nowStart = sdf.format(date);
		//String nowEnd=sdf.format(date)+" 23:59:59";
		// 3.查询短信配置数据
		EiInfo info = super.query(inInfo, "CM09.query", new TpmSmsConfig());
		List<Map<String, String>> sengMsg = info.getBlock("result") == null ? null : info.getBlock("result").getRows();
		//4.如果短信配置不为空，则循环获取值
		if (!sengMsg.isEmpty()) {
			//5.循环获取值
			sengMsg.forEach(map -> {
				//6.赋值开始时间和结束时间
				map.put("nowStart", nowStart);
				// map.put("nowEnd",nowEnd);
				//7.判断没有配置付款超期并且是执行状态  4代表付款超期配置,
				if (!map.get("id").equals("4") && map.get("isRuning").equals("1") && !map.get("lateDays").isEmpty()
						&& !map.get("time").isEmpty() && map.get("time").equals(nowTime)||!map.get("time1").isEmpty() && map.get("time1").equals(nowTime)) {
					//获取短信模板
					String msg = map.get("smsTemp");
					//获取所有满足短信发送条件的合同
					List<Map<String, String>> result = dao.query("CM09.queryCm", map);
					//8.循环合同发送短信
					result.forEach(map1 -> {
						String msgText = "";
						//获取单个合同的短信模板
						List<String> paraList = new ArrayList<>();
						msgText = msg.replace("$cont_name$", map1.get("contName"));
						msgText = msgText.replace("$cont_no$", map1.get("contNo"));
						msgText = msgText.replace("$day$", map.get("lateDays"));
						paraList.add(msgText);
						//获取单个合同的短信发送人
						List<String> workList = dao.query("CM09.queryPersonList", map1.get("id"));
						BaseDockingUtils.sendMsg(workList, paraList, "TP00001");
					});
				} else if (map.get("id").equals("4") && map.get("isRuning").equals("1") && !map.get("lateDays").isEmpty()
						&& !map.get("time").isEmpty() && map.get("time").equals(nowTime)||!map.get("time1").isEmpty() && map.get("time1").equals(nowTime)) {
					//获取短信模板
					String msg = map.get("smsTemp");
					//获取所有满足付款短信发送条件的合同
					List<Map<String, String>> result = dao.query("CM09.queryCmFk", map);
					//循环付款合同发送短信
					result.forEach(map1 -> {
						String msgText = "";
						//获取单个合同的短信模板
						List<String> paraList = new ArrayList<>();
						msgText = msg.replace("$cont_name$", map1.get("contName"));
						msgText = msgText.replace("$cont_no$", map1.get("contNo"));
						msgText = msgText.replace("$day$", map.get("lateDays"));
						paraList.add(msgText);
						//获取单个合同的短信发送人
						List<String> workList = dao.query("CM09.queryPersonList", map1.get("id"));
						BaseDockingUtils.sendMsg(workList, paraList, "TP00001");
					});
				}
			});

		}
		return inInfo;
	}
}
