/**
* Generate time : 2022-01-13 13:19:44
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.pb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DRTimeConfig
* 
*/
public class HRTimeConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreatorName = " ";		/* 创建人姓名*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 更新人ID*/
    private String recRevisorName = " ";		/* 更新人*/
    private String recReviseTime = " ";		/* 更新时间*/
    private String startTime = " ";		/* 开始时间*/
    private String endTime = " ";		/* 结束时间*/
    private String restStartTime = " ";		/* 休息开始时间*/
    private String restEndTime = " ";		/* 休息结束时间*/
    private String daySpan = " ";		/* 是否跨天(0-否，1-是)*/
    private String scheduleName = " ";		/* 班次名称*/
    private String deptNum = " ";		/* 班组编码*/
    private String deptName = " ";		/* 班组名称*/
    private String status = " ";		/* 状态(0-停用，1-启用)*/
    private String clockStartTime = " ";		/* 出勤开始时间*/
    private String clockEndTime = " ";		/* 下班截止时间*/
    private String datagroupCode = " ";		
    private String position = " ";		/* 岗位*/
    private String statusName = " ";		/* 员工工作现在状态*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
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
        eiColumn.setDescName("更新人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisorName");
        eiColumn.setDescName("更新人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("startTime");
        eiColumn.setDescName("开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endTime");
        eiColumn.setDescName("结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("restStartTime");
        eiColumn.setDescName("休息开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("restEndTime");
        eiColumn.setDescName("休息结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("daySpan");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName("是否跨天(0-否，1-是)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleName");
        eiColumn.setDescName("班次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("班组编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("班组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName("状态(0-停用，1-启用)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("clockStartTime");
        eiColumn.setDescName("出勤开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("clockEndTime");
        eiColumn.setDescName("下班截止时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("position");
        eiColumn.setDescName("岗位");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("员工工作现在状态");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HRTimeConfig() {
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
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
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
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 更新人ID
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 更新人ID
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recRevisorName - 更新人
     * @return the recRevisorName
     */
    public String getRecRevisorName() {
        return this.recRevisorName;
    }

    /**
     * set the recRevisorName - 更新人
     */
    public void setRecRevisorName(String recRevisorName) {
        this.recRevisorName = recRevisorName;
    }

    /**
     * get the recReviseTime - 更新时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 更新时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the startTime - 开始时间
     * @return the startTime
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * set the startTime - 开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * get the endTime - 结束时间
     * @return the endTime
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * set the endTime - 结束时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * get the restStartTime - 休息开始时间
     * @return the restStartTime
     */
    public String getRestStartTime() {
        return this.restStartTime;
    }

    /**
     * set the restStartTime - 休息开始时间
     */
    public void setRestStartTime(String restStartTime) {
        this.restStartTime = restStartTime;
    }

    /**
     * get the restEndTime - 休息结束时间
     * @return the restEndTime
     */
    public String getRestEndTime() {
        return this.restEndTime;
    }

    /**
     * set the restEndTime - 休息结束时间
     */
    public void setRestEndTime(String restEndTime) {
        this.restEndTime = restEndTime;
    }

    /**
     * get the daySpan - 是否跨天(0-否，1-是)
     * @return the daySpan
     */
    public String getDaySpan() {
        return this.daySpan;
    }

    /**
     * set the daySpan - 是否跨天(0-否，1-是)
     */
    public void setDaySpan(String daySpan) {
        this.daySpan = daySpan;
    }

    /**
     * get the scheduleName - 班次名称
     * @return the scheduleName
     */
    public String getScheduleName() {
        return this.scheduleName;
    }

    /**
     * set the scheduleName - 班次名称
     */
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * get the deptNum - 班组编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 班组编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 班组名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 班组名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the status - 状态(0-停用，1-启用)
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态(0-停用，1-启用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the clockStartTime - 出勤开始时间
     * @return the clockStartTime
     */
    public String getClockStartTime() {
        return this.clockStartTime;
    }

    /**
     * set the clockStartTime - 出勤开始时间
     */
    public void setClockStartTime(String clockStartTime) {
        this.clockStartTime = clockStartTime;
    }

    /**
     * get the clockEndTime - 下班截止时间
     * @return the clockEndTime
     */
    public String getClockEndTime() {
        return this.clockEndTime;
    }

    /**
     * set the clockEndTime - 下班截止时间
     */
    public void setClockEndTime(String clockEndTime) {
        this.clockEndTime = clockEndTime;
    }

    /**
     * get the datagroupCode 
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode 
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }
    
    /**
     * get the position - 岗位
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position - 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }
    
    /**
     * get the statusName - 员工工作现在状态
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 员工工作现在状态
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorName")), recCreatorName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecRevisorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorName")), recRevisorName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("startTime")), startTime));
        setEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endTime")), endTime));
        setRestStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("restStartTime")), restStartTime));
        setRestEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("restEndTime")), restEndTime));
        setDaySpan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("daySpan")), daySpan));
        setScheduleName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleName")), scheduleName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setClockStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clockStartTime")), clockStartTime));
        setClockEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clockEndTime")), clockEndTime));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recRevisorName",StringUtils.toString(recRevisorName, eiMetadata.getMeta("recRevisorName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("startTime",StringUtils.toString(startTime, eiMetadata.getMeta("startTime")));
        map.put("endTime",StringUtils.toString(endTime, eiMetadata.getMeta("endTime")));
        map.put("restStartTime",StringUtils.toString(restStartTime, eiMetadata.getMeta("restStartTime")));
        map.put("restEndTime",StringUtils.toString(restEndTime, eiMetadata.getMeta("restEndTime")));
        map.put("daySpan",StringUtils.toString(daySpan, eiMetadata.getMeta("daySpan")));
        map.put("scheduleName",StringUtils.toString(scheduleName, eiMetadata.getMeta("scheduleName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("clockStartTime",StringUtils.toString(clockStartTime, eiMetadata.getMeta("clockStartTime")));
        map.put("clockEndTime",StringUtils.toString(clockEndTime, eiMetadata.getMeta("clockEndTime")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("position",StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        return map;
    }
}