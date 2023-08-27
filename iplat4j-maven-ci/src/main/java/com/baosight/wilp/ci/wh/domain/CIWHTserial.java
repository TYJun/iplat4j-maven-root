/**
* Generate time : 2021-03-02 15:51:31
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.wh.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tserial通用序列号
* sc_serial
*/
public class CIWHTserial extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String recReviseTime = " ";		/* 修改时间*/
    private String serialNum = " ";		/* 序列号编码*/
    private Integer serialIndex = new Integer(0);		/* 序列号值*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serialNum");
        eiColumn.setDescName("序列号编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serialIndex");
        eiColumn.setDescName("序列号值");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CIWHTserial() {
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
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the serialNum - 序列号编码
     * @return the serialNum
     */
    public String getSerialNum() {
        return this.serialNum;
    }

    /**
     * set the serialNum - 序列号编码
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * get the serialIndex - 序列号值
     * @return the serialIndex
     */
    public Integer getSerialIndex() {
        return this.serialIndex;
    }

    /**
     * set the serialIndex - 序列号值
     */
    public void setSerialIndex(Integer serialIndex) {
        this.serialIndex = serialIndex;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setSerialNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serialNum")), serialNum));
        setSerialIndex(NumberUtils.toInteger(StringUtils.toString(map.get("serialIndex")), serialIndex));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("serialNum",StringUtils.toString(serialNum, eiMetadata.getMeta("serialNum")));
        map.put("serialIndex",StringUtils.toString(serialIndex, eiMetadata.getMeta("serialIndex")));
        return map;
    }
}