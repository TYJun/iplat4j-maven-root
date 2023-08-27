package com.baosight.wilp.security.filter;

import com.baosight.wilp.security.util.AESUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.rp.filter
 * @ClassName LoginDecodeFilter
 * @Description 拦截登录请求，进行参数解密
 * @Author chunchen2
 * @Date 2022/5/5 11:04
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/5/5 11:04
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class LoginDecodeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        LoginDecodeHttpServletRequestWrapper loginDecodeRequest =
                new LoginDecodeHttpServletRequestWrapper(request);

        filterChain.doFilter(loginDecodeRequest, response);
    }

    class LoginDecodeHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private Map<String , String[]> params = new HashMap<String, String[]>();

        public LoginDecodeHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.params.putAll(request.getParameterMap());
        }

        @Override
        public String getParameter(String name) {
            if("p_password".equalsIgnoreCase(name) || "p_username".equalsIgnoreCase(name)) {
                String orignPwd = super.getParameter(name);
                return AESUtils.decodeSessionAESData(orignPwd);
            }

            return super.getParameter(name);
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] retArr = params.get(name);
            if("p_password".equalsIgnoreCase(name) || "p_username".equalsIgnoreCase(name)) {
                String[] newArray = new String[retArr.length];
                for(int i=0; i< retArr.length ; i++) {
                    newArray[i] = AESUtils.decodeSessionAESData(retArr[i]);
                }
                retArr = newArray;
            }
            return retArr;
        }
    }
}
