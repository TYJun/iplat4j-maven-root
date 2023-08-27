package com.baosight.wilp.sq.ap.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.ap.domain.SqManage;
import com.baosight.wilp.sq.common.TyepCode;
import com.baosight.wilp.utils.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;


/**
 * app逻辑接口
 * <p>查询该账号下是否有问卷  getAssessManage
 * <p>查询项目  getAssessManageProject
 * <p>获取Radio  getAssessRadio
 * <p>保存明细  saveAssessProjectInstance
 * <p>查看明细  getAssessManageProjectInstance
 * <p>查询目标打分明细  getAssessManageProjectInstance_2
 * <p>小代码配置 sqType
 * <p>保存打分记录 saveSqManageProject
 * <p>查询标准信息 getSqProjectParam
 * <p>查询打分项目 getSqManageProjectInstance
 * <p>查询项目总分 getQueryPoints
 * <p>查询标准信息 getSqProjectInstance
 * <p>查询问卷调查 getAssessManage
 * <p>此单据与这个人是否有提交记录 getAssessManageProject3
 * <p>查询分组信息 getCountGroup
 * <p>查询项目 getAssessManageProjects
 * <p>查询项目打分信息 getQueryInstance
 * <p>查询小代码配置项 TyepCode
 *
 * @Title: ServiceSQAP01.java
 * @ClassName: ServiceSQAP01
 * @Package：com.baosight.wilp.sq.ap.service
 * @author: zhangjiaqian
 * @date: 2021年7月30日 下午4:14:48
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQAP01 extends ServiceBase {
	// 打印日志
	protected static final Logger logger = LoggerFactory.getLogger(ServiceSQAP01.class);

	/**
	 * 获取用户工号
	 *
	 * @param inInfo
	 * @return
	 * @Title: getUserWorkNo
	 * @return: String
	 */
	public String getUserWorkNo(EiInfo inInfo) {
		return UserSession.getUser().getUsername();
	}

	/**
	 * 进入满意度调查列表
	 * 查询当前登录账号下是否有符合要求的问卷
	 *
	 * @param inInfo
	 * @return
	 * @Title: getAssessManage
	 * @return: EiInfo
	 */
	public EiInfo getAssessManage(EiInfo inInfo) {
		/**
		 * 一，获取参数
		 */
		// 获取用户id
		String userId = inInfo.getString("LoginUser");
		if (StringUtils.isBlank(userId)) {
			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			userId = (String) getRole.get("workNo");
		}
		logger.info("用户workNo获取：" + userId);
		try {
			/**
			 * 二.参数校验
			 */
			if (StringUtils.isBlank(userId)) {
				inInfo.setMsgKey("199");
				inInfo.setMsg("缺少loginUser参数");
				return inInfo;
			}
			// 获取用户名
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(userId);
			// 获取院区编码
			String datagroupCode = DatagroupUtil.getDatagroupCode();
			/**
			 * 三.创建参数集合
			 */
			/**
			 * 四.逻辑校验
			 * 1.获取调查问卷列表
			 * 2.判断当前用户是否拥有问卷
			 */
			// 获取问卷调查
			List<Map<String, Object>> assessManageList = getAssessManage(datagroupCode);
			List manageList = new ArrayList();
			// 获取的调查问卷不为空
			if (CollectionUtils.isNotEmpty(assessManageList)) {
				// 对问卷列表进行遍历
				for (Map<String, Object> assessManageMap : assessManageList) {
					assessManageMap.put("workNo", staffByUserId.get("workNo"));
					assessManageMap.put("workName", staffByUserId.get("name"));
					// 10-执行状态，99-完成状态
					// 如果问卷在执行和已完结展示在问卷列表中
					if ("10".equals(assessManageMap.get("status_code")) || "99".equals(assessManageMap.get("status_code"))) {
						//此单据与这个人是否有提交记录
						List<Map<String, Object>> assessManageScoreList = getAssessManageScoreByBatchNoAndWorkMsg(assessManageMap);
						// 如果不为空则存在提交记录
						if (CollectionUtils.isNotEmpty(assessManageScoreList)) {
							assessManageMap.put("status_submit", "1");
						}
						// 0-全院范围，1-指定范围
						// 如果属于全院范围，所有人员都能看到，指定范围只有指定人员组的成员才能看到
						if ("1".equals(assessManageMap.get("assess_range"))) {
							// 查询指定范围人员群组
							if (getCountGroup(assessManageMap)) {
								manageList.add(assessManageMap);
							}
						} else if ("0".equals(assessManageMap.get("assess_range"))) {
							manageList.add(assessManageMap);
						}
					}
				}
				//封装返回信息
				inInfo.setMsgKey("200");
				inInfo.setMsg("成功");
				inInfo.set("param", manageList);
			}
		}catch (Exception e){
			e.printStackTrace();
			inInfo.setMsgKey("199");
			inInfo.setMsg("失败");
		}
		//返回
		return inInfo;
	}


	/**
	 * App从调查列表进入问卷调查方法
	 *
	 * @param inInfo
	 * @return EiInfo
	 * @author zhaowei
	 * @date 2022/4/1 20:53
	 */
//	public EiInfo getAssessManageProject(EiInfo inInfo) {
//		/**
//		 * 一.获取前端参数
//		 */
//		String billNo = inInfo.getString("billNo");
//		String workNo = inInfo.getString("workNo");
//		//String workNo = "admin";
//		if (StringUtils.isBlank(workNo)) {
//			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
//			workNo = (String) getRole.get("workNo");
//		}
//		/**
//		 * 二.参数校验
//		 */
//		if (StringUtils.isBlank(billNo)) {
//			//封装返回信息
//			inInfo.setMsgKey("199");
//			inInfo.setMsg("缺少billNo参数");
//			return inInfo;
//		}else if (StringUtils.isBlank(workNo)){
//			inInfo.setMsgKey("199");
//			inInfo.setMsg("缺少workNo参数");
//			return inInfo;
//		}
//		/**
//		 * 三.参数封装
//		 */
//		Map<String, Object> mapProject = new HashMap<String, Object>();
//		mapProject.put("batchNo", billNo);
//		mapProject.put("workNo", workNo);
//		// 查询该问卷的意见也建议
//		List<Map<String, Object>> assessManageAdvice = dao.query("SQAP01.queryAssessManageAdvice", mapProject);
//		if (CollectionUtils.isNotEmpty(assessManageAdvice)) {
//			inInfo.set("advice", assessManageAdvice.get(0).get("advice"));
//		} else {
//			inInfo.set("advice", "");
//		}
//		/**
//		 * 四.逻辑处理
//		 * 1.获取进入当前问卷存在的项目数
//		 */
//		List<Map<String, Object>> resultList;
//		// 查询当前问卷下存在的项目
//		List<Map<String, Object>> assessManageProjectsList = getAssessManageProjects(mapProject);
//		// 判断问卷下是否存在题目
//		if (CollectionUtils.isNotEmpty(assessManageProjectsList)) {
//			// 判断问卷中的题目是否自定义了选项
//			boolean isCustom = TyepCode();
//			// 对项目进行遍历回显
//			for (Map<String, Object> assessManageProjectsMap : assessManageProjectsList) {
//				assessManageProjectsMap.put("workNo", workNo);
//				// 对项目中的题目遍历回显
//				List<Map<String, Object>> instanceList = getQueryInstance(assessManageProjectsMap);
//				// 将题目绑定到项目下
//				assessManageProjectsMap.put("instance", instanceList);
//				// 对选项进行遍历
//				for (Map<String, Object> instanceMap : instanceList) {
//					// 是否自定义题目选项
//					instanceMap.put("isCustom", isCustom);
//					List<Map<String, Object>> itemList = getAssessRadio(instanceMap);
//					// 将选项绑定到题目下
//					instanceMap.put("instanceItem", itemList);
//					instanceMap.put("text", instanceMap.get("selectValue"));
//				}
//			}
//		}
//		//封装返回信息
//		inInfo.setMsgKey("200");
//		inInfo.setMsg("成功");
//		inInfo.set("param", assessManageProjectsList);
//		//返回
//		return inInfo;
//	}

	/**
	 * App从调查列表进入问卷调查方法
	 *
	 * @param inInfo
	 * @return EiInfo
	 * @author zhaowei
	 * @date 2022/4/1 20:53
	 */
	public EiInfo getAssessManageProject(EiInfo inInfo) {
		/**
		 * 一.获取前端参数
		 */
		String billNo = inInfo.getString("billNo");
		String workNo = inInfo.getString("workNo");
		if (StringUtils.isBlank(workNo)) {
			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			workNo = (String) getRole.get("workNo");
		}
		/**
		 * 二.参数校验
		 */
		if (StringUtils.isBlank(billNo)) {
			//封装返回信息
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少billNo参数");
			return inInfo;
		}else if (StringUtils.isBlank(workNo)){
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少workNo参数");
			return inInfo;
		}
		/**
		 * 三.参数封装
		 */
		Map<String, Object> mapProject = new HashMap<String, Object>();
		mapProject.put("batchNo", billNo);
		mapProject.put("workNo", workNo);
		// 查询该问卷的意见也建议
		List<Map<String, Object>> assessManageAdvice = dao.query("SQAP01.queryAssessManageAdvice", mapProject);
		if (CollectionUtils.isNotEmpty(assessManageAdvice)) {
			inInfo.set("advice", assessManageAdvice.get(0).get("advice"));
		} else {
			inInfo.set("advice", "");
		}
		/**
		 * 四.逻辑处理
		 * 1.获取进入当前问卷存在的项目数
		 */
//		List<List<Map<String, Object>>> resultList = new ArrayList<List<Map<String, Object>>>();
//		List<Map<String, Object>> resultList = new ArrayList<>();

		//查询父问卷下所有子问卷
		List<Map<String, Object>> childrenManageList = dao.query("SQAP01.getChildrenManage", mapProject);


		for (Map<String, Object> childrenManageMap : childrenManageList) {
			String batchNo = childrenManageMap.get("batchNo").toString();
			mapProject.put("batchNo", batchNo);
			// 查询当前问卷下存在的项目
			List<Map<String, Object>> assessManageProjectsList = getAssessManageProjects(mapProject);
			// 判断问卷下是否存在题目
			if (CollectionUtils.isNotEmpty(assessManageProjectsList)) {
				// 判断问卷中的题目是否自定义了选项
				boolean isCustom = TyepCode();
				// 对项目进行遍历回显
				for (Map<String, Object> assessManageProjectsMap : assessManageProjectsList) {
					assessManageProjectsMap.put("workNo", workNo);
					// 对项目中的题目遍历回显
					List<Map<String, Object>> instanceList = getQueryInstance(assessManageProjectsMap);
					// 将题目绑定到项目下
					assessManageProjectsMap.put("instance", instanceList);
					// 对选项进行遍历
					for (Map<String, Object> instanceMap : instanceList) {
						// 是否自定义题目选项
						instanceMap.put("isCustom", isCustom);
						List<Map<String, Object>> itemList = getAssessRadio(instanceMap);
						// 将选项绑定到题目下
						instanceMap.put("instanceItem", itemList);
						instanceMap.put("text", instanceMap.get("selectValue"));
					}
				}
			}
			childrenManageMap.put("ProjectItem",assessManageProjectsList);
//			resultList.add(assessManageProjectsList);
		}


		//封装返回信息
		inInfo.setMsgKey("200");
		inInfo.setMsg("成功");
		inInfo.set("param", childrenManageList);
		//返回
		return inInfo;
	}


	/**
	 * 给每个题目绑定选项
	 * 获取Radio
	 *
	 * @return
	 * @Title: getAssessRadio
	 * @return: List
	 */
	public List<Map<String, Object>> getAssessRadio(Map<String, Object> map) {
		// 创建集合
		List<Map<String, Object>> itemList = new ArrayList<>();
		// 自定义选项
		if ((Boolean) map.get("isCustom")) {
			itemList = dao.query("SQAP01.queryItemByInstanceId", map);
		} else {
			// 默认题型匹配默认选项
			if ("0".equals(map.get("pointType"))) {
//				itemList = sqType();
			} else {
				Map mapY = new HashMap();
				mapY.put("label", "是");
				mapY.put("value", Integer.valueOf((String) map.get("point")));

				Map mapN = new HashMap();
				mapN.put("label", "否");
				mapN.put("value", 0);
				//封装信息
				itemList.add(mapY);
				itemList.add(mapN);
			}
		}
		//返回
		return itemList;
	}


	/**
	 * 问卷已提交，查看答卷情况
	 *
	 * @param inInfo
	 * @Title: getAssessManageProjectInstance
	 * @return: void
	 */
	public EiInfo getAssessManageProjectInstance(EiInfo inInfo) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		//主题单据
		String billNo = (String) inInfo.get("billNo");
		String workNo = (String) inInfo.get("workNo");
		// 参数校验
		if (StringUtils.isBlank(workNo)) {
			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			workNo = (String) getRole.get("workNo");
		}
		if (StringUtils.isBlank(billNo)) {
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少参数billNo");
			return inInfo;
		} else if (StringUtils.isBlank(workNo)){
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少参数workNo");
			return inInfo;
		}
		//获取用户名
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
		mapParam.put("batchNo", billNo);
		mapParam.put("workNo", workNo);
		// 查询项目
		List<Map<String, Object>> assessManageProjectsList = getAssessManageProjects(mapParam);
		// 算出每个项目的总分和总得分 point是总分 score是得分
		BigDecimal sumPoint = new BigDecimal(0);
		BigDecimal sumScore = new BigDecimal(0);
		// 判断集合是否为空
		if (CollectionUtils.isNotEmpty(assessManageProjectsList)) {
			for (Map<String, Object> assessManageProjectsMap : assessManageProjectsList) {
				// 获取该问卷下的题目
				List<Map<String, Object>> queryManageInstanceList = dao.query("SQAP01.queryManageInstanceList", assessManageProjectsMap);
				sumPoint = new BigDecimal(0);
				sumScore = new BigDecimal(0);
				// 遍历该项目下的题目
				for (Map<String, Object> queryManageInstanceMap : queryManageInstanceList) {

					sumPoint = sumPoint.add(new BigDecimal(String.valueOf(queryManageInstanceMap.get("point"))));
					sumScore = sumScore.add(new BigDecimal(String.valueOf(queryManageInstanceMap.get("score"))));
				}
				assessManageProjectsMap.put("instance", queryManageInstanceList);
				assessManageProjectsMap.put("sumPoint", sumPoint);
				assessManageProjectsMap.put("sumScore", sumScore);
			}
			// 查询问卷意见
			List<Map<String, Object>> maps = dao.query("SQAP01.queryAssessManageAdvice", mapParam);
			if (CollectionUtils.isNotEmpty(maps)) {
				inInfo.set("advice", maps.get(0).get("advice"));
			} else {
				inInfo.set("advice", "");
			}
			inInfo.setMsgKey("200");
			inInfo.setMsg("成功");
			inInfo.set("param", assessManageProjectsList);

		}
		return inInfo;
	}

	/**
	 * 查询目标打分明细
	 *
	 * @param inInfo
	 * @Title: getAssessManageProjectInstance_2
	 * @return: void
	 */
	public EiInfo getAssessManageProjectInstance_2(EiInfo inInfo) {
		// 构建map
		Map<String, Object> mapInstance = new HashMap<>();
		//项目编码
		String projectId = inInfo.getString("projectId");
		//主题单据
		String billNo = inInfo.getString("billNo");
		//工号
		String workNo = inInfo.getString("workNo");
		// 参数校验
		if (StringUtils.isBlank(workNo)) {
			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			workNo = (String) getRole.get("workNo");
		}
		if (StringUtils.isBlank(billNo)) {
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少参数billNo");
			return inInfo;
		} else if (StringUtils.isBlank(workNo)){
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少参数workNo");
			return inInfo;
		}
		//获取用户名
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
		mapInstance.put("projectId", projectId);
		mapInstance.put("batchNo", billNo);
		mapInstance.put("workNo", workNo);
		mapInstance.put("creator", staffByUserId.get("name"));
		List<Map<String, Object>> instacneList = getSqManageProjectInstance(mapInstance);
		List<Map<String, Object>> maps = dao.query("SQAP01.querySQManageAdvice", mapInstance);
		if (CollectionUtils.isNotEmpty(maps)) {
			inInfo.set("advice", maps.get(0).get("advice"));
		} else {
			inInfo.set("advice", "");
		}
		if (CollectionUtils.isNotEmpty(instacneList)) {
			inInfo.setMsgKey("200");
			inInfo.setMsg("成功");
			inInfo.set("param", instacneList);
		}
		return inInfo;
	}


	/**
	 * 小代码配置
	 *
	 * @return
	 * @Title: sqType
	 * @return: EiInfo
	 */
	public List<Map<String, String>> sqType() {
		List<Map<String, String>> param = new ArrayList<Map<String, String>>();
		try {
			param = TyepCode.dealUseDay("WILP.sq.Scoring");
		} catch (Exception e) {
			return param;
		}
		return param;
	}


	/**
	 * 保存打分记录
	 *
	 * @param sqManage
	 * @return
	 * @Title: saveAssessManageProject
	 * @return: boolean
	 */
	public boolean saveSqManageProject(SqManage sqManage) {
		try {
			dao.insert("SQAP01.saveAssessManageProject", sqManage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 查询标准信息
	 *
	 * @return
	 * @Title: getSqProjectInstance
	 * @return: List
	 */
	public List getSqProjectParam(Map map) {
		List list = dao.query("SQAP01.queryProjectParam", map);
		return list;
	}


	/**
	 * 查询项目打分
	 *
	 * @param map
	 * @return
	 * @Title: getSqProjectParam
	 * @return: List
	 */
	public List<Map<String, Object>> getSqManageProjectInstance(Map map) {
		List<Map<String, Object>> list = dao.query("SQAP01.getSqManageProjectInstance", map);
		return list;
	}


	/**
	 * 获取调查问卷列表
	 *
	 * @return
	 * @Title: getAssessManage
	 * @return: List
	 */
	public List<Map<String, Object>> getAssessManage(String datagroupCode) {
		List<Map<String, Object>> assessManageList = dao.query("SQAP01.getAssessManage", datagroupCode);
		return assessManageList;
	}


	/**
	 * 此单据与这个人是否有提交记录
	 *
	 * @param map
	 * @return
	 * @Title: getAssessManageProject3
	 * @return: List
	 */
	public List<Map<String, Object>> getAssessManageScoreByBatchNoAndWorkMsg(Map map) {
		List<Map<String, Object>> assessManageScoreList = dao.query("SQAP01.getAssessManageScoreByBatchNoAndWorkMsg", map);
		return assessManageScoreList;
	}


	/**
	 * 查询分组信息
	 *
	 * @param map
	 * @return
	 * @Title: getCountGroup
	 * @return: List
	 */
	public boolean getCountGroup(Map<String, Object> map) {
		List<Map<String, Object>> personnelCount = dao.query("SQAP01.countGroup", map);
		if (CollectionUtils.isNotEmpty(personnelCount)) {
			return true;
		}
		return false;
	}


	/**
	 * 查询问卷下拥有的项目
	 *
	 * @param map
	 * @return
	 * @Title: getAssessManageProjects
	 * @return: List<AssessBillProject>
	 */
	public List<Map<String, Object>> getAssessManageProjects(Map<String, Object> map) {
		List<Map<String, Object>> assessManageProjectsList = dao.query("SQAP01.getAssessManageProjects", map);
		return assessManageProjectsList;
	}


	/**
	 * 查询项目打分信息
	 *
	 * @param map
	 * @return
	 * @return: List<SqProject>
	 */
	public List<Map<String, Object>> getQueryInstance(Map<String, Object> map) {
		List<Map<String, Object>> instanceList = dao.query("SQAP01.getQueryInstance", map);
		return instanceList;
	}

	/**
	 * 查询小代码配置项
	 * 是否自定义选项，默认为否
	 *
	 * @Title: TyepCode
	 * @Description: 查询小代码配置项
	 */
	public boolean TyepCode() {
		// 是否自定义选项，默认为否
		boolean isCustom = false;
		//创建集合
		List<Map<String, String>> paramList;
		try {
			//获取参数
			paramList = TyepCode.dealUseDay("WILP.sq.aq");
			for (Map<String, String> paramMap : paramList) {
				switch (paramMap.get("value")) {
					case "Y":
						isCustom = true;
						break;
					case "N":
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return isCustom;
		}
		//返回
		return isCustom;
	}

}
