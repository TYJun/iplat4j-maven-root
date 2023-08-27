package com.baosight.wilp.dm.common.domain;

/**
 * 生成编码的业务表
 * @author fangzekai
 *
 */
public enum DMGeneCodeType {

	DM_ITEMCLASS("PJFL"),	//宿舍满意度评价项目分类
	DM_ITEM("PJXM"); //宿舍满意度评价项目

	private String key;

	private DMGeneCodeType(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
