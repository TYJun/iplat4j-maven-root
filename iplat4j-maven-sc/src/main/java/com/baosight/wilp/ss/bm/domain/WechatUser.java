package com.baosight.wilp.ss.bm.domain;

public class WechatUser {
    private static String userId;

    /**
     * get the userId - 人员id
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * set the userId - 人员id
     */
    public void setUserId(String userId) {
        WechatUser.userId = userId;
    }
}
