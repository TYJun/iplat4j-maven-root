package com.baosight.wilp.common.ys.domain;

import java.io.Serializable;
import java.util.Map;

public class ResultData implements Serializable{
    

    private static final long serialVersionUID = 1L;
    private String respCode;
    private String respMsg;
    private Map<String,Object> map;
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
    public Map<String, Object> getMap() {
        return map;
    }
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    

}
