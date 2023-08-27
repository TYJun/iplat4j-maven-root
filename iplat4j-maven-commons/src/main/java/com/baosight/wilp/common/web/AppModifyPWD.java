package com.baosight.wilp.common.web;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.xservices.xs.authentication.SecurityBridgeFactory;
@RestController
public class AppModifyPWD {

    private static Dao dao = (Dao)PlatApplicationContext.getBean("dao");

    @PostMapping("/maintain")
    @CrossOrigin
    public String modifyPWD(HttpServletRequest request, HttpServletResponse response) {

        JSONObject retJson = new JSONObject();

        String loginName=request.getParameter("workNo");
        String oldPassword=request.getParameter("oldPassword");
        String newPassword=request.getParameter("password");Map map =new HashMap();
        map.put("loginName", loginName);
        List resultList = dao.query("XS0104.query", map);

        if(null != resultList && resultList.size() > 0){
            Map result = (Map)resultList.get(0);
            String encodedPassword = result.get("password").toString();

            boolean flag = "".equals(oldPassword) || "".equals(newPassword);
            if (flag) {
                retJson.put("respCode", "-1");
                retJson.put("respMsg", "原密码、新密码和确认密码都必须填写！");
                return retJson.toJSONString();
            }

            String matchedPassword = SecurityBridgeFactory.getSecurityPasswordEncrypt().encode(newPassword);
            if (SecurityBridgeFactory.getSecurityPasswordEncrypt().matches(oldPassword, encodedPassword)) {

                if (newPassword != null && newPassword.equals(oldPassword)) {
                    retJson.put("respCode", "-1");
                    retJson.put("respMsg", "新密码不能与原来的密码相同！");
                    return retJson.toJSONString();
                }

                EiInfo passwordInfo = new EiInfo();
                passwordInfo.set("password", newPassword);
                passwordInfo.set(EiConstant.serviceName, "XS0102");
                passwordInfo.set(EiConstant.methodName, "checkPassword");
                EiInfo checkInfo = XLocalManager.call(passwordInfo);
                if (checkInfo.getStatus() < 0) {
                    retJson.put("respCode", "-1");
                    retJson.put("respMsg", "新密码未通过密码规则校验！" + checkInfo.getMsg());
                }

                map.put("password", matchedPassword);
                map.put("recRevisor", result.get("userName"));
                map.put("recReviseTime", DateUtils.curDateTimeStr14());
                map.put("pwdRevisor", result.get("userName"));
                map.put("pwdReviseDate", DateUtils.curDateTimeStr14());
                map.put("userId", result.get("userId"));
                String pwdExpireDays = PlatApplicationContext.getProperty("xservices.security.pwdExpireDays");
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(DateUtils.toDate8(DateUtils.curDateStr8()));
                gc.add(5, Integer.parseInt(pwdExpireDays));
                map.put("pwdExpireDate", DateUtils.toDateStr8(gc.getTime()));
                dao.update("XS0104.update", map);

                retJson.put("respCode", "200");
                retJson.put("respMsg", "修改成功！");
            } else {
                retJson.put("respCode", "-1");
                retJson.put("respMsg", "原密码不正确！");
            }

        } else {// 用户不存在
            retJson.put("respCode", "-1");
            retJson.put("respMsg", "用户名不存在！");
        }

        return retJson.toJSONString();
    }
}
