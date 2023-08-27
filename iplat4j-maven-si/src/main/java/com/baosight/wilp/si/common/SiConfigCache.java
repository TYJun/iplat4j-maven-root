package com.baosight.wilp.si.common;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.si.pz.domain.SiConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 库存配置缓存
 * @ClassName: SiConfigCache
 * @Package com.baosight.wilp.rm.common
 * @date: 2022年08月31日 17:32
 *
 * 1.获取所有配置（指定院区）
 * 2.获取指定配置
 * 3.获取指定配置项单选框的值
 * 4.获取配置项输入框的值
 * 5.清空所有配置缓存
 * 6.清除指定院区的配置缓存
 */
public class SiConfigCache {

    /**物资领用配置: 是否开启入库验收、审核流程**/
    public static final String SI_CONFIG_ENTER_CHECK = "enterCheck";

    /**库存配置: 是否验收后入库**/
    public static final String SI_CONFIG_CHECK_IN_STORAGE = "checkInStorage";

    /**库存配置: 是否开启出库仓库签字**/
    public static final String SI_CONFIG_OUT_STOCK_SIGN = "outStockSign";

    /**库存配置: 是否仓库签字出库**/
    public static final String SI_CONFIG_STORAGE_MANAGER_CHECK = "storageManagerCheck";

    /**配置缓存Map**/
    private static Map<String, Map<String, SiConfig>> configMap = new HashMap<>(8);

    /**
     * 获取所有配置
     * @Title: getConfigs
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @return java.util.Map<java.lang.String,com.baosight.wilp.si.pz.domain.RmConfig>
     * @throws
     **/
    public static Map<String, SiConfig> getConfigs(String dataGroupCode) {
        if(configMap.isEmpty()) {
            //调用接口获取所有配置
            EiInfo invoke = SiUtils.invoke(null, "SIPZ01", "query", new String[] {"dataGroupCode"}, dataGroupCode);
            List<Map> rows = invoke.getBlock(EiConstant.queryBlock).getRows();
            //处理返回的配置信息
            if(CollectionUtils.isNotEmpty(rows)){
                Map<String, SiConfig> configs = new HashMap<>(rows.size());
                AtomicReference<String> key = new AtomicReference<>("");
                List<SiConfig> rmConfigs = JSON.parseArray(JSON.toJSONString(rows), SiConfig.class);
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
     * @return com.baosight.wilp.rm.pz.domain.RmConfig
     * @throws
     **/
    public static SiConfig getConfig(String dataGroupCode, String configCode) {
        Map<String, SiConfig> configs = getConfigs(dataGroupCode);
        if(configs == null) {
            return null;
        }
        return configs.get(configCode);
    }

    /**
     * 获取指定配置项单选框的值
     * @Title: getConfigRadioValue
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @param configCode configCode: 配置编码
     * @return java.lang.String
     * @throws
     **/
    public static String getConfigRadioValue(String dataGroupCode, String configCode) {
        SiConfig config = getConfig(dataGroupCode, configCode);
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
        SiConfig config = getConfig(dataGroupCode, configCode);
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
