/**
* Generate time : 2021-11-23 15:05:25
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cs.re.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CsGdMx 保洁工单明细表主体
* cs_gd_mx
*/
public class CsGdMx extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 保洁工单明细主键（UUID）*/
    private String taskId = " ";		/* 保洁工单主表主键*/
    private String statusCode = " ";		/* 工单状态(01待确认，02待完工，03待评价，98已撤销，99已结束)*/
    private String itemTypeCode = " ";		/* 保洁事项分类编码*/
    private String itemTypeName = " ";		/* 保洁事项分类*/
    private String itemCode = " ";		/* 保洁事项编码*/
    private String itemName = " ";		/* 保洁事项*/
    private String itemComments = " ";		/* 事项备注*/
    private String serviceDeptNum = " ";		/* 服务科室编码*/
    private String serviceDeptName = " ";		/* 服务科室名称*/
    private String executeUserNo = " ";		/* 保洁执行人工号*/
    private String executeUserName = " ";		/* 保洁执行人姓名*/
    private String evalGrade = " ";		/* 评价等级*/
    private String evalOpinion = " ";		/* 评价意见*/
    private String evalUserNo = " ";		/* 工单评价人工号*/
    private String evalUserName = " ";		/* 工单评价人姓名*/
    private String evalTime = " ";		/* 工单评价时间*/
    private String evalStatus = " ";		/* 评价状态(00为未评价，01为已评价)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("保洁工单明细主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskId");
        eiColumn.setDescName("保洁工单主表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("工单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemTypeCode");
        eiColumn.setDescName("保洁事项分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemTypeName");
        eiColumn.setDescName("保洁事项分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemCode");
        eiColumn.setDescName("保洁事项编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemName");
        eiColumn.setDescName("保洁事项");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemComments");
        eiColumn.setDescName("事项备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptNum");
        eiColumn.setDescName("服务科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptName");
        eiColumn.setDescName("服务科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executeUserNo");
        eiColumn.setDescName("保洁执行人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executeUserName");
        eiColumn.setDescName("保洁执行人姓名");
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

        eiColumn = new EiColumn("evalStatus");
        eiColumn.setDescName("评价状态(00为未评价，01为已评价)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CsGdMx() {
        initMetaData();
    }

    /**
     * get the id - 保洁工单明细主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 保洁工单明细主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the taskId - 保洁工单主表主键
     * @return the taskId
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * set the taskId - 保洁工单主表主键
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
     * get the itemTypeCode - 保洁事项分类编码
     * @return the itemTypeCode
     */
    public String getItemTypeCode() {
        return this.itemTypeCode;
    }

    /**
     * set the itemTypeCode - 保洁事项分类编码
     */
    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    /**
     * get the itemTypeName - 保洁事项分类
     * @return the itemTypeName
     */
    public String getItemTypeName() {
        return this.itemTypeName;
    }

    /**
     * set the itemTypeName - 保洁事项分类
     */
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    /**
     * get the itemCode - 保洁事项编码
     * @return the itemCode
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * set the itemCode - 保洁事项编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * get the itemName - 保洁事项
     * @return the itemName
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * set the itemName - 保洁事项
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * get the itemComments - 事项备注
     * @return the itemComments
     */
    public String getItemComments() {
        return this.itemComments;
    }

    /**
     * set the itemComments - 事项备注
     */
    public void setItemComments(String itemComments) {
        this.itemComments = itemComments;
    }

    /**
     * get the serviceDeptNum - 服务科室编码
     * @return the serviceDeptNum
     */
    public String getServiceDeptNum() {
        return this.serviceDeptNum;
    }

    /**
     * set the serviceDeptNum - 服务科室编码
     */
    public void setServiceDeptNum(String serviceDeptNum) {
        this.serviceDeptNum = serviceDeptNum;
    }

    /**
     * get the serviceDeptName - 服务科室名称
     * @return the serviceDeptName
     */
    public String getServiceDeptName() {
        return this.serviceDeptName;
    }

    /**
     * set the serviceDeptName - 服务科室名称
     */
    public void setServiceDeptName(String serviceDeptName) {
        this.serviceDeptName = serviceDeptName;
    }

    /**
     * get the executeUserNo - 保洁执行人工号
     * @return the executeUserNo
     */
    public String getExecuteUserNo() {
        return this.executeUserNo;
    }

    /**
     * set the executeUserNo - 保洁执行人工号
     */
    public void setExecuteUserNo(String executeUserNo) {
        this.executeUserNo = executeUserNo;
    }

    /**
     * get the executeUserName - 保洁执行人姓名
     * @return the executeUserName
     */
    public String getExecuteUserName() {
        return this.executeUserName;
    }

    /**
     * set the executeUserName - 保洁执行人姓名
     */
    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
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
     * get the evalStatus - 评价状态(00为未评价，01为已评价)
     * @return the evalStatus
     */
    public String getEvalStatus() {
        return this.evalStatus;
    }

    /**
     * set the evalStatus - 评价状态(00为未评价，01为已评价)
     */
    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTaskId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskId")), taskId));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setItemTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemTypeCode")), itemTypeCode));
        setItemTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemTypeName")), itemTypeName));
        setItemCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemCode")), itemCode));
        setItemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemName")), itemName));
        setItemComments(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemComments")), itemComments));
        setServiceDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptNum")), serviceDeptNum));
        setServiceDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptName")), serviceDeptName));
        setExecuteUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executeUserNo")), executeUserNo));
        setExecuteUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executeUserName")), executeUserName));
        setEvalGrade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalGrade")), evalGrade));
        setEvalOpinion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalOpinion")), evalOpinion));
        setEvalUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalUserNo")), evalUserNo));
        setEvalUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalUserName")), evalUserName));
        setEvalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTime")), evalTime));
        setEvalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalStatus")), evalStatus));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("taskId",StringUtils.toString(taskId, eiMetadata.getMeta("taskId")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("itemTypeCode",StringUtils.toString(itemTypeCode, eiMetadata.getMeta("itemTypeCode")));
        map.put("itemTypeName",StringUtils.toString(itemTypeName, eiMetadata.getMeta("itemTypeName")));
        map.put("itemCode",StringUtils.toString(itemCode, eiMetadata.getMeta("itemCode")));
        map.put("itemName",StringUtils.toString(itemName, eiMetadata.getMeta("itemName")));
        map.put("itemComments",StringUtils.toString(itemComments, eiMetadata.getMeta("itemComments")));
        map.put("serviceDeptNum",StringUtils.toString(serviceDeptNum, eiMetadata.getMeta("serviceDeptNum")));
        map.put("serviceDeptName",StringUtils.toString(serviceDeptName, eiMetadata.getMeta("serviceDeptName")));
        map.put("executeUserNo",StringUtils.toString(executeUserNo, eiMetadata.getMeta("executeUserNo")));
        map.put("executeUserName",StringUtils.toString(executeUserName, eiMetadata.getMeta("executeUserName")));
        map.put("evalGrade",StringUtils.toString(evalGrade, eiMetadata.getMeta("evalGrade")));
        map.put("evalOpinion",StringUtils.toString(evalOpinion, eiMetadata.getMeta("evalOpinion")));
        map.put("evalUserNo",StringUtils.toString(evalUserNo, eiMetadata.getMeta("evalUserNo")));
        map.put("evalUserName",StringUtils.toString(evalUserName, eiMetadata.getMeta("evalUserName")));
        map.put("evalTime",StringUtils.toString(evalTime, eiMetadata.getMeta("evalTime")));
        map.put("evalStatus",StringUtils.toString(evalStatus, eiMetadata.getMeta("evalStatus")));
        return map;
    }
}