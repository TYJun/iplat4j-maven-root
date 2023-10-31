package com.baosight.wilp.si.lj.service;

import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库单Service
 * @ClassName: SiOutService
 * @Package com.baosight.wilp.si.lj.service
 * @date: 2023年10月19日 11:14
 */
public interface SiOutService {

    /**
     * 新增出库主单据
     * @Title: insert
     * @param out out 出库单
     * @return void
     * @throws
     **/
    void insert(SiOut out);

    /**
     * 新增出库明细
     * @Title: insertDetail
     * @param detailList detailList 出库明细集合
     * @return void
     * @throws
     **/
    void insertDetail(List<SiOutDetail> detailList);
}
