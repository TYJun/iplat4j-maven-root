/**
* Generate time : 2022-03-24 12:46:36
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.rz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoomMan
* 
*/
public class DormsRoomMan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String roomId = " ";		/* 宿舍信息表ID*/
    private String manId = " ";		/* 人员入住信息表ID*/
    private String keepRoomDays = " ";		/* 宿舍保留天数*/
    private String bedNo = " ";		/* 床位号*/
    private String payStatus = "0";		/* 是否已经上传附件交钱(0为未交钱，1已交钱)*/
    private String actualInDate = " ";		/* 实际入住时间*/
    private String actualOutDate = " ";		/* 实际退房时间*/
    private String changeRoomStatus = "0";		/* 是否换过宿(0为未换过宿舍，1为换过宿舍)*/
    private String changeRoomNote = " ";		/* 调宿备注*/
    private String applyChangeDate = " ";		/* 申请调宿时间*/
    private String outRoomStatus = "0";		/* 退宿状态(0为未退宿，1为已退宿)*/
    private String outRoomNote = " ";		/* 退宿备注*/
    private String applyOutDate = " ";		/* 申请退宿时间*/
    private String checkoutRoomStatus = "0";		/* 是否已经进行退宿检查清单(0为未进行，1已进行)*/
    private String actualRent = " ";		/* 实际房租*/
    private String actualManageFee = " ";		/* 实际管理费*/
    private String evalStatus = "0";		/* 是否已经进行过评价(0为未评价，1已评价)*/
    private String evalLevelWy = " ";		/* 物业管理-评价等级(0-5)*/
    private String evalLevelRoom = " ";		/* 宿舍情况-评价等级(0-5)*/
    private String evalContent = " ";		/* 评价内容*/
    private String lastOperator = " ";		/* 最后操作人工号*/
    private String lastOperationName = " ";		/* 最后操作人人姓名*/
    private String lastOperationTime = " ";		/* 最后操作时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomId");
        eiColumn.setDescName("宿舍信息表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员入住信息表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("keepRoomDays");
        eiColumn.setDescName("宿舍保留天数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payStatus");
        eiColumn.setDescName("是否已经上传附件交钱(0为未交钱，1已交钱)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualInDate");
        eiColumn.setDescName("实际入住时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualOutDate");
        eiColumn.setDescName("实际退房时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("changeRoomStatus");
        eiColumn.setDescName("是否换过宿(0为未换过宿舍，1为换过宿舍)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("changeRoomNote");
        eiColumn.setDescName("调宿备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyChangeDate");
        eiColumn.setDescName("申请调宿时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outRoomStatus");
        eiColumn.setDescName("退宿状态(0为未退宿，1为已退宿)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outRoomNote");
        eiColumn.setDescName("退宿备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyOutDate");
        eiColumn.setDescName("申请退宿时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkoutRoomStatus");
        eiColumn.setDescName("是否已经进行退宿检查清单(0为未进行，1已进行)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualRent");
        eiColumn.setDescName("实际房租");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualManageFee");
        eiColumn.setDescName("实际管理费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalStatus");
        eiColumn.setDescName("是否已经进行过评价(0为未评价，1已评价)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevelWy");
        eiColumn.setDescName("物业管理-评价等级(0-5)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevelRoom");
        eiColumn.setDescName("宿舍情况-评价等级(0-5)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastOperator");
        eiColumn.setDescName("最后操作人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastOperationName");
        eiColumn.setDescName("最后操作人人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastOperationTime");
        eiColumn.setDescName("最后操作时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsRoomMan() {
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
     * get the roomId - 宿舍信息表ID
     * @return the roomId
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the roomId - 宿舍信息表ID
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the manId - 人员入住信息表ID
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员入住信息表ID
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the keepRoomDays - 宿舍保留天数
     * @return the keepRoomDays
     */
    public String getKeepRoomDays() {
        return this.keepRoomDays;
    }

    /**
     * set the keepRoomDays - 宿舍保留天数
     */
    public void setKeepRoomDays(String keepRoomDays) {
        this.keepRoomDays = keepRoomDays;
    }

    /**
     * get the bedNo - 床位号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床位号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the payStatus - 是否已经上传附件交钱(0为未交钱，1已交钱)
     * @return the payStatus
     */
    public String getPayStatus() {
        return this.payStatus;
    }

    /**
     * set the payStatus - 是否已经上传附件交钱(0为未交钱，1已交钱)
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * get the actualInDate - 实际入住时间
     * @return the actualInDate
     */
    public String getActualInDate() {
        return this.actualInDate;
    }

    /**
     * set the actualInDate - 实际入住时间
     */
    public void setActualInDate(String actualInDate) {
        this.actualInDate = actualInDate;
    }

    /**
     * get the actualOutDate - 实际退房时间
     * @return the actualOutDate
     */
    public String getActualOutDate() {
        return this.actualOutDate;
    }

    /**
     * set the actualOutDate - 实际退房时间
     */
    public void setActualOutDate(String actualOutDate) {
        this.actualOutDate = actualOutDate;
    }

    /**
     * get the changeRoomStatus - 是否换过宿(0为未换过宿舍，1为换过宿舍)
     * @return the changeRoomStatus
     */
    public String getChangeRoomStatus() {
        return this.changeRoomStatus;
    }

    /**
     * set the changeRoomStatus - 是否换过宿(0为未换过宿舍，1为换过宿舍)
     */
    public void setChangeRoomStatus(String changeRoomStatus) {
        this.changeRoomStatus = changeRoomStatus;
    }

    /**
     * get the changeRoomNote - 调宿备注
     * @return the changeRoomNote
     */
    public String getChangeRoomNote() {
        return this.changeRoomNote;
    }

    /**
     * set the changeRoomNote - 调宿备注
     */
    public void setChangeRoomNote(String changeRoomNote) {
        this.changeRoomNote = changeRoomNote;
    }

    /**
     * get the applyChangeDate - 申请调宿时间
     * @return the applyChangeDate
     */
    public String getApplyChangeDate() {
        return this.applyChangeDate;
    }

    /**
     * set the applyChangeDate - 申请调宿时间
     */
    public void setApplyChangeDate(String applyChangeDate) {
        this.applyChangeDate = applyChangeDate;
    }

    /**
     * get the outRoomStatus - 退宿状态(0为未退宿，1为已退宿)
     * @return the outRoomStatus
     */
    public String getOutRoomStatus() {
        return this.outRoomStatus;
    }

    /**
     * set the outRoomStatus - 退宿状态(0为未退宿，1为已退宿)
     */
    public void setOutRoomStatus(String outRoomStatus) {
        this.outRoomStatus = outRoomStatus;
    }

    /**
     * get the outRoomNote - 退宿备注
     * @return the outRoomNote
     */
    public String getOutRoomNote() {
        return this.outRoomNote;
    }

    /**
     * set the outRoomNote - 退宿备注
     */
    public void setOutRoomNote(String outRoomNote) {
        this.outRoomNote = outRoomNote;
    }

    /**
     * get the applyOutDate - 申请退宿时间
     * @return the applyOutDate
     */
    public String getApplyOutDate() {
        return this.applyOutDate;
    }

    /**
     * set the applyOutDate - 申请退宿时间
     */
    public void setApplyOutDate(String applyOutDate) {
        this.applyOutDate = applyOutDate;
    }

    /**
     * get the checkoutRoomStatus - 是否已经进行退宿检查清单(0为未进行，1已进行)
     * @return the checkoutRoomStatus
     */
    public String getCheckoutRoomStatus() {
        return this.checkoutRoomStatus;
    }

    /**
     * set the checkoutRoomStatus - 是否已经进行退宿检查清单(0为未进行，1已进行)
     */
    public void setCheckoutRoomStatus(String checkoutRoomStatus) {
        this.checkoutRoomStatus = checkoutRoomStatus;
    }

    /**
     * get the actualRent - 实际房租
     * @return the actualRent
     */
    public String getActualRent() {
        return this.actualRent;
    }

    /**
     * set the actualRent - 实际房租
     */
    public void setActualRent(String actualRent) {
        this.actualRent = actualRent;
    }

    /**
     * get the actualManageFee - 实际管理费
     * @return the actualManageFee
     */
    public String getActualManageFee() {
        return this.actualManageFee;
    }

    /**
     * set the actualManageFee - 实际管理费
     */
    public void setActualManageFee(String actualManageFee) {
        this.actualManageFee = actualManageFee;
    }

    /**
     * get the evalStatus - 是否已经进行过评价(0为未评价，1已评价)
     * @return the evalStatus
     */
    public String getEvalStatus() {
        return this.evalStatus;
    }

    /**
     * set the evalStatus - 是否已经进行过评价(0为未评价，1已评价)
     */
    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    /**
     * get the evalLevelWy - 物业管理-评价等级(0-5)
     * @return the evalLevelWy
     */
    public String getEvalLevelWy() {
        return this.evalLevelWy;
    }

    /**
     * set the evalLevelWy - 物业管理-评价等级(0-5)
     */
    public void setEvalLevelWy(String evalLevelWy) {
        this.evalLevelWy = evalLevelWy;
    }

    /**
     * get the evalLevelRoom - 宿舍情况-评价等级(0-5)
     * @return the evalLevelRoom
     */
    public String getEvalLevelRoom() {
        return this.evalLevelRoom;
    }

    /**
     * set the evalLevelRoom - 宿舍情况-评价等级(0-5)
     */
    public void setEvalLevelRoom(String evalLevelRoom) {
        this.evalLevelRoom = evalLevelRoom;
    }

    /**
     * get the evalContent - 评价内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the lastOperator - 最后操作人工号
     * @return the lastOperator
     */
    public String getLastOperator() {
        return this.lastOperator;
    }

    /**
     * set the lastOperator - 最后操作人工号
     */
    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    /**
     * get the lastOperationName - 最后操作人人姓名
     * @return the lastOperationName
     */
    public String getLastOperationName() {
        return this.lastOperationName;
    }

    /**
     * set the lastOperationName - 最后操作人人姓名
     */
    public void setLastOperationName(String lastOperationName) {
        this.lastOperationName = lastOperationName;
    }

    /**
     * get the lastOperationTime - 最后操作时间
     * @return the lastOperationTime
     */
    public String getLastOperationTime() {
        return this.lastOperationTime;
    }

    /**
     * set the lastOperationTime - 最后操作时间
     */
    public void setLastOperationTime(String lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRoomId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomId")), roomId));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setKeepRoomDays(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("keepRoomDays")), keepRoomDays));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setPayStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payStatus")), payStatus));
        setActualInDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualInDate")), actualInDate));
        setActualOutDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualOutDate")), actualOutDate));
        setChangeRoomStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeRoomStatus")), changeRoomStatus));
        setChangeRoomNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeRoomNote")), changeRoomNote));
        setApplyChangeDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyChangeDate")), applyChangeDate));
        setOutRoomStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outRoomStatus")), outRoomStatus));
        setOutRoomNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outRoomNote")), outRoomNote));
        setApplyOutDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyOutDate")), applyOutDate));
        setCheckoutRoomStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkoutRoomStatus")), checkoutRoomStatus));
        setActualRent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualRent")), actualRent));
        setActualManageFee(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualManageFee")), actualManageFee));
        setEvalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalStatus")), evalStatus));
        setEvalLevelWy(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalLevelWy")), evalLevelWy));
        setEvalLevelRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalLevelRoom")), evalLevelRoom));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setLastOperator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastOperator")), lastOperator));
        setLastOperationName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastOperationName")), lastOperationName));
        setLastOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastOperationTime")), lastOperationTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("roomId",StringUtils.toString(roomId, eiMetadata.getMeta("roomId")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("keepRoomDays",StringUtils.toString(keepRoomDays, eiMetadata.getMeta("keepRoomDays")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("payStatus",StringUtils.toString(payStatus, eiMetadata.getMeta("payStatus")));
        map.put("actualInDate",StringUtils.toString(actualInDate, eiMetadata.getMeta("actualInDate")));
        map.put("actualOutDate",StringUtils.toString(actualOutDate, eiMetadata.getMeta("actualOutDate")));
        map.put("changeRoomStatus",StringUtils.toString(changeRoomStatus, eiMetadata.getMeta("changeRoomStatus")));
        map.put("changeRoomNote",StringUtils.toString(changeRoomNote, eiMetadata.getMeta("changeRoomNote")));
        map.put("applyChangeDate",StringUtils.toString(applyChangeDate, eiMetadata.getMeta("applyChangeDate")));
        map.put("outRoomStatus",StringUtils.toString(outRoomStatus, eiMetadata.getMeta("outRoomStatus")));
        map.put("outRoomNote",StringUtils.toString(outRoomNote, eiMetadata.getMeta("outRoomNote")));
        map.put("applyOutDate",StringUtils.toString(applyOutDate, eiMetadata.getMeta("applyOutDate")));
        map.put("checkoutRoomStatus",StringUtils.toString(checkoutRoomStatus, eiMetadata.getMeta("checkoutRoomStatus")));
        map.put("actualRent",StringUtils.toString(actualRent, eiMetadata.getMeta("actualRent")));
        map.put("actualManageFee",StringUtils.toString(actualManageFee, eiMetadata.getMeta("actualManageFee")));
        map.put("evalStatus",StringUtils.toString(evalStatus, eiMetadata.getMeta("evalStatus")));
        map.put("evalLevelWy",StringUtils.toString(evalLevelWy, eiMetadata.getMeta("evalLevelWy")));
        map.put("evalLevelRoom",StringUtils.toString(evalLevelRoom, eiMetadata.getMeta("evalLevelRoom")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("lastOperator",StringUtils.toString(lastOperator, eiMetadata.getMeta("lastOperator")));
        map.put("lastOperationName",StringUtils.toString(lastOperationName, eiMetadata.getMeta("lastOperationName")));
        map.put("lastOperationTime",StringUtils.toString(lastOperationTime, eiMetadata.getMeta("lastOperationTime")));
        return map;
    }
}