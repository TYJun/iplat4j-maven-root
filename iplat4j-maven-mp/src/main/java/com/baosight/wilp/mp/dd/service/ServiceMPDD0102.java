package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单编辑/详情子页面Service
 * @ClassName: ServiceMPDD0102
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月12日 17:38
 *
 * 1.页面加载
 * 2.更新采购订单
 */
public class ServiceMPDD0102 extends ServiceBase {

    @Autowired
    private MpPurchaseOrderService orderService;

    @Autowired
    private MpContractService contractService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取采购订单主单据信息
        Map map = inInfo.getRow(MpConstant.QUERY_BLOCK, 0);
        List<MpPurchaseOrder> list = dao.query("MPLJ03.query", map);
        inInfo.setRows(MpConstant.QUERY_BLOCK, list);
        //获取明细
        map.put("orderId", map.get("id"));
        List<MpPurchaseOrderDetail> detailList = dao.query("MPLJ03.queryDetail",map);
        inInfo.setRows(MpConstant.RESULT_BLOCK, detailList);
        //向request域添加数据
        UserSession.setOutRequestProperty(MpConstant.OPERATE_NAME, inInfo.getString(MpConstant.OPERATE_NAME));
        return inInfo;
    }

    /**
     * 更新采购订单
     * @Title: updateOrder
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateOrder(EiInfo inInfo) {
        //获取参数
        MpPurchaseOrder order = new MpPurchaseOrder();
        order.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
        List<MpPurchaseOrderDetail> detailList = MpUtils.toList(inInfo.get("list"), MpPurchaseOrderDetail.class);
        //获取原先的明细
        List<MpPurchaseOrderDetail> details = orderService.queryPurchaseOrderDetailList(order.getId());
        //明细数据校验及处理
        if(!dealDetail(order, detailList, details)) {
            return ValidatorUtils.errorInfo("本次订单数量不能小于已出库数量且不能大于合同数量减去已生成订单数量");
        }
        //更新订单和订单明细
        orderService.update(order);
        orderService.deleteDetail(order.getId());
        orderService.insertDetail(details);
        //更新合同明细中的已生成订单数量
        detailList.forEach(orderDetail -> contractService.addOrderNum(orderDetail));
        return inInfo;
    }

    /**
     * 明细数据校验及处理
     * @Title: dealDetail
     * @param order order : 订单对象集合
     * @param detailList detailList : 页面订单明细集合
     * @param details details : 数据库原先明细对象集合
     * @return boolean
     * @throws
     **/
    private boolean dealDetail(MpPurchaseOrder order, List<MpPurchaseOrderDetail> detailList, List<MpPurchaseOrderDetail> details) {
        //遍历
        for (MpPurchaseOrderDetail detail : detailList) {
            //校验订单数量是否小于或等于合同数量-已生成订单数量
            boolean contResult = contractService.hasEnoughOrderNum(detail, MpConstant.OPERATE_TYPE_EDIT);
            //校验订单数量是否大于或等于已出库数量
            boolean orderResult = orderService.hasEnoughOrderNum(detail);
            if(!(contResult && orderResult)) {
                return false;
            }
            //累加订单数量和订单金额
            Double num = NumberUtils.toDouble(detail.getNum(), 0d);
            order.setOrderNum(MpUtils.doubleAdd(order.getOrderNum(), num));
            order.setOrderCost(order.getOrderCost().add(BigDecimal.valueOf(MpUtils.doubleMult(num,detail.getPrice()))));

            //判断订单明细原先是否存在。不存在,添加;存在更新订单数量
            MpPurchaseOrderDetail od = details.stream().filter(orderDetail -> detail.getMatNum().equals(orderDetail.getMatNum())
                    && detail.getMatTypeId().equals(orderDetail.getMatTypeId())).findFirst().orElse(null);
            if(od == null) {
                //数量不存在, 跳过当前循环
                if(detail.getNum() == null && detail.getNum() == 0) {
                    continue;
                }
                detail.setId(UUID.randomUUID().toString());
                detail.setOrderId(order.getId());
                details.add(detail);
            } else {
                //计算差值,用于更新合同明细中的已生成订单数量
                detail.setNum(MpUtils.doubleSub(num, od.getNum()));
                od.setNum(num);
            }
        }
        return true;
    }
}
