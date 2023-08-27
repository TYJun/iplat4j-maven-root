package com.baosight.wilp.dk.jz.domain;

import java.sql.Timestamp;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;

/**
 * 卡片主卡实体类
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: DkCardMaintain.java
 * @ClassName: DkCardMaintain
 * @Package：com.baosight.wilp.dk.jz.domain
 * @author: LENOVO
 * @date: 2021年10月25日 下午2:12:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class DkCardMaintain extends DaoEPBase{

	//序列号
	private static final long serialVersionUID = 1L;
    //卡片id
    private String cardid = " ";
    //卡片编码
    private String cardcode = " ";	
    //卡片名字
    private String cardname = " ";		
    //卡片类型
    private String cardtype = " ";		
    //备注
    private String memo = " ";		
    //创建时间
    private Timestamp createtime ;		
    //创建人
    private String createman = " ";		
    //卡片创建地址
    private String createip = " ";	
    //修改时间
    private Timestamp modifytime ;	
    //修改人
    private String modifyman = " ";	
    //修改地址
    private String modifyip = " ";	
    //状态
    private Integer status = new Integer(0);		/* ״̬,0=δ*/
    //巡检类型
    private String insptype = " ";		/* 巡检类型*/

    /**
     * initialize the metadata   源数据初始化
     */
    public void initMetaData() {
        EiColumn eiColumn;
        //卡片id
        eiColumn = new EiColumn("cardid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //卡片编码
        eiColumn = new EiColumn("cardcode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //卡片名字
        eiColumn = new EiColumn("cardname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //卡片类型
        eiColumn = new EiColumn("cardtype");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //备注
        eiColumn = new EiColumn("memo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //创建时间
        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //创建人
        eiColumn = new EiColumn("createman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //卡片创建地址
        eiColumn = new EiColumn("createip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改时间
        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改人
        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改地址
        eiColumn = new EiColumn("modifyip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //状态
        eiColumn = new EiColumn("status");
        eiColumn.setDescName("״̬,0=δ");
        eiMetadata.addMeta(eiColumn);
        //巡检类型
        eiColumn = new EiColumn("insptype");
        eiColumn.setDescName("巡检类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor   无参构造
     */
    public DkCardMaintain() {
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
}
