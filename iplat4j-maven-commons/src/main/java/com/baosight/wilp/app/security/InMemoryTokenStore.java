package com.baosight.wilp.app.security;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.app.domain.AppLoginToken;
import com.baosight.wilp.utils.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName com.baosight.wilp.cu.yd.security
 * @ClassName InMemoryTokenStore
 * @Description 内存方式存储app的token信息
 * @Author chunchen2
 * @Date 2021/11/30 20:06
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/30 20:06
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Component
// 这里为redis实现留一个判断，省的改代码
@ConditionalOnMissingBean(value = TokenStore.class, ignored = InMemoryTokenStore.class)
public class InMemoryTokenStore implements TokenStore {

    /**
     * token 过期时间，默认2小时
     **/
    private long expireTime = 24 * 60 * 60 * 1000 * 7;
    private boolean isSingleUser;

    /**
     *  清空内存中的会话数的周期
     **/
    private static final int CLEAR_INTERVAL = 1000;

    public InMemoryTokenStore(){
        String AppExpireTimeStr = PlatApplicationContext.getProperty("iplat.login.app.token.expireTime");
        expireTime = (null == AppExpireTimeStr || "".equalsIgnoreCase(AppExpireTimeStr.trim())) ? expireTime :
                Long.parseLong(AppExpireTimeStr);

        String isSingleUserStr = PlatApplicationContext.getProperty("iplat.login.app.token.singleUser");;
        isSingleUser = (null == isSingleUserStr || "".equalsIgnoreCase(isSingleUserStr.trim())) ? false :
                Boolean.parseBoolean(isSingleUserStr);
    }

    /**
     * 用于保存登录人的信息  key: uuid
     **/
    public static Map<String, AppLoginToken> tokenMap = new ConcurrentHashMap<>();

    /**
     * @Title getToken
     * @Author chunchen2
     * @Description // 获取Token的信息
     * @Date 21:14 2021/11/30
     * @param tokenId
     * @return com.baosight.wilp.cu.yd.domain.AppToken
     * @throws
     **/
    public AppLoginToken getToken(String tokenId) {
        return tokenMap.get(tokenId);
    }

    /**
     * @Title createToken
     * @Author chunchen2
     * @Description // 登录成功，创建token信息
     * @Date 20:55 2021/11/30
     * @param appToken
     * @return java.lang.String
     * @throws
     **/
    public String createToken(AppLoginToken appToken) {
        String userName =appToken.getWorkNo();
        appToken.setExpireTime(expireTime); // 设置每个token的过期时间
        appToken.setCreateTime(System.currentTimeMillis());
        appToken.setUsername(userName);

        if(isSingleUser){ // 只允许同时一个人登录, 清空内存中以及存在的登录信息
            appToken.setExpireTime(-1); // 此处添加，保持会话一直不过期，如果需要过期的话，注释改行即可
            Collection<AppLoginToken> appTokenValues = tokenMap.values();
            Iterator<AppLoginToken> appTokenIterator = appTokenValues.iterator();
            while(appTokenIterator.hasNext()){
                AppLoginToken token = appTokenIterator.next();
                if(token.getUsername().equalsIgnoreCase(userName)){ // 删除这条登录信息
                    appTokenIterator.remove();
                }
            }
        } else { // 清除要过期的会话信息，暂时不允许不过期，这样恶意登录内存可能会产生问题
            clearInValidTokenRecord();
        }
        String tokenId = UUID.fastUUID().toString(true);
        tokenMap.put(tokenId, appToken);

        return tokenId;
    }

    /**
     * @Title verifyToken
     * @Author chunchen2
     * @Description // 校验当前token是否过期
     * @Date 20:57 2021/11/30
     * @param tokenId
     * @return boolean
     * @throws
     **/
    public boolean verifyToken(String tokenId) {
        AppLoginToken appToken = tokenMap.get(tokenId);
        if(null != appToken){
            if(appToken.getExpireTime() == -1 || (appToken.getCreateTime() + appToken.getExpireTime() >
                    System.currentTimeMillis())){ // -1:永不过期
                return true;
            }
        }
        return false;
    }

    /**
     * @Title refreshToken
     * @Author chunchen2
     * @Description // 刷新Token的缓存
     * @Date 21:13 2021/11/30
     * @param tokenId
     * @return void
     * @throws
     **/
    public void refreshToken(String tokenId) {
        AppLoginToken appToken = tokenMap.get(tokenId);
        appToken.setCreateTime(System.currentTimeMillis());
        tokenMap.put(tokenId, appToken);
    }

    /**
     * @Title remove
     * @Author chunchen2
     * @Description // 移除 Token
     * @Date 21:14 2021/11/30
     * @param tokenId
     * @return void
     * @throws
     **/
    public void remove(String tokenId) {
        tokenMap.remove(tokenId);
    }

    private void clearInValidTokenRecord() {
        Collection<AppLoginToken> appTokenValues = tokenMap.values();
        // 添加判断内存中的数据数量，达到一定阙值才开始清空缓存
        if(appTokenValues.size() > CLEAR_INTERVAL){
            Iterator<AppLoginToken> appTokenIterator = appTokenValues.iterator();
            while(appTokenIterator.hasNext()){
                AppLoginToken token = appTokenIterator.next();
                if((token.getCreateTime() + token.getExpireTime()) < System.currentTimeMillis()){// 已过期
                    appTokenIterator.remove();
                }
            }
        }
    }
}
