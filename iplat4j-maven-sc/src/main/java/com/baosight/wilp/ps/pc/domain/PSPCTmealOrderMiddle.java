/**
* Generate time : 2021-06-09 18:35:46
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealOrderMiddle 订单中间表实体类
* 
*/
public class PSPCTmealOrderMiddle extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String billId = " ";		/* 订单id*/
    private String billNo = " ";		/* 订单编号*/
    private String statusCode = " ";		/* 订单状态*/
    private String payStatus = " ";		/* 支付状态*/
    private String moduleCode = " ";		/* 模块编号*/
    private String recCreateTime = " ";		/* 下单时间*/
    private String needDate = " ";		/* 需要时间*/
    private String mealNum = " ";		/* 餐次编码*/
    private String canteenNum = " ";		/* 食堂编号*/
    private String canteenName = " ";		/* 食堂名称*/
    private String openId = " ";		/* openid*/
    private String memo = " ";		/* 备注*/
    private String userName = " ";		/* 用户*/
    private String counter = " ";		/* 计数器*/
    private String param1 = " ";		/* 备用字段1*/
    private String param2 = " ";		/* 备用字段2*/

    private String payNo = " ";		/* 支付id*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("订单id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("订单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payStatus");
        eiColumn.setDescName("支付状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleCode");
        eiColumn.setDescName("模块编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("下单时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needDate");
        eiColumn.setDescName("需要时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("openid");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("counter");
        eiColumn.setDescName("计数器");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("param1");
        eiColumn.setDescName("备用字段1");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("param2");
        eiColumn.setDescName("备用字段2");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payNo");
        eiColumn.setDescName("支付id");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public PSPCTmealOrderMiddle() {
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
     * get the billId - 订单id
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 订单id
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the billNo - 订单编号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单编号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the statusCode - 订单状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 订单状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the payStatus - 支付状态
     * @return the payStatus
     */
    public String getPayStatus() {
        return this.payStatus;
    }

    /**
     * set the payStatus - 支付状态
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * get the moduleCode - 模块编号
     * @return the moduleCode
     */
    public String getModuleCode() {
        return this.moduleCode;
    }

    /**
     * set the moduleCode - 模块编号
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * get the recCreateTime - 下单时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 下单时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the needDate - 需要时间
     * @return the needDate
     */
    public String getNeedDate() {
        return this.needDate;
    }

    /**
     * set the needDate - 需要时间
     */
    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    /**
     * get the mealNum - 餐次编码
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐次编码
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the canteenNum - 食堂编号
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编号
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the openId - openid
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the userName - 用户
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName - 用户
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the counter - 计数器
     * @return the counter
     */
    public String getCounter() {
        return this.counter;
    }

    /**
     * set the counter - 计数器
     */
    public void setCounter(String counter) {
        this.counter = counter;
    }

    /**
     * get the param1 - 备用字段1
     * @return the param1
     */
    public String getParam1() {
        return this.param1;
    }

    /**
     * set the param1 - 备用字段1
     */
    public void setParam1(String param1) {
        this.param1 = param1;
    }

    /**
     * get the param2 - 备用字段2
     * @return the param2
     */
    public String getParam2() {
        return this.param2;
    }

    /**
     * set the param2 - 备用字段2
     */
    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setPayStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payStatus")), payStatus));
        setModuleCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleCode")), moduleCode));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setNeedDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needDate")), needDate));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setCounter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("counter")), counter));
        setParam1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("param1")), param1));
        setParam2(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("param2")), param2));
        setPayNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payNo")), payNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("payStatus",StringUtils.toString(payStatus, eiMetadata.getMeta("payStatus")));
        map.put("moduleCode",StringUtils.toString(moduleCode, eiMetadata.getMeta("moduleCode")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("needDate",StringUtils.toString(needDate, eiMetadata.getMeta("needDate")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("counter",StringUtils.toString(counter, eiMetadata.getMeta("counter")));
        map.put("param1",StringUtils.toString(param1, eiMetadata.getMeta("param1")));
        map.put("param2",StringUtils.toString(param2, eiMetadata.getMeta("param2")));
        map.put("payNo",StringUtils.toString(payNo, eiMetadata.getMeta("payNo")));
        return map;
    }
}