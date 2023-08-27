package com.baosight.wilp.ir.dl.app.domain;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;

import java.io.Serializable;

/**
 * @PackageName com.baosight.wilp.cu.yd.domain
 * @ClassName AppToken
 * @Description app登录的身份信息
 * @Author chunchen2
 * @Date 2021/11/30 19:27
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/30 19:27
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class AppLoginToken implements Serializable {

    /**
     * token 唯一标识
     **/
    private String tokenId;

    /**
     * token 对应的工号
     **/
    private String username;

    /**
     * token 对应的工号
     **/
    private String workNo;

    /**
     * token 过期的有效期（周期）
     **/
    private long expireTime = -1;

    /**
     * token 创建时间
     **/
    private long createTime;

    /**
     * token 关联的用户信息
     **/
    private EiInfo info;

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public EiInfo getInfo() {
        return info;
    }

    public void setInfo(EiInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
