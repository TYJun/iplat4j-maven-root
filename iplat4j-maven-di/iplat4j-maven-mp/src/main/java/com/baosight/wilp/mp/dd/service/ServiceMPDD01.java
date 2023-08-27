package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单页面Service
 * @ClassName: ServiceMPDD01
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月12日 9:35
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除数据
 */
public class ServiceMPDD01 extends ServiceBase {

    @Autowired
    private MpPurchaseOrderService orderService;

    @Autowired
    private MpContractService contractService;

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo info){
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        info.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        info.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        MpUtils.initQueryTime(info, "beginTime", "endTime");
        return this.query(info);
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param info info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo info){
        return super.query(info, "MPLJ03.query", new MpPurchaseOrder());
    }

    /**
     * 删除采购计划
     * @Title: delete
     * @param info info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo info){
        //获取并校验参数
        String id = info.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //获取订单明细,修改合同中以生成订单数量
        List<MpPurchaseOrderDetail> detailList = orderService.queryPurchaseOrderDetailList(id);

        //删除采购订单,返回0,删除失败;返回1,删除成功
        int delete = orderService.delete(id);
        if(delete == 0) {
            return ValidatorUtils.errorInfo("订单已在入库，无法删除");
        }
        detailList.forEach(detail -> contractService.reduceOrderNum(detail));
        //删除订单明细
        orderService.deleteDetail(id);
        return info;
    }
}
