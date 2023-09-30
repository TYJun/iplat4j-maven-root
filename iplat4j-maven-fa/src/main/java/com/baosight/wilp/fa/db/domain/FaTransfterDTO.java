package com.baosight.wilp.fa.db.domain;

import java.math.BigDecimal;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月21日 12:07
 */
public class FaTransfterDTO {
	private String faInfoId;
	private String enterBillNo;
	private String outBillNo;
	private String matNum;
	private String matName;
	private String matTypeNum;
	private String matTypeName;
	private String matModel;
	private String matSpec;
	private String unit;
	private String unitName;
	private Double outNum;
	private Double outPrice;
	private Double outAmount;

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
	}

	public String getEnterBillNo() {
		return enterBillNo;
	}

	public void setEnterBillNo(String enterBillNo) {
		this.enterBillNo = enterBillNo;
	}

	public String getOutBillNo() {
		return outBillNo;
	}

	public void setOutBillNo(String outBillNo) {
		this.outBillNo = outBillNo;
	}

	public String getMatNum() {
		return matNum;
	}

	public void setMatNum(String matNum) {
		this.matNum = matNum;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public String getMatTypeNum() {
		return matTypeNum;
	}

	public void setMatTypeNum(String matTypeNum) {
		this.matTypeNum = matTypeNum;
	}

	public String getMatTypeName() {
		return matTypeName;
	}

	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}

	public String getMatModel() {
		return matModel;
	}

	public void setMatModel(String matModel) {
		this.matModel = matModel;
	}

	public String getMatSpec() {
		return matSpec;
	}

	public void setMatSpec(String matSpec) {
		this.matSpec = matSpec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getOutNum() {
		return outNum;
	}

	public void setOutNum(Double outNum) {
		this.outNum = outNum;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	public Double getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}
}
