package com.baosight.wilp.si.bf.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.bf.domain.SiScrapDetail;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 物资批次选择页面Service
 * @ClassName: ServiceSIBF0103
 * @Package com.baosight.wilp.si.bf.service
 * @date: 2022年09月27日 15:38
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceSIBF0103 extends ServiceBase {

    /**
     * 页面数据加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
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
        inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
        inInfo.setCell(EiConstant.queryBlock, 0, "isNot0", "Y");
        return super.query(inInfo, "SIKC0101.query", new SiScrapDetail());
    }
}
