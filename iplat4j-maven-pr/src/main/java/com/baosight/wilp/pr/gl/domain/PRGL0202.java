/**
* Generate time : 2021-05-03 17:08:49
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pr.gl.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * PRGL0202页面实体类
 * 
 * @Title: PRGL0202.java
 * @ClassName: PRGL0202
 * @Package：com.baosight.wilp.pr.gl.domain
 * @author: zhangjiaqian
 * @date: 2021年10月25日 下午2:19:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class PRGL0202 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String dangerlogsid = " ";		/* 主键*/
    private String dangerid = "";		/* 外键,关联的问题*/
    private String logsman = " ";		/* 操作人*/
    private String logstime = " ";		/* 操作时间*/
    private String rejectstatus = " ";		/* 操作状态*/
    private String rejectreason = " ";		/* 操作原因*/
    private Integer id = new Integer(0);		/* 自增字段*/
    private String dangercode = "";	 /* 问题编码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dangerlogsid");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerid");
        eiColumn.setDescName("外键,关联的问题");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("logsman");
        eiColumn.setDescName("操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("logstime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectstatus");
        eiColumn.setDescName("操作状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectreason");
        eiColumn.setDescName("操作原因");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dangercode");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("自增字段");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PRGL0202() {
        initMetaData();
    }

    /**
     * get the dangerlogsid - 主键
     * @return the dangerlogsid
     */
    public String getDangerlogsid() {
        return this.dangerlogsid;
    }

    /**
     * set the dangerlogsid - 主键
     */
    public void setDangerlogsid(String dangerlogsid) {
        this.dangerlogsid = dangerlogsid;
    }

    /**
     * get the dangerid - 外键,关联的问题
     * @return the dangerid
     */
    public String getDangerid() {
        return this.dangerid;
    }

    /**
     * set the dangerid - 外键,关联的问题
     */
    public void setDangerid(String dangerid) {
        this.dangerid = dangerid;
    }

    /**
     * get the logsman - 操作人
     * @return the logsman
     */
    public String getLogsman() {
        return this.logsman;
    }

    /**
     * set the logsman - 操作人
     */
    public void setLogsman(String logsman) {
        this.logsman = logsman;
    }

    /**
     * get the logstime - 操作时间
     * @return the logstime
     */
    public String getLogstime() {
        return this.logstime;
    }

    /**
     * set the logstime - 操作时间
     */
    public void setLogstime(String logstime) {
        this.logstime = logstime;
    }

    /**
     * get the rejectstatus - 操作状态
     * @return the rejectstatus
     */
    public String getRejectstatus() {
        return this.rejectstatus;
    }

    /**
     * set the rejectstatus - 操作状态
     */
    public void setRejectstatus(String rejectstatus) {
        this.rejectstatus = rejectstatus;
    }

    /**
     * get the rejectreason - 操作原因
     * @return the rejectreason
     */
    public String getRejectreason() {
        return this.rejectreason;
    }

    /**
     * set the rejectreason - 操作原因
     */
    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason;
    }

    /**
     * get the id - 自增字段
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 自增字段
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDangercode() {
        return dangercode;
    }

    public void setDangercode(String dangercode) {
        this.dangercode = dangercode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDangerlogsid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerlogsid")), dangerlogsid));
        setDangerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerid")), dangerid));
        setLogsman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("logsman")), logsman));
        setLogstime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("logstime")), logstime));
        setRejectstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectstatus")), rejectstatus));
        setRejectreason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectreason")), rejectreason));
        setDangercode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangercode")), dangercode));
        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dangerlogsid",StringUtils.toString(dangerlogsid, eiMetadata.getMeta("dangerlogsid")));
        map.put("dangerid",StringUtils.toString(dangerid, eiMetadata.getMeta("dangerid")));
        map.put("logsman",StringUtils.toString(logsman, eiMetadata.getMeta("logsman")));
        map.put("logstime",StringUtils.toString(logstime, eiMetadata.getMeta("logstime")));
        map.put("rejectstatus",StringUtils.toString(rejectstatus, eiMetadata.getMeta("rejectstatus")));
        map.put("rejectreason",StringUtils.toString(rejectreason, eiMetadata.getMeta("rejectreason")));
        map.put("dangercode",StringUtils.toString(dangercode, eiMetadata.getMeta("dangercode")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        return map;
    }
}