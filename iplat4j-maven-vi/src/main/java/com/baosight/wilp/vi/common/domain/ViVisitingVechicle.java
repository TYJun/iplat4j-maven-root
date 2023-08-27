/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViVisitingVechicle
* 
*/
public class ViVisitingVechicle extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer visitingId = new Integer(0);		/*  来访记录ID vi_visting_info(id)*/
    private Integer vehicleId = new Integer(0);		/* 车辆ID vi_vehicle(id)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("visitingId");
        eiColumn.setDescName(" 来访记录ID vi_visting_info(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vehicleId");
        eiColumn.setDescName("车辆ID vi_vehicle(id)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViVisitingVechicle() {
        initMetaData();
    }

    /**
     * get the visitingId -  来访记录ID vi_visting_info(id)
     * @return the visitingId
     */
    public Integer getVisitingId() {
        return this.visitingId;
    }

    /**
     * set the visitingId -  来访记录ID vi_visting_info(id)
     */
    public void setVisitingId(Integer visitingId) {
        this.visitingId = visitingId;
    }

    /**
     * get the vehicleId - 车辆ID vi_vehicle(id)
     * @return the vehicleId
     */
    public Integer getVehicleId() {
        return this.vehicleId;
    }

    /**
     * set the vehicleId - 车辆ID vi_vehicle(id)
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setVisitingId(NumberUtils.toInteger(StringUtils.toString(map.get("visitingId")), visitingId));
        setVehicleId(NumberUtils.toInteger(StringUtils.toString(map.get("vehicleId")), vehicleId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("visitingId",StringUtils.toString(visitingId, eiMetadata.getMeta("visitingId")));
        map.put("vehicleId",StringUtils.toString(vehicleId, eiMetadata.getMeta("vehicleId")));
        return map;
    }
}