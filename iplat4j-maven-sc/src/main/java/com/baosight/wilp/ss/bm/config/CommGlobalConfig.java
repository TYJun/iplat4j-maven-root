/**
 * 
 */
package com.baosight.wilp.ss.bm.config;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @declaration 全局配置缓存类
 * @author kangroo
 * @datetime 2016年4月7日 上午9:13:44
 * @version 2016
 */
public class CommGlobalConfig implements Serializable {
	
	/**
	 * 版本序列化ID
	 */
	private static final long serialVersionUID = 7733998032337892011L;
	/**
	 * 系统配置类
	 */
	/**职工订餐订单号*/
	private static Map<String, String> zqBillNoMap = new HashMap<String, String>();
	/**职工订餐订单号*/
	private static Map<String, String> billNoMap = new HashMap<String, String>();
	/**病患订餐订单号*/
	private static Map<String, String> bhBillNoMap = new HashMap<String, String>();
	/**职工套餐订单号*/
	private static Map<String, String> comboBillNoMap = new HashMap<String, String>();
	/**病患套餐订单号*/
	private static Map<String, String> comboBillNoMapPatient = new HashMap<String, String>();
	/**POS订餐订单号 */
	private static Map<String, String> poBillNoMap = new HashMap<String, String>();
	/**APP支付类型 */
	private static List<Map<String,String>> appPayTypeList = new ArrayList<Map<String,String>>();
	/**PC支付类型  */
	private static List<Map<String,String>> pcPayTypeList = new ArrayList<Map<String,String>>();
	/**启动项基础配置*/
	private static List<Map<String,String>> baseConfigList = new ArrayList<Map<String,String>>();
	
	/**
	 * 默认构造器
	 */
	public CommGlobalConfig() {
		
	}
	public static Map<String, String> getPoBillNoMap() {
		return poBillNoMap;
	}

	public static void setPoBillNoMap(Map<String, String> poBillNoMap) {
		CommGlobalConfig.poBillNoMap = poBillNoMap;
	}

	public static Map<String, String> getBhBillNoMap() {
		return bhBillNoMap;
	}

	public static void setBhBillNoMap(Map<String, String> bhBillNoMap) {
		CommGlobalConfig.bhBillNoMap = bhBillNoMap;
	}
	

	public static Map<String, String> getZqBillNoMap() {
		return zqBillNoMap;
	}

	public static void setZqBillNoMap(Map<String, String> zqBillNoMap) {
		CommGlobalConfig.zqBillNoMap = zqBillNoMap;
	}
	
	public static Map<String, String> getComboBillNoMap() {
		return comboBillNoMap;
	}

	public static void setComboBillNoMap(Map<String, String> comboBillNoMap) {
		CommGlobalConfig.comboBillNoMap = comboBillNoMap;
	}

	public static Map<String, String> getComboBillNoMapPatient() {
		return comboBillNoMapPatient;
	}
	public static void setComboBillNoMapPatient(
			Map<String, String> comboBillNoMapPatient) {
		CommGlobalConfig.comboBillNoMapPatient = comboBillNoMapPatient;
	}
	public static List<Map<String, String>> getAppPayTypeList() {
		return appPayTypeList;
	}

	public static void setAppPayTypeList(List<Map<String, String>> appPayTypeList) {
		CommGlobalConfig.appPayTypeList = appPayTypeList;
	}

	public static List<Map<String, String>> getPcPayTypeList() {
		return pcPayTypeList;
	}

	public static void setPcPayTypeList(List<Map<String, String>> pcPayTypeList) {
		CommGlobalConfig.pcPayTypeList = pcPayTypeList;
	}

	public static List<Map<String, String>> getBaseConfigList() {
		return baseConfigList;
	}

	public static void setBaseConfigList(List<Map<String, String>> baseConfigList) {
		CommGlobalConfig.baseConfigList = baseConfigList;
	}

	public static Map<String, String> getBillNoMap() {
		return billNoMap;
	}
	public static void setBillNoMap(Map<String, String> billNoMap) {
		CommGlobalConfig.billNoMap = billNoMap;
	}
	
	/**
	 * @param 职工订餐
	 * @return pboStatus状态对象
	 */
	public static String getBillNoMapByDate(String date){
		if (StringUtils.isBlank(date))
			return "";
		
		String pobillNo = "";
		if (billNoMap.containsKey(date))
			pobillNo = billNoMap.get(date);
		else {
			billNoMap.put(date, "00001");
			pobillNo = "00000";
		}	
		return pobillNo;
	}
	
	/**
	 * @param 病患订单号
	 * @return pboStatus状态对象
	 */
	public static String getBhBillNoByDate(String date){
		if (StringUtils.isBlank(date))
			return "";
		
		String bhbillNo = "";
		if (bhBillNoMap.containsKey(date))
			bhbillNo = bhBillNoMap.get(date);
		else {
			bhBillNoMap.put(date, "00001");
			bhbillNo = "00000";
		}	
		return bhbillNo;
	}

	/**
	 * @param 职工套餐订单号
	 * @return pboStatus状态对象
	 */
	public static String getComboBillNoByDate(String date){
		if (StringUtils.isBlank(date))
			return "";
		
		String comboBillNo = "";
		if (comboBillNoMap.containsKey(date))
			comboBillNo = comboBillNoMap.get(date);
		else {
			comboBillNoMap.put(date, "00001");
			comboBillNo = "00000";
		}	
		return comboBillNo;
	}
	
	/**
	 * 病患套餐订单号
	 * @param date
	 * @return
	 */
	public static String getComboBillNoPatientByDate(String date){
		if (StringUtils.isBlank(date))
			return "";
		
		String comboBillNoPatient = "";
		if (comboBillNoMapPatient.containsKey(date))
			comboBillNoPatient = comboBillNoMapPatient.get(date);
		else {
			comboBillNoMapPatient.put(date, "00001");
			comboBillNoPatient = "00000";
		}	
		return comboBillNoPatient;
	}
	
	public static void syncBhBillNoMap(String key, String value) {
		bhBillNoMap.put(key, value);
	}
	public static void syncComboBillNoMap(String key, String value) {
		comboBillNoMap.put(key, value);
	}
	public static void syncComboBillNoMapPatient(String key, String value){
		comboBillNoMapPatient.put(key, value);
	}
	public static void syncPoBillNoMap(String key, String value) {
		poBillNoMap.put(key, value);
	}
	public static void syncBillNoMap(String key, String value) {
		billNoMap.put(key, value);
	}
	public static void syncZqBillNoMap(String key, String value) {
		zqBillNoMap.put(key, value);
	}
	
	/**
	 * 获取初始化配置参数
	 * @param response
	 * @param request
	 */
	public static String getConfigInfo(String parStr){
		List<Map<String, String>>  lst =  new ArrayList<Map<String,String>>();
		String str = "";
		if(null == parStr || "" == parStr)
			return "";
		else
		{
			lst = CommGlobalConfig.getBaseConfigList();
			for(int i=0; lst!=null&i<lst.size(); i++)
			{
				str = MapUtils.getString(lst.get(i), "type_code");
				if((parStr).equals(str))
					return MapUtils.getString(lst.get(i), "value1");
			}
			return "";
		}
	}
	
	/**
	 * @param POS机订餐
	 * @return pboStatus状态对象
	 */
	public static String getPoBillNoByDate(String date){
		if (StringUtils.isBlank(date))
			return "";
		
		String pobillNo = "";
		if (poBillNoMap.containsKey(date))
			pobillNo = poBillNoMap.get(date);
		else {
			poBillNoMap.put(date, "00001");
			pobillNo = "00000";
		}	
		return pobillNo;
	}
	
	/**
	 * @param
	 * @return pboStatus状态对象
	 */
	public static String getZqBillNoMapByDate(String date){
		if (StringUtils.isBlank(date))
			return "";

		String pobillNo = "";
		if (zqBillNoMap.containsKey(date))
			pobillNo = zqBillNoMap.get(date);
		else {
			zqBillNoMap.put(date, "00001");
			pobillNo = "00000";
		}
		return pobillNo;
	}
	
}
