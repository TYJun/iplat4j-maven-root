package com.baosight.wilp.mc.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mc.dz.domain.ScAddress;
import com.baosight.wilp.mc.jk.domain.DepartMent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServiceMCJK04 extends ServiceBase{
	@Autowired
	DepartMent departMent;

	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		List<EiInfo> list = new ArrayList<EiInfo>();
		list.add(inInfo);
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr();
		List lll= (List) attr.get("obj");
		for (int a = 0; a < lll.size(); a++) {
			departMent = (DepartMent) lll.get(a);
			System.out.println("-----------------------------");
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("BIZ_ID",departMent.getBIZ_ID());
			paramMap.put("DEP_CODE",departMent.getDEP_CODE());
			paramMap.put("DEP_NAME",departMent.getDEP_NAME());
			System.out.println(paramMap);

//			List<DepartMent> list1 = dao.query("MCJK01.query",departMent.getBIZ_ID());
//			if(CollectionUtils.isEmpty(list1)) {
//				dao.insert("MCJK01.insert",departMent);
//			}
//
//			aa
//			ServiceBase base = new ServiceBase();
			dao.update("MCJK01.update",paramMap);
		}
		inInfo.setMsg("200");
		return inInfo;
	}

}
