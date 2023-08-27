package com.baosight.wilp.dm.common;

import java.io.Serializable;

/**
 * 重写一个ResultData实体类用来提供给接口调用.
 * 
 * @Title: ResultData
 * @ClassName: ResultData.java
 * @Package：com.baosight.wilp.dm.common
 * @author: fangzekai
 * @date: 2021年12月06日 下午6:00:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class DMResultData implements Serializable{
    

    private static final long serialVersionUID = 1L;
    // 相应代码
    private String respCode = "200";
    // 相应信息
    private String respMsg = "";
    // 参数
    private Object param;
    // 对象
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
