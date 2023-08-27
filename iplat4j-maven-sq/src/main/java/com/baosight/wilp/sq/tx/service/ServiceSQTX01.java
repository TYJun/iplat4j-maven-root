package com.baosight.wilp.sq.tx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.ap.domain.SqProject;
import com.baosight.wilp.sq.common.TyepCode;
import com.baosight.xservices.xs.util.UserSession;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 问卷填写逻辑处理,  查询该账号下是否有问卷 ,查询项目
 * <P>查询该账号下是否有问卷      getAssessManage
 * <p>查询项目   getAssessManageProject
 *
 * @Title: ServiceSQTX01.java
 * @ClassName: ServiceSQTX01
 * @Package：com.baosight.wilp.sq.tx.service
 * @author: zhangjiaqian
 * @date: 2021年7月30日 下午2:39:53
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQTX01 extends ServiceBase {

	// 打印日志
	protected static final Logger logger = LoggerFactory.getLogger(ServiceSQTX01.class);

	/**
	 * 查询项目
	 *
	 * @Title: getAssessManageProject
	 * @return: void
	 */
	public EiInfo getAssessManageProject(EiInfo inInfo) {

		//单据号
		String billNo = (String) inInfo.get("billNo");
		if (StringUtils.isBlank(billNo)) {
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少loginUser参数");
		} else {
			Map<String, Object> mapProject = new HashMap<String, Object>();

			mapProject.put("billNo", billNo);
			List<SqProject> listProject = getAssessManageProject4(mapProject);//查询项目
			//查询配置项
			List<Map<String, String>> tyepCode = TyepCode();
			//获取配置项参数
			String flag = null;
			for (Map<String, String> map : tyepCode) {
				String label = map.get("label");
				if (label.equals("满意度自定义选项")) {
					flag = map.get("value");
				}
			}

			if (listProject.size() > 0) {
				for (SqProject assess : listProject) {
					String id = assess.getId();
					mapProject.put("id", id);
					if (flag.equals("N")) {
//						List listInstance = getQueryInstance(mapProject);//查询项目明细
//						assess.setSqProjectInstance(listInstance);
					} else {
//						List<SqExamInstance> listInstance = getQueryInstance(mapProject);//查询项目明细
//						for (SqExamInstance sqExamInstance : listInstance) {
//							sqExamInstance.setRadioList(getAssessRadio(sqExamInstance.getPointType(), String.valueOf(sqExamInstance.getPoint())));
//						}
//						assess.setSqProjectInstance(listInstance);
					}
				}
				inInfo.setMsgKey("200");
				inInfo.setMsg("成功");
				inInfo.set("param", listProject);
			}
		}
		return inInfo;
	}


	/**
	 * 获取Radio
	 *
	 * @param pointType
	 * @param point
	 * @return
	 * @Title: getAssessRadio
	 * @return: List
	 */
	public List getAssessRadio(String pointType, String point) {
		List<Map<String, String>> radioList = new ArrayList();
		Map map = new HashMap();
		if (pointType.equals("0")) {
			// radioList = getQueryType();
			radioList = sqType();
			if ("5".equals(point)) {
				Map map1 = new HashMap();
				Map map2 = new HashMap();
				Map map3 = new HashMap();
				map1.put("label", "满意");
				map1.put("value", Integer.valueOf(radioList.get(0).get("value")) / 2);
				map2.put("label", "一般");
				map2.put("value", 2.5);
				map3.put("label", "不满意");
				map3.put("value", 0);
				radioList.clear();
				radioList.add(map1);
				radioList.add(map2);
				radioList.add(map3);
			}
		} else {
			Map mapY = new HashMap();
			mapY.put("label", "是");
			mapY.put("value", Integer.valueOf(point));

			Map mapN = new HashMap();
			mapN.put("label", "否");
			mapN.put("value", 0);

			radioList.add(mapY);
			radioList.add(mapN);
		}
		return radioList;
	}


	/**
	 * 保存提交问卷信息
	 * 保存项目
	 * 保存题目
	 * 保存选项
	 *
	 * @param inInfo
	 * @Title: saveAssessProjectInstance
	 * @return: void
	 */
	public EiInfo saveAssessProjectInstance(EiInfo inInfo) {
		/*
		 * 获取前端参数
		 */
		// 问卷建议
//		String standardAdvice = inInfo.getString("advice");
		//获取批次号
//		String batchNo = inInfo.getString("billNo");
		String parentBatchNo = inInfo.getString("batchNo");

		String workNo = inInfo.getString("workNo");
		if (StringUtils.isBlank(workNo)) {
			Map<String, Object> getRole = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			workNo = (String) getRole.get("workNo");
		}
		logger.info("用户ID获取：" + workNo);

		try {
			/**
			 * 二.参数校验
			 */
			if (StringUtils.isBlank(workNo)) {
				inInfo.setMsgKey("199");
				inInfo.setMsg("缺少loginUser参数");
				return inInfo;
			}
			// 用于获取用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
			/*
			 * 参数封装
			 */
			Map<String, Object> mapParam = new HashMap<String, Object>();
			List<Map<String, Object>> list = (ArrayList<Map<String, Object>>)inInfo.get("list");
			for (Map<String, Object> projectMap2 : list) {
				BigDecimal scoreBigDecimal = new BigDecimal("0");
				BigDecimal pointBigDecimal = new BigDecimal("0");
				String batchNo = (String)projectMap2.get("batchNo");
				//获取当前时间
				String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String scoreId = UUID.randomUUID().toString();
				mapParam.put("scoreId", scoreId);
				mapParam.put("batchNo", batchNo);
//				mapParam.put("batchNo", batchNo);
//			mapParam.put("standardAdvice", standardAdvice);
				mapParam.put("workNo", staffByUserId.get("workNo"));
				//jsonArray
//				List instanceArray = (ArrayList)inInfo.get("instanceArray");
				List instanceArray = (ArrayList)projectMap2.get("ProjectItem");
				dao.delete("SQAP01.deleteSqManageItem", mapParam);
				dao.delete("SQAP01.deleteSqManageAdvice", mapParam);
				dao.delete("SQAP01.deleteSqManageScore", mapParam);
				/*
				 * 检验参数
				 */
				if (StringUtils.isBlank(batchNo)) {
					inInfo.setMsgKey("199");
					inInfo.setMsg("缺少参数billNo");
				} else if (StringUtils.isBlank(workNo)) {
					inInfo.setMsgKey("199");
					inInfo.setMsg("缺少参数LoginUser");
//			} else if (StringUtils.isBlank(instanceArray)) {
				} else if ( instanceArray == null || instanceArray.size() ==0) {
					inInfo.setMsgKey("199");
					inInfo.setMsg("缺少参数instanceArray");
				}
				//考核项目对象
				JSONArray jsonArray = JSONArray.fromObject(instanceArray);
				// 判断上传jsonArray是否为空
				boolean flag = true;
				// 不为空
				if (!jsonArray.isEmpty()) {
					/*
					 * 逻辑校验，三层遍历，禁止麻瓜使用魔法
					 */
					// 讲jsonArray类型转换成项目列表
					List<Map<String, Object>> projectList = jsonArray;
					// 第一次遍历遍历出题目
					for (Map<String, Object> projectMap : projectList) {
						projectMap.put("adviceId", UUID.randomUUID().toString());
						projectMap.put("scoreId", scoreId);
						projectMap.put("batchNo", batchNo);
						String advice = projectMap.get("advice").toString();

						// 获取projectMap中的instance
						if(advice == null || "null".equals(advice)){
							projectMap.put("advice","");
						}else{
							projectMap.put("advice",projectMap.get("advice").toString());
						}

						dao.insert("SQAP01.insertSqManageAdvice", projectMap);

						JSONArray instance = (JSONArray) projectMap.get("instance");

						// 不为空
						if (!instance.isEmpty()) {
							// 类型转换
							List<Map<String, Object>> instanceList = instance;
							// 第二次遍历，遍历获得题目
							for (Map<String, Object> instanceMap : instanceList) {


								JSONArray instanceItem = (JSONArray) instanceMap.get("instanceItem");

								String point = instanceMap.get("point").toString();
								pointBigDecimal = pointBigDecimal.add(new BigDecimal(point));
								// 保存每个项目的建议与评价
								
								// 不为空
								if (!instanceItem.isEmpty()) {
									List<Map<String, Object>> instanceItemList = instanceItem;
									// 第三次遍历出得分
									for (Map<String, Object> instanceItemMap : instanceItemList) {
										// 获得得分
										String score = instanceItemMap.get("inputScore").toString();
										String value = instanceItemMap.get("value").toString();
//										String point = instanceItemMap.get("point").toString();

//										Map<String, Object> scoreMap = (Map<String, Object>) scoreObject;
//										String value = (String) scoreMap.get("value");
//										Integer score = (Integer) scoreMap.get("score");
//										Integer point = (Integer) scoreMap.get("point");
										// 如果instanceResult=0
										// 1。保存题目明细
										instanceItemMap.put("sqManageItemId", UUID.randomUUID().toString());
										instanceItemMap.put("scoreId", scoreId);
										instanceItemMap.put("batchNo", batchNo);
										instanceItemMap.put("instanceId", instanceMap.get("instanceId"));
										instanceItemMap.put("instanceResult", value);
										scoreBigDecimal = scoreBigDecimal.add(new BigDecimal(score));

										flag = insertSqManageItem(instanceItemMap);

										Map<String, Object> itemMap = new HashMap<String, Object>();
										itemMap.put("id", UUID.randomUUID().toString());
										itemMap.put("parentBatchNo", parentBatchNo);
										itemMap.put("batchNo", batchNo);
										itemMap.put("workNo", workNo);
										itemMap.put("advice", advice);
										itemMap.put("projectId", projectMap.get("projectId"));
										itemMap.put("projectName", projectMap.get("projectName"));
										itemMap.put("instanceId", instanceMap.get("instanceId"));
										itemMap.put("instanceName", instanceMap.get("instanceName"));
										itemMap.put("itemName", instanceItemMap.get("label"));
										itemMap.put("itemPoint", instanceItemMap.get("score"));
										itemMap.put("itemScore", new BigDecimal(score));
										flag = insertSqItem(itemMap);


										if (!flag) {
											inInfo.setMsgKey("199");
											inInfo.setMsg("提交失败");
											break;
										}
									}
								}
							}
						}
					}
				}
				// 2.保存题目分数
				mapParam.put("score", scoreBigDecimal);
				mapParam.put("point", pointBigDecimal);
				mapParam.put("deptNum", staffByUserId.get("deptNum"));
				mapParam.put("deptName", staffByUserId.get("deptName"));
				mapParam.put("workNo", staffByUserId.get("workNo"));
				mapParam.put("workName", staffByUserId.get("name"));
				mapParam.put("parentBatchNo", parentBatchNo);

				dao.insert("SQAP01.insertSqManageScore", mapParam);
				if (flag) {
					inInfo.setMsgKey("200");
					inInfo.setMsg("提交成功");
				}
			}

		} catch (Exception e){
			e.printStackTrace();
			inInfo.setMsgKey("199");
			inInfo.setMsg("提交失败");
		}
		return inInfo;
	}


	/**
	 * 查看明细
	 *
	 * @param inInfo
	 * @Title: getAssessManageProjectInstance
	 * @return: void
	 */
	public EiInfo getAssessManageProjectInstance(EiInfo inInfo) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		//主题单据
		String billNo = (String) inInfo.get("billNo");
		String userId = UserSession.getUser().getUsername();
		//获取用户名
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(userId);
		if (StringUtils.isBlank(billNo)) {
			inInfo.setMsgKey("199");
			inInfo.setMsg("缺少参数billNo");
		} else {
			mapParam.put("billNo", billNo);
			//查询项目
			List<SqProject> listProject = getAssessManageProject4(mapParam);
			if (listProject.size() > 0) {
				for (SqProject sqProject : listProject) {
					List queryPoints = getQueryPoints(sqProject.getProjectCode());
					Object object = queryPoints.get(0);
					sqProject.setPoints(object.toString());
					String projectCode = sqProject.getProjectCode();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("billNo", billNo);
					map.put("projectCode", projectCode);
					map.put("creator", staffByUserId.get("name"));
					List<Map> listInstance = getAssessManageProject3(map);
					sqProject.setSqProjectInstance(listInstance);
				}
				inInfo.setMsgKey("200");
				inInfo.setMsg("成功");
				inInfo.set("param", listProject);
			}
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

		Map<String, Object> pMap = new HashMap<String, Object>();
		Map<String, Object> mapInstance = new HashMap<String, Object>();
		//项目编码
		String projectCode = (String) inInfo.get("projectCode");
		//主题单据
		String billNo = (String) inInfo.get("billNo");
		//工号
		String workNo = (String) inInfo.get("workNo");
		//获取用户名
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);

		mapInstance.put("projectCode", projectCode);
		mapInstance.put("billNo", billNo);
		mapInstance.put("workNo", workNo);
		mapInstance.put("creator", staffByUserId.get("name"));
		List listInstacne = getSqManageProjectInstance(mapInstance);

		if (listInstacne.size() > 0) {
			inInfo.setMsgKey("200");
			inInfo.setMsg("成功");
			inInfo.set("param", listInstacne);
		}
		return inInfo;
	}


	/**
	 * 小代码配置
	 *
	 * @Title: sqType
	 * @return: EiInfo
	 */
	public List<Map<String, String>> sqType() {
		List<Map<String, String>> param = new ArrayList<Map<String, String>>();
		try {
			param = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.sq.Scoring");
		} catch (Exception e) {
			return param;
		}
		return param;
	}


	/**
	 * 保存打分记录
	 *
	 * @param mapParam
	 * @return
	 * @Title: saveAssessManageProject
	 * @return: boolean
	 */
	public boolean insertSqManageItem(Map<String, Object> mapParam) {
		try {
			dao.insert("SQAP01.insertSqManageItem", mapParam);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存打分记录详情
	 *
	 * @param mapParam
	 * @return
	 * @Title:
	 * @return: boolean
	 */
	public boolean insertSqItem(Map<String, Object> mapParam) {
		try {
			dao.insert("SQAP01.insertSqItem", mapParam);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 查询项目打分
	 *
	 * @param map
	 * @return
	 * @Title: getSqProjectParam
	 * @return: List
	 */
	public List getSqManageProjectInstance(Map map) {
		List list = dao.query("SQAP01.getSqManageProjectInstance", map);
		return list;
	}


	/**
	 * 查询项目总分
	 *
	 * @param param
	 * @return
	 * @Title: getSqProjectParam
	 * @return: List
	 */
	public List getQueryPoints(String param) {
		List list = dao.query("SQAP01.queryPoints", param);
		return list;
	}


	/**
	 * 查询标准信息
	 *
	 * @return
	 * @Title: getSqProjectInstance
	 * @return: List
	 */
//	public List<SqExamInstance> getSqProjectInstance(Map map) {
//		List<SqExamInstance> list = dao.query("SQAP01.queryInstance", map);
//		return list;
//	}


	/**
	 * 查询问卷调查
	 *
	 * @return
	 * @Title: getAssessManage
	 * @return: List
	 */
	public List getAssessManage(String datagroupCode) {
		List<Map> query = dao.query("SQAP01.getAssessManage", datagroupCode);
		return query;
	}


	/**
	 * 此单据与这个人是否有提交记录
	 *
	 * @param map
	 * @return
	 * @Title: getAssessManageProject3
	 * @return: List
	 */
	public List getAssessManageProject3(Map map) {
		List<Map> query = dao.query("SQAP01.getAssessManageProject3", map);
		return query;
	}


	/**
	 * 查询分组信息
	 *
	 * @param map
	 * @return
	 * @Title: getCountGroup
	 * @return: List
	 */
	public List<Integer> getCountGroup(Map map) {
		List<Integer> query = dao.query("SQAP01.countGroup", map);
		return query;
	}


	/**
	 * 查询项目
	 *
	 * @param map
	 * @return
	 * @Title: getAssessManageProject4
	 * @return: List<AssessBillProject>
	 */
	public List<SqProject> getAssessManageProject4(Map map) {
		List<SqProject> list = dao.query("SQAP01.queryProject", map);
		return list;
	}


	/**
	 * 查询项目打分信息
	 *
	 * @param map
	 * @return
	 * @Title: getAssessManageProject4
	 * @return: List<SqProject>
	 */
//	public List<SqExamInstance> getQueryInstance(Map map) {
//		List<SqExamInstance> list = dao.query("SQAP01.queryInstance", map);
//		return list;
//	}


	/**
	 * 查询配置信息
	 *
	 * @Title: getQueryInstance
	 * @return: List<SqProjectInstance>
	 */
	public List<Map> getQueryType() {
		List<Map> list = dao.query("SQAP01.queryType", null);
		return list;
	}


	/**
	 * 查询小代码配置项
	 *
	 * @return
	 * @Title: TyepCode
	 * @return: List<Map>
	 */
	public List<Map<String, String>> TyepCode() {

		List<Map<String, String>> param = new ArrayList<Map<String, String>>();
		try {
			param = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.sq.aq");
		} catch (Exception e) {
			e.printStackTrace();
			return param;
		}
		return param;
	}


}
