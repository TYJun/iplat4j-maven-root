package com.baosight.wilp.hi.fl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hi.common.domain.HiClassify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院标识分类管理Service
 * 
 * <p>1.页面加载</p>
 * <p>2.页面查询</p>
 * <p>3.查询医院标识分类树</p>
 * <p>4.删除医院标识分类</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHIFL01.java
 * @ClassName:  ServiceHIFL01
 * @Package com.baosight.wilp.hi.fl.service
 * @Description: TODO
 * @author  liangyongfei
 * @date:   2022年8月19日 上午10:50:10
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHIFL01 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
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
	 *		classifyNum : 医院标识分类编码
	 * 		classifyNum : 医院标识分类名称
	 * 		parentId : 上级分类ID
	 * @return
	 * 		id : 主键
	 * 		classifyNum : 医院标识分类编码
	 * 		classifyNum : 医院标识分类名称
	 * 		superClassifyName : 上级分类
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-dataGroupCode", inInfo.getString("dataGroupCode"));
		//除去参数两侧的空格
		inInfo.getBlock("inqu_status").getRow(0).replaceAll((key, value)
				-> {return CommonUtils.trimForParams(value);});
        EiInfo outInfo = super.query(inInfo, "HIFL01.query", new HiClassify());
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
    	//1 获取参数
    	String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");

    	Map<String, String> queryMap = new HashMap<>(16);
		if("$".equals(node)){
			queryMap.put("parentId", "root");
		} else {
			queryMap.put("parentId", node);
		}
    	queryMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
    	//2.查询数据，通过dataGroupCode(账套)、parentId(分类ID)
	    List rows = dao.query("HIFL01.queryItemTypeTree", queryMap);
	    //3 增加节点 block 块
	    EiInfo outInfo = new EiInfo();
	    EiBlock outBlock = outInfo.addBlock(node);
	    outBlock.addRows(rows);
		return outInfo;
	}
    
    /**
     * 删除医院标识分类
	 *
	 * <p>
	 *     1.获取参数
	 *     2.判断医院标识分类下是否有医院标识分类
	 *     3.有，提示错误信息“医院标识分类下存在下级分类，无法删除”
	 *     4.没有，删除医院标识分类
	 * </p>
	 *
     * <p>Title: delete</p>   
     * <p>Description: </p>   
     * @param inInfo
     * @return   
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
	@SuppressWarnings("unchecked")
	public EiInfo delete (EiInfo inInfo) {
    	//1、获取参数，id。
		String id = (String) inInfo.getAttr().get("id");
    	 //2、通过id,判断分类是否存在下级分类
    	 int count = super.count("HIFL01.isDelete",id);
		 //2.1、存在，提示错误信息
		 if(count > 0){
    		 inInfo.setMsg("医院标识分类下存在下级分类，无法删除");
    		 inInfo.setStatus(-1);
    	 }else {
			 //2.2、不存在，删除
    		dao.delete( "HIFL01.delete",id);
		 }
		return inInfo;
    }


}
