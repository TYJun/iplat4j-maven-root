/**
* Generate time : 2022-07-04 16:32:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* HiApply
* 
*/
public class HiApply extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String applyNo = " ";		/* 申请单号*/
    private String status = " ";		/* 状态（01=新增,02=待审核,03=审核通过,04=审核驳回）*/
    private String statusName = " ";
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
    private Date installDate;	/* 安装日期*/
    private String applyReason = " ";		/* 申请理由*/
    private String applyDeptNum = " ";		/* 申请科室编码*/
    private String applyDeptName = " ";		/* 申请科室名称*/
    private String managerNo = " ";		/* 管理员工号*/
    private String managerName = " ";		/* 管理员姓名*/
    private String remark = " ";		/* 备注*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String  recReviseTime = " " ;		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String emo = " ";
    private String productionStatus = " ";
    private String emo1 = " ";
    private String emo2 = " ";
    private String emo3 = " ";
    private String emo4 = " ";
    private String emo5 = " ";


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

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态（01=新增,02=待审核,03=审核通过,04=审核驳回）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
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

        eiColumn = new EiColumn("applyReason");
        eiColumn.setDescName("申请理由");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyDeptNum");
        eiColumn.setDescName("申请科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyDeptName");
        eiColumn.setDescName("申请科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerNo");
        eiColumn.setDescName("管理员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerName");
        eiColumn.setDescName("管理员姓名");
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

        eiColumn = new EiColumn("emo");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("productionStatus");
        eiColumn.setDescName("制作状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo1");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo2");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo3");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo4");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo5");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);



    }

    /**
     * the constructor
     */
    public HiApply() {
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
     * get the status - 状态（01=新增,02=待审核,03=审核通过,04=审核驳回）
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态（01=新增,02=待审核,03=审核通过,04=审核驳回）
     */
    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
    public Date getInstallDate() {
        return this.installDate;
    }

    /**
     * set the installDate - 安装日期
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * get the applyReason - 申请理由
     * @return the applyReason
     */
    public String getApplyReason() {
        return this.applyReason;
    }

    /**
     * set the applyReason - 申请理由
     */
    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    /**
     * get the applyDeptNum - 申请科室编码
     * @return the applyDeptNum
     */
    public String getApplyDeptNum() {
        return this.applyDeptNum;
    }

    /**
     * set the applyDeptNum - 申请科室编码
     */
    public void setApplyDeptNum(String applyDeptNum) {
        this.applyDeptNum = applyDeptNum;
    }

    /**
     * get the applyDeptName - 申请科室名称
     * @return the applyDeptName
     */
    public String getApplyDeptName() {
        return this.applyDeptName;
    }

    /**
     * set the applyDeptName - 申请科室名称
     */
    public void setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
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


    public String getEmo() {
        return this.emo;
    }

    public void setEmo(String emo) {
        this.emo = emo;
    }

    public String getProductionStatus() {return this.productionStatus;}

    public void setProductionStatus(String productionStatus) {this.productionStatus = productionStatus;}

    public String getEmo1() {
        return this.emo1;
    }

    public void setEmo1(String emo1) {
        this.emo1 = emo1;
    }

    public String getEmo2() {
        return this.emo2;
    }

    public void setEmo2(String emo2) {
        this.emo2 = emo2;
    }

    public String getEmo3() {
        return this.emo3;
    }

    public void setEmo3(String emo3) {
        this.emo3 = emo3;
    }

    public String getEmo4() {
        return this.emo4;
    }

    public void setEmo4(String emo4) {
        this.emo4 = emo4;
    }

    public String getEmo5() {
        return this.emo5;
    }

    public void setEmo5(String emo5) {
        this.emo5 = emo5;
    }
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setApplyNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyNo")), applyNo));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
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
        setInstallDate(DateUtils.toDate(StringUtils.toString(map.get("installDate"))));
        setApplyReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyReason")), applyReason));
        setApplyDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyDeptNum")), applyDeptNum));
        setApplyDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyDeptName")), applyDeptName));
        setManagerNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerNo")), managerNo));
        setManagerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerName")), managerName));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setEmo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo")), emo));
        setProductionStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productionStatus")),productionStatus));
        setEmo1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo1")), emo1));
        setEmo2(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo2")), emo2));
        setEmo3(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo3")), emo3));
        setEmo4(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo4")), emo4));
        setEmo5(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo5")), emo5));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("applyNo",StringUtils.toString(applyNo, eiMetadata.getMeta("applyNo")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
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
        map.put("applyReason",StringUtils.toString(applyReason, eiMetadata.getMeta("applyReason")));
        map.put("applyDeptNum",StringUtils.toString(applyDeptNum, eiMetadata.getMeta("applyDeptNum")));
        map.put("applyDeptName",StringUtils.toString(applyDeptName, eiMetadata.getMeta("applyDeptName")));
        map.put("managerNo",StringUtils.toString(managerNo, eiMetadata.getMeta("managerNo")));
        map.put("managerName",StringUtils.toString(managerName, eiMetadata.getMeta("managerName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("emo",StringUtils.toString(emo, eiMetadata.getMeta("emo")));
        map.put("productionStatus",StringUtils.toString(productionStatus,eiMetadata.getMeta("productionStatus")));
        map.put("emo1",StringUtils.toString(emo1, eiMetadata.getMeta("emo1")));
        map.put("emo2",StringUtils.toString(emo2, eiMetadata.getMeta("emo2")));
        map.put("emo3",StringUtils.toString(emo3, eiMetadata.getMeta("emo3")));
        map.put("emo4",StringUtils.toString(emo4, eiMetadata.getMeta("emo4")));
        map.put("emo5",StringUtils.toString(emo5, eiMetadata.getMeta("emo5")));
        return map;
    }


}