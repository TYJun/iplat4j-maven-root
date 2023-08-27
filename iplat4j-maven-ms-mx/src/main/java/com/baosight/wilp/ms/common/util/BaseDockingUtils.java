package com.baosight.wilp.ms.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.xservices.xs.util.UserSession;


/***
 * 与基础模块对接
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  BaseDocking.java
 * @ClassName:  BaseDocking
 * @Package com.baosight.wilp.mt.re.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年6月23日 下午4:29:20 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
@SuppressWarnings("all")
public class BaseDockingUtils {
	
	/**
	 * 执行服务调用
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： 参数对象
	 * @param:  @param serviceId ： 服务ID
	 * @param:  @return
	 * @return: EiInfo  : 返回对象
	 * @throws
	 */
	private static EiInfo invoke (EiInfo inInfo, String serviceId) {
		try{
			inInfo.set(EiConstant.serviceId, serviceId);
			EiInfo outInfo = XServiceManager.call(inInfo);
			if(outInfo.getStatus() < 0){
				throw new PlatException(outInfo.getMsg());
			}
			return outInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 1.获取所有员工信息(原生)
	 * @Title: getStaffAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ： 类型为Map或EiInfo
	 * 		offset		 :分页开始的索引
	 *		limit		 :获取的数量
	 *		userId		 :员工userId
	 *		workNo		 :员工工号
	 *		userName	 :用户姓名
	 *		deptId		 :科室ID
	 *		deptNum		 :科室编码
	 *		deptName	 :科室名称
	 *		datagroupCode:院区编码
	 * @param:  @return
	 * @return: EiInfo  
	 * 		result: 数据对象
	 * 			id			:员工ID
	 *			workNo		:员工工号 
	 *			name		:员工姓名
	 *			gender		:员工性别
	 *			contactTel	:联系电话
	 * 			deptId		:科室ID
	 *			deptNum		:科室编码
	 *			deptName	:科室名称
	 *			idNo		:身份证号
	 *			type		:员工类型编码
	 *			staffType	:员工类型
	 *			isService	:是否是服务人员
	 *			status		:员工状态编码
	 *			isStatus	:员工状态
	 * @throws
	 */
	public static EiInfo getStaffAll(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_01");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_01");
		}
		return outinfo;
	}

	/**
	 * 获取所有员工信息(带分页)
	 * @Title: getStaffAllPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param 类型为Map或EiInfo
	 * @param:  @param resultId : EiBlock的id
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo getStaffAllPage(Object param, String resultId){
		EiInfo outinfo = new EiInfo();
		EiInfo staff = getStaffAll(param);
		if(staff != null){
			if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
				return staff;
			} else {
				EiBlock block = staff.getBlock("result");
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
				outinfo.setBlocks(tempMap);
			}
		}
		return outinfo;
	}
	
	/**
	 * 获取所有员工信息（不带分页）
	 * @Title: getStaffAllNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ：类型为Map或EiInfo
	 * @param:  @return
	 * @return: List<Map<String,Object>> ： 返回结果集  
	 * @throws
	 */
	public static List<Map<String, Object>> getStaffAllNoPage(Object param){
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo staff = getStaffAll(param);
		if(staff != null){
			Object object = staff.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 根据工号模糊查询获取返回结果Id字符串
	 * @Title: getStaffIdsByWorkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: String  ： id集合字符串
	 * @throws
	 */
	public static String getStaffIdsByWorkNo(String workNo){
		EiInfo inInfo = new EiInfo();
		inInfo.set("workNo", workNo);
		List<Map<String, Object>> list = getStaffAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据用户名模糊查询获取返回结果Id字符串
	 * @Title: getStaffIdsByName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param userName ： 用户名
	 * @param:  @return
	 * @return: String  ： id集合字符串
	 * @throws
	 */
	public static String getStaffIdsByName(String userName){
		EiInfo inInfo = new EiInfo();
		inInfo.set("name", userName);
		List<Map<String, Object>> list = getStaffAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据用户名模糊查询获取返回结果workNo字符串
	 * @Title: getStaffWorkNosByName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param userName ： 用户名
	 * @param:  @return
	 * @return: String  ： workNo集合字符串
	 * @throws
	 */
	public static String getStaffWorkNosByName(String userName){
		EiInfo inInfo = new EiInfo();
		inInfo.set("name", userName);
		List<Map<String, Object>> list = getStaffAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> workNos = new ArrayList<>();
			list.forEach(map -> {
				String workNo = map.get("workNo") == null ? "" : map.get("workNo") .toString();
				if(StringUtils.isNotBlank(workNo)){
					workNos.add(workNo);
				}
			});
			return StringUtils.join(workNos.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 2.通过员工ID或者员工工号查询员工详情
	 * @Title: getStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		userId	：员工userId
	 *		workNo	：员工工号
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态 
	 * @throws
	 */
	public static EiInfo getStaff(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_02");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_02");
		}
		return outinfo;
	}
	
	/**
	 * 通过员工ID查询员工详情
	 * @Title: getStaffByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param userId ： 用户ID
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
	 */
	public static Map<String,Object> getStaffByUserId(String userId){
		Map<String,Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("userId", userId);
		EiInfo staff = getStaff(inInfo);
		if(staff != null){
			Object object = staff.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 通过员工工号查询员工详情
	 * @Title: getStaffByWorkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo : 工号
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
	 */
	public static Map<String,Object> getStaffByWorkNo(String workNo){
		Map<String,Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("workNo", workNo);
		EiInfo staff = getStaff(inInfo);
		if(staff != null){
			Object object = staff.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 3.通过科室ID或者科室编码查询科室下员工
	 * @Title: getStaffByDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		deptId：科室Id
	 * 		deptNum：科室编码
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态 
	 * @throws
	 */
	public static EiInfo getStaffByDept(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_03");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_03");
		}
		return outinfo;
	}
	
	/**
	 * 通过科室ID查询科室下员工
	 * @Title: getStaffByDeptId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptId ： 科室ID
	 * @param:  @return
	 * @return: List<Map<String,Object>> : 用户信息
	 * @throws
	 */
	public static List<Map<String,Object>> getStaffByDeptId(String deptId){
		List<Map<String,Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptId", deptId);
		EiInfo staff = getStaffByDept(inInfo);
		if(staff != null){
			Object object = staff.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 通过科室编码查询科室下员工
	 * @Title: getStaffByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ：科室编码
	 * @param:  @return
	 * @return: Map<String,Object>  : 用户信息
	 * @throws
	 */
	public static List<Map<String,Object>> getStaffByDeptNum(String deptNum){
		List<Map<String,Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptNum", deptNum);
		EiInfo staff = getStaffByDept(inInfo);
		if(staff != null){
			Object object = staff.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 根据用户所属科室Id获取返回结果Id字符串
	 * @Title: getStaffIdsByDeptId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptId : 科室Id
	 * @param:  @return
	 * @return: String  : 用户ID集合字符串
	 * @throws
	 */
	public static String getStaffIdsByDeptId(String deptId){
		List<Map<String, Object>> list = getStaffByDeptId(deptId);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据用户所属科室编码获取返回结果Id字符串
	 * @Title: getStaffIdsByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum : 科室编码
	 * @param:  @return
	 * @return: String  ： 用户ID集合字符串
	 * @throws
	 */
	public static String getStaffIdsByDeptNum(String deptNum){
		List<Map<String, Object>> list = getStaffByDeptNum(deptNum);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据用户所属科室Id获取返回结果workNo字符串
	 * @Title: getStaffWorkNosByDeptId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptId ： 科室id
	 * @param:  @return
	 * @return: String  : 用户工号集合字符串
	 * @throws
	 */
	public static String getStaffWorkNosByDeptId(String deptId){
		List<Map<String, Object>> list = getStaffByDeptId(deptId);
		if(list.size() > 0){
			List<String> workNos = new ArrayList<>();
			list.forEach(map -> {
				String workNo = map.get("workNo") == null ? "" : map.get("workNo") .toString();
				if(StringUtils.isNotBlank(workNo)){
					workNos.add(workNo);
				}
			});
			return StringUtils.join(workNos.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据用户所属科室编码获取返回结果workNo字符串
	 * @Title: getStaffWorkNosByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return
	 * @return: String  ： 用户工号集合字符串
	 * @throws
	 */
	public static String getStaffWorkNosByDeptNum(String deptNum){
		List<Map<String, Object>> list = getStaffByDeptNum(deptNum);
		if(list.size() > 0){
			List<String> workNos = new ArrayList<>();
			list.forEach(map -> {
				String workNo = map.get("workNo") == null ? "" : map.get("workNo") .toString();
				if(StringUtils.isNotBlank(workNo)){
					workNos.add(workNo);
				}
			});
			return StringUtils.join(workNos.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 4.通过科室ID或者科室编码查询科室下员工数量
	 * @Title: getStaffCountDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param :类型为Map或EiInfo
	 * 		deptId：科室Id
	 * 		deptNum：科室编码
	 * @param:  @return
	 * @return: int : 数量  
	 * @throws
	 */
	public static int getStaffCountDept(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_04");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_04");
		}
		Object object = outinfo.get("result");
		if(object !=null){
			Map<String, Integer> map = (Map<String, Integer>) object;
			return map.get("count");
		}
		return 0;
	}
	
	/**
	 * 5.查询科室列表
	 * @Title: getDeptAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param :类型为Map或EiInfo
	 * 		offset	  		:分页开始的索引
	 *		limit		    :获取的数量
	 *		deptId			:	科室ID
	 *		deptNum			:	科室编码
	 *		deptName		:	科室名称
	 *		datagroupCode	:	院区编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id				:	科室ID
	 *		deptNum			:	科室编号 
	 *		deptName		:	科室名称
	 *		parentId		:	父级ID
	 *		parentDeptName	:	父级科室名称
	 *		deptDescribe	:	科室描述
	 *		type			:	科室类型
	 *		isService		:	是否是服务科室
	 *		status			:	科室状态
	 *		isStop			:	是否停用 
	 * @throws
	 */
	public static EiInfo getDeptAll(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_05");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_05");
		}
		return outinfo;
	}

	/**
	 * 获取所有科室信息（带分页）
	 * @Title: getDeptAllPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ：类型为Map或EiInfo
	 * @param:  @param resultId ：返回EiBlock的id
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo getDeptAllPage(Object param, String resultId){
		EiInfo outinfo = new EiInfo();
		EiInfo dept = getDeptAll(param);
		if(dept != null){
			if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
				return dept;
			} else {
				EiBlock block = dept.getBlock("result");
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
				outinfo.setBlocks(tempMap);
			}
		}
		return outinfo;
	}
	
	/**
	 * 获取所有科室信息（不带分页）
	 * @Title: getDeptAllNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ：类型为Map或EiInfo
	 * @param:  @return
	 * @return: List<Map<String,Object>> ： 返回结果集  
	 * @throws
	 */
	public static List<Map<String, Object>> getDeptAllNoPage(Object param){
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo dept = getDeptAll(param);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 根据科室编码模糊查询获取返回结果Id字符串
	 * @Title: getDeptIdsBydeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return
	 * @return: String  ：  id集合字符串
	 * @throws
	 */
	public static String getDeptIdsBydeptNum(String deptNum){
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptNum", deptNum);
		List<Map<String, Object>> list = getDeptAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据科室名模糊查询获取返回结果Id字符串
	 * @Title: getDeptIdsByName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptName ：科室名称
	 * @param:  @return
	 * @return: String  ： id集合字符串
	 * @throws
	 */
	public static String getDeptIdsByName(String deptName){
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptName", deptName);
		List<Map<String, Object>> list = getDeptAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> ids = new ArrayList<>();
			list.forEach(map -> {
				String id = map.get("id") == null ? "" : map.get("id") .toString();
				if(StringUtils.isNotBlank(id)){
					ids.add(id);
				}
			});
			return StringUtils.join(ids.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 根据科室名模糊查询获取返回结果deptNum字符串
	 * @Title: getDeptNosByName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptName : 科室名称
	 * @param:  @return
	 * @return: String  ： deptNum集合字符串
	 * @throws
	 */
	public static String getDeptNosByName(String deptName){
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptName", deptName);
		List<Map<String, Object>> list = getDeptAllNoPage(inInfo);
		if(list.size() > 0){
			List<String> deptNums = new ArrayList<>();
			list.forEach(map -> {
				String deptNum = map.get("deptNum") == null ? "" : map.get("deptNum") .toString();
				if(StringUtils.isNotBlank(deptNum)){
					deptNums.add(deptNum);
				}
			});
			return StringUtils.join(deptNums.toArray(),",");  
		} else {
			return "";
		}
	}
	
	/**
	 * 6.通过科室ID或者科室编码查询科室详情
	 * @Title: getDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ： 类型为Map或EiInfo
	 *		deptId			:	科室ID
	 *		deptNum			:	科室编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id				:	科室ID
	 *		deptNum			:	科室编号 
	 *		deptName		:	科室名称
	 *		parentId		:	父级ID
	 *		parentDeptName	:	父级科室名称
	 *		deptDescribe	:	科室描述
	 *		type			:	科室类型
	 *		isService		:	是否是服务科室
	 *		status			:	科室状态
	 *		isStop			:	是否停用 
	 * @throws
	 */
	public static EiInfo getDept(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_06");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_06");
		}
		return outinfo;
	}
	
	/**
	 * 根据科室id查询科室信息
	 * @Title: getDeptByDeptId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptId ： 科室Id
	 * @param:  @return
	 * @return: Map<String,Object>  : 科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptByDeptId(String deptId){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptId", deptId);
		EiInfo dept = getDept(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 根据科室编码查询科室信息
	 * @Title: getDeptByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return
	 * @return: Map<String,Object>  ：  科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptByDeptNum(String deptNum){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptNum", deptNum);
		EiInfo dept = getDept(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 7.通过员工ID或者员工工号查询所属科室详情
	 * @Title: getDeptByUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 *		userId	：员工userId
	 *		workNo	：员工工号
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id				:	科室ID
	 *		deptNum			:	科室编号 
	 *		deptName		:	科室名称
	 *		parentId		:	父级ID
	 *		parentDeptName	:	父级科室名称
	 *		deptDescribe	:	科室描述
	 *		type			:	科室类型
	 *		isService		:	是否是服务科室
	 *		status			:	科室状态
	 *		isStop			:	是否停用 		
	 * @throws
	 */
	public static EiInfo getDeptByUser(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_07");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_07");
		}
		return outinfo;
	}
	
	/**
	 * 根据用户ID查询用户所属科室信息
	 * @Title: getDeptByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param userId ： 用户id
	 * @param:  @return
	 * @return: Map<String,Object>  ： 科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptByUserId(String userId){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("userId", userId);
		EiInfo dept = getDeptByUser(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 根据用户工号查询用户所属科室信息
	 * @Title: getDeptByworkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 用户工号
	 * @param:  @return
	 * @return: Map<String,Object>  ： 科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptByworkNo(String workNo){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("workNo", workNo);
		EiInfo dept = getDeptByUser(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 8.通过地点获取科室信息
	 * @Title: getDeptBySpot
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		spotId 	 : 地点ID
	 *		spotNum  : 地点编码
	 *		spotName : 地点名称
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id				:	科室ID
	 *		deptNum			:	科室编号 
	 *		deptName		:	科室名称
	 *		parentId		:	父级ID
	 *		parentDeptName	:	父级科室名称
	 *		deptDescribe	:	科室描述
	 *		type			:	科室类型
	 *		isService		:	是否是服务科室
	 *		status			:	科室状态
	 *		isStop			:	是否停用 
	 * @throws
	 */
	public static EiInfo getDeptBySpot(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_08");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_08");
		}
		return outinfo;
	}
	
	/**
	 * 根据地点Id查询科室信息
	 * @Title: getDeptBySpotId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param spotId ： 地点ID
	 * @param:  @return
	 * @return: Map<String,Object>  ： 科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptBySpotId(String spotId){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("spotId", spotId);
		EiInfo dept = getDeptBySpot(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}
	
	/**
	 * 根据地点编码查询科室信息
	 * @Title: getDeptBySpotNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param spotNum ： 地点编码
	 * @param:  @return
	 * @return: Map<String,Object>  ： 科室信息
	 * @throws
	 */
	public static Map<String, Object> getDeptBySpotNum(String spotNum){
		Map<String, Object> map = new HashMap<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("spotNum", spotNum);
		EiInfo dept = getDeptBySpot(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				map = (Map<String, Object>) object;
			}
		}
		return map;
	}	
	
	/**
	 * 查询地点信息
	 * @author zhaow
	 * @Title: getSpotAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 *		deptId			:	科室ID
	 *		deptNum			:	科室编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	地点ID
	 *		spotNum		：	地点编码
	 *		spotName	：	地点
	 *		deptId		：	所属科室ID
	 *		deptNum		：	所属科室编码
	 *		deptName	：	所属科室名称
	 *		building	： 	楼
	 *		floor		：	层
	 *		room		：	房间
	 *		remark		：	备注 
	 * @throws
	 */
	public static EiInfo getSpotAll(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_09");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_09");
		}
		return outinfo;
	}
	
	/**
	 * 查询地点信息(有分页)
	 * @Title: getSpotAllPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 *		deptId			:	科室ID
	 *		deptNum			:	科室编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	地点ID
	 *		spotNum		：	地点编码
	 *		spotName	：	地点
	 *		deptId		：	所属科室ID
	 *		deptNum		：	所属科室编码
	 *		deptName	：	所属科室名称
	 *		building	： 	楼
	 *		floor		：	层
	 *		room		：	房间
	 *		remark		：	备注 
	 * @throws
	 */
	public static EiInfo getSpotAllPage(Object param,String resultId) {
		EiInfo outinfo = new EiInfo();
		EiInfo staff = getSpotAll(param);
		if(staff != null){
			if(StringUtils.isBlank(resultId) ){
				EiBlock block = staff.getBlock("result");
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block);
				outinfo.setBlocks(tempMap);
			} else {
				EiBlock block = new EiBlock(resultId);
				block.addRows((List) staff.getAttr().get("result"));
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block);
				outinfo.setBlocks(tempMap);
			}
		}
		return outinfo;
	 }
	 
	/**
	 * 9.通过科室查询地点信息
	 * @Title: getSpotByDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 *		deptId			:	科室ID
	 *		deptNum			:	科室编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	地点ID
	 *		spotNum		：	地点编码
	 *		spotName	：	地点
	 *		deptId		：	所属科室ID
	 *		deptNum		：	所属科室编码
	 *		deptName	：	所属科室名称
	 *		building	： 	楼
	 *		floor		：	层
	 *		room		：	房间
	 *		remark		：	备注 
	 * @throws
	 */
	public static EiInfo getSpotByDept(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_09");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_09");
		}
		return outinfo;
	}
	
	/**
     * 根据地点名称获取电话信息
     * @Title: getPhoneBySpotName
     * @author zhaow
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param spotNum ： 地点编码
     * @param:  @return
     * @return: List<Map<String,Object>>  ： 电话信息
     * @throws
     */
    public static List<Map<String, Object>> getSpotBySpotName(String spotName){
        List<Map<String, Object>> list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("spotName", spotName);
        EiInfo dept = getSpotByDept(map);
        if(dept != null){
            Object object = dept.get("result");
            if(object !=null){
                list = (List<Map<String, Object>>) object;
            }
        }
        return list; 
    }
	
	/**
	 * 根据科室Id查询地点信息
	 * @Title: getSpotByDeptId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptId ： 科室id
	 * @param:  @return
	 * @return: List<Map<String,Object>>  : 地点信息
	 * @throws
	 */
	public static List<Map<String, Object>> getSpotByDeptId(String deptId) {
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptId", deptId);
		EiInfo dept = getSpotByDept(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 根据科室编码查询地点信息
	 * @Title: getSpotByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return
	 * @return: List<Map<String,Object>>  ： 地点信息
	 * @throws
	 */
	public static List<Map<String, Object>> getSpotByDeptNum(String deptNum) {
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("deptNum", deptNum);
		EiInfo dept = getSpotByDept(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 10.通过电话获取科室和地点信息
	 * @Title: getSpotAndDeptByPhone
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		telNum	：	电话
	 *		datagroupCode  ：院区编码
	 * @param:  @return
	 * @return: List<Map<String, Object>> 
	 * 		id			：	地点ID
	 *		spotNum		：	地点编码
	 *		spotName	：	地点
	 *		deptId		：	所属科室ID
	 *		deptNum		：	所属科室编码
	 *		deptName	：	所属科室名称
	 *		building	： 	楼
	 *		floor		：	层
	 *		room		：	房间
	 * 		remark		：	备注 
	 * @throws
	 */
	public static List<Map<String, Object>> getSpotAndDeptByPhone(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_10");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_10");
		}
		List<Map<String, Object>> list = new ArrayList<>();
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 11.通过地点获取电话信息
	 * @Title: getPhoneBySpot
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		spotId		:	地点ID
	 *		spotNum		:	地点编码
	 *		spotName	:	地点名称
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			:	电话ID
	 *		telNum		:	电话
	 *		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称
	 *		spotId		:	地点ID
	 *		spotNum		:	地点编码
	 *		spotName	:	地点
	 *		building	:	楼
	 *		floor		:	层
	 *		room		:	房间
	 *		remark		:	地点备注
	 *		telTemark	:	电话备注 
	 * @throws
	 */
	public static EiInfo getPhoneBySpot(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_11");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_11");
		}
		return outinfo;
	}
	
	/**
	 * 根据地点id获取电话信息
	 * @Title: getPhoneBySpotId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param spotId : 地点Id
	 * @param:  @return
	 * @return: List<Map<String,Object>>  : 电话信息
	 * @throws
	 */
	public static List<Map<String, Object>> getPhoneBySpotId(String spotId){
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("spotId", spotId);
		EiInfo dept = getPhoneBySpot(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 根据地点编码获取电话信息
	 * @Title: getPhoneBySpotNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param spotNum ： 地点编码
	 * @param:  @return
	 * @return: List<Map<String,Object>>  ： 电话信息
	 * @throws
	 */
	public static List<Map<String, Object>> getPhoneBySpotNum(String spotNum){
		List<Map<String, Object>> list = new ArrayList<>();
		EiInfo inInfo = new EiInfo();
		inInfo.set("spotNum", spotNum);
		EiInfo dept = getPhoneBySpot(inInfo);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 根据地点名称获取电话信息
	 * @Title: getPhoneBySpotName
	 * @author zhaow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param spotNum ： 地点编码
	 * @param:  @return
	 * @return: List<Map<String,Object>>  ： 电话信息
	 * @throws
	 */
	public static List<Map<String, Object>> getPhoneBySpotName(String spotName){
		List<Map<String, Object>> list = new ArrayList<>();
		Map map = new HashMap<>();
		map.put("spotName", spotName);
		EiInfo dept = getPhoneBySpot(map);
		if(dept != null){
			Object object = dept.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 12.查询服务科室
	 * @Title: getServerDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param dataGroupCode ： 账套
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id				：	科室ID
	 *		deptNum			：	科室编号 
	 *		deptName		：	科室名称
	 *		parentId		：	父级ID
	 *		parentDeptName	：	父级科室名称
	 *		deptDescribe	：	科室描述
	 *		type			：	科室类型
	 *		isService		：	是否是服务科室
	 *		status			：	科室状态
	 *		isStop			：	是否停用
	 * @throws
	 */
	public static List<Map<String, Object>> getServerDept(String dataGroupCode){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("datagroupCode", dataGroupCode);
		EiInfo outinfo = invoke(inInfo,"S_AC_FW_12");
		
		List<Map<String, Object>> list = new ArrayList<>();
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 13.获取楼
	 * @Title: getBuilding
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param building : 楼
	 * @param:  @return
	 * @return: List<Map<String,Object>>  
	 * 		building ： 楼
	 * @throws
	 */
	public static List<Map<String, Object>> getBuilding(String building){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("building", building);
		EiInfo outinfo = invoke(inInfo,"S_AC_FW_13");
		
		List<Map<String, Object>> list = new ArrayList<>();
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 14.获取层
	 * @Title: getFloor
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param building : 楼
	 * @param:  @param floor ： 层
	 * @param:  @return
	 * @return: List<Map<String,Object>>  ： 层数据集
	 * 		floor ： 层
	 * @throws
	 */
	public static List<Map<String, Object>> getFloor(String building, String floor){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("building", building);
		inInfo.set("floor", floor);
		EiInfo outinfo = invoke(inInfo,"S_AC_FW_14");
		
		List<Map<String, Object>> list = new ArrayList<>();
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 15.获取房间信息
	 * @Title: getRoom
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param building ： 楼
	 * @param:  @param floor ： 层
	 * @param:  @param room ： 房间
	 * @param:  @return
	 * @return: List<Map<String,Object>>  ： 房间数据集
	 * 		room		：	房间
	 *		spot_num	：	地点编码
	 *	    spot_name	：	地点名称
	 * @throws
	 */
	public static List<Map<String, Object>> getRoom(String building, String floor, String room){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("building", building);
		inInfo.set("floor", floor);
		inInfo.set("room", room);
		EiInfo outinfo = invoke(inInfo,"S_AC_FW_15");
		
		List<Map<String, Object>> list = new ArrayList<>();
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, Object>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 16.获取用户院区
	 * @Title: getUserGroups
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: List<Map<String, String>>  ： 院区信息
	 * 		id		：	ID
	 *		value	：	院区编码
	 *		text	：	院区名称
	 * @throws
	 */
	public static List<Map<String, String>> getUserGroups (String workNo) {
		List<Map<String, String>> list = new ArrayList<>();
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("workNo", workNo);
		EiInfo outinfo = invoke(inInfo,"S_AU_FW_01");
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, String>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 17.通过用户工号和院区编码获取科室
	 * @Title: getDeptByWorkNoAndUserGroup
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo : 工号
	 * @param:  @param dataGroupCode ： 院区编码
	 * @param:  @return
	 * @return: List<Map<String,String>>  ： 返回数据集
	 * 		dept_id				：	科室ID
	 *		dept_name			：	科室名称
	 *		hospital_district	：	院区编码
	 * @throws
	 */
	public static List<Map<String, String>> getDeptByWorkNoAndUserGroup (String workNo, String dataGroupCode) {
		List<Map<String, String>> list = new ArrayList<>();
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("workNo", workNo);
		inInfo.set("datagroupCode", dataGroupCode);
		EiInfo outinfo = invoke(inInfo,"S_AU_FW_02");
		
		if(outinfo != null){
			Object object = outinfo.get("result");
			if(object !=null){
				list = (List<Map<String, String>>) object;
			}
		}
		return list; 
	}
	
	/**
	 * 18.通过用户工号获取院区编码
	 * @Title: getUserGroupByWorkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo : 工号
	 * @param:  @return
	 * @return: String  ： 院区编码
	 * @throws
	 */
	public static String getUserGroupByWorkNo (String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("workNo", workNo);
		EiInfo outinfo = invoke(inInfo,"S_AU_FW_03");
		if(outinfo != null){
			return StringUtils.isBlank(outinfo.getString("datagroupCode")) ? "" : outinfo.getString("datagroupCode");
		} else {
			return "";
		}
	}
	
	/**
	 * 19.通过科室编码获取院区编码
	 * @Title: getUserGroupByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return
	 * @return: String  ： 账套信息
	 * @throws
	 */
	public static String getUserGroupByDeptNum (String deptNum) {
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("deptNum", deptNum);
		EiInfo outinfo = invoke(inInfo,"S_AU_FW_04");
		if(outinfo != null){
			return StringUtils.isBlank(outinfo.getString("datagroupCode")) ? "" : outinfo.getString("datagroupCode");
		} else {
			return "";
		}
	}
	
	/**
	 * 20.发送短信
	 * @Title: sendMsg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNoList ： 工号集合
	 * @param:  @param paramList ： 参数集合
	 * @param:  @param templateCode ： 短信模板编码
	 * @param:  @param hospital ： 院区编码
	 * @return: void  
	 * @throws
	 */
	public static boolean sendMsg(List<String> workNoList, List<String> paramList, String templateCode){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("workNoList", workNoList);
		inInfo.set("paramList", paramList);
		inInfo.set("templateCode", templateCode);
		EiInfo info = invoke(inInfo,"S_MC_FW_01");
		if(info == null){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 21.消息推送
	 * @Title: pushMsg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param userList : 用户信息集合
	 * @param:  @param paramList ： 参数集合
	 * @param:  @param templateCode ：  模板编码
	 * @param:  @param appCode ：App编码
	 * @param:  @param token ： token
	 * @param:  @param url : 消息跳转路径
	 * @return: void  
	 * @throws
	 */
	public static boolean pushMsg(List<String> workNoList, List<String> paramList, String templateCode, 
			String appCode, String url){
		EiInfo inInfo = new EiInfo(); 
		inInfo.set("workNoList", workNoList);
		inInfo.set("paramList", paramList);
		inInfo.set("templateCode", templateCode);
		inInfo.set("appCode", appCode);
		inInfo.set("url", url);
		EiInfo info = invoke(inInfo,"S_MC_FW_02");
		if(info == null){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 22.获取token
	 * @Title: getToken
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param appCode
	 * @param:  @return
	 * @return: String  
	 * @throws
	 */
	public static String getToken(String appCode) {
		EiInfo info = new EiInfo();
		info.set("appCode", appCode);
		EiInfo outInfo = invoke(info,"S_MC_FW_03");
		if(outInfo == null){
			return "";
		} else {
			return StringUtils.isBlank(outInfo.getString("token")) ? "" : outInfo.getString("token");
		}
	}
	
	/**
	 * 23.获取物资分类
	 * @Title: getMatType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		id:物资分类ID
	 *		parentId:父ID
	 *		matClassCode:物资分类编码
	 *		matClassName:物资分类名称
	 * @param:  @return
	 * 		id : 物资分类ID
	 *		matClassCode : 物资分类编码
	 *		matClassName : 物资分类名称
	 *		parentId : 物资分类父类ID
	 *		parentName : 物资分类父类名称
	 * @return: List<Map<String,String>>  
	 * @throws
	 */
	public static List<Map<String, String>> getMatType(Object param) {
		EiInfo outInfo = null;
		if(param instanceof EiInfo){
			outInfo = invoke((EiInfo)param,"S_AC_FW_18");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outInfo = invoke(inInfo,"S_AC_FW_18");
		}
		List<Map<String, String>> list = new ArrayList<>();
		if(outInfo != null){
			Object object = outInfo.get("result");
			if(object !=null){
				list = (List<Map<String, String>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 24.获取物资列表
	 * @Title: getMat
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		offset : 分页开始的索引
	 *		limit : 获取的数量
	 *		id : 物资ID
	 *		matCode : 物资编码
	 *		matName : 物资名称
	 *		matClassId : 物资分类ID
	 *		matClassCode : 物资分类编码
	 *		matClassName : 物资分类名称
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id : 物资ID
	 *		matCode : 物资编码
	 *		matName : 物资名称
	 *		matClassId : 物资分类ID
	 *		matClassCode : 物资分类编码
	 *		matClassName : 物资分类名称
	 *		matSpec : 物资规格
	 *		matModel : 物资型号
	 *		unit : 单位
	 *		price : 价格
	 *		supplier : 供应商
	 *		manufacturer : 制造商
	 *		matTypeCode : 物资大类编码
	 *		remark : 备注 
	 * @throws
	 */
	public static EiInfo getMat(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_17");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_17");
		}
		return outinfo;
	}
	
	/**
	 * 获取物资列表(无分页)
	 * @Title: getMatNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * @param:  @return
	 * @return: List<Map<String,String>>  
	 * @throws
	 */
	public static List<Map<String, String>> getMatNoPage(Object param) {
		List<Map<String, String>> list = new ArrayList<>();
		EiInfo mat = getMat(param);
		if(mat != null){
			Object object = mat.get("result");
			if(object !=null){
				list = (List<Map<String, String>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 获取物资列表(有分页)
	 * @Title: getMatPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param ： 参数对象
	 * @param:  @param resultId ： 返回Block的id
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo getMatPage(Object param, String resultId){
		EiInfo outinfo = new EiInfo();
		EiInfo mat = getMat(param);
		if(mat != null){
			if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
				return mat;
			} else {
				EiBlock block = mat.getBlock("result");
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
				outinfo.setBlocks(tempMap);
			}
		}
		return outinfo;
	}
	
	/**
	 * 25.获取供应商列表
	 * @Title: getSupplier
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * 		offset : 分页开始的索引
	 *		limit : 获取的数量
	 *		id : 供应商ID
	 *		supplierCode : 供应商编码
	 *		supplierName : 供应商名称
	 *		supplierType : 供应商类型
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id : 供应商ID
	 *		supplierCode : 供应商编码
	 *		supplierName : 供应商名称
	 *		supplierType : 供应商类型
	 *		contacts : 联系人
	 *		contactNumber : 联系电话
	 *		contactAddress : 联系地址
	 *		contactEmail : 联系邮箱
	 *		zipCode : 邮编
	 *		legalRepresentative : 法人代表
	 *		icrNo : 工商注册号
	 *		businessScope : 经营范围
	 *		abbreviation : 供应商简称
	 *		hrpCode : hrp编码
	 * @throws
	 */
	public static EiInfo getSupplier(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = invoke((EiInfo)param,"S_AC_FW_19");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = invoke(inInfo,"S_AC_FW_19");
		}
		return outinfo;
	}
	
	/**
	 * 获取供应商列表(无分页)
	 * @Title: getSupplierNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * @param:  @return
	 * @return: List<Map<String,String>>  
	 * @throws
	 */
	public static List<Map<String, String>> getSupplierNoPage(Object param) {
		List<Map<String, String>> list = new ArrayList<>();
		EiInfo supplier = getSupplier(param);
		if(supplier != null){
			Object object = supplier.get("result");
			if(object !=null){
				list = (List<Map<String, String>>) object;
			}
		}
		return list;
	}
	
	/**
	 * 获取供应商列表(有分页)
	 * @Title: getSupplierPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param
	 * @param:  @param resultId
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo getSupplierPage(Object param, String resultId){
		EiInfo outinfo = new EiInfo();
		EiInfo supplier = getSupplier(param);
		if(supplier != null){
			if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
				return supplier;
			} else {
				EiBlock block = supplier.getBlock("result");
				Map<String, EiBlock> tempMap = new HashMap<>();
				tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
				outinfo.setBlocks(tempMap);
			}
		}
		return outinfo;
	}
	
	/**
	 * 获取物资列表
	 * @Title: getMateriaAllPage
     * @Description: 获取物资列表(分页)
     * @param:  @return
     * @return: EiInfo
     * @throws
	 */
	public static EiInfo getMateriaAllPage(Object param, String resultId){
	    EiInfo outinfo = new EiInfo();
        EiInfo staff = getMateriaAll(param);
        if(staff != null){
            if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
                return staff;
            } else {
                EiBlock block = staff.getBlock("result");
                Map<String, EiBlock> tempMap = new HashMap<>();
                tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
                outinfo.setBlocks(tempMap);
            }
        }
        return outinfo;
	}

    private static EiInfo getMateriaAll(Object param) {
        EiInfo outinfo = null;
        if(param instanceof EiInfo){
            outinfo = invoke((EiInfo)param,"S_AC_FW_17");
        } else if (param instanceof Map) {
            EiInfo inInfo = new EiInfo();
            ((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
            outinfo = invoke(inInfo,"S_AC_FW_17");
        }
        return outinfo;
    }
	
	/**
	 * 获取供应商列表
     * @Title: getSupplier
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @return
     * @return: EiInfo 
     * @throws
     */
    public static EiInfo getSupplierAllPage(Object param, String resultId){
        EiInfo outinfo = new EiInfo();
        EiInfo staff = getSupplierAll(param);
        if(staff != null){
            if(StringUtils.isBlank(resultId) || "result".equals(resultId)){
                return staff;
            } else {
                EiBlock block = staff.getBlock("result");
                Map<String, EiBlock> tempMap = new HashMap<>();
                tempMap.put(resultId, block == null ? new EiBlock(resultId) : block);
                outinfo.setBlocks(tempMap);
            }
        }
        return outinfo;
    }

    private static EiInfo getSupplierAll(Object param) {
        EiInfo outinfo = null;
        if(param instanceof EiInfo){
            outinfo = invoke((EiInfo)param,"S_AC_FW_19");
        } else if (param instanceof Map) {
            EiInfo inInfo = new EiInfo();
            ((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
            outinfo = invoke(inInfo,"S_AC_FW_19");
        }
        return outinfo;
    }

}
