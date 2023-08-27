package com.baosight.wilp.security.password.aop;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.log.xeye.entity.XEyeEntity;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.xservices.xs.authentication.AuthenticationInfo;
import com.baosight.xservices.xs.authentication.SecurityBridgeFactory;
import com.baosight.xservices.xs.util.LoginUserDetails;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @PackageName com.baosight.wilp.security.password.aop
 * @ClassName PasswordAspect
 * @Description 系统用户弱密码相关的aop处理
 * @Author chunchen2
 * @Date 2023/7/22 16:59
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/7/22 16:59
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Aspect
@Component
public class PasswordAspect {

    String pwdRegex =
            PlatApplicationContext.getProperty("xservices.security.checkpassword.regex");

    String defaultPassword = PlatApplicationContext.getProperty("xservices.security.default.password");

    // 用户注册添加用户切面
    @Pointcut("execution(public * com.baosight.xservices.xs.service.ServiceXS0102.insert(..))")
    public void xs0102InsertPointCut(){}

    // 匿名用户注册添加用户切面
    @Pointcut("execution(public * com.baosight.xservices.xs.service.ServiceXS0102.insertAnonymous(..))")
    public void xs0102InsertAnonymousPointCut(){}

    // pc登录校验用户接口aop
    @Pointcut("execution(public * com.baosight.xservices.xs.impl.SecurityUserStandardImpl.validateUser(..))")
    public void validateUserPointCut(){}

    // xs0107 找回密码检测密码强度
    @Pointcut("execution(public * com.baosight.xservices.xs.service.ServiceXS0107.update(..))")
    public void xs0107UpdatePointCut(){}

    // S_XS_21 生成微服务接口监测
    @Pointcut("execution(public * com.baosight.xservices.xs.service.ServiceXSUserManage.insertUser(..))")
    public void xSUserManagePointCut(){}

    // xs0104 个人修改密码
    @Pointcut("execution(public * com.baosight.xservices.xs.service.ServiceXS0104.update(..))")
    public void xs0104UpdatePointCut(){}

    @Around("xSUserManagePointCut()")
    public Object aopXSUserManagePointCut(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        EiInfo inInfo = (EiInfo) args[0];

        if(StringUtils.isNotBlank(defaultPassword)) { // 系统配置了默认密码才进行处理，否则即使调用S_XS_21参数是弱密码也不清楚改成什么
            List<Map<String, Object>> dataList = (List) Optional.ofNullable((List)inInfo.get("list")).orElse(Collections.emptyList());
            if (dataList.size() != 0) {
                for(Map<String, Object> dataItem : dataList){
                    String password = (String) dataItem.get("password");

                    boolean newPwdMatch = Pattern.matches(pwdRegex, password);
                    if(!newPwdMatch) { // 传递的是弱密码
                        // 修改Eiinfo里面的数据为强密码
                        dataItem.put("password", defaultPassword);
                        dataItem.put("rePass", defaultPassword);
                    }
                }
            }
        }

        return joinPoint.proceed(args);
    }

    @Around("xs0102InsertPointCut()")
    public Object aopXS0102InsertPointCut(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        EiInfo inInfo = (EiInfo) args[0];

        EiBlock eiBlock = inInfo.getBlock("inqu_status");
        Map<String, Object> inInfoRowMap = eiBlock.getRow(0);

        String password = (String)inInfoRowMap.get("password");
        String rePass = (String)inInfoRowMap.get("rePass");

        // 检查密码是否满足格式要求
        boolean newPwdMatch = Pattern.matches(pwdRegex, password);
        boolean rePwdMatch = Pattern.matches(pwdRegex, rePass);
        if(!newPwdMatch || !rePwdMatch) {
            inInfo.setStatus(-1);
            inInfo.setMsg("注册失败！新密码不符合密码强度规则！");
            return inInfo;
        }

        return joinPoint.proceed(args);
    }

    @Around("xs0102InsertAnonymousPointCut()")
    public Object aopXS0102InsertAnonymousPointCut(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        EiInfo inInfo = (EiInfo) args[0];

        EiBlock eiBlock = inInfo.getBlock("inqu_status");
        Map<String, Object> inInfoRowMap = eiBlock.getRow(0);

        String password = (String)inInfoRowMap.get("password");
        String rePass = (String)inInfoRowMap.get("rePass");

        // 检查密码是否满足格式要求
        boolean newPwdMatch = Pattern.matches(pwdRegex, password);
        boolean rePwdMatch = Pattern.matches(pwdRegex, rePass);
        if(!newPwdMatch || !rePwdMatch) {
            inInfo.setStatus(-1);
            inInfo.setMsg("注册失败！新密码不符合密码强度规则！");
            return inInfo;
        }

        return joinPoint.proceed(args);
    }

    // 这个视情况使用，如果需要强制修改初始密码，把这个注解直接注释即可。登录跳转修改密码页面
    @AfterReturning(value = "validateUserPointCut()", returning = "retInfo")
    public EiInfo aopValidateUserPointCut(JoinPoint joinPoint, EiInfo retInfo) throws Throwable {
        Object[] args = joinPoint.getArgs();
        EiInfo inInfo = (EiInfo) args[0];

        //允许初始密码登录, 但是不允许初始密码是工号的
        if(retInfo.getStatus() == -4 && "您使用的是初始密码，请先修改密码!".equalsIgnoreCase(retInfo.getMsg().trim())) {

            String loginName = inInfo.get("loginName").toString();
            String password = inInfo.get("password").toString();
            if(loginName.equalsIgnoreCase(password)){ // 用户名是工号的，不允许放行
                retInfo.setMsg("密码不允许是工号，请修改!");
                retInfo.setStatus(-4);
            } else {
                boolean flag = false;
                LoginUserDetails.loginUsers.add(loginName);

                if (!LoginUserDetails.isUserAdmin(loginName)) {
                    flag = AuthenticationInfo.userAuthentication.get(loginName) == null;
                }

                if (flag) {
                    AuthenticationInfo.getUserAuthentication(loginName);
                }

                retInfo.setMsg("恭喜您，登录成功!");
                retInfo.setStatus(1);
            }
        }
        return retInfo;
    }


    @Around("xs0107UpdatePointCut()")
    public Object aopXS0107UpdatePointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        EiInfo inInfo = (EiInfo) args[0];

        // 确认密码不进行判断，看iplat后面的处理逻辑也没用到。
        String password = (String)inInfo.get("password");

        // 检查密码是否满足格式要求
        boolean newPwdMatch = Pattern.matches(pwdRegex, password);
        if(!newPwdMatch) {
            inInfo.setStatus(-1);
            inInfo.setMsg("找回密码失败！新密码不符合密码强度规则！");
            return inInfo;
        }

        return joinPoint.proceed(args);
    }
}
