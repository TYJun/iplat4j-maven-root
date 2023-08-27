package com.baosight.wilp.di.yc.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.zh.domain.DIZH0101;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 巡检综合详情类：初始化详情
 * <p>初始化详情 initLoad
 * 
 * @Title: ServiceDIZH0101.java
 * @ClassName: ServiceDIZH0101
 * @Package：com.baosight.wilp.di.zh.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午2:55:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class ServiceDIYC0102 extends ServiceBase{
	
    

    /**
     * 图片保存路径
     */
    public static final String PIC_PATH = "../upload/device/";
    
    
    
    /**
     * 初始化详情
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>调试获取分页--结束
     * <p>查询基准
     * <p>查询项目,循环修改字段名称
     * <p>调试获取分页 --结束
     * <p>查询执行人员,循环修改字段名称
     * <p>查询图片信息
     * <p>获取result4模块展示整改前图片
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
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo info) {
		
		String taskCode = (String) info.getAttr().get("taskCode");
		String taskID = (String) info.getAttr().get("taskID");
		String schemeID = (String) info.getAttr().get("schemeID");
		Map<String, Object> param = new HashMap<>();
		param.put("taskCode", taskCode);
		param.put("taskID", taskID);
		param.put("schemeID", schemeID);
		//调试获取分页
		EiBlock block = info.getBlock("item");
		Integer offset = 0;
		Integer limit = 10;
		if (block != null) {
			Map blockMap = block.getAttr();
			Integer offset1 = (Integer) blockMap.get("offset");
			Integer limit1 = (Integer) blockMap.get("limit");	
			if (offset1 != null && limit1 != null) {
				offset = offset1;
				limit = limit1;
			}
		}
		param.put("offset", offset);
		param.put("limit", limit);
		//结束
		
		//查询基准
		List<Map<String, Object>> schemeInfo = dao.query("DIYC02.query", param);
		
		
		//查询项目,循环修改字段名称
		List<Map<String, Object>> item = dao.query("DIYC02.queryItem", param);
		int itemCount=dao.count("DIYC02.queryItemCount", param);
		item.forEach(m -> {
			m.put("taskCode", taskCode);
			m.put("taskID", taskID);
			m.put("schemeID", schemeID);
		});
			
		//调试获取分页 
			EiInfo outInfo = new EiInfo();
			EiBlock result = info.getBlock("item");
			if(result != null && !result.getAttr().isEmpty()){
				result.setRows(item);
				result.getAttr().put("count", itemCount);
				outInfo.addBlock(result);
			} else {
				EiBlock resultBlock = new EiBlock("item");
				
				EiBlockMeta eiBlockMeta = new EiBlockMeta();
				Map<String, Object> map1;
				if (item.size() > 0) {
					map1 = (Map<String, Object>) item.get(0);
				} else {
					map1 = new HashMap<>();
				}
				if(!map1.isEmpty()){
					for(String key : map1.keySet()){
						eiBlockMeta.addMeta(new EiColumn(key));
					}
				}
				
				resultBlock.setBlockMeta(eiBlockMeta);
				resultBlock.addRows(item);
				Map<String,Object> rAttr = new HashMap<>();
				rAttr.put("count", itemCount);
				rAttr.put("offset", 0);
				rAttr.put("limit", 10);
				resultBlock.setAttr(rAttr);
				outInfo.addBlock(resultBlock);
			}
		//结束
		
		//查询执行人员,循环修改字段名称
		List<Map<String, Object>> exman = dao.query("DIYC02.queryExman", param);
		exman.forEach(m -> {
			m.put("id", m.get("jobID"));
		});
		
		Map<String, Object> map = schemeInfo.get(0);
		outInfo.setAttr(map);
		outInfo.addRows("exman", exman);
		
		
		//查询图片信息
        List<DIZH0101> queryPic = dao.query("DIYC02.queryPic", taskID);
		
		
		
		if(null != queryPic) {
            for (DIZH0101 dizh0101 : queryPic) {
                String filename = dizh0101.getFilename();
                String replace = filename.replace(" ", "");
                dizh0101.setPathName(PIC_PATH + replace);
            }
        }
		
		//获取result4模块展示整改前图片
        EiBlock addBlock2 = outInfo.addBlock("result4");
        addBlock2.addBlockMeta(new DIZH0101().eiMetadata);
        outInfo.getBlock("result4").setRows(queryPic);
		
		
		
		return outInfo;
    }

}
