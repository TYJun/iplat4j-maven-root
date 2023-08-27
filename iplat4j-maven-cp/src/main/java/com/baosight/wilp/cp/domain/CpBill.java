/**
 * Generate time : 2022-03-21 17:22:39
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.cp.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * CpBill
 *
 */
public class CpBill extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id;		/* 主键*/
    private String billNo;		/* 投诉单号*/
    private String recCreator;		/* 登记人工号*/
    private String recCreateName;		/* 创建人名字*/
    private String recCreateTime ;		/* 登记时间*/
    private String recRevisor;		/* 修改人工号*/
    private String recReviseName;		/* 修改人名字*/
    private String recReviseTime ;		/* 修改时间*/
    private String statusCode;		/* 单子状态，01/已登记,02/已处理,03/已回访，98/已作废,99/结束*/
    private String complaintDate;		/* 投诉日期*/
    private String complaintName;		/* 投诉人名字*/
    private String complaintPhone;		/* 投诉人电话*/
    private String complaintDept;		/* 投诉人的部门*/
    private String complaintEmail;		/* 投诉人邮箱*/
    private String complaintType;		/* 投诉所属类型，监管类，病床类，患者类*/
    private String complaintWay;		/* 投诉方式，电话，信访，来访*/
    private String complaintContent;		/* 投诉内容*/
    private String teskEval;		/* 评价等级 （5很满意、4满意、3一般、2较差、1很差)*/
    private String evaluateContent;
    private String isDeal;  //是否还要处理
    private String deptNum;		/* 科室编码*/
    private String deptName;		/* 科室名称*/
    private String workNo;		/* 投诉人工号*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("投诉单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("登记人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("登记时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("修改人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("单子状态，01/已登记,02/已处理,03/已回访，98/已作废,99/结束");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintDate");
        eiColumn.setDescName("投诉日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintName");
        eiColumn.setDescName("投诉人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintPhone");
        eiColumn.setDescName("投诉人电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintDept");
        eiColumn.setDescName("投诉人的部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintEmail");
        eiColumn.setDescName("投诉人邮箱");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintType");
        eiColumn.setDescName("投诉所属类型，监管类，病床类，患者类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintWay");
        eiColumn.setDescName("投诉方式，电话，信访，来访");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("complaintContent");
        eiColumn.setDescName("投诉内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("teskEval");
        eiColumn.setDescName("评价等级 （5很满意、4满意、3一般、2较差、1很差)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evaluateContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isDeal");
        eiColumn.setDescName("是否还要处理");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("投诉人工号");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public CpBill() {
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
     * get the billNo - 投诉单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 投诉单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the recCreator - 登记人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 登记人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人名字
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人名字
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recCreateTime - 登记时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 登记时间
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
     * get the recReviseName - 修改人名字
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 修改人名字
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
     * get the statusCode - 单子状态，01/已登记,02/已处理,03/已回访，98/已作废,99/结束
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 单子状态，01/已登记,02/已处理,03/已回访，98/已作废,99/结束
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the complaintDate - 投诉日期
     * @return the complaintDate
     */
    public String getComplaintDate() {
        return this.complaintDate;
    }

    /**
     * set the complaintDate - 投诉日期
     */
    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    /**
     * get the complaintName - 投诉人名字
     * @return the complaintName
     */
    public String getComplaintName() {
        return this.complaintName;
    }

    /**
     * set the complaintName - 投诉人名字
     */
    public void setComplaintName(String complaintName) {
        this.complaintName = complaintName;
    }

    /**
     * get the complaintPhone - 投诉人电话
     * @return the complaintPhone
     */
    public String getComplaintPhone() {
        return this.complaintPhone;
    }

    /**
     * set the complaintPhone - 投诉人电话
     */
    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone;
    }

    /**
     * get the complaintDept - 投诉人的部门
     * @return the complaintDept
     */
    public String getComplaintDept() {
        return this.complaintDept;
    }

    /**
     * set the complaintDept - 投诉人的部门
     */
    public void setComplaintDept(String complaintDept) {
        this.complaintDept = complaintDept;
    }

    /**
     * get the complaintEmail - 投诉人邮箱
     * @return the complaintEmail
     */
    public String getComplaintEmail() {
        return this.complaintEmail;
    }

    /**
     * set the complaintEmail - 投诉人邮箱
     */
    public void setComplaintEmail(String complaintEmail) {
        this.complaintEmail = complaintEmail;
    }

    /**
     * get the complaintType - 投诉所属类型，监管类，病床类，患者类
     * @return the complaintType
     */
    public String getComplaintType() {
        return this.complaintType;
    }

    /**
     * set the complaintType - 投诉所属类型，监管类，病床类，患者类
     */
    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    /**
     * get the complaintWay - 投诉方式，电话，信访，来访
     * @return the complaintWay
     */
    public String getComplaintWay() {
        return this.complaintWay;
    }

    /**
     * set the complaintWay - 投诉方式，电话，信访，来访
     */
    public void setComplaintWay(String complaintWay) {
        this.complaintWay = complaintWay;
    }

    /**
     * get the complaintContent - 投诉内容
     * @return the complaintContent
     */
    public String getComplaintContent() {
        return this.complaintContent;
    }

    /**
     * set the complaintContent - 投诉内容
     */
    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    /**
     * get the taskEval - 评价等级 （5很满意、4满意、3一般、2较差、1很差)
     * @return the taskEval
     */
    public String getTeskEval() {
        return this.teskEval;
    }

    /**
     * set the taskEval - 评价等级 （5很满意、4满意、3一般、2较差、1很差)
     */
    public void setTeskEval(String teskEval) {
        this.teskEval = teskEval;
    }


    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(String isDeal) {
        this.isDeal = isDeal;
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
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the workNo - 科室名称
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 科室名称
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setComplaintDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintDate")), complaintDate));
        setComplaintName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintName")), complaintName));
        setComplaintPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintPhone")), complaintPhone));
        setComplaintDept(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintDept")), complaintDept));
        setComplaintEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintEmail")), complaintEmail));
        setComplaintType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintType")), complaintType));
        setComplaintWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintWay")), complaintWay));
        setComplaintContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("complaintContent")), complaintContent));
        setTeskEval(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("teskEval")), teskEval));
        setEvaluateContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evaluateContent")), evaluateContent));
        setIsDeal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isDeal")), isDeal));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("complaintDate",StringUtils.toString(complaintDate, eiMetadata.getMeta("complaintDate")));
        map.put("complaintName",StringUtils.toString(complaintName, eiMetadata.getMeta("complaintName")));
        map.put("complaintPhone",StringUtils.toString(complaintPhone, eiMetadata.getMeta("complaintPhone")));
        map.put("complaintDept",StringUtils.toString(complaintDept, eiMetadata.getMeta("complaintDept")));
        map.put("complaintEmail",StringUtils.toString(complaintEmail, eiMetadata.getMeta("complaintEmail")));
        map.put("complaintType",StringUtils.toString(complaintType, eiMetadata.getMeta("complaintType")));
        map.put("complaintWay",StringUtils.toString(complaintWay, eiMetadata.getMeta("complaintWay")));
        map.put("complaintContent",StringUtils.toString(complaintContent, eiMetadata.getMeta("complaintContent")));
        map.put("teskEval",StringUtils.toString(teskEval, eiMetadata.getMeta("teskEval")));
        map.put("evaluateContent",StringUtils.toString(evaluateContent, eiMetadata.getMeta("evaluateContent")));
        map.put("isDeal",StringUtils.toString(evaluateContent, eiMetadata.getMeta("isDeal")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        return map;
    }
}