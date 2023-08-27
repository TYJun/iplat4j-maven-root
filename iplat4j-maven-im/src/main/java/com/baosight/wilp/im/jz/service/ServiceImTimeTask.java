package com.baosight.wilp.im.jz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.im.common.domain.DeviceBillType;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.wilp.im.common.util.DeviceGeneCode;

import net.sf.json.JSONObject;

/**
 * 巡检基准定时生成任务：定时生成任务
 * <p>定时生成任务 createDeviceTask
 * 
 * @Title: ServiceTimeTask.java
 * @ClassName: ServiceTimeTask
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午7:45:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
@Service
public class ServiceImTimeTask {

    private static Dao dao = (Dao)PlatApplicationContext.getBean("dao");
    private static int prepareCreateTime = 1;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 定时生成任务
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>获取提前生成的日期prepareTime
     * <p>获取满足生成条件的 并且在执行状态的基准
     * <p>循环基准生成任务
     * <p>查询对应的基准详情
     * <p>如果查询不到对应的基准， 则直接跳过当前条
     * <p>跳过双休日
     * <p>校验节假日
     * <p>生成任务
     * <p>循环生成项目  
     * <p>更新基准中的下次执行时间
     * @param inInfo
     * @return
     * 生成任务成功，生成失败则执行回滚操作
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    //@Scheduled(cron = "* */5 * * * ?")
    public void createImTask() {
        System.out.println("开始生成任务");

        Date date = new Date();

        //获取提前生成的日期prepareTime
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, prepareCreateTime);
        String prepareTime = sdf.format(calendar.getTime());

        //获取满足生成条件的 并且在执行状态的基准
        List<Map<String, String>> schemeList = dao.query("IMJZ01.queryNeedScheme", prepareTime);
        String createTime = sdf.format(date);

        //循环基准生成任务
        schemeList.forEach(e -> {
            //查询对应的基准详情
            String schemeID = e.get("schemeID");
            Map<String, String> param = new HashMap<>();
            param.put("schemeID", schemeID);
            List<Map<String, String>> list =
                (List<Map<String, String>>)dao.query("IMJZ01.queryScheme", param);
            
            //改造获取完成人员名称 -- 2021-08-04
            /* if(list.size()>0) {
                for (Map<String, String> listMap : list) {
                    String createMan = listMap.get("createMan");
                    Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(createMan);
                    listMap.put("createManName", (String)staffByUserId.get("name"));
                    List<Map<String, Object>> spotByDeptNum = BaseDockingUtils.getSpotByDeptNum(listMap.get("departID"));
                    if(spotByDeptNum.size()>0) {
                        Map<String, Object> map = spotByDeptNum.get(0);
                        listMap.put("spotName", (String)map.get("spotName"));
                    }
                }
            }*/
            

            //如果查询不到对应的基准， 则直接跳过当前条
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            Map<String, String> map = list.get(0);
            String executeTime=e.get("nextExecuteTime");
            //跳过双休日
            if("Y".equals(e.get("weekend"))&&(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)) {
               // 更新基准中的下次执行时间
                updateSchemeCyc(e);
              return;
            }

            //校验节假日
            if("Y".equals(e.get("holiday"))) {
                String now = createTime.substring(0, 10);
                String year = createTime.substring(0, 4);
                List<String> query = dao.query("IMJZ01.festival", year);
                if(!query.isEmpty()) {
                    String str = query.get(0);  
                    JSONObject json = JSONObject.fromObject(str);  
                    List day = (List)json.get("list");
                    Integer param2 = null;
                    boolean effectiveDate = false;
                    //判断是否跳过节假日
                    for (Object obj : day) {
                        Map<String,String> p = (Map)obj;
                        String dat = p.get("date");
                        String[] split = dat.split("/");
                        String string1 = split[0];
                        String string2 = split[1];
                        String date1 = string1.substring(8,10);
                        String date2 = string2.substring(8,10);
                        Integer dat1 = Integer.valueOf(date1);
                        Integer dat2 = Integer.valueOf(date2);
                        param2 = dat2 - dat1;
                        try {
                            
                            Date nowTime = sdf.parse(now + " 00:00:00");
                            Date startTime = sdf.parse(string1 + " 00:00:00");
                            Date endTime = sdf.parse(string2 + " 00:00:00");
                            effectiveDate = isEffectiveDate(nowTime,startTime,endTime);
                        } catch (ParseException e1) {
                             e1.printStackTrace();
                        }
                        if(effectiveDate) {
                            e.put("cycle", param2.toString());
                            e.put("unit", "d");
                         // 更新基准中的下次执行时间
                            updateSchemeCyc(e);
                            return;
                        }
                        
                    }
                }

            }

            String taskCode = DeviceGeneCode.geneCode(DeviceBillType.IM_TASK);
            String taskId = UUID.randomUUID().toString();
            map.put("taskCode", taskCode);
            map.put("taskID", taskId);
            map.put("createTime", executeTime);
            map.put("recCreateTime", createTime);
            map.put("status", "1");
            //生成任务
            dao.insert("IMJZ01.insertTask", map);

            //循环生成项目
            list = (List<Map<String, String>>)dao.query("IMJZ01.queryItem", schemeID);
            list.forEach(t -> {
                t.put("taskID", taskId);
                t.put("itemID", UUID.randomUUID().toString());
                t.put("jitemName", t.get("itemName"));
                dao.insert("IMJZ01.addTaskItem", t);
            });
            //更新基准中的下次执行时间
            updateSchemeCyc(e);
        });
    }
    
//    public EiInfo createImTask(EiInfo inInfo) {
//        System.out.println("开始生成任务");
//
//        Date date = new Date();
//
//        //获取提前生成的日期prepareTime
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.HOUR, prepareCreateTime);
//        String prepareTime = sdf.format(calendar.getTime());
//
//        //获取满足生成条件的 并且在执行状态的基准
//        List<Map<String, String>> schemeList = dao.query("DIJZ01.queryNeedScheme", prepareTime);
//        String createTime = sdf.format(date);
//
//        //循环基准生成任务
//        schemeList.forEach(e -> {
//            //查询对应的基准详情
//            String schemeID = e.get("schemeID");
//            Map<String, String> param = new HashMap<>();
//            param.put("schemeID", schemeID);
//            List<Map<String, String>> list =
//                (List<Map<String, String>>)dao.query("DIJZ01.queryScheme", param);
//            
//            //改造获取完成人员名称 -- 2021-08-04
//            /* if(list.size()>0) {
//                for (Map<String, String> listMap : list) {
//                    String createMan = listMap.get("createMan");
//                    Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(createMan);
//                    listMap.put("createManName", (String)staffByUserId.get("name"));
//                    List<Map<String, Object>> spotByDeptNum = BaseDockingUtils.getSpotByDeptNum(listMap.get("departID"));
//                    if(spotByDeptNum.size()>0) {
//                        Map<String, Object> map = spotByDeptNum.get(0);
//                        listMap.put("spotName", (String)map.get("spotName"));
//                    }
//                }
//            }*/
//
//            //如果查询不到对应的基准， 则直接跳过当前条
//            if (CollectionUtils.isEmpty(list)) {
//                return;
//            }
//
//            Map<String, String> map = list.get(0);
//            String executeTime=e.get("nextExecuteTime");
//            //跳过双休日
//            if("Y".equals(e.get("weekend"))&&(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)) {
//              updateSchemeCyc(e);
//              return;
//            }
//
//            //校验节假日
//            if("Y".equals(e.get("holiday"))) {
//                String now = createTime.substring(0, 10);
//                String year = createTime.substring(0, 4);
//                List<String> query = dao.query("DIJZ01.festival", year);
//                if(!query.isEmpty()) {
//                    String str = query.get(0);  
//                    JSONObject json = JSONObject.fromObject(str);  
//                    List day = (List)json.get("list");
//                    Integer param2 = null;
//                    boolean effectiveDate = false;
//                    //判断是否跳过节假日
//                    for (Object obj : day) {
//                        Map<String,String> p = (Map)obj;
//                        String dat = p.get("date");
//                        String[] split = dat.split("/");
//                        String string1 = split[0];
//                        String string2 = split[1];
//                        String date1 = string1.substring(8,10);
//                        String date2 = string2.substring(8,10);
//                        Integer dat1 = Integer.valueOf(date1);
//                        Integer dat2 = Integer.valueOf(date2);
//                        param2 = dat2 - dat1;
//                        try {
//                            
//                            Date nowTime = sdf.parse(now + " 00:00:00");
//                            Date startTime = sdf.parse(string1 + " 00:00:00");
//                            Date endTime = sdf.parse(string2 + " 00:00:00");
//                            effectiveDate = isEffectiveDate(nowTime,startTime,endTime);
//                        } catch (ParseException e1) {
//                             e1.printStackTrace();
//                        }
//                        if(effectiveDate) {
//                            e.put("cycle", param2.toString());
//                            e.put("unit", "d");
//                            updateSchemeCyc(e);
//                            return;
//                        }
//                        
//                    }
//                }
//            }
//
//            String taskCode = DeviceGeneCode.geneCode(DeviceBillType.DI_TASK);
//            String taskId = UUID.randomUUID().toString();
//            map.put("taskCode", taskCode);
//            map.put("taskID", taskId);
//            map.put("createTime", executeTime);
//            map.put("recCreateTime", createTime);
//            map.put("status", "1");
//            //生成任务
//            dao.insert("DIJZ01.insertTask", map);
//
//            //生成项目
//            list = (List<Map<String, String>>)dao.query("DIJZ01.queryItem", schemeID);
//            list.forEach(t -> {
//                t.put("taskID", taskId);
//                t.put("itemID", UUID.randomUUID().toString());
//                t.put("jitemName", t.get("itemName"));
//                dao.insert("DIJZ01.addTaskItem", t);
//            });
//            updateSchemeCyc(e);
//        });
//		return inInfo;
//    }

    /**
     * 更新基准中的下次执行时间
     * @param map
     * unit   单位
     * cycle  间隔
     * nextExecuteTime   下次执行时间
     */
    @Async
    private void updateSchemeCyc(Map<String, String> map) {
        String unit = map.get("unit");
        String cycle = map.get("cycle");
        String startTime = map.get("nextExecuteTime");
        String nowTime = DeviceEiUtil.getNextCreateTime(cycle, unit, startTime);
        map.put("nowTime", nowTime);
        dao.update("IMJZ01.updateDeviceCyc", map);
    }

    
    /**
     * 
     * 时间范围校验
     *
     * @Title: time 
     * @param statTime  开始时间
     * @param nowTime   当前时间
     * @param endTime   结束时间
     * @return 
     * @return: Boolean
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * 
     * 巡检综合信息定时改变超时状态
     *
     * @Title: time 
     */
    
    //@Scheduled(cron = "* */5 * * * ?")
    public void changeImStaus() {
    	
    	dao.update("IMZH01.changeStatus",null);
    }
    
}
