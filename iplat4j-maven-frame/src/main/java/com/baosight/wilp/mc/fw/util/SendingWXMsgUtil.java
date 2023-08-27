package com.baosight.wilp.mc.fw.util;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * 短信发送工具类
 *  作者：hcd
 */
public class SendingWXMsgUtil {
		
	private static String pushWXUrl = PlatApplicationContext.getProperty("pushWXUrl");
//	private static final String HOST = "https://qyapi.weixin.qq.com";
	
    public static final String URL_GET_TOKEN = "gettoken";
	
    public static final String URL_SEND_MSG = "message/send";
    
    public static final String URL_GET_DEPT = "department/list";
    
    public static final String URL_GET_USER = "user/simplelist";
    
    private static RestTemplate restTemplate = new RestTemplate();

    public static String sendMsg(String agentId, String accessToken, String type, String userId, String content){
    	
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
        Map<String, Object> params = new HashMap<>();
        params.put("touser", userId);
        params.put("agentid", agentId);

        switch(type){
            case "text":
                params.put("msgtype", "text");
                Map<String, String> textMap = new HashMap<>();
//                textMap.put("content", "我去百度了。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a" +
//                        ">，聪明避开排队。");
                textMap.put("content", content);
                params.put("text", textMap);
                break;
            case "image":
                params.put("msgtype", "image");
                Map<String, String> imageMap = new HashMap<>();
                imageMap.put("media_id", "30fpF4qC9pEkyMaVXfPo5J6Mvf-ef_wuM3bQ91rTF-ml5Tuh-CVqSq1E7sGgfQf12");
                params.put("image", imageMap);
                break;
            case "voice":
                params.put("msgtype", "voice");
                Map<String, String> voiceMap = new HashMap<>();
                voiceMap.put("media_id", "39CIqQRVxKZGluEo9Z_3oPdoQpCK__v9oPw57bVc10l8xfaXQKEKCOnaapd3cDwkK");
                params.put("voice", voiceMap);
                break;
            default:
                break;
        }
        

        HttpEntity<String> request = new HttpEntity<String>(JSON.toJSONString(params), headers);

        String response = restTemplate.postForObject(pushWXUrl + URL_SEND_MSG +"?access_token=" + accessToken, request, String.class);
        
        System.out.println(response);
        return response;
    }
    
    public static String getAccessToken(String corpId,String secret){
    	
    	String accessToken = null;
        JSONObject response = null;
        try{
            response = restTemplate.getForObject(pushWXUrl + URL_GET_TOKEN +"?corpid=" + corpId + "&corpsecret=" + secret,JSONObject.class);
            accessToken = response.getString("access_token");
            if(null == accessToken){
                return "app信息密钥有误，请检查！";
            }
        } catch (Exception e){
            return response.getString("errmsg");
        }
        System.out.println(accessToken);
        return accessToken;
    }
    
    /**
	 * 封装方法
	 * 获取企业微信科室
	 *  作者：hcd
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getDeptList(String deptId, String accessToken) {
		
		List<Map<String, Object>> list = new ArrayList<>();
		JSONObject response = null;
        try{
            response = restTemplate.getForObject(pushWXUrl + URL_GET_DEPT +"?access_token=" + accessToken,JSONObject.class);
            list = (List<Map<String, Object>>) response.get("department");
        } catch (Exception e){
        	response.getString("errmsg");
        }
        System.out.println(list);
        return list;
	}
	
	/**
	 * 封装方法
	 * 获取企业微信人员
	 *  作者：hcd
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getUserList(String deptId, String accessToken) {
		
		List<Map<String, Object>> list = new ArrayList<>();
		JSONObject response = null;
		String fetch_child = "0";
        try{
            response = restTemplate.getForObject(pushWXUrl + URL_GET_USER +"?access_token=" + accessToken+"&department_id=" + deptId+"&fetch_child=" + fetch_child,JSONObject.class);
            list = (List<Map<String, Object>>) response.get("userlist");
        } catch (Exception e){
        	response.getString("errmsg");
        }
		System.out.println(list);
        return list;
	}
	

	public static void main(String[] args) {
		
		String corpId = "wwa499cbcf7df50066";
    	String secret = "o6Dg5OVhqvzaJE6skT5GWkYCBu-cqeJhjuDTsXuxflM";
    	
    	String agentId = "1000002";
    	String type = "text";
    	String content = "dgdlgdflgfdlkgfdlgfdgfdgfdgdf";
		
		String accessToken = "A1Ec0X3n7K-9ATqjHqD5uUVgxwFji4wFuaPaTaZ75qCW-yRcUAgN2hhNCraErhD7nUb06ty6T5V5chS9SGHo9EYmBqRvmu0vPZG0XHJplg7vCCta2xRrQYREw-11erhapLybGMoQuxotyv8W7E7hKcncq8xLE7g0pHCIb4kfjrCzbFiAwCQxD1Co8SBOnC5EELdL-UMHkkz_qRUgbFuMKQ";
		
//		getAccessToken(corpId,secret);
		
		getUserList("1",accessToken);
//		getDeptList(null,accessToken);
//		sendMsg(agentId,accessToken,type,content);


        
	}
	
	

}
