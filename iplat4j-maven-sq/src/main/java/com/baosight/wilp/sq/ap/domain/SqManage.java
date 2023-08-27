/**
* Generate time : 2021-08-02 16:44:37
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.ap.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SqManage
* 
*/
public class SqManage extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String billNo = " ";		/* 单据号*/
    private String standardId = " ";		/* 标准id*/
    private String problem = " ";		/* 检查中存在的问题*/
    private String standardRemark = " ";		/* 标准备注*/
    private String standardCode = " ";		/* 主题编码*/
    private String standardName = " ";		/* 调查考核主题*/
    private Date buildDate;	/* 考核时间*/
    private String creator = " ";		/* 记录创建人*/
    private String createTime ;		/* 记录创建时间*/
    private String modifier = " ";		/* 记录修改人*/
    private Timestamp modifyTime ;		/* 记录修改时间*/
    private Date beginDate;	/* 开始时间*/
    private Date endDate;	/* 结束时间*/
    private String assessTypeName = " ";		/* 问卷类型*/
    private String assessDeptName = " ";		/* 调查对象*/
    private String statusCode = " ";		/* 状态*/
    private Integer efficaciousNum = new Integer(0);		/* 有效数*/
    private Integer totalPoints = new Integer(0);		/* 总分*/
    private BigDecimal score = new BigDecimal(0);		/* 均分*/
    private String assessRange = " ";		/* 范围(1代表指定范围,0代表全院范围)*/
    private String workNameLeader = " ";		/* 负责人*/
    private String assessContactTel = " ";		/* 手机号*/
    private String workNoLeader = " ";		/* 负责人工号*/
    private String batchNo = " ";		/* 批次号*/
    private String reformContent = " ";		/* 整改方案内容*/
    private String reformStatus = " ";		/* 整改情况*/
    private String reformEval = " ";		/* 领导评价*/
    private String assessDeptNum = " ";		/* 调查对象科室编码*/
    private String dataGroupCode = " ";		/* 账套*/
    private String projectName = " ";		/* 人员分组名*/
    private String projectCode = " ";		/* 人员分组编码*/
    private String instanceCode = " ";		/* 打分编码*/
    private String instanceName = " ";		/* 打分名*/
    private String point = " ";		/* 分数*/
    private Integer orderCode = new Integer(0); 		/* 排序*/
    private String idea = " ";        /* 意见*/
    private String advice = " ";        /* 建议*/
    

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardId");
        eiColumn.setDescName("标准id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("problem");
        eiColumn.setDescName("检查中存在的问题");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardRemark");
        eiColumn.setDescName("标准备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardCode");
        eiColumn.setDescName("主题编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardName");
        eiColumn.setDescName("调查考核主题");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildDate");
        eiColumn.setDescName("考核时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("记录创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("记录修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beginDate");
        eiColumn.setDescName("开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endDate");
        eiColumn.setDescName("结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessTypeName");
        eiColumn.setDescName("问卷类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessDeptName");
        eiColumn.setDescName("调查对象");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("efficaciousNum");
        eiColumn.setDescName("有效数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalPoints");
        eiColumn.setDescName("总分");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("score");
        eiColumn.setDescName("均分");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("orderCode");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessRange");
        eiColumn.setDescName("范围(1代表指定范围,0代表全院范围)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNameLeader");
        eiColumn.setDescName("负责人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessContactTel");
        eiColumn.setDescName("手机号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNoLeader");
        eiColumn.setDescName("负责人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reformContent");
        eiColumn.setDescName("整改方案内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reformStatus");
        eiColumn.setDescName("整改情况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reformEval");
        eiColumn.setDescName("领导评价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessDeptNum");
        eiColumn.setDescName("调查对象科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectName");
        eiColumn.setDescName("人员分组名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectCode");
        eiColumn.setDescName("人员分组编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("instanceCode");
        eiColumn.setDescName("打分编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("instanceName");
        eiColumn.setDescName("打分名");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("point");
        eiColumn.setDescName("分数");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("idea");
        eiColumn.setDescName("意见");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("advice");
        eiColumn.setDescName("建议");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SqManage() {
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
     * get the billNo - 单据号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 单据号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the standardId - 标准id
     * @return the standardId
     */
    public String getStandardId() {
        return this.standardId;
    }

    /**
     * set the standardId - 标准id
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    /**
     * get the problem - 检查中存在的问题
     * @return the problem
     */
    public String getProblem() {
        return this.problem;
    }

    /**
     * set the problem - 检查中存在的问题
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * get the standardRemark - 标准备注
     * @return the standardRemark
     */
    public String getStandardRemark() {
        return this.standardRemark;
    }

    /**
     * set the standardRemark - 标准备注
     */
    public void setStandardRemark(String standardRemark) {
        this.standardRemark = standardRemark;
    }

    /**
     * get the standardCode - 主题编码
     * @return the standardCode
     */
    public String getStandardCode() {
        return this.standardCode;
    }

    /**
     * set the standardCode - 主题编码
     */
    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    /**
     * get the standardName - 调查考核主题
     * @return the standardName
     */
    public String getStandardName() {
        return this.standardName;
    }

    /**
     * set the standardName - 调查考核主题
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    /**
     * get the buildDate - 考核时间
     * @return the buildDate
     */
    public Date getBuildDate() {
        return this.buildDate;
    }

    /**
     * set the buildDate - 考核时间
     */
    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    /**
     * get the creator - 记录创建人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 记录创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the modifier - 记录修改人
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier - 记录修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the modifyTime - 记录修改时间
     * @return the modifyTime
     */
    public Timestamp getModifyTime() {
        return this.modifyTime;
    }

    /**
     * set the modifyTime - 记录修改时间
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * get the beginDate - 开始时间
     * @return the beginDate
     */
    public Date getBeginDate() {
        return this.beginDate;
    }

    /**
     * set the beginDate - 开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * get the endDate - 结束时间
     * @return the endDate
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * set the endDate - 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * get the assessTypeName - 问卷类型
     * @return the assessTypeName
     */
    public String getAssessTypeName() {
        return this.assessTypeName;
    }

    /**
     * set the assessTypeName - 问卷类型
     */
    public void setAssessTypeName(String assessTypeName) {
        this.assessTypeName = assessTypeName;
    }

    /**
     * get the assessDeptName - 调查对象
     * @return the assessDeptName
     */
    public String getAssessDeptName() {
        return this.assessDeptName;
    }

    /**
     * set the assessDeptName - 调查对象
     */
    public void setAssessDeptName(String assessDeptName) {
        this.assessDeptName = assessDeptName;
    }

    /**
     * get the statusCode - 状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the efficaciousNum - 有效数
     * @return the efficaciousNum
     */
    public Integer getEfficaciousNum() {
        return this.efficaciousNum;
    }

    /**
     * set the efficaciousNum - 有效数
     */
    public void setEfficaciousNum(Integer efficaciousNum) {
        this.efficaciousNum = efficaciousNum;
    }

    /**
     * get the totalPoints - 总分
     * @return the totalPoints
     */
    public Integer getTotalPoints() {
        return this.totalPoints;
    }

    /**
     * set the totalPoints - 总分
     */
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * get the score - 均分
     * @return the score
     */
    public BigDecimal getScore() {
        return this.score;
    }

    /**
     * set the score - 均分
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * get the assessRange - 范围(1代表指定范围,0代表全院范围)
     * @return the assessRange
     */
    public String getAssessRange() {
        return this.assessRange;
    }

    /**
     * set the assessRange - 范围(1代表指定范围,0代表全院范围)
     */
    public void setAssessRange(String assessRange) {
        this.assessRange = assessRange;
    }

    /**
     * get the workNameLeader - 负责人
     * @return the workNameLeader
     */
    public String getWorkNameLeader() {
        return this.workNameLeader;
    }

    /**
     * set the workNameLeader - 负责人
     */
    public void setWorkNameLeader(String workNameLeader) {
        this.workNameLeader = workNameLeader;
    }

    /**
     * get the assessContactTel - 手机号
     * @return the assessContactTel
     */
    public String getAssessContactTel() {
        return this.assessContactTel;
    }

    /**
     * set the assessContactTel - 手机号
     */
    public void setAssessContactTel(String assessContactTel) {
        this.assessContactTel = assessContactTel;
    }

    /**
     * get the workNoLeader - 负责人工号
     * @return the workNoLeader
     */
    public String getWorkNoLeader() {
        return this.workNoLeader;
    }

    /**
     * set the workNoLeader - 负责人工号
     */
    public void setWorkNoLeader(String workNoLeader) {
        this.workNoLeader = workNoLeader;
    }

    /**
     * get the batchNo - 批次号
     * @return the batchNo
     */
    public String getBatchNo() {
        return this.batchNo;
    }

    /**
     * set the batchNo - 批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * get the reformContent - 整改方案内容
     * @return the reformContent
     */
    public String getReformContent() {
        return this.reformContent;
    }

    /**
     * set the reformContent - 整改方案内容
     */
    public void setReformContent(String reformContent) {
        this.reformContent = reformContent;
    }

    /**
     * get the reformStatus - 整改情况
     * @return the reformStatus
     */
    public String getReformStatus() {
        return this.reformStatus;
    }

    /**
     * set the reformStatus - 整改情况
     */
    public void setReformStatus(String reformStatus) {
        this.reformStatus = reformStatus;
    }

    /**
     * get the reformEval - 领导评价
     * @return the reformEval
     */
    public String getReformEval() {
        return this.reformEval;
    }

    /**
     * set the reformEval - 领导评价
     */
    public void setReformEval(String reformEval) {
        this.reformEval = reformEval;
    }

    /**
     * get the assessDeptNum - 调查对象科室编码
     * @return the assessDeptNum
     */
    public String getAssessDeptNum() {
        return this.assessDeptNum;
    }

    /**
     * set the assessDeptNum - 调查对象科室编码
     */
    public void setAssessDeptNum(String assessDeptNum) {
        this.assessDeptNum = assessDeptNum;
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
     * get the projectName - 人员分组名
     * @return the projectName
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * set the projectName - 人员分组名
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * get the projectCode - 人员分组编码
     * @return the projectCode
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * set the projectCode - 人员分组编码
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }
    
    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStandardId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardId")), standardId));
        setProblem(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("problem")), problem));
        setStandardRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardRemark")), standardRemark));
        setStandardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardCode")), standardCode));
        setStandardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardName")), standardName));
        setBuildDate(DateUtils.toDate(StringUtils.toString(map.get("buildDate"))));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setModifyTime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifyTime"))));
        setBeginDate(DateUtils.toDate(StringUtils.toString(map.get("beginDate"))));
        setEndDate(DateUtils.toDate(StringUtils.toString(map.get("endDate"))));
        setAssessTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessTypeName")), assessTypeName));
        setAssessDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessDeptName")), assessDeptName));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setEfficaciousNum(NumberUtils.toInteger(StringUtils.toString(map.get("efficaciousNum")), efficaciousNum));
        setTotalPoints(NumberUtils.toInteger(StringUtils.toString(map.get("totalPoints")), totalPoints));
        setScore(NumberUtils.toBigDecimal(StringUtils.toString(map.get("score")), score));
        setOrderCode(NumberUtils.toInteger(StringUtils.toString(map.get("orderCode")), orderCode));
        setAssessRange(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessRange")), assessRange));
        setWorkNameLeader(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNameLeader")), workNameLeader));
        setAssessContactTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessContactTel")), assessContactTel));
        setWorkNoLeader(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNoLeader")), workNoLeader));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
        setReformContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reformContent")), reformContent));
        setReformStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reformStatus")), reformStatus));
        setReformEval(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reformEval")), reformEval));
        setAssessDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessDeptNum")), assessDeptNum));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setProjectName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectName")), projectName));
        setProjectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectCode")), projectCode));
        setInstanceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceName")), instanceName));
        setInstanceCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceCode")), instanceCode));
        setPoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("point")), point));
        setIdea(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idea")), idea));
        setAdvice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("advice")), advice));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("standardId",StringUtils.toString(standardId, eiMetadata.getMeta("standardId")));
        map.put("problem",StringUtils.toString(problem, eiMetadata.getMeta("problem")));
        map.put("standardRemark",StringUtils.toString(standardRemark, eiMetadata.getMeta("standardRemark")));
        map.put("standardCode",StringUtils.toString(standardCode, eiMetadata.getMeta("standardCode")));
        map.put("standardName",StringUtils.toString(standardName, eiMetadata.getMeta("standardName")));
        map.put("buildDate",StringUtils.toString(buildDate, eiMetadata.getMeta("buildDate")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifier",StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("modifyTime",StringUtils.toString(modifyTime, eiMetadata.getMeta("modifyTime")));
        map.put("beginDate",StringUtils.toString(beginDate, eiMetadata.getMeta("beginDate")));
        map.put("endDate",StringUtils.toString(endDate, eiMetadata.getMeta("endDate")));
        map.put("assessTypeName",StringUtils.toString(assessTypeName, eiMetadata.getMeta("assessTypeName")));
        map.put("assessDeptName",StringUtils.toString(assessDeptName, eiMetadata.getMeta("assessDeptName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("efficaciousNum",StringUtils.toString(efficaciousNum, eiMetadata.getMeta("efficaciousNum")));
        map.put("totalPoints",StringUtils.toString(totalPoints, eiMetadata.getMeta("totalPoints")));
        map.put("score",StringUtils.toString(score, eiMetadata.getMeta("score")));
        map.put("orderCode",StringUtils.toString(orderCode, eiMetadata.getMeta("orderCode")));
        map.put("assessRange",StringUtils.toString(assessRange, eiMetadata.getMeta("assessRange")));
        map.put("workNameLeader",StringUtils.toString(workNameLeader, eiMetadata.getMeta("workNameLeader")));
        map.put("assessContactTel",StringUtils.toString(assessContactTel, eiMetadata.getMeta("assessContactTel")));
        map.put("workNoLeader",StringUtils.toString(workNoLeader, eiMetadata.getMeta("workNoLeader")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("reformContent",StringUtils.toString(reformContent, eiMetadata.getMeta("reformContent")));
        map.put("reformStatus",StringUtils.toString(reformStatus, eiMetadata.getMeta("reformStatus")));
        map.put("reformEval",StringUtils.toString(reformEval, eiMetadata.getMeta("reformEval")));
        map.put("assessDeptNum",StringUtils.toString(assessDeptNum, eiMetadata.getMeta("assessDeptNum")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("projectName",StringUtils.toString(projectName, eiMetadata.getMeta("projectName")));
        map.put("projectCode",StringUtils.toString(projectCode, eiMetadata.getMeta("projectCode")));
        map.put("instanceName",StringUtils.toString(instanceName, eiMetadata.getMeta("instanceName")));
        map.put("instanceCode",StringUtils.toString(instanceCode, eiMetadata.getMeta("instanceCode")));
        map.put("point",StringUtils.toString(point, eiMetadata.getMeta("point")));
        map.put("idea",StringUtils.toString(idea, eiMetadata.getMeta("idea")));
        map.put("advice",StringUtils.toString(advice, eiMetadata.getMeta("advice")));
        return map;
    }
}