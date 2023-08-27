package com.baosight.wilp.ss.ac.commen;

import java.io.Serializable;

/**
 * 食堂订餐时间实体类
 * @ClassName CanteenTimesEntity
 * @Desc TODO
 * @author kangroo
 * @date 2018年3月24日 上午11:22:52
 */
public class CanteenTimesEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    //主键
    private String id;
    //食堂编码
    private String canteenNum;
    //食堂名称
    private String canteenName;
    //菜品编码
    private String mealNum;
    //菜品名称
    private String mealName;
    //当天时间
    private String todayOrderTime;
    //明天时间
    private String tomorrowOrderTime;
    //需求时间
    private int advanceTime;
    //送达时间
    private String sendTime;
    //备注
    private String remark;
    //今日菜品编码
    private String todayMealTypeCode;

    /**
     * get the id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * set the id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * get the canteenNum
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return canteenNum;
    }
    /**
     * set the canteenNum
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }
    /**
     * get the canteenName
     * @return the canteenName
     */
    public String getCanteenName() {
        return canteenName;
    }
    /**
     * set the canteenName
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }
    /**
     * get the mealNum
     * @return the mealNum
     */
    public String getMealNum() {
        return mealNum;
    }
    /**
     * set the mealNum
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }
    /**
     * get the mealName
     * @return the mealName
     */
    public String getMealName() {
        return mealName;
    }
    /**
     * set the mealName
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    /**
     * get the todayOrderTime
     * @return the todayOrderTime
     */
    public String getTodayOrderTime() {
        return todayOrderTime;
    }
    /**
     * set the todayOrderTime
     */
    public void setTodayOrderTime(String todayOrderTime) {
        this.todayOrderTime = todayOrderTime;
    }
    /**
     * get the tomorrowOrderTime
     * @return the tomorrowOrderTime
     */
    public String getTomorrowOrderTime() {
        return tomorrowOrderTime;
    }
    /**
     * set the tomorrowOrderTime
     */
    public void setTomorrowOrderTime(String tomorrowOrderTime) {
        this.tomorrowOrderTime = tomorrowOrderTime;
    }
    /**
     * get the advanceTime
     * @return the advanceTime
     */
    public int getAdvanceTime() {
        return advanceTime;
    }
    /**
     * set the advanceTime
     */
    public void setAdvanceTime(int advanceTime) {
        this.advanceTime = advanceTime;
    }
    /**
     * get the sendTime
     * @return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }
    /**
     * set the sendTime
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    /**
     * get the remark
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * set the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * get the todayMealTypeCode
     * @return the todayMealTypeCode
     */
    public String getTodayMealTypeCode() {
        return todayMealTypeCode;
    }
    /**
     * set the todayMealTypeCode
     */
    public void setTodayMealTypeCode(String todayMealTypeCode) {
        if (todayMealTypeCode == null || "null".equals(todayMealTypeCode.toLowerCase())) {
            todayMealTypeCode = "";
        }
        this.todayMealTypeCode = todayMealTypeCode;
    }

}
