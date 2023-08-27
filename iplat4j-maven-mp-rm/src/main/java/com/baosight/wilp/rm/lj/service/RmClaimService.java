package com.baosight.wilp.rm.lj.service;

import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用逻辑接口
 * @ClassName: RmClaimService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年08月31日 17:19
 * <p>
 * 1.查询领用单列表
 * 2.查询指定领用单
 * 3.新增领用单
 * 4.编辑领用单
 * 5.删除领用单
 * 6.查询领用单明细列表
 * 7.批量新增领用单明细
 * 8.编辑领用单明细
 * 9.删除领用单明细
 * 10.获取物资的预约量集合
 * 11.获取指定物资的预约量
 */
public interface RmClaimService {

    /**
     * 查询领用单列表
     *
     * @param claim  claim
     * @param offset offset
     * @param limit  limit
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaim>
     * @throws
     * @Title: queryClaimList
     **/
    List<RmClaim> queryClaimList(RmClaim claim, Integer offset, Integer limit);

    /**
     * 查询指定领用单
     *
     * @param id id
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     * @Title: queryClaimById
     **/
    RmClaim queryClaimById(String id);

    /**
     * 根据领用单号查询指定领用单
     *
     * @param claimNo claimNo
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     * @Title: queryClaimByClaimNo
     **/
    RmClaim queryClaimByClaimNo(String claimNo);

    /**
     * 查询领用单状态
     *
     * @param claimId claimId
     * @return java.lang.String
     * @throws
     * @Title: queryClaimStatusCode
     **/
    String queryClaimStatusCode(String claimId);

    /**
     * 新增领用单
     *
     * @param claim claim
     * @return void
     * @throws
     * @Title: insert
     **/
    void insert(RmClaim claim);

    /**
     * 编辑领用单
     *
     * @param claim claim
     * @return void
     * @throws
     * @Title: update
     **/
    void update(RmClaim claim);

    /**
     * 删除领用单
     *
     * @param id id
     * @return void
     * @throws
     * @Title: delete
     **/
    void delete(String id);

    /**
     * 查询领用单明细列表
     *
     * @param claimId claimId
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaimDetail>
     * @throws
     * @Title: queryClaimDetailList
     **/
    List<RmClaimDetail> queryClaimDetailList(String claimId);

    /**
     * 批量新增领用单明细
     *
     * @param detailList detailList
     * @return void
     * @throws
     * @Title: insertDetail
     **/
    void insertDetail(List<RmClaimDetail> detailList);

    /**
     * 编辑领用单明细
     *
     * @param detail detail
     * @return void
     * @throws
     * @Title: updateDetail
     **/
    void updateDetail(RmClaimDetail detail);

    /**
     * 删除领用单明细
     *
     * @param claimId claimId
     * @return void
     * @throws
     * @Title: deleteDetail
     **/
    void deleteDetail(String claimId);

    /**
     * 获取物资的预约量集合
     *
     * @param matNumList matNumList
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @throws
     * @Title: queryReserveNums
     **/
    List<Map<String, Object>> queryReserveNums(List<String> matNumList);

    /**
     * 获取指定物资的预约量
     *
     * @param matNum matNum
     * @return java.lang.Double
     * @throws
     * @Title: queryReserveNum
     **/
    Double queryReserveNum(String matNum);

    /**
     * 获取领用单流程
     *
     * @param claimNo claimNo
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @throws
     * @Title: queryClaimFlow
     **/
    List<Map<String, String>> queryClaimFlow(String claimNo);
}
