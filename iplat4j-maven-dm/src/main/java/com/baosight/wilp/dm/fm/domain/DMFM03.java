/**
* Generate time : 2021-08-23 22:54:47
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.fm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoomElecMiddleMonth
* 
*/
public class DMFM03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private BigDecimal elec = new BigDecimal("0");		/* 当前电表度数*/
    private String pointerTag = " ";		/* 点位*/
    private Timestamp operationDate ;		/* 采集时间*/
    private String dormitoryNo = " ";		/* 楼层编码*/
    private Timestamp recordTime ;		/* 创建时间*/
    private BigDecimal lastElec = new BigDecimal("0");		/* 上月电表度数*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elec");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("当前电表度数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pointerTag");
        eiColumn.setDescName("点位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationDate");
        eiColumn.setDescName("采集时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormitoryNo");
        eiColumn.setDescName("楼层编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recordTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastElec");
        eiColumn.setType("N");
        eiColumn.setScaleLength(3);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("上月电表度数");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DMFM03() {
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
     * get the elec - 当前电表度数
     * @return the elec
     */
    public BigDecimal getElec() {
        return this.elec;
    }

    /**
     * set the elec - 当前电表度数
     */
    public void setElec(BigDecimal elec) {
        this.elec = elec;
    }

    /**
     * get the pointerTag - 点位
     * @return the pointerTag
     */
    public String getPointerTag() {
        return this.pointerTag;
    }

    /**
     * set the pointerTag - 点位
     */
    public void setPointerTag(String pointerTag) {
        this.pointerTag = pointerTag;
    }

    /**
     * get the operationDate - 采集时间
     * @return the operationDate
     */
    public Timestamp getOperationDate() {
        return this.operationDate;
    }

    /**
     * set the operationDate - 采集时间
     */
    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * get the dormitoryNo - 楼层编码
     * @return the dormitoryNo
     */
    public String getDormitoryNo() {
        return this.dormitoryNo;
    }

    /**
     * set the dormitoryNo - 楼层编码
     */
    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    /**
     * get the recordTime - 创建时间
     * @return the recordTime
     */
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    /**
     * set the recordTime - 创建时间
     */
    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * get the lastElec - 上月电表度数
     * @return the lastElec
     */
    public BigDecimal getLastElec() {
        return this.lastElec;
    }

    /**
     * set the lastElec - 上月电表度数
     */
    public void setLastElec(BigDecimal lastElec) {
        this.lastElec = lastElec;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setElec(NumberUtils.toBigDecimal(StringUtils.toString(map.get("elec")), elec));
        setPointerTag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pointerTag")), pointerTag));
        setOperationDate(DateUtils.toTimestamp(StringUtils.toString(map.get("operationDate"))));
        setDormitoryNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormitoryNo")), dormitoryNo));
        setRecordTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recordTime"))));
        setLastElec(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastElec")), lastElec));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("elec",StringUtils.toString(elec, eiMetadata.getMeta("elec")));
        map.put("pointerTag",StringUtils.toString(pointerTag, eiMetadata.getMeta("pointerTag")));
        map.put("operationDate",StringUtils.toString(operationDate, eiMetadata.getMeta("operationDate")));
        map.put("dormitoryNo",StringUtils.toString(dormitoryNo, eiMetadata.getMeta("dormitoryNo")));
        map.put("recordTime",StringUtils.toString(recordTime, eiMetadata.getMeta("recordTime")));
        map.put("lastElec",StringUtils.toString(lastElec, eiMetadata.getMeta("lastElec")));
        return map;
    }
}