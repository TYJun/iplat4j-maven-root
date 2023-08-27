package com.baosight.wilp.si.common;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.si.kc.domain.NumberVo;
import com.baosight.wilp.si.kc.domain.SiDayStorage;
import com.baosight.wilp.si.kc.domain.SiMonthStorage;
import com.baosight.wilp.si.kc.domain.SiStorge;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fangjian
 * @version V5.0.1
 * @Description: 库存结算工具类
 * @ClassName: StockSettleUtils
 * @Package com.baosight.wilp.si.common
 * @date: 2022年09月22日 14:50
 *
 * 1.获取库存存量
 * 2.计算出库数量与金额
 * 3.计算入库数量与金额
 * 4.获取前一日的库存结算数据
 * 5.获取上一月的库存结算数据
 * 6.获取期末库存
 */
public class StockSettleUtils {

    public final static String TYPE = "cur";

    /**
     * 获取库存存量
     * @Title: queryStorage
     * @return java.util.List<com.baosight.wilp.si.kc.domain.SiStorge>
     * @throws
     **/
    public static List<SiStorge> queryStorage(Dao dao) {
        Map<String, Object> map = new HashMap<>(0);
        //获取总数
        List<Integer> countList = dao.query("SIKC01.count", map);
        int count = countList.get(0);
        //获取库存
        SqlMapDao sqlMapDao = (SqlMapDao) dao;
        sqlMapDao.setMaxQueryCount(count);
        List<SiStorge> list = sqlMapDao.query("SIKC01.query", map);
        return list;
    }

    /**
     * 计算出库数量与金额
     * @Title: calOut
     * @param date date : 出库日期/月份
     * @param wareHouseNo wareHouseNo : 出库仓库号
     * @param matNum matNum : 物资编码
     * @param dao dao : 数据操作dao
     * @return com.baosight.wilp.si.kc.domain.NumberVo
     * @throws
     **/
    public static NumberVo calOut(String date, String wareHouseNo, String matNum, Dao dao) {
        AtomicReference<Double> totalNum = new AtomicReference<>(0d);
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<Double> transferNum = new AtomicReference<>(0d);
        AtomicReference<BigDecimal> transferAmount = new AtomicReference<>(BigDecimal.ZERO);
        //插叙出库信息
        List<Map<String, Object>> list = dao.query("SIKC0301.queryOutByTime", new HashMap(4) {{
            put(date.length() < 8 ? "month" : "date", date);
            put("wareHouseNo", wareHouseNo);
            put("matNum", matNum);
        }});
        if(CollectionUtils.isNotEmpty(list)) {
            list.forEach(map -> {
                switch (SiUtils.isEmpty(map.get("outType"))) {
                    case SiConstant.OUT_TYPE_DB:
                        transferNum.set(SiUtils.cal(transferNum.get(), map.get("outNum"), SiConstant.MATH_ADD));
                        transferAmount.set(transferAmount.get().add(NumberUtils.toBigDecimal(map.get("outAmount"))));
                        break;
                    case SiConstant.OUT_TYPE_HC:
                        totalNum.set(SiUtils.cal(totalNum.get(), map.get("outNum"), SiConstant.MATH_SUB));
                        totalAmount.set(totalAmount.get().subtract(NumberUtils.toBigDecimal(map.get("outAmount"))));
                        break;
                    default:
                        totalNum.set(SiUtils.cal(totalNum.get(), map.get("outNum"), SiConstant.MATH_ADD));
                        totalAmount.set(totalAmount.get().add(NumberUtils.toBigDecimal(map.get("outAmount"))));
                }
            });
        }
        return new NumberVo(totalNum.get(), totalAmount.get(), transferNum.get(), transferAmount.get());
    }

    /**
     * 计算入库数量与金额
     * @Title: calEnter
     * @param date date : 入库日期/月份
     * @param wareHouseNo wareHouseNo : 入库仓库号
     * @param matNum matNum : 物资编码
     * @param dao dao : 数据操作dao
     * @return com.baosight.wilp.si.kc.domain.NumberVo
     * @throws
     **/
    public static NumberVo calEnter(String date, String wareHouseNo, String matNum, Dao dao) {
        AtomicReference<Double> totalNum = new AtomicReference<>(0d);
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<Double> transferNum = new AtomicReference<>(0d);
        AtomicReference<BigDecimal> transferAmount = new AtomicReference<>(BigDecimal.ZERO);
        //插叙出库信息
        List<Map<String, Object>> list = dao.query("SIKC0301.queryEnterByTime", new HashMap(4) {{
            put(date.length() < 8 ? "month" : "date", date);
            put("wareHouseNo", wareHouseNo);
            put("matNum", matNum);
        }});
        if(CollectionUtils.isNotEmpty(list)) {
            list.forEach(map -> {
                switch (SiUtils.isEmpty(map.get("enterType"))) {
                    case SiConstant.ENTER_TYPE_DB:
                        transferNum.set(SiUtils.cal(transferNum.get(), map.get("enterNum"), SiConstant.MATH_ADD));
                        transferAmount.set(transferAmount.get().add(NumberUtils.toBigDecimal(map.get("enterAmount"))));
                        break;
                    case SiConstant.ENTER_TYPE_HC:
                        totalNum.set(SiUtils.cal(totalNum.get(), map.get("enterNum"), SiConstant.MATH_SUB));
                        totalAmount.set(totalAmount.get().subtract(NumberUtils.toBigDecimal(map.get("enterAmount"))));
                        break;
                    default:
                        totalNum.set(SiUtils.cal(totalNum.get(), map.get("enterNum"), SiConstant.MATH_ADD));
                        totalAmount.set(totalAmount.get().add(NumberUtils.toBigDecimal(map.get("enterAmount"))));
                }
            });
        }
        return new NumberVo(totalNum.get(), totalAmount.get(), transferNum.get(), transferAmount.get());
    }

    /**
     * 获取前一日的库存结算数据
     * @Title: queryLastStorage
     * @param lastDay lastDay : 上一日日期
     * @param wareHouseNo wareHouseNo : 仓库号
     * @param matNum matNum : 物资编码
     * @param dao dao : 数据操作dao
     * @return com.baosight.wilp.si.kc.domain.NumberVo
     * @throws
     **/
    public static NumberVo queryLastStorage(Date lastDay, String wareHouseNo, String matNum, Dao dao) {
        List<SiDayStorage> list = dao.query("SIKC03.query", new HashMap(4) {{
            put("countDay", DateUtils.toDateStr10(lastDay));
            put("wareHouseNo", wareHouseNo);
            put("matNumEQ", matNum);
        }});
        if(CollectionUtils.isEmpty(list)) {
            return new NumberVo(-1D);
        } else {
            return new NumberVo(list.get(0).getLastNum(), list.get(0).getLastAmount());
        }
    }

    /**
     * 获取上一月的库存结算数据
     * @Title: queryLastMonthStorage
     * @param month month : 结算月
     * @param wareHouseNo wareHouseNo : 仓库号
     * @param matNum matNum : 物资编码
     * @param dao dao : 数据操作dao
     * @return com.baosight.wilp.si.kc.domain.NumberVo
     * @throws
     **/
    public static NumberVo queryLastMonthStorage(String month, String wareHouseNo, String matNum, Dao dao) {
        //获取结算月的上一个月
        Date date = DateUtils.toDate(month + "-01");
        Date lastDate = DateUtils.getLastDayInMonth(date, -1);
        String lastMonth = DateUtils.toDateStr(lastDate, "yyyy-MM");
        List<SiMonthStorage> list = dao.query("SIKC04.query", new HashMap(4) {{
            put("month", lastMonth);
            put("wareHouseNo", wareHouseNo);
            put("matNumEQ", matNum);
        }});
        if(CollectionUtils.isEmpty(list)) {
            return new NumberVo(-1D);
        } else {
            return new NumberVo(list.get(0).getLastNum(), list.get(0).getLastAmount());
        }
    }

    /**
     * 获取期末库存
     * @Title: calNext
     * @param type type : 类型
     * @param date date : 时间(次日/下月)
     * @param storage storage : 库存对象
     * @param dao dao : 数据dao
     * @return com.baosight.wilp.si.kc.domain.NumberVo
     * @throws
     **/
    public static NumberVo calNext(String type, String date, SiStorge storage, Dao dao) {
        //当日/当月结算,期末库存为当前库存
        if(TYPE.equals(type)) {
            return new NumberVo(storage.getTotalNum(), BigDecimal.valueOf(storage.getTotalAmount()));
        }
        //次日/下月结算,获取次日/次月的入库、出库数据
        NumberVo nextOutVo = calOut(date, storage.getWareHouseNo(), storage.getMatNum(), dao);
        NumberVo nextEnterVo = calEnter(date, storage.getWareHouseNo(), storage.getMatNum(), dao);

        //计算
        Double cal1 = SiUtils.cal(storage.getTotalNum(), SiUtils.cal(nextOutVo.getNum(), nextOutVo.getTransferNum(), SiConstant.MATH_ADD), SiConstant.MATH_ADD);
        Double cal2 = SiUtils.cal(nextEnterVo.getNum(), nextEnterVo.getTransferNum(), SiConstant.MATH_ADD);
        Double firstNum = SiUtils.cal(cal1, cal2, SiConstant.MATH_SUB);
        BigDecimal firstAmount = BigDecimal.valueOf(storage.getTotalAmount()).add(nextOutVo.getAmount())
                .add(nextOutVo.getTransferAmount()).subtract(nextEnterVo.getAmount()).subtract(nextEnterVo.getTransferAmount());
        return new NumberVo(firstNum, firstAmount);
    }
}
