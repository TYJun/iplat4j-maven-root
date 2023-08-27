/**
* Generate time : 2021-07-12 17:20:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* SSACTscWorkOrderBillDetail 订单明细表
* 
*/
public class SSACTscWorkOrderBillDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* uuid*/
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String billNo = " ";		/* 自取餐主表的bill_no*/
    private String billDetailNo = " ";		/* 字表订单号：主表订单号+‘0’ ；主表订单号+‘1’*/
    private String menuNum = " ";		/* 菜品编码*/
    private String menuName = " ";		/* 菜品名称*/
    private Integer menuNumber = new Integer(0);		/* 菜品数量*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private BigDecimal packagePrice = new BigDecimal("0");		/* 打包费*/
    private BigDecimal menuTotalPrice = new BigDecimal("0");		/* 菜品总价*/
    private Integer evalLevel = new Integer(0);		/* 评价等级(0-10)*/
    private String evalContent = " ";		/* 评价内容*/
    private String evalTime = " ";		/* 评价时间*/
    private String evalAvg = " ";		/* 平均评分*/
    private String scheId = " ";		/* 排班id*/
    private String menuPicUrl = " ";		/* 排班id*/
    private String room = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("uuid");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("自取餐主表的bill_no");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billDetailNo");
        eiColumn.setDescName("字表订单号：主表订单号+‘0’ ；主表订单号+‘1’");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName("菜品编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName("菜品名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNumber");
        eiColumn.setDescName("菜品数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("菜品单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("packagePrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("打包费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("菜品总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setDescName("评价等级(0-10)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheId");
        eiColumn.setDescName("排班id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("room");
        eiColumn.setDescName("送餐地址");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACTscWorkOrderBillDetail() {
        initMetaData();
    }

    /**
     * get the id - uuid
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - uuid
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
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
     * get the billNo - 自取餐主表的bill_no
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 自取餐主表的bill_no
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the billDetailNo - 字表订单号：主表订单号+‘0’ ；主表订单号+‘1’
     * @return the billDetailNo
     */
    public String getBillDetailNo() {
        return this.billDetailNo;
    }

    /**
     * set the billDetailNo - 字表订单号：主表订单号+‘0’ ；主表订单号+‘1’
     */
    public void setBillDetailNo(String billDetailNo) {
        this.billDetailNo = billDetailNo;
    }

    /**
     * get the menuNum - 菜品编码
     * @return the menuNum
     */
    public String getMenuNum() {
        return this.menuNum;
    }

    /**
     * set the menuNum - 菜品编码
     */
    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    /**
     * get the menuName - 菜品名称
     * @return the menuName
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * set the menuName - 菜品名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * get the menuNumber - 菜品数量
     * @return the menuNumber
     */
    public Integer getMenuNumber() {
        return this.menuNumber;
    }

    /**
     * set the menuNumber - 菜品数量
     */
    public void setMenuNumber(Integer menuNumber) {
        this.menuNumber = menuNumber;
    }

    /**
     * get the menuPrice - 菜品单价
     * @return the menuPrice
     */
    public BigDecimal getMenuPrice() {
        return this.menuPrice;
    }

    /**
     * set the menuPrice - 菜品单价
     */
    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

    /**
     * get the menuTotalPrice - 菜品总价
     * @return the menuTotalPrice
     */
    public BigDecimal getMenuTotalPrice() {
        return this.menuTotalPrice;
    }

    /**
     * set the menuTotalPrice - 菜品总价
     */
    public void setMenuTotalPrice(BigDecimal menuTotalPrice) {
        this.menuTotalPrice = menuTotalPrice;
    }

    /**
     * get the evalLevel - 评价等级(0-10)
     * @return the evalLevel
     */
    public Integer getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel - 评价等级(0-10)
     */
    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
    }

    /**
     * get the evalContent - 评价内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the scheId - 排班id
     * @return the scheId
     */
    public String getScheId() {
        return this.scheId;
    }

    /**
     * set the scheId - 排班id
     */
    public void setScheId(String scheId) {
        this.scheId = scheId;
    }

    public String getMenuPicUrl() {
		return menuPicUrl;
	}

	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}

    public String getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(String evalTime) {
        this.evalTime = evalTime;
    }

    public String getEvalAvg() {
        return evalAvg;
    }

    public void setEvalAvg(String evalAvg) {
        this.evalAvg = evalAvg;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billDetailNo")), billDetailNo));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuNumber(NumberUtils.toInteger(StringUtils.toString(map.get("menuNumber")), menuNumber));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setPackagePrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("packagePrice")), packagePrice));
        setMenuTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuTotalPrice")), menuTotalPrice));
        setEvalLevel(NumberUtils.toInteger(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setScheId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheId")), scheId));
        setRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("billDetailNo",StringUtils.toString(billDetailNo, eiMetadata.getMeta("billDetailNo")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuNumber",StringUtils.toString(menuNumber, eiMetadata.getMeta("menuNumber")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("packagePrice",StringUtils.toString(packagePrice, eiMetadata.getMeta("packagePrice")));
        map.put("menuTotalPrice",StringUtils.toString(menuTotalPrice, eiMetadata.getMeta("menuTotalPrice")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("scheId",StringUtils.toString(scheId, eiMetadata.getMeta("scheId")));
        map.put("room",StringUtils.toString(room, eiMetadata.getMeta("room")));
        return map;
    }
}