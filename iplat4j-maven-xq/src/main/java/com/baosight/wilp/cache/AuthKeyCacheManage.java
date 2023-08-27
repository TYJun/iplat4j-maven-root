package com.baosight.wilp.cache;

import com.baosight.wilp.entity.AuthKeyEitity;

import java.util.*;

/**
 * @PackageName com.baosight.wilp.cache
 * @ClassName CacheManage
 * @Description 用来缓存临时授权Key
 * @Author chunchen2
 * @Date 2023/2/23 16:38
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/23 16:38
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class AuthKeyCacheManage {

    // 先用synchronized 保证安全性实现基础测试， 后续考虑用rentrantLock的读写锁
    // private 保证外部不随意修改信息
    private static Map<String, List<AuthKeyEitity>> auth = new HashMap<>();

    public static synchronized void put(String userId, AuthKeyEitity authKeyEitity) {
        if(auth.containsKey(userId)) {
            // 首先去除已经过期的
            List<AuthKeyEitity> hisAuthLists = auth.get(userId);
            Iterator<AuthKeyEitity> it = hisAuthLists.iterator();
            while(it.hasNext()) {
                AuthKeyEitity next = it.next();
                if(next.isExpire()) { // 授权密钥已过期
                    it.remove();
                }
            }
            // 添加新的,这里就不去重了，没必要
            hisAuthLists.add(authKeyEitity);
        } else {
            ArrayList<AuthKeyEitity> authLists = new ArrayList<>();
            authLists.add(authKeyEitity);
            auth.put(userId, authLists);
        }
    }

    /**
     * @Title get
     * @Author chunchen2
     * @Description // 根据用户的id, 获取用户授权的临时密钥列表
     * @Date 16:30 2023/2/24
     * @param userId         用户id
     * @return java.util.List<com.baosight.wilp.entity.AuthKeyEitity>
     * @throws
     **/
    public static synchronized List<AuthKeyEitity> get(String userId) {
        if(auth.containsKey(userId)) {
            // 首先去除已经过期的
            List<AuthKeyEitity> hisAuthLists = auth.get(userId);
            Iterator<AuthKeyEitity> it = hisAuthLists.iterator();
            while(it.hasNext()) {
                AuthKeyEitity next = it.next();
                if(next.isExpire()) {
                    it.remove();
                }
            }
            return hisAuthLists;
        }

        return null;
    }

    /**
     * @Title remove
     * @Author chunchen2
     * @Description // 用户调用取消用户所有接口，直接去除掉已经取消授权的信息
     * @Date 14:57 2023/3/7
     * @param userId
     * @return boolean
     * @throws
     **/
    public static synchronized void remove(String userId) {
        if(auth.containsKey(userId)) {
            auth.remove(userId);
        }
    }

}
