package com.baosight.wilp.ss.bm.utils;

import java.io.Serializable;

/**
 * Todo(这里用一句话描述这个方法的作用)
 * 
 * @Title: ResultData
 * @ClassName: ResultData.java
 * @Package：com.baosight.wilp.ss.bm.utils
 * @author: xiajunqing
 * @date: 2021/11/20 13:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
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
