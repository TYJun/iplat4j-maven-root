/**
* Generate time : 2021-05-25 20:07:36
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* TSUserloginInfo 用户登录信息实体类
* 
*/
public class PSPCTsUserloginInfo extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String workNo = " ";		/* 工号*/
    private Timestamp lastLoginTime ;		/* 上次登录时间*/
    private Timestamp lastLogoutTime ;		/* 上次登出时间*/
    private String lastUseOrgCode = " ";		/* 上次使用机构代码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastLoginTime");
        eiColumn.setDescName("上次登录时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastLogoutTime");
        eiColumn.setDescName("上次登出时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastUseOrgCode");
        eiColumn.setDescName("上次使用机构代码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTsUserloginInfo() {
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
     * get the workNo - 工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the lastLoginTime - 上次登录时间
     * @return the lastLoginTime
     */
    public Timestamp getLastLoginTime() {
        return this.lastLoginTime;
    }

    /**
     * set the lastLoginTime - 上次登录时间
     */
    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * get the lastLogoutTime - 上次登出时间
     * @return the lastLogoutTime
     */
    public Timestamp getLastLogoutTime() {
        return this.lastLogoutTime;
    }

    /**
     * set the lastLogoutTime - 上次登出时间
     */
    public void setLastLogoutTime(Timestamp lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    /**
     * get the lastUseOrgCode - 上次使用机构代码
     * @return the lastUseOrgCode
     */
    public String getLastUseOrgCode() {
        return this.lastUseOrgCode;
    }

    /**
     * set the lastUseOrgCode - 上次使用机构代码
     */
    public void setLastUseOrgCode(String lastUseOrgCode) {
        this.lastUseOrgCode = lastUseOrgCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setLastLoginTime(DateUtils.toTimestamp(StringUtils.toString(map.get("lastLoginTime"))));
        setLastLogoutTime(DateUtils.toTimestamp(StringUtils.toString(map.get("lastLogoutTime"))));
        setLastUseOrgCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastUseOrgCode")), lastUseOrgCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("lastLoginTime",StringUtils.toString(lastLoginTime, eiMetadata.getMeta("lastLoginTime")));
        map.put("lastLogoutTime",StringUtils.toString(lastLogoutTime, eiMetadata.getMeta("lastLogoutTime")));
        map.put("lastUseOrgCode",StringUtils.toString(lastUseOrgCode, eiMetadata.getMeta("lastUseOrgCode")));
        return map;
    }
}