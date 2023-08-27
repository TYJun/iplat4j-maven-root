package com.baosight.wilp.si.rk.domain;

/**
 * 入库类型对应服务的枚举
 * @author fangjian
 */
public enum EnterTypeEnum {

	/**直入直出**/
	ZRZC("01", "enterStockByZRZC"),

	/**调拨入库*/
	DB("02", "enterStockByDB"),

	/**盘盈入库*/
	PK("03", "enterStockByCommon"),

	/**发票调差入库*/
	FPDC("04", "enterStockByFPDC"),

	/**红冲入库*/
	HC("05", "enterStockByHC"),

	/**采购入库*/
	CG("06", "enterStockByCommon"),

	/**初始化入库*/
	CS("07", "enterStockByCommon"),

	/**手工入库*/
	SG("08", "enterStockByCommon");

	/**
	 * 入库类型编码
	 **/
	private String code;

	/**
	 * 方法名
	 **/
	private String methodName;
	
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
