package com.baosight.wilp.hr.pb.service;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.baosight.wilp.hr.pb.domain.HRPBJhgl01;
import com.baosight.wilp.hr.pb.domain.HRPBSjpz01;
import com.baosight.wilp.hr.pb.domain.HRWorkPlan;
import com.baosight.wilp.hr.pb.utils.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;


/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHRPBJHGL01 extends ServiceBase {
    
    private static final String filePath = "drTemplate";
    private static final String drFilePath = "dr/file/";

    /**
     *
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo initLoad = super.initLoad(inInfo, new HRPBSjpz01());
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        //初始化加载数据deptList:班组信息
//        paramMap.put("userId", UserSession.getLoginName());
//        List<HashMap<String,Object>> listCanteenData = dao.query("DRPBSjpz01.getDeptData", paramMap);
//        initLoad.addBlock("canteenData").addRows(listCanteenData);
//        String date1 = "2022-01-01";
        String date = CalendarUtils.dateFormat(new Date());
        LinkedHashMap linkmap = null;
        try {
            linkmap = WeekDateUtils.getWeekMap(date);
            initLoad.addBlock("planDate").addRow(linkmap);
            List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
            Set<String> set = linkmap.keySet();
            for (String key : set) {
                int index = Integer.parseInt(key);
                Map<String,Object> map = new HashMap<>();
                map.put("typeName" ,"第" + (index + 1) + "周");
                map.put("typeCode" ,"Week" + index);
                listMap.add(map);
            }
            initLoad.addBlock("planWeek").addRows(listMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initLoad;
    }


    /**
     *
     * grid数据集查询订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#query(EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiBlock block = inInfo.getBlock("inqu_status");
        String weekAbc = "";
        String planDate = "";
        try {
            if(block != null) {
                //从查询条件中获取周次
                weekAbc = block.getCellStr(0, "weekAbc");
                weekAbc = weekAbc.split("Week")[1];
                //从查询条件中排班日期
                planDate = block.getCellStr(0, "planDate");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            date = format.parse(planDate);
            //设置查询条件的起止日期
            Date weekFirst = WeekDateUtils.getWeekFirstDay(Integer.parseInt(weekAbc), date);
            Date weekLast = WeekDateUtils.getWeekLastDay(Integer.parseInt(weekAbc), date);
            block.setCell(0,"weekFristDay",format.format(weekFirst));
            block.setCell(0,"weekLastDay",format.format(weekLast));
            //获取日期对应月份该周次的起止日期
            inInfo = super.query(inInfo, "HRPBJhgl01.queryFsfy", new HRPBJhgl01());
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setStatus(-1);
        }
        return inInfo;
    }

    /**
     *
     * 删除排班计划(未使用)
     * @param inInfo
     * @return
     * @see ServiceBase#delete(EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            outInfo = super.delete(inInfo, "HRPBSjpz01.delete");
            outInfo.setMsg("操作成功！");
        }catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("操作失败！");
            inInfo.setStatus(-1);
            return inInfo;
        }
        return outInfo;
    }


    /**
     *
     * 新增排班计划(未使用)
     * @param inInfo
     * @return
     * @see ServiceBase#insert(EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
        }catch (Exception e) {
            e.printStackTrace();
            outInfo.setMsg("保存失败！");
        }
        return outInfo;
    }

    /**
    *
    * -根据所选的班组和日期，导出那一月的排班模板
    * @param inInfo
    * @return
    */
    @SuppressWarnings("unchecked")
    public EiInfo downloadFile(EiInfo inInfo) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //获取传参
        Map attr = inInfo.getAttr();
        long startTime = System.currentTimeMillis();
        
        String planDate = StringUtil.toString(attr.get("planDate"));
        String yearMonth = StringUtil.toString(attr.get("yearMonth"));
        String deptNum = StringUtil.toString(attr.get("deptNum"));
        String deptName = StringUtil.toString(attr.get("deptName"));
        try {
            //生成表头数据，添加姓名，工号，科室编码，科室名称的表头信息
            List<List<String>> monthDays = WeekDateUtils.getMonthDays(planDate);
            List<String> list = new ArrayList<String>();
            list.add("科室名称");
            monthDays.add(0, list);
            list = new ArrayList<String>();
            list.add("科室编码");
            monthDays.add(0, list);
            list = new ArrayList<String>();
            list.add("工号");
            monthDays.add(0, list);
            list = new ArrayList<String>();
            list.add("姓名");
            monthDays.add(0, list);
            //查询表体数据
            List<DemoData2> queryList = SqlUtils.queryForListByString("HRPBJhgl01.queryForTemplateFsfy", deptNum);
            //生成模板文件存放地址
            String savePath = request.getSession().getServletContext().getRealPath("")+filePath+File.separatorChar;
            File files = new File(savePath);
            if (!files.isDirectory()) {
                //判断目录是否存在，不存在则创建目录
                files.mkdirs();
            }
            String timeMillis = deptName + yearMonth + "_" + CalendarUtils.dateTimeShortFormat(null);
            String fileName =  timeMillis + ".xlsx";
            //调用easyExcel组件生成Excel文件
            ExcelWriteUtils.writeExcel(savePath + fileName,"排班计划",monthDays,queryList);
            long endTime = System.currentTimeMillis();
            System.out.println("####################执行耗时" + (endTime - startTime) + "ms");
            //拼接文件路径返回前台
            String fileUrl = File.separatorChar + filePath + File.separatorChar + fileName;
            inInfo.set("fileUrl", fileUrl);
            inInfo.setStatus(1);
            inInfo.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("操作失败！");
        }
        return inInfo;
    }
    
    /**
    * -接收上传的文件
    * @param inInfo
    * @return EiInfo
    */
    @SuppressWarnings({"rawtypes"})
    public EiInfo uploadFile(EiInfo inInfo) {
        Map attr = inInfo.getAttr();
        /**
         * 1.获取上传的excel文件
         */
        String docUrl = StringUtil.toString(attr.get("docUrl"));
        String fileType = StringUtil.toString(attr.get("fileType"));
        if(!".xlsx".equals(fileType)){
            inInfo.setMsg("文件类型错误！");
            inInfo.setStatus(-1);
            return inInfo;
        }
        try {
            File file = new File(docUrl);
            //附件路径
            String docRootDir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + drFilePath;
            File files = new File(docRootDir);
            if (!files.isDirectory()) {
                System.out.println("没有该路径,创建目录"+docRootDir);
                files.mkdirs();
            }
            if (file != null) {
                /**
                 * 2.解析文件
                 */
                Map<String, List> redExcel = EasyExcelUtils.redExcel(docUrl);
                List<Map<String,Object>> listMap = handleExcelData(redExcel);
                //获取登录人信息
                String loginName = UserSession.getLoginName();
                String loginCName = UserSession.getLoginCName();
                /**
                 * 3.判断是否为班组管理员
                 */
                
                String deptNum = "";
                for (int i = 0; i < listMap.size(); i++) {
                    String deptNum1 = StringUtil.toString(listMap.get(i).get("deptNum"));
                    if(!deptNum.equals(deptNum1)) {
                        boolean flag = ACServiceUtils.checkWardenFsfy(loginName,deptNum1);
                        if(!flag){
                            String deptName = StringUtil.toString(listMap.get(i).get("deptName"));
                            inInfo.setStatus(-1);
                            inInfo.setMsg("非" + deptName + "管理员，无法调整排班信息");
                            return inInfo;
                        }
                    }
                    deptNum = deptNum1;
                }
                /**
                 * 4.验证文件中的班组成员和日期是否已有排班
                 */
                int count = 0;
                for (int i = 0; i < listMap.size(); i++) {
                    count += SqlUtils.countByMap("HRPBJhgl01.countPlan",listMap.get(i));
                    if(count > 0) {
                        //有排班返回提示
                        inInfo.setStatus(2);
                        inInfo.setMsg("已有部分排班，是否覆盖？");
                        return inInfo;
                    }
                }
                /**
                 * 5.没有重复执行导入
                 */
                List<HRWorkPlan> insertPbjhList = new ArrayList<HRWorkPlan>();
                HashMap<String,Object> sjpzParam = new HashMap<String, Object>();
                for (int i = 0; i < listMap.size(); i++) {
                    Map<String, Object> map = listMap.get(i);
                    HRWorkPlan drWorkPlan = new HRWorkPlan();
                    String workNo = (String)map.get("workNo");
                    String workName = (String)map.get("workName");
                    String planDeptNum = (String)map.get("deptNum");
                    String deptName = (String)map.get("deptName");
                    String planDate = (String)map.get("planDate");
                    String plan = (String)map.get("plan");
                    String week = WeekDateUtils.getWeek(planDate,"yyyy-MM-dd");
                    drWorkPlan.setRecCreator(loginName);
                    drWorkPlan.setRecCreatorName(loginCName);
                    drWorkPlan.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
                    //通过工号查询员工信息
                    EiInfo callWorkInfo = ACServiceUtils.queryWorkInfoFsfy(workNo);
                    HashMap<String, Object> workMap = (HashMap<String, Object>) callWorkInfo.get("result");
                    if(workMap == null) {
                        //没查到这个人
                        inInfo.setStatus(-1);
                        inInfo.setMsg(workName + "-" + workNo+"未在系统中登记，请检查！");
                        return inInfo;
                    }
                    //将查询结果复制到实体类
                    drWorkPlan = LocalServiceUtil.copyObject(workMap,drWorkPlan);
                    //复制完再设id
                    drWorkPlan.setId(UUIDUtils.getUUID32());
                    drWorkPlan.setStatus("01");
                    drWorkPlan.setPlanDate(planDate);
                    drWorkPlan.setWeek(week);
                    drWorkPlan.setWorkName(workName);
                    drWorkPlan.setDeptNum(planDeptNum);
                    drWorkPlan.setDeptName(deptName);
                    //查询班次时间配置,填充班次信息
                    sjpzParam.put("deptNum", planDeptNum);
                    sjpzParam.put("planName", plan);
                    HRPBSjpz01 sjpz = (HRPBSjpz01)SqlUtils.queryForObjectByMap("HRPBSjpz01.queryByName",sjpzParam);
                    if(sjpz == null) {
                        //没查到时间配置
                        inInfo.setStatus(-1);
                        inInfo.setMsg(deptName + "-" + plan+"班次不存在，请检查！");
                        return inInfo;
                    }
                    drWorkPlan.setScheduleId(sjpz.getId());
                    drWorkPlan.setScheduleName(sjpz.getScheduleName());
                    drWorkPlan.setStartTime(sjpz.getStartTime());
                    drWorkPlan.setEndTime(sjpz.getEndTime());
                    drWorkPlan.setRestStartTime(sjpz.getRestStartTime());
                    drWorkPlan.setRestEndTime(sjpz.getRestEndTime());
                    drWorkPlan.setDaySpan(sjpz.getDaySpan());
                    //根据班次信息计算上下班具体情况(预留考勤使用暂不实现)
                    insertPbjhList.add(drWorkPlan);
                }
                //执行insert方法保存排班
                if(insertPbjhList != null && insertPbjhList.size() > 0){
                    //插入数据
                    inInfo =  SqlUtils.insertSqlByList("HRPBJhgl01.insert",insertPbjhList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("上传失败！");
        }
        return inInfo;
    }
    
    /**
     * handleExcelData整理excel中的数据
     * */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> handleExcelData(Map<String, List> redExcel) {
        List<Map<Integer, String>> listHead = redExcel.get("head");
        List<DemoData2> listBody = redExcel.get("body");
        /**
         * 1.整理excel中的数据
         */
        Map<Integer, String> headMap = listHead.get(0);
        Set<Integer> keySet = headMap.keySet();
        //分解表头获取日期
        List<String> listDate = new ArrayList<String>();
        for (Integer key : keySet) {
            if(headMap.get(key).indexOf("(") > -1) {
                String value = headMap.get(key).split("\\(")[0];
                //把excel默认的日期格式yyyy/MM/dd替换成数据库保存的统一格式yyyy-MM-dd
                listDate.add(value.replaceAll("/", "-").trim());
            }
        }
        System.out.println(listDate);
        //分解表体获取排班
        List<JSONObject> listPlan = new ArrayList<JSONObject>();
        for (int i = 0; i < listBody.size(); i++) {
            listBody.get(i);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(listBody.get(i)));
            listPlan.add(jsonObject);
        }
        System.out.println(listPlan);
        /**
         * 2.整理排班表的员工、班组、班次
         */
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
        for (int j = 0; j < listPlan.size(); j++) {
            JSONObject planJson =listPlan.get(j);
            String workNo = planJson.getString("workNo");
            String workName = planJson.getString("workName");
            String deptNum = planJson.getString("deptNum");
            String deptName = planJson.getString("deptName");
            for (int i = 0; i < listDate.size(); i++) {
                String date = listDate.get(i);
                String plan = planJson.getString("day" + (i+1));
                if(StringUtil.isNotBlank(plan)) {
                    //某日期有排班的添加到集合
                    Map<String,Object> planMap = new HashMap<String, Object>();
                    planMap.put("workNo", workNo);
                    planMap.put("workName", workName);
                    planMap.put("deptNum", deptNum);
                    planMap.put("deptName", deptName);
                    planMap.put("plan", plan);
                    planMap.put("planDate", date);
                    listMap.add(planMap);
                }
            }
        }
        System.out.println(listMap);
        return listMap;
    }
    
    /**
    * -确认覆盖排班
    * @param inInfo
    * @return EiInfo
    */
    public EiInfo uploadPlan(EiInfo inInfo) {
        Map attr = inInfo.getAttr();
        String docUrl = StringUtil.toString(attr.get("docUrl"));
        /**
         * 1.获取上传的excel文件
         */
        try {
            File file = new File(docUrl);
            if (file != null) {
                /**
                 * 2.解析文件
                 */
                Map<String, List> redExcel = EasyExcelUtils.redExcel(docUrl);
                List<Map<String,Object>> listMap = handleExcelData(redExcel);
                //获取登录人信息
                String loginName = UserSession.getLoginName();
                String loginCName = UserSession.getLoginCName();
                List<HRWorkPlan> insertPbjhList = new ArrayList<HRWorkPlan>();
                List<HRWorkPlan> updatePbjhList = new ArrayList<HRWorkPlan>();
                HashMap<String,Object> sjpzParam = new HashMap<String, Object>();
                for (int i = 0; i < listMap.size(); i++) {
                    Map<String, Object> map = listMap.get(i);
                    int count = SqlUtils.countByMap("HRPBJhgl01.countPlan",map);
                    HRWorkPlan drWorkPlan = new HRWorkPlan();
                    String workNo = (String)map.get("workNo");
                    String workName = (String)map.get("workName");
                    String deptNum = (String)map.get("deptNum");
                    String deptName = (String)map.get("deptName");
                    String planDate = (String)map.get("planDate");
                    String plan = (String)map.get("plan");
                    String week = WeekDateUtils.getWeek(planDate,"yyyy-MM-dd");
                    drWorkPlan.setPlanDate(planDate);
                    //查询班次时间配置,填充班次信息
                    sjpzParam.put("deptNum", deptNum);
                    sjpzParam.put("planName", plan);
                    HRPBSjpz01 sjpz = (HRPBSjpz01)SqlUtils.queryForObjectByMap("HRPBSjpz01.queryByName",sjpzParam);
                    if(sjpz == null) {
                        //没查到时间配置
                        inInfo.setStatus(-1);
                        inInfo.setMsg(deptName + "-" + plan+"班次不存在，请检查！");
                        return inInfo;
                    }
                    drWorkPlan.setScheduleId(sjpz.getId());
                    drWorkPlan.setScheduleName(sjpz.getScheduleName());
                    drWorkPlan.setStartTime(sjpz.getStartTime());
                    drWorkPlan.setEndTime(sjpz.getEndTime());
                    drWorkPlan.setRestStartTime(sjpz.getRestStartTime());
                    drWorkPlan.setRestEndTime(sjpz.getRestEndTime());
                    drWorkPlan.setDaySpan(sjpz.getDaySpan());
                    if(count == 0) {
                        //没有重复,需要新增排班计划
                        drWorkPlan.setRecCreator(loginName);
                        drWorkPlan.setRecCreatorName(loginCName);
                        drWorkPlan.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
                        //通过工号查询员工信息
                        EiInfo callWorkInfo = ACServiceUtils.queryWorkInfoFsfy(workNo);
                        HashMap<String, Object> workMap = (HashMap<String, Object>) callWorkInfo.get("result");
                        if(workMap == null) {
                            //没查到这个人
                            inInfo.setStatus(-1);
                            inInfo.setMsg(workName + "-" + workNo+"未在系统中登记，请检查！");
                            return inInfo;
                        }
                        //将查询结果复制到实体类
                        drWorkPlan = LocalServiceUtil.copyObject(workMap,drWorkPlan);
                        //复制完再设id
                        drWorkPlan.setId(UUIDUtils.getUUID32());
                        drWorkPlan.setStatus("01");
                        drWorkPlan.setWeek(week);
                        drWorkPlan.setWorkName(workName);
                        drWorkPlan.setDeptNum(deptNum);
                        drWorkPlan.setDeptName(deptName);
                        //查询班次时间配置,填充班次信息
                        sjpzParam.put("deptNum", deptNum);
                        sjpzParam.put("planName", plan);
                        //根据班次信息计算上下班具体情况(预留考勤使用暂不实现)
                        insertPbjhList.add(drWorkPlan);
                    }else {
                        //有重复的更新排班
                        drWorkPlan.setRecRevisor(loginName);
                        drWorkPlan.setRecRevisorName(loginCName);
                        drWorkPlan.setRecReviseTime(CalendarUtils.dateTimeFormat(new Date()));
                        //通过工号查询员工信息
                        EiInfo callWorkInfo = ACServiceUtils.queryWorkInfoFsfy(workNo);
                        HashMap<String, Object> workMap = (HashMap<String, Object>) callWorkInfo.get("result");
                        if(workMap == null) {
                            //没查到这个人
                            inInfo.setStatus(-1);
                            inInfo.setMsg(workName + "-" + workNo+"未在系统中登记，请检查！");
                            return inInfo;
                        }
                        //将查询结果复制到实体类
                        drWorkPlan = LocalServiceUtil.copyObject(workMap,drWorkPlan);
                        //查询排班记录
                        HRWorkPlan workPlan = (HRWorkPlan)SqlUtils.queryForObjectByMap("HRPBJhgl01.queryWorkPlan",map);
                        //复制完再设id
                        drWorkPlan.setId(workPlan.getId());
                        drWorkPlan.setStatus("01");
                        drWorkPlan.setWorkName(workName);
                        updatePbjhList.add(drWorkPlan);
                    }
                }
                //执行insert方法保存排班
                if(insertPbjhList != null && insertPbjhList.size() > 0){
                    //插入数据
                    inInfo =  SqlUtils.insertSqlByList("HRPBJhgl01.insert",insertPbjhList);
                }
                //执行update方法更新排班
                if(updatePbjhList != null && updatePbjhList.size() > 0){
                    //更新数据
                    inInfo = SqlUtils.updateSqlByList("HRPBJhgl01.updatePlan",updatePbjhList);
                }
                inInfo.setMsg("操作成功！");
            }else {
                inInfo.setStatus(-1);
                inInfo.setMsg("文件已失效，请重新上传");
                return inInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("导入失败！");
        }
        return inInfo;
    }

    /**
     *
     * 编辑数据
     * @param inInfo
     * @return
     * @see ServiceBase#update(EiInfo)
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            //判断是否为班组管理员
            String loginName = UserSession.getLoginName();
            String loginCName = UserSession.getLoginCName();
            EiBlock block = inInfo.getBlock("inqu_status");
            String deptNum = block.getCellStr(0, "deptNum");
            String deptName = block.getCellStr(0, "deptName");
            boolean flag = ACServiceUtils.checkWardenFsfy(loginName,deptNum);
            if(!flag){
                outInfo.setStatus(-1);
                outInfo.setMsg("非" + deptName + "管理员，无法调整排班信息");
                return outInfo;
            }
            //判断当前班次与初始班次相比是否出现了变化
            List<HashMap<String,String>> result = inInfo.getBlock("result").getRows();
            System.out.println(result);
            List<HRWorkPlan> insertPbjhList = new ArrayList<HRWorkPlan>();
            List<HRWorkPlan> updatePbjhList = new ArrayList<HRWorkPlan>();
            List<HashMap<String,String>> updateStatus = new ArrayList<HashMap<String,String>>();
            for (int i = 0; i < result.size(); i++) {
                HashMap<String, String> hashMap = result.get(i);
                for (int j = 0  ; j < 7; j++) {
                    //当前班次
                    String nowSchedule = hashMap.get("week_" + j);
                    String planId = hashMap.get("week_" + j + "_planId");
                    if(StringUtil.isBlank(planId) && StringUtil.isBlank(nowSchedule)){
                        //没有设置排班跳过
                        continue;
                    }
                    String workNo = hashMap.get("workNo");
                    String workName = hashMap.get("workName");
                    String scheduleName = hashMap.get("week_" + j + "_scheduleName");
                    String planDate = hashMap.get("week_" + j + "_planDate");
                    HRWorkPlan drWorkPlan = new HRWorkPlan();
                    drWorkPlan.setScheduleId(nowSchedule);
                    drWorkPlan.setScheduleName(scheduleName);
                    drWorkPlan.setPlanDate(planDate);
                    if(StringUtil.isBlank(planId) && StringUtil.isNotBlank(nowSchedule)){
                        //没有原计划ID,但有班次计划，需要新增排班计划
                        drWorkPlan.setRecCreator(loginName);
                        drWorkPlan.setRecCreatorName(loginCName);
                        drWorkPlan.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
                        //通过工号查询员工信息
                        EiInfo callWorkInfo = ACServiceUtils.queryWorkInfoFsfy(workNo);
                        HashMap<String, Object> workMap = (HashMap<String, Object>) callWorkInfo.get("result");
                        //将查询结果复制到实体类
                        drWorkPlan = LocalServiceUtil.copyObject(workMap,drWorkPlan);
                        //复制完再设id
                        drWorkPlan.setId(UUIDUtils.getUUID32());
                        drWorkPlan.setStatus("01");
                        String week = WeekDateUtils.getWeek(planDate,"yyyy-MM-dd");
                        drWorkPlan.setWeek(week);
                        drWorkPlan.setWorkName(workName);
                        drWorkPlan.setDeptNum(deptNum);
                        drWorkPlan.setDeptName(deptName);
                        //查询班次时间配置,填充班次信息
                        HRPBSjpz01 sjpz = (HRPBSjpz01)SqlUtils.queryForObjectByString("HRPBSjpz01.queryById",nowSchedule);
                        drWorkPlan.setStartTime(sjpz.getStartTime());
                        drWorkPlan.setEndTime(sjpz.getEndTime());
                        drWorkPlan.setRestStartTime(sjpz.getRestStartTime());
                        drWorkPlan.setRestEndTime(sjpz.getRestEndTime());
                        drWorkPlan.setDaySpan(sjpz.getDaySpan());
                        //根据班次信息计算上下班具体情况(预留考勤使用，暂不实现)
                        insertPbjhList.add(drWorkPlan);
                    }else if(StringUtil.isNotBlank(planId)  && StringUtil.isNotBlank(nowSchedule)){
                        //已有排班计划，判断班次是否有改动
                        String oldSchedule = hashMap.get("week_" + j + "_scheduleId");
                        //nowSchedule中含有括号()的为jsp初始化文字，不需要考虑，如：白班(在岗)
                        if(nowSchedule.indexOf("(") < 0 && !oldSchedule.equals(nowSchedule)){
                            //有改动，需要更新排班计划
                            drWorkPlan.setRecRevisor(loginName);
                            drWorkPlan.setRecRevisorName(loginCName);
                            drWorkPlan.setRecReviseTime(CalendarUtils.dateTimeFormat(new Date()));
                            //通过工号查询员工信息
                            EiInfo callWorkInfo = ACServiceUtils.queryWorkInfoFsfy(workNo);
                            HashMap<String, Object> workMap = (HashMap<String, Object>) callWorkInfo.get("result");
                            //将查询结果复制到实体类
                            drWorkPlan = LocalServiceUtil.copyObject(workMap,drWorkPlan);
                            //复制完再设id
                            drWorkPlan.setId(planId);
                            drWorkPlan.setStatus("01");
                            drWorkPlan.setWorkName(workName);
                            //查询班次时间配置,填充班次信息
                            HRPBSjpz01 sjpz = (HRPBSjpz01)SqlUtils.queryForObjectByString("HRPBSjpz01.queryById",nowSchedule);
                            drWorkPlan.setStartTime(sjpz.getStartTime());
                            drWorkPlan.setEndTime(sjpz.getEndTime());
                            drWorkPlan.setRestStartTime(sjpz.getRestStartTime());
                            drWorkPlan.setRestEndTime(sjpz.getRestEndTime());
                            drWorkPlan.setDaySpan(sjpz.getDaySpan());
                            updatePbjhList.add(drWorkPlan);
                        }
                    }
                }
            }
            //执行insert方法保存排班
            if(insertPbjhList != null && insertPbjhList.size() > 0){
                //插入数据
                outInfo =  SqlUtils.insertSqlByList("HRPBJhgl01.insert",insertPbjhList);
            }
            //执行update方法更新排班
            if(updatePbjhList != null && updatePbjhList.size() > 0){
                //更新数据
                outInfo = SqlUtils.updateSqlByList("HRPBJhgl01.updatePlan",updatePbjhList);
            }
            outInfo.setMsg("操作成功！");
        } catch (Exception e) {
            e.printStackTrace();
            outInfo.setMsg("操作失败！");
        }
        return outInfo;
    }

    /**
     * Todo(在岗/休息)
     *
     * @Title: effect
     * @author xiajunqing
     * @date: 2022/1/20 17:05
     * @Param
     * @return:
     */
    public EiInfo effect(EiInfo inInfo){
        Map attr = inInfo.getAttr();
        try {
            //接收传参
            String deptNum = StringUtils.toString(attr.get("deptNum"));
            String deptName = StringUtils.toString(attr.get("deptName"));
            String loginName = UserSession.getLoginName();
            String loginCName = UserSession.getLoginCName();
            boolean flag = ACServiceUtils.checkWardenFsfy(loginName,deptNum);
            if(!flag){
                inInfo.setStatus(-1);
                inInfo.setMsg("非" + deptName + "管理员，无法调整排班信息");
                return inInfo;
            }
            String planId = StringUtils.toString(attr.get("planId"));
            String workNo = StringUtils.toString(attr.get("workNo"));
            String planStatus = StringUtils.toString(attr.get("planStatus"));
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("planId",planId);
            map.put("workNo",workNo);
            map.put("planStatus",planStatus);
            map.put("recRevisor",loginName);
            map.put("recRevisorName",loginCName);
            map.put("recReviseTime",CalendarUtils.dateTimeFormat(new Date()));
            inInfo = SqlUtils.updateSqlByMap("HRPBJhgl01.effect", map);
            inInfo.setMsg("调整成功！");
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("调整失败！");
        }
        return inInfo;
    }

    public EiInfo queryPlanWeek(EiInfo inInfo) {
        Map attr = inInfo.getAttr();
        String date = StringUtils.toString(attr.get("date"));
        LinkedHashMap linkmap = null;
        try {
            linkmap = WeekDateUtils.getWeekMap(date);
            inInfo.addBlock("planWeek").addRow(linkmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inInfo;
    }

    public EiInfo queryScheduleList(EiInfo inInfo) {
        Map attr = inInfo.getAttr();
        try {
            //SqlUtils.setDao(StringUtils.toString(attr.get("serviceName")));
            List<HashMap<String, Object>> listMap = SqlUtils.queryForListMap("HRPBSjpz01.query", attr);
            inInfo.addBlock("result").addRows(listMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inInfo;
    }

    public List<HRPBJhgl01> queryPbjhData(Map<String,Object> map){
        List<HRPBJhgl01> list = new ArrayList<HRPBJhgl01>();
        try {
            list = SqlUtils.querySqlByMap("HRPBJhgl01.query1",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取日期
     */
    public EiInfo getWeekDays(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            LinkedHashMap weekMap = WeekDateUtils.getWeekMap("2022-01-01");
            String blockId = inInfo.getString(EiConstant.queryBlock + EiConstant.separator + "0-node");
            // 返回的的 blockId 的名称是传入的结点其值
            // 如：inqu_status-0-node => "$", 则返回的的 block 名称是"$"
            EiBlock eiBlock = creatTreeNode(blockId, weekMap);
            outInfo.addBlock(eiBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outInfo;
    }

    /**
     * 创建时间节点树
     * @param blockId
     * @param map
     * @return
     */
    private static EiBlock creatTreeNode(String blockId, LinkedHashMap<String,List<String>> map) {
        EiBlock block = new EiBlock(blockId);
        block.addMeta(new EiColumn("label"));
        block.addMeta(new EiColumn("parent"));
        block.addMeta(new EiColumn("text"));
        block.addMeta(new EiColumn("hasChildren"));

        int start = map.size();
        Set<String> set = map.keySet();
        for (String key : set) {
            List<String> weekList = map.get(key);
            System.out.println(key + ":" + weekList);
            int index = Integer.parseInt(key);
            block.setCell(index, "value", "Week" + index);
            block.setCell(index, "pid", 0);
            block.setCell(index, "text", "第" + (index + 1) + "周");
            block.setCell(index, "isLeaf", 0);
            /*for (int i = 0; i < weekList.size(); i++) {
                String weekDay = weekList.get(i);
                block.setCell(i + start, "value", weekDay);
                block.setCell(i + start, "pid", "Week" + index);
                block.setCell(i + start, "text", weekDay);
                block.setCell(i + start, "isLeaf", 1);
            }*/
            start += weekList.size();
        }

        return block;
    }

}
