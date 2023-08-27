/**
* Generate time : 2021-03-09 15:40:07
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SHCX02
* sc_menu_description
*/
public class SSBMCpxx02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 描述id*/
    private String relationId = " ";		/* 关联id*/
    private String material = " ";		/* 材料*/
    private String description = " ";		/* 描述*/
    private String quantum = " ";		/* 份量*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("描述id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relationId");
        eiColumn.setDescName("关联id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("material");
        eiColumn.setDescName("材料");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quantum");
        eiColumn.setDescName("份量");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCpxx02() {
        initMetaData();
    }

    /**
     * get the id - 描述id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 描述id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the relationId - 关联id
     * @return the relationId
     */
    public String getRelationId() {
        return this.relationId;
    }

    /**
     * set the relationId - 关联id
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * get the material - 材料
     * @return the material
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * set the material - 材料
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * get the description - 描述
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description - 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the quantum - 份量
     * @return the quantum
     */
    public String getQuantum() {
        return this.quantum;
    }

    /**
     * set the quantum - 份量
     */
    public void setQuantum(String quantum) {
        this.quantum = quantum;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRelationId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("relationId")), relationId));
        setMaterial(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("material")), material));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setQuantum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("quantum")), quantum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("relationId",StringUtils.toString(relationId, eiMetadata.getMeta("relationId")));
        map.put("material",StringUtils.toString(material, eiMetadata.getMeta("material")));
        map.put("description",StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("quantum",StringUtils.toString(quantum, eiMetadata.getMeta("quantum")));
        return map;
    }
}