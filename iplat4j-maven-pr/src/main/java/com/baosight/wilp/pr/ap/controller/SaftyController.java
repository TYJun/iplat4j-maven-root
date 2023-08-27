package com.baosight.wilp.pr.ap.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 配置白名单EDCC03 找ano -- 暂不使用
 * 本类设置维修的统一app接口，在请求头规定类名方法名，以此分发请求
 * @author admin
 * 
 * 
 * @Title: SaftyController.java
 * @ClassName: SaftyController
 * @Package：com.baosight.wilp.pr.ap.controller
 * @author: zhangjiaqian
 * @date: 2021年10月25日 上午9:36:39
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@RestController
public class SaftyController {

    private final static String ADDRESS = "com.baosight.wilp.pr.ap.service.";

    @PostMapping("/safty")
    @CrossOrigin
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        // 分发请求
        // 方法名
        String methodName = request.getHeader("methodName");
        // 类名
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
