package com.baosight.wilp.dm.fj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍附件上传/下载方法Service
 *
 * @Title: ServiceDMFJ01.java
 * @ClassName: ServiceDMFJ01
 * @Package：com.baosight.wilp.dm.fj.service
 * @author: fangzekai
 * @date: 2022年3月24日 上午9:53:23
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMFJ01 extends ServiceBase {
	/**
	 * 保存附件
	 * 逻辑处理
	 *    1.获取当前登录人信息，如果登录人不存在，提示错误信息
	 *    2.获取附件列表 获取执行删除操作的附件列表 获取前端传来的manId
	 *    3.执行保存操作
	 *    4.设置状态和提示信息 返回inInfo
	 *
	 * @throws
	 * @Title: saveAttachment
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo saveAttachment(EiInfo inInfo) {
		/*
		 * 1. 获取当前登录人信息，如果登录人不存在，提示错误信息.
		 */
		// 调用工具类DMUtils查登陆人的用户信息
		Map<String, Object> userInfo = DMUtils.getUserInfo(null);
		// 判断账号是否为空，为空则提示。
		if(userInfo == null || userInfo.isEmpty()){
			inInfo.setMsg("您的账号存在问题，请联系管理员");
			return inInfo;
		}
		// 获取附件列表。
		Object fileObj = inInfo.get("fileList");
		// 获取执行删除操作的附件列表
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.get("deleteFile");
		// 获取前端传来的manId
		String manId = inInfo.getString("manId");
		saveFile(fileObj, manId, deleteFile);
		dao.update("DMFJ01.updatePayStatus",manId);
		inInfo.setMsg("保存附件成功！");
		inInfo.setMsgKey("200");
		return inInfo;
	}

	/**
	 * 保存附件信息
	 *
	 * @param fileObj
	 * @param id
	 * @param deleteFile
	 * @Title: saveFile
	 * @return: void
	 */
	private void saveFile(Object fileObj, String id, List<Map<String, Object>> deleteFile) {
		String path = System.getProperty("user.dir");
		String nameNo = UserSession.getUser().getUsername();
		Map<String, Object> userInfo = DMUtils.getUserInfo(nameNo);
		String name = userInfo.get("name").toString();
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (fileObj != null) {
			// 入参类型转换
			list = (List<Map<String, Object>>) fileObj;
		}
		//判断要删除的文件是否为空
		List<String> filePath = new ArrayList<>();
		if (!deleteFile.isEmpty()) {
			//循环修改删除文件状态
			for (Map<String, Object> map : deleteFile) {
				map.put("manId", id);
				map.put("recRevisor", nameNo);
				map.put("recReviseName", name);
				map.put("recReviseTime", currentTime);
				dao.update("DMFJ01.updateFile", map);
				filePath.add(path + File.separator + map.get("attachmentPath"));
			}
		}
		//删除旧的附件信息
		dao.delete("DMFJ01.deleteFile", id);
		// 增强循环
		for (Map<String, Object> map : list) {
			map.put("id",UUID.randomUUID().toString());
			map.put("manId",id);
			map.put("recCreator",nameNo);
			map.put("recCreateName",name);
			map.put("recCreateTime",currentTime);
			map.put("attachmentFlag","1");
			// 新增
			dao.insert("DMFJ01.insertFile", map);
		}
		//物理删除文件
		if (!filePath.isEmpty()) {
			//循环删除文件
			for (String fileP : filePath) {
				//获取文件
				File file = new File(fileP);
				//判断是否存在
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 查询附件
	 * 1、获取前端传来的manId值.
	 * 2、将manId值放入map给DMFJ01.queryAttachmentList 做参数去查询当前manId的附件List.
	 * 3、判断是否取得数据.
	 *
	 * @throws
	 * @Title: queryAttchmentFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo queryAttchmentFile(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的manId值.
		 */
		String manId = "";
		if(inInfo.get("manId") != null || !"".equals(inInfo.get("manId"))) {
			manId = inInfo.getString("manId");
		}
		/*
		 * 2、将manId值放入map给DMFJ01.queryAttachmentList 做参数去查询当前manId的附件List.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("manId", manId);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMFJ01.queryAttachmentList", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if(list !=null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "resultX", CommonUtils.createBlockMeta(list.get(0)), list, list.size());
		} else {
			inInfo.setMsg("未获取到数据");
			return inInfo;
		}
	}

}
