package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.si.common.SiConfigCache;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 入库验收审核页面Service
 * @ClassName: ServiceSIRK03
 * @Package com.baosight.wilp.si.rk.service
 * @date: 2022年11月18日 16:20
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.验收审核
 */
public class ServiceSIRK05 extends ServiceBase {

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
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
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
        inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(com.baosight.xservices.xs.util.UserSession.getUser().getUsername()));
        inInfo.setCell(EiConstant.queryBlock, 0, "isCheck", 1);
        return super.query(inInfo, "SIRK01.query");
    }

    /**
     * 查询入库明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        String enterBillNo = inInfo.getCellStr(EiConstant.queryBlock, 0, "enterBillNo");
        inInfo.setCell(EiConstant.queryBlock, 0, "enterBillNo", inInfo.getString("mainEnterBillNo"));
        EiInfo info = super.query(inInfo, "SIRK0101.query", new SiEnterDetail(), false, null, null, "detail", "detail");
        inInfo.setCell(EiConstant.queryBlock, 0, "enterBillNo", enterBillNo);
        return info;
    }

    /**
     * 入库验收审核
     * @Title: checkApproval
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo checkApproval(EiInfo inInfo) {
        //获取参数
        List<String> list = SiUtils.toList(inInfo.get("list"), String.class);
        String signature = "/si/showSign/" + inInfo.getString("signature");
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("请选择需要审核的入库单");
        }
        //校验是否已审核
        if(hasApproval(list)) { return inInfo; }

        /**是否开启验收入库：否: 修改状态**/
        String hasCheck = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_ENTER_CHECK);
        /**是否验收后入库：否: 进入库存**/
        String hasInStorage = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_CHECK_IN_STORAGE);
        //参数处理
        HashMap<String, Object> pMap = new HashMap<>(8);
        pMap.put("signature", signature);
        pMap.put("list", list);
        if(SiUtils.toBoolean(hasCheck) && !SiUtils.toBoolean(hasInStorage)) {
            pMap.put("billChecker", UserSession.getLoginName());
            pMap.put("billCheckerName", UserSession.getLoginCName());
            pMap.put("billCheckTime", DateUtils.curDateTimeStr19());
            pMap.put("enterTime", DateUtils.curDateTimeStr19());
        }
        //更新入库单状态,
        dao.update("SIRK01.approvalEnter", pMap);
        if(SiUtils.toBoolean(hasCheck) && !SiUtils.toBoolean(hasInStorage)) {
            //更新入库单明细中的入库时间和日期
            pMap.put("enterDate", DateUtils.curDateStr10());
            dao.update("SIRK0101.updateEnterDate", pMap);
            //物资入库
            return SiUtils.invoke(null, "SIRK0101", "enterStorage", new String[]{"enterBillNos"}, list);
        }
        return inInfo;
    }

    /**
     * 校验是否已审核
     * @Title: hasApproval
     * @param list list
     * @return boolean
     * @throws
     **/
    private boolean hasApproval(List<String> list) {
        List<Integer> query = dao.query("SIRK01.hasApproval", list);
        return CollectionUtils.isNotEmpty(query) && query.get(0) > 0;
    }

}
