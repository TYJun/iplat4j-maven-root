package com.bonawise.domain.base;

import java.io.Serializable;

/**
 * 调用支付时的商户信息
 * @author hunter
 *
 * 2018年5月22日
 */
public class BusinessInfoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6952641117843611171L;
	
	/**
	 * 医院编码
	 */
	private String hospitalCode;
	
	/**
	 * 模块编码
	 */
	private String modulCode;
	
	private String usedUnitCode;
	
	private String userUnitName;

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getModulCode() {
		return modulCode;
	}

	public void setModulCode(String modulCode) {
		this.modulCode = modulCode;
	}

	public String getUsedUnitCode() {
		return usedUnitCode;
	}

	public void setUsedUnitCode(String usedUnitCode) {
		this.usedUnitCode = usedUnitCode;
	}

	public String getUserUnitName() {
		return userUnitName;
	}

	public void setUserUnitName(String userUnitName) {
		this.userUnitName = userUnitName;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(hospitalCode)
		.append(PayConstant.CONFIG_SEPARATOR)
		.append(modulCode)
		.append(PayConstant.CONFIG_SEPARATOR)
		.append(usedUnitCode);
		return sb.toString();
		
	}
}
