/**
* Generate time : 2021-05-04 18:25:14
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.hm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DMHM01
* 
*/
public class DMHM01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String employeeId = " ";		/* 工号*/
    private String manName = " ";		/* 姓名*/
    private String sexCode = " ";		/* 性别*/
    private String degreeCode = " ";		/* 学位*/
    private String departmentNo = " ";		/* 部门科室*/
    private String employmentNature = " ";		/* 用工性质*/
    private String phone = " ";		/* 电话*/
    private String deposit = " ";		/* 押金编号*/
    private Integer isNetwork = new Integer(0);		/* 是否联网*/
    private Integer isStay = new Integer(0);		/* 是否办暂住证*/
    private String createName = " ";		/* 记录创建人姓名*/
    private Timestamp createDate ;		/* 记录创建时间*/
    private String updateName = " ";		/* 记录最后修改人姓名*/
    private Timestamp updateDate ;		/* 记录最后修改时间*/
    private String operatorType = " ";		/* 最后操作情况：I=插入，U=修改，D=删除*/
    private String note = " ";		/* 备注*/
    private Integer age = new Integer(0);		/* 员工年龄*/
    private String ifMarried = " ";		/* 婚否:是，否*/
    private String permanentResidence = " ";		/* 户口所在地*/
    private Date workTime;	/* 入职时间*/
    private Integer ifReview = new Integer(0);		/* 是否通过审核：-2:新增：-1审核中；1：审核通过；-3：申请被驳回，不通过*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("employeeId");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manName");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sexCode");
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("degreeCode");
        eiColumn.setDescName("学位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentNo");
        eiColumn.setDescName("部门科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("employmentNature");
        eiColumn.setDescName("用工性质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deposit");
        eiColumn.setDescName("押金编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isNetwork");
        eiColumn.setDescName("是否联网");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isStay");
        eiColumn.setDescName("是否办暂住证");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createName");
        eiColumn.setDescName("记录创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateName");
        eiColumn.setDescName("记录最后修改人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName("记录最后修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatorType");
        eiColumn.setDescName("最后操作情况：I=插入，U=修改，D=删除");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("age");
        eiColumn.setDescName("员工年龄");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ifMarried");
        eiColumn.setDescName("婚否:是，否");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("permanentResidence");
        eiColumn.setDescName("户口所在地");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workTime");
        eiColumn.setDescName("入职时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ifReview");
        eiColumn.setDescName("是否通过审核：-2:新增：-1审核中；1：审核通过；-3：申请被驳回，不通过");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DMHM01() {
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
     * get the sexCode - 性别
     * @return the sexCode
     */
    public String getSexCode() {
        return this.sexCode;
    }

    /**
     * set the sexCode - 性别
     */
    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    /**
     * get the degreeCode - 学位
     * @return the degreeCode
     */
    public String getDegreeCode() {
        return this.degreeCode;
    }

    /**
     * set the degreeCode - 学位
     */
    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    /**
     * get the departmentNo - 部门科室
     * @return the departmentNo
     */
    public String getDepartmentNo() {
        return this.departmentNo;
    }

    /**
     * set the departmentNo - 部门科室
     */
    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    /**
     * get the employmentNature - 用工性质
     * @return the employmentNature
     */
    public String getEmploymentNature() {
        return this.employmentNature;
    }

    /**
     * set the employmentNature - 用工性质
     */
    public void setEmploymentNature(String employmentNature) {
        this.employmentNature = employmentNature;
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
     * get the deposit - 押金编号
     * @return the deposit
     */
    public String getDeposit() {
        return this.deposit;
    }

    /**
     * set the deposit - 押金编号
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    /**
     * get the isNetwork - 是否联网
     * @return the isNetwork
     */
    public Integer getIsNetwork() {
        return this.isNetwork;
    }

    /**
     * set the isNetwork - 是否联网
     */
    public void setIsNetwork(Integer isNetwork) {
        this.isNetwork = isNetwork;
    }

    /**
     * get the isStay - 是否办暂住证
     * @return the isStay
     */
    public Integer getIsStay() {
        return this.isStay;
    }

    /**
     * set the isStay - 是否办暂住证
     */
    public void setIsStay(Integer isStay) {
        this.isStay = isStay;
    }

    /**
     * get the createName - 记录创建人姓名
     * @return the createName
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * set the createName - 记录创建人姓名
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * get the createDate - 记录创建时间
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 记录创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the updateName - 记录最后修改人姓名
     * @return the updateName
     */
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * set the updateName - 记录最后修改人姓名
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * get the updateDate - 记录最后修改时间
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    /**
     * set the updateDate - 记录最后修改时间
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get the operatorType - 最后操作情况：I=插入，U=修改，D=删除
     * @return the operatorType
     */
    public String getOperatorType() {
        return this.operatorType;
    }

    /**
     * set the operatorType - 最后操作情况：I=插入，U=修改，D=删除
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
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
     * get the ifMarried - 婚否:是，否
     * @return the ifMarried
     */
    public String getIfMarried() {
        return this.ifMarried;
    }

    /**
     * set the ifMarried - 婚否:是，否
     */
    public void setIfMarried(String ifMarried) {
        this.ifMarried = ifMarried;
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
     * get the workTime - 入职时间
     * @return the workTime
     */
    public Date getWorkTime() {
        return this.workTime;
    }

    /**
     * set the workTime - 入职时间
     */
    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    /**
     * get the ifReview - 是否通过审核：-2:新增：-1审核中；1：审核通过；-3：申请被驳回，不通过
     * @return the ifReview
     */
    public Integer getIfReview() {
        return this.ifReview;
    }

    /**
     * set the ifReview - 是否通过审核：-2:新增：-1审核中；1：审核通过；-3：申请被驳回，不通过
     */
    public void setIfReview(Integer ifReview) {
        this.ifReview = ifReview;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setEmployeeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("employeeId")), employeeId));
        setManName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manName")), manName));
        setSexCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sexCode")), sexCode));
        setDegreeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("degreeCode")), degreeCode));
        setDepartmentNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentNo")), departmentNo));
        setEmploymentNature(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("employmentNature")), employmentNature));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setDeposit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deposit")), deposit));
        setIsNetwork(NumberUtils.toInteger(StringUtils.toString(map.get("isNetwork")), isNetwork));
        setIsStay(NumberUtils.toInteger(StringUtils.toString(map.get("isStay")), isStay));
        setCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createName")), createName));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setUpdateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateName")), updateName));
        setUpdateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("updateDate"))));
        setOperatorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatorType")), operatorType));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setAge(NumberUtils.toInteger(StringUtils.toString(map.get("age")), age));
        setIfMarried(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ifMarried")), ifMarried));
        setPermanentResidence(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("permanentResidence")), permanentResidence));
        setWorkTime(DateUtils.toDate(StringUtils.toString(map.get("workTime"))));
        setIfReview(NumberUtils.toInteger(StringUtils.toString(map.get("ifReview")), ifReview));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("employeeId",StringUtils.toString(employeeId, eiMetadata.getMeta("employeeId")));
        map.put("manName",StringUtils.toString(manName, eiMetadata.getMeta("manName")));
        map.put("sexCode",StringUtils.toString(sexCode, eiMetadata.getMeta("sexCode")));
        map.put("degreeCode",StringUtils.toString(degreeCode, eiMetadata.getMeta("degreeCode")));
        map.put("departmentNo",StringUtils.toString(departmentNo, eiMetadata.getMeta("departmentNo")));
        map.put("employmentNature",StringUtils.toString(employmentNature, eiMetadata.getMeta("employmentNature")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("deposit",StringUtils.toString(deposit, eiMetadata.getMeta("deposit")));
        map.put("isNetwork",StringUtils.toString(isNetwork, eiMetadata.getMeta("isNetwork")));
        map.put("isStay",StringUtils.toString(isStay, eiMetadata.getMeta("isStay")));
        map.put("createName",StringUtils.toString(createName, eiMetadata.getMeta("createName")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("updateName",StringUtils.toString(updateName, eiMetadata.getMeta("updateName")));
        map.put("updateDate",StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("operatorType",StringUtils.toString(operatorType, eiMetadata.getMeta("operatorType")));
        map.put("note",StringUtils.toString(note, eiMetadata.getMeta("note")));
        map.put("age",StringUtils.toString(age, eiMetadata.getMeta("age")));
        map.put("ifMarried",StringUtils.toString(ifMarried, eiMetadata.getMeta("ifMarried")));
        map.put("permanentResidence",StringUtils.toString(permanentResidence, eiMetadata.getMeta("permanentResidence")));
        map.put("workTime",StringUtils.toString(workTime, eiMetadata.getMeta("workTime")));
        map.put("ifReview",StringUtils.toString(ifReview, eiMetadata.getMeta("ifReview")));
        return map;
    }
}