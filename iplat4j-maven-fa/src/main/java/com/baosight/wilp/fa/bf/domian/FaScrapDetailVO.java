/**
 * Generate time : 2022-12-08 14:57:07
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.fa.bf.domian;

import com.baosight.iplat4j.core.util.NumberUtils;

import java.math.BigDecimal;

import com.baosight.iplat4j.core.util.DateUtils;

import java.util.Date;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

/**
 * FaScrapDetail
 *
 */
public class FaScrapDetailVO extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id;        /* 报废明细表*/
	private String scrappedNo;        /* 报废单号*/
	private String faInfoId;
	private String goodsNum;        /* 资产编码*/
	private String goodsName;        /* 资产名称*/
	private String goodsClassifyCode;
	private String goodsClassifyName;
	private String deptNum;        /* 科室编码*/
	private String deptName;        /* 科室名称*/
	private String model;        /* 规格型号*/
	private Date useDate;    /* 启用日期*/
	private BigDecimal buyCost = new BigDecimal("0");        /* 资产原值*/
	private BigDecimal netAssetValue = new BigDecimal("0");        /* 资产净值*/
	private Integer usedYears = new Integer(0);        /* 已使用年限*/
	private String scrapDetailStatus;

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("报废明细表");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("scrappedNo");
		eiColumn.setDescName("报废单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("faInfoId");
		eiColumn.setDescName("资产主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsNum");
		eiColumn.setDescName("资产编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsName");
		eiColumn.setDescName("资产名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyCode");
		eiColumn.setDescName("类组编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyName");
		eiColumn.setDescName("类组名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptNum");
		eiColumn.setDescName("科室编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptName");
		eiColumn.setDescName("科室名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("model");
		eiColumn.setDescName("规格型号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("useDate");
		eiColumn.setDescName("启用日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("buyCost");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(10);
		eiColumn.setDescName("资产原值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("netAssetValue");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(10);
		eiColumn.setDescName("资产净值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("usedYears");
		eiColumn.setDescName("已使用年限");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("scrapDetailStatus");
		eiColumn.setDescName("报废状态");
		eiMetadata.addMeta(eiColumn);
	}

	/**
	 * the constructor
	 */
	public FaScrapDetailVO() {
		initMetaData();
	}

	/**
	 * get the id - 报废明细表
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 报废明细表
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getScrappedNo() {
		return scrappedNo;
	}

	public void setScrappedNo(String scrappedNo) {
		this.scrappedNo = scrappedNo;
	}

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
	}

	/**
	 * get the goodsNum - 资产编码
	 * @return the goodsNum
	 */
	public String getGoodsNum() {
		return this.goodsNum;
	}

	/**
	 * set the goodsNum - 资产编码
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * get the goodsName - 资产名称
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	/**
	 * set the goodsName - 资产名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	/**
	 * get the deptNum - 科室编码
	 * @return the deptNum
	 */
	public String getDeptNum() {
		return this.deptNum;
	}

	/**
	 * set the deptNum - 科室编码
	 */
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	/**
	 * get the deptName - 科室名称
	 * @return the deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * set the deptName - 科室名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * get the model - 规格型号
	 * @return the model
	 */
	public String getModel() {
		return this.model;
	}

	/**
	 * set the model - 规格型号
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * get the useDate - 启用日期
	 * @return the useDate
	 */
	public Date getUseDate() {
		return this.useDate;
	}

	/**
	 * set the useDate - 启用日期
	 */
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	/**
	 * get the buyCost - 资产原值
	 * @return the buyCost
	 */
	public BigDecimal getBuyCost() {
		return this.buyCost;
	}

	/**
	 * set the buyCost - 资产原值
	 */
	public void setBuyCost(BigDecimal buyCost) {
		this.buyCost = buyCost;
	}

	/**
	 * get the netAssetValue - 资产净值
	 * @return the netAssetValue
	 */
	public BigDecimal getNetAssetValue() {
		return this.netAssetValue;
	}

	/**
	 * set the netAssetValue - 资产净值
	 */
	public void setNetAssetValue(BigDecimal netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	/**
	 * get the usedYears - 已使用年限
	 * @return the usedYears
	 */
	public Integer getUsedYears() {
		return this.usedYears;
	}

	/**
	 * set the usedYears - 已使用年限
	 */
	public void setUsedYears(Integer usedYears) {
		this.usedYears = usedYears;
	}

	public String getScrapDetailStatus() {
		return scrapDetailStatus;
	}

	public void setScrapDetailStatus(String scrapDetailStatus) {
		this.scrapDetailStatus = scrapDetailStatus;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setScrappedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrappedNo")), scrappedNo));
		setFaInfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
		setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
		setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
		setGoodsClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyCode")), goodsClassifyCode));
		setGoodsClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyName")), goodsClassifyName));
		setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
		setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
		setModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
		setUseDate(OneSelfUtils.defaultDate(map.get("useDate"), null));
		setBuyCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("buyCost")), buyCost));
		setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
		setUsedYears(NumberUtils.toInteger(StringUtils.toString(map.get("usedYears")), usedYears));
		setScrapDetailStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapDetailStatus")), scrapDetailStatus));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("scrappedNo", StringUtils.toString(scrappedNo, eiMetadata.getMeta("scrappedNo")));
		map.put("faInfoId", StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoId")));
		map.put("goodsNum", StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
		map.put("goodsName", StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
		map.put("goodsClassifyCode", StringUtils.toString(goodsClassifyCode, eiMetadata.getMeta("goodsClassifyCode")));
		map.put("goodsClassifyName", StringUtils.toString(goodsClassifyName, eiMetadata.getMeta("goodsClassifyName")));
		map.put("deptNum", StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
		map.put("deptName", StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
		map.put("model", StringUtils.toString(model, eiMetadata.getMeta("model")));
		map.put("useDate", StringUtils.toString(useDate, eiMetadata.getMeta("useDate")));
		map.put("buyCost", StringUtils.toString(buyCost, eiMetadata.getMeta("buyCost")));
		map.put("netAssetValue", StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
		map.put("usedYears", StringUtils.toString(usedYears, eiMetadata.getMeta("usedYears")));
		map.put("scrapDetailStatus", StringUtils.toString(scrapDetailStatus, eiMetadata.getMeta("scrapDetailStatus")));
		return map;
	}
}