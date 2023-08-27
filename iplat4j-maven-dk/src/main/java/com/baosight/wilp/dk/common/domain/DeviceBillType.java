package com.baosight.wilp.dk.common.domain;

/**
 * 生成编码的业务表
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: DeviceBillType.java
 * @ClassName: DeviceBillType
 * @Package：com.baosight.wilp.dk.common.domain
 * @author: LENOVO
 * @date: 2021年10月25日 上午9:33:24
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public enum DeviceBillType {

	DI_ITEMCLASS("XF"),			//项目分类
	DI_ITEM("XM"),				//巡检项目
	DI_SCHEME("JZ"),			//巡检基准
	DI_TASK("RW"),			//巡检任务
	DK_ITEMCLASS("BY"),			//保养任务分类
	DK_KPINFO("CR"),			//保养卡片
	DK_ITEM("XM"),			//保养任务
	DK_SCHEME("JZ"),			//保养基准
	DK_TASK("RW");			//保养任务
	
	private String key;
	//构造方法
	private DeviceBillType(String key) {
		this.key = key;
	}
	//获取枚举值
	public String getKey() {
		return this.key;
	}
	
}
