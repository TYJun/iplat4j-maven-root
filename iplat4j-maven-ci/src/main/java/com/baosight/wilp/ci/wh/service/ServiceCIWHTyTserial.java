package com.baosight.wilp.ci.wh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.wh.domain.CIWHTserial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * 
 * 通用序列号service
 * 
 * @Title: ServiceCIWHTyTserial.java
 * @ClassName: ServiceCIWHTyTserial
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:29:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCIWHTyTserial extends ServiceBase{
	


    /**
     * 
     * getSerialIndex:获取序列号
     * serialNum:序列号编码
     * pix:前缀
     * leng:序列号长度
     *
     * @Title: getSerialIndex 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午4:29:46
     */
	public EiInfo getSerialIndex(EiInfo inInfo) {
		HashMap<String,Object> paramObject = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String serialNum = paramObject.get("serialNum").toString();
		String pix = paramObject.get("pix").toString();
		int leng = Integer.parseInt(paramObject.get("leng").toString());
		String str = getSerial(serialNum).toString();
		int length = str.length();
		for (int i = length; i < leng; i++) {
			str = "0" + str;
		}
		String value = pix + str;
		inInfo.set("serialIndex", value);
		return inInfo;
	}
	
	private Integer getSerial(String serialNum) {
		int serial = 0;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(d);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("serialNum", serialNum);
		List<CIWHTserial> serials = dao.query("CIWHTserial.query", paramMap);
		EiInfo serialsInfo = new EiInfo();
		serialsInfo.set("serviceName", "CIWHTserial");
		List<CIWHTserial> list = new ArrayList<CIWHTserial>();
		if (serials.size() > 0) {
			CIWHTserial entity = serials.get(0);
			Integer index = entity.getSerialIndex();
			serial = index + 1;
			entity.setSerialIndex(serial);
			entity.setRecReviseTime(time);
			//更新数据
			list.add(entity);
			serialsInfo.addBlock("result").addRows(list);
			this.update(serialsInfo,"CIWHTserial.update");
		} else {
			CIWHTserial entity = new CIWHTserial();
			entity.setId(UUID.randomUUID().toString());
			entity.setSerialNum(serialNum);
			entity.setRecReviseTime(time);
			entity.setSerialIndex(1);
			//保存数据
			list.add(entity);
			serialsInfo.addBlock("result").addRows(list);
			this.insert(serialsInfo,"CIWHTserial.insert");
			serial = 1;
		}
		return serial;
	}

}
