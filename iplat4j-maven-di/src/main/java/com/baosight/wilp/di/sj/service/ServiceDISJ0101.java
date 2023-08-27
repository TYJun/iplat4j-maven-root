 package com.baosight.wilp.di.sj.service;

 import com.alibaba.fastjson.JSONObject;
 import com.baosight.iplat4j.core.ei.EiInfo;
 import com.baosight.iplat4j.core.log.Logger;
 import com.baosight.iplat4j.core.log.LoggerFactory;
 import com.baosight.iplat4j.core.service.impl.ServiceBase;
 import com.baosight.iplat4j.core.web.threadlocal.UserSession;
 import com.baosight.wilp.common.util.BaseDockingUtils;

 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

/**
 * 巡检实绩维护和完工类：巡检实绩维护、更新巡检项目、完工操作
 * <p>1.巡检实绩维护      initLoad
 * <p>2.更新巡检项目      updateItem
 * <p>3.完工操作              updateTaskStatus
 * 
 * @Title: ServiceDISJ0101.java
 * @ClassName: ServiceDISJ0101
 * @Package：com.baosight.wilp.di.sj.service
 * @author: LENOVO
 * @date: 2021年9月16日 下午4:10:28
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDISJ0101 extends ServiceBase{

    protected static final Logger logger = LoggerFactory.getLogger(ServiceDISJ0101.class);
    /**
     * 巡检实绩维护
     * <p>2.查询基准信息
     * <p>3.查询项目列表信息
     * <p>4.查询执行人员列表信息
     * <p>5.将基准信息和任务id添加到EiiNFO并返回客户端
     * <p>6.将返回的巡检项目列表和执行人列表添加到EiiNFO并返回客户端
     * <p>7.未查询到基准信息，给前端返回错误信息
     * @param
     * * taskID         任务id
     * schemeID       基准id
     * @return
     * taskID        任务id
       schemeID      基准id
       taskCode      任务单号
       schemeName    任务(基准)名称
       machineName   设备
       name          责任人
       finishTime    完成时间
       finishName    完工操作人
       textarea      巡检说明
       id            id
       itemID        作业id
       code          作业编码
       jitemName     巡检作业项目
       itemDesc      巡检作业说明
       referenceValue  参考值
       successFlag   巡检结果
       writeValue    结果记录
       actualValueUnit   巡检实际值单位
       errorContent      异常故障描述
       exception_status  异常状态
       exception_result  异常处理结果
     */
     @Override
    public EiInfo initLoad(EiInfo inInfo) {
         String taskID = (String) inInfo.getAttr().get("taskID");
         String schemeID = (String) inInfo.getAttr().get("schemeID");
         Map<String, String> param = new HashMap<>();
         param.put("taskID", taskID);
         //2.查询基准信息
         List<Map<String, Object>> schemeInfo = dao.query("DISJ01.queryScheme", param);
         
         //3.查询项目列表信息
         List<Map<String, Object>> item = dao.query("DISJ01.queryItem", taskID);
         
         //4.查询执行人员列表信息
         List<Map<String, Object>> exman = dao.query("DISJ01.queryExman", schemeID);
         item.forEach(m -> {
             m.put("id", m.get("jobID"));
         });
         
         //5.将基准信息和任务id添加到EiiNFO并返回客户端
         EiInfo outInfo = new EiInfo();
         if(schemeInfo.size()>0) {
             Map<String, Object> map = schemeInfo.get(0);
             map.put("schemeMan", map.get("name"));
             map.put("schemeDept", map.get("deptName"));
             map.put("taskID", taskID);
             logger.info("基准对象map：", JSONObject.toJSONString(map));
             outInfo.setAttr(map);
             
             //6.将返回的巡检项目列表和执行人列表添加到EiiNFO并返回客户端
             outInfo.addRows("item", item);
             outInfo.addRows("exman", exman);
             logger.info("巡检实绩维护响应：{}", JSONObject.toJSONString(outInfo));
             return outInfo;
         }else {
             
             //7.未查询到基准信息，给前端返回错误信息
             outInfo.setStatus(-1);
             outInfo.setMsg("未找到该任务的基准信息");
             return outInfo;
         }
         
     }
     
     
     /**
      * 更新巡检项目
      * <p>1.获取操作时间
      * <p>2.从前端取出巡检项目列表
      * <p>3.将巡检项目列表中的修改状态（successFlag）取出并保存至di_taskitem表中
      * <p>4.执行成功后，新建EiiNFO返回客户端成功消息
      * @param
      * * itemList         项目list
      * writeValue       保养结果
      * @return
      * 项目保存成功
      * 
      */
     public EiInfo updateItem(EiInfo eiInfo) {
         //1.获取操作时间
         String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
         
         //2.从前端取出巡检项目列表
         List<Map<String, Object>> itemList = (List<Map<String, Object>>) eiInfo.get("itemList");
         
         //3.将巡检项目列表中的修改状态（successFlag）取出并保存至di_taskitem表中
         itemList.forEach(item -> {
             Map<String, Object> updateItem = new HashMap<>();
             String writeValue = (String)item.get("writeValue");
             updateItem.put("itemID", item.get("itemID"));
             updateItem.put("writeValue", writeValue);
             logger.info("巡检实绩维护【DISJ0101】---获取到巡检项目结果状态：{}", writeValue);
             dao.update("DISJ01.updateItemStatus", updateItem);
         });
         
         //4.执行成功后，新建EiiNFO返回客户端成功消息
         EiInfo outInfo = new EiInfo();
         outInfo.setMsg("项目保存成功");
         return outInfo;
     }
     
     /**
      *  完工操作
      *  <p>1.获取任务id(taskID),完工人和完工时间
      *  <p>2.将任务id(taskID),完工人和完工时间添加到map中作为update条件
      *  <p>3.更新任务状态为完工（2），记录完工操作人和完工时间
              0=未生效，1=生效，-1=失效，2=完成，3=关闭
        @param
      *  * taskID         任务id
      * @return
      * 完工操作成功
      */
     public EiInfo updateTaskStatus(EiInfo eiInfo) {
         
         //1.获取任务id(taskID),完工人和完工时间
         String taskID = (String) eiInfo.getAttr().get("taskID");
         Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUserId());
         String finishMan = (String)staffByUserId.get("name");
         String finishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
         
         //2.将任务id(taskID),完工人和完工时间添加到map中作为update条件
         Map<String, Object> updateItem = new HashMap<>();
         updateItem.put("taskID", taskID);
         updateItem.put("finishManName", finishMan);
         updateItem.put("finishTime", finishTime);
         updateItem.put("status", 2);
         logger.info("巡检完工操作【DISJ0101】---任务id【{}】已完工", taskID);
         
         //3.更新任务状态为完工（2），记录完工操作人和完工时间
         //0=未生效，1=生效，-1=失效，2=完成，3=关闭
         dao.update("DISJ01.updateTaskStatus", updateItem);
         EiInfo outInfo = new EiInfo();
         outInfo.setMsg("完工操作成功");
         return outInfo;
     }
}
