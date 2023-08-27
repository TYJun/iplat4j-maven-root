package com.baosight.wilp.dk.xm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.domain.DeviceBillType;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;
import com.baosight.wilp.dk.common.util.DeviceGeneCode;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 保养项目管理：保养项目页面的初始化方法、查询保养具体项目的信息、获取保养项目的分类信息、插入节点
 *              更新项目分类信息、批量删除分类信息、递归获取节点id、删除项目信息
 * <p>1.保养项目页面的初始化方法 initLoad
 * <p>2.查询保养具体项目的信息    query
 * <p>3.获取保养项目的分类信息   queryTypeTree
 * <p>4.插入节点                     insert
 * <p>5.跟新项目分类信息     update
 * <p>6.批量删除分类信息     deleteBatch
 * <p>7.递归获取节点id    getParentId
 * <p>8.删除项目信息             deleteItem
 * 
 * @Title: ServiceDKXM01.java
 * @ClassName: ServiceDKXM01
 * @Package：com.baosight.wilp.dk.xm.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午1:37:07
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKXM01 extends ServiceBase{

	private static Lock lock = new ReentrantLock();

	/**
	 * 保养项目页面的初始化方法
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 查询保养具体项目的信息
	 * <p>1.将传递参数分装map
	 * <p>2.使用map参数查询获取保养项目list和count
	 * <p>3.将返回的保养项目list和count添加到EiiNFO并返回客户端
	 * @param inInfo
     * typeName 项目分类
     * itemName 保养项目
     * typeId   保养项目分类id
     * @return
     * id 主键
     * code 项目编码
     * content 项目名称
     * typeName 上级分类
     * projectDesc 项目描述
     * referenceValue 项目参考值
     * memo 备注
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
    	//1.将传递参数分装map
        String[] param = {"itemName", "typeName", "typeId"};
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
        //2.使用map参数查询获取保养项目list和count
        List<Map<String, String>> list = dao.query("DKXM01.queryItem", map);
        int count = dao.count("DKXM01.countItem", map);
        //3.将返回的保养项目list和count添加到EiiNFO并返回客户端
        return DeviceEiUtil.buildResult(inInfo, list, count, "result");
    }
    
	/**
	 * 获取保养项目的分类信息
	 * <p>1.获取分类信息
	 * <p>2.处理是否含有子节点的信息
     * @Title: queryTypeTree 
     * @param inInfo
     * @return 
     * id     节点id
     * classifyName  分类信息名称
     * @return: EiInfo
	 */
	public EiInfo queryTypeTree(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        //1.获取分类信息
        EiInfo outInfo = super.query(inInfo, "DKXM01.queryClass");
        //2.处理是否含有子节点的信息
        String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
        outInfo.getBlocks().remove(EiConstant.resultBlock);
        return outInfo;
    }
	
	/**
	 *  插入节点
	 *  <p>1.将传递参数分装map
	 *  <p>2.调用新增方法将map作为参数
	 *  @param inInfo
	 *  id   分类id
     *  classifyCode  分类编码
     *  classifyName  分类名称
     *  parentId      父节点id
     *  memo          备注
     *  @return
     *  新增节点成功，失败则执行回滚操作
	 */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        //1.将传递参数分装map
        map.put("id", UUID.randomUUID().toString());
        //2.保存项目分类编码
		map.put("classifyCode", DeviceGeneCode.geneCode(DeviceBillType.DK_ITEMCLASS));
		//3.保存项目分类名
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("parentId", inInfo.getAttr().get("parentId").toString());
        //4.保存项目分类备注
        map.put("memo", inInfo.getAttr().get("memo").toString());
        //5.调用新增方法将map作为参数
        dao.update("DKXM01.insertClass", map);
        return inInfo;
    }
    
    /**
     * 更新项目分类信息
     * &lt;p&gt;Title: update&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.将传递参数分装map
     * <p>2.调用修改方法将map作为参数
     * @param inInfo
     * id   分类id
     * classifyName  分类名称
     * memo          备注
     * @return
     * 跟新分类成功，失败则执行回滚操作
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        //1.将传递参数分装map
        map.put("id", inInfo.getAttr().get("id").toString());
        //2.保存项目分类名
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        //3.调用修改方法将map作为参数
        dao.update("DKXM01.updateClass", map);
        return inInfo;
    }
    
    /**
     * 批量删除分类信息
     * <p>1.根据该id执行递归方法获取其子节点id
     * <p>2.获取到所有id再执行删除操作
     * @Title: deleteBatch 
     * @param inInfo
     * id  节点id
     * @return 
     * 删除成功，失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo deleteBatch(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        List<String> list = new ArrayList<String>();
        lock.lock();
        try {
            //1.获取节点id
            list.add(inInfo.getAttr().get("id").toString());
            //2.根据该id执行递归方法获取其子节点id
            getParentId(inInfo.getAttr().get("id").toString(), list);
            //3.获取到所有id再执行删除操作
            dao.delete("DKXM01.deleteClass", list);
            return inInfo;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 递归获取节点id
     * <p>根据该id执行递归方法获取其子节点id
     * @param id 节点id
     * @param resultList  子节点id
     */
    public void getParentId(String id, List<String> resultList) {
        //1.查询子节点
        List<String> list = dao.query("DKXM01.getChildId", id);
        //2.判断list是否为空
        if (list.isEmpty()) {
            return;
        }
        resultList.addAll(list);
        //3.根据该id执行递归方法获取其子节点id
        list.forEach(e -> {
            getParentId(e, resultList);
        });
    } 
    
    /**
     * 删除项目信息
     * <p>1.将传递参数分装list
     * <p>2.调用删除方法将list作为参数
     * @param info
     * list  项目id集合
     * @return
     * 操作成功
     */
    public EiInfo deleteItem(EiInfo info) {
    	//1.将传递参数分装list
        List<String> list=(List<String>)info.get("list");
       //2.调用删除方法将list作为参数
        dao.delete("DKXM01.deleteItem",list);
        EiInfo outInfo=new EiInfo();
        //3.返回操作成功
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
