package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.BeanExchangeUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 入库单编辑页面Service
 * @ClassName: ServiceSIRK0107
 * @Package com.baosight.wilp.si.rk.service
 * @date: 2023年04月28日 15:44
 */
public class ServiceSIRK0107 extends ServiceBase {

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
        inInfo.setRows(EiConstant.queryBlock, enterList);
        //获取入库明细
        List<SiEnterDetail> list = dao.query("SIRK0101.query", new HashMap(2) {{
            put("enterBillNo", inInfo.getString("enterBillNo"));
        }});
        inInfo.setRows(EiConstant.resultBlock, list);
        return inInfo;
    }

    /**
     * 编辑入库单
     * @Title: editEnter
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo editEnter(EiInfo inInfo) {
        /**1.参数处理**/
        SiEnter enter = new SiEnter();
        enter.fromMap(inInfo.getAttr());
        enter.setRecRevisor(UserSession.getLoginName());
        enter.setRecReviseTime(DateUtils.curDateTimeStr19());
        enter.setEnterTypeName(CommonUtils.getDataCodeItemName("wilp.si.enterType", enter.getEnterType()));
        //入库明细处理
        List<SiEnterDetail> enterDetailList = new ArrayList<>();
        List<Map<String, String>> rows  = (List<Map<String, String>>) inInfo.get("rows");
        for (Map<String, String> pMap : rows) {
            //过滤数量为0的
            if(NumberUtils.toDouble(pMap.get("enterNum"), 0d) == 0) { continue;}
            //数据处理
            SiEnterDetail detail = BeanExchangeUtils.mapToEnterDetail(pMap, enter);
            detail.setSurpNum(inInfo.getString("supplierNum"));
            detail.setSurpName(inInfo.getString("supplierName"));
            enterDetailList.add(detail);
        }
        /**2.数据更新**/
        dao.update("SIRK01.update", enter);
        dao.delete("SIRK0101.delete", enter.getEnterBillNo());
        dao.insert("SIRK0101.insert", enterDetailList);
        return inInfo;
    }

}
