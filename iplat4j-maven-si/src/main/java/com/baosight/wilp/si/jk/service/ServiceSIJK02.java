package com.baosight.wilp.si.jk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.wilp.si.wh.domain.SiWarehouse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存模块对外接口（系统其他模块）
 * @ClassName: ServiceSIJK02
 * @Package com.baosight.wilp.si.jk.service
 * @date: 2022年09月19日 15:43
 *
 * 1.获取仓库
 * 2.获取物资的库存量信息
 * 3.入库单价修改操作
 * 4.出库
 * 5.入库
 * 6.领用红冲出库(退库)
 * 7.更新入库单的
 */
public class ServiceSIJK02 extends ServiceBase {

    /**
     * 获取仓库
     * @Title: queryWareHouse
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      wareHouseNo: 仓库号
     *      wareHouseName: 仓库名称
     *      wareHouseType:  仓库类型
     *      priceType: 计价方式
     * @throws
     **/
    public EiInfo queryWareHouse(EiInfo inInfo) {
        List<SiWarehouse> list = dao.query("SIWH01.query", new HashMap(2) {{
            put("freezeFlag", "N");
            put("workNo", SiUtils.isEmpty(inInfo.getString("workNo"), UserSession.getLoginName()));
        }});
        inInfo.setRows("wareHouse", list);
        return inInfo;
    }

    /**
     * 获取物资的库存量信息
     * @Title: queryStock
     * @param inInfo inInfo
     *      matNumList: 物资编码集合
     *      matNum: 物资编码
     *      matName: 物资名称
     *      wareHouseNo: 仓库号
     *      wareHouseNos: 仓库号集合
     *      isShowZero: 是否显示零库存（Y/N）
     *      offset: 分页开始的索引
     *      limit: 获取的数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      matNum:	物资编码
     *      matName:	物资名称
     *      matTypeNum:	物资分类ID
     *      matTypeName:	物资分类名称
     *      matModel:	物资型号
     *      matSpec:	物资规格
     *      unit:	计量单位
     *      price:	单价
     *      stockNum:	库存数量
     * @throws
     **/
    public EiInfo queryStock(EiInfo inInfo) {
        //数据查询
        List<Map<String, String>> list = dao.query("SIJK02.queryStock", inInfo.getAttr());
        list.forEach(map->{
            DecimalFormat df = new DecimalFormat("0.00");
            map.put("stockNum", df.format(map.get("stockNum")));
        });
        int count = dao.count("SIJK02.countStock", inInfo.getAttr());
        //数据返回
        EiInfo eiInfo = CommonUtils.BuildOutEiInfo(inInfo, "result", null, list, count);
        eiInfo.getBlock("result").set(EiConstant.limitStr, inInfo.getInt("limit"));
        return eiInfo;
    }

    /**
     * 入库单价修改操作
     * @Title: updateStock
     * @param inInfo inInfo
     *      purchaseOrderNo:	采购订单单号
     *      matList:	修改了单价的物资列表
     *          matNum:	物资编码
     *          matName:	物资名称
     *          matTypeId:	物资分类编码
     *          matTypeName:	物资分类名称
     *          price:	单价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateStock(EiInfo inInfo) {
        /**1.获取参数**/
        String purchaseOrderNo = inInfo.getString("purchaseOrderNo");
        List<Map> matList = SiUtils.toList(inInfo.get("matList"), Map.class);
        //校验参数
        if(StringUtils.isBlank(purchaseOrderNo) || CollectionUtils.isEmpty(matList)) {
            inInfo.setStatus(-1);
            inInfo.setMsg("参数为空错误");
            return inInfo;
        }
        /**2.入库单、库存入库批次、入库履历数据处理**/
        matList.forEach(map -> {
            map.put("purchaseOrderNo", purchaseOrderNo);
            dao.update("SIJK02.updateEnterDetail");
            dao.update("SIJK02.updateStorageDetail");
            dao.update("SIJK02.updateEnterStorageRecord");
        });

        /**3.获取入库物资的批次号
         * 4.根据批次号查找出库单号
         * 5.遍历出库单号，获取出库单的出库履历
         * 6.修改出库履历中对应批次的单价
         * 7.重新计算出库平均单价
         * 8.更新出库明细的出库单价和总价
         * **/
        return inInfo;
    }

    /**
     * 出库
     * <p>
     *     1.获取参数
     *     2.参数校验
     *     3.参数赋值
     *     4.出库
     * </p>
     * @Title: outStock
     * @param inInfo inInfo
     *      outType:	出类型编码
     *      outTypeName:	出库类型名称
     *      originBillNo:	来源单据号
     *      originBillType:	来源单据类型
     *      originBillTypeName:	来源单据类型名称
     *      wareHouseNo:	仓库号
     *      wareHouseName:	仓库名称
     *      userDeptNum:	领用/退库科室编码
     *      userDeptName:	领用/退库科室名称
     *      costDeptNum:	成本科室编码
     *      costDeptName:	成本科室名称
     *      userWorkerNo:	领用/退库人工号
     *      userWorkerName:	领用/退库人姓名
     *      billMakerName: 制单人名字
     *      billMakeTime:	制单人
     *      billMaker:	制单时间
     *      dataGroupCode:	账套
     *      list:	出库明细集合
     *          matNum:	物资编码
     *          matName:	物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *          matModel:	物资型号
     *          matSpec:	物资规格
     *          unitName:	计量单位
     *          outNum:	出库数量
     *          outPrice:	出库单价
     *          outAmount:	出库总价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStock(EiInfo inInfo) {
        /**1.获取参数**/
        SiOut siOut = new SiOut();
        siOut.fromMap(inInfo.getAttr());
        List<SiOutDetail> list = SiUtils.toList(inInfo.get("list"), SiOutDetail.class);

        /**2.参数校验**/
        EiInfo validate = validateOutParams(siOut, list);
        if(validate.getStatus() == -1) {
            return validate;
        }

        /**3.参数赋值**/
        siOut.setId(UUID.randomUUID().toString());
        siOut.setOutBillNo(SerialNoUtils.generateSerialNo("si_out", "OW"));
        siOut.setRecCreateTime(DateUtils.curDateTimeStr19());
        siOut.setRecCreator(siOut.getBillMaker());
        list.forEach(detail -> perfectOutDetail(detail, siOut));

        /**4.出库**/
        return SiUtils.invoke(new EiInfo(), "SICK0101", "outStock", new String[]{"out", "outDetailList"}, siOut, list);
    }

    /**
     * 出库参数校验
     * @Title: validateOutParams
     * @param siOut siOut
     * @param list list
     * @return com.baosight.wilp.si.common.ValidateResult
     * @throws
     **/
    private EiInfo validateOutParams(SiOut siOut, List<SiOutDetail> list) {
        if (StringUtils.isBlank(siOut.getOutType())) {
            return ValidatorUtils.errorInfo("出库类型不能为空");
        }

        if (StringUtils.isBlank(siOut.getWareHouseNo())) {
            return ValidatorUtils.errorInfo("仓库不能为空");
        }

        //过滤明细中出库数量为空的或为0的数据
        list = list.stream().filter(detail -> detail.getOutNum() != null && detail.getOutNum() > 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("出库物资不能为空或数量为0");
        }

        return new EiInfo();
    }

    /**
     * 入库
     * <p>
     *     1.获取参数
     *     2.参数校验
     *     3.参数赋值
     *     4.入库
     * </p>
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
        /**1.获取参数**/
        SiEnter enter = new SiEnter();
        enter.fromMap(inInfo.getAttr());
        List<SiEnterDetail> list = SiUtils.toList(inInfo.get("list"), SiEnterDetail.class);

        /**2.参数校验**/
        EiInfo validate = validateEnterParams(enter, list);
        if(validate.getStatus() == -1) {
            return validate;
        }

        /**3.参数赋值**/
        enter.setId(UUID.randomUUID().toString());
        enter.setEnterBillNo(SerialNoUtils.generateSerialNo("si_enter", "EBN"));
        enter.setBatchNo("BCN" + DateUtils.curDateTimeStr14());
        enter.setRecCreateTime(DateUtils.curDateTimeStr19());
        enter.setRecCreator(enter.getBillMaker());
        String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
        if(!SiUtils.toBoolean(hasCheck)) {
            enter.setEnterTime(DateUtils.curDateTimeStr19());
        }

        for (int i = 0; i < list.size(); i++) {
            SiEnterDetail detail = list.get(i);
            detail.setRecCreator(enter.getRecCreator());
            detail.setRecCreateTime(DateUtils.curDateTimeStr19());
            detail.setId(UUID.randomUUID().toString());
            detail.setEnterBillNo(enter.getEnterBillNo());
            detail.setEnterBillDetailNo(SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6));
            detail.setEnterType(enter.getEnterType());
            detail.setEnterTypeName(enter.getEnterTypeName());
            detail.setBatchNo(enter.getBatchNo());
            detail.setEnterAmount(SiUtils.cal(detail.getEnterNum(), detail.getEnterPrice(), "multiply"));
            detail.setSortNo(i);
            if (!SiUtils.toBoolean(hasCheck)) {
                detail.setEnterDate(DateUtils.curDateStr10());
                detail.setEnterTime(DateUtils.curDateTimeStr19());
            }
            enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
            enter.setTotalAmount(enter.getTotalAmount() + detail.getEnterAmount());
        }

        /**4.入库**/
        return SiUtils.invoke(new EiInfo(), "SIRK0101", "enterStock",
                new String[]{"enter", "enterDetailList","userDeptNum","userDeptName"}, enter, list,
                inInfo.getString("userDeptNum"), inInfo.getString("userDeptNum"));
    }

    /**
     * 入库参数校验
     * @Title: validateEnterParams
     * @param enter enter
     * @param list list
     * @return com.baosight.wilp.si.common.ValidateResult
     * @throws
     **/
    private EiInfo validateEnterParams(SiEnter enter, List<SiEnterDetail> list) {
        if (StringUtils.isBlank(enter.getEnterType())) {
            return ValidatorUtils.errorInfo("入库类型不能为空");
        }

        if (StringUtils.isBlank(enter.getWareHouseNo())) {
            return ValidatorUtils.errorInfo("仓库不能为空");
        }

        //过滤明细中出库数量为空的或为0的数据
        list = list.stream().filter(detail -> detail.getEnterNum() != null && detail.getEnterNum() > 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("入库物资不能为空或数量为0");
        }

        return new EiInfo();
    }

    /**
     * 红冲出库（退库）
     * <p>
     *     1.参数处理
     *     2.根据originBillNo寻找出库单和出库单明细
     *     3.根据红冲明细生成红冲出库单
     *     4.出库
     * </p>
     * @Title: outStockByHC
     * @param inInfo inInfo
     *      originBillNo:	来源单据号(领用单号)
     *      originBillType:	来源单据类型
     *      originBillTypeName:	来源单据类型名称
     *      billMakerName: 制单人名字
     *      billMakeTime:	制单人
     *      billMaker:	制单时间
     *      remark: 备注
     *      list:	出库明细集合
     *          matNum:	物资编码
     *          matName:	物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *          matModel:	物资型号
     *          matSpec:	物资规格
     *          unit:       计量单位
     *          unitName:	计量单位
     *          outNum:	出库数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStockByHC(EiInfo inInfo) {
        /**1.参数处理**/
        Map<String, Object> paramMap = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("originBillNo", "originBillType", "originBillTypeName",
                "userDeptNum", "userDeptName", "costDeptNum", "costDeptName", "userWorkerNo", "userWorkerName",
                "billMakerName", "billMaker", "billMakeTime", "remark"));
        List<SiOutDetail> details = SiUtils.toList(inInfo.get("list"), SiOutDetail.class);
        //参数校验
        EiInfo validateInfo = validateParams(paramMap, details);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        /**2.根据originBillNo寻找出库单和出库单明细**/
        List<SiOut> outs = dao.query("SIJK02.queryOutStockByOriginBillNo", paramMap.get("originBillNo"));
        if(CollectionUtils.isEmpty(outs)) {
            return ValidatorUtils.errorInfo("源出库单不存在，无法红冲出库");
        }
        List<SiOutDetail> outDetails = dao.query("SIJK02.queryOutDetailByOriginBillNo", paramMap.get("originBillNo"));
        /**3.根据红冲明细生成红冲出库单**/
        List<Map<String, Object>> outList = buildHcOut(paramMap, details, outs, outDetails);
        /**4.出库**/
        for (Map<String, Object> map : outList) {
            SiUtils.invoke(null, "SICK0101", "outStock", new String[]{"out", "outDetailList"},
                    map.get("out"), map.get("details"));
        }
        return inInfo;
    }

    /**
     * 参数校验
     * @Title: validateParams
     * @param paramMap paramMap
     * @param details details
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    private EiInfo validateParams(Map<String, Object> paramMap, List<SiOutDetail> details) {
        if(StringUtils.isBlank(SiUtils.isEmpty(paramMap.get("originBillNo")))) {
            return ValidatorUtils.errorInfo("来源单据号不能为空");
        }
        //过滤
        details = details.stream().filter(detail -> detail.getOutNum() !=null && Math.abs(detail.getOutNum()) > 0)
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("出库明细不能为空或出库数量不能全部为0");
        }
        return new EiInfo();
    }

    /**
     * 根据红冲明细生成红冲出库单
     * <p>
     *     1.如果只有一个出库单, 直接构建红冲出库单及其明细
     *     2.有多个出库单, 根据红冲出库物资,构建多个对应的多个红冲出库单及其明细
     *      2.1将领用出库明细按物资分组
     *      2.2遍历参数中的红冲出库明细, 构建红冲出库单明细集合
     *       2.2.1获取红冲物资对应的出库单明细
     *       2.2.2构建新的红冲出库单明细集合
     *      2.3将红冲出库明细按出库单号分组
     *      2.4遍历分组, 构建红冲出库单及完善红冲出库明细
     *       2.4.1获取原先的领用出库单
     *       2.4.2构建红冲出库单
     *       2.4.3更新红冲出出库单明细
     * </p>
     * @Title: buildHcOut
     * @param paramMap paramMap : 参数map,红冲出库主单据信息
     * @param details details : 参数,红冲出库明细
     * @param outs outs : 红冲出库对应的领用出库单集合
     * @param outDetails outDetails : 红冲出库对应领用出库明细
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    private List<Map<String, Object>> buildHcOut(Map<String, Object> paramMap, List<SiOutDetail> details, List<SiOut> outs, List<SiOutDetail> outDetails) {
        List<Map<String, Object>> list = new ArrayList<>();
        /**1.如果只有一个出库单, 直接构建红冲出库单及其明细**/
        if(outs.size() == 1) {
            SiOut out = buildOutDomain(paramMap, outs.get(0));
            details.forEach(detail -> perfectOutDetail(detail, out));
            list.add(new HashMap(4){{ put("out", out); put("details", details); }});
        } else {
            /**2.有多个出库单, 根据红冲出库物资,构建多个对应的多个红冲出库单及其明细**/
            /**2.1将领用出库明细按物资分组**/
            Map<String, List<SiOutDetail>> collectMap = outDetails.stream().collect(Collectors.groupingBy(SiOutDetail::getMatNum));
            /**2.2遍历参数中的红冲出库明细, 构建红冲出库单明细集合**/
            List<SiOutDetail> hcList = new ArrayList<>();
            for (SiOutDetail detail : details) {
                /**2.2.1获取红冲物资对应的出库单明细**/
                List<SiOutDetail> matOutDetailList = collectMap.get(detail.getMatNum());
                /**2.2.2构建新的红冲出库单明细集合**/
                List<SiOutDetail> hcOutDetailList = buildHcOutDetailList(detail, matOutDetailList);
                hcList.addAll(hcOutDetailList);
            }
            /**2.3将红冲出库明细按出库单号分组**/
            Map<String, List<SiOutDetail>> hcListMap = hcList.stream().collect(Collectors.groupingBy(SiOutDetail::getOutBillNo));
            /**2.4遍历分组, 构建红冲出库单及完善红冲出库明细**/
            hcListMap.forEach((key, value) -> {
                /**2.4.1获取原先的领用出库单**/
                SiOut siOut = outs.stream().filter(out -> key.equals(out.getOutBillNo())).findFirst().orElse(new SiOut());
                /**2.4.2构建红冲出库单**/
                siOut = buildOutDomain(paramMap, siOut);
                /**2.4.3更新红冲出出库单明细**/
                for (SiOutDetail detail : value) { perfectOutDetail(detail, siOut); }
                //封装
                Map<String, Object> map = new HashMap(4);
                map.put("out", siOut); map.put("details", value);
                list.add(map);
            });
        }
        return list;
    }

    /**
     * 构建出库主单据
     * @Title: buildOutDomain
     * @param paramMap paramMap
     * @param siOut siOut
     * @return com.baosight.wilp.si.ck.domain.SiOut
     * @throws
     **/
    private SiOut buildOutDomain(Map<String, Object> paramMap, SiOut siOut) {
        String billNo = siOut.getOutBillNo();
        siOut.fromMap(paramMap);
        siOut.setId(UUID.randomUUID().toString());
        //防止批量执行时，执行速度过快，导致出库单号相同
        siOut.setOutBillNo(SerialNoUtils.generateSerialNo("si_out", "OW"));
        siOut.setOutType(SiConstant.OUT_TYPE_HC);
        siOut.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.si.outType", siOut.getOutType()));
        siOut.setOriginBillNo(billNo);
        siOut.setRecCreateTime(DateUtils.curDateTimeStr19());
        siOut.setRecCreator(siOut.getBillMaker());
        return siOut;
    }

    /**
     * 完善出库明细
     * @Title: perfectOutDetail
     * @param detail detail
     * @param out out
     * @return void
     * @throws
     **/
    private void perfectOutDetail(SiOutDetail detail, SiOut out) {
        detail.setRecCreator(out.getRecCreator());
        detail.setRecCreateTime(DateUtils.curDateTimeStr19());
        detail.setId(UUID.randomUUID().toString());
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6));
        detail.setOutType(out.getOutType());
        detail.setOutTypeName(out.getOutTypeName());
        detail.setOutAmount(SiUtils.cal(detail.getOutNum(), detail.getOutPrice(), "multiply"));
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
        out.setTotalNum(out.getTotalNum() + detail.getOutNum());
        out.setTotalAmount(SiUtils.cal(out.getTotalAmount(), detail.getOutAmount(), "add"));
    }

    /**
     * 完善红冲出库明细
     * @Title: buildHcOutDetailList
     * @param detail detail : 红冲出库对象
     * @param matOutDetailList matOutDetailList : 红冲出库物资对应的领用出库明细
     * @return java.util.List<com.baosight.wilp.si.ck.domain.SiOutDetail>
     * @throws
     **/
    private List<SiOutDetail> buildHcOutDetailList(SiOutDetail detail, List<SiOutDetail> matOutDetailList) {
        List<SiOutDetail> list = new ArrayList<>(); Double remainNum = Math.abs(detail.getOutNum());
        //遍历出库明细集合
        for (SiOutDetail outDetail : matOutDetailList) {
            Double fullNum  = SiUtils.cal(outDetail.getOutNum(), remainNum, SiConstant.MATH_SUB);
            //出库领用单满足红冲出库数量
            if(fullNum >= 0) {
                detail.setOutBillNo(outDetail.getOutBillNo());
                detail.setOutNum(Math.abs(remainNum));
                list.add(detail);
            } else {
                //领用出库数量不满足红冲出库数量
                list.add(outDetail);
                remainNum = Math.abs(fullNum);
            }
        }
        return list;
    }

    /**
     * 更新入库单的发票号
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
        //获取参数
        List<Map> list = SiUtils.toList(inInfo.get("list"), Map.class);
        //参数校验
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //遍历更新
        for (Map map : list) {
            dao.update("SIJK02.updateInvoiceNo", map);
        }
        return inInfo;
    }

}
