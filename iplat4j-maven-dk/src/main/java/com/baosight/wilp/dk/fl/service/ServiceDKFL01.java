package com.baosight.wilp.dk.fl.service;

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
 * 保养项目分类：初始化、分类查询、获取分类信息、插入节点、跟新分类信息、删除分类信息、获取递归节点
 * <p>1.初始化 initLoad()
 * <p>2.分类查询     query()
 * <p>3.获取分类信息      queryTypeTree()
 * <p>4.插入节点      insert()
 * <p>5.跟新分类信息      update()
 * <p>6.删除分类信息      deleteBatch()
 * <p>7.获取递归节点      getParentId()
 * 
 * @Title: ServiceDKFL01.java
 * @ClassName: ServiceDKFL01
 * @Package：com.baosight.wilp.dk.fl.service
 * @author: LENOVO
 * @date: 2021年9月10日 下午1:25:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKFL01 extends ServiceBase{

	private static Lock lock = new ReentrantLock();

	/**
	 * 初始化
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 分类查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>1.将传递参数分装map
	 * <p>2.使用map参数查询获取保养分类list和count
	 * <p>3.将返回的保养分类list和count添加到EiiNFO并返回客户端
	 * @param inInfo
	 * classifyName    保养分类名称
	 * @return 
	 * id              主键
     * classifyCode    保养分类编码
     * classifyName    保养分类名称
     * memo            保养分类备注 
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
    	//1.将传递参数分装map
        String[] param = {"classifyNameS","classifyNameSS"};
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
        //2.使用map参数查询获取保养分类list和count
        List<Map<String, String>> list = dao.query("DKXM01.queryClassInfo", map);
        int count = dao.count("DKXM01.countClass", map);
        //3.将返回的保养分类list和count添加到EiiNFO并返回客户端
        return DeviceEiUtil.buildResult(inInfo, list, count, "result");
    }
    
	/**
	 * 获取分类信息
	 * <p>1.获取分类信息
	 * <p>2.处理是否含有子节点的信息
	 *
	 * @Title: queryTypeTree 
	 * @param inInfo
	 * @return 
	 * id             分类主键
	 * classifyName   分类名称
	 * @return: EiInfo
	 */
	public EiInfo queryTypeTree(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        //1.获取分类信息
        EiInfo outInfo = super.query(inInfo, "DKXM01.queryClass");
        //2.处理是否含有子节点的信息
        String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
        //3.删除result子节点
        outInfo.getBlocks().remove(EiConstant.resultBlock);
        return outInfo;
    }
	
	/**
	 *  插入节点
	 *  <p>1.获取参数添加到map
	 *  <p>2.执行新增操作
	 *  
	 *  id              保养分类id
	 *  classifyCode    保养分类编码
	 *  classifyName    保养分类名称
	 *  parentId        父id
	 *  memo            备注
	 */
    @Override
    public EiInfo insert(EiInfo inInfo) {
    	//1.获取参数添加到map
        Map<String, String> map = new HashMap<String, String>();
        //2.获取id为随机数
        map.put("id", UUID.randomUUID().toString());
		map.put("classifyCode", DeviceGeneCode.geneCode(DeviceBillType.DK_ITEMCLASS));
		//3.将分类名赋值
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("parentId", inInfo.getAttr().get("parentId").toString());
        //4.将备注赋值
        map.put("memo", inInfo.getAttr().get("memo").toString());
        //5.执行新增操作
        dao.update("DKXM01.insertClass", map);
        return inInfo;
    }
    
    /**
     *   更新项目分类信息
     *   <p>1.获取参数添加到map
     *   <p>2.执行修改操作
     *   
     *   id             保养分类id
     *   classifyName   保养分类名称
     *   memo           备注
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
    	 //1.获取参数添加到map
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", inInfo.getAttr().get("id").toString());
        //2.将分类名字赋值
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        //3.执行修改操作
        dao.update("DKXM01.updateClass", map);
        return inInfo;
    }
    
    /**
     *  批量删除分类信息
     *  <p>1.获取参数添加到list
     *  <p>2.根据id递归获取其下节点子id
     *  <p>3.执行删除操作
     *  
     * @param inInfo
     * id            保养分类id
     * @return
     */
    public EiInfo deleteBatch(EiInfo inInfo) {
        //1.给结果集数量赋值
        inInfo.set("result-limit", 1000);
        List<String> list = new ArrayList<String>();
        lock.lock();
        try {
        	//2.获取参数添加到list
            list.add(inInfo.getAttr().get("id").toString());
            //3.根据id递归获取其下节点子id
            getParentId(inInfo.getAttr().get("id").toString(), list);
            //4.执行删除操作
            dao.delete("DKXM01.deleteClass", list);
            return inInfo;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 递归获取节点id
     * @param  id          保养分类id
     * @return resultList  分类id集合
     */
    public void getParentId(String id, List<String> resultList) {
        //1.获取子节点
        List<String> list = dao.query("DKXM01.getChildId", id);
        //2.节点集合为空直接返回
        if (list.isEmpty()) {
            return;
        }
        //3.结果集添加子节点
        resultList.addAll(list);
        //4.递归获取list下子节点
        list.forEach(e -> {
            getParentId(e, resultList);
        });
    }     
}
