package com.baosight.wilp.rm.common;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.rm.pz.domain.RmConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用配置缓存
 * @ClassName: RmConfigCache
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
public class RmConfigCache {

    /**物资领用配置: 是否开启年度需求申报限制**/
    public static final String RM_CONFIG_YEAR_REQ_LIMIT = "yearRequireLimit";

    /**物资领用配置: 是否开启月度需求申报限制**/
    public static final String RM_CONFIG_MONTH_REQ_LIMIT = "monthRequireLimit";

    /**物资领用配置: 是否开启需求科室审批**/
    public static final String RM_CONFIG_REQ_APPROVAL = "requireApproval";

    /**物资领用配置: 是否开启领用科室审批**/
    public static final String RM_CONFIG_CLAIM_DEPT_APPROVAL = "claimApproval";

    /**物资领用配置: 是否开启领用仓库审批**/
    public static final String RM_CONFIG_CLAIM_STOCK_APPROVAL = "claimStockApproval";

    /**物资领用配置: 是否开启退库审批**/
    public static final String RM_CONFIG_BACK_APPROVAL = "backApproval";

    /**配置缓存Map**/
    private static Map<String, Map<String, RmConfig>> configMap = new HashMap<>(8);

    /**
     * 获取所有配置
     * @Title: getConfigs
     * @param dataGroupCode dataGroupCode: 账套(院区)
     * @return java.util.Map<java.lang.String,com.baosight.wilp.rm.pz.domain.RmConfig>
     * @throws
     **/
    public static Map<String, RmConfig> getConfigs(String dataGroupCode) {
        if(configMap.isEmpty()) {
            //调用接口获取所有配置
            EiInfo invoke = RmUtils.invoke("RMPZ01", "query", Arrays.asList("dataGroupCode"), dataGroupCode);
            List<Map> rows = invoke.getBlock(RmConstant.QUERY_BLOCK).getRows();
            //处理返回的配置信息
            if(CollectionUtils.isNotEmpty(rows)){
                Map<String, RmConfig> configs = new HashMap<>(rows.size());
                AtomicReference<String> key = new AtomicReference<>("");
                List<RmConfig> rmConfigs = JSON.parseArray(JSON.toJSONString(rows), RmConfig.class);
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
    public static RmConfig getConfig(String dataGroupCode, String configCode) {
        Map<String, RmConfig> configs = getConfigs(dataGroupCode);
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
        RmConfig config = getConfig(dataGroupCode, configCode);
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
        RmConfig config = getConfig(dataGroupCode, configCode);
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
