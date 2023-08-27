package com.baosight.wilp.ac.we.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Todo(这里用一句话描述这个方法的作用)
 * 
 * @Title: RespResult
 * @ClassName: RespResult.java
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
public class RespResult implements Serializable {

	private static final long serialVersionUID = -1242806832220842479L;
	private String respCode;
	private String respMsg;

	public RespResult() {
		this("200", "success");
	}

	public RespResult(String respCode, String respMsg) {
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
