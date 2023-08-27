/**
* Generate time : 2022-07-04 16:32:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* HiIcon
* 
*/
public class HiIcon extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String applyNo = " ";		/* 申请单号*/
    private String iconNum = "";		/* 标识编号*/
    private String iconName = " ";		/* 标识名称*/
    private String iconZhContent = " ";		/* 标识中文内容*/
    private String iconEnContent = " ";		/* 标识英文内容*/
    private String classifyId = " ";		/* 标识分类表ID*/
    private String classifyName = " ";		/* 标识分类名称*/
    private Integer iconAmount = new Integer(0);		/* 数量*/
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String spotCode = " ";		/* 安装地点编码*/
    private String spotName = " ";		/* 安装地点名称*/
    private String spotDesc = " ";		/* 安装地点说明*/
    private String  installDate = " ";	/* 安装日期*/
    private String deptNum = " ";		/* 使用科室编码*/
    private String deptName = " ";		/* 使用科室名称*/
    private String managerNo = " ";		/* 管理员工号*/
    private String managerName = " ";		/* 管理员姓名*/
    private String status = " ";		/* 状态(0=新建,1=启用,-1=停用)*/
    private String remark = " ";		/* 备注*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime ;		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime ;		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String archiveFlag = " ";		/* 归档标记*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyNo");
        eiColumn.setDescName("申请单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iconNum");
        eiColumn.setDescName("标识编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iconName");
        eiColumn.setDescName("标识名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iconZhContent");
        eiColumn.setDescName("标识中文内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iconEnContent");
        eiColumn.setDescName("标识英文内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyId");
        eiColumn.setDescName("标识分类表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("标识分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iconAmount");
        eiColumn.setDescName("数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotCode");
        eiColumn.setDescName("安装地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("安装地点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotDesc");
        eiColumn.setDescName("安装地点说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("installDate");
        eiColumn.setDescName("安装日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("使用科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("使用科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerNo");
        eiColumn.setDescName("管理员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerName");
        eiColumn.setDescName("管理员姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态(0=新建,1=启用,-1=停用)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
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

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HiIcon() {
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
     * get the applyNo - 申请单号
     * @return the applyNo
     */
    public String getApplyNo() {
        return this.applyNo;
    }

    /**
     * set the applyNo - 申请单号
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    /**
     * get the iconNum - 标识编号
     * @return the iconNum
     */
    public String getIconNum() {
        return this.iconNum;
    }

    /**
     * set the iconNum - 标识编号
     */
    public void setIconNum(String iconNum) {
        this.iconNum = iconNum;
    }

    /**
     * get the iconName - 标识名称
     * @return the iconName
     */
    public String getIconName() {
        return this.iconName;
    }

    /**
     * set the iconName - 标识名称
     */
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    /**
     * get the iconZhContent - 标识中文内容
     * @return the iconZhContent
     */
    public String getIconZhContent() {
        return this.iconZhContent;
    }

    /**
     * set the iconZhContent - 标识中文内容
     */
    public void setIconZhContent(String iconZhContent) {
        this.iconZhContent = iconZhContent;
    }

    /**
     * get the iconEnContent - 标识英文内容
     * @return the iconEnContent
     */
    public String getIconEnContent() {
        return this.iconEnContent;
    }

    /**
     * set the iconEnContent - 标识英文内容
     */
    public void setIconEnContent(String iconEnContent) {
        this.iconEnContent = iconEnContent;
    }

    /**
     * get the classifyId - 标识分类表ID
     * @return the classifyId
     */
    public String getClassifyId() {
        return this.classifyId;
    }

    /**
     * set the classifyId - 标识分类表ID
     */
    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * get the classifyName - 标识分类名称
     * @return the classifyName
     */
    public String getClassifyName() {
        return this.classifyName;
    }

    /**
     * set the classifyName - 标识分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * get the iconAmount - 数量
     * @return the iconAmount
     */
    public Integer getIconAmount() {
        return this.iconAmount;
    }

    /**
     * set the iconAmount - 数量
     */
    public void setIconAmount(Integer iconAmount) {
        this.iconAmount = iconAmount;
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
     * get the spotCode - 安装地点编码
     * @return the spotCode
     */
    public String getSpotCode() {
        return this.spotCode;
    }

    /**
     * set the spotCode - 安装地点编码
     */
    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    /**
     * get the spotName - 安装地点名称
     * @return the spotName
     */
    public String getSpotName() {
        return this.spotName;
    }

    /**
     * set the spotName - 安装地点名称
     */
    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    /**
     * get the spotDesc - 安装地点说明
     * @return the spotDesc
     */
    public String getSpotDesc() {
        return this.spotDesc;
    }

    /**
     * set the spotDesc - 安装地点说明
     */
    public void setSpotDesc(String spotDesc) {
        this.spotDesc = spotDesc;
    }

    /**
     * get the installDate - 安装日期
     * @return the installDate
     */
    public String  getInstallDate() {
        return this.installDate;
    }

    /**
     * set the installDate - 安装日期
     */
    public void setInstallDate(String  installDate) {
        this.installDate = installDate;
    }

    /**
     * get the deptNum - 使用科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 使用科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 使用科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 使用科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the managerNo - 管理员工号
     * @return the managerNo
     */
    public String getManagerNo() {
        return this.managerNo;
    }

    /**
     * set the managerNo - 管理员工号
     */
    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    /**
     * get the managerName - 管理员姓名
     * @return the managerName
     */
    public String getManagerName() {
        return this.managerName;
    }

    /**
     * set the managerName - 管理员姓名
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * get the status - 状态(0=新建,1=启用,-1=停用)
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态(0=新建,1=启用,-1=停用)
     */
    public void setStatus(String status) {
        this.status = status;
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
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setApplyNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyNo")), applyNo));
        setIconNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iconNum")), iconNum));
        setIconName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iconName")), iconName));
        setIconZhContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iconZhContent")), iconZhContent));
        setIconEnContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iconEnContent")), iconEnContent));
        setClassifyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyId")), classifyId));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setIconAmount(NumberUtils.toInteger(StringUtils.toString(map.get("iconAmount")), iconAmount));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setSpotCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotCode")), spotCode));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
        setSpotDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotDesc")), spotDesc));
        setInstallDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("installDate")),installDate));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setManagerNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerNo")), managerNo));
        setManagerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerName")), managerName));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("applyNo",StringUtils.toString(applyNo, eiMetadata.getMeta("applyNo")));
        map.put("iconNum",StringUtils.toString(iconNum, eiMetadata.getMeta("iconNum")));
        map.put("iconName",StringUtils.toString(iconName, eiMetadata.getMeta("iconName")));
        map.put("iconZhContent",StringUtils.toString(iconZhContent, eiMetadata.getMeta("iconZhContent")));
        map.put("iconEnContent",StringUtils.toString(iconEnContent, eiMetadata.getMeta("iconEnContent")));
        map.put("classifyId",StringUtils.toString(classifyId, eiMetadata.getMeta("classifyId")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("iconAmount",StringUtils.toString(iconAmount, eiMetadata.getMeta("iconAmount")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("spotCode",StringUtils.toString(spotCode, eiMetadata.getMeta("spotCode")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        map.put("spotDesc",StringUtils.toString(spotDesc, eiMetadata.getMeta("spotDesc")));
        map.put("installDate",StringUtils.toString(installDate, eiMetadata.getMeta("installDate")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("managerNo",StringUtils.toString(managerNo, eiMetadata.getMeta("managerNo")));
        map.put("managerName",StringUtils.toString(managerName, eiMetadata.getMeta("managerName")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}