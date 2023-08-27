/**
* Generate time : 2021-04-14 17:23:55
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;

/**
* IMKP02
* 
*/
public class ImCardRelation extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer itemid = new Integer(0);		
    private String itemidXmid = " ";		/* 巡检项目ID*/
    private String cardid = " ";		
    private String jitemname = " ";		
    private String itemdesc = " ";		
    private String referencevalue = " ";		
    private Integer sortno = new Integer(0);		
    private String actualvalueunit = " ";		/* 巡检实际值单位*/

    /**
     * initialize the metadata
     */
//    public void initMetaData() {
//        EiColumn eiColumn;
//
//        eiColumn = new EiColumn("itemid");
//        eiColumn.setPrimaryKey(true);
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("itemidXmid");
//        eiColumn.setDescName("巡检项目ID");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("cardid");
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("jitemname");
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("itemdesc");
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("referencevalue");
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("sortno");
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);
//
//        eiColumn = new EiColumn("actualvalueunit");
//        eiColumn.setDescName("巡检实际值单位");
//        eiMetadata.addMeta(eiColumn);
//
//
//    }

    /**
     * the constructor
     */
//    public DiCardRelation() {
//        initMetaData();
//    }

    /**
     * get the itemid 
     * @return the itemid
     */
    public Integer getItemid() {
        return this.itemid;
    }

    /**
     * set the itemid 
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * get the itemidXmid - 巡检项目ID
     * @return the itemidXmid
     */
    public String getItemidXmid() {
        return this.itemidXmid;
    }

    /**
     * set the itemidXmid - 巡检项目ID
     */
    public void setItemidXmid(String itemidXmid) {
        this.itemidXmid = itemidXmid;
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
     * get the jitemname 
     * @return the jitemname
     */
    public String getJitemname() {
        return this.jitemname;
    }

    /**
     * set the jitemname 
     */
    public void setJitemname(String jitemname) {
        this.jitemname = jitemname;
    }

    /**
     * get the itemdesc 
     * @return the itemdesc
     */
    public String getItemdesc() {
        return this.itemdesc;
    }

    /**
     * set the itemdesc 
     */
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    /**
     * get the referencevalue 
     * @return the referencevalue
     */
    public String getReferencevalue() {
        return this.referencevalue;
    }

    /**
     * set the referencevalue 
     */
    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue;
    }

    /**
     * get the sortno 
     * @return the sortno
     */
    public Integer getSortno() {
        return this.sortno;
    }

    /**
     * set the sortno 
     */
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    /**
     * get the actualvalueunit - 巡检实际值单位
     * @return the actualvalueunit
     */
    public String getActualvalueunit() {
        return this.actualvalueunit;
    }

    /**
     * set the actualvalueunit - 巡检实际值单位
     */
    public void setActualvalueunit(String actualvalueunit) {
        this.actualvalueunit = actualvalueunit;
    }

//    /**
//     * get the value from Map
//     */
//    public void fromMap(Map map) {
//
//        setItemid(NumberUtils.toInteger(StringUtils.toString(map.get("itemid")), itemid));
//        setItemidXmid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemidXmid")), itemidXmid));
//        setCardid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardid")), cardid));
//        setJitemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jitemname")), jitemname));
//        setItemdesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemdesc")), itemdesc));
//        setReferencevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("referencevalue")), referencevalue));
//        setSortno(NumberUtils.toInteger(StringUtils.toString(map.get("sortno")), sortno));
//        setActualvalueunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualvalueunit")), actualvalueunit));
//    }

//    /**
//     * set the value to Map
//     */
//    public Map toMap() {
//        Map map = new HashMap();
//        map.put("itemid",StringUtils.toString(itemid, eiMetadata.getMeta("itemid")));
//        map.put("itemidXmid",StringUtils.toString(itemidXmid, eiMetadata.getMeta("itemidXmid")));
//        map.put("cardid",StringUtils.toString(cardid, eiMetadata.getMeta("cardid")));
//        map.put("jitemname",StringUtils.toString(jitemname, eiMetadata.getMeta("jitemname")));
//        map.put("itemdesc",StringUtils.toString(itemdesc, eiMetadata.getMeta("itemdesc")));
//        map.put("referencevalue",StringUtils.toString(referencevalue, eiMetadata.getMeta("referencevalue")));
//        map.put("sortno",StringUtils.toString(sortno, eiMetadata.getMeta("sortno")));
//        map.put("actualvalueunit",StringUtils.toString(actualvalueunit, eiMetadata.getMeta("actualvalueunit")));
//        return map;
//    }
}