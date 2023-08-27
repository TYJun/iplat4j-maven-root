package com.baosight.wilp.ss.bm.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;



/**
 * @declaration 订餐全局配置缓存类
 */
public class MealCommonConfig implements Serializable {

	private static final long serialVersionUID = 7733998032337892011L;
	
	//配置文件路径com/baosight/wilp/ss/bm/config/mealGlobalConfig.properties
	//private static final String path = "com" + MealGlobalConfig.class.getResource("mealGlobalConfig.properties").getPath().split("com")[1];
	static {
		try {
			
			//读取配置文件,对路径进行处理
			Properties properties = new Properties();
			ClassLoader loader = MealCommonConfig.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("application.properties");
			properties.load(in);
			//加载到缓存
			SmpConfig config = new SmpConfig(properties);
			MealCommonConfig.setSmpConfig(config);
		} catch (IOException e) {
			System.out.println("装配application.properties配置文件出错！"+e.getMessage());
			e.printStackTrace();

		}
	}
	/**
	 * 系统配置类
	 */
	private static SmpConfig smpConfig;

	/**
	 * 默认构造器
	 */
	public MealCommonConfig() {
		
	}
	
	public static SmpConfig getSmpConfig() {
		return smpConfig;
	}

	public static void setSmpConfig(SmpConfig smpConfig) {
		MealCommonConfig.smpConfig = smpConfig;
	}
}
