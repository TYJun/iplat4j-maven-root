package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购合同Service
 * @ClassName: MpContractService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年10月11日 14:37
 *
 * 1.根据ID查询采购合同信息
 * 2.新增采购合同信息
 * 3.更新采购合同信息
 * 4.删除采购合同信息
 * 5.根据合同ID查询合同明细信息
 * 6.批量新增采购合同明细信息
 * 6.新增合同明细
 * 6.更新合同明细
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
public interface MpContractService {

  /**
   * 根据ID查询采购合同信息
   * @Title: queryContract
   * @param id id : 合同ID
   * @return com.baosight.wilp.mp.lj.domain.MpContract
   * @throws
   **/
   MpContract queryContract(String id);

   /**
    * 根据合同号查询采购合同信息
    * @Title: queryContractByNo
    * @param contNo contNo : 合同号
    * @return com.baosight.wilp.mp.lj.domain.MpContract
    * @throws
    **/
   MpContract queryContractByNo(String contNo);

   /**
    * 新增采购合同信息
    * @Title: insert
    * @param contract contract : 合同对象
    * @return void
    * @throws
    **/
   void insert(MpContract contract);

   /**
    * 更新采购合同信息
    * @Title: update
    * @param contract contract : 合同对象
    * @return int
    * @throws
    **/
   int update(MpContract contract);

   /**
    * 删除采购合同信息
    * @Title: delete
    * @param id id : 合同ID
    * @return int
    * @throws
    **/
   int delete(String id);

   /**
    * 根据合同ID查询合同明细信息
    * @Title: queryContractDetailList
    * @param contId contId : 合同ID
    * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractDetail>
    * @throws
    **/
   List<MpContractDetail> queryContractDetailList(String contId);

   /**
    * 批量新增采购合同明细信息
    * @Title: insert
    * @param list list : 合同明细集合
    * @return void
    * @throws
    **/
   void insertDetail(List<MpContractDetail> list);

   /**
    * 新增合同明细
    * @Title: insertDetail
    * @param detail detail: 合同明细对象
    * @return void
    * @throws
    **/
   void insertDetail(MpContractDetail detail);

   /**
    * 更新合同明细
    * @Title: updateDetail
    * @param detail detail
    * @return void
    * @throws
    **/
   void updateDetail(MpContractDetail detail);

   /**
    * 根据合同ID删除合同明细
    * @Title: deleteDetail
    * @param contId contId : 合同ID
    * @return int
    * @throws
    **/
   int deleteDetail(String contId);

   /**
    * 根据合同ID查询合同附件信息
    * @Title: queryFileList
    * @param contId contId  : 合同ID
    * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractFile>
    * @throws
    **/
   List<MpContractFile> queryFileList(String contId);

   /**
    * 批量新增合同附件信息
    * @Title: insertContFile
    * @param list list : 合同附件集合
    * @return void
    * @throws
    **/
   void insertContFile(List<MpContractFile> list);

   /**
    * 根据合同ID删除合同附件
    * @Title: deleteContFile
    * @param contId contId : 合同ID
    * @return int
    * @throws
    **/
   int deleteContFile(String contId);

   /**
    * 获取合同树
    * @Title: queryContTree
    * @param supplierNum supplierNum : 供应商编码
    * @param deptNum deptNum : 管理科室编码
    * @param node node : 合同ID(节点ID)
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    * @throws
    **/
    List<Map<String, String>> queryContTree(String deptNum, String node, String supplierNum);

    /**
     * 获取合同明细
     * @Title: queryContDetail
     * @param detail detail : 合同明细对象
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractDetail>
     * @throws
     **/
    List<MpContractDetail> queryContDetail(MpContractDetail detail);

    /**
     * 减少以生成订单数量
     * @Title: reduceOrderNum
     * @param orderDetail orderDetail : 采购订单明细对象
     * @return int
     * @throws
     **/
    int reduceOrderNum(MpPurchaseOrderDetail orderDetail);

    /**
     * 增加以生成订单数量
     * @Title: addOrderNum
     * @param orderDetail orderDetail : 采购订单明细对象
     * @return int
     * @throws
     **/
    int addOrderNum(MpPurchaseOrderDetail orderDetail);

    /**
     * 校验订单数量是否满足
     * @Title: hasEnoughOrderNum
     * @param detail detail : 采购订单明细对象
     * @return boolean
     * @throws
     **/
    boolean hasEnoughOrderNum(MpPurchaseOrderDetail detail, String operateType);

    /**
     * 更新合同的入库数量
     * @Title: updateEnterNum
     * @param detail detail : 采购订单明细对象
     * @return int
     * @throws
     **/
    int updateEnterNum(MpPurchaseOrderDetail detail);

    /**
     * 修改合同的开票金额
     * @Title: updateInvoiceNum
     * @param detail detail : 发票明细对象
     * @return void
     * @throws
     **/
    int updateInvoiceNum(MpInvoiceDetail detail);

    /**
     * 更新合同的付款数量和金额
     * @Title: updatePayNum
     * @param details details : 发票明细集合
     * @return void
     * @throws
     **/
    int updatePayNum(List<MpInvoiceDetail> details);

    /**
     * 获取指定科室所有的合同的对接合同ID
     * @Title: queryDockContIds
     * @param deptNum deptNum : 管理科室编码
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    List<String> queryDockContIds(String deptNum);

    /**
     * 校验合同是否存在
     * @Title: contractIsExisted
     * @param contract contract : 合同对象
     * @return boolean
     * @throws
     **/
    boolean contractIsExisted(MpContract contract);

}
