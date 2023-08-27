/**
 * Generate time : 2023-02-02 17:32:10
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.fa.pd.domain;


import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FaInventory
 *
 */
public class FaInventoryDO extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id = " ";		/* 固定资产盘点表主键*/
	private String recCreator = " ";		/* 创建人*/
	private String recCreateName = " ";		/* 创建人*/
	private Date recCreateTime ;		/* 创建时间*/
	private String recRevisor = " ";		/* 修改人*/
	private String recReviseName = " ";		/* 修改人*/
	private Date recReviseTime ;		/* 修改时间*/
	private String dataGroupCode = " ";		/* 账套*/
	private String archiveFlag = " ";		/* 归档标记*/
	private String inventoryNo = " ";		/* 盘点单号*/
	private String inventoryDeptNum = " ";		/* 盘点科室编码*/
	private String inventoryDeptName = " ";		/* 盘点科室名称*/
	private String build = " ";		/* 楼*/
	private String floor = " ";		/* 层*/
	private String inventorySpotNum = " ";		/* 盘点地点*/
	private String inventorySpotName = " ";		/* 盘点地点*/
	private Date singleSystenDate;	/* 制单日期*/
	private String singleSystemMen = " ";		/* 制单人员*/
	private String singleSystemName = " ";		/* 制单人员名称*/
	private Date checkDate;	/* 审核日期*/
	private String checkMen = " ";		/* 审核人员*/
	private String checkMenName = " ";		/* 审核人员*/
	private String remark = " ";		/* 备注*/
	private String statusCode = " ";		/* 0/录入；1/确认*/
	private Integer afterInvenNum = new Integer(0);		/* 盘库后数量*/
	private Integer beforeInvenNum = new Integer(0);		/* 盘库前数量*/

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产盘点表主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreator");
		eiColumn.setDescName("创建人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreateName");
		eiColumn.setDescName("创建人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreateTime");
		eiColumn.setDescName("创建时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recRevisor");
		eiColumn.setDescName("修改人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recReviseName");
		eiColumn.setDescName("修改人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recReviseTime");
		eiColumn.setDescName("修改时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("dataGroupCode");
		eiColumn.setDescName("账套");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("archiveFlag");
		eiColumn.setDescName("归档标记");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inventoryNo");
		eiColumn.setDescName("盘点单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inventoryDeptNum");
		eiColumn.setDescName("盘点科室编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inventoryDeptName");
		eiColumn.setDescName("盘点科室名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("build");
		eiColumn.setDescName("楼");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("floor");
		eiColumn.setDescName("层");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inventorySpotNum");
		eiColumn.setDescName("盘点地点");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inventorySpotName");
		eiColumn.setDescName("盘点地点");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("singleSystenDate");
		eiColumn.setDescName("制单日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("singleSystemMen");
		eiColumn.setDescName("制单人员");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("singleSystemName");
		eiColumn.setDescName("制单人员名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("checkDate");
		eiColumn.setDescName("审核日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("checkMen");
		eiColumn.setDescName("审核人员");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("checkMenName");
		eiColumn.setDescName("审核人员");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("remark");
		eiColumn.setDescName("备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("statusCode");
		eiColumn.setDescName("0/录入；1/确认");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("afterInvenNum");
		eiColumn.setDescName("盘库后数量");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("beforeInvenNum");
		eiColumn.setDescName("盘库前数量");
		eiMetadata.addMeta(eiColumn);


	}

	/**
	 * the constructor
	 */
	public FaInventoryDO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产盘点表主键
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 固定资产盘点表主键
	 */
	public void setId(String id) {
		this.id = id;
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
	 * get the recCreateName - 创建人
	 * @return the recCreateName
	 */
	public String getRecCreateName() {
		return this.recCreateName;
	}

	/**
	 * set the recCreateName - 创建人
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
	public void setRecRevisor(String recRevisor) {
		this.recRevisor = recRevisor;
	}

	/**
	 * get the recReviseName - 修改人
	 * @return the recReviseName
	 */
	public String getRecReviseName() {
		return this.recReviseName;
	}

	/**
	 * set the recReviseName - 修改人
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
	 * get the inventoryNo - 盘点单号
	 * @return the inventoryNo
	 */
	public String getInventoryNo() {
		return this.inventoryNo;
	}

	/**
	 * set the inventoryNo - 盘点单号
	 */
	public void setInventoryNo(String inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	/**
	 * get the inventoryDeptNum - 盘点科室编码
	 * @return the inventoryDeptNum
	 */
	public String getInventoryDeptNum() {
		return this.inventoryDeptNum;
	}

	/**
	 * set the inventoryDeptNum - 盘点科室编码
	 */
	public void setInventoryDeptNum(String inventoryDeptNum) {
		this.inventoryDeptNum = inventoryDeptNum;
	}

	/**
	 * get the inventoryDeptName - 盘点科室名称
	 * @return the inventoryDeptName
	 */
	public String getInventoryDeptName() {
		return this.inventoryDeptName;
	}

	/**
	 * set the inventoryDeptName - 盘点科室名称
	 */
	public void setInventoryDeptName(String inventoryDeptName) {
		this.inventoryDeptName = inventoryDeptName;
	}

	/**
	 * get the build - 楼
	 * @return the build
	 */
	public String getBuild() {
		return this.build;
	}

	/**
	 * set the build - 楼
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/**
	 * get the floor - 层
	 * @return the floor
	 */
	public String getFloor() {
		return this.floor;
	}

	/**
	 * set the floor - 层
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	/**
	 * get the inventorySpotNum - 盘点地点
	 * @return the inventorySpotNum
	 */
	public String getInventorySpotNum() {
		return this.inventorySpotNum;
	}

	/**
	 * set the inventorySpotNum - 盘点地点
	 */
	public void setInventorySpotNum(String inventorySpotNum) {
		this.inventorySpotNum = inventorySpotNum;
	}

	/**
	 * get the inventorySpotName - 盘点地点
	 * @return the inventorySpotName
	 */
	public String getInventorySpotName() {
		return this.inventorySpotName;
	}

	/**
	 * set the inventorySpotName - 盘点地点
	 */
	public void setInventorySpotName(String inventorySpotName) {
		this.inventorySpotName = inventorySpotName;
	}

	/**
	 * get the singleSystenDate - 制单日期
	 * @return the singleSystenDate
	 */
	public Date getSingleSystenDate() {
		return this.singleSystenDate;
	}

	/**
	 * set the singleSystenDate - 制单日期
	 */
	public void setSingleSystenDate(Date singleSystenDate) {
		this.singleSystenDate = singleSystenDate;
	}

	/**
	 * get the singleSystemMen - 制单人员
	 * @return the singleSystemMen
	 */
	public String getSingleSystemMen() {
		return this.singleSystemMen;
	}

	/**
	 * set the singleSystemMen - 制单人员
	 */
	public void setSingleSystemMen(String singleSystemMen) {
		this.singleSystemMen = singleSystemMen;
	}

	/**
	 * get the singleSystemName - 制单人员名称
	 * @return the singleSystemName
	 */
	public String getSingleSystemName() {
		return this.singleSystemName;
	}

	/**
	 * set the singleSystemName - 制单人员名称
	 */
	public void setSingleSystemName(String singleSystemName) {
		this.singleSystemName = singleSystemName;
	}

	/**
	 * get the checkDate - 审核日期
	 * @return the checkDate
	 */
	public Date getCheckDate() {
		return this.checkDate;
	}

	/**
	 * set the checkDate - 审核日期
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * get the checkMen - 审核人员
	 * @return the checkMen
	 */
	public String getCheckMen() {
		return this.checkMen;
	}

	/**
	 * set the checkMen - 审核人员
	 */
	public void setCheckMen(String checkMen) {
		this.checkMen = checkMen;
	}

	/**
	 * get the checkMenName - 审核人员
	 * @return the checkMenName
	 */
	public String getCheckMenName() {
		return this.checkMenName;
	}

	/**
	 * set the checkMenName - 审核人员
	 */
	public void setCheckMenName(String checkMenName) {
		this.checkMenName = checkMenName;
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
	 * get the statusCode - 0/录入；1/确认
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set the statusCode - 0/录入；1/确认
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * get the afterInvenNum - 盘库后数量
	 * @return the afterInvenNum
	 */
	public Integer getAfterInvenNum() {
		return this.afterInvenNum;
	}

	/**
	 * set the afterInvenNum - 盘库后数量
	 */
	public void setAfterInvenNum(Integer afterInvenNum) {
		this.afterInvenNum = afterInvenNum;
	}

	/**
	 * get the beforeInvenNum - 盘库前数量
	 * @return the beforeInvenNum
	 */
	public Integer getBeforeInvenNum() {
		return this.beforeInvenNum;
	}

	/**
	 * set the beforeInvenNum - 盘库前数量
	 */
	public void setBeforeInvenNum(Integer beforeInvenNum) {
		this.beforeInvenNum = beforeInvenNum;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
		setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
		setRecCreateTime(DateUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
		setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
		setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
		setRecReviseTime(DateUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
		setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
		setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
		setInventoryNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryNo")), inventoryNo));
		setInventoryDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryDeptNum")), inventoryDeptNum));
		setInventoryDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryDeptName")), inventoryDeptName));
		setBuild(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("build")), build));
		setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
		setInventorySpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventorySpotNum")), inventorySpotNum));
		setInventorySpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventorySpotName")), inventorySpotName));
		setSingleSystenDate(DateUtils.toDate(StringUtils.toString(map.get("singleSystenDate"))));
		setSingleSystemMen(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("singleSystemMen")), singleSystemMen));
		setSingleSystemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("singleSystemName")), singleSystemName));
		setCheckDate(DateUtils.toDate(StringUtils.toString(map.get("checkDate"))));
		setCheckMen(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkMen")), checkMen));
		setCheckMenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkMenName")), checkMenName));
		setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
		setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
		setAfterInvenNum(NumberUtils.toInteger(StringUtils.toString(map.get("afterInvenNum")), afterInvenNum));
		setBeforeInvenNum(NumberUtils.toInteger(StringUtils.toString(map.get("beforeInvenNum")), beforeInvenNum));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
		map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
		map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
		map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
		map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
		map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
		map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
		map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
		map.put("inventoryNo",StringUtils.toString(inventoryNo, eiMetadata.getMeta("inventoryNo")));
		map.put("inventoryDeptNum",StringUtils.toString(inventoryDeptNum, eiMetadata.getMeta("inventoryDeptNum")));
		map.put("inventoryDeptName",StringUtils.toString(inventoryDeptName, eiMetadata.getMeta("inventoryDeptName")));
		map.put("build",StringUtils.toString(build, eiMetadata.getMeta("build")));
		map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
		map.put("inventorySpotNum",StringUtils.toString(inventorySpotNum, eiMetadata.getMeta("inventorySpotNum")));
		map.put("inventorySpotName",StringUtils.toString(inventorySpotName, eiMetadata.getMeta("inventorySpotName")));
		map.put("singleSystenDate",StringUtils.toString(singleSystenDate, eiMetadata.getMeta("singleSystenDate")));
		map.put("singleSystemMen",StringUtils.toString(singleSystemMen, eiMetadata.getMeta("singleSystemMen")));
		map.put("singleSystemName",StringUtils.toString(singleSystemName, eiMetadata.getMeta("singleSystemName")));
		map.put("checkDate",StringUtils.toString(checkDate, eiMetadata.getMeta("checkDate")));
		map.put("checkMen",StringUtils.toString(checkMen, eiMetadata.getMeta("checkMen")));
		map.put("checkMenName",StringUtils.toString(checkMenName, eiMetadata.getMeta("checkMenName")));
		map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
		map.put("afterInvenNum",StringUtils.toString(afterInvenNum, eiMetadata.getMeta("afterInvenNum")));
		map.put("beforeInvenNum",StringUtils.toString(beforeInvenNum, eiMetadata.getMeta("beforeInvenNum")));
		return map;
	}
}