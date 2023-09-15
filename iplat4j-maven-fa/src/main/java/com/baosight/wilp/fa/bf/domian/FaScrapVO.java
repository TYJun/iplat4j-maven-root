/**
 * Generate time : 2022-12-08 14:57:07
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.fa.bf.domian;

import com.baosight.iplat4j.core.util.DateUtils;

import java.sql.Timestamp;
import java.util.Date;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

/**
 * FaScrap
 */
public class FaScrapVO extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id;        /* 固定资产报废表主键*/
	private String faInfoId;
	private String detailId;
	private String goodsNum;
	private String goodsName;
	private String spec;
	private String goodsClassifyCode;
	private String goodsClassifyName;
	private String scrappedNo;        /* 报废单号*/
	private String applyDeptName;        /* 申请科室*/
	private String applyPerson;        /* 申请人*/
	private Timestamp applyTime;    /* 申请时间*/
	private String applyReason;        /* 申请理由*/
	private String applyFileCode;
	private String identifyDeptName;        /* 鉴定科*/
	private String identifyPerson;        /* 鉴定人*/
	private Timestamp identifyTime;    /* 鉴定时间*/
	private String identifyReason;        /* 鉴定理由*/
	private String functionDeptName;        /* 归口科室*/
	private String functionPerson;        /* 归口人*/
	private Timestamp functionTime;    /* 归口时间*/
	private String functionReason;        /* 归口理由*/
	private String assetDeptName;        /* 资产科*/
	private String assetPerson;        /* 资产确认人*/
	private Timestamp assetTime;    /* 确认时间*/
	private String assetReason;        /* 确认理由*/
	private String scrapStatus;        /* 状态(00-申请中,10-鉴定中,20-归口确认中,30-资产审批中,100-审批通过,90-技术驳回,80-归口驳回,70-资产科驳回)*/
	private String source;

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产报废表主键");
		eiMetadata.addMeta(eiColumn);


		eiColumn = new EiColumn("faInfoId");
		eiColumn.setDescName("资产id");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("detailId");
		eiColumn.setDescName("报废明细表id");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsNum");
		eiColumn.setDescName("资产编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsName");
		eiColumn.setDescName("资产名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("spec");
		eiColumn.setDescName("型号规格");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyCode");
		eiColumn.setDescName("资产类别编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyName");
		eiColumn.setDescName("资产类别名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("scrappedNo");
		eiColumn.setDescName("报废单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyDeptName");
		eiColumn.setDescName("申请科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyPerson");
		eiColumn.setDescName("申请人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyTime");
		eiColumn.setDescName("申请时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyReason");
		eiColumn.setDescName("申请理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyFileCode");
		eiColumn.setDescName("申请签字");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("identifyDeptName");
		eiColumn.setDescName("鉴定科");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("identifyPerson");
		eiColumn.setDescName("鉴定人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("identifyTime");
		eiColumn.setDescName("鉴定时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("identifyReason");
		eiColumn.setDescName("鉴定理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("functionDeptName");
		eiColumn.setDescName("归口科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("functionPerson");
		eiColumn.setDescName("归口人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("functionTime");
		eiColumn.setDescName("归口时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("functionReason");
		eiColumn.setDescName("归口理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetDeptName");
		eiColumn.setDescName("资产科");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetPerson");
		eiColumn.setDescName("资产确认人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetTime");
		eiColumn.setDescName("确认时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetReason");
		eiColumn.setDescName("确认理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("scrapStatus");
		eiColumn.setDescName("状态(00-申请中,10-鉴定中,20-归口确认中,30-资产审批中,100-审批通过,90-技术驳回,80-归口驳回,70-资产科驳回)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("source");
		eiColumn.setDescName("来源");
		eiMetadata.addMeta(eiColumn);
	}

	/**
	 * the constructor
	 */
	public FaScrapVO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产报废表主键
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 固定资产报废表主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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
	 * get the scrappedNo - 报废单号
	 *
	 * @return the scrappedNo
	 */
	public String getScrappedNo() {
		return this.scrappedNo;
	}

	/**
	 * set the scrappedNo - 报废单号
	 */
	public void setScrappedNo(String scrappedNo) {
		this.scrappedNo = scrappedNo;
	}


	/**
	 * get the applyDeptName - 申请科室
	 *
	 * @return the applyDeptName
	 */
	public String getApplyDeptName() {
		return this.applyDeptName;
	}

	/**
	 * set the applyDeptName - 申请科室
	 */
	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	/**
	 * get the applyPerson - 申请人
	 *
	 * @return the applyPerson
	 */
	public String getApplyPerson() {
		return this.applyPerson;
	}

	/**
	 * set the applyPerson - 申请人
	 */
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	/**
	 * get the applyTime - 申请时间
	 *
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return this.applyTime;
	}

	/**
	 * set the applyTime - 申请时间
	 */
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * get the applyReason - 申请理由
	 *
	 * @return the applyReason
	 */
	public String getApplyReason() {
		return this.applyReason;
	}

	/**
	 * set the applyReason - 申请理由
	 */
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getApplyFileCode() {
		return applyFileCode;
	}

	public void setApplyFileCode(String applyFileCode) {
		this.applyFileCode = applyFileCode;
	}

	/**
	 * get the identifyDeptName - 鉴定科
	 *
	 * @return the identifyDeptName
	 */
	public String getIdentifyDeptName() {
		return this.identifyDeptName;
	}

	/**
	 * set the identifyDeptName - 鉴定科
	 */
	public void setIdentifyDeptName(String identifyDeptName) {
		this.identifyDeptName = identifyDeptName;
	}

	/**
	 * get the identifyPerson - 鉴定人
	 *
	 * @return the identifyPerson
	 */
	public String getIdentifyPerson() {
		return this.identifyPerson;
	}

	/**
	 * set the identifyPerson - 鉴定人
	 */
	public void setIdentifyPerson(String identifyPerson) {
		this.identifyPerson = identifyPerson;
	}

	/**
	 * get the identifyTime - 鉴定时间
	 *
	 * @return the identifyTime
	 */
	public Date getIdentifyTime() {
		return this.identifyTime;
	}

	/**
	 * set the identifyTime - 鉴定时间
	 */
	public void setIdentifyTime(Timestamp identifyTime) {
		this.identifyTime = identifyTime;
	}

	/**
	 * get the identifyReason - 鉴定理由
	 *
	 * @return the identifyReason
	 */
	public String getIdentifyReason() {
		return this.identifyReason;
	}

	/**
	 * set the identifyReason - 鉴定理由
	 */
	public void setIdentifyReason(String identifyReason) {
		this.identifyReason = identifyReason;
	}

	/**
	 * get the functionDeptName - 归口科室
	 *
	 * @return the functionDeptName
	 */
	public String getFunctionDeptName() {
		return this.functionDeptName;
	}

	/**
	 * set the functionDeptName - 归口科室
	 */
	public void setFunctionDeptName(String functionDeptName) {
		this.functionDeptName = functionDeptName;
	}

	/**
	 * get the functionPerson - 归口人
	 *
	 * @return the functionPerson
	 */
	public String getFunctionPerson() {
		return this.functionPerson;
	}

	/**
	 * set the functionPerson - 归口人
	 */
	public void setFunctionPerson(String functionPerson) {
		this.functionPerson = functionPerson;
	}

	/**
	 * get the functionTime - 归口时间
	 *
	 * @return the functionTime
	 */
	public Date getFunctionTime() {
		return this.functionTime;
	}

	/**
	 * set the functionTime - 归口时间
	 */
	public void setFunctionTime(Timestamp functionTime) {
		this.functionTime = functionTime;
	}

	/**
	 * get the functionReason - 归口理由
	 *
	 * @return the functionReason
	 */
	public String getFunctionReason() {
		return this.functionReason;
	}

	/**
	 * set the functionReason - 归口理由
	 */
	public void setFunctionReason(String functionReason) {
		this.functionReason = functionReason;
	}

	/**
	 * get the assetDeptName - 资产科
	 *
	 * @return the assetDeptName
	 */
	public String getAssetDeptName() {
		return this.assetDeptName;
	}

	/**
	 * set the assetDeptName - 资产科
	 */
	public void setAssetDeptName(String assetDeptName) {
		this.assetDeptName = assetDeptName;
	}

	/**
	 * get the assetPerson - 资产确认人
	 *
	 * @return the assetPerson
	 */
	public String getAssetPerson() {
		return this.assetPerson;
	}

	/**
	 * set the assetPerson - 资产确认人
	 */
	public void setAssetPerson(String assetPerson) {
		this.assetPerson = assetPerson;
	}

	/**
	 * get the assetTime - 确认时间
	 *
	 * @return the assetTime
	 */
	public Date getAssetTime() {
		return this.assetTime;
	}

	/**
	 * set the assetTime - 确认时间
	 */
	public void setAssetTime(Timestamp assetTime) {
		this.assetTime = assetTime;
	}

	/**
	 * get the assetReason - 确认理由
	 *
	 * @return the assetReason
	 */
	public String getAssetReason() {
		return this.assetReason;
	}

	/**
	 * set the assetReason - 确认理由
	 */
	public void setAssetReason(String assetReason) {
		this.assetReason = assetReason;
	}

	/**
	 * get the scrapStatus - 状态(00-申请中,10-鉴定中,20-归口确认中,30-资产审批中,100-审批通过,90-技术驳回,80-归口驳回,70-资产科驳回)
	 *
	 * @return the scrapStatus
	 */
	public String getScrapStatus() {
		return this.scrapStatus;
	}

	/**
	 * set the scrapStatus - 状态(00-申请中,10-鉴定中,20-归口确认中,30-资产审批中,100-审批通过,90-技术驳回,80-归口驳回,70-资产科驳回)
	 */
	public void setScrapStatus(String scrapStatus) {
		this.scrapStatus = scrapStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setDetailId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("detailId")), detailId));
		setFaInfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
		setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
		setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
		setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
		setGoodsClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyCode")), goodsClassifyCode));
		setGoodsClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyName")), goodsClassifyName));
		setScrappedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrappedNo")), scrappedNo));
		setApplyDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyDeptName")), applyDeptName));
		setApplyPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyPerson")), applyPerson));
		setApplyTime(OneSelfUtils.defaultTimestamp(map.get("applyTime")));
		setApplyReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyReason")), applyReason));
		setApplyFileCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyFileCode")), applyFileCode));
		setIdentifyDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("identifyDeptName")), identifyDeptName));
		setIdentifyPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("identifyPerson")), identifyPerson));
		setIdentifyTime(OneSelfUtils.defaultTimestamp(map.get("identifyTime")));
		setIdentifyReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("identifyReason")), identifyReason));
		setFunctionDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("functionDeptName")), functionDeptName));
		setFunctionPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("functionPerson")), functionPerson));
		setFunctionTime(OneSelfUtils.defaultTimestamp(map.get("functionTime")));
		setFunctionReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("functionReason")), functionReason));
		setAssetDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetDeptName")), assetDeptName));
		setAssetPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetPerson")), assetPerson));
		setAssetTime(OneSelfUtils.defaultTimestamp(map.get("assetTime")));
		setAssetReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetReason")), assetReason));
		setScrapStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapStatus")), scrapStatus));
		setSource(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("source")), source));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("faInfoId", StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoId")));
		map.put("detailId", StringUtils.toString(detailId, eiMetadata.getMeta("detailId")));
		map.put("goodsNum", StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
		map.put("goodsName", StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
		map.put("spec", StringUtils.toString(spec, eiMetadata.getMeta("spec")));
		map.put("goodsClassifyCode", StringUtils.toString(goodsClassifyCode, eiMetadata.getMeta("goodsClassifyCode")));
		map.put("goodsClassifyName", StringUtils.toString(goodsClassifyName, eiMetadata.getMeta("goodsClassifyName")));
		map.put("scrappedNo", StringUtils.toString(scrappedNo, eiMetadata.getMeta("scrappedNo")));
		map.put("applyDeptName", StringUtils.toString(applyDeptName, eiMetadata.getMeta("applyDeptName")));
		map.put("applyPerson", StringUtils.toString(applyPerson, eiMetadata.getMeta("applyPerson")));
		map.put("applyTime", StringUtils.toString(applyTime, eiMetadata.getMeta("applyTime")));
		map.put("applyReason", StringUtils.toString(applyReason, eiMetadata.getMeta("applyReason")));
		map.put("applyFileCode", StringUtils.toString(applyFileCode, eiMetadata.getMeta("applyFileCode")));
		map.put("identifyDeptName", StringUtils.toString(identifyDeptName, eiMetadata.getMeta("identifyDeptName")));
		map.put("identifyPerson", StringUtils.toString(identifyPerson, eiMetadata.getMeta("identifyPerson")));
		map.put("identifyTime", StringUtils.toString(identifyTime, eiMetadata.getMeta("identifyTime")));
		map.put("identifyReason", StringUtils.toString(identifyReason, eiMetadata.getMeta("identifyReason")));
		map.put("functionDeptName", StringUtils.toString(functionDeptName, eiMetadata.getMeta("functionDeptName")));
		map.put("functionPerson", StringUtils.toString(functionPerson, eiMetadata.getMeta("functionPerson")));
		map.put("functionTime", StringUtils.toString(functionTime, eiMetadata.getMeta("functionTime")));
		map.put("functionReason", StringUtils.toString(functionReason, eiMetadata.getMeta("functionReason")));
		map.put("assetDeptName", StringUtils.toString(assetDeptName, eiMetadata.getMeta("assetDeptName")));
		map.put("assetPerson", StringUtils.toString(assetPerson, eiMetadata.getMeta("assetPerson")));
		map.put("assetTime", StringUtils.toString(assetTime, eiMetadata.getMeta("assetTime")));
		map.put("assetReason", StringUtils.toString(assetReason, eiMetadata.getMeta("assetReason")));
		map.put("scrapStatus", StringUtils.toString(scrapStatus, eiMetadata.getMeta("scrapStatus")));
		map.put("source", StringUtils.toString(source, eiMetadata.getMeta("source")));
		return map;
	}
}