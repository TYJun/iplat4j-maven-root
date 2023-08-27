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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 合同发票页面Service
 * @ClassName: ServiceMPFP01
 * @Package com.baosight.wilp.mp.fp.service
 * @date: 2022年10月14日 11:24
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除合同发票
 * 4.合同查询
 * 5.提交
 */
public class ServiceMPFP01 extends ServiceBase {

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
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        return query(inInfo);
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo, "MPLJ05.query", new MpContInvoice());
    }

    /**
     * 删除合同发票
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        //获取并校验删除
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //删除,判断是否删除成功.否，提示错误信息;是,删除明细
        int delete = invoiceService.delete(id);
        if(delete == 0) {
            return ValidatorUtils.errorInfo("已提交的发票无法删除");
        }
        //更新订单明细的开票字段为未开票
        orderService.updateHasInvoice(invoiceService.queryInvoiceDetailList(id), false);
        //删除发票明细
        invoiceService.deleteDetail(id);
        return inInfo;
    }

    /**
     * 提交发票
     * @Title: submit
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo submit(EiInfo inInfo) {
        //获取参数并校验
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //校验发票的状态是否可以提交
        MpContInvoice invoice = invoiceService.queryInvoice(id);
        if(invoice == null || !MpConstant.INVOICE_STATUS_NEW.equals(invoice.getStatusCode())) {
            return ValidatorUtils.errorInfo("发票不存在或已提交");
        }
        //校验发票金额是否正确
        List<MpInvoiceDetail> details = invoiceService.queryInvoiceDetailList(id);
        BigDecimal totalCost = details.stream().map(MpInvoiceDetail::getOrderCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        if(invoice.getInvoiceCost().compareTo(totalCost) != 0) {
            return ValidatorUtils.errorInfo("发票金额与订单总金额不等。发票含税金额:"+invoice.getInvoiceCost()
                    +"元,订单总金额:"+totalCost+"元");
        }
        //更新发票的状态
        invoice.setStatusCode(MpConstant.INVOICE_STATUS_SUBMIT);
        invoice.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.cont.invoiceStatus", invoice.getStatusCode()));
        invoice.setRecRevisor(UserSession.getLoginName());
        invoice.setRecReviseTime(new Date());
        invoiceService.update(invoice);
        //修改合同的开票金额
        details.forEach(detail -> contractService.updateInvoiceNum(detail));
        //更新入库单的发票号
        updateEnterBillInvoiceNo(details, invoice.getInvoiceNo());
        return inInfo;
    }

    /**
     * 更新入库单的发票号
     * @Title: updateEnterBillInvoiceNo
     * @param details details : 发票明细
     * @param invoiceNo invoiceNo : 发票号
     * @return void
     * @throws
     **/
    private void updateEnterBillInvoiceNo(List<MpInvoiceDetail> details, String invoiceNo) {
        List<Map<String, String>> list = new ArrayList<>();
        //遍历明细
        for (MpInvoiceDetail detail : details) {
            HashMap<String, String> map = new HashMap<>(8);
            map.put("originBillNo", detail.getOrderNo());
            map.put("matNum", detail.getMatNum());
            map.put("matTypeNum", detail.getMatTypeId());
            map.put("invoiceNo", invoiceNo);
            list.add(map);
        }

        //调用微服务接口
        MpUtils.invoke("MPJK02", "updateInvoiceNo", Arrays.asList("list"), list);
    }

}
