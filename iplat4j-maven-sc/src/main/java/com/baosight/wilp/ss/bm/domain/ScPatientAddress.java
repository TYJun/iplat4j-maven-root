/**
* Generate time : 2022-06-17 1:02:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* ScAddress
* 
*/
public class ScPatientAddress extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String deptName = " ";		/* 子集科室名称*/
    private String takeEffect = " ";		/* 是否生效*/
    private String deptNum = " ";		/* 子集科室编码(bizid)*/
    private String deptNumParent = " ";		/* 父科室编码 */
    private String deptNameParent = " ";		/* 父科室名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("子集科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("takeEffect");
        eiColumn.setDescName("是否生效");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("子集科室编码(bizid)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNumParent");
        eiColumn.setDescName("父科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNameParent");
        eiColumn.setDescName("父科室名称");
        eiMetadata.addMeta(eiColumn);



    }

    /**
     * the constructor
     */
    public ScPatientAddress() {
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
     * get the deptName - 地址
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 地址
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the takeEffect - 是否生效
     * @return the takeEffect
     */
    public String getTakeEffect() {
        return this.takeEffect;
    }

    /**
     * set the takeEffect - 是否生效
     */
    public void setTakeEffect(String takeEffect) {
        this.takeEffect = takeEffect;
    }

    /**
     * get the deptNum - 子集科室编码(bizid)
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 子集科室编码(bizid)
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptNumParent - 父科室编码
     * @return the deptNumParent
     */
    public String getDeptNumParent() {
        return this.deptNumParent;
    }

    /**
     * set the deptNumParent - 父科室编码
     */
    public void setDeptNumParent(String deptNumParent) {
        this.deptNumParent = deptNumParent;
    }

    /**
     * get the deptNameParent - 父科室名称
     * @return the deptNameParent
     */
    public String getDeptNameParent() {
        return this.deptNameParent;
    }

    /**
     * set the deptNameParent - 父科室名称
     */
    public void setDeptNameParent(String deptNameParent) {
        this.deptNameParent = deptNameParent;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setTakeEffect(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("takeEffect")), takeEffect));

        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptNumParent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNumParent")), deptNumParent));
        setDeptNameParent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNameParent")), deptNameParent));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("takeEffect",StringUtils.toString(takeEffect, eiMetadata.getMeta("takeEffect")));

        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptNumParent",StringUtils.toString(deptNumParent, eiMetadata.getMeta("deptNumParent")));
        map.put("deptNameParent",StringUtils.toString(takeEffect, eiMetadata.getMeta("deptNameParent")));
        return map;
    }
}