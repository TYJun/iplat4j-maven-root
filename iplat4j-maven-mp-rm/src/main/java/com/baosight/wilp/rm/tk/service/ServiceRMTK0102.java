package com.baosight.wilp.rm.tk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 退库领用单选择子页面Service
 * @ClassName: ServiceRMTK0102
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年11月01日 17:32
 * <p>
 * 1.页面加载
 * 2.页面数据查询
 * 3.页面明细数据查询
 */
public class ServiceRMTK0102 extends ServiceBase {

    /**
     * 领用单状态数组
     **/
    private static String[] statusCodes = {RmConstant.CLAIM_STATUS_PART_OUT,
            RmConstant.CLAIM_STATUS_UN_SIGN, RmConstant.CLAIM_STATUS_SIGNED, RmConstant.CLAIM_STATUS_OVER
    };

    /**
     * 页面加载
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: initLoad
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 页面数据查询
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes", statusCodes);
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "applyOrCostDept", inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "deptNum"));
        inInfo.removeCol(RmConstant.QUERY_BLOCK, "deptNum");
        inInfo.removeCol(RmConstant.QUERY_BLOCK, "deptName");
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }

    /**
     * 明细数据查询
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryDetail
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "showOutZero", "N");
        return super.query(inInfo, "RMLJ02.queryDetail", new RmClaimDetail(), false, null, null, "detail", "detail");
    }
}
