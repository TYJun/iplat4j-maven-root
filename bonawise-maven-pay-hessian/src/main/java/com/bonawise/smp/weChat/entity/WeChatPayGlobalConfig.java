package com.bonawise.smp.weChat.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.bonawise.smp.weChat.entity.WeChatPayHessianConfig;

/**
 * @desc 微信支付配置文件加载
 * @date 2018年5月29日 09点41分
 * @author yaokang
 *
 */
public class WeChatPayGlobalConfig {
	
	static {
		try {
			InputStream in = WeChatPayGlobalConfig.class.getClassLoader().getResourceAsStream("weChatPayHessianConfig.properties");
			Properties properties = new Properties();
			properties.load(in);
			WeChatPayHessianConfig weChatPay = new WeChatPayHessianConfig(properties);
			WeChatPayGlobalConfig.setWeChatPay(weChatPay);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static WeChatPayHessianConfig weChatPay;

	public static WeChatPayHessianConfig getWeChatPay() {
		return weChatPay;
	}

	public static void setWeChatPay(WeChatPayHessianConfig weChatPay) {
		WeChatPayGlobalConfig.weChatPay = weChatPay;
	}
	
	public WeChatPayGlobalConfig() {
		
	}
}
