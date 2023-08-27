package com.baosight.wilp.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName AuthKeyEitity
 * @Description 授权成功保存的对象实体
 * @Author chunchen2
 * @Date 2023/2/23 16:23
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/23 16:23
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class AuthKeyEitity {

    public AuthKeyEitity(){}

    public AuthKeyEitity(String userId, String userName, String authKEY, String authEndTime, String transactionId){
        this.userId = userId;
        this.userName = userName;
        this.authKEY = authKEY;
        this.authEndTime = authEndTime;
        this.transactionId = transactionId;
    }

    // 用户工号
    private String userId;

    // 用户姓名
    private String userName;

    // 临时授权Key
    private String authKEY;

    // 授权key 过期时间 "2023-02-23 21:11:36"
    private String authEndTime;

    // 业务编号，用于数据签名
    private String transactionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthKEY() {
        return authKEY;
    }

    public void setAuthKEY(String authKEY) {
        this.authKEY = authKEY;
    }

    public String getAuthEndTime() {
        return authEndTime;
    }

    public void setAuthEndTime(String authEndTime) {
        this.authEndTime = authEndTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JSONField(serialize = false)
    public boolean isExpire() {
        Date now = new Date();
        // 防止临界值失效， 预留30s 调用接口的时间
        String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(now.getTime() - 30 * 1000));
        boolean compareResult =  currentDateTime.compareTo(this.getAuthEndTime()) >= 0;
        return compareResult;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
