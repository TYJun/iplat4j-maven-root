package com.baosight.wilp.security.filter;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.security.base.SecurityFactory;
import com.baosight.wilp.utils.XssUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 增加后台服务过滤
 */
public class RestServiceFilter implements Filter {

    private List<String> authPageCheckLists = "".equalsIgnoreCase(PlatApplicationContext.getProperty("authPageCheckLists")) ?
            null : Arrays.asList(PlatApplicationContext.getProperty("authPageCheckLists").split(","));


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String method = request.getMethod();
            String contentType = request.getHeader("content-type");
            if(null != contentType){
                if ("POST".equalsIgnoreCase(method) && (contentType.contains("application/json") || contentType.contains("text/json"))) {
                    // 判断用户是否有权限访问资源
//                    boolean isAuth = hasPageAuth(request);
//                    if(!isAuth) {
//                        ResponseUtils.handleResponse(SecurityConstants.USER_NO_AUTH_TIPS, response);
//                        return;
//                    }

                    // xss 请求处理
                    ServletRequest requestWrapper = new RestServiceRequestWrapper((HttpServletRequest) servletRequest);
                    filterChain.doFilter(requestWrapper, servletResponse);
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    // 判断当前用户是否有页面service的访问权限
    private boolean hasPageAuth(HttpServletRequest request) {
        boolean canAccess = true;

        // 判断是否需要拦截这个请求，进行越权判断
        if(null != authPageCheckLists) {
            String requestURI = request.getRequestURI();
            boolean isHold = authPageCheckLists.stream().anyMatch(item -> requestURI.contains(item));
            if(isHold) {
                String reg = "\\/service\\/(.*?)\\/";
                String resourceURI = "";

                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(requestURI);

                while(matcher.find()) {
                    resourceURI = matcher.group(1);
                    break;
                }

                // 获取当前登录的用户
                SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().
                        getAttribute("SPRING_SECURITY_CONTEXT");
                Authentication authentication = securityContextImpl.getAuthentication();
                String curUser = String.valueOf(authentication.getPrincipal());

                if("admin".equalsIgnoreCase(curUser)) { // 如果有用户组的概念，可以改成用户组
                    canAccess = true;
                } else {
                    canAccess = SecurityFactory.getAuthorizationManager().hasPermission(curUser, "USER",
                            resourceURI, "PAGE", "ACCESS");
                }
            }
        }

        return canAccess;
    }

    /**
     * 包装request请求，对传入的数据进行脚本过滤
     */
    class RestServiceRequestWrapper extends HttpServletRequestWrapper {
        private byte[] requestBody = null;

        public RestServiceRequestWrapper(HttpServletRequest request) {

            super(request);
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = request.getReader();
                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                br.close();
                String body = sb.toString();
                body = filterString(body);

                requestBody = body.getBytes("UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 覆写原来的获取inputSteam方法，支持过滤
         * @return
         * @throws IOException
         */
        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (requestBody == null) {
                requestBody = new byte[0];
            }
            final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
            return new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return bais.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener listener) {

                }
            };
        }

        /**
         * 覆写原来的getReader方法，支持过滤
         * @return
         * @throws IOException
         */
        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        /**
         * 在此添加过滤逻辑，根据实际情况修改过滤规则
         * 注意:过滤替换条件设置不当会影响正常服务功能
         * @param requestBody
         * @return
         */
        private String filterString (String requestBody){
            return XssUtils.handle(requestBody);
        }

    }

}
