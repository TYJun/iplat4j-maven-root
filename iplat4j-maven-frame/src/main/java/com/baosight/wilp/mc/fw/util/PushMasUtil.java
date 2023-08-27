package com.baosight.wilp.mc.fw.util;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;

/**
 * 个推发送工具类
 * 作者：hcd
 * 
 */
public class PushMasUtil {

	private static final Logger logger = Logger.getLogger(PushMasUtil.class);

	private static APIClient handler = new APIClient();

	/**
	 * 1.先读取配置文件参数，启动时加载 hospital:医院 priority:优先级
	 */
	//imIP 移动代理服务器的 IP 地址
	private static String imIP = PlatApplicationContext.getProperty("imIP");
	//loginName 接口创建时的接口登录名
	private static String loginName = PlatApplicationContext.getProperty("loginName");
	//loginPWD 接口创建时的接口登录密码
	private static String loginPWD = PlatApplicationContext.getProperty("loginPWD");
	//apiCode 接口编码
	private static String apiCode = PlatApplicationContext.getProperty("apiCode");
	//
	private static String smID = PlatApplicationContext.getProperty("smID");
	//
	private static String dbName = PlatApplicationContext.getProperty("dbName");


	/**
	 *  作者：hcd
	 * @param 
	 *
	 * @param
	 */
	public static String pushMsg(String content, String mobileStr) {

		String[] mobiles = mobileStr.split(",");

		int result = 0;
		System.out.println("*************************"+"imIP:"+imIP);
		System.out.println("*************************"+"loginName:"+loginName);
		System.out.println("*************************"+"loginPWD:"+loginPWD);
		System.out.println("*************************"+"apiCode:"+apiCode);
		System.out.println("*************************"+"dbName:"+dbName);
		int connectRe = handler.init(imIP, loginName, loginPWD, apiCode, dbName);
		System.out.println("*************************"+"connectRe:"+connectRe);
		if(connectRe == APIClient.IMAPI_SUCC) {

			result = handler.sendSM(mobiles, content ,Long.parseLong(smID));

			logger.info("短信发送失败失败!!!"+"错误代码:"+result);
		}else {
			logger.info("移动代理服务器初始化失败!!!"+"错误代码:"+connectRe);
			return "移动代理服务器初始化失败!!!";
		}

		handler.release();

		return result+"";
	}

	public static void main(String[] args) {


//		String result = pushMsg("测试1111111", cid, appId, token,"");

		System.out.println("111");
	}





}
