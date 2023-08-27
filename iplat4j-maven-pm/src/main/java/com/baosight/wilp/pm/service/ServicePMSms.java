package com.baosight.wilp.pm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.RecevierEnum;
import com.baosight.wilp.pm.domain.Tpm01;
import com.baosight.wilp.pm.domain.TpmCalculate;
import com.baosight.wilp.pm.domain.TpmSmsConfig;
import com.baosight.wilp.pm.utils.DataGroupUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工程项目：发送短信（将短信插入短信表）
 * <p>1.发送短信（将短信插入短信表） sendMessage
 * <p>2.获取短信接收人 getSmsRecvier
 * <p>3.查询短信接收人 getList
 * <p>4.获取短信内容 getSmsContent
 * <p>5.根据工号获取用户名 getNames
 * <p>5.发送短信 smsSemd
 * <p>6.发送超期短信 sendExpireMessage
 *
 * @Title: ServicePMSms.java
 * @ClassName: ServicePMSms
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午5:21:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePMSms extends ServiceBase {

	/**
	 * 发送短信（将短信插入短信表）
	 *
	 * @throws
	 * @Title: sendMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param eiInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo sendMessage(EiInfo eiInfo) {
		//获取参数，
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("configType", eiInfo.get("configType").toString());
		//获取短信配置
		List<TpmSmsConfig> configList = dao.query("PM09.query", pMap);
		//没有短信配置，无需发送短信
		if (configList == null || configList.size() == 0) {
			return eiInfo;
		}
		//有短信配置，获取短信接收人
		TpmSmsConfig config = configList.get(0);
		//不发送短信或者不存在短信接收人，无需发送短信
		if (!"1".equals(config.getIsRuning()) || StringUtils.isBlank(config.getSmsRecvCode())) {
			return eiInfo;
		}
		//有短信配置，存在短信接收人
		List<Map<String, String>> recvList = getSmsRecvier(config.getSmsRecvCode(), eiInfo.get("projectId").toString());
		if (recvList == null || recvList.size() == 0) {//短信接收人为空、不发送短信
			return eiInfo;
		}
		//获取项目
		pMap.put("id", eiInfo.get("projectId").toString());
		List<Tpm01> projectList = dao.query("PM01.query", pMap);
		//获取归档信息
		List<TpmCalculate> calList = dao.query("PM09.queryCalculateListByPrid", projectList.get(0).getProjectNo());
		TpmCalculate calculate = calList != null && calList.size() > 0 ? calList.get(0) : null;
		//处理短信模板
		String smsContent = getSmsContent(config.getSmsTemp(), projectList.get(0), calculate, recvList);
		List<String> paramList = new ArrayList<>();
		paramList.add(smsContent);
		List<String> workNoList = new ArrayList<>();
		for (Map<String, String> workNo : recvList) {
			workNoList.add(workNo.get("workNo"));
		}
		workNoList = workNoList.stream().distinct().collect(Collectors.toList());
		//发送短信
		//smsSemd(recvList, smsContent);
		BaseDockingUtils.sendMsg(workNoList, paramList, "TP00006");
		return eiInfo;
	}

	/**
	 * 获取短信接收人
	 *
	 * @param smsRecvCode
	 * @param id
	 * @return
	 * @Title: getSmsRecvier
	 * @return: List<Map < String, String>>
	 */
	private List<Map<String, String>> getSmsRecvier(String smsRecvCode, String id) {
		List<Map<String, String>> list = new ArrayList<>();
		String[] split = smsRecvCode.split(",");
		for (String code : split) {
			list.addAll(getList(RecevierEnum.valueOf(code).getStatement(), id));
		}
		list = list.stream().distinct().collect(Collectors.toList());
		return list;
	}

	/**
	 * 查询短信接收人
	 *
	 * @param statement
	 * @param id
	 * @return
	 * @Title: getList
	 * @return: List<Map < String, String>>
	 */
	private List<Map<String, String>> getList(String statement, String id) {
		List<Map<String, String>> list = new ArrayList<>();
		list = dao.query("PM09." + statement, id);
		if (list.size() > 0) {
			Stream<Map<String, String>> filter = list.stream().filter(map -> StringUtils.isNotBlank(map.get("workNo")));
			list = filter.collect(Collectors.toList());
		}
		return list;
	}

	/**
	 * 获取短信内容
	 *
	 * @param smsTemp
	 * @param tpm01
	 * @param calculate
	 * @return
	 * @Title: getSmsContent
	 * @return: String
	 */
	private String getSmsContent(String smsTemp, Tpm01 tpm01, TpmCalculate calculate, List<Map<String, String>> recvList) {
		if (smsTemp.contains("$project_name$")) {
			smsTemp = smsTemp.replace("$project_name$", tpm01.getProjectName());
		}
		if (smsTemp.contains("$project_no$")) {
			smsTemp = smsTemp.replace("$project_no$", tpm01.getProjectNo());
		}
		if (smsTemp.contains("$project_contor$")) {
			smsTemp = smsTemp.replace("$project_contor$", tpm01.getProjectPrincipal());
		}
		//项目联络人
		if (smsTemp.contains("$project_cons$")) {
			smsTemp = smsTemp.replace("$project_cons$", tpm01.getProjectObjectConsName());
		}
		//执行人
		if (smsTemp.contains("$project_exec$")) {
			List<String> execList = new ArrayList<>();
			for (int i = 0; i < recvList.size();i++){
				execList.add(recvList.get(i).get("name"));
			}
			execList = execList.stream().distinct().collect(Collectors.toList());
			smsTemp = smsTemp.replace("$project_exec$", execList.toString());
		}
		//督办人
		if (smsTemp.contains("$project_via$")) {
			List<String> viaList = new ArrayList<>();
			for (int i = 0; i < recvList.size();i++){
				viaList.add(recvList.get(i).get("name"));
			}
			viaList = viaList.stream().distinct().collect(Collectors.toList());
			smsTemp = smsTemp.replace("$project_via$", viaList.toString());
		}
		if (calculate != null) {
			if (smsTemp.contains("$submit_maker$")) {
				smsTemp = smsTemp.replace("$submit_maker$", calculate.getSubmitter());
			}
			if (smsTemp.contains("$accept_maker$")) {
				smsTemp = smsTemp.replace("$accept_maker$", calculate.getRecipient());
			}
			if (smsTemp.contains("$ZLsubmit_maker$")) {
				smsTemp = smsTemp.replace("$ZLsubmit_maker$", calculate.getDataSubmitter());
			}
			if (smsTemp.contains("$aduit_maker$")) {
				smsTemp = smsTemp.replace("$aduit_maker$", calculate.getDataRecipient());
			}
			if (smsTemp.contains("$report_submit_maker$")) {
				smsTemp = smsTemp.replace("$report_submit_maker$", calculate.getDataSubmitterCodeBackName());
			}
			if (smsTemp.contains("$report_accept_maker$")) {
				smsTemp = smsTemp.replace("$report_accept_maker$", calculate.getDataRecipientCodeBackName());
			}
		}
		return smsTemp;
	}

	/**
	 * 根据工号获取用户名
	 *
	 * @param workNos
	 * @return
	 * @Title: getNames
	 * @return: CharSequence
	 */
	private CharSequence getNames(String workNos) {
		List<String> list = dao.query("PM09.queryNameByWorkNos", workNos);
		return list == null || list.size() == 0 ? "" : list.get(0);
	}

	/**
	 * 发送短信
	 *
	 * @param recvList
	 * @param smsContent
	 * @Title: smsSemd
	 * @return: void
	 */
	private void smsSemd(List<Map<String, String>> recvList, String smsContent) {
		for (Map<String, String> map : recvList) {
			//PushMsgUtil.pushMsg(map.get("phone"), smsContent, "PR");
		}
	}

	/**
	 * 发送超期短信
	 *
	 * @throws
	 * @Title: sendExpireMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return: void
	 */
	public void sendExpireMessage() {
		//构建参数，
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("configType", "overDue");
		//获取短信配置
		List<TpmSmsConfig> configList = dao.query("PM09.query", pMap);
		//没有短信配置，无需发送短信
		if (configList == null || configList.size() == 0) {
			return;
		}
		//有短信配置，获取短信接收人
		TpmSmsConfig config = configList.get(0);
		//不发送短信或者不存在短信接收人，无需发送短信
		if (!"1".equals(config.getIsRuning()) || StringUtils.isBlank(config.getSmsRecvCode())) {
			return;
		}
		//获取超期的工程项目
		pMap.put("expireDay", config.getLateDays());
		pMap.put("dataGroupCode", DataGroupUtils.getDataGroup());
		List<Tpm01> list = dao.query("PM09.queryExpireProject", pMap);
		//遍历、发送短信
		for (Tpm01 tpm01 : list) {
			String smsContent = getSmsContent(config.getSmsTemp(), tpm01, null, null);
			List<Map<String, String>> recvList = getSmsRecvier(config.getSmsRecvCode(), tpm01.getId());
			if (recvList == null || recvList.size() == 0) {
				continue;
			}
			smsSemd(recvList, smsContent);
		}
	}

}
