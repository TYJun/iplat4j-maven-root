package com.baosight.wilp.mp.lj.service;

import com.baosight.wilp.mp.lj.domain.MpContPay;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 合同付款业务service接口
 * @ClassName: MpContPayService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2022年10月28日 9:33
 *
 * 1.根据ID查询付款信息
 * 2.新增付款信息
 * 3.更新付款信息
 * 4.删除付款信息
 */
public interface MpContPayService {

    /**
     * 根据ID获取付款信息
     * @Title: queryContPay
     * @param id id
     * @return com.baosight.wilp.mp.lj.domain.MpContPay
     * @throws
     **/
    MpContPay queryContPay(String id);

    /**
     * 新增付款信息
     * @Title: insert
     * @param pay pay
     * @return void
     * @throws
     **/
    void insert(MpContPay pay);

    /**
     * 更新付款信息
     * @Title: update
     * @param pay pay
     * @return int
     * @throws
     **/
    int update(MpContPay pay);

    /**
     * 删除付款信息
     * @Title: delete
     * @param id id
     * @return int
     * @throws
     **/
    int delete(String id);

}
