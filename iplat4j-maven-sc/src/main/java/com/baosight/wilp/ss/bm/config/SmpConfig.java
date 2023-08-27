/**
 * 
 */
package com.baosight.wilp.ss.bm.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author chenqing
 * @datetime 2015年9月24日 下午3:35:01
 * @desc 系统配置实体类
 */
public class SmpConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通用服务节点url
	 */
	private String commonNodeUrl;
	/**
	 * 通用服务节点url
	 */
	private String commonNodeUrlWechat;
	/**
	 * 通用服务节点url
	 */
	private String commonNodeUrlAli;
	/**
	 * hessian服务pattern
	 */
	private String hessianPattern;
	/**
	 * hessian服务pattern
	 */
	private String hessianPatternWechat;
	/**
	 * hessian服务pattern
	 */
	private String hessianPatternAli;
	/**
	 * 医院代码
	 */
	private String hospitalCode;
	/**
	 * 医院代码
	 */
	private String hospitalCodeWechat;
	/**
	 * 医院代码
	 */
	private String hospitalCodeAli;
	
	/**
	 * 医院名称
	 */
	private String hospitalName;
	/**
	 * 职工一卡通充值收款食堂代码
	 */
	private String canteenNumWork;
	/**
	 * 职工一卡通充值收款食堂名称
	 */
	private String canteenNameWork;

	/**
	 * 职工充值收款食堂代码
	 */
	private String canteenNum;

	/**
	 * 病患一卡通充值收款食堂代码
	 */
	private String patientCanteenNum;

	/**
	 * 立牌医院编码，抓取支付节点数据时需要使用2019年9月17日 10:16:05
	 */
	private String scanHospitalCode;
	
	/**
	 * 职工充值收款食堂名称
	 */
	private String canteenName;

	/**
	 * 病患一卡通充值收款食堂名称
	 */
	private String patientCanteenName;

	/**
	 * 病患在线支付回调方法集合
	 */
	private Map<String,String> notifyMap = new HashMap<String, String>();
	
	
	public String getCanteenNameWork() {
		return canteenNameWork;
	}

	public void setCanteenNameWork(String canteenNameWork) {
		this.canteenNameWork = canteenNameWork;
	}

	public String getCanteenNumWork() {
		return canteenNumWork;
	}

	public void setCanteenNumWork(String canteenNumWork) {
		this.canteenNumWork = canteenNumWork;
	}
	
	public String getScanHospitalCode() {
		return scanHospitalCode;
	}

	public void setScanHospitalCode(String scanHospitalCode) {
		this.scanHospitalCode = scanHospitalCode;
	}

	public String getPatientCanteenNum() {
		return patientCanteenNum;
	}

	public String getPatientCanteenName() {
		return patientCanteenName;
	}

	public void setPatientCanteenNum(String patientCanteenNum) {
		this.patientCanteenNum = patientCanteenNum;
	}

	public void setPatientCanteenName(String patientCanteenName) {
		this.patientCanteenName = patientCanteenName;
	}

	public String getCanteenNum() {
		return canteenNum;
	}

	public void setCanteenNum(String canteenNum) {
		this.canteenNum = canteenNum;
	}

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getCommonNodeUrl() {
		return commonNodeUrl;
	}

	public void setCommonNodeUrl(String commonNodeUrl) {
		this.commonNodeUrl = commonNodeUrl;
	}

	public String getHessianPattern() {
		return hessianPattern;
	}

	public void setHessianPattern(String hessianPattern) {
		this.hessianPattern = hessianPattern;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalCodeWechat() {
		return hospitalCodeWechat;
	}

	public void setHospitalCodeWechat(String hospitalCodeWechat) {
		this.hospitalCodeWechat = hospitalCodeWechat;
	}

	public String getHospitalCodeAli() {
		return hospitalCodeAli;
	}

	public void setHospitalCodeAli(String hospitalCodeAli) {
		this.hospitalCodeAli = hospitalCodeAli;
	}

	public String getCommonNodeUrlWechat() {
		return commonNodeUrlWechat;
	}

	public void setCommonNodeUrlWechat(String commonNodeUrlWechat) {
		this.commonNodeUrlWechat = commonNodeUrlWechat;
	}

	public String getCommonNodeUrlAli() {
		return commonNodeUrlAli;
	}

	public void setCommonNodeUrlAli(String commonNodeUrlAli) {
		this.commonNodeUrlAli = commonNodeUrlAli;
	}

	public String getHessianPatternWechat() {
		return hessianPatternWechat;
	}

	public void setHessianPatternWechat(String hessianPatternWechat) {
		this.hessianPatternWechat = hessianPatternWechat;
	}

	public String getHessianPatternAli() {
		return hessianPatternAli;
	}

	public void setHessianPatternAli(String hessianPatternAli) {
		this.hessianPatternAli = hessianPatternAli;
	}


	public Map<String, String> getNotifyMap() {
		return notifyMap;
	}

	public void setNotifyMap(Map<String, String> notifyMap) {
		this.notifyMap = notifyMap;
	}

	/**
	 * 默认构造器
	 */
	public SmpConfig() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从配置文件中构造对象
	 */
	public SmpConfig(Properties props) {
		if (null == props || props.isEmpty())
			return;
		this.commonNodeUrl = props.getProperty("common_node_url");
		this.commonNodeUrlWechat = props.getProperty("common_node_url_wechat");
		this.commonNodeUrlAli = props.getProperty("common_node_url_ali");
		this.hessianPattern = props.getProperty("hessian_pattern");
		this.hessianPatternWechat = props.getProperty("hessian_pattern_wechat");
		this.hessianPatternAli = props.getProperty("hessian_pattern_ali");
		this.hospitalCode = props.getProperty("hospital_code");
		this.hospitalCodeWechat = props.getProperty("hospital_code_wechat");
		this.hospitalCodeAli = props.getProperty("hospital_code_ali");
		this.hospitalName = props.getProperty("hospital_name");
		this.scanHospitalCode = props.getProperty("scan_hospital_code");
		this.canteenNumWork = props.getProperty("canteen_num_work");
		this.canteenNameWork = props.getProperty("canteen_name_work");

		this.canteenNum = props.getProperty("canteen_num");
		this.canteenName = props.getProperty("canteen_name");
		this.patientCanteenNum = props.getProperty("patient_canteen_num");
		this.patientCanteenName = props.getProperty("patient_canteen_name");

		this.notifyMap.put("patient_wechat_service", props.getProperty("patient_wechat_service")) ;
		this.notifyMap.put("patient_wechat_method", props.getProperty("patient_wechat_method")) ;
		this.notifyMap.put("patient_ali_service", props.getProperty("patient_ali_service")) ;
		this.notifyMap.put("patient_ali_method", props.getProperty("patient_ali_method")) ;
		this.notifyMap.put("work_ali_service", props.getProperty("work_ali_service")) ;
		this.notifyMap.put("work_ali_method", props.getProperty("work_ali_method")) ;
		this.notifyMap.put("work_wechat_service", props.getProperty("work_wechat_service")) ;
		this.notifyMap.put("work_wechat_method", props.getProperty("work_wechat_method")) ;

		this.notifyMap.put("work_recharge_ali_service", props.getProperty("work_ali_service")) ;
		this.notifyMap.put("work_recharge_ali_method", "workRechargeNotifyAli") ;
		this.notifyMap.put("work_recharge_wechat_service",  props.getProperty("work_wechat_service")) ;
		this.notifyMap.put("work_recharge_wechat_method", "workRechargeNotifyWechat") ;

		this.notifyMap.put("patient_recharge_ali_service", props.getProperty("patient_ali_service")) ;
		this.notifyMap.put("patient_recharge_ali_method", "patientRechargeNotifyAli") ;
		this.notifyMap.put("patient_recharge_wechat_service",  props.getProperty("patient_wechat_service")) ;
		this.notifyMap.put("patient_recharge_wechat_method", "patientRechargeNotifyWechat") ;
	}
	
}
