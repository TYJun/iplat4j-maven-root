package com.baosight.wilp.ci.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.apache.commons.collections.CollectionUtils;
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
 * @Title:  CiUtils.java
 * @ClassName:  CiUtils
 * @Package com.baosight.wilp.ci.common
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午4:32:45 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class CiUtils {

	private static String defaultBlankValue = "";
	
	/**
	 * 获取院区
	 * @Title: getDataGroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: String   ： 院区
	 * @throws
	 */
	public static String getDataGroupCode (String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		//调用微服务S_AU_FW_03
		String userGroup = BaseDockingUtils.getUserGroupByWorkNo(workNo);
		return StringUtils.isBlank(userGroup) ? "" : userGroup;
	}
	
	/**
	 * 获取用户信息
	 * @Title: getUserInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: Map<String,Object> 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		dataGroupCode : 院区
	 * @throws
	 */
	public static Map<String, Object> getUserInfo (String workNo) {
		//获取用户信息
    	workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
    	//调用微服务S_AC_FW_02
    	Map<String,Object> map = BaseDockingUtils.getStaffByWorkNo(workNo);
    	//获取用户院区
    	map.put("dataGroupCode", getDataGroupCode(workNo));
		System.out.println("map++"+map);
    	return map;
	}
	
	/**
	 * 执行本地服务调用
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： inInfo对象
	 * @param:  @param serviceName ： 服务名
	 * @param:  @param methodName ： 方法名
	 * @param:  @param paramNames ： 参数名数组
	 * @param:  @param params：参数
	 * @param:  @return
	 * @return: EiInfo  ： 返回inInfo对象
	 * @throws
	 */
	public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName,String[] paramNames, Object... params) {
		EiInfo info = null;
		if(inInfo != null){
			info = inInfo;
		} else {
			info = new EiInfo();
		}
		//参数赋值
		if(paramNames != null){
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
	 * 获取物资信息
	 * @Title: getMat
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("all")
	public static EiInfo getMat(Map<String, Object> map) {
		//调用微服务接口S_AC_FW_17查物资信息
		EiInfo info = BaseDockingUtils.getMatPage(map, "result");
		EiBlock block = info.getBlock("result");
		if(block == null){
			info.addBlock(new EiBlock("result"));
			return info;
		}
		if(block.getRowCount() > 0) {
			List<Map> rows = block.getRows();
			rows.forEach(pMap-> {
				pMap.put("matNum", pMap.get("matCode"));
				pMap.put("matTypeName", pMap.get("matClassName"));
				pMap.put("unitName", CommonUtils.getDataCodeItemName("wilp.ac.gm.unit", 
						pMap.get("unit") == null ? "" : pMap.get("unit").toString()));
				pMap.put("supplierCode", getSupplierByName((String) pMap.get("supplier")));
			});
		}
		return info;
	}


	public static Map<String, Object> getMatByCode(Map<String, Object> map) {
		//调用微服务接口S_AC_FW_17查物资信息
		EiInfo info = BaseDockingUtils.getMatPage(map, "result");
		List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) info.getAttr().get("result");

		if(CollectionUtils.isEmpty(resultList)){
			return null;
		}else {
			HashMap<String, Object> attr = resultList.get(0);
			attr.put("supplierCode", getSupplierByName((String) attr.get("supplier")));
			return attr;
		}

	}

	/**
	 * 根据供应商姓名查询供应商编码
	 * @Title  supplierName
	 * @author liu
	 * @param supplier
	 * @return java.lang.String
	 */
	public static String getSupplierByName(String supplier){

		Map<String, Object> map = new HashMap<>();
		map.put("supplierName",supplier);

		//查询供应商
		EiInfo supplierInfo = BaseDockingUtils.getSupplier(map);
		List<HashMap<String,Object >> list = (List<HashMap<String, Object>>) supplierInfo.get("result");

		if(CollectionUtils.isEmpty(list)){
			return null;
		}else {
			return (String) list.get(0).get("supplierCode");
		}
	}
	/**
	 * 获取物资分类树
	 * @Title: getMatTypeTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id ： 当前节点的id
	 *		text : 分类名称
	 *		pId ： 上级节点的id
	 *		leaf : 是否有子节点 
	 * @throws
	 */
	public static List<Map<String, String>> getMatTypeTree(Map<String, String> map){
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> list = BaseDockingUtils.getMatType(map);
		List<Map<String, String>> treeList = new ArrayList<>();
		list.forEach(pMap -> {
			Map<String, String> rMap = new HashMap<>();
			rMap.put("label", pMap.get("id"));
			rMap.put("text", pMap.get("matClassName"));
			rMap.put("pId", StringUtils.isBlank(pMap.get("parentId")) ? "root" : pMap.get("parentId"));
			rMap.put("leaf", "");
			treeList.add(rMap);
		});
		return treeList;
	}
	
	/**
	 * 获取科室信息
	 * @Title: getDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
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
	public static EiInfo getDept(Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		//调用微服务接口S_AC_FW_05查询科室信息
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}

	/**
	 * 递归物资分类，构建字符串
	 * @Title: getMatTypeStrs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param string ： 物资分类Id
	 * @param:  @return
	 * @return: String  : 物资分类编码字符串
	 * @throws
	 */
	public static String getMatTypeStrs(String matTypeNum) {
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> list = BaseDockingUtils.getMatType(new EiInfo());
		if(StringUtils.isBlank(matTypeNum)){
			return "";
		}
		List<String> matTypeNums = new ArrayList<>();
		//获取当前分类id对应的编码
		list.forEach(map -> {
			if(matTypeNum.equals(map.get("id"))){
				matTypeNums.add(map.get("matClassCode"));
			}
		});
		recursionMatType(matTypeNums,matTypeNum,list);
		return StringUtils.join(matTypeNums.toArray(),",");
	}

	//递归
	private static void recursionMatType(List<String> matTypeNums, String id, List<Map<String, String>> list) {
		int endFlag = 0;
		for (Map<String, String> map : list) {
			if(id.equals(map.get("parentId"))){
				endFlag = endFlag + 1;
				matTypeNums.add(map.get("matClassCode"));
				recursionMatType(matTypeNums,map.get("id"),list);
			}
		}
		if(endFlag > 0){
			return;
		}
	}

	/**
	 * 获取指定物资信息
	 * @Title: getMatByMatNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param matNum ： 物资编码
	 * @param:  @return
	 * @return: Map<String,String>  
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
	public static Map<String, String> getMatByMatNum(String matNum) {
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("matCode",matNum);
		//调用微服务接口S_AC_FW_18查询物资分类信息
		List<Map<String, String>> matList = BaseDockingUtils.getMatNoPage(pMap);
		if(matList == null || matList.size() == 0){
			return new HashMap<>();
		} else {
			return matList.get(0);
		}
	}
	
	/**
	 * 数学计算
	 * @Title: cal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param num1 ： 数字一
	 * @param:  @param num2 ： 数字二
	 * @param:  @param symbol ： 计算符号
	 * @return: Double  ： 计算结果
	 * @throws
	 */
	public static Double cal (Object num1, Object num2, String symbol) {
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal decimal1 = NumberUtils.toBigDecimal(num1);
		BigDecimal decimal2 = NumberUtils.toBigDecimal(num2);
		if("add".equals(symbol)){
			result = decimal1.add(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if ("subtract".equals(symbol)) {
			result = decimal1.subtract(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if ("multiply".equals(symbol)) {
			result = decimal1.multiply(decimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if ("divide".equals(symbol)) {
			result = decimal2.equals(0) ? BigDecimal.ZERO : decimal1.divide(decimal2, 2, BigDecimal.ROUND_HALF_UP);
		} else {
			result = BigDecimal.ZERO;
		}
		return result.doubleValue();
	}
	
	/**
	 * 处理调用方法的返回结果
	 * @Title: dealOutInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： 当前方法的EiInfo
	 * @param:  @param outInfo : 调用接口的EiInfo
	 * @param:  @param msg : 错误msg
	 * @param:  @return
	 * @return: boolean  
	 * @throws
	 */
	public static boolean dealOutInfo(EiInfo inInfo, EiInfo outInfo, String msg) {
		boolean result = false;
		if(outInfo.getStatus() == -1){
			result = true;
			inInfo.setStatus(-1);
			inInfo.setMsg(StringUtils.isBlank(outInfo.getMsg()) ? msg : outInfo.getMsg());
		}
		return result;
	}

	/**
	 * 空处理
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String isEmpty(Object object) {
		if(object == null){
			return "";
		}
		if(StringUtils.isBlank(object.toString())){
			return "";
		}
		return object.toString();
	}

	/**
	 * 空处理
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @param defultValue ： 默认值
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String isEmpty(Object object, String defultValue) {
		if(StringUtils.isNotBlank(defultValue)){
			defaultBlankValue = defultValue;
		}
		if(object == null){
			return defaultBlankValue;
		}
		if(StringUtils.isBlank(object.toString())){
			return defaultBlankValue;
		}
		return object.toString();
	}

	public static List<DaoEPBase> getCanteen() {

		List<DaoEPBase> canteenList = new ArrayList<>();

		EiInfo info = new EiInfo();
		info.set(EiConstant.serviceId, "S_SS_ST_02");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataGroupCode",null);

		//病患食堂
		paramMap.put("canteenTypeNum","bhst");
		info.set("paramObject", paramMap);
		info = XServiceManager.call(info);
		List<DaoEPBase> canteenBH = (List<DaoEPBase>) info.getAttr().get("obj");

		//职工食堂
		paramMap.put("canteenTypeNum","zgst");
		info.set("paramObject", paramMap);
		info = XServiceManager.call(info);
		List<DaoEPBase> canteenZG = (List<DaoEPBase>) info.getAttr().get("obj");

		canteenList.addAll(canteenBH);
		canteenList.addAll(canteenZG);

		return canteenList;
	}

	/**
	 * call:调用方法
	 * @param  serviceName:服务名
	 * @param  methodName:方法名
	 * @param  paramObject:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo call(String serviceName,String methodName,Map<String,Object> paramObject) {
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, serviceName);
		//设置方法名
		eiInfo.set(EiConstant.methodName, methodName);
		//设置参数
		eiInfo.set("paramObject", paramObject);
		//调用本地服务
		return XLocalManager.call(eiInfo);
	}

	/**
	 * callNewTx:调用方法以新事务方式调用本地服务方法
	 * @param  serviceName:服务名
	 * @param  methodName:方法名
	 * @param  paramObject:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo callNewTx(String serviceName,String methodName,Map<String,Object> paramObject) {
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, serviceName);
		//设置方法名
		eiInfo.set(EiConstant.methodName, methodName);
		//设置参数
		eiInfo.set("paramObject", paramObject);
		//调用本地服务
		return XLocalManager.callNewTx(eiInfo);
	}

	/**
	 * callNoTx:调用方法以非事务管控方式调用本地服务方法
	 * @param  serviceName:服务名
	 * @param  methodName:方法名
	 * @param  paramObject:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo callNoTx(String serviceName,String methodName,Map<String,Object> paramObject) {
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, serviceName);
		//设置方法名
		eiInfo.set(EiConstant.methodName, methodName);
		//设置参数
		eiInfo.set("paramObject", paramObject);
		//调用本地服务
		return XLocalManager.callNoTx(eiInfo);
	}

	/**
	 * 查询小代码配置
	 *
	 * @Title: callCode查询小代码
	 * @Param serviceId服务id
	 * 		  codeset分类编号
	 * 		  itemCode代码编号
	 * @return:
	 */
	public static EiInfo callCode(String serviceId,String codeset,String itemCode){
		//准备传入参数
		EiInfo eiInfo = new EiInfo();
		if(itemCode !=null){
			String condition = "ITEM_CODE='"+itemCode+"'";
			eiInfo.set("condition",condition);
		}
		eiInfo.set(EiConstant.serviceId,serviceId);
		eiInfo.set("codeset",codeset);
		//服务接口调用
		return XServiceManager.call(eiInfo);
	}
}
