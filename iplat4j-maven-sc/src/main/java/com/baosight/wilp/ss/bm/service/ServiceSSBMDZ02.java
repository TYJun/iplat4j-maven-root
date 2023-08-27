package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.ScPatientAddress;

import java.util.HashMap;
import java.util.List;


public class ServiceSSBMDZ02 extends ServiceBase{



	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		String building = (String)inInfo.getAttr().get("building");
		String floor = (String)inInfo.getAttr().get("floor");
		String deptName = (String)inInfo.getAttr().get("deptName");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("building", building);
		hashMap.put("floor", floor);
		hashMap.put("deptName", deptName);
		List<ScPatientAddress> outInfo =dao.query("SSBMDZ02.query", hashMap);
		inInfo.addRows("result", outInfo);
		return inInfo;
	}

	public EiInfo update(EiInfo inInfo) {

		String id = (String) inInfo.get("id");
		dao.delete("SSBMDZ02.update1", id);
		inInfo.setMsg("修改成功");
		return inInfo;
	}
	public EiInfo update1(EiInfo inInfo) {

		String id = (String) inInfo.get("id");
		dao.delete("SSBMDZ02.update2", id);
		inInfo.setMsg("修改成功");
		return inInfo;
	}

	public EiInfo delete(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("SSBMDZ02.update", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}

	public EiInfo delete1(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("SSBMDZ02.update2", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}

	public EiInfo queryBuilding (EiInfo inInfo){

		List<ScPatientAddress> list = dao.query("SSBMDZ02.queryBuilding", new HashMap<>());
		inInfo.addBlock("building").addRows(list);
		inInfo.getBlock("building").setBlockMeta(new ScPatientAddress().eiMetadata);
		return inInfo;

	}

	public EiInfo queryFloor (EiInfo inInfo){

		List<ScPatientAddress> list = dao.query("SSBMDZ02.queryFloor", new HashMap<>());
		inInfo.addBlock("floor").addRows(list);
		inInfo.getBlock("floor").setBlockMeta(new ScPatientAddress().eiMetadata);
		return inInfo;

	}


	public EiInfo queryDeptName (EiInfo inInfo){

		List<ScPatientAddress> list = dao.query("SSBMDZ02.queryDeptName", new HashMap<>());
		inInfo.addBlock("deptName").addRows(list);
		inInfo.getBlock("deptName").setBlockMeta(new ScPatientAddress().eiMetadata);
		return inInfo;

	}



}
