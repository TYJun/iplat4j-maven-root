package com.baosight.wilp.mp.fp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContInvoice;
import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpInvoiceService;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 发票管理子页面Service
 * @ClassName: ServiceMPFP0101
 * @Package com.baosight.wilp.mp.fp.service
 * @date: 2022年10月14日 14:50
 *
 * 1.页面加载
 * 2.合同查询
 * 3.根据合同查询供应商
 * 4.保存发票信息
 */
public class ServiceMPFP0101 extends ServiceBase {

    @Autowired
    private MpInvoiceService invoiceService;

    @Autowired
    private MpContractService contractService;

    @Autowired
    private MpPurchaseOrderService orderService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //编辑或查看详情，数据回显
        if(!MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            //获取发票信息
            List<MpContInvoice> list = dao.query("MPLJ05.query", inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
            inInfo.setRows(MpConstant.QUERY_BLOCK, list);
            //获取发票明细
            List<MpInvoiceDetail> detailList = invoiceService.queryInvoiceDetailList(inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
            inInfo.setRows(MpConstant.RESULT_BLOCK, detailList);
        }
        return inInfo;
    }

    /**
     * 合同查询
     * @Title: queryCont
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryCont(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        List<Map<String, String>> rows = contractService.queryContTree(MpUtils.toString(deptMap.get("deptNum")), "root", null);
        inInfo.setRows("cont", rows);
        return inInfo;
    }

    /**
     * 获取合同的供应商
     * @Title: querySupplier
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo querySupplier(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        List<Map<String, String>> rows = contractService.queryContTree(MpUtils.toString(deptMap.get("deptNum")),
                inInfo.getString("id"), null);
        if(CollectionUtils.isNotEmpty(rows)) {
            inInfo.set("data", rows.get(0));
        }
        return inInfo;
    }

    /**
     * 保存发票信息
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        //获取参数
        MpContInvoice invoice = new MpContInvoice();
        invoice.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
        List<MpInvoiceDetail> details = MpUtils.toList(inInfo.get("list"), MpInvoiceDetail.class);
        //参数校验
        EiInfo validateInfo = ValidatorUtils.validateEntity(invoice);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("发票明细不能为空");
        }
        //判断是新增还是编辑
        if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            invoice.setId(UUID.randomUUID().toString());
            invoice.setStatusCode(MpConstant.INVOICE_STATUS_NEW);
            invoice.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.cont.invoiceStatus", invoice.getStatusCode()));
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            invoice.setDeptNum(MpUtils.toString(deptMap.get("deptNum")));
            invoice.setDeptName(MpUtils.toString(deptMap.get("deptName")));
            invoice.setRecCreator(UserSession.getLoginName());
            invoice.setRecCreatorName(UserSession.getLoginCName());
            invoice.setRecCreateTime(new Date());
            invoice.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            invoiceService.insert(invoice);
        } else {
            invoice.setRecRevisor(UserSession.getLoginName());
            invoice.setRecReviseTime(new Date());
            invoiceService.update(invoice);
            //更新订单明细的开票字段为未开票
            orderService.updateHasInvoice(invoiceService.queryInvoiceDetailList(invoice.getId()), false);
            invoiceService.deleteDetail(invoice.getId());
        }
        //明细处理
        details.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setInvoiceId(invoice.getId());
        });
        invoiceService.insertDetail(details);
        //更新订单开票标记
        orderService.updateHasInvoice(details, true);
        return inInfo;
    }
}
