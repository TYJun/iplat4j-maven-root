package com.baosight.wilp.rm.tk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmApproval;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;
import com.baosight.wilp.rm.lj.service.RmApprovalHistoryService;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 退库申请审批子页面Service
 * @ClassName: ServiceRMTK0201
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年10月25日 9:47
 * <p>
 * 1.页面加载
 * 2.审批通过
 * 3.审批驳回
 */
public class ServiceRMTK0201 extends ServiceBase {

    @Autowired
    private RmBackOutService backOutService;

    @Autowired
    private RmApprovalHistoryService approvalHistoryService;

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
        //获取退库主信息
        List<RmBackOut> list = dao.query("RMLJ03.query", inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
        inInfo.setRows(RmConstant.QUERY_BLOCK, list);
        //获取退库明细信息
        List<RmBackOutDetail> detailList = backOutService.queryBackOutDetailList(inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id"));
        inInfo.setRows(RmConstant.RESULT_BLOCK, detailList);
        return inInfo;
    }

    /**
     * 审批通过
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: pass
     **/
    public EiInfo pass(EiInfo inInfo) {
        return approval(inInfo, RmConstant.BACK_OUT_STATUS_UN_OUT);
    }

    /**
     * 审批驳回
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: reject
     **/
    public EiInfo reject(EiInfo inInfo) {
        //参数校验
        if (StringUtils.isBlank(inInfo.getString("rejectReason"))) {
            return ValidatorUtils.errorInfo("驳回原因不能为空");
        }
        return approval(inInfo, RmConstant.BACK_OUT_STATUS_REJECT);
    }

    /**
     * 审批
     *
     * @param inInfo     inInfo
     * @param statusCode statusCode
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: approval
     **/
    private EiInfo approval(EiInfo inInfo, String statusCode) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //校验是否可以审批
        RmBackOut backOut = backOutService.queryBackOut(id);
        if (backOut == null || !RmConstant.BACK_OUT_STATUS_UN_APPROVAL.equals(backOut.getStatusCode())) {
            return ValidatorUtils.errorInfo("退库申请不存在或已审批");
        }
        //审批
        RmBackOut back = RmBackOut.getStatusInstance(id, statusCode);
        backOutService.update(back);
        //保存审批履历
        RmApproval approval = RmApproval.getInstance(id, statusCode, back.getStatusName(),
                RmConstant.BACK_OUT_STATUS_REJECT.equals(statusCode) ? inInfo.getString("rejectReason") : null);
        approvalHistoryService.approval(approval);
        return inInfo;
    }
}
