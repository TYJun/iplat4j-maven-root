/**
 * Generate time : 2022-12-10 15:15:30
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.fa.db.domain;

import com.baosight.iplat4j.core.util.DateUtils;

import java.sql.Timestamp;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

/**
 * FaTransfer
 */
public class FaTransferVO extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id = " ";        /* 固定资产调拨表主键*/
	private String transferNo = " ";        /* 调拨单号*/
	private String applyDeptNum = " ";        /* 申请科室*/
	private String applyDeptName = " ";        /* 申请科室*/
	private String applyPerson = " ";        /* 申请人*/
	private Timestamp applyTime;        /* 申请时间*/
	private String applyReason = " ";        /* 申请原因*/
	private String applyFileCode;
	private String confirmDeptNum = " ";        /* 确认科室*/
	private String confirmDeptName = " ";        /* 确认科室*/
	private String confirmBuild = " ";        /* 楼*/
	private String confirmFloor = " ";        /* 层*/
	private String confirmRoom = " ";        /* 房间*/
	private String confirmLocationNum = " ";        /* 地点*/
	private String confirmLocationName = " ";        /* 地点*/
	private String confirmPerson = " ";        /* 确认人*/
	private Timestamp confirmTime;        /* 确认时间*/
	private String confirmReason = " ";        /* 确认理由*/
	private String confirmFileCode;
	private String auditDeptNum = " ";        /* 审批科室*/
	private String auditDeptName = " ";        /* 审批科室*/
	private String auditPerson = " ";        /* 审批人*/
	private Timestamp auditTime;        /* 审批时间*/
	private String auditReason = " ";        /* 审批理由*/
	private String auditFileCode;
	private String transferStatus = " ";        /* 审批状态(00-草稿,10-调入科室待确认,20-资产科待确认,30-资产科确认,40-资产科驳回,50-调入科室驳回)*/
	private String source;

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产调拨表主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("transferNo");
		eiColumn.setDescName("调拨单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyDeptNum");
		eiColumn.setDescName("申请科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyDeptName");
		eiColumn.setDescName("申请科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyPerson");
		eiColumn.setDescName("申请人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyTime");
		eiColumn.setDescName("申请时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyReason");
		eiColumn.setDescName("申请原因");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("applyFileCode");
		eiColumn.setDescName("申请签字");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmDeptNum");
		eiColumn.setDescName("确认科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmDeptName");
		eiColumn.setDescName("确认科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmBuild");
		eiColumn.setDescName("楼");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmFloor");
		eiColumn.setDescName("层");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmRoom");
		eiColumn.setDescName("房间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmLocationNum");
		eiColumn.setDescName("地点");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmLocationName");
		eiColumn.setDescName("地点");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmPerson");
		eiColumn.setDescName("确认人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmTime");
		eiColumn.setDescName("确认时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmReason");
		eiColumn.setDescName("确认理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("confirmFileCode");
		eiColumn.setDescName("确认签字");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditDeptNum");
		eiColumn.setDescName("审批科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditDeptName");
		eiColumn.setDescName("审批科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditPerson");
		eiColumn.setDescName("审批人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditTime");
		eiColumn.setDescName("审批时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditReason");
		eiColumn.setDescName("审批理由");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("auditFileCode");
		eiColumn.setDescName("审批签字");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("transferStatus");
		eiColumn.setDescName("审批状态(00-草稿,10-调入科室待确认,20-资产科待确认,30-资产科确认,40-资产科驳回,50-调入科室驳回)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("source");
		eiColumn.setDescName("来源");
		eiMetadata.addMeta(eiColumn);
	}

	/**
	 * the constructor
	 */
	public FaTransferVO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产调拨表主键
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 固定资产调拨表主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get the transferNo - 调拨单号
	 *
	 * @return the transferNo
	 */
	public String getTransferNo() {
		return this.transferNo;
	}

	/**
	 * set the transferNo - 调拨单号
	 */
	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	/**
	 * get the applyDeptNum - 申请科室
	 *
	 * @return the applyDeptNum
	 */
	public String getApplyDeptNum() {
		return this.applyDeptNum;
	}

	/**
	 * set the applyDeptNum - 申请科室
	 */
	public void setApplyDeptNum(String applyDeptNum) {
		this.applyDeptNum = applyDeptNum;
	}

	/**
	 * get the applyDeptName - 申请科室
	 *
	 * @return the applyDeptName
	 */
	public String getApplyDeptName() {
		return this.applyDeptName;
	}

	/**
	 * set the applyDeptName - 申请科室
	 */
	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	/**
	 * get the applyPerson - 申请人
	 *
	 * @return the applyPerson
	 */
	public String getApplyPerson() {
		return this.applyPerson;
	}

	/**
	 * set the applyPerson - 申请人
	 */
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	/**
	 * get the applyTime - 申请时间
	 *
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return this.applyTime;
	}

	/**
	 * set the applyTime - 申请时间
	 */
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * get the applyReason - 申请原因
	 *
	 * @return the applyReason
	 */
	public String getApplyReason() {
		return this.applyReason;
	}

	/**
	 * set the applyReason - 申请原因
	 */
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getApplyFileCode() {
		return applyFileCode;
	}

	public void setApplyFileCode(String applyFileCode) {
		this.applyFileCode = applyFileCode;
	}

	/**
	 * get the confirmDeptNum - 确认科室
	 *
	 * @return the confirmDeptNum
	 */
	public String getConfirmDeptNum() {
		return this.confirmDeptNum;
	}

	/**
	 * set the confirmDeptNum - 确认科室
	 */
	public void setConfirmDeptNum(String confirmDeptNum) {
		this.confirmDeptNum = confirmDeptNum;
	}

	/**
	 * get the confirmDeptName - 确认科室
	 *
	 * @return the confirmDeptName
	 */
	public String getConfirmDeptName() {
		return this.confirmDeptName;
	}

	/**
	 * set the confirmDeptName - 确认科室
	 */
	public void setConfirmDeptName(String confirmDeptName) {
		this.confirmDeptName = confirmDeptName;
	}

	/**
	 * get the confirmBuild - 楼
	 *
	 * @return the confirmBuild
	 */
	public String getConfirmBuild() {
		return this.confirmBuild;
	}

	/**
	 * set the confirmBuild - 楼
	 */
	public void setConfirmBuild(String confirmBuild) {
		this.confirmBuild = confirmBuild;
	}

	/**
	 * get the confirmFloor - 层
	 *
	 * @return the confirmFloor
	 */
	public String getConfirmFloor() {
		return this.confirmFloor;
	}

	/**
	 * set the confirmFloor - 层
	 */
	public void setConfirmFloor(String confirmFloor) {
		this.confirmFloor = confirmFloor;
	}

	public String getConfirmRoom() {
		return confirmRoom;
	}

	public void setConfirmRoom(String confirmRoom) {
		this.confirmRoom = confirmRoom;
	}

	/**
	 * get the confirmLocationNum - 地点
	 *
	 * @return the confirmLocationNum
	 */
	public String getConfirmLocationNum() {
		return this.confirmLocationNum;
	}

	/**
	 * set the confirmLocationNum - 地点
	 */
	public void setConfirmLocationNum(String confirmLocationNum) {
		this.confirmLocationNum = confirmLocationNum;
	}

	/**
	 * get the confirmLocationName - 地点
	 *
	 * @return the confirmLocationName
	 */
	public String getConfirmLocationName() {
		return this.confirmLocationName;
	}

	/**
	 * set the confirmLocationName - 地点
	 */
	public void setConfirmLocationName(String confirmLocationName) {
		this.confirmLocationName = confirmLocationName;
	}

	/**
	 * get the confirmPerson - 确认人
	 *
	 * @return the confirmPerson
	 */
	public String getConfirmPerson() {
		return this.confirmPerson;
	}

	/**
	 * set the confirmPerson - 确认人
	 */
	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	/**
	 * get the confirmTime - 确认时间
	 *
	 * @return the confirmTime
	 */
	public Date getConfirmTime() {
		return this.confirmTime;
	}

	/**
	 * set the confirmTime - 确认时间
	 */
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}

	/**
	 * get the confirmReason - 确认理由
	 *
	 * @return the confirmReason
	 */
	public String getConfirmReason() {
		return this.confirmReason;
	}

	/**
	 * set the confirmReason - 确认理由
	 */
	public void setConfirmReason(String confirmReason) {
		this.confirmReason = confirmReason;
	}

	public String getConfirmFileCode() {
		return confirmFileCode;
	}

	public void setConfirmFileCode(String confirmFileCode) {
		this.confirmFileCode = confirmFileCode;
	}

	/**
	 * get the auditDeptNum - 审批科室
	 *
	 * @return the auditDeptNum
	 */
	public String getAuditDeptNum() {
		return this.auditDeptNum;
	}

	/**
	 * set the auditDeptNum - 审批科室
	 */
	public void setAuditDeptNum(String auditDeptNum) {
		this.auditDeptNum = auditDeptNum;
	}

	/**
	 * get the auditDeptName - 审批科室
	 *
	 * @return the auditDeptName
	 */
	public String getAuditDeptName() {
		return this.auditDeptName;
	}

	/**
	 * set the auditDeptName - 审批科室
	 */
	public void setAuditDeptName(String auditDeptName) {
		this.auditDeptName = auditDeptName;
	}

	/**
	 * get the auditPerson - 审批人
	 *
	 * @return the auditPerson
	 */
	public String getAuditPerson() {
		return this.auditPerson;
	}

	/**
	 * set the auditPerson - 审批人
	 */
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	/**
	 * get the auditTime - 审批时间
	 *
	 * @return the auditTime
	 */
	public Date getAuditTime() {
		return this.auditTime;
	}

	/**
	 * set the auditTime - 审批时间
	 */
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * get the auditReason - 审批理由
	 *
	 * @return the auditReason
	 */
	public String getAuditReason() {
		return this.auditReason;
	}

	/**
	 * set the auditReason - 审批理由
	 */
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public String getAuditFileCode() {
		return auditFileCode;
	}

	public void setAuditFileCode(String auditFileCode) {
		this.auditFileCode = auditFileCode;
	}

	/**
	 * get the transferStatus - 审批状态(00-草稿,10-调入科室待确认,20-资产科待确认,30-资产科确认,40-资产科驳回,50-调入科室驳回)
	 *
	 * @return the transferStatus
	 */
	public String getTransferStatus() {
		return this.transferStatus;
	}

	/**
	 * set the transferStatus - 审批状态(00-草稿,10-调入科室待确认,20-资产科待确认,30-资产科确认,40-资产科驳回,50-调入科室驳回)
	 */
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setTransferNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transferNo")), transferNo));
		setApplyDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyDeptNum")), applyDeptNum));
		setApplyDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyDeptName")), applyDeptName));
		setApplyPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyPerson")), applyPerson));
		setApplyTime(OneSelfUtils.defaultTimestamp(map.get("applyTime")));
		setApplyReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyReason")), applyReason));
		setApplyFileCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyFileCode")), applyFileCode));
		setConfirmDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmDeptNum")), confirmDeptNum));
		setConfirmDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmDeptName")), confirmDeptName));
		setConfirmBuild(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmBuild")), confirmBuild));
		setConfirmFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmFloor")), confirmFloor));
		setConfirmRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmRoom")), confirmRoom));
		setConfirmLocationNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmLocationNum")), confirmLocationNum));
		setConfirmLocationName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmLocationName")), confirmLocationName));
		setConfirmPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmPerson")), confirmPerson));
		setConfirmTime(OneSelfUtils.defaultTimestamp(StringUtils.toString(map.get("confirmTime"))));
		setConfirmReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmReason")), confirmReason));
		setConfirmFileCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmFileCode")), confirmFileCode));
		setAuditDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditDeptNum")), auditDeptNum));
		setAuditDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditDeptName")), auditDeptName));
		setAuditPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditPerson")), auditPerson));
		setAuditTime(OneSelfUtils.defaultTimestamp(StringUtils.toString(map.get("auditTime"))));
		setAuditReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditReason")), auditReason));
		setAuditFileCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditFileCode")), auditFileCode));
		setTransferStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transferStatus")), transferStatus));
		setSource(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("source")), source));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("transferNo", StringUtils.toString(transferNo, eiMetadata.getMeta("transferNo")));
		map.put("applyDeptNum", StringUtils.toString(applyDeptNum, eiMetadata.getMeta("applyDeptNum")));
		map.put("applyDeptName", StringUtils.toString(applyDeptName, eiMetadata.getMeta("applyDeptName")));
		map.put("applyPerson", StringUtils.toString(applyPerson, eiMetadata.getMeta("applyPerson")));
		map.put("applyTime", StringUtils.toString(applyTime, eiMetadata.getMeta("applyTime")));
		map.put("applyReason", StringUtils.toString(applyReason, eiMetadata.getMeta("applyReason")));
		map.put("applyFileCode", StringUtils.toString(applyFileCode, eiMetadata.getMeta("applyFileCode")));
		map.put("confirmDeptNum", StringUtils.toString(confirmDeptNum, eiMetadata.getMeta("confirmDeptNum")));
		map.put("confirmDeptName", StringUtils.toString(confirmDeptName, eiMetadata.getMeta("confirmDeptName")));
		map.put("confirmBuild", StringUtils.toString(confirmBuild, eiMetadata.getMeta("confirmBuild")));
		map.put("confirmFloor", StringUtils.toString(confirmFloor, eiMetadata.getMeta("confirmFloor")));
		map.put("confirmRoom", StringUtils.toString(confirmRoom, eiMetadata.getMeta("confirmRoom")));
		map.put("confirmLocationNum", StringUtils.toString(confirmLocationNum, eiMetadata.getMeta("confirmLocationNum")));
		map.put("confirmLocationName", StringUtils.toString(confirmLocationName, eiMetadata.getMeta("confirmLocationName")));
		map.put("confirmPerson", StringUtils.toString(confirmPerson, eiMetadata.getMeta("confirmPerson")));
		map.put("confirmTime", StringUtils.toString(confirmTime, eiMetadata.getMeta("confirmTime")));
		map.put("confirmReason", StringUtils.toString(confirmReason, eiMetadata.getMeta("confirmReason")));
		map.put("confirmFileCode", StringUtils.toString(confirmFileCode, eiMetadata.getMeta("confirmFileCode")));
		map.put("auditDeptNum", StringUtils.toString(auditDeptNum, eiMetadata.getMeta("auditDeptNum")));
		map.put("auditDeptName", StringUtils.toString(auditDeptName, eiMetadata.getMeta("auditDeptName")));
		map.put("auditPerson", StringUtils.toString(auditPerson, eiMetadata.getMeta("auditPerson")));
		map.put("auditTime", StringUtils.toString(auditTime, eiMetadata.getMeta("auditTime")));
		map.put("auditReason", StringUtils.toString(auditReason, eiMetadata.getMeta("auditReason")));
		map.put("auditFileCode", StringUtils.toString(auditFileCode, eiMetadata.getMeta("auditFileCode")));
		map.put("transferStatus", StringUtils.toString(transferStatus, eiMetadata.getMeta("transferStatus")));
		map.put("source", StringUtils.toString(source, eiMetadata.getMeta("source")));
		return map;
	}
}