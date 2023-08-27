/**
* Generate time : 2021-05-26 13:24:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealPatientCard 病员卡片信息实体类
* 
*/
public class PSPCTmealPatientCard extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String createTime = " ";		
    private String creator = " ";		
    private String reviseTime = " ";		
    private String resvisor = " ";		
    private String patientNum = " ";		/* 住院号*/
    private String patientCode = " ";		
    private String patientName = " ";		/* 病人姓名*/
    private String patientTel = " ";		/* 病人电话*/
    private String hospitalTime = " ";		/* 入院时间*/
    private String buildingNum = " ";		/* 楼号*/
    private String building = " ";		/* 楼*/
    private String floorNum = " ";		/* 层号*/
    private String floor = " ";		/* 层*/
    private String wardCode = "";		/* 病区编码*/
    private String wardName = " ";		/* 病区名称*/
    private String deptNum = " ";		/* 科室编码*/
    private String dpetName = " ";		/* 科室名称*/
    private String bedNo = " ";		/* 床号*/
    private String dataGroupCode = " ";		
    private String dataGroupCodeTree = " ";		
    private String registerType = " ";		
    private String yizhu = " ";		/* 医嘱信息*/
    private String activeStatus = "0";		/* 卡片激活状态*/
    private String idenNo = " ";		/* 身份证号*/
    private String hospitalNo = " ";		/* 院区编号*/
    private String hospitalName = " ";		/* 院区名称*/
    private String cardBaseCode = " ";		/* 卡片编码*/
    private String balance = " ";		/* 余额*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reviseTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("resvisor");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patientNum");
        eiColumn.setDescName("住院号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patientCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patientName");
        eiColumn.setDescName("病人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patientTel");
        eiColumn.setDescName("病人电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalTime");
        eiColumn.setDescName("入院时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingNum");
        eiColumn.setDescName("楼号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorNum");
        eiColumn.setDescName("层号");
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

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dpetName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCodeTree");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("registerType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("yizhu");
        eiColumn.setDescName("医嘱信息");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activeStatus");
        eiColumn.setDescName("卡片激活状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idenNo");
        eiColumn.setDescName("身份证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalNo");
        eiColumn.setDescName("院区编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalName");
        eiColumn.setDescName("院区名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealPatientCard() {
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
     * get the createTime 
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime 
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the creator 
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the reviseTime 
     * @return the reviseTime
     */
    public String getReviseTime() {
        return this.reviseTime;
    }

    /**
     * set the reviseTime 
     */
    public void setReviseTime(String reviseTime) {
        this.reviseTime = reviseTime;
    }

    /**
     * get the resvisor 
     * @return the resvisor
     */
    public String getResvisor() {
        return this.resvisor;
    }

    /**
     * set the resvisor 
     */
    public void setResvisor(String resvisor) {
        this.resvisor = resvisor;
    }

    /**
     * get the patientNum - 住院号
     * @return the patientNum
     */
    public String getPatientNum() {
        return this.patientNum;
    }

    /**
     * set the patientNum - 住院号
     */
    public void setPatientNum(String patientNum) {
        this.patientNum = patientNum;
    }

    /**
     * get the patientCode 
     * @return the patientCode
     */
    public String getPatientCode() {
        return this.patientCode;
    }

    /**
     * set the patientCode 
     */
    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    /**
     * get the patientName - 病人姓名
     * @return the patientName
     */
    public String getPatientName() {
        return this.patientName;
    }

    /**
     * set the patientName - 病人姓名
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * get the patientTel - 病人电话
     * @return the patientTel
     */
    public String getPatientTel() {
        return this.patientTel;
    }

    /**
     * set the patientTel - 病人电话
     */
    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel;
    }

    /**
     * get the hospitalTime - 入院时间
     * @return the hospitalTime
     */
    public String getHospitalTime() {
        return this.hospitalTime;
    }

    /**
     * set the hospitalTime - 入院时间
     */
    public void setHospitalTime(String hospitalTime) {
        this.hospitalTime = hospitalTime;
    }

    /**
     * get the buildingNum - 楼号
     * @return the buildingNum
     */
    public String getBuildingNum() {
        return this.buildingNum;
    }

    /**
     * set the buildingNum - 楼号
     */
    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
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
     * get the floorNum - 层号
     * @return the floorNum
     */
    public String getFloorNum() {
        return this.floorNum;
    }

    /**
     * set the floorNum - 层号
     */
    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
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
     * get the dpetName - 科室名称
     * @return the dpetName
     */
    public String getDpetName() {
        return this.dpetName;
    }

    /**
     * set the dpetName - 科室名称
     */
    public void setDpetName(String dpetName) {
        this.dpetName = dpetName;
    }

    /**
     * get the bedNo - 床号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the dataGroupCode 
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode 
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the dataGroupCodeTree 
     * @return the dataGroupCodeTree
     */
    public String getDataGroupCodeTree() {
        return this.dataGroupCodeTree;
    }

    /**
     * set the dataGroupCodeTree 
     */
    public void setDataGroupCodeTree(String dataGroupCodeTree) {
        this.dataGroupCodeTree = dataGroupCodeTree;
    }

    /**
     * get the registerType 
     * @return the registerType
     */
    public String getRegisterType() {
        return this.registerType;
    }

    /**
     * set the registerType 
     */
    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    /**
     * get the yizhu - 医嘱信息
     * @return the yizhu
     */
    public String getYizhu() {
        return this.yizhu;
    }

    /**
     * set the yizhu - 医嘱信息
     */
    public void setYizhu(String yizhu) {
        this.yizhu = yizhu;
    }

    /**
     * get the activeStatus - 卡片激活状态
     * @return the activeStatus
     */
    public String getActiveStatus() {
        return this.activeStatus;
    }

    public String getCardBaseCode() {
        return cardBaseCode;
    }

    public void setCardBaseCode(String cardBaseCode) {
        this.cardBaseCode = cardBaseCode;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * set the activeStatus - 卡片激活状态
     */
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * get the idenNo - 身份证号
     * @return the idenNo
     */
    public String getIdenNo() {
        return this.idenNo;
    }

    /**
     * set the idenNo - 身份证号
     */
    public void setIdenNo(String idenNo) {
        this.idenNo = idenNo;
    }

    /**
     * get the hospitalNo - 院区编号
     * @return the hospitalNo
     */
    public String getHospitalNo() {
        return this.hospitalNo;
    }

    /**
     * set the hospitalNo - 院区编号
     */
    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    /**
     * get the hospitalName - 院区名称
     * @return the hospitalName
     */
    public String getHospitalName() {
        return this.hospitalName;
    }

    /**
     * set the hospitalName - 院区名称
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reviseTime")), reviseTime));
        setResvisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("resvisor")), resvisor));
        setPatientNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patientNum")), patientNum));
        setPatientCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patientCode")), patientCode));
        setPatientName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patientName")), patientName));
        setPatientTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patientTel")), patientTel));
        setHospitalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hospitalTime")), hospitalTime));
        setBuildingNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingNum")), buildingNum));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloorNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorNum")), floorNum));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setWardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardCode")), wardCode));
        setWardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardName")), wardName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDpetName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dpetName")), dpetName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setDataGroupCodeTree(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCodeTree")), dataGroupCodeTree));
        setRegisterType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("registerType")), registerType));
        setYizhu(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yizhu")), yizhu));
        setActiveStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("activeStatus")), activeStatus));
        setIdenNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idenNo")), idenNo));
        setHospitalNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hospitalNo")), hospitalNo));
        setHospitalName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hospitalName")), hospitalName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("reviseTime",StringUtils.toString(reviseTime, eiMetadata.getMeta("reviseTime")));
        map.put("resvisor",StringUtils.toString(resvisor, eiMetadata.getMeta("resvisor")));
        map.put("patientNum",StringUtils.toString(patientNum, eiMetadata.getMeta("patientNum")));
        map.put("patientCode",StringUtils.toString(patientCode, eiMetadata.getMeta("patientCode")));
        map.put("patientName",StringUtils.toString(patientName, eiMetadata.getMeta("patientName")));
        map.put("patientTel",StringUtils.toString(patientTel, eiMetadata.getMeta("patientTel")));
        map.put("hospitalTime",StringUtils.toString(hospitalTime, eiMetadata.getMeta("hospitalTime")));
        map.put("buildingNum",StringUtils.toString(buildingNum, eiMetadata.getMeta("buildingNum")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floorNum",StringUtils.toString(floorNum, eiMetadata.getMeta("floorNum")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("wardCode",StringUtils.toString(wardCode, eiMetadata.getMeta("wardCode")));
        map.put("wardName",StringUtils.toString(wardName, eiMetadata.getMeta("wardName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("dpetName",StringUtils.toString(dpetName, eiMetadata.getMeta("dpetName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("dataGroupCodeTree",StringUtils.toString(dataGroupCodeTree, eiMetadata.getMeta("dataGroupCodeTree")));
        map.put("registerType",StringUtils.toString(registerType, eiMetadata.getMeta("registerType")));
        map.put("yizhu",StringUtils.toString(yizhu, eiMetadata.getMeta("yizhu")));
        map.put("activeStatus",StringUtils.toString(activeStatus, eiMetadata.getMeta("activeStatus")));
        map.put("idenNo",StringUtils.toString(idenNo, eiMetadata.getMeta("idenNo")));
        map.put("hospitalNo",StringUtils.toString(hospitalNo, eiMetadata.getMeta("hospitalNo")));
        map.put("hospitalName",StringUtils.toString(hospitalName, eiMetadata.getMeta("hospitalName")));
        return map;
    }
}