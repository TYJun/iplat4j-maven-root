package com.baosight.wilp.mp.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpDbUtils;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.service.MpContractService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 对接第三方系统(熙软)接口Sevice
 * @ClassName: ServiceMPJK04
 * @Package com.baosight.wilp.mp.jk.service
 * @date: 2022年11月04日 17:28
 *
 * 1.获取合同视图数据
 */
public class ServiceMPJK04 extends ServiceBase {

    @Autowired
    private MpContractService contractService;

    static ThreadLocal<StringBuilder> threadLocal = ThreadLocal.withInitial(() -> {
        return new StringBuilder("select ")
                .append("cce_id dockContId,")
                .append("cce_code contNo,")
                .append("cce_name contName,")
                .append("cce_sorf contClassify,")
                .append("cce_type contType,")
                .append("cce_myunit deptName,")
                .append("cce_outunit supplierName,")
                .append("cce_joincontract_date signDate,")
                .append("cce_joincontract_addr signAddr,")
                .append("cce_effective_date validDate,")
                .append("cce_invalidat_date overDate,")
                .append("cce_summoney contCost,")
                .append("cce_coin_type currencyCode,")
                .append("cce_remark remark,")
                .append("cce_buytype purchaseWay,")
                .append("cce_pay_type payWay,")
                .append("cce_contract_class manageDeptNum,")
                .append("cce_link manageNum,")
                .append("cce_status statusCode,")
                .append("total_payment totalPay,")
                .append("total_receandpay totalRecantPay,")
                .append("validity_of_contract validLimit,")
                .append("contract_nature contSecondClassify")
                .append(" from cms_contract_enter where cce_contract_class = ?");
    });

    /**
     * 查询对接合同
     * @Title: queryXrCont
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryXrCont(EiInfo inInfo) {
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, MpConstant.QUERY_BLOCK, MpConstant.RESULT_BLOCK);
        //查询合同信息
        List<String> dockContIds = contractService.queryDockContIds(MpUtils.toString(map.get("deptNum")));
        map.put("dockContIds", dockContIds);
        List<Map> list = queryContList(Map.class, map, true);
        //统计总数
        int count = NumberUtils.toint(map.get("contTotalNumber"), 0);
        inInfo.setRows(MpConstant.RESULT_BLOCK, list);
        inInfo.getBlock(MpConstant.RESULT_BLOCK).set("count", count);
        return inInfo;
    }

   /**
    * 查询合同列表
    * @Title: queryContList
    * @param clazz clazz
    * @param map map
    * @return java.util.List<T>
    * @throws
    **/
    public <T> List<T> queryContList(Class<T> clazz, Map map, boolean isCount) {
        List<Object> params = new ArrayList<>(map.size());
        StringBuilder sqlBuilder = threadLocal.get();
        /**1.根据参数屏接sql**/
        params.add(map.get("deptNum"));
        //合同号参数
        String contNo = MpUtils.toString(map.get("contNo"));
        if(StringUtils.isNotBlank(contNo)) {
            sqlBuilder.append(" and cce_code like ?");
            params.add(String.join("", "%", contNo, "%"));
        }
        //合同名称参数
        String contName = MpUtils.toString(map.get("contName"));
        if(StringUtils.isNotBlank(contName)) {
            sqlBuilder.append(" and cce_name like ?");
            params.add(String.join("", "%", contName, "%"));
        }
        //对接合同ID
        List<String> dockContIds = MpUtils.toList(map.get("dockContIds"), String.class);
        if(CollectionUtils.isNotEmpty(dockContIds)) {
            String dockContIdsStr = StringUtils.join(dockContIds, "','");
            sqlBuilder.append(" and cce_id not in ").append(String.join("", "('", dockContIdsStr, "')"));
            //params.add(dockContIdsStr);
        }
        //分页参数
        int offset = NumberUtils.toint(map.get("offset"), -1);
        int limit = NumberUtils.toint(map.get("limit"), -1);
        if(offset >=0 && limit > 0) {
            sqlBuilder.append(" limit ?, ?");
            params.add(offset); params.add(limit);
        }
        /**2.数据查询**/
        try {
            /**2.1 查询合同信息**/
            List<T> list = MpDbUtils.executeQuery(clazz, sqlBuilder.toString(), params.toArray(new Object[params.size()]));
            if(isCount) {
                /**2.2 统计数量**/
                String countSql = sqlBuilder.toString();
                countSql = "select count(*) count " + countSql.substring(countSql.indexOf("from"), countSql.indexOf("limit"));
                int count = MpDbUtils.count(countSql, params.toArray(new Object[params.size()]));
                map.put("contTotalNumber", count);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadLocal.remove();
        }
        return new ArrayList<>();
    }
}
