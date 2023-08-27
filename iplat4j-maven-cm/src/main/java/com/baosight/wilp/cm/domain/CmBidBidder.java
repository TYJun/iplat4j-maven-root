/**
* Generate time : 2022-01-28 16:52:47
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* CmBidBidder
* 
*/
public class CmBidBidder extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 合同招标参标单位主键*/
    private String bidPk = " ";		/* 招标表主键*/
    private String bidderNo = " ";		/* 招标单位编号*/
    private String bidderName = " ";		/* 招标单位名称*/
    private Integer sort = new Integer(0);		/* 排序*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreator = " ";		/* 创建人*/
    private Timestamp recCreateTime ;		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("合同招标参标单位主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidPk");
        eiColumn.setDescName("招标表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidderNo");
        eiColumn.setDescName("招标单位编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidderName");
        eiColumn.setDescName("招标单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorNo");
        eiColumn.setDescName("创建人工号");
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
    public CmBidBidder() {
        initMetaData();
    }

    /**
     * get the id - 合同招标参标单位主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 合同招标参标单位主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the bidPk - 招标表主键
     * @return the bidPk
     */
    public String getBidPk() {
        return this.bidPk;
    }

    /**
     * set the bidPk - 招标表主键
     */
    public void setBidPk(String bidPk) {
        this.bidPk = bidPk;
    }

    /**
     * get the bidderNo - 招标单位编号
     * @return the bidderNo
     */
    public String getBidderNo() {
        return this.bidderNo;
    }

    /**
     * set the bidderNo - 招标单位编号
     */
    public void setBidderNo(String bidderNo) {
        this.bidderNo = bidderNo;
    }

    /**
     * get the bidderName - 招标单位名称
     * @return the bidderName
     */
    public String getBidderName() {
        return this.bidderName;
    }

    /**
     * set the bidderName - 招标单位名称
     */
    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    /**
     * get the sort - 排序
     * @return the sort
     */
    public Integer getSort() {
        return this.sort;
    }

    /**
     * set the sort - 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * get the recCreatorNo - 创建人工号
     * @return the recCreatorNo
     */
    public String getRecCreatorNo() {
        return this.recCreatorNo;
    }

    /**
     * set the recCreatorNo - 创建人工号
     */
    public void setRecCreatorNo(String recCreatorNo) {
        this.recCreatorNo = recCreatorNo;
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
    public Timestamp getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Timestamp recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBidPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bidPk")), bidPk));
        setBidderNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bidderNo")), bidderNo));
        setBidderName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bidderName")), bidderName));
        setSort(NumberUtils.toInteger(StringUtils.toString(map.get("sort")), sort));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("bidPk",StringUtils.toString(bidPk, eiMetadata.getMeta("bidPk")));
        map.put("bidderNo",StringUtils.toString(bidderNo, eiMetadata.getMeta("bidderNo")));
        map.put("bidderName",StringUtils.toString(bidderName, eiMetadata.getMeta("bidderName")));
        map.put("sort",StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        return map;
    }
}