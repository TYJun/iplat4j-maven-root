package com.baosight.wilp.mc.dz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mc.dz.domain.ScAddress;

import java.text.SimpleDateFormat;
import java.util.*;


public class ServiceMCDZ01 extends ServiceBase{



	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		String building = (String)inInfo.getAttr().get("building");
		String floor = (String)inInfo.getAttr().get("floor");
		String address = (String)inInfo.getAttr().get("address");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("building", building);
		hashMap.put("floor", floor);
		hashMap.put("address", address);
		List<ScAddress> outInfo =dao.query("MCDZ01.query", hashMap);
		inInfo.addRows("result", outInfo);
		return inInfo;
	}
	public EiInfo update(EiInfo inInfo) {

		String id = (String) inInfo.get("id");
		dao.delete("MCDZ01.update1", id);
		inInfo.setMsg("修改成功");
		return inInfo;
	}

	public EiInfo delete(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("MCDZ01.update", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}

	public EiInfo queryBuilding (EiInfo inInfo){

		List<ScAddress> list = dao.query("MCDZ01.queryBuilding", new HashMap<>());
		inInfo.addBlock("building").addRows(list);
		inInfo.getBlock("building").setBlockMeta(new ScAddress().eiMetadata);
		return inInfo;

	}

	public EiInfo queryFloor (EiInfo inInfo){

		List<ScAddress> list = dao.query("MCDZ01.queryFloor", new HashMap<>());
		inInfo.addBlock("floor").addRows(list);
		inInfo.getBlock("floor").setBlockMeta(new ScAddress().eiMetadata);
		return inInfo;

	}

	public EiInfo delete1(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("MCDZ01.update2", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}



}
