package com.baosight.wilp.ir.dl.aop;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.security.UnknownAuthenticationException;
import com.baosight.iplat4j.core.security.login.LoginRestrictor;
import com.baosight.iplat4j.core.security.web.PlatformWebAuthenticationDetails;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.ir.dl.service.ServiceIRDL01;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackageName com.baosight.iplat4j.core.security.login
 * @ClassName LoginCountRestrictor
 * @Description pc登录失败次数限制
 * @Author chunchen2
 * @Date 2021/11/11 17:17
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/11 17:17
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Aspect
@Component
public class LoginCountRestrictor implements LoginRestrictor{

    @Pointcut("execution(public * com.baosight.iplat4j.core.security.authentication.PlatformAuthenticationProvider.authenticate(..))")
    public void loginPointCut(){}

    @Around("loginPointCut()")
    public Object loginCountAop(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] object = joinPoint.getArgs();
        UsernamePasswordAuthenticationToken userToken  = (UsernamePasswordAuthenticationToken) object[0];
        String loginName = String.valueOf(userToken.getPrincipal());
        String loginPwd = String.valueOf(userToken.getCredentials());

        if(StringUtils.isEmpty(loginName)|| StringUtils.isEmpty(loginPwd)){
            throw new UnknownAuthenticationException("用户名密码不能为空!");
        }

        // 登录前校验该用户的是否因为登录次数超过限制被锁定
        EiInfo inInfo = new EiInfo();
        inInfo.set("loginName", loginName);
        inInfo.set(EiConstant.serviceName, "IRDL01");
        inInfo.set(EiConstant.methodName, "queryLoginFailureInfo");
        EiInfo outInfo = XLocalManager.call(inInfo);

        int loginFailureCount = outInfo.getInt("loginFailureCount");
        String failureCreateTime = outInfo.getString("failureCreateTime");
        long currentTime = new Date().getTime();
        long failureTime = 0;
        int limitCount = ServiceIRDL01.limitCount;
        int limitMinutes = ServiceIRDL01.limitMinutes;
        try {
            if(null != failureCreateTime){
                failureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(failureCreateTime).getTime();
            }
        } catch (ParseException e) {
        }

        // 登录之前的预处理
        if(loginFailureCount >= limitCount && (currentTime - failureTime) < 60 * 1000 * limitMinutes) {
            throw new UnknownAuthenticationException("该账号已经锁定，请 " +limitMinutes + " 分钟之后再登录");
        }

        Object proceed = null;
        try {
            proceed = joinPoint.proceed(object);
        } catch (Throwable e) {
            EiInfo exInfo = new EiInfo();
            exInfo.set("loginName", loginName);
            exInfo.set(EiConstant.serviceName, "IRDL01");
            exInfo.set(EiConstant.methodName, "loginFailureHandle");
            XLocalManager.call(exInfo);
            if(loginFailureCount == limitCount - 1) {
                throw new UnknownAuthenticationException("该账号已经锁定，请 " +limitMinutes + " 分钟之后再登录");
            } else {
                throw e;
            }
        }
        return proceed;
    }


    public void canLogin(String userName, Object credential, PlatformWebAuthenticationDetails details) throws AuthenticationException {

    }


    /**
     * @Title afterLogin
     * @Author chunchen2
     * @Description // 登录成功后的处理
     * @Date 10:29 2021/11/22
     * @param userName
     * @param credential
     * @param details
     * @param exception
     * @return void
     * @throws
     **/
    public void afterLogin(String userName, Object credential, PlatformWebAuthenticationDetails details,
                           Object exception) throws AuthenticationException {
        if(null == exception){
            // 登录成功, 如果存在登录记录, 清除登录用户的登录次数
            EiInfo delInfo = new EiInfo();
            delInfo.set("loginName", userName);
            delInfo.set(EiConstant.serviceName, "IRDL01");
            delInfo.set(EiConstant.methodName, "clearUserLoginFailureInfo");
            XLocalManager.call(delInfo);


            // 添加登录操作日志
            try{
                EiInfo logEiInfo = new EiInfo();
                logEiInfo.set("model", "DL");
                logEiInfo.set("sign", "登录平台");
                logEiInfo.set("loginName", userName);
                logEiInfo.set(EiConstant.serviceName, "LCPL01");
                logEiInfo.set(EiConstant.methodName, "insertPerationLog");
                EiInfo logOutInfo = XLocalManager.call(logEiInfo);
            } catch (Exception e){
                // 异常捕获，防止出现不同的frame代码不支持的情况
            }
        }
    }
}
