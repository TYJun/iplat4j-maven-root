/**
 *@Name ServiceDHRM01.java
 *@Description 宿舍入住申请
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.sh.service;

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

import java.util.*;

/**
 * 宿舍入住审核页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMSH01.java
 * @ClassName: ServiceDMSH01
 * @Package：com.baosight.wilp.dm.sh.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMSH01 extends ServiceBase{

	/**
	 * 一、宿舍入住审核查询页面加载.
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
	 * 此方法用于宿舍入住审核页面列表数据查询
	 *
	 * 逻辑处理：
	 * 1、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分
	 * 2、调用本地服务DMRZ01.queryRZInfoList()方法进行列表数据查询
	 *
	 * @Title: query
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
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
		 * 1、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
		 */
		String role = (String) userInfo.get("role");
		// 获取前端的人员大类值（manNature）
		String manNature = inInfo.getString("manNature");
		if(role.contains("ADMIN")){
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "职工";
			}
			role = "DMADMIN";
		}else if(role.contains("DMSPR_XSSS")){
			// 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
			manNature = "学生";
			role = "DMSPR_XSSS";
		}else if(role.contains("DMSPR_ZGSS")){
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "职工";
			}
			role = "DMSPR_ZGSS";
		} else {
			if (manNature == null){
				manNature = "职工";
			}
			role = "DMZSR";
		}
		inInfo.set("manNature", manNature);
		inInfo.set("role",role);

		/**
		 * 2、调用本地服务DMRZ01.queryRZInfoList()方法进行列表数据查询.
		 */
		inInfo.set("statusCode", "00");
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "queryRZInfoList");
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 调用本地服务DMRZ01.updateStatusCode修改状态代码
	 * 1、获取当前用户信息.
	 * 2、调用本地服务DMRZ01.updateStatusCode进行状态的更新,更新人员入住信息表的状态.
	 * 3、调用本地服务DMRZ01.insertLCInfo将审核入住申请流程插入宿舍操作流程历史表中.
	 *
	 * @Title: updateStatusCode
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo updateStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		// 赋值操作人
		inInfo.set("operator", loginName);
		/*
		 * 2、调用本地服务DMRZ01.updateStatusCode进行状态的更新,更新人员入住信息表的状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "updateStatusCode");
		EiInfo outInfo =XLocalManager.call(inInfo);
		/*
		 * 3、调用本地服务DMRZ01.insertLCInfo将审核入住申请流程插入宿舍操作流程历史表中.
		 */
		// 将申请流程插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "insertLCInfo");
		outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}


	/**
	 * 调用本地服务DMRZ01.batchUpdateStatusCode修改状态代码
	 * 1、获取当前用户信息.
	 * 2、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态,更新人员入住信息表的状态.
	 * 3、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
	 * 4、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
	 * 5、企业微信通知退宿申请结果
	 * @Title: batchUpdateStatusCode
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo batchUpdateStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		// 赋值操作人
		inInfo.set("operator", loginName);
		/*
		 * 2、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态,更新人员入住信息表的状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchUpdateStatusCode");
		EiInfo outInfo =XLocalManager.call(inInfo);
		/*
		 * 3、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchUpdateLCStatusCode");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
		 */
		// 将审核申请流程插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
		outInfo = XLocalManager.call(inInfo);

		inInfo.set(EiConstant.serviceName, "DMSH01");
		inInfo.set(EiConstant.methodName, "selectStudent");
		outInfo = XLocalManager.call(inInfo);

		/*
		 * 5、企业微信通知退宿申请结果
		 */
		//获取app编码
		String appCode = "AP00002";
		List<String> workNoList =new ArrayList<>();
		List<String> paramList = new ArrayList<>();

		//获取退宿人员工号
		String manNo = inInfo.getString("manNoList");
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作
		if (StringUtils.isNotBlank(manNo) && manNo.split(",").length > 1){
			// 以一个数组去存分割后的字符串。
			String[] manNoArray = manNo.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manNoArray.length; i++) {
				// 接收拆出来的manNo
				workNoList.add(manNoArray[i]);

			}// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(manNo)){
			workNoList.add(manNo);
		}


		//发送的消息
		String smsTemp = "您退宿申请已审批拒绝，请您及时去系统上重新发起退宿申请";
		paramList.add(smsTemp);

		//发送企业微信
		BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);


		return outInfo;
	}

	/**
	 * 调用本地服务DMRZ01.batchUpdateStatusCode修改状态代码
	 * 1、获取当前用户信息.
	 * 2、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态,更新人员入住信息表的状态.
	 * 3、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
	 * 4、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
	 *
	 * @Title: batchUpdateStatusCode
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo outUpdateStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		// 赋值操作人
		inInfo.set("operator", loginName);
		/*
		 * 2、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态,更新人员入住信息表的状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "outUpdateStatusCode");
		EiInfo outInfo =XLocalManager.call(inInfo);
		/*
		 * 3、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchUpdateLCStatusCode");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
		 */
		// 将审核申请流程插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
		outInfo = XLocalManager.call(inInfo);

		return outInfo;
	}

	/**
	 * 此方法用于判断自动分配选房的住宿人是否为全日制学生
	 *
	 * 逻辑处理：
	 * 1.获取参数的值并处理
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMSH01.selectStudent 查询当前申请中包含的全日制学生，提取出给前端做提醒.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: selectStudent
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo selectStudent(EiInfo inInfo) {
		// 人员属性
		List studentNatureList = new ArrayList();
		studentNatureList = Arrays.asList("进修医生", "进修护士", "实习医生", "实习护士", "政策类研究生", "规范会培训生");

		/**
		 *  1.获取参数的值并处理
		 */
		String manId = inInfo.getString("manIdList");
		// 先实例化 manIdList。
		List<Map<String, String>> manIdList = new LinkedList<>();
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
		if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdArray = manId.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdArray.length; i++) {
				// 实例化一个Map<String,String>类型的manIdInfo，用来接收拆出来的manId。
				Map<String, String> manIdInfo = new HashMap<>();
				manIdInfo.put("manId", manIdArray[i]);
				// 将接收到数据的map添加到manIdInfo列表中。
				manIdList.add(manIdInfo);
			}
			// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(manId)){
			// 实例化一个Map<String,String>类型的idInfo，用来接收单独的manId。
			Map<String, String> manIdInfo = new HashMap<>();
			manIdInfo.put("manId", manId);
			// 将接收到数据的map添加到manIdInfo列表中。
			manIdList.add(manIdInfo);
		}
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("manIdList", manIdList);
		map.put("studentNatureList", studentNatureList);
		/*
		 * 3、以map作为参数执行 DMSH01.selectStudent 查询当前申请中包含的全日制学生，提取出给前端做提醒.
		 */
		EiInfo outInfo = new EiInfo();
		List<Map<String, Object>> list = dao.query("DMSH01.selectStudent", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		StringBuffer msg = new StringBuffer();
		if (CollectionUtils.isEmpty(list)) {
			return outInfo;
		}else{
			for (int i = 0; i < list.size(); i++) {
				String manNo = (String) list.get(i).get("manNo");
				String manName = (String) list.get(i).get("manName");
				msg.append( manNo + ":" + manName + "\n");
			}
		}
		msg.append("上述人员为非全日制学生，不可自动分配选房！");
		outInfo.set("manId", manId);
		outInfo.setMsg(String.valueOf(msg));
		return outInfo;
	}


}

