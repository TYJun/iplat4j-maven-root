package com.baosight.wilp.ms.common.domain.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信配置实体
 *
 * @author chenYang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsSmsConfig {

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 配置开关 1开 0关
     */
    private String onOff;

}
