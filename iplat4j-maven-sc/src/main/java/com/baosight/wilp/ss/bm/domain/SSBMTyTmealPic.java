/**
* Generate time : 2021-03-11 16:17:31
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* TYTmealPic
* sc_pic
*/
public class SSBMTyTmealPic extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String picId = " ";		
    private String fileId = " ";		
    private String mealId = " ";		
    private String fileUrl = " ";		
    private String fileType = " ";		/* 0－图片；1－文档；*/
    private String imgStr = " ";		/* 0－图片；1－文档；*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("picId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileUrl");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileType");
        eiColumn.setDescName("0－图片；1－文档；");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMTyTmealPic() {
        initMetaData();
    }

    /**
     * get the picId 
     * @return the picId
     */
    public String getPicId() {
        return this.picId;
    }

    /**
     * set the picId 
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * get the fileId 
     * @return the fileId
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileId 
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the mealId 
     * @return the mealId
     */
    public String getMealId() {
        return this.mealId;
    }

    /**
     * set the mealId 
     */
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    /**
     * get the fileUrl 
     * @return the fileUrl
     */
    public String getFileUrl() {
        return this.fileUrl;
    }

    /**
     * set the fileUrl 
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * get the fileType - 0－图片；1－文档；
     * @return the fileType
     */
    public String getFileType() {
        return this.fileType;
    }

    /**
     * set the fileType - 0－图片；1－文档；
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setPicId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("picId")), picId));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
        setMealId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealId")), mealId));
        setFileUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileUrl")), fileUrl));
        setFileType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileType")), fileType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("picId",StringUtils.toString(picId, eiMetadata.getMeta("picId")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        map.put("mealId",StringUtils.toString(mealId, eiMetadata.getMeta("mealId")));
        map.put("fileUrl",StringUtils.toString(fileUrl, eiMetadata.getMeta("fileUrl")));
        map.put("fileType",StringUtils.toString(fileType, eiMetadata.getMeta("fileType")));
        return map;
    }
}