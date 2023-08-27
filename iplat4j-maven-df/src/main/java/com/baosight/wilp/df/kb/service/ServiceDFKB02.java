package com.baosight.wilp.df.kb.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.util.DFUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author liangyongfei
 * @version V5.0.4
 * @Description: 设备年度统计看板Service
 * @ClassName: ServiceDFKB02
 * @Package com.baosight.wilp.df.kb
 * @date: 2022年06月10日 10:50
 */
public class ServiceDFKB02 extends ServiceBase {
    private static final String PROCESS_INSPECT = "巡检";
    private static final String PROCESS_MAINTAIN = "保养";
    private static final String PROCESS_CLEAN = "保洁";
    private static final String PROCESS_LUBRICATE = "润滑";

    private static final String CONTRAST_COLOR_GREEN = "green";
    private static final String CONTRAST_COLOR_RED = "red";
    /*****************************************************基本信息*************************************************/

    /**
     * @Title: queryBaseViewTableData
     * @Description: 基础信息页面-获取设备基础信息
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryBaseViewTableData(EiInfo inInfo) {
        //获取参数
        String id  = inInfo.getString("deviceId");
        //数据查询
        List<LinkedHashMap<String, String>> list = dao.query("DFKB02.getDeviceBasicInformation", id);
        if (CollectionUtils.isEmpty(list)) {
            inInfo.set("data", new ArrayList<>());
        } else {
            List<Map<String, String>> data = DFUtils.mapSplit(list.get(0), 3);
            inInfo.set("data", data);
        }
        return inInfo;
    }

    /**
     * @Title: queryBaseViewChartData
     * @Description:  基础信息页面-获取设备信息图表数据
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryBaseViewChartData(EiInfo inInfo) {
        //获取参数
        String id  = inInfo.getString("deviceId");
        String message = "";
        //从小代码中获取需填写的设备档案的所有字段数
        List<Map<String, String>> listCount = CommonUtils.getDataCode("wilp.df.kb.count");
        //获取所有设备档案已填写的信息
        Integer listFailF = 0;
        Map<String,Object> map = new HashMap<>(4);
        map.put("id",id);
        for(int i = 0;i < listCount.size();i++) {
            String df = listCount.get(i).get("value");
            map.put("df",df);
            List<Integer> listFail = dao.query("DFKB02.getIconDisplayFailYu", map);
            listFailF += listFail.get(0);
        }
        //计算该设备信息百分比
        Integer filedCount = listCount.size();
        Double listRate = 0.00;
        if(filedCount > 0) {
            listRate = BigDecimal.valueOf(listFailF).multiply(new BigDecimal(100))
                .divide(BigDecimal.valueOf(filedCount), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        //基础信息右下方数据，显示启用日期，已使用数据
        List<Map<String, String>> listArray  = dao.query("DFKB02.getIconDisplayArray", id);
        Map<String,String> useMap = listArray.get(0);
        //计算该设备信息使用年限百分比
        Double useDay = Double.valueOf(String.valueOf(useMap.get("useDay")));
        Double useLimit = StringUtils.isBlank(useMap.get("useLimit")) ? 0d : Double.valueOf(useMap.get("useLimit"));
        Double useRate = 0.00;
        if(useLimit > 0) {
            useRate = Double.valueOf((useDay/useLimit)*100);
        }
        //计算该设imit信息
        if(useRate >=80){
            message += "设备即将接近使用年限, 准备更换";
        }
        //计算该设备的评分
        if( listRate > 80 && useDay ==0){
            message += "设备基本情况良好 ";
        }
        if(listRate <= 80){
            message += "设备信息缺失较多, 需要完善";
        }
        //信息完整度
        Map<String, String> complaint = new HashMap<>(4);
        complaint.put("allCount", String.valueOf(listCount.size()));
        complaint.put("fillCount", String.valueOf(listFailF));
        complaint.put("percent", listRate+"%");
        //设备使用年限
        Map<String, String> useLimitMap = new HashMap<>(4);
        useLimitMap.put("useTime", String.valueOf(useDay));
        useLimitMap.put("startTime",useMap.get("startTime"));
        useLimitMap.put("usePercent", useRate+"%");
        //设备评分和评价信息
        Map<String, String> score = new HashMap<>(4);
        score.put("grade", String.valueOf(listRate));
        score.put("message",message);
        //数据返回
        Map<String, Object> data = new HashMap<>(4);
        data.put("completion", complaint);
        data.put("useLimit", useLimitMap);
        data.put("score", score);
        inInfo.set("data",data);
        return inInfo;
    }

    /*****************************************************维护情况*************************************************/

    /**
     * @Title: queryPlanAndFinishData
     * @Description: 获取本年计划、实际完成数据
     * @param inInfo inInfo
     *      year: 年份
     *      deviceId: 设备ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      list:[{
     *          label:操作名称(巡检、保养、保洁、润滑),
     *          account: 计划总数(任务数),
     *          percent: 完成率
     *      },],
     *      imTableData: [{
     *          label: 属性说明,
     *          inspection: 巡检属性说明值,
     *          maintenance:保养属性说明值
     *      },],
     *      clTableData: [{
     *          label: 属性说明,
     *          amount:属性说明值
     *      },],
     *      score: {
     * 		    grade: 评价,
     * 			message: 评价说明,
     *      }
     * @throws
     **/
    public EiInfo queryRepairViewData(EiInfo inInfo) {
        Map<String, Object> data = new HashMap<>(4);
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("year", "deviceId", "deviceName"));

        /**2.获取指定设备的巡检任务统计数据**/
        Map<String, String> diMap = countDiOrDkTask(params, "countInspectTask");

        /**3.获取指定设备的保养任务统计数据**/
        Map<String, String> dkMap = countDiOrDkTask(params, "countMaintainTask");

        /**4.获取保洁任务数**/
        int cleanCount = super.count("DFKB02.countCleanTask", params);

        /**5.获取润滑任务数**/
        int lubricateCount = super.count("DFKB02.countLubricateTask", params);

        /**6.构建返回数据**/
        //构建返回list数据
        List<Map<String, String>> list = buildListResult(diMap, dkMap, cleanCount, lubricateCount);
        //构建巡检、保养表格数据
        List<Map<String, String>> imTableData = buildImTableData(diMap, dkMap);
        //构建保洁、润滑表格数据
        List<Map<String, String>> clTableData = buildClTableData(cleanCount, lubricateCount);

        /**7.计算评分**/
        Map<String, String> score = calculatePoint(diMap, dkMap);

        /**8.数据返回**/
        data.put("list", list);
        data.put("imTableData", imTableData);
        data.put("clTableData", clTableData);
        data.put("score", score);
        inInfo.set("data", data);
        return inInfo;
    }

    /**
     * @Title: buildListResult
     * @Description: 构建页面图表部分数据
     * @param diMap diMap 巡检任务统计Map
     * @param dkMap dkMap 保养任务统计Map
     * @param cleanCount cleanCount 保洁任务数量
     * @param lubricateCount lubricateCount 润滑任务数量
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> buildListResult(Map<String, String> diMap, Map<String, String> dkMap, int cleanCount, int lubricateCount) {
        List<Map<String, String>> list = new ArrayList<>();
        //巡检
        diMap.put("label", PROCESS_INSPECT);
        list.add(diMap);
        //保养
        dkMap.put("label", PROCESS_MAINTAIN);
        list.add(dkMap);
        //保洁
        Map<String, String> cleanMap = new HashMap<>(4);
        cleanMap.put("label", PROCESS_CLEAN);
        cleanMap.put("account", String.valueOf(cleanCount));
        cleanMap.put("percent", cleanCount > 0 ? "100%" : "0%");
        list.add(cleanMap);
        //润滑
        Map<String, String> lubricateMap = new HashMap<>(4);
        lubricateMap.put("label", PROCESS_LUBRICATE);
        lubricateMap.put("account", String.valueOf(lubricateCount));
        lubricateMap.put("percent", lubricateCount > 0 ? "100%" : "0%");
        list.add(lubricateMap);
        return list;
    }

    /**
     * @Title: buildImTableData
     * @Description: 构建页面巡检、保养表格数据
     * @param diMap diMap 巡检任务统计Map
     * @param dkMap dkMap 保养任务统计Map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> buildImTableData(Map<String, String> diMap, Map<String, String> dkMap) {
        List<Map<String, String>> list = new ArrayList<>();

        //构建labelMap
        String label = " {\"account\":\"计划次数\",\"finishAmount\":\"完成次数\",\"executeAmount\":\"执行中\",\"timeoutAmount\":\"超时次数\",\"percent\":\"完成率\"}";
        LinkedHashMap<String, String> labelMap = JSON.parseObject(label, LinkedHashMap.class);
        //遍历，构建list
        labelMap.forEach((key, value) -> {
            Map<String, String> map = new HashMap<>(4);
            map.put("label", value);
            map.put("inspection", diMap.get(key));
            map.put("maintenance", dkMap.get(key));
            list.add(map);
        });
        return list;
    }

    /**
     * @Title: buildClTableData
     * @Description:构建页面保洁、润滑表格数据
     * @param cleanCount cleanCount 保洁任务数量
     * @param lubricateCount lubricateCount 润滑任务数量
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> buildClTableData(int cleanCount, int lubricateCount) {
        List<Map<String, String>> list = new ArrayList<>();
        //保洁
        Map<String, String> cleanMap = new HashMap<>(4);
        cleanMap.put("label", PROCESS_CLEAN);
        cleanMap.put("amount", String.valueOf(cleanCount));
        list.add(cleanMap);
        //润滑
        Map<String, String> lubricateMap = new HashMap<>(4);
        lubricateMap.put("label", PROCESS_LUBRICATE);
        lubricateMap.put("amount", String.valueOf(lubricateCount));
        list.add(lubricateMap);
        return list;
    }

    /**
     * @Title: calculatePoint
     * @Description: 计算评分
     * @param maps ：可变参数集合
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> calculatePoint(Map<String, String>... maps) {
        Map<String, String> result = new HashMap<>(4);
        Integer divide = 0, grade = 100; String message = "";
        BigDecimal totalRate = BigDecimal.ZERO;
        for (Map<String, String> map : maps) {
            if(Integer.valueOf(String.valueOf(map.get("account"))) > 0){
                divide++;
            }
            totalRate = totalRate.add(new BigDecimal(map.get("percent").replace("%", "")));
        }
        if(divide > 0){
            grade = totalRate.divide(new BigDecimal(divide), 0, BigDecimal.ROUND_HALF_UP).intValue();
        }

        //信息
        if (grade > 90) {
            message = "执行率良好，明年继续保持";
        } else if (grade > 80) {
            message = "执行率较低，明年需要加强";
        } else {
            message = "执行率很差，明年重点监察";
        }
        //数据返回
        result.put("grade", String.valueOf(grade));
        result.put("message", message);
        return result;
    }

    /**
     * @Title: getInspectTask
     * @Description: 获取指定设备指定年份内巡检/保养任务数、任务完成数、执行中数量、超时数量、完成率
     * @param params params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> countDiOrDkTask(Map<String, Object> params, String sqlStatementId) {
        List<Map<String, String>> list = dao.query("DFKB02."+sqlStatementId, params);
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>(4);
        } else {
            Map<String, String> map = list.get(0);
            Integer account = Integer.valueOf(String.valueOf(map.get("account")));
            Integer finishAmount = Integer.valueOf(String.valueOf(map.get("finishAmount")));
            BigDecimal rate = account == 0 ? BigDecimal.ZERO : new BigDecimal(finishAmount).multiply(new BigDecimal(100))
                    .divide(new BigDecimal(account), 2, BigDecimal.ROUND_HALF_UP);
            map.put("percent", rate+ "%");
            return map;
        }
    }
    
    /*****************************************************同期对比*************************************************/

    /**
     * @Title: queryContrastViewData
     * @Description: 获取同期对比页面数据
     * @param inInfo inInfo
     *      year: 年份
     *      deviceId: 设备ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryContrastViewData(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("year", "deviceId", "deviceName"));

        /**2.获取指定年份巡检、保养、保洁、润滑完工数据**/
        Map<String, Integer> curData = getFinishData(params);

        /**3.获取定年份的上一年巡检、保养、保洁、润滑完工数据**/
        String lastYear = String.valueOf((Integer.valueOf(params.get("year").toString()) - 1));
        params.put("year", lastYear);
        Map<String, Integer> lastData = getFinishData(params);

        /**4.构建返回数据**/
        //构建对比上升/下降数据
        List<Map<String, Object>> arrow = buildArrowData(curData, lastData);
        //构建对比图表数据
        Map<String, Object> chartData = buildChartData(curData, lastData, inInfo.getString("year"), lastYear);
        //构建评分数据
        String score = buildScoreData(arrow,chartData);

        /**5.数据返回**/
        Map<String, Object> data = new HashMap<>(8);
        data.put("grade", score);
        data.put("arrow", arrow);
        data.put("echartsData", chartData);
        inInfo.set("data", data);
        return inInfo;
    }

    /**
     * @Title: getFinishData
     * @Description: 获取指定年份巡检、保养、保洁、润滑完工数据
     * @param params params
     *      year: 年份
     *      deviceId: 设备ID
     * @return java.util.Map<java.lang.String,java.lang.Integer>
     *     diFinishAmount: 巡检完工量
     *     dkFinishAmount: 保养完工量
     *     cleanFinishAmount: 保洁完工量
     *     lubricateFinishAmount: 润滑完工量
     * @throws
     **/
    private Map<String, Integer> getFinishData(Map<String, Object> params) {
        //获取巡检的完工量
        int diFinishAmount = super.count("DFKB02.inspectionCount",params);
        //获取保养的完工量
        int dkFinishAmount = super.count("DFKB02.maintainCount",params);
        //获取保洁的完工量
        int cleanFinishAmount = super.count("DFKB02.countCleanTask",params);
        //获取润滑的完工量
        int lubricateFinishAmount = super.count("DFKB02.countLubricateTask",params);
        //数据返回
        Map<String, Integer> map = new LinkedHashMap<>(8);
        map.put("diFinishAmount", diFinishAmount);
        map.put("dkFinishAmount", dkFinishAmount);
        map.put("cleanFinishAmount", cleanFinishAmount);
        map.put("lubricateFinishAmount", lubricateFinishAmount);
        return map;
    }

    /**
     * @Title: buildArrowData
     * @Description: 构建对比上升/下降数据
     * @param curData curData: 指定年份巡检、保养、保洁、润滑完工数据
     * @param lastData lastData: 指定年份上一年份巡检、保养、保洁、润滑完工数据
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     *     up: 上升/下降标志值为true/false,
     *     color: 颜色,值为green、red,
     *     name: 值为巡检、保养、保洁、润滑,
     * @throws
     **/
    private List<Map<String, Object>> buildArrowData(Map<String, Integer> curData, Map<String, Integer> lastData) {
        List<Map<String, Object>> list = new ArrayList<>();
        //构建name的Map
        String names = "{\"diFinishAmount\": \"巡检\",\"dkFinishAmount\": \"保养\",\"cleanFinishAmount\": \"保洁\",\"lubricateFinishAmount\": \"润滑\"}";
        LinkedHashMap<String, String> nameMap = JSON.parseObject(names, LinkedHashMap.class);
        //遍历
        nameMap.forEach((key, value) -> {
            Map<String, Object> map = new HashMap<>(4);
            map.put("name", value);
            Integer curValue = curData.get(key);
            Integer lastValue = lastData.get(key);
            if (curValue >= lastValue) {
                map.put("up", true);map.put("color", CONTRAST_COLOR_GREEN);
            } else {
                map.put("up", false);map.put("color", CONTRAST_COLOR_RED);
            }
            list.add(map);
        });
        return list;
    }

    /**
     * @Title: buildChartData
     * @Description: 构建同期对比图表数据
     * @param curData curData: 指定年份巡检、保养、保洁、润滑完工数据
     * @param lastData lastData: 指定年份上一年份巡检、保养、保洁、润滑完工数据
     * @param year year : 年份
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
     **/
    private Map<String, Object> buildChartData(Map<String, Integer> curData, Map<String, Integer> lastData, String year, String lastYear) {
        Map<String, Object> result = new HashMap<>(8);
        result.put("axisX", new String[]{PROCESS_INSPECT, PROCESS_MAINTAIN, PROCESS_CLEAN, PROCESS_LUBRICATE});
        result.put("tag", new String[]{year+"执行率", lastYear+"执行率"});
        List<Integer> now = JSON.parseObject(JSON.toJSONString(curData.values()), ArrayList.class);
        List<Integer> prev = JSON.parseObject(JSON.toJSONString(lastData.values()), ArrayList.class);
        result.put("now", now);
        result.put("prev", prev);
        return result;
    }

    /**
     * @Title: buildScoreData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param arrow arrow 对比上升/下降数据
     * @param chartData
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private String buildScoreData(List<Map<String, Object>> arrow, Map<String, Object> chartData) {
        List<Integer> now = (List<Integer>) chartData.get("now");
        List<Integer> prev = (List<Integer>) chartData.get("prev");
        //计算得分
        Double grade = 0d;
        for (int i = 0; i < 4; i++) {
            //如果是上升箭头 或者之前的完成时0(1:当前有数据那么肯定大于等于之前, 2:当前无数据两者都是0也返回100*0.25)
            if ((boolean)arrow.get(i).get("up") || prev.get(i) == 0) {
                grade += 100 * 0.25;
            } else {
                //否则获得 当前年/上一年  * 100%
                BigDecimal point = new BigDecimal(now.get(i)).multiply(new BigDecimal(100))
                        .divide(new BigDecimal(prev.get(i)), 2, BigDecimal.ROUND_HALF_UP);
                grade += point.multiply(new BigDecimal(0.25)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        return String.valueOf(grade);
    }

    /*****************************************************全年评价*************************************************/

    /**
     * @Title: queryEvalViewData
     * @Description: 全年评价页面数据接口
     * @param inInfo inInfo
     *      year : 年度
     *      deviceId : 设备Id
     *      basicGrade : 基本信息页面评价
     *      repairGrade : 维护情况页面评价
     *      contrastGrade : 同期对比页面评价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryEvalViewData(EiInfo inInfo) {
        Map<String, String> result = new HashMap<>(8);
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("year", "deviceId", "deviceName"));
        String basicGrade = inInfo.getString("basicGrade");
        String repairGrade = inInfo.getString("repairGrade");
        String contrastGrade = inInfo.getString("contrastGrade");

        /**2.评分参数为空时,获取对应页面的评价**/
        //基本信息页面评价
        if(StringUtils.isBlank(basicGrade)){
            Map<String, Object> baseData = (Map<String, Object>) queryBaseViewChartData(inInfo).get("data");
            basicGrade = baseData.get("score") == null ? "" : ((Map<String, String>)baseData.get("score")).get("grade");
        }
        result.put("baseInfo", basicGrade);
        //维护情况页面评价
        if(StringUtils.isBlank(repairGrade)){
            Map<String, Object> repairData = (Map<String, Object>) queryRepairViewData(inInfo).get("data");
            repairGrade = repairData.get("score") == null ? "" : ((Map<String, String>)repairData.get("score")).get("grade");
        }
        result.put("mainSit", repairGrade);
        //同期对比页面评价
        if(StringUtils.isBlank(contrastGrade)){
            Map<String, Object> contrastData = (Map<String, Object>) queryContrastViewData(inInfo).get("data");
            contrastGrade = String.valueOf(contrastData.get("grade"));
        }
        result.put("contrast", contrastGrade);

        /**3.获取OEE评分、故障率**/
        Map<String, String> map = countOEEAndErrorRate(params);
        result.putAll(map);

        /**4.计算总评***/
        Double point = Double.valueOf(basicGrade) * 0.05 + Double.valueOf(repairGrade) * 0.3 + Double.valueOf(contrastGrade) * 0.1
                + Double.valueOf(map.get("oee").replace("%", "")) * 0.3
                + (100 - Double.valueOf(map.get("errRate").replace("%", ""))) * 0.25;
        result.putAll(Score.getScore(point));

        /**5.数据返回**/
        inInfo.set("data", result);
        return inInfo;
    }

    /**
     * @Title: countOEEAndErrorRate
     * @Description: 计算OEE评分、故障率
     * @param params params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> countOEEAndErrorRate(Map<String, Object> params) {
        Map<String, String> result = new HashMap<>(4);
        /**
         * 计算oee
         *  EA = 1 - 维修时间/运行时间
         *  PE = 1 - (保养+润滑)/运行时间
         *  OEE(%) = EA*PE*100
         **/

        /**计算故障率(维修时间/运行时间)**/

        result.put("oee", "100%");
        result.put("errRate", "0%");
        return result;
    }

    /**
     * @Title:
     * @Description: 总评枚举
     * @throws
     **/
    enum Score {
        VERY_TALENTED(95d, "A+", "成绩优秀,明年再接再厉"),
        EXCELLENT(90d, "A", "成绩优秀,明年再接再厉"),
        SLIGHTLY_EXCELLENT(85d, "A-", "明年再接再厉"),
        VERY_GOOD(80d, "B+", "成绩良好,明年继续加油"),
        GOOD(75d, "B+", "成绩良好,明年继续加油"),
        SLIGHTLY_GOOD(70d, "B+", "成绩良好,明年继续加油"),
        GENERAL(60d, "C", "成绩良好,"),
        BAD(0d, "D", "成绩较差, 明年请改正");

        /**
         * 分数
         **/
        private Double point;
        /**
         * 评价
         **/
        private String grade;
        /**
         * 说明
         **/
        private String message;

        Score(Double point, String grade, String message) {
            this.point = point;
            this.grade = grade;
            this.message = message;
        }

        /**
         * @Title: getScore
         * @Description: 获取总评数据
         * @param point point
         * @return java.util.Map<java.lang.String,java.lang.String>
         * @throws
         **/
        public static Map<String, String> getScore (Double point) {
            Map<String, String> map = new HashMap<>(4);
            if (point >= VERY_TALENTED.point) {
                buildMap(map, VERY_TALENTED);
            } else if (point >= EXCELLENT.point) {
                buildMap(map, EXCELLENT);
            } else if (point >= SLIGHTLY_EXCELLENT.point) {
                buildMap(map, SLIGHTLY_EXCELLENT);
            } else if (point >= VERY_GOOD.point) {
                buildMap(map, VERY_GOOD);
            } else if (point >= GOOD.point) {
                buildMap(map, GOOD);
            } else if (point >= SLIGHTLY_GOOD.point) {
                buildMap(map, SLIGHTLY_GOOD);
            } else if (point >= GENERAL.point) {
                buildMap(map, GENERAL);
            } else {
                buildMap(map, BAD);
            }
            return map;
        }

        private static void buildMap(Map<String, String> map, Score score) {
            map.put("grade", score.grade);
            map.put("message", score.message);
        }
    }

}
