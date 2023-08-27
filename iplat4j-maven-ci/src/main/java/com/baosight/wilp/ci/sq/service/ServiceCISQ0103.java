package com.baosight.wilp.ci.sq.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *物资申请编辑页面
 * <p>1.初始化查询 initLoad
 * <p>2.申请编辑 update
 * <p>3.查询科室 queryDept
 *
 * @Title: ServiceCISQ0101.java
 * @ClassName: ServiceCISQ0101
 * @Package：com.baosight.wilp.md.sq.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCISQ0103 extends ServiceBase {

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String,String> pMap = new HashMap<>(4);
        pMap.put("applyBillNo", inInfo.getString("applyBillNo"));

        inInfo.set("canteenData_textField", inInfo.get("canteenName"));
        List<CiEnterDetail> list = dao.query("CISQ0101.query", pMap);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiEnterDetail().eiMetadata, list, list.size());
    }
}
