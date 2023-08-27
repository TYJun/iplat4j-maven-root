package com.baosight.wilp.cs.re.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * SystemCleanController 移动端ajax请求中转Controller
 * 1.配置白名单，EDCC03页面搜索ano
 * 2.本类设置保洁的统一app接口，在请求头规定类名方法名，以此分发请求
 * 
 * @Title: SystemCleanController.java
 * @ClassName: SystemCleanController
 * @Package：com.baosight.wilp.cs.re.controller
 * @author: fangzekai
 * @date: 2021年12月06日 下午6:00:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@RestController
public class SystemCleanController {

    // 获取类的路径包名
    private final static String ADDRESS = "com.baosight.wilp.cs.re.service.";

    /**
     * executeMethod 执行ajax方法
     * 入参：methodName 方法名,className 服务名
     * 出参：方法执行结果(ResultData)
     *
     * @Title executeMethod
     * @param request
     * @param response
     * @return: Map
     * @author: fangzekai
     * @date: 2021年12月06日 下午6:00:42
     */
    @PostMapping("/cleanService")
    @CrossOrigin
    public Object executeMethod(HttpServletRequest request, HttpServletResponse response) {
        // 分发请求
        String methodName = request.getHeader("methodName");
        String serviceName = request.getHeader("serviceName");
        try {
            Class<?> cls = Class.forName(ADDRESS + serviceName);
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
