package com.baosight.wilp.ss.bm.utils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class CardGlobalConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6269141011912998860L;
	
	static{
		try{
			ClassLoader loader = CardGlobalConfig.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("cardConfigUrl.properties");
			Properties props = new Properties();
			props.load(in);
			CardUrlConfig cardUrlConfig = new CardUrlConfig(props);
			CardGlobalConfig.setCardUrlConfig(cardUrlConfig);
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static CardUrlConfig cardUrlConfig;

	public static CardUrlConfig getCardUrlConfig() {
		return cardUrlConfig;
	}

	public static void setCardUrlConfig(CardUrlConfig cardUrlConfig) {
		CardGlobalConfig.cardUrlConfig = cardUrlConfig;
	}
	
}
