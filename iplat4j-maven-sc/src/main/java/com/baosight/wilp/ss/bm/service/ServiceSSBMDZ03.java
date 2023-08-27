package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMDZ03;
import com.baosight.wilp.ss.bm.domain.ScPatientAddress;

import java.util.HashMap;
import java.util.List;


public class ServiceSSBMDZ03 extends ServiceBase{



	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		String groupName = (String)inInfo.getAttr().get("groupName");
		String deptName = (String)inInfo.getAttr().get("deptName");

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("groupName", groupName);

		hashMap.put("deptName", deptName);
		List<ScPatientAddress> outInfo =dao.query("SSBMDZ03.query", hashMap);
		inInfo.addRows("result", outInfo);
		return inInfo;
	}



	public EiInfo delete(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("SSBMDZ03.delete", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}


	public EiInfo queryDeptName (EiInfo inInfo){
		List<ScPatientAddress> list = dao.query("SSBMDZ03.queryDeptName2", new HashMap<>());
		inInfo.addBlock("dept").addRows(list);
		inInfo.getBlock("dept").setBlockMeta(new SSBMDZ03().eiMetadata);
		return inInfo;

	}



}
