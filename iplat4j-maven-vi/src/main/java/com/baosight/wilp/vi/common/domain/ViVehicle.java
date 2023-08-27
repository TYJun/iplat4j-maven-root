/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViVehicle
* 
*/
public class ViVehicle extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer id = new Integer(0);		/* 主键，自增长*/
    private String vehicleNumber = "";		/* 车牌号码，唯一索引*/
    private String vehicleTypeCode = "";		/* 轻量车号*/
    private Timestamp createTime ;		/* 创建时间*/
    private Timestamp updateTime ;		/* 更新时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键，自增长");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vehicleNumber");
        eiColumn.setDescName("车牌号码，唯一索引");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vehicleTypeCode");
        eiColumn.setDescName("轻量车号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateTime");
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViVehicle() {
        initMetaData();
    }

    /**
     * get the id - 主键，自增长
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 主键，自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the vehicleNumber - 车牌号码，唯一索引
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    /**
     * set the vehicleNumber - 车牌号码，唯一索引
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * get the vehicleTypeCode - 轻量车号
     * @return the vehicleTypeCode
     */
    public String getVehicleTypeCode() {
        return this.vehicleTypeCode;
    }

    /**
     * set the vehicleTypeCode - 轻量车号
     */
    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the updateTime - 更新时间
     * @return the updateTime
     */
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    /**
     * set the updateTime - 更新时间
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setVehicleNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("vehicleNumber")), vehicleNumber));
        setVehicleTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("vehicleTypeCode")), vehicleTypeCode));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setUpdateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("updateTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("vehicleNumber",StringUtils.toString(vehicleNumber, eiMetadata.getMeta("vehicleNumber")));
        map.put("vehicleTypeCode",StringUtils.toString(vehicleTypeCode, eiMetadata.getMeta("vehicleTypeCode")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("updateTime",StringUtils.toString(updateTime, eiMetadata.getMeta("updateTime")));
        return map;
    }
}