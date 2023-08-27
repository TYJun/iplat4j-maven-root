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
* ViFreemanWriter
* 
*/
public class ViFreemanWriter extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer id = new Integer(0);		/* 主键*/
    private String freemanUid = "";		/* 唯一索引*/
    private String accessType = "";		/* 访问方式类型*/
    private String accessToken = "";		/* 访问类型对应的唯一表示*/
    private Timestamp registerTime ;		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("freemanUid");
        eiColumn.setDescName("唯一索引");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accessType");
        eiColumn.setDescName("访问方式类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accessToken");
        eiColumn.setDescName("访问类型对应的唯一表示");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("registerTime");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViFreemanWriter() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the freemanUid - 唯一索引
     * @return the freemanUid
     */
    public String getFreemanUid() {
        return this.freemanUid;
    }

    /**
     * set the freemanUid - 唯一索引
     */
    public void setFreemanUid(String freemanUid) {
        this.freemanUid = freemanUid;
    }

    /**
     * get the accessType - 访问方式类型
     * @return the accessType
     */
    public String getAccessType() {
        return this.accessType;
    }

    /**
     * set the accessType - 访问方式类型
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    /**
     * get the accessToken - 访问类型对应的唯一表示
     * @return the accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * set the accessToken - 访问类型对应的唯一表示
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * get the registerTime 
     * @return the registerTime
     */
    public Timestamp getRegisterTime() {
        return this.registerTime;
    }

    /**
     * set the registerTime 
     */
    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setFreemanUid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("freemanUid")), freemanUid));
        setAccessType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accessType")), accessType));
        setAccessToken(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accessToken")), accessToken));
        setRegisterTime(DateUtils.toTimestamp(StringUtils.toString(map.get("registerTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("freemanUid",StringUtils.toString(freemanUid, eiMetadata.getMeta("freemanUid")));
        map.put("accessType",StringUtils.toString(accessType, eiMetadata.getMeta("accessType")));
        map.put("accessToken",StringUtils.toString(accessToken, eiMetadata.getMeta("accessToken")));
        map.put("registerTime",StringUtils.toString(registerTime, eiMetadata.getMeta("registerTime")));
        return map;
    }
}