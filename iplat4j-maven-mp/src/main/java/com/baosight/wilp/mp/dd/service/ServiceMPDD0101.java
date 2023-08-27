package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.SerialNoUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContractDetail;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单新增子页面Service
 * @ClassName: ServiceMPDD0101
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月12日 9:37
 *
 * 1.页面数据加载
 * 2.获取合同树结构
 * 3.查询合同明细
 * 4.新增采购订单
 */
public class ServiceMPDD0101 extends ServiceBase {

    @Autowired
    private MpContractService contractService;

    @Autowired
    private MpPurchaseOrderService orderService;

    /**
     * 页面数据加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 获取合同树结构
     * @Title: queryContTree
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryContTree(EiInfo inInfo) {
        //1 获取参数
        String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        String contId = inInfo.getCellStr(EiConstant.queryBlock, 0, "contId");
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        //2.通过node，显示标识位置分布
        List<Map<String, String>> rows = contractService.queryContTree(MpUtils.toString(deptMap.get("deptNum")),
                StringUtils.isBlank(contId) ? node : contId, null);
        //3 增加节点 block 块
        EiInfo outInfo = new EiInfo();
        EiBlock outBlock = outInfo.addBlock(node);
        outBlock.addRows(rows);
        return outInfo;
    }

    /**
     * 查询合同明细
     * @Title: queryContDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryContDetail(EiInfo inInfo) {
        //参数处理
        MpContractDetail detail = new MpContractDetail();
        detail.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
        if(StringUtils.isBlank(detail.getContId())) {
            detail.setContId("root");
        }
        //数据查询
        List<MpContractDetail> list = contractService.queryContDetail(detail);
        inInfo.setRows(MpConstant.RESULT_BLOCK, list);
        return inInfo;
    }

    /**
     * 新增采购订单
     * @Title: addOrder
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo addOrder(EiInfo inInfo) {
        //获取参数
        List<MpPurchaseOrderDetail> detailList = MpUtils.toList(inInfo.get("list"), MpPurchaseOrderDetail.class);
        //参数校验
        detailList = detailList.stream().filter(detail -> detail.getNum() !=null && detail.getNum() > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("订单数量不能为空或全部为0");
        }
        //校验订单数量是否满足
        if(!validateNumber(detailList)) {
            return ValidatorUtils.errorInfo("本次订单数量不能大于合同数量减去已生成订单数量");
        }
        //构建订单主单据
        MpPurchaseOrder order = new MpPurchaseOrder();
        order.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
        buildOrder(order,detailList);
        //数据保存
        orderService.insert(order);
        orderService.insertDetail(detailList);
        //修改合同中的已生成订单数量
        detailList.forEach(detail -> contractService.addOrderNum(detail));
        return inInfo;
    }

    /**
     * 校验订单数量是否满足
     * @Title: validateNumber
     * @param detailList detailList
     * @return boolean
     * @throws
     **/
    private boolean validateNumber(List<MpPurchaseOrderDetail> detailList) {
        for (MpPurchaseOrderDetail detail : detailList) {
            boolean result = contractService.hasEnoughOrderNum(detail, MpConstant.OPERATE_TYPE_ADD);
            if(!result) { return false; }
        }
        return true;
    }

    /**
     * 构建订单主单据
     * @Title: buildOrder
     * @return com.baosight.wilp.mp.lj.domain.MpPurchaseOrder
     * @throws
     *@param order
     * @param detailList */
    private MpPurchaseOrder buildOrder(MpPurchaseOrder order, List<MpPurchaseOrderDetail> detailList) {
        order.setId(UUID.randomUUID().toString());
        order.setOrderNo(SerialNoUtils.generateSerialNo("pm_purchase_order","MPO", DateUtils.DATE8_PATTERN,4));
        order.setStatusCode(MpConstant.ORDER_STATUS_NEW);
        order.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.purchase.OrderStatus", order.getStatusCode()));
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        order.setDeptNum(MpUtils.toString(deptMap.get("deptNum")));
        order.setDeptName(MpUtils.toString(deptMap.get("deptName")));
        order.setRecCreator(UserSession.getLoginName());
        order.setRecCreatorName(UserSession.getLoginCName());
        order.setRecCreateTime(new Date());
        order.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        //处理明细
        detailList.forEach(detail ->{
            detail.setId(UUID.randomUUID().toString());
            detail.setOrderId(order.getId());
            detail.setNum(detail.getNum());
            order.setOrderNum(MpUtils.doubleAdd(order.getOrderNum(), detail.getNum()));
            order.setOrderCost(order.getOrderCost().add(BigDecimal.valueOf(MpUtils.doubleMult(detail.getNum(),detail.getPrice()))));
        });
        return order;
    }


}
