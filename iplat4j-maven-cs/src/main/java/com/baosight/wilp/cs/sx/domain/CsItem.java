/**
* Generate time : 2021-11-19 16:27:03
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cs.sx.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CsItem 保洁事项表的实体类
*/
public class CsItem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String moduleId = " ";		/* 事项分类id*/
    private String itemCode = " ";		/* 事项编码*/
    private String itemName = " ";		/* 事项名称*/
    private String serviceDeptNum = " ";		/* 事项绑定的科室编码*/
    private String serviceDeptName = " ";		/* 事项绑定的科室名称*/
    private String comments = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleId");
        eiColumn.setDescName("事项分类id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemCode");
        eiColumn.setDescName("事项编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemName");
        eiColumn.setDescName("事项名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptNum");
        eiColumn.setDescName("事项绑定的科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptName");
        eiColumn.setDescName("事项绑定的科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comments");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CsItem() {
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
     * get the moduleId - 事项分类id
     * @return the moduleId
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * set the moduleId - 事项分类id
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * get the itemCode - 事项编码
     * @return the itemCode
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * set the itemCode - 事项编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * get the itemName - 事项名称
     * @return the itemName
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * set the itemName - 事项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * get the serviceDeptNum - 事项绑定的科室编码
     * @return the serviceDeptNum
     */
    public String getServiceDeptNum() {
        return this.serviceDeptNum;
    }

    /**
     * set the serviceDeptNum - 事项绑定的科室编码
     */
    public void setServiceDeptNum(String serviceDeptNum) {
        this.serviceDeptNum = serviceDeptNum;
    }

    /**
     * get the serviceDeptName - 事项绑定的科室名称
     * @return the serviceDeptName
     */
    public String getServiceDeptName() {
        return this.serviceDeptName;
    }

    /**
     * set the serviceDeptName - 事项绑定的科室名称
     */
    public void setServiceDeptName(String serviceDeptName) {
        this.serviceDeptName = serviceDeptName;
    }

    /**
     * get the comments - 备注
     * @return the comments
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * set the comments - 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setModuleId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleId")), moduleId));
        setItemCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemCode")), itemCode));
        setItemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemName")), itemName));
        setServiceDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptNum")), serviceDeptNum));
        setServiceDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptName")), serviceDeptName));
        setComments(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comments")), comments));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("moduleId",StringUtils.toString(moduleId, eiMetadata.getMeta("moduleId")));
        map.put("itemCode",StringUtils.toString(itemCode, eiMetadata.getMeta("itemCode")));
        map.put("itemName",StringUtils.toString(itemName, eiMetadata.getMeta("itemName")));
        map.put("serviceDeptNum",StringUtils.toString(serviceDeptNum, eiMetadata.getMeta("serviceDeptNum")));
        map.put("serviceDeptName",StringUtils.toString(serviceDeptName, eiMetadata.getMeta("serviceDeptName")));
        map.put("comments",StringUtils.toString(comments, eiMetadata.getMeta("comments")));
        return map;
    }
}