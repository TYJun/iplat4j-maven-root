package com.baosight.wilp.im.common.domain;

import java.util.List;
import java.util.Map;

public class ResultData {

    private String respCode;
    private String respMsg;
    private List<Map<String,Object>> list;
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
    public List<Map<String, Object>> getList() {
        return list;
    }
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
	
}
