package com.baosight.wilp.mc.controller;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.mc.jk.domain.Personnel;
import com.baosight.wilp.mc.jk.service.ServiceMCJK01;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.*;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baosight.iplat4j.core.ei.EiInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: WorkOrderController.java
 * @ClassName: WorkOrderController
 * @Package：com.baosight.wilp.pj.jk.controller
 * @author: zhaow
 * @date: 2021年12月23日 下午8:37:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@RestController
public class PersonnelController {
	private final static String ADDRESS = "com.baosight.wilp.mc.jk.service.";

	/**
	 *
	 * executeMethod 执行ajax方法
	 * *入参：methodName 方法名,className 服务名
	 * *出餐：方法执行结果(ResultData)
	 *
	 * @Title: executeMethod
	 * @param request
	 * @param response
	 * @return
	 * @return: Object
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:26:57
	 */
	@PostMapping("/workPersonnel")
	@CrossOrigin
	public Object executeMethod(HttpServletRequest request, HttpServletResponse response) {
		// 分发请求
		String methodName = request.getHeader("methodName");
		String className = request.getHeader("className");
		try {
			Class<?> cls = Class.forName(ADDRESS + className);
			Object obj = cls.newInstance();
			// 获取方法
			Method m = cls.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			return m.invoke(obj, request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("respCode", "199");
		resultMap.put("respMsg", "系统错误");
		return resultMap;
	}
}
