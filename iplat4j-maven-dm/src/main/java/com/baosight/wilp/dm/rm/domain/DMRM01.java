/**
* Generate time : 2022-02-22 19:54:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.rm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DMRM01
* 
*/
public class DMRM01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String roomNo = " ";		/* 房间编号*/
    private Integer bedNum = new Integer(0);		/* 床位数*/
    private String typeCode = " ";		/* 房间类型*/
    private String buildingCode = " ";		/* 宿舍楼*/
    private String floorCode = " ";		/* 层*/
    private String dormitoryNo = " ";		/* 楼-层-房间编号*/
    private String note = " ";		/* 备注*/
    private String direction = " ";		/* 朝向*/
    private String dormsPosition = " ";		/* 宿舍位置:院内、院外*/
    private String dormsIfwc = " ";		/* 独立卫生间:有、没有*/
    private String dormsAreas = " ";		/* 房屋面积:<50㎡”、“50-100㎡”、“>100㎡*/
    private String roomName = " ";		/* 宿舍编号，key,sushelou401*/
    private BigDecimal rent = new BigDecimal("0");		/* 房租*/
    private BigDecimal manageFee = new BigDecimal("0");		/* 管理费*/
    private Double lastElec = new Double(0.000);		/* 上次用电量*/
    private Double lastWater = new Double(0.000);		/* 上次用水量*/
    private BigDecimal hospitalManageFee = new BigDecimal("0");		/* 医院管理费*/
    private BigDecimal propertyManageFee = new BigDecimal("0");		/* 物业管理费*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomNo");
        eiColumn.setDescName("房间编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNum");
        eiColumn.setDescName("床位数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("房间类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingCode");
        eiColumn.setDescName("宿舍楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorCode");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormitoryNo");
        eiColumn.setDescName("楼-层-房间编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("direction");
        eiColumn.setDescName("朝向");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormsPosition");
        eiColumn.setDescName("宿舍位置:院内、院外");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormsIfwc");
        eiColumn.setDescName("独立卫生间:有、没有");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormsAreas");
        eiColumn.setDescName("房屋面积:<50㎡”、“50-100㎡”、“>100㎡");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomName");
        eiColumn.setDescName("宿舍编号，key,sushelou401");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rent");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("房租");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("管理费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastElec");
        eiColumn.setDescName("上次用电量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastWater");
        eiColumn.setDescName("上次用水量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalManageFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("医院管理费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("propertyManageFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("物业管理费");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DMRM01() {
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
     * get the roomNo - 房间编号
     * @return the roomNo
     */
    public String getRoomNo() {
        return this.roomNo;
    }

    /**
     * set the roomNo - 房间编号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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
     * get the typeCode - 房间类型
     * @return the typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * set the typeCode - 房间类型
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * get the buildingCode - 宿舍楼
     * @return the buildingCode
     */
    public String getBuildingCode() {
        return this.buildingCode;
    }

    /**
     * set the buildingCode - 宿舍楼
     */
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    /**
     * get the floorCode - 层
     * @return the floorCode
     */
    public String getFloorCode() {
        return this.floorCode;
    }

    /**
     * set the floorCode - 层
     */
    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    /**
     * get the dormitoryNo - 楼-层-房间编号
     * @return the dormitoryNo
     */
    public String getDormitoryNo() {
        return this.dormitoryNo;
    }

    /**
     * set the dormitoryNo - 楼-层-房间编号
     */
    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    /**
     * get the note - 备注
     * @return the note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set the note - 备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get the direction - 朝向
     * @return the direction
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * set the direction - 朝向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * get the dormsPosition - 宿舍位置:院内、院外
     * @return the dormsPosition
     */
    public String getDormsPosition() {
        return this.dormsPosition;
    }

    /**
     * set the dormsPosition - 宿舍位置:院内、院外
     */
    public void setDormsPosition(String dormsPosition) {
        this.dormsPosition = dormsPosition;
    }

    /**
     * get the dormsIfwc - 独立卫生间:有、没有
     * @return the dormsIfwc
     */
    public String getDormsIfwc() {
        return this.dormsIfwc;
    }

    /**
     * set the dormsIfwc - 独立卫生间:有、没有
     */
    public void setDormsIfwc(String dormsIfwc) {
        this.dormsIfwc = dormsIfwc;
    }

    /**
     * get the dormsAreas - 房屋面积:<50㎡”、“50-100㎡”、“>100㎡
     * @return the dormsAreas
     */
    public String getDormsAreas() {
        return this.dormsAreas;
    }

    /**
     * set the dormsAreas - 房屋面积:<50㎡”、“50-100㎡”、“>100㎡
     */
    public void setDormsAreas(String dormsAreas) {
        this.dormsAreas = dormsAreas;
    }

    /**
     * get the roomName - 宿舍编号，key,sushelou401
     * @return the roomName
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * set the roomName - 宿舍编号，key,sushelou401
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * get the rent - 房租
     * @return the rent
     */
    public BigDecimal getRent() {
        return this.rent;
    }

    /**
     * set the rent - 房租
     */
    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    /**
     * get the manageFee - 管理费
     * @return the manageFee
     */
    public BigDecimal getManageFee() {
        return this.manageFee;
    }

    /**
     * set the manageFee - 管理费
     */
    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    /**
     * get the lastElec - 上次用电量
     * @return the lastElec
     */
    public Double getLastElec() {
        return this.lastElec;
    }

    /**
     * set the lastElec - 上次用电量
     */
    public void setLastElec(Double lastElec) {
        this.lastElec = lastElec;
    }

    /**
     * get the lastWater - 上次用水量
     * @return the lastWater
     */
    public Double getLastWater() {
        return this.lastWater;
    }

    /**
     * set the lastWater - 上次用水量
     */
    public void setLastWater(Double lastWater) {
        this.lastWater = lastWater;
    }

    /**
     * get the hospitalManageFee - 医院管理费
     * @return the hospitalManageFee
     */
    public BigDecimal getHospitalManageFee() {
        return this.hospitalManageFee;
    }

    /**
     * set the hospitalManageFee - 医院管理费
     */
    public void setHospitalManageFee(BigDecimal hospitalManageFee) {
        this.hospitalManageFee = hospitalManageFee;
    }

    /**
     * get the propertyManageFee - 物业管理费
     * @return the propertyManageFee
     */
    public BigDecimal getPropertyManageFee() {
        return this.propertyManageFee;
    }

    /**
     * set the propertyManageFee - 物业管理费
     */
    public void setPropertyManageFee(BigDecimal propertyManageFee) {
        this.propertyManageFee = propertyManageFee;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRoomNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomNo")), roomNo));
        setBedNum(NumberUtils.toInteger(StringUtils.toString(map.get("bedNum")), bedNum));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setBuildingCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingCode")), buildingCode));
        setFloorCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorCode")), floorCode));
        setDormitoryNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormitoryNo")), dormitoryNo));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setDirection(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("direction")), direction));
        setDormsPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormsPosition")), dormsPosition));
        setDormsIfwc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormsIfwc")), dormsIfwc));
        setDormsAreas(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormsAreas")), dormsAreas));
        setRoomName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomName")), roomName));
        setRent(NumberUtils.toBigDecimal(StringUtils.toString(map.get("rent")), rent));
        setManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("manageFee")), manageFee));
        setLastElec(NumberUtils.toDouble(StringUtils.toString(map.get("lastElec")), lastElec));
        setLastWater(NumberUtils.toDouble(StringUtils.toString(map.get("lastWater")), lastWater));
        setHospitalManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("hospitalManageFee")), hospitalManageFee));
        setPropertyManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("propertyManageFee")), propertyManageFee));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("roomNo",StringUtils.toString(roomNo, eiMetadata.getMeta("roomNo")));
        map.put("bedNum",StringUtils.toString(bedNum, eiMetadata.getMeta("bedNum")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("buildingCode",StringUtils.toString(buildingCode, eiMetadata.getMeta("buildingCode")));
        map.put("floorCode",StringUtils.toString(floorCode, eiMetadata.getMeta("floorCode")));
        map.put("dormitoryNo",StringUtils.toString(dormitoryNo, eiMetadata.getMeta("dormitoryNo")));
        map.put("note",StringUtils.toString(note, eiMetadata.getMeta("note")));
        map.put("direction",StringUtils.toString(direction, eiMetadata.getMeta("direction")));
        map.put("dormsPosition",StringUtils.toString(dormsPosition, eiMetadata.getMeta("dormsPosition")));
        map.put("dormsIfwc",StringUtils.toString(dormsIfwc, eiMetadata.getMeta("dormsIfwc")));
        map.put("dormsAreas",StringUtils.toString(dormsAreas, eiMetadata.getMeta("dormsAreas")));
        map.put("roomName",StringUtils.toString(roomName, eiMetadata.getMeta("roomName")));
        map.put("rent",StringUtils.toString(rent, eiMetadata.getMeta("rent")));
        map.put("manageFee",StringUtils.toString(manageFee, eiMetadata.getMeta("manageFee")));
        map.put("lastElec",StringUtils.toString(lastElec, eiMetadata.getMeta("lastElec")));
        map.put("lastWater",StringUtils.toString(lastWater, eiMetadata.getMeta("lastWater")));
        map.put("hospitalManageFee",StringUtils.toString(hospitalManageFee, eiMetadata.getMeta("hospitalManageFee")));
        map.put("propertyManageFee",StringUtils.toString(propertyManageFee, eiMetadata.getMeta("propertyManageFee")));
        return map;
    }
}