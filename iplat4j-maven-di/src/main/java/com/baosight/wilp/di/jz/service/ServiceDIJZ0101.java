package com.baosight.wilp.di.jz.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.di.common.domain.DeviceBillType;
import com.baosight.wilp.di.common.util.DeviceEiUtil;
import com.baosight.wilp.di.common.util.DeviceGeneCode;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 巡检新增、编辑功能：跳转新增或编辑页面、查询科室、查询人员、保存基准、设备包信息
 * <p>1.跳转新增或编辑页面  initLoad
 * <p>2.查询科室   queryDept
 * <p>3.查询人员   queryPerson
 * <p>4.保存基准   insertScheme
 * <p>5.设备包信息   device
 * 
 * @Title: ServiceDIJZ0101.java
 * @ClassName: ServiceDIJZ0101
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午3:52:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class ServiceDIJZ0101 extends ServiceBase {

    protected static final Logger logger = LoggerFactory.getLogger(ServiceDIJZ0101.class);

	/**
	 * 跳转新增或编辑页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>根据操作类型operType判断是跳转新增页面还的编辑页面
	 * <p>查询基准
	 * <p>查询周期
	 * <p>查询项目
	 * <p>查询执行人员
	 * <p>查询设备信息
	 * @param inInfo
	 * operType   操作类型
	 * schemeID   基准id
	 * @return
       machineCode 设备编码
       machineName 设备名称
       models      规格型号
       fixedPlace  安装地点
       cycle       周期
       unit        单位
       unitName    单位
       holiday     是否跳过假期
       holidayName 是否跳过假期
       weekend     是否跳过周末
       weekendName 是否跳过周末
       startTime   开始时间
       itemId      作业id
       content     巡检作业项目名称
       referenceValue  巡检项目参考值
       projectDesc     巡检作业说明
       actualValueUnit 巡检实际单位
       deptName   执行部门
       deptNum    执行部门编号
       name       执行人
       workNo     工号
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    //根据操作类型operType判断是跳转新增页面还的编辑页面
		String operType = (String) inInfo.getAttr().get("operType");
		if (operType.equals("add")) {
			return inInfo;
		} else {
			String id = (String) inInfo.getAttr().get("id");
			Map<String, String> param = new HashMap<>();
			param.put("schemeID", id);
			//查询基准
			List<Map<String, Object>> schemeInfo = dao.query("DIJZ01.queryScheme", param);
			
			//查询周期
			List<Map<String, Object>> cycle = dao.query("DIJZ01.queryCycle", id);
			//查询项目
			List<Map<String, Object>> item = dao.query("DIJZ01.queryItem", id);
			item.forEach(i -> {
				i.put("itemId", i.get("itemID"));
				i.put("content", i.get("itemName"));
				i.put("projectDesc", i.get("itemDesc"));
			});
			//查询执行人员
			List<Map<String, Object>> exman = dao.query("DIJZ01.queryExman", id);
			item.forEach(m -> {
				m.put("id", m.get("jobID"));
			});
			
			EiInfo outInfo = new EiInfo();
			Map<String, Object> map = schemeInfo.get(0);
			map.put("schemeMan", map.get("managerNo"));
			map.put("schemeDept", map.get("departNo"));
			map.put("operType", "update");
			logger.info("基准对象map：", JSONObject.toJSONString(map));
			outInfo.setAttr(map);
			
			String machineId = (String)map.get("machineID");
			//查询设备信息
			List<Map<String, Object>> device = dao.query("DIJZ01.queryDevice", machineId);
			
			outInfo.addRows("device", device);
			outInfo.addRows("cycle", cycle);
			outInfo.addRows("item", item);
			outInfo.addRows("exman", exman);
			return outInfo;
		}
	}
	
	/**
     * 新增或编辑页面
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>根据操作类型operType判断是跳转新增页面还的编辑页面
     * <p>查询基准
     * <p>查询周期
     * <p>查询项目
     * <p>查询执行人员
     * <p>查询设备信息
     * @param inInfo
     * operType   操作类型
     * schemeID   基准id
     * @return
       machineCode 设备编码
       machineName 设备名称
       models      规格型号
       fixedPlace  安装地点
       cycle       周期
       unit        单位
       unitName    单位
       holiday     是否跳过假期
       holidayName 是否跳过假期
       weekend     是否跳过周末
       weekendName 是否跳过周末
       startTime   开始时间
       itemId      作业id
       content     巡检作业项目名称
       referenceValue  巡检项目参考值
       projectDesc     巡检作业说明
       actualValueUnit 巡检实际单位
       deptName   执行部门
       deptNum    执行部门编号
       name       执行人
       workNo     工号
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //根据操作类型operType判断是跳转新增页面还的编辑页面
        String operType = (String) inInfo.getAttr().get("operType");
        if (operType.equals("add")) {
            return inInfo;
        } else {
            String id = (String) inInfo.getAttr().get("schemeID");
            Map<String, String> param = new HashMap<>();
            param.put("schemeID", id);
            //查询基准
            List<Map<String, Object>> schemeInfo = dao.query("DIJZ01.queryScheme", param);
            
            //查询周期
            List<Map<String, Object>> cycle = dao.query("DIJZ01.queryCycle", id);
            //查询项目
            List<Map<String, Object>> item = dao.query("DIJZ01.queryItem", id);
            item.forEach(i -> {
                i.put("itemId", i.get("itemID"));
                i.put("content", i.get("itemName"));
                i.put("projectDesc", i.get("itemDesc"));
            });
            //查询执行人员
            List<Map<String, Object>> exman = dao.query("DIJZ01.queryExman", id);
            item.forEach(m -> {
                m.put("id", m.get("jobID"));
            });
            
            EiInfo outInfo = new EiInfo();
            Map<String, Object> map = schemeInfo.get(0);
            map.put("schemeMan", map.get("managerNo"));
            map.put("schemeDept", map.get("departNo"));
            map.put("operType", "update");
            logger.info("基准对象map：", JSONObject.toJSONString(map));
            outInfo.setAttr(map);
            
            String machineId = (String)map.get("machineID");
            //查询设备信息
            List<Map<String, Object>> device = dao.query("DIJZ01.queryDevice", machineId);
            
            outInfo.addRows("device", device);
            outInfo.addRows("cycle", cycle);
            outInfo.addRows("item", item);
            outInfo.addRows("exman", exman);
            return outInfo;
        }
    }
	
	/**
	 * 查询科室
	 * <p>接口改造获取科室信息
	 *
	 * @Title: queryDept 
	 * @param eiInfo
	 * @return 
	 * deptName   科室名称
	 * deptNum    科室编号
	 * @return: EiInfo
	 */
	public EiInfo queryDept(EiInfo info) {
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
       // return BaseDockingUtils.getDeptAllPage(map, "dept");
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        int count=0;
        EiInfo outInfo=BaseDockingUtils.getDeptAllPage(map, "dept");
        if(outInfo.getBlock("dept")!=null) {
          list=outInfo.getBlock("dept").getRows();
          if(!list.isEmpty()) {
          count=(int)outInfo.getBlock("dept").getAttr().get("count");
          }
             return DeviceEiUtil.buildResult(info, list, count, "dept");
        }else {
             return DeviceEiUtil.buildResult(info, list, count, "dept");
        }
    }
//	public EiInfo queryDept(EiInfo eiInfo) {
//		Map<String, Object> paramMap = DeviceEiUtil.buildParamter(eiInfo, "inqu_status", "dept");
//		//paramMap.remove("limit");
//		//paramMap.remove("offset");
//		paramMap.put("offset", 0);
//		paramMap.put("limit", 500);
//		//List query = dao.query("DIJZ0101.queryDept", paramMap);
//		//接口改造获取科室
//		EiInfo deptInfo=BaseDockingUtils.getDeptAllPage(paramMap, "dept");
//		Map blank = new HashMap();
//		blank.put("deptName", "--请选择--");
//		//query.add(0, blank);
//		List<Map<String, Object>> query=new ArrayList<Map<String,Object>>();
//		query.add(0, blank);
//		if(deptInfo.getBlock("dept")==null) {
//			deptInfo.addBlock("dept").setRows(query);
//		}else {
//		deptInfo.getBlock("dept").getRows().add(0,blank);
//		}
////		logger.info("巡检基准--查询部门list:{}", JSONObject.toJSONString(query));
////		int count = dao.count("DIJZ0101.countDept", paramMap);
////		return DeviceEiUtil.buildResult(eiInfo, query, count, "dept");
//		return deptInfo;
//	}
	
	/**
	 * 查询人员
	 * <p>接口改造获取人员信息
	 *
	 * @Title: queryPerson 
	 * @param eiInfo
	 * @return 
	 * workNo  人员编号
	 * name    人员名称
	 * @return: EiInfo
	 */
	public EiInfo queryPerson(EiInfo info) {
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "person");
        String blockId = info.getBlockIds();
        if(blockId.equals("inqu_status,person")) {
            EiInfo outinfo = new EiInfo();
            EiBlock block = new EiBlock("person");
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            String name = (String) listMap.get(0).get("name");
            map.put("userName", name);
         //   return BaseDockingUtils.getStaffAllPage(map,"person");
            List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
            int count=0;
            EiInfo outInfo=BaseDockingUtils.getStaffAllPage(map, "person");
            if(outInfo.getBlock("person")!=null) {
              list=outInfo.getBlock("person").getRows();
              if(!list.isEmpty()) {
                  count=(int)outInfo.getBlock("person").getAttr().get("count");
                  }
                 return DeviceEiUtil.buildResult(info, list, count, "person");
            }else {
                 return DeviceEiUtil.buildResult(info, list, count, "person");
            }
        }
       // return BaseDockingUtils.getStaffAllPage(map, "person");
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        int count=0;
        EiInfo outInfo=BaseDockingUtils.getStaffAllPage(map, "person");
        if(outInfo.getBlock("person")!=null) {
          list=outInfo.getBlock("person").getRows();
          if(!list.isEmpty()) {
          count=(int)outInfo.getBlock("person").getAttr().get("count");
          }
             return DeviceEiUtil.buildResult(info, list, count, "person");
        }else {
             return DeviceEiUtil.buildResult(info, list, count, "person");
        }
    }
//	public EiInfo queryMan(EiInfo eiInfo) {
//		Map<String, Object> paramMap = DeviceEiUtil.buildParamter(eiInfo, "inqu_status", "man");
//		//paramMap.remove("limit");
//		//paramMap.remove("offset");
//		paramMap.put("offset", 0);
//		paramMap.put("limit", 500);
//		//List<Map<String, Object>> query = dao.query("DIJZ0101.queryMan", paramMap);
//		//接口改造获取人员
//		EiInfo eiInfos=BaseDockingUtils.getStaffAllPage(paramMap, "man");
//		Map blank = new HashMap();
//		blank.put("name", "--请选择--");
//		//query.add(0, blank);
//		List<Map<String, Object>> query=new ArrayList<Map<String,Object>>();
//		query.add(blank);
//		if(eiInfos.getBlock("man")==null) {
//			eiInfos.addBlock("man").setRows(query);
//		}else {
//		eiInfos.getBlock("man").getRows().add(0,blank);
//		}
////		logger.info("巡检基准--查询人员list:{}", JSONObject.toJSONString(query));
////		int count = dao.count("DIJZ0101.countMan", paramMap);
////		return DeviceEiUtil.buildResult(eiInfo, query, count, "man");
//		return eiInfos;
//	}
	
	/**
	 * 保存基准
	 * <p>获取登录人和操作时间
	 * <p>基准基础数据
	 * <p>如果是更新操作，则先删除原来数据，再重新添加基准及基准周期、关联设备、巡查项目、执行人列表等
	 * <p>取出设备
     * <p>取出项目
     * <p>取出执行人
     * <p>取出周期
	 * <p>校验参数,不为空(设备信息、项目信息、周期信息、执行人)
	 * <p>循环设备插入基准
	 * <p>循环插入项目
	 * <p>循环插入周期
	 * <p>循环插入执行人
	 *
	 * @Title: insertScheme 
	 * @param eiInfo
	 * schemeID      基准id
	 * schemeCode    基准编号
	 * schemeName    基准名称
	 * departNo      科室编号
	 * departName    科室名称
	 * managerNo     责任人编号
	 * managerName   责任人名称
	 * activeTime    有效时间
	 * smsSend       短信提醒
	 * timeoutReminderTime   超时提前提醒时间
	 * jobContent    作业说明
	 * @return 
	 * 保存成功，保存失败则执行回滚操作
	 * @return: EiInfo
	 */
	public EiInfo insertScheme(EiInfo eiInfo) {
        // 获取登录人和操作时间
        String loginName = UserSession.getLoginName();
        String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String operType = (String)eiInfo.get("operType");
        // 基准基础数据
        Map<String, Object> schemeMap = new HashMap<>();
        String viewSchemeID = (String)eiInfo.get("schemeID");// 前端拿到的schemeID
        String viewSchemeCode = (String)eiInfo.get("schemeCode");// 前端拿到的schemeCode
        String schemeName = (String)eiInfo.get("schemeName");
        String departNo = (String)eiInfo.get("departNo");
        String departName = (String)eiInfo.get("departName");
        String managerNo = (String)eiInfo.get("managerNo");
        String managerName = (String)eiInfo.get("managerName");
        String activeTime = (String)eiInfo.get("activeTime");
        String smsSend = (String)eiInfo.get("smsSend");
        String timeoutReminderTime = (String)eiInfo.get("timeoutReminderTime");
        String jobContent = (String)eiInfo.get("jobContent");
        schemeMap.put("schemeName", schemeName);
        schemeMap.put("departNo", departNo);
        schemeMap.put("departName", departName);
        schemeMap.put("managerNo", managerNo);
        schemeMap.put("managerName", managerName);
        schemeMap.put("activeTime", activeTime);
        schemeMap.put("smsSend", smsSend);
        schemeMap.put("timeoutReminderTime", timeoutReminderTime);
        schemeMap.put("jobContent", jobContent);

        logger.info("设备巡检基准【DIJZ0101】操作类型operType==", operType);
        /*如果是更新操作，则先删除原来数据，再重新添加基准及基准周期、关联设备、巡查项目、执行人列表等*/
        if ("update".equals(operType)) {
            dao.delete("DIJZ01.deleteOneScheme", viewSchemeID);
            dao.delete("DIJZ01.deleteCycle", viewSchemeID);// 删除执行周期
            dao.delete("DIJZ01.deleteItem", viewSchemeID);// 删除基准项目
            dao.delete("DIJZ01.deleteExman", viewSchemeID);// 删除执行人
        }

        // 取出设备
        List<Map<String, Object>> deviceList = (List<Map<String, Object>>)eiInfo.get("deviceList");
        // 取出项目
        List<Map<String, Object>> itemList = (List<Map<String, Object>>)eiInfo.get("itemList");
        // 取出执行人
        List<Map<String, Object>> exmanList = (List<Map<String, Object>>)eiInfo.get("exmanList");
        // 取出周期
        List<Map<String, Object>> cycleList = (List<Map<String, Object>>)eiInfo.get("cycleList");

        // 校验参数
        if (deviceList.isEmpty()) {
            eiInfo.setMsg("未添加设备信息");
            eiInfo.setStatus(-1);
            return eiInfo;
        }
        if (cycleList.isEmpty()) {
            eiInfo.setMsg("未添加周期信息");
            eiInfo.setStatus(-1);
            return eiInfo;
        }
        if (itemList.isEmpty()) {
            eiInfo.setMsg("未添加项目信息");
            eiInfo.setStatus(-1);
            return eiInfo;
        }
        if (exmanList.isEmpty()) {
            eiInfo.setMsg("未添加执行人信息");
            eiInfo.setStatus(-1);
            return eiInfo;
        }
        String schemenName = (String)schemeMap.get("schemeName");
        // 循环设备插入基准
        deviceList.forEach(device -> {
            String schemeID;
            String schemeCode;
            String machineID = (String)device.get("machineId");
            String machineName = (String)device.get("machineName");
            String packetId = (String)device.get("packetId");

            // 生成巡检的id 和 code
            if ("update".equals(operType)) {
                schemeID = viewSchemeID;
                schemeCode = viewSchemeCode;
            } else {
                schemeID = UUID.randomUUID().toString().replace("-", "");
                schemeCode = DeviceGeneCode.geneCode(DeviceBillType.DI_SCHEME);
            }

            if (!schemenName.contains("--")) {
                schemeMap.put("schemeName", machineName + "--" + schemenName);
            } else {
                schemeMap.put("schemeName", schemenName);
            }
            schemeMap.put("machineID", machineID);
            schemeMap.put("schemeID", schemeID);
            schemeMap.put("schemeCode", schemeCode);
            schemeMap.put("createMan", loginName);
            schemeMap.put("createTime", operTime);
            schemeMap.put("packetId", packetId);
            schemeMap.put("status", "0");

            // 插入基准主项目
            dao.insert("DIJZ01.insertScheme", schemeMap);

            // 循环插入项目
            for (int index=0; index<itemList.size(); index++){
                Map<String, Object> insertItem = itemList.get(index);
                insertItem.put("itemID", UUID.randomUUID().toString());
                insertItem.put("schemeID", schemeID);
                insertItem.put("itemIDxmID", insertItem.get("itemId"));
                insertItem.put("cardItemID", "");
                insertItem.put("itemName", insertItem.get("content"));
                insertItem.put("itemDesc", insertItem.get("projectDesc"));
                insertItem.put("needPhoto", "N");
                insertItem.put("sortNo", index+1);
                dao.insert("DIJZ01.insertItem", insertItem);
            }
            itemList.forEach(item -> {

            });

            // 循环插入周期
            cycleList.forEach(cycle -> {
                cycle.put("id", UUID.randomUUID().toString());
                cycle.put("schemeID", schemeID);
                cycle.put("createMan", loginName);
                cycle.put("createTime", operTime);
                //cycle.put("nextExecuteTime", operTime);
                cycle.put("nextExecuteTime", cycle.get("startTime"));
                dao.insert("DIJZ01.insertCycle", cycle);
            });

            // 循环插入执行人
            exmanList.forEach(exman -> {
                Map<String, Object> insertExman = new HashMap<>();
                insertExman.put("jobID", UUID.randomUUID().toString());
                insertExman.put("schemeID", schemeID);
                insertExman.put("jobDepartNo", exman.get("deptNum"));
                insertExman.put("jobDepartName", exman.get("deptName"));
                insertExman.put("jobManNo", exman.get("workNo"));
                insertExman.put("jobManName", exman.get("name"));
                dao.insert("DIJZ01.insertExman", insertExman);
            });

        });
        EiInfo outInfo = new EiInfo();
        outInfo.setMsg("add".equals(operType) ? "新增基准成功" : "编辑基准成功");
        return outInfo;
    }
	
	
	
	/**
	 * 设备包信息
	 *
	 * @Title: device 
	 * @param eiInfo
	 * id   设备包id
	 * @return 
	 * packet_code  设备包编号
	 * packet_name  设备包名称
	 * @return: EiInfo
	 */
    public EiInfo device(EiInfo eiInfo) {
        String id = (String)eiInfo.get("id");
        List<Map<String, String>> query = dao.query("DIJZ0101.queryDevice", id);
        eiInfo.set("param", query);
        return eiInfo;
    }

}
