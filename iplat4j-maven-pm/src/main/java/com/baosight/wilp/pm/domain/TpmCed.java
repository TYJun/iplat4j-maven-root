/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 项目进程表
 * 
 * @Title: TpmCed.java
 * @ClassName: TpmCed
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:21:34
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmCed extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 项目进程主键*/
    private String projectPk = " ";		/* 项目主键*/
    private String processArrange = " ";		/* 进程安排*/
    private String reportMakerId = " ";		/* 汇报人工号*/
    private Double expense = new Double(0);		/* 费用*/
    private String startTime = " ";		/* 开始时间*/
    private String endTime = " ";		/* 结束时间*/
    private String arrangeNote = " ";		/* 备注*/
    private String finishFlag = " ";		/* 完成标记*/
    private String schedule = " ";		/* 进度*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("项目进程主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectPk");
        eiColumn.setDescName("项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processArrange");
        eiColumn.setDescName("进程安排");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reportMakerId");
        eiColumn.setDescName("汇报人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("expense");
        eiColumn.setDescName("费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("startTime");
        eiColumn.setDescName("开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endTime");
        eiColumn.setDescName("结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("arrangeAddr");
        eiColumn.setDescName("日程地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("arrangeType");
        eiColumn.setDescName("日程类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("arrangeStatus");
        eiColumn.setDescName("日程选项");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("arrangeNote");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishFlag");
        eiColumn.setDescName("完成标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schedule");
        eiColumn.setDescName("进度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public TpmCed() {
        initMetaData();
    }

    /**
     * get the id - 项目进程主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 项目进程主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the projectPk - 项目主键
     * @return the projectPk
     */
    public String getProjectPk() {
        return this.projectPk;
    }

    /**
     * set the projectPk - 项目主键
     */
    public void setProjectPk(String projectPk) {
        this.projectPk = projectPk;
    }

    /**
     * get the processArrange - 进程安排
     * @return the processArrange
     */
    public String getProcessArrange() {
        return this.processArrange;
    }

    /**
     * set the processArrange - 进程安排
     */
    public void setProcessArrange(String processArrange) {
        this.processArrange = processArrange;
    }

    /**
     * get the reportMakerId - 汇报人工号
     * @return the reportMakerId
     */
    public String getReportMakerId() {
        return this.reportMakerId;
    }

    /**
     * set the reportMakerId - 汇报人工号
     */
    public void setReportMakerId(String reportMakerId) {
        this.reportMakerId = reportMakerId;
    }

    /**
     * get the expense - 费用
     * @return the expense
     */
    public Double getExpense() {
        return this.expense;
    }

    /**
     * set the expense - 费用
     */
    public void setExpense(Double expense) {
        this.expense = expense;
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
     * get the arrangeNote - 备注
     * @return the arrangeNote
     */
    public String getArrangeNote() {
        return this.arrangeNote;
    }

    /**
     * set the arrangeNote - 备注
     */
    public void setArrangeNote(String arrangeNote) {
        this.arrangeNote = arrangeNote;
    }

    /**
     * get the finishFlag - 完成标记
     * @return the finishFlag
     */
    public String getFinishFlag() {
        return this.finishFlag;
    }

    /**
     * set the finishFlag - 完成标记
     */
    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    /**
     * get the schedule - 进度
     * @return the schedule
     */
    public String getSchedule() {
        return this.schedule;
    }

    /**
     * set the schedule - 进度
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
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
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setProjectPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPk")), projectPk));
        setProcessArrange(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processArrange")), processArrange));
        setReportMakerId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportMakerId")), reportMakerId));
        setExpense(NumberUtils.toDouble(StringUtils.toString(map.get("expense")), expense));
        setStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("startTime")), startTime));
        setEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endTime")), endTime));
        setArrangeNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("arrangeNote")), arrangeNote));
        setFinishFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishFlag")), finishFlag));
        setSchedule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schedule")), schedule));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectPk",StringUtils.toString(projectPk, eiMetadata.getMeta("projectPk")));
        map.put("processArrange",StringUtils.toString(processArrange, eiMetadata.getMeta("processArrange")));
        map.put("reportMakerId",StringUtils.toString(reportMakerId, eiMetadata.getMeta("reportMakerId")));
        map.put("expense",StringUtils.toString(expense, eiMetadata.getMeta("expense")));
        map.put("startTime",StringUtils.toString(startTime, eiMetadata.getMeta("startTime")));
        map.put("endTime",StringUtils.toString(endTime, eiMetadata.getMeta("endTime")));
        map.put("arrangeNote",StringUtils.toString(arrangeNote, eiMetadata.getMeta("arrangeNote")));
        map.put("finishFlag",StringUtils.toString(finishFlag, eiMetadata.getMeta("finishFlag")));
        map.put("schedule",StringUtils.toString(schedule, eiMetadata.getMeta("schedule")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}