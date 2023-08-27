package com.baosight.wilp.vi.dj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.vi.utils.ViUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年06月15日 20:14
 */
public class ServiceVIDJ0101 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String workNo = (String) staffByUserId.get("workNo");
		String name = (String) staffByUserId.get("name");
		String deptNum = (String) staffByUserId.get("deptNum");
		String deptName = (String) staffByUserId.get("deptName");
		inInfo.set("inqu_status-0-interviewerWorkNo",workNo);
		inInfo.set("inqu_status-0-nterviewerName",name);
		inInfo.set("inqu_status-0-visitingDeptId",deptNum);
		inInfo.set("inqu_status-0-visitingDeptName",deptName);
		inInfo.set("inqu_status-0-vistingBeginDate", DateUtils.curDateStr10());
		return inInfo;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	public EiInfo removePicByUid(EiInfo info){
		String docId = info.getString("docId");
		int delete = dao.delete("VIDJ01.removePicByUid", docId);
		return info;
	}

	// 访客新增
	public EiInfo confirm(EiInfo info){
		// 获取登记类型（PC/APP）
		String type = info.getString("type");
		String batNo = creatBatNo();
		String nterviewerName = "";
		switch (type){
			case "PC":
				// PC端是单个人员登记的
				EiBlock block = info.getBlock("inqu_status");
				Map<String,Object> map = block.getRow(0);
				List<Map<String,Object>> rows = (List<Map<String, Object>>) info.get("rows");
				List<Map<String,Object>>  fileList = (List<Map<String, Object>>) info.get("fileList");
				List<Map<String,Object>>  workPicList = (List<Map<String, Object>>) info.get("workPicList");
				// 保存主表信息
				map.put("nterviewerPhone","");
				map.put("creatIp","");
				map.put("creatorType","被访者");
				map.put("creatorIdentity",type);
				map.put("createTime",DateUtils.curDateTimeStr19());
				map.put("auditorStep","1");
				map.put("batNo",batNo);
				dao.insert("VIDJ01.insertViVistingInfo",map);
				// 保存明细表信息
				dao.insert("VIDJ01.insertViVistingInfoDetail",new HashMap<String,Object>(){{
					put("batNo",batNo);
					put("rows",rows);
				}});
				// 保存照片
				if (CollectionUtils.isNotEmpty(fileList)){
					// 保存文件和主表关联关系
					dao.insert("VIDJ01.insertViVisitingFile",new HashMap<String,Object>(){{
						put("batNo",batNo);
						put("fileList",fileList);
					}});
					new Thread(()->{
						dao.insert("VIDJ01.insertViAttachFile",fileList);
					}).run();
				}
				// 保存附件信息
				if (CollectionUtils.isNotEmpty(workPicList)){
					for (Map workPicMap:workPicList) {
						workPicMap.put("batNo",batNo);
					}
					dao.insert("VIDJ01.insertViAttachFile",workPicList);
				}
				// 保存审批记录
				dao.insert("VIDJ01.insertViAuditlog",new HashMap<String,Object>(){{
					put("visitingId",batNo);
					put("auditTime",DateUtils.curDateTimeStr19());
					put("auditorMan",map.get("interviewerWorkNo"));
					put("auditorIp","");
					put("auditorClientType",type);
					put("desc","");
					put("auditFlag",1);
				}});
				break;
			case "APP":
				// APP支持多人
				// 获取访问科室/被访人姓名/访问时间
				Map<String, Object> infoMap = new HashMap<>(8);
				String visitingDeptName = info.getString("visitingDeptName");
				String interviewerWorkNo = info.getString("workNo");
				nterviewerName = info.getString("nterviewerName");
				String vistingBeginDate = info.getString("vistingBeginDate");
				infoMap.put("visitingDeptId",info.getString("deptNum"));
				infoMap.put("visitingDeptName",visitingDeptName);
				infoMap.put("interviewerWorkNo",interviewerWorkNo);
				infoMap.put("nterviewerName",nterviewerName);
				infoMap.put("nterviewerPhone","");
				infoMap.put("creatIp","");
				infoMap.put("vistingBeginDate",vistingBeginDate);
				infoMap.put("creatorType","访客");
				infoMap.put("creatorIdentity",type);
				infoMap.put("createTime",DateUtils.curDateTimeStr19());
				infoMap.put("auditorStep","0");
				infoMap.put("visitApp",info.getString("visitApp"));
				infoMap.put("batNo",batNo);
				List<Map<String,Object>> visitorArr = (List) info.get("visitorArr");
				// 保存主表信息
				dao.insert("VIDJ01.insertViVistingInfo",infoMap);
				if (CollectionUtils.isNotEmpty(visitorArr)){
					// 保存访客信息
					dao.insert("VIDJ01.insertViVistingInfoDetail",new HashMap<String,Object>(){{
						put("batNo",batNo);
						put("rows",visitorArr);
					}});
				} else {
					info.setStatus(-1);
					info.set("AppStaus","500");
					info.setMsg("访客不能为空");
					return info;
				}
				List<Map<String,Object>> fileListApp = new ArrayList<>();
				List<Map<String,Object>> picListApp = new ArrayList<>();
				for (int i = 0; i < visitorArr.size(); i++) {
					Map<String, Object> visitorMap = visitorArr.get(i);
					List<String> imgList = (List) visitorMap.get("imgList");
					if (CollectionUtils.isNotEmpty(imgList)){
						for (int j = 0; j < imgList.size(); j++) {
							Map<String, Object> fileMapApp = new HashMap<>();
							String docId = UUID.randomUUID().toString();
							visitorMap.put("docId",docId);
							visitorMap.put("visitId",visitorMap.get("visitId"));
							fileMapApp.put("visitId",visitorMap.get("visitId"));
							fileMapApp.put("docId", docId);
							fileMapApp.put("fileName",visitorMap.get("guestName")+".jpeg");
							fileMapApp.put("mimeType","jpeg");
							fileMapApp.put("uploadTime",DateUtils.curDateTimeStr19());
							String imgBase64 = imgList.get(j);
							if (!"".equals(imgBase64) && imgBase64!=null){
								String[] split = imgBase64.split(",");
								if (!split[0].equals("")){
									String base64 = split[1];
									fileMapApp.put("fileContent",base64);
								}
							}
							fileListApp.add(fileMapApp);
							picListApp.add(fileMapApp);
						}
					}
				}
				if (CollectionUtils.isNotEmpty(fileListApp)){
					// 保存文件
					dao.insert("VIDJ01.insertViAttachFile",fileListApp);
				}
				if (CollectionUtils.isNotEmpty(picListApp)){
					// 保存关联关系
					dao.insert("VIDJ01.insertViVisitingFile",new HashMap<String,Object>(){{
						put("batNo",batNo);
						put("fileList",picListApp);
					}});
				}
				if (ViUtils.getDataCode("WILP.vi.pushWxMsg")){
					new Thread(() -> {
						String appCode = "AP00002";
						List<String> workNoList = new ArrayList<>();
						List<String> paramList = new ArrayList<>();
						String workNo = (String) infoMap.get("interviewerWorkNo");
						workNoList.add(workNo);
						if (workNoList.isEmpty()){
							return;
						} else {
							paramList.add("您有一条访客申请，请注意审批，点击进入系统:http://mzsrmyy.yyhq365.cn/thirdpartieswechat/authVisit?type=visit");
							BaseDockingUtils.pushWxMsg(workNoList, paramList,"TP00001", appCode);
						}
					}).run();
				}
				break;
		}
		// 消息推送被访问的老师获得消息
		return info;
	}

	public EiInfo showTempPic(EiInfo info) {
		Object object = info.get("picList");
		List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		list.forEach(map ->{
			map.put("base64", CommonUtils.imageToBase64Str(getFilePath(map.get("docId"))));
		});
		info.set("list", list);
		return info;
	}

	public static String getFilePath(String docId) {
		//获取文件管理器
		PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
		//获取附件信息
		Map<String,String> docMap = fileUpLoadManager.getDocById(docId);
		//获取路径
		if(docMap == null || docMap.isEmpty()){
			return "";
		} else {
			return docMap.get("realPath") + docMap.get("chgName");
		}
	}

	// 创建批次号并且加锁
	public synchronized String creatBatNo(){
		List<Map<String,Integer>> batNoList = dao.query("VIDJ01.queryNextBatNo", new HashMap<>(8));
		return String.valueOf(batNoList.get(0).get("Auto_increment"));
	}

	public EiInfo creatBatNo(EiInfo info){
		String s = creatBatNo();
		info.set("no",s);
		return info;
	}
}
