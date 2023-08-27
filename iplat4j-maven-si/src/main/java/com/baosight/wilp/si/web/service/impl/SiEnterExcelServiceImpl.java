package com.baosight.wilp.si.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.wilp.si.web.service.SiExcelImportService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 入库excel导入导出Service
 * @ClassName: SiEnterExcelServiceImpl
 * @Package com.baosight.wilp.si.web.service.impl
 * @date: 2023年02月15日 17:28
 */
@Service("siEnterExcelService")
public class SiEnterExcelServiceImpl implements SiExcelImportService {

    /**入库Excel表头对应字段**/
    private String[] columnHeaders = new String[]{"wareHouseNo", "wareHouseName", "enterType", "enterTypeName", "matNum", "matName",
        "matTypeNum", "matTypeName", "matSpec", "matModel", "unitName", "enterNum", "enterPrice", "enterDate", "surpName", "invoiceNo"};

    /**小代码缓存Map**/
    private static Map<String, List<Map<String, String>>> dataCodeCacheMap = new HashMap<>(4);

    /**仓库缓存List**/
    private static List<Map<String, String>> wareHouseCacheList = new ArrayList<>();

    /**供应商缓存List**/
    private static List<Map> supplierCacheList = new ArrayList<>();

    /**
     * 构建Excel表头
     * @Title: buildHeader
     * @return java.util.List<com.baosight.wilp.si.common.SiExcelHeader>
     * @throws
     **/
    @Override
    public List<SiExcelHeader> buildHeader() {
        List<String> enterHeads = Arrays.asList("仓库号","仓库名称", "入库类型编码","入库类型名称","物资编码","物资名称",
                "物资分类编码","物资分类名称", "物资规格", "物资型号","计量单位", "入库数量","入库单价", "入库日期", "供应商名称", "发票号");
        List<SiExcelHeader> headerList = SiExcelHeader.toInputHeaders(enterHeads);
        cacheHeaders.addAll(headerList);
        return headerList;
    }

    /**
     * 构建Excel导出数据
     * @Title: buildData
     * @param map map : 参数
     * @return java.util.List<java.lang.String[]>
     * @throws
     **/
    @Override
    public List<String[]> buildData(Map<String, Object> map) {
        return null;
    }

    /**
     * 解析Excel导入数据行
     * @Title: parseRow
     * @param row row : excel数据行
     * @param dataList dataList : excel数据集合
     * @param errorList errorList : excel错误数据集合
     * @return void
     * @throws
     **/
    @Override
    public void parseRow(Row row, List<Object> dataList, List<String[]> errorList) {
        String[] errorRow = new String[columnHeaders.length+1];
        /**1.获取Excel行数据**/
        Map<String, String> map = new HashMap<>(columnHeaders.length);
        for (int i = 0; i < columnHeaders.length; i++) {
            map.put(columnHeaders[i], SiExcelUtils.getCellValue(row.getCell(i)));
            errorRow[i] = SiExcelUtils.getCellValue(row.getCell(i));
        }
        /**2.数据校验**/
        String errMsg = validate(map);

        /**3.数据赋值**/
        if(StringUtils.isBlank(errMsg)) {
            dataList.add(map);
        } else {
            errorRow[errorRow.length-1] = errMsg;
            errorList.add(errorRow);
        }

    }

    /**
     * Excel导入数据校验
     * @Title: validate
     * @param object object
     * @return java.lang.String
     * @throws
     **/
    @Override
    public String validate(Object object) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = (Map<String, String>) object;

        //校验仓库号是否为空
        Map<String, String> wareHouseMap = null;
        if(StringUtils.isBlank(map.get("wareHouseNo"))) {
            sb.append("仓库号不能为空\r\n");
        } else {
            //校验仓库号是否存在
            wareHouseMap = queryWareHouse(map.get("wareHouseNo"));
            if(wareHouseMap == null) { sb.append("仓库号不存在\r\n"); }
        }

        //校验仓库名称是否为空
        if(StringUtils.isBlank(map.get("wareHouseName"))) {
            sb.append("仓库名称不能为空\r\n");
        } else {
            //校验仓库名称是否匹配
            if(wareHouseMap != null && !map.get("wareHouseName").equals(wareHouseMap.get("wareHouseName"))) {
                sb.append("仓库名称与实际仓库名称不匹配\r\n");
            }
        }

        //入库类型不为空时，校验入库类型是否存在
        if(StringUtils.isBlank(map.get("enterType"))) {
            map.put("enterType", "08");
            map.put("enterTypeName", "手工入库");
        } else {
            Map<String, String> itemMap = queryDataCodeItem("wilp.si.enterType", map.get("enterType"), null);
            if(itemMap == null) {
                sb.append("入库类型不存在\r\n");
            } else {
                if(!itemMap.get("label").equals(map.get("enterTypeName"))) {
                    sb.append("入库类型名称与实际入库类型名称不匹配\r\n");
                }
            }
        }

        //校验物资编码是否为空
        if(StringUtils.isBlank(map.get("matNum"))) {
            sb.append("物资编码不能为空\r\n");
        }

        //校验物资名称是否为空
        if(StringUtils.isBlank(map.get("matName"))) {
            sb.append("物资名称不能为空\r\n");
        }

        //校验物资分类编码是否为空
        if(StringUtils.isBlank(map.get("matTypeNum"))) {
            sb.append("物资分类编码不能为空\r\n");
        }
        //校验物资分类名称是否为空
        if(StringUtils.isBlank(map.get("matTypeName"))) {
            sb.append("物资分类名称不能为空\r\n");
        }

        //校验计量单位是否为空
        if(StringUtils.isBlank(map.get("unitName"))) {
            sb.append("计量单位不能为空\r\n");
        } else {
            Map<String, String> unitMap = queryDataCodeItem("wilp.ac.gm.unit", null, map.get("unitName"));
            if(unitMap == null) {
                sb.append("计量单位不存在\r\n");
            } else {map.put("unit", unitMap.get("value"));}
        }

        //校验入库数量是否大于0
        if(NumberUtils.toDouble(map.get("enterNum"), 0d) <= 0) {
            sb.append("入库数量必须大于0\r\n");
        }

        //校验入库单价是否大于0
        if(NumberUtils.toDouble(map.get("enterPrice"), 0d) <= 0) {
            sb.append("入库单价必须大于0\r\n");
        }

        //校验入库日期格式是否正确
        if(!checkDate(map.get("enterDate"))) {
            sb.append("入库日期格式错误(应为yyyy-MM-dd)\r\n");
        }

        //校验供应商
        if(StringUtils.isNotBlank(map.get("surpName"))) {
            map.put("surpNum", querySupplier(map.get("surpName")));
        }
        return sb.toString();
    }

    /**
     * 根据仓库号获取仓库
     * @Title: queryWareHouse
     * @param wareHouseNo wareHouseNo : 仓库号
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> queryWareHouse(String wareHouseNo) {
        //查询仓库
        if(wareHouseCacheList.isEmpty()) {
            EiInfo invoke = SiUtils.invoke(null, "SIWH01", "queryWareHouse", null);
            if (invoke.getStatus() >= 0 && invoke.getBlock("result") != null && invoke.getBlock("result").getRowCount() > 0) {
                wareHouseCacheList = invoke.getBlock(EiConstant.resultBlock).getRows();
            }
        }
        //获取指定仓库
        return wareHouseCacheList.stream().filter(map -> wareHouseNo.equals(map.get("wareHouseNo"))).findFirst().orElse(null);
    }

    /**
     * 获取指定小代码项
     * @Title: queryDataCodeItem
     * @param codeSet codeSet : 代码
     * @param itemName itemName : 中文名称
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> queryDataCodeItem(String codeSet, String itemCode, String itemName) {
        if(dataCodeCacheMap.isEmpty() || dataCodeCacheMap.get(codeSet) == null) {
            List<Map<String, String>> dataCodes = CommonUtils.getDataCode(codeSet);
            dataCodeCacheMap.put(codeSet, dataCodes);
        }
        List<Map<String, String>> items = dataCodeCacheMap.get(codeSet);
        if(StringUtils.isNotBlank(itemCode)) {
            return items.stream().filter(codeMap -> codeMap.get("value").equals(itemCode)).findFirst().orElse(null);
        }

        if(StringUtils.isNotBlank(itemName)) {
            return items.stream().filter(codeMap -> codeMap.get("label").equals(itemName)).findFirst().orElse(null);
        }
        return null;
    }

    /**
     * 校验日期的格式
     * @Title: checkDate
     * @param dateStr dateStr
     * @return boolean
     * @throws
     **/
    private static boolean checkDate(String dateStr) {
        try {
            DateUtils.toDate(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据供应商名称获取供应商
     * @Title: querySupplier
     * @param supplier supplier
     * @return java.lang.String
     * @throws
     **/
    private String querySupplier(String supplier) {
        if(supplierCacheList.isEmpty()) {
            EiInfo invoke = SiUtils.invoke(null, "SITY02", "selectSupplier", null);
            if (invoke.getStatus() >= 0 && invoke.getBlock("supplier") != null && invoke.getBlock("supplier").getRowCount() > 0) {
                supplierCacheList = invoke.getBlock("supplier").getRows();
            }
        }
        Map<String, String> supplierMap = supplierCacheList.stream().filter(map -> supplier.equals(map.get("supplierName"))).findFirst().orElse(null);
        return supplierMap == null ? "" : supplierMap.get("supplierCode");
    }

    /**
     * 清理缓存
     * @Title: clearCache
     * @return void
     * @throws
     **/
    private void clearCache() {
        dataCodeCacheMap.clear();
        wareHouseCacheList.clear();
        supplierCacheList.clear();
    }

    /**
     * 保存导入数据
     * @Title: saveData
     * @param list list : excel导入数据集合
     * @return java.lang.String
     * @throws
     **/
    @Override
    public String saveData(List<Object> list) {
        if(list.isEmpty()) { return null; }
        /**1.数据处理**/
        List<Map> dataList = JSON.parseArray(JSON.toJSONString(list), Map.class);
        Map<Object, List<Map>> groupMap = dataList.stream().collect(Collectors.groupingBy(map -> map.get("wareHouseNo")));
        /**2.构建入库单和入库单明细**/
        for (Object wareHouseNo : groupMap.keySet()) {
            List<Map> enterMatList = groupMap.get(wareHouseNo);
            /**按供应商分组**/
            Map<Object, List<Map>> supMap = enterMatList.stream().collect(Collectors.groupingBy(map -> map.get("surpName")));
            for (Object supplierName : supMap.keySet()) {
                /**构建入库主单据**/
                List<Map> supMatList = supMap.get(supplierName);
                SiEnter enter = new SiEnter(); List<SiEnterDetail> detailList = new ArrayList<>();
                enter.fromMap(enterMatList.get(0));
                enter.setRecCreator(UserSession.getLoginName());
                enter.setRecCreateTime(DateUtils.curDateTimeStr19());
                enter.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
                enter.setId(UUID.randomUUID().toString());
                enter.setEnterBillNo(SerialNoUtils.generateSerialNo("si_enter", "EBN"));
                enter.setBillMaker(UserSession.getLoginName());
                enter.setBillMakerName(UserSession.getLoginCName());
                enter.setBillMakeTime(DateUtils.curDateTimeStr19());
                enter.setBatchNo("BCN" + DateUtils.curDateTimeStr14());
                String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
                if(!SiUtils.toBoolean(hasCheck)) {
                    enter.setEnterTime(DateUtils.curDateTimeStr19());
                }

                /**构建入库明细**/
                for (Map map : supMatList) {
                    /**处理重复数据**/
                    boolean flag = detailList.stream().anyMatch(d -> {
                        boolean repeatFlag = d.getMatNum().equals(map.get("matNum"));
                        if(repeatFlag) {
                            double enterNum = NumberUtils.toDouble(map.get("enterNum"));
                            double enterPrice = NumberUtils.toDouble(map.get("enterPrice"));
                            d.setEnterNum(d.getEnterNum() + enterNum);
                            d.setEnterAmount(d.getEnterAmount() + SiUtils.cal(enterNum, enterPrice, SiConstant.MATH_MULTI));
                            enter.setTotalNum(enter.getTotalNum() + enterNum);
                            enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), SiUtils.cal(enterNum, enterPrice, SiConstant.MATH_MULTI), SiConstant.MATH_ADD));
                        }
                        return repeatFlag;
                    });
                    if(flag) { continue; }

                    SiEnterDetail detail = new SiEnterDetail();
                    detail.fromMap(map);
                    if(SiUtils.isNumeric(detail.getMatNum())) {
                        detail.setMatNum(String.valueOf(Math.round(NumberUtils.toDouble(detail.getMatNum()))));
                    }
                    if(SiUtils.isNumeric(detail.getMatTypeNum())) {
                        detail.setMatTypeNum(String.valueOf(Math.round(NumberUtils.toDouble(detail.getMatTypeNum()))));
                    }
                    detail.setRecCreator(UserSession.getLoginName());
                    detail.setRecCreateTime(DateUtils.curDateTimeStr19());
                    detail.setId(UUID.randomUUID().toString());
                    detail.setEnterBillDetailNo(SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6));
                    detail.setEnterBillNo(enter.getEnterBillNo());
                    detail.setEnterAmount(SiUtils.cal(detail.getEnterNum(), detail.getEnterPrice(), SiConstant.MATH_MULTI));
                    detail.setEnterTime(detail.getEnterDate()+ "08:00:00");
                    detail.setBatchNo(enter.getBatchNo());
                    detailList.add(detail);
                    enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
                    enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), detail.getEnterAmount(), SiConstant.MATH_ADD));
                }
                /**入库**/
                SiUtils.invoke(null,"SIRK0101", "enterStock", new String[]{"enter","enterDetailList"}, enter, detailList);
            }
        }
        clearCache();
        return null;
    }
}
