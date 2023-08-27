package com.baosight.wilp.fa.zn.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月27日 11:07
 */
public class ServiceFAZN0101 extends ServiceBase {
	public EiInfo initLoad(EiInfo info){
		return info;
	}

	public EiInfo saveDeptInfo(EiInfo info){
		Map map = info.getRow("info", 0);
		map.put("deptName",map.get("deptNum_textField"));
		dao.insert("FAZN01.saveDeptInfo",map);
		return info;
	}
}
