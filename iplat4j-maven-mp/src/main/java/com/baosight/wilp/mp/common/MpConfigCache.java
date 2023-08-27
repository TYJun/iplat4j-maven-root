package com.baosight.wilp.mp.common;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.pz.domain.MpConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购配置缓存
 * @ClassName: MpConfigCache
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年11月30日 11:44
 *
 * 1.获取所有配置（指定院区）
 * 2.获取指定配置
 * 3.获取指定配置项单选框的值
 * 4.获取配置项输入框的值
 * 5.清空所有配置缓存
 * 6.清除指定院区的配置缓存
 */
public class MpConfigCache {

    /**物资采购配置: 是否开启采购计划OA审批**/
    public static final String MP_CONFIG_OA = "OAApproval";

    /**物资采购配置: 是否开启采购计划OA审批**/
    public static final String MP_CONFIG_DEAN = "deanApproval";

    /**物资采购配置: 固定资产对接分类**/
    public static final String MP_CONFIG_DOCK_FA = "dockFixedAsset";

    private static Map<String, Map<String, MpConfig>> configMap = new HashMap<>(8);

    /**
     * 获取所有配置
     * @Title: getConfigs
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @return java.util.Map<java.lang.String,com.baosight.wilp.mp.pz.domain.MpConfig>
     * @throws
     **/
    public static Map<String, MpConfig> getConfigs(String dataGroupCode) {
        if(configMap.isEmpty()) {
            //调用接口获取所有配置
            EiInfo invoke = MpUtils.invoke("MPPZ02", "query", Arrays.asList("dataGroupCode"), dataGroupCode);
            List<Map> rows = invoke.getBlock(MpConstant.QUERY_BLOCK).getRows();
            //处理返回的配置信息
            if(CollectionUtils.isNotEmpty(rows)){
                Map<String, MpConfig> configs = new HashMap<>(rows.size());
                AtomicReference<String> key = new AtomicReference<>("");
                List<MpConfig> rmConfigs = JSON.parseArray(JSON.toJSONString(rows), MpConfig.class);
                rmConfigs.forEach(config -> {
                    configs.put(config.getConfigCode(), config);
                    key.set(config.getDataGroupCode());
                });
                configMap.put(key.get(), configs);
            }
        }
        return configMap.get(dataGroupCode);
    }

    /**
     * 获取指定的配置
     * @Title: getConfig
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @param configCode configCode: 配置编码
     * @return com.baosight.wilp.mp.pz.domain.MpConfig
     * @throws
     **/
    public static MpConfig getConfig(String dataGroupCode, String configCode) {
        Map<String, MpConfig> configs = getConfigs(dataGroupCode);
        if(configs == null) {
            return null;
        }
        return configs.get(configCode);
    }

    /**
     * 获取指定配置项单选框的值
     * @Title: getConfigRadioValue
     * @return java.lang.String
     * @throws
     **/
    public static String getConfigRadioValue(String dataGroupCode, String configCode) {
        dataGroupCode = MpUtils.toString(dataGroupCode, BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        MpConfig config = getConfig(dataGroupCode, configCode);
        return config == null ? "" : config.getConfigValueRadio();
    }

    /**
     * 获取配置项输入框的值
     * @Title: getConfigTextValue
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @param configCode configCode: 配置编码
     * @return java.lang.String
     * @throws
     **/
    public static String getConfigTextValue(String dataGroupCode, String configCode) {
        dataGroupCode = MpUtils.toString(dataGroupCode, BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        MpConfig config = getConfig(dataGroupCode, configCode);
        return config == null ? "" : config.getConfigValueText();
    }

    /**
     * 清空缓存
     * @Title: clearAll
     * @return void
     * @throws
     **/
    public static void clearAll() {
        configMap.clear();
    }

    /**
     * 清除指定院区的缓存
     * @Title: clear
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @return void
     * @throws
     **/
    public static void clear(String dataGroupCode) {
        configMap.remove(dataGroupCode);
    }
}
