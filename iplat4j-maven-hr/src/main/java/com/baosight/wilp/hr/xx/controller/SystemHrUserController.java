package com.baosight.wilp.hr.xx.controller;


import com.baosight.wilp.hr.common.StringUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * SystemController 移动端ajax请求中转Controller
 * 1.配置白名单，EDCC03页面搜索ano
 * 2.本类设置订餐的统一app接口，在请求头规定类名方法名，以此分发请求
 * 
 * @Title: SystemWorkController.java
 * @ClassName: SystemWorkController
 * @Package：com.baosight.wilp.ss.ac.controller
 * @author: liutao
 * @date: 2021年9月9日 下午2:48:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@RestController
public class SystemHrUserController {

    private final static String ADDRESS = "com.baosight.wilp.hr.xx.service.";

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
     * @date: 2021年9月9日 下午2:53:00
     */
    @PostMapping("/hrUser")
    @CrossOrigin
    public Object executeMethod(HttpServletRequest request, HttpServletResponse response) {
        // 分发请求
        String methodName = request.getHeader("methodName");
        String className = request.getHeader("className");
        if(StringUtil.isBlank(className)) {
            //header中没有传递服务名就从param中获取
            className = request.getParameter("className");
            methodName = request.getParameter("methodName");
        }
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
