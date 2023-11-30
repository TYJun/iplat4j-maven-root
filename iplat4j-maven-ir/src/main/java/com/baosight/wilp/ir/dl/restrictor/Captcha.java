package com.baosight.wilp.ir.dl.restrictor;

import com.baosight.iplat4j.core.FrameworkInfo;
import com.baosight.iplat4j.core.security.login.LoginRestrictor;
import com.baosight.iplat4j.core.security.web.PlatformWebAuthenticationDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @PackageName com.baosight.wilp.ir.dl.restrictor
 * @ClassName CaptchaRestrictor
 * @Description 登录验证码的限制器
 * @Author chunchen2
 * @Date 2021/11/22 10:14
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/22 10:14
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Component
public class Captcha implements LoginRestrictor {

    public void canLogin(String userName, Object credential,PlatformWebAuthenticationDetails details)throws AuthenticationException {
        //
//        if (FrameworkInfo.isCaptchaEnabled() && !StringUtils.isNotEmpty(details.getAuthenticationType())) {
//            //sessionKAPTCHA_SESSION_KEY
//            String captchaExpected = details.getCaptchaExpected();
//            //p_captcha
//            String captchaOffered = details.getCaptcha();
//            if (captchaExpected != null && captchaOffered != null
//                    && captchaExpected.equals(captchaOffered)) {
//                return;
//            }
//            if(StringUtils.isEmpty(captchaOffered)){ // 添加验证码非空提示
//                throw new IllegalArgumentException("验证码不能为空!!!");
//            }
//            throw new IllegalArgumentException("验证码错误!!!");
//        }
    }

    @Override
    public void afterLogin(String userName, Object credential,
                           PlatformWebAuthenticationDetails details, Object exception)
            throws AuthenticationException {
    }
}
