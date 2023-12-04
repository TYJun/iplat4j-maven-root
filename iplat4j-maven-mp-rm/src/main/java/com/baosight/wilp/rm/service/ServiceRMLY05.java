package com.baosight.wilp.rm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 新物资领用申请页面service
 * @ClassName: ServiceRMLY05
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2023年02月02日 17:19
 *
 * 1.页面加载
 * 2.页面查询
 */
public class ServiceRMLY05 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum",deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName",deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
        //新增/编辑区域
        inInfo.setCell("detail_form", 0, "deptNum",deptMap.get("deptNum"));
        inInfo.setCell("detail_form", 0, "deptName",deptMap.get("deptName"));
        inInfo.setCell("detail_form", 0, "recCreator", UserSession.getLoginName());
        inInfo.setCell("detail_form", 0, "recCreatorName", UserSession.getLoginCName());
        inInfo.set(RmConstant.OPERATE_NAME, RmConstant.OPERATE_TYPE_ADD);
       return inInfo;
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
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }

    /**
     * 查询领用申请明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        String claimId = inInfo.getCellStr("detail_form", 0, "id");
        List<RmClaimDetail> detailList = claimService.queryClaimDetailList(claimId);
        inInfo.setRows("detail", detailList);
        return inInfo;
    }
}
