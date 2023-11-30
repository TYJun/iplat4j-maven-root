package com.baosight.wilp.rm.tk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 退库出库子页面Service
 * @ClassName: ServiceRMTK0202
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年10月25日 9:58
 *
 * 1.页面加载
 * 2.退库出库
 */
public class ServiceRMTK0202 extends ServiceBase {

    @Autowired
    private RmBackOutService backOutService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取退库主信息
        List<RmBackOut> list = dao.query("RMLJ03.query", inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
        inInfo.setRows(RmConstant.QUERY_BLOCK, list);
        //获取退库明细信息
        List<RmBackOutDetail> detailList = backOutService.queryBackOutDetailList(inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id"));
        detailList.forEach(d -> d.setCurOutNum(RmUtils.doubleSub(d.getNum(), d.getOutNum())));
        inInfo.setRows(RmConstant.RESULT_BLOCK, detailList);
        return inInfo;
    }

    /**
     * 退库出库
     * @Title: outStock
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStock(EiInfo inInfo) throws Exception {
        //获取参数
        RmBackOut back = new RmBackOut();
        back.fromMap(inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
        List<RmBackOutDetail> details = RmUtils.toList(inInfo.get("list"), RmBackOutDetail.class);
        //参数校验
        details = details.stream().filter(detail -> !detail.getCurOutNum().isNaN() && detail.getCurOutNum() > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("出库数量不能为空或数量全部为0");
        }
        //更新退库单信息
        updateBack(back, details);

        //构建(红冲)出库数据对象
        Map<String, Object> outMap = buildOut(back, details);
        //出库
        RmUtils.invoke("RMJK03", "outStockByHC", outMap);
        return inInfo;
    }

    /**
     * 更新退库单信息
     * @Title: updateBack
     * @param back back : 退库单对象
     * @param details details : 退库单明细
     * @return
     * @throws
     **/
    private void updateBack(RmBackOut back, List<RmBackOutDetail> details) throws Exception {
        //更新退库数量，更新失败,则本次退库数量不对。
        for (RmBackOutDetail detail : details) {
            int update = backOutService.updateDetail(detail);
            if(update == 0) {
                throw new Exception("本次退库数量不能大于退库数量减去已退库数量");
            }
        }
        //判断退库单是否已全部退库
        boolean hasAll = backOutService.hasAllBackOut(back.getId());
        //更新退库单信息
        back.setStatusCode(hasAll ? RmConstant.BACK_OUT_STATUS_OVER : RmConstant.BACK_OUT_STATUS_PART_OUT);
        back.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.backOut.status", back.getStatusCode()));
        backOutService.update(back);
    }

    /**
     * 构建出库信息
     * @Title: buildOut
     * @param back back
     * @param details details
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
     **/
    private Map<String, Object> buildOut(RmBackOut back, List<RmBackOutDetail> details) {
        Map<String, Object> outMap = new HashMap<>(16);
        outMap.put("originBillNo", details.get(0).getClaimNo());
        outMap.put("originBillType", "05");
        outMap.put("originBillTypeName", "红冲");
        outMap.put("userDeptNum", back.getCostDeptNum());
        outMap.put("userDeptName", back.getCostDeptName());
        outMap.put("costDeptNum", back.getCostDeptNum());
        outMap.put("costDeptName", back.getCostDeptName());
        outMap.put("userWorkerNo", back.getRecCreator());
        outMap.put("userWorkerName", back.getRecCreatorName());
        outMap.put("billMaker", UserSession.getLoginName());
        outMap.put("billMakerName", UserSession.getLoginCName());
        outMap.put("billMakeTime", DateUtils.curDateTimeStr19());
        List<Map> list = new ArrayList<>();
        for (RmBackOutDetail detail : details) {
            Map map = detail.toMap();
            map.put("matTypeNum", map.get("matTypeId"));
            map.put("outNum", map.get("curOutNum"));
            list.add(map);
        }
        outMap.put("list", list);
        return outMap;
    }
}
