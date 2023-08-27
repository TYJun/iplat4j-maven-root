package com.baosight.wilp.security.filter;

import com.baosight.wilp.utils.XssUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName com.baosight.wilp.security.filter
 * @ClassName WebRequestFilter
 * @Description 对 /web 的请求进行过滤
 * @Author chunchen2
 * @Date 2023/4/11 10:35
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/4/11 10:35
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class WebRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if(requestURI.contains("/web/EP03")) {
            throw new ServletException("您没有权限访问");
        }

        // 处理一下cookie头信息，防止xss
        CookieRequestWrapper cookieRequestWrapper =
                new CookieRequestWrapper(request);

        filterChain.doFilter(cookieRequestWrapper, response);
    }

    class CookieRequestWrapper extends HttpServletRequestWrapper {

        public CookieRequestWrapper(HttpServletRequest request){
            super(request);
        }

        @Override
        public Cookie[] getCookies() {
            Cookie[] cookies = super.getCookies();
            for(Cookie item : cookies) {
                String value = item.getValue();
                value = XssUtils.handle(value);
                item.setValue(value);
            }

            return super.getCookies();
        }
    }
}


