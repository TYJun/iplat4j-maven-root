package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SSACPer01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String zao = " ";
    private String wu = " ";
    private String wan = " ";



    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("zao");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wu");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wan");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACPer01() {
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
     * get the typecode
     * @return the typecode
     */
    public String getZao() {
        return this.zao;
    }

    /**
     * set the typecode
     */
    public void setZao(String zao) {
        this.zao = zao;
    }

    /**
     * get the typename
     * @return the typename
     */
    public String getWu() {
        return this.wu;
    }

    /**
     * set the typename
     */
    public void setWu(String wu) {
        this.wu = wu;
    }

    /**
     * get the typepid
     * @return the typepid
     */
    public String getWan() {
        return this.wan;
    }

    /**
     * set the typepid
     */
    public void setWan(String wan) {
        this.wan = wan;
    }


    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setZao(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("zao")), zao));
        setWu(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wu")), wu));
        setWan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wan")), wan));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("zao",StringUtils.toString(zao, eiMetadata.getMeta("zao")));
        map.put("wu",StringUtils.toString(wu, eiMetadata.getMeta("wu")));
        map.put("wan",StringUtils.toString(wan, eiMetadata.getMeta("wan")));
        return map;
    }
}
