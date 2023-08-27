package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mp.common.MpUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 合同履历表
 * MpContractHistory
 */
public class MpContractHistory extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**合同ID*/
    private String contId ;

    /**合同号*/
    private String contNo ;

    /**合同名称*/
    private String contName ;

    /**状态编码*/
    private String statusCode ;

    /**状态名称*/
    private String statusName ;

    /**合同分类*/
    private String contClassify ;

    /**合同类型*/
    private String contType ;

    /**供应商编码*/
    private String supplierNum ;

    /**供应商名称*/
    private String supplierName ;

    /**合同金额*/
    private BigDecimal contCost = new BigDecimal("0");

    /**合同签订日期*/
    private Date signDate;

    /**合同生效日期*/
    private Date validDate;

    /**合同终止日期*/
    private Date overDate;

    /**币种编码*/
    private String currencyCode ;

    /**币种名称*/
    private String currencyName ;

    /**所属（管理）科室编码*/
    private String manageDeptNum ;

    /**所属（管理）科室名称*/
    private String manageDeptName ;

    /**管理员（负责人）工号*/
    private String managerNum ;

    /**管理员（负责人）名称*/
    private String managerName ;

    /**采购方式编码*/
    private String purchaseWay ;

    /**采购方式名称*/
    private String purchaseWayName ;

    /**付款方式编码*/
    private String payWay ;

    /**付款方式名称*/
    private String payWayName ;

    /**合同期效（年）*/
    private Integer validLimit = new Integer(0);

    /**资金来源名称*/
    private String fundingSourceNum ;

    /**资金来源编码*/
    private String fundingSourceName ;

    /**合同说明*/
    private String remark ;

    /**对接系统的合同ID*/
    private String dockContId ;

    /**操作类型*/
    private String operateType ;

    /**操作人*/
    private String operateNo ;

    /**操作人姓名*/
    private String operateName ;

    /**操作时间*/
    private Date operateTime ;

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

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contClassify");
        eiColumn.setDescName("合同分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contType");
        eiColumn.setDescName("合同类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("供应商名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("合同金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signDate");
        eiColumn.setDescName("合同签订日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validDate");
        eiColumn.setDescName("合同生效日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("overDate");
        eiColumn.setDescName("合同终止日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyCode");
        eiColumn.setDescName("币种编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyName");
        eiColumn.setDescName("币种名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageDeptNum");
        eiColumn.setDescName("所属（管理）科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageDeptName");
        eiColumn.setDescName("所属（管理）科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerNum");
        eiColumn.setDescName("管理员（负责人）工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerName");
        eiColumn.setDescName("管理员（负责人）名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseWay");
        eiColumn.setDescName("采购方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseWayName");
        eiColumn.setDescName("采购方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWay");
        eiColumn.setDescName("付款方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWayName");
        eiColumn.setDescName("付款方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validLimit");
        eiColumn.setDescName("合同期效（年）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingsourceName");
        eiColumn.setDescName("资金来源名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingsourceNum");
        eiColumn.setDescName("资金来源编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("合同说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dockContId");
        eiColumn.setDescName("对接系统的合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateType");
        eiColumn.setDescName("操作类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateNo");
        eiColumn.setDescName("操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateName");
        eiColumn.setDescName("操作人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public MpContractHistory() {
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
     * get the contClassify - 合同分类
     * @return the contClassify
     */
    public String getContClassify() {
        return this.contClassify;
    }

    /**
     * set the contClassify - 合同分类
     */
    public void setContClassify(String contClassify) {
        this.contClassify = contClassify;
    }

    /**
     * get the contType - 合同类型
     * @return the contType
     */
    public String getContType() {
        return this.contType;
    }

    /**
     * set the contType - 合同类型
     */
    public void setContType(String contType) {
        this.contType = contType;
    }

    /**
     * get the supplierNum - 供应商编码
     * @return the supplierNum
     */
    public String getSupplierNum() {
        return this.supplierNum;
    }

    /**
     * set the supplierNum - 供应商编码
     */
    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    /**
     * get the supplierName - 供应商名称
     * @return the supplierName
     */
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * set the supplierName - 供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * get the contCost - 合同金额
     * @return the contCost
     */
    public BigDecimal getContCost() {
        return this.contCost;
    }

    /**
     * set the contCost - 合同金额
     */
    public void setContCost(BigDecimal contCost) {
        this.contCost = contCost;
    }

    /**
     * get the signDate - 合同签订日期
     * @return the signDate
     */
    public Date getSignDate() {
        return this.signDate;
    }

    /**
     * set the signDate - 合同签订日期
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * get the validDate - 合同生效日期
     * @return the validDate
     */
    public Date getValidDate() {
        return this.validDate;
    }

    /**
     * set the validDate - 合同生效日期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * get the overDate - 合同终止日期
     * @return the overDate
     */
    public Date getOverDate() {
        return this.overDate;
    }

    /**
     * set the overDate - 合同终止日期
     */
    public void setOverDate(Date overDate) {
        this.overDate = overDate;
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
     * get the manageDeptNum - 所属（管理）科室编码
     * @return the manageDeptNum
     */
    public String getManageDeptNum() {
        return this.manageDeptNum;
    }

    /**
     * set the manageDeptNum - 所属（管理）科室编码
     */
    public void setManageDeptNum(String manageDeptNum) {
        this.manageDeptNum = manageDeptNum;
    }

    /**
     * get the manageDeptName - 所属（管理）科室名称
     * @return the manageDeptName
     */
    public String getManageDeptName() {
        return this.manageDeptName;
    }

    /**
     * set the manageDeptName - 所属（管理）科室名称
     */
    public void setManageDeptName(String manageDeptName) {
        this.manageDeptName = manageDeptName;
    }

    /**
     * get the managerNum - 管理员（负责人）工号
     * @return the managerNum
     */
    public String getManagerNum() {
        return this.managerNum;
    }

    /**
     * set the managerNum - 管理员（负责人）工号
     */
    public void setManagerNum(String managerNum) {
        this.managerNum = managerNum;
    }

    /**
     * get the managerName - 管理员（负责人）名称
     * @return the managerName
     */
    public String getManagerName() {
        return this.managerName;
    }

    /**
     * set the managerName - 管理员（负责人）名称
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * get the purchaseWay - 采购方式编码
     * @return the purchaseWay
     */
    public String getPurchaseWay() {
        return this.purchaseWay;
    }

    /**
     * set the purchaseWay - 采购方式编码
     */
    public void setPurchaseWay(String purchaseWay) {
        this.purchaseWay = purchaseWay;
    }

    /**
     * get the purchaseWayName - 采购方式名称
     * @return the purchaseWayName
     */
    public String getPurchaseWayName() {
        return this.purchaseWayName;
    }

    /**
     * set the purchaseWayName - 采购方式名称
     */
    public void setPurchaseWayName(String purchaseWayName) {
        this.purchaseWayName = purchaseWayName;
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
     * get the validLimit - 合同期效（年）
     * @return the validLimit
     */
    public Integer getValidLimit() {
        return this.validLimit;
    }

    /**
     * set the validLimit - 合同期效（年）
     */
    public void setValidLimit(Integer validLimit) {
        this.validLimit = validLimit;
    }

    /**
     * get the fundingSourceName - 资金来源名称
     * @return the fundingSourceName
     */
    public String getFundingSourceName() {
        return this.fundingSourceName;
    }

    /**
     * set the fundingSourceName - 资金来源名称
     */
    public void setFundingSourceName(String fundingSourceName) {
        this.fundingSourceName = fundingSourceName;
    }

    /**
     * get the fundingSourceNum - 资金来源编码
     * @return the fundingSourceNum
     */
    public String getFundingSourceNum() {
        return this.fundingSourceNum;
    }

    /**
     * set the fundingSourceNum - 资金来源编码
     */
    public void setFundingSourceNum(String fundingSourceNum) {
        this.fundingSourceNum = fundingSourceNum;
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
     * get the dockContId - 对接系统的合同ID
     * @return the dockContId
     */
    public String getDockContId() {
        return this.dockContId;
    }

    /**
     * set the dockContId - 对接系统的合同ID
     */
    public void setDockContId(String dockContId) {
        this.dockContId = dockContId;
    }

    /**
     * get the operateType - 操作类型
     * @return the operateType
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * set the operateType - 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * get the operateNo - 操作人
     * @return the operateNo
     */
    public String getOperateNo() {
        return this.operateNo;
    }

    /**
     * set the operateNo - 操作人
     */
    public void setOperateNo(String operateNo) {
        this.operateNo = operateNo;
    }

    /**
     * get the operateName - 操作人姓名
     * @return the operateName
     */
    public String getOperateName() {
        return this.operateName;
    }

    /**
     * set the operateName - 操作人姓名
     */
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    /**
     * get the operateTime - 操作时间
     * @return the operateTime
     */
    public Date getOperateTime() {
        return this.operateTime;
    }

    /**
     * set the operateTime - 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {
        setId(MpUtils.toString(map.get("id"), id));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setContName(MpUtils.toString(map.get("contName"), contName));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setContClassify(MpUtils.toString(map.get("contClassify"), contClassify));
        setContType(MpUtils.toString(map.get("contType"), contType));
        setSupplierNum(MpUtils.toString(map.get("supplierNum"), supplierNum));
        setSupplierName(MpUtils.toString(map.get("supplierName"), supplierName));
        setContCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("contCost")), contCost));
        setSignDate(DateUtils.toDate(StringUtils.toString(map.get("signDate"))));
        setValidDate(DateUtils.toDate(StringUtils.toString(map.get("validDate"))));
        setOverDate(DateUtils.toDate(StringUtils.toString(map.get("overDate"))));
        setCurrencyCode(MpUtils.toString(map.get("currencyCode"), currencyCode));
        setCurrencyName(MpUtils.toString(map.get("currencyName"), currencyName));
        setManageDeptNum(MpUtils.toString(map.get("manageDeptNum"), manageDeptNum));
        setManageDeptName(MpUtils.toString(map.get("manageDeptName"), manageDeptName));
        setManagerNum(MpUtils.toString(map.get("managerNum"), managerNum));
        setManagerName(MpUtils.toString(map.get("managerName"), managerName));
        setPurchaseWay(MpUtils.toString(map.get("purchaseWay"), purchaseWay));
        setPurchaseWayName(MpUtils.toString(map.get("purchaseWayName"), purchaseWayName));
        setPayWay(MpUtils.toString(map.get("payWay"), payWay));
        setPayWayName(MpUtils.toString(map.get("payWayName"), payWayName));
        setValidLimit(NumberUtils.toInteger(StringUtils.toString(map.get("validLimit")), validLimit));
        setFundingSourceName(MpUtils.toString(map.get("fundingSourceName"), fundingSourceName));
        setFundingSourceNum(MpUtils.toString(map.get("fundingSourceNum"), fundingSourceNum));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setDockContId(MpUtils.toString(map.get("dockContId"), dockContId));
        setOperateType(MpUtils.toString(map.get("operateType"), operateType));
        setOperateNo(MpUtils.toString(map.get("operateNo"), operateNo));
        setOperateName(MpUtils.toString(map.get("operateName"), operateName));
        setOperateTime(MpUtils.toDate(StringUtils.toString(map.get("operateTime"))));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("contClassify",StringUtils.toString(contClassify, eiMetadata.getMeta("contClassify")));
        map.put("contType",StringUtils.toString(contType, eiMetadata.getMeta("contType")));
        map.put("supplierNum",StringUtils.toString(supplierNum, eiMetadata.getMeta("supplierNum")));
        map.put("supplierName",StringUtils.toString(supplierName, eiMetadata.getMeta("supplierName")));
        map.put("contCost",StringUtils.toString(contCost, eiMetadata.getMeta("contCost")));
        map.put("signDate",StringUtils.toString(signDate, eiMetadata.getMeta("signDate")));
        map.put("validDate",StringUtils.toString(validDate, eiMetadata.getMeta("validDate")));
        map.put("overDate",StringUtils.toString(overDate, eiMetadata.getMeta("overDate")));
        map.put("currencyCode",StringUtils.toString(currencyCode, eiMetadata.getMeta("currencyCode")));
        map.put("currencyName",StringUtils.toString(currencyName, eiMetadata.getMeta("currencyName")));
        map.put("manageDeptNum",StringUtils.toString(manageDeptNum, eiMetadata.getMeta("manageDeptNum")));
        map.put("manageDeptName",StringUtils.toString(manageDeptName, eiMetadata.getMeta("manageDeptName")));
        map.put("managerNum",StringUtils.toString(managerNum, eiMetadata.getMeta("managerNum")));
        map.put("managerName",StringUtils.toString(managerName, eiMetadata.getMeta("managerName")));
        map.put("purchaseWay",StringUtils.toString(purchaseWay, eiMetadata.getMeta("purchaseWay")));
        map.put("purchaseWayName",StringUtils.toString(purchaseWayName, eiMetadata.getMeta("purchaseWayName")));
        map.put("payWay",StringUtils.toString(payWay, eiMetadata.getMeta("payWay")));
        map.put("payWayName",StringUtils.toString(payWayName, eiMetadata.getMeta("payWayName")));
        map.put("validLimit",StringUtils.toString(validLimit, eiMetadata.getMeta("validLimit")));
        map.put("fundingSourceName",StringUtils.toString(fundingSourceName, eiMetadata.getMeta("fundingSourceName")));
        map.put("fundingSourceNum",StringUtils.toString(fundingSourceNum, eiMetadata.getMeta("fundingSourceNum")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("dockContId",StringUtils.toString(dockContId, eiMetadata.getMeta("dockContId")));
        map.put("operateType",StringUtils.toString(operateType, eiMetadata.getMeta("operateType")));
        map.put("operateNo",StringUtils.toString(operateNo, eiMetadata.getMeta("operateNo")));
        map.put("operateName",StringUtils.toString(operateName, eiMetadata.getMeta("operateName")));
        map.put("operateTime",StringUtils.toString(operateTime, eiMetadata.getMeta("operateTime")));
        return map;
    }

    public static MpContractHistory newInstance(String contId, String operateType) {
        MpContractHistory history = new MpContractHistory();
        history.setContId(contId);
        history.setOperateType(operateType);
        history.setOperateNo(UserSession.getLoginName());
        history.setOperateName(UserSession.getLoginCName());
        history.setOperateTime(new Date());
        return history;
    }
}