/**
* Generate time : 2021-07-19 17:11:07
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.bz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 考核项目实体类
 * 
 * @Title: SqProject.java
 * @ClassName: SqProject
 * @Package：com.baosight.wilp.sq.bz.domain
 * @author: zhangjaiqian
 * @date: 2021年7月19日 下午5:11:26
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class SQBZ02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String projectCode = " ";		/* 项目编码*/
    private String projectName = " ";		/* 项目名称*/
    private String projectRemark = " ";		/* 项目备注*/
    private String creator = " ";		/* 记录创建人*/
    private Timestamp createTime ;		/* 创建时间*/
    private String modifier = " ";		/* 记录修改人*/
    private Timestamp modifyTime ;		/* 记录修改时间*/
    private String standardId = " ";		/* 主题外键*/
    private Integer orderNumber = new Integer(0);		/* 菜单顺序*/

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

        eiColumn = new EiColumn("projectRemark");
        eiColumn.setDescName("项目备注");
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

        eiColumn = new EiColumn("standardId");
        eiColumn.setDescName("主题外键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNumber");
        eiColumn.setDescName("菜单顺序");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SQBZ02() {
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
     * get the projectRemark - 项目备注
     * @return the projectRemark
     */
    public String getProjectRemark() {
        return this.projectRemark;
    }

    /**
     * set the projectRemark - 项目备注
     */
    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
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
     * get the standardId - 主题外键
     * @return the standardId
     */
    public String getStandardId() {
        return this.standardId;
    }

    /**
     * set the standardId - 主题外键
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setProjectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectCode")), projectCode));
        setProjectName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectName")), projectName));
        setProjectRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectRemark")), projectRemark));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setModifyTime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifyTime"))));
        setStandardId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardId")), standardId));
        setOrderNumber(NumberUtils.toInteger(StringUtils.toString(map.get("orderNumber")), orderNumber));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectCode",StringUtils.toString(projectCode, eiMetadata.getMeta("projectCode")));
        map.put("projectName",StringUtils.toString(projectName, eiMetadata.getMeta("projectName")));
        map.put("projectRemark",StringUtils.toString(projectRemark, eiMetadata.getMeta("projectRemark")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifier",StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("modifyTime",StringUtils.toString(modifyTime, eiMetadata.getMeta("modifyTime")));
        map.put("standardId",StringUtils.toString(standardId, eiMetadata.getMeta("standardId")));
        map.put("orderNumber",StringUtils.toString(orderNumber, eiMetadata.getMeta("orderNumber")));
        return map;
    }
}