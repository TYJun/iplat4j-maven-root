package com.bonawise.smp.base.domain;

import java.io.Serializable;

public class ResultData implements Serializable{
    

    private static final long serialVersionUID = 1L;
    private String respCode = "200";
    private String respMsg = "";
    private Object param;
    private Object obj;
    private boolean success = true;
    private Integer total = 0;
    
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
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ResultData [respCode=" + respCode + ", respMsg=" + respMsg + ", param=" + param + ", obj=" + obj
				+ ", success=" + success + ", total=" + total + "]";
	}
	
}
