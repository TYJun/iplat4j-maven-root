package com.baosight.wilp.hi.cx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hi.common.domain.HiIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院标识布局管理页面Service
 * 
 * <p>1.页面加载</p>
 * <p>2.页面查询</p>
 * <p>3.查询医院位置分类树</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHICX01.java
 * @ClassName:  ServiceHICX01
 * @Package com.baosight.wilp.hi.cx.service
 * @Description: TODO
 * @author 梁勇飞
 * @date:   2022年8月21日 下午5:11:10
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHICX01 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 *		dataGroupCode : 账套
	 * 		building : 楼
	 * 		floor : 层
	 * @return
	 * 		id : 主键
	 * 		itemTypeNum : 维修事项分类编码
	 * 		itemTypeName : 维修事项分类名称
	 * 		superItemTypeName : 上级分类
	 * 		wgroupName : 维修科室
	 * <p>1、获取参数
	 * <p>2、查询数据
	 * <p>3、显示楼、层的数量
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		//1、获取参数(账套、楼、层)
		inInfo.set("inqu_status-0-dataGroupCode", inInfo.getString("dataGroupCode"));
		inInfo.set("inqu_status-0-building",inInfo.getString("building"));
		inInfo.set("inqu_status-0-floor",inInfo.getString("floor"));
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		//除去参数两侧的空格
		inInfo.getBlock("inqu_status").getRow(0).replaceAll((key, value)
				-> {return CommonUtils.trimForParams(value);});
		//2、查询标识的数量
		Integer count = Integer.valueOf(super.count("HICX01.queryCount",param));
		//3、查询标识信息
        EiInfo outInfo = super.query(inInfo, "HICX01.query", new HiIcon());
		if(count > 0){
			String msyHospitalLogo = "医院标识数目为"+ count;
			outInfo.setMsg(msyHospitalLogo);
		}
        return outInfo;
    }
	
	 /**
     * PC端查询医院位置分类树
     * @Title: queryTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		node ： 选中节点的id
     * @param:  @return
     * @return: EiInfo
     * 		label : 节点id（分类id）,
			text : 	节点名称（分类名称）
			pId : 父节点id(上级分类id)
			leaf : 是否存在子节点，1=不存在子节点 、 2=存在子节点
     * @throws
     */
    @SuppressWarnings("rawtypes")
	public EiInfo queryTree(EiInfo inInfo) {
    	//1 获取参数(node:节点)
    	String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		Map<String, String> queryMap = new HashMap<>(2);
		queryMap.put("parentId", node);
		List<Map<String,String>> rows =new ArrayList<>();
		//2.通过node，显示标识位置分布
    	if("root".equals(node)){
			 rows = dao.query("HICX01.queryItemTypeTree", queryMap);
		}else {
    		 rows = dao.query("HICX01.queryItemTypeTreeTwo",queryMap);
		}
	    //3 增加节点 block 块
	    EiInfo outInfo = new EiInfo();
	    EiBlock outBlock = outInfo.addBlock(node);
	    outBlock.addRows(rows);
		return outInfo;
	}

}
