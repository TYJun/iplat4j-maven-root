/**
* Generate time : 2022-07-13 10:28:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* HiApproval
* 
*/
public class HiApproval extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String applyNo = " ";		/* 标识申请ID*/
    private String status = " ";		/* 状态（00=无效，01=有效）*/
    private String approvalResult = " ";		/* 审批结果（通过，驳回）*/
    private String rejectReason = " ";		/* 驳回原因*/
    private String approver = " ";		/* 审批人工号*/
    private String approverName = " ";		/* 审批人姓名*/
    private String approvalTime = " ";		/* 审批时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyNo");
        eiColumn.setDescName("标识申请ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态（00=无效，01=有效）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approvalResult");
        eiColumn.setDescName("审批结果（通过，驳回）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("驳回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approver");
        eiColumn.setDescName("审批人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approverName");
        eiColumn.setDescName("审批人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approvalTime");
        eiColumn.setDescName("审批时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HiApproval() {
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
     * get the applyNo - 标识申请ID
     * @return the applyNo
     */
    public String getApplyNo() {
        return this.applyNo;
    }

    /**
     * set the applyNo - 标识申请ID
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    /**
     * get the status - 状态（00=无效，01=有效）
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态（00=无效，01=有效）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the approvalResult - 审批结果（通过，驳回）
     * @return the approvalResult
     */
    public String getApprovalResult() {
        return this.approvalResult;
    }

    /**
     * set the approvalResult - 审批结果（通过，驳回）
     */
    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    /**
     * get the rejectReason - 驳回原因
     * @return the rejectReason
     */
    public String getRejectReason() {
        return this.rejectReason;
    }

    /**
     * set the rejectReason - 驳回原因
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    /**
     * get the approver - 审批人工号
     * @return the approver
     */
    public String getApprover() {
        return this.approver;
    }

    /**
     * set the approver - 审批人工号
     */
    public void setApprover(String approver) {
        this.approver = approver;
    }

    /**
     * get the approverName - 审批人姓名
     * @return the approverName
     */
    public String getApproverName() {
        return this.approverName;
    }

    /**
     * set the approverName - 审批人姓名
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    /**
     * get the approvalTime - 审批时间
     * @return the approvalTime
     */
    public String getApprovalTime() {
        return this.approvalTime;
    }

    /**
     * set the approvalTime - 审批时间
     */
    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setApplyNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyNo")), applyNo));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setApprovalResult(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approvalResult")), approvalResult));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setApprover(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approver")), approver));
        setApproverName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approverName")), approverName));
        setApprovalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approvalTime")), approvalTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("applyNo",StringUtils.toString(applyNo, eiMetadata.getMeta("applyNo")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("approvalResult",StringUtils.toString(approvalResult, eiMetadata.getMeta("approvalResult")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("approver",StringUtils.toString(approver, eiMetadata.getMeta("approver")));
        map.put("approverName",StringUtils.toString(approverName, eiMetadata.getMeta("approverName")));
        map.put("approvalTime",StringUtils.toString(approvalTime, eiMetadata.getMeta("approvalTime")));
        return map;
    }
}