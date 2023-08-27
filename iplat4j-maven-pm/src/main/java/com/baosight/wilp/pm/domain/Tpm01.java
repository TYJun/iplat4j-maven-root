/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 工程项目表实体
 *
 * @Title: Tpm01.java
 * @ClassName: Tpm01
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:20:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class Tpm01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String parentProjectPk = " ";		/* 父项目主键*/
    private String projectNo = " ";		/* 项目号*/
    private String projectName = " ";		/* 项目名称*/
    private String projectProp = " ";		/* 项目性质*/
    private String projectTypeNum = " ";		/* 项目类型编码*/
    private String projectTypeName = " ";		/* 项目类型名称*/
    private String projectPriNum = " ";		/* 项目优先级编码*/
    private String projectContent = " ";		/* 项目内容*/
    private String projectStatus = " ";		/* 项目状态编码*/
    private String projectProgress = "";		/* 项目进度*/
    private String projectRemark = " ";		/* 项目备注*/
    private String projectObjectCons = " ";		/* 项目对象联络人*/
    private String projectObjectConsName = " ";		/* 项目对象联络人*/
    private String projectObjectDeptNum = " ";		/* 项目对象科室编码*/
    private String projectObjectDeptName = " ";		/* 项目对象科室名称*/
    private String contorName = " ";		/* 项目负责人*/
    private String contorId = " ";		/* 项目负责人工号*/
    private String projectPrincipal = " ";		/* 项目负责人*/
    private String undertakeDeptNum = " ";		/* 承办科室编码*/
    private String undertakeDeptName = " ";		/* 承办科室名称*/
    private String supplierNum = " ";		/* 供应商编码*/
    private String supplierName = " ";		/* 供应商名称*/
    private String startDate = " ";		/* 开始日期*/
    private String finishDeadline = " ";		/* 完成期限*/
    private String actFinisthDate = " ";		/* 实际完成日期*/
    private String ystime = " ";		/* 验收时间*/
    private Double finishExpense = new Double(0);		/* 结算费用*/
    private Double winbidExpense = new Double(0);		/* 中标费用*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String dataGroupCode = " ";		/* 账套*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
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

        eiColumn = new EiColumn("baseProjectPk");
        eiColumn.setDescName("基项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentProjectPk");
        eiColumn.setDescName("父项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectNo");
        eiColumn.setDescName("项目号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectName");
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectProp");
        eiColumn.setDescName("项目性质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectTypeNum");
        eiColumn.setDescName("项目类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectTypeName");
        eiColumn.setDescName("项目类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectPriNum");
        eiColumn.setDescName("项目优先级编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectContent");
        eiColumn.setDescName("项目内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectStatus");
        eiColumn.setDescName("项目状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectProgress");
        eiColumn.setDescName("项目进度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectObject");
        eiColumn.setDescName("项目对象");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectObjectCons");
        eiColumn.setDescName("项目对象联络人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectObjectConsName");
        eiColumn.setDescName("项目对象联络人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectObjectDeptNum");
        eiColumn.setDescName("项目对象科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectObjectDeptName");
        eiColumn.setDescName("项目对象科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contorName");
        eiColumn.setDescName("承办人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contorId");
        eiColumn.setDescName("承办人工号");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("projectPrincipal");
        eiColumn.setDescName("承办人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("undertakeDeptNum");
        eiColumn.setDescName("承办科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("undertakeDeptName");
        eiColumn.setDescName("承办科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("execDept");
        eiColumn.setDescName("执行单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierNum");
        eiColumn.setDescName("供应商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("供应商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("startDate");
        eiColumn.setDescName("开始日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishDeadline");
        eiColumn.setDescName("完成期限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actFinisthDate");
        eiColumn.setDescName("实际完成日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("budgetExpense");
        eiColumn.setDescName("预算费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishExpense");
        eiColumn.setDescName("结算费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("audit");
        eiColumn.setDescName("审计");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectRemark");
        eiColumn.setDescName("项目备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("文件名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileType");
        eiColumn.setDescName("文件类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileContent");
        eiColumn.setDescName("文件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("zbno");
        eiColumn.setDescName("招标负责人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("winbidExpense");
        eiColumn.setDescName("中标费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("winbidDeptName");
        eiColumn.setDescName("中标单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("winbidDeptNum");
        eiColumn.setDescName("中标单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidDeptNum");
        eiColumn.setDescName("参标单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidDeptName");
        eiColumn.setDescName("参标单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ystime");
        eiColumn.setDescName("验收时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileFlag");
        eiColumn.setDescName("是否上传附件标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contFlag");
        eiColumn.setDescName("生成合同标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("budaFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
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


    }

    /**
     * the constructor
     */
    public Tpm01() {
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
     * get the parentProjectPk - 父项目主键
     * @return the parentProjectPk
     */
    public String getParentProjectPk() {
        return this.parentProjectPk;
    }

    /**
     * set the parentProjectPk - 父项目主键
     */
    public void setParentProjectPk(String parentProjectPk) {
        this.parentProjectPk = parentProjectPk;
    }

    /**
     * get the projectNo - 项目号
     * @return the projectNo
     */
    public String getProjectNo() {
        return this.projectNo;
    }

    /**
     * set the projectNo - 项目号
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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
     * get the projectProp - 项目性质
     * @return the projectProp
     */
    public String getProjectProp() {
        return this.projectProp;
    }

    /**
     * set the projectProp - 项目性质
     */
    public void setProjectProp(String projectProp) {
        this.projectProp = projectProp;
    }

    /**
     * get the projectTypeNum - 项目类型编码
     * @return the projectTypeNum
     */
    public String getProjectTypeNum() {
        return this.projectTypeNum;
    }

    /**
     * get the projectTypeName - 项目类型名称
     * @return the projectTypeName
     */
    public String getProjectTypeName() {
        return this.projectTypeName;
    }

    /**
     * set the projectTypeNum - 项目类型编码
     */
    public void setProjectTypeNum(String projectTypeNum) {
        this.projectTypeNum = projectTypeNum;
    }

    /**
     * set the projectTypeName - 项目类型编码
     */
    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    /**
     * get the projectPriNum - 项目优先级编码
     * @return the projectPriNum
     */
    public String getProjectPriNum() {
        return this.projectPriNum;
    }

    /**
     * set the projectPriNum - 项目优先级编码
     */
    public void setProjectPriNum(String projectPriNum) {
        this.projectPriNum = projectPriNum;
    }

    /**
     * get the projectContent - 项目内容
     * @return the projectContent
     */
    public String getProjectContent() {
        return this.projectContent;
    }

    /**
     * set the projectContent - 项目内容
     */
    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    /**
     * get the projectStatus - 项目状态编码
     * @return the projectStatus
     */
    public String getProjectStatus() {
        return this.projectStatus;
    }

    /**
     * set the projectStatus - 项目状态编码
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * get the projectProgress - 项目进度
     * @return the projectProgress
     */
    public String getProjectProgress() {
        return this.projectProgress;
    }

    /**
     * set the projectProgress - 项目进度
     */
    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    /**
     * get the projectObjectCons - 项目对象联络人
     * @return the projectObjectCons
     */
    public String getProjectObjectCons() {
        return this.projectObjectCons;
    }

    /**
     * set the projectObjectCons - 项目对象联络人
     */
    public void setProjectObjectCons(String projectObjectCons) {
        this.projectObjectCons = projectObjectCons;
    }

    public String getProjectObjectConsName() {
		return projectObjectConsName;
	}

	public void setProjectObjectConsName(String projectObjectConsName) {
		this.projectObjectConsName = projectObjectConsName;
	}

	/**
     * get the projectObjectDeptNum - 项目对象科室编码
     * @return the projectObjectDeptNum
     */
    public String getProjectObjectDeptNum() {
        return this.projectObjectDeptNum;
    }

    /**
     * set the projectObjectDeptNum - 项目对象科室编码
     */
    public void setProjectObjectDeptNum(String projectObjectDeptNum) {
        this.projectObjectDeptNum = projectObjectDeptNum;
    }

    public String getContorName() {
        return contorName;
    }

    public void setContorName(String contorName) {
        this.contorName = contorName;
    }

    /**
     * get the contorId - 承办人工号
     * @return the contorId
     */
    public String getContorId() {
        return this.contorId;
    }

    /**
     * set the contorId - 承办人工号
     */
    public void setContorId(String contorId) {
        this.contorId = contorId;
    }

    /**
     * get the undertakeDeptNum - 承办科室编码
     * @return the undertakeDeptNum
     */
    public String getUndertakeDeptNum() {
        return this.undertakeDeptNum;
    }

    /**
     * set the undertakeDeptNum - 承办科室编码
     */
    public void setUndertakeDeptNum(String undertakeDeptNum) {
        this.undertakeDeptNum = undertakeDeptNum;
    }

    public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	/**
     * get the startDate - 开始日期
     * @return the startDate
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * set the startDate - 开始日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * get the finishDeadline - 完成期限
     * @return the finishDeadline
     */
    public String getFinishDeadline() {
        return this.finishDeadline;
    }

    /**
     * set the finishDeadline - 完成期限
     */
    public void setFinishDeadline(String finishDeadline) {
        this.finishDeadline = finishDeadline;
    }

    /**
     * get the actFinisthDate - 实际完成日期
     * @return the actFinisthDate
     */
    public String getActFinisthDate() {
        return this.actFinisthDate;
    }

    /**
     * set the actFinisthDate - 实际完成日期
     */
    public void setActFinisthDate(String actFinisthDate) {
        this.actFinisthDate = actFinisthDate;
    }

    /**
     * get the finishExpense - 结算费用
     * @return the finishExpense
     */
    public Double getFinishExpense() {
        return this.finishExpense;
    }

    /**
     * set the finishExpense - 结算费用
     */
    public void setFinishExpense(Double finishExpense) {
        this.finishExpense = finishExpense;
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
     * get the winbidExpense - 中标费用
     * @return the winbidExpense
     */
    public Double getWinbidExpense() {
        return this.winbidExpense;
    }

    /**
     * set the winbidExpense - 中标费用
     */
    public void setWinbidExpense(Double winbidExpense) {
        this.winbidExpense = winbidExpense;
    }

    /**
     * get the ystime - 验收时间
     * @return the ystime
     */
    public String getYstime() {
        return this.ystime;
    }

    /**
     * set the ystime - 验收时间
     */
    public void setYstime(String ystime) {
        this.ystime = ystime;
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
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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

    public String getProjectObjectDeptName() {
		return projectObjectDeptName;
	}

	public void setProjectObjectDeptName(String projectObjectDeptName) {
		this.projectObjectDeptName = projectObjectDeptName;
	}

	public String getProjectPrincipal() {
		return projectPrincipal;
	}

	public void setProjectPrincipal(String projectPrincipal) {
		this.projectPrincipal = projectPrincipal;
	}

	public String getUndertakeDeptName() {
		return undertakeDeptName;
	}

	public void setUndertakeDeptName(String undertakeDeptName) {
		this.undertakeDeptName = undertakeDeptName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParentProjectPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentProjectPk")), parentProjectPk));
        setProjectNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectNo")), projectNo));
        setProjectName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectName")), projectName));
        setProjectProp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectProp")), projectProp));
        setProjectTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectTypeNum")), projectTypeNum));
        setProjectTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectTypeName")), projectTypeName));
        setProjectPriNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPriNum")), projectPriNum));
        setProjectContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectContent")), projectContent));
        setProjectStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectStatus")), projectStatus));
        setProjectProgress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectProgress")), projectProgress));
        setProjectObjectCons(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectObjectCons")), projectObjectCons));
        setProjectObjectDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectObjectDeptNum")), projectObjectDeptNum));
        setProjectObjectDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectObjectDeptName")), projectObjectDeptName));
        setContorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contorName")), contorName));
        setContorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contorId")), contorId));
        setProjectPrincipal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPrincipal")), projectPrincipal));
        setUndertakeDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("undertakeDeptNum")), undertakeDeptNum));
        setUndertakeDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("undertakeDeptName")), undertakeDeptName));
        setSupplierNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("supplierNum")), supplierNum));
        setStartDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("startDate")), startDate));
        setFinishDeadline(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishDeadline")), finishDeadline));
        setActFinisthDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actFinisthDate")), actFinisthDate));
        setFinishExpense(NumberUtils.toDouble(StringUtils.toString(map.get("finishExpense")), finishExpense));
        setProjectRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectRemark")), projectRemark));
        setWinbidExpense(NumberUtils.toDouble(StringUtils.toString(map.get("winbidExpense")), winbidExpense));
        setYstime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ystime")), ystime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
	public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parentProjectPk",StringUtils.toString(parentProjectPk, eiMetadata.getMeta("parentProjectPk")));
        map.put("projectNo",StringUtils.toString(projectNo, eiMetadata.getMeta("projectNo")));
        map.put("projectName",StringUtils.toString(projectName, eiMetadata.getMeta("projectName")));
        map.put("projectProp",StringUtils.toString(projectProp, eiMetadata.getMeta("projectProp")));
        map.put("projectTypeNum",StringUtils.toString(projectTypeNum, eiMetadata.getMeta("projectTypeNum")));
        map.put("projectTypeName",StringUtils.toString(projectTypeName, eiMetadata.getMeta("projectTypeName")));
        map.put("projectPriNum",StringUtils.toString(projectPriNum, eiMetadata.getMeta("projectPriNum")));
        map.put("projectContent",StringUtils.toString(projectContent, eiMetadata.getMeta("projectContent")));
        map.put("projectStatus",StringUtils.toString(projectStatus, eiMetadata.getMeta("projectStatus")));
        map.put("projectProgress",StringUtils.toString(projectProgress, eiMetadata.getMeta("projectProgress")));
        map.put("projectObjectCons",StringUtils.toString(projectObjectCons, eiMetadata.getMeta("projectObjectCons")));
        map.put("projectObjectDeptNum",StringUtils.toString(projectObjectDeptNum, eiMetadata.getMeta("projectObjectDeptNum")));
        map.put("projectObjectDeptName",StringUtils.toString(projectObjectDeptName, eiMetadata.getMeta("projectObjectDeptName")));
        map.put("projectPrincipal",StringUtils.toString(projectPrincipal, eiMetadata.getMeta("projectPrincipal")));
        map.put("contorName",StringUtils.toString(contorName, eiMetadata.getMeta("contorName")));
        map.put("contorId",StringUtils.toString(contorId, eiMetadata.getMeta("contorId")));
        map.put("undertakeDeptNum",StringUtils.toString(undertakeDeptNum, eiMetadata.getMeta("undertakeDeptNum")));
        map.put("undertakeDeptName",StringUtils.toString(undertakeDeptName, eiMetadata.getMeta("undertakeDeptName")));
        map.put("supplierNum",StringUtils.toString(supplierNum, eiMetadata.getMeta("supplierNum")));
        map.put("startDate",StringUtils.toString(startDate, eiMetadata.getMeta("startDate")));
        map.put("finishDeadline",StringUtils.toString(finishDeadline, eiMetadata.getMeta("finishDeadline")));
        map.put("actFinisthDate",StringUtils.toString(actFinisthDate, eiMetadata.getMeta("actFinisthDate")));
        map.put("finishExpense",StringUtils.toString(finishExpense, eiMetadata.getMeta("finishExpense")));
        map.put("projectRemark",StringUtils.toString(projectRemark, eiMetadata.getMeta("projectRemark")));
        map.put("winbidExpense",StringUtils.toString(winbidExpense, eiMetadata.getMeta("winbidExpense")));
        map.put("ystime",StringUtils.toString(ystime, eiMetadata.getMeta("ystime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }


}