/**
 *@Name ServiceDHRM01.java
 *@Description 宿舍入住申请
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.dx.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMBaseDockingUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍批量短信发送页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMDX01.java
 * @ClassName: ServiceDMDX01
 * @Package：com.baosight.wilp.dm.dx.service
 * @author: fangzekai
 * @date: 2022年03月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMDX01 extends ServiceBase{
	@Autowired
	private AsyncServiceMsg asyncServiceMsg;

	/**
	 * 一、宿舍批量短信发送页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询入住信息
	 *   逻辑处理
	 *   1.将要查询的参数组成数组并调用工具类转换参数
	 *   2.将构建好的map传入DMDX01.queryRYInfoList进行查询并分页，同时查询列表数量
	 *   3.判断是否存在，存在则构建返回对象 否则设置返回信息和状态
	 *
	 * @Title: query
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> userInfo = DMUtils.getUserInfo(null);
		if(userInfo == null || userInfo.isEmpty()){
			inInfo.setMsg("您的账号存在问题，请联系管理员");
			return inInfo;
		}
		/*
		 * 1、将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"manNo", "manName","deptName", "employmentNature", "statusCode"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		/*
		 * 2、将构建好的map传入DMDX01.queryRYInfoList进行查询并分页，同时查询列表数量.
		 *    判断列表对象是否存在，存在则构建返回对象.
		 */
		//查询
		List<Map<String, Object>> list = dao.query("DMDX01.queryRYInfoList",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("DMDX01.queryRYInfoListCount",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			inInfo.setMsg("没有查询到数据。");
			return inInfo;
		}
	}

	/**
	 * 此方法用于批量发送短信
	 *     1.对参数进行处理
	 *     2.将构建好的map传入DMDX01.querySelectPeople进行查询所选中的人的相关信息
	 *     3.判断是否取得数据
	 *     4.获取批量发送短信batchSmsTemp的模板并发送短信 若发送失败返回提示
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo batchSendMsg(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String, Object> map = new HashMap<>();
		Boolean totalFlag = true;
		// 先实例化 manIdList。
		List<Map<String, String>> manIdList = new LinkedList<>();
		// 获取参数的值。
		String getmanIdList = (String) inInfo.get("manIdList");
		// 对获取的值进行判空和以逗号隔开做长度判断，当为lenght>1是则该值为多个数组组成。
		if (StringUtils.isNotBlank(getmanIdList) && getmanIdList.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdTotal = getmanIdList.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdTotal.length; i++) {
				// 实例化一个Map<String,String>类型的item，用来接收拆出来的manId。
				Map<String, String> item = new HashMap<>();
				item.put("manId", manIdTotal[i]);
				// 将接收到数据的map添加到manIdList列表中。
				manIdList.add(item);
			}
			// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(getmanIdList)){
			// 实例化一个Map<String,String>类型的item，用来接收拆出来的manId。
			Map<String, String> item = new HashMap<>();
			item.put("manId", getmanIdList);
			// 将接收到数据的map添加到manIdList列表中。
			manIdList.add(item);
		}
		// 将存有manId的 manIdList列表再用一个map接收。
		map.put("manIdList",manIdList);
		/*
		 * 2.将构建好的map传入DMDX01.querySelectPeople进行查询所选中的人的相关信息
		 *    判断列表对象是否存在，存在则构建返回对象。
		 */
		List<Map<String, String>> list = dao.query("DMDX01.querySelectPeople",map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		/*
		 * 查得数据后对查到的数据处理到短信的模板中去。↓
		 */
		// 获取批量发送短信batchSmsTemp的模板
		String configType = "batchSmsTemp";
		List<Map<String, String>> querySmsTemplist = dao.query("DMDX01.queryBatchSMSTemp",configType);
		for (int i = 0; i < list.size(); i++) {
			List<String> workNoList = new ArrayList<>();
			List<String> paramList = new ArrayList<>();
			String smsTemp = querySmsTemplist.get(0).get("smsTemp");     /* 短信模板 */
			workNoList.add(list.get(i).get("manNo"));
			//替换(判断短信类容模板中是否包含指定的字段,包含，将维修任务对象中对应的属性值替)
			if(smsTemp.contains("$name$")){//姓名
				smsTemp = smsTemp.replace("$name$", list.get(i).get("manName"));
			}
			if(smsTemp.contains("$nameNo$")){ //工号
				smsTemp = smsTemp.replace("$nameNo$", list.get(i).get("manNo"));
			}
			if(smsTemp.contains("$Tel$")){ //电话
				smsTemp = smsTemp.replace("$Tel$", list.get(i).get("phone"));
			}
			if(smsTemp.contains("$Dor_name$")){ //宿舍总称
				smsTemp = smsTemp.replace("$Dor_name$", list.get(i).get("roomName"));
			}
			paramList.add(smsTemp);
//			outInfo = asyncServiceMsg.sendMessage(workNoList, paramList, "TP00001");
			Boolean flag = BaseDockingUtils.sendMsg(workNoList, paramList, "TP00001");
			if (!flag){
				totalFlag = false;
			}
		}
		if (totalFlag){
			outInfo.setMsg("发送短信成功！！！");
			outInfo.setMsgKey("200");
		}else {
			outInfo.setMsg("发送短信失败，请重试！");
			outInfo.setMsgKey("201");
		}
		return outInfo;
	}

}

