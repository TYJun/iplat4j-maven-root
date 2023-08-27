/**
* Generate time : 2021-03-23 14:36:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* AccompanyHistory
* 
*/
public class AccompanyHistory extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String accompanyId = " ";		
    private String recCreateNo = " ";		
    private String recCreateTime = " ";		
    private String processNode = " ";		/* 流程节点*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processNode");
        eiColumn.setDescName("流程节点");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public AccompanyHistory() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the accompanyId 
     * @return the accompanyId
     */
    public String getAccompanyId() {
        return this.accompanyId;
    }

    /**
     * set the accompanyId 
     */
    public void setAccompanyId(String accompanyId) {
        this.accompanyId = accompanyId;
    }

    /**
     * get the recCreateNo 
     * @return the recCreateNo
     */
    public String getRecCreateNo() {
        return this.recCreateNo;
    }

    /**
     * set the recCreateNo 
     */
    public void setRecCreateNo(String recCreateNo) {
        this.recCreateNo = recCreateNo;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the processNode - 流程节点
     * @return the processNode
     */
    public String getProcessNode() {
        return this.processNode;
    }

    /**
     * set the processNode - 流程节点
     */
    public void setProcessNode(String processNode) {
        this.processNode = processNode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setAccompanyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyId")), accompanyId));
        setRecCreateNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateNo")), recCreateNo));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setProcessNode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processNode")), processNode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("accompanyId",StringUtils.toString(accompanyId, eiMetadata.getMeta("accompanyId")));
        map.put("recCreateNo",StringUtils.toString(recCreateNo, eiMetadata.getMeta("recCreateNo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("processNode",StringUtils.toString(processNode, eiMetadata.getMeta("processNode")));
        return map;
    }
}