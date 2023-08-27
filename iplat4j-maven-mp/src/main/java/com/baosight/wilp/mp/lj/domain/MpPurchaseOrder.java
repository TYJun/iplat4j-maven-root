package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 采购订单实体
 * MpPurchaseOrder
 * @author fangjian
 */
public class MpPurchaseOrder extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**订单号*/
    private String orderNo ;

    /**物资总数*/
    private Double orderNum = new Double(0.00);

    /**总金额*/
    private BigDecimal orderCost = new BigDecimal("0.00");

    /**合同ID*/
    private String contId ;

    /**合同号*/
    private String contNo ;

    /**合同名称*/
    private String contName ;

    /** 状态编码*/
    private String statusCode;

    /** 状态名称*/
    private String statusName;

    /**供应商编码*/
    private String supplierNum ;

    /**供应商名称*/
    private String supplierName ;

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

    /**创建时间*/
    private String recCreateTimeStr ;

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

        eiColumn = new EiColumn("orderNo");
        eiColumn.setDescName("订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNum");
        eiColumn.setDescName("物资总数");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总金额");
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

        eiColumn = new EiColumn("supplierNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("供应商名称");
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

        eiColumn = new EiColumn("recCreateTimeStr");
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
    public MpPurchaseOrder() {
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
     * get the orderNo - 订单号
     * @return the orderNo
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * set the orderNo - 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * get the orderNum - 物资总数
     * @return the orderNum
     */
    public Double getOrderNum() {
        return this.orderNum;
    }

    /**
     * set the orderNum - 物资总数
     */
    public void setOrderNum(Double orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * get the orderCost - 总金额
     * @return the orderCost
     */
    public BigDecimal getOrderCost() {
        return this.orderCost;
    }

    /**
     * set the orderCost - 总金额
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
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
     * set the recCreateTime - 创建(汇总)时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
        if(this.recCreateTime != null) {
            setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
        }
    }

    /**
     * get the recCreateTimeStr - 创建(汇总)时间
     * @return the recCreateTimeStr
     */
    public String getRecCreateTimeStr() {
        return this.recCreateTimeStr;
    }

    /**
     * set the recCreateTimeStr - 创建(汇总)时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
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
        setOrderNo(MpUtils.toString(map.get("orderNo"), orderNo));
        setOrderNum(NumberUtils.toDouble(StringUtils.toString(map.get("orderNum")), orderNum));
        setOrderCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("orderCost")), orderCost));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setContName(MpUtils.toString(map.get("contName"), contName));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setSupplierNum(MpUtils.toString(map.get("supplierNum"), supplierNum));
        setSupplierName(MpUtils.toString(map.get("supplierName"), supplierName));
        setDeptNum(MpUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(MpUtils.toString(map.get("deptName"), deptName));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("orderNo",StringUtils.toString(orderNo, eiMetadata.getMeta("orderNo")));
        map.put("orderNum",StringUtils.toString(orderNum, eiMetadata.getMeta("orderNum")));
        map.put("orderCost",StringUtils.toString(orderCost, eiMetadata.getMeta("orderCost")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("supplierNum",StringUtils.toString(supplierNum, eiMetadata.getMeta("supplierNum")));
        map.put("supplierName",StringUtils.toString(supplierName, eiMetadata.getMeta("supplierName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}