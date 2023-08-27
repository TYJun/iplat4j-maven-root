package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库单打印
 * @ClassName: ServiceSICK0104
 * @Package com.baosight.wilp.si.ck.service
 * @date: 2022年12月09日 17:07
 *
 * 1.页面加载
 * 2.查询打印出库单的参数
 */
public class ServiceSICK0104 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 查询打印出库单的参数
     * @Title: queryOutBill
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryOutBill(EiInfo inInfo) {
        List<Map> list = dao.query("SICK0104.printOutBill", inInfo.getString("outBillNo"));
        Map parameters = new HashMap(2);
        if(CollectionUtils.isEmpty(list)) {
            parameters.put("outBillNo", inInfo.getString("outBillNo"));
        } else {
            parameters = list.get(0);
            parameters.put("matClassify", "");
        }
        inInfo.set("parameters", parameters);
        return inInfo;
    }
}
