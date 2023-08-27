/**
* Generate time : 2021-05-07 17:20:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.jz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiSchemeitem
* 
*/
public class DiSchemeitem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String itemid = " ";		/* 条目ID,UUID*/
    private String schemeid = " ";		/* 基准的ID号,UUID*/
    private String itemidXmid = " ";		
    private Integer carditemid = new Integer(0);		/* 如果引用了卡片，这里是卡片条目的ID号,但不做外键*/
    private String itemname = " ";		/* 巡检项目*/
    private String itemdesc = " ";		/* 巡检说明*/
    private String referencevalue = " ";		/* 参考值*/
    private Integer sortno = new Integer(0);		/* 排序说明*/
    private String actualvalueunit = " ";		/* 巡检实际值单位*/
    private String needphoto = " ";		/* 是否需要拍照*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("itemid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("条目ID,UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemeid");
        eiColumn.setDescName("基准的ID号,UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemidXmid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("carditemid");
        eiColumn.setDescName("如果引用了卡片，这里是卡片条目的ID号,但不做外键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemname");
        eiColumn.setDescName("巡检项目");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemdesc");
        eiColumn.setDescName("巡检说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("referencevalue");
        eiColumn.setDescName("参考值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortno");
        eiColumn.setDescName("排序说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualvalueunit");
        eiColumn.setDescName("巡检实际值单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needphoto");
        eiColumn.setDescName("是否需要拍照");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DiSchemeitem() {
        initMetaData();
    }

    /**
     * get the itemid - 条目ID,UUID
     * @return the itemid
     */
    public String getItemid() {
        return this.itemid;
    }

    /**
     * set the itemid - 条目ID,UUID
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * get the schemeid - 基准的ID号,UUID
     * @return the schemeid
     */
    public String getSchemeid() {
        return this.schemeid;
    }

    /**
     * set the schemeid - 基准的ID号,UUID
     */
    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    /**
     * get the itemidXmid 
     * @return the itemidXmid
     */
    public String getItemidXmid() {
        return this.itemidXmid;
    }

    /**
     * set the itemidXmid 
     */
    public void setItemidXmid(String itemidXmid) {
        this.itemidXmid = itemidXmid;
    }

    /**
     * get the carditemid - 如果引用了卡片，这里是卡片条目的ID号,但不做外键
     * @return the carditemid
     */
    public Integer getCarditemid() {
        return this.carditemid;
    }

    /**
     * set the carditemid - 如果引用了卡片，这里是卡片条目的ID号,但不做外键
     */
    public void setCarditemid(Integer carditemid) {
        this.carditemid = carditemid;
    }

    /**
     * get the itemname - 巡检项目
     * @return the itemname
     */
    public String getItemname() {
        return this.itemname;
    }

    /**
     * set the itemname - 巡检项目
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * get the itemdesc - 巡检说明
     * @return the itemdesc
     */
    public String getItemdesc() {
        return this.itemdesc;
    }

    /**
     * set the itemdesc - 巡检说明
     */
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    /**
     * get the referencevalue - 参考值
     * @return the referencevalue
     */
    public String getReferencevalue() {
        return this.referencevalue;
    }

    /**
     * set the referencevalue - 参考值
     */
    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue;
    }

    /**
     * get the sortno - 排序说明
     * @return the sortno
     */
    public Integer getSortno() {
        return this.sortno;
    }

    /**
     * set the sortno - 排序说明
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

    /**
     * get the needphoto - 是否需要拍照
     * @return the needphoto
     */
    public String getNeedphoto() {
        return this.needphoto;
    }

    /**
     * set the needphoto - 是否需要拍照
     */
    public void setNeedphoto(String needphoto) {
        this.needphoto = needphoto;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setItemid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemid")), itemid));
        setSchemeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeid")), schemeid));
        setItemidXmid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemidXmid")), itemidXmid));
        setCarditemid(NumberUtils.toInteger(StringUtils.toString(map.get("carditemid")), carditemid));
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setItemdesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemdesc")), itemdesc));
        setReferencevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("referencevalue")), referencevalue));
        setSortno(NumberUtils.toInteger(StringUtils.toString(map.get("sortno")), sortno));
        setActualvalueunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualvalueunit")), actualvalueunit));
        setNeedphoto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needphoto")), needphoto));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("itemid",StringUtils.toString(itemid, eiMetadata.getMeta("itemid")));
        map.put("schemeid",StringUtils.toString(schemeid, eiMetadata.getMeta("schemeid")));
        map.put("itemidXmid",StringUtils.toString(itemidXmid, eiMetadata.getMeta("itemidXmid")));
        map.put("carditemid",StringUtils.toString(carditemid, eiMetadata.getMeta("carditemid")));
        map.put("itemname",StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("itemdesc",StringUtils.toString(itemdesc, eiMetadata.getMeta("itemdesc")));
        map.put("referencevalue",StringUtils.toString(referencevalue, eiMetadata.getMeta("referencevalue")));
        map.put("sortno",StringUtils.toString(sortno, eiMetadata.getMeta("sortno")));
        map.put("actualvalueunit",StringUtils.toString(actualvalueunit, eiMetadata.getMeta("actualvalueunit")));
        map.put("needphoto",StringUtils.toString(needphoto, eiMetadata.getMeta("needphoto")));
        return map;
    }
}