package com.bonawise.smp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseResult implements Serializable {

	private static final long serialVersionUID = -1242806832220842479L;
	private String respCode;
	private String respMsg;

	public ResponseResult() {
		this("200", "success");
	}

	public ResponseResult(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return this.respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("respCode", this.respCode);
		map.put("respMsg", this.respMsg);
		return map;
	}

	@Override
	public String toString() {
		return "ResponseResult [respCode=" + respCode + ", respMsg=" + respMsg + "]";
	}

}
