package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 对接合同选择子页面Service
 * @ClassName: ServiceMPHT0104
 * @Package com.baosight.wilp.mp.ht.service
 * @date: 2022年11月07日 17:49
 */
public class ServiceMPHT0104 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        return inInfo;
    }

}
