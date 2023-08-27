package com.baosight.wilp.dk.jz.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dk.common.domain.DeviceBillType;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;
import com.baosight.wilp.dk.common.util.DeviceGeneCode;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 保养新增、编辑功能：跳转新增或编辑页面、查询科室、查询人员、保存基准、设备包信息
 * <p>1.跳转新增或编辑页面  initLoad
 * <p>2.查询科室   queryDept
 * <p>3.查询人员   queryPerson
 * <p>4.保存基准   insertScheme
 * <p>5.设备包信息   device
 * 
 * @Title: ServiceDKJZ0101.java
 * @ClassName: ServiceDKJZ0101
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午2:46:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKJZ0101 extends ServiceBase{

	protected static final Logger logger = LoggerFactory.getLogger(ServiceDKJZ0101.class);
    
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
		String operType = (String) inInfo.getAttr().get("operType");
		//1.根据操作类型operType判断是跳转新增页面还的编辑页面
		if (operType.equals("add")) {
			return super.initLoad(inInfo);
		} else {
			String id = (String) inInfo.getAttr().get("id");
			Map<String, String> param = new HashMap<>();
			param.put("schemeID", id);
			//2.查询基准
			List<Map<String, Object>> schemeInfo = dao.query("DKJZ01.queryScheme", param);
			
			//3.查询周期
			List<Map<String, Object>> cycle = dao.query("DKJZ01.queryCycle", id);
			//4.查询项目
			List<Map<String, Object>> item = dao.query("DKJZ01.queryItem", id);
			//5.for循环赋值
			item.forEach(i -> {
				i.put("itemId", i.get("itemID"));
				i.put("content", i.get("itemName"));
				i.put("projectDesc", i.get("itemDesc"));
			});
			//6.查询执行人员
			List<Map<String, Object>> exman = dao.query("DKJZ01.queryExman", id);
			//7.for循环赋值
			item.forEach(m -> {
				m.put("id", m.get("jobID"));
			});
			//8.将基准管理人、管理科室、操作类型重新赋值
			EiInfo outInfo = new EiInfo();
			Map<String, Object> map = schemeInfo.get(0);
			//9.基准管理人员
		    map.put("schemeMan", map.get("managerNo"));
		    //10.基准管理科室
            map.put("schemeDept", map.get("departNo"));
            //11.操作类型
			map.put("operType", "update");
			logger.info("基准对象map：", JSONObject.toJSONString(map));
			outInfo.setAttr(map);
			//12.获取设备id
			String machineId = (String)map.get("machineID");
			//13.查询设备信息
			List<Map<String, Object>> device = dao.query("DKJZ01.queryDevice", machineId);
			//14.info里添加设备数据集
			outInfo.addRows("device", device);
			//15.info里添加周期数据集
			outInfo.addRows("cycle", cycle);
			//16.1info里添加项目数据集
			outInfo.addRows("item", item);
			//17.info里添加执行人数据集
			outInfo.addRows("exman", exman);
			//18.返回基准所包含的所有信息
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
        String operType = (String) inInfo.getAttr().get("operType");
        //1.根据操作类型operType判断是跳转新增页面还的编辑页面
        if (operType.equals("add")) {
            return super.initLoad(inInfo);
        } else {
            String id = (String) inInfo.getAttr().get("id");
            Map<String, String> param = new HashMap<>();
            param.put("schemeID", id);
            //2.查询基准
            List<Map<String, Object>> schemeInfo = dao.query("DKJZ01.queryScheme", param);
            
            //3.查询周期
            List<Map<String, Object>> cycle = dao.query("DKJZ01.queryCycle", id);
            //4.查询项目
            List<Map<String, Object>> item = dao.query("DKJZ01.queryItem", id);
            //5.for热循环添加项目id和项目名等数据
            item.forEach(i -> {
                i.put("itemId", i.get("itemID"));
                i.put("content", i.get("itemName"));
                i.put("projectDesc", i.get("itemDesc"));
            });
            //6.查询执行人员
            List<Map<String, Object>> exman = dao.query("DKJZ01.queryExman", id);
            //7.for热循环添加执行人员id
            item.forEach(m -> {
                m.put("id", m.get("jobID"));
            });
            //8.将基准管理人、管理科室、操作类型重新赋值
            EiInfo outInfo = new EiInfo();
            Map<String, Object> map = schemeInfo.get(0);
            //9.赋值基准管理人
            map.put("schemeMan", map.get("managerNo"));
            //10.赋值添加管理科室
            map.put("schemeDept", map.get("departNo"));
            map.put("operType", "update");
            logger.info("基准对象map：", JSONObject.toJSONString(map));
            outInfo.setAttr(map);
            //11.获取设备id
            String machineId = (String)map.get("machineID");
            //12.查询设备信息
            List<Map<String, Object>> device = dao.query("DKJZ01.queryDevice", machineId);
            //13.info添加设备数据集
            outInfo.addRows("device", device);
            //14.info添加周期数据集
            outInfo.addRows("cycle", cycle);
            //15.info添加项目数据集
            outInfo.addRows("item", item);
            //16.info添加执行人员数据集
            outInfo.addRows("exman", exman);
            //17.返回基准所包含的所有信息
            return outInfo;
        }
    }
	
    /**
     * 查询科室
     * <p>获取页面传来的科室参数
     * <p>将参数带入数据接口方法获取科室数据
     * <p>获取科室数据并判断不为空
     * <p>获取返回数据条数
     *
     * @Title: queryDept 
     * @param eiInfo
     * @return 
     * deptName   科室名称
     * deptNum    科室编号
     * @return: EiInfo
     */
	public EiInfo queryDept(EiInfo eiInfo) {
	    //1.获取页面传来的科室参数
	    Map<String, Object> map = CommonUtils.buildParamter(eiInfo, "inqu_status", "dept");
	       // return BaseDockingUtils.getDeptAllPage(map, "dept");
	        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	        int count=0;
	        //2.将参数带入数据接口方法获取科室数据
	        EiInfo outInfo=BaseDockingUtils.getDeptAllPage(map, "dept");
	        //3.获取科室数据并判断不为空
	        if(outInfo.getBlock("dept")!=null) {
	          list=outInfo.getBlock("dept").getRows();
	          if(!list.isEmpty()) {
	          //4.获取返回数据条数
	          count=(int)outInfo.getBlock("dept").getAttr().get("count");
	          }
	             return DeviceEiUtil.buildResult(eiInfo, list, count, "dept");
	        }else {
	            //5.返回数据集到页面
	             return DeviceEiUtil.buildResult(eiInfo, list, count, "dept");
	        }
	}
	
	/**
     * 查询人员
     * <p>获取页面传来的人员参数
     * <p>判断是否为人员blockid,不是则不执行下列操作
     * <p>获取参数并存到map里
     * <p>执行数据接口方法获取人员信息
     * <p>判断人员不为空并获取人员信息总数
     *
     * @Title: queryPerson 
     * @param eiInfo
     * @return 
     * workNo  人员编号
     * name    人员名称
     * @return: EiInfo
     */
	public EiInfo queryMan(EiInfo eiInfo) {
	  //1.获取页面传来的人员参数
	    Map<String, Object> map = CommonUtils.buildParamter(eiInfo, "inqu_status", "person");
        String blockId = eiInfo.getBlockIds();
        //2.判断是否为人员blockid,不是则不执行下列操作
        if(blockId.equals("inqu_status,person")) {
            EiInfo outinfo = new EiInfo();
            EiBlock block = new EiBlock("person");
            //3.获取参数并存到map里
            List<Map<String, Object>> listMap = eiInfo.getBlock("inqu_status").getRows();
            String name = (String) listMap.get(0).get("name");
            map.put("userName", name);
         //   return BaseDockingUtils.getStaffAllPage(map,"person");
            List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
            int count=0;
            //4.执行数据接口方法获取人员信息
            EiInfo outInfo=BaseDockingUtils.getStaffAllPage(map, "person");
            //5.判断人员不为空并获取人员信息总数
            if(outInfo.getBlock("person")!=null) {
              list=outInfo.getBlock("person").getRows();
              //6.集合不为空判断
              if(!list.isEmpty()) {
              //7.给count赋值
              count=(int)outInfo.getBlock("person").getAttr().get("count");
              }
                 return DeviceEiUtil.buildResult(eiInfo, list, count, "person");
            }else {
                //8.返回结果集到页面
                 return DeviceEiUtil.buildResult(eiInfo, list, count, "person");
            }
        }
       // return BaseDockingUtils.getStaffAllPage(map, "person");
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        int count=0;
       //9.执行数据接口方法获取人员信息
        EiInfo outInfo=BaseDockingUtils.getStaffAllPage(map, "person");
       //10.判断人员不为空并获取人员信息总数
        if(outInfo.getBlock("person")!=null) {
          list=outInfo.getBlock("person").getRows();
        //11.集合不为空判断
          if(!list.isEmpty()) {
              //12.给count赋值
              count=(int)outInfo.getBlock("person").getAttr().get("count");
              }
          //13.返回结果集到页面
             return DeviceEiUtil.buildResult(eiInfo, list, count, "person");
        }else {
             return DeviceEiUtil.buildResult(eiInfo, list, count, "person");
        }
	}
	
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
		//1.获取登录人和操作时间
		String loginName = UserSession.getLoginName();
		String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String operType = (String)eiInfo.get("operType");
		
		//2.基准基础数据
		Map<String, Object> schemeMap = new HashMap<>();
        String viewSchemeID = (String)eiInfo.get("schemeID");// 3.前端拿到的schemeID
        String viewSchemeCode = (String)eiInfo.get("schemeCode");// 4.前端拿到的基准编码
        String schemeName = (String)eiInfo.get("schemeName");// 5.前端拿到的基准名称
        String departNo = (String)eiInfo.get("departNo");// 6.前端拿到的s科室编号
        String departName = (String)eiInfo.get("departName");// 7.前端拿到的科室名称
        String managerNo = (String)eiInfo.get("managerNo");// 8.前端拿到的人员编号
        String managerName = (String)eiInfo.get("managerName");// 9.前端拿到的人员名称
        String activeTime = (String)eiInfo.get("activeTime");// 10.前端拿到的有效时间
        String smsSend = (String)eiInfo.get("smsSend");// 11.前端拿到的smsSend
        String timeoutReminderTime = (String)eiInfo.get("timeoutReminderTime");// 12.前端拿到的超时时间
        String jobContent = (String)eiInfo.get("jobContent");// 13.前端拿到的jobContent
        //14.往schemeMap赋值基准名称
        schemeMap.put("schemeName", schemeName);
        //15.往schemeMap赋值科室编号
        schemeMap.put("departNo", departNo);
        //16.往schemeMap赋值科室名称
        schemeMap.put("departName", departName);
        //17.往schemeMap赋值管理人编号
        schemeMap.put("managerNo", managerNo);
        //18.往schemeMap赋值管理人名称
        schemeMap.put("managerName", managerName);
        //19.往schemeMap赋值有效时间
        schemeMap.put("activeTime", activeTime);
        //20.往schemeMap赋值基是否发送短信
        schemeMap.put("smsSend", smsSend);
        //21.往schemeMap赋值超时时间
        schemeMap.put("timeoutReminderTime", timeoutReminderTime);
        //22.往schemeMap赋值项目名
        schemeMap.put("jobContent", jobContent);
		
		logger.info("设备保养基准【DKJZ0101】操作类型operType==", operType);
		/*23.如果是更新操作，则先删除原来数据，再重新添加基准及基准周期、关联设备、巡查项目、执行人列表等*/
		if("update".equals(operType)){
			dao.delete("DKJZ01.deleteOneScheme", viewSchemeID);
			dao.delete("DKJZ01.deleteCycle", viewSchemeID);//24.删除执行周期
			dao.delete("DKJZ01.deleteItem", viewSchemeID);//25.删除基准项目
			dao.delete("DKJZ01.deleteExman", viewSchemeID);//26.删除执行人
		}
		
		//27.取出设备
		List<Map<String, Object>> deviceList = (List<Map<String, Object>>) eiInfo.get("deviceList");
		//28.取出项目
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) eiInfo.get("itemList");
		//29.取出执行人
		List<Map<String, Object>> exmanList = (List<Map<String, Object>>) eiInfo.get("exmanList");
		//30.取出周期
		List<Map<String, Object>> cycleList = (List<Map<String, Object>>) eiInfo.get("cycleList");
 		
		//31.校验参数设备信息为空
		if(deviceList.isEmpty()) {
		    eiInfo.setMsg("未添加设备信息");
		    eiInfo.setStatus(-1);
		    return eiInfo;
		}
		//32.校验参数周期信息为空
		if(cycleList.isEmpty()) {
			eiInfo.setMsg("未添加周期信息");
			eiInfo.setStatus(-1);
			return eiInfo;
		}
		//33.校验参数项目信息为空
		if(itemList.isEmpty()) {
			eiInfo.setMsg("未添加项目信息");
			eiInfo.setStatus(-1);
			return eiInfo;
		}
		//34.校验参数执行人为空
		if(exmanList.isEmpty()) {
			eiInfo.setMsg("未添加执行人信息");
			eiInfo.setStatus(-1);
			return eiInfo;
		}
		String schemenName = (String) schemeMap.get("schemeName");
		//35.循环设备插入基准
		deviceList.forEach(device -> {
			String schemeID;
			String schemeCode;
			//36.获取设id
			String machineID = (String) device.get("machineId");
			//37.获取设备名
			String machineName = (String) device.get("machineName");
			//38.获取设备包id
			String packetId = (String) device.get("packetId");
			
			//39.生成保养的id  和  code
			if("update".equals(operType)){
				schemeID = viewSchemeID;
				schemeCode = viewSchemeCode;
			}else {
			    //40.基准id赋值uuid
				schemeID = UUID.randomUUID().toString().replace("-", "");
				//41.获取基准编码
				schemeCode = DeviceGeneCode.geneCode(DeviceBillType.DK_SCHEME);
			}
			//42.基准名称没有‘--’则拼接设备名--基准名称 
			if(!schemenName.contains("--")){
				schemeMap.put("schemeName",machineName+"--"+schemenName);
			}else {
				schemeMap.put("schemeName",schemenName);
			}
			//43.往schemeMap赋值设备id
			schemeMap.put("machineID", machineID);
			//44.往schemeMap赋值基准id
			schemeMap.put("schemeID", schemeID);
			//45.往schemeMap赋值基准编码
			schemeMap.put("schemeCode", schemeCode);
			//46.往schemeMap赋值创建人
			schemeMap.put("createMan", loginName);
			//47.往schemeMap赋值创建时间
			schemeMap.put("createTime", operTime);
			//48.往schemeMap赋值设备包id
			schemeMap.put("packetId", packetId);
			//49.往schemeMap赋值基准状态
			schemeMap.put("status", "0");
			
			//50.插入基准主项目
			dao.insert("DKJZ01.insertScheme", schemeMap);
			
			//51.循环插入项目
			itemList.forEach(item -> {
			    Map<String, Object> insertItem = new HashMap<>();
			     //52.往insertItem赋值项目id为uuid
				 insertItem.put("itemID", UUID.randomUUID().toString());
				 insertItem.put("schemeID", schemeID);
				 insertItem.put("itemIDxmID", item.get("itemId"));
				 //53.往insertItem赋值项目卡片id
				 insertItem.put("cardItemID", "");
				 insertItem.put("itemName", item.get("content"));
				 //54.往insertItem赋值项目描述
				 insertItem.put("itemDesc", item.get("projectDesc"));
				 insertItem.put("referenceValue", item.get("referenceValue"));
				 //55.往insertItem赋值项目巡检值
				 insertItem.put("actualValueUnit", item.get("actualValueUnit"));
				 insertItem.put("needPhoto", "N");
				 //56.新增基准鲜项目
				 dao.insert("DKJZ01.insertItem", insertItem);
			});
			
			//57.循环插入周期
			cycleList.forEach(cycle -> {
			    //58.往cycle里赋值周期id为uuid
				cycle.put("id", UUID.randomUUID().toString());
				//59.往cycle里赋值基准id
				cycle.put("schemeID", schemeID);
				//60.往cycle里赋值创建人
				cycle.put("createMan", loginName);
				//61.往cycle里赋值创建时间
				cycle.put("createTime", operTime);
				//62.cycle.put("nextExecuteTime", operTime);
				cycle.put("nextExecuteTime", cycle.get("startTime"));
				//63.执行新增基准周期
				dao.insert("DKJZ01.insertCycle", cycle);
			});
			
			//64.循环插入执行人
			exmanList.forEach(exman -> {
			    Map<String, Object> insertExman = new HashMap<>();
			    //65.往insertExman里赋值执行人id为uuid
                insertExman.put("jobID", UUID.randomUUID().toString());
                //66.往insertExman里赋值基准id
                insertExman.put("schemeID", schemeID);
                //67.往insertExman里赋值执行人部门工号
                insertExman.put("jobDepartNo", exman.get("deptNum"));
                //68.往insertExman里赋值执行人部门名称
                insertExman.put("jobDepartName", exman.get("deptName"));
                //69.往insertExman里赋值执行人工号
                insertExman.put("jobManNo", exman.get("workNo"));
                insertExman.put("jobManName", exman.get("name"));
                //70.执行新增基准执行人
				dao.insert("DKJZ01.insertExman", insertExman);
			});
			
		});
		EiInfo outInfo = new EiInfo();
		//71.返回新增成功或编辑成功
		outInfo.setMsg("add".equals(operType)?"新增基准成功":"编辑基准成功");
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
        //1.获取设备包信息
        List<Map<String,String>> query = dao.query("DKJZ0101.queryDevice", id);
        //2.将设备包信息赋值到info里
        eiInfo.set("param", query);
        return eiInfo;
    }
	
}
