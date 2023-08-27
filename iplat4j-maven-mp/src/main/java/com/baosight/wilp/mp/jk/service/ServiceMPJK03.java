package com.baosight.wilp.mp.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpUtils;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购与物资领用对接接口
 * @ClassName: ServiceMPJK03
 * @Package com.baosight.wilp.mp.jk.service
 * @date: 2022年10月20日 9:48
 *
 * 1.获取需求计划明细
 * 2.需求计划回调
 */
public class ServiceMPJK03 extends ServiceBase {

    /**
     * 获取需求计划明细集合
     * @Title: queryDetailList
     * @param inInfo inInfo
     *      matTypeNums : 物资分类编码集合
     *      detailIds: 需求计划明细id集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      planId:	需求计划ID
     *      matNum:	物资编码
     *      matName:	物资名称
     *      matTypeId:	物资分类ID
     *      matTypeName:	物资分类名称
     *      matModel:	物资型号
     *      matSpec:	物资规格
     *      unit:	计量单位
     *      price:	单价
     *      num:	需求数量
     * @throws
     **/
    public EiInfo queryDetailList(EiInfo inInfo) {
        return MpUtils.invoke(inInfo,"S_RM_JK_01");
    }

    /**
     * 需求计划汇总回调
     * @Title: updatePlan
     * @param inInfo inInfo
     *      planDetailList: 需求计划明细集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updatePlan(EiInfo inInfo) {
        return MpUtils.invoke(inInfo,"S_RM_JK_02");
    }

}
