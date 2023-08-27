package com.baosight.wilp.mc.fw.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;


/**
 * 短信发送工具类
 *  作者：hcd
 */
public class SendingDingMsgUtil {
	//钉钉地址
	private static String pushDingUrl = PlatApplicationContext.getProperty("pushDingUrl");
//	private static String pushDingUrl = "http://192.168.8.224:8190";
//	private static String pushDingUrl = "https://oapi.dingtalk.com";
	//token
	public static final String URL_GET_TOKEN = "/gettoken";
	//电话
	public static final String URL_GET_MOBILE = "/topapi/v2/user/getbymobile";
	//科室列表
	public static final String URL_GET_DEPTLIST = "/department/list";
	//人员列表
	public static final String URL_GET_USERLIST = "/user/simplelist";// /user/simplelist /user/listbypage   user/listbypage
	//人员详情
	public static final String URL_GET_USERINFO = "/user/get";
	
	//钉钉工作消息推送
    public static final String URL_WORKNOTICE_PUSH = "/topapi/message/corpconversation/asyncsend_v2";

	public static void main(String[] args) {
		
		String AgentId = "1303972137";
		String AppKey = "dingpm2s1ldqy8c366zn";
		String AppSecret = "acPI9jHd-1eaII5Oh-lyz32NYyZ6iuA59CWQDynQ6947VXdJoI87HppATsmiD5yo";
//		String msgContent = "测试";
		
		String token = getToken(AppKey,AgentId,AppSecret);
		
		System.out.println("#########################token:"+token );
		
//		String token = "bf96703c77f23a9d9baba014aae77215";
//		
//		List<Map<String, Object>> list = getDeptList("1",token);
//		
//		System.out.println("#########################list:"+list );
		
//		getUserList("95279303",token);
		
//		String mobile = getUserMobile("031917106532504493",token);
//		
//		System.out.println("#########################mobile:"+mobile );
		
		
//		String UserId = getDingUser("","");
		
	}
	
	/**
	 * 封装方法
	 * 获取Token值
	 *  作者：hcd
	 * @param
	 */
	public static String getToken(String AppKey, String AgentId, String AppSecret) {
		
		System.out.println("************************************AppKey:"+AppKey);
		System.out.println("************************************AgentId:"+AgentId);
		System.out.println("************************************AppSecret:"+AppSecret);

		String accessToken = null;
		
		DefaultDingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_GET_TOKEN);
		
		System.out.println("************************************client:"+client);
		
        OapiGettokenRequest request = new OapiGettokenRequest();
        OapiGettokenResponse response = null;

        request.setAppkey(AppKey);
        request.setAppsecret(AppSecret);
        request.setHttpMethod("GET");

        try{
           response = client.execute(request);
        } catch (Exception e){
        	accessToken = "获取token失败,请重新获取!"+e.getMessage();
    	  
        }

        accessToken = response.getAccessToken();
      
        if(null == accessToken){
          accessToken = "获取token失败,请重新获取!";
        }
		
        System.out.println(accessToken);
        return accessToken;
		
	}
	
	/**
	 * 封装方法
	 * 通过电话获取UserId
	 *  作者：hcd
	 * @param
	 */
	public static String getDingUser(String phone, String accessToken) {
		
		DingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_GET_MOBILE);
		OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
		req.setMobile(phone);
		OapiV2UserGetbymobileResponse rsp = null;
		String userId = null;
		try {
			rsp = client.execute(req, accessToken);
			JSONObject resultJson = JSON.parseObject(rsp.getBody());
			String result = resultJson.get("result").toString();
			JSONObject userIdJson = JSON.parseObject(result);
			userId = userIdJson.get("userid").toString();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "获取用户ID失败!"+e.getMessage();
		}
		System.out.println(userId);

        return userId;
		
	}
	
	
	/**
	 * 封装方法
	 * 推送钉钉消息
	 *  作者：hcd
	 * @param
	 */
	public static String sendMsg(String accessToken, String AgentId, String userIds,String msgContent) {
		
		 DingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_WORKNOTICE_PUSH);
         OapiMessageCorpconversationAsyncsendV2Request.Msg msg = sendMsgInfos("text", msgContent);
         OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
         request.setUseridList(userIds);
         request.setAgentId(Long.parseLong(AgentId));
         request.setToAllUser(false);
         request.setMsg(msg);

         OapiMessageCorpconversationAsyncsendV2Response response = null;
        
         try {
             response = client.execute(request, accessToken);
         } catch (Exception e) {
        	 	return "推送消息失败!"+e.getMessage();
         }

       return response.getTaskId().toString();
		
	}
	
	/**
     *  封装要发送的消息
     * @param msgType 消息的类型
     * @param data    不同类型对应的发送数据
     * @return
     */
    private static OapiMessageCorpconversationAsyncsendV2Request.Msg sendMsgInfos(String msgType, String data){
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        switch (msgType){
            case "text":
                msg.setMsgtype("text");
                msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
                msg.getText().setContent(data);
                break;
            case "image":
                msg.setMsgtype("image");
                msg.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
                msg.getImage().setMediaId(data);
                break;
            case "file":
                msg.setMsgtype("file");
                msg.setFile(new OapiMessageCorpconversationAsyncsendV2Request.File());
                msg.getFile().setMediaId(data);
                break;
            default:
                msg.setMsgtype("text");
                msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
                msg.getText().setContent(data);
                break;
        }
        return msg;
    }
    
    /**
	 * 封装方法
	 * 获取钉钉科室
	 *  作者：hcd
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getDeptList(String deptId, String accessToken) {
		
		DingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_GET_DEPTLIST);
		OapiDepartmentListRequest req = new OapiDepartmentListRequest();
		req.setLang("zh_CN");
		req.setFetchChild(true);
		req.setId(deptId);
		req.setHttpMethod("GET");
		OapiDepartmentListResponse rsp = null;
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			rsp = client.execute(req, accessToken);
			JSONObject resultJson = JSON.parseObject(rsp.getBody());
			list = (List<Map<String, Object>>) resultJson.get("department");
			System.out.println(list);
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return "获取部门列表失败!"+e.getMessage();
		}

        return list;
		
	}
	
	/**
	 * 封装方法
	 * 获取钉钉人员
	 *  作者：hcd
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getUserList(String deptId, String accessToken) {
		
		DingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_GET_USERLIST);
//		OapiUserListbypageRequest req = new OapiUserListbypageRequest();
		OapiUserSimplelistRequest req = new OapiUserSimplelistRequest();
		req.setLang("zh_CN");
		req.setDepartmentId(Long.parseLong(deptId));
//		req.setOffset(1L);
//		req.setSize(1L);
		req.setOrder("entry_asc");
		req.setHttpMethod("GET");
		
//		OapiUserListbypageResponse rsp = null;
		OapiUserSimplelistResponse rsp = null;
		
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			rsp = client.execute(req, accessToken);
			System.out.println(rsp.getBody());
			JSONObject resultJson = JSON.parseObject(rsp.getBody());
			list = (List<Map<String, Object>>) resultJson.get("userlist");
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return "获取人员列表失败!"+e.getMessage();
		}
		System.out.println(list);

        return list;
		
	}
	
	/**
	 * 封装方法
	 * 通过UserId获取电话
	 *  作者：hcd
	 * @param
	 */
	public static Map<String, String> getUserMobile(String userId, String accessToken) {
		
		DingTalkClient client = new DefaultDingTalkClient(pushDingUrl + URL_GET_USERINFO);
		OapiUserGetRequest req = new OapiUserGetRequest();
		req.setUserid(userId);
		req.setHttpMethod("GET");
		OapiUserGetResponse rsp = null;
		
		Map<String, String> map = new HashMap<>();
		try {
			
			rsp = client.execute(req, accessToken);
			JSONObject resultJson = JSON.parseObject(rsp.getBody());
			String mobile = resultJson.getString("mobile");
			String jobnumber = resultJson.getString("jobnumber");
			map.put("mobile", mobile);
			map.put("jobnumber", jobnumber);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return "获取人员详情失败!"+e.getMessage();
		}
		System.out.println(map);

        return map;
		
	}

}
