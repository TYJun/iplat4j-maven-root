package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.lj.domain.MpContInvoice;
import com.baosight.wilp.mp.lj.domain.MpInvoiceDetail;
import com.baosight.wilp.mp.lj.service.MpInvoiceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 合同发票Service实现
 * @ClassName: MpInvoiceServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年10月11日 14:40
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
@Service
public class MpInvoiceServiceImpl implements MpInvoiceService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 获取指定的发票信息
     * @Title: queryInvoice
     * @param id id : 发票ID
     * @return com.baosight.wilp.mp.lj.domain.MpContInvoice
     * @throws
     **/
    @Override
    public MpContInvoice queryInvoice(String id) {
        List<MpContInvoice> list = dao.query("MPLJ05.query", new HashMap() {{
            put("id", id);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增发票信息
     * @Title: insert
     * @param invoice invoice : 发票对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpContInvoice invoice) {
        dao.insert("MPLJ05.insert", invoice);
    }

    /**
     * 更新发票信息
     * @Title: update
     * @param invoice invoice : 发票对象
     * @return int
     * @throws
     **/
    @Override
    public int update(MpContInvoice invoice) {
        return dao.update("MPLJ05.update", invoice);
    }

    /**
     * 删除发票信息
     * @Title: delete
     * @param id id : 发票ID
     * @return int
     * @throws
     **/
    @Override
    public int delete(String id) {
        return dao.delete("MPLJ05.delete", id);
    }

    /**
     * 获取指定发票的明细信息
     * @Title: queryInvoiceDetailList
     * @param invoiceId invoiceId : 发票ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpInvoiceDetail>
     * @throws
     **/
    @Override
    public List<MpInvoiceDetail> queryInvoiceDetailList(String invoiceId) {
        return dao.query("MPLJ05.queryDetail", new HashMap() {{
            put("invoiceId", invoiceId);
        }});
    }

    /**
     * 批量新增发票明细信息
     * @Title: insertDetail
     * @param list list : 发票明细对象集合
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<MpInvoiceDetail> list) {
        dao.insert("MPLJ05.insertDetail", list);
    }

    /**
     * 根据发票ID删除发票明细信息
     * @Title: deleteDetail
     * @param invoiceId invoiceId  : 发票ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteDetail(String invoiceId) {
        return dao.delete("MPLJ05.deleteDetail", invoiceId);
    }

    /**
     * 清除付款信息
     * @Title: clearPayId
     * @param payId payId : 付款ID
     * @return void
     * @throws
     **/
    @Override
    public int clearPayId(String payId) {
        return dao.update("MPLJ05.clearPayId", payId);
    }

    /**
     * 根据付款ID更新发票状态
     * @Title: updateByPayId
     * @param payId payId : 付款ID
     * @return int
     * @throws
     **/
    @Override
    public int updateByPayId(String payId) {
        return dao.update("MPLJ05.updateByPayId", new HashMap(4) {{
            put("payId", payId);
            put("statusCode", MpConstant.INVOICE_STATUS_PAY);
            put("statusName", CommonUtils.getDataCodeItemName("wilp.mp.cont.invoiceStatus", MpConstant.INVOICE_STATUS_PAY));
        }});
    }

    /**
     * 向发票表中插入付款ID
     * @Title: addPayId
     * @param payId payId : 付款ID
     * @param list list : 发票ID集合
     * @return int
     * @throws
     **/
    @Override
    public int addPayId(String payId, List<String> list) {
        return dao.update("MPLJ05.addPayId", new HashMap(4){{
            put("payId", payId);
            put("list", list);
        }});
    }

    /**
     * 根据付款ID获取发票明细(合并相同物资)
     * @Title: queryInvoiceDetailByPayId
     * @param payId payId : 付款ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpInvoiceDetail>
     * @throws
     **/
    @Override
    public List<MpInvoiceDetail> queryInvoiceDetailByPayId(String payId) {
        return dao.query("MPLJ05.queryInvoiceDetailByPayId", payId);
    }

    /**
     * 根据付款ID获取发票信息
     * @Title: queryContInvoiceByPayId
     * @param payId payId : 付款ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContInvoice>
     * @throws
     **/
    @Override
    public List<MpContInvoice> queryContInvoiceByPayId(String payId) {
        return dao.query("MPLJ05.query", new HashMap(2) {{ put("payId", payId); }});
    }
}
