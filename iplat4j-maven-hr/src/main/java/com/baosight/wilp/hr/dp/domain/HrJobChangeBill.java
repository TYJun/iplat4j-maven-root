/**
* Generate time : 2022-03-29 10:00:35
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.dp.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* HrJobChangeBill
* 
*/
public class HrJobChangeBill extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String billNo = " ";		/* 单据号(DPyyMMxxxx)*/
    private String billTime = " ";		/* 申请日期*/
    private String arriveTime = " ";		/* 到岗时间*/
    private String leaveTime = " ";		/* 离岗时间*/
    private String deptNum = " ";		/* 申请部门*/
    private String changeCode = " ";		/* 支援类型*/
    private String shiftTimeSection = " ";		/* 时间段*/
    private String shiftFirstTime = " ";		/* 上班时间*/
    private String becauseMemo = " ";		/* 申请原因说明*/
    private String auditMan = " ";		/* 申请人确认人姓名*/
    private String auditId = " ";		/* 申请人确认ID*/
    private Timestamp auditDate ;		/* 申请人确认时间*/
    private Timestamp createDate ;		/* 创建时间*/
    private String creatorName = " ";		/* 创建人*/
    private String creatorId = " ";		/* 创建人ID */
    private Timestamp updateDate ;		/* 更改时间*/
    private String updatorName = " ";		/* 更改人*/
    private String updatorId = " ";		/* 更改人ID*/
    private String operateType = " ";		/* 最后操作类型*/
    private String statusCode = " ";		/* 状态(0-新建，1-待审核，2-已审核，3-驳回)*/
    private String currentDealer = " ";		/* 满意度*/
    private String processInstId = " ";		
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private Integer numbers = new Integer(0);		/* 支援人数*/
    private String auditOpinion = " ";		/* 审批意见*/
    private String auditFlag = " ";		
    private String supportStation = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("单据号(DPyyMMxxxx)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTime");
        eiColumn.setDescName("申请日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("arriveTime");
        eiColumn.setDescName("到岗时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("leaveTime");
        eiColumn.setDescName("离岗时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("申请部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("changeCode");
        eiColumn.setDescName("支援类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shiftTimeSection");
        eiColumn.setDescName("时间段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shiftFirstTime");
        eiColumn.setDescName("上班时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("becauseMemo");
        eiColumn.setDescName("申请原因说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditMan");
        eiColumn.setDescName("申请人确认人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditId");
        eiColumn.setDescName("申请人确认ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditDate");
        eiColumn.setDescName("申请人确认时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人ID ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName("更改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorName");
        eiColumn.setDescName("更改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorId");
        eiColumn.setDescName("更改人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateType");
        eiColumn.setDescName("最后操作类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态(0-新建，1-待审核，2-已审核，3-驳回)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName("满意度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("numbers");
        eiColumn.setDescName("支援人数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditOpinion");
        eiColumn.setDescName("审批意见");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supportStation");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrJobChangeBill() {
        initMetaData();
    }

    /**
     * get the id - 主键ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the billNo - 单据号(DPyyMMxxxx)
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 单据号(DPyyMMxxxx)
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the billTime - 申请日期
     * @return the billTime
     */
    public String getBillTime() {
        return this.billTime;
    }

    /**
     * set the billTime - 申请日期
     */
    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    /**
     * get the arriveTime - 到岗时间
     * @return the arriveTime
     */
    public String getArriveTime() {
        return this.arriveTime;
    }

    /**
     * set the arriveTime - 到岗时间
     */
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * get the leaveTime - 离岗时间
     * @return the leaveTime
     */
    public String getLeaveTime() {
        return this.leaveTime;
    }

    /**
     * set the leaveTime - 离岗时间
     */
    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    /**
     * get the deptNum - 申请部门
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 申请部门
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the changeCode - 支援类型
     * @return the changeCode
     */
    public String getChangeCode() {
        return this.changeCode;
    }

    /**
     * set the changeCode - 支援类型
     */
    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    /**
     * get the shiftTimeSection - 时间段
     * @return the shiftTimeSection
     */
    public String getShiftTimeSection() {
        return this.shiftTimeSection;
    }

    /**
     * set the shiftTimeSection - 时间段
     */
    public void setShiftTimeSection(String shiftTimeSection) {
        this.shiftTimeSection = shiftTimeSection;
    }

    /**
     * get the shiftFirstTime - 上班时间
     * @return the shiftFirstTime
     */
    public String getShiftFirstTime() {
        return this.shiftFirstTime;
    }

    /**
     * set the shiftFirstTime - 上班时间
     */
    public void setShiftFirstTime(String shiftFirstTime) {
        this.shiftFirstTime = shiftFirstTime;
    }

    /**
     * get the becauseMemo - 申请原因说明
     * @return the becauseMemo
     */
    public String getBecauseMemo() {
        return this.becauseMemo;
    }

    /**
     * set the becauseMemo - 申请原因说明
     */
    public void setBecauseMemo(String becauseMemo) {
        this.becauseMemo = becauseMemo;
    }

    /**
     * get the auditMan - 申请人确认人姓名
     * @return the auditMan
     */
    public String getAuditMan() {
        return this.auditMan;
    }

    /**
     * set the auditMan - 申请人确认人姓名
     */
    public void setAuditMan(String auditMan) {
        this.auditMan = auditMan;
    }

    /**
     * get the auditId - 申请人确认ID
     * @return the auditId
     */
    public String getAuditId() {
        return this.auditId;
    }

    /**
     * set the auditId - 申请人确认ID
     */
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    /**
     * get the auditDate - 申请人确认时间
     * @return the auditDate
     */
    public Timestamp getAuditDate() {
        return this.auditDate;
    }

    /**
     * set the auditDate - 申请人确认时间
     */
    public void setAuditDate(Timestamp auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * get the createDate - 创建时间
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the creatorName - 创建人
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the creatorId - 创建人ID 
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人ID 
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the updateDate - 更改时间
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    /**
     * set the updateDate - 更改时间
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get the updatorName - 更改人
     * @return the updatorName
     */
    public String getUpdatorName() {
        return this.updatorName;
    }

    /**
     * set the updatorName - 更改人
     */
    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    /**
     * get the updatorId - 更改人ID
     * @return the updatorId
     */
    public String getUpdatorId() {
        return this.updatorId;
    }

    /**
     * set the updatorId - 更改人ID
     */
    public void setUpdatorId(String updatorId) {
        this.updatorId = updatorId;
    }

    /**
     * get the operateType - 最后操作类型
     * @return the operateType
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * set the operateType - 最后操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * get the statusCode - 状态(0-新建，1-待审核，2-已审核，3-驳回)
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态(0-新建，1-待审核，2-已审核，3-驳回)
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the currentDealer - 满意度
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer - 满意度
     */
    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    /**
     * get the processInstId 
     * @return the processInstId
     */
    public String getProcessInstId() {
        return this.processInstId;
    }

    /**
     * set the processInstId 
     */
    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
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
     * get the numbers - 支援人数
     * @return the numbers
     */
    public Integer getNumbers() {
        return this.numbers;
    }

    /**
     * set the numbers - 支援人数
     */
    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    /**
     * get the auditOpinion - 审批意见
     * @return the auditOpinion
     */
    public String getAuditOpinion() {
        return this.auditOpinion;
    }

    /**
     * set the auditOpinion - 审批意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    /**
     * get the auditFlag 
     * @return the auditFlag
     */
    public String getAuditFlag() {
        return this.auditFlag;
    }

    /**
     * set the auditFlag 
     */
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    /**
     * get the supportStation 
     * @return the supportStation
     */
    public String getSupportStation() {
        return this.supportStation;
    }

    /**
     * set the supportStation 
     */
    public void setSupportStation(String supportStation) {
        this.supportStation = supportStation;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setBillTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billTime")), billTime));
        setArriveTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("arriveTime")), arriveTime));
        setLeaveTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("leaveTime")), leaveTime));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setChangeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeCode")), changeCode));
        setShiftTimeSection(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shiftTimeSection")), shiftTimeSection));
        setShiftFirstTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shiftFirstTime")), shiftFirstTime));
        setBecauseMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("becauseMemo")), becauseMemo));
        setAuditMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditMan")), auditMan));
        setAuditId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditId")), auditId));
        setAuditDate(DateUtils.toTimestamp(StringUtils.toString(map.get("auditDate"))));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setUpdateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("updateDate"))));
        setUpdatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorName")), updatorName));
        setUpdatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorId")), updatorId));
        setOperateType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateType")), operateType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setNumbers(NumberUtils.toInteger(StringUtils.toString(map.get("numbers")), numbers));
        setAuditOpinion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditOpinion")), auditOpinion));
        setAuditFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditFlag")), auditFlag));
        setSupportStation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("supportStation")), supportStation));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("billTime",StringUtils.toString(billTime, eiMetadata.getMeta("billTime")));
        map.put("arriveTime",StringUtils.toString(arriveTime, eiMetadata.getMeta("arriveTime")));
        map.put("leaveTime",StringUtils.toString(leaveTime, eiMetadata.getMeta("leaveTime")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("changeCode",StringUtils.toString(changeCode, eiMetadata.getMeta("changeCode")));
        map.put("shiftTimeSection",StringUtils.toString(shiftTimeSection, eiMetadata.getMeta("shiftTimeSection")));
        map.put("shiftFirstTime",StringUtils.toString(shiftFirstTime, eiMetadata.getMeta("shiftFirstTime")));
        map.put("becauseMemo",StringUtils.toString(becauseMemo, eiMetadata.getMeta("becauseMemo")));
        map.put("auditMan",StringUtils.toString(auditMan, eiMetadata.getMeta("auditMan")));
        map.put("auditId",StringUtils.toString(auditId, eiMetadata.getMeta("auditId")));
        map.put("auditDate",StringUtils.toString(auditDate, eiMetadata.getMeta("auditDate")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("updateDate",StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("updatorName",StringUtils.toString(updatorName, eiMetadata.getMeta("updatorName")));
        map.put("updatorId",StringUtils.toString(updatorId, eiMetadata.getMeta("updatorId")));
        map.put("operateType",StringUtils.toString(operateType, eiMetadata.getMeta("operateType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("numbers",StringUtils.toString(numbers, eiMetadata.getMeta("numbers")));
        map.put("auditOpinion",StringUtils.toString(auditOpinion, eiMetadata.getMeta("auditOpinion")));
        map.put("auditFlag",StringUtils.toString(auditFlag, eiMetadata.getMeta("auditFlag")));
        map.put("supportStation",StringUtils.toString(supportStation, eiMetadata.getMeta("supportStation")));
        return map;
    }
}