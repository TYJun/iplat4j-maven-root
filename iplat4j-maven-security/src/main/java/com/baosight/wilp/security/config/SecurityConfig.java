package com.baosight.wilp.security.config;

import com.baosight.wilp.security.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.DispatcherType;

/**
 * @PackageName com.baosight.wilp.security.config
 * @ClassName SecurityConfig
 * @Description 安全相关的注册类
 * @Author chunchen2
 * @Date 2023/2/16 17:43
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/16 17:43
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Configuration
public class SecurityConfig {

//    @Bean
//    public LoginDecodeFilter loginDecodeFilter(){
//        return new LoginDecodeFilter();
//    }
//
//    @Bean
//    public RestServiceFilter restServiceFilter(){
//        return new RestServiceFilter();
//    }
//
//    @Bean
//    public WebRequestFilter webRequestFilter(){
//        return new WebRequestFilter();
//    }

//    @Bean
//    public FilterRegistrationBean registrationBonaLoginDecodeFilter(LoginDecodeFilter loginDecodeFilter){
//        FilterRegistrationBean registration = new FilterRegistrationBean(loginDecodeFilter);
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.addUrlPatterns("/login");
//        registration.setOrder(-100);
//        return registration;
//    }

//    @Bean
//    public FilterRegistrationBean registrationBonaRestServiceFilter(RestServiceFilter restServiceFilter){
//        FilterRegistrationBean registration = new FilterRegistrationBean(restServiceFilter);
//        registration.addUrlPatterns("/service/*");
//        registration.setOrder(-210);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean registrationBonaWebRequestFilter(WebRequestFilter webRequestFilter){
//        FilterRegistrationBean registration = new FilterRegistrationBean(webRequestFilter);
//        registration.addUrlPatterns("/web/*");
//        return registration;
//    }


}
