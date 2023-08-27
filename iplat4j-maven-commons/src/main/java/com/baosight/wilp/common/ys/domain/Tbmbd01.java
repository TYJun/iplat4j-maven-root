/**
* Generate time : 2021-01-21 15:52:52
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd01
* 
*/
public class Tbmbd01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String archiveFlag = " ";		
    private String bussTypeNum = " ";		
    private String costUnitFlag = " ";		
    private String deptName = " ";		
    private String deptNum = " ";		
    private String deptTypeNum = " ";		
    private String erpCode = " ";		
    private String finaCode = " ";		
    private String isOutUnit = " ";		
    private String lastFlag = " ";		
    private String mainteWgroupNum = " ";		
    private String recCreateTime = " ";		
    private String recCreator = " ";		
    private String recReviseTime = " ";		
    private String recRevisor = " ";		
    private String remark = " ";		
    private String statusNum = " ";		
    private String superDeptName = " ";		
    private String superDeptNum = " ";		
    private String parentId = " ";		
    private String serviceType = " ";		/* 服务类别*/
    private String statFlag = " ";		/* 核算区域*/
    private String jpDeptName = " ";		/* 科室名称简拼*/
    private String stopFlag = " ";		/* 停用标记*/
    private String hisDeptNum = " ";		/* his科室代码*/
    private String hisDeptName = " ";		/* his科室名称*/
    private String deptUnion = " ";		
    private String partyBranch = " ";		
    private String hisDeptType = " ";		/* his科室类型*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bussTypeNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costUnitFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptTypeNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("erpCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finaCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isOutUnit");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mainteWgroupNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superDeptName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superDeptNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceType");
        eiColumn.setDescName("服务类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statFlag");
        eiColumn.setDescName("核算区域");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpDeptName");
        eiColumn.setDescName("科室名称简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName("停用标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hisDeptNum");
        eiColumn.setDescName("his科室代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hisDeptName");
        eiColumn.setDescName("his科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptUnion");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("partyBranch");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hisDeptType");
        eiColumn.setDescName("his科室类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd01() {
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
     * get the archiveFlag 
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag 
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the bussTypeNum 
     * @return the bussTypeNum
     */
    public String getBussTypeNum() {
        return this.bussTypeNum;
    }

    /**
     * set the bussTypeNum 
     */
    public void setBussTypeNum(String bussTypeNum) {
        this.bussTypeNum = bussTypeNum;
    }

    /**
     * get the costUnitFlag 
     * @return the costUnitFlag
     */
    public String getCostUnitFlag() {
        return this.costUnitFlag;
    }

    /**
     * set the costUnitFlag 
     */
    public void setCostUnitFlag(String costUnitFlag) {
        this.costUnitFlag = costUnitFlag;
    }

    /**
     * get the deptName 
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName 
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptNum 
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum 
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptTypeNum 
     * @return the deptTypeNum
     */
    public String getDeptTypeNum() {
        return this.deptTypeNum;
    }

    /**
     * set the deptTypeNum 
     */
    public void setDeptTypeNum(String deptTypeNum) {
        this.deptTypeNum = deptTypeNum;
    }

    /**
     * get the erpCode 
     * @return the erpCode
     */
    public String getErpCode() {
        return this.erpCode;
    }

    /**
     * set the erpCode 
     */
    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    /**
     * get the finaCode 
     * @return the finaCode
     */
    public String getFinaCode() {
        return this.finaCode;
    }

    /**
     * set the finaCode 
     */
    public void setFinaCode(String finaCode) {
        this.finaCode = finaCode;
    }

    /**
     * get the isOutUnit 
     * @return the isOutUnit
     */
    public String getIsOutUnit() {
        return this.isOutUnit;
    }

    /**
     * set the isOutUnit 
     */
    public void setIsOutUnit(String isOutUnit) {
        this.isOutUnit = isOutUnit;
    }

    /**
     * get the lastFlag 
     * @return the lastFlag
     */
    public String getLastFlag() {
        return this.lastFlag;
    }

    /**
     * set the lastFlag 
     */
    public void setLastFlag(String lastFlag) {
        this.lastFlag = lastFlag;
    }

    /**
     * get the mainteWgroupNum 
     * @return the mainteWgroupNum
     */
    public String getMainteWgroupNum() {
        return this.mainteWgroupNum;
    }

    /**
     * set the mainteWgroupNum 
     */
    public void setMainteWgroupNum(String mainteWgroupNum) {
        this.mainteWgroupNum = mainteWgroupNum;
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
     * get the remark 
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the statusNum 
     * @return the statusNum
     */
    public String getStatusNum() {
        return this.statusNum;
    }

    /**
     * set the statusNum 
     */
    public void setStatusNum(String statusNum) {
        this.statusNum = statusNum;
    }

    /**
     * get the superDeptName 
     * @return the superDeptName
     */
    public String getSuperDeptName() {
        return this.superDeptName;
    }

    /**
     * set the superDeptName 
     */
    public void setSuperDeptName(String superDeptName) {
        this.superDeptName = superDeptName;
    }

    /**
     * get the superDeptNum 
     * @return the superDeptNum
     */
    public String getSuperDeptNum() {
        return this.superDeptNum;
    }

    /**
     * set the superDeptNum 
     */
    public void setSuperDeptNum(String superDeptNum) {
        this.superDeptNum = superDeptNum;
    }

    /**
     * get the parentId 
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId 
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the serviceType - 服务类别
     * @return the serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * set the serviceType - 服务类别
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * get the statFlag - 核算区域
     * @return the statFlag
     */
    public String getStatFlag() {
        return this.statFlag;
    }

    /**
     * set the statFlag - 核算区域
     */
    public void setStatFlag(String statFlag) {
        this.statFlag = statFlag;
    }

    /**
     * get the jpDeptName - 科室名称简拼
     * @return the jpDeptName
     */
    public String getJpDeptName() {
        return this.jpDeptName;
    }

    /**
     * set the jpDeptName - 科室名称简拼
     */
    public void setJpDeptName(String jpDeptName) {
        this.jpDeptName = jpDeptName;
    }

    /**
     * get the stopFlag - 停用标记
     * @return the stopFlag
     */
    public String getStopFlag() {
        return this.stopFlag;
    }

    /**
     * set the stopFlag - 停用标记
     */
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * get the hisDeptNum - his科室代码
     * @return the hisDeptNum
     */
    public String getHisDeptNum() {
        return this.hisDeptNum;
    }

    /**
     * set the hisDeptNum - his科室代码
     */
    public void setHisDeptNum(String hisDeptNum) {
        this.hisDeptNum = hisDeptNum;
    }

    /**
     * get the hisDeptName - his科室名称
     * @return the hisDeptName
     */
    public String getHisDeptName() {
        return this.hisDeptName;
    }

    /**
     * set the hisDeptName - his科室名称
     */
    public void setHisDeptName(String hisDeptName) {
        this.hisDeptName = hisDeptName;
    }

    /**
     * get the deptUnion 
     * @return the deptUnion
     */
    public String getDeptUnion() {
        return this.deptUnion;
    }

    /**
     * set the deptUnion 
     */
    public void setDeptUnion(String deptUnion) {
        this.deptUnion = deptUnion;
    }

    /**
     * get the partyBranch 
     * @return the partyBranch
     */
    public String getPartyBranch() {
        return this.partyBranch;
    }

    /**
     * set the partyBranch 
     */
    public void setPartyBranch(String partyBranch) {
        this.partyBranch = partyBranch;
    }

    /**
     * get the hisDeptType - his科室类型
     * @return the hisDeptType
     */
    public String getHisDeptType() {
        return this.hisDeptType;
    }

    /**
     * set the hisDeptType - his科室类型
     */
    public void setHisDeptType(String hisDeptType) {
        this.hisDeptType = hisDeptType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setBussTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bussTypeNum")), bussTypeNum));
        setCostUnitFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("costUnitFlag")), costUnitFlag));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptTypeNum")), deptTypeNum));
        setErpCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("erpCode")), erpCode));
        setFinaCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finaCode")), finaCode));
        setIsOutUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isOutUnit")), isOutUnit));
        setLastFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastFlag")), lastFlag));
        setMainteWgroupNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mainteWgroupNum")), mainteWgroupNum));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setStatusNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusNum")), statusNum));
        setSuperDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superDeptName")), superDeptName));
        setSuperDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superDeptNum")), superDeptNum));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setServiceType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceType")), serviceType));
        setStatFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statFlag")), statFlag));
        setJpDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpDeptName")), jpDeptName));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setHisDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hisDeptNum")), hisDeptNum));
        setHisDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hisDeptName")), hisDeptName));
        setDeptUnion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptUnion")), deptUnion));
        setPartyBranch(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("partyBranch")), partyBranch));
        setHisDeptType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hisDeptType")), hisDeptType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("bussTypeNum",StringUtils.toString(bussTypeNum, eiMetadata.getMeta("bussTypeNum")));
        map.put("costUnitFlag",StringUtils.toString(costUnitFlag, eiMetadata.getMeta("costUnitFlag")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptTypeNum",StringUtils.toString(deptTypeNum, eiMetadata.getMeta("deptTypeNum")));
        map.put("erpCode",StringUtils.toString(erpCode, eiMetadata.getMeta("erpCode")));
        map.put("finaCode",StringUtils.toString(finaCode, eiMetadata.getMeta("finaCode")));
        map.put("isOutUnit",StringUtils.toString(isOutUnit, eiMetadata.getMeta("isOutUnit")));
        map.put("lastFlag",StringUtils.toString(lastFlag, eiMetadata.getMeta("lastFlag")));
        map.put("mainteWgroupNum",StringUtils.toString(mainteWgroupNum, eiMetadata.getMeta("mainteWgroupNum")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("statusNum",StringUtils.toString(statusNum, eiMetadata.getMeta("statusNum")));
        map.put("superDeptName",StringUtils.toString(superDeptName, eiMetadata.getMeta("superDeptName")));
        map.put("superDeptNum",StringUtils.toString(superDeptNum, eiMetadata.getMeta("superDeptNum")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("serviceType",StringUtils.toString(serviceType, eiMetadata.getMeta("serviceType")));
        map.put("statFlag",StringUtils.toString(statFlag, eiMetadata.getMeta("statFlag")));
        map.put("jpDeptName",StringUtils.toString(jpDeptName, eiMetadata.getMeta("jpDeptName")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("hisDeptNum",StringUtils.toString(hisDeptNum, eiMetadata.getMeta("hisDeptNum")));
        map.put("hisDeptName",StringUtils.toString(hisDeptName, eiMetadata.getMeta("hisDeptName")));
        map.put("deptUnion",StringUtils.toString(deptUnion, eiMetadata.getMeta("deptUnion")));
        map.put("partyBranch",StringUtils.toString(partyBranch, eiMetadata.getMeta("partyBranch")));
        map.put("hisDeptType",StringUtils.toString(hisDeptType, eiMetadata.getMeta("hisDeptType")));
        return map;
    }
}