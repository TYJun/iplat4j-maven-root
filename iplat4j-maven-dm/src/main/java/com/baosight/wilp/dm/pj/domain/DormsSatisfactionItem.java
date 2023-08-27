/**
* Generate time : 2022-03-28 16:04:34
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsSatisfactionItem
* 
*/
public class DormsSatisfactionItem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String code = " ";		/* 项目编码*/
    private String content = " ";		/* 项目内容*/
    private String moduleId = " ";		/* 分类id*/
    private String memo = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("code");
        eiColumn.setDescName("项目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setDescName("项目内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleId");
        eiColumn.setDescName("分类id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsSatisfactionItem() {
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
     * get the code - 项目编码
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * set the code - 项目编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get the content - 项目内容
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * set the content - 项目内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get the moduleId - 分类id
     * @return the moduleId
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * set the moduleId - 分类id
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setModuleId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleId")), moduleId));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("code",StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("content",StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("moduleId",StringUtils.toString(moduleId, eiMetadata.getMeta("moduleId")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        return map;
    }
}