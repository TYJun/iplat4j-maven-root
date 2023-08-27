package com.baosight.wilp.df.common.domain;
/**
 * 
 * 设备档案枚举类
 * 
 * @Title: ParamKey.java
 * @ClassName: ParamKey
 * @Package：com.baosight.wilp.df.common.domain
 * @author: zhaow
 * @date: 2021年8月11日 下午2:40:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public enum ParamKey {
    // 设备档案DF_CLASSFYPARAM缩写DF
	DF_CLASSFYPARAM("DF"); 
    
	// 定义关键字
	private String key;
	
	// 设置关键字
	private ParamKey(String key) {
		this.key = key;
	}
	
	// 获取关键字
	public String getKey() {
		return this.key;
	}
}
