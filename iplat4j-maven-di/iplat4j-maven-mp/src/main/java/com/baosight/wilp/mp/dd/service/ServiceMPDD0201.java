package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 订单采购入库子页面Service
 * @ClassName: ServiceMPDD0201
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月13日 15:13
 *
 * 1.页面加载
 * 2.采购入库
 */
public class ServiceMPDD0201 extends ServiceBase {

    @Autowired
    private MpPurchaseOrderService orderService;

    @Autowired
    private MpContractService contractService;

    /**入库类型--直入直出**/
    public static final String ENTER_TYPE_ZRZC = "01";

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //查询未全部出库的明细
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "isNoAllEnter", "Y");
        return MpUtils.invoke(inInfo, "MPDD0102", "initLoad");
    }

    /**
     * 入库
     * <p>1.获取参数
     *    2.参数校验
     *    3.构建入库数据，调用微服务S_SI_JK_02入库
     *    4.更新订单明细的已入库数量、合同的已入库数据
     *    5.更新订单的状态
     * </p>
     * @Title: enterStock
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo enterStock(EiInfo inInfo) {
        /**1.获取参数**/
        Map row = inInfo.getRow(MpConstant.QUERY_BLOCK, 0);
        List<MpPurchaseOrderDetail> detailList = MpUtils.toList(inInfo.get("list"), MpPurchaseOrderDetail.class);
        /**2.参数校验**/
        if(StringUtils.isBlank(MpUtils.toString(row.get("wareHouseNo")))) {
            return ValidatorUtils.errorInfo("仓库不能为空");
        }
        if(ENTER_TYPE_ZRZC.equals(row.get("enterType")) && StringUtils.isBlank(MpUtils.toString(row.get("deptNum")))) {
            return ValidatorUtils.errorInfo("领用科室不能为空");
        }
        detailList = detailList.stream().filter(detail -> detail.getCurEnterNum() != null && detail.getCurEnterNum() > 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("入库明细不能为空或入库数量不能全部为0");
        }
        //校验入库数量是否小于或等于可入库数量（订单数量-已入库数量）
        if(!validateEnterNum(detailList)) {
            return ValidatorUtils.errorInfo("本次入库数量不能大于订单数量减去已入库数量");
        }

        /**3.构建入库数据，调用微服务S_SI_JK_02入库**/
        EiInfo enterInfo = buildEnter(row, detailList);
        EiInfo outInfo = MpUtils.invoke(enterInfo, "MPJK02", "enterStock");
        if(outInfo.getStatus() == -1) {
            return outInfo;
        }
        /**4.更新订单明细的已入库数量、合同的已入库数据**/
        detailList.forEach(detail -> {
            orderService.updateDetail(detail);
            contractService.updateEnterNum(detail);
        });
        /**5.更新订单的状态**/
        updateOrder(MpUtils.toString(row.get("id")));

        /**6.推送数据到固定资产**/
        enterInfo.set("enterBillNo", outInfo.getString("enterBillNo"));
        MpUtils.invoke(enterInfo,"MPJK05", "pushEnter");
        return inInfo;
    }

    /**
     * 校验入库数量是否小于或等于可入库数量（订单数量-已入库数量）
     * @Title: validateEnterNum
     * @param detailList detailList : 订单明细集合
     * @return boolean
     * @throws
     **/
    private boolean validateEnterNum(List<MpPurchaseOrderDetail> detailList) {
        for (MpPurchaseOrderDetail orderDetail : detailList) {
            boolean result = orderService.hasEnoughEnterNum(orderDetail);
            if(!result) { return false; }
        }
        return true;
    }

    /**
     * 构建入库信息
     * @Title: buildEnter
     * @param row row
     * @param detailList detailList
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    private EiInfo buildEnter(Map row, List<MpPurchaseOrderDetail> detailList) {
        EiInfo enter = new EiInfo();
        //构建入库主单据
        enter.set("enterType", row.get("enterType"));
        enter.set("enterTypeName", ENTER_TYPE_ZRZC.equals(row.get("enterType")) ? "直入直出" : "采购入库");
        enter.set("originBillNo",row.get("orderNo"));
        enter.set("originBillType","02");
        enter.set("originBillTypeName","采购到货单");
        enter.set("contNo",detailList.get(0).getContNo());
        enter.set("invoiceNum","");
        enter.set("wareHouseNo", row.get("wareHouseNo"));
        enter.set("wareHouseName", row.get("wareHouseName"));
        enter.set("batchNo","BCN" + DateUtils.curDateTimeStr14());
        enter.set("billMakeTime", DateUtils.curDateTimeStr19());
        enter.set("billMakerName", UserSession.getLoginCName());
        enter.set("billMaker",UserSession.getLoginName());
        enter.set("userDeptNum",row.get("deptNum"));
        enter.set("userDeptName",row.get("deptName"));
        enter.set("dataGroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        List<Map> list = new ArrayList<>();
        detailList.forEach(detail -> {
            Map map = detail.toMap();
            map.put("matTypeNum", map.get("matTypeId"));
            map.put("enterNum", map.get("curEnterNum"));
            map.put("enterPrice", map.get("price"));
            BigDecimal enterAmount = NumberUtils.toBigDecimal(map.get("enterNum"),BigDecimal.ZERO)
                    .multiply(NumberUtils.toBigDecimal(map.get("enterPrice"),BigDecimal.ZERO)).setScale(2, BigDecimal.ROUND_HALF_UP);
            map.put("enterAmount", enterAmount.toString());
            map.put("surpNum", row.get("supplierNum"));
            map.put("surpName", row.get("supplierName"));
            map.put("validEnd", row.get("warrantyDate"));
            map.remove("matTypeId");
            map.remove("curEnterNum");
            map.remove("price");
            list.add(map);
        });
        enter.set("list", list);
        return enter;
    }

    /**
     * 更新订单状态
     * @Title: updateOrder
     * @param id id : 订单ID
     * @return void
     * @throws
     **/
    private void updateOrder(String id) {
        //判断是否完成入库
        boolean result = orderService.hasAllEnter(id);
        //构建订单对象
        MpPurchaseOrder order = new MpPurchaseOrder();
        order.setId(id);
        order.setStatusCode(result ? MpConstant.ORDER_STATUS_ENTER : MpConstant.ORDER_STATUS_PART_ENTER);
        order.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.purchase.OrderStatus", order.getStatusCode()));
        order.setRecCreateTime(new Date());
        order.setRecRevisor(UserSession.getLoginName());
        //更新订单
        orderService.update(order);
    }

}
