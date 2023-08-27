package com.baosight.wilp.dl.pe.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.security.password.entity.UserPwdTokenInfo;
import com.baosight.wilp.security.password.restrictor.PCLoginAfterRestrictor;
import com.baosight.wilp.security.util.AESUtils;
import com.baosight.wilp.security.util.PasswordUtil;
import com.baosight.xservices.xs.authentication.SecurityBridgeFactory;
import org.apache.axis.utils.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @PackageName com.baosight.wilp.dl.pe.service
 * @ClassName ServiceDLPE01
 * @Description 密码过期或者密码强度过弱
 * @Author chunchen2
 * @Date 2022/9/1 11:40
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/9/1 11:40
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceDLPE01 extends ServiceEPBase {

    String pwdRegex =
            PlatApplicationContext.getProperty("xservices.security.checkpassword.regex");

    String passwordTip = PlatApplicationContext.getProperty("xservices.security.checkpassword.desc");

    static final int errCode = -1;
    static final int exceptionCode = -2;
    static final int successCode = 1;

    /**
     * @Title updatePwd
     * @Author chunchen2
     * @Description // 修改用户密码
     * @Date 11:43 2022/9/1
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updatePwd(EiInfo info) {

        String creToken = info.getString("creToken");
        String oldPassword = info.getString("oldPassword");
        String newPassword = info.getString("password");
        String rePassword = info.getString("rePassword");

        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(rePassword) ||
                StringUtils.isEmpty(creToken)){
            info.setStatus(errCode);
            info.setMsg("请求参数不能为空！");
            return info;
        }

        // 新旧密码是否一致判断
        if(oldPassword.equalsIgnoreCase(newPassword)) {
            info.setStatus(errCode);
            info.setMsg("新密码不能和原密码一致！");
            return info;
        }

        // 新密码和确认密码是否一致
        if(!newPassword.equalsIgnoreCase(rePassword)) {
            info.setStatus(errCode);
            info.setMsg("新密码和确认密码不一致，请检查！");
            return info;
        }

        // 检查密码是否满足格式要求
        boolean newPwdMatch = Pattern.matches(pwdRegex, newPassword);
        boolean rePwdMatch = Pattern.matches(pwdRegex, rePassword);
        if(!newPwdMatch || !rePwdMatch) {
            info.setStatus(errCode);
            info.setMsg(passwordTip);
            return info;
        }

        // 添加清理map里面的token信息，防止内存溢出
        clearInValidTokenRecord();

        // 获取密文中的信息，安全比对
        String sessionId = AESUtils.getRequest().getSession().getId();
        UserPwdTokenInfo userPwdTokenInfo = PCLoginAfterRestrictor.sessionIdMaps.get(sessionId);
        if(null != userPwdTokenInfo){
            if(verifyToken(userPwdTokenInfo)) { // 没有过期
                String cacheToken = userPwdTokenInfo.getCreToken();
                if(creToken.equalsIgnoreCase(cacheToken)){
                    String loginName = PasswordUtil.decrypt(creToken, sessionId, PasswordUtil.getStaticSalt());
                    // 根据用户名查询用户
                    Map<String, String> queryMap = new HashMap();
                    queryMap.put("loginName", loginName);
                    List resultList = this.dao.query("XS0104.query", queryMap);
                    if (resultList != null && resultList.size() > 0) {
                        Map<String, String> result = (Map)resultList.get(0);

                        String databasePwd = result.get("password").toString(); // 数据库里面的密码

                        // 校验旧密码是否与数据库一致
                        if(!SecurityBridgeFactory.getSecurityPasswordEncrypt().matches(oldPassword, databasePwd)){// 原来的密码输错
                            info.setStatus(errCode);
                            info.setMsg("原密码错误！");
                        } else {
                            // 开始更改密码
                            String encryptNewPwd = SecurityBridgeFactory.getSecurityPasswordEncrypt().encode(newPassword);
                            queryMap.put("password", encryptNewPwd);
                            queryMap.put("recRevisor", loginName);
                            queryMap.put("recReviseTime", DateUtils.curDateTimeStr14());
                            queryMap.put("pwdRevisor", loginName);
                            queryMap.put("pwdReviseDate", DateUtils.curDateTimeStr14());
                            queryMap.put("userId", result.get("userId"));
                            String pwdExpireDays = PlatApplicationContext.getProperty("xservices.security.pwdExpireDays");
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(DateUtils.toDate8(DateUtils.curDateStr8()));
                            gc.add(5, Integer.parseInt(pwdExpireDays));
                            queryMap.put("pwdExpireDate", DateUtils.toDateStr8(gc.getTime()));
                            this.dao.update("XS0104.update", queryMap);
                            info.setStatus(successCode);
                            info.setMsg("更新密码成功！");
                            PCLoginAfterRestrictor.sessionIdMaps.remove(sessionId);
                        }
                    } else { // 工号异常，未查询到记录
                        info.setStatus(errCode);
                        info.setMsg("系统异常，请联系管理员！");
                    }
                } else { //传过来的和缓存中保存的不一致
                    info.setStatus(exceptionCode);
                    info.setMsg("非法来源请求！");
                }
            } else {
                PCLoginAfterRestrictor.sessionIdMaps.remove(sessionId);
                info.setStatus(exceptionCode);
                info.setMsg("会话已过期，请重新登录！");
            }
        } else {
            info.setStatus(exceptionCode);
            info.setMsg("非法请求！");
        }

        return info;
    }

    private boolean verifyToken(UserPwdTokenInfo tokenInfo) {
        if(null != tokenInfo){
            if(tokenInfo.getExpireTime() == errCode || (tokenInfo.getCreateTime() + tokenInfo.getExpireTime() >
                    System.currentTimeMillis())){ // -1:永不过期
                return true;
            }
        }
        return false;
    }

    private void clearInValidTokenRecord() {
        Collection<UserPwdTokenInfo> appTokenValues = PCLoginAfterRestrictor.sessionIdMaps.values();
        // 添加判断内存中的数据数量，达到一定阙值才开始清空缓存
        if(appTokenValues.size() > 1000) {
            synchronized (ServiceDLPE01.class) {
                if(appTokenValues.size() > 1000) {
                    Iterator<UserPwdTokenInfo> appTokenIterator = appTokenValues.iterator();
                    while (appTokenIterator.hasNext()) {
                        UserPwdTokenInfo token = appTokenIterator.next();
                        if ((token.getCreateTime() + token.getExpireTime()) < System.currentTimeMillis()) {// 已过期
                            appTokenIterator.remove();
                        }
                    }
                }
            }
        }
    }

}
