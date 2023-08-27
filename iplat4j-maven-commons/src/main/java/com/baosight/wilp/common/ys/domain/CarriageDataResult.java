package com.baosight.wilp.common.ys.domain;
/**
 * 运送数据实体类
 * @author zhaow
 *
 */
public class CarriageDataResult {
	/**
	 * 返回Code
	 */
	private String respCode;
	
	/**
	 * 返回信息
	 */
	private String respMsg;
	
	/**
	 * 返回对象
	 */
	private Object obj;

	/**
	 * 扩展的返回对象
	 */
	private Object obj2;
	
	/**
	 * 返回计数 用于datagrid
	 */
	private Integer total;
	
	public CarriageDataResult() {
		this.respCode = "200";
		this.respMsg = "操作成功!";
	}

	public CarriageDataResult(String respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}

}