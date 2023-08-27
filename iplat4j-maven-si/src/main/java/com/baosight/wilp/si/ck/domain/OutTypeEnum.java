package com.baosight.wilp.si.ck.domain;

/**
 * 出库类型对应服务的枚举
 */
public enum OutTypeEnum {

	ZRZC("01", "outStockByZRZC"),//直入直出
	DB("02", "outStockByCommon"),//调拨出库
	PK("03", "outStockByCommon"),//盘亏出库
	FPDC("04", "outStockByFPDC"),//发票调差出库
	HC("05", "outStockByHC"),//红冲出库
	LY("06", "outStockByCommon"),//领用出库
	WX("07", "outStockByCommon"),//维修出库
	WY("08", "outStockByWY"),//委外出库
	BF("09", "outStockByBF");//报废出库
	
	private String code;//code
	private String methodName;//方法名
	
	private OutTypeEnum(String code, String methodName) {
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
		for(OutTypeEnum outType : OutTypeEnum.values()){
			if(outType.getCode().equals(code)){
				return outType.getMethodName();
			}
		}
		return "";
	}
	
}
