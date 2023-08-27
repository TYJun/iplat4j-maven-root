package com.baosight.wilp.dm.xx.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍信息管理新增页面service
 *
 * @Title: ServiceDMXX0101.java
 * @ClassName: ServiceDMXX0101
 * @Package：com.baosight.wilp.dm.xx.service
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXX0101 extends ServiceBase{
	/**
	 * 页面初始化加载
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 	宿舍信息登记.
	 *  1、获取当前用户信息.
	 * 	2、调用本地服务DMXX0101.insertDMInfoTable将宿舍信息插入宿舍信息表中.
	 * 	3、调用本地服务DMXX0101.savePicture将宿舍照片插入宿舍的登记图片中.
	 *  4、返回操作结果.
	 *
	 * @Title: insert
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
	 */
	public EiInfo insert(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		// 生成宿舍对应id
		String id = UUID.randomUUID().toString();
		inInfo.set("id", id);
		// 创建人工号
		inInfo.set("recCreaterNo", loginName);
		/*
		 * 2、调用本地服务DMXX0101.insertDMInfoTable将宿舍信息插入宿舍信息表中.
		 */
		// 将工单信息插入宿舍信息表中
		inInfo.set(EiConstant.serviceName, "DMXX0101");
		inInfo.set(EiConstant.methodName, "insertDMInfoTable");
		EiInfo outInfo = XLocalManager.call(inInfo);

		/*
		 * 3、调用本地服务DMXX0101.savePicture将宿舍照片插入宿舍的登记图片中.
		 */
		inInfo.set(EiConstant.serviceName, "DMXX0101");
		inInfo.set(EiConstant.methodName, "savePicture");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、返回操作结果.
		 */
		outInfo.addMsg("操作成功");
		outInfo.setMsgKey("200");

		return outInfo;
	}

	/**
	 * 宿舍信息表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXX0101.insertDMInfoTable 进行数据的插入，插入宿舍信息表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertDMInfoTable
	 * @param:  @param inInfo
	 *      id ：主键
	 *      building  : 宿舍楼
	 *      floor  : 宿舍层
	 *      roomNo  : 宿舍号
	 *      roomName : 宿舍总称(楼+层+宿舍号)
	 *      bedNum  : 床位数
	 *      typeCode : 房间类型(1男生宿舍/0女生宿舍)
	 *      dormPosition : 宿舍位置：院内、院外
	 *      dormArea  : 房屋面积："<50㎡"、"50-100㎡"、">100㎡"
	 *      elevatorRoom ： 是否为电梯房
	 *      priBathroom : 独立卫生间：有、没有
	 *      rent  : 房租
	 *      manageFee : 管理费
	 *      annualFee : 年费
	 *      note : 备注信息
	 *      recCreaterNo ： 创建人工号
	 *      recCreaterName : 创建人
	 *      recCreateTime : 创建时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertDMInfoTable(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");   /*主键*/
		String building = inInfo.get("building") == null ? "" : inInfo.getString("building");     /* 宿舍楼*/
		String floor = inInfo.get("floor") == null ? "" : inInfo.getString("floor");     /* 宿舍层 */
		String roomNo = inInfo.get("roomNo") == null ? "" : inInfo.getString("roomNo");     /* 宿舍号*/
		String roomName = inInfo.get("roomName") == null ? "" : inInfo.getString("roomName");     /* 宿舍总称(楼+层+宿舍号) */
		String bedNum = inInfo.get("bedNum") == null ? "" : inInfo.getString("bedNum");       /* 床位数*/
		String remainingBedNum = inInfo.get("bedNum") == null ? "" : inInfo.getString("bedNum"); /* 初始剩余床位数*/
		String dormProperties = inInfo.get("dormProperties") == null ? "" : inInfo.getString("dormProperties");     /* 宿舍属性（学生宿舍/职工宿舍）*/
		String typeCode = inInfo.get("typeCode") == null ? "" : inInfo.getString("typeCode");     /* 宿舍类型（男/女生宿舍）*/
		String openRoom = inInfo.get("openRoom") == null ? "" : inInfo.getString("openRoom");     /* 是否开放选房(0: 未开放，1:已开放)*/
		String elevatorRoom = inInfo.get("elevatorRoom") == null ? "" : inInfo.getString("elevatorRoom");        /* 是否为电梯房*/
		String priBathroom = inInfo.get("priBathroom") == null ? "" : inInfo.getString("priBathroom");        /* 是否有独立卫生间*/
		String dormPosition = inInfo.get("dormPosition") == null ? "" : inInfo.getString("dormPosition");     /* 宿舍位置*/
		String dormArea = inInfo.get("dormArea") == null ? "" : inInfo.getString("dormArea");      /* 宿舍大概面积*/
		String rent = inInfo.get("rent") == null ? "0" : inInfo.getString("rent");       /* 房租*/
		String manageFee = inInfo.get("manageFee") == null ? "0" : inInfo.getString("manageFee");        /* 管理费*/
		String annualFee = inInfo.get("annualFee") == null ? "0" : inInfo.getString("annualFee");        /* 年费*/
		String materials = inInfo.get("materials") == null ? "" : inInfo.getString("materials");     /* 宿舍物资信息*/
		String note = inInfo.get("note") == null ? "" : inInfo.getString("note");     /* 宿舍备注信息*/
		String recCreaterNo = inInfo.get("recCreaterNo") == null ? UserSession.getUser().getUsername() : inInfo.getString("recCreaterNo");        /* 创建人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(recCreaterNo);
		String recCreaterName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*创建人名称*/
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 创建时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("id",id);
		map.put("building",building);
		map.put("floor",floor);
		map.put("roomNo",roomNo);
		map.put("roomName",roomName);
		map.put("bedNum",bedNum);
		map.put("remainingBedNum",remainingBedNum);
		map.put("dormProperties",dormProperties);
		map.put("typeCode",typeCode);
		map.put("openRoom",openRoom);
		map.put("elevatorRoom",elevatorRoom);
		map.put("priBathroom",priBathroom);
		map.put("dormPosition",dormPosition);
		map.put("dormArea",dormArea);
		map.put("rent",rent);
		map.put("manageFee",manageFee);
		map.put("annualFee",annualFee);
		map.put("materials",materials);
		map.put("note",note);
		map.put("recCreaterNo",recCreaterNo);
		map.put("recCreaterName",recCreaterName);
		map.put("recCreateTime",recCreateTime);
		/*
		 * 3、以map作为参数执行 DMXX0101.insertDMInfoTable 进行数据的插入，插入宿舍信息表.
		 */
		dao.insert("DMXX0101.insertDMInfoTable", map);

		//获取列表数据
		List<Map<String,String>> rows =(List<Map<String,String>>)inInfo.get("htb");
		for (Map<String,String> map1:rows) {
			//赋值map
			map1.put("id", UUID.randomUUID().toString());
			map1.put("dormId", id);
			map1.put("recCreator", recCreaterName);
			//插入项目
			dao.insert("DMXX0101.dormMarInsert", map1);
		}

		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		return outInfo;
	}

	/**
	 * 保存宿舍图片.
	 *
	 * <p>1.获取参数</br>
	 * 	 2.获取图片的保存路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）</br>
	 * 	 3.遍历图片List</br>
	 *   4.判断图片是pc端上传还是app端上传</br>
	 *   5.pc端上传，直接保存图片信息到dorms_room_pic表</br>
	 *   6.app端上传，先将图片base64码转成图片文件保存，再保存图片信息到dorms_room_pic表
	 * </p>
	 *
	 * @Title: savePicture
	 * @param:  @param inInfo
	 * 		taskNo: 任务单号
	 * 		dataGroupCode： 账套（院区）
	 * 		picList ： 图片集合List
	 * 			fileName:图片名称
	 * 			path： 图片路径（PC端使用）
	 * 			base64 ： 图片Base64码(APP端使用)
	 * 			type : 上传图片的流程 RE=登记时上传，FS=完工时上传
	 * @param:  @return
	 * @param:  @throws IOException
	 * @return: EiInfo
	 * 		msg : 提示消息
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo savePicture(EiInfo inInfo) throws IOException {
		//获取参数
		Object object = inInfo.get("picList");
		List<Map<String,String>> picList = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		//获取操作人
		String recCreator = DMUtils.isEmpty(inInfo.get("recCreaterNo"), UserSession.getUser().getUsername());		/* 创建人*/
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		/* 创建时间*/
		//获取图片存储基础路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）
		String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String destPath = docRootDir+ "/dm/img/";
		//遍历图片List
		picList.forEach(map -> {  //{"fileName":"a.jpg","docId":"","base64":"","type":"RE||FS"}
			map.put("id", UUID.randomUUID().toString());
			map.put("recCreator", recCreator);
			map.put("recCreateTime", recCreateTime);
			map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
			map.put("roomId", inInfo.getString("id"));
			//判断图片是pc端上传还是app端上传
			if(StringUtils.isBlank(map.get("docId"))){//App端
				//app端上传，先将图片base64码转成图片文件保存，再保存图片信息到cs_tp表
				String[] files = map.get("fileName").split(".");
				String path = destPath + Calendar.getInstance().getTimeInMillis()+ (files.length > 1 ? files[1] : ".png");
				CommonUtils.base64StrToImage(map.get("base64"), path);//存储图片
				map.put("path", path);
			} else {
				map.put("path", DMUtils.getFilePath(map.get("docId")));
			}
			dao.insert("DMXX0101.insertPic", map);
		});
		inInfo.setMsg("操作成功");
		return inInfo;
	}

	/**
	 * 回显上传的图片.
	 *
	 * <p>根据图片的路径，获取到图片文件，再将图片文件转成base64码</p>
	 *
	 * @Title: showPic
	 * @param:  @param info
	 * 		roomId ： 宿舍ID
	 * 		type ： 上传图片的流程 RE=登记时上传
	 * @param:  @return
	 * @return: EiInfo
	 * 		base64 ： 图片base64码
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo showPic(EiInfo info) {
		Map<String,String> pMap = new HashMap<>(4);
		pMap.put("roomId", info.getString("roomId"));
		List<Map<String,String>> list=dao.query("DMXX0101.showPic",pMap);
		list.forEach(map ->{
			map.put("picId", map.get("picId"));
			map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
			map.put("path","");
		});
		info.set("list", list);
		return info;
	}

	/**
	 * PC端登记宿舍信息时上传图片时回显
	 *
	 * <p>获取页面参数,将参数中的图片路径，转换成图片base64码，然后返回页面</p>
	 *
	 * @Title: showTempPic
	 * @param:  @param info
	 * 		picList：图片信息集合
	 * 		path ： 图片保存路径
	 * @param:  @return
	 * @return: EiInfo
	 * 		 base64 ： 图片base64码
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo showTempPic(EiInfo info) {
		Object object = info.get("picList");
		List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		list.forEach(map ->{
			map.put("base64", CommonUtils.imageToBase64Str(DMUtils.getFilePath(map.get("docId"))));
		});
		info.set("list", list);
		return info;
	}

}

