package com.baosight.wilp.rm.lj.service;

import com.baosight.wilp.rm.lj.domain.RmApproval;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 审批履历Service接口
 * @ClassName: RmApprovalHistoryService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年09月13日 9:15
 * <p>
 * 1.审批
 * 2.新增审批履历
 * 3.编辑审批履历
 * 4.获取审批履历
 */
public interface RmApprovalHistoryService {

    /**
     * 保存审批履历
     *
     * @param approval approval : 审批履历对象
     * @return void
     * @throws
     * @Title: approval
     **/
    void approval(RmApproval approval);

    /**
     * 新增
     *
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     * @Title: insert
     **/
    void insert(RmApproval approval);

    /**
     * 编辑
     *
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     * @Title: update
     **/
    void update(RmApproval approval);

    /**
     * 获取审批履历
     *
     * @param relateId relateId : 关联ID(需求计划ID/领用申请ID)
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmApproval>
     * @throws
     * @Title: queryApproval
     **/
    List<RmApproval> queryApproval(String relateId);
}
