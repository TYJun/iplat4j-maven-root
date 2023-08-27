/**
* Generate time : 2021-09-09 9:41:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CarriageAddressTree
* sc_location_tree
*/
public class CarriageAddressTree extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
	private String code = " "; 		/* 树节点 */
	private String name = " "; 		/* 树节点 */
    private String topCode = " ";		/* 上一级编码*/
    private String hasChildren = " ";		/* 是否有子节点*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("code");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("topCode");
        eiColumn.setDescName("上一级编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("hasChildren");
        eiColumn.setDescName("是否有子节点");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageAddressTree() {
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
     * get the code 
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * set the code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get the name 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * get the topCode - 上一级编码
     * @return the topCode
     */
    public String getTopCode() {
        return this.topCode;
    }

    /**
     * set the topCode - 上一级编码
     */
    public void setTopCode(String topCode) {
        this.topCode = topCode;
    }

    /**
     * get the hasChildren - 是否有子节点
     * @return the hasChildren
     */
    public String getHasChildren() {
        return this.hasChildren;
    }

    /**
     * set the hasChildren - 是否有子节点
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setTopCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("topCode")), topCode));
        setHasChildren(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hasChildren")), hasChildren));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("code",StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("topCode",StringUtils.toString(topCode, eiMetadata.getMeta("topCode")));
        map.put("hasChildren",StringUtils.toString(hasChildren, eiMetadata.getMeta("hasChildren")));
        return map;
    }
}