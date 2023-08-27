package com.baosight.wilp.security.util;

import com.alibaba.fastjson.JSON;
import com.baosight.wilp.security.entity.SecurityResponse;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PackageName com.baosight.wilp.security.util
 * @ClassName ResponseUtils
 * @Description 请求响应的工具类
 * @Author chunchen2
 * @Date 2023/2/21 10:16
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/21 10:16
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ResponseUtils {

    /**
     * @Title handleResponse
     * @Author chunchen2
     * @Description // 错误提示
     * @Date 17:19 2023/2/20
     * @param msg
     * @param response
     * @return void
     * @throws
     **/
    public static void handleResponse(String msg, HttpServletResponse response) throws IOException {
        SecurityResponse securityResponse = SecurityResponse.failure(msg);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(securityResponse));
        out.flush();
        out.close();
    }

    public static void handleResponse(String msg, HttpServletResponse response, String code) throws IOException {
        SecurityResponse securityResponse = SecurityResponse.failure(msg, code);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(securityResponse));
        out.flush();
        out.close();
    }

}
