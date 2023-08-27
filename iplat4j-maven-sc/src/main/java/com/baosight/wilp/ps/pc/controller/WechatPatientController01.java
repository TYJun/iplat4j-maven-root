package com.baosight.wilp.ps.pc.controller;

import com.alibaba.fastjson.JSONObject;
import com.baosight.wilp.ps.pc.utils.GetPostUntil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 */
@RestController
public class WechatPatientController01 {

    /**
     * 获取openid
     * @param
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("/getopenid")
    @ResponseBody
    public String getopenid(String code,HttpServletRequest request, HttpServletResponse response){
        // 梅州的微信配置地址
        String appid= "wxaed039cd4bc423fe";
        String secret = "672a027efc2ecab175d525e44547c284";
        // 本地的微信配置地址
//        String appid= "wx2d29f1e07fe5ec50";
//        String secret = "68679f107ad2a5f940317968d2c60c1d";

        response.setHeader("Access-Control-Allow-Origin", "*");
        /*星号表示所有的域都可以接受，*/
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
//        String wxLoginUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String wxLoginUrl = "http://172.16.200.20:8017/sns/oauth2/access_token";
        String param = "appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        String jsonString = GetPostUntil.sendGet(wxLoginUrl, param);
        JSONObject json = JSONObject.parseObject(jsonString);
        String openid = json.getString("openid");
        System.out.println("###############"+openid);
        return openid;
    }


}
