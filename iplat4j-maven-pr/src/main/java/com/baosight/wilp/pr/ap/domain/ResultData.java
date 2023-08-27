package com.baosight.wilp.pr.ap.domain;

import java.io.Serializable;
import java.util.Map;


/**
 * 
 * app端统一返回格式 -- 暂不使用
 * 
 * @Title: ResultData.java
 * @ClassName: ResultData
 * @Package：com.baosight.wilp.nb.ap.domain
 * @author: zhangjiaqian
 * @date: 2021年2月20日 下午1:53:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ResultData implements Serializable{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 返回编码
     */
    private String respCode;
    
    /**
     * 返回信息
     */
    private String respMsg;
    
    /**
     * 返回参数
     */
    private Object param;
    
    
    public Object getParam() {
        return param;
    }
    public void setParam(Object param) {
        this.param = param;
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
    

}
