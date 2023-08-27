package com.baosight.wilp.mp.lj.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.mp.lj.domain.MpApproval;

import java.util.List;

/**
 * @author lyf
 * @version V5.0.0
 * @Description: 采购计划审批履历Service接口
 * @ClassName: MpProcurementApprovalHistoryService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年09月13日 9:15
 *
 * 1.审批
 * 2.新增审批履历
 * 3.编辑审批履历
 * 4.获取审批履历
 */
public interface MpProcurementApprovalHistoryService {

    /**
     * 保存审批履历
     * @Title: approval
     * @param approval approval : 审批履历对象
     * @return void
     * @throws
     **/
    void approval(MpApproval approval);

    /**
     * 新增
     * @Title: insert
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    void insert(MpApproval approval);

    /**
     * 编辑
     * @Title: update
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    void update(MpApproval approval);

    /**
     * 获取审批履历
     * @Title: queryApproval
     * @param relateId relateId
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpApproval>
     * @throws
     **/
    List<MpApproval> queryApproval(String relateId);
}
