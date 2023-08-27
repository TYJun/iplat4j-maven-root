/**
* Generate time : 2021-09-15 16:07:04
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.dx.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* 安全巡查对象地点表实体类
* ImObjectSpot
* 
*/
public class ImObjectSpot extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String spotId = " ";		/* 地点id*/
    private String spotCode = " ";		/* 地点编码*/
    private String spotName = " ";		/* 地点名称*/
    private String objName = " ";		/* 巡检对象*/
    private String objRemark = " ";		/* 巡检对象备注*/
    private String createMan = " ";		/* 创建人*/
    private String createTime = " ";		/* 创建时间*/
    private String modifyMan = " ";		/* 修改人*/
    private String modifyTime = " ";		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String NFCCode = " ";		/* NFC编码*/
    

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotId");
        eiColumn.setDescName("地点id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotCode");
        eiColumn.setDescName("地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("地点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("objName");
        eiColumn.setDescName("巡检对象");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("objRemark");
        eiColumn.setDescName("巡检对象备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createMan");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyMan");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("NFCCode");
        eiColumn.setDescName("NFC编码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImObjectSpot() {
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
     * get the spotCode - 地点编码
     * @return the spotCode
     */
    public String getSpotCode() {
        return this.spotCode;
    }

    /**
     * set the spotCode - 地点编码
     */
    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    /**
     * get the spotName - 地点名称
     * @return the spotName
     */
    public String getSpotName() {
        return this.spotName;
    }

    /**
     * set the spotName - 地点名称
     */
    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    /**
     * get the objName - 巡检对象
     * @return the objName
     */
    public String getObjName() {
        return this.objName;
    }

    /**
     * set the objName - 巡检对象
     */
    public void setObjName(String objName) {
        this.objName = objName;
    }

    /**
     * get the objRemark - 巡检对象备注
     * @return the objRemark
     */
    public String getObjRemark() {
        return this.objRemark;
    }

    /**
     * set the objRemark - 巡检对象备注
     */
    public void setObjRemark(String objRemark) {
        this.objRemark = objRemark;
    }

    /**
     * get the createMan - 创建人
     * @return the createMan
     */
    public String getCreateMan() {
        return this.createMan;
    }

    /**
     * set the createMan - 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the modifyMan - 修改人
     * @return the modifyMan
     */
    public String getModifyMan() {
        return this.modifyMan;
    }

    /**
     * set the modifyMan - 修改人
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }

    /**
     * get the modifyTime - 修改时间
     * @return the modifyTime
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * set the modifyTime - 修改时间
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    public String getNFCCode() {
        return NFCCode;
    }

    public void setNFCCode(String nFCCode) {
        NFCCode = nFCCode;
    }
    
    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSpotId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotId")), spotId));
        setSpotCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotCode")), spotCode));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
        setObjName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("objName")), objName));
        setObjRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("objRemark")), objRemark));
        setCreateMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createMan")), createMan));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setModifyMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyMan")), modifyMan));
        setModifyTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyTime")), modifyTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setNFCCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("NFCCode")), NFCCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("spotId",StringUtils.toString(spotId, eiMetadata.getMeta("spotId")));
        map.put("spotCode",StringUtils.toString(spotCode, eiMetadata.getMeta("spotCode")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        map.put("objName",StringUtils.toString(objName, eiMetadata.getMeta("objName")));
        map.put("objRemark",StringUtils.toString(objRemark, eiMetadata.getMeta("objRemark")));
        map.put("createMan",StringUtils.toString(createMan, eiMetadata.getMeta("createMan")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifyMan",StringUtils.toString(modifyMan, eiMetadata.getMeta("modifyMan")));
        map.put("modifyTime",StringUtils.toString(modifyTime, eiMetadata.getMeta("modifyTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("NFCCode",StringUtils.toString(NFCCode, eiMetadata.getMeta("NFCCode")));
        return map;
    }
}