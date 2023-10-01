package com.baosight.wilp.fa.ap.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.ap.domain.FaInventoryDetailFinal;
import com.baosight.wilp.fa.bf.domian.FaScrapDetailVO;
import com.baosight.wilp.fa.bf.domian.FaScrapVO;
import com.baosight.wilp.fa.db.domain.FaTransferDetailVO;
import com.baosight.wilp.fa.db.domain.FaTransferVO;
import com.baosight.wilp.fa.qr.domain.FaConfirmDO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产与手持机对接API.
 * App查询固定资产信息(待盘点-0/已盘点-1).
 * App查询固定资产明细信息(待盘点-0/已盘点-1).
 * App对比盘点信息与盘库单返回盘盈盘亏API.
 * 比较两个集合并将两个集合的数据重新组装成三种状态.
 * 通过传入的rfid自动判断盘点状态.
 * 通过传入的固定资产Id自动判断盘点状态.
 * App保存盘点提交信息API(废弃方法，废弃原因多次调用数据库连接).
 * App保存盘点提交信息API.
 * 固定资产医院支持使用类型（00-兼容模式、10-RFID、20-QRcode）.
 * 自动获取配置的ip地址.
 * App获取固定资产盘点状态.
 * App获取固定资产盘点状态.
 * 自动获取配置的url地址.
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年06月14日 10:07
 */
public class ServiceFAAP01 extends ServiceBase {

    /**
     * App查询固定资产盘点信息(待盘点-0/已盘点-1).
     * 1.通过工号判断用户角色，再返回用户能查看的科室.
     * 2.通过盘点科室和盘点地点查询固定资产信息并返回.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/14 11:07
     * @version 5.0.0
     */
    public EiInfo queryFaInventoryInfo(EiInfo info) {
        // 1.通过工号判断用户角色，再返回用户能查看的科室
//		String specifyDept = OneSelfUtils.specifyDept(info.getString("workNo"));
//		if (StringUtils.isNotEmpty(specifyDept)) {
//			info.set("inventoryDeptName", specifyDept);
//		}
        // 2.通过盘点科室和盘点地点查询固定资产信息并返回
        List<Map<String, Object>> queryFaInventoryInfoList = dao.query("FAAP01.queryFaInventoryInfo", info.getAttr());
        info.setRows("result", OneSelfUtils.transitionResult(queryFaInventoryInfoList));
        return info;
    }

    /**
     * App查询固定资产明细信息(待盘点-0/已盘点-1).
     * 1.通过盘点单号查询固定资产详细信息.
     * 如果已盘点则查询已盘点表
     * 如果未盘点则查询未盘点表
     * 2.遍历固定资产详细信息获得rfid.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/14 11:37
     * @version 5.0.0
     */
    public EiInfo queryFaInventoryDetailInfo(EiInfo info) {
        List<String> rfidList = new ArrayList<>();
        // 1.通过盘点单号查询固定资产详细信息
        List<Map<String, Object>> queryFaInventoryDetailFinalInfoList = dao.query("FAAP01.queryFaInventoryDetailFinalInfo", info.getAttr());
        List<Map<String, Object>> queryFaInventoryDetailInfoList = dao.query("FAAP01.queryFaInventoryDetailInfo", info.getAttr());
        if (CollectionUtils.isNotEmpty(queryFaInventoryDetailFinalInfoList)) {
            info.setRows("result", queryFaInventoryDetailFinalInfoList);
            List<Map<String, Object>> losses = queryFaInventoryDetailFinalInfoList.stream().filter(map -> "-1".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> normal = queryFaInventoryDetailFinalInfoList.stream().filter(map -> "0".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> profit = queryFaInventoryDetailFinalInfoList.stream().filter(map -> "1".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> none = queryFaInventoryDetailFinalInfoList.stream().filter(map -> "未盘点".equals(map.get("inventoryFlagMean"))).collect(Collectors.toList());
            Map<String, Object> map = new HashMap<>();
            map.put("profit", OneSelfUtils.transitionResult(profit));
            map.put("normal", OneSelfUtils.transitionResult(normal));
            map.put("losses", OneSelfUtils.transitionResult(losses));
            map.put("none", OneSelfUtils.transitionResult(none));
            info.setAttr(map);
        } else {
            info.setRows("result", OneSelfUtils.transitionResult(queryFaInventoryDetailInfoList));
            List<Map<String, Object>> losses = queryFaInventoryDetailInfoList.stream().filter(map -> "-1".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> normal = queryFaInventoryDetailInfoList.stream().filter(map -> "0".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> profit = queryFaInventoryDetailInfoList.stream().filter(map -> "1".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
            List<Map<String, Object>> none = queryFaInventoryDetailInfoList.stream().filter(map -> "未盘点".equals(map.get("inventoryFlagMean"))).collect(Collectors.toList());
            Map<String, Object> map = new HashMap<>();
            map.put("profit", OneSelfUtils.transitionResult(profit));
            map.put("normal", OneSelfUtils.transitionResult(normal));
            map.put("losses", OneSelfUtils.transitionResult(losses));
            map.put("none", OneSelfUtils.transitionResult(none));
            info.setAttr(map);
        }
        // 2.遍历固定资产详细信息获得rfid
        if (CollectionUtils.isNotEmpty(queryFaInventoryDetailInfoList)) {
            for (Map<String, Object> queryFaInventoryDetailInfoMap : queryFaInventoryDetailInfoList) {
                String rfidCode = (String) queryFaInventoryDetailInfoMap.get("rfidCode");
                if (StringUtils.isNotEmpty(rfidCode)) {
                    rfidList.add(rfidCode);
                }
            }
        }
        info.set("rfid", rfidList);
        return info;
    }

    /**
     * App对比盘点信息与盘库单返回盘盈盘亏API.
     * 1.扫描rfid模式.
     * 2.扫描二维码模式.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/15 15:40
     * @version 5.0.0
     * @todo 用枚举类来表示盘盈盘亏正常 [这个问题下次迭代时处理]
     */
    public EiInfo checkInventoryInfo(EiInfo info) {
        // 获取盘点类型(rfid:rfid模式,QRcode:二维码模式)
        String inventorytype = info.getString("inventorytype");
        switch (inventorytype) {
            /*
             * 1.扫描rfid模式
             * 前端传值为list集合，盘点单中的为list集合
             * scanlist和detaillist相同——状态正常
             * scanlist存在detaillist不存在——盘盈profit
             * scanlist不存在detaillist存在——盘亏losses
             */
            case "rfid":
                /*
                 * 获取前端rfid传值
                 * 如果不为空则转化成list
                 */
                List<String> scanRfidList = new ArrayList<>();
                Object scanRfidObj = info.get("scanRfidList");
                if (scanRfidObj != null) {
                    scanRfidList = (List<String>) scanRfidObj;
                }
                scanRfidList = scanRfidList.stream().distinct().collect(Collectors.toList());
                /*
                 * 获取盘点单RFID
                 * 如果不为空则转化成list
                 */
                List<String> detailRfidList = new ArrayList<>();
                List<Map<String, Object>> detailRfidMapList = dao.query("FAAP01.queryDetailByInventoryNoRfid", info.getAttr());
                if (CollectionUtils.isNotEmpty(detailRfidMapList)) {
                    for (Map<String, Object> detailRfidMap : detailRfidMapList) {
                        detailRfidList.add((String) detailRfidMap.get("rfidCode"));
                    }
                }
                /**
                 * 两个集合进行比较并返回前端
                 * scanlist和detaillist相同——状态正常
                 * scanlist存在detaillist不存在——盘盈profit
                 * scanlist不存在detaillist存在——盘亏losses
                 */
                info.setAttr(compareList(scanRfidList, detailRfidList, info.getString("inventoryNo"), "rfid"));
                break;
            /*
             * 2.扫描二维码模式
             * 前端传值为list集合，盘点单中的为list集合
             * scanlist和detaillist相同——状态正常
             * scanlist存在detaillist不存在——盘盈profit
             * scanlist不存在detaillist存在——盘亏losses
             */
            case "QRcode":
                /*
                 * 获取前端二维码传值
                 * 如果不为空则转化成list
                 */
                List<String> scanQRcodeList = new ArrayList<>();
                Object scanQRcodeObj = info.get("scanQRcodeList");
                if (scanQRcodeObj != null) {
                    scanQRcodeList = (List<String>) scanQRcodeObj;
                }
                scanQRcodeList = scanQRcodeList.stream().distinct().collect(Collectors.toList());
                /*
                 * 获取盘点单固定资产Id
                 * 如果不为空则转化成list
                 */
                List<String> detailQRcodeList = new ArrayList<>();
                List<Map<String, Object>> detailQRcodeMapList = dao.query("FAAP01.queryDetailByInventoryNoQR", info.getAttr());
                for (Map<String, Object> detailQRcodeMap : detailQRcodeMapList) {
                    detailQRcodeList.add((String) detailQRcodeMap.get("goodsNum"));
                }
                /**
                 * 两个集合进行比较并返回前端
                 * scanlist和detaillist相同——状态正常
                 * scanlist存在detaillist不存在——盘盈profit
                 * scanlist不存在detaillist存在——盘亏losses
                 */
                info.setAttr(compareList(scanQRcodeList, detailQRcodeList, info.getString("inventoryNo"), "QRcode"));
                break;
        }
        return info;
    }

    /**
     * 比较两个集合并将两个集合的数据重新组装成三种状态.
     * 前端传值为list集合，盘点单中的为list集合
     * scanlist和detaillist相同——状态正常same
     * scanlist存在detaillist不存在——盘盈profit
     * scanlist不存在detaillist存在——盘亏losses
     *
     * @param scanList
     * @param detailList
     * @return java.util.List<java.lang.String>
     * @author zhaowei
     * @date 2022/6/15 16:23
     * @version 5.0.0
     */
    public Map<String, Object> compareList(List<String> scanList, List<String> detailList, String inventoryNo, String inventorytype) {
        Map<String, Object> compareMap = new HashMap<>(4);
        // 正常的集合
        List<String> sameList = new ArrayList();
        // 盘盈的集合
        List<String> profitList = new ArrayList(scanList);
        // 盘亏的集合
        List<String> lossesList = new ArrayList(detailList);
        /*
         * 1.通过过滤获取结果相同的集合
         */
        scanList.stream().forEach(scan -> {
            if (detailList.contains(scan)) {
                sameList.add(scan);
            }
        });
        /*
         * 2.通过集合过滤获取不同的集合
         * scanlist存在detaillist不存在——盘盈profit
         * scanlist不存在detaillist存在——盘亏losses
         */
        profitList.removeAll(detailList);
        lossesList.removeAll(scanList);
        List<Map<String, Object>> sameMapList = new ArrayList<>();
        List<Map<String, Object>> profitMapList = new ArrayList<>();
        List<Map<String, Object>> lossesMapList = new ArrayList<>();
        /*
         * 对不同扫描类型
         * 进行参数封装
         * 正常，盘盈，盘亏
         */
        switch (inventorytype) {
            /*
             * rfid模式
             * 通过rfid芯片对比盘库单返回盘库结果
             * 构建盘库正常的集合
             * 构建盘库盘盈的集合
             * 构建盘库盘亏的集合
             */
            case "rfid":
                sameMapList = queryFaInfoByRfid(null, sameList, "normal", inventoryNo);
                profitMapList = queryFaInfoByRfid(null, profitList, "profit", inventoryNo);
                lossesMapList = queryFaInfoByRfid(sameList, lossesList, "losses", inventoryNo);
                saveScanRfid(sameMapList, profitMapList, lossesMapList, inventoryNo);
                break;
            /*
             * 二维码模式
             * 通过固定资产Id对比盘库单返回盘库结果
             * 构建盘库正常的集合
             * 构建盘库盘盈的集合
             * 构建盘库盘亏的集合
             */
            case "QRcode":
                sameMapList = queryFaInfoByGoodsNum(sameList, "normal", inventoryNo);
                profitMapList = queryFaInfoByGoodsNum(profitList, "profit", inventoryNo);
                lossesMapList = queryFaInfoByGoodsNum(lossesList, "losses", inventoryNo);
                saveScanQRcode(sameMapList, profitMapList, lossesMapList, inventoryNo);
                break;
        }
        /*
         * 将构建好的集合匹配类型返回
         * normal：正常
         * profit：盘盈
         * losses：盘亏
         */
        compareMap.put("normal", sameMapList);
        compareMap.put("profit", profitMapList);
        compareMap.put("losses", lossesMapList);
        return compareMap;
    }

    /**
     * 扫描芯片保存
     *
     * @param sameMapList
     * @param profitMapList
     * @param lossesMapList
     * @param inventoryNo
     * @author zhaowei
     * @date 2023/3/22 9:45
     */
    public void saveScanRfid(List<Map<String, Object>> sameMapList, List<Map<String, Object>> profitMapList, List<Map<String, Object>> lossesMapList, String inventoryNo) {
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>(sameMapList);
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>(profitMapList);
        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>(lossesMapList);
        // 相同的集合正常的集合
        List<Map<String, Object>> list4 = list1.stream().filter(map -> !"1".equals(map.get("inventoryCode"))).collect(Collectors.toList());
        // 相同的集合盘盈的集合
        List<Map<String, Object>> list5 = list1.stream().filter(map -> "1".equals(map.get("inventoryCode"))).collect(Collectors.toList());
        list4.stream().forEach(map -> map.put("inventoryFlagCode", 0));
        list2.addAll(list5);
        list2 = list2.stream().distinct().collect(Collectors.toList());
        list2.stream().forEach(map -> map.put("inventoryFlagCode", 1));
        list3.stream().forEach(map -> map.put("inventoryFlagCode", -1));
        List<Map<String, Object>> inventoryList = new ArrayList<>();
        inventoryList.addAll(list2);
        inventoryList.addAll(list3);
        inventoryList.addAll(list4);
        Map<String, Object> map = new HashMap<>();
        map.put("inventoryNo", inventoryNo);
        map.put("inventoryList", inventoryList);
        List<Map<String, Object>> faInventoryInfo = dao.query("FAAP01.queryFaInventoryInfo", map);
        if (CollectionUtils.isNotEmpty(faInventoryInfo)) {
            map.put("faInventoryId", faInventoryInfo.get(0).get("faInventoryId"));
            map.put("spotNum", faInventoryInfo.get(0).get("inventorySpotNum"));
            map.put("spotName", faInventoryInfo.get(0).get("inventorySpotName"));
            dao.delete("FAAP01.deleteDetail", map);
            dao.insert("FAAP01.insertDetail", map);
        }
    }

    /**
     * 扫描二维码保存
     *
     * @param sameMapList
     * @param profitMapList
     * @param lossesMapList
     * @param inventoryNo
     * @author zhaowei
     * @date 2023/4/3 18:03
     */
    public void saveScanQRcode(List<Map<String, Object>> sameMapList, List<Map<String, Object>> profitMapList, List<Map<String, Object>> lossesMapList, String inventoryNo) {
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>(sameMapList);
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>(profitMapList);
        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>(lossesMapList);
        list1.stream().forEach(map -> map.put("inventoryFlagCode", 0));
        list2.stream().forEach(map -> map.put("inventoryFlagCode", 1));
        list3.stream().forEach(map -> map.put("inventoryFlagCode", -1));
        List<Map<String, Object>> inventoryList = new ArrayList<>();
        inventoryList.addAll(list1);
        inventoryList.addAll(list2);
        inventoryList.addAll(list3);
        Map<String, Object> map = new HashMap<>();
        map.put("inventoryNo", inventoryNo);
        map.put("inventoryList", inventoryList);
        dao.delete("FAAP01.deleteDetail", map);
        dao.insert("FAAP01.insertDetail", map);
    }

    /**
     * 通过传入的rfid自动判断盘点状态
     * 通过rfid芯片对比盘库单返回盘库结果
     * 构建盘库正常的集合
     * 构建盘库盘盈的集合
     * 构建盘库盘亏的集合
     *
     * @param rfidList
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author zhaowei
     * @date 2022/6/15 17:30
     * @version 5.0.0
     */
    public List<Map<String, Object>> queryFaInfoByRfid(List<String> normalList, List<String> rfidList, String type, String inventoryNo) {
        /*
         * 构建查询条件集合
         */
        Map<String, Object> params = new HashMap<>(16);
        params.put("type", type);
        params.put("rfid", rfidList);
        params.put("normal", normalList);
        params.put("inventoryNo", inventoryNo);
        List<Map<String, Object>> resultList = new ArrayList<>();
        /*
         * 通过rfid芯片查询对应的固定资产信息
         * 1.不存在芯片返回为null
         * 2.存在芯片
         * 通过芯片返回固定资产结果
         * 如果结果存在
         * 则通过盘点类型进行结果封装
         * 如果结果不存在
         * 返回为null
         */
        if (CollectionUtils.isNotEmpty(rfidList)) {
            List<Map<String, Object>> faInfoList;
            /*
             * 通过盘点类型进行结果封装
             */
            switch (type) {
                case "normal":
                    faInfoList = dao.query("FAAP01.queryFaInfoByRfid", params);
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        faInfoMap.put("inventoryFlag", "已盘点");
                        resultList.add(faInfoMap);
                    }
                    break;
                case "profit":
                    faInfoList = dao.query("FAAP01.queryFaInfoByRfidProfit", params);
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        faInfoMap.put("inventoryFlag", "盘盈");
                        faInfoMap.put("inventoryCode", "1");
                        resultList.add(faInfoMap);
                    }
                    break;
                case "losses":
                    // 所有盘亏的信息
                    if (normalList.isEmpty()) {
                        faInfoList = dao.query("FAAP01.queryFaInfoByRfidLosses", params);
                    } else {
                        faInfoList = dao.query("FAAP01.queryFaInfoNotInRfid", params);
                        // 存在芯片的时候 -- 芯片盘亏
                        faInfoList.stream().forEach(map -> {
                            if (map.get("rfidCode") != null) {
                                map.put("inventoryFlag", "盘亏");
                            }
                        });
                        // 在当前科室但是没有绑定芯片
                        faInfoList.stream().forEach(map -> {
                            if (map.get("rfidCode") == null) {
                                map.put("inventoryFlag", "盘亏(缺少芯片)");
                                map.put("inventoryFlag", "盘亏");
                            }
                        });
                    }
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        resultList.add(faInfoMap);
                    }
                    break;
            }
        } else {
            // 全部没有绑定芯片
            if ("losses".equals(type)) {
                List<Map<String, Object>> faInfoList;
                if (normalList.isEmpty()) {
                    faInfoList = dao.query("FAAP01.queryFaInfoByRfidLosses", params);
                } else {
                    faInfoList = dao.query("FAAP01.queryFaInfoNotInRfid", params);
                    // 在当前科室但是没有绑定芯片
                    faInfoList.stream().forEach(map -> {
                        if (!normalList.contains(map.get("rfidCode"))) {
                            map.put("inventoryFlag", "盘亏(缺少芯片)");
                            map.put("inventoryFlag", "盘亏");
                        }
                    });
                }
                for (Map<String, Object> faInfoMap : faInfoList) {
                    resultList.add(faInfoMap);
                }
            }
        }
        return OneSelfUtils.transitionResult(resultList);
    }

    /**
     * 通过传入的固定资产Id自动判断盘点状态
     * 通过rfid芯片对比盘库单返回盘库结果
     * 构建盘库正常的集合
     * 构建盘库盘盈的集合
     * 构建盘库盘亏的集合
     *
     * @param goodsNumList
     * @param type
     * @param inventoryNo
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author zhaowei
     * @date 2022/6/27 16:11
     * @version 5.0.0
     */
    public List<Map<String, Object>> queryFaInfoByGoodsNum(List<String> goodsNumList, String type, String inventoryNo) {
        /*
         * 构建查询条件集合
         */
        Map<String, Object> params = new HashMap<>(2);
        params.put("goodsNum", goodsNumList);
        params.put("inventoryNo", inventoryNo);
        List<Map<String, Object>> resultList = new ArrayList<>();
        /*
         * 通过固定资产Id查询对应的固定资产信息
         * 1.不存在固定资产Id返回为null
         * 2.存在固定资产Id
         * 通过固定资产Id返回固定资产结果
         * 如果结果存在
         * 则通过盘点类型进行结果封装
         * 如果结果不存在
         * 返回为null
         */
        if (CollectionUtils.isNotEmpty(goodsNumList)) {
            List<Map<String, Object>> faInfoList;
            if ("profit".equals(type)) {
                faInfoList = dao.query("FAAP01.queryFaInfoByGoodsNumProfit", params);
            } else {
                faInfoList = dao.query("FAAP01.queryFaInfoByGoodsNumByList", params);
            }
            if (CollectionUtils.isEmpty(faInfoList)) {
                return new ArrayList<>();
            }
            /*
             * 通过盘点类型进行结果封装
             */
            switch (type) {
                case "normal":
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        faInfoMap.put("inventoryFlag", "已盘点");
                        resultList.add(faInfoMap);
                    }
                    break;
                case "profit":
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        faInfoMap.put("inventoryFlag", "盘盈");
                        resultList.add(faInfoMap);
                    }
                    break;
                case "losses":
                    for (Map<String, Object> faInfoMap : faInfoList) {
                        faInfoMap.put("inventoryFlag", "盘亏");
                        resultList.add(faInfoMap);
                    }
                    break;
            }
        }
        return OneSelfUtils.transitionResult(resultList);
    }

    /**
     * App保存盘点提交信息API(废弃方法，废弃原因多次调用数据库连接).
     * 通过工单号删除之前的信息
     * 再通过新增类型插入数据
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/14 12:09
     * @version 5.0.0
     */
    public EiInfo saveFaInventoryDetailInfoNew(EiInfo info) {
        List<Map<String, Object>> faInventoryDetailInfoList = new ArrayList<>();
        List<String> normalList = new ArrayList<>();
        List<String> profitList = new ArrayList<>();
        List<String> lossesList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        Object normal = info.get("normal");
        Object profit = info.get("profit");
        Object losses = info.get("losses");
        Object faInventoryDetailInfo = info.get("myarr");
        dao.delete("FAAP01.deleteFaInventoryDetailInfoByInventoryNo", info.getAttr());
        dao.update("FAAP01.updateFaInventoryDetailInfoByInventoryNo", info.getAttr());
        // 盘点正常
        if (normal != "") {
            normalList = (List<String>) normal;
            normalList = normalList.stream().distinct().collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(normalList)) {
                params.clear();
                params.put("inventoryFlag", 0);
                params.put("dealFlag", 1);
                params.put("inventoryNo", info.getString("inventoryNo"));
                params.put("goodsIdList", normalList);
                params.putAll(info.getAttr());
                dao.insert("FAAP01.saveFaInventoryDetailInfo", params);
            }
        }
        // 盘盈
        if (profit != "") {
            profitList = (List<String>) profit;
            profitList = profitList.stream().distinct().collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(profitList)) {
                params.clear();
                params.put("inventoryFlag", 1);
                params.put("dealFlag", 1);
                params.put("inventoryNo", info.getString("inventoryNo"));
                params.put("goodsIdList", profitList);
                params.putAll(info.getAttr());
                dao.insert("FAAP01.saveFaInventoryDetailInfo", params);
            }
        }
        // 盘亏
        if (losses != "") {
            lossesList = (List<String>) losses;
            lossesList = lossesList.stream().distinct().collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(lossesList)) {
                params.clear();
                params.put("inventoryFlag", -1);
                params.put("dealFlag", 1);
                params.put("inventoryNo", info.getString("inventoryNo"));
                params.put("goodsIdList", lossesList);
                params.putAll(info.getAttr());
                dao.insert("FAAP01.saveFaInventoryDetailInfo", params);
            }
        }
        if (faInventoryDetailInfo != "") {
            faInventoryDetailInfoList = (List<Map<String, Object>>) faInventoryDetailInfo;
            for (Map<String, Object> faInventoryDetailFinal : faInventoryDetailInfoList) {
                dao.update("FAAP01.updateFaInventoryDetailInfo", faInventoryDetailFinal);
            }
        }
        return info;
    }

    /**
     * App保存盘点提交信息API.(2023-03-22 废弃)
     * 通过工单号删除之前的信息
     * 再通过新增类型插入数据
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/14 12:09
     * @version 5.0.0
     */
    public EiInfo saveFaInventoryDetailInfoOld(EiInfo info) {
        List<Map<String, Object>> faInventoryDetailInfoList = new ArrayList<>();
        List<String> normalList = new ArrayList<>();
        List<String> profitList = new ArrayList<>();
        List<String> lossesList = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        Object normal = info.get("normal");
        Object profit = info.get("profit");
        Object losses = info.get("losses");
        if (normal != "") {
            normalList = (List<String>) normal;
        }
        if (profit != "") {
            profitList = (List<String>) profit;
        }
        if (losses != "") {
            lossesList = (List<String>) losses;
        }
        Object faInventoryDetailInfo = info.get("myarr");
        for (Map<String, Object> map : (List<Map<String, Object>>) faInventoryDetailInfo) {
            nums.add((String) map.get("goodsNum"));
        }
        List<FaInventoryDetailFinal> queryFaInfo = dao.query("FAAP01.selectFaInfo", nums);
        for (FaInventoryDetailFinal faInfo : queryFaInfo) {
            if (faInventoryDetailInfo != "") {
                faInventoryDetailInfoList = (List<Map<String, Object>>) faInventoryDetailInfo;
                for (Map<String, Object> faInventoryDetailFinal : faInventoryDetailInfoList) {
                    if (normalList.contains(faInventoryDetailFinal.get("goodsNum")) && faInfo.getGoodsNum().equals(faInventoryDetailFinal.get("goodsNum"))) {
                        faInfo.setInventoryFlag("0");
                        faInfo.setDealFlag("1");
                        faInfo.setRemark((String) faInventoryDetailFinal.get("remark"));
                        faInfo.setInventoryNo(info.getString("inventoryNo"));
                        faInfo.setInventoryId(info.getString("inventoryId"));
                        break;
                    }
                    if (profitList.contains(faInventoryDetailFinal.get("goodsNum")) && faInfo.getGoodsNum().equals(faInventoryDetailFinal.get("goodsNum"))) {
                        faInfo.setInventoryFlag("1");
                        faInfo.setDealFlag("1");
                        faInfo.setRemark((String) faInventoryDetailFinal.get("remark"));
                        faInfo.setInventoryNo(info.getString("inventoryNo"));
                        faInfo.setInventoryId(info.getString("inventoryId"));
                        break;
                    }
                    if (lossesList.contains(faInventoryDetailFinal.get("goodsNum")) && faInfo.getGoodsNum().equals(faInventoryDetailFinal.get("goodsNum"))) {
                        faInfo.setInventoryFlag("-1");
                        faInfo.setDealFlag("1");
                        faInfo.setRemark((String) faInventoryDetailFinal.get("remark"));
                        faInfo.setInventoryNo(info.getString("inventoryNo"));
                        faInfo.setInventoryId(info.getString("inventoryId"));
                        break;
                    }
                }
            }
        }
        // 判断盘点单是否已经审批
        info.set("flag", false);
        List<Map<String, Object>> statusList = dao.query("FAAP01.selectFaInventoryDetailInfoStatus", info.getAttr());
        if (CollectionUtils.isNotEmpty(statusList)) {
            String statusCode = (String) statusList.get(0).get("statusCode");
            if ("0".equals(statusCode)) {
                info.set("flag", true);
                dao.delete("FAAP01.deleteFaInventoryDetailInfoByInventoryNo", info.getAttr());
                dao.update("FAAP01.updateFaInventoryDetailInfoByInventoryNo", info.getAttr());
                dao.insert("FAAP01.insertFaInventoryDetailInfo", queryFaInfo);
            }
        }
        return info;
    }

    /**
     * App保存盘点提交信息API
     * 对之前的记录做update操作
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/22 9:26
     */
    public EiInfo saveFaInventoryDetailInfoNo(EiInfo info) {
        List<Map<String, Object>> goodsList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        String inventoryNo = info.getString("inventoryNo");
        Object goods = info.get("goods");
        if (goods != null) {
            goodsList = (List<Map<String, Object>>) goods;
        }
        List<Map<String, Object>> losses = goodsList.stream().filter(map -> "待确认".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
        List<Map<String, Object>> normal = goodsList.stream().filter(map -> "已盘点".equals(map.get("inventoryFlag"))).collect(Collectors.toList());
        losses.stream().forEach(map -> map.put("inventoryFlag", "-1"));
        normal.stream().forEach(map -> map.put("inventoryFlag", "0"));
        list.addAll(losses);
        list.addAll(normal);
        result.put("inventoryNo", inventoryNo);
        // 判断盘点单是否已经审批
        info.set("isPass", true);
        List<Map<String, Object>> statusList = dao.query("FAAP01.selectFaInventoryDetailInfoStatus", info.getAttr());
        if (CollectionUtils.isNotEmpty(statusList)) {
            String statusCode = (String) statusList.get(0).get("statusCode");
            if ("0".equals(statusCode)) {
                info.set("isPass", false);
                for (int i = 0; i < list.size(); i++) {
                    result.put("goodsId", list.get(i).get("goodsId"));
                    result.put("inventoryFlag", list.get(i).get("inventoryFlag"));
                    dao.update("FAAP01.updateFaInventoryDetailInfoByInventoryNo", result);
                }
            }
        }
        return info;
    }

    /**
     * 资产盘点单提交
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/4/3 15:51
     */
    public EiInfo saveFaInventoryDetailInfo(EiInfo info) {
        String inventoryNo = info.getString("inventoryNo");
        String flag = info.getString("flag");
        int i = 0;
        switch (flag) {
            case "checking":
                i = dao.update("FAAP01.updateStatusCodeByInventoryNo", new HashMap<String, Object>() {{
                    put("flag", 1);
                    put("inventoryNo", inventoryNo);
                }});
                break;
            case "checked":
                i = dao.update("FAAP01.updateStatusCodeByInventoryNo", new HashMap<String, Object>() {{
                    put("flag", 99);
                    put("inventoryNo", inventoryNo);
                }});
                break;
        }
        if (i > 0) {
            info.set("isPass", true);
        } else {
            info.set("isPass", false);
        }
        return info;
    }

    /**
     * 切换资产盘点类型以及添加备注
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/29 17:22
     */
    public EiInfo updateFaInventoryDetailStatusAndReason(EiInfo info) {
        String type = info.getString("tabType");
        if (info.get("result") != null) {
            Map<String, Object> result = (Map<String, Object>) info.get("result");
            switch (type) {
                case "profit":
                    result.put("inventoryFlag", "0");
                    break;
                case "losses":
                    result.put("inventoryFlag", "0");
                    break;
                case "normal":
                    result.put("inventoryFlag", "-1");
                    break;
            }
            int i = dao.update("FAAP01.updateFaInventoryDetailStatusAndReason", result);
            if (i > 0) {
                info.setStatus(200);
                info.setMsg("修改成功");
            } else {
                info.setStatus(201);
                info.setMsg("修改失败");
            }
        }
        return info;
    }

    /**
     * 重置当前盘点单
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/29 18:09
     */
    public EiInfo updateFaInventoryDetail(EiInfo info) {
        String inventoryNo = info.getString("inventoryNo");
        List<Map<String, Object>> list = dao.query("FAAP01.queryFaInventoryInfo", new HashMap<String, Object>() {{
            put("inventoryNo", inventoryNo);
        }});
        if (list.size() > 0) {
            dao.delete("FAAP01.deleteDetail", new HashMap<String, Object>() {{
                put("inventoryNo", inventoryNo);
            }});
            Map<String, Object> map = list.get(0);
            map.put("newGoodsLocationNum", map.get("inventorySpotNum"));
            map.put("newGoodsLocation", map.get("inventorySpotName"));
            map.put("dealFlag", "0");
            dao.insert("FAPD01.saveFaInventoryDetailInfo", map);
            info.setStatus(200);
            info.setMsg("重置成功");
        } else {
            info.setStatus(201);
            info.setMsg("重置失败");
        }
        return info;
    }

    /**
     * 固定资产医院支持使用类型（00-兼容模式、10-RFID、20-QRcode）
     * 调用小代码接口
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/14 13:13
     * @version 5.0.0
     */
    public EiInfo gebruikstype(EiInfo info) {
        EiInfo eiInfo = new EiInfo();
        // 定义系统微服务方法
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId, serviceId);
        // 传参为小代码中定义的小代码类型
        eiInfo.set("codeset", "wilp.fa.gebruikstype");
        // 服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) outInfo.get("list");
        info.setRows("result", resultList);
        return info;
    }

    /**
     * 自动获取配置的ip地址
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/11 17:42
     * @version v5.0.0
     */
    public EiInfo automaticallyIp(EiInfo info) {
        EiInfo eiInfo = new EiInfo();
        // 定义系统微服务方法
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId, serviceId);
        // 传参为小代码中定义的小代码类型
        eiInfo.set("codeset", "wilp.fa.automaticallyIp");
        // 服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) outInfo.get("list");
        if (CollectionUtils.isNotEmpty(resultList)) {
            String ip = (String) resultList.get(0).get("value");
            info.set("ip", ip);
        }
        return info;
    }

    /**
     * App获取固定资产盘点状态
     * （000-已盘点、100-资产调拨、200-资产维修、300-资产闲置、400-资产报废）
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/11 17:38
     * @version v5.0.0
     */
    public EiInfo checkStatus(EiInfo info) {
        EiInfo eiInfo = new EiInfo();
        // 定义系统微服务方法
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId, serviceId);
        // 传参为小代码中定义的小代码类型
        eiInfo.set("codeset", "wilp.fa.checkStatus");
        // 服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) outInfo.get("list");
        if (CollectionUtils.isNotEmpty(resultList)) {
            info.setRows("result", resultList);
        }
        return info;
    }

    /**
     * 自动获取配置的url地址
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/11 17:42
     * @version v5.0.0
     */
    public EiInfo automaticallyURL(EiInfo info) {
        EiInfo eiInfo = new EiInfo();
        // 定义系统微服务方法
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId, serviceId);
        // 传参为小代码中定义的小代码类型
        eiInfo.set("codeset", "wilp.fa.automaticallyURL");
        // 服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) outInfo.get("list");
        if (CollectionUtils.isNotEmpty(resultList)) {
            String url = (String) resultList.get(0).get("value");
            info.set("url", url);
        }
        return info;
    }

    /**
     * 自动创建编号
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/11 17:42
     * @version v5.0.0
     */
    public EiInfo autoCreateNum(EiInfo info) {
        EiInfo eiInfo = new EiInfo();
        // 定义系统微服务方法
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId, serviceId);
        // 传参为小代码中定义的小代码类型
        eiInfo.set("codeset", "wilp.fa.autoCreateNum");
        // 服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) outInfo.get("list");
        if (CollectionUtils.isNotEmpty(resultList)) {
            String autoCreateNum = (String) resultList.get(0).get("value");
            info.set("autoCreateNum", autoCreateNum);
        }
        return info;
    }

    // 通过查询资产类别
    public EiInfo CreateNum(EiInfo info) {
        EiInfo eiInfo = OneSelfUtils.invoke(info, "FAAP01", "autoCreateNum");
        Boolean flag = Boolean.valueOf(eiInfo.getString("autoCreateNum"));
        if (flag) {
            OneSelfUtils.privateCreateCode(info.getString("faTypeId"));
        } else {
            OneSelfUtils.publicCreateCode(info.getString("code"));
        }
        return info;
    }

    // 资产中间表
    public EiInfo insertFaConfirm(EiInfo info) {
        Map attr = info.getAttr();
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
        if (attr.get("list") != null) {
            List<Map<String, Object>> faConfirmList = (List<Map<String, Object>>) attr.get("list");
            if (CollectionUtils.isNotEmpty(faConfirmList)) {
                List<FaConfirmDO> collect = faConfirmList.stream().map(map ->
                        {
                            FaConfirmDO faConfirmDO = new FaConfirmDO();
                            String dateTimeStr19 = DateUtils.curDateTimeStr19();
                            map.put("checkDate", dateTimeStr19);
                            map.put("acquitvDate", dateTimeStr19);
                            map.put("recCreateTime", dateTimeStr19);
                            map.put("confirmDate", dateTimeStr19);
                            map.put("deptNum", map.get("userDeptNum"));
                            map.put("deptName", map.get("userDeptName"));
                            map.put("outBillNo", map.get("outBillNo"));
                            faConfirmDO.fromMap(map);
                            faConfirmDO.setModel((String) map.get("matModel"));
                            faConfirmDO.setUnitNum((String) map.get("unit"));
                            faConfirmDO.setId(UUID.randomUUID().toString());
                            faConfirmDO.setRecCreateName((String) staffByUserId.get("name"));
                            faConfirmDO.setTransferType("00");
                            faConfirmDO.setConfirmStatus("00");
                            faConfirmDO.setReceiveType((String) map.get("enterType"));
                            return faConfirmDO;
                        }
                ).collect(Collectors.toList());
                if ("05".equals(collect.get(0).getEnterType())) {
                    // 判断是否为直入直出的退库类型
                    for (FaConfirmDO faConfirmDO : collect) {
                        System.out.println(faConfirmDO.toString());
                    }
                    // 获取退货的物资
                    for (FaConfirmDO faConfirmDO : collect) {
                        if (faConfirmDO.getOutBillNo() != null) {
                            List<String> faInfoIdList = new ArrayList<>();
                            // 针对直入直出查询对应的调拨单
                            List<Map<String, Object>> transferReturn = dao.query("FAAP01.queryTransferByReturn", new HashMap<String, Object>() {{
                                put("billNo", faConfirmDO.getEnterBillNo());
                            }});
                            if (CollectionUtils.isNotEmpty(transferReturn)) {
                                // 存在调拨单 --固定资产发起的退库操作即退货
                                // 修改调拨单中的资产卡片状态 -》 退货中039 -》 逻辑删除000
                                dao.update("FAAP01.updateTransferByReturn", new HashMap<String, Object>() {{
                                    put("transferNo", transferReturn.get(0).get("transferNo"));
                                }});
                                // 获取这些资产id
                                List<Map<String, Object>> list = dao.query("FADB01.transferDetailResult", new HashMap<String, Object>() {{
                                    put("transferNo", transferReturn.get(0).get("transferNo"));
                                }});
                                for (int i = 0; i < list.size(); i++) {
                                    faInfoIdList.add((String) list.get(i).get("faInfoId"));
                                }
                            } else {
                                // 不存在调拨单 --仓库发起的退货操作
                                // 删除对应数量的资产卡片
                                List<Map<String, Object>> confirmReturn = dao.query("FAAP01.queryConfirmByReturn", new HashMap<String, Object>() {{
                                    put("enterBillNo", faConfirmDO.getOriginBillNo());
                                    put("matNum", faConfirmDO.getMatNum());
                                    put("confirmStatus", "00");
                                }});
                                if (CollectionUtils.isNotEmpty(confirmReturn)){
                                    // 如果状态是待生成卡片就改变卡片数量
                                    BigDecimal enterNum = new BigDecimal(String.valueOf(confirmReturn.get(0).get("enterNum")));
                                    BigDecimal enterAmount = new BigDecimal(String.valueOf(confirmReturn.get(0).get("enterAmount")));
                                    if (enterNum.compareTo(faConfirmDO.getEnterNum()) > 0){
                                        // 卡片数量充足才删除卡片
                                        dao.update("FAAP01.updateConfirmByReturn", new HashMap<String, Object>() {{
                                            put("enterBillNo", faConfirmDO.getOriginBillNo());
                                            put("matNum", faConfirmDO.getMatNum());
                                            put("enterNum", enterNum.subtract(faConfirmDO.getEnterNum()));
                                            put("enterAmount", enterAmount.subtract(faConfirmDO.getEnterAmount()));
                                        }});
                                    } else {
                                        // 卡片数量不足才删除卡片
                                        dao.delete("FAAP01.deleteConfirmByReturnZero",new HashMap<String,Object>(){{
                                            put("enterBillNo", faConfirmDO.getOriginBillNo());
                                            put("matNum", faConfirmDO.getMatNum());
                                        }});
                                    }
                                } else {
                                    // 如果已经生成卡片就删除对应待出库的卡片
                                    List<Map<String, Object>> list = dao.query("FAAP01.queryConfirmByReturnId", new HashMap<String, Object>() {{
                                        put("enterBillNo", faConfirmDO.getOriginBillNo());
                                        put("matNum", faConfirmDO.getMatNum());
                                        put("statusCode", "020");
                                        put("enterNum", Integer.valueOf(faConfirmDO.getEnterNum().intValue()));
                                    }});
                                    if (CollectionUtils.isNotEmpty(list)){
                                        for (int i = 0; i < list.size(); i++) {
                                            faInfoIdList.add((String) list.get(i).get("id"));
                                        }
                                        dao.delete("FAAP01.deleteConfirmByReturn",new HashMap<String,Object>(){{
                                            put("faInfoIdList", faInfoIdList);
                                        }});
                                    }
                                }
                            }
                            if (faInfoIdList != null && CollectionUtils.isNotEmpty(faInfoIdList)){
                                // 资产调拨回仓库需要删除对应抛帐表中的数据
                                dao.delete("FADB01.deleteFaThrowAccountOut", new HashMap<String, Object>() {{
                                    put("faInfoIdList", faInfoIdList);
                                }});
                            }
                        } else {
                            List<String> faInfoIdList = new ArrayList<>();
                            // 针对手工入库查询对应的确认单
                            List<Map<String, Object>> confirmReturn = dao.query("FAAP01.queryConfirmByReturn", new HashMap<String, Object>() {{
                                put("enterBillNo", faConfirmDO.getOriginBillNo());
                                put("matNum", faConfirmDO.getMatNum());
                                put("confirmStatus", "00");
                            }});
                            if (CollectionUtils.isNotEmpty(confirmReturn)) {
                                // 如果状态是待生成卡片就改变卡片数量
                                BigDecimal enterNum = new BigDecimal(String.valueOf(confirmReturn.get(0).get("enterNum")));
                                BigDecimal enterAmount = new BigDecimal(String.valueOf(confirmReturn.get(0).get("enterAmount")));
                                if (enterNum.compareTo(faConfirmDO.getEnterNum()) > 0){
                                    dao.update("FAAP01.updateConfirmByReturn", new HashMap<String, Object>() {{
                                        put("enterBillNo", faConfirmDO.getOriginBillNo());
                                        put("matNum", faConfirmDO.getMatNum());
                                        put("enterNum", enterNum.subtract(faConfirmDO.getEnterNum()));
                                        put("enterAmount", enterAmount.subtract(faConfirmDO.getEnterAmount()));
                                    }});
                                } else {
                                    dao.delete("FAAP01.deleteConfirmByReturnZero",new HashMap<String,Object>(){{
                                        put("enterBillNo", faConfirmDO.getOriginBillNo());
                                        put("matNum", faConfirmDO.getMatNum());
                                    }});
                                }
                            } else {
                                // 如果已经生成卡片就删除对应待出库的卡片
                                List<Map<String, Object>> list = dao.query("FAAP01.queryConfirmByReturnId", new HashMap<String, Object>() {{
                                    put("enterBillNo", faConfirmDO.getOriginBillNo());
                                    put("matNum", faConfirmDO.getMatNum());
                                    put("statusCode", "010");
                                    put("enterNum", Integer.valueOf(faConfirmDO.getEnterNum().intValue()));
                                }});
                                if (CollectionUtils.isNotEmpty(list)){
                                    if (list.size() < Integer.valueOf(faConfirmDO.getEnterNum().intValue())){
                                        int enterNum = Integer.valueOf(faConfirmDO.getEnterNum().intValue());
                                        int size = list.size();
                                        int i = enterNum - size;
                                        info.setStatus(-1);
                                        info.setMsg("'待用'状态资产卡片不足，需要先去固定资产，调拨回仓库"+ i + "个资产卡片");
                                        return info;
                                    } else {
                                        for (int i = 0; i < list.size(); i++) {
                                            faInfoIdList.add((String) list.get(i).get("id"));
                                        }
                                        dao.delete("FAAP01.deleteConfirmByReturn",new HashMap<String,Object>(){{
                                            put("faInfoIdList", faInfoIdList);
                                        }});
                                    }
                                } else {
                                    info.setStatus(-1);
                                    info.setMsg("对应的资产卡片均已出库，需要退货请先到固定资产处进行退库操作");
                                    return info;
                                }
                            }
                            if (faInfoIdList != null && CollectionUtils.isNotEmpty(faInfoIdList)){
                                // 资产调拨回仓库需要删除对应抛帐表中的数据
                                dao.delete("FADB01.deleteFaThrowAccountOut", new HashMap<String, Object>() {{
                                    put("faInfoIdList", faInfoIdList);
                                }});
                            }
                        }
                    }
                } else {
                    dao.insert("FAAP01.insertFaConfirm", collect);
                }
            }
        }
        return info;
    }

    /**
     * 出库单回调方法
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/22 20:37
     */
    public EiInfo updateFaInfoOutBillNo(EiInfo info) {
        System.out.println("原来的出库单接口");
        if (info.get("outBillNoMap") != null) {
            Map<String, Object> outBillNoMap = (Map<String, Object>) info.get("outBillNoMap");
            outBillNoMap.forEach((k, v) -> {
                System.out.println(k);
                System.out.println(v);
                Map map = new HashMap<>();
                map.put("outBillNo", k);
                map.put("faInfoIdList", v);
                dao.update("FAAP01.updateFaInfoOutBillNo", map);
            });
        }
        return info;
    }

    /**
     * 通过资产编码查询
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/2 13:57
     */
    public EiInfo queryFaInfoByGoodsNum(EiInfo info) {
        // 如果处于现场停用处于那个环节
        String goodsNum = info.getString("goodsNum");
        List<Map<String, Object>> faInfo = dao.query("FAAP01.queryFaInfoByGoodsNum", goodsNum);
        if (CollectionUtils.isNotEmpty(faInfo)) {
            List<Map<String, Object>> transitionResult = OneSelfUtils.transitionResult(faInfo);
            String statusCode = (String) transitionResult.get(0).get("statusCode");
            switch (statusCode) {
                case "010":
                    info.setStatus(200);
                    info.setMsg("资产待启用");
                    info.set("result", transitionResult.get(0));
                    break;
                case "020":
                    info.setStatus(200);
                    info.setMsg("资产启用");
                    info.set("result", transitionResult.get(0));
                    break;
                case "030":
                    // 查询调拨表
                    List<Map<String, Object>> transferList = dao.query("FAAP01.queryTransferInfo", goodsNum);
                    // 查询报废表
                    List<Map<String, Object>> scrappedList = dao.query("FAAP01.queryScrappedInfo", goodsNum);
                    if (CollectionUtils.isNotEmpty(transferList)) {
                        List<Map<String, Object>> transferResult = OneSelfUtils.transitionResult(transferList);
                        Map<String, Object> result = new HashMap<>();
                        info.setStatus(200);
                        info.setMsg("调拨履历图");
                        result.putAll(transferResult.get(0));
                        List<Map<String, Object>> transferRecordList = dao.query("FAAP01.queryOnceRecordTransfer", new HashMap() {{
                            put("goodsNum", goodsNum);
                        }});
                        if (CollectionUtils.isNotEmpty(transferRecordList)) {
                            List<Map<String, Object>> transferRecord = OneSelfUtils.transitionResult(transferRecordList);
                            result.putAll(transferRecord.get(0));
                        }
                        info.set("result", result);
                        return info;
                    } else if (CollectionUtils.isNotEmpty(scrappedList)) {
                        List<Map<String, Object>> scrappedResult = OneSelfUtils.transitionResult(scrappedList);
                        Map<String, Object> result = new HashMap<>();
                        info.setStatus(200);
                        info.setMsg("报废履历图");
                        result.putAll(scrappedResult.get(0));
                        List<Map<String, Object>> scrapRecordList = dao.query("FAAP01.queryOnceRecordScrap", new HashMap() {{
                            put("goodsNum", goodsNum);
                        }});
                        if (CollectionUtils.isNotEmpty(scrappedList)) {
                            List<Map<String, Object>> scrapRecord = OneSelfUtils.transitionResult(scrapRecordList);
                            result.putAll(scrapRecord.get(0));
                        }
                        info.set("result", result);
                        return info;
                    } else {
                        info.setStatus(500);
                        info.setMsg("资产状态有误,请联系管理员");
                    }
                    break;
                case "050":
                    info.setStatus(200);
                    info.setMsg("资产预报废");
                    info.set("result", transitionResult.get(0));
                    break;
                case "060":
                    info.setStatus(200);
                    info.setMsg("资产已报废");
                    info.set("result", transitionResult.get(0));
                    break;
                default:
                    info.setStatus(500);
                    info.setMsg("服务器错误");
                    break;
            }
        } else {
            info.setStatus(500);
            info.setMsg("资产不存在");
        }
        return info;
    }

    /**
     * h5校验登录人信息
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/13 10:43
     */
    public EiInfo checkLoginInfo(EiInfo info) {
        String workNo = info.getString("workNo");
        if (StringUtils.isNotEmpty(workNo)) {
            Boolean flag = false;
            // 获取当前登录人的用户信息
            Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
            // 查询固定资产
            List<String> rolesList = dao.query("FAAP01.queryRoles", staffByUserId);
            if (rolesList.contains(FixedAssetsEum.common.getAcronym()) || rolesList.contains(FixedAssetsEum.user.getAcronym())
                    || rolesList.contains(FixedAssetsEum.guard.getAcronym()) || rolesList.contains(FixedAssetsEum.member.getAcronym())
                    || rolesList.contains(FixedAssetsEum.leader.getAcronym())
            ) {
                flag = true;
            }
            if (flag) {
                info.setStatus(200);
                info.setMsg("校验成功");
                info.set("isPass", true);
            } else {
                info.setStatus(500);
                info.setMsg("权限不足");
                info.set("isPass", false);
            }
        } else {
            info.setStatus(500);
            info.setMsg("用户名不存在");
            info.set("isPass", false);
        }
        return info;
    }

    /**
     * h5提交调拨/报废申请
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/13 10:43
     */
    public EiInfo sumbitInfo(EiInfo info) {
        String type = info.getString("type");
        String confirmDeptNum = info.getString("confirmDeptNum");
        String confirmDeptName = info.getString("confirmDeptName");
        String applyDeptNum = info.getString("applyDeptNum");
        String applyDeptName = info.getString("applyDeptName");
        String applyPerson = info.getString("applyPerson");
        String applyReason = info.getString("applyReason");
        Map<String, Object> map = new HashMap<>(16);
        map.put("applyDeptNum", applyDeptNum);
        map.put("applyDeptName", applyDeptName);
        map.put("applyPerson", applyPerson);
        map.put("applyReason", applyReason);
        map.put("confirmDeptNum", confirmDeptNum);
        map.put("confirmDeptName", confirmDeptName);
        map.put("source", "H5");
        Map<String, Object> goods = (Map<String, Object>) info.get("goods");
        switch (type) {
            case "transfer":
                String transferNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym());
                map.put("id", UUID.randomUUID().toString());
                map.put("transferNo", transferNo);
                map.put("transferStatus", "10");
                FaTransferVO faTransferVO = new FaTransferVO();
                faTransferVO.fromMap(map);
                faTransferVO.setApplyTime(new Timestamp(new Date().getTime()));
                List<FaTransferDetailVO> faTransferDetailVOList = new ArrayList<>();
                FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
                goods.put("id", UUID.randomUUID().toString());
                goods.put("transferNo", transferNo);
                faTransferDetailVO.fromMap(goods);
                faTransferDetailVOList.add(faTransferDetailVO);
                dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
                dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
                dao.update("FADB01.updateFaInfoStatus", faTransferDetailVOList);
                break;
            case "scrap":
                String scrapNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.BF.getAcronym());
                map.put("id", UUID.randomUUID().toString());
                map.put("scrappedNo", scrapNo);
                FaScrapVO faScrapVO = new FaScrapVO();
                faScrapVO.fromMap(map);
                faScrapVO.setApplyTime(new Timestamp(new Date().getTime()));
                FaScrapDetailVO faScrapDetailVO = new FaScrapDetailVO();
                goods.put("id", UUID.randomUUID().toString());
                goods.put("scrappedNo", scrapNo);
                goods.put("scrapDetailStatus", "00");
                faScrapDetailVO.fromMap(goods);
                List<FaScrapDetailVO> faScrapDetailVOList = new ArrayList<>();
                faScrapDetailVOList.add(faScrapDetailVO);
                faScrapDetailVOList.stream().forEach(vo -> vo.setUsedYears(
                        LocalDate.fromDateFields(new Date()).getYear() - LocalDate.fromDateFields(vo.getUseDate()).getYear()
                ));
                dao.insert("FABF01.insertFaScrapVo", faScrapVO);
                dao.insert("FABF01.insertFaScrapDetailVO", faScrapDetailVOList);
                dao.update("FABF01.updateFaInfoStatus", faScrapDetailVOList);
                break;
        }
        info.setStatus(200);
        info.setMsg("成功");
        return info;
    }

    /**
     * 接收科室下拉框
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/13 10:42
     */
    public EiInfo receivingDepartment(EiInfo info) {
        Map<String, Object> map = new HashMap<>();
        map.put("deptName", info.getString("deptName"));
        EiInfo queryDept = BaseDockingUtils.getDeptAll(map);
        List<Map<String, String>> result = (List<Map<String, String>>) queryDept.getAttr().get("result");
        if (CollectionUtils.isNotEmpty(result)) {
            info.setStatus(200);
            info.setMsg("查询成功,存在数据");
            info.set("result", result);
        } else {
            info.setStatus(200);
            info.setMsg("查询成功,不存在数据");
            info.set("result", result);
        }
        return info;
    }

    /**
     * 查看调拨/报废曾经的记录
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/16 14:45
     */
    public EiInfo queryOnceRecord(EiInfo info) {
        String type = info.getString("type");
        String goodsNum = info.getString("goodsNum");
        switch (type) {
            // 报废
            case "scrap":
                // 通过资产编码查询报废信息
                List<Map<String, Object>> scrapRecord = dao.query("FAAP01.queryOnceRecordScrap", new HashMap() {{
                    put("goodsNum", goodsNum);
//					put("source", "H5");
                }});
                if (CollectionUtils.isNotEmpty(scrapRecord)) {
                    info.setStatus(200);
                    info.setMsg("查询成功");
                    Map<String, Object> map = OneSelfUtils.transitionResult(scrapRecord).get(0);
                    map.put("type", "scrap");
                    info.set("result", map);
                } else {
                    info.setStatus(404);
                    info.setMsg("没有数据");
                }
                break;
            // 调拨
            case "transfer":
                // 通过资产编码查询调拨信息
                List<Map<String, Object>> transferRecord = dao.query("FAAP01.queryOnceRecordTransfer", new HashMap() {{
                    put("goodsNum", goodsNum);
//					put("source", "H5");
                }});
                if (CollectionUtils.isNotEmpty(transferRecord)) {
                    info.setStatus(200);
                    info.setMsg("查询成功");
                    Map<String, Object> map = OneSelfUtils.transitionResult(transferRecord).get(0);
                    map.put("type", "transfer");
                    info.set("result", map);
                } else {
                    info.setStatus(404);
                    info.setMsg("没有数据");
                }
                break;
        }
        return info;
    }
}