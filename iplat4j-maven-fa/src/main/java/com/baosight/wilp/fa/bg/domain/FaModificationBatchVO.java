/**
* Generate time : 2022-12-06 11:30:33
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.bg.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaModificationBatchVO
* 变更批次表
*/
public class FaModificationBatchVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id;		/* 资产变更批次表主键*/
    private String creatorName;		/* 创建人*/
    private Date createTime;	/* 创建时间*/
    private String type;		/* 类型(000-大类变更,100-信息变更,200-价值变更)*/
    private String changeReason;		/* 变更理由*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("资产变更批次表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setDescName("类型(000-大类变更,100-信息变更,200-价值变更)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("changeReason");
        eiColumn.setDescName("变更理由");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaModificationBatchVO() {
        initMetaData();
    }

    /**
     * get the id - 资产变更批次表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 资产变更批次表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the creatorName - 创建人
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * get the type - 类型(000-大类变更,100-信息变更,200-价值变更)
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type - 类型(000-大类变更,100-信息变更,200-价值变更)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the changeReason - 变更理由
     * @return the changeReason
     */
    public String getChangeReason() {
        return this.changeReason;
    }

    /**
     * set the changeReason - 变更理由
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreateTime(DateUtils.toDate(StringUtils.toString(map.get("createTime"))));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setChangeReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeReason")), changeReason));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("type",StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("changeReason",StringUtils.toString(changeReason, eiMetadata.getMeta("changeReason")));
        return map;
    }
}