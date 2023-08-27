package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mp.lj.domain.*;
import com.baosight.wilp.mp.lj.service.MpContractHistoryService;
import com.baosight.wilp.mp.lj.service.MpContractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购合同操作履历Service实现
 * @ClassName: MpContractHistoryService
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年11月15日 17:33
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
@Service
public class MpContractHistoryServiceImpl implements MpContractHistoryService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    @Autowired
    private MpContractService contractService;

    /**
     * 根据合同ID获取合同履历信息
     * @Title: queryContractHistory
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistory>
     * @throws
     **/
    @Override
    public List<MpContractHistory> queryContractHistory(String contId) {
        return dao.query("MPLJ08.query", new HashMap(2) {{
            put("contId", contId);
        }});
    }

    /**
     * 保存合同履历
     * @Title: insert
     * @param history history : 合同履历对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpContractHistory history) {
        dao.insert("MPLJ08.insert", history);
    };

    /**
     * 根据履历ID获取明细履历信息
     * @Title: queryContractHistoryDetail
     * @param historyId historyId : 合同履历ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail>
     * @throws
     **/
    @Override
    public List<MpContractHistoryDetail> queryContractHistoryDetail(String historyId){
        return dao.query("MPLJ08.queryHistoryDetail", new HashMap(2) {{
            put("historyId", historyId);
        }});
    }

    /**
     * 根据合同ID获取明细履历信息
     * @Title: queryContractHistoryDetailByContId
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail>
     * @throws
     **/
    @Override
    public List<MpContractHistoryDetail> queryContractHistoryDetailByContId(String contId) {
        return dao.query("MPLJ08.queryHistoryDetail", new HashMap(2) {{
            put("contId", contId);
        }});
    }

    /**
     * 保存明细履历信息
     * @Title: insertHistoryDetail
     * @param list list : 合同明细履历对象集合
     * @return void
     * @throws
     **/
    @Override
    public void insertHistoryDetail(List<MpContractHistoryDetail> list) {
        dao.insert("MPLJ08.insertHistoryDetail", list);
    }

    /**
     * 根据履历ID获取附件履历信息
     * @Title: queryContractHistoryFile
     * @param historyId historyId : 合同履历ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryFile>
     * @throws
     **/
    @Override
    public List<MpContractHistoryFile> queryContractHistoryFile(String historyId) {
        return dao.query("MPLJ08.queryHistoryFile", new HashMap(2) {{
            put("historyId", historyId);
        }});
    }

    /**
     * 根据合同ID获取附件履历信息
     * @Title: queryContractHistoryFileByContId
     * @param contId contId : 合同ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpContractHistoryFile>
     * @throws
     **/
    @Override
    public List<MpContractHistoryFile> queryContractHistoryFileByContId(String contId) {
        return dao.query("MPLJ08.queryHistoryFile", new HashMap(2) {{
            put("contId", contId);
        }});
    }

    /**
     * 保存附件履历信息
     * @Title: insertHistoryFile
     * @param list list : 合同附件履历对象集合
     * @return void
     * @throws
     **/
    @Override
    public void insertHistoryFile(List<MpContractHistoryFile> list) {
        dao.insert("MPLJ08.insertHistoryFile", list);
    }

    /**
     * 保存合同生效履历
     * @Title: saveEffectHistory
     * @param contId contId : 合同ID
     * @param operateNo operateNo : 操作人工号
     * @param operateName operateName : 操作人姓名
     * @return void
     * @throws
     **/
    @Override
    public void saveEffectHistory(String contId, String operateNo, String operateName) {
        //构建并保存合同履历对象
        MpContract contract = contractService.queryContract(contId);
        MpContractHistory history = MpContractHistory.newInstance(contract.getId(), "合同生效");
        BeanUtils.copyProperties(contract,history);
        history.setOperateNo(operateNo);
        history.setOperateName(operateName);
        history.setId(UUID.randomUUID().toString());
        insert(history);
        //构建并保存合同明细履历对象
        List<MpContractDetail> detailList = contractService.queryContractDetailList(contId);
        List<MpContractHistoryDetail> details = new ArrayList<>(detailList.size());
        detailList.forEach(detail -> {
            MpContractHistoryDetail historyDetail = MpContractHistoryDetail.newEffectInstance(history.getId());
            BeanUtils.copyProperties(detail,historyDetail);
            historyDetail.setId(UUID.randomUUID().toString());
            historyDetail.setAfterPrice(detail.getPrice());
            historyDetail.setAfterNum(detail.getNum());
            historyDetail.setAfterTotalCost(detail.getTotalCost());
            details.add(historyDetail);
        });
        insertHistoryDetail(details);
        //构建并保存合同附件履历对象
        List<MpContractFile> fileList = contractService.queryFileList(contId);
        List<MpContractHistoryFile> files = new ArrayList<>(fileList.size());
        fileList.forEach(file -> {
            MpContractHistoryFile historyFile = MpContractHistoryFile.newInstance(history.getId(), 0);
            BeanUtils.copyProperties(file,historyFile);
            historyFile.setId(UUID.randomUUID().toString());
            files.add(historyFile);
        });
        insertHistoryFile(files);
    }

    /**
     * 保存合同变更来履历
     * @Title: saveChangeHistory
     * @param history history : 合同履历对象
     * @param historyDetails historyDetails : 合同明细履历集合
     * @param historyFiles historyFiles : 合同附件履历集合
     * @return void
     * @throws
     **/
    @Override
    public void saveChangeHistory(MpContractHistory history, List<MpContractHistoryDetail> historyDetails, List<MpContractHistoryFile> historyFiles) {
        insert(history);
        if(CollectionUtils.isNotEmpty(historyDetails)) {
            insertHistoryDetail(historyDetails);
        }
        if(CollectionUtils.isNotEmpty(historyFiles)) {
            insertHistoryFile(historyFiles);
        }
    }

    /**
     * 保存合同终止履历
     * @Title: saveTerminationHistory
     * @param contId contId : 合同ID
     * @param operateNo operateNo : 操作人工号
     * @param operateName operateName : 操作人姓名
     * @return void
     * @throws
     **/
    @Override
    public void saveTerminationHistory(String contId, String operateNo, String operateName) {
        //构建合同履历对象
        MpContract contract = contractService.queryContract(contId);
        MpContractHistory history = MpContractHistory.newInstance(contract.getId(), "合同终止");
        BeanUtils.copyProperties(contract,history);
        history.setOperateNo(operateNo);
        history.setOperateName(operateName);
        history.setId(UUID.randomUUID().toString());
        //保存合同履历
        insert(history);
    }
}
