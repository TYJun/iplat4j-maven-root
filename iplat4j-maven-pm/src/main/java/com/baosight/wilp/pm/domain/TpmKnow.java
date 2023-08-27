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
 * 项目知会人表实体
 * 
 * @Title: TpmKnow.java
 * @ClassName: TpmKnow
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:21:47
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmKnow extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 项目知会人员主键*/
    private String projectPk = " ";		/* 项目主键*/
    private String notifyStaffId = " ";		/* 知会人员工号*/
    private String number = " ";		/* 电话号码*/
    private String deptNum = " ";		/* 科室编码*/
    private String deptName = " ";		/* 科室编码*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String notifyStaffName = " ";		
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("项目知会人员主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectPk");
        eiColumn.setDescName("项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("notifyStaffId");
        eiColumn.setDescName("知会人员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("number");
        eiColumn.setDescName("电话号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
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

        eiColumn = new EiColumn("notifyStaffName");
        eiColumn.setDescName("知会人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public TpmKnow() {
        initMetaData();
    }

    /**
     * get the id - 项目知会人员主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 项目知会人员主键
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
     * get the notifyStaffId - 知会人员工号
     * @return the notifyStaffId
     */
    public String getNotifyStaffId() {
        return this.notifyStaffId;
    }

    /**
     * set the notifyStaffId - 知会人员工号
     */
    public void setNotifyStaffId(String notifyStaffId) {
        this.notifyStaffId = notifyStaffId;
    }

    /**
     * get the number - 电话号码
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * set the number - 电话号码
     */
    public void setNumber(String number) {
        this.number = number;
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

    /**
     * get the notifyStaffName 
     * @return the notifyStaffName
     */
    public String getNotifyStaffName() {
        return this.notifyStaffName;
    }

    /**
     * set the notifyStaffName 
     */
    public void setNotifyStaffName(String notifyStaffName) {
        this.notifyStaffName = notifyStaffName;
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
        setNotifyStaffId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notifyStaffId")), notifyStaffId));
        setNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("number")), number));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setNotifyStaffName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notifyStaffName")), notifyStaffName));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

	/**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectPk",StringUtils.toString(projectPk, eiMetadata.getMeta("projectPk")));
        map.put("notifyStaffId",StringUtils.toString(notifyStaffId, eiMetadata.getMeta("notifyStaffId")));
        map.put("number",StringUtils.toString(number, eiMetadata.getMeta("number")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("notifyStaffName",StringUtils.toString(notifyStaffName, eiMetadata.getMeta("notifyStaffName")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}