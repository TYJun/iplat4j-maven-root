/**
* Generate time : 2021-05-25 18:45:18
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSBMSJZD01
* sc_typegroup
*/
public class SSBMSjzd01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String typegroupcode = " ";		
    private String typegroupname = " ";		
    private String moduleid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typegroupcode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typegroupname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMSjzd01() {
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
     * get the typegroupcode 
     * @return the typegroupcode
     */
    public String getTypegroupcode() {
        return this.typegroupcode;
    }

    /**
     * set the typegroupcode 
     */
    public void setTypegroupcode(String typegroupcode) {
        this.typegroupcode = typegroupcode;
    }

    /**
     * get the typegroupname 
     * @return the typegroupname
     */
    public String getTypegroupname() {
        return this.typegroupname;
    }

    /**
     * set the typegroupname 
     */
    public void setTypegroupname(String typegroupname) {
        this.typegroupname = typegroupname;
    }

    /**
     * get the moduleid 
     * @return the moduleid
     */
    public String getModuleid() {
        return this.moduleid;
    }

    /**
     * set the moduleid 
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTypegroupcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupcode")), typegroupcode));
        setTypegroupname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupname")), typegroupname));
        setModuleid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleid")), moduleid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("typegroupcode",StringUtils.toString(typegroupcode, eiMetadata.getMeta("typegroupcode")));
        map.put("typegroupname",StringUtils.toString(typegroupname, eiMetadata.getMeta("typegroupname")));
        map.put("moduleid",StringUtils.toString(moduleid, eiMetadata.getMeta("moduleid")));
        return map;
    }
}