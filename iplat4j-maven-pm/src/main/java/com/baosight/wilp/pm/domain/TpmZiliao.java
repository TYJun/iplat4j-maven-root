/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 项目提交资料表
 * 
 * @Title: TpmZiliao.java
 * @ClassName: TpmZiliao
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:24:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmZiliao extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String projectPk = " ";		/* 项目主键*/
    private String dataCode = " ";		/* 资料编码*/
    private String dataName = " ";		/* 资料名称*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectPk");
        eiColumn.setDescName("项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataCode");
        eiColumn.setDescName("资料编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataName");
        eiColumn.setDescName("资料名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public TpmZiliao() {
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
     * get the projectPk - 项目主键
     * @return the projectPk
     */
    public String getProjectPk() {
        return this.projectPk;
    }

    /**
     * set the projectPk - 项目主键
     */
    public void setProjectPk(String projectPk) {
        this.projectPk = projectPk;
    }

    /**
     * get the dataCode - 资料编码
     * @return the dataCode
     */
    public String getDataCode() {
        return this.dataCode;
    }

    /**
     * set the dataCode - 资料编码
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    /**
     * get the dataName - 资料名称
     * @return the dataName
     */
    public String getDataName() {
        return this.dataName;
    }

    /**
     * set the dataName - 资料名称
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
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
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
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
        setProjectPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPk")), projectPk));
        setDataCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataCode")), dataCode));
        setDataName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataName")), dataName));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectPk",StringUtils.toString(projectPk, eiMetadata.getMeta("projectPk")));
        map.put("dataCode",StringUtils.toString(dataCode, eiMetadata.getMeta("dataCode")));
        map.put("dataName",StringUtils.toString(dataName, eiMetadata.getMeta("dataName")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}