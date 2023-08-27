package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 需求计划与需求计划汇总关联表实体
 * MpRequireRelation
 * @author fangjian
 */
public class MpRequireRelation extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 需求计划ID*/
    private String requireId;

    /** 需求计划明细ID*/
    private String requireDetailId;

    /** 需求计划汇总ID*/
    private String requireCollectId;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("requireId");
        eiColumn.setDescName("需求计划ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("requireDetailId");
        eiColumn.setDescName("需求计划明细ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("requireCollectId");
        eiColumn.setDescName("需求计划汇总ID");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public MpRequireRelation() {
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
     * get the requireId - 需求计划ID
     * @return the requireId
     */
    public String getRequireId() {
        return this.requireId;
    }

    /**
     * set the requireId - 需求计划ID
     */
    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }

    /**
     * get the requireDetailId - 需求计划明细ID
     * @return the requireDetailId
     */
    public String getRequireDetailId() {
        return this.requireDetailId;
    }

    /**
     * set the requireDetailId - 需求计划明细ID
     */
    public void setRequireDetailId(String requireDetailId) {
        this.requireDetailId = requireDetailId;
    }

    /**
     * get the requireCollectId - 需求计划汇总ID
     * @return the requireCollectId
     */
    public String getRequireCollectId() {
        return this.requireCollectId;
    }

    /**
     * set the requireCollectId - 需求计划汇总ID
     */
    public void setRequireCollectId(String requireCollectId) {
        this.requireCollectId = requireCollectId;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRequireId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requireId")), requireId));
        setRequireDetailId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requireDetailId")), requireDetailId));
        setRequireCollectId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requireCollectId")), requireCollectId));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(4);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("requireId",StringUtils.toString(requireId, eiMetadata.getMeta("requireId")));
        map.put("requireDetailId",StringUtils.toString(requireDetailId, eiMetadata.getMeta("requireDetailId")));
        map.put("requireCollectId",StringUtils.toString(requireCollectId, eiMetadata.getMeta("requireCollectId")));
        return map;
    }
}