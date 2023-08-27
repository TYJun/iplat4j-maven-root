/**
 * Generate time : 2021-11-11 10:49:49
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * PmStage
 *
 */
public class TpmStage extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id = " ";		/* 项目阶段主键*/
	private String projectPk = " ";		/* 项目主键*/
	private String stageCode = " ";		/* 阶段编码*/
	private String stageName = " ";		/* 阶段名称*/
	private String stageRemark = " ";		/* 阶段备注*/
	private String typeCode = " ";		/* 类型编码*/
	private String typeName = " ";		/* 类型名称*/
	private String typeRemark = " ";		/* 类型备注*/
	private String planDate = " ";		/* 计划日期*/
	private String createNo = " ";		/* 创建人工号*/
	private String createName = " ";		/* 创建人*/
	private String createTime = " ";		/* 创建时间*/
	private String updateNo = " ";		/* 修改人工号*/
	private String updateName = " ";		/* 修改人*/
	private String updateTime = " ";		/* 修改时间*/
	private String dataGroupCode = " ";		/* 账套*/
	private String flag = " ";		/* 阶段标记位（0-未执行，1执行中）*/

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("项目阶段主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("projectPk");
		eiColumn.setDescName("项目主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("stageCode");
		eiColumn.setDescName("阶段编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("stageName");
		eiColumn.setDescName("阶段名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("stageRemark");
		eiColumn.setDescName("阶段备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typeCode");
		eiColumn.setDescName("类型编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typeName");
		eiColumn.setDescName("类型名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typeRemark");
		eiColumn.setDescName("类型备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("planDate");
		eiColumn.setDescName("计划日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("createNo");
		eiColumn.setDescName("创建人工号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("createName");
		eiColumn.setDescName("创建人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("createTime");
		eiColumn.setDescName("创建时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("updateNo");
		eiColumn.setDescName("修改人工号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("updateName");
		eiColumn.setDescName("修改人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("updateTime");
		eiColumn.setDescName("修改时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("dataGroupCode");
		eiColumn.setDescName("账套");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("flag");
		eiColumn.setDescName("阶段标记位（0-未执行，1执行中）");
		eiMetadata.addMeta(eiColumn);


	}

	/**
	 * the constructor
	 */
	public TpmStage() {
		initMetaData();
	}

	/**
	 * get the id - 项目阶段主键
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 项目阶段主键
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
	 * get the stageCode - 阶段编码
	 * @return the stageCode
	 */
	public String getStageCode() {
		return this.stageCode;
	}

	/**
	 * set the stageCode - 阶段编码
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	/**
	 * get the stageName - 阶段名称
	 * @return the stageName
	 */
	public String getStageName() {
		return this.stageName;
	}

	/**
	 * set the stageName - 阶段名称
	 */
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	/**
	 * get the stageRemark - 阶段备注
	 * @return the stageRemark
	 */
	public String getStageRemark() {
		return this.stageRemark;
	}

	/**
	 * set the stageRemark - 阶段备注
	 */
	public void setStageRemark(String stageRemark) {
		this.stageRemark = stageRemark;
	}

	/**
	 * get the typeCode - 类型编码
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set the typeCode - 类型编码
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * get the typeName - 类型名称
	 * @return the typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * set the typeName - 类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * get the typeRemark - 类型备注
	 * @return the typeRemark
	 */
	public String getTypeRemark() {
		return this.typeRemark;
	}

	/**
	 * set the typeRemark - 类型备注
	 */
	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	/**
	 * get the planDate - 计划日期
	 * @return the planDate
	 */
	public String getPlanDate() {
		return this.planDate;
	}

	/**
	 * set the planDate - 计划日期
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	/**
	 * get the createNo - 创建人工号
	 * @return the createNo
	 */
	public String getCreateNo() {
		return this.createNo;
	}

	/**
	 * set the createNo - 创建人工号
	 */
	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}

	/**
	 * get the createName - 创建人
	 * @return the createName
	 */
	public String getCreateName() {
		return this.createName;
	}

	/**
	 * set the createName - 创建人
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * get the createTime - 创建时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * set the createTime - 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * get the updateNo - 修改人工号
	 * @return the updateNo
	 */
	public String getUpdateNo() {
		return this.updateNo;
	}

	/**
	 * set the updateNo - 修改人工号
	 */
	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}

	/**
	 * get the updateName - 修改人
	 * @return the updateName
	 */
	public String getUpdateName() {
		return this.updateName;
	}

	/**
	 * set the updateName - 修改人
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * get the updateTime - 修改时间
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set the updateTime - 修改时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * get the dataGroupCode - 账套
	 * @return the dataGroupCode
	 */
	public String getdataGroupCode() {
		return this.dataGroupCode;
	}

	/**
	 * set the dataGroupCode - 账套
	 */
	public void setdataGroupCode(String dataGroupCode) {
		this.dataGroupCode = dataGroupCode;
	}

	/**
	 * get the flag - 阶段标记位（0-未执行，1执行中）
	 * @return the flag
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * set the flag - 阶段标记位（0-未执行，1执行中）
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setProjectPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPk")), projectPk));
		setStageCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stageCode")), stageCode));
		setStageName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stageName")), stageName));
		setStageRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stageRemark")), stageRemark));
		setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
		setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
		setTypeRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeRemark")), typeRemark));
		setPlanDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planDate")), planDate));
		setCreateNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createNo")), createNo));
		setCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createName")), createName));
		setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
		setUpdateNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateNo")), updateNo));
		setUpdateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateName")), updateName));
		setUpdateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateTime")), updateTime));
		setdataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
		setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("projectPk",StringUtils.toString(projectPk, eiMetadata.getMeta("projectPk")));
		map.put("stageCode",StringUtils.toString(stageCode, eiMetadata.getMeta("stageCode")));
		map.put("stageName",StringUtils.toString(stageName, eiMetadata.getMeta("stageName")));
		map.put("stageRemark",StringUtils.toString(stageRemark, eiMetadata.getMeta("stageRemark")));
		map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
		map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
		map.put("typeRemark",StringUtils.toString(typeRemark, eiMetadata.getMeta("typeRemark")));
		map.put("planDate",StringUtils.toString(planDate, eiMetadata.getMeta("planDate")));
		map.put("createNo",StringUtils.toString(createNo, eiMetadata.getMeta("createNo")));
		map.put("createName",StringUtils.toString(createName, eiMetadata.getMeta("createName")));
		map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
		map.put("updateNo",StringUtils.toString(updateNo, eiMetadata.getMeta("updateNo")));
		map.put("updateName",StringUtils.toString(updateName, eiMetadata.getMeta("updateName")));
		map.put("updateTime",StringUtils.toString(updateTime, eiMetadata.getMeta("updateTime")));
		map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
		map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
		return map;
	}
}