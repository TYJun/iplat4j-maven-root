package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMTserial;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 
 * 通用序列号service
 * 
 * @Title: ServiceSSBMTyTserial.java
 * @ClassName: ServiceSSBMTyTserial
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
public class ServiceSSBMTyTserial extends ServiceBase{
	


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
		Integer leng = Integer.parseInt(paramObject.get("leng").toString());
		String str = getSerial(serialNum).toString();
		int length = str.length();
		for (int i = length; i < leng; i++) {
			str = "0" + str;
		}
		String value = String.valueOf(pix) + str;
		inInfo.set("serialIndex", value);
		return inInfo;
	}
	
	private Integer getSerial(String serialNum) {
		Integer serial = new Integer(0);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(d);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("serialNum", serialNum);
		List<SSBMTserial> serials = this.query("SSBMTserial.query", paramMap);
		EiInfo serialsInfo = new EiInfo();
		serialsInfo.set("serviceName", "SSBMTserial");
		List<SSBMTserial> list = new ArrayList<SSBMTserial>();
		if (serials.size() > 0) {
			SSBMTserial entity = (SSBMTserial) serials.get(0);
			Integer index = entity.getSerialIndex();
			serial = Integer.valueOf(index.intValue() + 1);
			entity.setSerialIndex(serial);
			entity.setRecReviseTime(time);
			//更新数据
			list.add(entity);
			serialsInfo.addBlock("result").addRows(list);
			this.update(serialsInfo,"SSBMTserial.update");
		} else {
			SSBMTserial entity = new SSBMTserial();
			entity.setId(UUIDUtils.getUUID32());
			entity.setSerialNum(serialNum);
			entity.setRecReviseTime(time);
			entity.setSerialIndex(Integer.valueOf(1));
			//保存数据
			list.add(entity);
			serialsInfo.addBlock("result").addRows(list);
			this.insert(serialsInfo,"SSBMTserial.insert");
			serial = Integer.valueOf(1);
		}
		return serial;
	}
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMTserial());
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		//数据查询
		EiInfo outInfo = super.query(inInfo, "SSBMTserial.query", new SSBMTserial());
		return outInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<SSBMTserial> query(String paramString, Object paramObject) {
		return dao.query(paramString, paramObject);
	}

	public EiInfo insert(EiInfo inInfo) {
		return super.insert(inInfo);
	}
	
	public EiInfo update(EiInfo inInfo) {
		return super.update(inInfo);
	}
	
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo);
	}
}
