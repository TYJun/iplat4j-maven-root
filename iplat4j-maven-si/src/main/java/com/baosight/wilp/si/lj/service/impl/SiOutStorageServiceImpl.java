package com.baosight.wilp.si.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.lj.service.SiOutService;
import com.baosight.wilp.si.lj.service.SiOutStorageService;
import com.baosight.wilp.si.lj.service.SiStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库操作Service实现
 * @ClassName: SiOutStorageServiceImpl
 * @Package com.baosight.wilp.si.lj.service.impl
 * @date: 2023年10月19日 11:17
 */
@Service("siOutStorageService")
public class SiOutStorageServiceImpl implements SiOutStorageService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    @Autowired
    private SiOutService siOutService;

    @Autowired
    private SiStorageService siStorageService;

    /**
     * 出库操作
     * @Title: outStock
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStock(SiOut out, List<SiOutDetail> detailList) {
        /**校验出库仓库是否冻结**/
        if(wareHouseIsFreeze(out.getWareHouseNo())) {
            return ValidatorUtils.errorInfo("仓库不存在或已被冻结");
        }
        /**保存出库单和出库明细**/
        siOutService.insert(out);
        siOutService.insertDetail(detailList);
        /**库存操作**/
        EiInfo outInfo = null;
        switch (out.getOutType()) {
            case SiConstant.OUT_TYPE_ZRZC:
                outInfo = outStockByZrZc(out, detailList); break;
            case SiConstant.OUT_TYPE_DB:
                outInfo = outStockByTransfer(out, detailList); break;
            case SiConstant.OUT_TYPE_HC:
                outInfo = outStockByRedRush(out, detailList); break;
            case SiConstant.OUT_TYPE_BF:
                outInfo = outStockByScrap(out, detailList); break;
            default:
                outInfo = outStockByCommon(out, detailList);
        }
        return outInfo;
    }

    /**
     * 直入直出出库操作
     * @Title: outStockByZrZc
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStockByZrZc(SiOut out, List<SiOutDetail> detailList) {
        return null;
    }

    /**
     * 通用出库操作(领用、盘亏、维修)
     * @Title: outStockByCommon
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStockByCommon(SiOut out, List<SiOutDetail> detailList) {
        return null;
    }

    /**
     * 调拨出库操作
     * @Title: outStockByTransfer
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStockByTransfer(SiOut out, List<SiOutDetail> detailList) {
        return null;
    }

    /**
     * 红冲出库(退库)
     * @Title: outStockByRedRush
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStockByRedRush(SiOut out, List<SiOutDetail> detailList) {
        return null;
    }

    /**
     * 报废出库
     * @Title: outStockByRedRush
     * @param out out 出库单
     * @param detailList detailList 出库明细
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo outStockByScrap(SiOut out, List<SiOutDetail> detailList) {
        return null;
    }

    /**
     * 判断合同是否冻结
     * @Title: wareHouseIsFreeze
     * @param wareHouseNo wareHouseNo 合同号
     * @return boolean
     * @throws
     **/
    private boolean wareHouseIsFreeze(String wareHouseNo) {
        EiInfo outInfo = SiUtils.invoke(null, "SIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, wareHouseNo);
        return SiUtils.toBoolean(outInfo.getString("isCheck"));
    }
}
