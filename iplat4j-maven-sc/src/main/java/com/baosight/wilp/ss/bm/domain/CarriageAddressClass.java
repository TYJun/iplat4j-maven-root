/**
* Generate time : 2021-02-09 9:12:44
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CarriageAddressClass
* sc_location_class
*/
public class CarriageAddressClass extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 地址分类主键（UUID）*/
    private String className = " ";		/* 地址分类名称*/
    private String deptNum = " ";		/* 关联科室编号*/
    private String deptName = " ";		/* 关联科室名称*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String spotNum = " ";		/* 地址编号*/
    private String spotName = " ";		/* 地址名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("地址分类主键（UUID）");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("className");
        eiColumn.setDescName("地址分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("关联科室编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("关联科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotNum");
        eiColumn.setDescName("地址编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("地址名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageAddressClass() {
        initMetaData();
    }

    /**
     * get the id - 地址分类主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 地址分类主键（UUID）
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
     * get the deptNum - 关联科室编号
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 关联科室编号
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 关联科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 关联科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
     * get the spotNum - 地址编号
     * @return the spotNum
     */
    public String getSpotNum() {
        return this.spotNum;
    }

    /**
     * set the spotNum - 地址编号
     */
    public void setSpotNum(String spotNum) {
        this.spotNum = spotNum;
    }

    /**
     * get the spotName - 地址名称
     * @return the spotName
     */
    public String getSpotName() {
        return this.spotName;
    }

    /**
     * set the spotName - 地址名称
     */
    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

   
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setClassName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("className")), className));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotNum")), spotNum));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
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
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("spotNum",StringUtils.toString(spotNum, eiMetadata.getMeta("spotNum")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        return map;
    }
}