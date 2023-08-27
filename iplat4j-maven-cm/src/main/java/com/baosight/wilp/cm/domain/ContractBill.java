/**
* Generate time : 2021-04-09 21:38:14
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 合同主表实体类
 * 
 * @Title: ContractBill.java
 * @ClassName: ContractBill
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:40:34
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBill extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		
    private String contNo = " ";		/* 合同号*/
    private String originBillNo = " ";		/* 来源单据号*/
    private String originBillType = " ";		/* 来源单据类型*/
    private String contName = " ";		/* 合同名称*/
    private String contTypeNum = " ";		/* 合同类型*/
    private String contSignTime = " ";		/* 合同签订日期*/
    private String planTakeefTime = " ";		/* 计划生效日期*/
    private String planFinishTime = " ";		/* 计划终止日期*/
    private String surpNum = " ";		/* 供应商*/
    private String surpName = " ";		/* 供应商名字*/
    private String currType = " ";		/* 币种*/
    private Double contTaxExcludeAmount = new Double(0.00);		/* 合同不含税金额*/
    private Double contTaxIncludeAmount = new Double(0.00);		/* 合同含税金额*/
    private Integer contTaxRate = new Integer(0);		/* 税率*/
    private Double contTaxAmount = new Double(0.00);		/* 合同税额*/
    private Double contFeeAmount = new Double(0.00);		/* 合同费用总金额*/
    private Double invoiceTaxExcludeAmount = new Double(0.00);		/* 发票金额 不含税*/
    private Double invoiceTaxIncludeAmount = new Double(0.00);		/* 发票金额 含税*/
    private Double invoiceTaxAmount = new Double(0.00);		/* 发票税额*/
    private Double settleTaxExcludeAmount = new Double(0.00);		/* 结算金额 不含税*/
    private Double settleTaxIncludeAmount = new Double(0.00);		/* 结算金额 含税*/
    private Double settleTaxAmount = new Double(0.00);		/* 结算税额*/
    private String settleTime = " ";		/* 结算时间*/
    private String payAgreNum = " ";		/* 付款协议*/
    private String contStatus = "0";		/* 合同状态*/
    private String contAdmin = " ";		/* 合同管理员*/
    private String contAdminName = " ";		/* 合同管理员名字*/
    private String billMaker = " ";		/* 制单人*/
    private Timestamp billMakeTime ;		/* 制表时间*/
    private Timestamp checkTime ;		/* 审批时间*/
    private String checkMaker = " ";		/* 审批人*/
    private String finishMaker = " ";		/* 结案人*/
    private Timestamp finishTime ;		/* 结案时间*/
    private String endMaker = " ";		/* 终止人*/
    private Timestamp endTime ;		/* 终止时间*/
    private Integer quarPeriod = new Integer(0);		/* 质保期*/
    private Double budget = new Double(0.00);		/* 预算*/
    private String prePayDate = " ";		
    private String prono = " ";		/* 项目号*/
    private Double checkMoney = new Double(0.00);		/* 审计后金额*/
    private String contDeptNum = " ";		/* 相关部门编号*/
    private String contDeptName = " ";		/* 相关部门名字*/
    private String yesorno = " ";		/* 是否发送短信*/
    private String xuqianContno = " ";		/* 续签主合同号*/
    private String msgStatus = " ";		
    private String deviceNum = " ";		
    private String smsConfig1 = " ";		/*  是否发送过*/
    private String projectId = " ";		/*  项目id*/
    private String projectName = " ";		/*  项目名称*/
    private String scheduleAutoNo = " ";
    private String scheduleName = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectId");
        eiColumn.setDescName("项目id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectName");
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevsior");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillNo");
        eiColumn.setDescName("来源单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillType");
        eiColumn.setDescName("来源单据类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTypeNum");
        eiColumn.setDescName("合同类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contSignTime");
        eiColumn.setDescName("合同签订日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planTakeefTime");
        eiColumn.setDescName("计划生效日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planFinishTime");
        eiColumn.setDescName("计划终止日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("供应商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("供应商名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currType");
        eiColumn.setDescName("币种");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTaxExcludeAmount");
        eiColumn.setDescName("合同不含税金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTaxIncludeAmount");
        eiColumn.setDescName("合同含税金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTaxRate");
        eiColumn.setDescName("税率");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTaxAmount");
        eiColumn.setDescName("合同税额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contFeeAmount");
        eiColumn.setDescName("合同费用总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxExcludeAmount");
        eiColumn.setDescName("发票金额 不含税");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxIncludeAmount");
        eiColumn.setDescName("发票金额 含税");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxAmount");
        eiColumn.setDescName("发票税额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("settleTaxExcludeAmount");
        eiColumn.setDescName("结算金额 不含税");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("settleTaxIncludeAmount");
        eiColumn.setDescName("结算金额 含税");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("settleTaxAmount");
        eiColumn.setDescName("结算税额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("settleTime");
        eiColumn.setDescName("结算时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payAgreNum");
        eiColumn.setDescName("付款协议");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contStatus");
        eiColumn.setDescName("合同状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contAdmin");
        eiColumn.setDescName("合同管理员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contAdminName");
        eiColumn.setDescName("合同管理员名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMaker");
        eiColumn.setDescName("制单人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakeTime");
        eiColumn.setDescName("制表时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkTime");
        eiColumn.setDescName("审批时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkMaker");
        eiColumn.setDescName("审批人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishMaker");
        eiColumn.setDescName("结案人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishTime");
        eiColumn.setDescName("结案时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endMaker");
        eiColumn.setDescName("终止人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endTime");
        eiColumn.setDescName("终止时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quarPeriod");
        eiColumn.setDescName("质保期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("budget");
        eiColumn.setDescName("预算");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("prePayDate");
        eiColumn.setDescName("项目付款日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("prono");
        eiColumn.setDescName("项目号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkMoney");
        eiColumn.setDescName("审计后金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contDeptNum");
        eiColumn.setDescName("相关部门编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contDeptName");
        eiColumn.setDescName("相关部门名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("yesorno");
        eiColumn.setDescName("是否发送短信");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("xuqianContno");
        eiColumn.setDescName("续签主合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("msgStatus");
        eiColumn.setDescName("消息状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deviceNum");
        eiColumn.setDescName("设备编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsConfig1");
        eiColumn.setDescName("是否发送过");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleAutoNo");
        eiColumn.setDescName("合同进度编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleName");
        eiColumn.setDescName("合同进度名称");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public ContractBill() {
        initMetaData();
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreator
     * @return the recCreator
     */
    public String getProjectId() {
        return this.projectId;
    }

    /**
     * set the recCreator
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * get the recCreator
     * @return the recCreator
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * set the recCreator
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevsior 
     * @return the recRevsior
     */
    public String getRecRevsior() {
        return this.recRevsior;
    }

    /**
     * set the recRevsior 
     */
    public void setRecRevsior(String recRevsior) {
        this.recRevsior = recRevsior;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
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
     * get the originBillNo - 来源单据号
     * @return the originBillNo
     */
    public String getOriginBillNo() {
        return this.originBillNo;
    }

    /**
     * set the originBillNo - 来源单据号
     */
    public void setOriginBillNo(String originBillNo) {
        this.originBillNo = originBillNo;
    }

    /**
     * get the originBillType - 来源单据类型
     * @return the originBillType
     */
    public String getOriginBillType() {
        return this.originBillType;
    }

    /**
     * set the originBillType - 来源单据类型
     */
    public void setOriginBillType(String originBillType) {
        this.originBillType = originBillType;
    }

    /**
     * get the contName - 合同名称
     * @return the contName
     */
    public String getContName() {
        return this.contName;
    }

    /**
     * set the contName - 合同名称
     */
    public void setContName(String contName) {
        this.contName = contName;
    }

    /**
     * get the contTypeNum - 合同类型
     * @return the contTypeNum
     */
    public String getContTypeNum() {
        return this.contTypeNum;
    }

    /**
     * set the contTypeNum - 合同类型
     */
    public void setContTypeNum(String contTypeNum) {
        this.contTypeNum = contTypeNum;
    }

    /**
     * get the contSignTime - 合同签订日期
     * @return the contSignTime
     */
    public String getContSignTime() {
        return this.contSignTime;
    }

    /**
     * set the contSignTime - 合同签订日期
     */
    public void setContSignTime(String contSignTime) {
        this.contSignTime = contSignTime;
    }

    /**
     * get the planTakeefTime - 计划生效日期
     * @return the planTakeefTime
     */
    public String getPlanTakeefTime() {
        return this.planTakeefTime;
    }

    /**
     * set the planTakeefTime - 计划生效日期
     */
    public void setPlanTakeefTime(String planTakeefTime) {
        this.planTakeefTime = planTakeefTime;
    }

    /**
     * get the planFinishTime - 计划终止日期
     * @return the planFinishTime
     */
    public String getPlanFinishTime() {
        return this.planFinishTime;
    }

    /**
     * set the planFinishTime - 计划终止日期
     */
    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    /**
     * get the surpNum - 供应商
     * @return the surpNum
     */
    public String getSurpNum() {
        return this.surpNum;
    }

    /**
     * set the surpNum - 供应商
     */
    public void setSurpNum(String surpNum) {
        this.surpNum = surpNum;
    }

    /**
     * get the surpName - 供应商名字
     * @return the surpName
     */
    public String getSurpName() {
        return this.surpName;
    }

    /**
     * set the surpName - 供应商名字
     */
    public void setSurpName(String surpName) {
        this.surpName = surpName;
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
     * get the contTaxExcludeAmount - 合同不含税金额
     * @return the contTaxExcludeAmount
     */
    public Double getContTaxExcludeAmount() {
        return this.contTaxExcludeAmount;
    }

    /**
     * set the contTaxExcludeAmount - 合同不含税金额
     */
    public void setContTaxExcludeAmount(Double contTaxExcludeAmount) {
        this.contTaxExcludeAmount = contTaxExcludeAmount;
    }

    /**
     * get the contTaxIncludeAmount - 合同含税金额
     * @return the contTaxIncludeAmount
     */
    public Double getContTaxIncludeAmount() {
        return this.contTaxIncludeAmount;
    }

    /**
     * set the contTaxIncludeAmount - 合同含税金额
     */
    public void setContTaxIncludeAmount(Double contTaxIncludeAmount) {
        this.contTaxIncludeAmount = contTaxIncludeAmount;
    }

    /**
     * get the contTaxRate - 税率
     * @return the contTaxRate
     */
    public Integer getContTaxRate() {
        return this.contTaxRate;
    }

    /**
     * set the contTaxRate - 税率
     */
    public void setContTaxRate(Integer contTaxRate) {
        this.contTaxRate = contTaxRate;
    }

    /**
     * get the contTaxAmount - 合同税额
     * @return the contTaxAmount
     */
    public Double getContTaxAmount() {
        return this.contTaxAmount;
    }

    /**
     * set the contTaxAmount - 合同税额
     */
    public void setContTaxAmount(Double contTaxAmount) {
        this.contTaxAmount = contTaxAmount;
    }

    /**
     * get the contFeeAmount - 合同费用总金额
     * @return the contFeeAmount
     */
    public Double getContFeeAmount() {
        return this.contFeeAmount;
    }

    /**
     * set the contFeeAmount - 合同费用总金额
     */
    public void setContFeeAmount(Double contFeeAmount) {
        this.contFeeAmount = contFeeAmount;
    }

    /**
     * get the invoiceTaxExcludeAmount - 发票金额 不含税
     * @return the invoiceTaxExcludeAmount
     */
    public Double getInvoiceTaxExcludeAmount() {
        return this.invoiceTaxExcludeAmount;
    }

    /**
     * set the invoiceTaxExcludeAmount - 发票金额 不含税
     */
    public void setInvoiceTaxExcludeAmount(Double invoiceTaxExcludeAmount) {
        this.invoiceTaxExcludeAmount = invoiceTaxExcludeAmount;
    }

    /**
     * get the invoiceTaxIncludeAmount - 发票金额 含税
     * @return the invoiceTaxIncludeAmount
     */
    public Double getInvoiceTaxIncludeAmount() {
        return this.invoiceTaxIncludeAmount;
    }

    /**
     * set the invoiceTaxIncludeAmount - 发票金额 含税
     */
    public void setInvoiceTaxIncludeAmount(Double invoiceTaxIncludeAmount) {
        this.invoiceTaxIncludeAmount = invoiceTaxIncludeAmount;
    }

    /**
     * get the invoiceTaxAmount - 发票税额
     * @return the invoiceTaxAmount
     */
    public Double getInvoiceTaxAmount() {
        return this.invoiceTaxAmount;
    }

    /**
     * set the invoiceTaxAmount - 发票税额
     */
    public void setInvoiceTaxAmount(Double invoiceTaxAmount) {
        this.invoiceTaxAmount = invoiceTaxAmount;
    }

    /**
     * get the settleTaxExcludeAmount - 结算金额 不含税
     * @return the settleTaxExcludeAmount
     */
    public Double getSettleTaxExcludeAmount() {
        return this.settleTaxExcludeAmount;
    }

    /**
     * set the settleTaxExcludeAmount - 结算金额 不含税
     */
    public void setSettleTaxExcludeAmount(Double settleTaxExcludeAmount) {
        this.settleTaxExcludeAmount = settleTaxExcludeAmount;
    }

    /**
     * get the settleTaxIncludeAmount - 结算金额 含税
     * @return the settleTaxIncludeAmount
     */
    public Double getSettleTaxIncludeAmount() {
        return this.settleTaxIncludeAmount;
    }

    /**
     * set the settleTaxIncludeAmount - 结算金额 含税
     */
    public void setSettleTaxIncludeAmount(Double settleTaxIncludeAmount) {
        this.settleTaxIncludeAmount = settleTaxIncludeAmount;
    }

    /**
     * get the settleTaxAmount - 结算税额
     * @return the settleTaxAmount
     */
    public Double getSettleTaxAmount() {
        return this.settleTaxAmount;
    }

    /**
     * set the settleTaxAmount - 结算税额
     */
    public void setSettleTaxAmount(Double settleTaxAmount) {
        this.settleTaxAmount = settleTaxAmount;
    }

    /**
     * get the settleTime - 结算时间
     * @return the settleTime
     */
    public String getSettleTime() {
        return this.settleTime;
    }

    /**
     * set the settleTime - 结算时间
     */
    public void setSettleTime(String settleTime) {
        this.settleTime = settleTime;
    }

    /**
     * get the payAgreNum - 付款协议
     * @return the payAgreNum
     */
    public String getPayAgreNum() {
        return this.payAgreNum;
    }

    /**
     * set the payAgreNum - 付款协议
     */
    public void setPayAgreNum(String payAgreNum) {
        this.payAgreNum = payAgreNum;
    }

    /**
     * get the contStatus - 合同状态
     * @return the contStatus
     */
    public String getContStatus() {
        return this.contStatus;
    }

    /**
     * set the contStatus - 合同状态
     */
    public void setContStatus(String contStatus) {
        this.contStatus = contStatus;
    }

    /**
     * get the contAdmin - 合同管理员
     * @return the contAdmin
     */
    public String getContAdmin() {
        return this.contAdmin;
    }

    /**
     * set the contAdmin - 合同管理员
     */
    public void setContAdmin(String contAdmin) {
        this.contAdmin = contAdmin;
    }

    /**
     * get the contAdminName - 合同管理员名字
     * @return the contAdminName
     */
    public String getContAdminName() {
        return this.contAdminName;
    }

    /**
     * set the contAdminName - 合同管理员名字
     */
    public void setContAdminName(String contAdminName) {
        this.contAdminName = contAdminName;
    }

    /**
     * get the billMaker - 制单人
     * @return the billMaker
     */
    public String getBillMaker() {
        return this.billMaker;
    }

    /**
     * set the billMaker - 制单人
     */
    public void setBillMaker(String billMaker) {
        this.billMaker = billMaker;
    }

    /**
     * get the billMakeTime - 制表时间
     * @return the billMakeTime
     */
    public Timestamp getBillMakeTime() {
        return this.billMakeTime;
    }

    /**
     * set the billMakeTime - 制表时间
     */
    public void setBillMakeTime(Timestamp billMakeTime) {
        this.billMakeTime = billMakeTime;
    }

    /**
     * get the checkTime - 审批时间
     * @return the checkTime
     */
    public Timestamp getCheckTime() {
        return this.checkTime;
    }

    /**
     * set the checkTime - 审批时间
     */
    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * get the checkMaker - 审批人
     * @return the checkMaker
     */
    public String getCheckMaker() {
        return this.checkMaker;
    }

    /**
     * set the checkMaker - 审批人
     */
    public void setCheckMaker(String checkMaker) {
        this.checkMaker = checkMaker;
    }

    /**
     * get the finishMaker - 结案人
     * @return the finishMaker
     */
    public String getFinishMaker() {
        return this.finishMaker;
    }

    /**
     * set the finishMaker - 结案人
     */
    public void setFinishMaker(String finishMaker) {
        this.finishMaker = finishMaker;
    }

    /**
     * get the finishTime - 结案时间
     * @return the finishTime
     */
    public Timestamp getFinishTime() {
        return this.finishTime;
    }

    /**
     * set the finishTime - 结案时间
     */
    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * get the endMaker - 终止人
     * @return the endMaker
     */
    public String getEndMaker() {
        return this.endMaker;
    }

    /**
     * set the endMaker - 终止人
     */
    public void setEndMaker(String endMaker) {
        this.endMaker = endMaker;
    }

    /**
     * get the endTime - 终止时间
     * @return the endTime
     */
    public Timestamp getEndTime() {
        return this.endTime;
    }

    /**
     * set the endTime - 终止时间
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * get the quarPeriod - 质保期
     * @return the quarPeriod
     */
    public Integer getQuarPeriod() {
        return this.quarPeriod;
    }

    /**
     * set the quarPeriod - 质保期
     */
    public void setQuarPeriod(Integer quarPeriod) {
        this.quarPeriod = quarPeriod;
    }

    /**
     * get the budget - 预算
     * @return the budget
     */
    public Double getBudget() {
        return this.budget;
    }

    /**
     * set the budget - 预算
     */
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    /**
     * get the prePayDate 
     * @return the prePayDate
     */
    public String getPrePayDate() {
        return this.prePayDate;
    }

    /**
     * set the prePayDate 
     */
    public void setPrePayDate(String prePayDate) {
        this.prePayDate = prePayDate;
    }

    /**
     * get the prono - 项目号
     * @return the prono
     */
    public String getProno() {
        return this.prono;
    }

    /**
     * set the prono - 项目号
     */
    public void setProno(String prono) {
        this.prono = prono;
    }

    /**
     * get the checkMoney - 审计后金额
     * @return the checkMoney
     */
    public Double getCheckMoney() {
        return this.checkMoney;
    }

    /**
     * set the checkMoney - 审计后金额
     */
    public void setCheckMoney(Double checkMoney) {
        this.checkMoney = checkMoney;
    }

    /**
     * get the contDeptNum - 相关部门编号
     * @return the contDeptNum
     */
    public String getContDeptNum() {
        return this.contDeptNum;
    }

    /**
     * set the contDeptNum - 相关部门编号
     */
    public void setContDeptNum(String contDeptNum) {
        this.contDeptNum = contDeptNum;
    }

    /**
     * get the contDeptName - 相关部门名字
     * @return the contDeptName
     */
    public String getContDeptName() {
        return this.contDeptName;
    }

    /**
     * set the contDeptName - 相关部门名字
     */
    public void setContDeptName(String contDeptName) {
        this.contDeptName = contDeptName;
    }

    /**
     * get the yesorno - 是否发送短信
     * @return the yesorno
     */
    public String getYesorno() {
        return this.yesorno;
    }

    /**
     * set the yesorno - 是否发送短信
     */
    public void setYesorno(String yesorno) {
        this.yesorno = yesorno;
    }

    /**
     * get the xuqianContno - 续签主合同号
     * @return the xuqianContno
     */
    public String getXuqianContno() {
        return this.xuqianContno;
    }

    /**
     * set the xuqianContno - 续签主合同号
     */
    public void setXuqianContno(String xuqianContno) {
        this.xuqianContno = xuqianContno;
    }

    /**
     * get the msgStatus 
     * @return the msgStatus
     */
    public String getMsgStatus() {
        return this.msgStatus;
    }

    /**
     * set the msgStatus 
     */
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     * get the deviceNum 
     * @return the deviceNum
     */
    public String getDeviceNum() {
        return this.deviceNum;
    }

    /**
     * set the deviceNum 
     */
    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    /**
     * get the smsConfig1 -  是否发送过
     * @return the smsConfig1
     */
    public String getSmsConfig1() {
        return this.smsConfig1;
    }

    /**
     * set the smsConfig1 -  是否发送过
     */
    public void setSmsConfig1(String smsConfig1) {
        this.smsConfig1 = smsConfig1;
    }

    public String getScheduleAutoNo() {
        return scheduleAutoNo;
    }

    public void setScheduleAutoNo(String scheduleAutoNo) {
        this.scheduleAutoNo = scheduleAutoNo;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevsior(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevsior")), recRevsior));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setOriginBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillNo")), originBillNo));
        setOriginBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillType")), originBillType));
        setContName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contName")), contName));
        setContTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contTypeNum")), contTypeNum));
        setContSignTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contSignTime")), contSignTime));
        setPlanTakeefTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planTakeefTime")), planTakeefTime));
        setPlanFinishTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planFinishTime")), planFinishTime));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setCurrType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currType")), currType));
        setContTaxExcludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("contTaxExcludeAmount")), contTaxExcludeAmount));
        setContTaxIncludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("contTaxIncludeAmount")), contTaxIncludeAmount));
        setContTaxRate(NumberUtils.toInteger(StringUtils.toString(map.get("contTaxRate")), contTaxRate));
        setContTaxAmount(NumberUtils.toDouble(StringUtils.toString(map.get("contTaxAmount")), contTaxAmount));
        setContFeeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("contFeeAmount")), contFeeAmount));
        setInvoiceTaxExcludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("invoiceTaxExcludeAmount")), invoiceTaxExcludeAmount));
        setInvoiceTaxIncludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("invoiceTaxIncludeAmount")), invoiceTaxIncludeAmount));
        setInvoiceTaxAmount(NumberUtils.toDouble(StringUtils.toString(map.get("invoiceTaxAmount")), invoiceTaxAmount));
        setSettleTaxExcludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("settleTaxExcludeAmount")), settleTaxExcludeAmount));
        setSettleTaxIncludeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("settleTaxIncludeAmount")), settleTaxIncludeAmount));
        setSettleTaxAmount(NumberUtils.toDouble(StringUtils.toString(map.get("settleTaxAmount")), settleTaxAmount));
        setSettleTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("settleTime")), settleTime));
        setPayAgreNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payAgreNum")), payAgreNum));
        setContStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contStatus")), contStatus));
        setContAdmin(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contAdmin")), contAdmin));
        setContAdminName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contAdminName")), contAdminName));
        setBillMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMaker")), billMaker));
        setBillMakeTime(DateUtils.toTimestamp(StringUtils.toString(map.get("billMakeTime"))));
        setCheckTime(DateUtils.toTimestamp(StringUtils.toString(map.get("checkTime"))));
        setCheckMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkMaker")), checkMaker));
        setFinishMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishMaker")), finishMaker));
        setFinishTime(DateUtils.toTimestamp(StringUtils.toString(map.get("finishTime"))));
        setEndMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endMaker")), endMaker));
        setEndTime(DateUtils.toTimestamp(StringUtils.toString(map.get("endTime"))));
        setQuarPeriod(NumberUtils.toInteger(StringUtils.toString(map.get("quarPeriod")), quarPeriod));
        setBudget(NumberUtils.toDouble(StringUtils.toString(map.get("budget")), budget));
        setPrePayDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("prePayDate")), prePayDate));
        setProno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("prono")), prono));
        setCheckMoney(NumberUtils.toDouble(StringUtils.toString(map.get("checkMoney")), checkMoney));
        setContDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contDeptNum")), contDeptNum));
        setContDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contDeptName")), contDeptName));
        setYesorno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yesorno")), yesorno));
        setXuqianContno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("xuqianContno")), xuqianContno));
        setMsgStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("msgStatus")), msgStatus));
        setDeviceNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceNum")), deviceNum));
        setSmsConfig1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsConfig1")), smsConfig1));
        setProjectId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectId")), projectId));
        setProjectName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectName")), projectName));
        setScheduleAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleAutoNo")), scheduleAutoNo));
        setScheduleName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleName")), scheduleName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevsior",StringUtils.toString(recRevsior, eiMetadata.getMeta("recRevsior")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("originBillNo",StringUtils.toString(originBillNo, eiMetadata.getMeta("originBillNo")));
        map.put("originBillType",StringUtils.toString(originBillType, eiMetadata.getMeta("originBillType")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("contTypeNum",StringUtils.toString(contTypeNum, eiMetadata.getMeta("contTypeNum")));
        map.put("contSignTime",StringUtils.toString(contSignTime, eiMetadata.getMeta("contSignTime")));
        map.put("planTakeefTime",StringUtils.toString(planTakeefTime, eiMetadata.getMeta("planTakeefTime")));
        map.put("planFinishTime",StringUtils.toString(planFinishTime, eiMetadata.getMeta("planFinishTime")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("currType",StringUtils.toString(currType, eiMetadata.getMeta("currType")));
        map.put("contTaxExcludeAmount",StringUtils.toString(contTaxExcludeAmount, eiMetadata.getMeta("contTaxExcludeAmount")));
        map.put("contTaxIncludeAmount",StringUtils.toString(contTaxIncludeAmount, eiMetadata.getMeta("contTaxIncludeAmount")));
        map.put("contTaxRate",StringUtils.toString(contTaxRate, eiMetadata.getMeta("contTaxRate")));
        map.put("contTaxAmount",StringUtils.toString(contTaxAmount, eiMetadata.getMeta("contTaxAmount")));
        map.put("contFeeAmount",StringUtils.toString(contFeeAmount, eiMetadata.getMeta("contFeeAmount")));
        map.put("invoiceTaxExcludeAmount",StringUtils.toString(invoiceTaxExcludeAmount, eiMetadata.getMeta("invoiceTaxExcludeAmount")));
        map.put("invoiceTaxIncludeAmount",StringUtils.toString(invoiceTaxIncludeAmount, eiMetadata.getMeta("invoiceTaxIncludeAmount")));
        map.put("invoiceTaxAmount",StringUtils.toString(invoiceTaxAmount, eiMetadata.getMeta("invoiceTaxAmount")));
        map.put("settleTaxExcludeAmount",StringUtils.toString(settleTaxExcludeAmount, eiMetadata.getMeta("settleTaxExcludeAmount")));
        map.put("settleTaxIncludeAmount",StringUtils.toString(settleTaxIncludeAmount, eiMetadata.getMeta("settleTaxIncludeAmount")));
        map.put("settleTaxAmount",StringUtils.toString(settleTaxAmount, eiMetadata.getMeta("settleTaxAmount")));
        map.put("settleTime",StringUtils.toString(settleTime, eiMetadata.getMeta("settleTime")));
        map.put("payAgreNum",StringUtils.toString(payAgreNum, eiMetadata.getMeta("payAgreNum")));
        map.put("contStatus",StringUtils.toString(contStatus, eiMetadata.getMeta("contStatus")));
        map.put("contAdmin",StringUtils.toString(contAdmin, eiMetadata.getMeta("contAdmin")));
        map.put("contAdminName",StringUtils.toString(contAdminName, eiMetadata.getMeta("contAdminName")));
        map.put("billMaker",StringUtils.toString(billMaker, eiMetadata.getMeta("billMaker")));
        map.put("billMakeTime",StringUtils.toString(billMakeTime, eiMetadata.getMeta("billMakeTime")));
        map.put("checkTime",StringUtils.toString(checkTime, eiMetadata.getMeta("checkTime")));
        map.put("checkMaker",StringUtils.toString(checkMaker, eiMetadata.getMeta("checkMaker")));
        map.put("finishMaker",StringUtils.toString(finishMaker, eiMetadata.getMeta("finishMaker")));
        map.put("finishTime",StringUtils.toString(finishTime, eiMetadata.getMeta("finishTime")));
        map.put("endMaker",StringUtils.toString(endMaker, eiMetadata.getMeta("endMaker")));
        map.put("endTime",StringUtils.toString(endTime, eiMetadata.getMeta("endTime")));
        map.put("quarPeriod",StringUtils.toString(quarPeriod, eiMetadata.getMeta("quarPeriod")));
        map.put("budget",StringUtils.toString(budget, eiMetadata.getMeta("budget")));
        map.put("prePayDate",StringUtils.toString(prePayDate, eiMetadata.getMeta("prePayDate")));
        map.put("prono",StringUtils.toString(prono, eiMetadata.getMeta("prono")));
        map.put("checkMoney",StringUtils.toString(checkMoney, eiMetadata.getMeta("checkMoney")));
        map.put("contDeptNum",StringUtils.toString(contDeptNum, eiMetadata.getMeta("contDeptNum")));
        map.put("contDeptName",StringUtils.toString(contDeptName, eiMetadata.getMeta("contDeptName")));
        map.put("yesorno",StringUtils.toString(yesorno, eiMetadata.getMeta("yesorno")));
        map.put("xuqianContno",StringUtils.toString(xuqianContno, eiMetadata.getMeta("xuqianContno")));
        map.put("msgStatus",StringUtils.toString(msgStatus, eiMetadata.getMeta("msgStatus")));
        map.put("deviceNum",StringUtils.toString(deviceNum, eiMetadata.getMeta("deviceNum")));
        map.put("smsConfig1",StringUtils.toString(smsConfig1, eiMetadata.getMeta("smsConfig1")));
        map.put("projectId",StringUtils.toString(projectId, eiMetadata.getMeta("projectId")));
        map.put("projectName",StringUtils.toString(projectName, eiMetadata.getMeta("projectName")));
        map.put("scheduleAutoNo",StringUtils.toString(scheduleAutoNo, eiMetadata.getMeta("scheduleAutoNo")));
        map.put("scheduleName",StringUtils.toString(scheduleName, eiMetadata.getMeta("scheduleName")));

        return map;
    }
}