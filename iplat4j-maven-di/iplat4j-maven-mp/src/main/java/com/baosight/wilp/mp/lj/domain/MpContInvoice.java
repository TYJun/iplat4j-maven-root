package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* MpContInvoice
* 合同发票实体
*/
public class MpContInvoice extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**合同ID*/
    @NotBlank(message="合同ID不能为空")
    private String contId ;

    /**付款ID*/
    private String payId ;

    /**合同号*/
    @NotBlank(message="合同号不能为空")
    private String contNo ;

    /**合同名称*/
    @NotBlank(message="合同名称不能为空")
    private String contName ;

    /**发票号*/
    @NotBlank(message="发票号不能为空")
    private String invoiceNo ;

    /**状态编码*/
    private String statusCode ;

    /**状态名称*/
    private String statusName ;

    /**发票类型*/
    @NotBlank(message="发票类型不能为空")
    private String invoiceType ;

    /**发票类型名称*/
    @NotBlank(message="发票类型名称不能为空")
    private String invoiceTypeName ;

    /**开票(供应商)编码*/
    private String supplierNum ;

    /**开票（供应商）名称*/
    private String supplierName ;

    /**开付日期*/
    @NotNull(message="开付日期不能为空")
    private Date payDate;

    /**币种编码*/
    private String currencyCode ;

    /**币种名称*/
    private String currencyName ;
    
    /**发票金额（元）*/
    @DecimalMin(value = "0.00",message = "发票金额必须大于0")
    private BigDecimal invoiceCost = new BigDecimal("0");

    /**发票税额（元）*/
    private BigDecimal invoiceTaxCost = new BigDecimal("0");

    /**合同说明*/
    private String remark ;

    /**科室编码*/
    private String deptNum;

    /**科室名称*/
    private String deptName;

    /**创建人*/
    private String recCreator ;

    /**创建人姓名*/
    private String recCreatorName ;

    /**创建时间*/
    private Date recCreateTime ;

    /**修改人*/
    private String recRevisor ;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode ;

    /**归档标记*/
    private String archiveFlag ;		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contId");
        eiColumn.setDescName("合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payId");
        eiColumn.setDescName("付款ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceNo");
        eiColumn.setDescName("发票号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceType");
        eiColumn.setDescName("发票类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTypeName");
        eiColumn.setDescName("发票类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierNum");
        eiColumn.setDescName("开票(供应商)编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("开票（供应商）名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payDate");
        eiColumn.setDescName("开付日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyCode");
        eiColumn.setDescName("币种编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyName");
        eiColumn.setDescName("币种名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("发票金额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceTaxCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("发票税额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("合同说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpContInvoice() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the contId - 合同ID
     * @return the contId
     */
    public String getContId() {
        return this.contId;
    }

    /**
     * set the contId - 合同ID
     */
    public void setContId(String contId) {
        this.contId = contId;
    }

    /**
     * get the contId - 付款ID
     * @return the payId
     */
    public String getPayId() {
        return this.payId;
    }

    /**
     * set the payId - 付款ID
     */
    public void setPayId(String payId) {
        this.payId = payId;
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
     * get the statusCode - 状态编码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态编码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * get the invoiceType - 发票类型
     * @return the invoiceType
     */
    public String getInvoiceType() {
        return this.invoiceType;
    }

    /**
     * set the invoiceType - 发票类型
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * get the invoiceTypeName - 发票类型名称
     * @return the invoiceTypeName
     */
    public String getInvoiceTypeName() {
        return this.invoiceTypeName;
    }

    /**
     * set the invoiceTypeName - 发票类型名称
     */
    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    /**
     * get the supplierNum - 开票(供应商)编码
     * @return the supplierNum
     */
    public String getSupplierNum() {
        return this.supplierNum;
    }

    /**
     * set the supplierNum - 开票(供应商)编码
     */
    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    /**
     * get the supplierName - 开票（供应商）名称
     * @return the supplierName
     */
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * set the supplierName - 开票（供应商）名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * get the payDate - 开付日期
     * @return the payDate
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * set the payDate - 开付日期
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * get the currencyCode - 币种编码
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * set the currencyCode - 币种编码
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * get the currencyName - 币种名称
     * @return the currencyName
     */
    public String getCurrencyName() {
        return this.currencyName;
    }

    /**
     * set the currencyName - 币种名称
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * get the invoiceCost - 发票金额（元）
     * @return the invoiceCost
     */
    public BigDecimal getInvoiceCost() {
        return this.invoiceCost;
    }

    /**
     * set the invoiceCost - 发票金额（元）
     */
    public void setInvoiceCost(BigDecimal invoiceCost) {
        this.invoiceCost = invoiceCost;
    }

    /**
     * get the invoiceTaxCost - 发票税额（元）
     * @return the invoiceTaxCost
     */
    public BigDecimal getInvoiceTaxCost() {
        return this.invoiceTaxCost;
    }

    /**
     * set the invoiceTaxCost - 发票税额（元）
     */
    public void setInvoiceTaxCost(BigDecimal invoiceTaxCost) {
        this.invoiceTaxCost = invoiceTaxCost;
    }

    /**
     * get the remark - 合同说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 合同说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getDeptName() { return this.deptName;}

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
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
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
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
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setPayId(MpUtils.toString(map.get("payId"), payId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setContName(MpUtils.toString(map.get("contName"), contName));
        setInvoiceNo(MpUtils.toString(map.get("invoiceNo"), invoiceNo));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setInvoiceType(MpUtils.toString(map.get("invoiceType"), invoiceType));
        setInvoiceTypeName(MpUtils.toString(map.get("invoiceTypeName"), invoiceTypeName));
        setSupplierNum(MpUtils.toString(map.get("supplierNum"), supplierNum));
        setSupplierName(MpUtils.toString(map.get("supplierName"), supplierName));
        setPayDate(DateUtils.toDate(StringUtils.toString(map.get("payDate"))));
        setCurrencyCode(MpUtils.toString(map.get("currencyCode"), currencyCode));
        setCurrencyName(MpUtils.toString(map.get("currencyName"), currencyName));
        setInvoiceCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("invoiceCost")), invoiceCost));
        setInvoiceTaxCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("invoiceTaxCost")), invoiceTaxCost));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setDeptNum(MpUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(MpUtils.toString(map.get("deptName"), deptName));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("payId",StringUtils.toString(payId, eiMetadata.getMeta("payId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("invoiceNo",StringUtils.toString(invoiceNo, eiMetadata.getMeta("invoiceNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("invoiceType",StringUtils.toString(invoiceType, eiMetadata.getMeta("invoiceType")));
        map.put("invoiceTypeName",StringUtils.toString(invoiceTypeName, eiMetadata.getMeta("invoiceTypeName")));
        map.put("supplierNum",StringUtils.toString(supplierNum, eiMetadata.getMeta("supplierNum")));
        map.put("supplierName",StringUtils.toString(supplierName, eiMetadata.getMeta("supplierName")));
        map.put("payDate",StringUtils.toString(payDate, eiMetadata.getMeta("payDate")));
        map.put("currencyCode",StringUtils.toString(currencyCode, eiMetadata.getMeta("currencyCode")));
        map.put("currencyName",StringUtils.toString(currencyName, eiMetadata.getMeta("currencyName")));
        map.put("invoiceCost",StringUtils.toString(invoiceCost, eiMetadata.getMeta("invoiceCost")));
        map.put("invoiceTaxCost",StringUtils.toString(invoiceTaxCost, eiMetadata.getMeta("invoiceTaxCost")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}