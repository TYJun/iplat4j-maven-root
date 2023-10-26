package com.baosight.wilp.si.jk.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存与固定资产对接接口
 * @ClassName: ServiceSIJK04
 * @Package com.baosight.wilp.si.jk.service
 * @date: 2022年12月19日 16:14
 *
 * 1.入库记录查询
 * 2.出库记录查询
 * 3.固定资产出库
 * 4.入库推送数据到固定资产
 */
public class ServiceSIJK04 extends ServiceBase {

    private static final String IS_DOCK_FA_YES = "Y";

    /**
     * 入库记录查询
     * @Title: queryEnter
     *      enterBillNo: 入库单号
     *      matNum: 物资编码
     * @param inInfo inInfo
     *      id : 主键
     *      enterBillNo	: 入库单号 资产入库的唯一标识码,大于零的自然数
     *      enterTypeName :	入库类型名称
     *      enterPerson : 制单人
     *      enterTime : 制单时间
     *      wareHouseName :	仓库名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryEnter(EiInfo inInfo) {
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, Arrays.asList("enterBillNo", "matNum"));
        List<Map> list = dao.query("SIJK04.queryEnter", params);
        inInfo.setAttr(list.get(0));
        return inInfo;
    }

    /**
     * 出库记录查询
     * @Title: queryOut
     *      outBillNo: 出库单号
     *      matNum: 物资编码
     * @param inInfo inInfo
     *      id : 主键
     *      outBillNo :	出库单号 资产出库的唯一标识码,大于零的自然数
     *      outTypeName : 出库类型名称
     *      outPerson : 制单人
     *      outTime : 制单时间
     *      userDeptName : 领用科室
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryOut(EiInfo inInfo) {
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, Arrays.asList("outBillNo", "matNum"));
        List<Map> list = dao.query("SIJK04.queryOut", params);
        inInfo.setAttr(list.get(0));
        return inInfo;
    }

    /**
     * 固定资产出库
     * @Title: outStock
     *      userDeptNum:	使用科室编码
     *      userDeptName:	使用科室名称
     *      billMakerName: 制单人名字
     *      billMakeTime:	制单人
     *      billMaker:	制单时间
     *      dataGroupCode:	账套
     *      list:	出库明细集合
     *          enterBillNo: 入库单号
     *          matNum:	物资编码
     *          matName:	物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *          matModel:	物资型号
     *          matSpec:	物资规格
     *          unit: 计量单位
     *          unitName:	计量单位
     *          outNum:	出库数量
     *          outPrice:	出库单价
     *          outAmount:	出库总价
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStock(EiInfo inInfo) {
        /**1.获取参数**/
        List<String> faInfoIds = SiUtils.toList(inInfo.get("faInfoIdList"), String.class);
        List<Map> list = SiUtils.toList(inInfo.get("list"), Map.class);

        /**2.参数校验**/
        EiInfo validate = validateOutParams(inInfo, list);
        if(validate.getStatus() == -1) {
            return validate;
        }

        /**构建回调接口参数**/
        String outType = inInfo.getString("outType");
        EiInfo faInfo = new EiInfo();
        faInfo.set("outType", outType); faInfo.set("faInfoIds", faInfoIds);

        /**判断是领用出库还是退库**/
        EiInfo outInfo = null;
        if(SiConstant.OUT_TYPE_LY.equals(outType)) {
            //领用出库
            EiInfo claimOutInfo  = buildOutInfo(inInfo, list, null);
            //出库
            outInfo = SiUtils.invoke(claimOutInfo, "SICK0101", "outStock");
            if(outInfo.getStatus() == -1) { return outInfo; }
            //回调接口参数赋值
            faInfo.set("claimOutBillNo", outInfo.getString("outBillNo"));
        } else {
            //退库
            List<EiInfo> redRushOutInfoList = buildRedRushOut(inInfo, list);
            List<Map<String, String>> outList = new ArrayList<>();
            for (EiInfo info : redRushOutInfoList) {
                if(SiConstant.OUT_TYPE_ZRZC.equals(info.get("backType"))) {
                    //退货
                    outInfo = SiUtils.invoke(info, "SIRK0101", "enterStock");
                    outInfo.set("outBillNo", outInfo.get("enterBillNo"));
                } else {
                    //退库
                    outInfo = SiUtils.invoke(info, "SICK0101", "outStock");
                }
                if(outInfo.getStatus() == -1) { return outInfo; }
                //处理领用出库单号和退库单号的关联
                Map<String, String> outMap = new HashMap<>(4);
                outMap.put("outBillNo", outInfo.getString("outBillNo"));
                outMap.put("originBillNo", info.getString("originOutBillNo"));
                outList.add(outMap);

            }
            faInfo.set("outList", outList);
        }
        /**回调微服务**/
        SiUtils.invokeRemote(faInfo,"S_FA_SI_01");
        return faInfo;
    }

    /**
     * 退库(红冲出库)参数构建
     * @Title: buildRedRushOut
     * @param inInfo inInfo 参数EiInfo
     * @param list list 物资明细集合
     * @return java.util.List<com.baosight.iplat4j.core.ei.EiInfo>
     * @throws
     **/
    private List<EiInfo> buildRedRushOut(EiInfo inInfo, List<Map> list) {
        List<EiInfo> returnList = new ArrayList<>();
        //将物资集合按出库单号分组
        Map<String, List<Map>> groupMap = list.stream().collect(Collectors.groupingBy(map -> SiUtils.isEmpty(map.get("outBillNo"))));
        //遍历
        for (String outBillNo : groupMap.keySet()) {
            //根据原先出库单号判断出库类型
            List<Map> mapList = groupMap.get(outBillNo);
            //直入直出退库退货
            Map<String, String> map = getOutInfo(outBillNo);
            if(SiConstant.OUT_TYPE_ZRZC.equals(map.get("outType"))) {
                EiInfo info = buildRedRushZrzc(inInfo, mapList, map);
                info.set("backType", SiConstant.OUT_TYPE_ZRZC);
                returnList.add(info);
            } else {
                //退库
                EiInfo info = buildOutInfo(inInfo, mapList, outBillNo);
                returnList.add(info);
            }
        }
        return returnList;
    }

   /**
    * 构建直入直出的退库及退货
    * <p>如果是直入直出，则构建直入直出退货单，退货验收后，会自动退库</p>
    * @Title: buildRedRushZrzc
    * @param inInfo inInfo
    * @param mapList mapList
    * @param outMap outMap
    * @return com.baosight.iplat4j.core.ei.EiInfo
    * @throws
    **/
    private EiInfo buildRedRushZrzc(EiInfo inInfo, List<Map> mapList, Map<String, String> outMap) {
        EiInfo returnInfo = new EiInfo();
        //获取入库单号
        String enterBillNo = outMap.get("originBillNo");
        //查询入库数据
        List<SiEnter> enterList = dao.query("SIRK01.query", new HashMap(2) {{
            put("enterBillNoEQ", enterBillNo);
        }});
        SiEnter enter = enterList.get(0);
        enter.setId(UUID.randomUUID().toString());
        enter.setRecCreator(inInfo.getString("billMaker"));
        enter.setRecCreateTime(DateUtils.curDateTimeStr19());
        enter.setOriginBillNo(enterBillNo);
        enter.setOriginBillType(enter.getEnterType());
        enter.setOriginBillTypeName(enter.getOriginBillTypeName());
        enter.setEnterBillNo(SerialNoUtils.generateSerialNo("si_enter", "EBN"));
        enter.setEnterType(SiConstant.ENTER_TYPE_HC);
        enter.setEnterTypeName(CommonUtils.getDataCodeItemName("wilp.si.enterType", enter.getEnterType()));
        enter.setEnterTime(null);
        enter.setBillMaker(inInfo.getString("billMaker"));
        enter.setBillMakerName(inInfo.getString("billCheckerName"));
        enter.setBillMakeTime(inInfo.getString("billMakeTime"));
        enter.setIsCheck(0); enter.setPrintFlag(0);
        enter.setTotalNum(0d); enter.setTotalAmount(0d);
        enter.setRemark(inInfo.getString("remark"));
        enter.setCheckSign(null); enter.setApprovalSign(null);

        //入库明细处理
        List<SiEnterDetail> enterDetailList = new ArrayList<>();
        for (Map map : mapList) {
            SiEnterDetail detail = new SiEnterDetail();
            detail.fromMap(map);
            detail.setId(UUID.randomUUID().toString());
            detail.setRecCreator(enter.getRecCreator());
            detail.setRecCreateTime(enter.getRecCreateTime());
            detail.setEnterBillNo(enter.getEnterBillNo());
            detail.setEnterBillDetailNo(SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6));
            detail.setEnterType(enter.getEnterType());
            detail.setEnterTypeName(enter.getEnterTypeName());
            detail.setEnterNum(NumberUtils.toDouble(map.get("outNum")));
            detail.setEnterPrice(NumberUtils.toDouble(map.get("outPrice")));
            detail.setBatchNo(enter.getBatchNo());
            detail.setEnterAmount(SiUtils.cal(detail.getEnterNum(), detail.getEnterPrice(), SiConstant.MATH_MULTI));
            String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
            if(!SiUtils.toBoolean(hasCheck)) {
                detail.setEnterDate(DateUtils.curDateStr10());
                detail.setEnterTime(DateUtils.curDateTimeStr19());
            }
            enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
            enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), detail.getEnterAmount(), SiConstant.MATH_ADD));
            enterDetailList.add(detail);
        }
        returnInfo.set("enter", enter);
        returnInfo.set("enterDetailList", enterDetailList);
        returnInfo.set("originOutBillNo", outMap.get("outBillNo"));
        return returnInfo;
    }

    /**
     * 构建出库数据
     * @Title: buildOutInfo
     * @param inInfo inInfo
     * @param list list
     * @param originBillNo originBillNo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    private EiInfo buildOutInfo(EiInfo inInfo, List<Map> list, String originBillNo) {
        EiInfo returnInfo = new EiInfo();
        //构建出库主单据
        SiOut out = new SiOut();
        out.fromMap(inInfo.getAttr());
        out.setRecCreateTime(DateUtils.curDateTimeStr19());
        out.setRecCreator(out.getBillMaker());
        out.setDataGroupCode(SiUtils.isEmpty(out.getDataGroupCode(), BaseDockingUtils.getUserGroupByWorkNo(out.getBillMaker())));
        out.setId(UUID.randomUUID().toString());
        out.setOutBillNo(SerialNoUtils.generateSerialNo("si_out", "OW"));
        out.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.si.outType", out.getOutType()));
        if(StringUtils.isNotBlank(originBillNo)) {
            returnInfo.set("originOutBillNo", originBillNo);
            out.setOriginBillNo(originBillNo);
            out.setOriginBillType(SiConstant.OUT_RESOURCE_TYPE_LY);
            //获取原出库单
            List<Map<String, String>> oList = dao.query("SIJK04.queryOutDept", originBillNo);
            Map<String, String> oMap = CollectionUtils.isEmpty(oList) ? new HashMap(2) : oList.get(0);
            out.setCostDeptNum(oMap.get("userDeptNum"));
            out.setCostDeptName(oMap.get("userDeptName"));
            out.setUserDeptNum(oMap.get("costDeptNum"));
            out.setUserDeptName(oMap.get("costDeptName"));
        } else {
            out.setUserDeptNum(out.getCostDeptNum());
            out.setUserDeptName(out.getCostDeptName());
        }
        out.setWareHouseNo(SiConfigCache.getConfigTextValue(out.getDataGroupCode(), SiConfigCache.SI_CONFIG_FIXED_ASSET));
        out.setWareHouseName(getWareHouseName(out.getWareHouseNo()));
        //构建出库明细集合
        List<SiOutDetail> outDetailList = new ArrayList<>();
        for (Map map : list) {
            SiOutDetail detail = new SiOutDetail();
            detail.fromMap(map);
            detail.setRecCreator(out.getBillMaker());
            detail.setRecCreateTime(out.getRecCreateTime());
            detail.setId(UUID.randomUUID().toString());
            String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
            detail.setOutBillDetailNo(detailBillNo);
            detail.setOutBillNo(out.getOutBillNo());
            detail.setOutType(out.getOutType());
            detail.setOutTypeName(out.getOutTypeName());
            detail.setOutDate(DateUtils.curDateStr10());
            detail.setOutTime(DateUtils.curDateTimeStr19());
            outDetailList.add(detail);
        }

        //返回
        returnInfo.set("out", out);
        returnInfo.set("outDetailList", outDetailList);
        return returnInfo;
    }

    /**
     * 获取出库信息
     * @Title: getOutInfo
     * @param outBillNo outBillNo
     * @return java.lang.String
     * @throws
     **/
    private Map getOutInfo(String outBillNo) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("outBillNo", outBillNo);
        List<Map<String, String>> list = dao.query("SIJK04.queryOut", params);
        return list.get(0);
    }

    /**
     * 校验出库参数
     * @Title: validateOutParams
     * @param inInfo inInfo 参数EiInfo
     * @param list list 物资明细集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     **/
    private EiInfo validateOutParams(EiInfo inInfo, List<Map> list) {
        String outType = inInfo.getString("outType");
        if(StringUtils.isBlank(outType)) {
            return ValidatorUtils.errorInfo("出库类型不能为空");
        }

        if(SiConstant.OUT_TYPE_LY.equals(outType) && StringUtils.isBlank(inInfo.getString("costDeptNum"))) {
            return ValidatorUtils.errorInfo("成本(调拨)科室不能为空");
        }
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("资产集合不能为空");
        }
        return new EiInfo();
    }


    /**
     * 根据仓库号获取仓库名称
     * @Title: getWareHouseName
     * @param wareHouseNo wareHouseNo
     * @return java.lang.String
     * @throws
     **/
    private String getWareHouseName(String wareHouseNo) {
        List<String> list = dao.query("SIJK04.queryWareHouse", wareHouseNo);
        return list.get(0);
    }

    /**
     * 入库推送
     * @Title: pushEnter
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pushEnter(EiInfo inInfo) {
        //获取参数
        List<Map> details = SiUtils.toList(inInfo.get("enterDetails"), Map.class);
        SiEnter enter = (SiEnter) inInfo.get("enter");
        //获取合同订单信息
        Map<String, String> contInfo = queryContInfo(enter.getContNo());
        //数据处理
        for (Map map : details) {
            map.put("originBillNo", enter.getOriginBillNo());
            map.put("originBillType", enter.getOriginBillType());
            map.put("originBillTypeName", enter.getOriginBillTypeName());
            map.put("purchaseVouch", SiConstant.ENTER_TYPE_HC.equals(enter.getEnterType()) ? "" : enter.getOriginBillNo());
            map.put("purchaseStaffName", contInfo.get("managerName"));
            map.put("invoiceNum", map.get("invoiceNo"));
            map.put("contNo", enter.getContNo());
            map.put("acquitvDate", contInfo.get("signDate"));
            map.put("acquitvYear", contInfo.get("signYear"));
            map.put("fundingSourceNum", contInfo.get("fundingSourceNum"));
            map.put("fundingSourceName", contInfo.get("fundingSourceName"));
            map.put("wareHouseNo", enter.getWareHouseNo());
            map.put("wareHouseName", enter.getWareHouseName());
            map.put("billMakerName", UserSession.getLoginCName());
            map.put("billMakeTime", DateUtils.curDateTimeStr19());
            map.put("billMaker", UserSession.getLoginName());
            map.put("dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
            map.put("outBillNo", inInfo.getString("outBillNo"));
            map.put("userDeptNum", enter.getUserDeptNum());
            map.put("userDeptName", enter.getUserDeptName());
            map.remove("eiMetadata");
        }
        //调用微服务
        SiUtils.invokeRemote("S_FA_CONFIRM_01", Arrays.asList("list"), details);
        return inInfo;
    }

    /**
     * 获取合同信息
     * @Title: queryContInfo
     * @param contNo contNo
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> queryContInfo(String contNo) {
        List<Map<String, String>> list = dao.query("SIJK04.queryCont", contNo);
        return CollectionUtils.isEmpty(list) ? new HashMap(2) : list.get(0);
    }

    public EiInfo testOut(EiInfo inInfo) {
        EiInfo o = new EiInfo();
        o.set("outType", "06");
        o.set("faInfoIds", new ArrayList<>());
        o.set("costDeptNum", "000011");
        o.set("costDeptName", "图书室");
        o.set("billMakerName", "系统管理员");
        o.set("billMakeTime", "2023-09-27 17:30:00");
        o.set("billMaker", "admin");
        o.set("dataGroupCode", "");
        String list ="[{\"outBillNo\":\"\",\"matNum\":\"MA05928\",\"matName\":\"组合电脑桌\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1000*600/300*1560\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"5\",\"outPrice\":\t\"1078.00\",\"outAmount\":\"5390.00\"}" +
                "{\"outBillNo\":\"\",\"matNum\":\"MA04096\",\"matName\":\"电脑桌\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1200*850*790\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"5\",\"outPrice\":\t\"966.00\",\"outAmount\":\"4830.00\"}]";
        o.set("list", JSON.parseArray(list, Map.class));
        return SiUtils.invokeRemote(o, "S_SI_FA_03");
    }

    public EiInfo testRedRushOut(EiInfo inInfo) {
        EiInfo o = new EiInfo();
        o.set("outType", "05");
        o.set("faInfoIds", new ArrayList<>());
        o.set("billMakerName", "系统管理员");
        o.set("billMakeTime", "2023-09-27 18:30:00");
        o.set("billMaker", "admin");
        o.set("dataGroupCode", "");
        // String list ="[{\"enterBillNo\":\"EBN202212210001\",\"matNum\":\"4060103198\",\"matName\":\"仪器柜\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1500*500*2000\",\"unit\": \"11\",\"unitName\":\"个\",\"outNum\":\t\"1\",\"outPrice\":\t\"1775.00\",\"outAmount\":\"1775.00\"}]";
        String list ="[{\"outBillNo\":\"OW202309270006\",\"matNum\":\"MA05928\",\"matName\":\"组合电脑桌\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1000*600/300*1560\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"2\",\"outPrice\":\t\"1078.00\"}" +
                "{\"outBillNo\":\"OW202309270006\",\"matNum\":\"MA04096\",\"matName\":\"电脑桌\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1200*850*790\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"2\",\"outPrice\":\t\"966.00\"}]";
        o.set("list", JSON.parseArray(list, Map.class));
        return SiUtils.invokeRemote(o, "S_SI_FA_03");
    }

    public EiInfo testZrZc(EiInfo inInfo) {
        EiInfo o = new EiInfo();
        o.set("outType", "05");
        o.set("faInfoIds", new ArrayList<>());
        o.set("billMakerName", "系统管理员");
        o.set("billMakeTime", "2023-09-27 18:30:00");
        o.set("billMaker", "admin");
        o.set("dataGroupCode", "");
        // String list ="[{\"enterBillNo\":\"EBN202212210001\",\"matNum\":\"4060103198\",\"matName\":\"仪器柜\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1500*500*2000\",\"unit\": \"11\",\"unitName\":\"个\",\"outNum\":\t\"1\",\"outPrice\":\t\"1775.00\",\"outAmount\":\"1775.00\"}]";
        String list ="[{\"outBillNo\":\"OW202309270004\",\"matNum\":\"MA02492\",\"matName\":\"办公椅\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"工作人员用\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"5\",\"outPrice\":\t\"825.00\"}" +
                "{\"outBillNo\":\"OW202309270004\",\"matNum\":\"MA02506\",\"matName\":\"办公桌\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1500*800*760\",\"unit\": \"19\",\"unitName\":\"张\",\"outNum\":\t\"5\",\"outPrice\":\t\"3850.00\"}]";
        o.set("list", JSON.parseArray(list, Map.class));
        return SiUtils.invokeRemote(o, "S_SI_FA_03");
    }

}
