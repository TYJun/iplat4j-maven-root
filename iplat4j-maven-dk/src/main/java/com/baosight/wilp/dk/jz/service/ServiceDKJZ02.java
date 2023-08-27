package com.baosight.wilp.dk.jz.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.domain.DeviceBillType;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;
import com.baosight.wilp.dk.common.util.DeviceGeneCode;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 创建设备保养的定时任务:定时生成任务
 * <p>定时生成任务 createDeviceTask
 * 
 * @Title: ServiceDKJZ02.java
 * @ClassName: ServiceDKJZ02
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午6:03:48
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
public class ServiceDKJZ02 extends ServiceBase{
    
    
    

    private static Dao dao = (Dao)PlatApplicationContext.getBean("dao");
    private static int prepareCreateTime = 1;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    
//    /**
//     * 1测试方法注册服务名S_TA_SK_01
//     * */
//    public EiInfo test(EiInfo inInfo) {
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        System.out.println("测试定时器~~~~执行了~~~~淦");
//        return inInfo;
//    }
    
    
    
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
    
    @Scheduled(cron = "* */5 * * * ?")
    public void createDeviceTask() {
        System.out.println("开始生成保养任务------");
        //1.创建当天时间
        Date date = new Date();

        //2.获取提前生成的日期prepareTime
        Calendar calendar = Calendar.getInstance();
        //3.将当前时间赋值到日历
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, prepareCreateTime);
        //4.将当前时间转换成字符日期
        String prepareTime = sdf.format(calendar.getTime());

        //5.获取满足生成条件的 并且在执行状态的基准
        List<Map<String, String>> schemeList = dao.query("DKJZ01.queryNeedScheme", prepareTime);
        String createTime = sdf.format(date);

        //6.循环基准生成任务
        schemeList.forEach(e -> {
            //7.查询对应的基准详情
            String schemeID = e.get("schemeID");
            Map<String, String> param = new HashMap<>();
            param.put("schemeID", schemeID);
            //8.通过基准id获取对应的基准
            List<Map<String, String>> list =
                (List<Map<String, String>>)dao.query("DKJZ01.queryScheme", param);

            //9.如果查询不到对应的基准， 则直接跳过当前条
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            Map<String, String> map = list.get(0);
            //10.获取下次执行时间
            String executeTime=e.get("nextExecuteTime");
            //11.跳过双休日
            if("Y".equals(e.get("weekend"))&&(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)) {
               //12.给周期间隔赋值1天
                e.put("cycle", "1");
                e.put("unit", "d");
                updateSchemeCyc(e);
              return;
            }

            //13.校验节假日
            if("Y".equals(e.get("holiday"))) {
                //14.转换创建时间为yyyy-mm-dd格式
                String now = createTime.substring(0, 10);
                //15.获取创建时间的年份
                String year = createTime.substring(0, 4);
                //16.通过年份获取当年的节假日时间
                List<String> query = dao.query("DKJZ01.festival", year);
                //17.查询结果不为空
                if(!query.isEmpty()) {
                    String str = query.get(0); 
                    //18.将节假日期转换成json对象
                    JSONObject json = JSONObject.fromObject(str);  
                    //19.获取节假日准确的哪一天
                    List day = (List)json.get("list");
                    Integer param2 = null;
                    boolean effectiveDate = false;
                    //20.判断是否跳过节假日
                    for (Object obj : day) {
                        Map<String,String> p = (Map)obj;
                        String dat = p.get("date");
                        //21.截取日期
                        String[] split = dat.split("/");
                        String string1 = split[0];
                        String string2 = split[1];
                        //22.处理日期
                        String date1 = string1.substring(8,10);
                        String date2 = string2.substring(8,10);
                        //23.获取一个节假日的开始天数
                        Integer dat1 = Integer.valueOf(date1);
                        //24.获取一个节假日的结束天数
                        Integer dat2 = Integer.valueOf(date2);
                        param2 = dat2 - dat1;
                        try {
                            //25.将日期拼接字符串并转换成date格式
                            Date nowTime = sdf.parse(now + " 00:00:00");
                            //26.拼接开始时间完整格式
                            Date startTime = sdf.parse(string1 + " 00:00:00");
                            //27.拼接结束时间完整格式
                            Date endTime = sdf.parse(string2 + " 00:00:00");
                            effectiveDate = isEffectiveDate(nowTime,startTime,endTime);
                        } catch (ParseException e1) {
                             e1.printStackTrace();
                        }
                        //28.周期单位赋值
                        if(effectiveDate) {
                            //29.给周期赋值节假日之间差值
                            e.put("cycle", param2.toString());
                            e.put("unit", "d");
                            updateSchemeCyc(e);
                            return;
                        }
                    }
                }
            }
          //30.根据基准id获取任务
          List<String> taskList=dao.query("DKJZ01.getTaskById", param);
          //31.根据基准id获取已完成任务的完成时间
          List<String> taskFinishTimeList=dao.query("DKJZ01.getTaskFinishTimeById", param);
          //32.根据基准id获取是否还有没完成任务数
          int noNum=dao.count("DKJZ01.getNoTask", param);
          //33.如果集合为空，则代表一次任务都没生成过，直接生成任务
          if(taskList.isEmpty()) {
            //34.获取任务编码
            String taskCode = DeviceGeneCode.geneCode(DeviceBillType.DI_TASK);
            //35.uuid获取任务id
            String taskId = UUID.randomUUID().toString();
            //36.赋值任务编码
            map.put("taskCode", taskCode);
            //37.赋值任务id
            map.put("taskID", taskId);
            //38.赋值创建时间
            map.put("createTime", createTime);
            //39.赋值任务计划时间
            map.put("recCreateTime", executeTime);
            map.put("status", "1");
            //40.生成任务
            dao.insert("DKJZ01.insertTask", map);

            //41.生成项目
            list = (List<Map<String, String>>)dao.query("DKJZ01.queryItem", schemeID);
            //42.循环获取赋值
            list.forEach(t -> {
                //43.赋值任务id
                t.put("taskID", taskId);
                //44.赋值保养项目id为uuid
                t.put("itemID", UUID.randomUUID().toString());
                t.put("jitemName", t.get("itemName"));
                dao.insert("DKJZ01.addTaskItem", t);
            });
            //45.修改周期
            updateSchemeCyc(e);
           //46.如果基准id找到生成任务且任务已完成，则根据完成时间生成任务
          }else if(!taskFinishTimeList.isEmpty() && taskFinishTimeList.get(0)!=null) {
             if(noNum==0) {
            //47.获取提前生成的日期prepareTime
              Calendar calendar1 = Calendar.getInstance();
              Calendar calendar2 = Calendar.getInstance();
              Date finishTime=null;
              try {
                 finishTime=sdf.parse(taskFinishTimeList.get(0));
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                 e1.printStackTrace();
            }
              //48.本次完成时间和下次生成任务时间
              calendar1.setTime(finishTime);
              calendar2.setTime(finishTime);
              //49.根据周期判断添加任务生成时间周期
              if(e.get("unit").equals("h")) {
                  //50.按小时改变时间
                  calendar1.add(Calendar.HOUR, Integer.valueOf(e.get("cycle")));
                  calendar2.add(Calendar.HOUR, Integer.valueOf(e.get("cycle"))*2);
              }else if(e.get("unit").equals("d")){
                  //51.按天数改变时间
                  calendar1.add(Calendar.DAY_OF_YEAR, Integer.valueOf(e.get("cycle")));  
                  calendar2.add(Calendar.DAY_OF_YEAR, Integer.valueOf(e.get("cycle"))*2);  
              }else if(e.get("unit").equals("m")) {
                  //52.按月改变时间
                  calendar1.add(Calendar.MONTH, Integer.valueOf(e.get("cycle"))); 
                  calendar2.add(Calendar.MONTH, Integer.valueOf(e.get("cycle"))*2); 
              }
              //53.本次生成时间和下次生成任务时间
              String finishTime1 = sdf.format(calendar1.getTime());
              String finishTime2 = sdf.format(calendar2.getTime());
              //54.当前时间大于等于本次任务生成时间才能生成本次任务
              if(calendar1.getTime().getTime()<=date.getTime()) {
                 //55.获取任务编码
                  String taskCode = DeviceGeneCode.geneCode(DeviceBillType.DI_TASK);
                  //56.uuid获取任务id
                  String taskId = UUID.randomUUID().toString();
                  //57.赋值任务编码
                  map.put("taskCode", taskCode);
                  //58.赋值任务id
                  map.put("taskID", taskId);
                  //59.赋值创建时间为完成时间
                  map.put("createTime", finishTime1);
                  map.put("recCreateTime", finishTime1);
                  map.put("status", "1");
                  //60.生成任务
                  dao.insert("DKJZ01.insertTask", map);

                  //61.生成项目
                  list = (List<Map<String, String>>)dao.query("DKJZ01.queryItem", schemeID);
                  //62.循环赋值
                  list.forEach(t -> {
                      //63.赋值任务id
                      t.put("taskID", taskId);
                      //64.赋值保养项目id为uuid
                      t.put("itemID", UUID.randomUUID().toString());
                      //65.赋值保养项目名称
                      t.put("jitemName", t.get("itemName"));
                      //66.新增保养任务项目
                      dao.insert("DKJZ01.addTaskItem", t);
                  });
                  //67.修改周期
                  e.put("nextExecuteTime", finishTime1);
                  updateSchemeCyc(e);
              }
          }
         }
        });
    }
    
//    public EiInfo createDeviceTaskDK(EiInfo inInfo) {
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
//        List<Map<String, String>> schemeList = dao.query("DKJZ01.queryNeedScheme", prepareTime);
//        String createTime = sdf.format(date);
//
//        //循环基准生成任务
//        schemeList.forEach(e -> {
//            //查询对应的基准详情
//            String schemeID = e.get("schemeID");
//            Map<String, String> param = new HashMap<>();
//            param.put("schemeID", schemeID);
//            List<Map<String, String>> list =
//                (List<Map<String, String>>)dao.query("DKJZ01.queryScheme", param);
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
//                List<String> query = dao.query("DKJZ01.festival", year);
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
//            dao.insert("DKJZ01.insertTask", map);
//
//            //生成项目
//            list = (List<Map<String, String>>)dao.query("DKJZ01.queryItem", schemeID);
//            list.forEach(t -> {
//                t.put("taskID", taskId);
//                t.put("itemID", UUID.randomUUID().toString());
//                t.put("jitemName", t.get("itemName"));
//                dao.insert("DKJZ01.addTaskItem", t);
//            });
//            updateSchemeCyc(e);
//        });
//		return inInfo;
//    }

    /**
     * 更新基准中的下次执行时间
     * <p>获取周期时间参数
     * <p>周期时间参数转换成时间
     * @param map
     * unit   单位
     * cycle  间隔
     * nextExecuteTime   下次执行时间
     */
    private void updateSchemeCyc(Map<String, String> map) {
        //1.获取周期时间参数
        String unit = map.get("unit");
        String cycle = map.get("cycle");
        String startTime = map.get("nextExecuteTime");
        //2.周期时间参数转换成时间
        String nowTime = DeviceEiUtil.getNextCreateTime(cycle, unit, startTime);
        map.put("nowTime", nowTime);
        //3.修改时间周期
        dao.update("DKJZ01.updateDeviceCyc", map);
    }

    
    /**
     * 
     * 时间范围校验
     * <p>当前时间和开始时间比较
     * <p>获取日历时间
     * <p>获取开始时间
     * <p>获取结束时间
     *
     * @Title: time 
     * @param statTime
     * @param nowTime
     * @param endTime
     * @return 
     * @return: Boolean
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        //1.当前时间和开始时间比较
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        //2.获取日历时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //3.获取开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);
        //4.获取结束时间
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        //5.判断时间在开始时间后结束时间中间
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
