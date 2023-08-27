/**
* Generate time : 2021-02-23 10:54:07
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd06
* 
*/
public class Tbmbd06 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* id*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String spotNum = " ";		/* 地点编码*/
    private String spotName = " ";		/* 地点名称*/
    private String deptNum = " ";		/* 科室*/
    private String wgroupNum = " ";		/* 维保班组*/
    private String building = " ";		/* 楼号*/
    private String floor = " ";		/* 层数*/
    private String room = " ";		/* 房间*/
    private String remark = " ";		/* 备注*/
    private String jpSpotName = " ";		/* 地点名简拼*/
    private String position = " ";		/* 方位*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String stopFlag = " ";		
    private String nfccardno = " ";		
    private String nfcflag = " ";		/* 0/未绑定，1/已绑定*/
    private String hexcode = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("id");
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

        eiColumn = new EiColumn("spotNum");
        eiColumn.setDescName("地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("地点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wgroupNum");
        eiColumn.setDescName("维保班组");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("room");
        eiColumn.setDescName("房间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpSpotName");
        eiColumn.setDescName("地点名简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("position");
        eiColumn.setDescName("方位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nfccardno");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nfcflag");
        eiColumn.setDescName("0/未绑定，1/已绑定");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hexcode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd06() {
        initMetaData();
    }

    /**
     * get the id - id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - id
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
     * get the wgroupNum - 维保班组
     * @return the wgroupNum
     */
    public String getWgroupNum() {
        return this.wgroupNum;
    }

    /**
     * set the wgroupNum - 维保班组
     */
    public void setWgroupNum(String wgroupNum) {
        this.wgroupNum = wgroupNum;
    }

    /**
     * get the building - 楼号
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 楼号
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the floor - 层数
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 层数
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
     * get the jpSpotName - 地点名简拼
     * @return the jpSpotName
     */
    public String getJpSpotName() {
        return this.jpSpotName;
    }

    /**
     * set the jpSpotName - 地点名简拼
     */
    public void setJpSpotName(String jpSpotName) {
        this.jpSpotName = jpSpotName;
    }

    /**
     * get the position - 方位
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position - 方位
     */
    public void setPosition(String position) {
        this.position = position;
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
     * get the nfccardno 
     * @return the nfccardno
     */
    public String getNfccardno() {
        return this.nfccardno;
    }

    /**
     * set the nfccardno 
     */
    public void setNfccardno(String nfccardno) {
        this.nfccardno = nfccardno;
    }

    /**
     * get the nfcflag - 0/未绑定，1/已绑定
     * @return the nfcflag
     */
    public String getNfcflag() {
        return this.nfcflag;
    }

    /**
     * set the nfcflag - 0/未绑定，1/已绑定
     */
    public void setNfcflag(String nfcflag) {
        this.nfcflag = nfcflag;
    }

    /**
     * get the hexcode 
     * @return the hexcode
     */
    public String getHexcode() {
        return this.hexcode;
    }

    /**
     * set the hexcode 
     */
    public void setHexcode(String hexcode) {
        this.hexcode = hexcode;
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
        setSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotNum")), spotNum));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setWgroupNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wgroupNum")), wgroupNum));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setJpSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpSpotName")), jpSpotName));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setNfccardno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nfccardno")), nfccardno));
        setNfcflag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nfcflag")), nfcflag));
        setHexcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hexcode")), hexcode));
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
        map.put("spotNum",StringUtils.toString(spotNum, eiMetadata.getMeta("spotNum")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("wgroupNum",StringUtils.toString(wgroupNum, eiMetadata.getMeta("wgroupNum")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("room",StringUtils.toString(room, eiMetadata.getMeta("room")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("jpSpotName",StringUtils.toString(jpSpotName, eiMetadata.getMeta("jpSpotName")));
        map.put("position",StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("nfccardno",StringUtils.toString(nfccardno, eiMetadata.getMeta("nfccardno")));
        map.put("nfcflag",StringUtils.toString(nfcflag, eiMetadata.getMeta("nfcflag")));
        map.put("hexcode",StringUtils.toString(hexcode, eiMetadata.getMeta("hexcode")));
        return map;
    }
}