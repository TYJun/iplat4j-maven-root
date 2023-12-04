package com.baosight.wilp.fa.zj.domain;

import java.math.BigDecimal;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月27日 10:09
 */
public class FaDepreciationDTO {
	private String id;
	// 资产主键
	private String faInfoId;
	// 科室编码
	private String deptNum;
	// 科室名称
	private String deptName;
	// 资产编码
	private String goodsNum;
	// 资产名称
	private String goodsName;
	// 末级分类
	private String goodsCategoryCode;
	private String goodsCategoryName;
	// 类组
	private String goodsClassifyCode;
	private String goodsClassifyName;
	// 资产类别
	private String goodsTypeCode;
	private String goodsTypeName;
	// 规格
	private String model;
	// 型号
	private String spec;
	// 资产原值
	private BigDecimal buyCost;
	// 抛帐变化量
	private BigDecimal variationValue;
	// 抛帐类型
	private String accountType;
	// 供应商
	private String surpName;
	private String surpNum;
	// 发票
	private String invoiceNo;
	// 折旧月份
	private String depreciationMonth;
	// 已使用月份
	private int usedMonth;
	// 本月折旧
	private BigDecimal depreciationValue;
	// 累计折旧
	private BigDecimal totalDepreciation;
	// 资产净值
	private BigDecimal netAssetValue;
	// 资金来源
	private String fundingSourceNum;
	// 资金来源
	private String fundingSourceName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSurpName() {
		return surpName;
	}

	public void setSurpName(String surpName) {
		this.surpName = surpName;
	}

	public String getSurpNum() {
		return surpNum;
	}

	public void setSurpNum(String surpNum) {
		this.surpNum = surpNum;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
	}

	public String getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(BigDecimal buyCost) {
		this.buyCost = buyCost;
	}

	public String getGoodsTypeCode() {
		return goodsTypeCode;
	}

	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCategoryCode() {
		return goodsCategoryCode;
	}

	public void setGoodsCategoryCode(String goodsCategoryCode) {
		this.goodsCategoryCode = goodsCategoryCode;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsClassifyCode() {
		return goodsClassifyCode;
	}

	public void setGoodsClassifyCode(String goodsClassifyCode) {
		this.goodsClassifyCode = goodsClassifyCode;
	}

	public String getGoodsClassifyName() {
		return goodsClassifyName;
	}

	public void setGoodsClassifyName(String goodsClassifyName) {
		this.goodsClassifyName = goodsClassifyName;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public BigDecimal getVariationValue() {
		return variationValue;
	}

	public void setVariationValue(BigDecimal variationValue) {
		this.variationValue = variationValue;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDepreciationMonth() {
		return depreciationMonth;
	}

	public void setDepreciationMonth(String depreciationMonth) {
		this.depreciationMonth = depreciationMonth;
	}

	public int getUsedMonth() {
		return usedMonth;
	}

	public void setUsedMonth(int usedMonth) {
		this.usedMonth = usedMonth;
	}

	public BigDecimal getDepreciationValue() {
		return depreciationValue;
	}

	public void setDepreciationValue(BigDecimal depreciationValue) {
		this.depreciationValue = depreciationValue;
	}

	public BigDecimal getTotalDepreciation() {
		return totalDepreciation;
	}

	public void setTotalDepreciation(BigDecimal totalDepreciation) {
		this.totalDepreciation = totalDepreciation;
	}

	public BigDecimal getNetAssetValue() {
		return netAssetValue;
	}

	public void setNetAssetValue(BigDecimal netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	public String getFundingSourceNum() {
		return fundingSourceNum;
	}

	public void setFundingSourceNum(String fundingSourceNum) {
		this.fundingSourceNum = fundingSourceNum;
	}

	public String getFundingSourceName() {
		return fundingSourceName;
	}

	public void setFundingSourceName(String fundingSourceName) {
		this.fundingSourceName = fundingSourceName;
	}
}
