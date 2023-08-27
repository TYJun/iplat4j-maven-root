/**
* Generate time : 2021-02-20 10:09:38
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageAddressTree
* 
*/
public class CarriageAddressTree extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 地址分类树结构主键（UUID）*/
    private String parentId = " ";		/* 父节点*/
    private String nodeName = " ";		/* 树节点名称*/
    private String sortNo = " ";		/* 排序*/
    private String hasChildren = " ";		/* 是否有子节点（有\\无）*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("地址分类树结构主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("父节点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeName");
        eiColumn.setDescName("树节点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortNo");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hasChildren");
        eiColumn.setDescName("是否有子节点（有\\无）");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageAddressTree() {
        initMetaData();
    }

    /**
     * get the id - 地址分类树结构主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 地址分类树结构主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the parentId - 父节点
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 父节点
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the nodeName - 树节点名称
     * @return the nodeName
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * set the nodeName - 树节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * get the sortNo - 排序
     * @return the sortNo
     */
    public String getSortNo() {
        return this.sortNo;
    }

    /**
     * set the sortNo - 排序
     */
    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * get the hasChildren - 是否有子节点（有\\无）
     * @return the hasChildren
     */
    public String getHasChildren() {
        return this.hasChildren;
    }

    /**
     * set the hasChildren - 是否有子节点（有\\无）
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setNodeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeName")), nodeName));
        setSortNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sortNo")), sortNo));
        setHasChildren(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hasChildren")), hasChildren));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("nodeName",StringUtils.toString(nodeName, eiMetadata.getMeta("nodeName")));
        map.put("sortNo",StringUtils.toString(sortNo, eiMetadata.getMeta("sortNo")));
        map.put("hasChildren",StringUtils.toString(hasChildren, eiMetadata.getMeta("hasChildren")));
        return map;
    }
}