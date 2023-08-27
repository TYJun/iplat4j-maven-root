package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 新红冲入库子页面Service
 * @ClassName: ServiceSIRK0108
 * @Package com.baosight.wilp.si.rk.service
 * @date: 2023年04月28日 15:45
 */
public class ServiceSIRK0108 extends ServiceBase {

    private static final String OPERATE_NAME = "type";
    private static final String OPERATE_VALUE = "edit";

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取入库单
        List<SiEnter> enterList = dao.query("SIRK01.query", new HashMap(2) {{
            put("enterBillNoEQ", inInfo.getString("enterBillNo"));
        }});
        inInfo.setRows("redRush", enterList);
        inInfo.setCell(EiConstant.queryBlock, 0, "enterBillNo", inInfo.getString("originBillNo"));
        //编辑、回显明细信息
        if(OPERATE_VALUE.equals(inInfo.getString(OPERATE_NAME))) {
            //获取入库明细
            List<Map> list = dao.query("SIRK0108.query", inInfo.getString("enterBillNo"));
            inInfo.setRows(EiConstant.resultBlock, list);
        }
        return inInfo;
    }

    /**
     * 数据查询
     * @Title: queryOriginEnter
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryOriginEnter(EiInfo inInfo) {
        inInfo.setCell(EiConstant.queryBlock, 0, "showRedRush", "Y");
        return super.query(inInfo,"SIRK0101.query", new SiEnterDetail(), false, null, null, "mat", "mat");
    }
}
