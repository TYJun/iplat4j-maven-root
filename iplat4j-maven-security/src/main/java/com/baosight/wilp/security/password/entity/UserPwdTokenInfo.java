package com.baosight.wilp.security.password.entity;

import java.io.Serializable;

/**
 * @PackageName com.baosight.wilp.security.login.entity
 * @ClassName UserPwdTokenInfo
 * @Description pc存储用户密码有效时间
 * @Author chunchen2
 * @Date 2022/9/1 13:11
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/9/1 13:11
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class UserPwdTokenInfo implements Serializable {

    private String creToken;

    /**
     * token 过期的有效期（周期）
     **/
    private long expireTime = -1;

    /**
     * token 创建时间
     **/
    private long createTime;

    public String getCreToken() {
        return creToken;
    }

    public void setCreToken(String creToken) {
        this.creToken = creToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
