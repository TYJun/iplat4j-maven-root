package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.MpContInvoice;
import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购发票Service
 * @ClassName: MpInvoiceService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年10月11日 14:38
 *
 * 1.获取指定的发票信息
 * 2.新增发票信息
 * 3.更新发票信息
 * 4.删除发票信息
 * 5.获取指定发票的明细信息
 * 6.批量新增发票明细信息
 * 7.根据发票ID删除发票明细信息
 * 8.清除付款信息
 * 9.根据付款ID更新发票状态
 * 10.向发票表中插入付款ID
 * 11.根据付款ID获取发票明细(合并相同物资)
 * 12.根据付款ID获取发票信息
 */
public interface MpInvoiceService {

    /**
     * 获取指定的发票信息
     * @Title: queryInvoice
     * @param id id : 发票ID
     * @return com.baosight.wilp.mp.lj.domain.MpContInvoice
     * @throws
     **/
    MpContInvoice queryInvoice(String id);

    /**
     * 新增发票信息
     * @Title: insert
     * @param invoice invoice : 发票对象
     * @return void
     * @throws
     **/
    void insert(MpContInvoice invoice);

    /**
     * 更新发票信息
     * @Title: update
     * @param invoice invoice : 发票对象
     * @return int
     * @throws
     **/
    int update(MpContInvoice invoice);

    /**
     * 删除发票信息
     * @Title: delete
     * @param id id : 发票ID
     * @return int
     * @throws
     **/
    int delete(String id);

    /**
     * 获取指定发票的明细信息
     * @Title: queryInvoiceDetailList
     * @param invoiceId invoiceId : 发票ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpInvoiceDetail>
     * @throws
     **/
    List<MpInvoiceDetail> queryInvoiceDetailList(String invoiceId);

    /**
     * 批量新增发票明细信息
     * @Title: insertDetail
     * @param list list : 发票明细集合
     * @return void
     * @throws
     **/
    void insertDetail(List<MpInvoiceDetail> list);

    /**
     * 根据发票ID删除发票明细信息
     * @Title: deleteDetail
     * @param invoiceId invoiceId : 发票ID
     * @return int
     * @throws
     **/
    int deleteDetail(String invoiceId);

    /**
     * 清除付款信息
     * @Title: clearPayId
     * @param payId payId : 付款ID
     * @return void
     * @throws
     **/
    int clearPayId(String payId);

    /**
     * 根据付款ID更新发票状态
     * @Title: updateByPayId
     * @param payId payId : 付款ID
     * @return void
     * @throws
     **/
    int updateByPayId(String payId);

    /**
     * 向发票表中插入付款ID
     * @Title: addPayId
     * @param payId payId : 付款ID
     * @param list list : 发票ID集合
     * @return int
     * @throws
     **/
    int addPayId(String payId, List<String> list);

    /**
     * 根据付款ID获取发票明细(合并相同物资)
     * @Title: queryInvoiceDetailByPayId
     * @param id id : 付款ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpInvoiceDetail>
     * @throws
     **/
    List<MpInvoiceDetail> queryInvoiceDetailByPayId(String id);

    /**
     * 根据付款ID获取发票信息
     * @Title: queryContInvoiceByPayId
     * @param payId payId : 付款ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContInvoice>
     * @throws
     **/
    List<MpContInvoice> queryContInvoiceByPayId(String payId);
}
