package com.baosight.wilp.ss.bm.config;

import java.awt.*;

/**
 * Todo(这里用一句话描述这个方法的作用)
 *
 * @Title: FontConfig
 * @ClassName: FontConfig.java
 * @Package：com.baosight.wilp.ss.bm.config
 * @author: xiajunqing
 * @date: 2022/2/21 10:20
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class FontConfig {
    //加载字体-Simhei黑体
	public static Font getSimheiFont(int style,float size) {
		Font simheiFont = null; 
		try {
			//创建字体实体
			simheiFont = Font.createFont(Font.TRUETYPE_FONT, FontConfig.class.getResourceAsStream("simhei.ttf"));
			simheiFont = simheiFont.deriveFont(style,size);
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
        return simheiFont;  
    } 
}
