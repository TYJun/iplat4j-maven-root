/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViAuditLog
* 
*/
public class ViAuditLog extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer id = new Integer(0);		/* 主键，自增长*/
    private Integer visitingId = new Integer(0);		/* 对应的到访记录vi_visting_info(id)*/
    private Timestamp auditTime ;		/* 审批时间*/
    private String auditorMan = "";		/* 审批操作人（工号）*/
    private String auditorIp = "";		/* 审批操作人IP*/
    private String auditorClientType = "";		/* 审批操作终端类型*/
    private String desc = "";		/* 审批说明*/
    private Integer auditFlag = new Integer(0);		/* 审批结果  1=审批通过，-1=审批驳回*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键，自增长");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("visitingId");
        eiColumn.setDescName("对应的到访记录vi_visting_info(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditTime");
        eiColumn.setDescName("审批时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorMan");
        eiColumn.setDescName("审批操作人（工号）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorIp");
        eiColumn.setDescName("审批操作人IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditorClientType");
        eiColumn.setDescName("审批操作终端类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("desc");
        eiColumn.setDescName("审批说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditFlag");
        eiColumn.setDescName("审批结果  1=审批通过，-1=审批驳回");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViAuditLog() {
        initMetaData();
    }

    /**
     * get the id - 主键，自增长
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 主键，自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the visitingId - 对应的到访记录vi_visting_info(id)
     * @return the visitingId
     */
    public Integer getVisitingId() {
        return this.visitingId;
    }

    /**
     * set the visitingId - 对应的到访记录vi_visting_info(id)
     */
    public void setVisitingId(Integer visitingId) {
        this.visitingId = visitingId;
    }

    /**
     * get the auditTime - 审批时间
     * @return the auditTime
     */
    public Timestamp getAuditTime() {
        return this.auditTime;
    }

    /**
     * set the auditTime - 审批时间
     */
    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * get the auditorMan - 审批操作人（工号）
     * @return the auditorMan
     */
    public String getAuditorMan() {
        return this.auditorMan;
    }

    /**
     * set the auditorMan - 审批操作人（工号）
     */
    public void setAuditorMan(String auditorMan) {
        this.auditorMan = auditorMan;
    }

    /**
     * get the auditorIp - 审批操作人IP
     * @return the auditorIp
     */
    public String getAuditorIp() {
        return this.auditorIp;
    }

    /**
     * set the auditorIp - 审批操作人IP
     */
    public void setAuditorIp(String auditorIp) {
        this.auditorIp = auditorIp;
    }

    /**
     * get the auditorClientType - 审批操作终端类型
     * @return the auditorClientType
     */
    public String getAuditorClientType() {
        return this.auditorClientType;
    }

    /**
     * set the auditorClientType - 审批操作终端类型
     */
    public void setAuditorClientType(String auditorClientType) {
        this.auditorClientType = auditorClientType;
    }

    /**
     * get the desc - 审批说明
     * @return the desc
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * set the desc - 审批说明
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * get the auditFlag - 审批结果  1=审批通过，-1=审批驳回
     * @return the auditFlag
     */
    public Integer getAuditFlag() {
        return this.auditFlag;
    }

    /**
     * set the auditFlag - 审批结果  1=审批通过，-1=审批驳回
     */
    public void setAuditFlag(Integer auditFlag) {
        this.auditFlag = auditFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setVisitingId(NumberUtils.toInteger(StringUtils.toString(map.get("visitingId")), visitingId));
        setAuditTime(DateUtils.toTimestamp(StringUtils.toString(map.get("auditTime"))));
        setAuditorMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorMan")), auditorMan));
        setAuditorIp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorIp")), auditorIp));
        setAuditorClientType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditorClientType")), auditorClientType));
        setDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("desc")), desc));
        setAuditFlag(NumberUtils.toInteger(StringUtils.toString(map.get("auditFlag")), auditFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("visitingId",StringUtils.toString(visitingId, eiMetadata.getMeta("visitingId")));
        map.put("auditTime",StringUtils.toString(auditTime, eiMetadata.getMeta("auditTime")));
        map.put("auditorMan",StringUtils.toString(auditorMan, eiMetadata.getMeta("auditorMan")));
        map.put("auditorIp",StringUtils.toString(auditorIp, eiMetadata.getMeta("auditorIp")));
        map.put("auditorClientType",StringUtils.toString(auditorClientType, eiMetadata.getMeta("auditorClientType")));
        map.put("desc",StringUtils.toString(desc, eiMetadata.getMeta("desc")));
        map.put("auditFlag",StringUtils.toString(auditFlag, eiMetadata.getMeta("auditFlag")));
        return map;
    }
}