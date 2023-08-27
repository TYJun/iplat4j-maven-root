/**
* Generate time : 2021-05-07 17:20:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiSchemecycle
* 
*/
public class ImSchemecycle extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String schemeCode = " ";		
    private String nextExecuteTime = " ";		
    private String createMan = " ";		
    private String createTime = " ";		
    private String unit = " ";		
    private String cycle = " ";		
    private String startTime = " ";		
    private String holiday = " ";		
    private String weekend = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemeCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextExecuteTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createMan");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cycle");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("startTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("holiday");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("weekend");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImSchemecycle() {
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
     * get the schemeCode 
     * @return the schemeCode
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * set the schemeCode 
     */
    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * get the nextExecuteTime 
     * @return the nextExecuteTime
     */
    public String getNextExecuteTime() {
        return this.nextExecuteTime;
    }

    /**
     * set the nextExecuteTime 
     */
    public void setNextExecuteTime(String nextExecuteTime) {
        this.nextExecuteTime = nextExecuteTime;
    }

    /**
     * get the createMan 
     * @return the createMan
     */
    public String getCreateMan() {
        return this.createMan;
    }

    /**
     * set the createMan 
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    /**
     * get the createTime 
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime 
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the unit 
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit 
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the cycle 
     * @return the cycle
     */
    public String getCycle() {
        return this.cycle;
    }

    /**
     * set the cycle 
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * get the startTime 
     * @return the startTime
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * set the startTime 
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * get the holiday 
     * @return the holiday
     */
    public String getHoliday() {
        return this.holiday;
    }

    /**
     * set the holiday 
     */
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    /**
     * get the weekend 
     * @return the weekend
     */
    public String getWeekend() {
        return this.weekend;
    }

    /**
     * set the weekend 
     */
    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSchemeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeCode")), schemeCode));
        setNextExecuteTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextExecuteTime")), nextExecuteTime));
        setCreateMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createMan")), createMan));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setCycle(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cycle")), cycle));
        setStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("startTime")), startTime));
        setHoliday(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("holiday")), holiday));
        setWeekend(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("weekend")), weekend));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("schemeCode",StringUtils.toString(schemeCode, eiMetadata.getMeta("schemeCode")));
        map.put("nextExecuteTime",StringUtils.toString(nextExecuteTime, eiMetadata.getMeta("nextExecuteTime")));
        map.put("createMan",StringUtils.toString(createMan, eiMetadata.getMeta("createMan")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("cycle",StringUtils.toString(cycle, eiMetadata.getMeta("cycle")));
        map.put("startTime",StringUtils.toString(startTime, eiMetadata.getMeta("startTime")));
        map.put("holiday",StringUtils.toString(holiday, eiMetadata.getMeta("holiday")));
        map.put("weekend",StringUtils.toString(weekend, eiMetadata.getMeta("weekend")));
        return map;
    }
}