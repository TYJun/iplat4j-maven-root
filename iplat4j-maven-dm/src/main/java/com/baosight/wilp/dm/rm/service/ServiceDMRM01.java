/**
 *@Name ServiceDMRM.java
 *@Description TODO
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.rm.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.dm.common.until.CsBaseDockingUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * TODO(宿舍信息管理页面-DMRM01，可以新增数据，编辑宿舍，删除宿舍)
 * 
 *          1.初始化页面加载已有的宿舍，返回调用query方法
 *          2.查询操作：查询已经添加的宿舍
 *          3.删除操作：删除已经添加的宿舍
 *          4.新增操作：新增一个宿舍
 *          5.编辑操作：对已经有的宿舍进编辑修改一些基础的数据
 * 
 * @Title: ServiceDMRM01.java
 * @ClassName: ServiceDMRM01
 * @Package：com.baosight.wilp.dm.rm.service
 * @author: 辉
 * @date: 2021年11月23日 下午5:19:56
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMRM01 extends ServiceBase{
    /**
     * TODO(初始化页面加载已有的宿舍，返回调用query方法)
            * 调用query方法
     * @title initLoad
     * @param resquest 请求入参 {inInfo}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		//String buildingCode = CommonUtils.getDataCodeItemName("wilp.dm.rm.buildingCode", inInfo.getAttr().get("buildingCode").toString());

		return query(inInfo);
	}
	
	/**
     * TODO(查询操作：查询已经添加的宿舍)
     * 1.获取数据：buildingCode-楼，floorCode-层，roomNo-房间编号，typeCode-房间类型
     * 2.查询数据库中，dorms_room_man-宿舍入住关系表
     * 3.判断查询结果是否为空
     * @title query
     * @param resquest 请求入参 {buildingCode-楼，floorCode-层，roomNo-房间编号，typeCode-房间类型}
     * @return inInfo
     */ 
    @SuppressWarnings("unchecked")
	@Override
    public EiInfo query(EiInfo inInfo) {
        /*1.获取数据：buildingCode-楼，floorCode-层，roomNo-房间编号，typeCode-房间类型**/
    	Map<String, Object> map = PrUtils.buildParamter(inInfo,	null, "result");
    	//buildingCode-楼
    	map.put("buildingCode", inInfo.get("buildingCode"));
    	//floorCode-层
	    map.put("floorCode", inInfo.get("floorCode"));
	    //roomNo-房间编号
	    map.put("roomNo", inInfo.get("roomNo"));
	    //typeCode-房间类型
	    map.put("typeCode", inInfo.get("typeCode"));
	    /*2.查询数据库中，dorms_room、dorms_room_man-**/
    	List<Map<String, Object>> list = dao.query("dMRM01.query",map,(Integer)map.get("offset"), (Integer)map.get("limit"));
		//for (Map<String, Object> queryMap : list) {
			//String buildingCode = CommonUtils.getDataCodeItemName("wilp.dm.rm.buildingCode", queryMap.get("buildingCode").toString());
			//String floorCode = CommonUtils.getDataCodeItemName("wilp.dm.rm.floorCode", queryMap.get("floorCode").toString());
			//queryMap.put("buildingCode",buildingCode);
			//queryMap.put("floorCode",floorCode);
		//}
    	int count = super.count("dMRM01.count",map);
		/*3.判断查询结果是否为空**/
		if(list != null && list.size() > 0){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
			return inInfo; 
		}
        
    }
    
    /**
     * TODO(删除操作：删除已经添加的宿舍)
     * @title delete
     * @param resquest 请求入参 {}
     * @return super.delete(inInfo, "dMRM01.delete")
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        //宿舍信息表
    	return super.delete(inInfo, "dMRM01.delete");
    }
    
    /**
     * TODO(新增操作：新增一个宿舍)
     *     1.获取数据：id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型，buildingCode-宿舍楼，floorCode-层，dormitoryNo-楼，层，房间编号，note-备注
     *           direction-朝向，dormsPosition-宿舍位置，dormsIfwc-独立卫生间，dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-管理费，
     *           lastElec-上次用电量，lastWater-上次用水量
     *     2.将获取的数据放入map集合插入进数据库表宿舍管理信息表中
     *     3.捕获异常信息  
     * @title insert
     * @param resquest 请求入参 { id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型，buildingCode-宿舍楼，floorCode-层，
     *                        dormitoryNo-楼，层，房间编号，note-备注direction-朝向，dormsPosition-宿舍位置，dormsIfwc-独立卫生间，
     *                        dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-管理费，lastElec-上次用电量，lastWater-上次用水量}
     * @return outInfo
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
    	Map<String,Object> map = new HashMap<>(10);
    	/**1.获取数据：id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型（男/女生宿舍），buildingCode-宿舍楼，floorCode-层，dormitoryNo-楼，层，房间编号，note-备注
                direction-朝向，dormsPosition-宿舍位置，dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-医院管理费与物业管理费的总和，
		 hospitalManageFee医院管理费，propertyManageFee物业管理费
                lastElec-上次用电量，lastWater-上次用水量*/
    	try {
    		map.put("id", UUID.randomUUID().toString());
        	map.put("roomNo", inInfo.get("roomNo"));
        	map.put("bedNum", inInfo.get("bedNum"));
        	map.put("typeCode", inInfo.get("typeCode"));
        	map.put("buildingCode", inInfo.get("buildingCode"));
        	map.put("floorCode", inInfo.get("floorCode"));
        	map.put("dormitoryNo", "");
        	map.put("note", inInfo.get("note"));
        	map.put("direction", "");
        	map.put("dormsPosition", inInfo.get("dormsPosition"));
        	map.put("dormsIfwc", inInfo.get("dormsIfwc"));
        	map.put("dormsAreas", inInfo.get("dormsAreas"));
        	map.put("roomName", inInfo.get("buildingCode").toString() + "-" + inInfo.get("floorCode").toString() + "-" + inInfo.get("roomNo"));
        	map.put("rent", inInfo.get("rent"));
			//医院管理费与物业管理费的总和
			double  hospitalManageFee = NumberUtils.toDouble( inInfo.get("hospitalManageFee").toString());
			double  propertyManageFee = NumberUtils.toDouble( inInfo.get("propertyManageFee").toString());
			double manageFee = hospitalManageFee+propertyManageFee;
        	map.put("manageFee", manageFee);
        	map.put("lastElec", 0);
        	map.put("lastWater", 0);
			map.put("hospitalManageFee", inInfo.get("hospitalManageFee"));
			map.put("propertyManageFee", inInfo.get("propertyManageFee"));

        	/**2.将获取的数据放入map集合插入进数据库表宿舍管理信息表中*/
        	dao.insert("dMRM01.insert",map);
        	EiInfo outInfo = new EiInfo();
        	//返回提示信息
            outInfo.setMsg("增加成功");
            return outInfo;
            //3.捕获异常信息  
    	 }catch (Exception e) {
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("增加失败");
            return outInfo;
		}
    	
    }
    
    /**
     * TODO(编辑操作：对已经有的宿舍进编辑修改一些基础的数据)
     *    1.获取数据：id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型，buildingCode-宿舍楼，floorCode-层，dormitoryNo-楼，层，房间编号，note-备注
     *           direction-朝向，dormsPosition-宿舍位置，dormsIfwc-独立卫生间，dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-管理费，
     *           lastElec-上次用电量，lastWater-上次用水量
     *    2.将获取的数据放入map集合插入进数据库表宿舍管理信息表中
     *    3.捕获异常信息
     * @title update
     * @param resquest 请求入参 { id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型，buildingCode-宿舍楼，floorCode-层，
     *                        dormitoryNo-楼，层，房间编号，note-备注direction-朝向，dormsPosition-宿舍位置，dormsIfwc-独立卫生间，
     *                        dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-管理费，lastElec-上次用电量，lastWater-上次用水量}
     * @return outInfo
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
    	Map<String,Object> map = new HashMap<>(10);
    	try {
    	    /**1.获取数据：id-主键，roomNo-房间编号，bedNum-床位数，typeCode-房间类型，buildingCode-宿舍楼，floorCode-层，dormitoryNo-楼，层，房间编号，note-备注
                direction-朝向，dormsPosition-宿舍位置，dormsIfwc-独立卫生间，dormsAreas-房屋面积，roomName-宿舍编号，rent-房租，manageFee-医院管理费与物业管理费的总和，
			 hospitalManageFee医院管理费，propertyManageFee物业管理费
                lastElec-上次用电量，lastWater-上次用水量*/
    		map.put("id", inInfo.get("id"));
        	map.put("roomNo", inInfo.get("roomNo"));
        	map.put("bedNum", inInfo.get("bedNum"));
        	map.put("typeCode", inInfo.get("typeCode"));
        	map.put("buildingCode", inInfo.get("buildingCode"));
        	map.put("floorCode", inInfo.get("floorCode"));
        	map.put("dormitoryNo", "");
        	map.put("note", inInfo.get("note"));
        	map.put("direction", "");
        	map.put("dormsPosition", inInfo.get("dormsPosition"));
        	map.put("dormsIfwc", inInfo.get("dormsIfwc"));
        	map.put("dormsAreas", inInfo.get("dormsAreas"));
        	map.put("roomName", inInfo.get("buildingCode").toString() + "-" + inInfo.get("floorCode").toString() + "-" +inInfo.get("roomNo"));
        	map.put("rent", inInfo.get("rent"));
			//管理费为医院管理费和物业管理费之和
			double manageFee = NumberUtils.toDouble(inInfo.get("hospitalManageFee").toString()) + NumberUtils.toDouble(inInfo.get("propertyManageFee").toString());
        	map.put("manageFee", manageFee);
        	map.put("lastElec", 0);
        	map.put("lastWater", 0);
			map.put("hospitalManageFee", inInfo.get("hospitalManageFee"));
			map.put("propertyManageFee", inInfo.get("propertyManageFee"));

        	/**2.将获取的数据放入map集合更新进数据库表宿舍管理信息表中*/
        	dao.update("dMRM01.update",map);
        	EiInfo outInfo = new EiInfo();
        	//返回提示信息
            outInfo.setMsg("修改成功");
            return outInfo;
            /**3.捕获异常信息*/
    	}catch (Exception e) {
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("修改失败");
            return outInfo;
		}
    }


	/**
	 *  1.处理参数
	 *  2.调用微服务接口S_AC_FW_13，获取楼信息
	 *
	 * @Title: selectSpotBuildingName
	 * @Description: 楼补全
	 * @param:  @param inInfo
	 *      building ： 楼
	 * @param:  @return
	 * @return: EiInfo
	 *      building ： 楼
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotBuildingName(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
		map.put("building", inInfo.getString("buildingCode"));
		//调用微服务接口S_AC_FW_13，获取楼信息
		return CsBaseDockingUtils.selectSpotBuildingName(map);
	}
	/**
	 *  1.处理参数
	 *  2.调用微服务接口S_AC_FW_14，获取层信息
	 *
	 * @Title: selectSpotFloorName
	 * @Description: 层补全
	 * @param:  @param inInfo
	 *      building ： 层
	 *      floor ： 层
	 * @param:  @return
	 * @return: EiInfo
	 *      floor ： 层
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotFloorName(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));

		if(inInfo.get("buildingCode") == null) {
			map.put("buildingCode", inInfo.getMsg());
		}else {
			map.put("building", inInfo.getString("buildingCode"));
		}
		map.put("floor", inInfo.getString("floorCode"));
		//调用微服务接口S_AC_FW_14，获取楼信息
		return CsBaseDockingUtils.selectSpotFloorName(map);
	}

	/**
	 *  1.处理参数
	 *  2.调用微服务接口S_AC_FW_15，获取地点信息
	 *
	 * @Title: selectSpotRoomName
	 * @Description: 地点补全
	 * @param:  @param inInfo
	 *      building ： 层
	 *      floor ： 层
	 *      spotName ： 地点名称
	 * @param:  @return
	 * @return: EiInfo
	 *      reqSpotName ： 房间
	 *      spotNum ： 地点编码
	 *      spotName ： 地点名称
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotRoomName(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));

		if(inInfo.get("buildingCode") == null || inInfo.get("floorCode") == null) {
			map.put("floor", inInfo.getMsg());
			map.put("building", inInfo.getName());
		}else {
			map.put("building", inInfo.getString("buildingCode"));
			map.put("floor", inInfo.getString("floorCode"));
		}
		map.put("spotName", inInfo.getString("roomNo"));
		//调用微服务接口S_AC_FW_15，获取房间、地点编码、地点名称
		return CsBaseDockingUtils.selectSpotRoomName(map);
	}


}

