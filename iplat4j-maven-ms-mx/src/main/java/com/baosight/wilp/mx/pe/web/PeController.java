package com.baosight.wilp.mx.pe.web;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mx.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 智能监控统计分析页面处理层（统计分析页面的请求处理方法）
 *
 * @author: panlingfeng
 * @createDate: 2021/12/23 5:07 下午
 */
@RestController
@RequestMapping("sym/pe")
public class PeController {

    private Dao dao = (Dao) PlatApplicationContext.getBean("dao"); //获取持久化操作对象

    private static final String MONTH = "month"; //定义月分类标识

    /**
     * 统计分析页面的请求处理方法
     *
     * 1、准备好参数查询容器和返回结果容器，报警记录统计
     * 2、今日解除报警数量
     * 3、已确认的报警数量
     * 4、未确认的报警数量
     * 5、报警类型占比分析
     * 6、报警位置占比分析
     * 7、历史报警情况
     * 8、纳入结果中
     * 9、返回结果
     * @author panlingfeng
     * @date 2021/12/24 5:17 下午
     * @params type 类型，月 日
     * @params time 统计日期
     * @return Result 统一返回对象
     */
    @GetMapping("query")
    @CrossOrigin
    public Result query(@RequestParam("type") String type, @RequestParam("time") String time) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(time)) {
            //准备好参数查询容器和返回结果容器
            HashMap<String, String> hashMap = new HashMap<>();
            HashMap<String, Object> resultMap = new HashMap<>();
            hashMap.put(type, time);
            //报警记录统计
            //实时报警数量
            int countForRealTime = dao.count("MXPE01.queryCountForRealTime", hashMap);
            //今日解除报警数量
            int countForCancel = dao.count("MXPE01.queryCountForCancel", hashMap);
            //已确认的报警数量
            int countForConfirmed = dao.count("MXPE01.queryCountForConfirmed", hashMap);
            //未确认的报警数量
            int countForUnconfirmed = dao.count("MXPE01.queryCountForUnconfirmed", hashMap);
            //报警类型占比分析
            Object countForAlarmType = dao.query("MXPE01.queryCountForAlarmType", hashMap);
            //报警位置占比分析
            Object countForClassify = dao.query("MXPE01.queryCountForClassify", hashMap);
            //历史报警情况
            Object addForHistory = null, existForHistory = null;
            if (MONTH.equalsIgnoreCase(type)) { //判断是否是月
                addForHistory = dao.query("MXPE01.queryStatsMonthAddForHistory", hashMap);
                hashMap.put("exist", "flag");
                existForHistory = dao.query("MXPE01.queryStatsMonthAddForHistory", hashMap);
            } else {
                addForHistory = dao.query("MXPE01.queryStatsDayAddForHistory", hashMap);
                hashMap.put("exist", "flag");
                existForHistory = dao.query("MXPE01.queryStatsDayAddForHistory", hashMap);
            }
            //纳入结果中
            if (addForHistory != null) {
                resultMap.put("addForHistory", addForHistory);
            }
            if (existForHistory != null) {
                resultMap.put("existForHistory", existForHistory);
            }
            resultMap.put("countForRealTime", countForRealTime);
            resultMap.put("countForCancel", countForCancel);
            resultMap.put("countForConfirmed", countForConfirmed);
            resultMap.put("countForUnconfirmed", countForUnconfirmed);
            resultMap.put("countForAlarmType", countForAlarmType);
            resultMap.put("countForClassify", countForClassify);
            return Result.ok(resultMap); //返回结果
        }
        return Result.error();
    }
}
