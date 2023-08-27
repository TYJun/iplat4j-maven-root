package com.baosight.wilp.hr.common;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  HrUtils.java
 * @ClassName: HrUtils
 * @Package com.baosight.wilp.hr.common
 * @Description: TODO
 * @author gcc
 * @date:   2022年4月4日 下午4:32:45
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class HrUtils {

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
			});
		}
		return info;
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




	/**
	 * 接口（本地服务）调用
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param：可以为string、map、EiInfo
	 * @param:  @param serviceName：服务名
	 * @param:  @param methodName：方法名称
	 * @param:  @return
	 * @return: EiInfo  ： 调用服务的返回结果
	 * @throws
	 */
	public static EiInfo invoke (Object param, String serviceName, String methodName) {
		EiInfo inInfo = new EiInfo();
		if(param instanceof EiInfo) {
			inInfo = (EiInfo) param;
		} else if (param instanceof String) {
			inInfo.set((String) param, param);
		} else if (param instanceof Map) {
			for (Object key : ((Map) param).keySet()) {
				inInfo.set((String)key, ((Map) param).get(key));
			}
		}
		inInfo.set(EiConstant.serviceName, serviceName);
		inInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}



	/**
	 * 构建
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param blockId ： block块的id
	 * @param:  @return
	 * @return: EiInfo  ： EiInfo对象
	 * @throws
	 */
	public static EiInfo buildBlankEiInfo (String blockId) {
		EiInfo inInfo = new EiInfo();
		EiBlock block = new EiBlock(blockId);
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * 判断是否是正整数
	 * @Title: isInteger
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param num 参数
	 * @return boolean : true=是， false;
	 * @throws
	 **/
	public static boolean isInteger(String num) {
		Pattern pattern = Pattern.compile("^[0-9]+$");
		return pattern.matcher(num).matches();
	}

	/**
	 * 楼补全 -MDRY0101
	 *
	 * <p>调用微服务接口S_AC_FW_13查询楼信息</p>
	 *
	 * @Title: selectSpotBuildingName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		building ： 楼
	 * @param:  @return
	 * @return: EiInfo
	 * 		building ： 楼
	 * @throws
	 */
	public static EiInfo selectSpotBuildingName (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		String building = map.get("building") == null ? "" : map.get("building").toString();
		//调用微服务接口S_AC_FW_13查询楼信息
		List<Map<String, Object>> list = BaseDockingUtils.getBuilding(building);
		return CommonUtils.BuildOutEiInfo("building", list);
	}

	/**
	 * 层补全 -MDRY0101
	 *
	 * <p>调用微服务接口S_AC_FW_14查询楼信息</p>
	 *
	 * @Title: selectSpotFloorName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		building ： 层
	 * 		floor ： 层
	 * @param:  @return
	 * @return: EiInfo
	 * 		floor ： 层
	 * @throws
	 */
	public static EiInfo selectSpotFloorName (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		String building = map.get("building") == null ? "" : map.get("building").toString();
		String floor = map.get("floor") == null ? "" : map.get("floor").toString();
		List<Map<String, Object>> list = BaseDockingUtils.getFloor(building, floor);
		return CommonUtils.BuildOutEiInfo("floor", list);
	}

	/**
	 * 获取当前登录人
	 *
	 * @Title: selectSpotFloorName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @param:  @return
	 * @return: EiInfo
	 * 		name ： 名称
	 * 		workNo ：工号
	 * 		dataGroupCode ：账套
	 * @throws
	 */
	public static Map<String, String> getUserInformation (Map<String, String> map,EiInfo eiInfo) {
		//获取当前登录人信息
		Map<String, Object> userInfo = getUserInfo(UserSession.getUser().getUsername());
		String workNo = isEmpty(eiInfo.get("workNo"), userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString());
		String name =isEmpty(eiInfo.get("name"), userInfo.get("name") == null ? "" : userInfo.get("name").toString());
		String dataGroupCode = isEmpty(eiInfo.get("dataGroupCode"), userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString());
		map.put("creatorId",workNo);
		map.put("creatorName",name);
		map.put("dataGroupCode",dataGroupCode);
		return map;
	}


	/**
	 * 插入操作履历表
	 *
	 * @Title: insertHistory
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @param:  @return
	 * @return: EiInfo
	 * 		id ： 主键
	 * 		flag ：标记
	 * 		dao ：dao对象
	 * @throws
	 */
	@SuppressWarnings("all")
	public static void   insertHistory(String id, String flag , Dao dao){
		// 获取当前登录人信息
		Map<String, Object> userInfo = HrUtils.getUserInfo(UserSession.getUser().getUsername());
		String name =userInfo.get("name").toString();
		String time = DateUtils.curDateTimeStr19();
		Map<String,String> map =  new HashMap();
		map.put("id", UUID.randomUUID().toString());
		map.put("manId", id);
		map.put("operatorType", flag);
		map.put("operatormanName",name);
		map.put("operatorTime",time);
		dao.insert("HRXX03.insert",map);
	}
}
