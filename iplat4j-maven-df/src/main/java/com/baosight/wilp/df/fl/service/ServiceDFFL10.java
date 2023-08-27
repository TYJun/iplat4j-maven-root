package com.baosight.wilp.df.fl.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * 设备系统分类：初始化查询、设备分类查询、设备系统分类菜单树查询、设备名称查询、设备参数查询、设备地点查询、添加节点、编辑节点、删除树节点与子节点、递归获取子节点
 * <p>1.初始化查询 initLoad
 * <p>2.设备分类查询 query
 * <p>3.设备系统分类菜单树查询 queryDFFLTree
 * <p>4.添加节点 insert
 * <p>5.编辑节点 update
 * <p>6.删除树节点与子节点 deleteBatch
 * <p>7.查询设备参数 queryDevice
 * <p>8.根据设备分类id查询设备参数 queryParameter
 *
 * @Title: ServiceDFFL10.java
 * @ClassName: ServiceDFFL10
 * @Package：com.baosight.wilp.df.fl.service
 * @author: zhaow
 * @date: 2021年8月10日 下午2:45:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFFL10 extends ServiceBase{
	/**
	 * 定义一个可重入锁
	 **/
	private static final Lock lock = new ReentrantLock();

	/**
	 * @Title: initLoad
	 * @Description: 初始化方法
	 * @param info
	 * @return info
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 返回info
		return info;
	}

	/**
	 * 
	 * @Title: query
	 * @Description: 设备分类查询
	 * @param info
	 * moduleId : 设备分类id
	 * classifyName : 设备分类名称
	 * classifyMemo : 设备分类备注
	 * @return
	 * id : 设备分类id
	 * classifyName : 设备分类名称
     * classifyCode : 设备分类编码
     * memo : 设备分类备注
	 */
	@Override
	public EiInfo query(EiInfo info) {
		// 调用分页接口实例化map
		Map<String, Object> map = CommonUtils.buildParamter(info, "result", new ArrayList<>());
		// 获取info中的attr的参数(下同)
		String moduleId = (String) info.getAttr().get("queryModuleId");
		String classifyName = (String) info.getAttr().get("queryClassifyName");
		String classifyMemo = (String) info.getAttr().get("queryClassifyMemo");
		// map赋值moduleId
		map.put("moduleId", moduleId);
		map.put("classifyName", classifyName);
		map.put("classifyMemo", classifyMemo);
		// 获取设备分类的集合
		List<Map<String,String>> list = dao.query("DFFL10.getClassInfo", map);
		// 获取设备分类数量
		int count = dao.count("DFFL10.getClassInfoCount",map);
		// 如果集合为空
		if(CollectionUtils.isEmpty(list)) {
		    // 返回
            return info;
        }
		// 调用分页接口返回
		return CommonUtils.BuildOutEiInfo(info, null, null, list, count);
	}
	
	/**
	 * @Title: queryDFFLTree 
	 * @Description: 设备系统分类菜单树查询
	 * @param info
	 * @return info
	 * id : 设备分类id
	 * moduleCode : 所属模块
	 * classifyCode : 分类编码
	 * classifyName : 分类名称
	 * parentId : 父节点id
	 * memo : 备注
	 * parentName : 父节点名称
	 * isLeaf : 是否有子节点
	 */
	public EiInfo queryDFFLTree(EiInfo info) {
	    // 设置分页为1000
	    info.set("result-limit", 1000);
	    // 调用父类查询方法获取info
		EiInfo outInfo = super.query(info, "DFFL10.queryDFFLTree",new EiBlockMeta());
		// 将获取的结果赋值
		String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		// 将赋值的结果赋值给block
		outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
		// 移除block
		outInfo.getBlocks().remove(EiConstant.resultBlock);
		// 返回
		return outInfo;
	}

	/**
	 * @Title: insert
	 * @Description: 设备分类节点添加
	 * @param info
	 * id : 设备分类id
	 * moduleCode : 所属模块
	 * classifyCode : 分类编码
	 * classifyName : 分类名称
	 * parentId : 父节点id
	 * memo : 备注
	 * @return
	 */
	@Override
	public EiInfo insert(EiInfo info) {
		// 实例化map
		Map<String,String> map = new HashMap<String,String>(8);
		// 生成uuid
		String id = UUID.randomUUID().toString();
		// 模块
		String moduleCode = "DF";
		// 分类规则：模块+时间戳
		String classifyCode = moduleCode+Calendar.getInstance().getTimeInMillis();
		// 获取info中的classifyName(下同)
		String classifyName = (String) info.get("classifyName");
		String parentId = (String) info.get("parentId");
		String memo = (String) info.get("memo");
		// 赋值map的id(下同)
		map.put("id", id);
		map.put("moduleCode", moduleCode);
		map.put("classifyCode", classifyCode);
		map.put("classifyName", classifyName);
		map.put("parentId", parentId);
		map.put("memo", memo);
		// 插入方法
		dao.insert("DFFL10.insert",map);
		// 设置返回值
		info.setMsg("新增成功");
		// 返回
		return info;
	}

	/**
	 *
	 * @Title: update
	 * @Description: 设备分类节点编辑
	 * @param info
	 * id : 设备分类id
	 * moduleCode : 所属模块
	 * classifyCode : 分类编码
	 * classifyName : 分类名称
	 * parentId : 父节点id
	 * memo : 备注
	 * @return info
	 */
	@Override
	public EiInfo update(EiInfo info) {
		// 实例化map
		Map<String,String> map = new HashMap<String,String>();
		// map赋值classifyName
		map.put("classifyName", info.getString("classifyName"));
		map.put("id", info.getString("id"));
		map.put("memo", info.getString("memo"));
		// 更新
		dao.update("DFFL10.update",map);
		// 设置返回值
		info.setMsg("编辑成功");
		// 返回
		return info;
	}

	/**
	 *
	 * @Title: deleteBatch
	 * @Description: 设备分类节点删除
	 * @param info
	 * id : 设备分类id
	 * @return: info
	 */
	public EiInfo deleteBatch(EiInfo info) {
		// 实例化list
		List<String> list = new ArrayList<String>();
		// 获取info中的id
		String id = (String) info.get("id");
		// 加锁
		lock.lock();
		try {
			// list加入id
			list.add(id);
			// 递归获取子节点
			getParentId(id, list);
			// 删除
			dao.delete("DFFL10.deleteBatch", list);
			// 返回
			return info;
		} finally {
			// 解锁
			lock.unlock();
		}
	}

	/**
	 *
	 * @Title: getParentId
	 * @Description: 递归获取设备子节点
	 * @param id : 设备分类id
	 * @param resultList : 设备分类子节点id
	 * @return: void
	 */
	private void getParentId(String id, List<String> resultList) {
		// 查询子节点
		List<String> list = dao.query("DFFL10.getChildId", id);
		// 集合为空
		if (CollectionUtils.isEmpty(list)) {
			// 返回
			return;
		}
		// 添加list
		resultList.addAll(list);
		// list循环
		list.forEach(e -> {
			// 调用递归方法
			getParentId(e, resultList);
		});
	}

	/**
	 * @Title: queryDevice 
	 * @Description: 查询设备参数信息
	 * @param info
	 * @return info
	 * id : 设备分类参数id
	 * moduleId : 参数所属分类ID
	 * paramName : 参数名称
	 * paramKey : 参数编码
	 * paramValue : 参数值
	 * paramUnit : 参数单位
	 * memo : 备注
	 * classifyName : 分类名称
	 */
	public EiInfo queryDevice(EiInfo info) {
		// 获取参数
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "device");
		// 数据查询
		List<Map<String, Object>> list = dao.query("DFFL10.queryDeviceList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		// 获取数量
		int count = dao.count("DFFL10.queryDeviceList", map);
		// 如果集合不为空
		if(CollectionUtils.isNotEmpty(list)){
		    // 调用分页接口返回
			return CommonUtils.BuildOutEiInfo(info, "device", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
		    // 返回
			return info; 
		}
	}
	
	/**
	 * @Title: queryParameter 
	 * @Description: 根据设备分类id查询设备参数
	 * @param info
	 * @return: info
	 * paramKey : 参数编码
	 * paramName : 参数名称
	 * paramValue : 参数值
	 * paramUnit : 参数单位
	 * memo : 备注
	 */
	public EiInfo queryParameter(EiInfo info) {
	    // 从info的attr中获取machineTypeId
		String machineTypeId = (String) info.getAttr().get("machineTypeId");
		// 实例化map
		Map<String, String> map = new HashMap<>();
		// map赋值machineTypeId
		map.put("machineTypeId", machineTypeId);
		// 查询设备参数集合
		List<Map<String, String>> list = dao.query("DFFL10.queryParameter", map);
		// 获取设备参数数量
		int count = dao.count("DFFL10.queryParameter", map);
		// info加入数据
		info.addRows("result", list);
		// 返回
		return info;
	}

}
