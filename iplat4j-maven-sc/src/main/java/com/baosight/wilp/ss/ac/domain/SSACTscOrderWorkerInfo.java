/**
* Generate time : 2021-07-08 16:52:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSACTscOrderWorkerInfo 职工订单信息
* 
*/
public class SSACTscOrderWorkerInfo extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String openId = " ";		/* 微信ID(对于微信用户,保存openID;对于PC端用户,保存用户工号)*/
    private String userName = " ";		/* 用户姓名*/
    private String building = " ";		/* 所属大楼*/
    private String buildingName = " ";		
    private String floor = " ";		/* 病区编码*/
    private String floorName = " ";		
    private String userTelNumber = " ";		/* 用户电话*/
    private String deptNum = " ";		/* 病区编码*/
    private String deptName = " ";		/* 病区名称*/
    private String bedNo = " ";		/* 床位号*/
    private String userNo = " ";		/* 住院号/工号*/
    private String address = " ";		/* 订餐地址*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String recReviseTime ;		/* 更新时间*/
    private String room = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("微信ID(对于微信用户,保存openID;对于PC端用户,保存用户工号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("所属大楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userNo");
        eiColumn.setDescName("住院号/工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("订餐地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("room");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACTscOrderWorkerInfo() {
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
     * get the openId - 微信ID(对于微信用户,保存openID;对于PC端用户,保存用户工号)
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - 微信ID(对于微信用户,保存openID;对于PC端用户,保存用户工号)
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * get the userName - 用户姓名
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName - 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the building - 所属大楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 所属大楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the buildingName 
     * @return the buildingName
     */
    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * set the buildingName 
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * get the floor - 病区编码
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 病区编码
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the floorName 
     * @return the floorName
     */
    public String getFloorName() {
        return this.floorName;
    }

    /**
     * set the floorName 
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    /**
     * get the userTelNumber - 用户电话
     * @return the userTelNumber
     */
    public String getUserTelNumber() {
        return this.userTelNumber;
    }

    /**
     * set the userTelNumber - 用户电话
     */
    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

    /**
     * get the deptNum - 病区编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 病区编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 病区名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 病区名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
     * get the userNo - 住院号/工号
     * @return the userNo
     */
    public String getUserNo() {
        return this.userNo;
    }

    /**
     * set the userNo - 住院号/工号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * get the address - 订餐地址
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 订餐地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
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
     * get the room 
     * @return the room
     */
    public String getRoom() {
        return this.room;
    }

    /**
     * set the room 
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setFloorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorName")), floorName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userNo")), userNo));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("floorName",StringUtils.toString(floorName, eiMetadata.getMeta("floorName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("userNo",StringUtils.toString(userNo, eiMetadata.getMeta("userNo")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("room",StringUtils.toString(room, eiMetadata.getMeta("room")));
        return map;
    }
}