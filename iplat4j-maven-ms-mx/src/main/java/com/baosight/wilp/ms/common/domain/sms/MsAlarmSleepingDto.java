package com.baosight.wilp.ms.common.domain.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

/**
 * 记录点位状态变更以及变更时间的实体
 *
 * @author chenYang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsAlarmSleepingDto {

    /**
     * 点位的tag
     */
    private String tag;

    /**
     * 上一次有效变更时间
     */
    private LocalDateTime dateTime;

    /**
     * 上一次报警值（不包含恢复）
     */
    private MsAlarmTypeEnum alarmTypeEnum;

    /**
     * 定时器
     */
    private ScheduledFuture future;

}
