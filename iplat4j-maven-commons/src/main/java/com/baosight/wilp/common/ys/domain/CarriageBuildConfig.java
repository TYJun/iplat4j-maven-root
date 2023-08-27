/**
* Generate time : 2021-02-09 9:13:43
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageBuildConfig
* 
*/
public class CarriageBuildConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 运送楼层人员配置主键（UUID）*/
    private String building = " ";		/* 楼层（多个用逗号隔开）*/
    private String workNo = " ";		/* 运送人工号*/
    private String name = " ";		/* 运送人姓名*/
    private String flag = " ";		/* 停用标记*/
    private String phone = " ";		/* 联系方式*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("运送楼层人员配置主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼层（多个用逗号隔开）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("运送人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("运送人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setDescName("停用标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("联系方式");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageBuildConfig() {
        initMetaData();
    }

    /**
     * get the id - 运送楼层人员配置主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 运送楼层人员配置主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the building - 楼层（多个用逗号隔开）
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 楼层（多个用逗号隔开）
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the workNo - 运送人工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 运送人工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the name - 运送人姓名
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 运送人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the flag - 停用标记
     * @return the flag
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * set the flag - 停用标记
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * get the phone - 联系方式
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        return map;
    }
}