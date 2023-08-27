package com.baosight.wilp.it.utils;
/**
 * 第一段
 * 独立任务枚举类.
 * 第二段
 * 构造独立任务有参构造方法，
 * 第三段
 * @author zhaowei
 * @date 2022/8/16 10:54
 * @version V1.0
 */
public enum IndependentTaskEnum {
	FL("","FL","独立任务分类"),
	DJ("","DJ","独立任务登记");
	// 全名
	private final String fullName;
	// 缩略词
	private final String abbreviation;
	// 字段描述
	private final String description;

	IndependentTaskEnum(String fullName,String abbreviation,String description){
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public String getDescription() {
		return description;
	}
}
