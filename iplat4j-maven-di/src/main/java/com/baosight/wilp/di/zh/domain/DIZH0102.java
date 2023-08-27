/**
* Generate time : 2021-06-09 17:27:32
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.zh.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DiDeviceTaskPic
* 
*/
public class DIZH0102 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String path = " ";		/* 图片地址*/
    private String filename = " ";		/* 图片名*/
    private String taskid = " ";		/* 任务实绩的ID号*/
    private String itemid = " ";
    private String deptNum = " ";		/* 保存时间*/
    private String deptName = " ";		/* 保存人*/
    private String taskNameType = " ";		/* 完整路径*/

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

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskNameType");
        eiColumn.setDescName("任务名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DIZH0102() {
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
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the createTime - 保存时间
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the createMan - 保存人
     * @return the createMan
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the createMan - 保存人
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTaskNameType() {
        return taskNameType;
    }

    public void setTaskNameType(String taskNameType) {
        this.taskNameType = taskNameType;
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
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setTaskNameType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskNameType")), taskNameType));
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
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("taskNameType",StringUtils.toString(taskNameType, eiMetadata.getMeta("taskNameType")));
        return map;
    }
}