package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
* MpContPay
* 合同付款实体
*/
public class MpContPay extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**付款号*/
    private String payNo ;

    /**合同ID*/
    @NotBlank(message = "合同不能为空")
    private String contId ;

    /**合同号*/
    @NotBlank(message = "合同不能为空")
    private String contNo ;

    /**合同名称*/
    private String contName;

    /**科室编码*/
    private String deptNum;

    /**科室名称*/
    private String deptName;

    /**状态编码*/
    private String statusCode ;

    /**状态名称*/
    private String statusName ;

    /**币种编码*/
    private String currencyCode ;

    /**币种名称*/
    private String currencyName ;

    /**付款方式编码*/
    private String payWay ;

    /**付款方式名称*/
    private String payWayName ;

    /**付款日期*/
    @NotNull(message = "付款日期不能为空")
    private Date payDate;

    /**付款金额（元）*/
    @DecimalMin(value = "0.00",message = "付款金额必须大于0")
    private BigDecimal payCost = new BigDecimal("0");

    /**付款税额（元）*/
    private BigDecimal payTaxCost = new BigDecimal("0");

    /**付款内容*/
    private String payContent ;

    /**合同说明*/
    private String remark ;

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

    private String supplierName;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payNo");
        eiColumn.setDescName("付款号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contId");
        eiColumn.setDescName("合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyCode");
        eiColumn.setDescName("币种编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyName");
        eiColumn.setDescName("币种名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWay");
        eiColumn.setDescName("付款方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWayName");
        eiColumn.setDescName("付款方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payDate");
        eiColumn.setDescName("付款日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("付款金额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTaxCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("付款税额（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payContent");
        eiColumn.setDescName("付款内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("合同说明");
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

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("开票单位");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpContPay() {
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
     * get the payNo - 付款号
     * @return the payNo
     */
    public String getPayNo() {
        return this.payNo;
    }

    /**
     * set the payNo - 付款号
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo;
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
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室名称
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
     * get the payWay - 付款方式编码
     * @return the payWay
     */
    public String getPayWay() {
        return this.payWay;
    }

    /**
     * set the payWay - 付款方式编码
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * get the payWayName - 付款方式名称
     * @return the payWayName
     */
    public String getPayWayName() {
        return this.payWayName;
    }

    /**
     * set the payWayName - 付款方式名称
     */
    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    /**
     * get the payDate - 付款日期
     * @return the payDate
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * set the payDate - 付款日期
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * get the payCost - 付款金额（元）
     * @return the payCost
     */
    public BigDecimal getPayCost() {
        return this.payCost;
    }

    /**
     * set the payCost - 付款金额（元）
     */
    public void setPayCost(BigDecimal payCost) {
        this.payCost = payCost;
    }

    /**
     * get the payTaxCost - 付款税额（元）
     * @return the payTaxCost
     */
    public BigDecimal getPayTaxCost() {
        return this.payTaxCost;
    }

    /**
     * set the payTaxCost - 付款税额（元）
     */
    public void setPayTaxCost(BigDecimal payTaxCost) {
        this.payTaxCost = payTaxCost;
    }

    /**
     * get the payContent - 付款内容
     * @return the payContent
     */
    public String getPayContent() {
        return this.payContent;
    }

    /**
     * set the payContent - 付款内容
     */
    public void setPayContent(String payContent) {
        this.payContent = payContent;
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
     * get the supplierName - 开票单位
     * @return the supplierName
     */
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * set the supplierName - 开票单位
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setPayNo(MpUtils.toString(map.get("payNo"), payNo));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setContName(MpUtils.toString(map.get("contName"), contName));
        setDeptNum(MpUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(MpUtils.toString(map.get("deptName"), deptName));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setCurrencyCode(MpUtils.toString(map.get("currencyCode"), currencyCode));
        setCurrencyName(MpUtils.toString(map.get("currencyName"), currencyName));
        setPayWay(MpUtils.toString(map.get("payWay"), payWay));
        setPayWayName(MpUtils.toString(map.get("payWayName"), payWayName));
        setPayDate(DateUtils.toDate(StringUtils.toString(map.get("payDate"))));
        setPayCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("payCost")), payCost));
        setPayTaxCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("payTaxCost")), payTaxCost));
        setPayContent(MpUtils.toString(map.get("payContent"), payContent));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
        setSupplierName(MpUtils.toString(map.get("supplierName"), supplierName));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("payNo",StringUtils.toString(payNo, eiMetadata.getMeta("payNo")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("currencyCode",StringUtils.toString(currencyCode, eiMetadata.getMeta("currencyCode")));
        map.put("currencyName",StringUtils.toString(currencyName, eiMetadata.getMeta("currencyName")));
        map.put("payWay",StringUtils.toString(payWay, eiMetadata.getMeta("payWay")));
        map.put("payWayName",StringUtils.toString(payWayName, eiMetadata.getMeta("payWayName")));
        map.put("payDate",StringUtils.toString(payDate, eiMetadata.getMeta("payDate")));
        map.put("payCost",StringUtils.toString(payCost, eiMetadata.getMeta("payCost")));
        map.put("payTaxCost",StringUtils.toString(payTaxCost, eiMetadata.getMeta("payTaxCost")));
        map.put("payContent",StringUtils.toString(payContent, eiMetadata.getMeta("payContent")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("supplierName",StringUtils.toString(supplierName, eiMetadata.getMeta("supplierName")));
        return map;
    }

    /**
     * 构建付款对象
     * @Title: getStatusInstance
     * @param id id : 付款ID
     * @param statusCode statusCode : 状态
     * @return com.baosight.wilp.mp.lj.domain.MpContPay
     * @throws
     **/
    public static MpContPay getStatusInstance(String id, String statusCode) {
        MpContPay pay = new MpContPay();
        pay.setId(id);
        pay.setStatusCode(statusCode);
        pay.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.cont.payStatus", statusCode));
        pay.setRecRevisor(UserSession.getLoginName());
        pay.setRecReviseTime(new Date());
        return pay;
    }
}