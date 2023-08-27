package com.baosight.wilp.si.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存App接口Service
 * All rights Reserved, Designed By www.bonawise.com
 * @author 24128
 * @version V5.0.2
 * @Title: SIJKApp.java
 * @ClassName: SIJKApp
 * @Package com.baosight.wilp.si.jk
 * @Description: TODO
 * @date: 2022年1月05日 16:09
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceSIJKApp extends ServiceBase {
    /**
     * App入库明细接口
     * @Title: enterWarehousDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo、
     *              startTime       开始时间
     *               endTime        结束时间
     *               matName        物资名称
     *               enterTypeName    入库类型
     *               wareHouseName 仓库名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *          wareHouseName ： 仓库名称
     *		    matName : 物资名称
     *		    enterTypeName: 入库类型
     *		    matSpec : 规格
     *		    unitName：单位
     *		    enterNum： 入库数量
     *		    enterTime 入库时间
     * @throws
     **/
    public EiInfo enterWarehouseDetail(EiInfo inInfo) {
        String startTime= inInfo.getString("startTime");
        String endTime= inInfo.getString("endTime");
        String matName= inInfo.getString("matName");
        String enterTypeName= inInfo.getString("enterTypeName");
        String wareHouseName= inInfo.getString("wareHouseName");
        Map<String, Object> map= new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("matName",matName);
        map.put("enterTypeName",enterTypeName);
        map.put("wareHouseName",wareHouseName);
        List<Map<String,Object>> list= dao.query("SIJKApp.enterWarehouseDetail",map);
        inInfo.set("list",list);
        return inInfo;
    }
    /**
     * App入库明细接口
     * @Title: outWarehouseDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo、
     *              startTime       开始时间
     *               endTime        结束时间
     *               matName        物资名称
     *               outTypeName    出库类型
     *               wareHouseName 仓库名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *          wareHouseName ： 仓库名称
     *		    matName : 物资名称
     *		    outTypeName: 出库类型
     *		    matSpec : 规格
     *		    unitName：单位
     *		    outNum： 出库数量
     *		    outTime 出库时间
     * @throws
     **/
    public EiInfo outWarehouseDetail(EiInfo inInfo) {
        String startTime= inInfo.getString("startTime");
        String endTime= inInfo.getString("endTime");
        String matName= inInfo.getString("matName");
        String outTypeName= inInfo.getString("outTypeName");
        String wareHouseName= inInfo.getString("wareHouseName");
        Map<String, Object> map= new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("matName",matName);
        map.put("outTypeName",outTypeName);
        map.put("wareHouseName",wareHouseName);
        List<Map<String,Object>> list= dao.query("SIJKApp.outWarehouseDetail",map);
        inInfo.set("list",list);
        return inInfo;
    }

}
