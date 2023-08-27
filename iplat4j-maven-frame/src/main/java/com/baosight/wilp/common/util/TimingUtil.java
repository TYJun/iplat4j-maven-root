package com.baosight.wilp.common.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 定时任务工具类.
 * <p>
 * 日志定时任务.
 * </p>
 *
 * @Title ServiceAURU01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@Configuration
@EnableScheduling
public class TimingUtil {

	// 定时任务
	@SuppressWarnings("unused")
	@Scheduled(cron = "0 0 1 * * ?")
	public void times() {

		EiInfo inInfo = new EiInfo();
		inInfo.set(EiConstant.serviceName, "LCLM01");
		inInfo.set(EiConstant.methodName, "delete");
		EiInfo outInfo = XLocalManager.call(inInfo);

	}

}
