package com.baosight.wilp.ms.pl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ms.pl.domain.MSPL0201;
import com.baosight.wilp.ms.pl.domain.MSPL0202;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author znl
 * @title: ServiceMSPL0201
 * @projectName iplat_v5_monitor
 * @description: 树列表 (页面初始化方法、查询列表、查询方法、删除方法）
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/10 9:57
 */
public class ServiceMSPL0202 extends ServiceBase {
    
    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:38
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        MSPL0202 mspl0202 = new MSPL0202();
        if (inInfo.getAttr().get("id") != null) {    //inqu_status
            mspl0202.setId(inInfo.getAttr().get("id").toString());
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(0, mspl0202);
            inInfo.addBlock("inqu_status").addRows(arrayList);
            inInfo.getBlock("inqu_status").setBlockMeta(new MSPL0202().eiMetadata);
        }
        EiInfo outInfo = super.query(inInfo, "MSPL02.relevance23");
        return outInfo;
    }

    /**  
     * @title: 查询分组列表
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:38 
     */
    public EiInfo queryWorktype(EiInfo inInfo) {
        HashMap<Object, Object> attr = new HashMap<>();
        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId, "S_XS_01");    //调用用户组信息
        EiInfo outInfo = XServiceManager.call(eiInfo);
        eiInfo.set("limit", outInfo.getAttr().get("size"));
        XServiceManager.call(eiInfo);
        EiInfo outInfo2 = XServiceManager.call(eiInfo);
        List result = (List) outInfo2.getAttr().get("result");
        inInfo.addBlock("worktypeName").addRows(result);
        inInfo.getBlock("worktypeName").setBlockMeta(new MSPL0201().eiMetadata);
        return inInfo;
    }
    /**  
     * @title: 查询方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:38 
     */
    public EiInfo query(EiInfo inInfo) {
        MSPL0202 mspl0202 = new MSPL0202();
        if (inInfo.getAttr().get("id") != null) {    //inqu_status
            mspl0202.setId(inInfo.getAttr().get("id").toString());
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(0, mspl0202);
            inInfo.addBlock("inqu_status").addRows(arrayList);
            inInfo.getBlock("inqu_status").setBlockMeta(new MSPL0202().eiMetadata);
        }
        EiInfo outInfo = super.query(inInfo, "MSPL02.relevance23");
        return outInfo;
    }
    /**  
     * @title: 删除方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:38 
     */
    public EiInfo delete(EiInfo inInfo) {
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        HashMap o = (HashMap) result.getRows().get(0);
        o.put("id", inInfo.getAttr().get("id"));
        EiInfo outInfo = super.delete(inInfo, "MSPL02.relevanceDelete");
        return inInfo;
    }
}
