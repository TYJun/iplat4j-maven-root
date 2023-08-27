package com.baosight.wilp.sq.common;

public enum RecevierEnum {

	/**
	 * 查询问卷负责人
	 */
	SQFZ("queryReceiverForWorkNoLead"),
	/**
	 * 查询【全体】答卷人
	 */
	SQRY_ALL("queryReceiverForAll"),
	/**
	 * 查询【未答题】答卷人
	 */
	SQRY_PART("queryReceiverForRemainder");

	private String statement;

	RecevierEnum(String statement){
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}
}
