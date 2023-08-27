package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.xservices.xs.util.UserSession;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志Aspect.
 * <p>
 * 日志中心环绕增强型切面.
 * </p>
 *
 * @Title ArchivesLogAspect.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

@Aspect
@Component
public class ArchivesLogAspect {
	
//	@Autowired
//	private HttpServletRequest request;

	@SuppressWarnings({ "unchecked", "unused" })
	@Around("@annotation(archivesLog)")
	public Object around(ProceedingJoinPoint pjd, ArchivesLog archivesLog) throws Throwable {
			
		/********************************************************************/
		
		/* result为连接点的放回结果 */
		Object result = null;

		String errors = null;
		String status = "1";
		String isMonitoring = "1";
		String isParameter = "0";
		
		// 操作模块
		String model = archivesLog.model();
		// 操作标示
		String sign = archivesLog.sign();
		

		// 类名
		String className = pjd.getTarget().getClass().getName();
		// 方法名
		String methodName = pjd.getSignature().getName();
		// 参数
		Object obj[] = pjd.getArgs();
		String prames = ArchivesLogUtil.obj2json(obj);

		/* 前置通知方法 */
		// System.out.println("前置通知方法>目标方法名：" + methodName + ",参数为：" + prames);

		/* 执行目标方法 */
		try {
			
			EiInfo logEiInfo = new EiInfo();
			logEiInfo.set("model", model);
			logEiInfo.set("sign", sign);
			logEiInfo.set(EiConstant.serviceName, "LCPL01");
			logEiInfo.set(EiConstant.methodName, "insertPerationLog");
			EiInfo logOutInfo = XLocalManager.call(logEiInfo);
			
			result = pjd.proceed();

			/* 返回通知方法 */
			 System.out.println("返回通知方法>目标方法名" + methodName + ",返回结果为：" + result);
		} catch (Throwable e) {
			status = "0";
			errors = e + "";
			/* 异常通知方法 */
			// System.out.println("异常通知方法>目标方法名" + methodName + ",异常为：" + e);
			/* 当环绕通知方法本身还有其它异常时，非连接点方法出现的异常，此时抛出来 */
//            throw new RuntimeException();
		} finally {

			EiInfo eiInfo = new EiInfo();

			eiInfo.set("module", model);
			eiInfo.set("class", className);
			eiInfo.set("method", methodName);

//			eiInfo.set(EiConstant.serviceId, "S_LC_FW_01");
//			EiInfo outInfo = XServiceManager.call(eiInfo);
			eiInfo.set(EiConstant.serviceName, "LCFW01");
			eiInfo.set(EiConstant.methodName, "queryMethod");
			EiInfo outInfo = XLocalManager.call(eiInfo);

			List<Map<String, Object>> list = (List<Map<String, Object>>) outInfo.get("result");

			// 如果监测表有该方法就不入库
			if (CollectionUtils.isEmpty(list)) {
//				eiInfo.set(EiConstant.serviceId, "S_LC_FW_02");
//				EiInfo outInfo1 = XServiceManager.call(eiInfo);
				eiInfo.set(EiConstant.serviceName, "LCFW01");
				eiInfo.set(EiConstant.methodName, "insertMethod");
				EiInfo outInfo1 = XLocalManager.call(eiInfo);
			} else {
				isMonitoring = list.get(0).get("isMonitoring").toString();
				isParameter = list.get(0).get("isParameter").toString();
			}

			// 当isMonitoring等于1时，将日志存入数据库
			if ("1".equals(isMonitoring)) {
				if ("1".equals(isParameter)) {
					eiInfo.set("prames", prames);
				}
				eiInfo.set("status", status);
				eiInfo.set("errors", errors);

//				eiInfo.set(EiConstant.serviceId, "S_LC_FW_03");
//				EiInfo outInfo2 = XServiceManager.call(eiInfo);
				eiInfo.set(EiConstant.serviceName, "LCFW01");
				eiInfo.set(EiConstant.methodName, "insertLog");
				EiInfo outInfo3 = XLocalManager.call(eiInfo);
			}

			/* 后置通知 */
			// System.out.println("后置通知方法>目标方法名1111111111111" + model +
			// "====后置通知方法>目标类名22222222222222"+ sign);

		}

		return result;
		
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
}