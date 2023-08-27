/**
* Generate time : 2022-03-13 11:57:52
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.xx.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoom
* 
*/
public class DormsRoom extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String building = " ";		/* 宿舍楼*/
    private String floor = " ";		/* 宿舍层*/
    private String roomNo = " ";		/* 宿舍号*/
    private String roomName = " ";		/* 宿舍总称(楼+层+宿舍号)*/
    private Integer bedNum = new Integer(0);		/* 床位数*/
    private Integer remainingBedNum = new Integer(0);		/* 剩余床位数*/
    private String typeCode = " ";		/* 房间类型(1男生宿舍/0女生宿舍)*/
    private String dormPosition = " ";		/* 宿舍位置：院内、院外*/
    private String dormArea = " ";		/* 房屋面积：\"<50㎡\"、\"50-100㎡\"、\">100㎡\"*/
    private String elevatorRoom = " ";		/* 是否为电梯房*/
    private String priBathroom = " ";		/* 独立卫生间：有、没有*/
    private String rent = " ";		/* 房租*/
    private String manageFee = " ";		/* 管理费*/
    private String note = " ";		/* 备注信息*/
    private String recCreaterNo = " ";		/* 创建人工号*/
    private String recCreaterName = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisorNo = " ";		/* 修改人工号*/
    private String recRevisorName = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("宿舍楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("宿舍层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomNo");
        eiColumn.setDescName("宿舍号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomName");
        eiColumn.setDescName("宿舍总称(楼+层+宿舍号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNum");
        eiColumn.setDescName("床位数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remainingBedNum");
        eiColumn.setDescName("剩余床位数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("房间类型(1男生宿舍/0女生宿舍)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormPosition");
        eiColumn.setDescName("宿舍位置：院内、院外");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormArea");
        eiColumn.setDescName("房屋面积：\"<50㎡\"、\"50-100㎡\"、\">100㎡\"");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elevatorRoom");
        eiColumn.setDescName("是否为电梯房");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("priBathroom");
        eiColumn.setDescName("独立卫生间：有、没有");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rent");
        eiColumn.setDescName("房租");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageFee");
        eiColumn.setDescName("管理费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("备注信息");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreaterNo");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreaterName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisorNo");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisorName");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsRoom() {
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
     * get the building - 宿舍楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 宿舍楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the floor - 宿舍层
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 宿舍层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the roomNo - 宿舍号
     * @return the roomNo
     */
    public String getRoomNo() {
        return this.roomNo;
    }

    /**
     * set the roomNo - 宿舍号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * get the roomName - 宿舍总称(楼+层+宿舍号)
     * @return the roomName
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * set the roomName - 宿舍总称(楼+层+宿舍号)
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * get the bedNum - 床位数
     * @return the bedNum
     */
    public Integer getBedNum() {
        return this.bedNum;
    }

    /**
     * set the bedNum - 床位数
     */
    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    /**
     * get the remainingBedNum - 剩余床位数
     * @return the remainingBedNum
     */
    public Integer getRemainingBedNum() {
        return this.remainingBedNum;
    }

    /**
     * set the remainingBedNum - 剩余床位数
     */
    public void setRemainingBedNum(Integer remainingBedNum) {
        this.remainingBedNum = remainingBedNum;
    }

    /**
     * get the typeCode - 房间类型(1男生宿舍/0女生宿舍)
     * @return the typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * set the typeCode - 房间类型(1男生宿舍/0女生宿舍)
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * get the dormPosition - 宿舍位置：院内、院外
     * @return the dormPosition
     */
    public String getDormPosition() {
        return this.dormPosition;
    }

    /**
     * set the dormPosition - 宿舍位置：院内、院外
     */
    public void setDormPosition(String dormPosition) {
        this.dormPosition = dormPosition;
    }

    /**
     * get the dormArea - 房屋面积：\"<50㎡\"、\"50-100㎡\"、\">100㎡\"
     * @return the dormArea
     */
    public String getDormArea() {
        return this.dormArea;
    }

    /**
     * set the dormArea - 房屋面积：\"<50㎡\"、\"50-100㎡\"、\">100㎡\"
     */
    public void setDormArea(String dormArea) {
        this.dormArea = dormArea;
    }

    /**
     * get the elevatorRoom - 是否为电梯房
     * @return the elevatorRoom
     */
    public String getElevatorRoom() {
        return this.elevatorRoom;
    }

    /**
     * set the elevatorRoom - 是否为电梯房
     */
    public void setElevatorRoom(String elevatorRoom) {
        this.elevatorRoom = elevatorRoom;
    }

    /**
     * get the priBathroom - 独立卫生间：有、没有
     * @return the priBathroom
     */
    public String getPriBathroom() {
        return this.priBathroom;
    }

    /**
     * set the priBathroom - 独立卫生间：有、没有
     */
    public void setPriBathroom(String priBathroom) {
        this.priBathroom = priBathroom;
    }

    /**
     * get the rent - 房租
     * @return the rent
     */
    public String getRent() {
        return this.rent;
    }

    /**
     * set the rent - 房租
     */
    public void setRent(String rent) {
        this.rent = rent;
    }

    /**
     * get the manageFee - 管理费
     * @return the manageFee
     */
    public String getManageFee() {
        return this.manageFee;
    }

    /**
     * set the manageFee - 管理费
     */
    public void setManageFee(String manageFee) {
        this.manageFee = manageFee;
    }

    /**
     * get the note - 备注信息
     * @return the note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set the note - 备注信息
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get the recCreaterNo - 创建人工号
     * @return the recCreaterNo
     */
    public String getRecCreaterNo() {
        return this.recCreaterNo;
    }

    /**
     * set the recCreaterNo - 创建人工号
     */
    public void setRecCreaterNo(String recCreaterNo) {
        this.recCreaterNo = recCreaterNo;
    }

    /**
     * get the recCreaterName - 创建人
     * @return the recCreaterName
     */
    public String getRecCreaterName() {
        return this.recCreaterName;
    }

    /**
     * set the recCreaterName - 创建人
     */
    public void setRecCreaterName(String recCreaterName) {
        this.recCreaterName = recCreaterName;
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
     * get the recRevisorNo - 修改人工号
     * @return the recRevisorNo
     */
    public String getRecRevisorNo() {
        return this.recRevisorNo;
    }

    /**
     * set the recRevisorNo - 修改人工号
     */
    public void setRecRevisorNo(String recRevisorNo) {
        this.recRevisorNo = recRevisorNo;
    }

    /**
     * get the recRevisorName - 修改人
     * @return the recRevisorName
     */
    public String getRecRevisorName() {
        return this.recRevisorName;
    }

    /**
     * set the recRevisorName - 修改人
     */
    public void setRecRevisorName(String recRevisorName) {
        this.recRevisorName = recRevisorName;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setRoomNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomNo")), roomNo));
        setRoomName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomName")), roomName));
        setBedNum(NumberUtils.toInteger(StringUtils.toString(map.get("bedNum")), bedNum));
        setRemainingBedNum(NumberUtils.toInteger(StringUtils.toString(map.get("remainingBedNum")), remainingBedNum));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setDormPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormPosition")), dormPosition));
        setDormArea(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormArea")), dormArea));
        setElevatorRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("elevatorRoom")), elevatorRoom));
        setPriBathroom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("priBathroom")), priBathroom));
        setRent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rent")), rent));
        setManageFee(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manageFee")), manageFee));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setRecCreaterNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreaterNo")), recCreaterNo));
        setRecCreaterName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreaterName")), recCreaterName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorNo")), recRevisorNo));
        setRecRevisorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorName")), recRevisorName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("roomNo",StringUtils.toString(roomNo, eiMetadata.getMeta("roomNo")));
        map.put("roomName",StringUtils.toString(roomName, eiMetadata.getMeta("roomName")));
        map.put("bedNum",StringUtils.toString(bedNum, eiMetadata.getMeta("bedNum")));
        map.put("remainingBedNum",StringUtils.toString(remainingBedNum, eiMetadata.getMeta("remainingBedNum")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("dormPosition",StringUtils.toString(dormPosition, eiMetadata.getMeta("dormPosition")));
        map.put("dormArea",StringUtils.toString(dormArea, eiMetadata.getMeta("dormArea")));
        map.put("elevatorRoom",StringUtils.toString(elevatorRoom, eiMetadata.getMeta("elevatorRoom")));
        map.put("priBathroom",StringUtils.toString(priBathroom, eiMetadata.getMeta("priBathroom")));
        map.put("rent",StringUtils.toString(rent, eiMetadata.getMeta("rent")));
        map.put("manageFee",StringUtils.toString(manageFee, eiMetadata.getMeta("manageFee")));
        map.put("note",StringUtils.toString(note, eiMetadata.getMeta("note")));
        map.put("recCreaterNo",StringUtils.toString(recCreaterNo, eiMetadata.getMeta("recCreaterNo")));
        map.put("recCreaterName",StringUtils.toString(recCreaterName, eiMetadata.getMeta("recCreaterName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisorNo",StringUtils.toString(recRevisorNo, eiMetadata.getMeta("recRevisorNo")));
        map.put("recRevisorName",StringUtils.toString(recRevisorName, eiMetadata.getMeta("recRevisorName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}