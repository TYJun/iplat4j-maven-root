package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 *
 * 特种设备档案页面
 * <p>1.页面查询</p>
 * <p>2.分页</p>
 * <p>3.删除特种设备档案</p>
 * <p>4.启用特种设备</p>
 * <p>5.停用特种设备</p>
 * <p>6.调用人员信息接口</p>
 * <p>7.调用地点接口</p>
 * <p>8.调用科室接口</p>
 * @Title: ServiceDFSB01.java
 * @ClassName: ServiceDFSB01
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年8月13日 下午3:27:48
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 */

 public  class ServiceDFSB01 extends ServiceBase{
	/**
	 * 页面查询
	 * @param ：info
	 * id：					设备id
	 * machineCode：			设备编码
	 * machineName：			设备名称
	 * machineTypeId：		设备分类信息
	 * classifyName：			设备分类
	 * statusCode：			状态
	 * models：				规格型号
	 * fixedId：				安装地点ID
	 * fixedPlace:			安装地点
	 * innerMachineCode:		设备内部编码
	 * documentNo：			档案号
	 * workMedia：			工作介质
	 * useCertNo：			使用证编号
	 * useArea：				使用范围
	 * registerCode：			注册号码
	 * registerDate：			注册登记日期
	 * outFactoryDate：		出厂日期
	 * fixedTime：			安装时间
	 * useTime：				使用日期
	 * nonuseDate：			停用日期
	 * managerDeptId：		管理科室ID
	 * managerDeptName：		管理科室
	 * managerManId：			管理员ID
	 * managerManName：		管理员名称
	 * chargeUserId：			负责人ID
	 * chargeUserName：		负责人名称
	 * useDeaprtId：			使用科室ID
	 * useDeaprtName：		使用科室
	 * relatedDevice：		关联设备
	 * useFor：				用途
	 * memo：					备注
	 * needScan：				是否扫描二维码
	 * manufacturerName：		制造单位
	 * manufacturerCertno：	制造单位资格号
	 * fixedUnit：			安装单位
	 * fixedCertno：			安装单位资格号
	 * maintUnit：			维保单位
	 * maintCertno：			维保单位资格号
	 * checkUnit：			检查单位
	 * checkCertno：			检查单位资格号
	 * thisCheckDate：		本次检验日期
	 * thisFinishDate：		本次检验完工日期
	 * nextCheckDate：		下次检验日期
	 * annualInspCycle：		周期
	 * thisExpiredDate：		本次到检日期
	 * thisChexpiredDate：	本次到检完工时间
	 * nextExpiredDate：		下次检验时间
	 * regularInspCycle：		周期
	 * recCreator：			创建人
	 * recCreateTime：		创建时间
	 * recRevisor：			修改人
	 * recReviseTime：	   	修改时间
	 * dataGroupCode：        账套
	 * @return：
	 * id：					设备id
	 * machineCode：			设备编码
	 * machineName：			设备名称
	 * machineTypeId：		设备分类信息
	 * classifyName：			设备分类
	 * statusCode：			状态
	 * models：				规格型号
	 * fixedId：				安装地点ID
	 * fixedPlace:			安装地点
	 * innerMachineCode:		设备内部编码
	 * documentNo：			档案号
	 * workMedia：			工作介质
	 * useCertNo：			使用证编号
	 * useArea：				使用范围
	 * registerCode：			注册号码
	 * registerDate：			注册登记日期
	 * outFactoryDate：		出厂日期
	 * fixedTime：			安装时间
	 * useTime：				使用日期
	 * nonuseDate：			停用日期
	 * managerDeptId：		管理科室ID
	 * managerDeptName：		管理科室
	 * managerManId：			管理员ID
	 * managerManName：		管理员名称
	 * chargeUserId：			负责人ID
	 * chargeUserName：		负责人名称
	 * useDeaprtId：			使用科室ID
	 * useDeaprtName：		使用科室
	 * relatedDevice：		关联设备
	 * useFor：				用途
	 * memo：					备注
	 * needScan：				是否扫描二维码
	 * manufacturerName：		制造单位
	 * manufacturerCertno：	制造单位资格号
	 * fixedUnit：			安装单位
	 * fixedCertno：			安装单位资格号
	 * maintUnit：			维保单位
	 * maintCertno：			维保单位资格号
	 * checkUnit：			检查单位
	 * checkCertno：			检查单位资格号
	 * thisCheckDate：		本次检验日期
	 * thisFinishDate：		本次检验完工日期
	 * nextCheckDate：		下次检验日期
	 * annualInspCycle：		周期
	 * thisExpiredDate：		本次到检日期
	 * thisChexpiredDate：	本次到检完工时间
	 * nextExpiredDate：		下次检验时间
	 * regularInspCycle：		周期
	 * recCreator：			创建人
	 * recCreateTime：		创建时间
	 * recRevisor：			修改人
	 * recReviseTime：	   	修改时间
	 * dataGroupCode：        账套
	 */
	 public EiInfo initLoad(EiInfo info) {
		 return this.query(info);
	 }
      /**
       * 页面查询
       * @param :fieldList集合 包含：
       * machineCode：		特种设备编码
       * machineName：		特种设备名称
       * machineTypeId：	设备分类id
       * models：			规格
       * needScan：			是否扫描二维码
       * thisCheckDateS：	检查时间起
       * thisCheckDateE：	检查时间止
       * statusCode：		状态
       * @return：
	   * id：					设备id
	   * machineCode：			设备编码
	   * machineName：			设备名称
	   * machineTypeId：		设备分类信息
	   * classifyName：			设备分类
	   * statusCode：			状态
	   * models：				规格型号
	   * fixedId：				安装地点ID
	   * fixedPlace:			安装地点
	   * innerMachineCode:		设备内部编码
	   * documentNo：			档案号
	   * workMedia：			工作介质
	   * useCertNo：			使用证编号
	   * useArea：				使用范围
	   * registerCode：			注册号码
	   * registerDate：			注册登记日期
	   * outFactoryDate：		出厂日期
	   * fixedTime：			安装时间
	   * useTime：				使用日期
	   * nonuseDate：			停用日期
	   * managerDeptId：		管理科室ID
	   * managerDeptName：		管理科室
	   * managerManId：			管理员ID
	   * managerManName：		管理员名称
	   * chargeUserId：			负责人ID
	   * chargeUserName：		负责人名称
	   * useDeaprtId：			使用科室ID
	   * useDeaprtName：		使用科室
	   * relatedDevice：		关联设备
	   * useFor：				用途
	   * memo：					备注
	   * needScan：				是否扫描二维码
	   * manufacturerName：		制造单位
	   * manufacturerCertno：	制造单位资格号
	   * fixedUnit：			安装单位
	   * fixedCertno：			安装单位资格号
	   * maintUnit：			维保单位
	   * maintCertno：			维保单位资格号
	   * checkUnit：			检查单位
	   * checkCertno：			检查单位资格号
	   * thisCheckDate：		本次检验日期
	   * thisFinishDate：		本次检验完工日期
	   * nextCheckDate：		下次检验日期
	   * annualInspCycle：		周期
	   * thisExpiredDate：		本次到检日期
	   * thisChexpiredDate：	本次到检完工时间
	   * nextExpiredDate：		下次检验时间
	   * regularInspCycle：		周期
	   * recCreator：			创建人
	   * recCreateTime：		创建时间
	   * recRevisor：			修改人
	   * recReviseTime：	   	修改时间
	   * dataGroupCode：        账套
       * @see ServiceBase#query(EiInfo)
       */
	 public EiInfo query(EiInfo info) {
	     //获取参数
		 String[] fieldList = {"machineCode","machineName","classifyName","models","needScan",
				 "thisCheckDateS","thisCheckDateE","statusCode","registerDateS","registerDateE","regularInspCycle","thisCheckDate"};
		 //数据查询
		 Map<String, Object> map = CommonUtils.buildParamter(info,"result", Arrays.asList(fieldList));
		 List<Map<String, Object>> list = dao.query("DFSB01.query", map);
		 Integer count = dao.count("DFSB01.count", map);
		 //返回
		 if(list==null || list.size() == 0){
			 return info;
		 }
		 //返回
		 return CommonUtils.BuildOutEiInfo(info, null, null, list, count);

	 }

	 /**
	  * @Title: queryPerson
	  * <p>1.接口调用获取人员信息</p>
	  * @Description: 接口改造(人员)
	  * @param ：info
	  * userName : 用户名
	  * @return info
	  * workNo : 工号
	  * userName : 用户名
	  */
	    public EiInfo queryPerson(EiInfo info) {
	        // 调用分页接口构建map
	        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "person");
	        // 获取blockId
	        String blockId = info.getBlockIds();
	        // 如果blockId相等
	        if(blockId.equals("inqu_status,person")) {
	            // 实例化info
	            EiInfo outinfo = new EiInfo();
	            // 实例化block
	            EiBlock block = new EiBlock("person");
	            // 获取block中的数据的集合
	            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
	            // 获取集合中的数据
	            String name = (String) listMap.get(0).get("name");
	            // 设置userName
	            map.put("userName", name);
	            // 调用改造人员接口并返回
	            return BaseDockingUtils.getStaffAllPage(map,"person");
	        }
	        // 调用改造人员接口并返回
	        return BaseDockingUtils.getStaffAllPage(map, "person");
	    }

	    /**
	     * @Title: queryDept
		 * <p>1.调用远程服务获取科室信息</p>
	     * @Description: 接口改造(科室)
	     * @param info
	     * @return info
	     * deptNum : 科室编码
	     * deptName : 科室名称
	     */
	    public EiInfo queryDept(EiInfo info) {
	        // 调用分页接口构建map
	        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
	        // 调用远程服务获取改造科室接口
	        return BaseDockingUtils.getDeptAllPage(map, "dept");
	    }

	    /**
	     * @Title: querySpot
		 * <p>1.调用远程服务获取科室地点信息</p>
	     * @Description: 接口改造(地点)
	     * @param ：info
	     * spotName : 科室地点
	     * @return info
	     * spotName : 科室地点
	     */
	    public EiInfo querySpot(EiInfo info) {
	        // 实例化info
	        EiInfo outinfo = new EiInfo();
	        // 实例化block
	        EiBlock block = new EiBlock("spot");
	        // 调用分页接口，构建map
	        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
	        // 获取blockId
	        String blockId = info.getBlockIds();
	        // 如果blockId相等
	        if(blockId.equals("inqu_status,spot")) {
	            // 如果blockId不为空
	            if(info.getBlock("inqu_status").getRows().size()>0) {
	                // 获取block中的数据的集合
	                List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
	                // 获取集合中的数据
	                String spotName = (String) listMap.get(0).get("spotName");
	                // 调用远程接口返回集合
	                List<Map<String, Object>> spotAll = BaseDockingUtils.getSpotBySpotName(spotName);
	                // 对返回集合进行分页
	                List startPage = startPage(spotAll,Integer.valueOf(map.get("offset").toString()),Integer.valueOf(map.get("limit").toString()));
	                // 设置block数据
	                block.addRows(startPage);
	                // 实例化一个map
	                Map<String, EiBlock> tempMap = new HashMap<>();
	                // map赋值spot
	                tempMap.put("spot", block);
	                // info设置block
	                outinfo.setBlocks(tempMap);
	                // 返回
	                return outinfo;
	            }
	        }
	        // 调用改造地点接口
	        EiInfo spotAll = BaseDockingUtils.getSpotAllPage(map, "spot");
	        // 获取block中的数据的集合
	        List<Map<String, Object>> list = (List) spotAll.getBlock("spot").getRows();
	        // 实例化list集合
	        List<Map<String, Object>> replaceList = new ArrayList<>();
	        // 遍历集合
	        for (Map<String, Object> map2 : list) {
	            // 将spotId加入集合
	            map2.put("spotId", map2.get("id"));
	            // 将集合报错到list中
	            replaceList.add(map2);
	        }
	        // 调用分页
	        List startPage = startPage(list,Integer.valueOf(map.get("offset").toString()),Integer.valueOf(map.get("limit").toString()));
	        // 将分页信息加入block
	        block.addRows(startPage);
	        // 实例化map
	        Map<String, EiBlock> tempMap = new HashMap<>();
	        // 设置map值spot
	        tempMap.put("spot", block);
	        // 设置info的block
	        outinfo.setBlocks(tempMap);
	        // 返回
	        return outinfo;
	    }

	    /**
	     * @Title: startPage
	     * @Description: 分页
	     * @param list : 返回List数据
	     * @param pageNum : 起始页
	     * @param pageSize : 分页数limit
	     * @return: List : 返回List数据
	     */
	    public static List startPage(List list,Integer pageNum,Integer pageSize) {
	        // 集合为空
	        if(CollectionUtils.isEmpty(list)) {
	            // 返回
	            return null;
	        }
	        // 记录总数
	        Integer count = list.size();
	        // 页数
	        Integer pageCount = 0;
	        // 总数取余limit
	        if(count % pageSize == 0) {
	            // 页数取除结果
	            pageCount = count / pageSize;
	        }else {
	            // 页数取除结果+1
	            pageCount = (count / pageSize) + 1;
	        }
	        // 开始索引
	        int fromIndex = 0;
	        // 结束索引
	        int toIndex = 0;
	        // 如果起始数+limit大于总数
	        if((pageNum+pageSize) > count) {
	            // 开始索引就是起始数
	            fromIndex = pageNum;
	            // 结束索引就是总数
	            toIndex = count;
	        }else {
	            // 开始索引是起始数
	            fromIndex = pageNum;
	            // 结束索引是起始数+limit
	            toIndex = pageNum + pageSize;
	        }
	        // 将list进行分割
	        List pageList = list.subList(fromIndex, toIndex);
	        // 返回集合
	        return pageList;
	    }

	    /**
	     * @Title: deleteItem
		 * <p>1.通过id查询设备</p>
		 * <p>2.判断设备是否在启用，再删除设备</p>
	     * @Description: 删除设备信息
	     * @param ：info
		 * id			设备id
	     * @return: info
		 * Msg：  返回信息
	     */
	    public EiInfo deleteItem(EiInfo info) {
	        // 获取list
	        List<String> list = (List<String>)info.get("list");
	        // 实例化list
	        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
	        // 循环
	        for(int i = 0; i < list.size(); i++) {
	            // 查询方法
	            mapList = dao.query("DFSB01.queryDeviceScheme",list.get(i));
	        }
	        // 如果集合为空
	        if(CollectionUtils.isEmpty(mapList)) {
	            // 删除
	            dao.delete("DFSB01.deleteItem",list);
	            // 设置返回信息
				info.setStatus(1);
	            info.setMsg("删除成功");
	            // 返回
	            return info;
	        }
	        // 设置返回信息
			info.setStatus(-1);
	        info.setMsg("删除失败,该设备存在基准");
	        // 返回
	        return info;
	    }

		/**
		 * 修改设备状态
		 * <p>1.获取设备id，查询设备</p>
		 * <p>2.判断设备是否在启用，如果不是启用设备</p>
		 * @Title: updateStart
		 * @param ：info
		 * id		设备id
		 * @return
		 * Msg：	返回信息
		 * Status： 状态码
		 */
	    public EiInfo updateStart(EiInfo info) {
	      //获取集合
	    	List<String> idList = (List<String>)info.get("idList");
	    	//计数
	    	int rows = dao.update("DFSB01.start",idList);
	    	if(rows == 0){
	    	    //返回信息
	    		info.setMsg("更新失败");
	    		info.setStatus(-1);
	    	}
	    	//返回
	    	return info;
	    }
		 /**
		  * 修改设备状态
		  * <p>1.获取设备id，查询设备</p>
		  * <p>2.判断设备是否已经禁用，如果不是禁用设备</p>
		  * @Title:updateStop
		  * @param ：info
		  * id        设备id
		  * @return：
		  * Msg：	返回信息
		  * Status：返回状态
		  */
		 public EiInfo updateStop(EiInfo info) {
		     //获取集合
			 List<String> idList = (List<String>)info.get("idList");
			 //计数
			 int rows = dao.update("DFSB01.stop",idList);
			 if(rows == 0) {
			   //返回信息
				 info.setMsg("更新失败");
				 info.setStatus(-1);
			 }
			 //返回
			 return info;
		 }

		/**
		 * 上移下移排序
		 */
		public EiInfo sortByOrderNum(EiInfo info) {
			HashMap<String, Object> map = new HashMap<>();

			String sort = (String) info.get("sort");
			String id = (String) info.get("id");

			if (sort.equals("UP")){
				map.put("orderNum",-1);
			}else if (sort.equals("DOWN")){
				map.put("orderNum",1);
			}
			map.put("id",id);
			dao.update("DFSB01.updateOrderNum",map);
			return info;
		}

}

