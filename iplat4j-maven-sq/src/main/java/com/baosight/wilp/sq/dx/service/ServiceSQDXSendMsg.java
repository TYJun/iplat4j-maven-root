package com.baosight.wilp.sq.dx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.common.RecevierEnum;
import com.baosight.wilp.sq.common.SqSmsConfig;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaowei
 * @date 2022年04月04日 15:06
 */
public class ServiceSQDXSendMsg extends ServiceBase {
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @param inInfo
	 * @return inInfo
	 * @Title: sendSQMsg
	 * @Description: 发送短信
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
	public EiInfo sendSQMsg(EiInfo inInfo) {
		// 1.获取当前时间
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		//2.获取当前时间点
		String nowTime = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String nowStart = simpleDateFormat.format(date);
		//获取app编码
		String appCode = "AP00002";
		// 3.查询短信配置数据
		EiInfo info = super.query(inInfo, "SQDX01.querySQMsg", new SqSmsConfig());
		List<Map<String, String>> querySQMsgList = info.getBlock("result") == null ? null : info.getBlock("result").getRows();
		System.out.println("querySQMsgList的值："+querySQMsgList);
		if (CollectionUtils.isNotEmpty(querySQMsgList)) {
			// foreach循环
			querySQMsgList.forEach(map -> {
				System.out.println("nnnnn");
				// 通过config_type判断是什么类型的短信
				// sq1-问卷截止提醒
				// sq2-问卷周期生成提醒
				if ("sq1".equals(map.get("configType")) && "1".equals(map.get("isRunning"))  && StringUtils.isNotEmpty(map.get("day"))) {
					//获取单个合同的短信模板
					List<String> paramList = new ArrayList<>();
					List<Map<String, String>> recvList = new ArrayList<>();
					// 查询正在执行中且要到期的问卷
					List<Map<String, Object>> adventManageList = dao.query("SQWJ01.queryAdventManage", map);
					System.out.println("adventManageList："+adventManageList);
					// 遍历临期问卷
					adventManageList.forEach(adventManageMap -> {
						String smsTemp = "";
						smsTemp = map.get("smsTemp").replace("$standard_name$", (String) adventManageMap.get("standardName"));
						smsTemp = smsTemp.replace("$batch_no$", (String) adventManageMap.get("batchNo"));
						smsTemp = smsTemp.replace("$day$", (String) adventManageMap.get("endDate"));
						paramList.add(smsTemp);
						// 获得接收人员编码
						String smsRecvCode = map.get("smsRecvCode");
						String[] split = smsRecvCode.split(",");
						for (String code : split) {
							recvList.addAll(getList(RecevierEnum.valueOf(code).getStatement(), adventManageMap));
						}
						List<String> workNoList = new ArrayList<>();
						for (Map<String, String> workNo : recvList) {
							workNoList.add(workNo.get("workNo"));
						}
						workNoList = workNoList.stream().distinct().collect(Collectors.toList());
						//发送短信
						BaseDockingUtils.sendMsg(workNoList, paramList, "TP00001");
						//发送企业微信
						BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);
					});
				} else if ("sq2".equals(map.get("configType")) && "1".equals(map.get("isRunning"))  && StringUtils.isNotEmpty(map.get("day"))) {
					// 提前1天，查询需要自动生成的问卷编号
					List<Map<String, Object>> autoCreateStandardCodeList = dao.query("SQWJ01.queryIscycleAutoCreateStandard", map);
					// 循环发送短信
					autoCreateStandardCodeList.forEach(autoCreateStandardCodeMap -> {
						String smsTemp = "";
						//获取单个合同的短信模板
						List<String> paraList = new ArrayList<>();
						smsTemp = map.get("smsTemp").replace("$standard_name$", (String) autoCreateStandardCodeMap.get("standardName"));
						smsTemp = smsTemp.replace("$day$", map.get("day"));
						paraList.add(smsTemp);
						//获取单个合同的短信发送人
						List<String> workNoList = new ArrayList<>();
						workNoList.add((String) autoCreateStandardCodeMap.get("workNoLeader"));
						// 发送短信
						BaseDockingUtils.sendMsg(workNoList, paraList, "TP00001");
						//发送企业微信
						BaseDockingUtils.pushWxMsg(workNoList, paraList, "TP00001", appCode);
					});
				}
			});
		}
		return inInfo;
	}

	private List<Map<String, String>> getList(String statement, Map<String, Object> adventManageMap) {
		List<Map<String, String>> list = new ArrayList<>();
		list = dao.query("SQDX01." + statement, adventManageMap);
		if (list.size() > 0) {
			Stream<Map<String, String>> filter = list.stream().filter(map -> org.apache.commons.lang3.StringUtils.isNotBlank(map.get("workNo")));
			list = filter.collect(Collectors.toList());
		}
		return list;
	}
}
