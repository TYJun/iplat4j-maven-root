package com.baosight.wilp.ss.wx.util;


import java.io.Serializable;

public class Result<T> implements Serializable {
    /**
     * 只有0,1两种取值，1正确，0错误
     */
    private int code;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 报错信息
     */
    private String msg;
    /**
     * 数据详情
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
