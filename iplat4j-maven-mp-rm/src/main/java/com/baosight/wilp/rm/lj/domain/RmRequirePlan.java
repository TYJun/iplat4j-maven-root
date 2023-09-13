package com.baosight.wilp.rm.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidationGroups;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求计划表实体
 * RmRequirePlan
 * @author fangjian
 */
public class RmRequirePlan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 需求计划单号*/
    @NotBlank(message="需求计划单号不能为空", groups = ValidationGroups.Update.class)
    private String planNo;

    /** 需求计划时间(年份/年月/申请日期)*/
    @NotBlank(message="需求计划时间不能为空")
    private String planTime;

    /** 计划类型*/
    private String planType;

    /** 计划类型名称*/
    private String planTypeName;

    /** 年度计划物资总数*/
    private Double planNum = new Double(0.00);

    /** 总金额*/
    private BigDecimal planCost = new BigDecimal("0.00");

    /** 状态编码*/
    private String statusCode;

    /** 状态名称*/
    private String statusName;

    /** 领用(申请)科室编码*/
    private String deptNum;

    /** 领用(申请)科室编码*/
    private String deptName;

    /** 科室负责人工号*/
    private String deptPrincipal;

    /** 科室负责人姓名*/
    private String deptPrincipalName;

    /** 需求计划概述*/
    private String planDesc="";

    /** 备注/申请理由*/
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

    /** 驳回原因*/
    private String rejectReason;

    /** 需求计划明细*/
    private List<RmRequirePlanDetail> planDetailList;

    /**需求计划集合ID**/
    private List<String> planIds;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planNo");
        eiColumn.setDescName("需求计划单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planTime");
        eiColumn.setDescName("需求计划时间(年份/年月/申请日期)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planType");
        eiColumn.setDescName("计划类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planTypeName");
        eiColumn.setDescName("计划类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planNum");
        eiColumn.setDescName("计划物资总数");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("领用(申请)科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("领用(申请)科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptPrincipal");
        eiColumn.setDescName("科室负责人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptPrincipalName");
        eiColumn.setDescName("科室负责人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注/申请理由");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planDesc");
        eiColumn.setDescName("需求计划概述");
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

    }

    /**
     * the constructor
     */
    public RmRequirePlan() {
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
     * get the planNo - 需求计划单号
     * @return the planNo
     */
    public String getPlanNo() {
        return this.planNo;
    }

    /**
     * set the planNo - 需求计划单号
     */
    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    /**
     * get the planTime - 需求计划时间(年份/年月/申请日期)
     * @return the planTime
     */
    public String getPlanTime() {
        return this.planTime;
    }

    /**
     * set the planTime - 需求计划时间(年份/年月/申请日期)
     */
    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    /**
     * get the planType - 计划类型
     * @return the planType
     */
    public String getPlanType() {
        return this.planType;
    }

    /**
     * set the planType - 计划类型
     */
    public void setPlanType(String planType) {
        this.planType = planType;
    }

    /**
     * get the planTypeName - 计划类型名称
     * @return the planTypeName
     */
    public String getPlanTypeName() {
        return this.planTypeName;
    }

    /**
     * set the planTypeName - 计划类型名称
     */
    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    /**
     * get the planNum - 年度计划物资总数
     * @return the planNum
     */
    public Double getPlanNum() {
        return this.planNum;
    }

    /**
     * set the planNum - 年度计划物资总数
     */
    public void setPlanNum(Double planNum) {
        this.planNum = planNum;
    }

    /**
     * get the planCost - 总金额
     * @return the planCost
     */
    public BigDecimal getPlanCost() {
        return this.planCost;
    }

    /**
     * set the planCost - 总金额
     */
    public void setPlanCost(BigDecimal planCost) {
        this.planCost = planCost;
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
     * get the deptNum - 领用(申请)科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 领用(申请)科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 领用(申请)科室编码
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 领用(申请)科室编码
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptPrincipal - 科室负责人工号
     * @return the deptPrincipal
     */
    public String getDeptPrincipal() {
        return this.deptPrincipal;
    }

    /**
     * set the deptPrincipal - 科室负责人工号
     */
    public void setDeptPrincipal(String deptPrincipal) {
        this.deptPrincipal = deptPrincipal;
    }

    /**
     * get the deptPrincipalName - 科室负责人姓名
     * @return the deptPrincipalName
     */
    public String getDeptPrincipalName() {
        return this.deptPrincipalName;
    }

    /**
     * set the deptPrincipalName - 科室负责人姓名
     */
    public void setDeptPrincipalName(String deptPrincipalName) {
        this.deptPrincipalName = deptPrincipalName;
    }

    /**
     * get the planDesc - 需求计划概述
     * @return the planDesc
     */
    public String getPlanDesc() {
        return this.planDesc;
    }

    /**
     * set the planDesc - 需求计划概述
     */
    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    /**
     * get the remark - 备注/申请理由
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注/申请理由
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建（申请）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建（申请）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建（申请）人姓名
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

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public List<RmRequirePlanDetail> getPlanDetailList() {
        return planDetailList;
    }

    public void setPlanDetailList(List<RmRequirePlanDetail> planDetailList) {
        this.planDetailList = planDetailList;
    }

    public List<String> getPlanIds() {
        return planIds;
    }

    public void setPlanIds(List<String> planIds) {
        this.planIds = planIds;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setPlanNo(RmUtils.toString(map.get("planNo"), planNo));
        setPlanTime(RmUtils.toString(map.get("planTime"), planTime));
        setPlanType(RmUtils.toString(map.get("planType"), planType));
        setPlanTypeName(RmUtils.toString(map.get("planTypeName"), planTypeName));
        setPlanNum(NumberUtils.toDouble(StringUtils.toString(map.get("planNum")), planNum));
        setPlanCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("planCost")), planCost));
        setStatusCode(RmUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(RmUtils.toString(map.get("statusName"), statusName));
        setDeptNum(RmUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(RmUtils.toString(map.get("deptName"), deptName));
        setDeptPrincipal(RmUtils.toString(map.get("deptPrincipal"), deptPrincipal));
        setDeptPrincipalName(RmUtils.toString(map.get("deptPrincipalName"), deptPrincipalName));
        setRemark(RmUtils.toString(map.get("remark"), remark));
        setPlanDesc(RmUtils.toString(map.get("planDesc"), planDesc));
        setRecCreator(RmUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(RmUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(RmUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(RmUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(RmUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(RmUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(RmUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("planNo",StringUtils.toString(planNo, eiMetadata.getMeta("planNo")));
        map.put("planTime",StringUtils.toString(planTime, eiMetadata.getMeta("planTime")));
        map.put("planType",StringUtils.toString(planType, eiMetadata.getMeta("planType")));
        map.put("planTypeName",StringUtils.toString(planTypeName, eiMetadata.getMeta("planTypeName")));
        map.put("planNum",StringUtils.toString(planNum, eiMetadata.getMeta("planNum")));
        map.put("planCost",StringUtils.toString(planCost, eiMetadata.getMeta("planCost")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptPrincipal",StringUtils.toString(deptPrincipal, eiMetadata.getMeta("deptPrincipal")));
        map.put("deptPrincipalName",StringUtils.toString(deptPrincipalName, eiMetadata.getMeta("deptPrincipalName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("planDesc",StringUtils.toString(planDesc, eiMetadata.getMeta("planDesc")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        return map;
    }

    public static RmRequirePlan getStatusInstant(String id, String statusCode) {
        RmRequirePlan plan = new RmRequirePlan();
        plan.setId(id);
        plan.setStatusCode(statusCode);
        plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.require.status", statusCode));
        return plan;
    }
}