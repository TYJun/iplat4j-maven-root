/**
* Generate time : 2022-06-17 1:02:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.mc.jk.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class DepartMent extends DaoEPBase {

    private static final long serialVersionUID = 1L;


    private String id = " ";
    private String BIZ_ID = " ";
    private String DEP_CODE = " ";
    private String DEP_NAME = " ";


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEP_CODE");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEP_NAME");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("BIZ_ID");
        eiColumn.setDescName("唯一标识");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public DepartMent() {
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


    public String getDEP_CODE() {
        return this.DEP_CODE;
    }

    public void setDEP_CODE(String DEP_CODE) {
        this.DEP_CODE = DEP_CODE;
    }


    public String getDEP_NAME() {
        return this.DEP_NAME;
    }


    public void setDEP_NAME(String DEP_NAME) {
        this.DEP_NAME = DEP_NAME;
    }

    public String getBIZ_ID() {
        return this.BIZ_ID;
    }


    public void setBIZ_ID(String BIZ_ID) {
        this.BIZ_ID = BIZ_ID;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDEP_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEP_CODE")), DEP_CODE));
        setDEP_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEP_NAME")), DEP_NAME));
        setBIZ_ID(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("BIZ_ID")), BIZ_ID));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("DEP_CODE",StringUtils.toString(DEP_CODE, eiMetadata.getMeta("DEP_CODE")));
        map.put("DEP_NAME",StringUtils.toString(DEP_NAME, eiMetadata.getMeta("DEP_NAME")));
        map.put("BIZ_ID",StringUtils.toString(BIZ_ID, eiMetadata.getMeta("BIZ_ID")));
        return map;
    }
}