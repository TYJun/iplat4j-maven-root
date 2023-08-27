package com.baosight.wilp.cu.yd.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.utils.AppLoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.cu.yd.service
 * @ClassName ServiceCUYD01
 * @Description 公共 App 接口，
 * @Author chunchen2
 * @Date 2021/9/15 10:56
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/9/15 10:56
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceCUYD01 extends ServiceBase {

    private Logger logger = LoggerFactory.getLogger(ServiceCUYD01.class);

    String appVerfyToken = PlatApplicationContext.getProperty("iplat.login.app.token.authentication");

    /**
     * APP登录接口
     *
     * <p>1.获取参数，调用iplat框架的微服务接口S_XS_08，根据返回判断用户是否登录成功</br>
     *   2.用户登录成功后获取用户信息、用户角色 </br>
     *   3.判断登录是否传过来有效的cid参数</br>
     *   4.cid为空时，不做操作，返回用户信息</br>
     *   5.cid不为空时，调用微服务接口ACFW01.updateCid更新用户的cid
     * </p>
     *
     * @Title: login
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 	userName ： 用户名
     * 	passWord ： 密码
     * 	cid ： app消息推送时使用的手机唯一标识
     * @param:  @return
     * @return: EiInfo
     * 	 userName ： 工号
     * 	 realName 姓名
     * 	 userInfo
     * 		personnelNum ： 工号
     * 		personnelName ： 姓名
     *			contactTel	：	联系电话
     *			deptNum		:	科室编码
     *			deptName	:	科室名称
     * 	 role ： 角色编码字符串
     * 	 roleName ： 角色名称字符串
     * @throws
     */
    /*
     * @Title login
     * @Author chunchen2
     * @Description app登录接口
     * @Date 13:39 2021/9/18
      * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws 
     **/
    public EiInfo login (EiInfo inInfo) {

        EiInfo loginOutEiInfo = null; // 登录结果返回值

        //1.获取参数，
        String userName = inInfo.getString("userName");
        String passWord = inInfo.getString("passWord");
        //String dataGroupCode = inInfo.getString("dataGroupCode");
        String cid=inInfo.getString("cid");

        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){ //用户名密码非空校验
            //throw new PlatException("用户名或密码不能为空!!");
            loginOutEiInfo = new EiInfo();
            loginOutEiInfo.setAttr(new HashMap(){{ // 设置给前端代码显示
                put("userInfo", null);
            }});
            loginOutEiInfo.setStatus(LoginCode.NOTEMPTY.getCode());
            loginOutEiInfo.setMsg(LoginCode.getMsgByCode(LoginCode.NOTEMPTY.getCode()));
        } else {

            //用户登录校验 （调用iplat框架的微服务接口S_XS_08:用户登录验证服务）
            EiInfo loginEiInfo = new EiInfo();
            loginEiInfo.set(EiConstant.serviceId, "S_XS_08");
            loginEiInfo.set("loginName", userName);
            loginEiInfo.set("password", passWord);
            loginOutEiInfo = AppLoginUtils.invoke(loginEiInfo);
            int status = loginOutEiInfo.getStatus();

            if (LoginCode.SUCCESS.getCode() == status) { // 登录成功

                // 调用微服务 S_AU_FW_03 :获取用户所属的账套
                EiInfo orgCodeInEiInfo = new EiInfo("dataGroupCode");
                orgCodeInEiInfo.set(EiConstant.serviceId, "S_AU_FW_03");
                orgCodeInEiInfo.set("workNo", userName);
                EiInfo orgCodeOutEiInfo = AppLoginUtils.invoke(orgCodeInEiInfo);
                String datagroupCode = (String) orgCodeOutEiInfo.get("datagroupCode");

                // 调用微服务 S_AC_FW_02 :通过员工ID或者员工工号查询员工详情
                Map<String, String> userResultInfo = null; // 返回员工信息的map
                EiInfo staffInEiInfo = new EiInfo();
                staffInEiInfo.set("workNo", userName);
                staffInEiInfo.set(EiConstant.serviceId, "S_AC_FW_02");
                EiInfo staffOutEiInfo = AppLoginUtils.invoke(staffInEiInfo);
                if (0 == staffOutEiInfo.getStatus()) { // 获取员工信息成功
                    userResultInfo = (Map<String, String>) staffOutEiInfo.get("result");
                    if (null != userResultInfo && !userResultInfo.isEmpty()) {
                        userResultInfo.put("workNo", userResultInfo.get("workNo"));
                        userResultInfo.put("realName", userResultInfo.get("name"));
                        userResultInfo.put("name", userResultInfo.get("name"));
                        userResultInfo.put("contactTel", userResultInfo.get("contactTel"));
                        userResultInfo.put("deptName", userResultInfo.get("deptName"));
                        userResultInfo.put("deptNum", userResultInfo.get("deptNum"));
                        userResultInfo.put("gender", userResultInfo.get("gender"));
                        userResultInfo.put("dataGroupCode", datagroupCode);
                    }
                }

                loginOutEiInfo.set("userInfo", userResultInfo);

                // 获取员工的角色信息
                String roles = null;
                String roleNames = null;
                Map roleParams = new HashMap();
                String platSchema = PlatApplicationContext.getProperty("platSchema");
                roleParams.put("platSchema", platSchema);
                roleParams.put("loginName", userName);
                List<Map> roleLists = this.dao.query("CUYD01.getUserRole", roleParams);
                if (null != roleLists && roleLists.size() > 0) {
                    Map<String, String> roleMap = roleLists.get(0);
                    roles = roleMap.get("roles");
                    roleNames = roleMap.get("roleNames");
                }

                loginOutEiInfo.set("role", roles);
                loginOutEiInfo.set("roleName", roleNames);


                //判断登录是否传过来有效的cid参数，有的话更新cid
                EiInfo info = null;
                if (StringUtils.isNotEmpty(cid)) {
                    try {
                        //cid不为空时，调用微服务接口ACFW01.updateCid更新用户的cid
                        inInfo.set("cid", cid);
                        inInfo.set("workNo", userName);
                        System.out.println("ACFW01-updateCid参数：" + inInfo.getAttr());
                        inInfo.set(EiConstant.serviceName, "ACFW01");
                        inInfo.set(EiConstant.methodName, "updateCid");
                        info = XLocalManager.call(inInfo);
                    } catch (PlatException ex) {
                        System.out.println("服务ACFW01-updateCid调用失败:" + ex.getMessage() + ",错误原因:" + ex.getCause());
                        info.setStatus(EiConstant.STATUS_FAILURE);
                        logger.error(ex.getMessage(), ex);
                    }
                }

                // 登录成功，更新token信息
                if("true".equalsIgnoreCase(appVerfyToken)){
                    info = new EiInfo();
                    try{
                        info.set("eiInfo", loginOutEiInfo);
                        info.set(EiConstant.serviceName, "IRDL02");
                        info.set(EiConstant.methodName, "createLoginToken");
                        XLocalManager.call(info);
                    } catch(PlatException e) {
                        loginOutEiInfo.setStatus(-1);
                        loginOutEiInfo.setMsg("创建token信息失败" + e.getMessage());
                    }
                }
            } else { //登录失败
                //throw new PlatException(LoginCode.getMsgByCode(status));
                loginOutEiInfo.setStatus(status);

                // 如果 status 是 -4
                if(status == -4) {
                    loginOutEiInfo.setMsg(loginOutEiInfo.getMsg());
                } else {
                    loginOutEiInfo.setMsg(LoginCode.getMsgByCode(status));
                }

            }
        }

        return loginOutEiInfo;
    }

    enum LoginCode {
        SUCCESS(1, "账号密码正确，且账号正常，可以登录"),
        ERROR(-1, "账号或者密码不正确，无法登录"),
        LOCK(-2, "账号被锁定或不可用，无法登录"),
        NOTEXISTS(-3, "该用户不存在，无法登录"),
        EXPIRED(-4, "用户的密码过期，修改密码后才可登录"),
        USEREXPIRE(-5, "用户已过期, 请联系管理员！！"),
        NOTEMPTY(-6, "用户名密码不能为空！！");


        private int code;
        private String msg;

        LoginCode(int code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static String getMsgByCode(int code) {
            for(LoginCode loginCode : LoginCode.values()){
                if(loginCode.getCode() == code){
                    return loginCode.getMsg();
                }
            }
            return "";
        }
    }
}
