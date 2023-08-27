/**
* Generate time : 2021-03-05 16:41:03
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd05
* 
*/
public class Tbmbd05 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String telNum = " ";		/* 电话号码*/
    private String deptNum = " ";		/* 科室*/
    private String remark = " ";		/* 备注*/
    private String spotNum = " ";		/* 房间*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String stopFlag = " ";		
    private String orgCode = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("telNum");
        eiColumn.setDescName("电话号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotNum");
        eiColumn.setDescName("房间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orgCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd05() {
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
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
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
     * get the telNum - 电话号码
     * @return the telNum
     */
    public String getTelNum() {
        return this.telNum;
    }

    /**
     * set the telNum - 电话号码
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * get the deptNum - 科室
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the spotNum - 房间
     * @return the spotNum
     */
    public String getSpotNum() {
        return this.spotNum;
    }

    /**
     * set the spotNum - 房间
     */
    public void setSpotNum(String spotNum) {
        this.spotNum = spotNum;
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
     * get the stopFlag 
     * @return the stopFlag
     */
    public String getStopFlag() {
        return this.stopFlag;
    }

    /**
     * set the stopFlag 
     */
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * get the orgCode 
     * @return the orgCode
     */
    public String getOrgCode() {
        return this.orgCode;
    }

    /**
     * set the orgCode 
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setTelNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("telNum")), telNum));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotNum")), spotNum));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setOrgCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orgCode")), orgCode));
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
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("telNum",StringUtils.toString(telNum, eiMetadata.getMeta("telNum")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("spotNum",StringUtils.toString(spotNum, eiMetadata.getMeta("spotNum")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("orgCode",StringUtils.toString(orgCode, eiMetadata.getMeta("orgCode")));
        return map;
    }
}