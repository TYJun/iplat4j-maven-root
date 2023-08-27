/**
* Generate time : 2021-11-23 15:05:25
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cs.re.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CsGdLc 保洁工单流程表主体
* cs_gd_lc
*/
public class CsGdLc extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 保洁工单流程主键（UUID）*/
    private String taskId = " ";		/* 保洁工单主表主键*/
    private String statusCode = " ";		/* 工单状态(01待确认，02待完工，03待评价，98已撤销，99已结束)*/
    private String statusCodeMean = " ";		/* 工单状态含义*/
    private String operationUserNo = " ";		/* 操作人工号*/
    private String operationUserName = " ";		/* 操作人名称*/
    private String operationTime = " ";		/* 操作时间*/
    private String datagroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("保洁工单流程主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskId");
        eiColumn.setDescName("保洁工单主表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("工单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCodeMean");
        eiColumn.setDescName("工单状态含义");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationUserNo");
        eiColumn.setDescName("操作人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationUserName");
        eiColumn.setDescName("操作人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CsGdLc() {
        initMetaData();
    }

    /**
     * get the id - 保洁工单流程主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 保洁工单流程主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the taskId - 保洁工单主表主键
     * @return the taskId
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * set the taskId - 保洁工单主表主键
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * get the statusCode - 工单状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 工单状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the statusCodeMean - 工单状态含义
     * @return the statusCodeMean
     */
    public String getStatusCodeMean() {
        return this.statusCodeMean;
    }

    /**
     * set the statusCodeMean - 工单状态含义
     */
    public void setStatusCodeMean(String statusCodeMean) {
        this.statusCodeMean = statusCodeMean;
    }

    /**
     * get the operationUserNo - 操作人工号
     * @return the operationUserNo
     */
    public String getOperationUserNo() {
        return this.operationUserNo;
    }

    /**
     * set the operationUserNo - 操作人工号
     */
    public void setOperationUserNo(String operationUserNo) {
        this.operationUserNo = operationUserNo;
    }

    /**
     * get the operationUserName - 操作人名称
     * @return the operationUserName
     */
    public String getOperationUserName() {
        return this.operationUserName;
    }

    /**
     * set the operationUserName - 操作人名称
     */
    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    /**
     * get the operationTime - 操作时间
     * @return the operationTime
     */
    public String getOperationTime() {
        return this.operationTime;
    }

    /**
     * set the operationTime - 操作时间
     */
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTaskId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskId")), taskId));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setStatusCodeMean(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCodeMean")), statusCodeMean));
        setOperationUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationUserNo")), operationUserNo));
        setOperationUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationUserName")), operationUserName));
        setOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime")), operationTime));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("taskId",StringUtils.toString(taskId, eiMetadata.getMeta("taskId")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusCodeMean",StringUtils.toString(statusCodeMean, eiMetadata.getMeta("statusCodeMean")));
        map.put("operationUserNo",StringUtils.toString(operationUserNo, eiMetadata.getMeta("operationUserNo")));
        map.put("operationUserName",StringUtils.toString(operationUserName, eiMetadata.getMeta("operationUserName")));
        map.put("operationTime",StringUtils.toString(operationTime, eiMetadata.getMeta("operationTime")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        return map;
    }
}