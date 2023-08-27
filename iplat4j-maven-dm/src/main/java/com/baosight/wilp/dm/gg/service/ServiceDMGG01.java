package com.baosight.wilp.dm.gg.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.dm.gg.domain.DMGG01;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 宿舍公告页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMGG01.java
 * @ClassName: ServiceDMGG01
 * @Package：com.baosight.wilp.dm.gg.service
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMGG01 extends ServiceBase {

	/**
	 * 此方法用于页面初始化加载
	 *
	 * 逻辑处理：
	 * 1.调用query方法
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 此方法用于查询宿舍公告页面列表信息
	 *
	 * 逻辑处理：
	 * 1.调用sql方法DMGG01.query，查询dorms_room_notice宿舍公告管理表的信息
	 *
	 * @Title: query
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		// 调用sql方法DMGG01.query，查询dorms_room_notice宿舍公告管理表的信息
		EiInfo outInfo = super.query(inInfo, "DMGG01.query", new DMGG01());
		return outInfo;
	}

	/**
	 * 此方法用于新增公告
	 *
	 * 逻辑处理：
	 * 1.获取前端参数，noticeNo-公告序号；noticeType-公告分类；notice-公告内容
	 * 2.调用sql方法DMGG01.insert向dorms_room_notice宿舍公告管理表插入信息
	 *
	 * @Title: insert
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		String id = UUID.randomUUID().toString();	/* 主键*/
		/**
		 * 1.获取前端参数，noticeNo-公告序号；noticeType-公告分类；notice-公告内容
		 */
		String noticeNo = inInfo.getString("noticeNo"); /* 公告序号 */
		String noticeType = inInfo.getString("noticeType"); /* 公告分类 */
		String notice = inInfo.getString("notice"); /* 公告内容 */
		String recCreator = UserSession.getUser().getUsername();		/* 创建人*/
        String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		/* 创建时间*/
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
		map.put("noticeNo", noticeNo);
		map.put("noticeType", noticeType);
        map.put("notice", notice);
        map.put("recCreator", recCreator);
        map.put("recCreateTime", recCreateTime);
		/**
		 * 2.调用sql方法DMGG01.insert向dorms_room_notice宿舍公告管理表插入信息
		 */
		dao.insert("DMGG01.insert",map);
	    EiInfo outInfo = new EiInfo();
	    outInfo.set("id", id);
		/*
		 * 3、给入住的人发企业微信宿舍通告。
		 */
		String appCode = "AP00002";
		//获取入住人员编号
		List<String> workNoList = new ArrayList<>();
		List<Map<String,Object>> list = dao.query("DMRZ01.queryworkNo",null);
		for (int i =list.size();i>0;i--){
			Map<String, Object> map1 = list.get(i-1);
			workNoList.add((String) map1.get("workNo"));
		}

		List<String> paramList = new ArrayList<>();
		String Msg = "关于宿舍"+noticeType+"："+notice;
		paramList.add(Msg);

		//发送企业微信
		BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);

		return outInfo;
	}

	/**
	 * 此方法用于修改公告
	 *
	 * 逻辑处理：
	 * 1.调用sql方法DMGG01.update修改dorms_room_notice宿舍公告管理表的信息
	 * 2、给入住的人发企业微信宿舍通告。
	 *
	 * @Title: update
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		// 调用sql方法DMGG01.update修改dorms_room_notice宿舍公告管理表的信息
		EiInfo outInfo = super.update(inInfo, "DMGG01.update");
		/*
		 * 2、给入住的人发企业微信宿舍通告。
		 */
		String appCode = "AP00002";
		List<String> paramList = new ArrayList<>();
		String Msg = "关于宿舍"+inInfo.getBlock("result").getCell(0,"noticeType")+"："+inInfo.getBlock("result").getCell(0,"notice");
		paramList.add(Msg);

		//获取入住人员编号
		List<String> workNoList = new ArrayList<>();
		List<Map<String,Object>> list = dao.query("DMRZ01.queryworkNo",null);
		for (int i =list.size();i>0;i--){
			Map<String, Object> map1 = list.get(i-1);
			workNoList.add((String) map1.get("workNo"));
		}

		//发送企业微信
		BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);
		return outInfo;
	}

	/**
	 * 此方法用于删除公告
	 *
	 * 逻辑处理：
	 * 1.调用sql方法DMGG01.delete删除dorms_room_notice宿舍公告管理表的信息
	 *
	 * @Title: delete
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		//调用sql方法DMGG01.delete删除dorms_room_notice宿舍公告管理表的信息
		EiInfo outInfo = super.delete(inInfo, "DMGG01.delete");
		return outInfo;
	}
	
	
	/**
	 * 此方法用于获取公告数据
	 *
	 * 1.获取参数noticeType-公告分类
	 * 2.调用sql方法DMGG01.queryHoldnotice查询dorms_room_notice宿舍公告管理表的信息
	 *
	 * @Title: queryHoldnotice
	 * @param： inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryHoldnotice(EiInfo inInfo) {
		/**
		 * 1.获取参数noticeType-公告分类
		 */
		Map<String,String> map = new HashMap<>();
		map.put("noticeType",inInfo.getString("noticeType"));
		/**
		 * 2.调用sql方法DMGG01.queryHoldnotice查询dorms_room_notice宿舍公告管理表的信息
		 */
		List<DMGG01> list = dao.query("DMGG01.queryHoldnotice", map);
		inInfo.addBlock("notice").addRows(list);
		inInfo.getBlock("notice").setBlockMeta(new DMGG01().eiMetadata);
		return inInfo;
	}
	
}
