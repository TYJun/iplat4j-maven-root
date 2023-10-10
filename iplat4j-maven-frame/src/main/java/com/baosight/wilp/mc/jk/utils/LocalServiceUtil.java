package com.baosight.wilp.mc.jk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 本地service工具类
 * */
public class LocalServiceUtil {

	/**
	 * 获取JdbcTemplate
	 * */
	public static JdbcTemplate getJdbcTemplate() {
		//jdbcTemplate.execute("CREATE TABLE USER (user_id integer, name varchar(100))");
		return (JdbcTemplate) PlatApplicationContext.getBean("jdbcTemplate");
		
	}

	/**
	 * Todo(通过json进行类型强转)
	 *
	 * @Title: beanCastByJson
	 * @Param object
	 * @Param clazz
	 * @return: Object
	 */
//	public static Object beanCastByJson(Object object, Class clazz){
//		String result1 = JSON.toJSONString(object);
//		JSONObject json = JSONObject.fromObject(result1);
//		return JSONObject.toBean(json,clazz);
//	}

	/**
	 * Todo(通过json进行类型强转)
	 *
	 * @Title: beanCastByJson
	 * @Param object
	 * @Param clazz
	 * @return: Object
	 */
//	public static List jsonArrayCastByJson(JSONArray object, Class clazz){
//		return JSONArray.toList(object, clazz);
//	}

	/**
	 * Todo(Object通过fastJson进行类型强转实体类)
	 *
	 * @Title: beanCastByJson
	 * @Param object
	 * @Param clazz
	 * @return: Object
	 */
	public static Object beanCastByJson(Object object, Class clazz){
		String json = JSON.toJSONString(object);
		return JSONObject.parseObject(json,clazz);
	}

	/**
	 * Todo(JSONArray通过fastJson强转list集合)
	 *
	 * @Title: jsonArrayCastByJson
	 * @Param object
	 * @Param clazz
	 * @return: Object
	 */
	public static List jsonArrayCastByJson(JSONArray object, Class clazz){
		return object.toJavaList(clazz);
	}

	/**
	 * Todo(Object通过fastJson强转List集合)
	 *
	 * @Title: objectCastListByJson
	 * @Param
	 * @return:
	 */
	public static List objectCastListByJson(Object object, Class clazz){
		String jsonString = JSON.toJSONString(object);
		JSONArray objects = JSON.parseArray(jsonString);
		return objects.toJavaList(clazz);
	}

	/**
	 * Todo(java对象集合通过fastJson强转其他实体类型list集合)
	 *
	 * @Title: listCastByJson
	 * @Param list
	 * 			clazz
	 * @return: List
	 */
	public static List listCastByJson(List list, Class clazz){
		String jsonString = JSONArray.toJSONString(list);
		return JSONObject.parseArray(jsonString, clazz);
	}

	public static void main(String[] args) {
		ResultData r = new ResultData();
		ResultData re = (ResultData)beanCastByJson(r, ResultData.class);
		System.out.println(re);
	}

	/**
	 * call:调用方法
	 * @param  serviceName:服务名
	 * @param  methodName:方法名
	 * @param  paramObject:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo call(String serviceName,String methodName,Map<String,Object> paramObject)  throws Exception{
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, serviceName);
		//设置方法名
		eiInfo.set(EiConstant.methodName, methodName);
		//设置参数
		eiInfo.set("paramObject", paramObject);
		//调用本地服务
		EiInfo outInfo = XLocalManager.call(eiInfo);
		return outInfo;
	}
	
	/**
	 * callNewTx:调用方法以新事务方式调用本地服务方法
	 * @param  serviceName:服务名
	 * @param  methodName:方法名
	 * @param  paramObject:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo callNewTx(String serviceName,String methodName,Map<String,Object> paramObject)  throws Exception{
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, serviceName);
		//设置方法名
		eiInfo.set(EiConstant.methodName, methodName);
		//设置参数
		eiInfo.set("paramObject", paramObject);
		//调用本地服务
		EiInfo outInfo = XLocalManager.callNewTx(eiInfo);
		return outInfo;
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
		EiInfo outInfo = XLocalManager.callNoTx(eiInfo);
		return outInfo;
	}

	/**
	 * @Title: callCode查询小代码
	 * @Param serviceId服务id
	 * 		  conditionCode代码编码
	 * @return:
	 */
	public static EiInfo callCode(String serviceId,String conditionCode){
		//准备传入参数
		EiInfo eiInfo = new EiInfo();
		String tableName = "tedcm01";
		String valueColumnName = "ITEM_CODE";
		String labelColumnName = "ITEM_CNAME";
		String orderBy = "SORT_ID asc,ITEM_CODE asc";
		eiInfo.set(EiConstant.serviceId,serviceId);
		eiInfo.set("tableName",tableName);
		eiInfo.set("valueColumnName",valueColumnName);
		eiInfo.set("labelColumnName",labelColumnName);
		eiInfo.set("condition",conditionCode);
		eiInfo.set("orderBy",orderBy);
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: callCode1查询小代码
	 * @Param serviceId服务id
	 * 		  codeset分类编号
	 * 		  itemCode代码编号
	 * @return:
	 */
	public static EiInfo callCode1(String serviceId,String codeset,String itemCode){
		//准备传入参数
		EiInfo eiInfo = new EiInfo();
		//String serviceId = "S_ED_02";
		//String codeset = "iplat.ed.subapp";
		String condition = "ITEM_CODE='"+itemCode+"'";
		eiInfo.set(EiConstant.serviceId,serviceId);
		eiInfo.set("codeset",codeset);
		eiInfo.set("condition",condition);
		//服务接口调用
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return outInfo;
	}
}
