package com.baosight.wilp.si.ty.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.common.SiConfigCache;
import com.baosight.wilp.si.common.SiUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存待办Service
 * @ClassName: ServiceSITYUndo
 * @Package com.baosight.wilp.si.ty.service
 * @date: 2023年09月19日 11:05
 */
public class ServiceSITYUndo extends ServiceBase {

    public static final String MODULE_NAME = "物资采购";

    /**
     * 出库签收待办
     * @Title: outSignUndo
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outSignUndo(EiInfo inInfo) {
        //获取工号
        String workNo = SiUtils.isEmpty(inInfo.getString("workNo"), UserSession.getLoginName());
        //获取当前人员的所有业务科室
        EiInfo eiInfo = SiUtils.invoke(null,"SITY02", "selectUserBusinessDept", new String[]{"workNo"}, workNo);
        List<Map<String, String>> list = eiInfo.getBlock("userDept").getRows();
        /**数据查询**/
        inInfo.setCell(EiConstant.queryBlock, 0,"costDeptNums", CollectionUtils.isEmpty(list) ? Arrays.asList("no data")
                : list.stream().map(map -> map.get("deptNum")).collect(Collectors.toList()));
        String hasSign = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_OUT_STOCK_SIGN);
        inInfo.setCell(EiConstant.queryBlock, 0,"isCheck", SiUtils.toBoolean(hasSign) ? 2 : 0);
        inInfo.addBlock(EiConstant.resultBlock).set("showCount", "true");
        EiInfo outInfo = super.query(inInfo, "SICK01.query", new SiOut());
        /**构建待办数据**/
        inInfo.set("moduleName", "物资签收");
        inInfo.set("todoCount", outInfo.getBlock(EiConstant.resultBlock).getInt("count"));
        inInfo.set("todoDetailUrl","SICK04");
        inInfo.set("todoList", JSON.toJSONString(unSignExchange(outInfo.getBlock(EiConstant.resultBlock).getRows())));
        return inInfo;
    }

    /**
     * 将待签收数据转换成待办数据
     * @Title: unSignExchange
     * @param rows rows
     * @return java.util.List
     * @throws
     **/
    private List<Map<String, String>> unSignExchange(List<Map> rows) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Map row : rows) {
            HashMap<String, String> map = new HashMap<>(2);
            map.put("jumpUrl", "SICK04?inqu_status-0-outBillNo="+row.get("outBillNo"));
            map.put("itemMsg", "你有一条"+row.get("costDeptName")+"的物资领用待签收, 请及时签收");
            list.add(map);
        }
        return list;
    }

    /**
     * 领用驳回待办
     * @Title: claimRejectUndo
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo claimRejectUndo(EiInfo inInfo) {
        //获取工号
        String workNo = SiUtils.isEmpty(inInfo.getString("workNo"), UserSession.getLoginName());
        //数据查询
        List<Map<String, String>> rows = dao.query("SITYUndo.queryRejectClaim", workNo);
        int count = super.count("SITYUndo.countRejectClaim", workNo);
        /**数据转换**/
        rows.forEach(row -> {
            row.put("jumpUrl", "RMLY01?inqu_status-0-claimNo="+row.get("claimNo"));
            row.put("itemMsg", "你有一条"+row.get("costDeptName")+"的申领物资记录被驳回,请及时处理。");
        });
        /**构建待办数据**/
        inInfo.set("moduleName", "申领驳回");
        inInfo.set("todoCount", count);
        inInfo.set("todoDetailUrl","RMLY01");
        inInfo.set("todoList", JSON.toJSONString(rows));
        return inInfo;
    }
}
