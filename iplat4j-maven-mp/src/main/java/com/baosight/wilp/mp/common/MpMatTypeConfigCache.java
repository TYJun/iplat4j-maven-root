package com.baosight.wilp.mp.common;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.mp.pz.domain.MpMatTypeConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 专业科室与物资分类关联配置缓存类
 * @ClassName: MpMatTypeConfigCache
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年10月26日 16:24
 *
 * 1。刷新缓存
 * 2.获取所有的配置
 * 3.获取指定配置(科室分类一对一)
 * 4.获取指定科室的配置集合(科室分类一对多)
 * 5.获取指定配置中的物资分类(科室分类一对一)
 * 6.获取指定科室绑定的物资分类编码集合(科室分类一对多)
 * 7.获取指定科室对应的物资分类树
 * 8.清空缓存
 */
public class MpMatTypeConfigCache {

    private static Map<String, List<MpMatTypeConfig>> cache = new HashMap<>();

    /**
     * 刷新缓存
     * @Title: fresh
     * @return void
     * @throws
     **/
    public static void fresh() {
        EiInfo invoke = MpUtils.invoke(new EiInfo(), "MPPZ01", "queryConfigs");
        List<MpMatTypeConfig> configs = MpUtils.toList(invoke.get("configs"), MpMatTypeConfig.class);
        if(CollectionUtils.isNotEmpty(configs)) {
            cache = configs.stream().collect(Collectors.groupingBy(config -> config.getDeptNum()));
        }
    }

    /**
     * 获取所有配置
     * @Title: getAllConfig
     * @return java.util.Map<java.lang.String,com.baosight.wilp.mp.pz.domain.MpMatTypeConfig>
     * @throws
     **/
    public static Map<String, List<MpMatTypeConfig>> getAllConfig() {
        if(cache.isEmpty()) {
            fresh();
        }
        return cache;
    }

    /**
     * 获取指定配置
     * @Title: getConfig
     * @param deptNum deptNum : 科室编码
     * @return com.baosight.wilp.mp.pz.domain.MpMatTypeConfig
     * @throws
     **/
    public static MpMatTypeConfig getConfig(String deptNum) {
        if(cache.isEmpty()) {
            fresh();
        }
        List<MpMatTypeConfig> configs = cache.get(deptNum);
        return CollectionUtils.isEmpty(configs) ? null : configs.get(0);
    }

    /**
     * 获取指定科室绑定的物资分类
     * @Title: getConfigs
     * @param deptNum deptNum : 科室编码
     * @return java.util.List<com.baosight.wilp.mp.pz.domain.MpMatTypeConfig>
     * @throws
     **/
    public static List<MpMatTypeConfig> getConfigs(String deptNum) {
        if(cache.isEmpty()) {
            fresh();
        }
        return cache.get(deptNum);
    }

    /**
     * 获取指定科室关联的物资分类
     * @Title: getMatType
     * @param deptNum deptNum : 科室编码
     * @return java.lang.String
     * @throws
     **/
    public static String getMatType (String deptNum) {
        MpMatTypeConfig config = getConfig(deptNum);
        return config == null ? "" : config.getMatTypeId();
    }

    /**
     * 获取指定科室绑定的物资分编码集合
     * @Title: getMatTypes
     * @param deptNum deptNum : 科室编码
     * @return List<String>
     * @throws
     **/
    public static List<String> getMatTypes (String deptNum) {
        List<MpMatTypeConfig> configs = getConfigs(deptNum);
        return CollectionUtils.isEmpty(configs) ? new ArrayList()
                : configs.stream().map(config -> config.getMatTypeNum()).collect(Collectors.toList());
    }

    /**
     * 构建分类树
     * @Title: typeTree
     * @param deptNum deptNum : 科室编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    public static List<Map<String, String>> typeTree(String deptNum) {
        List<Map<String, String>> list = new ArrayList<>();
        List<MpMatTypeConfig> configs = getConfigs(deptNum);
        for (MpMatTypeConfig config : configs) {
            HashMap<String, String> map = new HashMap<>(4);
            map.put("label",config.getMatTypeId());
            map.put("code",config.getMatTypeNum());
            map.put("text",config.getMatTypeName());
            list.add(map);
        }
        return list;
    }

    /**
     * 清空缓存
     * @Title: clear
     * @return void
     * @throws
     **/
    public static void clear() {
        cache.clear();
    }
}
