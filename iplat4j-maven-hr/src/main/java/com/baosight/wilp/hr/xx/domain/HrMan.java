/**
* Generate time : 2022-03-18 12:44:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.xx.domain;

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
* HrMan
* 
*/
public class HrMan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String realName = " ";		/* 姓名*/
    private String sex = " ";		/* 性别：男、女*/
    private String birthPlace = " ";		/* 籍贯*/
    private String kampong = " ";		/* 民族*/
    private String manCode = " ";		/* 身份证*/
    private String maritalStatus = " ";/* 婚姻状况 */
    private BigDecimal salary = new BigDecimal("0");		/* 基本工资*/
    private String schoolingCode = " ";		/* 学历*/
    private String highestDegree = " ";    /* 最高学位 */
    private String highestEducational= " "; /*最高学历*/
    private String politicalStatus = " ";		/* 政治面貌*/
    private String birthDate = " ";		/* 出生年月*/
    private String phone = " ";		/* 联系电话*/
    private String workNo = " ";		/* 工号*/
    private String deptNum = " ";		/* 所属部门*/
    private String serviceDeptNum = " ";		/* 服务部门*/
    private String jobCode = " ";		/* 工作岗位*/
    private String health = " ";		/* 健康状况*/
    private String familyAddress = " ";		/* 家庭地址*/
    private String company = " ";		/* 公司*/
    private String memo = " ";		/* 备注*/
    private String preInTime = " ";		/* 预计入职时间*/
    private String emergency= " ";   /* 紧急联系人 */
    private String emergencyPhone = " "; /*紧急联系人电话 */
    private String managementDeptNum = " "; /*管理部门 */
    private String personnelCategory = " ";   /* 人员类别 */
    private String inTime = " ";		/* 入职时间*/
    private String preOutTime = " ";		/* 预计离职时间*/
    private String outTime = " ";		/* 离职日期*/
    private Timestamp createDate ;		/* 创建时间*/
    private String creatorName = " ";		/* 创建人*/
    private String creatorId = " ";		/* 创建人ID */
    private Timestamp updateDate ;		/* 更改时间*/
    private String updatorName = " ";		/* 更改人*/
    private String updatorId = " ";		/* 更改人ID*/
    private String operatorType = " ";		/* 最后操作类型*/

    private String statusCode = " ";		/* 状态(01-新建，02-入职,03-取消入职，04-离职)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("realName");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sex");
        eiColumn.setDescName("性别：男、女");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("birthPlace");
        eiColumn.setDescName("籍贯");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("kampong");
        eiColumn.setDescName("民族");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manCode");
        eiColumn.setDescName("身份证");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("maritalStatus");
        eiColumn.setDescName("婚姻状况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("salary");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("基本工资");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schoolingCode");
        eiColumn.setDescName("学历");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highestDegree");
        eiColumn.setDescName("最高学位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highestEducational");
        eiColumn.setDescName("最高学历");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("politicalStatus");
        eiColumn.setDescName("政治面貌");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("birthDate");
        eiColumn.setDescName("出生年月");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("managementDeptNum");
        eiColumn.setDescName("管理部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("所属部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptNum");
        eiColumn.setDescName("服务部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobCode");
        eiColumn.setDescName("工作岗位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("health");
        eiColumn.setDescName("健康状况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("familyAddress");
        eiColumn.setDescName("家庭地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("company");
        eiColumn.setDescName("公司");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("preInTime");
        eiColumn.setDescName("预计入职时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emergency");
        eiColumn.setDescName("紧急联系人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emergencyPhone");
        eiColumn.setDescName("紧急联系人电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("personnelCategory");
        eiColumn.setDescName("人员类别");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("inTime");
        eiColumn.setDescName("入职时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("preOutTime");
        eiColumn.setDescName("预计离职时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outTime");
        eiColumn.setDescName("离职日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人ID ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName("更改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorName");
        eiColumn.setDescName("更改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorId");
        eiColumn.setDescName("更改人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatorType");
        eiColumn.setDescName("最后操作类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态(01-新建，02-入职,03-取消入职，04-离职)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrMan() {
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
     * get the realName - 姓名
     * @return the realName
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * set the realName - 姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * get the sex - 性别：男、女
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * set the sex - 性别：男、女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * get the birthPlace - 籍贯
     * @return the birthPlace
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * set the birthPlace - 籍贯
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * get the kampong - 民族
     * @return the kampong
     */
    public String getKampong() {
        return this.kampong;
    }

    /**
     * set the kampong - 民族
     */
    public void setKampong(String kampong) {
        this.kampong = kampong;
    }

    /**
     * get the manCode - 身份证
     * @return the manCode
     */
    public String getManCode() {
        return this.manCode;
    }

    /**
     * set the manCode - 身份证
     */
    public void setManCode(String manCode) {
        this.manCode = manCode;
    }

    /**
     * get the maritalStatus - 婚姻状况
     * @return the maritalStatus
     */
    public String getMaritalStatus() {return this.maritalStatus;}

    /**
     * set the maritalStatus - 婚姻状况
     */
    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}

    /**
     * get the salary - 基本工资
     * @return the salary
     */
    public BigDecimal getSalary() {
        return this.salary;
    }

    /**
     * set the salary - 基本工资
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * get the schoolingCode - 学历
     * @return the schoolingCode
     */
    public String getSchoolingCode() {
        return this.schoolingCode;
    }

    /**
     * set the schoolingCode - 学历
     */
    public void setSchoolingCode(String schoolingCode) {
        this.schoolingCode = schoolingCode;
    }

    /**
     * get the highestDegree - 最高学位
     * @return the highestDegree
     */
    public String getHighestDegree() {return this.highestDegree;}

    /**
     * set the highestDegree - 最高学位
     */
    public void setHighestDegree(String highestDegree) { this.highestDegree = highestDegree;}

    /**
     * get the highestEducational - 最高学历
     * @return the highestEducational
     */
    public String getHighestEducational() { return this.highestEducational;}

    /**
     * set the highestEducational - 最高学历
     */
    public void setHighestEducational(String highestEducational) { this.highestEducational= highestEducational;}

    /**
     * get the politicalStatus - 政治面貌
     * @return the politicalStatus
     */
    public String getPoliticalStatus() {
        return this.politicalStatus;
    }

    /**
     * set the politicalStatus - 政治面貌
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * get the birthDate - 出生年月
     * @return the birthDate
     */
    public String getBirthDate() {
        return this.birthDate;
    }

    /**
     * set the birthDate - 出生年月
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * get the phone - 联系电话
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the workNo - 工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the deptNum - 所属部门
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 所属部门
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptNum - 所属部门
     * @return the deptNum
     */
    public String getManagementDeptNum() {
        return this.managementDeptNum;
    }

    /**
     * set the deptNum - 所属部门
     */
    public void setManagementDeptNum(String managementDeptNum) {
        this.managementDeptNum = managementDeptNum;
    }

    /**
     * get the serviceDeptNum - 服务部门
     * @return the serviceDeptNum
     */
    public String getServiceDeptNum() {
        return this.serviceDeptNum;
    }

    /**
     * set the serviceDeptNum - 服务部门
     */
    public void setServiceDeptNum(String serviceDeptNum) {
        this.serviceDeptNum = serviceDeptNum;
    }

    /**
     * get the jobCode - 工作岗位
     * @return the jobCode
     */
    public String getJobCode() {
        return this.jobCode;
    }

    /**
     * set the jobCode - 工作岗位
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * get the health - 健康状况
     * @return the health
     */
    public String getHealth() {
        return this.health;
    }

    /**
     * set the health - 健康状况
     */
    public void setHealth(String health) {
        this.health = health;
    }

    /**
     * get the familyAddress - 家庭地址
     * @return the familyAddress
     */
    public String getFamilyAddress() {
        return this.familyAddress;
    }

    /**
     * set the familyAddress - 家庭地址
     */
    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    /**
     * get the company - 公司
     * @return the company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * set the company - 公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the preInTime - 预计入职时间
     * @return the preInTime
     */
    public String getPreInTime() {
        return this.preInTime;
    }

    /**
     * set the preInTime - 预计入职时间
     */
    public void setPreInTime(String preInTime) {
        this.preInTime = preInTime;
    }

    /**
     * get the emergency - 紧急联系人
     * @return the emergency
     */
    public String getEmergency() {return this.emergency;}

    /**
     * set the  emergency - 紧急联系人
     */
    public void setEmergency(String emergency) {this.emergency = emergency;}

    /**
     * get the emergencyPhone - 紧急联系人电话
     * @return the emergencyPhone
     */
    public String getEmergencyPhone() {return this.emergencyPhone;}

    /**
     * set the  emergencyPhone - 紧急联系人电话
     */
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone;}

    /**
     * get the personnelCategory - 人员类别
     * @return the personnelCategory
     */
    public String getPersonnelCategory() {return this.personnelCategory;}

    /**
     * set the  personnelCategory - 人员类别
     */
    public void setPersonnelCategory(String personnelCategory) {this.personnelCategory = personnelCategory;}

    /**
     * get the inTime - 入职时间
     * @return the inTime
     */
    public String getInTime() {
        return this.inTime;
    }

    /**
     * set the inTime - 入职时间
     */
    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    /**
     * get the preOutTime - 预计离职时间
     * @return the preOutTime
     */
    public String getPreOutTime() {
        return this.preOutTime;
    }

    /**
     * set the preOutTime - 预计离职时间
     */
    public void setPreOutTime(String preOutTime) {
        this.preOutTime = preOutTime;
    }

    /**
     * get the outTime - 离职日期
     * @return the outTime
     */
    public String getOutTime() {
        return this.outTime;
    }

    /**
     * set the outTime - 离职日期
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    /**
     * get the createDate - 创建时间
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the creatorName - 创建人
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the creatorId - 创建人ID 
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人ID 
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the updateDate - 更改时间
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    /**
     * set the updateDate - 更改时间
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get the updatorName - 更改人
     * @return the updatorName
     */
    public String getUpdatorName() {
        return this.updatorName;
    }

    /**
     * set the updatorName - 更改人
     */
    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    /**
     * get the updatorId - 更改人ID
     * @return the updatorId
     */
    public String getUpdatorId() {
        return this.updatorId;
    }

    /**
     * set the updatorId - 更改人ID
     */
    public void setUpdatorId(String updatorId) {
        this.updatorId = updatorId;
    }

    /**
     * get the operatorType - 最后操作类型
     * @return the operatorType
     */
    public String getOperatorType() {
        return this.operatorType;
    }

    /**
     * set the operatorType - 最后操作类型
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }
    /**
     * get the operatorType - 最后操作类型
     * @return the operatorType
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the operatorType - 最后操作类型
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realName")), realName));
        setSex(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sex")), sex));
        setBirthPlace(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("birthPlace")), birthPlace));
        setKampong(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("kampong")), kampong));
        setManCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manCode")), manCode));
        setMaritalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("maritalStatus")), maritalStatus));
        setSalary(NumberUtils.toBigDecimal(StringUtils.toString(map.get("salary")), salary));
        setSchoolingCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schoolingCode")), schoolingCode));
        setHighestDegree(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highestDegree")), highestDegree));
        setHighestEducational(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highestEducational")), highestEducational));
        setPoliticalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("politicalStatus")), politicalStatus));
        setBirthDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("birthDate")), birthDate));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setServiceDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptNum")), serviceDeptNum));
        setServiceDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managementDeptNum")), managementDeptNum));
        setJobCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobCode")), jobCode));
        setHealth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("health")), health));
        setFamilyAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("familyAddress")), familyAddress));
        setCompany(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("company")), company));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setPreInTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("preInTime")), preInTime));
        setEmergency(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emergency")), emergency));
        setEmergencyPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emergencyPhone")), emergencyPhone));
        setPersonnelCategory(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("personnelCategory")), personnelCategory));
        setInTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inTime")), inTime));
        setPreOutTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("preOutTime")), preOutTime));
        setOutTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outTime")), outTime));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setUpdateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("updateDate"))));
        setUpdatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorName")), updatorName));
        setUpdatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorId")), updatorId));
        setOperatorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatorType")), operatorType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("realName",StringUtils.toString(realName, eiMetadata.getMeta("realName")));
        map.put("sex",StringUtils.toString(sex, eiMetadata.getMeta("sex")));
        map.put("birthPlace",StringUtils.toString(birthPlace, eiMetadata.getMeta("birthPlace")));
        map.put("kampong",StringUtils.toString(kampong, eiMetadata.getMeta("kampong")));
        map.put("manCode",StringUtils.toString(manCode, eiMetadata.getMeta("manCode")));
        map.put("maritalStatus",StringUtils.toString(maritalStatus, eiMetadata.getMeta("maritalStatus")));
        map.put("salary",StringUtils.toString(salary, eiMetadata.getMeta("salary")));
        map.put("schoolingCode",StringUtils.toString(schoolingCode, eiMetadata.getMeta("schoolingCode")));
        map.put("highestDegree",StringUtils.toString(highestDegree, eiMetadata.getMeta("highestDegree")));
        map.put("highestEducational",StringUtils.toString(highestEducational, eiMetadata.getMeta("highestEducational")));
        map.put("politicalStatus",StringUtils.toString(politicalStatus, eiMetadata.getMeta("politicalStatus")));
        map.put("birthDate",StringUtils.toString(birthDate, eiMetadata.getMeta("birthDate")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("serviceDeptNum",StringUtils.toString(serviceDeptNum, eiMetadata.getMeta("serviceDeptNum")));
        map.put("managementDeptNum",StringUtils.toString(managementDeptNum, eiMetadata.getMeta("managementDeptNum")));
        map.put("jobCode",StringUtils.toString(jobCode, eiMetadata.getMeta("jobCode")));
        map.put("health",StringUtils.toString(health, eiMetadata.getMeta("health")));
        map.put("familyAddress",StringUtils.toString(familyAddress, eiMetadata.getMeta("familyAddress")));
        map.put("company",StringUtils.toString(company, eiMetadata.getMeta("company")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("preInTime",StringUtils.toString(preInTime, eiMetadata.getMeta("preInTime")));
        map.put("emergency",StringUtils.toString(emergency, eiMetadata.getMeta("emergency")));
        map.put("emergencyPhone",StringUtils.toString(emergencyPhone, eiMetadata.getMeta("emergencyPhone")));
        map.put("personnelCategory",StringUtils.toString(personnelCategory, eiMetadata.getMeta("personnelCategory")));
        map.put("inTime",StringUtils.toString(inTime, eiMetadata.getMeta("inTime")));
        map.put("preOutTime",StringUtils.toString(preOutTime, eiMetadata.getMeta("preOutTime")));
        map.put("outTime",StringUtils.toString(outTime, eiMetadata.getMeta("outTime")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("updateDate",StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("updatorName",StringUtils.toString(updatorName, eiMetadata.getMeta("updatorName")));
        map.put("updatorId",StringUtils.toString(updatorId, eiMetadata.getMeta("updatorId")));
        map.put("operatorType",StringUtils.toString(operatorType, eiMetadata.getMeta("operatorType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        return map;
    }
}