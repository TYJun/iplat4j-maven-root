package com.baosight.wilp.fa.da.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.parameters.P;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 固定资产档案详情类.
 * 固定资产档案详情初始化方法.
 * 固定资产录入方法.
 * 通过Excel表格导入固定资产信息.
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022/10/9 17:34
 */
public class ServiceFADA0101 extends ServiceBase {
    /**
     * 固定资产档案详情初始化方法.
     * 1.获取操作类型.
     * 1.1.录入分支，返回创建时间和创建人.
     * 1.2.编辑分支，通过固定资产id返回固定资产详情.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/5/30 17:01
     * @version v5.0.0
     */
    @Override
    public EiInfo initLoad(EiInfo info) {
        // 1.获取操作类型
        String type = info.getString("type");
        switch (type) {
            // 1.1.录入分支，返回创建时间和创建人
            case "enter":
                String manufacturerNatyCode = "156";
                String faInfoId = info.getString("faInfoId");
                if (StringUtils.isNotEmpty(faInfoId)) {
                    List<Map<String, Object>> faInfoDOList = dao.query("FADA01.query", new HashMap<String, Object>() {{
                        put("faInfoId", faInfoId);
                    }});
                    if (CollectionUtils.isNotEmpty(faInfoDOList)) {
                        manufacturerNatyCode = StringUtils.isNotEmpty((String) faInfoDOList.get(0).get("manufacturerNatyCode")) ? (String) faInfoDOList.get(0).get("manufacturerNatyCode") : "156";
                        faInfoDOList.get(0).put("manufacturerNatyCode", manufacturerNatyCode);
                        info.setRows("info", faInfoDOList);
                        info.set("purchaseDept", faInfoDOList.get(0).get("purchaseDept"));
                        info.set("surpNum", faInfoDOList.get(0).get("surpNum"));
                        info.set("surpName", faInfoDOList.get(0).get("surpName"));
                        info.set("buyDate", faInfoDOList.get(0).get("buyDate"));
                        info.set("goodsTypeCode", faInfoDOList.get(0).get("goodsTypeCode"));
                        info.set("goodsTypeName", faInfoDOList.get(0).get("goodsTypeName"));
                        info.set("installLocationNum", faInfoDOList.get(0).get("installLocationNum"));
                        info.set("installLocation", faInfoDOList.get(0).get("installLocation"));
                        info.set("deptNum", faInfoDOList.get(0).get("confirmDeptNum"));
                        info.set("deptName", faInfoDOList.get(0).get("confirmDeptName"));
                    }
                }
                Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
                info.set("info-0-recCreateName", staffByUserId.get("name"));
                info.set("info-0-recCreateTime", DateUtils.curDateTimeStr19());
                info.set("info-0-enterNum", 1);
                info.set("info-0-manufacturerNatyCode", manufacturerNatyCode);
                info.set("info-0-residualRate", 0);
                break;
            // 1.2.编辑分支，通过固定资产id返回固定资产详情
            case "edit":
                List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
                info.set("build", faInfoInfoList.get(0).get("build"));
                info.set("floor", faInfoInfoList.get(0).get("floor"));
                info.set("installLocation", faInfoInfoList.get(0).get("installLocation"));
                info.set("installLocationNum", faInfoInfoList.get(0).get("installLocationNum"));
                info.set("info-0-faInfoId", faInfoInfoList.get(0).get("faInfoId"));
                info.set("info-0-goodsNum", faInfoInfoList.get(0).get("goodsNum"));
                info.set("info-0-goodsName", faInfoInfoList.get(0).get("goodsName"));
                info.set("info-0-contractNo", faInfoInfoList.get(0).get("contractNo"));
                info.set("info-0-goodsTypeCode", faInfoInfoList.get(0).get("goodsTypeCode"));
                info.set("info-0-goodsTypeCode_textField", faInfoInfoList.get(0).get("goodsTypeName"));
                info.set("info-0-goodsClassifyCode", faInfoInfoList.get(0).get("goodsClassifyCode"));
                info.set("info-0-goodsClassifyName", faInfoInfoList.get(0).get("goodsClassifyName"));
                info.set("info-0-rfidCode", faInfoInfoList.get(0).get("rfidCode"));
                info.set("info-0-model", faInfoInfoList.get(0).get("model"));
                info.set("info-0-unitName", faInfoInfoList.get(0).get("unitName"));
                info.set("info-0-deptNum", faInfoInfoList.get(0).get("deptNum"));
                info.set("info-0-deptNum_textField", faInfoInfoList.get(0).get("deptName"));
                info.set("info-0-manufacturer", faInfoInfoList.get(0).get("manufacturer"));
                info.set("info-0-surpName", faInfoInfoList.get(0).get("surpName"));
                info.set("info-0-buyDate", faInfoInfoList.get(0).get("buyDate"));
                info.set("info-0-useDate", faInfoInfoList.get(0).get("useDate"));
                info.set("info-0-statusCode", faInfoInfoList.get(0).get("statusCode"));
                info.set("info-0-statusCodeMean", faInfoInfoList.get(0).get("statusCodeMean"));
                info.set("info-0-build", faInfoInfoList.get(0).get("build"));
                info.set("info-0-floor", faInfoInfoList.get(0).get("floor"));
                info.set("info-0-installLocation", faInfoInfoList.get(0).get("installLocation"));
                info.set("info-0-installLocationNum", faInfoInfoList.get(0).get("installLocationNum"));
                info.set("info-0-residualRate", faInfoInfoList.get(0).get("residualRate"));
                info.set("info-0-estimatedResidualValue", faInfoInfoList.get(0).get("estimatedResidualValue"));
                info.set("info-0-useYears", faInfoInfoList.get(0).get("useYears"));
                info.set("info-0-amount", faInfoInfoList.get(0).get("amount"));
                info.set("info-0-price", faInfoInfoList.get(0).get("price"));
                info.set("info-0-buyCost", faInfoInfoList.get(0).get("buyCost"));
                info.set("info-0-monthDepreciation", faInfoInfoList.get(0).get("monthDepreciation"));
                info.set("info-0-netAssetValue", faInfoInfoList.get(0).get("netAssetValue"));
                info.set("info-0-estimateCost", faInfoInfoList.get(0).get("estimateCost"));
                info.set("info-0-fundsSource", faInfoInfoList.get(0).get("fundsSource"));
                info.set("info-0-finaceClassNum", faInfoInfoList.get(0).get("finaceClassNum"));
                info.set("info-0-invoiceNo", faInfoInfoList.get(0).get("invoiceNo"));
                info.set("info-0-inAccountDate", faInfoInfoList.get(0).get("inAccountDate"));
                info.set("info-0-invoiceDate", faInfoInfoList.get(0).get("invoiceDate"));
                info.set("info-0-deviceTypeCode", faInfoInfoList.get(0).get("deviceTypeCode"));
                info.set("info-0-deviceCode", faInfoInfoList.get(0).get("deviceCode"));
                info.set("info-0-remark", faInfoInfoList.get(0).get("remark"));
                info.set("info-0-archiveFlag", faInfoInfoList.get(0).get("archiveFlag"));
                info.set("info-0-recCreateName", faInfoInfoList.get(0).get("recCreateName"));
                info.set("info-0-recCreateTime", faInfoInfoList.get(0).get("recCreateTime"));
                break;
        }
        return info;
    }

    /**
     * 资产待入确认表自动批量生成卡片
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/12 14:26
     */
    public EiInfo saveFaInfo(EiInfo info) throws ParseException {
        // 获取用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        // 获取操作类型
        String type = info.getString("type");
        // 创建一个数组用于存储实体类
        List<FaInfoDO> faInfoDOList = new ArrayList<>();
        Map<String, Object> param = info.getRow("info", 0);
        param.put("goodsCategoryName", param.get("goodsCategoryCode_textField"));
        param.put("installLocation", param.get("confirmLocationNum_textField"));
        param.put("installLocationNum", param.get("confirmLocationNum"));
        param.put("inAccountStatus", 0);
        switch (type) {
            case "enter":
                String goodsNum = OneSelfUtils.privateCreateCode((String) param.get("goodsClassifyCode"));
                Integer amount = Integer.valueOf(String.valueOf(param.get("enterNum")));
                String goodsCategoryCode = (String) param.get("goodsCategoryCode");
                String goodsClassifyCode = (String) param.get("goodsClassifyCode");
                String goodsTypeCode = (String) param.get("goodsTypeCode");
                if ("B".equals(goodsCategoryCode.substring(0,1))){
                    goodsCategoryCode = goodsCategoryCode.replace("B","A08");
                    goodsClassifyCode = goodsClassifyCode.replace("B","A08");
                    goodsTypeCode = goodsTypeCode.replace("B","A08");
                } else {
                    goodsCategoryCode = "A" + goodsCategoryCode.substring(1);
                    goodsClassifyCode = "A" + goodsClassifyCode.substring(1);
                    goodsTypeCode = "A" + goodsTypeCode.substring(1);
                }
                param.put("goodsCategoryCode", goodsCategoryCode);
                param.put("goodsClassifyCode", goodsClassifyCode);
                param.put("goodsTypeCode", goodsTypeCode);
                param.put("spec", param.get("matSpec"));
                param.put("price", param.get("enterPrice"));
                param.put("deptNum", param.get("confirmDeptNum"));
                param.put("deptName", param.get("confirmDeptName"));
                param.put("build", param.get("confirmBuild"));
                param.put("floor", param.get("confirmFloor"));
                param.put("room", param.get("confirmRoom"));
                param.put("unitName", info.getString("unitName"));
                param.put("fundingSourceName", info.getString("fundingSourceName"));
                param.put("assetGetWayName", info.getString("assetGetWayName"));
                param.put("assetUseName", info.getString("assetUseName"));
                param.put("deprectName", info.getString("deprectName"));
                param.put("manufacturerNatyName", info.getString("manufacturerNatyName"));
                if ("card".equals(info.getString("createType"))) {
                    if ("01".equals(info.getString("receiveType"))) {
                        param.put("statusCode", "020");
                    } else {
                        param.put("statusCode", "010");
                    }
                } else {
                    param.put("statusCode", "020");
                }
                param.put("assetType", goodsNum.substring(0, 1));
                param.put("netAssetValue", param.get("buyCost"));
                param.put("cardStatus", 0);
                param.put("archiveFlag", 1);
                param.put("lockFlag", 0);
                param.put("surpName", param.get("surpNum_textField"));
//				param.put("purchaseDept", param.get("purchaseDept_textField"));
                param.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
                param.put("recCreator", staffByUserId.get("workNo"));
                param.put("recCreateName", staffByUserId.get("name"));
                param.put("recCreateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (param.get("useDate") != null && !"".equals(param.get("useDate"))) {
                    param.put("useDate", param.get("useDate"));
                } else {
                    param.put("useDate", param.get("acquitvDate"));
                }
                if (param.get("acquitvDate") != null && !"".equals(param.get("acquitvDate"))) {
                    param.put("buyDate", param.get("acquitvDate"));
                }
                for (int i = 0; i < amount; i++) {
                    FaInfoDO faInfoDO = new FaInfoDO();
                    faInfoDO.fromMap(param);
                    faInfoDO.setId(UUID.randomUUID().toString());
                    faInfoDO.setGoodsNum(goodsNum.substring(0, goodsNum.length() - 4) + String.format("%04d", Integer.valueOf(goodsNum.substring(goodsNum.length() - 4)) + i));
                    faInfoDO.setAmount(1);
                    faInfoDO.setOperationType((String) param.get("receiveType"));
                    faInfoDOList.add(faInfoDO);
                }
                Map<String, Object> map = info.getRow("info", 0);
                int update = dao.update("FAQR01.updateConfirmStatus", map);
                info.setMsg("成功" + update + "条记录");
                OneSelfUtils.batchInsert("FADA01.saveFaInfo", faInfoDOList);
                for (FaInfoDO faInfoDO : faInfoDOList) {
                    String nalGoodsClassifyCode = faInfoDO.getGoodsClassifyCode();
                    String nalGoodsTypeCode = faInfoDO.getGoodsTypeCode();
                    switch (nalGoodsTypeCode.substring(0, 1)) {
                        case "B":
                            nalGoodsTypeCode = nalGoodsTypeCode.replace("B", "A08");
                            nalGoodsClassifyCode = nalGoodsClassifyCode.replace("B", "A08");
                            faInfoDO.setGoodsTypeCode(nalGoodsTypeCode);
                            faInfoDO.setGoodsClassifyCode(nalGoodsClassifyCode);
                            break;
                        case "C":
                            nalGoodsTypeCode = nalGoodsTypeCode.replace("C", "A");
                            nalGoodsClassifyCode = nalGoodsClassifyCode.replace("C", "A");
                            faInfoDO.setGoodsTypeCode(nalGoodsTypeCode);
                            faInfoDO.setGoodsClassifyCode(nalGoodsClassifyCode);
                            break;
                    }
                }
                dao.insert("FAAP01.putInStorageAccount", new HashMap<String, Object>() {{
                    put("faInfoDOList", faInfoDOList);
                    put("surpNum", param.get("surpNum"));
                    put("surpName", param.get("surpName"));
                    put("invoiceNo", param.get("invoiceNo"));
                    put("deptNum", param.get("confirmDeptNum"));
                    put("deptName", param.get("confirmDeptName"));
                }});
                // 判断是否是直入直出，如果是直入直出就进行抛帐出库
                if ("card".equals(info.getString("createType"))) {
                    if ("01".equals(info.getString("receiveType"))) {
                        dao.insert("FAAP01.putOutStorageAccountCard", new HashMap<String, Object>() {{
                            put("faInfoDOList", faInfoDOList);
                            put("surpNum", param.get("surpNum"));
                            put("surpName", param.get("surpName"));
                            put("invoiceNo", param.get("invoiceNo"));
                            put("deptNum", param.get("confirmDeptNum"));
                            put("deptName", param.get("confirmDeptName"));
                        }});
                    }
                }
                break;
            case "edit":
                FaInfoDO faInfoDO = new FaInfoDO();
                param.put("id", param.get("faInfoId"));
                param.put("recRevisor", staffByUserId.get("workNo"));
                param.put("recReviseName", staffByUserId.get("name"));
                param.put("recReviseTime", DateUtils.curDateTimeStr19());
                faInfoDO.fromMap(param);
                dao.update("FADA01.editFaInfo", faInfoDO);
                break;
        }
        return info;
    }

    /**
     * 通过Excel表格导入固定资产信息.
     * 1.获取Excel表格信息并插入数据库.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/9 17:35
     */
    public EiInfo saveFaInfoByExcel(EiInfo info) {
        // 1.获取Excel表格信息并插入数据库
        List<Map<String, Object>> faInfoList = info.getBlock("info").getRows();
        dao.insert("FADA01.saveFaInfoByExcel", faInfoList);
        return info;
    }

    // 通过末级编码回显二级和三级编码和名称
    public EiInfo backGoodsCategoryCode(EiInfo info) {
        String goodsCategoryCode = info.getString("goodsCategoryCode");
        String goodsClassifyCode = null;
        if ("B".equals(goodsCategoryCode.substring(0,1))){
            goodsClassifyCode = goodsCategoryCode.substring(0, 3) + "0000";
        } else {
            goodsClassifyCode = goodsCategoryCode.substring(0, 5) + "0000";
        }
        String finalGoodsClassifyCode = goodsClassifyCode;
        List<Map> list = dao.query("FALB01.queryAessettypeInfo", new HashMap<String, Object>() {{
            put("typeCode", finalGoodsClassifyCode);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            info.set("list",list.get(0));
        }
        return info;
    }
}

