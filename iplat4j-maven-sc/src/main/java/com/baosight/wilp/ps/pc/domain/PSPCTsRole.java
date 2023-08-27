/**
* Generate time : 2021-05-25 17:10:14
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* TSRole role规则表实体类
* 
*/
public class PSPCTsRole extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String rolecode = " ";		
    private String rolename = " ";		
    private String classid = " ";		
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

        eiColumn = new EiColumn("rolecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rolename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTsRole() {
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
     * get the rolecode 
     * @return the rolecode
     */
    public String getRolecode() {
        return this.rolecode;
    }

    /**
     * set the rolecode 
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    /**
     * get the rolename 
     * @return the rolename
     */
    public String getRolename() {
        return this.rolename;
    }

    /**
     * set the rolename 
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * get the classid 
     * @return the classid
     */
    public String getClassid() {
        return this.classid;
    }

    /**
     * set the classid 
     */
    public void setClassid(String classid) {
        this.classid = classid;
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
        setRolecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rolecode")), rolecode));
        setRolename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rolename")), rolename));
        setClassid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classid")), classid));
        setModuleid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleid")), moduleid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("rolecode",StringUtils.toString(rolecode, eiMetadata.getMeta("rolecode")));
        map.put("rolename",StringUtils.toString(rolename, eiMetadata.getMeta("rolename")));
        map.put("classid",StringUtils.toString(classid, eiMetadata.getMeta("classid")));
        map.put("moduleid",StringUtils.toString(moduleid, eiMetadata.getMeta("moduleid")));
        return map;
    }
}