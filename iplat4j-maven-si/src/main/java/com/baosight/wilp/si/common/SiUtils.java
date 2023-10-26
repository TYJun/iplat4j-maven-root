package com.baosight.wilp.si.common;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 备件库存模块工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  SiUtils.java
 * @ClassName:  SiUtils
 * @Package com.baosight.wilp.si.common
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午4:32:45 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class SiUtils {

	private static String defaultBlankValue = "";
	private static String positiveFlag1 = "true";
	private static String positiveFlag2 = "Y";
	private static Pattern pattern = Pattern.compile("^-?[0-9]+.?[0-9]*$");

	/**
	 * 获取院区
	 *
	 * @throws
	 * @Title: getDataGroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param workNo ： 工号
	 * @param: @return
	 * @return: String   ： 院区
	 */
	public static String getDataGroupCode(String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		//调用微服务S_AU_FW_03
		String userGroup = BaseDockingUtils.getUserGroupByWorkNo(workNo);
		return StringUtils.isBlank(userGroup) ? "" : userGroup;
	}

	/**
	 * 获取用户信息
	 *
	 * @throws
	 * @Title: getUserInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param workNo ： 工号
	 * @param: @return
	 * @return: Map<String, Object>
	 * id			：	员工ID
	 * workNo		：	员工工号
	 * name		：	员工姓名
	 * gender		：	员工性别
	 * contactTel	：	联系电话
	 * deptId		:	科室ID
	 * deptNum		:	科室编码
	 * deptName	:	科室名称
	 * dataGroupCode : 院区
	 */
	public static Map<String, Object> getUserInfo(String workNo) {
		//获取用户信息
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		//调用微服务S_AC_FW_02
		Map<String, Object> map = BaseDockingUtils.getStaffByWorkNo(workNo);
		//获取用户院区
		map.put("dataGroupCode", getDataGroupCode(workNo));
		return map;
	}

	/**
	 * 执行本地服务调用
	 *
	 * @throws
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo ： inInfo对象
	 * @param: @param serviceName ： 服务名
	 * @param: @param methodName ： 方法名
	 * @param: @param paramNames ： 参数名数组
	 * @param: @param params：参数
	 * @param: @return
	 * @return: EiInfo  ： 返回inInfo对象
	 */
	public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName, String[] paramNames, Object... params) {
		EiInfo info = inInfo != null ? inInfo : new EiInfo();
		//参数赋值
		if (paramNames != null) {
			for (int i = 0; i < paramNames.length; i++) {
				info.set(paramNames[i], params[i]);
			}
		}
		//执行方法
		info.set(EiConstant.serviceName, serviceName);
		info.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(info);
		return outInfo;
	}

	/**
	 * 微服务调用
	 *
	 * @param inInfo    inInfo : 参数EiInfo
	 * @param serviceId serviceId : 微服务ID
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 * @Title: invoke
	 **/
	public static EiInfo invokeRemote(EiInfo inInfo, String serviceId) {
		inInfo.set(EiConstant.serviceId, serviceId);
		EiInfo outInfo = XServiceManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 微服务调用
	 *
	 * @param serviceId  serviceId
	 * @param paramNames paramNames
	 * @param params     params
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 * @Title: invokeRemote
	 **/
	public static EiInfo invokeRemote(String serviceId, List<String> paramNames, Object... params) {
		EiInfo eiInfo = new EiInfo();
		//参数赋值
		if (paramNames != null) {
			for (int i = 0; i < paramNames.size(); i++) {
				eiInfo.set(paramNames.get(i), params[i]);
			}
		}
		return invokeRemote(eiInfo, serviceId);
	}


	/**
	 * @Title: invoke
	 * @Description: TODO(本地服务调用)
	 * @param inInfo inInfo : 参数EiInfo
	 * @param serviceName serviceName : 服务名
	 * @param methodName methodName : 方法名
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo invoke (EiInfo inInfo, String serviceName, String methodName) {
		inInfo.set(EiConstant.serviceName, serviceName);
		inInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 获取物资信息
	 *
	 * @throws
	 * @Title: getMat
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param map
	 * @param: @return
	 * @return: EiInfo
	 */
	@SuppressWarnings("all")
	public static EiInfo getMat(Map<String, Object> map) {
		//调用微服务接口S_AC_FW_17查物资信息
		map.put("matTypeCode", WareHouseDataSplitUtils.getWareHouseMatRootType(com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName()));
		EiInfo info = BaseDockingUtils.getMatPage(map, "result");
		EiBlock block = info.getBlock("result");
		if (block == null) {
			info.addBlock(new EiBlock("result"));
			return info;
		}
		if (block.getRowCount() > 0) {
			List<Map> rows = block.getRows();
			rows.forEach(pMap -> {
				pMap.put("matNum", pMap.get("matCode"));
				pMap.put("matTypeNum", pMap.get("matClassCode"));
				pMap.put("matTypeName", pMap.get("matClassName"));
				pMap.put("unitName", CommonUtils.getDataCodeItemName("wilp.ac.gm.unit",
						pMap.get("unit") == null ? "" : pMap.get("unit").toString()));
			});
		}
		return info;
	}


	public static EiInfo getMatInformation(Map<String, Object> map) {
		//调用微服务接口S_AC_FW_17查物资信息
		map.put("matTypeCode", WareHouseDataSplitUtils.getWareHouseMatRootType(com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName()));
		EiInfo info = BaseDockingUtils.getMatPage(map, "mat");
		EiBlock block = info.getBlock("mat");
		if (block == null) {
			info.addBlock(new EiBlock("mat"));
			return info;
		}
		if (block.getRowCount() > 0) {
			List<Map> rows = block.getRows();
			rows.forEach(pMap -> {
				pMap.put("matNum", pMap.get("matCode"));
				pMap.put("matTypeNum", pMap.get("matClassCode"));
				pMap.put("matTypeName", pMap.get("matClassName"));
				pMap.put("unitName", CommonUtils.getDataCodeItemName("wilp.ac.gm.unit",
						pMap.get("unit") == null ? "" : pMap.get("unit").toString()));
			});
		}
		return info;
	}
	/**
	 * 获取物资分类树
	 *
	 * @throws
	 * @Title: getMatTypeTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param map
	 * @param: @return
	 * @return: EiInfo
	 * id ： 当前节点的id
	 * text : 分类名称
	 * pId ： 上级节点的id
	 * leaf : 是否有子节点
	 */
	public static List<Map<String, String>> getMatTypeTree(Map<String, String> map) {
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> list = BaseDockingUtils.getMatType(map);
		List<Map<String, String>> treeList = new ArrayList<>();
		list.forEach(pMap -> {
			Map<String, String> rMap = new HashMap<>();
			rMap.put("label", pMap.get("id"));
			rMap.put("text", pMap.get("matClassName"));
			rMap.put("matTypeNum", pMap.get("matClassCode"));
			rMap.put("pId", StringUtils.isBlank(pMap.get("parentId")) ? "root" : pMap.get("parentId"));
			rMap.put("leaf", "");
			treeList.add(rMap);
		});
		return treeList;
	}

	/**
	 * 获取科室信息
	 *
	 * @throws
	 * @Title: getDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param map
	 * offset	  		:分页开始的索引
	 * limit		    :获取的数量
	 * deptId			:	科室ID
	 * deptNum			:	科室编码
	 * deptName		:	科室名称
	 * datagroupCode	:	院区编码
	 * @param: @return
	 * @return: EiInfo
	 * id				:	科室ID
	 * deptNum			:	科室编号
	 * deptName		:	科室名称
	 * parentId		:	父级ID
	 * parentDeptName	:	父级科室名称
	 * deptDescribe	:	科室描述
	 * type			:	科室类型
	 * isService		:	是否是服务科室
	 * status			:	科室状态
	 * isStop			:	是否停用
	 */
	public static EiInfo getDept(Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		//调用微服务接口S_AC_FW_05查询科室信息
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}

	/**
	 * 递归物资分类，构建字符串
	 *
	 * @throws
	 * @Title: getMatTypeStrs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param string ： 物资分类Id
	 * @param: @return
	 * @return: String  : 物资分类编码字符串
	 */
	public static String getMatTypeStrs(String matTypeNum) {
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> list = BaseDockingUtils.getMatType(new EiInfo());
		if (StringUtils.isBlank(matTypeNum)) {
			return "";
		}
		List<String> matTypeNums = new ArrayList<>();
		//获取当前分类id对应的编码
		list.forEach(map -> {
			if (matTypeNum.equals(map.get("id"))) {
				matTypeNums.add(map.get("matClassCode"));
			}
		});
		recursionMatType(matTypeNums, matTypeNum, list);
		return StringUtils.join(matTypeNums.toArray(), ",");
	}

	//递归
	private static void recursionMatType(List<String> matTypeNums, String id, List<Map<String, String>> list) {
		int endFlag = 0;
		for (Map<String, String> map : list) {
			if (id.equals(map.get("parentId"))) {
				endFlag = endFlag + 1;
				matTypeNums.add(map.get("matClassCode"));
				recursionMatType(matTypeNums, map.get("id"), list);
			}
		}
		if (endFlag > 0) {
			return;
		}
	}

	/**
	 * 获取指定物资信息
	 *
	 * @throws
	 * @Title: getMatByMatNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param matNum ： 物资编码
	 * @param: @return
	 * @return: Map<String, String>
	 * id : 物资ID
	 * matCode : 物资编码
	 * matName : 物资名称
	 * matClassId : 物资分类ID
	 * matClassCode : 物资分类编码
	 * matClassName : 物资分类名称
	 * matSpec : 物资规格
	 * matModel : 物资型号
	 * unit : 单位
	 * price : 价格
	 * supplier : 供应商
	 * manufacturer : 制造商
	 * matTypeCode : 物资大类编码
	 * remark : 备注
	 */
	public static Map<String, String> getMatByMatNum(String matNum) {
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("matCode", matNum);
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> matList = BaseDockingUtils.getMatNoPage(pMap);
		if (matList == null || matList.size() == 0) {
			return new HashMap<>();
		} else {
			return matList.get(0);
		}
	}

	/**
	 * 数学计算
	 *
	 * @throws
	 * @Title: cal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param num1 ： 数字一
	 * @param: @param num2 ： 数字二
	 * @param: @param symbol ： 计算符号
	 * @return: Double  ： 计算结果
	 */
	public static Double cal(Object num1, Object num2, String symbol) {
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal decimal1 = NumberUtils.toBigDecimal(num1, BigDecimal.ZERO);
		BigDecimal decimal2 = NumberUtils.toBigDecimal(num2, BigDecimal.ZERO);
		//数据计算
		switch (symbol) {
			case SiConstant.MATH_ADD:
				result = decimal1.add(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
				break;
			case SiConstant.MATH_SUB:
				result = decimal1.subtract(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
				break;
			case SiConstant.MATH_MULTI:
				result = decimal1.multiply(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
				break;
			case SiConstant.MATH_DIVIDE:
				result = decimal2.equals(0) ? BigDecimal.ZERO : decimal1.divide(decimal2, 2, BigDecimal.ROUND_HALF_UP);
				break;
			default:
				result = BigDecimal.ZERO;
		}
		return result.doubleValue();
	}

	/**
	 * 处理调用方法的返回结果
	 *
	 * @throws
	 * @Title: dealOutInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo ： 当前方法的EiInfo
	 * @param: @param outInfo : 调用接口的EiInfo
	 * @param: @param msg : 错误msg
	 * @param: @return
	 * @return: boolean
	 */
	public static boolean dealOutInfo(EiInfo inInfo, EiInfo outInfo, String msg) {
		boolean result = false;
		if (outInfo.getStatus() == -1) {
			result = true;
			inInfo.setStatus(-1);
			inInfo.setMsg(StringUtils.isBlank(outInfo.getMsg()) ? msg : outInfo.getMsg());
		}
		return result;
	}

	/**
	 * 空处理
	 *
	 * @throws
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param object ： 参数
	 * @param: @return
	 * @return: String  ： 参数字符串
	 */
	public static String isEmpty(Object object) {
		if (object == null) {
			return "";
		}
		if (StringUtils.isBlank(object.toString())) {
			return "";
		}
		return object.toString();
	}

	/**
	 * 空处理
	 *
	 * @throws
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param object ： 参数
	 * @param: @param defultValue ： 默认值
	 * @param: @return
	 * @return: String  ： 参数字符串
	 */
	public static String isEmpty(Object object, String defaultValue) {
		if (StringUtils.isBlank(defaultValue)) {
			 defaultValue = defaultBlankValue;
		}
		if (object == null) {
			return defaultValue;
		}
		if (StringUtils.isBlank(object.toString())) {
			return defaultValue;
		}
		return object.toString();
	}

	/**
	 * 将对象转成保留两位小数的数字字符串
	 * @Title: toNumberString
	 * @param object object
	 * @param defaultValue defaultValue
	 * @return java.lang.String
	 * @throws
	 **/
	public static String toNumberString(Object object, String defaultValue) {
		String numberString = isEmpty(object, defaultValue);
		return new BigDecimal(numberString).setScale(2).toString();
	}

	/**
	 * 将object转成List
	 *
	 * @param object object
	 * @return java.util.Collection<? extends java.lang.Object>
	 * @throws
	 * @Title: toList
	 **/
	public static <T> List<T> toList(Object object, Class<T> clazz) {
		if (object == null) {
			return new ArrayList<>();
		}
		try {
			return JSON.parseArray(JSON.toJSONString(object), clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * 将字符串转成Date
	 *
	 * @param date date
	 * @return java.util.Date
	 * @throws
	 * @Title: toDate
	 **/
	public static Date toDate(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return date.length() == 10 ? DateUtils.toDate(date) : DateUtils.toDateTime(date);
	}

	/**
	 * 将对象转成boolean
	 *
	 * @param obj obj
	 * @return java.lang.Boolean
	 * @throws
	 * @Title: toBoolean
	 **/
	public static Boolean toBoolean(Object obj) {
		String objStr = isEmpty(obj);
		if (StringUtils.isBlank(objStr)) {
			return false;
		}
		if (positiveFlag1.equals(objStr) || positiveFlag2.equals(objStr)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一级模块是否存在
	 *
	 * @param moduleCode moduleCode : 一级模块编码
	 * @return boolean
	 * @throws
	 * @Title: isExistModule
	 * @Description: 判断一级模块是否存在
	 **/
	public static boolean isExistModule(String moduleCode) {
		//设置参数
		EiInfo eiInfo = new EiInfo();
		String serviceId = "S_ED_31";
		eiInfo.set(EiConstant.serviceId, serviceId);
		eiInfo.set("moduleEnames1", moduleCode);
		//调用接口
		EiInfo outInfo = XServiceManager.call(eiInfo);
		//status=1模块存在 status=0模块不存在
		int status = outInfo.getStatus();
		return status > 0 ? true : false;
	}

	/**
	 * 判断微服务是否存在
	 *
	 * @param serviceId serviceId : 微服务标识
	 * @return boolean
	 * @throws
	 * @Title: isExistService
	 **/
	public static boolean isExistService(String serviceId) {
		//准备传入参数
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId, "S_ED_08");
		eiInfo.set("service_id", serviceId);
		//调用接口
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return outInfo.getStatus() > -1 && outInfo.getInt("totalCount") > 0;
	}

	/**
	 * 页面日期查询初始赋值
	 *
	 * @param inInfo  inInfo : 参数EiInfo
	 * @param isMonth isMonth : 是否是月初到月末,不是,则为近一个日期
	 * @return void
	 * @throws
	 * @Title: initQueryTime
	 **/
	public static void initQueryTime(EiInfo inInfo, boolean isMonth) {
		Calendar cal = Calendar.getInstance();
		if (isMonth) {
			inInfo.set("inqu_status-0-endTime", DateUtils.toDateStr10(DateUtils.getLastDayInMonth(cal.getTime())));
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			inInfo.set("inqu_status-0-endTime", DateUtils.curDateStr10());
			cal.add(Calendar.MONTH, -1);
		}
		inInfo.set("inqu_status-0-beginTime", DateUtils.toDateStr10(cal.getTime()));
	}

	/**
	 * 判断是否是数字
	 * @Title: isNumeric
	 * @param obj obj
	 * @return boolean
	 * @throws
	 **/
	public static boolean isNumeric(Object obj) {
		String objStr = isEmpty(obj);
		if(StringUtils.isBlank(objStr)) {
			return false;
		}
		return pattern.matcher(objStr).matches();
	}
}


