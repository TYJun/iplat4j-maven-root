/**
* Generate time : 2022-02-17 16:50:11
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.gg.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DMGG01
* 
*/
public class DMGG01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String noticeNo = " ";		/* 公告序号*/
    private String noticeType = " ";		/* 公告类别*/
    private String notice = " ";		/* 公告内容*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noticeNo");
        eiColumn.setDescName("公告序号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noticeType");
        eiColumn.setDescName("公告类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("notice");
        eiColumn.setDescName("公告内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DMGG01() {
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
     * get the noticeNo - 公告序号
     * @return the noticeNo
     */
    public String getNoticeNo() {
        return this.noticeNo;
    }

    /**
     * set the noticeNo - 公告序号
     */
    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    /**
     * get the noticeType - 公告类别
     * @return the noticeType
     */
    public String getNoticeType() {
        return this.noticeType;
    }

    /**
     * set the noticeType - 公告类别
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * get the notice - 公告内容
     * @return the notice
     */
    public String getNotice() {
        return this.notice;
    }

    /**
     * set the notice - 公告内容
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setNoticeNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("noticeNo")), noticeNo));
        setNoticeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("noticeType")), noticeType));
        setNotice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notice")), notice));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("noticeNo",StringUtils.toString(noticeNo, eiMetadata.getMeta("noticeNo")));
        map.put("noticeType",StringUtils.toString(noticeType, eiMetadata.getMeta("noticeType")));
        map.put("notice",StringUtils.toString(notice, eiMetadata.getMeta("notice")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        return map;
    }
}