/**
* Generate time : 2021-03-26 18:23:25
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* AccompanyProject
* 
*/
public class AccompanyProject extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String accompanyProjectNo = " ";		/* 陪检项目编号*/
    private String accompanyProject = " ";		/* 陪检项目*/
    private String accompanyAddress = " ";		/* 陪检地址*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyProjectNo");
        eiColumn.setDescName("陪检项目编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyProject");
        eiColumn.setDescName("陪检项目");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyAddress");
        eiColumn.setDescName("陪检地址");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public AccompanyProject() {
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
     * get the accompanyProjectNo - 陪检项目编号
     * @return the accompanyProjectNo
     */
    public String getAccompanyProjectNo() {
        return this.accompanyProjectNo;
    }

    /**
     * set the accompanyProjectNo - 陪检项目编号
     */
    public void setAccompanyProjectNo(String accompanyProjectNo) {
        this.accompanyProjectNo = accompanyProjectNo;
    }

    /**
     * get the accompanyProject - 陪检项目
     * @return the accompanyProject
     */
    public String getAccompanyProject() {
        return this.accompanyProject;
    }

    /**
     * set the accompanyProject - 陪检项目
     */
    public void setAccompanyProject(String accompanyProject) {
        this.accompanyProject = accompanyProject;
    }

    /**
     * get the accompanyAddress - 陪检地址
     * @return the accompanyAddress
     */
    public String getAccompanyAddress() {
        return this.accompanyAddress;
    }

    /**
     * set the accompanyAddress - 陪检地址
     */
    public void setAccompanyAddress(String accompanyAddress) {
        this.accompanyAddress = accompanyAddress;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setAccompanyProjectNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyProjectNo")), accompanyProjectNo));
        setAccompanyProject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyProject")), accompanyProject));
        setAccompanyAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyAddress")), accompanyAddress));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("accompanyProjectNo",StringUtils.toString(accompanyProjectNo, eiMetadata.getMeta("accompanyProjectNo")));
        map.put("accompanyProject",StringUtils.toString(accompanyProject, eiMetadata.getMeta("accompanyProject")));
        map.put("accompanyAddress",StringUtils.toString(accompanyAddress, eiMetadata.getMeta("accompanyAddress")));
        return map;
    }
}