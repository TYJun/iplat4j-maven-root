/**
* Generate time : 2021-11-23 14:44:48
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cs.re.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CsGd 保洁工单主表实体
* cs_gd
*/
public class CsGd extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String taskNo = " ";		/* 工单号*/
    private String statusCode = " ";		/* 工单状态(01待确认，02待完工，03待评价，98已撤销，99已结束)*/
    private String reqStaffId = " ";		/* 来电人工号*/
    private String reqStaffName = " ";		/* 来电人姓名*/
    private String reqTelNum = " ";		/* 来电电话*/
    private String reqDeptNum = " ";		/* 来电科室编码*/
    private String reqDeptName = " ";		/* 来电科室名*/
    private String reqSpotNum = " ";		/* 保洁地点编码*/
    private String reqSpotName = " ";		/* 保洁地点名*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String comments = " ";		/* 备注*/
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人工号*/
    private String recReviseName = " ";		/* 修改人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String confirmUserNo = " ";		/* 工单确认人工号*/
    private String confirmUserName = " ";		/* 工单确认人姓名*/
    private String confirmTime = " ";		/* 工单确认时间*/
    private String finishUserNo = " ";		/* 工单完工人工号*/
    private String finishUserName = " ";		/* 工单完工人姓名*/
    private String finishTime = " ";		/* 工单完工时间*/
    private String evalGrade = " ";		/* 评价等级*/
    private String evalOpinion = " ";		/* 评价意见*/
    private String evalUserNo = " ";		/* 工单评价人工号*/
    private String evalUserName = " ";		/* 工单评价人姓名*/
    private String evalTime = " ";		/* 工单评价时间*/
    private String printFlag = "N";		/* 打印标记*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String datagroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskNo");
        eiColumn.setDescName("工单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("工单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqStaffId");
        eiColumn.setDescName("来电人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqStaffName");
        eiColumn.setDescName("来电人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqTelNum");
        eiColumn.setDescName("来电电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqDeptNum");
        eiColumn.setDescName("来电科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqDeptName");
        eiColumn.setDescName("来电科室名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSpotNum");
        eiColumn.setDescName("保洁地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSpotName");
        eiColumn.setDescName("保洁地点名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comments");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("修改人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmUserNo");
        eiColumn.setDescName("工单确认人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmUserName");
        eiColumn.setDescName("工单确认人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmTime");
        eiColumn.setDescName("工单确认时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishUserNo");
        eiColumn.setDescName("工单完工人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishUserName");
        eiColumn.setDescName("工单完工人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishTime");
        eiColumn.setDescName("工单完工时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalGrade");
        eiColumn.setDescName("评价等级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalOpinion");
        eiColumn.setDescName("评价意见");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalUserNo");
        eiColumn.setDescName("工单评价人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalUserName");
        eiColumn.setDescName("工单评价人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTime");
        eiColumn.setDescName("工单评价时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CsGd() {
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
     * get the taskNo - 工单号
     * @return the taskNo
     */
    public String getTaskNo() {
        return this.taskNo;
    }

    /**
     * set the taskNo - 工单号
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * get the statusCode - 工单状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 工单状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the reqStaffId - 来电人工号
     * @return the reqStaffId
     */
    public String getReqStaffId() {
        return this.reqStaffId;
    }

    /**
     * set the reqStaffId - 来电人工号
     */
    public void setReqStaffId(String reqStaffId) {
        this.reqStaffId = reqStaffId;
    }

    /**
     * get the reqStaffName - 来电人姓名
     * @return the reqStaffName
     */
    public String getReqStaffName() {
        return this.reqStaffName;
    }

    /**
     * set the reqStaffName - 来电人姓名
     */
    public void setReqStaffName(String reqStaffName) {
        this.reqStaffName = reqStaffName;
    }

    /**
     * get the reqTelNum - 来电电话
     * @return the reqTelNum
     */
    public String getReqTelNum() {
        return this.reqTelNum;
    }

    /**
     * set the reqTelNum - 来电电话
     */
    public void setReqTelNum(String reqTelNum) {
        this.reqTelNum = reqTelNum;
    }

    /**
     * get the reqDeptNum - 来电科室编码
     * @return the reqDeptNum
     */
    public String getReqDeptNum() {
        return this.reqDeptNum;
    }

    /**
     * set the reqDeptNum - 来电科室编码
     */
    public void setReqDeptNum(String reqDeptNum) {
        this.reqDeptNum = reqDeptNum;
    }

    /**
     * get the reqDeptName - 来电科室名
     * @return the reqDeptName
     */
    public String getReqDeptName() {
        return this.reqDeptName;
    }

    /**
     * set the reqDeptName - 来电科室名
     */
    public void setReqDeptName(String reqDeptName) {
        this.reqDeptName = reqDeptName;
    }

    /**
     * get the reqSpotNum - 保洁地点编码
     * @return the reqSpotNum
     */
    public String getReqSpotNum() {
        return this.reqSpotNum;
    }

    /**
     * set the reqSpotNum - 保洁地点编码
     */
    public void setReqSpotNum(String reqSpotNum) {
        this.reqSpotNum = reqSpotNum;
    }

    /**
     * get the reqSpotName - 保洁地点名
     * @return the reqSpotName
     */
    public String getReqSpotName() {
        return this.reqSpotName;
    }

    /**
     * set the reqSpotName - 保洁地点名
     */
    public void setReqSpotName(String reqSpotName) {
        this.reqSpotName = reqSpotName;
    }

    /**
     * get the building - 楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the floor - 层
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the comments - 备注
     * @return the comments
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * set the comments - 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
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
     * get the recRevisor - 修改人工号
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人工号
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseName - 修改人姓名
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 修改人姓名
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
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
     * get the confirmUserNo - 工单确认人工号
     * @return the confirmUserNo
     */
    public String getConfirmUserNo() {
        return this.confirmUserNo;
    }

    /**
     * set the confirmUserNo - 工单确认人工号
     */
    public void setConfirmUserNo(String confirmUserNo) {
        this.confirmUserNo = confirmUserNo;
    }

    /**
     * get the confirmUserName - 工单确认人姓名
     * @return the confirmUserName
     */
    public String getConfirmUserName() {
        return this.confirmUserName;
    }

    /**
     * set the confirmUserName - 工单确认人姓名
     */
    public void setConfirmUserName(String confirmUserName) {
        this.confirmUserName = confirmUserName;
    }

    /**
     * get the confirmTime - 工单确认时间
     * @return the confirmTime
     */
    public String getConfirmTime() {
        return this.confirmTime;
    }

    /**
     * set the confirmTime - 工单确认时间
     */
    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * get the finishUserNo - 工单完工人工号
     * @return the finishUserNo
     */
    public String getFinishUserNo() {
        return this.finishUserNo;
    }

    /**
     * set the finishUserNo - 工单完工人工号
     */
    public void setFinishUserNo(String finishUserNo) {
        this.finishUserNo = finishUserNo;
    }

    /**
     * get the finishUserName - 工单完工人姓名
     * @return the finishUserName
     */
    public String getFinishUserName() {
        return this.finishUserName;
    }

    /**
     * set the finishUserName - 工单完工人姓名
     */
    public void setFinishUserName(String finishUserName) {
        this.finishUserName = finishUserName;
    }

    /**
     * get the finishTime - 工单完工时间
     * @return the finishTime
     */
    public String getFinishTime() {
        return this.finishTime;
    }

    /**
     * set the finishTime - 工单完工时间
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * get the evalGrade - 评价等级
     * @return the evalGrade
     */
    public String getEvalGrade() {
        return this.evalGrade;
    }

    /**
     * set the evalGrade - 评价等级
     */
    public void setEvalGrade(String evalGrade) {
        this.evalGrade = evalGrade;
    }

    /**
     * get the evalOpinion - 评价意见
     * @return the evalOpinion
     */
    public String getEvalOpinion() {
        return this.evalOpinion;
    }

    /**
     * set the evalOpinion - 评价意见
     */
    public void setEvalOpinion(String evalOpinion) {
        this.evalOpinion = evalOpinion;
    }

    /**
     * get the evalUserNo - 工单评价人工号
     * @return the evalUserNo
     */
    public String getEvalUserNo() {
        return this.evalUserNo;
    }

    /**
     * set the evalUserNo - 工单评价人工号
     */
    public void setEvalUserNo(String evalUserNo) {
        this.evalUserNo = evalUserNo;
    }

    /**
     * get the evalUserName - 工单评价人姓名
     * @return the evalUserName
     */
    public String getEvalUserName() {
        return this.evalUserName;
    }

    /**
     * set the evalUserName - 工单评价人姓名
     */
    public void setEvalUserName(String evalUserName) {
        this.evalUserName = evalUserName;
    }

    /**
     * get the evalTime - 工单评价时间
     * @return the evalTime
     */
    public String getEvalTime() {
        return this.evalTime;
    }

    /**
     * set the evalTime - 工单评价时间
     */
    public void setEvalTime(String evalTime) {
        this.evalTime = evalTime;
    }

    /**
     * get the printFlag - 打印标记
     * @return the printFlag
     */
    public String getPrintFlag() {
        return this.printFlag;
    }

    /**
     * set the printFlag - 打印标记
     */
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
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
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTaskNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskNo")), taskNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setReqStaffId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqStaffId")), reqStaffId));
        setReqStaffName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqStaffName")), reqStaffName));
        setReqTelNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqTelNum")), reqTelNum));
        setReqDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqDeptNum")), reqDeptNum));
        setReqDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqDeptName")), reqDeptName));
        setReqSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSpotNum")), reqSpotNum));
        setReqSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSpotName")), reqSpotName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setComments(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comments")), comments));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setConfirmUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmUserNo")), confirmUserNo));
        setConfirmUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmUserName")), confirmUserName));
        setConfirmTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmTime")), confirmTime));
        setFinishUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishUserNo")), finishUserNo));
        setFinishUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishUserName")), finishUserName));
        setFinishTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishTime")), finishTime));
        setEvalGrade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalGrade")), evalGrade));
        setEvalOpinion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalOpinion")), evalOpinion));
        setEvalUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalUserNo")), evalUserNo));
        setEvalUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalUserName")), evalUserName));
        setEvalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTime")), evalTime));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("taskNo",StringUtils.toString(taskNo, eiMetadata.getMeta("taskNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("reqStaffId",StringUtils.toString(reqStaffId, eiMetadata.getMeta("reqStaffId")));
        map.put("reqStaffName",StringUtils.toString(reqStaffName, eiMetadata.getMeta("reqStaffName")));
        map.put("reqTelNum",StringUtils.toString(reqTelNum, eiMetadata.getMeta("reqTelNum")));
        map.put("reqDeptNum",StringUtils.toString(reqDeptNum, eiMetadata.getMeta("reqDeptNum")));
        map.put("reqDeptName",StringUtils.toString(reqDeptName, eiMetadata.getMeta("reqDeptName")));
        map.put("reqSpotNum",StringUtils.toString(reqSpotNum, eiMetadata.getMeta("reqSpotNum")));
        map.put("reqSpotName",StringUtils.toString(reqSpotName, eiMetadata.getMeta("reqSpotName")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("comments",StringUtils.toString(comments, eiMetadata.getMeta("comments")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("confirmUserNo",StringUtils.toString(confirmUserNo, eiMetadata.getMeta("confirmUserNo")));
        map.put("confirmUserName",StringUtils.toString(confirmUserName, eiMetadata.getMeta("confirmUserName")));
        map.put("confirmTime",StringUtils.toString(confirmTime, eiMetadata.getMeta("confirmTime")));
        map.put("finishUserNo",StringUtils.toString(finishUserNo, eiMetadata.getMeta("finishUserNo")));
        map.put("finishUserName",StringUtils.toString(finishUserName, eiMetadata.getMeta("finishUserName")));
        map.put("finishTime",StringUtils.toString(finishTime, eiMetadata.getMeta("finishTime")));
        map.put("evalGrade",StringUtils.toString(evalGrade, eiMetadata.getMeta("evalGrade")));
        map.put("evalOpinion",StringUtils.toString(evalOpinion, eiMetadata.getMeta("evalOpinion")));
        map.put("evalUserNo",StringUtils.toString(evalUserNo, eiMetadata.getMeta("evalUserNo")));
        map.put("evalUserName",StringUtils.toString(evalUserName, eiMetadata.getMeta("evalUserName")));
        map.put("evalTime",StringUtils.toString(evalTime, eiMetadata.getMeta("evalTime")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        return map;
    }
}