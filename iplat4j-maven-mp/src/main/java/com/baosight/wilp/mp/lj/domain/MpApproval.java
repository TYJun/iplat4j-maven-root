package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.utils.UUID;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 审批履历表
 * RmApproval
 * @author fangjian
 */
public class MpApproval extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 关联ID，年度/临时/月度/领用等表主键*/
    private String relateId;

    /** 状态(0=失效,1=有效)*/
    private String status;


    /** 审批结果编码*/
    private String approvalResultCode;

    /** 审批结果名称*/
    private String approvalResultName;

    /** 驳回原因*/
    private String rejectReason;

    /** 审批人工号*/
    private String approver;

    /** 审批人姓名*/
    private String approverName;

    /***/
    private Date approvalTime ;


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relateId");
        eiColumn.setDescName("关联ID，年度/临时/月度/领用等表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态(0=失效,1=有效)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态(0=失效,1=有效)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approvalResultCode");
        eiColumn.setDescName("审批结果编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("approvalResultName");
        eiColumn.setDescName("审批结果名称");
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

        eiColumn = new EiColumn("approvalTimeStr");
        eiColumn.setDescName("审批时间");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public MpApproval() {
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
     * get the relateId - 关联ID，年度/临时/月度/领用等表主键
     * @return the relateId
     */
    public String getRelateId() {
        return this.relateId;
    }

    /**
     * set the relateId - 关联ID，年度/临时/月度/领用等表主键
     */
    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    /**
     * get the status - 状态(0=失效,1=有效)
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态(0=失效,1=有效)
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * get the approvalResultCode - 审批结果编码
     * @return the approvalResultCode
     */
    public String getApprovalResultCode() {
        return this.approvalResultCode;
    }

    /**
     * set the approvalResultCode - 审批结果编码
     */
    public void setApprovalResultCode(String approvalResultCode) {
        this.approvalResultCode = approvalResultCode;
    }

    /**
     * get the approvalResultName - 审批结果名称
     * @return the approvalResultName
     */
    public String getApprovalResultName() {
        return this.approvalResultName;
    }

    /**
     * set the approvalResultName - 审批结果名称
     */
    public void setApprovalResultName(String approvalResultName) {
        this.approvalResultName = approvalResultName;
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
    public Date getApprovalTime() {
        return this.approvalTime;
    }

    /**
     * set the approvalTime - 审批时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }




    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setRelateId(MpUtils.toString(map.get("relateId"), relateId));
        setStatus(MpUtils.toString(map.get("status"), status));
        setApprovalResultCode(MpUtils.toString(map.get("approvalResultCode"), approvalResultCode));
        setApprovalResultName(MpUtils.toString(map.get("approvalResultName"), approvalResultName));
        setRejectReason(MpUtils.toString(map.get("rejectReason"), rejectReason));
        setApprover(MpUtils.toString(map.get("approver"), approver));
        setApproverName(MpUtils.toString(map.get("approverName"), approverName));
        setApprovalTime(MpUtils.toDate(StringUtils.toString(map.get("approvalTime"))));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("relateId",StringUtils.toString(relateId, eiMetadata.getMeta("relateId")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("approvalResultCode",StringUtils.toString(approvalResultCode, eiMetadata.getMeta("approvalResultCode")));
        map.put("approvalResultName",StringUtils.toString(approvalResultName, eiMetadata.getMeta("approvalResultName")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("approver",StringUtils.toString(approver, eiMetadata.getMeta("approver")));
        map.put("approverName",StringUtils.toString(approverName, eiMetadata.getMeta("approverName")));
        map.put("approvalTime",StringUtils.toString(approvalTime, eiMetadata.getMeta("approvalTime")));
        return map;
    }


    /**
     * 构建审批履历对象
     * @Title: getInstance
     * @param relateId relateId
     * @param approvalResultCode approvalResultCode
     * @param approvalResultName approvalResultName
     * @param rejectReason rejectReason
     * @return void
     * @throws
     **/
    public static MpApproval getInstance(String relateId, String approvalResultCode, String approvalResultName, String rejectReason) {
        MpApproval approval = new MpApproval();
        approval.setId(UUID.randomUUID().toString());
        approval.setRelateId(relateId);
        approval.setApprovalResultCode(approvalResultCode);
        approval.setApprovalResultName(approvalResultName);
        approval.setRejectReason(rejectReason);
        approval.setApprover(UserSession.getLoginName());
        approval.setApproverName(UserSession.getLoginCName());
        approval.setApprovalTime(new Date());
        return approval;
    }
}