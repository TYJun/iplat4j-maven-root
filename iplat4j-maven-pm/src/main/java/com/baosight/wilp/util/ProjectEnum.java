package com.baosight.wilp.util;

public enum ProjectEnum {
	// 项目阶段——stageCode
	stageCode("PMJD",1),
	// 项目类型——typeCode
	typeCode("PMLX",2);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ProjectEnum(String name,int index){
		this.name = name;
		this.index = index;
	}

	// get/set方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
