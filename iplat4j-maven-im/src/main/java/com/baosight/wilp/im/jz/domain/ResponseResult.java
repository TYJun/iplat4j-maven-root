package com.baosight.wilp.im.jz.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ResponseResult {

	private String respCode;
	
	private String respMsg;
	
	private Object obj;
	
	public ResponseResult() {
		super();
		this.respCode = "200";
		this.respMsg = "操作成功";
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

}
