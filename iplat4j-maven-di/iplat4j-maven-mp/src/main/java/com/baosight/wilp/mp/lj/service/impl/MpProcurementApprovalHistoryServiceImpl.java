package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.lj.domain.MpApproval;
import com.baosight.wilp.mp.lj.service.MpProcurementApprovalHistoryService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author liangyongfei
 * @version V5.0.0
 * @Description: 审批履历Service接口实现
 * @ClassName: RmApprovalHistoryServiceImpl
 * @Package com.baosight.wilp.rm.lj.service.impl
 * @date: 2022年09月13日 9:16
 *
 * 1.保存审批履历
 * 2.新增审批履历
 * 3.编辑审批履历
 */
@Service
public class MpProcurementApprovalHistoryServiceImpl implements MpProcurementApprovalHistoryService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**状态：有效**/
    String STATUS_VALID = "1";
    /**状态：无效**/
    String STATUS_INVALID = "0";

    /**
     * 保存审批履历
     * @Title: approval
     * @param approval approval : 审批履历对象
     * @return void
     * @throws
     **/
    @Async("taskExecutor")
    @Override
    public void approval(MpApproval approval) {
        //将之前的审批履历标记为无效
        approval.setStatus(STATUS_INVALID);
        update(approval);
        //插入新的审批履历
        approval.setStatus(STATUS_VALID);
        insert(approval);
    }

    /**
     * 新增
     * @Title: insert
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    @Override

    public void insert(MpApproval approval) {
        dao.insert("MPLJ04.insert", approval);
    }

    /**
     * 编辑
     * @Title: update
     * @param approval approval  : 审批履历对象
     * @return void
     * @throws
     **/
    @Override
    public void update(MpApproval approval) {
        dao.update("MPLJ04.update", approval);
    }

    /**
     * 获取审批履历
     * @Title: queryApproval
     * @param relateId relateId : 关联ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpApproval>
     * @throws
     **/
    @Override
    public List<MpApproval> queryApproval(String relateId) {
        return dao.query("MPLJ04.query", new HashMap(2){{
            put("relateId", relateId);
        }});
    }
}
