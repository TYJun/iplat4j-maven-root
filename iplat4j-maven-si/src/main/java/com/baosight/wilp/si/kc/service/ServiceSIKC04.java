package com.baosight.wilp.si.kc.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.StockSettleUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.kc.domain.NumberVo;
import com.baosight.wilp.si.kc.domain.SiMonthStorage;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.utils.UUID;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.1
 * @Description: 库存月结算Service
 * @ClassName: ServiceSIKC03
 * @Package com.baosight.wilp.si.kc.service
 * @date: 2022年09月22日 9:47

 * 1.页面加载
 * 2.页面数据查询
 * 3.日结算定时任务(基于日结算)
 * 4.日结算定时任务
 */
public class ServiceSIKC04 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.setCell("inqu_status", 0,"month", DateUtils.toDateStr(DateUtils.getLastDayInMonth(new Date(),-1), "yyyy-MM"));
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
        return query(inInfo);
    }

    /**
     * 数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
        return super.query(inInfo, "SIKC04.query", new SiMonthStorage());
    }

    /**
     * 根据日结算获取月结算
     * @Title: settlementStockByDay
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo settlementStockByDay(EiInfo inInfo) {
        Date endTime = DateUtils.getLastDayInMonth(new Date(), -1);
        Date beginTime = DateUtils.addDays(DateUtils.getLastDayInMonth(new Date(), -2), 1);

        List<SiMonthStorage> list = dao.query("SIKC04.settleByMonth", new HashMap(4) {{
            put("beginTime", DateUtils.toDateStr10(beginTime));
            put("endTime", DateUtils.toDateStr10(endTime));
        }});
        if(CollectionUtils.isNotEmpty(list)){
            dao.insert("SIKC04.insert", list);
        }
        return inInfo;
    }

    /**
     * 按月结算库存量
     * @Title: settlementStock
     * @param inInfo inInfo
     *      type: cur=当月结算, next:次月结算
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     *
     * 1.获取结算月份
     * 2.获取仓库所有物资
     * 3.遍历物资
     * 4.获取物资的出库数据
     * 5.获取物资入库数据
     * 6.获取物资在结算日前一月的库存记录，
     * 7.获取月末库存,
     * 8.获取月初库存
     *    8.1.不存在前一月的库存记录, 根据月末库存、入库记录、出库记录计算月初库存
     *    8.2.存在前一月的库存记录, 以前一月月末库存作为日初库存
     * 9.保存月结算记录
     **/
    public EiInfo settlementStock(EiInfo inInfo) {
        /**1.获取结算月份**/
        String type = inInfo.getString("type");
        String month = StockSettleUtils.TYPE.equals(type) ? DateUtils.curDateStr("yyyy-MM")
                : DateUtils.toDateStr(DateUtils.getLastDayInMonth(new Date(), -1), "yyyy-MM");
        /**2.获取库存**/
        List<SiStorge> storages = StockSettleUtils.queryStorage(dao);
        /**3.遍历物资**/
        List<SiMonthStorage> list = new ArrayList<>();
        for (SiStorge storage : storages) {
            SiMonthStorage monthStorage = new SiMonthStorage();
            monthStorage.fromMap(storage.toMap());
            monthStorage.setId(UUID.randomUUID().toString());
            monthStorage.setMonth(month);
            /**4.获取物资的出库数据**/
            NumberVo outVo = StockSettleUtils.calOut(month, storage.getWareHouseNo(), storage.getMatNum(), dao);
            monthStorage.setOutNum(outVo.getNum());
            monthStorage.setOutAmount(outVo.getAmount());
            monthStorage.setTransferOutNum(outVo.getTransferNum());
            monthStorage.setTransferOutAmount(outVo.getTransferAmount());
            /**5.获取物资入库数据**/
            NumberVo enterVo = StockSettleUtils.calEnter(month, storage.getWareHouseNo(), storage.getMatNum(), dao);
            monthStorage.setEnterNum(enterVo.getNum());
            monthStorage.setEnterAmount(enterVo.getAmount());
            monthStorage.setTransferEnterNum(enterVo.getTransferNum());
            monthStorage.setTransferEnterAmount(enterVo.getTransferAmount());
            /**6.获取物资在结算日前一月的库存记录**/
            NumberVo lastVo = StockSettleUtils.queryLastMonthStorage(month, storage.getWareHouseNo(), storage.getMatNum(), dao);
            /**7.获取月末库存**/
            NumberVo nextVo = StockSettleUtils.calNext(type, DateUtils.curDateStr("yyyy-MM"), storage, dao);
            monthStorage.setLastNum(nextVo.getNum());
            monthStorage.setLastAmount(nextVo.getAmount());
            /**8.获取月初库存**/
            if(lastVo.getNum() < 0) {
                /**8.1不存在前一日的库存记录, 根据月末库存、入库记录、出库记录计算月初库存**/
                //nextVo.getNum() + outVo.getNum() + outVo.getTransferNum() - enterVo.getNum() - enterVo.getTransferNum()
                Double cal1 = SiUtils.cal(nextVo.getNum(), SiUtils.cal(outVo.getNum(), outVo.getTransferNum(), SiConstant.MATH_ADD), SiConstant.MATH_ADD);
                Double cal2 = SiUtils.cal(enterVo.getNum(), enterVo.getTransferNum(), SiConstant.MATH_ADD);
                monthStorage.setFirstNum(SiUtils.cal(cal1, cal2, SiConstant.MATH_SUB));
                monthStorage.setFirstAmount(nextVo.getAmount().add(outVo.getAmount()).add(outVo.getTransferAmount())
                        .subtract(enterVo.getAmount()).subtract(enterVo.getTransferAmount()));
            } else {
                /**8.2存在前一月的库存记录, 以前一月月末库存作为月初库存**/
                monthStorage.setFirstNum(lastVo.getNum());
                monthStorage.setFirstAmount(lastVo.getAmount());
            }
            monthStorage.setRecCreateTime(new Date()); monthStorage.setRecReviseTime(new Date());
            list.add(monthStorage);
        }
        /**9.保存月结算记录**/
        dao.insert("SIKC04.insert", list);
        return inInfo;
    }

    /**
     * 计算库存总价和库存总量
     * @param inInfo
     * @return
     */
    public EiInfo countNumAndSum(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo,"SIKC04.countNumAndSum", new SiMonthStorage());
        outInfo.set("firstNum",outInfo.getBlock("result").getRow(0).get("firstNum"));
        outInfo.set("firstAmount",outInfo.getBlock("result").getRow(0).get("firstAmount"));
        outInfo.set("lastNum",outInfo.getBlock("result").getRow(0).get("lastNum"));
        outInfo.set("lastAmount",outInfo.getBlock("result").getRow(0).get("lastAmount"));
        return outInfo;
    }

}
