/**
* Generate time : 2021-02-09 9:12:44
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageAddressClass
* 
*/
public class CarriageAddressClass extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 地址分类主键（UUID）*/
    private String classId = " ";		/* 地址分类*/
    private String className = " ";		/* 地址分类名称*/
    private String deptNum = " ";		/* 关联科室编号*/
    private String deptName = " ";		/* 关联科室名称*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String addressNo = " ";		/* 地址编号*/
    private String address = " ";		/* 地址名称*/
    private String times = " ";		/* 模板时间（min）*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("地址分类主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classId");
        eiColumn.setDescName("地址分类");
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

        eiColumn = new EiColumn("addressNo");
        eiColumn.setDescName("地址编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("地址名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("times");
        eiColumn.setDescName("模板时间（min）");
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
     * get the classId - 地址分类
     * @return the classId
     */
    public String getClassId() {
        return this.classId;
    }

    /**
     * set the classId - 地址分类
     */
    public void setClassId(String classId) {
        this.classId = classId;
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
     * get the addressNo - 地址编号
     * @return the addressNo
     */
    public String getAddressNo() {
        return this.addressNo;
    }

    /**
     * set the addressNo - 地址编号
     */
    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    /**
     * get the address - 地址名称
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 地址名称
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the times - 模板时间（min）
     * @return the times
     */
    public String getTimes() {
        return this.times;
    }

    /**
     * set the times - 模板时间（min）
     */
    public void setTimes(String times) {
        this.times = times;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setClassId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classId")), classId));
        setClassName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("className")), className));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setAddressNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("addressNo")), addressNo));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setTimes(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("times")), times));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("classId",StringUtils.toString(classId, eiMetadata.getMeta("classId")));
        map.put("className",StringUtils.toString(className, eiMetadata.getMeta("className")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("addressNo",StringUtils.toString(addressNo, eiMetadata.getMeta("addressNo")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("times",StringUtils.toString(times, eiMetadata.getMeta("times")));
        return map;
    }
}