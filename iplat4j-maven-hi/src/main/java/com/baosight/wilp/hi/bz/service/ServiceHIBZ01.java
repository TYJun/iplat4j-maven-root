package com.baosight.wilp.hi.bz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hi.common.domain.HiStandard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院标准管理Service
 * 
 * <p>1.页面加载</p>
 * <p>2.页面查询</p>
 * <p>3.查询医院标识分类树</p>
 * <p>4.删除医院标识分类</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHIBZ01.java
 * @ClassName:  ServiceHIBZ01
 * @Package com.baosight.wilp.hi.bz.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年08月19日 下午2:11:10
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHIBZ01 extends ServiceBase {

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
	 *		standardNum : 标准编码
	 * 		standardName : 标准名称
	 * @return
	 * 		id : 主键
	 * 		standardNum : 标准编码
	 * 		standardName : 标准名称
	 * 		classifyName : 分类名称
	 * 		remark : 备注
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-dataGroupCode", inInfo.getString("dataGroupCode"));
		//除去参数两侧的空格
		inInfo.getBlock("inqu_status").getRow(0).replaceAll((key, value)
				-> {return CommonUtils.trimForParams(value);});
        EiInfo outInfo = super.query(inInfo, "HIBZ01.query", new HiStandard());
        return outInfo;
    }
	
	 /**
     * PC端查询医院标识分类
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
    	//1、获取参数(node)，选中节点的id
    	String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");

    	Map<String, String> queryMap = new HashMap<>(16);
    	//2、赋值queryMap，包括parentId(上级分类id),dataGroupCode(账套)
		if("$".equals(node)){
			queryMap.put("parentId", "root");
		} else {
			queryMap.put("parentId", node);
		}
    	queryMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
    	//2.查询数据,通过parentId(上级分类)、dataGroupCode(账套)
	    List rows = dao.query("HIBZ01.queryItemTypeTree", queryMap);
	    //3、增加节点 block 块，返回数据
	    EiInfo outInfo = new EiInfo();
	    EiBlock outBlock = outInfo.addBlock(node);
	    outBlock.addRows(rows);
		return outInfo;
	}
    
    /**
     * 删除标识标准分类
	 *
	 * <p>
	 *     1、获取参数
	 *     1.判断标准是否为空
	 *     2.有,删除。将医院标准信息及相对应的附件删掉
	 *     3.没有，提示参数不能为空
	 * </p>
	 *
     * <p>Title: delete</p>   
     * <p>Description: </p>   
     * @param inInfo
     * @return   
     * @see ServiceBase#delete(EiInfo)
     */
    @Override
	@SuppressWarnings("unchecked")
	public EiInfo delete (EiInfo inInfo) {
    	//1、获取参数
    	EiBlock block = inInfo.getBlock("result");
    	 Map<String, Object> pMap = (Map<String, Object>) block.getRows().get(0);
    	 //2、通过id判断该删除列是否有参数
    	 int count = super.count("HIBZ01.isDelete",pMap);
		 //2.1、无参数时，提示错误信息
		 if(count <= 0){
    		 inInfo.setMsg("参数不能为空");
    		 return inInfo;
    	 }
    	 //2.2、有参数，删除。将医院标准信息及相对应的附件删掉
    	return super.delete(inInfo, "HIBZ01.delete");
    }


}
