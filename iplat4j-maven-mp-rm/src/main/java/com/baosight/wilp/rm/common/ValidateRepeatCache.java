package com.baosight.wilp.rm.common;

import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 重复提交校验缓存
 * @ClassName: ValidateRepeatCache
 * @Package com.baosight.wilp.rm.common
 * @date: 2023年07月19日 11:10
 */
public class ValidateRepeatCache {

    private static LoadingCache<String, String> newCache = null;

    static {
        newCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> removalNotification) {
                        System.out.println("过时删除的钩子触发了... key ===> " + removalNotification.getKey());
                    }
                })
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    // 处理缓存键不存在缓存值时的处理逻辑
                    @Override
                    public String load(String key) throws Exception {
                        return "";
                    }
                });
    }

    /**
     * 校验唯一标识是否存在,不存在则添加缓存
     *
     * @param token token
     * @return boolean
     * @throws
     * @Title: validateAndPut
     **/
    public synchronized static boolean validateAndPut(String token) {
        try {
            String cacheToken = newCache.get(token);
            if (StringUtils.isBlank(cacheToken)) {
                newCache.put(token, token);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
