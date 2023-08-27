package com.baosight.wilp.it.utils;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 第一段
 * 创建独立任务编号工具类.
 * 1.通过操作类型生成编号方法.
 * 2.获取当前登录账号的账套.
 * 第二段
 * 通过枚举类中定义的操作类型生成编号方法.
 * 获取当前登录账号的账套.
 * 第三段
 * @author zhaowei
 * @version V1.0
 * @date 2022年08月03日 11:07
 */
public class CreateNum {
	// 注入dao
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	/*
	 * 第一段
	 * 通过操作类型生成编号方法.
	 * 第二段
	 * 1.获取当前时间，进行时间的格式转换.
	 * 2.通过类型拼接当前日期的初始单号.
	 * 3.根据枚举类操作类型进入不同的分支进行编号的更新.
	 * 3.1.独立分类分支.
	 * 3.2.独立登记分支.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/16 10:54
	 * @param code
	 * @return java.lang.String
	 * @version V1.0
	 */
	public static String CreateNumByType(String code) {
		/*
		 * 1.获取当前时间，进行时间的格式转换
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
		String format = dateFormat.format(new Date());
		/*
		 * 2.通过类型拼接当前日期的初始单号
		 */
		String createNum = code + format + "001";
		List<Map<String, Object>> maxCode;
		/*
		 * 3.根据枚举类操作类型进入不同的分支进行编号的更新
		 */
		switch (code) {
			/*
			 * 3.1.独立分类分支
			 * 通过当前日期进行数据库查询操作
			 * 如果不存在最大单号则进行拼接
			 * 如果存在最大单号则进行数据切割再拼接
			 */
			case "FL":
				maxCode = dao.query("ITFL01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					createNum = "ITFL" + format + "001";
					break;
				} else {
					String num = (String) maxCode.get(0).get("num");
					String substring = num.substring(12);
					createNum = "ITFL" + format + String.format("%03d", Integer.valueOf(substring) + 1);
				}
				break;
			/*
			 * 3.2.独立登记分支
			 * 通过当前日期进行数据库查询操作
			 * 如果不存在最大单号则进行拼接
			 * 如果存在最大单号则进行数据切割再拼接
			 */
			case "DJ":
				maxCode = dao.query("ITDJ01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					createNum = "IT" + format + "001";
					break;
				} else {
					String num = (String) maxCode.get(0).get("num");
					String substring = num.substring(10);
					createNum = "IT" + format + String.format("%03d", Integer.valueOf(substring) + 1);
				}
				break;
		}
		return createNum;
	}

	/**
	 * 第一段
	 * 获取当前登录账号的账套.
	 * 第二段
	 * 1.通过当前登录账号获取账套.
	 * 第三段
	 * @author zhaowei
	 * @return java.lang.String
	 * @date 2022/8/16 16:53
	 * @version V1.0
	 */
	public static String getDataGroupCode(String workNo) {
		/*
		 * 1.通过当前登录账号获取账套
		 * 不为空则获取账套信息
		 */
		if(StringUtils.isNotEmpty(workNo)){
			return StringUtils.isNotEmpty(BaseDockingUtils.getUserGroupByWorkNo(workNo)) ? BaseDockingUtils.getUserGroupByWorkNo(workNo) : "";
		}else {
			return StringUtils.isNotEmpty(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getUser().getUsername())) ? BaseDockingUtils.getUserGroupByWorkNo(UserSession.getUser().getUsername()) : "";
		}
	}

	/**
	 * 第一段
	 * 获取当前登录账号的账套.
	 * 第二段
	 * 1.通过当前登录账号获取拥有的全部账套.
	 * 第三段
	 * @author zhaowei
	 * @return java.util.List<java.lang.String>
	 * @date 2022/8/17 11:00
	 * @version V1.0
	 */
	public static List<String> getDataGroupCodeAll(String workNo) {
		/*
		 * 1.通过当前登录账号获取拥有的全部账套.
		 * 调用获取用户院区方法
		 * 获取该账号拥有的所有院区信息并返回
		 */
		if(StringUtils.isNotEmpty(workNo)){
			List<Map<String, String>> userGroups = BaseDockingUtils.getUserGroups(workNo);
			List<String> userGroupsList = new ArrayList<>();
			for (Map<String, String> userGroupsMap : userGroups) {
				userGroupsList.add(userGroupsMap.get("value"));
			}
			if (CollectionUtils.isEmpty(userGroups)){
				return null;
			}
			return userGroupsList;
		}else {
			List<Map<String, String>> userGroups = BaseDockingUtils.getUserGroups(UserSession.getUser().getUsername());
			List<String> userGroupsList = new ArrayList<>();
			for (Map<String, String> userGroupsMap : userGroups) {
				userGroupsList.add(userGroupsMap.get("value"));
			}
			if (CollectionUtils.isEmpty(userGroups)){
				return null;
			}
			return userGroupsList;
		}

	}

	/**
	 * 第一段
	 * 获取账套为全部的所有人员信息.
	 * 第二段
	 * 1.构建一个list集合存储所有数据.
	 * 2.获取当前用户拥有的所有院区信息，
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 13:45
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @version V1.0
	 */
	public static List<Map<String, Object>> queryPersonnelListByDataGroupCodeAll(Map<String, Object> map) {
		/*
		 * 1.构建一个list集合存储所有数据
		 */
		List<Map<String,Object>> queryPersonnelListByDataGroupCodeAll = new ArrayList<>();
		/*
		 * 2.获取当前用户拥有的所有院区信息
		 * 取出其中的账套信息，调用通过账套查询该账套院区下所有员工信息方法
		 * 存入构建的集合中并返回
		 */
		List<Map<String, String>> userGroups = BaseDockingUtils.getUserGroups(UserSession.getUser().getUsername());
		for (Map<String, String> dataGroupCode : userGroups) {
			map.put("dataGroupCode", dataGroupCode.get("value"));
			map.put("datagroupCode", dataGroupCode.get("value"));
			List<Map<String, Object>> personnelListByDataGroupCode = queryPersonnelListByDataGroupCode(map);
			queryPersonnelListByDataGroupCodeAll.addAll(personnelListByDataGroupCode);
		}
		return queryPersonnelListByDataGroupCodeAll;
	}

	/**
	 * 第一段
	 * 通过账套查询该账套院区下所有员工信息.
	 * 第二段
	 * 1.构建参数作为查询条件，再调用获取所有员工信息接口.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 13:48
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @version V1.0
	 */
	public static List<Map<String, Object>> queryPersonnelListByDataGroupCode(Map<String, Object> map) {
		/*
		 * 1.构建参数作为查询条件，再调用获取所有员工信息接口
		 */
		map.put("workNo", map.get("reqStaffId"));
		map.put("userName", map.get("reqStaffName"));
		List<Map<String, Object>> list = BaseDockingUtils.getStaffAllNoPage(map);
		return list;
	}

	/**
	 * 第一段
	 * 获取账套为全部的所有科室和地点信息.
	 * 第二段
	 * 1.构建一个list集合存储所有数据.
	 * 2.获取当前用户拥有的所有院区信息.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 14:36
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @version V1.0
	 */
	public static List<Map<String,Object>> getSpotAndDeptByPhoneByDataGroupCodeAll(Map<String, Object> map){
		/*
		 * 1.构建一个list集合存储所有数据
		 */
		List<Map<String,Object>> getSpotAndDeptByPhoneByDataGroupCodeAll = new ArrayList<>();
		/*
		 * 2.获取当前用户拥有的所有院区信息
		 * 取出其中的账套信息，调用通过账套查询该账套院区下所有科室和地点信息方法
		 * 存入构建的集合中并返回
		 */
		List<Map<String, String>> userGroups = BaseDockingUtils.getUserGroups(UserSession.getUser().getUsername());
		for (Map<String, String> dataGroupCode : userGroups) {
			map.put("dataGroupCode", dataGroupCode.get("value"));
			map.put("datagroupCode", dataGroupCode.get("value"));
			List<Map<String, Object>> spotAndDeptByPhoneByDataGroupCode = getSpotAndDeptByPhoneByDataGroupCode(map);
			getSpotAndDeptByPhoneByDataGroupCodeAll.addAll(spotAndDeptByPhoneByDataGroupCode);
		}
		return getSpotAndDeptByPhoneByDataGroupCodeAll;
	}

	/**
	 * 第一段
	 * 通过电话获取科室和地点信息方法.
	 * 第二段
	 * 1.调用微服务S_AC_FW_10通过电话获取科室和地点信息.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 14:37
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @version V1.0
	 */
	public static List<Map<String,Object>> getSpotAndDeptByPhoneByDataGroupCode(Map<String, Object> map){
		/*
		 * 1.调用微服务S_AC_FW_10通过电话获取科室和地点信息
		 */
		List<Map<String, Object>> phoneList = BaseDockingUtils.getSpotAndDeptByPhone(map);
		return phoneList;
	}

	/**
	 * 第一段
	 * 获取所有科室信息方法.
	 * 第二段
	 * 1.构建一个list集合存储所有数据.
	 * 2.获取当前用户拥有的所有院区信息.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 14:45
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 */
	public static List<Map<String,Object>> getDeptAllNoPageByDataGroupCodeAll(Map<String, Object> map){
		/*
		 * 1.构建一个list集合存储所有数据
		 */
		List<Map<String,Object>> getDeptAllNoPageByDataGroupCodeAll = new ArrayList<>();
		/*
		 * 2.获取当前用户拥有的所有院区信息
		 * 取出其中的账套信息，调用通过账套查询该账套院区下所有科室信息
		 * 存入构建的集合中并返回
		 */
		List<Map<String, String>> userGroups = BaseDockingUtils.getUserGroups(UserSession.getUser().getUsername());
		for (Map<String, String> dataGroupCode : userGroups) {
			map.put("dataGroupCode", dataGroupCode.get("value"));
			map.put("datagroupCode", dataGroupCode.get("value"));
			List<Map<String, Object>> deptAllNoPageByDataGroupCode = getDeptAllNoPageByDataGroupCode(map);
			getDeptAllNoPageByDataGroupCodeAll.addAll(deptAllNoPageByDataGroupCode);
		}
		return getDeptAllNoPageByDataGroupCodeAll;
	}

	/**
	 * 第一段
	 * 查询科室信息方法.
	 * 第二段
	 * 1.调用微服务接口S_AC_FW_05查询科室信息.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/17 14:41
	 * @param map
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @version V1.0
	 */
	public static List<Map<String,Object>> getDeptAllNoPageByDataGroupCode(Map<String, Object> map){
		/*
		 * 1.调用微服务接口S_AC_FW_05查询科室信息
		 */
		List<Map<String, Object>> deptList = BaseDockingUtils.getDeptAllNoPage(map);
		return deptList;
	}
}
