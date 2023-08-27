package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMGeneCode;
import com.baosight.wilp.dm.common.domain.DMGeneCodeType;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * 宿舍满意度评价项目管理：初始化查询、项目查询、插入节点、跟新项目分类信息、删除节点、删除项目信息、获取宿舍满意度评价项目的分类信息
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 * <p>3.插入节点 insert
 * <p>4.跟新项目分类信息 update
 * <p>5.删除节点 deleteBatch
 * <p>6.删除项目信息 deleteItem
 * <p>7.获取宿舍满意度评价项目的分类信息 queryTypeTree
 * 
 * @Title: ServiceDMPJ02.java
 * @ClassName: ServiceDMPJ02
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年3月25日 下午1:20:19
 * @version: V1.0
 * @Copyright: 20212 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceDMPJ02 extends ServiceBase {
	
	private static Lock lock = new ReentrantLock();

	/**
	 * 宿舍满意度评价项目页面的初始化方法
	 * @param inInfo
	 * @return
	 * id 主键
	 * code 项目编码
	 * content 项目名称
	 * typeName 上级分类
	 * memo 备注
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
     * 宿舍满意度评价项目页面查询方法
     * <p>将取参数封装包含分页
     * @param inInfo
     * typeName 项目分类
     * itemName 巡检项目
     * typeId 巡检项目id
     * @return
     * id 主键
     * code 项目编码
     * content 项目名称
     * typeName 上级分类
     * memo 备注
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo query(EiInfo inInfo) {
        String[] param = {"itemName", "typeName", "typeId"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        List<Map<String, Object>> list = dao.query("DMPJ02.queryItem",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMPJ02.countItem",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }
    
	/**
	 * 
	 * 获取宿舍满意度评价项目的分类信息：树形菜单
	 * <p>1.通过该节点信息获取其子节点信息
	 * @Title: queryTypeTree 
	 * @param inInfo
	 * @return 
	 * id     节点id
	 * classifyName  分类信息名称
	 * @return: EiInfo
	 */
	public EiInfo queryTypeTree(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        EiInfo outInfo = super.query(inInfo, "DMPJ02.queryClass");
        //通过该节点信息获取其子节点信息
        String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
        outInfo.getBlocks().remove(EiConstant.resultBlock);
        return outInfo;
    }
	
	/**
	 * 插入节点
	 * @param inInfo
	 * id   分类id
	 * classifyCode  分类编码
	 * classifyName  分类名称
	 * parentId      父节点id
	 * memo          备注
	 * @return
	 * 新增节点成功，失败则执行回滚操作
	 * @see ServiceBase#insert(EiInfo)
	 */
    public EiInfo insert(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", UUID.randomUUID().toString());
		map.put("classifyCode", DMGeneCode.geneCode(DMGeneCodeType.DM_ITEMCLASS));
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("parentId", inInfo.getAttr().get("parentId").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        dao.update("DMPJ02.insertClass", map);
        return inInfo;
    }
    
    /**
     * 更新项目分类信息
     * @param inInfo
     * id   分类id
     * classifyName  分类名称
     * memo          备注
     * @return
     * 跟新分类成功，失败则执行回滚操作
     * @see ServiceBase#update(EiInfo)
     */
    public EiInfo update(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", inInfo.getAttr().get("id").toString());
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        dao.update("DMPJ02.updateClass", map);
        return inInfo;
    }
    
    /**
     * 删除节点
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
            list.add(inInfo.getAttr().get("id").toString());
            //根据该id执行递归方法获取其子节点id
            getParentId(inInfo.getAttr().get("id").toString(), list);
            //判断分类及子分类下是否有巡检项目
            int count = super.count("DMPJ02.isExistItem", list);
            if(count > 0){
                inInfo.setStatus(-1);
                inInfo.setMsg("该项目分类或其子分类下有项目信息，无法删除");
                return inInfo;
            }
            //获取到所有id再执行删除操作
            dao.delete("DMPJ02.deleteClass", list);
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
        List<String> list = dao.query("DMPJ02.getChildId", id);
        if (list.isEmpty()) {
            return;
        }
        resultList.addAll(list);
        //根据该id执行递归方法获取其子节点id
        list.forEach(e -> {
            getParentId(e, resultList);
        });
    } 
    
    /**
     * 删除项目信息
     * <p>获取选中项目id并执行批量删除操作
     *
     * @Title: deleteItem 
     * @param info
     * list  选中行id
     * @return 
     * 删除成功，删除失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo deleteItem(EiInfo info) {
        //获取选中项目id并执行批量删除操作
        List<String> list=(List<String>)info.get("list");
        dao.delete("DMPJ02.deleteItem",list);
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
