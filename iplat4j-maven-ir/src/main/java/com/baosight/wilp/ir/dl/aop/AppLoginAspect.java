package com.baosight.wilp.ir.dl.aop;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.ir.dl.service.ServiceIRDL01;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName com.baosight.wilp.sm.dl.aop
 * @ClassName AppLoginAspect
 * @Description TODO
 * @Author chunchen2
 * @Date 2021/11/16 14:43
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/16 14:43
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Aspect
@Component
public class AppLoginAspect {

    String appVerfyCode = PlatApplicationContext.getProperty("iplat.login.app.verifyCode");

    public static Map<String, String> verifyCodeMap = new ConcurrentHashMap<>();

    @Pointcut("execution(public * com.baosight.wilp.common.web.MobileServiceDispatcher.handlePost(..))")
    public void appLoginPointCut(){}

    @Around("appLoginPointCut()")
    public Object appLoginAop(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = null;
        Object[] paramArrays = joinPoint.getArgs();
        List<Object> paramLists = Arrays.asList(paramArrays);

        HttpServletRequest request = (HttpServletRequest) paramLists.get(0);
        String methodName = request.getHeader("methodName");
        String loginName = null;

        if("login".equalsIgnoreCase(methodName)){
            // app 登录
            String params = request.getParameter("prames");
            if(null != params){
                EiInfo eiInfo = EiInfo.parseJSONString(params);
                loginName = eiInfo.getString("userName");

                if(null == loginName || loginName.length() <= 0) return errorRet("登录的用户名不能为空！");

                // 登录前校验该用户的是否因为登录次数超过限制被锁定
                EiInfo inInfo = new EiInfo();
                inInfo.set("loginName", "app_" + loginName.trim());
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
                    return errorRet("该账号已经锁定，请 " +limitMinutes + " 分钟之后再登录");
                }

                // 添加App验证码校验
                if("true".equalsIgnoreCase(appVerfyCode)){
                    boolean verifyCodeFlag = checkVerifyCode(eiInfo);
                    if(!verifyCodeFlag){ // 验证码输入错误，从登录失败的次数 +1
                        EiInfo exInfo = new EiInfo();
                        exInfo.set("loginName", "app_" + loginName.trim());
                        exInfo.set(EiConstant.serviceName, "IRDL01");
                        exInfo.set(EiConstant.methodName, "loginFailureHandle");
                        XLocalManager.call(exInfo);
                        return errorRet("验证码不正确！");
                    }
                }

                try {
                    proceed = joinPoint.proceed(paramArrays);

                    EiInfo retInfo = (EiInfo) proceed;
                    if(retInfo.getStatus() == 1){
                        // 登录成功, 如果存在登录记录, 清除登录用户的登录次数
                        EiInfo delInfo = new EiInfo();
                        delInfo.set("loginName", "app_" + loginName.trim());
                        delInfo.set(EiConstant.serviceName, "IRDL01");
                        delInfo.set(EiConstant.methodName, "clearUserLoginFailureInfo");
                        XLocalManager.call(delInfo);

                        // 添加app登录操作日志
                        try{
                            EiInfo logEiInfo = new EiInfo();
                            logEiInfo.set("model", "DL");
                            logEiInfo.set("sign", "app登录");
                            logEiInfo.set("loginName", loginName.trim());
                            logEiInfo.set(EiConstant.serviceName, "LCPL01");
                            logEiInfo.set(EiConstant.methodName, "insertPerationLog");
                            EiInfo logOutInfo = XLocalManager.call(logEiInfo);
                        } catch (Exception e){
                            // 异常捕获，防止出现不同的frame代码不支持的情况
                        }
                    } else { // 登录失败，直接次数 +1
                        EiInfo exInfo = new EiInfo();
                        exInfo.set("loginName", "app_" + loginName.trim());
                        exInfo.set(EiConstant.serviceName, "IRDL01");
                        exInfo.set(EiConstant.methodName, "loginFailureHandle");
                        XLocalManager.call(exInfo);
                    }
                } catch (Throwable e) {
                    EiInfo exInfo = new EiInfo();
                    exInfo.set("loginName", "app_" + loginName.trim());
                    exInfo.set(EiConstant.serviceName, "IRDL01");
                    exInfo.set(EiConstant.methodName, "loginFailureHandle");
                    XLocalManager.call(exInfo);
                    throw e;
                }
            } else {
                return errorRet("传入参数不能为空！");
            }
        } else {
            proceed = joinPoint.proceed(paramArrays);
        }

        return proceed;
    }

    private boolean checkVerifyCode(EiInfo eiInfo) {
        boolean ret = false;
        String uuid = eiInfo.getString("uuid");
        String verifyCode = eiInfo.getString("verifyCode");

        if(null == uuid || uuid.length() == 0 || null == verifyCode || verifyCode.length() == 0){
            return ret;
        }

        if(verifyCodeMap.containsKey(uuid)){
            String oldVerifyCode = verifyCodeMap.remove(uuid); // 消费完即丢弃
            if(verifyCode.equalsIgnoreCase(oldVerifyCode)){
                ret = true;
            }
        }
        return ret;
    }

    private EiInfo errorRet(String msg){
        EiInfo outEiInfo = new EiInfo();
        outEiInfo.setStatus(-1);
        outEiInfo.setMsg(msg);
        outEiInfo.setAttr(new HashMap(){{ // 设置给前端代码显示
            put("userInfo", null);
        }});

        return outEiInfo;
    }

}
