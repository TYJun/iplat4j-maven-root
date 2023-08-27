package com.baosight.wilp.dk.jz.domain;

import java.util.List;
import java.util.Map;

/**
 * result数据类
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: ResultData.java
 * @ClassName: ResultData
 * @Package：com.baosight.wilp.dk.jz.domain
 * @author: LENOVO
 * @date: 2021年10月25日 下午2:12:46
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ResultData {
    //返回编码
    private String respCode;
    //返回信息
    private String respMsg;
    //list
    private List<Map<String,Object>> list;
    //返回编码get方法
    public String getRespCode() {
        return respCode;
    }
    //返回编码set方法
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    //返回的信息get方法
    public String getRespMsg() {
        return respMsg;
    }
    //返回信息set方法
    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
    //返回list的get方法
    public List<Map<String, Object>> getList() {
        return list;
    }
    //返回list的set方法
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
	
}
