package com.baosight.wilp.mx.sc.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.common.util.PrUtils;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.mx.ps.domain.MXPS01;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author GAO
 * @title: ServiceMXSC01
 * @projectName
 * @description: 获取视频路径
 * @date 2022/4/1 10:08
 */
public class ServiceMXSC01 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName
     * @description: TODO
     * @author GAO
     * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
     * @date 2022-04-01 15:18
     *初始化
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {

        inInfo = super.initLoad(inInfo, new MXPS01());
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo){
        inInfo = super.initLoad(inInfo, new MXPS01());
        return inInfo;
    }

    /**
     * @title: 获取视频监控树
     * @projectName
     * @description: TODO
     * @author GAO
     * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
     * @date 2022-04-01 15:18
     */
    public EiInfo queryTree(EiInfo inInfo) {
        Map blocks = inInfo.getBlocks();
        EiBlock status = (EiBlock) blocks.get("inqu_status");
        List<Map> rows = status.getRows();
        String a = rows.get(0).get("node").toString();
        Map map = new HashMap();
        map.put("node", a);

        List<Map> list = dao.query("MXSC01.queryTree", map);
        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
        inInfo.getBlocks().remove(EiConstant.resultBlock);
        return inInfo;
    }


    /**
     * @title:  封装树返回类
     * @projectName
     * @description: TODO
     * @author gao
     * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
     * @date 2022-04-01 15:23
     */
    private EiBlockMeta initMetaData() {
        EiBlockMeta eiMetadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("label");
        eiColumn.setDescName("英文名");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("text");
        eiColumn.setDescName("中文名");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
        eiColumn.setDescName("父级英文名");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("leaf");
        eiColumn.setDescName("0存在 1不存在");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("url");
        eiColumn.setDescName("视频路径");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        return eiMetadata;

    }
}
