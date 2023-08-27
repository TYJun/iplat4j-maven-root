package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.domain.MpRequireRelation;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划汇总逻辑处理Service接口
 * @ClassName: MpRequireCollectService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年10月18日 11:43
 *
 * 1.根据ID获取需求汇总
 * 2.新增需求汇总
 * 3.更新需求汇总
 * 4.删除需求汇总
 * 5.获取指定需求汇总明细
 * 6.批量新增需求汇总明细
 * 7.批量新增需求汇总关联信息
 * 8.删除需求汇总明细
 * 9.根据汇总ID获取需求计划ID集合
 * 10.根据汇总ID获取需求计划明细ID集合
 */
public interface MpRequireCollectService {

    /**
     * 根据ID获取需求汇总
     * @Title: queryRequireCollect
     * @param id id
     * @return com.baosight.wilp.mp.lj.domain.MpRequireCollect
     * @throws
     **/
    MpRequireCollect queryRequireCollect(String id);

    /**
     * 新增需求汇总
     * @Title: insert
     * @param collect collect
     * @return void
     * @throws
     **/
    void insert(MpRequireCollect collect);

    /**
     * 更新需求汇总
     * @Title: update
     * @param collect collect
     * @return int
     * @throws
     **/
    int update(MpRequireCollect collect);

    /**
     * 删除需求汇总
     * @Title: delete
     * @param id id
     * @return int
     * @throws
     **/
    int delete(String id);

    /**
     * 获取指定需求汇总明细
     * @Title: queryDetailList
     * @param collectId collectId
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail>
     * @throws
     **/
    List<MpRequireCollectDetail> queryDetailList(String collectId);

    /**
     * 批量新增需求汇总明细
     * @Title: insertDetail
     * @param list list
     * @return void
     * @throws
     **/
    void insertDetail(List<MpRequireCollectDetail> list);

    /**
     * 删除需求汇总明细
     * @Title: deleteDetail
     * @param collectId collectId
     * @return int
     * @throws
     **/
    int deleteDetail(String collectId);

    /**
     * 批量新增需求汇总关联信息
     * @Title: insertRelation
     * @param list list
     * @return void
     * @throws
     **/
    void insertRelation(List<MpRequireRelation> list);

    /**
     * 删除需求汇总关联息
     * @Title: deleteRelation
     * @param collectId collectId
     * @return int
     * @throws
     **/
    int deleteRelation(String collectId);

    /**
     * 根据汇总ID获取需求计划ID集合
     * @Title: queryRequireIds
     * @param id id
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    List<String> queryRequireIds(String id);

    /**
     * 根据汇总ID获取需求计划明细ID集合
     * @Title: queryRequireDetailIds
     * @param id id
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    List<String> queryRequireDetailIds(String id);
}
