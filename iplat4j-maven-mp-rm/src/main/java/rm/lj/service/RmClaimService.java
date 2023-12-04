package rm.lj.service;

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
 *
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
     * @Title: queryClaimList
     * @param claim claim
     * @param offset offset
     * @param limit limit
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaim>
     * @throws
     **/
    List<RmClaim> queryClaimList(RmClaim claim, Integer offset, Integer limit);

    /**
     * 查询指定领用单
     * @Title: queryClaimById
     * @param id id
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     **/
    RmClaim queryClaimById(String id);

    /**
     * 根据领用单号查询指定领用单
     * @Title: queryClaimByClaimNo
     * @param claimNo claimNo
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     **/
    RmClaim queryClaimByClaimNo(String claimNo);

    /**
     * 查询领用单状态
     * @Title: queryClaimStatusCode
     * @param claimId claimId
     * @return java.lang.String
     * @throws
     **/
    String queryClaimStatusCode(String claimId);

    /**
     * 新增领用单
     * @Title: insert
     * @param claim claim
     * @return void
     * @throws
     **/
    void insert(RmClaim claim);

    /**
     * 编辑领用单
     * @Title: update
     * @param claim claim
     * @return void
     * @throws
     **/
    void update (RmClaim claim);

    /**
     * 删除领用单
     * @Title: delete
     * @param id id
     * @return void
     * @throws
     **/
    void delete(String id);

    /**
     * 查询领用单明细列表
     * @Title: queryClaimDetailList
     * @param claimId claimId
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaimDetail>
     * @throws
     **/
    List<RmClaimDetail> queryClaimDetailList(String claimId);

    /**
     * 获取待出库的领用单明细列表
     * @Title: queryOutClaimDetailList
     * @param claimId claimId
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaimDetail>
     * @throws
     **/
    List<RmClaimDetail> queryOutClaimDetailList(String claimId);

    /**
     * 批量新增领用单明细
     * @Title: insertDetail
     * @param detailList detailList
     * @return void
     * @throws
     **/
    void insertDetail(List<RmClaimDetail> detailList);

    /**
     * 编辑领用单明细
     * @Title: updateDetail
     * @param detail detail
     * @return void
     * @throws
     **/
    void updateDetail(RmClaimDetail detail);

    /**
     * 删除领用单明细
     * @Title: deleteDetail
     * @param claimId claimId
     * @return void
     * @throws
     **/
    void deleteDetail(String claimId);

    /**
     * 获取物资的预约量集合
     * @Title: queryReserveNums
     * @param matNumList matNumList
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    List<Map<String, Object>> queryReserveNums(List<String> matNumList);

    /**
     * 获取指定物资的预约量
     * @Title: queryReserveNum
     * @param matNum matNum
     * @return java.lang.Double
     * @throws
     **/
    Double queryReserveNum(String matNum);

    /**
     * 获取领用单流程
     * @Title: queryClaimFlow
     * @param claimNo claimNo
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    List<Map<String, String>> queryClaimFlow(String claimNo);

    /**
     * 根据领用单ID和物资编码集合查询指定领用明细
     * @Title: queryPartClaimDetailList
     * @param claimId claimId 领用ID
     * @param matNums matNums 物资编码集合
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaimDetail>
     * @throws
     **/
    List<RmClaimDetail> queryPartClaimDetailList(String claimId, List<String> matNums);

    /**
     * 领用明细驳回
     * @Title: rejectDetail
     * @param claimId claimId 领用ID
     * @param list list 领用明细集合
     * @return void
     * @throws
     **/
    void rejectDetail(String claimId, List<RmClaimDetail> list);

    /**
     * 构建驳回领用单号
     * @Title: getRejectClaimNo
     * @param claimNo claimNo 领用单号
     * @return java.lang.String
     * @throws
     **/
    String getRejectClaimNo(String claimNo);
}
