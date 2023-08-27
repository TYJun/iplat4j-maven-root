package com.baosight.wilp.di.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.jz.domain.DIJZ03;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * 设备包管理：初始化查询、设备包查询、设备包删除、设备包启、禁用
 * <p>1.初始化查询 initLoad
 * <p>2.设备包查询 query
 * <p>3.设备包删除 delete
 * <p>4.设备包启、禁用 update
 * 
 * @Title: ServiceDIJZ02.java
 * @ClassName: ServiceDIJZ02
 * @Package：com.baosight.wilp.di.jz.service
 * @author: zhangjiaqian
 * @date: 2021年5月28日 下午2:17:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIJZ03 extends ServiceBase{


    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        String configureName = inInfo.getString("configureName");
        List<DIJZ03> outInfo =dao.query("DIJZ03.query",new HashMap(){{
            put("configureName",configureName);
        }});
        inInfo.addRows("result", outInfo);
        return inInfo;
    }



    public EiInfo delete(EiInfo inInfo) {
        String id = (String) inInfo.getString("id");
        dao.delete("DIJZ03.delete", id);
        inInfo.setMsg("删除成功");
        return inInfo;
    }




}
