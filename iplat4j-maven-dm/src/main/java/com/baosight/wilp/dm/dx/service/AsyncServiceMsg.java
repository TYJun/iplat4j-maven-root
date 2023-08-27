 package com.baosight.wilp.dm.dx.service;

import java.util.List;

import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.wilp.dm.hs.service.ServiceDMHS01;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.common.util.BaseDockingUtils;

/**
 * 异步发送短信
 * 
 * @Title: AsyncServiceMsg.java
 * @ClassName: AsyncServiceMsg
 * @Package：com.baosight.wilp.dm.dx.service
 * @author: fangzekai
 * @date: 2022年1月10日 下午4:18:49
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
 @Component
 public class AsyncServiceMsg {
	private static final Logger logger = LoggerFactory.getLogger(AsyncServiceMsg.class);

	/**
	 *此方法用于发送短信
	 *
	 * @Title: sendMessage
	 * @param: List<String> workNoList, List<String> paramList, String templateCode
	 * @return EiInfo outInfo
	 */
	@Async
	public EiInfo sendMessage(List<String> workNoList, List<String> paramList, String templateCode) {
		EiInfo outInfo = new EiInfo();
		try {
			Boolean flag = BaseDockingUtils.sendMsg(workNoList, paramList, templateCode);
			logger.info("---------------------------");
			logger.info(flag);
			outInfo.setMsg("发送短信成功！！！");
			outInfo.setMsgKey("200");
			logger.info("---------------------------");
		} catch (Exception e) {
			outInfo.setMsg("发送短信失败，请重试！");
			outInfo.setMsgKey("201");
		}
		return outInfo;
	}

}
