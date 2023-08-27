package com.baosight.wilp.dk.jz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;

/**
 * 卡片实体类
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: DkCardItemInspection.java
 * @ClassName: DkCardItemInspection
 * @Package：com.baosight.wilp.dk.jz.domain
 * @author: LENOVO
 * @date: 2021年10月25日 下午2:12:09
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class DkCardItemInspection extends DaoEPBase{

	//序列号
	private static final long serialVersionUID = 1L;
    //id
    private Integer itemid = new Integer(0);		
    private String itemidXmid = " ";		/* 巡检项目ID*/
    //卡片id
    private String cardid = " ";		
    //项目名
    private String jitemname = " ";		
    //项目描述
    private String itemdesc = " ";		
    //参考值
    private String referencevalue = " ";	
    //排序
    private Integer sortno = new Integer(0);	
    //巡检实际值单位
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
//    public DiCardItemInspection() {
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
}
