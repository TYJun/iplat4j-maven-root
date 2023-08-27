package com.baosight.wilp.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.wilp.utils.ErrorTips;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

 
/**
 * 配置白名单EDCC03 找ano
 * 本类设置维修的统一app接口，在请求头规定类名方法名，以此分发请求
 * @author admin
 *
 */
@RestController
public class MobileServiceDispatcher {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(MobileServiceDispatcher.class);


    @PostMapping("/MobileAgentService")
    @CrossOrigin
    public Object handlePost(HttpServletRequest request, HttpServletResponse response) {

		EiInfo outInfo = new EiInfo();
		EiInfo inInfo = null;

		String serviceName = request.getHeader("serviceName");
		String methodName = request.getHeader("methodName");

		if(org.apache.commons.lang3.StringUtils.isNotEmpty(serviceName) && StringUtils.isNotEmpty(methodName)){
			String requestParams = request.getParameter("prames");

			if(null != requestParams) {
				HashMap<String, Object> requestParamsMap = JSON.parseObject(requestParams, HashMap.class);

				inInfo = new EiInfo();
				requestParamsMap.put(EiConstant.serviceName, serviceName);
				requestParamsMap.put(EiConstant.methodName, methodName);

				inInfo.setAttr(requestParamsMap);
			} else { // 请求参数为空，只有serviceName和methodName
				inInfo = new EiInfo();
				inInfo.set(EiConstant.serviceName, serviceName);
				inInfo.set(EiConstant.methodName, methodName);
			}

			try {
				outInfo = XLocalManager.call(inInfo);
				if(outInfo.getStatus() != 1 && "login".equalsIgnoreCase(methodName)) { // 错误提示统一修改为 用户名或密码错误！
					if(outInfo.getStatus() == -4) {
						outInfo.setMsg(outInfo.getMsg());
					} else {
						outInfo.setMsg(ErrorTips.USERNAME_OR_PWD_ERROR_TIPS);
					}

				}
			} catch (Exception ex) {
				outInfo.setStatus(EiConstant.STATUS_FAILURE);
				// 不对外展示异常信息，防止敏感信息泄露，异常直接输出到log4j的日志中去
				outInfo.setMsg("服务调用异常，具体原因请查看日志信息！");
				LOGGER.error(ex.getMessage(), ex);
			}
		} else {
			outInfo.setStatus(EiConstant.STATUS_FAILURE);
			outInfo.setMsg("传入参数中未指定服务serviceName或者methodName");
		}

		return outInfo;

    }

}
