package com.baosight.wilp.ir.dl.filter;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.ir.dl.app.security.InMemoryTokenStore;
import com.baosight.wilp.ir.dl.app.security.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PackageName com.baosight.wilp.ir.dl.filter
 * @ClassName AppAuthenticationTokenFilter
 * @Description App认证过滤器
 * @Author chunchen2
 * @Date 2021/12/1 10:25
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/12/1 10:25
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Component
public class AppAuthenticationTokenFilter extends OncePerRequestFilter {

    private String AppTokenAuthenticationFlag = PlatApplicationContext.
            getProperty("iplat.login.app.token.authentication");

    @Autowired
    private TokenStore tokenStore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if("true".equalsIgnoreCase(AppTokenAuthenticationFlag)){ // app接口需要认证，才可以访问
            String tokenId = request.getHeader("auth");     // auth：前端传过来的tokenId
            String requestSource = request.getHeader("source"); // 请求来源，判断是否是app的请求.source:app/其他
            String methodName = request.getHeader("methodName"); // 前端放在请求头里面的调用方法参数

            if(!"login".equalsIgnoreCase(methodName) && "app".equalsIgnoreCase(requestSource)){
                if(null == tokenId || !InMemoryTokenStore.tokenMap.containsKey(tokenId)){
                    responseReq(response);
                    return;
                } else {
                    if(tokenStore.verifyToken(tokenId)){ // token 有效
                        tokenStore.refreshToken(tokenId);
                    } else { // token 过期
                        tokenStore.remove(tokenId);
                        responseReq(response);
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void responseReq(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("respCode", "401");
        jsonObject.put("respMsg", "App未登录或已过期，请重新登录");

        PrintWriter writer = response.getWriter();
        writer.print(jsonObject);
        writer.flush();
    }
}
