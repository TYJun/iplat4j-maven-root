package com.baosight.wilp.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
    	
    	EiInfo outInfo = null;
        
        String serviceName = request.getHeader("serviceName");
		String methodName = request.getHeader("methodName");
		
		EiInfo inInfo = null;
		
		String prames = "";
		
		if(request.getParameter("prames") != null ) {
			prames = request.getParameter("prames");
			inInfo = EiInfo.parseJSONString(prames);
		}else {
			inInfo = new EiInfo();
		}
        
        if ((StringUtils.isNotEmpty(serviceName)) && (StringUtils.isNotEmpty(methodName))) {
			try {
				
				inInfo.set(EiConstant.serviceName, serviceName);
				inInfo.set(EiConstant.methodName, methodName);
				outInfo = XLocalManager.call(inInfo);
			} catch (PlatException ex) {
				outInfo.setStatus(EiConstant.STATUS_FAILURE);
				outInfo.setMsg("服务" + serviceName + "-" + methodName + "调用失败:" + ex.getMessage() + ",错误原因:"
						+ ex.getCause());
				LOGGER.error(ex.getMessage(), ex);
			}
		}else {
			throw new PlatException("传入参数json中未指定服务号serviceId或服务serviceName、methodName");
		}
       
        return outInfo;

    }

}
