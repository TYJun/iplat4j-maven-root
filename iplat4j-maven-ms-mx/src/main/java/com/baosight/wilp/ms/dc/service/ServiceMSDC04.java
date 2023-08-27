package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.dc.domain.MSDC01;
import com.baosight.wilp.ms.dc.domain.MSDC03;
import com.baosight.wilp.ms.pl.domain.MSPL01;


/**  
 * @title: 视频源页面
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 14:32 
 */
public class ServiceMSDC04 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:32
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo, new MSDC03());
    }

    public EiInfo QueryParameters(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "MSDC01.query", new MSDC01());
        return outInfo;
    }

    /**
     * @title: 修改报警规则参数绑定
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:32
     * 1、从inInfo获取传入需要修改的id和填入的parentIds，然后进行修改数据数据
     */
    public EiInfo updateTmsar(EiInfo inInfo) {
        MSPL01 mspl01 = new MSPL01();
        mspl01.setTmsar01Id(inInfo.get("parentIds").toString());
        mspl01.setId(inInfo.get("id").toString());
        PackageOarameters.setRows(inInfo, mspl01);
        EiInfo outInfo = super.update(inInfo, "MSPL01.updateTmsar");
        outInfo.setMsg("修改成功");
        return outInfo;
    }
}
