package rm.lj.service;

import com.baosight.wilp.rm.lj.domain.RmApproval;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 审批履历Service接口
 * @ClassName: RmApprovalHistoryService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年09月13日 9:15
 *
 * 1.审批
 * 2.新增审批履历
 * 3.编辑审批履历
 * 4.获取审批履历
 */
public interface RmApprovalHistoryService {

    /**
     * 保存审批履历
     * @Title: approval
     * @param approval approval : 审批履历对象
     * @return void
     * @throws
     **/
    void approval(RmApproval approval);

    /**
     * 新增
     * @Title: insert
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    void insert(RmApproval approval);

    /**
     * 编辑
     * @Title: update
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    void update(RmApproval approval);

    /**
     * 获取审批履历
     * @Title: queryApproval
     * @param relateId relateId : 关联ID(需求计划ID/领用申请ID)
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmApproval>
     * @throws
     **/
    List<RmApproval> queryApproval(String relateId);

    /**
     * 修改历史审批记录为过时
     * @Title: deprecated
     * @param relateId relateId : 关联ID(需求计划ID/领用申请ID)
     * @return int
     * @throws
     **/
    int deprecated(String relateId);
}
