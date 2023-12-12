package com.baosight.wilp.rm.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.*;

public class ServiceRMPZ03 extends ServiceBase {
    /**
     * 页面初始化方法
     */

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询功能
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "RMPZ03.query");

        return outInfo;

    }

    /**
     * 删除功能
     */
	public EiInfo delete(EiInfo inInfo) {

        String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
		dao.delete("RMPZ03.delete",map);
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}

    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String matCode = inInfo.get("matCode") == null ? "" : inInfo.getString("matCode");
        String matName = inInfo.get("matName") == null ? "" : inInfo.getString("matName");
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("matCode",matCode);
        map.put("matName",matName);
        dao.insert("RMPZ03.insert", map);

        inInfo.setMsg("成功");
        return inInfo;


    }


}
