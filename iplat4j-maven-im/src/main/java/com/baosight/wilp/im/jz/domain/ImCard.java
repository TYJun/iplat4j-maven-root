/**
* Generate time : 2021-04-19 15:36:51
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import java.sql.Timestamp;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;

/**
* IMKP01
* 
*/
public class ImCard extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String cardid = " ";		
    private String cardcode = " ";		
    private String cardname = " ";		
    private String cardtype = " ";		
    private String memo = " ";		
    private Timestamp createtime ;		
    private String createman = " ";		
    private String createip = " ";		
    private Timestamp modifytime ;		
    private String modifyman = " ";		
    private String modifyip = " ";		
    private Integer status = new Integer(0);		/* ״̬,0=δ*/
    private String insptype = " ";		/* 巡检类型*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("cardid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardcode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardtype");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("״̬,0=δ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("insptype");
        eiColumn.setDescName("巡检类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImCard() {
        initMetaData();
    }

    /**
     * get the cardid 
     * @return the cardid
     */
    public String getCardid() {
        return this.cardid;
    }

    /**
     * set the cardid 
     */
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    /**
     * get the cardcode 
     * @return the cardcode
     */
    public String getCardcode() {
        return this.cardcode;
    }

    /**
     * set the cardcode 
     */
    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    /**
     * get the cardname 
     * @return the cardname
     */
    public String getCardname() {
        return this.cardname;
    }

    /**
     * set the cardname 
     */
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    /**
     * get the cardtype 
     * @return the cardtype
     */
    public String getCardtype() {
        return this.cardtype;
    }

    /**
     * set the cardtype 
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * get the memo 
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo 
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the createtime 
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime 
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * get the createman 
     * @return the createman
     */
    public String getCreateman() {
        return this.createman;
    }

    /**
     * set the createman 
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * get the createip 
     * @return the createip
     */
    public String getCreateip() {
        return this.createip;
    }

    /**
     * set the createip 
     */
    public void setCreateip(String createip) {
        this.createip = createip;
    }

    /**
     * get the modifytime 
     * @return the modifytime
     */
    public Timestamp getModifytime() {
        return this.modifytime;
    }

    /**
     * set the modifytime 
     */
    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * get the modifyman 
     * @return the modifyman
     */
    public String getModifyman() {
        return this.modifyman;
    }

    /**
     * set the modifyman 
     */
    public void setModifyman(String modifyman) {
        this.modifyman = modifyman;
    }

    /**
     * get the modifyip 
     * @return the modifyip
     */
    public String getModifyip() {
        return this.modifyip;
    }

    /**
     * set the modifyip 
     */
    public void setModifyip(String modifyip) {
        this.modifyip = modifyip;
    }

    /**
     * get the status - ״̬,0=δ
     * @return the status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * set the status - ״̬,0=δ
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get the insptype - 巡检类型
     * @return the insptype
     */
    public String getInsptype() {
        return this.insptype;
    }

    /**
     * set the insptype - 巡检类型
     */
    public void setInsptype(String insptype) {
        this.insptype = insptype;
    }

//    /**
//     * get the value from Map
//     */
//    public void fromMap(Map map) {
//
//        setCardid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardid")), cardid));
//        setCardcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardcode")), cardcode));
//        setCardname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardname")), cardname));
//        setCardtype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardtype")), cardtype));
//        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
//        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
//        setCreateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createman")), createman));
//        setCreateip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createip")), createip));
//        setModifytime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifytime"))));
//        setModifyman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyman")), modifyman));
//        setModifyip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyip")), modifyip));
//        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
//        setInsptype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("insptype")), insptype));
//    }
//
//    /**
//     * set the value to Map
//     */
//    public Map toMap() {
//        Map map = new HashMap();
//        map.put("cardid",StringUtils.toString(cardid, eiMetadata.getMeta("cardid")));
//        map.put("cardcode",StringUtils.toString(cardcode, eiMetadata.getMeta("cardcode")));
//        map.put("cardname",StringUtils.toString(cardname, eiMetadata.getMeta("cardname")));
//        map.put("cardtype",StringUtils.toString(cardtype, eiMetadata.getMeta("cardtype")));
//        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
//        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
//        map.put("createman",StringUtils.toString(createman, eiMetadata.getMeta("createman")));
//        map.put("createip",StringUtils.toString(createip, eiMetadata.getMeta("createip")));
//        map.put("modifytime",StringUtils.toString(modifytime, eiMetadata.getMeta("modifytime")));
//        map.put("modifyman",StringUtils.toString(modifyman, eiMetadata.getMeta("modifyman")));
//        map.put("modifyip",StringUtils.toString(modifyip, eiMetadata.getMeta("modifyip")));
//        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
//        map.put("insptype",StringUtils.toString(insptype, eiMetadata.getMeta("insptype")));
//        return map;
//    }
}