package com.baosight.wilp.im.jz.service;

import java.math.BigDecimal;

import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import com.baosight.wilp.im.common.domain.ResultData;

import com.baosight.wilp.common.pr.UUIDUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.wilp.im.common.util.DevicePicUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * 巡检app接口：App端巡检异常报表、App端巡检工作量统计报表、离线下载巡检任务、获取异常任务、设备巡检历史任务、
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
 * @Title: ServiceDIJZApp.java
 * @ClassName: ServiceDIJZApp
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月11日 下午9:06:39
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZApp extends ServiceBase {
    
    /**
     * 图片保存路径
     */
    public static final String PIC_PATH = "upload/device";



	/**
	 * App端巡查异常报表
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
	public EiInfo getDevExceptionTaskApp(EiInfo info) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		//ResultData result = new ResultData();
		String userID =info.getString("userID");
				//request.getParameter("userID");
		int page =Integer.parseInt(info.getString("page"));
				//Integer.parseInt(request.getParameter("page"));
		int rows = 10;
		int startRow = (page - 1) * rows;

		String exceptionStatus  =info.getString("exceptionStatus");
				//request.getParameter("exceptionStatus");
		//校验用户id是否为空
		if (StringUtils.isBlank(userID)) {
			info.setMsgKey("199");
			info.setMsg("userID参数为空");
//			pMap.put("respCode", 199);
//			pMap.put("respMsg", "userID参数为空");
		} else {
			map.put("userID", userID);
			map.put("rows", rows);
			map.put("startRow", startRow);
			map.put("exceptionStatus", exceptionStatus);
			try {
//				int count= dao.count("IMJZ01.getDevExceptionTaskAppCount", map);
//				//判断总数是否小于startRow
//				if(count<=startRow) {
//					startRow=count-rows;
//					map.put("startRow", startRow);
//				}
				List list = dao.query("IMJZ01.getDevExceptionTaskApp", map);
						//appInspectionService.getDevExceptionTask(map);

			/*
			 * for(int i=0 ;i<list.size(); i++) { Map map2 = (Map) list.get(i); int itemID =
			 * (int)map2.get("itemID"); List mList= dao.query("DIJZ01.getPicListApp",
			 * itemID); //appInspectionService.getPicList(itemID);
			 *
			 * JSONArray jsonPicArray = new JSONArray(); if(mList != null) { for(int j1=0
			 * ;j1<mList.size(); j1++) { HashMap map1 = (HashMap) mList.get(j1);
			 *
			 * String storagePath = "";
			 * if(map1.get("thumbnailPath").toString().indexOf("thumbnailFiles") != -1) {
			 * storagePath =
			 * map1.get("thumbnailPath").toString().split("thumbnailFiles")[1]; }
			 *
			 * String url = request.getScheme()+"://"+
			 * request.getServerName()+":"+request.getServerPort()+request.getContextPath()+
			 * "/"+"thumbnailFiles"+storagePath; jsonPicArray.add(url); } }
			 *
			 * map2.put("picArray", jsonPicArray); }
			 */

//				pMap.put("respCode", "200");
//				pMap.put("respMsg", "获取成功");
				info.setMsgKey("200");
				info.setMsg("获取成功");
				pMap.put("obj", list);
			} catch (Exception e) {
				e.printStackTrace();
				info.setMsgKey("201");
				info.setMsg("获取失败");
//				pMap.put("respCode", "201");
//				pMap.put("respMsg", "获取失败");
			}
		}

		/*
		 * Object object = JsonUtils.toJson(pMap); try { PrintWriter pw =
		 * response.getWriter(); pw.write((String) object); pw.flush(); } catch
		 * (IOException e) { e.printStackTrace(); } return inInfo;
		 */
			info.addRow("DevException", pMap);
			return info;
	}

	/**
	 * App端巡查报表
	 * @Title: getDeviceWorkload
	 * 班组保养/保养工作量统计
	 * <p>校验时间不为空
	 * <p>循环获取工作量总和
	 * @param info
	 * beginDate  开始时间
	 * endDate    结束时间
	 * @param info
	 * @return
	 * total_jgnum    总数
	 * total_wgnum    完工数
	 * total_csnum    失效数
	 * total_zxnum    生效数
	 * total_cswgnum  关闭数
	 */
	public EiInfo getDeviceWorkload(EiInfo info) {
		ResultData result = new ResultData();


		 String beginDate = info.getString("beginDate");
		 String endDate =info.getString("endDate") ;
		 //String type = request.getParameter("type");
		 //校验时间不为空
		 if(StringUtils.isBlank(beginDate)) {

			    //inInfo.setStatus(-1);
			 info.setMsgKey("195");
			 info.setMsg("beginDate参数为空");
			 //result.setRespCode("195");
		    // result.setRespMsg("beginDate参数为空");
		     return info;
		    }
		 if(StringUtils.isBlank(endDate)) {
			 info.setMsgKey("196");
			 info.setMsg("endDate参数为空");
			 //result.setRespCode("196");
		     //result.setRespMsg("endDate参数为空");
		     return info;
		     }
//		 if(StringUtils.isBlank(type)) { 
//			 result.setRespCode("197");
//		     result.setRespMsg("type参数为空"); 
//		     return result; 
//		     }
		 
		/*String flag = request.getParameter("flag");
		String year = request.getParameter("year");
		String season = request.getParameter("season");
		String month = request.getParameter("month");
		String type = request.getParameter("type");
		if (StringUtils.isBlank(year)) {
			result.setRespCode("199");
			result.setRespMsg("查询年度为空!");
			return result;
		}
		if(type!=null&!type.equals("")){
			if(!(type.toLowerCase().equals("maintain")||
					type.toLowerCase().equals("inspection"))){
				result.setRespCode("195");
				result.setRespMsg("类型不合法!");
				return result;
			}
		}
		else{
			result.setRespCode("199");
			result.setRespMsg("查询类型为空!");
			return result;
		}

		String beginDate = "";
		String endDate = "";

		if (flag.equals("Y")) {
			beginDate = year + "0101";
			endDate = year + "1231";
		} else if (flag.equals("S")) {
			if (season.equals("1")) {
				beginDate = year + "0101";
				endDate = year + "0331";
			} else if (season.equals("2")) {
				beginDate = year + "0401";
				endDate = year + "0631";
			} else if (season.equals("3")) {
				beginDate = year + "0701";
				endDate = year + "0931";
			} else if (season.equals("4")) {
				beginDate = year + "1001";
				endDate = year + "1231";
			} else {
				result.setRespCode("198");
				result.setRespMsg("季度不合法!");
				return result;
			}
		} else if (flag.equals("M")) {
			if (StringUtils.isBlank(month)) {
				result.setRespCode("197");
				result.setRespMsg("查询月份为空!");
				return result;
			}
			try {
				int m = Integer.parseInt(month);
				if (m < 10 && m > 0) {
					month = "0" + m;
				} else if (m > 12) {
					result.setRespCode("196");
					result.setRespMsg("月份不合法!");
					return result;
				}
			} catch (Exception e) {
				result.setRespCode("197");
				result.setRespMsg("月份不合法!");
				return result;
			}

			beginDate = year + month + "01";
			endDate = year + month + "31";
		} else {
			result.setRespCode("196");
			result.setRespMsg("查询维度不合法,必须为年(Y)、季度(S)、月(M)!");
			return result;
		}*/
		/*
		 * try { StringBuilder sqlBuf = new StringBuilder( "select * from (" +
		 * "SELECT t.id AS deptID,DEPT_NAME AS NAME,a.XJtype,ifnull(sum(a.num), 0) AS jgnum,ifnull(sum(a.wgnum), 0) AS wgnum,ifnull(sum(a.csnum), 0) AS csnum,ifnull(sum(a.zxnum), 0) AS zxnum,ifnull(sum(a.cswgnum), 0) AS cswgnum"
		 * + " FROM tbmbd01 t LEFT JOIN (" +
		 * " SELECT t.departID,1 AS num,ds.XJtype,case t.status when '2' then 1 else 0 end as wgnum,case t.status when '-1' then 1 else 0 end as csnum,case t.status when '1' then 1 else 0 end as zxnum,case t.status when '4' then 1 else 0 end as cswgnum"
		 * +
		 * " FROM device_task t 	LEFT JOIN device_scheme ds on	t.schemeID = ds.schemeID"
		 * + " WHERE DATE_FORMAT(t.createTime, '%Y-%m-%d') >= ? " +
		 * " AND DATE_FORMAT(t.createTime, '%Y-%m-%d') <= ? " +
		 * " AND t.taskType = ? ) a ON t.id = a.departID " + " WHERE 1=1 " +
		 * " AND EXISTS(SELECT 1 FROM device_scheme s WHERE s.departID=t.id) " +
		 * " GROUP BY DEPT_NAME,XJtype" +
		 * ") tb where tb.jgnum != 0 order by tb.jgnum desc;");
		 */
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startTimeS", beginDate);
			map.put("startTimeE", endDate);
			//map.put("taskType", null);
			List<Map<String, Object>> query = dao.query("IMJZ01.getDeviceWorkloadApp", map);
			/*
			 * systemService.findForJdbc( sqlBuf.toString(), beginDate, endDate, type);
			 */
			int total_jgnum = 0;
			int total_wgnum = 0;
			int total_csnum = 0;
			int total_zxnum = 0;
			int total_cswgnum = 0;
		    //循环获取工作量总和
			for (Map<String, Object> m : query) {
				BigDecimal jgnum = (BigDecimal) m.get("jgnum");
				BigDecimal wgnum = (BigDecimal) m.get("wgnum");
				BigDecimal csnum = (BigDecimal) m.get("csnum");
				BigDecimal zxnum = (BigDecimal) m.get("zxnum");
				BigDecimal cswgnum = (BigDecimal) m.get("cswgnum");
				total_jgnum += jgnum.intValue();
				total_wgnum += wgnum.intValue();
				total_csnum += csnum.intValue();
				total_zxnum += zxnum.intValue();
				total_cswgnum += cswgnum.intValue();
			}

			Map<String,Object> maps = new HashMap<String,Object>();
			maps.put("total_jgnum", total_jgnum);
			maps.put("total_wgnum", total_wgnum);
			maps.put("total_csnum", total_csnum);
			maps.put("total_zxnum", total_zxnum);
			maps.put("total_cswgnum", total_cswgnum);
			maps.put("data", query);
			info.addRow("work", maps);
			//result.setObj(maps);
		} catch (Exception e) {
			e.printStackTrace();
//			result.setRespCode("201");
//			result.setRespMsg("查询失败!");
//			return result;

			info.setMsgKey("201");
			 info.setMsg("查询失败!");
			 //result.setRespCode("196");
		     //result.setRespMsg("endDate参数为空");
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
    public EiInfo getDeviceOfflineTask (EiInfo inInfo){
        String userID = inInfo.getString("userID");
        //参数处理
        if(StringUtils.isBlank(userID)){
            inInfo.setStatus(-1);
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        inInfo.set("isAdvance", 0);
        inInfo.set("taskStatus", "1");
        inInfo.set("isLeader", "N");
        inInfo.set("isPage", "N");
        //获取当前用户角色，判断是否为巡检领导
        //Map<String, Object> userInfo = DeviceEiUtil.getUserInfo(inInfo.getString("userID"));
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(userID);
        String name = (String)staffByUserId.get("name");
        String userRole = getUserRole(userID);
        if(userRole != null && userRole.contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //查询
        return queryTask(inInfo);
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
    public EiInfo getDevExceptionTask(EiInfo inInfo) {
        //参数处理
        if(StringUtils.isBlank(inInfo.getString("userID"))){
            inInfo.setStatus(-1);
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        inInfo.set("isLeader", "N");
        inInfo.set("successFlag", "-1");
        //获取当前用户角色，判断是否为巡检领导
        Map<String, Object> userInfo = DeviceEiUtil.getUserInfo(inInfo.getString("userID"));
        if(userInfo != null && userInfo.get("role").toString().contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //分页
        String page = inInfo.getString("page");
        if(StringUtils.isNotBlank(page)){
            int offset = (Integer.parseInt(page)-1) * 10;
            inInfo.set("offset", offset);
            inInfo.set("limit", 10);
        }
        //查询
        return queryTaskExc(inInfo);
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
    public EiInfo getDevTaskHistory(EiInfo inInfo) {
        //参数处理
        if(StringUtils.isBlank(inInfo.getString("userID"))){
            inInfo.setStatus(-1);
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID参数");
            return inInfo;
        }
        inInfo.set("isLeader", "N");
        //获取当前用户角色，判断是否为巡检领导
        Map<String, Object> userInfo = DeviceEiUtil.getUserInfo(inInfo.getString("userID"));
        if(userInfo != null && userInfo.get("role").toString().contains("LEADER")){
            inInfo.set("isLeader", "Y");
        }
        //分页
        String page = inInfo.getString("page");
        if(StringUtils.isNotBlank(page)){
            int offset = (Integer.parseInt(page)-1) * 10;
            inInfo.set("offset", offset);
            inInfo.set("limit", 10);
        }
        //查询
        return queryTask(inInfo);
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
    public EiInfo deviceTaskDetail(EiInfo inInfo) {
        //参数处理
        if(StringUtils.isBlank(inInfo.getString("taskID"))){
            inInfo.setStatus(-1);
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少taskID参数");
            return inInfo;
        }
        inInfo.set("isLeader", "Y");
        inInfo.set("isPage", "N");
        //查询
        return queryTask(inInfo);
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
    public EiInfo getDeviceDetail(EiInfo inInfo) {
        //参数处理
        if(StringUtils.isBlank(inInfo.getString("id"))){
            inInfo.setStatus(-1);
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少设备id参数");
            return inInfo;
        }
        List<Map<String, Object>> list = dao.query("IMJZ01.getMachineInfo", inInfo.getString("id"));
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
    public EiInfo queryTask(EiInfo inInfo) {
        String[] param = {"isAdvance", "taskID", "taskCode", "taskStatus", "isLeader","userID",
            "successFlag","exceptionStatus","fixedCode","beginTime","endTime","offset","limit"};
        String blockId = "result";
        if(StringUtils.isNoneBlank(inInfo.getString("page"))){
            blockId = null;
        }
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), blockId);
         /*int count = 0;
        //是否分页
        if("N".equals(inInfo.getString("isPage"))){
            map.put("offset",null);
        } else {
            count = dao.count("IMJZ01.queryTaskCount", map);
        }*/
        //查询行数
        List<Integer> count = dao.query("IMJZ01.queryTaskCount", map);
        List<Map<String, Object>> list = new ArrayList<>();
        Integer coun = null;
        if(!count.isEmpty()){
            coun =  count.get(0);
            if(coun>1000){
                int s = coun/1000;
                int s2 = coun%1000;
                if(s2 != 0){
                    s += 1;
                }
                int offset = 0;
                int limit = 1000;
                for(int i = 0;i < s;i++){
                    map.put("offset",offset);
                    map.put("limit",limit);
                    List<Map<String, Object>> listParam  = dao.query("IMJZ01.queryTask", map);
                    list.addAll(listParam);
                    offset = limit;
                    limit += 1000;
                }
            }else {
                map.put("offset",0);
                map.put("limit",1000);
                list = dao.query("IMJZ01.queryTask", map);
            }
        }
        //循环将任务项目数据处理出来
        List<Map<String, Object>> rList =  dealResult(list);
        coun = "N".equals(inInfo.getString("isPage")) ? list == null ? 0 : list.size() : coun;
        return DeviceEiUtil.buildResult(inInfo, rList, coun, "result");
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
    private List<Map<String, Object>> dealResult(List<Map<String, Object>> list) {
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        Map<String, Object> temp = new HashMap<String, Object>();
        //循环将任务项目数据处理出来
        list.forEach(map -> {
            Map<String, Object> taskMap = null;
            List<Map<String, Object>> plist = null;
            if(temp.containsKey(map.get("taskID"))){
                taskMap = (Map<String, Object>) temp.get(map.get("taskID"));
                plist = (List<Map<String, Object>>) taskMap.get("itemList");
            } else {
                taskMap = map;
                plist = new ArrayList<>();
            }
            Map<String, Object> pMap = new HashMap<>();
            pMap.put("taskID", map.get("taskID"));
            pMap.put("itemID", map.get("itemID"));
            pMap.put("jitemName", map.get("jitemName"));
            pMap.put("itemDesc", map.get("itemDesc"));
            pMap.put("referenceValue", map.get("referenceValue"));
            pMap.put("writeValue", map.get("writeValue"));
            pMap.put("actualValueUnit", map.get("actualValueUnit"));
            pMap.put("errorContent", map.get("errorContent"));
            pMap.put("successFlag", map.get("successFlag"));
            pMap.put("successFlagName", map.get("successFlagName"));
            pMap.put("exceptionStatus", map.get("exceptionStatus"));
            pMap.put("exceptionResult", map.get("exceptionResult"));
            pMap.put("needPhoto", map.get("needPhoto"));
            plist.add(pMap);
            taskMap.put("itemList", plist);
            temp.put(map.get("taskID").toString(),taskMap);
        });
        temp.forEach((key, value) -> {
            result.add((Map<String, Object>)value);
        });
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
    public EiInfo commitXJ(EiInfo inInfo) throws ParseException {
        //判断用户id是否为空
        if(StringUtils.isBlank(inInfo.getString("userID")) || StringUtils.isBlank(inInfo.getString("json"))){
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少userID或json参数");
            return inInfo;
        }
        Map<String,Object> workInfo =BaseDockingUtils.getStaffByWorkNo(inInfo.getString("userID"));
        //获取参数
        JSONArray jsonArr = JSONArray.fromObject(inInfo.getString("json"));
        // 循环主单据
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String taskID = jsonObj.getString("taskID");
            boolean taskStatus = selecTaskStatus(taskID);
            if(!taskStatus){ //已经完工了，跳出本次循环
                continue;
            }
            //校验任务的巡检项是否都已完成实际维护
            String closeFlag = jsonObj.getString("closeFlag");
            JSONArray taskItem = JSONArray.fromObject(jsonObj.getString("taskItem"));
            boolean isFinish = isAllFinish(closeFlag, taskItem);
            if(!isFinish){ //巡检项没有全部完成实际，跳出本次循环
                continue;
            }
            //完工处理(存在问题，任务是离线的，提交任务完工时，如果任务的完工时间超过了任务的有效时间，这个任务是否能完工？)
            String exceptionFlag = "1";
            String finishTimes = "";
            if(jsonObj.has("finishTime")){
                finishTimes = jsonObj.getString("finishTime");
            }
            for (int j = 0; j < taskItem.size(); j++) {
                JSONObject taskItemDetail = taskItem.getJSONObject(j);
                String itemID = taskItemDetail.getString("itemID");
                String writeValue = "";
                if(taskItemDetail.containsKey("writeValue")){
                    writeValue = taskItemDetail.getString("writeValue");
                }
                String successFlag = "1";
                if(taskItemDetail.containsKey("successFlag")){
                    successFlag = StringUtils.isBlank(taskItemDetail.getString("successFlag")) || "null".equals(taskItemDetail.getString("successFlag")) ?
                        "1" : taskItemDetail.getString("successFlag");
                }
                String errorContent = "";
                if(taskItemDetail.containsKey("errorContent")){
                    errorContent = taskItemDetail.getString("errorContent");
                }

                String exceptionStatus = "";
                if ("-1".equals(successFlag)) {
                    exceptionStatus = "0";// 待处理
                    exceptionFlag = "-1";
                }
                //更新巡检任务巡检项
                updateTaskItem(itemID, errorContent, writeValue, successFlag, new Date(), inInfo.getString("userID"), exceptionStatus);
            }
            //更新巡检任务
            updateDeviceTask(StringUtils.isBlank(finishTimes) ? new Date() : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(finishTimes),
                (String)workInfo.get("name"), "Y".equals(closeFlag) ? "3" : "2", exceptionFlag, taskID);
        }
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
    public EiInfo handDevExceptionTask(EiInfo inInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //校验参数是否为空
        if(StringUtils.isBlank(inInfo.getString("itemID")) || StringUtils.isBlank(inInfo.getString("exceptionResult"))){
            inInfo.setMsgKey("201");
            inInfo.setMsg("缺少itemID或exceptionResult参数");
            return inInfo;
        }
        map.put("itemID", inInfo.getString("itemID"));
        map.put("exceptionResult", inInfo.getString("exceptionResult"));
        dao.update("IMJZ01.handDevExceptionTask",map);

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
    private boolean selecTaskStatus(String taskID){
        Map<String, Integer> map = (Map<String, Integer>) dao.query("IMJZ01.getTaskStatus" ,taskID).get(0);
        Integer status = map.get("status");
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
    private boolean isAllFinish(String closeFlag, JSONArray taskItem) {
        if("Y".equals(closeFlag)) {
            return true;
        }
        boolean isfinish = true;
        //判断巡检项是否完成
        for (int i = 0; i < taskItem.size(); i++) {
            JSONObject object = taskItem.getJSONObject(i);
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
    public void updateDeviceTask(Date finishTime, String finishMan, String status, String exceptionFlag, String taskID) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> param = new HashMap<>();
        //如果任务已完成，录入完成人名称
        if(status.equals("2")){
            param.put("finishTime", format.format(finishTime));
            param.put("finishMan", finishMan);
            param.put("finishManName", finishMan);
        } else {
            param.put("invalidTime", format.format(finishTime));
            param.put("invalidMan", finishMan);
        }
        param.put("status", status);
        param.put("exceptionFlag", exceptionFlag);
        param.put("taskID", taskID);
        dao.update("IMJZ01.updateDeviceTask", param);
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
    public void updateTaskItem(String itemID, String errorContent, String writeValue,
        String successFlag, Date writeTime, String writeMan, String exceptionStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("itemID", itemID);
        param.put("errorContent", errorContent);
        param.put("writeValue", writeValue);
        param.put("successFlag", successFlag);
        param.put("writeTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(writeTime));
        param.put("writeMan", writeMan);
        param.put("exceptionStatus", exceptionStatus);
        dao.update("IMJZ01.updateTaskItem", param);
    }



    // ====================================新增保存图片功能================================================================



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
    public EiInfo getPic(EiInfo inInfo) {
        //获取参数
        String itemID = inInfo.getString("itemID");
        String taskID = inInfo.getString("taskID");
        String workNo = inInfo.getString("workNo");
        String pic = inInfo.getString("base64Pic");
        //String[] split = pic.split(",");
        //ArrayList<String> strParam = strParam(split);

        Map param = new HashMap();
        param.put("itemID", itemID);
        param.put("taskID", taskID);
        param.put("workNo", workNo);

        //保存图片方法
        Boolean uploadPic = uploadPic(param,pic);

        EiInfo outInfo = new EiInfo();

        if(!uploadPic) {
            outInfo.setStatus(-1);
            outInfo.setMsgKey("201");
            outInfo.setMsg("图片信息保存失败");
            return outInfo;
        }
        outInfo.setMsgKey("200");
        outInfo.setMsg("图片信息保存成功");
        return outInfo;
    }


    /**
     *
     * 保存图片方法
     * <p>图片保存路径
     * <p>图片具体参
     * <p>将图片字符串生成图片
     * <p>将图片关联信息保存到数据库
     * @Title: uploadPic
     * @param param
     * itemID     作业id
     * taskID     任务id
     * workNo     人员编号
     * pic  图片字符串
     * @return
     * @return: Boolean
     */
    public Boolean uploadPic(Map<String,String> param,String pic) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //图片保存路径
        String savaPath = request.getSession().getServletContext().getRealPath("")+PIC_PATH+File.separatorChar;
        //图片具体参数
        String itemID = param.get("itemID");
        String taskID = param.get("taskID");
        String workNo = param.get("workNo");


        if (StringUtils.isBlank(pic)) {
            return false;
        }

        File files = new File(savaPath);
        if (!files.isDirectory()) {
            files.mkdirs();
        }
        String picId = UUIDUtils.getUUID32();
        //将图片字符串生成图片
        boolean basa64ToFile = DevicePicUtil.basa64ToFile(pic, savaPath + picId + ".jpg");
        if(!basa64ToFile) {
            return basa64ToFile;
        }

        //将图片关联信息保存到数据库
        Map map = new HashMap();
        map.put("path", savaPath);
        map.put("taskID", taskID);
        map.put("itemID", itemID);
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map.put("workNo", workNo);
        map.put("fileName", picId + ".jpg");

        dao.insert("IMJZ01.insertPic", map);
        return true;
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
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        //获取院区编码
        String datagroupCode = BaseDockingUtils.getUserGroupByWorkNo(name);
        pMap.put("workNo",name);
        pMap.put("dataGroupCode",datagroupCode);
        pMap.put("platSchema",platSchema);

        List<String> list = dao.query("IMJZ01.getUserRole",pMap);
        String roles = "";
        if(list.size() > 0 ) {
            roles = list.get(0);
        }
        return roles;
    }



    /**
     * 查询巡检任务
     * @Title: queryTask
     * @Description:
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
    public EiInfo queryTaskExc(EiInfo inInfo) {
        String[] param = {"isAdvance", "taskID", "taskCode", "taskStatus", "isLeader","userID",
            "successFlag","exceptionStatus","fixedCode","beginTime","endTime","offset","limit"};
        String blockId = "result";
        if(StringUtils.isNoneBlank(inInfo.getString("page"))){
            blockId = null;
        }
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), blockId);
        int count = 0;
        //是否分页
        if("N".equals(inInfo.getString("isPage"))){
            map.put("offset",null);
        } else {
            count = dao.count("IMJZ01.queryTaskCountExc", map);
        }

        //改造获取完成人员名称 -- 2021-08-04
        List<Map<String, Object>> list = dao.query("IMJZ01.queryTaskExc", map);


        //循环将任务项目数据处理出来
        //List<Map<String, Object>> rList =  dealResult(list);
        count = "N".equals(inInfo.getString("isPage")) ? list == null ? 0 : list.size() : count;
        return DeviceEiUtil.buildResult(inInfo, list, count, "result");
    }

}
