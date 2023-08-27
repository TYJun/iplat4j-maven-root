/**
* Generate time : 2022-02-17 10:39:57
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SSBMSCDD01
* 
*/
public class SSBMSCDD01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String className = " ";		/* 地址分类名称*/
    private String deptNum = " ";		/* 科室编码*/
    private String deptName = " ";		/* 科室名称*/
    private String spotNum = " ";		/* 地点编码*/
    private String spotName = " ";		/* 地点名称*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String room = " ";		/* 房间*/
    private String canteenNum = " ";		/* 食堂编码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("className");
        eiColumn.setDescName("地址分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotNum");
        eiColumn.setDescName("地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("地点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("room");
        eiColumn.setDescName("房间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMSCDD01() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the className - 地址分类名称
     * @return the className
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * set the className - 地址分类名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
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
     * get the spotNum - 地点编码
     * @return the spotNum
     */
    public String getSpotNum() {
        return this.spotNum;
    }

    /**
     * set the spotNum - 地点编码
     */
    public void setSpotNum(String spotNum) {
        this.spotNum = spotNum;
    }

    /**
     * get the spotName - 地点名称
     * @return the spotName
     */
    public String getSpotName() {
        return this.spotName;
    }

    /**
     * set the spotName - 地点名称
     */
    public void setSpotName(String spotName) {
        this.spotName = spotName;
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
     * get the room - 房间
     * @return the room
     */
    public String getRoom() {
        return this.room;
    }

    /**
     * set the room - 房间
     */
    public void setRoom(String room) {
        this.room = room;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setClassName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("className")), className));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotNum")), spotNum));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("className",StringUtils.toString(className, eiMetadata.getMeta("className")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("spotNum",StringUtils.toString(spotNum, eiMetadata.getMeta("spotNum")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("room",StringUtils.toString(room, eiMetadata.getMeta("room")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        return map;
    }
}