/**
* Generate time : 2022-02-23 10:22:18
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.fm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DMFM01
* 
*/
public class DMFM01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String manId = " ";		/* 人员id*/
    private String employeeId = " ";		/* 工号*/
    private String manName = " ";		/* 姓名*/
    private String roomId = " ";		/* 房间id*/
    private String roomName = " ";		/* 房间地点*/
    private String roomNo = " ";		/* 房间编号*/
    private String bedNum = " ";		/* 床位数*/
    private String buildingCode = " ";		/* 宿舍楼*/
    private String floorCode = " ";		/* 层*/
    private String dormitoryNo = " ";		/* 楼-层-房间编号*/
    private BigDecimal monthRent = new BigDecimal("0");		/* 月租*/
    private BigDecimal lastElec = new BigDecimal("0");		/* 上月用电量*/
    private BigDecimal lastWater = new BigDecimal("0");		/* 上月用水量*/
    private BigDecimal lastWaterfee = new BigDecimal("0");		/* 上月水费*/
    private BigDecimal lastElecfee = new BigDecimal("0");		/* 上月电费*/
    private BigDecimal waterFee = new BigDecimal("0");		/* 本月水费*/
    private BigDecimal elecFee = new BigDecimal("0");		/* 本月电费*/
    private BigDecimal monthElec = new BigDecimal("0");		/* 本月用电量*/
    private BigDecimal monthWater = new BigDecimal("0");		/* 本月用水量*/
    private Timestamp createrTime ;		/* 创建时间*/
    private String creator = " ";		/* 创建人*/
    private Timestamp buiidtime ;		/* 生成时间*/
    private String remark = " ";		/* 备注*/
    private BigDecimal hospitalManageFee = new BigDecimal("0");		/* 医院管理费*/
    private BigDecimal propertyManageFee = new BigDecimal("0");		/* 物业管理费*/
    private BigDecimal networkFee = new BigDecimal("0");		/* 网费*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("employeeId");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manName");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomId");
        eiColumn.setDescName("房间id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomName");
        eiColumn.setDescName("房间地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomNo");
        eiColumn.setDescName("房间编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNum");
        eiColumn.setDescName("床位数");
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

        eiColumn = new EiColumn("monthRent");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("月租");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastElec");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("上月用电量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastWater");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("上月用水量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastWaterfee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("上月水费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastElecfee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("上月电费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("waterFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("本月水费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elecFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("本月电费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monthElec");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("本月用电量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monthWater");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("本月用水量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createrTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buiidtime");
        eiColumn.setDescName("生成时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
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

        eiColumn = new EiColumn("networkFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("网费");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DMFM01() {
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
     * get the manId - 人员id
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员id
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the employeeId - 工号
     * @return the employeeId
     */
    public String getEmployeeId() {
        return this.employeeId;
    }

    /**
     * set the employeeId - 工号
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * get the manName - 姓名
     * @return the manName
     */
    public String getManName() {
        return this.manName;
    }

    /**
     * set the manName - 姓名
     */
    public void setManName(String manName) {
        this.manName = manName;
    }

    /**
     * get the roomId - 房间id
     * @return the roomId
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the roomId - 房间id
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the roomName - 房间地点
     * @return the roomName
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * set the roomName - 房间地点
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
    public String getBedNum() {
        return this.bedNum;
    }

    /**
     * set the bedNum - 床位数
     */
    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
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
     * get the monthRent - 月租
     * @return the monthRent
     */
    public BigDecimal getMonthRent() {
        return this.monthRent;
    }

    /**
     * set the monthRent - 月租
     */
    public void setMonthRent(BigDecimal monthRent) {
        this.monthRent = monthRent;
    }

    /**
     * get the lastElec - 上月用电量
     * @return the lastElec
     */
    public BigDecimal getLastElec() {
        return this.lastElec;
    }

    /**
     * set the lastElec - 上月用电量
     */
    public void setLastElec(BigDecimal lastElec) {
        this.lastElec = lastElec;
    }

    /**
     * get the lastWater - 上月用水量
     * @return the lastWater
     */
    public BigDecimal getLastWater() {
        return this.lastWater;
    }

    /**
     * set the lastWater - 上月用水量
     */
    public void setLastWater(BigDecimal lastWater) {
        this.lastWater = lastWater;
    }

    /**
     * get the lastWaterfee - 上月水费
     * @return the lastWaterfee
     */
    public BigDecimal getLastWaterfee() {
        return this.lastWaterfee;
    }

    /**
     * set the lastWaterfee - 上月水费
     */
    public void setLastWaterfee(BigDecimal lastWaterfee) {
        this.lastWaterfee = lastWaterfee;
    }

    /**
     * get the lastElecfee - 上月电费
     * @return the lastElecfee
     */
    public BigDecimal getLastElecfee() {
        return this.lastElecfee;
    }

    /**
     * set the lastElecfee - 上月电费
     */
    public void setLastElecfee(BigDecimal lastElecfee) {
        this.lastElecfee = lastElecfee;
    }

    /**
     * get the waterFee - 本月水费
     * @return the waterFee
     */
    public BigDecimal getWaterFee() {
        return this.waterFee;
    }

    /**
     * set the waterFee - 本月水费
     */
    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }

    /**
     * get the elecFee - 本月电费
     * @return the elecFee
     */
    public BigDecimal getElecFee() {
        return this.elecFee;
    }

    /**
     * set the elecFee - 本月电费
     */
    public void setElecFee(BigDecimal elecFee) {
        this.elecFee = elecFee;
    }

    /**
     * get the monthElec - 本月用电量
     * @return the monthElec
     */
    public BigDecimal getMonthElec() {
        return this.monthElec;
    }

    /**
     * set the monthElec - 本月用电量
     */
    public void setMonthElec(BigDecimal monthElec) {
        this.monthElec = monthElec;
    }

    /**
     * get the monthWater - 本月用水量
     * @return the monthWater
     */
    public BigDecimal getMonthWater() {
        return this.monthWater;
    }

    /**
     * set the monthWater - 本月用水量
     */
    public void setMonthWater(BigDecimal monthWater) {
        this.monthWater = monthWater;
    }

    /**
     * get the createrTime - 创建时间
     * @return the createrTime
     */
    public Timestamp getCreaterTime() {
        return this.createrTime;
    }

    /**
     * set the createrTime - 创建时间
     */
    public void setCreaterTime(Timestamp createrTime) {
        this.createrTime = createrTime;
    }

    /**
     * get the creator - 创建人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the buiidtime - 生成时间
     * @return the buiidtime
     */
    public Timestamp getBuiidtime() {
        return this.buiidtime;
    }

    /**
     * set the buiidtime - 生成时间
     */
    public void setBuiidtime(Timestamp buiidtime) {
        this.buiidtime = buiidtime;
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
     * get the networkFee - 网费
     * @return the networkFee
     */
    public BigDecimal getNetworkFee() {
        return this.networkFee;
    }

    /**
     * set the networkFee - 网费
     */
    public void setNetworkFee(BigDecimal networkFee) {
        this.networkFee = networkFee;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setEmployeeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("employeeId")), employeeId));
        setManName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manName")), manName));
        setRoomId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomId")), roomId));
        setRoomName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomName")), roomName));
        setRoomNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomNo")), roomNo));
        setBedNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNum")), bedNum));
        setBuildingCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingCode")), buildingCode));
        setFloorCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorCode")), floorCode));
        setDormitoryNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormitoryNo")), dormitoryNo));
        setMonthRent(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthRent")), monthRent));
        setLastElec(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastElec")), lastElec));
        setLastWater(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastWater")), lastWater));
        setLastWaterfee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastWaterfee")), lastWaterfee));
        setLastElecfee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastElecfee")), lastElecfee));
        setWaterFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("waterFee")), waterFee));
        setElecFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("elecFee")), elecFee));
        setMonthElec(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthElec")), monthElec));
        setMonthWater(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthWater")), monthWater));
        setCreaterTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createrTime"))));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setBuiidtime(DateUtils.toTimestamp(StringUtils.toString(map.get("buiidtime"))));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setHospitalManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("hospitalManageFee")), hospitalManageFee));
        setPropertyManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("propertyManageFee")), propertyManageFee));
        setNetworkFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("networkFee")), networkFee));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("employeeId",StringUtils.toString(employeeId, eiMetadata.getMeta("employeeId")));
        map.put("manName",StringUtils.toString(manName, eiMetadata.getMeta("manName")));
        map.put("roomId",StringUtils.toString(roomId, eiMetadata.getMeta("roomId")));
        map.put("roomName",StringUtils.toString(roomName, eiMetadata.getMeta("roomName")));
        map.put("roomNo",StringUtils.toString(roomNo, eiMetadata.getMeta("roomNo")));
        map.put("bedNum",StringUtils.toString(bedNum, eiMetadata.getMeta("bedNum")));
        map.put("buildingCode",StringUtils.toString(buildingCode, eiMetadata.getMeta("buildingCode")));
        map.put("floorCode",StringUtils.toString(floorCode, eiMetadata.getMeta("floorCode")));
        map.put("dormitoryNo",StringUtils.toString(dormitoryNo, eiMetadata.getMeta("dormitoryNo")));
        map.put("monthRent",StringUtils.toString(monthRent, eiMetadata.getMeta("monthRent")));
        map.put("lastElec",StringUtils.toString(lastElec, eiMetadata.getMeta("lastElec")));
        map.put("lastWater",StringUtils.toString(lastWater, eiMetadata.getMeta("lastWater")));
        map.put("lastWaterfee",StringUtils.toString(lastWaterfee, eiMetadata.getMeta("lastWaterfee")));
        map.put("lastElecfee",StringUtils.toString(lastElecfee, eiMetadata.getMeta("lastElecfee")));
        map.put("waterFee",StringUtils.toString(waterFee, eiMetadata.getMeta("waterFee")));
        map.put("elecFee",StringUtils.toString(elecFee, eiMetadata.getMeta("elecFee")));
        map.put("monthElec",StringUtils.toString(monthElec, eiMetadata.getMeta("monthElec")));
        map.put("monthWater",StringUtils.toString(monthWater, eiMetadata.getMeta("monthWater")));
        map.put("createrTime",StringUtils.toString(createrTime, eiMetadata.getMeta("createrTime")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("buiidtime",StringUtils.toString(buiidtime, eiMetadata.getMeta("buiidtime")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("hospitalManageFee",StringUtils.toString(hospitalManageFee, eiMetadata.getMeta("hospitalManageFee")));
        map.put("propertyManageFee",StringUtils.toString(propertyManageFee, eiMetadata.getMeta("propertyManageFee")));
        map.put("networkFee",StringUtils.toString(networkFee, eiMetadata.getMeta("networkFee")));
        return map;
    }
}