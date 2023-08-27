package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单Service
 * @ClassName: MpPurchaseOrderService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年10月11日 14:35
 *
 * 1.获取指定采购订单
 * 2.新增采购订单
 * 3.更新采购订单
 * 4.删除采购订单
 * 5.获取采购订单明细
 * 6.批量新增采购订单明细
 * 7.更新采购订单明细
 * 8.删除采购订单明细
 * 9.校验订单数量是否大于入库数量
 * 10.判断订单是否完成入库
 * 11.判断入库数量是否大于可入库数量
 * 12.更新是否已开票编辑
 */
public interface MpPurchaseOrderService {

    /**
     * 获取指定采购订单
     * @Title: queryPurchaseOrder
     * @param id id
     * @return com.baosight.wilp.mp.lj.domain.MpPurchaseOrder
     * @throws
     **/
    MpPurchaseOrder queryPurchaseOrder(String id);

    /**
     * 新增采购订单
     * @Title: insert
     * @param order order
     * @return void
     * @throws
     **/
    void insert(MpPurchaseOrder order);

    /**
     * 更新采购订单
     * @Title: update
     * @param order order
     * @return int
     * @throws
     **/
    int update(MpPurchaseOrder order);

    /**
     * 删除采购订单
     * @Title: delete
     * @param id id
     * @return int
     * @throws
     **/
    int delete(String id);

    /**
     * 获取采购订单明细
     * @Title: queryPurchaseOrderDetailList
     * @param purchaseOrderId purchaseOrderId
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail>
     * @throws
     **/
    List<MpPurchaseOrderDetail> queryPurchaseOrderDetailList(String purchaseOrderId);

    /**
     * 批量新增采购订单明细
     * @Title: insertDetail
     * @param list list
     * @return void
     * @throws
     **/
    void insertDetail(List<MpPurchaseOrderDetail> list);

    /**
     * 更新采购订单明细
     * @Title: updateDetail
     * @param detail detail
     * @return int
     * @throws
     **/
    int updateDetail(MpPurchaseOrderDetail detail);

    /**
     * 删除采购订单明细
     * @Title: deleteDetail
     * @param purchaseOrderId purchaseOrderId
     * @return int
     * @throws
     **/
    int deleteDetail(String purchaseOrderId);

    /**
     * 校验订单数量是否大于入库数量
     * @Title: hasEnoughOrderNum
     * @param detail detail
     * @return boolean
     * @throws
     **/
    boolean hasEnoughOrderNum(MpPurchaseOrderDetail detail);

    /**
     * 判断订单是否完成入库
     * @Title: hasAllEnter
     * @param id id : 订单ID
     * @return boolean
     * @throws
     **/
    boolean hasAllEnter(String id);

    /**
     * 判断入库数量是否大于可入库数量
     * @Title: hasEnoughEnterNum
     * @param detail detail : 订单明细对象
     * @return boolean
     * @throws
     **/
    boolean hasEnoughEnterNum(MpPurchaseOrderDetail detail);

    /**
     * 更新是否已开票编辑
     * @Title: updateHasInvoice
     * @param details details : 发票明细集合
     * @param hasInvoice hasInvoice : 是否已开票
     * @return int
     * @throws
     **/
    int updateHasInvoice(List<MpInvoiceDetail> details, boolean hasInvoice);
}
