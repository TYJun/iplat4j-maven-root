package com.baosight.wilp.ci.kc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ci.kc.domain.CiStatistics;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 统计库存每月期初、期末余额，当月入库出库数据
 * 每月最后一日的23:30触发    0 30 23 L * ?
 * @Title: ServiceCIKC03
 * @ClassName: ServiceCIKC03
 * @Package: com.baosight.wilp.ci.kc.service
 * @author: liu
 * @date: 2022-09-21 15:35
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
public class ServiceCIKC03 extends ServiceBase {


    private final static Logger logger = LoggerFactory.getLogger(ServiceCIKC03.class);
    /**
     * 统计库存每月期初、期末余额，当月入库出库数据
     * 每月最后一日的23:30触发(0 30 23 L * ?)
     * @Title  statistics
     * @author liu
     * @date 2022-09-21 15:41
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo statistics(EiInfo eiInfo){

        /**
         * 1.查询库存表(ci_storge),查出每个仓库下不同物资的当月数量(期末数量)
         */
        //当月
        String month = DateUtils.curDateStr("yyyy-MM");
        //下月
        String  nextMonth = String.valueOf(YearMonth.parse(month).plus(1, ChronoUnit.MONTHS));

        List<Map<String,String>> storgeList = dao.query("CIKC03.getStorge", null);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("month",month);
        for (Map<String, String> storgeMap : storgeList) {

            logger.info("查询物资信息参数:{}", pMap);
            pMap.put("wareHouseNo",storgeMap.get("wareHouseNo"));
            pMap.put("matCode",storgeMap.get("matNum"));

            /**
             * 2.不同物资当月的入库数量
             */
            List<String> enterList = dao.query("CIKC03.getEnterNum", pMap);

            /**
             * 3.不同物资当月的出库数量
             */
            List<String> outList = dao.query("CIKC03.getOutNum", pMap);


            //生成物资月统计信息
            CiStatistics ciStatistics = new CiStatistics();
            ciStatistics.setWareHouseNo(storgeMap.get("wareHouseNo"));
            ciStatistics.setWareHouseName(storgeMap.get("wareHouseName"));
            ciStatistics.setMatCode(storgeMap.get("matNum"));
            ciStatistics.setMatName(storgeMap.get("matName"));
            ciStatistics.setEndBalance(StringUtils.toString(storgeMap.get("totalNum")));
            ciStatistics.setEnterNum(enterList.get(0));
            ciStatistics.setOutNum(outList.get(0));

            /**
             * 4.查询物资信息是否存在
             */
            List<CiStatistics> queryList = dao.query("CIKC03.query", pMap);
            logger.info("物资信息参数:{}", queryList);
            if(CollectionUtils.isEmpty(queryList)){
                //4.1不存在插入当月数据
                ciStatistics.setId(UUID.randomUUID().toString());
                ciStatistics.setCreateTime(DateUtils.curDateTimeStr19());
                ciStatistics.setMonth(month);
                ciStatistics.setStartBalance("0");
                dao.insert("CIKC03.insert",ciStatistics);

            }else{
                //4.2存在更新当月数据
                ciStatistics.setId(queryList.get(0).getId());
                dao.insert("CIKC03.update",ciStatistics);
            }

            /**
             * 5.插入下月的期初数据
             */
            ciStatistics.setId(UUID.randomUUID().toString());
            ciStatistics.setCreateTime(DateUtils.curDateTimeStr19());
            ciStatistics.setMonth(nextMonth);
            ciStatistics.setWareHouseNo(storgeMap.get("wareHouseNo"));
            ciStatistics.setMatCode(storgeMap.get("matNum"));
            ciStatistics.setEnterNum("0.00");
            ciStatistics.setOutNum("0.00");
            ciStatistics.setEndBalance("0.00");
            ciStatistics.setStartBalance(StringUtils.toString(storgeMap.get("totalNum")));
            dao.insert("CIKC03.insert",ciStatistics);

        }

        return eiInfo;
    }
}
