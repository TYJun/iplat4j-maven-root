package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单Service实现
 * @ClassName: MpPurchaseOrderServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年10月11日 14:42
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
@Service
public class MpPurchaseOrderServiceImpl implements MpPurchaseOrderService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 获取指定采购订单
     * @Title: queryPurchaseOrder
     * @param id id : 采购订单ID
     * @return com.baosight.wilp.mp.lj.domain.MpPurchaseOrder
     * @throws
     **/
    @Override
    public MpPurchaseOrder queryPurchaseOrder(String id) {
        List<MpPurchaseOrder> list = dao.query("MPLJ03.query", new HashMap(2) {{
            put("id", id);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增采购订单
     * @Title: insert
     * @param order order : 采购订单对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpPurchaseOrder order) {
        dao.insert("MPLJ03.insert", order);
    }

    /**
     * 更新采购订单
     * @Title: update
     * @param order order : 采购订单对象
     * @return int
     * @throws
     **/
    @Override
    public int update(MpPurchaseOrder order) {
        return dao.update("MPLJ03.update", order);
    }

    /**
     * 删除采购订单
     * @Title: delete
     * @param id id : 采购订单ID
     * @return int
     * @throws
     **/
    @Override
    public int delete(String id) {
        return dao.delete("MPLJ03.delete", id);
    }

    /**
     * 获取采购订单明细
     * @Title: queryPurchaseOrderDetailList
     * @param purchaseOrderId purchaseOrderId : 采购订单ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail>
     * @throws
     **/
    @Override
    public List<MpPurchaseOrderDetail> queryPurchaseOrderDetailList(String purchaseOrderId) {
        return dao.query("MPLJ03.queryDetail", new HashMap(2) {{
            put("orderId", purchaseOrderId);
        }});
    }

    /**
     * 批量新增采购订单明细
     * @Title: insertDetail
     * @param list list : 采购订单明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<MpPurchaseOrderDetail> list) {
        dao.insert("MPLJ03.insertDetail", list);
    }

    /**
     * 更新采购订单明细
     * @Title: updateDetail
     * @param detail detail : 采购订单明细对象
     * @return int
     * @throws
     **/
    @Override
    public int updateDetail(MpPurchaseOrderDetail detail) {
        return dao.update("MPLJ03.updateDetail", detail);
    }

    /**
     * 删除采购订单明细
     * @Title: deleteDetail
     * @param purchaseOrderId purchaseOrderId : 采购订单ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteDetail(String purchaseOrderId) {
        return dao.delete("MPLJ03.deleteDetail", purchaseOrderId);
    }

    /**
     * 校验订单数量是否大于入库数量
     * @Title: hasEnoughOrderNum
     * @param detail detail
     * @return boolean
     * @throws
     **/
    @Override
    public boolean hasEnoughOrderNum(MpPurchaseOrderDetail detail) {
        //数据查询
        List<MpPurchaseOrderDetail> list = dao.query("MPLJ03.queryDetailNum", detail);
        //数据处理
        if(CollectionUtils.isEmpty(list)) { return true;}
        MpPurchaseOrderDetail orderDetail = list.get(0);
        if(orderDetail.getEnterNum() == null) { return true;}
        return MpUtils.doubleSub(detail.getNum(), orderDetail.getEnterNum()) >= 0;
    }

    /**
     * 判断订单是否完成入库
     * @Title: hasAllEnter
     * @param id id : 订单ID
     * @return boolean
     * @throws
     **/
    @Override
    public boolean hasAllEnter(String id) {
        int count = dao.count("MPLJ03.hasAllEnter", id);
        return count > 0 ? false : true;
    }

    /**
     * 判断入库数量是否大于可入库数量
     * @Title: hasEnoughEnterNum
     * @param detail detail : 订单明细对象
     * @return boolean
     * @throws
     **/
    @Override
    public boolean hasEnoughEnterNum(MpPurchaseOrderDetail detail) {
        //数据查询
        List<MpPurchaseOrderDetail> list = dao.query("MPLJ03.queryDetailNum", detail);
        MpPurchaseOrderDetail orderDetail = list.get(0);
        //数据处理
        if(orderDetail.getEnterNum() == null) {
            //订单数量 - 本次入库数量 >= 0
            return MpUtils.doubleSub(orderDetail.getNum(),detail.getCurEnterNum()) >= 0;
        } else {
            //可入库数量 = 订单数量 - 已入库数量
            Double remainEnterNum = MpUtils.doubleSub(orderDetail.getNum(),orderDetail.getEnterNum());
            //可入库数量 - 本次入库数量 >= 0
            return MpUtils.doubleSub(remainEnterNum, orderDetail.getEnterNum()) >= 0;
        }
    }

    /**
     * 更新是否已开票编辑
     * @Title: updateHasInvoice
     * @param details details : 发票明细集合
     * @param hasInvoice : 是否已开票
     * @return int
     * @throws
     **/
    @Override
    public int updateHasInvoice(List<MpInvoiceDetail> details, boolean hasInvoice) {
        int result = 0;
        for (MpInvoiceDetail detail : details) {
            result = result + dao.update(hasInvoice ? "MPLJ03.addHasInvoice" : "MPLJ03.removeHasInvoice", detail);
        }
        return result;
    }

}
