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
import com.baosight.wilp.si.kc.domain.SiDayStorage;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.utils.UUID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.1
 * @Description: 库存日结算Service
 * @ClassName: ServiceSIKC03
 * @Package com.baosight.wilp.si.kc.service
 * @date: 2022年09月22日 9:47
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.日结算定时任务
 */
public class ServiceSIKC03 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Date date = DateUtils.addDays(new Date(), -1);
        inInfo.setCell("inqu_status", 0, "beginTime", DateUtils.toDateStr(date));
        inInfo.setCell("inqu_status", 0, "endTime", DateUtils.toDateStr(date));
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
        return super.query(inInfo, "SIKC03.query", new SiDayStorage());
    }

    /**
     * 按日结算库存量
     * @Title: settlementStock
     * @param inInfo inInfo
     *      type: cur=当天结算, next:次日结算
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     *
     * 1.获取结算日期
     * 2.获取仓库所有物资
     * 3.遍历物资
     * 4.获取物资的出库数据
     * 5.获取物资入库数据
     * 6.获取物资在结算日前一日的库存记录，
     * 7.获取日末库存,
     * 8.获取日初库存
     *    8.1.不存在前一日的库存记录, 根据日末库存、入库记录、出库记录计算日初库存
     *    8.2.存在前一日的库存记录, 以前一日日末库存作为日初库存
     * 9.保存日结算记录
     **/
    public EiInfo settlementStock(EiInfo inInfo) {
        /**1.获取结算日期**/
        String type = inInfo.getString("type");
        Date countDate = StockSettleUtils.TYPE.equals(type) ? DateUtils.curDate() : DateUtils.addDays(DateUtils.curDate(), -1);
        /**2.获取库存**/
        List<SiStorge> storages = StockSettleUtils.queryStorage(dao);
        /**3.遍历物资**/
        List<SiDayStorage> list = new ArrayList<>();
        for (SiStorge storage : storages) {
            SiDayStorage day = new SiDayStorage();
            day.fromMap(storage.toMap());
            day.setId(UUID.randomUUID().toString());
            day.setCountDay(countDate);
            /**4.获取物资的出库数据**/
            NumberVo outVo = StockSettleUtils.calOut(DateUtils.toDateStr(countDate), storage.getWareHouseNo(), storage.getMatNum(), dao);
            day.setOutNum(outVo.getNum());
            day.setOutAmount(outVo.getAmount());
            day.setTransferOutNum(outVo.getTransferNum());
            day.setTransferOutAmount(outVo.getTransferAmount());
            /**5.获取物资入库数据**/
            NumberVo enterVo = StockSettleUtils.calEnter(DateUtils.toDateStr(countDate), storage.getWareHouseNo(), storage.getMatNum(), dao);
            day.setEnterNum(enterVo.getNum());
            day.setEnterAmount(enterVo.getAmount());
            day.setTransferEnterNum(enterVo.getTransferNum());
            day.setTransferEnterAmount(enterVo.getTransferAmount());
            /**6.获取物资在结算日前一日的库存记录**/
            NumberVo lastVo = StockSettleUtils.queryLastStorage(DateUtils.addDays(countDate, -1), storage.getWareHouseNo(), storage.getMatNum(), dao);
            /**7.获取日末库存**/
            NumberVo nextVo = StockSettleUtils.calNext(type, DateUtils.curDateStr(), storage, dao);
            day.setLastNum(nextVo.getNum());
            day.setLastAmount(nextVo.getAmount());
            /**8.获取日初库存**/
            if(lastVo.getNum() < 0) {
                /**8.1不存在前一日的库存记录, 根据日末库存、入库记录、出库记录计算日初库存**/
                //nextVo.getNum() + outVo.getNum() + outVo.getTransferNum() - enterVo.getNum() - enterVo.getTransferNum()
                Double cal1 = SiUtils.cal(nextVo.getNum(), SiUtils.cal(outVo.getNum(), outVo.getTransferNum(), SiConstant.MATH_ADD), SiConstant.MATH_ADD);
                Double cal2 = SiUtils.cal(enterVo.getNum(), enterVo.getTransferNum(), SiConstant.MATH_ADD);
                day.setFirstNum(SiUtils.cal(cal1, cal2, SiConstant.MATH_SUB));
                day.setFirstAmount(nextVo.getAmount().add(outVo.getAmount()).add(outVo.getTransferAmount())
                        .subtract(enterVo.getAmount()).subtract(enterVo.getTransferAmount()));
            } else {
                /**8.2存在前一日的库存记录, 以前一日日末库存作为日初库存**/
                day.setFirstNum(lastVo.getNum());
                day.setFirstAmount(lastVo.getAmount());
            }
            day.setRecCreateTime(new Date()); day.setRecReviseTime(new Date());
            list.add(day);
        }
        /**9.保存日结算记录**/
        dao.insert("SIKC03.insert", list);
        return inInfo;
    }

    /**
     * 计算库存总价和库存总量
     * @param inInfo
     * @return
     */
    public EiInfo countNumAndSum(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo,"SIKC03.countNumAndSum", new SiDayStorage());
        outInfo.set("firstNum",outInfo.getBlock("result").getRow(0).get("firstNum"));
        outInfo.set("firstAmount",outInfo.getBlock("result").getRow(0).get("firstAmount"));
        outInfo.set("lastNum",outInfo.getBlock("result").getRow(0).get("lastNum"));
        outInfo.set("lastAmount",outInfo.getBlock("result").getRow(0).get("lastAmount"));
        return outInfo;
    }

    public EiInfo settlementStockTest(EiInfo inInfo) {
        /**1.获取结算日期**/
        Date countDate = DateUtils.toDate(inInfo.getString("date"));
        /**2.获取库存**/
        List<SiStorge> storages = StockSettleUtils.queryStorage(dao);
        /**3.遍历物资**/
        List<SiDayStorage> list = new ArrayList<>();
        for (SiStorge storage : storages) {
            SiDayStorage day = new SiDayStorage();
            day.fromMap(storage.toMap());
            day.setId(UUID.randomUUID().toString());
            day.setCountDay(countDate);
            /**4.获取物资的出库数据**/
            NumberVo outVo = StockSettleUtils.calOut(DateUtils.toDateStr(countDate), storage.getWareHouseNo(), storage.getMatNum(), dao);
            day.setOutNum(outVo.getNum() == -1 ? 0d : outVo.getNum());
            day.setOutAmount(outVo.getNum() == -1 ? BigDecimal.ZERO : outVo.getAmount());
            day.setTransferOutNum(outVo.getNum() == -1 ? 0d : outVo.getTransferNum());
            day.setTransferOutAmount(outVo.getNum() == -1 ? BigDecimal.ZERO : outVo.getTransferAmount());
            /**5.获取物资入库数据**/
            NumberVo enterVo = StockSettleUtils.calEnter(DateUtils.toDateStr(countDate), storage.getWareHouseNo(), storage.getMatNum(), dao);
            day.setEnterNum(enterVo.getNum() == -1 ? 0d : enterVo.getNum());
            day.setEnterAmount(enterVo.getNum() == -1 ? BigDecimal.ZERO : enterVo.getAmount());
            day.setTransferEnterNum(enterVo.getNum() == -1 ? 0d : enterVo.getTransferNum());
            day.setTransferEnterAmount(enterVo.getNum() == -1 ? BigDecimal.ZERO : enterVo.getTransferAmount());
            /**6.获取物资在结算日前一日的库存记录**/
            NumberVo lastVo = StockSettleUtils.queryLastStorage(DateUtils.addDays(countDate, -1), storage.getWareHouseNo(), storage.getMatNum(), dao);
            /**7.获取日初库存**/
            day.setFirstNum(lastVo.getNum() == -1 ? 0d : lastVo.getNum());
            day.setFirstAmount(BigDecimal.ZERO);
            day.setFirstAmount(lastVo.getNum() == -1 ? BigDecimal.ZERO : lastVo.getAmount());
            /**8.获取日末库存**/
            Double cal1 = SiUtils.cal(day.getFirstNum(), day.getEnterNum(), SiConstant.MATH_ADD);
            day.setLastNum(SiUtils.cal(cal1, day.getOutNum(), SiConstant.MATH_SUB));
            day.setLastAmount(day.getFirstAmount().add(day.getEnterAmount()).subtract(day.getOutAmount()));
            day.setRecCreateTime(new Date()); day.setRecReviseTime(new Date());
            list.add(day);
        }
        /**9.保存日结算记录**/
        dao.insert("SIKC03.insert", list);
        return inInfo;
    }

}
