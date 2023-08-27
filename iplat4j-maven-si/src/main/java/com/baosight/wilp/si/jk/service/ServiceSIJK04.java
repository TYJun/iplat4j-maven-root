package com.baosight.wilp.si.jk.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.SerialNoUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
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
        Map params = inInfo.getAttr();
        List<Map> list = SiUtils.toList(inInfo.get("list"), Map.class);

        /**2.参数校验**/
        EiInfo validate = validateOutParams(params, list);
        if(validate.getStatus() == -1) {
            return validate;
        }

        /**3.参数处理**/
        SiOut out = new SiOut();
        out.fromMap(params);
        out.setOutType(SiConstant.OUT_TYPE_LY);
        out.setOutTypeName("领用出库");
        out.setRecCreateTime(DateUtils.curDateTimeStr19());
        out.setRecCreator(out.getBillMaker());
        //对明细按仓库分组
        Map<String, List<SiOutDetail>> splitMap = splitDetailByStock(list, params);
        //遍历
        Map<String, List<String>> backMap = new HashMap<>(splitMap.size());
        EiInfo outInfo = null;
        for(String key : splitMap.keySet()){
            List<SiOutDetail> value = splitMap.get(key);
            String[] wareHouseArray = key.split("-");
            SiOut siOut = JSON.parseObject(JSON.toJSONString(out), SiOut.class);
            siOut.setId(UUID.randomUUID().toString());
            siOut.setOutBillNo(SerialNoUtils.generateSerialNo("si_out", "OW"));
            siOut.setCostDeptNum(siOut.getUserDeptNum());
            siOut.setCostDeptName(siOut.getUserDeptName());
            siOut.setWareHouseNo(wareHouseArray[0]);
            siOut.setWareHouseName(wareHouseArray[1]);
            value.forEach(detail -> {
                    detail.setOutBillNo(siOut.getOutBillNo());
                    siOut.setTotalNum(siOut.getTotalNum() + detail.getOutNum());
                    siOut.setTotalAmount(siOut.getTotalAmount() + detail.getOutAmount());
            });
            backMap.put(siOut.getOutBillNo(), value.stream().map(detail -> detail.getExtField()).collect(Collectors.toList()));
            /**4.出库**/
            outInfo = SiUtils.invoke(new EiInfo(), "SICK0101", "outStock", new String[]{"out", "outDetailList"}, siOut, value);
            if(outInfo.getStatus() == -1) {
                return outInfo;
            }
        };
        /**回调微服务**/
        SiUtils.invokeRemote("S_FA_SI_01", Arrays.asList("outBillNoMap"), backMap);

        //返回
        EiInfo result = new EiInfo();
        result.set("outBillNoMap", JSON.toJSONString(backMap));
        return result;
    }

    /**
     * 校验出库参数
     * @Title: validateOutParams
     * @param params params
     * @param list list
     * @return com.baosight.iplat4j.core.ei.EiInfo
     **/
    private EiInfo validateOutParams(Map params, List<Map> list) {
        if(StringUtils.isBlank(SiUtils.isEmpty(params.get("userDeptNum")))) {
            return ValidatorUtils.errorInfo("科室不能为空");
        }
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo("资产集合不能为空");
        }
        return new EiInfo();
    }

    /**
     * 将固定资产按仓库分组
     * @Title: splitDetailByStock
     * @param list list
     * @return java.util.Map<java.lang.String,java.util.List<com.baosight.wilp.si.ck.domain.SiOutDetail>>
     * @throws
     **/
    private Map<String, List<SiOutDetail>> splitDetailByStock(List<Map> list, Map params) {
        Map<String, List<SiOutDetail>> resultMap = new HashMap<>(16);
        //按入库单号分组
        Map<String, List<Map>> groupMap = list.stream().collect(Collectors.groupingBy(map -> SiUtils.isEmpty(map.get("enterBillNo"))));
        //遍历分组
        for (String enterBillNo : groupMap.keySet()) {
            String wareHouse = getWareHouse(enterBillNo);
            //明细数据处理
            List<SiOutDetail> details = buildDetail(groupMap.get(enterBillNo), params);
            //判断仓库是否存在
            if(resultMap.containsKey(wareHouse)) {
                resultMap.get(wareHouse).addAll(details);
            } else {
                resultMap.put(wareHouse, details);
            }
        }
        return resultMap;
    }

    /**
     * 根据入库单号获取仓库
     * @Title: getWareHouse
     * @param enterBillNo enterBillNo
     * @return java.lang.String
     * @throws
     **/
    private String getWareHouse(String enterBillNo) {
        List<String> list = dao.query("SIJK04.queryWareHouse", enterBillNo);
        return list.get(0);
    }

    /**
     * 构建物资明细
     * @Title: buildDetail
     * @param list list
     * @param params params
     * @return java.util.List<com.baosight.wilp.si.ck.domain.SiOutDetail>
     * @throws
     **/
    private List<SiOutDetail> buildDetail(List<Map> list, Map params) {
        List<SiOutDetail> resultList = new ArrayList<>();
        for (Map map : list) {
            SiOutDetail detail = new SiOutDetail();
            detail.fromMap(map);
            detail.setRecCreator(SiUtils.isEmpty(params.get("billMaker")));
            detail.setRecCreateTime(DateUtils.curDateTimeStr19());
            detail.setId(UUID.randomUUID().toString());
            String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
            detail.setOutBillDetailNo(detailBillNo);
            detail.setOutType(SiConstant.OUT_TYPE_LY);
            detail.setOutTypeName("领用出库");
            detail.setOutDate(DateUtils.curDateStr10());
            detail.setOutTime(DateUtils.curDateTimeStr19());
            detail.setExtField(SiUtils.isEmpty(map.get("faInfoId")));
            resultList.add(detail);
        }
        return resultList;
    }

    /**
     * 入库推送
     * @Title: pushEnter
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pushEnter(EiInfo inInfo) {
        //获取固定资产分类
        List<Map<String, String>> list = dao.query("SIJK04.queryFaClassType", null);
        if(CollectionUtils.isNotEmpty(list)) {
            Map<String, String> configMap = list.get(0);
            //判断是否需要推送入库数据
            if(IS_DOCK_FA_YES.equals(configMap.get("configValueRadio"))) {
                //获取固定资产的分类编码配置
                String configValueText = configMap.get("configValueText");
                if(StringUtils.isBlank(configValueText)) { return inInfo; }
                //递归获取分类下的子分类编码
                List<String> matTypeNumList = new ArrayList<>();
                for (String matTypeNum : configValueText.split(",")) {
                    EiInfo invoke = SiUtils.invoke(null, "SITY02", "getChildMatTypeNumList", new String[]{"rootMatTypeNum"}, matTypeNum);
                    matTypeNumList.addAll(SiUtils.toList(invoke.get("matTypeList"), String.class));
                }
                //过滤出固定资产
                List<Map> details = SiUtils.toList(inInfo.get("enterDetails"), Map.class);
                details = details.stream().filter(map -> matTypeNumList.contains(SiUtils.isEmpty(map.get("matTypeNum")))).collect(Collectors.toList());
                //数据处理
                List<Map> pushList = completeData(details, inInfo.getAttr());
                if(CollectionUtils.isNotEmpty(pushList)) {
                    //调用微服务
                    SiUtils.invokeRemote("S_FA_CONFIRM_01", Arrays.asList("list"), pushList);
                }
            }
        }
        return inInfo;
    }

    /**
     * 完善入库推送参数
     * @Title: completeData
     * @param details details
     * @param pMap pMap
     * @return java.util.List<java.util.Map>
     * @throws
     **/
    private List<Map> completeData(List<Map> details, Map pMap) {
        List<Map> list = new ArrayList<>();
        //遍历、构建参数
        for (Map map : details) {
            map.put("purchaseVouch", "");
            map.put("originBillType", pMap.get("originBillType"));
            map.put("originBillTypeName", pMap.get("originBillTypeName"));
            map.put("purchaseStaffName", "");
            map.put("fundingSourceNum", "");
            map.put("fundingSourceName", "");
            map.put("acquitvDate", "");
            map.put("acquitvYear", "");
            map.put("contNo", "");
            map.put("invoiceNum", "");
            map.put("wareHouseNo", pMap.get("wareHouseNo"));
            map.put("wareHouseName", pMap.get("wareHouseName"));
            map.put("billMakerName", UserSession.getLoginCName());
            map.put("billMakeTime", DateUtils.curDateTimeStr19());
            map.put("billMaker", UserSession.getLoginName());
            map.put("dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
            list.add(map);
        }
        return list;
    }

    public EiInfo test(EiInfo inInfo) {
        EiInfo o = new EiInfo();
        o.set("userDeptNum", "10310101");
        o.set("userDeptName", "PICC门诊");
        o.set("billMakerName", "系统管理员");
        o.set("billMakeTime", "2022-12-22 17:30:00");
        o.set("billMaker", "admin");
        o.set("dataGroupCode", "10310101");
       // String list ="[{\"enterBillNo\":\"EBN202212210001\",\"matNum\":\"4060103198\",\"matName\":\"仪器柜\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"1500*500*2000\",\"unit\": \"11\",\"unitName\":\"个\",\"outNum\":\t\"1\",\"outPrice\":\t\"1775.00\",\"outAmount\":\"1775.00\"}]";
        String list ="[{\"enterBillNo\":\"EBN20221212090000\",\"matNum\":\"13468\",\"matName\":\"文件架\",\"matTypeNum\":\"40601\",\"matTypeName\" : \"家具用具\",\"matMode\":\"\",\"matSpec\":\"三格立式ADM94739\",\"unit\": \"11\",\"unitName\":\"个\",\"outNum\":\t\"1\",\"outPrice\":\t\"17.68\",\"outAmount\":\"17.68\"}]";
        o.set("list", JSON.parseArray(list, Map.class));
        return SiUtils.invokeRemote(o, "S_SI_FA_03");
    }

}
