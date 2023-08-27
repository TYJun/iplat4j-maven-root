package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.dc.domain.*;

import java.util.*;

/**  
    * @title: 移动页面(页面初始化方法,修改方法)
    * @projectName iplat_v5_monitor
    * @description: TODO
    * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
    * @date 2021-09-08 15:08
    */
@SuppressWarnings("unchecked")
public class ServiceMSDC02 extends ServiceBase {
    /**
        * @title: 页面初始化方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:15
        */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        WebSocketService.TAG_CONFIG_CACHE.clear();
        return inInfo;
    }
    /**
        * @title: 修改方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
        * @date 2021-09-08 14:16
        * 1、获取inqu_status参数
        * 2、获取parentId
        * 3、执行sql语句
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        */
    public EiInfo insert2(EiInfo inInfo) {
        /**
         * 获取inInfo对象中传过来的id
         */
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        MSDC01 msdc01 = new MSDC01();
        msdc01.setGroup_id((String) o.get("parentId"));
        System.out.println(o.get("parentId"));
        if (inInfo.getAttr().get("id") != null) {
            msdc01.setDC_id(inInfo.getAttr().get("id").toString());
        }
        PackageOarameters.setRows(inInfo, msdc01);
        EiInfo outInfo = super.update(inInfo, "MSDC02.update");
        outInfo.setMsg("修改成功");
        return outInfo;
    }
}
