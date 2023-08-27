package com.baosight.wilp.security.password.restrictor;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.security.login.LoginRestrictor;
import com.baosight.iplat4j.core.security.web.PlatformWebAuthenticationDetails;
import com.baosight.wilp.security.password.entity.UserPwdTokenInfo;
import com.baosight.wilp.security.util.AESUtils;
import com.baosight.wilp.security.util.PasswordUtil;
import com.baosight.wilp.utils.ErrorTips;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName com.baosight.wilp.security.login.restrictor
 * @ClassName PCLoginAfterRestrictor
 * @Description PC 登录之后，返回值处理
 *              关键流程类：
 *  *                       SecurityUserStandardImpl
 *  *                       UserManagerRestImpl->checkIdentity
 *  *                       ExceptionMappingAuthenticationFailureHandler
 * @Author chunchen2
 * @Date 2023/2/16 16:55
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/16 16:55
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Component
public class PCLoginAfterRestrictor implements LoginRestrictor {

    String defaultPassword = PlatApplicationContext.getProperty("xservices.security.default.password");

    // 存放登录失败跳转的会话信息
    public static ConcurrentHashMap<String, UserPwdTokenInfo> sessionIdMaps = new ConcurrentHashMap();

    @Override
    public void canLogin(String userName, Object credential, PlatformWebAuthenticationDetails details) throws AuthenticationException {
    }

    @Override
    public void afterLogin(String userName, Object credential, PlatformWebAuthenticationDetails details,
                           Object exception) throws AuthenticationException {
        if(null != exception) { //CredentialsExpiredException、BadCredentialsException、AuthenticationServiceException
            if(exception instanceof CredentialsExpiredException) {
                if(StringUtils.isNotBlank(defaultPassword)) { // 系统配置了默认密码菜进行判断处理
                    String sessionId = AESUtils.getRequest().getSession().getId();
                    UserPwdTokenInfo userPwdTokenInfo = new UserPwdTokenInfo();
                    String creToken = PasswordUtil.encrypt(userName, sessionId, PasswordUtil.getStaticSalt());
                    userPwdTokenInfo.setCreToken(creToken);
                    userPwdTokenInfo.setCreateTime(System.currentTimeMillis());
                    userPwdTokenInfo.setExpireTime(5 * 60 * 1000); // 默认设置5分钟
                    sessionIdMaps.put(sessionId, userPwdTokenInfo);

                    String message = ((CredentialsExpiredException) exception).getMessage();

                    ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) PlatApplicationContext.getApplicationContext();
                    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getBeanFactory();
                    ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler = beanFactory.getBean(ExceptionMappingAuthenticationFailureHandler.class);
                    Map<String, String> failureUrlMap = new HashMap();
                    failureUrlMap.put(CredentialsExpiredException.class.getName(), "/resetPwd.jsp?ac=" + creToken +"&msg=" + UriEncoder.encode(message));
                    exceptionMappingAuthenticationFailureHandler.setExceptionMappings(failureUrlMap);
                    throw new CredentialsExpiredException(message);
                }
            } else if(exception instanceof BadCredentialsException) { // 账号不存在异常
                throw new AuthenticationServiceException(ErrorTips.USERNAME_OR_PWD_ERROR_TIPS);
            }
        }
    }
}
