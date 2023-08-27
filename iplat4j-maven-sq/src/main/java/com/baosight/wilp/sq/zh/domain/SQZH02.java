/**
* Generate time : 2021-08-04 9:51:25
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.zh.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SqManageProject
* 
*/
public class SQZH02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String projectCode = " ";		/* 项目编码*/
    private String projectName = " ";		/* 项目名称*/
    private String manageId = " ";		/* 项目管理外键*/
    private Integer point = new Integer(0);		/* 满分*/
    private Integer score = new Integer(0);		/* 得分*/
    private String reason = " ";		/* 扣分原因*/
    private String instanceCode = " ";		/* 检查标准编码*/
    private String instanceName = " ";		/* 检查标准名称*/
    private String creator = " ";		/* 记录创建人*/
    private Timestamp createTime ;		/* 记录创建时间*/
    private String modifier = " ";		/* 记录修改人*/
    private Timestamp modifyTime ;		/* 记录修改时间*/
    private Integer orderNumber = new Integer(0);		/* 菜单顺序*/
    private String workNo = " ";		/* 打分人*/
    private String billNo = " ";		/* 单据号*/
    private String idea = " ";		/* 意见*/
    private String advice = " ";		/* 建议*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectCode");
        eiColumn.setDescName("项目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectName");
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageId");
        eiColumn.setDescName("项目管理外键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("point");
        eiColumn.setDescName("满分");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("score");
        eiColumn.setDescName("得分");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reason");
        eiColumn.setDescName("扣分原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("instanceCode");
        eiColumn.setDescName("检查标准编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("instanceName");
        eiColumn.setDescName("检查标准名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("记录创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("记录修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNumber");
        eiColumn.setDescName("菜单顺序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("打分人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idea");
        eiColumn.setDescName("意见");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("advice");
        eiColumn.setDescName("建议");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SQZH02() {
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
     * get the projectCode - 项目编码
     * @return the projectCode
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * set the projectCode - 项目编码
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * get the projectName - 项目名称
     * @return the projectName
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * set the projectName - 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * get the manageId - 项目管理外键
     * @return the manageId
     */
    public String getManageId() {
        return this.manageId;
    }

    /**
     * set the manageId - 项目管理外键
     */
    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

    /**
     * get the point - 满分
     * @return the point
     */
    public Integer getPoint() {
        return this.point;
    }

    /**
     * set the point - 满分
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * get the score - 得分
     * @return the score
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * set the score - 得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * get the reason - 扣分原因
     * @return the reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * set the reason - 扣分原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * get the instanceCode - 检查标准编码
     * @return the instanceCode
     */
    public String getInstanceCode() {
        return this.instanceCode;
    }

    /**
     * set the instanceCode - 检查标准编码
     */
    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    /**
     * get the instanceName - 检查标准名称
     * @return the instanceName
     */
    public String getInstanceName() {
        return this.instanceName;
    }

    /**
     * set the instanceName - 检查标准名称
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * get the creator - 记录创建人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 记录创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createTime - 记录创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 记录创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the modifier - 记录修改人
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier - 记录修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the modifyTime - 记录修改时间
     * @return the modifyTime
     */
    public Timestamp getModifyTime() {
        return this.modifyTime;
    }

    /**
     * set the modifyTime - 记录修改时间
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * get the orderNumber - 菜单顺序
     * @return the orderNumber
     */
    public Integer getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * set the orderNumber - 菜单顺序
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * get the workNo - 打分人
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 打分人
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the billNo - 单据号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 单据号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the idea - 意见
     * @return the idea
     */
    public String getIdea() {
        return this.idea;
    }

    /**
     * set the idea - 意见
     */
    public void setIdea(String idea) {
        this.idea = idea;
    }

    /**
     * get the advice - 建议
     * @return the advice
     */
    public String getAdvice() {
        return this.advice;
    }

    /**
     * set the advice - 建议
     */
    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setProjectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectCode")), projectCode));
        setProjectName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectName")), projectName));
        setManageId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manageId")), manageId));
        setPoint(NumberUtils.toInteger(StringUtils.toString(map.get("point")), point));
        setScore(NumberUtils.toInteger(StringUtils.toString(map.get("score")), score));
        setReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reason")), reason));
        setInstanceCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceCode")), instanceCode));
        setInstanceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceName")), instanceName));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setModifyTime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifyTime"))));
        setOrderNumber(NumberUtils.toInteger(StringUtils.toString(map.get("orderNumber")), orderNumber));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setIdea(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idea")), idea));
        setAdvice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("advice")), advice));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectCode",StringUtils.toString(projectCode, eiMetadata.getMeta("projectCode")));
        map.put("projectName",StringUtils.toString(projectName, eiMetadata.getMeta("projectName")));
        map.put("manageId",StringUtils.toString(manageId, eiMetadata.getMeta("manageId")));
        map.put("point",StringUtils.toString(point, eiMetadata.getMeta("point")));
        map.put("score",StringUtils.toString(score, eiMetadata.getMeta("score")));
        map.put("reason",StringUtils.toString(reason, eiMetadata.getMeta("reason")));
        map.put("instanceCode",StringUtils.toString(instanceCode, eiMetadata.getMeta("instanceCode")));
        map.put("instanceName",StringUtils.toString(instanceName, eiMetadata.getMeta("instanceName")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifier",StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("modifyTime",StringUtils.toString(modifyTime, eiMetadata.getMeta("modifyTime")));
        map.put("orderNumber",StringUtils.toString(orderNumber, eiMetadata.getMeta("orderNumber")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("idea",StringUtils.toString(idea, eiMetadata.getMeta("idea")));
        map.put("advice",StringUtils.toString(advice, eiMetadata.getMeta("advice")));
        return map;
    }
}