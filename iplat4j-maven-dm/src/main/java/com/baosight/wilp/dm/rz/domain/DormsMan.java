/**
* Generate time : 2022-03-28 16:05:34
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.rz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsMan
* 
*/
public class DormsMan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String manNo = " ";		/* 工号*/
    private String manName = " ";		/* 姓名*/
    private String statusCode = " ";		/* 工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99已退宿)*/
    private String sex = " ";		/* 性别*/
    private Integer age = new Integer(0);		/* 员工年龄*/
    private String phone = " ";		/* 电话*/
    private String identityCard = " ";		/* 身份证*/
    private String educationBackground = " ";		/* 学历*/
    private String academicYear = " ";		/* 学年*/
    private String maritalStatus = " ";		/* 婚否:是，否*/
    private String deptNum = " ";		/* 部门科室编码*/
    private String deptName = " ";		/* 部门科室名称*/
    private String employmentNature = " ";		/* 职工性质*/
    private String cashPledge = " ";		/* 押金编号*/
    private String isNetwork = " ";		/* 是否联网*/
    private String isStay = " ";		/* 是否办暂住证*/
    private String permanentResidence = " ";		/* 户口所在地*/
    private String note = " ";		/* 备注信息*/
    private String hiredate = " ";		/* 入职时间*/
    private String estimatedInDate = " ";		/* 预计入住时间*/
    private String estimatedOutDate = " ";		/* 预计退房时间*/
    private String evalStatus = "0";		/* 是否已经进行过评价(0为未评价，1已评价)*/
    private String evalContent = " ";		/* 评价内容*/
    private String evalTime = " ";		/* 评价时间*/
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人工号*/
    private String recReviseName = " ";		/* 修改人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manName");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName("工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99已退宿)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sex");
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("age");
        eiColumn.setDescName("员工年龄");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("identityCard");
        eiColumn.setDescName("身份证");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("educationBackground");
        eiColumn.setDescName("学历");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("academicYear");
        eiColumn.setDescName("学年");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("maritalStatus");
        eiColumn.setDescName("婚否:是，否");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("部门科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("部门科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("employmentNature");
        eiColumn.setDescName("职工性质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cashPledge");
        eiColumn.setDescName("押金编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isNetwork");
        eiColumn.setDescName("是否联网");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isStay");
        eiColumn.setDescName("是否办暂住证");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("permanentResidence");
        eiColumn.setDescName("户口所在地");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("备注信息");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hiredate");
        eiColumn.setDescName("入职时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("estimatedInDate");
        eiColumn.setDescName("预计入住时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("estimatedOutDate");
        eiColumn.setDescName("预计退房时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalStatus");
        eiColumn.setDescName("是否已经进行过评价(0为未评价，1已评价)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTime");
        eiColumn.setDescName("评价时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("修改人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsMan() {
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
     * get the manNo - 工号
     * @return the manNo
     */
    public String getManNo() {
        return this.manNo;
    }

    /**
     * set the manNo - 工号
     */
    public void setManNo(String manNo) {
        this.manNo = manNo;
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
     * get the statusCode - 工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99已退宿)
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99已退宿)
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the sex - 性别
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * set the sex - 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * get the age - 员工年龄
     * @return the age
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * set the age - 员工年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * get the phone - 电话
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the identityCard - 身份证
     * @return the identityCard
     */
    public String getIdentityCard() {
        return this.identityCard;
    }

    /**
     * set the identityCard - 身份证
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * get the educationBackground - 学历
     * @return the educationBackground
     */
    public String getEducationBackground() {
        return this.educationBackground;
    }

    /**
     * set the educationBackground - 学历
     */
    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    /**
     * get the academicYear - 学年
     * @return the academicYear
     */
    public String getAcademicYear() {
        return this.academicYear;
    }

    /**
     * set the academicYear - 学年
     */
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    /**
     * get the maritalStatus - 婚否:是，否
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    /**
     * set the maritalStatus - 婚否:是，否
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * get the deptNum - 部门科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 部门科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 部门科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 部门科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the employmentNature - 职工性质
     * @return the employmentNature
     */
    public String getEmploymentNature() {
        return this.employmentNature;
    }

    /**
     * set the employmentNature - 职工性质
     */
    public void setEmploymentNature(String employmentNature) {
        this.employmentNature = employmentNature;
    }

    /**
     * get the cashPledge - 押金编号
     * @return the cashPledge
     */
    public String getCashPledge() {
        return this.cashPledge;
    }

    /**
     * set the cashPledge - 押金编号
     */
    public void setCashPledge(String cashPledge) {
        this.cashPledge = cashPledge;
    }

    /**
     * get the isNetwork - 是否联网
     * @return the isNetwork
     */
    public String getIsNetwork() {
        return this.isNetwork;
    }

    /**
     * set the isNetwork - 是否联网
     */
    public void setIsNetwork(String isNetwork) {
        this.isNetwork = isNetwork;
    }

    /**
     * get the isStay - 是否办暂住证
     * @return the isStay
     */
    public String getIsStay() {
        return this.isStay;
    }

    /**
     * set the isStay - 是否办暂住证
     */
    public void setIsStay(String isStay) {
        this.isStay = isStay;
    }

    /**
     * get the permanentResidence - 户口所在地
     * @return the permanentResidence
     */
    public String getPermanentResidence() {
        return this.permanentResidence;
    }

    /**
     * set the permanentResidence - 户口所在地
     */
    public void setPermanentResidence(String permanentResidence) {
        this.permanentResidence = permanentResidence;
    }

    /**
     * get the note - 备注信息
     * @return the note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set the note - 备注信息
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get the hiredate - 入职时间
     * @return the hiredate
     */
    public String getHiredate() {
        return this.hiredate;
    }

    /**
     * set the hiredate - 入职时间
     */
    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * get the estimatedInDate - 预计入住时间
     * @return the estimatedInDate
     */
    public String getEstimatedInDate() {
        return this.estimatedInDate;
    }

    /**
     * set the estimatedInDate - 预计入住时间
     */
    public void setEstimatedInDate(String estimatedInDate) {
        this.estimatedInDate = estimatedInDate;
    }

    /**
     * get the estimatedOutDate - 预计退房时间
     * @return the estimatedOutDate
     */
    public String getEstimatedOutDate() {
        return this.estimatedOutDate;
    }

    /**
     * set the estimatedOutDate - 预计退房时间
     */
    public void setEstimatedOutDate(String estimatedOutDate) {
        this.estimatedOutDate = estimatedOutDate;
    }

    /**
     * get the evalStatus - 是否已经进行过评价(0为未评价，1已评价)
     * @return the evalStatus
     */
    public String getEvalStatus() {
        return this.evalStatus;
    }

    /**
     * set the evalStatus - 是否已经进行过评价(0为未评价，1已评价)
     */
    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    /**
     * get the evalContent - 评价内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the evalTime - 评价时间
     * @return the evalTime
     */
    public String getEvalTime() {
        return this.evalTime;
    }

    /**
     * set the evalTime - 评价时间
     */
    public void setEvalTime(String evalTime) {
        this.evalTime = evalTime;
    }

    /**
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
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
     * get the recRevisor - 修改人工号
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人工号
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseName - 修改人姓名
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 修改人姓名
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manNo")), manNo));
        setManName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manName")), manName));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setSex(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sex")), sex));
        setAge(NumberUtils.toInteger(StringUtils.toString(map.get("age")), age));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setIdentityCard(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("identityCard")), identityCard));
        setEducationBackground(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("educationBackground")), educationBackground));
        setAcademicYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("academicYear")), academicYear));
        setMaritalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("maritalStatus")), maritalStatus));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setEmploymentNature(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("employmentNature")), employmentNature));
        setCashPledge(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cashPledge")), cashPledge));
        setIsNetwork(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isNetwork")), isNetwork));
        setIsStay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isStay")), isStay));
        setPermanentResidence(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("permanentResidence")), permanentResidence));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setHiredate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hiredate")), hiredate));
        setEstimatedInDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("estimatedInDate")), estimatedInDate));
        setEstimatedOutDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("estimatedOutDate")), estimatedOutDate));
        setEvalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalStatus")), evalStatus));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setEvalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTime")), evalTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manNo",StringUtils.toString(manNo, eiMetadata.getMeta("manNo")));
        map.put("manName",StringUtils.toString(manName, eiMetadata.getMeta("manName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("sex",StringUtils.toString(sex, eiMetadata.getMeta("sex")));
        map.put("age",StringUtils.toString(age, eiMetadata.getMeta("age")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("identityCard",StringUtils.toString(identityCard, eiMetadata.getMeta("identityCard")));
        map.put("educationBackground",StringUtils.toString(educationBackground, eiMetadata.getMeta("educationBackground")));
        map.put("academicYear",StringUtils.toString(academicYear, eiMetadata.getMeta("academicYear")));
        map.put("maritalStatus",StringUtils.toString(maritalStatus, eiMetadata.getMeta("maritalStatus")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("employmentNature",StringUtils.toString(employmentNature, eiMetadata.getMeta("employmentNature")));
        map.put("cashPledge",StringUtils.toString(cashPledge, eiMetadata.getMeta("cashPledge")));
        map.put("isNetwork",StringUtils.toString(isNetwork, eiMetadata.getMeta("isNetwork")));
        map.put("isStay",StringUtils.toString(isStay, eiMetadata.getMeta("isStay")));
        map.put("permanentResidence",StringUtils.toString(permanentResidence, eiMetadata.getMeta("permanentResidence")));
        map.put("note",StringUtils.toString(note, eiMetadata.getMeta("note")));
        map.put("hiredate",StringUtils.toString(hiredate, eiMetadata.getMeta("hiredate")));
        map.put("estimatedInDate",StringUtils.toString(estimatedInDate, eiMetadata.getMeta("estimatedInDate")));
        map.put("estimatedOutDate",StringUtils.toString(estimatedOutDate, eiMetadata.getMeta("estimatedOutDate")));
        map.put("evalStatus",StringUtils.toString(evalStatus, eiMetadata.getMeta("evalStatus")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("evalTime",StringUtils.toString(evalTime, eiMetadata.getMeta("evalTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}