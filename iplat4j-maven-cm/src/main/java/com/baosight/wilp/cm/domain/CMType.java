package com.baosight.wilp.cm.domain;

// 合同工单号简写
public enum CMType {
	// 合同
	HT("queryHTNo"),
	// 发票
	FP("queryFPNo"),
	// 付款
	FK("queryFKNo"),
	// 节点
	JD("queryJDNo"),
	// 进度
	SC("querySCNo"),
	// 招标
	ZB("queryZBNo");
	// 创建合同类型字段
	private String cmType;

	public String getCmType() {
		return cmType;
	}

	public void setCmType(String cmType) {
		this.cmType = cmType;
	}

	CMType(String cmType) {
		this.cmType = cmType;
	}
}
