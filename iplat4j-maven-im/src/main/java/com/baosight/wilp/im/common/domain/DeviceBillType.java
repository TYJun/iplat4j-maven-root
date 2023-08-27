package com.baosight.wilp.im.common.domain;

/**
 * 生成编码的业务表
 * @author cy105
 *
 */
public enum DeviceBillType {

	IM_ITEMCLASS("XF"),			//项目分类
	IM_ITEM("XM"),				//巡检项目
	IM_SCHEME("JZ"),			//巡检基准
	IM_TASK("RW");			//巡检任务
/*	DK_ITEMCLASS("BY"),			//保养任务分类
	DK_KPINFO("CR"),			//保养卡片
	DK_ITEM("XM"),			//保养任务
	DK_SCHEME("JZ"),			//保养基准
	DK_TASK("RW");			//保养任务
*/	
	private String key;
	
	private DeviceBillType(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
