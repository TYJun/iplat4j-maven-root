package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.WareHouseDataSplitUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;

import java.util.Arrays;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用综合查询页面service
 * @ClassName: ServiceRMLY04
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2023年02月02日 17:19
 *
 * 1.页面加载
 * 2.页面查询
 */
public class ServiceRMLY04 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 50);
        return query(inInfo);
    }

    /**
     * 页面查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //判断是否是仓库人员
        if(RmUtils.containRole(UserSession.getLoginName(), "MPCK001", "MPCK002", "MPCK003")) {
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimType",
                    WareHouseDataSplitUtils.getClaimType(UserSession.getLoginName()));
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes", Arrays.asList("30","40","50","60","70","80","90"));
        } else {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName",deptMap.get("deptName"));
        }
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }
}
