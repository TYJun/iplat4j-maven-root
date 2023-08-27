package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 入库单打印Service
 * @ClassName: ServiceSIRK0105
 * @Package com.baosight.wilp.si.rk.service
 * @date: 2022年12月09日 16:58
 *
 * 1.页面加载
 * 2.获取入库打印的参数
 */
public class ServiceSIRK0105 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 获取入库打印的参数
     * @Title: queryEnterBill
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryEnterBill(EiInfo inInfo) {
        List<Map> list = dao.query("SIRK0105.printEnterBill", inInfo.getString("enterBillNo"));
        Map parameters = new HashMap(2);
        if(CollectionUtils.isEmpty(list)) {
            parameters.put("enterBillNo", inInfo.getString("enterBillNo"));
        } else {
            parameters = list.get(0);
            parameters.put("matClassify", "");
        }
        inInfo.set("parameters", parameters);
        return inInfo;
    }

}
