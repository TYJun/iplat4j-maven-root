/**
* Generate time : 2021-03-03 17:31:14
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* TYRY01
* tbmbd01
* tbmbd02
*/
public class SSBMTyRy01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreater = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String id = " ";		/* 主键*/
    private String workNo = " ";		/* 工号*/
    private String name = " ";		/* 姓名*/
    private String deptNum = " ";		/* 科室编码*/
    private String deptName = " ";		/* 科室编码*/
    private String gender = " ";		/* 性别*/
    private String contactTel = " ";		/* 联系电话*/
    private String position = " ";		/* 岗位*/
    private String chiefFlag = " ";		/* 负责人*/
    private String taskTranStaff = " ";		/* 任务转出接收人*/
    private String serviceStaff = " ";		/* 服务人员*/
    private String staffType = " ";		/* 员工类型*/
    private String staffStatus = " ";		/* 状态*/
    private String effectiveDate = " ";		/* 有效期*/
    private String jpName = " ";		/* 员工姓名简拼*/
    private String picUrl = " ";		/* 员工照片*/
    private String stopFlag = " ";		
    private String serviceType = " ";		
    private String idNo = " ";		/* 身份证号*/
    private String worktype = " ";		/* 员工工种*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreater");
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

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gender");
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contactTel");
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("position");
        eiColumn.setDescName("岗位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("chiefFlag");
        eiColumn.setDescName("负责人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskTranStaff");
        eiColumn.setDescName("任务转出接收人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceStaff");
        eiColumn.setDescName("服务人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("staffType");
        eiColumn.setDescName("员工类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("staffStatus");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("effectiveDate");
        eiColumn.setDescName("有效期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpName");
        eiColumn.setDescName("员工姓名简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("picUrl");
        eiColumn.setDescName("员工照片");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idNo");
        eiColumn.setDescName("身份证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("worktype");
        eiColumn.setDescName("员工工种");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMTyRy01() {
        initMetaData();
    }

    /**
     * get the recCreater - 创建人
     * @return the recCreater
     */
    public String getRecCreater() {
        return this.recCreater;
    }

    /**
     * set the recCreater - 创建人
     */
    public void setRecCreater(String recCreater) {
        this.recCreater = recCreater;
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
     * get the name - 姓名
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 姓名
     */
    public void setName(String name) {
        this.name = name;
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

    
    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
     * get the gender - 性别
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * set the gender - 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get the contactTel - 联系电话
     * @return the contactTel
     */
    public String getContactTel() {
        return this.contactTel;
    }

    /**
     * set the contactTel - 联系电话
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * get the position - 岗位
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position - 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * get the chiefFlag - 负责人
     * @return the chiefFlag
     */
    public String getChiefFlag() {
        return this.chiefFlag;
    }

    /**
     * set the chiefFlag - 负责人
     */
    public void setChiefFlag(String chiefFlag) {
        this.chiefFlag = chiefFlag;
    }

    /**
     * get the taskTranStaff - 任务转出接收人
     * @return the taskTranStaff
     */
    public String getTaskTranStaff() {
        return this.taskTranStaff;
    }

    /**
     * set the taskTranStaff - 任务转出接收人
     */
    public void setTaskTranStaff(String taskTranStaff) {
        this.taskTranStaff = taskTranStaff;
    }

    /**
     * get the serviceStaff - 服务人员
     * @return the serviceStaff
     */
    public String getServiceStaff() {
        return this.serviceStaff;
    }

    /**
     * set the serviceStaff - 服务人员
     */
    public void setServiceStaff(String serviceStaff) {
        this.serviceStaff = serviceStaff;
    }

    /**
     * get the staffType - 员工类型
     * @return the staffType
     */
    public String getStaffType() {
        return this.staffType;
    }

    /**
     * set the staffType - 员工类型
     */
    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    /**
     * get the staffStatus - 状态
     * @return the staffStatus
     */
    public String getStaffStatus() {
        return this.staffStatus;
    }

    /**
     * set the staffStatus - 状态
     */
    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    /**
     * get the effectiveDate - 有效期
     * @return the effectiveDate
     */
    public String getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * set the effectiveDate - 有效期
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * get the jpName - 员工姓名简拼
     * @return the jpName
     */
    public String getJpName() {
        return this.jpName;
    }

    /**
     * set the jpName - 员工姓名简拼
     */
    public void setJpName(String jpName) {
        this.jpName = jpName;
    }

    /**
     * get the picUrl - 员工照片
     * @return the picUrl
     */
    public String getPicUrl() {
        return this.picUrl;
    }

    /**
     * set the picUrl - 员工照片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
     * get the serviceType 
     * @return the serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * set the serviceType 
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * get the idNo - 身份证号
     * @return the idNo
     */
    public String getIdNo() {
        return this.idNo;
    }

    /**
     * set the idNo - 身份证号
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * get the worktype - 员工工种
     * @return the worktype
     */
    public String getWorktype() {
        return this.worktype;
    }

    /**
     * set the worktype - 员工工种
     */
    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreater(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreater")), recCreater));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setGender(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gender")), gender));
        setContactTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contactTel")), contactTel));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setChiefFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("chiefFlag")), chiefFlag));
        setTaskTranStaff(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskTranStaff")), taskTranStaff));
        setServiceStaff(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceStaff")), serviceStaff));
        setStaffType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("staffType")), staffType));
        setStaffStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("staffStatus")), staffStatus));
        setEffectiveDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("effectiveDate")), effectiveDate));
        setJpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpName")), jpName));
        setPicUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("picUrl")), picUrl));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setServiceType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceType")), serviceType));
        setIdNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idNo")), idNo));
        setWorktype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("worktype")), worktype));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreater",StringUtils.toString(recCreater, eiMetadata.getMeta("recCreater")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("gender",StringUtils.toString(gender, eiMetadata.getMeta("gender")));
        map.put("contactTel",StringUtils.toString(contactTel, eiMetadata.getMeta("contactTel")));
        map.put("position",StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("chiefFlag",StringUtils.toString(chiefFlag, eiMetadata.getMeta("chiefFlag")));
        map.put("taskTranStaff",StringUtils.toString(taskTranStaff, eiMetadata.getMeta("taskTranStaff")));
        map.put("serviceStaff",StringUtils.toString(serviceStaff, eiMetadata.getMeta("serviceStaff")));
        map.put("staffType",StringUtils.toString(staffType, eiMetadata.getMeta("staffType")));
        map.put("staffStatus",StringUtils.toString(staffStatus, eiMetadata.getMeta("staffStatus")));
        map.put("effectiveDate",StringUtils.toString(effectiveDate, eiMetadata.getMeta("effectiveDate")));
        map.put("jpName",StringUtils.toString(jpName, eiMetadata.getMeta("jpName")));
        map.put("picUrl",StringUtils.toString(picUrl, eiMetadata.getMeta("picUrl")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("serviceType",StringUtils.toString(serviceType, eiMetadata.getMeta("serviceType")));
        map.put("idNo",StringUtils.toString(idNo, eiMetadata.getMeta("idNo")));
        map.put("worktype",StringUtils.toString(worktype, eiMetadata.getMeta("worktype")));
        return map;
    }
}