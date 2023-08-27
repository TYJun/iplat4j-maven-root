package com.baosight.wilp.si.bf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.si.bf.domain.SiScrap;
import com.baosight.wilp.si.bf.domain.SiScrapDetail;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.BeanExchangeUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.kc.domain.SiStorgeDetail;
import com.baosight.wilp.si.kc.domain.SiStorgeRecord;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 报废审核页面Service
 * @ClassName: ServiceSIBF0102
 * @Package com.baosight.wilp.si.common
 * @date: 2022年09月27日 9:33
 *
 * 1.页面加载
 * 2.明细数据查询
 * 3.审核
 */
public class ServiceSIBF0102 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        List<SiScrap> list = dao.query("SIBF01.query", inInfo.getRow("inqu_status", 0));
        inInfo.setRows("inqu_status", list);
        EiInfo query = query(inInfo);
        inInfo.addBlock(query.getBlock("result"));
        return inInfo;
    }

    /**
     * 明细数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.set("inqu_status-0-scrapId", inInfo.get("inqu_status-0-id"));
        return super.query(inInfo, "SIBF01.queryDetail", new SiScrapDetail());
    }

    /**
     * 审核
     * @Title: confirm
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo confirm(EiInfo inInfo) {
        String id = inInfo.getString("inqu_status-0-id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        //更新报废单
        SiScrap scrap = SiScrap.getInstance(id, SiConstant.SCRAP_STATUS_CONFIRM, SiConstant.SCRAP_STATUS_SUBMIT);
        int update = dao.update("SIBF01.update", scrap);
        //报废出库
        if(update > 0) {
            return outStorage(id);
        } else {
            return ValidatorUtils.errorInfo(inInfo, "数据无法审核");
        }
    }

    /**
     * 报废出库
     * <p>
     *     1.获取报废明细
     *     2.根据报废明细构建出库单和出库明细
     *     3.保存出库记录
     *     4.更新库存
     *         4.1校验仓库是否被冻结
     *         4.2遍历分组的报废明细
     *         4.3获取物资批次最新数量
     *         4.4校验物资库存是否大于报废数量
     *         4.5构建库存操作履历
     *         4.6更新库存
     * </p>
     * @Title: outStorage
     * @param id id
     * @return void
     * @throws
     **/
    private EiInfo outStorage(String id) {
        /**1.获取报废明细**/
        List<SiScrapDetail> detailList = dao.query("SIBF01.queryDetail", new HashMap(2) {{
            put("scrapId", id);
        }});

        /**2.根据报废明细构建出库单和出库明细**/
        List<Map<String, Object>> outList = buildOut(detailList);

        /**3.保存出库记录**/
        outList.forEach(map -> {
            dao.insert("SICK01.insert", map.get("out"));
            dao.insert("SICK0101.insert", map.get("outDetailList"));
        });

        /**4.更新库存**/
        List<SiStorgeRecord> records = new ArrayList<>();
        Map<String, List<SiScrapDetail>> collect = detailList.stream().collect(Collectors.groupingBy(detail -> detail.getWareHouseNo()));
        for (String key : collect.keySet()) {
            /**4.1校验仓库是否被冻结**/
            EiInfo outInfo = SiUtils.invoke(null, "SIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, key);
            if("false".equals(outInfo.getString("isCheck"))){
                return ValidatorUtils.errorInfo("仓库不存在或已被冻结");
            }
            /**4.2遍历分组的报废明细**/
            List<SiScrapDetail> list = collect.get(key);
            for (SiScrapDetail scrapDetail : list) {
                /**4.3获取物资批次最新数量**/
                List<SiStorgeDetail> storageDetails = dao.query("SIKC0101.query", new HashMap(4) {{
                    put("batchNos", scrapDetail.getBatchNo());
                    put("wareHouseNo", scrapDetail.getWareHouseNo());
                    put("matNum", scrapDetail.getMatNum());
                }});
                if(CollectionUtils.isEmpty(storageDetails)) {  return ValidatorUtils.errorInfo("物资批次不存在");}
                SiStorgeDetail storageDetail = storageDetails.get(0);
                /**4.4校验物资库存是否大于报废数量**/
                if(SiUtils.cal(scrapDetail.getScrapNum(), storageDetail.getEnterNum(),SiConstant.MATH_SUB) > 0) {
                    return ValidatorUtils.errorInfo("物资批次库存不足，无法报废");
                }
                /**4.5构建库存操作履历**/
                records.add(buildRecord(scrapDetail, storageDetail, outList));
                /**4.6更新库存和库存明细**/
                storageDetail.setEnterNum(scrapDetail.getScrapNum());
                storageDetail.setEnterAmount(scrapDetail.getScrapAmount().doubleValue());
                dao.update("SIKC01.updateStorgeByReduce", storageDetail.toMap());
                dao.update("SIKC0101.updateStorgeDetailByReduce", storageDetail);
            }
        }
        /**5.保存履历**/
        SiUtils.invoke(null,"SIKC02","insert",new String[]{"recordList"},records);
        return new EiInfo();
    }

    /**
     * 根据报废明细，构建出库单和出库单明细
     * @Title: buildOut
     * @param detailList detailList
     * @return java.util.List<java.util.Map<java.lang.String,com.baosight.iplat4j.core.data.DaoEPBase>>
     * @throws
     **/
    private List<Map<String, Object>> buildOut(List<SiScrapDetail> detailList) {
        List<Map<String, Object>> list = new ArrayList<>();
        //按仓库分组
        Map<String, List<SiScrapDetail>> collect = detailList.stream().collect(Collectors.groupingBy(detail -> detail.getWareHouseNo()));
        //遍历
        collect.forEach((key, value) -> {
            Map<String, Object> resultMap = new HashMap<>(4);
            //构建出库主单据
            SiScrapDetail scrapDetail = value.get(0);
            SiOut siOut = BeanExchangeUtils.mapToOut(new HashMap(8) {{
                put("outType", SiConstant.OUT_TYPE_BF);
                put("originBillNo", scrapDetail.getScrapNo());
                put("originBillType", SiConstant.OUT_RESOURCE_TYPE_BF);
                put("wareHouseNo", scrapDetail.getWareHouseNo());
                put("wareHouseName", scrapDetail.getWareHouseName());
            }});

            String ow = siOut.getOutBillNo().replace("OW", "");
            siOut.setOutBillNo("OW"+ (NumberUtils.toLong(ow) + RandomUtils.nextLong(0,9)));
            resultMap.put("out", siOut);
            //构建出库明细
            List<SiOutDetail> outDetailList = new ArrayList<>();
            value.forEach(siScrapDetail -> {
                outDetailList.add(BeanExchangeUtils.scrapDetailToOutDetail(siScrapDetail, siOut));
            });
            resultMap.put("outDetailList", outDetailList);
            list.add(resultMap);
        });
        return list;
    }

    /**
     * 构建库存操作履历
     * @Title: buildRecord
     * @param scrapDetail scrapDetail
     * @param storageDetail storageDetail
     * @param outList outList
     * @return com.baosight.wilp.si.kc.domain.SiStorgeRecord
     * @throws
     **/
    private SiStorgeRecord buildRecord(SiScrapDetail scrapDetail, SiStorgeDetail storageDetail, List<Map<String, Object>> outList) {
        SiOut out = getSiOut(scrapDetail.getWareHouseNo(), outList);
        SiStorgeRecord record = new SiStorgeRecord();
        record.fromMap(scrapDetail.toMap());
        record.setId(UUID.randomUUID().toString());
        record.setOriginBillNo(out.getOutBillNo());
        record.setOriginBillType(out.getOutType());
        record.setOriginBillTypeName(out.getOutTypeName());
        record.setPrice(scrapDetail.getEnterPrice());
        record.setBeforeNum(storageDetail.getEnterNum());
        record.setBeforeAmount(storageDetail.getEnterAmount());
        record.setAfterNum(SiUtils.cal(storageDetail.getEnterNum(), scrapDetail.getScrapNum(), SiConstant.MATH_SUB));
        record.setAfterAmount(SiUtils.cal(storageDetail.getEnterAmount(), scrapDetail.getScrapAmount(), SiConstant.MATH_SUB));
        return record;
    }

    /**
     * 获取出库单
     * @Title: getSiOut
     * @param wareHouseNo wareHouseNo
     * @param outList outList
     * @return com.baosight.wilp.si.ck.domain.SiOut
     * @throws
     **/
    private SiOut getSiOut(String wareHouseNo, List<Map<String, Object>> outList) {
        for (Map<String, Object> map : outList) {
            SiOut out = (SiOut) map.get("out");
            if(wareHouseNo.equals(out.getWareHouseNo())) {
                return out;
            }
        }
        return new SiOut();
    }
}
