package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.MpContractHistory;
import com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail;
import com.baosight.wilp.mp.lj.domain.MpContractHistoryFile;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购合同操作履历Service接口
 * @ClassName: MpContractHistoryService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年11月15日 17:32
 *
 * 1.根据合同ID获取合同履历信息
 * 2.保存合同履历
 * 3.根据履历ID获取明细履历信息
 * 4.根据合同ID获取明细履历信息
 * 5.保存明细履历信息
 * 6.根据履历ID获取附件履历信息
 * 7.根据合同ID获取附件履历信息
 * 8.保存附件履历信息
 * 9.保存生效履历
 * 10.保存变更履历
 * 11.保存终止履历
 */
public interface MpContractHistoryService {

    /**
     * 根据合同ID获取合同履历信息
     * @Title: queryContractHistory
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistory>
     * @throws
     **/
    List<MpContractHistory> queryContractHistory(String contId);

    /**
     * 保存合同履历
     * @Title: insert
     * @param history history : 合同履历对象
     * @return void
     * @throws
     **/
    void insert(MpContractHistory history);

    /**
     * 根据履历ID获取明细履历信息
     * @Title: queryContractHistoryDetail
     * @param historyId historyId : 合同履历ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail>
     * @throws
     **/
    List<MpContractHistoryDetail> queryContractHistoryDetail(String historyId);

    /**
     * 根据合同ID获取明细履历信息
     * @Title: queryContractHistoryDetailByContId
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail>
     * @throws
     **/
    List<MpContractHistoryDetail> queryContractHistoryDetailByContId(String contId);

    /**
     * 保存明细履历信息
     * @Title: insertHistoryDetail
     * @param list list : 合同明细履历对象集合
     * @return void
     * @throws
     **/
    void insertHistoryDetail(List<MpContractHistoryDetail> list);

    /**
     * 根据履历ID获取附件履历信息
     * @Title: queryContractHistoryFile
     * @param historyId historyId : 合同履历ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryFile>
     * @throws
     **/
    List<MpContractHistoryFile> queryContractHistoryFile(String historyId);

    /**
     * 根据合同ID获取附件履历信息
     * @Title: queryContractHistoryFileByContId
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryFile>
     * @throws
     **/
    List<MpContractHistoryFile> queryContractHistoryFileByContId(String contId);

    /**
     * 保存附件履历信息
     * @Title: insertHistoryFile
     * @param list list : 合同附件履历对象集合
     * @return void
     * @throws
     **/
    void insertHistoryFile(List<MpContractHistoryFile> list);

    /**
     * 保存合同生效履历
     * @Title: saveEffectHistory
     * @param contId contId : 合同ID
     * @param operateNo operateNo : 操作人工号
     * @param operateName operateName : 操作人姓名
     * @return void
     * @throws
     **/
    @Async(value = "taskExecutor")
    void saveEffectHistory(String contId, String operateNo, String operateName);

   /**
    * 保存合同变更来履历
    * @Title: saveChangeHistory
    * @param history history : 合同履历对象
    * @param historyDetails historyDetails : 合同明细履历集合
    * @param historyFiles historyFiles : 合同附件履历集合
    * @return void
    * @throws
    **/
    @Async(value = "taskExecutor")
    void saveChangeHistory(MpContractHistory history, List<MpContractHistoryDetail> historyDetails, List<MpContractHistoryFile> historyFiles);

    /**
     * 保存合同终止履历
     * @Title: saveTerminationHistory
     * @param contId contId : 合同ID
     * @param operateNo operateNo : 操作人工号
     * @param operateName operateName : 操作人姓名
     * @return void
     * @throws
     **/
    @Async(value = "taskExecutor")
    void saveTerminationHistory(String contId, String operateNo, String operateName);

   }
