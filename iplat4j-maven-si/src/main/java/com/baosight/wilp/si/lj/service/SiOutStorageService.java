package com.baosight.wilp.si.lj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库操作Service
 * @ClassName: SiOutStorageService
 * @Package com.baosight.wilp.si.lj.service
 * @date: 2023年10月19日 11:16
 */
public interface SiOutStorageService {

    /**
     * 出库操作
     * @Title: outStock
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStock(SiOut out, List<SiOutDetail> detailList);

    /**
     * 直入直出出库操作
     * @Title: outStockByZrZc
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStockByZrZc(SiOut out, List<SiOutDetail> detailList);

    /**
     * 通用出库操作(领用、盘亏、维修)
     * @Title: outStockByCommon
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStockByCommon(SiOut out, List<SiOutDetail> detailList);

    /**
     * 调拨出库操作
     * @Title: outStockByTransfer
     * @param out out
     * @param detailList detailList
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStockByTransfer(SiOut out, List<SiOutDetail> detailList);

    /**
     * 红冲出库(退库)
     * @Title: outStockByRedRush
     * @param out out
     * @param detailList detailList
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStockByRedRush(SiOut out, List<SiOutDetail> detailList);

    /**
     * 报废出库
     * @Title: outStockByScrap
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    EiInfo outStockByScrap(SiOut out, List<SiOutDetail> detailList);
}
