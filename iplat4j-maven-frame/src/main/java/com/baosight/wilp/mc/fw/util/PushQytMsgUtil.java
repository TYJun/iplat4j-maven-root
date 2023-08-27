package com.baosight.wilp.mc.fw.util;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;



import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 个推发送工具类
 * 作者：hcd
 * 
 */
@SuppressWarnings("deprecation")
public class PushQytMsgUtil {

	private static final Logger logger = Logger.getLogger(PushQytMsgUtil.class);
	
	/**
	 * 1.先读取配置文件参数，启动时加载 hospital:医院 priority:优先级
	 */
	
	private static String pushQytUrl = PlatApplicationContext.getProperty("pushQytUrl");
	private static String qytEid = PlatApplicationContext.getProperty("qytEid");
	private static String qytUserid = PlatApplicationContext.getProperty("qytUserid");
	private static String qytPassword = PlatApplicationContext.getProperty("qytUserid");


	/**
	 *  作者：hcd
	 * @param msg
	 * 
	 * @param
	 */
	public static String pushMsg(String content, String mobile) {
		
		String result = "";
		
//		Map<String, String> senddata = new HashMap<String, String>();
//		senddata.put("eid", encrypt("qxt", key));//企业账号
//		senddata.put("userid", encrypt("admin", key));//用户名
//		senddata.put("password", encrypt("123456", key));//密码
//		senddata.put("mobile", encrypt("13888888888", key));//发送号码，多个号码用,隔开
//		senddata.put("content", encrypt("JAVA短信发送测试", key));//短信内容
		String eid = qytEid;
		String userid = qytUserid;
		String password = qytPassword;
		
		// 建立HttpPost对象
		HttpPost post = new HttpPost(pushQytUrl);
		
		try {
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			// 添加参数
//			for (String k : data.keySet()) {
//				params.add(new BasicNameValuePair(k, data.get(k)));
//			}
			params.add(new BasicNameValuePair("eid", eid));
			params.add(new BasicNameValuePair("userid", userid));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("mobile", mobile));
			params.add(new BasicNameValuePair("content", content));

			// 设置编码
			HttpEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(formEntity);

			// 发送Post,并返回一个HttpResponse对象
			@SuppressWarnings("resource")
			HttpResponse response = new DefaultHttpClient().execute(post);

			result = EntityUtils.toString(response.getEntity());
			System.out.println("短信发送失败失败!!!"+"错误代码:"+result);
			logger.info("短信发送成功!!!"+"代码:"+result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("短信发送失败!!!"+"错误代码:"+result);
		}
		return result;
	}

	public static void main(String[] args) {
		
		
//		String result = pushMsg("测试1111111", cid, appId, token,"");

		System.out.println("111");
	}





}
