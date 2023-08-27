package com.baosight.wilp.dk.kp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.dk.common.domain.DeviceBillType;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;
import com.baosight.wilp.dk.common.util.DeviceGeneCode;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 卡片新增编辑查看类：保养卡片初始化方法、查询保养卡片的信息、插入编辑卡片
 * <p>1.保养卡片初始化方法     initLoad
 * <p>2.查询保养卡片的信息     query
 * <p>3.插入编辑卡片                 insertKP
 * 
 * @Title: ServiceDKKP0101.java
 * @ClassName: ServiceDKKP0101
 * @Package：com.baosight.wilp.dk.kp.service
 * @author: LENOVO
 * @date: 2021年9月10日 下午3:34:13
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKKP0101 extends ServiceBase{
	protected static final Logger logger = LoggerFactory.getLogger(ServiceDKKP0101.class);

	/**
	 * 保养卡片初始化方法
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 查询保养卡片的信息
	 * <p>1.获取操作类型参数
	 * <p>2.通过操作类型判断操作   1.查看  2.新增  3.编辑
	 * <p>3.将传递参数分装map
	 * <p>4.使用map参数查询获取保养项目list和count
	 * <p>5.将返回的保养项目list和count添加到EiiNFO并返回客户端
	 * @param
	 * *cardID             卡片id
	 * cardCode           卡片编码
	 * cardName           卡片名称
	 * kpType             操作类型
	 * memo               卡片备注
	 * @return
	 * cardIDQ            卡片id
	 * cardCodeQ          卡片代码
	 * cardNameQ          卡片名称
	 * kpType             操作类型
	 * memo                备注
	 */
   @Override
   public EiInfo query(EiInfo inInfo) {
	   //1.获取操作类型参数
	   String kpType = (String) inInfo.getAttr().get("kpType");
	   
	   //2.通过操作类型判断操作   1.查看  2.新增  3.编辑	   
	   //3.查看
	   if(kpType.equals("1")) {
		 //4.将传递参数分装map
	       String[] param = {"cardID", "cardCode", "cardName","kpType","memo"};
	       Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "item");
	       inInfo.setAttr(map);
	       //5.使用map参数查询获取卡片里的保养项目list和count
	       List<Map<String, String>> list = dao.query("DKKP01.queryItem", map);
	       int count = dao.count("DKKP01.countItem", map);
	       //6.将返回的卡片里的保养项目list和count添加到EiiNFO并返回客户端
	       return DeviceEiUtil.buildResult(inInfo, list, count, "item");	       
	   //7.新增
	   }else if(kpType.equals("2")) {
		   return inInfo;	   
	   //8.编辑
	   }else if(kpType.equals("3")) {
		 //9.将传递参数分装map
	       String[] param = {"cardID", "cardCode", "cardName","kpType","memo"};
	       Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "item");
	       inInfo.setAttr(map);
	       //10.使用map参数查询获取卡片里的保养项目list和count
	       List<Map<String, String>> list = dao.query("DKKP01.queryItem", map);
	       int count = dao.count("DKKP01.countItem", map);
	       //11.将返回的卡片里的保养项目list和count添加到EiiNFO并返回客户端
	       return DeviceEiUtil.buildResult(inInfo, list, count, "item");
	   }else {
		   return inInfo;
	   }
     }
   
   /**
	 * 插入编辑卡片
	 * <p>1.获取登录人和操作时间
	 * <p>2.获取卡片和信息数据
	 * <p>3.取出项目
	 * <p>4.如果是更新操作，则先跟新卡片主信息再删除卡片里的项目信息，再重新添加项目信息;新增操作直接添加
	 * <p>5.插入或跟新卡片主信息
	 * <p>6.跟新卡片主信息
	 * <p>7.删除卡片里的项目
	 * <p>8.插入卡片主项目
	 * <p>9.循环插入项目
	 * @param eiInfo
	 * cardID             卡片id
     * cardCode           卡片编码
     * cardName           卡片名称
     * memo               卡片备注
	 * @return
	 * 新增卡片成功或编辑卡片成功
	 */
	public EiInfo insertKP(EiInfo eiInfo) {
		//1.获取登录人和操作时间
		String loginName = UserSession.getLoginName();
		String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String kpType = (String)eiInfo.get("kpType");
		
		//2.获取卡片和信息数据
		Map<String, Object> schemeMap = new HashMap<>();
		String vCardID = (String) eiInfo.get("cardID");//3.前端拿到的cardID
		//String vCardCode = (String) eiInfo.get("cardCode");//4.前端拿到的cardCode
		String cardName = (String) eiInfo.get("cardName");
		String memo = (String) eiInfo.get("memo");
		schemeMap.put("cardName", cardName);
		schemeMap.put("memo", memo);
		
		//5.取出项目
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) eiInfo.get("itemList");
		
		//6.校验参数
		if(itemList.isEmpty()) {
			eiInfo.setMsg("未添加项目信息");
			eiInfo.setStatus(-1);
			return eiInfo;
		}
		logger.info("设备保养卡片【DKKP0101】操作类型kpType==", kpType);
		/*7.kpType==2是新增,kpType==3是跟新
		 * 如果是更新操作，则先跟新卡片主信息再删除卡片里的项目信息，再重新添加项目信息;新增操作直接添加*/
		//插入或跟新卡片主信息
		String kpID;
		String kpCode;
		//8.如果是跟新
		if("3".equals(kpType)){
			kpID = vCardID;
			//9.保存卡片id
			schemeMap.put("cardID", kpID);
			//10.保存修改人为登录人
			schemeMap.put("modifyMan", loginName);
			//11.保存修改时间为当前时间
			schemeMap.put("modifyTime", operTime);
			dao.update("DKKP01.updateKp", schemeMap);//跟新卡片主信息
			//12.卡片id不为空执行删除卡片操作
			if(kpID!=null && kpID!="") {
			dao.delete("DKKP01.deleteKpItem", kpID);//删除卡片里的项目
			}
		}else {
		    //13.新增，获取卡片id为uuid
			kpID = UUID.randomUUID().toString().replace("-", "");
			//14.获取卡片编码
			kpCode = DeviceGeneCode.geneCode(DeviceBillType.DK_KPINFO);
			schemeMap.put("cardID", kpID);
			//15.保存卡片编码
			schemeMap.put("cardCode", kpCode);
			schemeMap.put("createMan", loginName);
			schemeMap.put("createTime", operTime);
			//16.保存卡片状态为新建状态
			schemeMap.put("status", "0");
			
			//17.插入卡片主项目
			dao.insert("DKKP01.insertKp", schemeMap);
		}
			
			//18.循环插入项目
			itemList.forEach(item -> {
				 Map<String, Object> insertItem = new HashMap<>();
				 //insertItem.put("itemID", UUID.randomUUID().toString());
				 insertItem.put("cardID", kpID);
				 insertItem.put("itemID_xmID", item.get("itemID_xmID"));//项目id
				 insertItem.put("jitemName", item.get("jitemName"));//作业项目名称
				 insertItem.put("itemDesc", item.get("itemDesc"));
				 insertItem.put("referenceValue", item.get("referenceValue"));
				 insertItem.put("sortNo", item.get("sortNo"));
				 insertItem.put("actualValueUnit", item.get("actualValueUnit"));
				 //19.保存卡片项目
				 dao.insert("DKKP01.insertKpItem", insertItem);
			});
			
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("2".equals(kpType)?"新增卡片成功":"编辑卡片成功");
		return outInfo;
	}
}
