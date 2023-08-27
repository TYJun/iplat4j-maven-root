package com.baosight.wilp.ci.kc.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.ci.kc.domain.CiStorgeDetail;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

/**
 * 库存存量管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>获取物资分类</p>
 * <p>判断库存中是否存在指定物资</p>
 * <p>插入库存数据</p>
 * <p>更新库存数据</p>
 * <p>扣除库存存量</p>
 * <p>添加库存存量</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIKC01.java
 * @ClassName:  ServiceCIKC01
 * @Package com.baosight.wilp.ci.kc.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午6:23:45 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIKC01 extends ServiceBase {
	
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
	 * 		wareHouseNo：仓库号
	 * 		matTypeNum： 物资分类
	 * 		matNum：物资编码
	 * 		matName：物资名称
	 * 		isNot0 ： 是否显示0库存
	 * @return 
	 * 		wareHouseName : 仓库
	 *		wareHouseNo : 仓库号
	 *		matTypeName : 物资分类名称
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unitName : 计量单位
	 *		totalNum : 库存总量
	 *		totalAmount : 库存总价
	 *		minNum : 最低存量
	 *		maxNum : 最高存量
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
    	inInfo.set("inqu_status-0-dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
    	inInfo.set("inqu_status-0-matTypeNums", CiUtils.getMatTypeStrs(inInfo.getString("inqu_status-0-matTypeNum")));
        EiInfo outInfo = super.query(inInfo, "CIKC01.query", new CiStorge());

		List<HashMap<String, Object>> list = outInfo.getBlock("result").getRows();
		if(CollectionUtils.isNotEmpty(list)){
			for (HashMap<String, Object> map : list) {
				String matNum = (String) map.get("matNum");
				Map<String, Object> pMap = new HashMap<>();
				pMap.put("matCode",matNum);
				Map<String, Object> matMap = CiUtils.getMatByCode(pMap);
				if(matMap != null){
					map.put("surpNum",matMap.get("supplierCode"));
					map.put("surpName",matMap.get("supplier"));
				}

			}
		}

        return outInfo;

    }
    
    /**
     * 获取指定仓库的指定物资
     * @Title: getStockByMatNum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wareHouseNo:仓库号
     * 		matNum ： 物资编码
     * @param:  @return
     * @return: EiInfo
     * 		  storge： 物资库存对象
     * 				wareHouseName : 仓库
	 *				wareHouseNo : 仓库号
	 *				matTypeName : 物资分类名称
	 *				matNum : 物资编码
	 *				matName : 物资名称
	 *				matModel : 物资型号
	 *				matSpec : 物资规格
	 *				unitName : 计量单位
	 *				totalNum : 库存总量
	 *				totalAmount : 库存总价
	 *				minNum : 最低存量
	 *				maxNum : 最高存量
     * @throws
     */
    @SuppressWarnings("unchecked")
	public EiInfo getStockByMatNum(EiInfo inInfo) {
    	Map<String,String> map = new HashMap<>();
		map.put("wareHouseNo", inInfo.getString("wareHouseNo"));
		map.put("matNumEQ", inInfo.getString("matNum"));
		List<CiStorge> list = dao.query("CIKC01.query", map);
		CiStorge siStorge = list==null ? null : list.get(0);
		inInfo.set("storge", siStorge);
		return inInfo;
    }

    /**
     * 获取物资分类树
     * @Title: getMateriaType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		parentId ： 当前节点的id
     * @param:  @return
     * @return: EiInfo
     * 		id ： 当前节点的id
	 *		text : 分类名称
	 *		pId ： 上级节点的id
	 *		leaf : 是否有子节点
     * @throws
     */
    public EiInfo getMateriaType (EiInfo inInfo) {
    	//1 获取参数
    	String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
    	Map<String, String> queryMap = new HashMap<>();
    	queryMap.put("parentId", node);
    	//2.查询数据
	    List<Map<String, String>> rows = CiUtils.getMatTypeTree(queryMap);
	    //3 增加节点 block 块
	    EiInfo outInfo = new EiInfo();
	    EiBlock outBlock = outInfo.addBlock(node);
	    outBlock.addRows(rows);
		return outInfo;
    }
    
    /**
     * 判断库存中是否存在指定物资
     * @Title: isExistMat
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wareHouseNo:仓库号
     * 		matNum:物资编码
     * @param:  @return
     * @return: EiInfo 
     * 		isExistMat: 物资是否存在，"true"=存在，"false"=不存在 
     * @throws
     */
    public EiInfo isExistMat (EiInfo inInfo) {
    	Map<String, Object> map = CommonUtils.buildParamter(inInfo, Arrays.asList(new String[]{"wareHouseNo","matNum"}));
    	int count = super.count("CIKC01.isExistMat", map);
    	if(count > 0){
    		inInfo.set("isExistMat", "true");
    	} else {
    		inInfo.set("isExistMat", "false");
    	}
    	return inInfo;
    }
    
    /**
     * 插入库存数据
     * @Title: insertStorge
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo insertStorge (EiInfo inInfo) {
    	//获取参数
    	CiStorge storge = inInfo.get("storge") == null ? null : (CiStorge)inInfo.get("storge");
		//判断参数是否为空
		if(storge == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("插入库存数据失败"); 
			return inInfo;
		} else {
			dao.insert("CIKC01.insert", storge);
		}
        return inInfo;
    }
    
    /**
     * 更新库存数据
     * @Title: updateStorge
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    public EiInfo updateStorge (EiInfo inInfo) {
    	//获取参数
    	CiStorge storge = inInfo.get("storge") == null ? null : (CiStorge)inInfo.get("storge");
		//判断参数是否为空
		if(storge == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("更新库存数据失败"); 
			return inInfo;
		} else {
			dao.update("CIKC01.update", storge);
		}
        return inInfo;
    }
    
    /**
     * 扣除库存存量
     * 
     * <p>出库、红冲入库时扣除库存<p>
     * 
     * @Title: updateStorgeByReduce
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		storgeDetailList ： 库存明细
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    @SuppressWarnings("unchecked")
	public EiInfo updateStorgeByReduce (EiInfo inInfo) {
    	boolean isbreak = false;
    	Object object = inInfo.get("storge");
    	List<CiStorgeDetail> storgeDetailList = inInfo.get("storgeDetailList") == null ? null : (List<CiStorgeDetail>)inInfo.get("storgeDetailList");
		if(storgeDetailList == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("更新库存失败");
			return inInfo;
		} else {
			for (CiStorgeDetail detail : storgeDetailList) {
				if(object == null){
					dao.update("CIKC01.updateStorgeByReduce", detail.toMap());
				} else {
					if(!isbreak){
						CiStorge storge = (CiStorge) object;
						dao.update("CIKC01.updateStorgeByReduce", storge.toMap());
						isbreak = true;
					}
				}
				dao.update("CIKC0101.updateStorgeDetailByReduce", detail);
			}
		}
		return inInfo;
    }
    
    /**
     * 添加库存存量
     * 
     * <p>红冲出库时回加库存<p>
     * 
     * @Title: updateStorgeByReduce
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
    @SuppressWarnings("unchecked")
	public EiInfo updateStorgeByAdd (EiInfo inInfo) {
    	Object object = inInfo.get("storge");
    	List<CiStorgeDetail> storgeDetailList = inInfo.get("storgeDetailList") == null ? null : (List<CiStorgeDetail>)inInfo.get("storgeDetailList");
		if(storgeDetailList == null || object == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("更新库存失败");
			return inInfo;
		} else {
			CiStorge storge = (CiStorge) object;
			dao.update("CIKC01.update", storge);
			storgeDetailList.forEach(detail -> dao.update("CIKC0101.update", detail));
		}
		return inInfo;
    }
}
