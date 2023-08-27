package com.baosight.wilp.dk.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.pr.UUIDUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;
import com.baosight.wilp.dk.common.util.DevicePicUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 保养app接口：App端巡检异常报表、App端巡检工作量统计报表、离线下载巡检任务、获取异常任务、设备巡检历史任务、
 *             获取任务详情、设备档案查询、查询巡检任务、巡检任务完工、异常任务处理、维护详情新增图片功能
 * <p>App端巡检异常报表  getDevExceptionTaskApp
 * <p>App端巡检工作量统计报表  getDeviceWorkload
 * <p>离线下载巡检任务  getDeviceOfflineTask
 * <p>获取异常任务  getDevExceptionTask
 * <p>设备巡检历史任务  getDevTaskHistory
 * <p>获取任务详情  deviceTaskDetail
 * <p>设备档案查询  getDeviceDetail
 * <p>查询巡检任务  queryTask
 * <p>巡检任务完工  commitXJ
 * <p>异常任务处理  handDevExceptionTask
 * <p>维护详情新增图片功能  getPic
 * 
 * @Title: ServiceDKJZApp.java
 * @ClassName: ServiceDKJZApp
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午6:51:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKJZApp extends ServiceBase {

    /**
     * 图片保存路径
     */
    public static final String PIC_PATH = "upload/deviceDK";



    /**
     * App端保养异常报表
     * @Title: getDevExceptionTaskApp
     * @Description:
     * <p>校验用户id是否为空
     * <p>判断总数是否小于startRow
     * @param:  @param inInfo
     * userID  用户id
     * page    分页
     * exceptionStatus  异常状态
     * @return
     * taskID    任务id
     * itemID    任务作业id     
       jitemName 作业名称     
       machine_name   设备名称
       taskCode       任务编码
       schemeName     基准名称
       writeValue     巡检录入值
       errorContent   异常故障描述
       exception_status 异常状态,0--待处理,1--已处理
     * @return: EiInfo  
     * @throws
     */
	public EiInfo getDevExceptionTaskAppDK(EiInfo info) {
	    //1.后去pmap和map对象
		Map<String, Object> pMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String userID =info.getString("userID");
		//2.获取当前页和行数
		int page =Integer.parseInt(info.getString("page"));
		int rows = 10;
		int startRow = (page - 1) * rows;
		//3.获取异常状态
		String exceptionStatus  =info.getString("exceptionStatus");
		//4.校验用户id是否为空
		if (StringUtils.isBlank(userID)) {
			info.setMsgKey("199");
			info.setMsg("userID参数为空");
		} else {
		    //5.不为空则赋值用户id
			map.put("userID", userID);
			//6.将数据rows赋值到map里
			map.put("rows", rows);
			//7.将开始行数据赋值到map里
			map.put("startRow", startRow);
			map.put("exceptionStatus", exceptionStatus);
			try {
//				int count= dao.count("DKJZ01.getDevExceptionTaskAppCount", map);
//				//8.判断总数是否小于startRow
//				if(count<=startRow) {
//					startRow=count-rows;
//					map.put("startRow", startRow);
//				}
				//9.获取异常任务
				List list = dao.query("DKJZ01.getDevExceptionTaskApp", map);
				//10.将获取数据状态赋值到info里
				info.setMsgKey("200");
				//11.将获取数据信息赋值到info里
				info.setMsg("获取成功");
				pMap.put("obj", list);
			} catch (Exception e) {
			    //12.获取失败
				e.printStackTrace();
				info.setMsgKey("201");
				info.setMsg("获取失败");
			}
		}
			info.addRow("DevException", pMap);
			return info;
	}
	
	/**
     * App端保养报表
     * @Title: getDeviceWorkload
     * 班组保养/保养工作量统计
     * <p>校验时间不为空
     * <p>循环获取工作量总和
     * @param *request
     * beginDate  开始时间
     * endDate    结束时间
     * @param *response
     * @return
     * total_jgnum    总数
     * total_wgnum    完工数
     * total_csnum    失效数
     * total_zxnum    生效数
     * total_cswgnum  关闭数
     */
	public EiInfo getDeviceWorkloadDK(EiInfo info) {
		
		 //1.获取开始时间
		 String beginDate = info.getString("beginDate"); 
		 //2.获取结束时间
		 String endDate =info.getString("endDate") ;
		  //3.校验开始时间不为空
		 if(StringUtils.isBlank(beginDate)) { 
			 
			 info.setMsgKey("195");
			 info.setMsg("beginDate参数为空");
		     return info;
		    }
		 //4.校验结束时间不为空
		 if(StringUtils.isBlank(endDate)) { 
			 info.setMsgKey("196");
			 info.setMsg("endDate参数为空");
		     return info;
		     }
		try {
		    //5.开始时间和结束时间赋值
			Map<String, Object> map = new HashMap<String, Object>();
			//6.给开始时间赋值
			map.put("startTimeS", beginDate);
			//7.给结束时间赋值
			map.put("startTimeE", endDate);
			//8.获取巡检工作统计
			List<Map<String, Object>> query = dao.query("DKJZ01.getDeviceWorkloadApp", map);
			int total_jgnum = 0;
			//9.初始化完工量
			int total_wgnum = 0;
			int total_csnum = 0;
			int total_zxnum = 0;
			int total_cswgnum = 0;
			//10.循环获取工作量总和
			for (Map<String, Object> m : query) {
				BigDecimal jgnum = (BigDecimal) m.get("jgnum");
				//11.获取完工量
				BigDecimal wgnum = (BigDecimal) m.get("wgnum");
				BigDecimal csnum = (BigDecimal) m.get("csnum");
				BigDecimal zxnum = (BigDecimal) m.get("zxnum");
				BigDecimal cswgnum = (BigDecimal) m.get("cswgnum");
				//12.统计总数
				total_jgnum += jgnum.intValue();
				//13.完工量求和
				total_wgnum += wgnum.intValue();
				total_csnum += csnum.intValue();
				total_zxnum += zxnum.intValue();
				total_cswgnum += cswgnum.intValue();
			}
			//14.将获取的总数赋值map
			Map<String,Object> maps = new HashMap<String,Object>();
			maps.put("total_jgnum", total_jgnum);
			//15.将完工量和赋值
			maps.put("total_wgnum", total_wgnum);
			maps.put("total_csnum", total_csnum);
			maps.put("total_zxnum", total_zxnum);
			maps.put("total_cswgnum", total_cswgnum);
			maps.put("data", query);
			info.addRow("work", maps);
		} catch (Exception e) {
			e.printStackTrace();
			//16.查询状态和信息赋值
			info.setMsgKey("201");
			 info.setMsg("查询失败!");
		     return info;
		}
		return info;
	}

	/**
     * 离线下载巡检任务
     * @Title: getDeviceOfflineTask
     * @Description:
     * <p>参数处理
     * <p>获取当前用户角色，判断是否为巡检领导
     * <p>获取当前登录用户信息
     * <p>查询
     * @param:  @param inInfo
     * userID  用户id
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo getDeviceOfflineTaskDK (EiInfo inInfo){
        String userID = inInfo.getString("userID");
        //1.参数处理
        if(StringUtils.isBlank(inInfo.getString("userID"))){
            inInfo.setStatus(-1);
            //2.给userid赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        //3.参数保存inInfo
        inInfo.set("isAdvance", 0);
        inInfo.set("taskStatus", "1");
        //4.赋值领导为N
        inInfo.set("isLeader", "N");
        //5.赋值分页为N
        inInfo.set("isPage", "N");
        //6.获取当前用户角色，判断是否为巡检领导
        //7.获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(userID);
        //8.获取用户名
        String name = (String)staffByUserId.get("name");
        //9.获取用户权限
        String userRole = getUserRole(userID);
        //10.判断权限不为空
        if(userRole != null && userRole.contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //11.查询
        return queryTaskDK(inInfo);
    }

    /**
     * 获取异常任务
     * @Title: getDevExceptionTask
     * @Description:
     * <p>参数处理
     * <p>获取当前用户角色，判断是否为巡检领导
     * <p>分页
     * <p>查询
     * @param:  @param inInfo
     * userID  用户id
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo getDevExceptionTaskDK(EiInfo inInfo) {
        //1.参数处理
        if(StringUtils.isBlank(inInfo.getString("userID"))){
            inInfo.setStatus(-1);
            //2.给用户id赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        //3.赋值是否是领导为N
        inInfo.set("isLeader", "N");
        //4.赋值状态为异常
        inInfo.set("successFlag", "-1");
        //5.获取当前用户角色，判断是否为巡检领导
        Map<String, Object> userInfo = DeviceEiUtil.getUserInfo(inInfo.getString("userID"));
        if(userInfo != null && userInfo.get("role").toString().contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //6.分页
        String page = inInfo.getString("page");
        if(StringUtils.isNotBlank(page)){
            int offset = (Integer.parseInt(page)-1) * 10;
            inInfo.set("offset", offset);
            inInfo.set("limit", 10);
        }
        //7.查询
        return queryTaskDK(inInfo);
    }

    /**
     * 设备巡检历史任务
     * @Title: getDevTaskHistory
     * @Description: 
     * <p>参数处理
     * <p>获取当前用户角色，判断是否为巡检领导
     * <p>分页
     * <p>查询
     * @param:  @param inInfo
     * userID  用户id
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo getDevTaskHistoryDK(EiInfo inInfo) {
        //1.参数处理
        if(StringUtils.isBlank(inInfo.getString("userID"))){
            inInfo.setStatus(-1);
            //2.给用户id为空赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        //3.给领导角色赋值为N
        inInfo.set("isLeader", "N");
        //4.获取当前用户角色，判断是否为巡检领导
        Map<String, Object> userInfo = DeviceEiUtil.getUserInfo(inInfo.getString("userID"));
        if(userInfo != null && userInfo.get("role").toString().contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //5.分页
        String page = inInfo.getString("page");
        //6.如果分页不为空，则直接赋值
        if(StringUtils.isNotBlank(page)){
            int offset = (Integer.parseInt(page)-1) * 10;
            inInfo.set("offset", offset);
            inInfo.set("limit", 10);
        }
        //7.查询任务
        return queryTaskDK(inInfo);
    }

    /**
     * 获取任务详情
     * @Title: deviceTaskDetail
     * @Description:
     * <p>参数处理
     * <p>查询
     * @param:  @param inInfo
     * taskID  任务id
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo deviceTaskDetailDK(EiInfo inInfo) {
        //1.参数处理
        if(StringUtils.isBlank(inInfo.getString("taskID"))){
            inInfo.setStatus(-1);
            //2.给用户id为空赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少taskID参数");
            return inInfo;
        }
        //3.是否为领导，设置为否
        inInfo.set("isLeader", "Y");
        //4.是否有分页，设置为否
        inInfo.set("isPage", "N");
        //5.查询任务
        return queryTaskDK(inInfo);
    }

    /**
     * 设备档案查询
     * @Title: getDeviceDetail
     * @Description:
     * <p>参数处理
     * @param:  @param inInfo
     * id        设备id
     * @param:  @return
     * @return: EiInfo  
     * machine_code   设备编号
     * machine_name   设备名称
     * machine_type_id  设备分类id
     * models           规格型号
     * fixed_place      设备地址
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo getDeviceDetailDK(EiInfo inInfo) {
        //1.参数处理
        if(StringUtils.isBlank(inInfo.getString("id"))){
            inInfo.setStatus(-1);
            //2.给用户id为空赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少设备id参数");
            return inInfo;
        }
        //3.获取设备信息
        List<Map<String, Object>> list = dao.query("DKJZ01.getMachineInfo", inInfo.getString("id"));
        //4.将设备信息分装到info返回页面
        inInfo.set("device", list == null ? new HashMap<>() : list.get(0));
        return inInfo;
    }

    /**
     * 查询巡检任务
     * @Title: queryTask
     * @Description:
     * <p>是否分页
     * <p>改造获取完成人员名称 
     * <p>循环将任务项目数据处理出来
     * @param:  @param inInfo
     * isAdvance   是否可以提前看到任务，默认不可以
     * taskID     任务id
     * taskCode   任务编号
     * taskStatus 任务状态
     * isLeader   是否领导
     * userID     用户id
     * successFlag 成功标记
     * exceptionStatus  异常状态
     * fixedCode    地址编号
     * beginTime    开始时间
     * endTime      结束时间
     * offset       分页偏移量
     * limit        每页数量
     * @return: EiInfo  
     * schemeCode    基准编码     
       schemeName    基准名称（任务名称
       activeTime    任务有效时间  
       jobContent    作业说明     
       manageDeptNum    责任科室编码 
       manageDeptNum    责任科室名称    
       managerCode      负责人工号    
       managerName      负责人名称
       machineId        设备Id  
       machineCode      设备编码     
       machineName      设备名称    
       fixedPlaceId     设备安装地点
       fixedPlaceCode   设备安装地点
       fixedPlaceName   设备安装地点   
       needScan         是否需要扫描二维码 
       taskID           任务ID     
       taskCode         任务编码      
       taskStatusCode   任务状态编码                                       
       taskStatusName   任务状态名称    
       createTime       创建时间
       activeTime       截至时间
       finishTime       完工时间
       finishManCode    完工人工号     
       finishManName    完工人名称       
       exceptionFlag    任务结果状态编码                                      
       exceptionFlagName       任务结果状态名称  
       itemID                  巡检项目ID   
       jitemName                         作业项目（巡检项目名称） 
       itemDesc                          作业说明（巡检项目说明）
       referenceValue                    参考值     
       writeValue                        巡检录入值（巡检实际值） 
       actualValueUnit                   巡检实际值单位    
       errorContent                      异常故障描述     
       successFlag                       巡检项结果状态编码  
       successFlagName                   巡检项结果状态名称
       exception_status exceptionStatus  异常处理状态     
       exception_result exceptionResult  异常处理结果     
       needPhoto                         巡检项是否需要拍照
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryTaskDK(EiInfo inInfo) {
        //1.将参数名保存数组
        String[] param = {"isAdvance", "taskID", "taskCode", "taskStatus", "isLeader","userID",
            "successFlag","exceptionStatus","fixedCode","beginTime","endTime","offset","limit"};
        String blockId = "result";
        if(StringUtils.isNoneBlank(inInfo.getString("page"))){
            blockId = null;
        }
        //2.将数组参数分装到map里
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), blockId);
        int count = 0;
        //3.是否有分页
        if("N".equals(inInfo.getString("isPage"))){
            map.put("offset",null);
        } else {
            count = dao.count("DKJZ01.queryTaskCount", map);
        }
        //4.获取所有任务和总数
        List<Map<String, Object>> list = dao.query("DKJZ01.queryTask", map);
        //5.将获取到的任务筛选
        List<Map<String, Object>> rList =  dealResultDK(list);
        //6.判断是否要分页，为N则不要分页获取所有list的数量
        count = "N".equals(inInfo.getString("isPage")) ? list == null ? 0 : list.size() : count;
        return DeviceEiUtil.buildResult(inInfo, rList, count, "result");
    }

    /**
     * 数据处理
     * <p>循环将任务项目数据处理出来
     *
     * @Title: dealResult 
     * @param list
     * @return 
     * itemID                  巡检项目ID   
       jitemName                         作业项目（巡检项目名称） 
       itemDesc                          作业说明（巡检项目说明）
       referenceValue                    参考值     
       writeValue                        巡检录入值（巡检实际值） 
       actualValueUnit                   巡检实际值单位    
       errorContent                      异常故障描述     
       successFlag                       巡检项结果状态编码  
       successFlagName                   巡检项结果状态名称
       exception_status exceptionStatus  异常处理状态     
       exception_result exceptionResult  异常处理结果     
       needPhoto                         巡检项是否需要拍照
     * @return: List<Map<String,Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> dealResultDK(List<Map<String, Object>> list) {
        //1.新建一个result集合空对象
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        Map<String, Object> temp = new HashMap<String, Object>();
        //2.循环将任务项目数据处理出来
        list.forEach(map -> {
            Map<String, Object> taskMap = null;
            List<Map<String, Object>> plist = null;
            //3.是否包含任务id，包含则赋值，不包含则为空
            if(temp.containsKey(map.get("taskID"))){
                taskMap = (Map<String, Object>) temp.get(map.get("taskID"));
                plist = (List<Map<String, Object>>) taskMap.get("itemList");
            } else {
                taskMap = map;
                plist = new ArrayList<>();
            }
            //4.创建map对象并封装参数
            Map<String, Object> pMap = new HashMap<>();
            //5.将任务id赋值
            pMap.put("taskID", map.get("taskID"));
            //6.将项目id赋值
            pMap.put("itemID", map.get("itemID"));
            //7.将项目名称赋值
            pMap.put("jitemName", map.get("jitemName"));
            //8.项目描述
            pMap.put("itemDesc", map.get("itemDesc"));
            pMap.put("referenceValue", map.get("referenceValue"));
            pMap.put("writeValue", map.get("writeValue"));
            //9.项目实际值
            pMap.put("actualValueUnit", map.get("actualValueUnit"));
            pMap.put("errorContent", map.get("errorContent"));
            pMap.put("successFlag", map.get("successFlag"));
            pMap.put("successFlagName", map.get("successFlagName"));
            //10.项目异常状态
            pMap.put("exceptionStatus", map.get("exceptionStatus"));
            pMap.put("exceptionResult", map.get("exceptionResult"));
            pMap.put("needPhoto", map.get("needPhoto"));
            plist.add(pMap);
            //11.taskMap分装plist
            taskMap.put("itemList", plist);
            temp.put(map.get("taskID").toString(),taskMap);
        });
        //12.循环将value赋值
        temp.forEach((key, value) -> {
            result.add((Map<String, Object>)value);
        });
        //13.返回集合值
        return result;
    }

    /**
     * @throws ParseException 
     * 巡检任务完工
     * @Title: commitXJ
     * @Description:
     * <p>判断用户id是否为空
     * <p>获取参数
     * <p>循环主单据,已经完工了，跳出本次循环
     * <p>校验任务的巡检项是否都已完成实际维护,巡检项没有全部完成实际，跳出本次循环
     * <p>完工处理(存在问题，任务是离线的，提交任务完工时，如果任务的完工时间超过了任务的有效时间，这个任务是否能完工？)
     * <p>更新巡检任务巡检项
     * <p>更新巡检任务
     * @param:  @param inInfo
     * userID   用户id
     * @param:  @return
     * @return: EiInfo  
     * 执行成功,失败则执行回滚操作
     * @throws
     */
    public EiInfo commitXJDK(EiInfo inInfo) throws ParseException {
        //1.判断用户id是否为空
        if(StringUtils.isBlank(inInfo.getString("userID")) || StringUtils.isBlank(inInfo.getString("json"))){
            //2.如果用户id为空给其赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID或json参数");
            return inInfo;
        }
        //3.通过用户id调用内部数据接口获取该用户的所有信息
        Map<String,Object> workInfo =BaseDockingUtils.getStaffByWorkNo(inInfo.getString("userID"));
        //4.获取json参数转换成json集合对象
        JSONArray jsonArr = JSONArray.fromObject(inInfo.getString("json"));
        // 5.循环主单据(json集合对象)
        for (int i = 0; i < jsonArr.size(); i++) {
            //6.将json集合对象拆分成单个对象
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            //7.获取任务id
            String taskID = jsonObj.getString("taskID");
            //8.通过任务id获取该任务是否完成
            boolean taskStatus = selecTaskStatusDK(taskID);
            if(!taskStatus){ //9.已经完工了，跳出本次循环
                continue;
            }
            //10.校验任务的巡检项是否都已完成实际维护
            String closeFlag = jsonObj.getString("closeFlag");
            //11.通过json对象获取项目集合
            JSONArray taskItem = JSONArray.fromObject(jsonObj.getString("taskItem"));
            //12.判断该任务的所有巡检项目是否全部完成实绩
            boolean isFinish = isAllFinishDK(closeFlag, taskItem);
            if(!isFinish){ //13.巡检项没有全部完成实际，跳出本次循环
                continue;
            }
            //14.完工处理(存在问题，任务是离线的，提交任务完工时，如果任务的完工时间超过了任务的有效时间，这个任务是否能完工？)
            String exceptionFlag = "1"; 
            String finishTimes = "";
            if(jsonObj.has("finishTime")){
                finishTimes = jsonObj.getString("finishTime");
            }
            //15.循环任务作业项
            for (int j = 0; j < taskItem.size(); j++) {
                //16.将json集合转换成json对象
                JSONObject taskItemDetail = taskItem.getJSONObject(j);
                String itemID = taskItemDetail.getString("itemID");
                String writeValue = "";
                //17.是否包含巡检录入值
                if(taskItemDetail.containsKey("writeValue")){
                    writeValue = taskItemDetail.getString("writeValue");
                }
                String successFlag = "1";
                //18.是否包含成功标识
                if(taskItemDetail.containsKey("successFlag")){
                    successFlag = StringUtils.isBlank(taskItemDetail.getString("successFlag")) || "null".equals(taskItemDetail.getString("successFlag")) ? 
                        "1" : taskItemDetail.getString("successFlag");
                }
                String errorContent = "";
                //19.是否包含异常结果
                if(taskItemDetail.containsKey("errorContent")){
                    errorContent = taskItemDetail.getString("errorContent");
                }

                String exceptionStatus = "";
                //20.判断处理状态
                if ("-1".equals(successFlag)) {
                    exceptionStatus = "0";// 21.待处理
                    exceptionFlag = "-1";
                }
                //22.更新巡检任务巡检项
                updateTaskItemDK(itemID, errorContent, writeValue, successFlag, new Date(), inInfo.getString("userID"), exceptionStatus);
            }
            //23.更新巡检任务
            updateDeviceTaskDK(StringUtils.isBlank(finishTimes) ? new Date() : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(finishTimes), 
                (String)workInfo.get("name"), "Y".equals(closeFlag) ? "3" : "2", exceptionFlag, taskID);
        }
        //24.返回状态和信息
        inInfo.setMsgKey("200");
        inInfo.setMsg("执行成功");
        return inInfo;


    }


    /**
     * 异常任务处理
     * @Title: handDevExceptionTask
     * @Description:
     * <p>校验参数是否为空
     * @param:  @param inInfo
     * itemID    任务作业id
     * @param:  @return
     * @return: EiInfo  
     * 异常任务处理成功，失败则执行回滚操作
     * @throws
     */
    public EiInfo handDevExceptionTaskDK(EiInfo inInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //1.校验参数是否为空
        if(StringUtils.isBlank(inInfo.getString("itemID")) || StringUtils.isBlank(inInfo.getString("exceptionResult"))){
            //2.判断用户id为空赋值状态和信息
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少itemID或exceptionResult参数");
            return inInfo;
        }
        //3.map封装参数并修改异常任务
        //4.项目id赋值
        map.put("itemID", inInfo.getString("itemID"));
        map.put("exceptionResult", inInfo.getString("exceptionResult"));
        dao.update("DKJZ01.handDevExceptionTask",map);
        //5.返回处理成功
        inInfo.setMsgKey("200");
        inInfo.setMsg("异常任务处理成功");
        return inInfo;
    }


    /**
     * 
     * 查询是否完工
     *
     * @Title: selecTaskStatus 
     * @param taskID  任务id
     * @return 
     * @return: boolean
     */
    @SuppressWarnings("unchecked")
    private boolean selecTaskStatusDK(String taskID){
        //1.查询是否完工
        Map<String, Integer> map = (Map<String, Integer>) dao.query("DKJZ01.getTaskStatus" ,taskID).get(0);
        //2.获取完工状态
        Integer status = map.get("status");
        //3.未完工
        if(status == 2){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 
     * 巡检项是否完成实际
     * <p>判断巡检项是否完成
     * @Title: isAllFinish 
     * @param closeFlag  关闭标识
     * @param taskItem   巡检项目
     * @return 
     * @return: boolean
     */
    private boolean isAllFinishDK(String closeFlag, JSONArray taskItem) {
        //1.巡检项目是否完工，Y为完工
        if("Y".equals(closeFlag)) {
            return true;
        }
        boolean isfinish = true;
        //2.循环判断巡检项是否完成实际
        for (int i = 0; i < taskItem.size(); i++) {
            //3.获取单个json对象
            JSONObject object = taskItem.getJSONObject(i);
            //4.判断巡检项目完成状态
            if(StringUtils.isBlank(object.getString("successFlag"))){
                isfinish = isfinish && false;
                break;
            }
        }
        return isfinish;
    }

    /**
     * 
     * 更新巡检任务
     * <p>如果任务已完成，录入完成人名称
     * @Title: updateDeviceTask 
     * @param finishTime    完成时间
     * @param finishMan     完成人
     * @param status        状态
     * @param exceptionFlag  异常标识
     * @param taskID         任务id
     * @return: void
     */
    public void updateDeviceTaskDK(Date finishTime, String finishMan, String status, String exceptionFlag, String taskID) {
        //1.创建一个时间转换对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> param = new HashMap<>();
        //2.如果任务已完成，录入完成人名称
        if(status.equals("2")){
            //3.完成时间
            param.put("finishTime", format.format(finishTime));
            //4.完成人
            param.put("finishMan", finishMan);
            //5.完成人
            param.put("finishManName", finishMan);
        } else {
            param.put("invalidTime", format.format(finishTime));
            param.put("invalidMan", finishMan);
        }
        //6.修改巡检任务状态
        param.put("status", status);
        param.put("exceptionFlag", exceptionFlag);
        //7.任务id
        param.put("taskID", taskID);
        //8.修改任务状态信息
        dao.update("DKJZ01.updateDeviceTask", param);
    }

    /**
     * 
     * 更新巡检任务巡检项
     *
     * @Title: updateTaskItem 
     * @param itemID    作业id
     * @param errorContent    异常
     * @param writeValue      录入值
     * @param successFlag     成功标识
     * @param writeTime       录入时间
     * @param writeMan        录入人
     * @param exceptionStatus 异常状态
     * @return: void
     */
    public void updateTaskItemDK(String itemID, String errorContent, String writeValue, 
        String successFlag, Date writeTime, String writeMan, String exceptionStatus) {
        Map<String, Object> param = new HashMap<>();
        //1.将map赋值
        //2.巡检项目id
        param.put("itemID", itemID);
        param.put("errorContent", errorContent);
        //3.巡检项目写入值
        param.put("writeValue", writeValue);
        param.put("successFlag", successFlag);
        //4.转换写入时间格式并赋值
        param.put("writeTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(writeTime));
        //5.写入人
        param.put("writeMan", writeMan);
        param.put("exceptionStatus", exceptionStatus);
        //6.通过map修改任务巡项
        dao.update("DKJZ01.updateTaskItem", param);
    }


    /**
     * 
     * 维护详情新增图片功能
     *
     * @Title: getPic 
     * <p>获取参数
     * <p>保存图片方法
     * @param inInfo
     * itemID     作业id
     * taskID     任务id
     * workNo     人员编号
     * base64Pic  图片字符串
     * @return 
     * 图片信息保存成功,图片信息保存失败
     * @return: EiInfo
     */
    public EiInfo getPicDK(EiInfo inInfo) {
        //1.获取参数
        //2.获取巡检项目id
        String itemID = inInfo.getString("itemID");
        //3.获取任务id
        String taskID = inInfo.getString("taskID");
        //4.获取人员编号
        String workNo = inInfo.getString("workNo");
        //5.获取图片base64字符串
        String pic = inInfo.getString("base64Pic");
        //6.map赋值
        Map param = new HashMap();
        param.put("itemID", itemID);
        param.put("taskID", taskID);
        param.put("workNo", workNo);

        //7.保存图片方法
        Boolean uploadPic = uploadPicDK(param,pic);
        
        EiInfo outInfo = new EiInfo();
        //8.保存图片失败
        if(!uploadPic) {
            outInfo.setStatus(-1);
            //9.图片保存失败赋值状态和信息
            outInfo.setMsgKey("201");
            outInfo.setMsg("图片信息保存失败");
            return outInfo;
        }
        //10.保存图片成功
        outInfo.setMsgKey("200");
        outInfo.setMsg("图片信息保存成功");        
        return outInfo;
    }

    /**
     * 获取指定用户所在的用户组
     * @Title: getTaskDataGroupCode
     * @Description:
     * <p>获取院区编码
     * @param:  @param info
     * name   用户名称
     * @return
     * GROUP_ENAME    组名称
     * @return: EiInfo  
     * @throws
     */
    @SuppressWarnings("unchecked")
    public String getUserRole(String name) {
        Map<String, String> pMap = new HashMap<>();
        //1.获取配置文件数据库名
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        //2.获取院区编码
        String datagroupCode = BaseDockingUtils.getUserGroupByWorkNo(name);
        //3.赋值人员编码
        pMap.put("workNo",name);
        //4.赋值数据组编码
        pMap.put("dataGroupCode",datagroupCode);
        //5.赋值数据库名
        pMap.put("platSchema",platSchema);
        //6.获取用户角色
        List<String> list = dao.query("DKJZ01.getUserRole",pMap);
        String roles = "";
        if(list.size() > 0 ) {
            roles = list.get(0);
        }
        return roles;
    }

    /**
     * 
     * 保存图片方法
     * <p>图片保存路径
     * <p>图片具体参
     * <p>将图片字符串生成图片
     * <p>将图片关联信息保存到数据库
     * @Title: uploadPic 
     * @param *inInfo
     * itemID     作业id
     * taskID     任务id
     * workNo     人员编号
     * pic  图片字符串
     * @return 
     * @return: Boolean
     */
    public Boolean uploadPicDK(Map<String,String> param,String pic) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //1.图片保存路径
        String savaPath = request.getSession().getServletContext().getRealPath("")+PIC_PATH+File.separatorChar;
        //2.图片具体参数
        String itemID = param.get("itemID");
        String taskID = param.get("taskID");
        String workNo = param.get("workNo");

        //3.判断图片base64是否为空
        if (StringUtils.isBlank(pic)) {
            return false;
        }
        //4.创建文件目录
        File files = new File(savaPath);
        if (!files.isDirectory()) {
            files.mkdirs();
        }
        String picId = UUIDUtils.getUUID32();
        //5.将图片字符串生成图片
        boolean basa64ToFile = DevicePicUtil.basa64ToFile(pic, savaPath + picId + ".jpg");
        if(!basa64ToFile) {
            return basa64ToFile;
        }

        //6.将图片关联信息保存到数据库
        Map map = new HashMap();
        //7.赋值图片路径
        map.put("path", savaPath);
        //8.赋值任务id
        map.put("taskID", taskID);
        //9.赋值项目id
        map.put("itemID", itemID);
        //10.转换创建时间并赋值
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        //11.赋值人员编号
        map.put("workNo", workNo);
        //12.赋值图片名字
        map.put("fileName", picId + ".jpg");
        //13.新增图片信息到数据库
        dao.insert("DKJZ01.insertPic", map);
        return true;
    }

}
