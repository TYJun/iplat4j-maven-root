package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.ScAddr;
import com.baosight.wilp.ss.bm.domain.ScAddress;

import java.util.HashMap;
import java.util.List;


public class ServiceSSBMDZ04 extends ServiceBase{



	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		String building = (String)inInfo.getAttr().get("building");
		String menuName = (String)inInfo.getAttr().get("menuName");
//		String address = (String)inInfo.getAttr().get("address");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("building", building);
		hashMap.put("menuName", menuName);
//		hashMap.put("address", address);
		List<ScAddr> outInfo =dao.query("SSBMDZ04.query", hashMap);
		inInfo.addRows("result", outInfo);
		return inInfo;
	}

	public EiInfo update(EiInfo inInfo) {

		String id = (String) inInfo.get("id");
		dao.delete("SSBMDZ04.update1", id);
		inInfo.setMsg("修改成功");
		return inInfo;
	}
	public EiInfo update1(EiInfo inInfo) {

		String id = (String) inInfo.get("id");
		dao.delete("SSBMDZ04.update2", id);
		inInfo.setMsg("启用成功");
		return inInfo;
	}

	public EiInfo delete(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("SSBMDZ04.update", id);

		inInfo.setMsg("停用成功");
		return inInfo;
	}

	public EiInfo delete1(EiInfo inInfo) {

		String id = (String) inInfo.get("id");

		dao.delete("SSBMDZ04.update2", id);

		inInfo.setMsg("删除成功");
		return inInfo;
	}

	public EiInfo queryBuilding (EiInfo inInfo){

		List<ScAddress> list = dao.query("SSBMDZ04.queryBuilding", new HashMap<>());
		inInfo.addBlock("building").addRows(list);
		inInfo.getBlock("building").setBlockMeta(new ScAddr().eiMetadata);
		return inInfo;

	}

	public EiInfo queryMenuName (EiInfo inInfo){

		List<ScAddress> list = dao.query("SSBMDZ04.queryMenuName", new HashMap<>());
		inInfo.addBlock("menuName").addRows(list);
		inInfo.getBlock("menuName").setBlockMeta(new ScAddr().eiMetadata);
		return inInfo;

	}


}
