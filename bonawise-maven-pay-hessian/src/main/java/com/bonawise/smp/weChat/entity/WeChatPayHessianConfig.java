package com.bonawise.smp.weChat.entity;

import java.io.Serializable;
import java.util.Properties;

/**
 * @desc 系统配置实体类
 * @date 2018年5月29日 09点35分
 * @author yaokang
 *
 */
public class WeChatPayHessianConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WeChatPayHessianConfig(){
		
	}
	
	/**
	 * 通用服务节点url
	 */
	private String commonNodeUrl;
	
	/**
	 * hessian服务pattern
	 */
	private String hessianPattern;
	
	/**
	 * 医院代码
	 */
	private String hospitalCode;
	
	/**
	 * 医院名称
	 */
	private String hospitalName;
	
	private String scanHospitalCode;
	
	private String escortUsedUnitCode;
	
	private String escortModulCode;

	public String getEscortUsedUnitCode() {
		return escortUsedUnitCode;
	}


	public void setEscortUsedUnitCode(String escortUsedUnitCode) {
		this.escortUsedUnitCode = escortUsedUnitCode;
	}


	public String getEscortModulCode() {
		return escortModulCode;
	}


	public void setEscortModulCode(String escortModulCode) {
		this.escortModulCode = escortModulCode;
	}


	public String getScanHospitalCode() {
		return scanHospitalCode;
	}


	public void setScanHospitalCode(String scanHospitalCode) {
		this.scanHospitalCode = scanHospitalCode;
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


	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	/**
	 * @desc 初始化参数
	 * @param properties
	 */
	public WeChatPayHessianConfig(Properties properties) {
		if(properties == null || properties.isEmpty()) {
			return ;
		} else {
			this.commonNodeUrl = properties.getProperty("common_node_url");
			this.hessianPattern = properties.getProperty("hessian_pattern");
			this.hospitalCode = properties.getProperty("hospital_code");
			this.hospitalName = properties.getProperty("hospital_name");
			this.scanHospitalCode = properties.getProperty("scan_hospital_code");
			this.escortModulCode=properties.getProperty("escort_modul_code");
			this.escortUsedUnitCode=properties.getProperty("escort_used_unit_code");
		}
	}
}
