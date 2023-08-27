package com.baosight.wilp.rm.lj.service;

import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资退库逻辑Service接口
 * @ClassName: RmBackOutService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年08月31日 17:29
 *
 * 1.获取指定的退库申请
 * 2.新增退库申请
 * 3.更新退库申请
 * 4.删除退库申请
 * 5.根据退库申请ID查询明细
 * 6.批量新增退库申请明细
 * 7.更新退库申请明细
 * 8.删除退库申请明细
 * 9.退库单是否已完成退库
 */
public interface RmBackOutService {

    /**
     * 获取指定的退库申请
     * @Title: queryBackOut
     * @param id id
     * @return com.baosight.wilp.rm.lj.domain.RmBackOut
     * @throws
     **/
    RmBackOut queryBackOut(String id);

    /**
     * 新增退库申请
     * @Title: insert
     * @param backOut backOut
     * @return void
     * @throws
     **/
    void insert(RmBackOut backOut);

    /**
     * 更新退库申请
     * @Title: update
     * @param backOut backOut
     * @return int
     * @throws
     **/
    int update(RmBackOut backOut);

    /**
     * 删除退库申请
     * @Title: delete
     * @param id id
     * @return int
     * @throws
     **/
    int delete(String id);

    /**
     * 根据退库申请ID查询明细
     * @Title: queryBackOutDetailList
     * @param backOutId backOutId
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmBackOutDetail>
     * @throws
     **/
    List<RmBackOutDetail> queryBackOutDetailList(String backOutId);

    /**
     * 批量新增退库申请明细
     * @Title: insertDetail
     * @param list list
     * @return void
     * @throws
     **/
    void insertDetail(List<RmBackOutDetail> list);

    /**
     * 更新退库申请明细
     * @Title: updateDetail
     * @param detail detail
     * @return int
     * @throws
     **/
    int updateDetail(RmBackOutDetail detail);

    /**
     * 删除退库申请明细
     * @Title: deleteDetail
     * @param backOutId backOutId
     * @return int
     * @throws
     **/
    int deleteDetail(String backOutId);

    /**
     * 退库单是否完成退库
     * @Title: hasAllBackOut
     * @param id id
     * @return void
     * @throws
     **/
    boolean hasAllBackOut(String id);

    /**
     * 根据领用单号获取对应退库明细数量
     * @Title: queryBackOutDetailListByClaimNo
     * @param claimNo claimNo : 领用单号
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    List<Map<String, Object>> queryBackOutDetailListByClaimNo(String claimNo);
}
