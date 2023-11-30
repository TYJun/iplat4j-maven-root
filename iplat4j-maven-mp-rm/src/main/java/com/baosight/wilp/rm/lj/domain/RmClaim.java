package com.baosight.wilp.rm.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资领用实体
 * RmClaim
 * @author fangjian
 */
public class RmClaim extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 领用单号*/
    private String claimNo;

    /** 状态编码*/
    private String statusCode;

     /** 状态名称*/
    private String statusName;

    /** 领用数量*/
    private Double claimNum = new Double(0.00);

    /** 领用总金额*/
    private BigDecimal claimTotalMoney = new BigDecimal("0.00");

    /** 已出库数量*/
    private Double outNum = new Double(0.00);

    /** 领用(申请)科室编码*/
    private String deptNum;

    /** 领用(申请)科室编码*/
    private String deptName;

    /** 成本归集科室编码*/
    private String costDeptNum;

    /** 成本归集科室名称*/
    private String costDeptName;

    /** 领用申请人工号*/
    private String applyUserNo;

    /** 领用申请人姓名*/
    private String applyUserName;

    /** 备注*/
    private String remark="";

    /** 创建（申请）人*/
    private String recCreator;

    /** 创建（申请）人姓名*/
    private String recCreatorName;

    /** 创建时间*/
    private Date recCreateTime ;

    /** 创建时间*/
    private String recCreateTimeStr ;

    /** 修改人*/
    private String recRevisor;

     /** 修改时间*/
    private Date recReviseTime ;

    /** 账套*/
    private String dataGroupCode;

    /** 归档标记*/
    private String archiveFlag;

    /**科室驳回原因**/
    private String rejectReason;

    /**仓库驳回原因**/
    private String stockRejectReason;

    /**签收电子签名**/
    private String signature;

    /**申领类型**/
    private String claimType;

    /***
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("claimNo");
        eiColumn.setDescName("领用单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("claimNum");
        eiColumn.setDescName("领用数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("claimTotalMoney");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("领用总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("已出库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("领用(申请)科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("领用(申请)科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costDeptNum");
        eiColumn.setDescName("成本归集科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costDeptName");
        eiColumn.setDescName("成本归集科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyUserNo");
        eiColumn.setDescName("领用申请人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyUserName");
        eiColumn.setDescName("领用申请人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建（申请）人姓名");
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

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stockRejectReason");
        eiColumn.setDescName("仓库驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signature");
        eiColumn.setDescName("签收电子签名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("claimType");
        eiColumn.setDescName("申领类型");
        eiMetadata.addMeta(eiColumn);

    }

  /****
     * the constructor
     */
    public RmClaim() {
        initMetaData();
    }

   /***
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

   /***
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

   /***
     * get the claimNo - 领用单号
     * @return the claimNo
     */
    public String getClaimNo() {
        return this.claimNo;
    }

   /***
     * set the claimNo - 领用单号
     */
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

   /***
     * get the statusCode - 状态编码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

   /***
     * set the statusCode - 状态编码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

   /***
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

   /***
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

   /***
     * get the claimNum - 领用数量
     * @return the claimNum
     */
    public Double getClaimNum() {
        return this.claimNum;
    }

   /***
     * set the claimNum - 领用数量
     */
    public void setClaimNum(Double claimNum) {
        this.claimNum = claimNum;
    }

    /***
     * get the claimTotalMoney - 领用总金额
     * @return the claimTotalMoney
     */
    public BigDecimal getClaimTotalMoney() {
        return claimTotalMoney;
    }

    /***
     * set the claimTotalMoney - 领用总金额
     */
    public void setClaimTotalMoney(BigDecimal claimTotalMoney) {
        this.claimTotalMoney = claimTotalMoney;
    }

    /***
     * get the outNum - 已出库数量
     * @return the outNum
     */
    public Double getOutNum() {
        return this.outNum;
    }

   /***
     * set the outNum - 已出库数量
     */
    public void setOutNum(Double outNum) {
        this.outNum = outNum;
    }

   /***
     * get the deptNum - 领用(申请)科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

   /***
     * set the deptNum - 领用(申请)科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

   /***
     * get the deptName - 领用(申请)科室编码
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

   /***
     * set the deptName - 领用(申请)科室编码
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCostDeptNum() {
        return costDeptNum;
    }

    public void setCostDeptNum(String costDeptNum) {
        this.costDeptNum = costDeptNum;
    }

    public String getCostDeptName() {
        return costDeptName;
    }

    public void setCostDeptName(String costDeptName) {
        this.costDeptName = costDeptName;
    }

    public String getApplyUserNo() {
        return applyUserNo;
    }

    public void setApplyUserNo(String applyUserNo) {
        this.applyUserNo = applyUserNo;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /***
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

   /***
     * set the recCreator - 创建（申请）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

   /***
     * get the recCreatorName - 创建（申请）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

   /***
     * set the recCreatorName - 创建（申请）人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

   /***
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
        if(this.recCreateTime != null) {
            setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
        }
    }

    /**
     * get the recCreateTimeStr - 创建时间
     * @return the recCreateTimeStr
     */
    public String getRecCreateTimeStr() {
        return this.recCreateTimeStr;
    }

    /**
     * set the recCreateTimeStr - 创建时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
    }

   /***
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

   /***
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

   /***
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

   /***
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

   /***
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

   /***
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

   /***
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

   /***
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getStockRejectReason() {
        return stockRejectReason;
    }

    public void setStockRejectReason(String stockRejectReason) {
        this.stockRejectReason = stockRejectReason;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    /***
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setClaimNo(RmUtils.toString(map.get("claimNo"), claimNo));
        setStatusCode(RmUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(RmUtils.toString(map.get("statusName"), statusName));
        setClaimNum(NumberUtils.toDouble(StringUtils.toString(map.get("claimNum")), claimNum));
        setClaimTotalMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("claimTotalMoney")), claimTotalMoney));
        setOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("outNum")), outNum));
        setDeptNum(RmUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(RmUtils.toString(map.get("deptName"), deptName));
        setCostDeptNum(RmUtils.toString(map.get("costDeptNum"), costDeptNum));
        setCostDeptName(RmUtils.toString(map.get("costDeptName"), costDeptName));
        setApplyUserNo(RmUtils.toString(map.get("applyUserNo"), applyUserNo));
        setApplyUserName(RmUtils.toString(map.get("applyUserName"), applyUserName));
        setRemark(RmUtils.toString(map.get("remark"), remark));
        setRecCreator(RmUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(RmUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(RmUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(RmUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(RmUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(RmUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(RmUtils.toString(map.get("archiveFlag"), archiveFlag));
        setSignature(RmUtils.toString(map.get("signature"), signature));
        setClaimType(RmUtils.toString(map.get("claimType"), claimType));
    }

   /***
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("claimNo",StringUtils.toString(claimNo, eiMetadata.getMeta("claimNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("claimNum",StringUtils.toString(claimNum, eiMetadata.getMeta("claimNum")));
        map.put("claimTotalMoney",StringUtils.toString(claimTotalMoney, eiMetadata.getMeta("claimTotalMoney")));
        map.put("outNum",StringUtils.toString(outNum, eiMetadata.getMeta("outNum")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("costDeptNum",StringUtils.toString(costDeptNum, eiMetadata.getMeta("costDeptNum")));
        map.put("costDeptName",StringUtils.toString(costDeptName, eiMetadata.getMeta("costDeptName")));
        map.put("applyUserNo",StringUtils.toString(applyUserNo, eiMetadata.getMeta("applyUserNo")));
        map.put("applyUserName",StringUtils.toString(applyUserName, eiMetadata.getMeta("applyUserName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("stockRejectReason",StringUtils.toString(stockRejectReason, eiMetadata.getMeta("stockRejectReason")));
        map.put("signature",StringUtils.toString(signature, eiMetadata.getMeta("signature")));
        map.put("claimType",StringUtils.toString(claimType, eiMetadata.getMeta("claimType")));
        return map;
    }

    /**
     * 构建领用单实体对象
     * @Title: getStatusInstance
     * @param id id
     * @param statusCode statusCode
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     **/
    public static RmClaim getStatusInstance(String id, String statusCode) {
        RmClaim claim = new RmClaim();
        claim.setId(id);
        claim.setStatusCode(statusCode);
        claim.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.claim.status", statusCode));
        return claim;
    }
}