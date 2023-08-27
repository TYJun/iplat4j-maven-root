package com.baosight.wilp.ci.rk.domain;

/**
 * 入库类型对应服务的枚举
 */
public enum EnterTypeEnum {
	//直入直出
	ZRZC("01", "enterStockByZRZC"),
	//调拨入库
	DB("02", "enterStockByDB"),
	//盘盈入库
	PK("03", "enterStockByCommon"),
	//发票调差入库
	FPDC("04", "enterStockByFPDC"),
	//红冲入库
	HC("05", "enterStockByHC"),
	//采购入库
	CG("06", "enterStockByCommon"),
	//初始化入库
	CS("07", "enterStockByCS");
	
	private String code;//code
	private String methodName;//方法名
	
	private EnterTypeEnum(String code, String methodName) {
		this.code = code;
		this.methodName = methodName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public static String getMethodNameByCode(String code) {
		for(EnterTypeEnum enterType : EnterTypeEnum.values()){
			if(enterType.getCode().equals(code)){
				return enterType.getMethodName();
			}
		}
		return "";
	}
	
}
