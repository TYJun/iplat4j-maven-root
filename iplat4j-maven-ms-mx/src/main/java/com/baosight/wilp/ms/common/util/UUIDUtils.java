package com.baosight.wilp.ms.common.util;

import java.util.LinkedList;
import java.util.UUID;

/**
 * UUID工具类
 * @author: panlingfeng
 * @createDate: 2021/8/4 5:36 下午
 */
public class UUIDUtils {

    private final static Integer MAX = 500; //默认一次生成多少个

    private final static Integer ACTIVE = 50; //激活生产线程

    private static final LinkedList<String> cacheList = new LinkedList<>();

    /**
     * 获取UUID
     * @author panlingfeng
     * @date 2021/8/4 5:41 下午
     * @params
     * @return
     * 1.判断是否到底了激活标准，到达了则进行线程激活生产
     * 2.判断缓存中是否有UUID，有则获取
     * 3.来不急生成则临时创建
     */
    public synchronized static String getUUID() {
        if(cacheList.size() < ACTIVE) {
            new Thread(() -> {
                while (cacheList.size() < MAX) {
                    cacheList.addFirst(createUUID());
                }
            }).start();
        }
        if(cacheList.size() > 0) {
            return cacheList.pollLast();
        }else {
            return createUUID();
        }
    }

    /**
     * 创建我们需要的格式的UUID
     * @author panlingfeng
     * @date 2021/8/4 5:39 下午
     * @params
     * @return String
     */
    private static String createUUID() {
        return UUID.randomUUID().toString().toLowerCase().replace("-","");
    }

}
