package com.baosight.wilp.dm.wi.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * TODO(宿舍待入住管理(分配房间))
 * 
 *     1.初始化页面加载已经申请入住的人员调用query方法
 *     2.查询审核状态是1的人员信息
 *     3.对已经入住的人员可以进行退房操作
 *     4.对已经入住的人员可以进行换房操作
 *     5.对审核通过的人员进分配房间
 *     6.对审核通过的人员进分配床号
 *     
 * @Title: ServiceDMWI01.java
 * @ClassName: ServiceDMWI01
 * @Package：com.baosight.wilp.dm.wi.service
 * @author: 辉
 * @date: 2021年11月24日 下午2:07:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMWI01 extends ServiceBase{
    
    /**
     * TODO(初始化页面加载已经申请入住的人员调用query方法)
             *         调用query方法
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用query方法
	    return query(inInfo);
	}
	
   /**
     * TODO(查询审核状态是1的人员信息)
     * @title query
     * @param resquest 请求入参 {ifReview-是否审核通过}
     * @return outInfo
     */
    @SuppressWarnings("unchecked")
	@Override
    public EiInfo query(EiInfo inInfo) {
        //传入数据ifReview-是否审核通过
    	inInfo.set("ifReview", "1");
    	//调用类DMHM01
    	inInfo.set(EiConstant.serviceName, "DMHM01");
    	//调用DMHM01中的query方法
     	inInfo.set(EiConstant.methodName, "query");
     	//不主动设置事务
        EiInfo outInfo =XLocalManager.call(inInfo);
        //返回
        return outInfo;
    }
    
    
    /**
     * TODO(对已经入住的人员可以进行退房操作)
     * @title query
     * @param resquest 请求入参 {id-主键，outType-状态码，ifReview-是否审核通过}
     * @return query(inInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
    	HashMap<String, String> map = new HashMap<>();
    	//id主键
    	map.put("id",inInfo.getString("drmId"));
    	// 1 代表以退房标志
    	map.put("outType", "1"); 
    	EiInfo outInfo = new EiInfo();
    	try {
    	    //更新宿舍入住关系表中的outType状态码
    		dao.update("dMWI01.checkout",map);
    		//ifReview-是否审核通过
    		inInfo.set("ifReview", "3");
    		//调用类DMHM01
    		inInfo.set(EiConstant.serviceName, "DMHM01");
    		//调用DMHM01中的updateStatus方法
	    	inInfo.set(EiConstant.methodName, "updateStatus");
			//更新退房原因及是否归还钥匙
			map.put("outReason",inInfo.getString("outReason"));
			map.put("returnKey",inInfo.getString("returnKey"));
			map.put("outDate",inInfo.getString("outDate"));
			//插入退房原因和是否归还钥匙以及更新退房时间
			dao.update("dMWI01.insertReasonAndReturnKey",map);
	    	outInfo =XLocalManager.call(inInfo);
	    	//提示返回信息
    		outInfo.setMsg("退房成功");
    		return outInfo;
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		outInfo.setMsg("退房失败");
    		return outInfo;
		}
    }
    
    /**
     * TODO(对已经入住的人员可以进行换房操作)
     * @title update
     * @param resquest 请求入参 {id-主键，roomId-房间信息表Id，bedNo-床位号}
     * @return outInfo
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
    	HashMap<String, String> map = new HashMap<>();
    	//传入数据id-主键，roomId-房间信息表Id，bedNo-床位号，changeTime-换房时间
    	map.put("id",inInfo.getString("id"));
    	map.put("roomId",inInfo.getString("roomId"));
    	map.put("bedNo", getBedNo(inInfo.getString("roomId")).toString());
		map.put("changeTime",inInfo.getString("changeTime"));
    	EiInfo outInfo = new EiInfo();
    	try {
    	    //更新宿舍入住关系表中的roomId-房间信息表Id，bedNo-床位号
    		dao.update("dMWI01.changeRoom",map);
    		//返回提示信息
	    	outInfo.setMsg("换房成功");
    		return outInfo;
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		outInfo.setMsg("换房失败");
    		return outInfo;
		}
    }
    
    /**
     * TODO(对审核通过的人员进分配房间)
     *  1.获取数据：id-主键，roomId-房间信息表ID，manId-人员信息表ID，bedNo-床位号，employmentNature-用工性质，phone-电话，deposit-押金编号，isNetwork-是否联网
     *              isStay-是否办暂住证，inDate-入住日期，outDate-退房日期，inType-入住、调配，departmentNo-科室部门，adjustDate-调费日期，firstInDate-第一次入住时间，
     *              estimatedOutDate-预计退房时间，sendCardDate-发卡时间，outType-退宿、调配，evalLevelWy-物业管理-评价等级(0-5)，evalLevelSs-宿舍情况-评价等级(0-5)，evalContent-，
     *              standardPriece-月租费，elecPriece-水电费，waterPriece-管理费，updateDate-更新时间，sex-性别，isView-是否审批
     *  2.将数据插入进宿舍入住关系表中
     *  3.捕获异常
     * @title insert
     * @param resquest 请求入参 {id-主键，roomId-房间信息表ID，manId-人员信息表ID，bedNo-床位号，employmentNature-用工性质，phone-电话，deposit-押金编号，isNetwork-是否联网
     *                        isStay-是否办暂住证，inDate-入住日期，outDate-退房日期，inType-入住、调配，departmentNo-科室部门，adjustDate-调费日期，firstInDate-第一次入住时间，
     *                        estimatedOutDate-预计退房时间，sendCardDate-发卡时间，outType-退宿、调配，evalLevelWy-物业管理-评价等级(0-5)，evalLevelSs-宿舍情况-评价等级(0-5)，evalContent-，
     *                        standardPriece-月租费，elecPriece-水电费，waterPriece-管理费，updateDate-更新时间，sex-性别，isView-是否审批}
     * @return outInfo
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
    	
    	Map<String,Object> map = new HashMap<>(10);
    	try {
    	    /**1.获取数据：id-主键，roomId-房间信息表ID，manId-人员信息表ID，bedNo-床位号，employmentNature-用工性质，phone-电话，deposit-押金编号，isNetwork-是否联网
                isStay-是否办暂住证，inDate-入住日期，outDate-退房日期，inType-入住、调配，departmentNo-科室部门，adjustDate-调费日期，firstInDate-第一次入住时间，
                estimatedOutDate-预计退房时间，sendCardDate-发卡时间，outType-退宿、调配，evalLevelWy-物业管理-评价等级(0-5)，evalLevelSs-宿舍情况-评价等级(0-5)，evalContent-，
                standardPriece-月租费，elecPriece-水电费，waterPriece-管理费，updateDate-更新时间，sex-性别，isView-是否审批*/
    		map.put("id", UUID.randomUUID().toString());
        	map.put("roomId", inInfo.get("roomId"));
    	    map.put("manId", inInfo.get("manId"));
            map.put("bedNo", getBedNo(inInfo.get("roomId").toString()));
        	map.put("employmentNature", "");
        	map.put("phone",inInfo.get("phone"));
        	map.put("deposit", "");
        	map.put("isNetwork", "0");
        	map.put("isStay", "0");
        	map.put("inDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        	map.put("outDate", inInfo.get("estimatedOutDate"));
        	map.put("inType", "");
        	map.put("departmentNo", "");
        	map.put("adjustDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        	map.put("firstInDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        	map.put("estimatedOutDate", inInfo.get("estimatedOutDate"));
        	map.put("sendCardDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        	map.put("outType", "");
        	map.put("evalLevelWy", "0");
        	map.put("evalLevelSs", "0");
        	map.put("evalContent", "");
        	map.put("standardPriece",inInfo.get("standardPriece"));
        	map.put("elecPriece",inInfo.get("elecPriece"));
        	map.put("waterPriece",inInfo.get("waterPriece"));
        	map.put("updateDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        	map.put("sex", "");
        	map.put("isView", "");
        	/**2.将数据插入进宿舍入住关系表中*/
        	dao.insert("dMWI01.insert",map);
        	
        	// 更改状态
        	EiInfo outInfo = new EiInfo();
        	//传入数据
        	outInfo.set("id", inInfo.get("manId"));
        	outInfo.set("ifReview","2");
        	//调用服务
        	outInfo.set(EiConstant.serviceName, "DMHM01");
        	outInfo.set(EiConstant.methodName, "updateStatus");
            return XLocalManager.call(outInfo);
            /**3.捕获异常*/
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
            outInfo.setMsg("分配失败");
            return outInfo;
		}

    }
    
    /**
     * TODO(对审核通过的人员进分配床号)
     * @title getBedNo
     * @param resquest 请求入参 {roomId-房间信息表Id}
     * @return outInfo
     */
	private Integer getBedNo(String roomId) {
		// TODO Auto-generated method stub
	
		Map<String, String> map=new HashMap<>();
		//roomId-房间信息表Id
		map.put("roomId", roomId);
		// 获取该房间已使用床位编号
		List<Map<String, Integer>> list = dao.query("dMRM01.countUsedBed",map);
		// 获取该房间总床位数
		List<Map<String, Integer>> bedNumList = dao.query("dMRM01.getBedNumByRoomId",map);
		Integer bedNum = bedNumList.get(0).get("bedNum");
		int step = 1;
		for (int i = 0; i <  list.size(); i++,step++) {
			if(list.get(i).get("usedBed") == step) {
				continue;
			}else {
				break;
			}
		}
		if(step <= bedNum) {
			return step;
		}else {
			return 0;
		}
	}

}

