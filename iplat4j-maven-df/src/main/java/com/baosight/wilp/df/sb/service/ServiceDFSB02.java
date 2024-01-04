package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 特种设备检验页面
 * <p>1.页面查询方法</p>
 * <p>2.查询设备信息</p>
 * <p>3.删除工单</p>
 * <p>4.提交工单</p>
 * <p>5.审核工单</p>
 * @Title: ServiceDFSB02.java
 * @ClassName: ServiceDFSB02
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年8月16日 下午4:54:56
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 */
public class ServiceDFSB02 extends ServiceBase {
	/**
	 * <p>1.调用本地查询方法</p>
	 * @Title: initLoad
	 * @Description:页面初始化
	 * @param： info
	 * @return：
	 * id：      		 检校计划主键ID
	 * taskNo：			 工单号
	 * machineId：		 设备id
	 * machineCode：	 检验设备编码
	 * machineName：	 检验设备名称
	 * innerMachineCode： 内部设备编码
	 * checkType：		 检校类别编码
	 * thisCheckDate：   本次检验日
	 * thisFinishDate：  本次检验完工日
	 * nextCheckDate：   下次检验日
	 * statusCode：		  工单状态(0=新建，-1=待审核，1=已审核)
	 * recCreator：		 创建人
	 * recCreateTime：   创建时间
	 * recRevisor：	    修改人
	 * recReviseTime:	修改时间
	 * dataGroupCode:   账套
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		return query(info);
	}
	/**
	 * 查询页面方法
	 * @Title: query
	 * @Description: 查询页面方法
	 * @param: info  find
	 * taskNo：				工单号
	 * machineName：		设备名称
	 * machineCode：		设备编码
	 * thisExpiredDateS：	查询时间起
	 * thisExpiredDateE：	查询时间止
	 * statusCode：			工单状态
	 * @return：
	 * id：      		 检校计划主键ID
	 * taskNo：			 工单号
	 * machineId：		 设备id
	 * machineCode：	 检验设备编码
	 * machineName：	 检验设备名称
	 * innerMachineCode： 内部设备编码
	 * checkType：		 检校类别编码
	 * thisCheckDate：   本次检验日
	 * thisFinishDate：  本次检验完工日
	 * nextCheckDate：   下次检验日
	 * statusCode：		  工单状态(0=新建，-1=待审核，1=已审核)
	 * recCreator：		 创建人
	 * recCreateTime：   创建时间
	 * recRevisor：	    修改人
	 * recReviseTime:	修改时间
	 * dataGroupCode:   账套
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo info) {
	    //获取数据
	    String[] find = {"taskNo","machineName","machineCode",
	        "thisCheckDateS","thisCheckDateE","statusCode",};
	    Map<String, Object> map = CommonUtils.buildParamter(info, "result",Arrays.asList(find));
	    //数据查询
	    List<Map<String, Object>> list = dao.query("DFSB02.query", map);
		//查询计数
	    Integer count = dao.count("DFSB02.count", map);
	    //返回
	    if(list==null || list.size() == 0){
	        return info;
	    }
	   //返回
		return CommonUtils.BuildOutEiInfo(info, null, null, list, count);

	}

	
	/**
	 * 
	 * 查询设备信息
	 * <p>1.查询设备信息</p>
	 * @Title: queryMachineId 
	 * @param ：inInfo queryMachineId
	 * machineId            设备id
	 * innerMachineCode：	设备内部编码
	 * machineCode:         设备编码
	 * machineName:         设备名称
	 * @return 
	 * @return: queryMachineId:设备分类id
	 * machineId            设备id
	 * innerMachineCode：	设备内部编码
	 * machineCode:         设备编码
	 * machineName:         设备名称
	 */
	    @SuppressWarnings("unchecked")
		public EiInfo queryMachineId(EiInfo inInfo) {
			//获取参数
			Map<String, Object> map = CommonUtils.buildParamter(inInfo,"inqu_status", "result");
			//数据查询
			List<Map<String, Object>> list = dao.query("DFSB02.queryMachineId", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
			//查询计数
			int count = dao.count("DFSB02.querycount", map);
			//返回
			if(list != null && list.size() > 0){
				//调用分页接口返回信息
				return CommonUtils.BuildOutEiInfo(inInfo, "result", null, list, count);
			} else {
			    //返回
				return inInfo; 
			}
		}
	    
	    /**
	     * 
	     * 查询通过设备分类id
	     * <p>1.通过设备id查询分类</p>
	     * @Title: queryByMachineId 
	     * @param inInfo
		 * machineId：设备id
	     * machineName：设备名称
	     * innerMachineCode:设备内部编码
	     * @return: EiInfo
	     */
	    @SuppressWarnings("unchecked")
		public EiInfo queryByMachineId(EiInfo inInfo) {
			//获取参数
			Map<String, Object> map = CommonUtils.buildParamter(inInfo,"inqu_status", "result");
			map.put("machineId", inInfo.get("machineId"));
			//数据查询
			List<Map<String, Object>> list = dao.query("DFDA02.queryMachineId", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
			inInfo.set("machineName", list.get(0).get("machineName"));
			inInfo.set("innerMachineCode", list.get(0).get("innerMachineCode"));
			int count = super.count("DFSB02.querycount", map);
			//返回
			if(list != null && list.size() > 0){
				return CommonUtils.BuildOutEiInfo(inInfo, "result", null, list, count);
			} else {
				return inInfo; 
			}
		}
	    /**
         * 删除工单
         * <p>1.通过id获取工单信息</p>
		 * <p>2.删除工单信息</p>
         * @Title: delete 
         * @param info list集合
         * id：id
         * @return: EiInfo
         */
		public EiInfo delete(EiInfo info) {
			List<String> list = (List<String>) info.get("List");
			/*Map map = new HashMap<>();
			map.put("id", info.get("List").toString());
			int count = dao.count("DFSB02.queryStatusCode",map );
	       	if(count==0) {
	       		info.setMsg("不能删除该工单");
	       		info.setStatus(-1);
	       		return info;
	       	}*/
			//删除工单
			dao.delete("DFSB02.delete",list);
			//返回
			return info;
		}
		
		/**
		 * 
		 * 工单提交
		 * <p>1.通过id获取工单信息</p>
		 * <p>2.将新建工单状态改为待审核</p>
		 * @Title: updateTiJiao 
		 * @param： info
		 * idList
		 * id：id
		 * @return: EiInfo
		 */
		public EiInfo updateTiJiao(EiInfo info) {
		    //获取集合
			List<String> idList = (List<String>)info.get("idList");
		/*	Map map = new HashMap<>();
			map.put("taskNo", info.getAttr().get("idList").toString());*/        
			//查询数据
			int rows = dao.update("DFSB02.tiJiao",idList);
			if(rows == 0){
					info.setMsg("提交失败");
					info.setStatus(-1);
		  }
			//返回
		  return info; 
		 }
		
		/**
		 * 
		 * 工单审核
		 * <p>1.通过id获取工单信息</p>
		 * <p>2.将待审核工单状态改为已经审核</p>
		 * @Title: updateShenHe 
		 * @param: info
		 * id：			主键
		 * @return: EiInfo
		 */
		 public EiInfo updateShenHe(EiInfo info) {
		     //获取集合
			 List<String> idList = (List<String>)info.get("idList");
			 //查询数据
			 int rows = dao.update("DFSB02.shenHe",idList);
			 if(rows == 0) {
				 	info.setMsg("更新失败");
		   			info.setStatus(-1);
		  }
			 //返回
		  return info; 
		 }
}
