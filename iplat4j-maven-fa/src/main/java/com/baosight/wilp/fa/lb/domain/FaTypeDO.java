package com.baosight.wilp.fa.lb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月21日 11:32
 */
public class FaTypeDO extends DaoEPBase {

	private static final long serialVersionUID = 1L;
	/** 固定资产类别表主键 */
	private String id;
	/** 类别编码 */
	@NotBlank(message = "类别编码不能为空")
	private String typeCode;
	/** 类别名称 */
	private String typeName;
	/** 父ID(根节点为root) */
	private String parentId;
	/** 等级 */
	private String level;
	/** 是否是叶子节点(N=不是，Y=是) */
	private String isLeaf;
	/** 备注 */
	private String remark;
	/** 使用年限(年) */
	private Integer useYears;
	/** 编码规则 */
	private String codeRule;
	/** 排序字段 */
	private Integer sortNo;
	/** 创建人 */
	private String recCreator;
	/** 创建人姓名 */
	private String recCreateName;
	/** 创建时间 */
	private Date recCreateTime;
	/** 修改人 */
	private String recRevisor;
	/** 修改人姓名 */
	private String recReviseName;
	/** 修改时间 */
	private Date recReviseTime;
	/** 归档标记 */
	private String archiveFlag;
	/** 账套 */
	private String dataGroupCode;

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产类别表主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typeCode");
		eiColumn.setDescName("类别编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typeName");
		eiColumn.setDescName("类别名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("parentId");
		eiColumn.setDescName("父ID(根节点为root)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("level");
		eiColumn.setDescName("等级");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("isLeaf");
		eiColumn.setDescName("是否是叶子节点(N=不是，Y=是)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("remark");
		eiColumn.setDescName("备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("useYears");
		eiColumn.setDescName("使用年限(年)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("codeRule");
		eiColumn.setDescName("编码规则");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("sortNo");
		eiColumn.setDescName("排序字段");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreator");
		eiColumn.setDescName("创建人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreateName");
		eiColumn.setDescName("创建人姓名");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreateTime");
		eiColumn.setDescName("创建时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recRevisor");
		eiColumn.setDescName("修改人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recReviseName");
		eiColumn.setDescName("修改人姓名");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recReviseTime");
		eiColumn.setDescName("修改时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("archiveFlag");
		eiColumn.setDescName("归档标记");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("dataGroupCode");
		eiColumn.setDescName("账套");
		eiMetadata.addMeta(eiColumn);


	}

	/**
	 * the constructor
	 */
	public FaTypeDO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产类别表主键
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 固定资产类别表主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get the typeCode - 类别编码
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set the typeCode - 类别编码
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * get the typeName - 类别名称
	 * @return the typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * set the typeName - 类别名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * get the parentId - 父ID(根节点为root)
	 * @return the parentId
	 */
	public String getParentId() {
		return this.parentId;
	}

	/**
	 * set the parentId - 父ID(根节点为root)
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * get the level - 等级
	 * @return the level
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * set the level - 等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * get the isLeaf - 是否是叶子节点(N=不是，Y=是)
	 * @return the isLeaf
	 */
	public String getIsLeaf() {
		return this.isLeaf;
	}

	/**
	 * set the isLeaf - 是否是叶子节点(N=不是，Y=是)
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
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
	 * get the useYears - 使用年限(年)
	 * @return the useYears
	 */
	public Integer getUseYears() {
		return this.useYears;
	}

	/**
	 * set the useYears - 使用年限(年)
	 */
	public void setUseYears(Integer useYears) {
		this.useYears = useYears;
	}

	/**
	 * get the codeRule - 编码规则
	 * @return the codeRule
	 */
	public String getCodeRule() {
		return this.codeRule;
	}

	/**
	 * set the codeRule - 编码规则
	 */
	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}

	/**
	 * get the sortNo - 排序字段
	 * @return the sortNo
	 */
	public Integer getSortNo() {
		return this.sortNo;
	}

	/**
	 * set the sortNo - 排序字段
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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
	@Override
	public void setRecCreator(String recCreator) {
		this.recCreator = recCreator;
	}

	/**
	 * get the recCreateName - 创建人姓名
	 * @return the recCreateName
	 */
	public String getRecCreateName() {
		return this.recCreateName;
	}

	/**
	 * set the recCreateName - 创建人姓名
	 */
	public void setRecCreateName(String recCreateName) {
		this.recCreateName = recCreateName;
	}

	/**
	 * get the recCreateTime - 创建时间
	 * @return the recCreateTime
	 */
	public Date getRecCreateTime() {
		return this.recCreateTime;
	}

	/**
	 * set the recCreateTime - 创建时间
	 */
	public void setRecCreateTime(Date recCreateTime) {
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
	@Override
	public void setRecRevisor(String recRevisor) {
		this.recRevisor = recRevisor;
	}

	/**
	 * get the recReviseName - 修改人姓名
	 * @return the recReviseName
	 */
	public String getRecReviseName() {
		return this.recReviseName;
	}

	/**
	 * set the recReviseName - 修改人姓名
	 */
	public void setRecReviseName(String recReviseName) {
		this.recReviseName = recReviseName;
	}

	/**
	 * get the recReviseTime - 修改时间
	 * @return the recReviseTime
	 */
	public Date getRecReviseTime() {
		return this.recReviseTime;
	}

	/**
	 * set the recReviseTime - 修改时间
	 */
	public void setRecReviseTime(Date recReviseTime) {
		this.recReviseTime = recReviseTime;
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
	@Override
	public void setArchiveFlag(String archiveFlag) {
		this.archiveFlag = archiveFlag;
	}

	/**
	 * get the dataGroupCode - 账套
	 * @return the dataGroupCode
	 */
	public String getDataGroupCode() {
		return this.dataGroupCode;
	}

	/**
	 * set the dataGroupCode - 账套
	 */
	public void setDataGroupCode(String dataGroupCode) {
		this.dataGroupCode = dataGroupCode;
	}

	/**
	 * get the value from Map
	 */
	@Override
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
		setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
		setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
		setLevel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("level")), level));
		setIsLeaf(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isLeaf")), isLeaf));
		setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
		setUseYears(NumberUtils.toInteger(StringUtils.toString(map.get("useYears")), useYears));
		setCodeRule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("codeRule")), codeRule));
		setSortNo(NumberUtils.toInteger(StringUtils.toString(map.get("sortNo")), sortNo));
		setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
		setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
		setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
		setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
		setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
		setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
		setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
		setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
	}

	/**
	 * set the value to Map
	 */
	@Override
	public Map toMap() {
		Map map = new HashMap(24);
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
		map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
		map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
		map.put("level",StringUtils.toString(level, eiMetadata.getMeta("level")));
		map.put("isLeaf",StringUtils.toString(isLeaf, eiMetadata.getMeta("isLeaf")));
		map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		map.put("useYears",StringUtils.toString(useYears, eiMetadata.getMeta("useYears")));
		map.put("codeRule",StringUtils.toString(codeRule, eiMetadata.getMeta("codeRule")));
		map.put("sortNo",StringUtils.toString(sortNo, eiMetadata.getMeta("sortNo")));
		map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
		map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
		map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
		map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
		map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
		map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
		map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
		map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
		return map;
	}
}