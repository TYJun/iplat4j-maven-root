package com.baosight.wilp.dk.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.zh.domain.DKZH0101;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 保养综合详情类：初始化详情、查询方法
 * <p>初始化详情 initLoad
 * <p>查询方法     query
 * 
 * @Title: ServiceDKZH0101.java
 * @ClassName: ServiceDKZH0101
 * @Package：com.baosight.wilp.dk.zh.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午2:55:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKZH0101 extends ServiceBase{

	/**
     * 图片保存路径
     */
    public static final String PIC_PATH = "../upload/deviceDK/";
    
    /**
     * 初始化详情
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.获取参数并将参数分装到MAP
     * <p>2.调试获取分页--结束
     * <p>3.查询基准
     * <p>4.查询项目,循环修改字段名称
     * <p>5.将获取到的项目添加到 EiInfo里
     * <p>6.调试获取分页 --结束
     * <p>7.查询执行人员,循环修改字段名称
     * <p>8.将查询到的基准添加到EiInfo里
     * <p>9.将查询到的执行人员添加到EiInfo里
     * <p>10.查询图片信息
     * <p>11.获取result4模块展示整改前图片
     * @param info
     * taskCode      任务编号
     * taskID        任务id
     * schemeID      基准id
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
       id        id
       workNo    执行人编号
       schemeID  基准id
       deptName  执行部门
       deptNo    执行部门编号
       name      执行人
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
		//1.获取参数并将参数分装到MAP
		String taskCode = (String) info.getAttr().get("taskCode");
		//2.获取任务id
		String taskID = (String) info.getAttr().get("taskID");
		//3.获取基准id
		String schemeID = (String) info.getAttr().get("schemeID");
		Map<String, Object> param = new HashMap<>();
		//4.将任务编码保存到param里
		param.put("taskCode", taskCode);
		//5.将任务id保存到param里
		param.put("taskID", taskID);
		//6.将基准id保存到param里
		param.put("schemeID", schemeID);
		//7.调试
		EiBlock block = info.getBlock("item");
		Integer offset = 0;
		Integer limit = 10;
		//8.block不为空
		if (block != null) {
			Map blockMap = block.getAttr();
			Integer offset1 = (Integer) blockMap.get("offset");
			Integer limit1 = (Integer) blockMap.get("limit");	
			//9.偏移量不为空
			if (offset1 != null && limit1 != null) {
				offset = offset1;
				limit = limit1;
			}
		}
		param.put("offset", offset);
		param.put("limit", limit);
		//10.结束
		
		//11.查询基准
		List<Map<String, Object>> schemeInfo = dao.query("DKZH01.query", param);
		
		
		//12.查询项目,循环修改字段名称
			List<Map<String, Object>> item = dao.query("DKZH01.queryItem", param);
			int itemCount=dao.count("DKZH01.queryItemCount", param);
			//13.循环赋值
			item.forEach(m -> {
				m.put("taskCode", taskCode);
				m.put("taskID", taskID);
				m.put("schemeID", schemeID);
			});
			
		//14.将获取到的项目添加到 EiInfo里
			EiInfo outInfo = new EiInfo();
			EiBlock result = info.getBlock("item");
			//15.itemBlock不为空
			if(result != null && !result.getAttr().isEmpty()){
				result.setRows(item);
				//16.将分页总数赋值
				result.getAttr().put("count", itemCount);
				outInfo.addBlock(result);
			} else {
			    //17.获取项目block
				EiBlock resultBlock = new EiBlock("item");
				
				EiBlockMeta eiBlockMeta = new EiBlockMeta();
				Map<String, Object> map1;
				//18.获取block数据
				if (item.size() > 0) {
					map1 = (Map<String, Object>) item.get(0);
				} else {
					map1 = new HashMap<>();
				}
				//19.获取的数据不为空
				if(!map1.isEmpty()){
					for(String key : map1.keySet()){
						eiBlockMeta.addMeta(new EiColumn(key));
					}
				}
				//20.添加元数据和数据集以及分页条数
				resultBlock.setBlockMeta(eiBlockMeta);
				resultBlock.addRows(item);
				Map<String,Object> rAttr = new HashMap<>();
				//21.赋值分页总数
				rAttr.put("count", itemCount);
				rAttr.put("offset", 0);
				rAttr.put("limit", 10);
				resultBlock.setAttr(rAttr);
				outInfo.addBlock(resultBlock);
			}
		//22.结束
		
		//23.查询执行人员,循环修改字段名称
		List<Map<String, Object>> exman = dao.query("DKZH01.queryExman", param);
		exman.forEach(m -> {
			m.put("id", m.get("jobID"));
		});
		//24.将查询到的基准添加到EiInfo里
		Map<String, Object> map = schemeInfo.get(0);
		outInfo.setAttr(map);
		//25.将查询到的执行人员添加到EiInfo里
		outInfo.addRows("exman", exman);
		
		
		//26.查询图片信息
        List<DKZH0101> queryPic = dao.query("DKZH01.queryPic", taskID);
		
		
		//27.图片不为空
		if(null != queryPic) {
            for (DKZH0101 dizh0101 : queryPic) {
                //28.获取图片名称
                String filename = dizh0101.getFilename();
                String replace = filename.replace(" ", "");
                dizh0101.setPathName(PIC_PATH + replace);
            }
        }
		
		//29.获取result4模块展示整改前图片
        EiBlock addBlock2 = outInfo.addBlock("result4");
        addBlock2.addBlockMeta(new DKZH0101().eiMetadata);
        outInfo.getBlock("result4").setRows(queryPic);
		
		
		
		return outInfo;
    }

	
	
	
	
	/**
	 * 
	 * 查询方法
	 * <p>1.查询基准
     * <p>2.查询项目,循环修改字段名称
     * <p>3.将获取到的项目添加到 EiInfo里
     * <p>4.调试获取分页 --结束
	 * <p>5.查询执行人员
	 *@param info
     * taskCode      任务编号
     * taskID        任务id
     * schemeID      基准id
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
       id        id
       workNo    执行人编号
       schemeID  基准id
       deptName  执行部门
       deptNo    执行部门编号
       name      执行人
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
	public EiInfo query(EiInfo info) {
		//1.获取参数
		String taskCode = (String) info.getAttr().get("taskCode");
		//2.获取任务id
		String taskID = (String) info.getAttr().get("taskID");
		//3.获取基准id
		String schemeID = (String) info.getAttr().get("schemeID");
		Map<String, Object> param = new HashMap<>();
		
		
		//4.调试
		EiBlock block = info.getBlock("item");
		Integer offset = 0;
		Integer limit = 10;
		//5.block不为空
		if (block != null) {
			Map blockMap = block.getAttr();
			Integer offset1 = (Integer) blockMap.get("offset");
			Integer limit1 = (Integer) blockMap.get("limit");
			Integer count = (Integer) blockMap.get("count");
			//6.偏移量不为空
			if (offset1 != null && limit1 != null) {
				offset = offset1;
				limit = limit1;
			}
			//7.判断数量不能小于偏移量和不=0
			if(count<=offset1 && count!=0) {
				offset=count-limit1;
			}
		}
		//8.map赋值taskCode，taskID
		param.put("taskCode", taskCode);
		//9.将任务id保存到param里
		param.put("taskID", taskID);
		//10.将基准id保存到param里
		param.put("schemeID", schemeID);
		//11.将偏移量保存到param里
		param.put("offset", offset);
		param.put("limit", limit);
		//12.结束
		
		//13.查询基准
		List<Map<String, Object>> schemeInfo = dao.query("DIZH01.query", param);
		
		//14.查询项目
			List<Map<String, Object>> item = dao.query("DIZH01.queryItem", param);
			int itemCount=dao.count("DIZH01.queryItemCount", param);
			//15.循环赋值
			item.forEach(m -> {
			    //16.赋值任务编码
				m.put("taskCode", taskCode);
				//17.赋值任务id
				m.put("taskID", taskID);
				//18.赋值基准id
				m.put("schemeID", schemeID);
			});
			
		//19.调试
			EiInfo outInfo = new EiInfo();
			EiBlock result = info.getBlock("item");
			//20.itemBlock不为空直接赋值
			if(result != null && !result.getAttr().isEmpty()){
				result.setRows(item);
				result.getAttr().put("count", itemCount);
				outInfo.addBlock(result);
				//21.itemBlock不空重新创建赋值
			} else {
				EiBlock resultBlock = new EiBlock("item");
				
				EiBlockMeta eiBlockMeta = new EiBlockMeta();
				Map<String, Object> map1;
				//22.创建源数据
				if (item.size() > 0) {
					map1 = (Map<String, Object>) item.get(0);
				} else {
					map1 = new HashMap<>();
				}
				//23.不为空判断
				if(!map1.isEmpty()){
					for(String key : map1.keySet()){
						eiBlockMeta.addMeta(new EiColumn(key));
					}
				}
				//24.新增元数据和分页信息以及item数据
				resultBlock.setBlockMeta(eiBlockMeta);
				resultBlock.addRows(item);
				Map<String,Object> rAttr = new HashMap<>();
				rAttr.put("count", itemCount);
				rAttr.put("offset", 0);
				rAttr.put("limit", 10);
				resultBlock.setAttr(rAttr);
				outInfo.addBlock(resultBlock);
			}
		//25.结束
		
		//26.查询执行人员
		List<Map<String, Object>> exman = dao.query("DIZH01.queryExman", param);
		exman.forEach(m -> {
			m.put("id", m.get("jobID"));
			m.put("manId", m.get("jobManID"));
			m.put("deptId", m.get("jobDepartID"));
		});
		//27.新增执行人员数据
		Map<String, Object> map = schemeInfo.get(0);
		outInfo.setAttr(map);
		//28.返回执行人
		outInfo.addRows("exman", exman);
		return outInfo;
    }
	
}
