/**
* Generate time : 2021-06-15 10:52:25
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSBMDD01
* sc_type
* sc_typegroup
*/
public class SSBMDD01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String typegroupcode = " ";		
    private String typegroupname = " ";		
    private String moduleid = " ";	
    
    private String typecode = " ";		
    private String typename = " ";	
    
    private String typepid = " ";		
    private String typegroupid = " ";		
    private String typemessage = " ";		/* 说明*/
    private String paramdesc1 = " ";		
    private String paramvalue1 = " ";		
    private String paramdesc2 = " ";		
    private String paramvalue2 = " ";		
    private String paramdesc3 = " ";		
    private String paramvalue3 = " ";			
    private Integer sortno = new Integer(0);

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

        
        eiColumn = new EiColumn("typecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typepid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typegroupid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typemessage");
        eiColumn.setDescName("说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc1");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue1");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc2");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue2");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc3");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue3");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc4");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue4");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc5");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue5");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc6");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue6");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortno");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public SSBMDD01() {
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
     * get the typecode 
     * @return the typecode
     */
    public String getTypecode() {
        return this.typecode;
    }

    /**
     * set the typecode 
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /**
     * get the typename 
     * @return the typename
     */
    public String getTypename() {
        return this.typename;
    }

    /**
     * set the typename 
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * get the typepid 
     * @return the typepid
     */
    public String getTypepid() {
        return this.typepid;
    }

    /**
     * set the typepid 
     */
    public void setTypepid(String typepid) {
        this.typepid = typepid;
    }

    /**
     * get the typegroupid 
     * @return the typegroupid
     */
    public String getTypegroupid() {
        return this.typegroupid;
    }

    /**
     * set the typegroupid 
     */
    public void setTypegroupid(String typegroupid) {
        this.typegroupid = typegroupid;
    }

    /**
     * get the typemessage - 说明
     * @return the typemessage
     */
    public String getTypemessage() {
        return this.typemessage;
    }

    /**
     * set the typemessage - 说明
     */
    public void setTypemessage(String typemessage) {
        this.typemessage = typemessage;
    }

    /**
     * get the paramdesc1 
     * @return the paramdesc1
     */
    public String getParamdesc1() {
        return this.paramdesc1;
    }

    /**
     * set the paramdesc1 
     */
    public void setParamdesc1(String paramdesc1) {
        this.paramdesc1 = paramdesc1;
    }

    /**
     * get the paramvalue1 
     * @return the paramvalue1
     */
    public String getParamvalue1() {
        return this.paramvalue1;
    }

    /**
     * set the paramvalue1 
     */
    public void setParamvalue1(String paramvalue1) {
        this.paramvalue1 = paramvalue1;
    }

    /**
     * get the paramdesc2 
     * @return the paramdesc2
     */
    public String getParamdesc2() {
        return this.paramdesc2;
    }

    /**
     * set the paramdesc2 
     */
    public void setParamdesc2(String paramdesc2) {
        this.paramdesc2 = paramdesc2;
    }

    /**
     * get the paramvalue2 
     * @return the paramvalue2
     */
    public String getParamvalue2() {
        return this.paramvalue2;
    }

    /**
     * set the paramvalue2 
     */
    public void setParamvalue2(String paramvalue2) {
        this.paramvalue2 = paramvalue2;
    }

    /**
     * get the paramdesc3 
     * @return the paramdesc3
     */
    public String getParamdesc3() {
        return this.paramdesc3;
    }

    /**
     * set the paramdesc3 
     */
    public void setParamdesc3(String paramdesc3) {
        this.paramdesc3 = paramdesc3;
    }

    /**
     * get the paramvalue3 
     * @return the paramvalue3
     */
    public String getParamvalue3() {
        return this.paramvalue3;
    }

    /**
     * set the paramvalue3 
     */
    public void setParamvalue3(String paramvalue3) {
        this.paramvalue3 = paramvalue3;
    }

    /**
     * get the sortno 
     * @return the sortno
     */
    public Integer getSortno() {
        return this.sortno;
    }

    /**
     * set the sortno 
     */
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTypegroupcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupcode")), typegroupcode));
        setTypegroupname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupname")), typegroupname));
        setModuleid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleid")), moduleid));
        
        setTypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typecode")), typecode));
        setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
        setTypepid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typepid")), typepid));
        setTypegroupid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupid")), typegroupid));
        setTypemessage(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typemessage")), typemessage));
        setParamdesc1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramdesc1")), paramdesc1));
        setParamvalue1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramvalue1")), paramvalue1));
        setParamdesc2(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramdesc2")), paramdesc2));
        setParamvalue2(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramvalue2")), paramvalue2));
        setParamdesc3(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramdesc3")), paramdesc3));
        setParamvalue3(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramvalue3")), paramvalue3));
        setSortno(NumberUtils.toInteger(StringUtils.toString(map.get("sortno")), sortno));
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
        
        map.put("typecode",StringUtils.toString(typecode, eiMetadata.getMeta("typecode")));
        map.put("typename",StringUtils.toString(typename, eiMetadata.getMeta("typename")));
        map.put("typepid",StringUtils.toString(typepid, eiMetadata.getMeta("typepid")));
        map.put("typegroupid",StringUtils.toString(typegroupid, eiMetadata.getMeta("typegroupid")));
        map.put("typemessage",StringUtils.toString(typemessage, eiMetadata.getMeta("typemessage")));
        map.put("paramdesc1",StringUtils.toString(paramdesc1, eiMetadata.getMeta("paramdesc1")));
        map.put("paramvalue1",StringUtils.toString(paramvalue1, eiMetadata.getMeta("paramvalue1")));
        map.put("paramdesc2",StringUtils.toString(paramdesc2, eiMetadata.getMeta("paramdesc2")));
        map.put("paramvalue2",StringUtils.toString(paramvalue2, eiMetadata.getMeta("paramvalue2")));
        map.put("paramdesc3",StringUtils.toString(paramdesc3, eiMetadata.getMeta("paramdesc3")));
        map.put("paramvalue3",StringUtils.toString(paramvalue3, eiMetadata.getMeta("paramvalue3")));
        map.put("sortno",StringUtils.toString(sortno, eiMetadata.getMeta("sortno")));
        return map;
    }
}