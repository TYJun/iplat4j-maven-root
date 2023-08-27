/**
* Generate time : 2021-07-19 14:09:36
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.bz.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 考核主题实体类
 * 
 * @Title: SQBZ01.java
 * @ClassName: SQBZ01
 * @Package：com.baosight.wilp.sq.bz.domain
 * @author: zhangjiaqian
 * @date: 2021年7月19日 下午2:10:56
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class SQBZ01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键，UUID*/
    private String standardCode = " ";		/* 标准编码*/
    private String standardName = " ";		/* 标准名称*/
    private String remark = " ";		/* 备注*/
    private String creator = " ";		/* 记录创建人*/
    private Timestamp createTime ;		/* 创建时间*/
    private String modifier = " ";		/* 记录修改人*/
    private Timestamp modifyTime ;		/* 记录修改时间*/
    private String assessDeptName = " ";		/* 调查对象*/
    private String assessWorkNoLeader = " ";		/* 评价负责人工号*/
    private String assessWorkNameLeader = " ";		/* 评价负责人名*/
    private String assessContactTel = " ";		/* 负责人电话*/
    private String assessMail = " ";		/* 负责人邮箱*/
    private String assessTypeCode = " ";		/* 问卷类型编码*/
    private String assessTypeName = " ";		/* 问卷类型名*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键，UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardCode");
        eiColumn.setDescName("标准编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardName");
        eiColumn.setDescName("标准名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("记录创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("记录修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessDeptName");
        eiColumn.setDescName("调查对象");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessWorkNoLeader");
        eiColumn.setDescName("评价负责人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessWorkNameLeader");
        eiColumn.setDescName("评价负责人名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessContactTel");
        eiColumn.setDescName("负责人电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessMail");
        eiColumn.setDescName("负责人邮箱");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessTypeCode");
        eiColumn.setDescName("问卷类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("assessTypeName");
        eiColumn.setDescName("问卷类型名");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SQBZ01() {
        initMetaData();
    }

    /**
     * get the id - 主键，UUID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键，UUID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the standardCode - 标准编码
     * @return the standardCode
     */
    public String getStandardCode() {
        return this.standardCode;
    }

    /**
     * set the standardCode - 标准编码
     */
    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    /**
     * get the standardName - 标准名称
     * @return the standardName
     */
    public String getStandardName() {
        return this.standardName;
    }

    /**
     * set the standardName - 标准名称
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName;
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
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
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
     * get the assessDeptName - 调查对象
     * @return the assessDeptName
     */
    public String getAssessDeptName() {
        return this.assessDeptName;
    }

    /**
     * set the assessDeptName - 调查对象
     */
    public void setAssessDeptName(String assessDeptName) {
        this.assessDeptName = assessDeptName;
    }

    /**
     * get the assessWorkNoLeader - 评价负责人工号
     * @return the assessWorkNoLeader
     */
    public String getAssessWorkNoLeader() {
        return this.assessWorkNoLeader;
    }

    /**
     * set the assessWorkNoLeader - 评价负责人工号
     */
    public void setAssessWorkNoLeader(String assessWorkNoLeader) {
        this.assessWorkNoLeader = assessWorkNoLeader;
    }

    /**
     * get the assessWorkNameLeader - 评价负责人名
     * @return the assessWorkNameLeader
     */
    public String getAssessWorkNameLeader() {
        return this.assessWorkNameLeader;
    }

    /**
     * set the assessWorkNameLeader - 评价负责人名
     */
    public void setAssessWorkNameLeader(String assessWorkNameLeader) {
        this.assessWorkNameLeader = assessWorkNameLeader;
    }

    /**
     * get the assessContactTel - 负责人电话
     * @return the assessContactTel
     */
    public String getAssessContactTel() {
        return this.assessContactTel;
    }

    /**
     * set the assessContactTel - 负责人电话
     */
    public void setAssessContactTel(String assessContactTel) {
        this.assessContactTel = assessContactTel;
    }

    /**
     * get the assessMail - 负责人邮箱
     * @return the assessMail
     */
    public String getAssessMail() {
        return this.assessMail;
    }

    /**
     * set the assessMail - 负责人邮箱
     */
    public void setAssessMail(String assessMail) {
        this.assessMail = assessMail;
    }

    /**
     * get the assessTypeCode - 问卷类型编码
     * @return the assessTypeCode
     */
    public String getAssessTypeCode() {
        return this.assessTypeCode;
    }

    /**
     * set the assessTypeCode - 问卷类型编码
     */
    public void setAssessTypeCode(String assessTypeCode) {
        this.assessTypeCode = assessTypeCode;
    }

    /**
     * get the assessTypeName - 问卷类型名
     * @return the assessTypeName
     */
    public String getAssessTypeName() {
        return this.assessTypeName;
    }

    /**
     * set the assessTypeName - 问卷类型名
     */
    public void setAssessTypeName(String assessTypeName) {
        this.assessTypeName = assessTypeName;
    }
    
    public String getDataGroupCode() {
        return dataGroupCode;
    }

    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setStandardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardCode")), standardCode));
        setStandardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardName")), standardName));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setModifyTime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifyTime"))));
        setAssessDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessDeptName")), assessDeptName));
        setAssessWorkNoLeader(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessWorkNoLeader")), assessWorkNoLeader));
        setAssessWorkNameLeader(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessWorkNameLeader")), assessWorkNameLeader));
        setAssessContactTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessContactTel")), assessContactTel));
        setAssessMail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessMail")), assessMail));
        setAssessTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessTypeCode")), assessTypeCode));
        setAssessTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assessTypeName")), assessTypeName));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("standardCode",StringUtils.toString(standardCode, eiMetadata.getMeta("standardCode")));
        map.put("standardName",StringUtils.toString(standardName, eiMetadata.getMeta("standardName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifier",StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("modifyTime",StringUtils.toString(modifyTime, eiMetadata.getMeta("modifyTime")));
        map.put("assessDeptName",StringUtils.toString(assessDeptName, eiMetadata.getMeta("assessDeptName")));
        map.put("assessWorkNoLeader",StringUtils.toString(assessWorkNoLeader, eiMetadata.getMeta("assessWorkNoLeader")));
        map.put("assessWorkNameLeader",StringUtils.toString(assessWorkNameLeader, eiMetadata.getMeta("assessWorkNameLeader")));
        map.put("assessContactTel",StringUtils.toString(assessContactTel, eiMetadata.getMeta("assessContactTel")));
        map.put("assessMail",StringUtils.toString(assessMail, eiMetadata.getMeta("assessMail")));
        map.put("assessTypeCode",StringUtils.toString(assessTypeCode, eiMetadata.getMeta("assessTypeCode")));
        map.put("assessTypeName",StringUtils.toString(assessTypeName, eiMetadata.getMeta("assessTypeName")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}