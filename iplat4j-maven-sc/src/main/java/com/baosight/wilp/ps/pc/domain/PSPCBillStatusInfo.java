package com.baosight.wilp.ps.pc.domain;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * PSPCBillStatusInfo 订单状态变更的实体类
 * 
 * @ClassName: PSPCBillStatusInfo
 */
public class PSPCBillStatusInfo extends DaoEPBase{

    private static final long serialVersionUID = 1L;

    // 业务表名
    private String pboTbName;
    // 业务ID
    private String billId;
    // 更改前状态
    private String beforeStatus;
    // 更改后状态
    private String afterStatus;
    // 下一节点处理人
    private String currentDealer;
    // 更改原因
    private String oprationRoute;
    //作废编号
    private String rejectCode;
    //作废原因
    private String rejectReason;
    /**是否作废0:不作废,1:作废*/
    private String isReject = "0";
    
    private boolean endTask ;
    
    // 业务编码
    private String pboCode;
    // 操作人姓名
    private String creatorName;
    private String handleAdvice;
    // 操作人工号
    private String creatorId;

    private String uuid = " ";		/* 主键，由系统函数生成*/
    
    private String billNo = " ";		/* 单据编号*/
    
    private String statusCode = " ";		/* 操作单据时的单据状态*/
    
    private String operationRoute = " ";		/* 操作路由*/
    
    private Timestamp createDate ;		/* 创建时间*/
    
    private String taskNode = " ";		/* 流程节点描述*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("uuid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键，由系统函数生成");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pboCode");
        eiColumn.setDescName("PBO代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("单据编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("操作单据时的单据状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationRoute");
        eiColumn.setDescName("操作路由");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("handleAdvice");
        eiColumn.setDescName("处理意见");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskNode");
        eiColumn.setDescName("流程节点描述");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("pboTbName");
        eiColumn.setDescName("业务表名");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("billId");
        eiColumn.setDescName(" 业务ID");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("beforeStatus");
        eiColumn.setDescName("更改前状态");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("afterStatus");
        eiColumn.setDescName("更改后状态");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName("下一节点处理人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("oprationRoute");
        eiColumn.setDescName("更改原因");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("rejectCode");
        eiColumn.setDescName("作废编号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("作废原因");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("isReject");
        eiColumn.setDescName("是否作废0:不作废,1:作废");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public PSPCBillStatusInfo() {
        initMetaData();
    }

    /**
     * get the uuid - 主键，由系统函数生成
     * @return the uuid
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * set the uuid - 主键，由系统函数生成
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * get the pboCode - PBO代码
     * @return the pboCode
     */
    public String getPboCode() {
        return this.pboCode;
    }

    /**
     * set the pboCode - PBO代码
     */
    public void setPboCode(String pboCode) {
        this.pboCode = pboCode;
    }

    /**
     * get the billNo - 单据编号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 单据编号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the statusCode - 操作单据时的单据状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 操作单据时的单据状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the operationRoute - 操作路由
     * @return the operationRoute
     */
    public String getOperationRoute() {
        return this.operationRoute;
    }

    /**
     * set the operationRoute - 操作路由
     */
    public void setOperationRoute(String operationRoute) {
        this.operationRoute = operationRoute;
    }

    /**
     * get the handleAdvice - 处理意见
     * @return the handleAdvice
     */
    public String getHandleAdvice() {
        return this.handleAdvice;
    }

    /**
     * set the handleAdvice - 处理意见
     */
    public void setHandleAdvice(String handleAdvice) {
        this.handleAdvice = handleAdvice;
    }

    /**
     * get the creatorId - 创建人ID
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the creatorName - 创建人姓名
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the createDate - 创建时间
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the taskNode - 流程节点描述
     * @return the taskNode
     */
    public String getTaskNode() {
        return this.taskNode;
    }

    /**
     * set the taskNode - 流程节点描述
     */
    public void setTaskNode(String taskNode) {
        this.taskNode = taskNode;
    }

    public String getPboTbName() {
		return pboTbName;
	}

	public void setPboTbName(String pboTbName) {
		this.pboTbName = pboTbName;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}

	public String getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(String afterStatus) {
		this.afterStatus = afterStatus;
	}

	public String getCurrentDealer() {
		return currentDealer;
	}

	public void setCurrentDealer(String currentDealer) {
		this.currentDealer = currentDealer;
	}

	public String getOprationRoute() {
		return oprationRoute;
	}

	public void setOprationRoute(String oprationRoute) {
		this.oprationRoute = oprationRoute;
	}

	public String getRejectCode() {
		return rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getIsReject() {
		return isReject;
	}
	/**是否作废0:不作废,1:作废*/
	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}

    public boolean isEndTask() {
        return endTask;
    }

    public void setEndTask(boolean endTask) {
        this.endTask = endTask;
    }
	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setUuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uuid")), uuid));
        setPboCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pboCode")), pboCode));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setOperationRoute(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationRoute")), operationRoute));
        setHandleAdvice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("handleAdvice")), handleAdvice));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setTaskNode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskNode")), taskNode));
        setPboTbName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pboTbName")), pboTbName));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setBeforeStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("beforeStatus")), beforeStatus));
        setAfterStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("afterStatus")), afterStatus));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setOperationRoute(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oprationRoute")), oprationRoute));
        setRejectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectCode")), rejectCode));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setIsReject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isReject")), isReject));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("uuid",StringUtils.toString(uuid, eiMetadata.getMeta("uuid")));
        map.put("pboCode",StringUtils.toString(pboCode, eiMetadata.getMeta("pboCode")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("operationRoute",StringUtils.toString(operationRoute, eiMetadata.getMeta("operationRoute")));
        map.put("handleAdvice",StringUtils.toString(handleAdvice, eiMetadata.getMeta("handleAdvice")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("taskNode",StringUtils.toString(taskNode, eiMetadata.getMeta("taskNode")));
        map.put("pboTbName",StringUtils.toString(pboTbName, eiMetadata.getMeta("pboTbName")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("beforeStatus",StringUtils.toString(beforeStatus, eiMetadata.getMeta("beforeStatus")));
        map.put("afterStatus",StringUtils.toString(afterStatus, eiMetadata.getMeta("afterStatus")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("oprationRoute",StringUtils.toString(oprationRoute, eiMetadata.getMeta("oprationRoute")));
        map.put("rejectCode",StringUtils.toString(rejectCode, eiMetadata.getMeta("rejectCode")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("isReject",StringUtils.toString(isReject, eiMetadata.getMeta("isReject")));
        return map;
    }

	@Override
	public String toString() {
		return "PSPCBillStatusInfo [pboTbName=" + pboTbName + ", billId=" + billId + ", beforeStatus=" + beforeStatus
				+ ", afterStatus=" + afterStatus + ", currentDealer=" + currentDealer + ", oprationRoute="
				+ oprationRoute + ", rejectCode=" + rejectCode + ", rejectReason=" + rejectReason + ", isReject="
				+ isReject + ", endTask=" + endTask + ", pboCode=" + pboCode + ", creatorName=" + creatorName
				+ ", handleAdvice=" + handleAdvice + ", creatorId=" + creatorId + ", uuid=" + uuid + ", billNo="
				+ billNo + ", statusCode=" + statusCode + ", operationRoute=" + operationRoute + ", createDate="
				+ createDate + ", taskNode=" + taskNode + "]";
	}

}
