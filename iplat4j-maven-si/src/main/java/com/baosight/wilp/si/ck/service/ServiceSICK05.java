package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.SiConfigCache;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库仓库签字页面Service
 * @ClassName: ServiceSICK05
 * @Package com.baosight.wilp.si.ck.service
 * @date: 2023年04月24日 15:19
 */
public class ServiceSICK05 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        SiUtils.initQueryTime(inInfo, false);
        inInfo.set("workNo", UserSession.getLoginName());
        inInfo.set("name", UserSession.getLoginCName());
        EiInfo query = query(inInfo); query.addBlock("detail");
        return query;
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(EiConstant.queryBlock, 0, "isCheck", 0);
        return super.query(inInfo, "SICK01.query", new SiOut());
    }

    /**2
     * 查询出库明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        String outBillNo = inInfo.getCellStr(EiConstant.queryBlock, 0, "outBillNo");
        inInfo.setCell(EiConstant.queryBlock, 0, "outBillNo", inInfo.getString("mainOutBillNo"));
        EiInfo info = super.query(inInfo, "SICK0101.query", new SiOutDetail(), false, null, null, "detail", "detail");
        inInfo.setCell(EiConstant.queryBlock, 0, "outBillNo", outBillNo);
        return info;
    }

    /**
     * 签收
     * @Title: signOut
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStockSign(EiInfo inInfo) {
        List<String> list = SiUtils.toList(inInfo.get("list"), String.class);
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("请选择需要签字的出库单");
        }
        //获取审核人的签名图片地址
        String signature = "/si/showSign/" + inInfo.getString("signature");
        //更新入库单状态
        dao.update("SICK01.outStockSign", new HashMap(4){{
            put("signature", signature);
            put("list", list);
        }});

        String hasSign = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_OUT_STOCK_SIGN);
        /**是否开启仓库签字出库: 是: 仓库签字确认后出库(操作库存)**/
        String hasCheck = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_STORAGE_MANAGER_CHECK);
        if(SiUtils.toBoolean(hasSign) && SiUtils.toBoolean(hasCheck)) {
            //出库
            return SiUtils.invoke(null,"SICK0101", "outStorage", new String[]{"outBillNos"}, list);
        }
        return inInfo;
    }


    /**
     * 仓库结束出库签收
     * @Title: overOutStockSign
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo overOutStockSign(EiInfo inInfo) {
        List<String> list = SiUtils.toList(inInfo.get("list"), String.class);
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("请选择需要签字的出库单");
        }
        //获取审核人的签名图片地址
        String signature = "/si/showSign/" + inInfo.getString("signature");
        //更新入库单状态
        dao.update("SICK01.overOutStockSign", new HashMap(4){{
            put("signature", signature);
            put("list", list);
            put("overTime", DateUtils.curDateTimeStr19());
            put("overMan", UserSession.getLoginName());
        }});
        //更新领用单状态
        if(SiUtils.isExistModule("RM")){
            dao.update("SICK04.overClaimStatus", list);
        }
        return inInfo;
    }
}
