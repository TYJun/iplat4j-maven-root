package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.*;
import com.baosight.wilp.mp.lj.service.MpContractService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购合同Service实现
 * @ClassName: MpContractServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年10月11日 14:39
 *
 * 1.根据ID查询采购合同信息
 * 2.新增采购合同信息
 * 3.更新采购合同信息
 * 4.删除采购合同信息
 * 5.根据合同ID查询合同明细信息
 * 6.批量新增采购合同明细信息
 * 7.根据合同ID删除合同明细
 * 8.根据合同ID查询合同附件信息
 * 9.批量新增合同附件信息
 * 10.根据合同ID删除合同附件
 * 11.获取合同树
 * 12.获取合同明细
 * 13.减少以生成订单数量
 * 14.增加以生成订单数量
 * 15.校验订单数量是否满足
 * 16.更新合同的入库数量和金额
 * 17.更新合同的开票数量和金额
 * 18.更新合同的付款数量和金额
 * 19.获取指定科室所有的合同的对接合同ID
 * 20.判断合同是否已存在
 */
@Service
public class MpContractServiceImpl implements MpContractService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 根据ID查询采购合同信息
     * @Title: queryContract
     * @param id id : 合同ID
     * @return com.baosight.wilp.mp.lj.domain.MpContract
     * @throws
     **/
    @Override
    public MpContract queryContract(String id) {
        List<MpContract> list = dao.query("MPLJ02.query", new HashMap(2){{put("id", id); }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据合同号查询采购合同信息
     * @Title: queryContractByNo
     * @param contNo contNo : 合同号
     * @return com.baosight.wilp.mp.lj.domain.MpContract
     * @throws
     **/
    @Override
    public MpContract queryContractByNo(String contNo) {
        List<MpContract> list = dao.query("MPLJ02.query", new HashMap(2){{put("contNoEQ", contNo); }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增采购合同信息
     * @Title: insert
     * @param contract contract : 合同信息对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpContract contract) {
        dao.insert("MPLJ02.insert", contract);
    }

    /**
     * 更新采购合同信息
     * @Title: update
     * @param contract contract : 合同信息对象
     * @return int
     * @throws
     **/
    @Override
    public int update(MpContract contract) {
        return dao.update("MPLJ02.update", contract);
    }

    /**
     * 删除采购合同信息
     * @Title: delete
     * @param id id : 合同ID
     * @return int
     * @throws
     **/
    @Override
    public int delete(String id) {
        return dao.delete("MPLJ02.delete", id);
    }

    /**
     * 根据合同ID查询合同明细信息
     * @Title: queryContractDetailList
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractDetail>
     * @throws
     **/
    @Override
    public List<MpContractDetail> queryContractDetailList(String contId) {
        return dao.query("MPLJ02.queryDetail", new HashMap(2){{ put("contId", contId); }});
    }

    /**
     * 批量新增采购合同明细信息
     * @Title: insert
     * @param list list : 合同明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<MpContractDetail> list) {
        dao.insert("MPLJ02.insertDetail", list);
    }

    /**
     * 新增合同明细
     * @Title: insertDetail
     * @param detail detail
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(MpContractDetail detail) {
        insertDetail(new ArrayList(2){{add(detail);}});
    }

    /**
     * 更新合同明细
     * @Title: updateDetail
     * @param detail detail
     * @return void
     * @throws
     **/
    @Override
    public void updateDetail(MpContractDetail detail) {
        dao.update("MPLJ02.updateDetail", detail);
    }

    /**
     * 根据合同ID删除合同明细
     * @Title: deleteDetail
     * @param contId contId  : 合同ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteDetail(String contId) {
        return dao.delete("MPLJ02.deleteDetail", contId);
    }

    /**
     * 根据合同ID查询合同附件信息
     * @Title: queryFileList
     * @param contId contId  : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractFile>
     * @throws
     **/
    @Override
    public List<MpContractFile> queryFileList(String contId) {
        return dao.query("MPLJ02.queryFile", new HashMap(2) {{ put("contId", contId); }});
    }

    /**
     * 批量新增合同附件信息
     * @Title: insertContFile
     * @param list list : 合同附件集合
     * @return void
     * @throws
     **/
    @Override
    public void insertContFile(List<MpContractFile> list) {
        dao.insert("MPLJ02.insertFile", list);
    }

    /**
     * 根据合同ID删除合同附件
     * @Title: deleteContFile
     * @param contId contId  : 合同ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteContFile(String contId) {
        return dao.delete("MPLJ02.deleteFile", contId);
    }

    /**
     * 获取合同树
     * @Title: queryContTree
     * @param supplierNum supplierNum : 供应商编码
     * @param deptNum deptNum : 管理科室编码
     * @param node node : 合同ID(节点ID)
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    @Override
    public List<Map<String, String>> queryContTree(String deptNum, String node, String supplierNum) {
        return dao.query("MPLJ02.queryContTree", new HashMap(4) {{
            put("deptNum", deptNum);
            put("contId", node);
            put("supplierNum", supplierNum);
        }});
    }

    /**
     * 获取合同明细
     * @Title: queryContDetail
     * @param orderDetail orderDetail : 采购订单明细
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractDetail>
     * @throws
     **/
    @Override
    public List<MpContractDetail> queryContDetail(MpContractDetail orderDetail) {
        return dao.query("MPLJ02.queryDetail", orderDetail.toMap());
    }

    /**
     * 减少已生成订单数量
     * @Title: reduceOrderNum
     * @param orderDetail orderDetail : 采购订单明细
     * @return int
     * @throws
     **/
    @Override
    public int reduceOrderNum(MpPurchaseOrderDetail orderDetail) {
        return dao.update("MPLJ02.reduceOrderNum", orderDetail);
    }

    /**
     * 增加已生成订单数量
     * @Title: addOrderNum
     * @param orderDetail orderDetail : 采购订单明细
     * @return int
     * @throws
     **/
    @Override
    public int addOrderNum(MpPurchaseOrderDetail orderDetail) {
        return dao.update("MPLJ02.addOrderNum", orderDetail);
    }

    /**
     * 校验订单数量是否满足
     * @Title: hasEnoughOrderNum
     * @param detail detail : 采购订单明细
     * @return boolean
     * @throws
     **/
    @Override
    public boolean hasEnoughOrderNum(MpPurchaseOrderDetail detail, String operateType) {
        //查询合同明细
        List<MpContractDetail> list = dao.query("MPLJ02.queryContDetailNum", detail.toMap());
        //处理
        if(CollectionUtils.isEmpty(list)) { return false; }
        MpContractDetail contractDetail = list.get(0);
        if(contractDetail.getOrderNum() == null) {
            return MpUtils.doubleSub(contractDetail.getNum(), detail.getNum()) >= 0;
        }
        Double remainOrderNum = MpConstant.OPERATE_TYPE_ADD.equals(operateType) ?
                MpUtils.doubleSub(contractDetail.getNum(), contractDetail.getOrderNum()) : contractDetail.getNum();
        return MpUtils.doubleSub(remainOrderNum, detail.getNum()) >= 0;
    }

    /**
     * 更新已入库数量
     * @Title: updateEnterNum
     * @param detail detail : 采购订单明细
     * @return int
     * @throws
     **/
    @Override
    public int updateEnterNum(MpPurchaseOrderDetail detail) {
        return dao.update("MPLJ02.updateEnterNum", detail);
    }

    /**
     * 修改合同的开票金额
     * @Title: updateInvoiceNum
     * @param detail detail : 发票明细对象
     * @return void
     * @throws
     **/
    @Override
    public int updateInvoiceNum(MpInvoiceDetail detail) {
        return dao.update("MPLJ02.updateInvoiceNum", detail);
    }

    /**
     * 更新合同的付款数量和金额
     * @Title: updatePayNum
     * @param details details: 发票明细对象集合
     * @return int
     * @throws
     **/
    @Override
    public int updatePayNum(List<MpInvoiceDetail> details) {
        AtomicInteger result  = new AtomicInteger();
        details.forEach(detail -> result.set(dao.update("MPLJ02.updatePayNum", detail)));
        return result.get();
    }

    /**
     * 获取指定科室所有的合同的对接合同ID
     * @Title: queryDockContIds
     * @param deptNum deptNum : 管理科室编码
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    @Override
    public List<String> queryDockContIds(String deptNum) {
        List<String> list = dao.query("MPLJ02.queryDockContIds", deptNum);
        return list.stream().filter(dockContId -> StringUtils.isNotBlank(dockContId)).collect(Collectors.toList());
    }

    /**
     * 判断合同是否已存在
     * @Title: contractIsExisted
     * @param contract contract
     * @return boolean
     * @throws
     **/
    @Override
    public boolean contractIsExisted(MpContract contract) {
        int count = dao.count("MPLJ02.isExistedContract", contract);
        return count > 0;
    }

}
