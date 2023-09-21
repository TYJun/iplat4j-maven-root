package com.baosight.wilp.ss.wx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.cache.CacheManager;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ss.bm.utils.*;
import com.baosight.wilp.ss.wx.config.WxConstants;
import com.baosight.wilp.ss.wx.domain.SSWxThirdCompany;
import com.baosight.wilp.ss.wx.util.RestUtils;
import com.baosight.wilp.ss.wx.util.YmlValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Desc  提供给第三方授权获取微信用户Openid
 * @author
 */

@Controller
@RequestMapping("/thirdpartieswechat")
@Slf4j
public class DaiapiWeChatOauthController {

    //职工订餐：网页授权方式查看业务方法页面
    @GetMapping("/auth2")
    public String auth()  {
        log.info("进入到微信页面自定义方法地址...");
        //拼接微信网页授权地址
        String redirectURL = String.format(WxConstants.WX_H5_OAUTH2_PRIVATE_URL, WxConstants.WX_APP_ID, URIUtil.encodeURIComponent(WxConstants.H5_REQUEST_URL),
                WxConstants.WX_SNSAPI_BASE, StringUtils.trimToEmpty(WxConstants.WX_STATE),WxConstants.AGENT_ID);
        log.info("【微信网页授权】获取redirectURL,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    // 避免影响其他业务重新开个接口
    @GetMapping("/authVisit")
    public String authVisit(HttpServletRequest request) {
        String type = request.getParameter("type");
        String url = WxConstants.H5_REQUEST_URL;
        if (type != null){
            url = WxConstants.H5_REQUEST_URL + "?type=" + type;
        }
        log.info("进入到微信页面自定义方法地址...");
        //拼接微信网页授权地址
        String redirectURL = String.format(WxConstants.WX_H5_OAUTH2_PRIVATE_URL, WxConstants.WX_APP_ID, URIUtil.encodeURIComponent(url),
                WxConstants.WX_SNSAPI_PRIVATEINFO, StringUtils.trimToEmpty(WxConstants.WX_STATE), WxConstants.AGENT_ID);
        log.info("【微信网页授权】获取redirectURL,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    //回调地址调用
    @RequestMapping("/userinfo2")
    public String userinfo(HttpServletRequest req){
        ResultData resultData = new ResultData();

        log.info("进入到回调地址...");
        String code=req.getParameter("code");
        log.info("【回调地址】获取的code值为{}",code);

        try {
            if(!"authdeny".equals(code)){
                //拼装获取access_token的url请求
                Map cache = CacheManager.getCache("scWorkWxLocalCache");
                //读取缓存
                String cacheToken = (String)cache.get("access_token");
                String access_token = "";
                String dateTimeShort = CalendarUtils.dateTimeShortFormat(new Date());
                System.out.println("cacheToken:"+cacheToken);
                if(StringUtil.isBlank(cacheToken)){
                    //缓存中没有
                    access_token = getAccessToken(YmlValue.corpID);
                    //存放缓存
                    cache.put("access_token",dateTimeShort + "#?#" + access_token);
                }else{
                    //缓存中有，判断调用凭据access_token是否过期
                    String time = cacheToken.split("#?#")[0];
                    boolean compareTime = compareTime(time, dateTimeShort);
                    System.out.println("compareTime:"  + compareTime);
                    if(compareTime == false || "null".equals(cacheToken.split("#?#")[2])){
                        //凭据有效时间2小时，过期要重新获取
                        access_token = getAccessToken(YmlValue.corpID);
                        //存放缓存
                        cache.put("access_token",dateTimeShort + "#?#" + access_token);
                    }else{
                        access_token = cacheToken.split("#?#")[2];
                    }
                    System.out.println("access_token:" + access_token);
                }


                //获取访问用户身份
                String getOauthUrl = WxConstants.WX_GET_USERID_URL.replace("ACCESS_TOKEN", access_token).replace("CODE", code);
                Map response = RestUtils.get(getOauthUrl);
                log.info("获取访问用户身份:" +response);
                if(response.containsKey("errcode") && (Integer) response.get("errcode") != 0){
                    log.error(response.toString());
                    return "redirect:"+WxConstants.REQUEST_URL_LOGIN_ERROR;
                }
                String userId = (String) response.get("UserId");
                String userTicket = (String) response.get("user_ticket");

                //获取OPENID
//                String getSeeOpenUserId = WxConstants.GET_OPEN_USER_ID.replace("ACCESS_TOKEN", access_token);
//                List user_list1 = new ArrayList();
//                user_list1.add(userId);
//                log.info("user_list1:" +user_list1);
//                com.alibaba.fastjson.JSONObject postJsonOpenUserId = new com.alibaba.fastjson.JSONObject();
//                postJsonOpenUserId.put("open_userid_list",user_list1);
//                Map seeOpenUserIdResponse = RestUtils.post(getSeeOpenUserId,postJsonOpenUserId);
//                log.info("获取访问用户id信息返回:{}", seeOpenUserIdResponse.toString());
//                if(seeOpenUserIdResponse.containsKey("errcode") && (Integer) seeOpenUserIdResponse.get("errcode") != 0){
//                    log.error("获取访问用户id信息失败:{}", seeOpenUserIdResponse.toString());
//                }
//                String openIdList = (String) seeOpenUserIdResponse.get("open_userid_list");

                //获取用户的明文的UserId
                String getSeeUserId = WxConstants.GET_USER_ID_TOW;
                List<String> user_list = Arrays.asList(userId);
                log.info("user_list:" +user_list);
                JSONObject postJsonUserId = new JSONObject();
                postJsonUserId.put("open_userid_list",user_list);
                postJsonUserId.put("source_agentid", WxConstants.AGENT_ID);
                log.info("postJsonUserId:" +postJsonUserId);
                //发送请求
                String url = getSeeUserId + "?postData=" + postJsonUserId.toJSONString();
                JSONObject seeUserIdResponse = RestUtils.httpClientGet(url);
                log.info("获取访问用户id信息返回:{}", seeUserIdResponse.toString());
                if(seeUserIdResponse.containsKey("errcode") && seeUserIdResponse.getInteger("errcode") != 0){
                    log.error("获取访问用户id信息失败:{}", seeUserIdResponse.toString());
                    return "redirect:"+WxConstants.REQUEST_URL_LOGIN_ERROR;
                }

                //获取访问用户敏感信息
//                String getUserDetail = WxConstants.GET_USER_DETAIL.replace("ACCESS_TOKEN", access_token);
//                com.alibaba.fastjson.JSONObject postJson = new com.alibaba.fastjson.JSONObject();
//                postJson.put("user_ticket",userTicket);
//                Map userDetailResponse = RestUtils.post(getUserDetail, postJson);
//                log.info("获取访问用户敏感信息返回:{}", response.toString());
//                if(response.containsKey("errcode") && (Integer) response.get("errcode") != 0){
//                    log.error("获取访问用户敏感信息失败:{}", response.toString());
//                    return "redirect:"+WxConstants.REQUEST_URL_LOGIN_ERROR;
//                }
//                String mobile = (String) userDetailResponse.get("mobile");
                String seeUserId = "";
                JSONArray useridList = seeUserIdResponse.getJSONArray("userid_list");
                if(!useridList.isEmpty()) {
                    seeUserId = useridList.getJSONObject(0).getString("userid");
                }

                StringBuffer string = new StringBuffer(WxConstants.REQUEST_URL_LOGIN_SUCCESS);
//                string.append("?userId=").append(seeUserId).append("&mobile=").append(mobile);
                string.append("?userId=").append(seeUserId);
                /**物资采购详情页面跳转参数**/
                String nid = req.getParameter("nid");
                if(StringUtil.isNotBlank(nid)) {
                    string.append("&serial=").append(req.getParameter("serial")).append("&nid=").append(nid);
                }
                // 访客跳转
                if (req.getParameter("type") != null){
                    string.append("&type=").append(req.getParameter("type"));
                }
//                log.info("获取访问用户身份获取的userId:{}", seeUserId);
                log.info("获取访问用户身份获取的seeUserId:{}", seeUserId);
                log.info("获取访问用户身份成功跳转url:{}",string.toString());
                return "redirect:"+string;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:"+WxConstants.REQUEST_URL_LOGIN_ERROR;
        }
        return "sucess";
    }

    //判断两个时间的差值,  起始时间 + 2小时 - 当前时间 > 5秒,不满足是返回false，表示需要重新获取
    public static boolean compareTime(String start , String end) throws Exception {
        Date startDate = CalendarUtils.dateTimeShortFormat.parse(start);
        Date endDate = CalendarUtils.dateTimeShortFormat.parse(end);

        long startTime = startDate.getTime() + 120*60*1000;
        //判断当前时间与需求送餐时间的差值
        long between = (startTime - endDate.getTime())/1000;

        return between > 1;
    }

    /**
     * 获取access_token
     * @Title  getAccessToken
     * @author liu
     * @date 2022-11-01 10:24
     * @param corpId
     * @return java.lang.String
     */
    public String getAccessToken(String corpId){
        log.info("--------获取access_token--------");
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("corpId",corpId);
            EiInfo call = LocalServiceUtil.call("SSWXThirdCompany", "getCompany", map);
            if(call.getStatus() < 0){
                log.error("获取access_token,查询企业信息失败,corpId:{}",corpId);
                return null;
            }
            String result = null;
            SSWxThirdCompany company = (SSWxThirdCompany) call.get("company");

            String agentSecret = company.getAgentSecret();
            log.info("获取access_token参数,agentSecret:{}", agentSecret);
            String  accessTokenUrl =  String.format(WxConstants.GET_ACCESS_TOKEN,WxConstants.WX_APP_ID,agentSecret);
            Map response = RestUtils.get(accessTokenUrl );
            log.info("获取access_token,response:{}",response.toString());
            //获取错误日志
            if(response.containsKey("errcode") && (Integer) response.get("errcode") != 0){
                log.error("获取access_token失败,response:{}",response.toString());
            }else{
                result = (String) response.get("access_token");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
