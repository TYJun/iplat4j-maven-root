package com.baosight.wilp.mp.pz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.pz.domain.MpPurchaseTypeConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购份额配置页面Service
 * @ClassName: ServiceMPPZ03
 * @Package com.baosight.wilp.mp.pz.service
 * @date: 2022年08月25日 14:01
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除数据
 */
public class ServiceMPPZ03 extends ServiceBase {

    /**
     * 页面加载
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
     *      deptName: 科室名称
     *      matTypeName: 物资分类名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      id : 主键
     *      deptNum : 科室编码
     *      deptName : 科室名称
     *      matTypeId : 物资分类ID
     *      matTypeName : 物资分类名称
     *      remark : 备注
     *      dataGroupCode : 账套
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "dataGroupCode",
                BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        return super.query(inInfo, "MPPZ03.query", new MpPurchaseTypeConfig());
    }

    /**
     * 数据删除
     * <p>根据id删除指定数据</p>
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        dao.delete("MPPZ03.delete", inInfo.getString("id"));
        return inInfo;
    }

    /**
     * 获取本年采购类型配置
     * @Title: queryConfigs
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryConfigs(EiInfo inInfo) {

        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId,"S_ED_02");
        eiInfo.set("codeset","wilp.mp.purchase.purchaseType");
        //服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<HashMap<String,Object>> list = (List<HashMap<String, Object>>) outInfo.get("list");

        HashMap<String, Object> map = new HashMap<>();
        map.put("purchaseYear", DateUtils.curDateStr("yyyy"));

        for (HashMap<String, Object> type : list) {
            map.put("purchaseType", type.get("value"));
            List<MpPurchaseTypeConfig> query = dao.query("MPPZ03.query", map);
            if(CollectionUtils.isEmpty(query)){
                type.put("purchaseTypeCost", type.get("label")+"-0元");
            } else {
                type.put("purchaseTypeCost", type.get("label")+ "-" + query.get(0).getPurchaseCost() +"元");
            }

        }

        inInfo.addBlock("purchaseType").addRows(list);
        return inInfo;
    }
}
