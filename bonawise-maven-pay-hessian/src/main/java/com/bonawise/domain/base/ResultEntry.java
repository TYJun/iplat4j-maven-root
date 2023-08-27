package com.bonawise.domain.base;

import java.io.Serializable;

/**
 * 封装返回结果
 * @author hunter
 *
 * 2018年5月21日
 */
public class ResultEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2061868875665699547L;

	private Integer respCode;
	
	private String respMsg;
	
	private Object data;
	/**
	 * 商户平台的订单号
	 */
	private String out_trade_no;
	
	public ResultEntry(){
		this.respCode = 200;
		this.respMsg = "成功";
	}
	
	

	public ResultEntry(Integer respCode, String respMsg, Object data) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.data = data;
	}



	public ResultEntry(Integer respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public ResultEntry(Object data) {
		super();
		this.data = data;
	}
	

	public ResultEntry(Object data, String out_trade_no) {
		super();
		this.data = data;
		this.out_trade_no = out_trade_no;
	}



	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}



	@Override
	public String toString() {
		return "ResultEntry [respCode=" + respCode + ", respMsg=" + respMsg + ", data=" + data + ", out_trade_no="
				+ out_trade_no + "]";
	}
	
	
}
