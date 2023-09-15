package com.baosight.wilp.rm.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmUtils;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用对接外部接口(本地服务 、 微服务接口)
 * @ClassName: ServiceRMJK03
 * @Package com.baosight.wilp.rm.jk
 * @date: 2022年09月07日 13:51
 * <p>
 * 1.获取仓库信息
 * 2.获取物资库存信息
 * 3.出库
 * 4.红冲出库
 */
public class ServiceRMJK03 extends ServiceBase {

    /**
     * 获取仓库信息
     *
     * @param inInfo inInfo
     *               wareHouseNo: 仓库号
     *               wareHouseName: 仓库名称
     *               wareHouseType:  仓库类型
     *               priceType: 计价方式
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: dockWareHouse
     **/
    public EiInfo dockWareHouse(EiInfo inInfo) {
        EiInfo outInfo = RmUtils.invoke(inInfo, "S_SI_JK_05");
        return outInfo;
    }

    /**
     * 获取物资库存信息
     *
     * @param inInfo inInfo
     *               matNumList: 物资编码集合
     *               matNum: 物资编码
     *               matName: 物资名称
     *               wareHouseNo: 仓库号
     *               isShowZero: 是否显示零库存（Y/N）
     *               offset: 分页开始的索引
     *               limit: 获取的数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * matNum:	物资编码
     * matName:	物资名称
     * matTypeNum:	物资分类ID
     * matTypeName:	物资分类名称
     * matModel:	物资型号
     * matSpec:	物资规格
     * unit:	计量单位
     * price:	单价
     * stockNum:	库存数量
     * @throws
     * @Title: dockMatStock
     **/
    public EiInfo dockMatStock(EiInfo inInfo) {
        EiInfo outInfo = RmUtils.invoke(inInfo, "S_SI_JK_03");
        return outInfo;
    }

    /**
     * 出库
     *
     * @param inInfo inInfo
     *               outType:	出类型编码
     *               outTypeName:	出库类型名称
     *               originBillNo:	来源单据号
     *               originBillType:	来源单据类型
     *               originBillTypeName:	来源单据类型名称
     *               wareHouseNo:	仓库号
     *               wareHouseName:	仓库名称
     *               userDeptNum:	领用/退库科室编码
     *               userDeptName:	领用/退库科室名称
     *               userWorkerNo:	领用/退库人工号
     *               userWorkerName:	领用/退库人姓名
     *               billMakerName: 制单人名字
     *               billMakeTime:	制单人
     *               billMaker:	制单时间
     *               dataGroupCode:	账套
     *               list:	出库明细集合
     *               matNum:	物资编码
     *               matName:	物资名称
     *               matTypeNum ： 物资分类编码
     *               matTypeName : 物资分类名称
     *               matModel:	物资型号
     *               matSpec:	物资规格
     *               unitName:	计量单位
     *               outNum:	出库数量
     *               outPrice:	出库单价
     *               outAmount:	出库总价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: outStock
     **/
    public EiInfo outStock(EiInfo inInfo) {
        EiInfo outInfo = RmUtils.invoke(inInfo, "S_SI_JK_01");
        return outInfo;
    }

    /**
     * 红冲出库
     *
     * @param inInfo inInfo
     *               originBillNo:	来源单据号
     *               originBillType:	来源单据类型
     *               originBillTypeName:	来源单据类型名称
     *               billMakerName: 制单人名字
     *               billMakeTime:	制单人
     *               billMaker:	制单时间
     *               list:	出库明细集合
     *               matNum:	物资编码
     *               matName:	物资名称
     *               matTypeNum ： 物资分类编码
     *               matTypeName : 物资分类名称
     *               matModel:	物资型号
     *               matSpec:	物资规格
     *               unit:       计量单位
     *               unitName:	计量单位
     *               outNum:	出库数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: outStockByHC
     **/
    public EiInfo outStockByHC(EiInfo inInfo) {
        inInfo.set("remark", "科室退库红冲");
        EiInfo outInfo = RmUtils.invoke(inInfo, "S_SI_JK_06");
        return outInfo;
    }
}
