package com.baosight.wilp.mp.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpUtils;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购与库存对接Service
 * @ClassName: ServiceMPJK02
 * @Package com.baosight.wilp.mp.jk.service
 * @date: 2022年10月13日 15:10
 *
 * 1.获取仓库
 * 2.入库
 * 3.更新入库单的发票号
 */
public class ServiceMPJK02 extends ServiceBase {

    /**
     * 获取仓库信息
     * @Title: dockWareHouse
     * @param inInfo inInfo
     *      wareHouseNo: 仓库号
     *      wareHouseName: 仓库名称
     *      wareHouseType:  仓库类型
     *      priceType: 计价方式
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo dockWareHouse(EiInfo inInfo) {
        EiInfo outInfo = MpUtils.invoke(inInfo, "S_SI_JK_05");
        return outInfo;
    }

    /**
     * 入库
     * @Title: enterStock
     * @param inInfo inInfo
     *      enterType:	入库类型编码
     *      enterTypeName:	入库类型名称
     *      originBillNo:	来源单据号
     *      originBillType:	来源单据类型
     *      originBillTypeName:	来源单据类型名称
     *      contNo:	合同号
     *      invoiceNum:	发票号
     *      wareHouseNo:	仓库号
     *      wareHouseName:	仓库名称
     *      batchNo:	批次
     *      billMakeTime:	制单人
     *      billMakerName: 制单人名字
     *      billMaker:	制单时间
     *      dataGroupCode:	账套
     *      userDeptNum: 领用科室编码
     *      userDeptName: 领用科室名称
     *      list:	入库明细集合
     *          matNum:	物资编码
     *          matName:	物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *          matModel:	物资型号
     *          matSpec:	物资规格
     *          unitName:	计量单位
     *          enterNum:	入库数量
     *          enterPrice:	入库单价
     *          enterAmount:	入库总价
     *          surpNum:	供应商编码
     *          surpName:	供应商名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo enterStock(EiInfo inInfo) {
        EiInfo outInfo = MpUtils.invoke(inInfo, "S_SI_JK_02");
        return outInfo;
    }

    /**
     * 更新发票号
     * @Title: updateInvoiceNo
     * @param inInfo inInfo
     *      list: 参数集合
     *          originBillNo: 来源单据号(订单号)
     *          matNum: 物资编码
     *          matTypeNum: 物资分类编码
     *          invoiceNo: 发票号
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateInvoiceNo(EiInfo inInfo) {
        return MpUtils.invoke(inInfo, "S_SI_JK_07");
    }
}
