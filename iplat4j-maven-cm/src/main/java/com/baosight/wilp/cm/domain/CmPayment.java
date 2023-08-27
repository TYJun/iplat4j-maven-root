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
* CmPayment
* 
*/
public class CmPayment extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* id*/
    private String paymentAutoNo = " ";		/* 付款管理号*/
    private String paymentNo = " ";		/* 付款单号*/
    private String paymentContent = " ";		/* 付款内容*/
    private String paymentType = " ";		/* 付款方式*/
    private String contPk = " ";		/* 合同主键*/
    private String contNo = " ";		/* 合同号*/
    private String contName = " ";		/* 合同名称*/
    private String invoiceAutoNo = " ";		/* 发票管理号*/
    private String currType = " ";		/* 币种*/
    private Double paymentTaxExcludeAmount = new Double(0.00);		/* 付款金额*/
    private Double paymentTaxAmount = new Double(0.00);		/* 付款税额*/
    private String paymentTime = " ";		/* 付款时间*/
    private String remark = " ";		/* 备注*/
    private String paymentStatus = " ";		/* 付款单状态*/
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
        eiColumn.setDescName("id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentAutoNo");
        eiColumn.setDescName("付款管理号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentNo");
        eiColumn.setDescName("付款单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentContent");
        eiColumn.setDescName("付款内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentType");
        eiColumn.setDescName("付款方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contPk");
        eiColumn.setDescName("合同主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceAutoNo");
        eiColumn.setDescName("发票管理号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currType");
        eiColumn.setDescName("币种");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentTaxExcludeAmount");
        eiColumn.setDescName("付款金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentTaxAmount");
        eiColumn.setDescName("付款税额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentTime");
        eiColumn.setDescName("付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paymentStatus");
        eiColumn.setDescName("付款单状态");
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
    public CmPayment() {
        initMetaData();
    }

    /**
     * get the id - id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the paymentAutoNo - 付款管理号
     * @return the paymentAutoNo
     */
    public String getPaymentAutoNo() {
        return this.paymentAutoNo;
    }

    /**
     * set the paymentAutoNo - 付款管理号
     */
    public void setPaymentAutoNo(String paymentAutoNo) {
        this.paymentAutoNo = paymentAutoNo;
    }

    /**
     * get the paymentNo - 付款单号
     * @return the paymentNo
     */
    public String getPaymentNo() {
        return this.paymentNo;
    }

    /**
     * set the paymentNo - 付款单号
     */
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    /**
     * get the paymentContent - 付款内容
     * @return the paymentContent
     */
    public String getPaymentContent() {
        return this.paymentContent;
    }

    /**
     * set the paymentContent - 付款内容
     */
    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }

    /**
     * get the paymentType - 付款方式
     * @return the paymentType
     */
    public String getPaymentType() {
        return this.paymentType;
    }

    /**
     * set the paymentType - 付款方式
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    /**
     * get the invoiceAutoNo - 发票管理号
     * @return the invoiceAutoNo
     */
    public String getInvoiceAutoNo() {
        return this.invoiceAutoNo;
    }

    /**
     * set the invoiceAutoNo - 发票管理号
     */
    public void setInvoiceAutoNo(String invoiceAutoNo) {
        this.invoiceAutoNo = invoiceAutoNo;
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
     * get the paymentTaxExcludeAmount - 付款金额
     * @return the paymentTaxExcludeAmount
     */
    public Double getPaymentTaxExcludeAmount() {
        return this.paymentTaxExcludeAmount;
    }

    /**
     * set the paymentTaxExcludeAmount - 付款金额
     */
    public void setPaymentTaxExcludeAmount(Double paymentTaxExcludeAmount) {
        this.paymentTaxExcludeAmount = paymentTaxExcludeAmount;
    }

    /**
     * get the paymentTaxAmount - 付款税额
     * @return the paymentTaxAmount
     */
    public Double getPaymentTaxAmount() {
        return this.paymentTaxAmount;
    }

    /**
     * set the paymentTaxAmount - 付款税额
     */
    public void setPaymentTaxAmount(Double paymentTaxAmount) {
        this.paymentTaxAmount = paymentTaxAmount;
    }

    /**
     * get the paymentTime - 付款时间
     * @return the paymentTime
     */
    public String getPaymentTime() {
        return this.paymentTime;
    }

    /**
     * set the paymentTime - 付款时间
     */
    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
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
     * get the paymentStatus - 付款单状态
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    /**
     * set the paymentStatus - 付款单状态
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        setPaymentAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentAutoNo")), paymentAutoNo));
        setPaymentNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentNo")), paymentNo));
        setPaymentContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentContent")), paymentContent));
        setPaymentType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentType")), paymentType));
        setContPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contPk")), contPk));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setContName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contName")), contName));
        setInvoiceAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceAutoNo")), invoiceAutoNo));
        setCurrType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currType")), currType));
        setPaymentTaxExcludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("paymentTaxExcludeAmount")), paymentTaxExcludeAmount));
        setPaymentTaxAmount(NumberUtils.toDouble(StringUtils.toString(map.get("paymentTaxAmount")), paymentTaxAmount));
        setPaymentTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentTime")), paymentTime));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setPaymentStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paymentStatus")), paymentStatus));
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
        map.put("paymentAutoNo",StringUtils.toString(paymentAutoNo, eiMetadata.getMeta("paymentAutoNo")));
        map.put("paymentNo",StringUtils.toString(paymentNo, eiMetadata.getMeta("paymentNo")));
        map.put("paymentContent",StringUtils.toString(paymentContent, eiMetadata.getMeta("paymentContent")));
        map.put("paymentType",StringUtils.toString(paymentType, eiMetadata.getMeta("paymentType")));
        map.put("contPk",StringUtils.toString(contPk, eiMetadata.getMeta("contPk")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("invoiceAutoNo",StringUtils.toString(invoiceAutoNo, eiMetadata.getMeta("invoiceAutoNo")));
        map.put("currType",StringUtils.toString(currType, eiMetadata.getMeta("currType")));
        map.put("paymentTaxExcludeAmount",StringUtils.toString(paymentTaxExcludeAmount, eiMetadata.getMeta("paymentTaxExcludeAmount")));
        map.put("paymentTaxAmount",StringUtils.toString(paymentTaxAmount, eiMetadata.getMeta("paymentTaxAmount")));
        map.put("paymentTime",StringUtils.toString(paymentTime, eiMetadata.getMeta("paymentTime")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("paymentStatus",StringUtils.toString(paymentStatus, eiMetadata.getMeta("paymentStatus")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recRevisorNo",StringUtils.toString(recRevisorNo, eiMetadata.getMeta("recRevisorNo")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}