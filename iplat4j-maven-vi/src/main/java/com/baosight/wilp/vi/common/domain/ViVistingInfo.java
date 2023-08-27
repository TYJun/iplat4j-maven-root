/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViVistingInfo
* 
*/
public class ViVistingInfo extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private Integer id = new Integer(0);		/* 主键*/
    private String visitingDeptId = "";		/* ac_department(id)*/
    private String visitingDeptName = "";		/* 访问部门名称 取自ac_department表*/
    private Timestamp vistingBeginDate;	/* 到访日期*/
    private Timestamp vistingEndDate;	/* 结束访问日期*/
    private String interviewerWorkNo = "";		/* 被访问的人的工号*/
    private String nterviewerName = "";		/* 被访问人的姓名*/
    private String nterviewerPhone = "";		/* 被访问人的电话*/
    private Timestamp createTime ;		/* 申请请求创建时间*/
    private String creatorType = "";		/* 创建人类型*/
    private String creatorIdentity = "";		/* 创建人标识*/
    private String creatIp = "";		/* 数据填写的客户端IP地址*/
    private String auditorIdentity = "";		/* 要求对这条记录进行审批的人员的工号*/
    private String auditorStepMean ="";
    private Integer auditorStep = new Integer(0);		/* 审批的状态：0=审批过程中，1=审批通过，-1=审批驳回*/
    private String auditorMemo = "";		/* 一般由审批人在审批驳回时填写*/
    private String batNo = "";		/* 批次号*/
    private String auditTime = "";
    private String auditorClientType = "";
    public String getBeginDateStr() {
        return SDF.format(this.vistingBeginDate);
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditorClientType() {
        return auditorClientType;
    }

    public void setAuditorClientType(String auditorClientType) {
        this.auditorClientType = auditorClientType;
    }

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("visitingDeptId");
        eiColumn.setDescName("ac_department(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("visitingDeptName");
        eiColumn.setDescName("访问部门名称 取自ac_department表");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vistingBeginDate");
        eiColumn.setDescName("到访日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vistingEndDate");
        eiColumn.setDescName("结束访问日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("interviewerWorkNo");
        eiColumn.setDescName("被访问的人的工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nterviewerName");
        eiColumn.setDescName("被访问人的姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nterviewerPhone");
        eiColumn.setDescName("被访问人的电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("申请请求创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditTime");
        eiColumn.setDescName("申请请求创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorType");
        eiColumn.setDescName("创建人类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorIdentity");
        eiColumn.setDescName("创建人标识");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatIp");
        eiColumn.setDescName("数据填写的客户端IP地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorIdentity");
        eiColumn.setDescName("要求对这条记录进行审批的人员的工号");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("auditorStepMean");
        eiColumn.setDescName("审批的状态：0=审批过程中，1=审批通过，-1=审批驳回");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorStep");
        eiColumn.setDescName("审批的状态：0=审批过程中，1=审批通过，-1=审批驳回");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorMemo");
        eiColumn.setDescName("一般由审批人在审批驳回时填写");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViVistingInfo() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the visitingDeptId - ac_department(id)
     * @return the visitingDeptId
     */
    public String getVisitingDeptId() {
        return this.visitingDeptId;
    }

    /**
     * set the visitingDeptId - ac_department(id)
     */
    public void setVisitingDeptId(String visitingDeptId) {
        this.visitingDeptId = visitingDeptId;
    }

    /**
     * get the visitingDeptName - 访问部门名称 取自ac_department表
     * @return the visitingDeptName
     */
    public String getVisitingDeptName() {
        return this.visitingDeptName;
    }

    /**
     * set the visitingDeptName - 访问部门名称 取自ac_department表
     */
    public void setVisitingDeptName(String visitingDeptName) {
        this.visitingDeptName = visitingDeptName;
    }

    /**
     * get the vistingBeginDate - 到访日期
     * @return the vistingBeginDate
     */
    public Timestamp getVistingBeginDate() {
        return this.vistingBeginDate;
    }

    /**
     * set the vistingBeginDate - 到访日期
     */
    public void setVistingBeginDate(Timestamp vistingBeginDate) {
        this.vistingBeginDate = vistingBeginDate;
    }

    /**
     * get the vistingEndDate - 结束访问日期
     * @return the vistingEndDate
     */
    public Timestamp getVistingEndDate() {
        return this.vistingEndDate;
    }

    /**
     * set the vistingEndDate - 结束访问日期
     */
    public void setVistingEndDate(Timestamp vistingEndDate) {
        this.vistingEndDate = vistingEndDate;
    }

    /**
     * get the interviewerWorkNo - 被访问的人的工号
     * @return the interviewerWorkNo
     */
    public String getInterviewerWorkNo() {
        return this.interviewerWorkNo;
    }

    /**
     * set the interviewerWorkNo - 被访问的人的工号
     */
    public void setInterviewerWorkNo(String interviewerWorkNo) {
        this.interviewerWorkNo = interviewerWorkNo;
    }

    /**
     * get the nterviewerName - 被访问人的姓名
     * @return the nterviewerName
     */
    public String getNterviewerName() {
        return this.nterviewerName;
    }

    /**
     * set the nterviewerName - 被访问人的姓名
     */
    public void setNterviewerName(String nterviewerName) {
        this.nterviewerName = nterviewerName;
    }

    /**
     * get the nterviewerPhone - 被访问人的电话
     * @return the nterviewerPhone
     */
    public String getNterviewerPhone() {
        return this.nterviewerPhone;
    }

    /**
     * set the nterviewerPhone - 被访问人的电话
     */
    public void setNterviewerPhone(String nterviewerPhone) {
        this.nterviewerPhone = nterviewerPhone;
    }

    /**
     * get the createTime - 申请请求创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 申请请求创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the creatorType - 创建人类型
     * @return the creatorType
     */
    public String getCreatorType() {
        return this.creatorType;
    }

    /**
     * set the creatorType - 创建人类型
     */
    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    /**
     * get the creatorIdentity - 创建人标识
     * @return the creatorIdentity
     */
    public String getCreatorIdentity() {
        return this.creatorIdentity;
    }

    /**
     * set the creatorIdentity - 创建人标识
     */
    public void setCreatorIdentity(String creatorIdentity) {
        this.creatorIdentity = creatorIdentity;
    }

    /**
     * get the creatIp - 数据填写的客户端IP地址
     * @return the creatIp
     */
    public String getCreatIp() {
        return this.creatIp;
    }

    /**
     * set the creatIp - 数据填写的客户端IP地址
     */
    public void setCreatIp(String creatIp) {
        this.creatIp = creatIp;
    }

    /**
     * get the auditorIdentity - 要求对这条记录进行审批的人员的工号
     * @return the auditorIdentity
     */
    public String getAuditorIdentity() {
        return this.auditorIdentity;
    }

    /**
     * set the auditorIdentity - 要求对这条记录进行审批的人员的工号
     */
    public void setAuditorIdentity(String auditorIdentity) {
        this.auditorIdentity = auditorIdentity;
    }

    /**
     * get the auditorStep - 审批的状态：0=审批过程中，1=审批通过，-1=审批驳回
     * @return the auditorStep
     */
    public Integer getAuditorStep() {
        return this.auditorStep;
    }

    /**
     * set the auditorStep - 审批的状态：0=审批过程中，1=审批通过，-1=审批驳回
     */
    public void setAuditorStep(Integer auditorStep) {
        this.auditorStep = auditorStep;
    }

    public String getAuditorStepMean() {
        return auditorStepMean;
    }

    public void setAuditorStepMean(String auditorStepMean) {
        this.auditorStepMean = auditorStepMean;
    }

    /**
     * get the auditorMemo - 一般由审批人在审批驳回时填写
     * @return the auditorMemo
     */
    public String getAuditorMemo() {
        return this.auditorMemo;
    }

    /**
     * set the auditorMemo - 一般由审批人在审批驳回时填写
     */
    public void setAuditorMemo(String auditorMemo) {
        this.auditorMemo = auditorMemo;
    }

    /**
     * get the batNo - 批次号
     * @return the batNo
     */
    public String getBatNo() {
        return this.batNo;
    }

    /**
     * set the batNo - 批次号
     */
    public void setBatNo(String batNo) {
        this.batNo = batNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setVisitingDeptId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("visitingDeptId")), visitingDeptId));
        setVisitingDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("visitingDeptName")), visitingDeptName));
        setVistingBeginDate(DateUtils.toTimestamp(StringUtils.toString(map.get("vistingBeginDate"))));
        setVistingEndDate(DateUtils.toTimestamp(StringUtils.toString(map.get("vistingEndDate"))));
        setInterviewerWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("interviewerWorkNo")), interviewerWorkNo));
        setNterviewerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nterviewerName")), nterviewerName));
        setNterviewerPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nterviewerPhone")), nterviewerPhone));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setAuditTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditTime")), auditTime));
        setCreatorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorType")), creatorType));
        setCreatorIdentity(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorIdentity")), creatorIdentity));
        setCreatIp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatIp")), creatIp));
        setAuditorIdentity(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorIdentity")), auditorIdentity));
        setAuditorStep(NumberUtils.toInteger(StringUtils.toString(map.get("auditorStep")), auditorStep));
        setAuditorStepMean(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorStepMean")), auditorStepMean));
        setAuditorMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorMemo")), auditorMemo));
        setBatNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batNo")), batNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("visitingDeptId",StringUtils.toString(visitingDeptId, eiMetadata.getMeta("visitingDeptId")));
        map.put("visitingDeptName",StringUtils.toString(visitingDeptName, eiMetadata.getMeta("visitingDeptName")));
        map.put("vistingBeginDate",StringUtils.toString(vistingBeginDate, eiMetadata.getMeta("vistingBeginDate")));
        map.put("vistingEndDate",StringUtils.toString(vistingEndDate, eiMetadata.getMeta("vistingEndDate")));
        map.put("interviewerWorkNo",StringUtils.toString(interviewerWorkNo, eiMetadata.getMeta("interviewerWorkNo")));
        map.put("nterviewerName",StringUtils.toString(nterviewerName, eiMetadata.getMeta("nterviewerName")));
        map.put("nterviewerPhone",StringUtils.toString(nterviewerPhone, eiMetadata.getMeta("nterviewerPhone")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("auditTime",StringUtils.toString(auditTime, eiMetadata.getMeta("auditTime")));
        map.put("creatorType",StringUtils.toString(creatorType, eiMetadata.getMeta("creatorType")));
        map.put("creatorIdentity",StringUtils.toString(creatorIdentity, eiMetadata.getMeta("creatorIdentity")));
        map.put("creatIp",StringUtils.toString(creatIp, eiMetadata.getMeta("creatIp")));
        map.put("auditorIdentity",StringUtils.toString(auditorIdentity, eiMetadata.getMeta("auditorIdentity")));
        map.put("auditorStep",StringUtils.toString(auditorStep, eiMetadata.getMeta("auditorStep")));
        map.put("auditorStepMean",StringUtils.toString(auditorStepMean, eiMetadata.getMeta("auditorStepMean")));
        map.put("auditorMemo",StringUtils.toString(auditorMemo, eiMetadata.getMeta("auditorMemo")));
        map.put("batNo",StringUtils.toString(batNo, eiMetadata.getMeta("batNo")));
        return map;
    }
}