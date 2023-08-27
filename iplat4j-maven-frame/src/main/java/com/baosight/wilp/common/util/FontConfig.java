package com.baosight.wilp.common.util;

import java.awt.Font;

public class FontConfig {
    //加载字体-Simhei黑体
	public static Font getSimheiFont(int style,float size) {
		Font simheiFont = null; 
		try {  
			simheiFont = Font.createFont(Font.TRUETYPE_FONT, FontConfig.class.getResourceAsStream("simhei.ttf"));
			simheiFont = simheiFont.deriveFont(style,size);
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
        return simheiFont;  
    } 
}
