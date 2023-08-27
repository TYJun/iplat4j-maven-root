/**
* Generate time : 2021-06-07 14:52:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSBMTqrcodeAddressConf
* sc_qrcode_address
*/
public class SSBMCtm01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevisor = " ";		
    private String recReviseTime = " ";		
    private String areaCode = " ";		/* 院区编码*/
    private String areaName = " ";		/* 院区名称*/
    private String buildingName = " ";		
    private String building = " ";		/* 所属楼*/
    private String floorName = " ";		
    private String floor = " ";		/* 层*/
    private String wardCode = " ";		/* 病区编码*/
    private String wardName = " ";		/* 病区名称*/
    private String deptName = " ";		/* 科室名称*/
    private String deptCode = " ";		/* 科室编码*/
    private String bedNo = " ";		
    private String addNum = " ";		/* 地点编码*/
    private String multiCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("areaCode");
        eiColumn.setDescName("院区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("areaName");
        eiColumn.setDescName("院区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("所属楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardCode");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptCode");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setFieldLength(36);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("addNum");
        eiColumn.setDescName("地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("multiCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCtm01() {
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
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor 
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor 
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the areaCode - 院区编码
     * @return the areaCode
     */
    public String getAreaCode() {
        return this.areaCode;
    }

    /**
     * set the areaCode - 院区编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * get the areaName - 院区名称
     * @return the areaName
     */
    public String getAreaName() {
        return this.areaName;
    }

    /**
     * set the areaName - 院区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
     * get the building - 所属楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 所属楼
     */
    public void setBuilding(String building) {
        this.building = building;
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
     * get the wardCode - 病区编码
     * @return the wardCode
     */
    public String getWardCode() {
        return this.wardCode;
    }

    /**
     * set the wardCode - 病区编码
     */
    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    /**
     * get the wardName - 病区名称
     * @return the wardName
     */
    public String getWardName() {
        return this.wardName;
    }

    /**
     * set the wardName - 病区名称
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptCode - 科室编码
     * @return the deptCode
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * set the deptCode - 科室编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * get the bedNo 
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo 
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the addNum - 地点编码
     * @return the addNum
     */
    public String getAddNum() {
        return this.addNum;
    }

    /**
     * set the addNum - 地点编码
     */
    public void setAddNum(String addNum) {
        this.addNum = addNum;
    }

    /**
     * get the multiCode - 账套
     * @return the multiCode
     */
    public String getMultiCode() {
        return this.multiCode;
    }

    /**
     * set the multiCode - 账套
     */
    public void setMultiCode(String multiCode) {
        this.multiCode = multiCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setAreaCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("areaCode")), areaCode));
        setAreaName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("areaName")), areaName));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorName")), floorName));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setWardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardCode")), wardCode));
        setWardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardName")), wardName));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setDeptCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptCode")), deptCode));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setAddNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("addNum")), addNum));
        setMultiCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("multiCode")), multiCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("areaCode",StringUtils.toString(areaCode, eiMetadata.getMeta("areaCode")));
        map.put("areaName",StringUtils.toString(areaName, eiMetadata.getMeta("areaName")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floorName",StringUtils.toString(floorName, eiMetadata.getMeta("floorName")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("wardCode",StringUtils.toString(wardCode, eiMetadata.getMeta("wardCode")));
        map.put("wardName",StringUtils.toString(wardName, eiMetadata.getMeta("wardName")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptCode",StringUtils.toString(deptCode, eiMetadata.getMeta("deptCode")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("addNum",StringUtils.toString(addNum, eiMetadata.getMeta("addNum")));
        map.put("multiCode",StringUtils.toString(multiCode, eiMetadata.getMeta("multiCode")));
        return map;
    }
}