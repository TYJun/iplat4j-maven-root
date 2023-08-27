/**
* Generate time : 2021-06-09 17:27:32
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.zh.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiDeviceTaskPic
* 
*/
public class DIZH0101 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String path = " ";		/* 图片地址*/
    private String filename = " ";		/* 图片名*/
    private String taskid = " ";		/* 任务实绩的ID号*/
    private String itemid = " ";		
    private String createTime = " ";		/* 保存时间*/
    private String createMan = " ";		/* 保存人*/
    private String pathName = " ";		/* 完整路径*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("path");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filename");
        eiColumn.setDescName("图片名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskid");
        eiColumn.setDescName("任务实绩的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("保存时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createMan");
        eiColumn.setDescName("保存人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("pathName");
        eiColumn.setDescName("完整路径");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DIZH0101() {
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
     * get the path - 图片地址
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * set the path - 图片地址
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * get the filename - 图片名
     * @return the filename
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * set the filename - 图片名
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * get the taskid - 任务实绩的ID号
     * @return the taskid
     */
    public String getTaskid() {
        return this.taskid;
    }

    /**
     * set the taskid - 任务实绩的ID号
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * get the itemid 
     * @return the itemid
     */
    public String getItemid() {
        return this.itemid;
    }

    /**
     * set the itemid 
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * get the createTime - 保存时间
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 保存时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the createMan - 保存人
     * @return the createMan
     */
    public String getCreateMan() {
        return this.createMan;
    }

    /**
     * set the createMan - 保存人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setPath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("path")), path));
        setFilename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filename")), filename));
        setTaskid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskid")), taskid));
        setItemid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemid")), itemid));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setCreateMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createMan")), createMan));
        setPathName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pathName")), pathName));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("path",StringUtils.toString(path, eiMetadata.getMeta("path")));
        map.put("filename",StringUtils.toString(filename, eiMetadata.getMeta("filename")));
        map.put("taskid",StringUtils.toString(taskid, eiMetadata.getMeta("taskid")));
        map.put("itemid",StringUtils.toString(itemid, eiMetadata.getMeta("itemid")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("createMan",StringUtils.toString(createMan, eiMetadata.getMeta("createMan")));
        map.put("pathName",StringUtils.toString(pathName, eiMetadata.getMeta("pathName")));
        return map;
    }
}