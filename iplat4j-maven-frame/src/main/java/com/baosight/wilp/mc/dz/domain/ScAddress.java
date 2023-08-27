/**
* Generate time : 2022-06-17 1:02:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.mc.dz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ScAddress
* 
*/
public class ScAddress extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String building = " ";		/* 楼*/
    private String floor = " ";		/* 层*/
    private String address = " ";		/* 地址*/
    private String takeEffect = " ";		/* 是否生效*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("takeEffect");
        eiColumn.setDescName("是否生效");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ScAddress() {
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
     * get the building - 楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the floor - 层
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the address - 地址
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the takeEffect - 是否生效
     * @return the takeEffect
     */
    public String getTakeEffect() {
        return this.takeEffect;
    }

    /**
     * set the takeEffect - 是否生效
     */
    public void setTakeEffect(String takeEffect) {
        this.takeEffect = takeEffect;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setTakeEffect(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("takeEffect")), takeEffect));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("takeEffect",StringUtils.toString(takeEffect, eiMetadata.getMeta("takeEffect")));
        return map;
    }
}