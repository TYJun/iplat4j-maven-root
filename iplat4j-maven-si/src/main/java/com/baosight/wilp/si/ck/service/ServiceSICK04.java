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
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库签收
 * @ClassName: ServiceSICK04
 * @Package com.baosight.wilp.si.ck.service
 * @date: 2023年03月16日 16:47
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.签收
 */
public class ServiceSICK04 extends ServiceBase {

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
        String costDeptNum = inInfo.getCellStr(EiConstant.queryBlock, 0, "costDeptNum");
        //没有选中科室查询条件,获取这个人所有可签收的出库单
        if(StringUtils.isBlank(costDeptNum)) {
            //获取人员所有关联的科室
            EiInfo eiInfo = SiUtils.invoke(null,"SITY02", "selectUserBusinessDept", new String[]{"workNo"}, UserSession.getLoginName());
            List<Map<String, String>> list = eiInfo.getBlock("userDept").getRows();
            if(CollectionUtils.isEmpty(list)) {
                //不查出任何数据
                inInfo.setCell(EiConstant.queryBlock, 0, "costDeptNums", Arrays.asList("no data"));
            } else {
                List<String> codes = list.stream().map(map -> map.get("deptNum")).collect(Collectors.toList());
                inInfo.setCell(EiConstant.queryBlock, 0, "costDeptNums", codes);
            }
        } else {
            inInfo.setCell(EiConstant.queryBlock, 0, "costDeptNums", Arrays.asList(costDeptNum));
        }
        /**是否开启出库仓库签字: 是: 需要仓库先签字，然后科室签收**/
        String hasSign = SiConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                SiConfigCache.SI_CONFIG_OUT_STOCK_SIGN);
        inInfo.setCell(EiConstant.queryBlock, 0, "isCheck", SiUtils.toBoolean(hasSign) ? 2 : 0);
        return super.query(inInfo, "SICK01.query", new SiOut());
    }

    /**
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
    public EiInfo signOut(EiInfo inInfo) {
        List<String> list = SiUtils.toList(inInfo.get("list"), String.class);
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("请选择需要签收的出库单");
        }
        //获取审核人的签名图片地址
        String signature = "/si/showSign/" + inInfo.getString("signature");
        //更新入库单状态
        dao.update("SICK01.outSign", new HashMap(4){{
            put("signature", signature);
            put("billChecker", UserSession.getLoginName());
            put("billCheckerName", UserSession.getLoginCName());
            put("billCheckTime", DateUtils.curDateTimeStr19());
            put("list", list);
        }});
        //修改领用状态
        if(SiUtils.isExistModule("RM")){
            dao.update("SICK04.updateClaimStatus", list);
        }
        return inInfo;
    }
}
