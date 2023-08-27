package com.baosight.wilp.ss.bm.controller;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ss.bm.utils.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Desc  提供给第三方授权获取微信用户Openid
 * @author
 */

@Controller
@RequestMapping("/wechat")
@Slf4j
public class ApiWeChatOauthController {

    //职工订餐：网页授权方式查看业务方法页面
    @GetMapping("/auth")
    public String auth()  {
        log.info("进入到微信页面自定义方法地址...");
        //拼接微信网页授权地址
        String redirectURL = String.format(WxConstant.WX_CONNECT_OAUTH2_AUTHORIZE_URL, WxConstant.WX_APP_ID, URIUtil.encodeURIComponent(WxConstant.REQUEST_URL),
                WxConstant.WX_SNSAPI_BASE, StringUtils.trimToEmpty(WxConstant.WX_STATE));
        log.info("【微信网页授权】获取redirectURL,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    //回调地址调用
    @RequestMapping("/userinfo")
    public String userinfo(HttpServletRequest req){
        ResultData resultData = new ResultData();
        log.info("进入到回调地址...");
        String code=req.getParameter("code");
        log.info("【回调地址】获取的code值为{}",code);
        try {
            if(!"authdeny".equals(code)){

                //拼装获取access_token的url请求
                String tokenUrl = WxConstant.WX_GET_TOKEN_URL.replace("ID", WxConstant.WX_APP_ID).replace("SECRET", WxConstant.WX_SECRENT);
                log.info("【访问用户身份tokenUrl】,tokenUrl={}",tokenUrl);
                System.out.println("到这一步了吗？？");
                //获取访问用户身份的access_token
                String access_token =  AuthUtil.doGetJson(tokenUrl).get("access_token").toString();
                log.info("【访问用户身份access_token】,access_token={}",access_token);
                System.out.println("是否到这一步了吗？？");
                //拼装获取访问用户身份的userid的url请求
                String useridURL = WxConstant.WX_GET_USERID_URL.replace("ACCESS_TOKEN", access_token).replace("CODE", code);
                //获取访问用户身份的基本信息（如userid）
                JSONObject obj=AuthUtil.doGetJson(useridURL);
                log.info("【访问用户身份基本信息】,obj={}",obj);

                //拼装获取访问用户身份详细信息的url请求
                String userurl = WxConstant.WX_GET_USERINFO_URL.replace("ACCESS_TOKEN", access_token).replace("USERID", obj.getString("UserId"));
                //获取访问用户身份的详细信息
                JSONObject userInfo=AuthUtil.doGetJson(userurl);
                log.info("【访问用户身份详细信息】,userInfo={}",userInfo);

                String userId = (String) userInfo.get("userid");
                String name = (String) userInfo.get("name");
                String mobile = (String) userInfo.get("mobile");
                log.info("【userId】,userId={}",userId);
                log.info("【name】,name={}",name);
                log.info("【mobile】,userInfo={}",mobile);
                if (StringUtils.isEmpty(mobile)){
                    System.out.println("未获取到信息");
                    String urlt = WxConstant.REQUEST_URLt;
                    return "redirect:"+urlt;
                }
                HashMap<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("mobile",mobile);
                paramMap.put("userId",userId);
                try {
                    EiInfo call = LocalServiceUtil.callNoTx("PSSC01", "login", paramMap);
                    if(call.getStatus() < 0){
                        System.out.println("调用PSSC01.login失败！");
                        String urlt = WxConstant.REQUEST_URLt;
                        return "redirect:"+urlt;
                    }else {
                        resultData.setRespCode("200");
                        resultData.setRespMsg("操作成功");
                        String urls = WxConstant.REQUEST_URLs + "?" + "userId=" +userId;
                        System.out.println("__________:"+urls);
                        return "redirect:"+urls;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    resultData.setRespCode("201");
                    resultData.setRespMsg("程序异常");
                    resultData.setSuccess(false);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "走完";
    }

}
