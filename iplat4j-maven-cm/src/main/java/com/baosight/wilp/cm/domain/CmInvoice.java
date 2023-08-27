/**
* Generate time : 2021-12-31 15:31:12
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CmInvoice
* 
*/
public class CmInvoice extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 合同发票主键*/
    private String invoiceAutoNo = " ";		/* 合同管理号*/
    private String invoiceNo = " ";		/* 发票号*/
    private String surpNum = " ";		/* 开票单位编号*/
    private String surpName = " ";		/* 开票单位名称*/
    private String surpLinkMan = " ";		/* 开票单位联系人*/
    private String surpTel = " ";		/* 开票单位联系方式*/
    private String invoiceType = " ";		/* 发票类别*/
    private String currType = " ";		/* 币种*/
    private Double invoiceTaxExcludeAmount = new Double(0.00);		/* 发票金额（元）*/
    private Double invoiceTaxAmount = new Double(0.00);		/* 发票税额（元）*/
    private String invoiceIssuingTime = " ";		/* 发票开付时间*/
    private String contNo = " ";		/* 合同号*/
    private String contPk = " ";		/* 合同主键*/
    private String remark = " ";		/* 备注*/
    private String invoiceStatus = " ";		/* 发票状态*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recRevisorNo = " ";		/* 修改人工号*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("合同发票主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceAutoNo");
        eiColumn.setDescName("合同管理号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceNo");
        eiColumn.setDescName("发票号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("开票单位编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("开票单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpLinkMan");
        eiColumn.setDescName("开票单位联系人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpTel");
        eiColumn.setDescName("开票单位联系方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceType");
        eiColumn.setDescName("发票类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currType");
        eiColumn.setDescName("币种");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxExcludeAmount");
        eiColumn.setDescName("发票金额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxAmount");
        eiColumn.setDescName("发票税额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceIssuingTime");
        eiColumn.setDescName("发票开付时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contPk");
        eiColumn.setDescName("合同主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceStatus");
        eiColumn.setDescName("发票状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorNo");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisorNo");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CmInvoice() {
        initMetaData();
    }

    /**
     * get the id - 合同发票主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 合同发票主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the invoiceAutoNo - 合同管理号
     * @return the invoiceAutoNo
     */
    public String getInvoiceAutoNo() {
        return this.invoiceAutoNo;
    }

    /**
     * set the invoiceAutoNo - 合同管理号
     */
    public void setInvoiceAutoNo(String invoiceAutoNo) {
        this.invoiceAutoNo = invoiceAutoNo;
    }

    /**
     * get the invoiceNo - 发票号
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    /**
     * set the invoiceNo - 发票号
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * get the surpNum - 开票单位编号
     * @return the surpNum
     */
    public String getSurpNum() {
        return this.surpNum;
    }

    /**
     * set the surpNum - 开票单位编号
     */
    public void setSurpNum(String surpNum) {
        this.surpNum = surpNum;
    }

    /**
     * get the surpName - 开票单位名称
     * @return the surpName
     */
    public String getSurpName() {
        return this.surpName;
    }

    /**
     * set the surpName - 开票单位名称
     */
    public void setSurpName(String surpName) {
        this.surpName = surpName;
    }

    /**
     * get the surpLinkMan - 开票单位联系人
     * @return the surpLinkMan
     */
    public String getSurpLinkMan() {
        return this.surpLinkMan;
    }

    /**
     * set the surpLinkMan - 开票单位联系人
     */
    public void setSurpLinkMan(String surpLinkMan) {
        this.surpLinkMan = surpLinkMan;
    }

    /**
     * get the surpTel - 开票单位联系方式
     * @return the surpTel
     */
    public String getSurpTel() {
        return this.surpTel;
    }

    /**
     * set the surpTel - 开票单位联系方式
     */
    public void setSurpTel(String surpTel) {
        this.surpTel = surpTel;
    }

    /**
     * get the invoiceType - 发票类别
     * @return the invoiceType
     */
    public String getInvoiceType() {
        return this.invoiceType;
    }

    /**
     * set the invoiceType - 发票类别
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * get the currType - 币种
     * @return the currType
     */
    public String getCurrType() {
        return this.currType;
    }

    /**
     * set the currType - 币种
     */
    public void setCurrType(String currType) {
        this.currType = currType;
    }

    /**
     * get the invoiceTaxExcludeAmount - 发票金额（元）
     * @return the invoiceTaxExcludeAmount
     */
    public Double getInvoiceTaxExcludeAmount() {
        return this.invoiceTaxExcludeAmount;
    }

    /**
     * set the invoiceTaxExcludeAmount - 发票金额（元）
     */
    public void setInvoiceTaxExcludeAmount(Double invoiceTaxExcludeAmount) {
        this.invoiceTaxExcludeAmount = invoiceTaxExcludeAmount;
    }

    /**
     * get the invoiceTaxAmount - 发票税额（元）
     * @return the invoiceTaxAmount
     */
    public Double getInvoiceTaxAmount() {
        return this.invoiceTaxAmount;
    }

    /**
     * set the invoiceTaxAmount - 发票税额（元）
     */
    public void setInvoiceTaxAmount(Double invoiceTaxAmount) {
        this.invoiceTaxAmount = invoiceTaxAmount;
    }

    /**
     * get the invoiceIssuingTime - 发票开付时间
     * @return the invoiceIssuingTime
     */
    public String getInvoiceIssuingTime() {
        return this.invoiceIssuingTime;
    }

    /**
     * set the invoiceIssuingTime - 发票开付时间
     */
    public void setInvoiceIssuingTime(String invoiceIssuingTime) {
        this.invoiceIssuingTime = invoiceIssuingTime;
    }

    /**
     * get the contNo - 合同号
     * @return the contNo
     */
    public String getContNo() {
        return this.contNo;
    }

    /**
     * set the contNo - 合同号
     */
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    /**
     * get the contPk - 合同主键
     * @return the contPk
     */
    public String getContPk() {
        return this.contPk;
    }

    /**
     * set the contPk - 合同主键
     */
    public void setContPk(String contPk) {
        this.contPk = contPk;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the invoiceStatus - 发票状态
     * @return the invoiceStatus
     */
    public String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    /**
     * set the invoiceStatus - 发票状态
     */
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorNo - 创建人工号
     * @return the recCreatorNo
     */
    public String getRecCreatorNo() {
        return this.recCreatorNo;
    }

    /**
     * set the recCreatorNo - 创建人工号
     */
    public void setRecCreatorNo(String recCreatorNo) {
        this.recCreatorNo = recCreatorNo;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recRevisorNo - 修改人工号
     * @return the recRevisorNo
     */
    public String getRecRevisorNo() {
        return this.recRevisorNo;
    }

    /**
     * set the recRevisorNo - 修改人工号
     */
    public void setRecRevisorNo(String recRevisorNo) {
        this.recRevisorNo = recRevisorNo;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setInvoiceAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceAutoNo")), invoiceAutoNo));
        setInvoiceNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceNo")), invoiceNo));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setSurpLinkMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpLinkMan")), surpLinkMan));
        setSurpTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpTel")), surpTel));
        setInvoiceType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceType")), invoiceType));
        setCurrType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currType")), currType));
        setInvoiceTaxExcludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("invoiceTaxExcludeAmount")), invoiceTaxExcludeAmount));
        setInvoiceTaxAmount(NumberUtils.toDouble(StringUtils.toString(map.get("invoiceTaxAmount")), invoiceTaxAmount));
        setInvoiceIssuingTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceIssuingTime")), invoiceIssuingTime));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setContPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contPk")), contPk));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setInvoiceStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceStatus")), invoiceStatus));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecRevisorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorNo")), recRevisorNo));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("invoiceAutoNo",StringUtils.toString(invoiceAutoNo, eiMetadata.getMeta("invoiceAutoNo")));
        map.put("invoiceNo",StringUtils.toString(invoiceNo, eiMetadata.getMeta("invoiceNo")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("surpLinkMan",StringUtils.toString(surpLinkMan, eiMetadata.getMeta("surpLinkMan")));
        map.put("surpTel",StringUtils.toString(surpTel, eiMetadata.getMeta("surpTel")));
        map.put("invoiceType",StringUtils.toString(invoiceType, eiMetadata.getMeta("invoiceType")));
        map.put("currType",StringUtils.toString(currType, eiMetadata.getMeta("currType")));
        map.put("invoiceTaxExcludeAmount",StringUtils.toString(invoiceTaxExcludeAmount, eiMetadata.getMeta("invoiceTaxExcludeAmount")));
        map.put("invoiceTaxAmount",StringUtils.toString(invoiceTaxAmount, eiMetadata.getMeta("invoiceTaxAmount")));
        map.put("invoiceIssuingTime",StringUtils.toString(invoiceIssuingTime, eiMetadata.getMeta("invoiceIssuingTime")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contPk",StringUtils.toString(contPk, eiMetadata.getMeta("contPk")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("invoiceStatus",StringUtils.toString(invoiceStatus, eiMetadata.getMeta("invoiceStatus")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recRevisorNo",StringUtils.toString(recRevisorNo, eiMetadata.getMeta("recRevisorNo")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}