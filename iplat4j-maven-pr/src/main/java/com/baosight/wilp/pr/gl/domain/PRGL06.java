/**
* Generate time : 2021-05-06 16:37:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pr.gl.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* TSTypegroup
* 
*/
public class PRGL06 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String typegroupcode = " ";		
    private String typegroupname = " ";		
    private String moduleid = " ";		
    private String typeId = " ";		
    private String typename = " ";		
    private String typecode = " ";		
    private String param = " ";		
    private String val = " ";		

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
        
        eiColumn = new EiColumn("typeId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("typename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("typecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("param");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("val");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PRGL06() {
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTypegroupcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupcode")), typegroupcode));
        setTypegroupname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupname")), typegroupname));
        setModuleid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleid")), moduleid));
        setTypeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeId")), typeId));
        setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
        setTypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typecode")), typecode));
        setParam(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("param")), param));
        setVal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("val")), val));
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
        map.put("typeId",StringUtils.toString(typeId, eiMetadata.getMeta("typeId")));
        map.put("typename",StringUtils.toString(typename, eiMetadata.getMeta("typename")));
        map.put("typecode",StringUtils.toString(typecode, eiMetadata.getMeta("typecode")));
        map.put("param",StringUtils.toString(param, eiMetadata.getMeta("param")));
        map.put("val",StringUtils.toString(val, eiMetadata.getMeta("val")));
        return map;
    }
}