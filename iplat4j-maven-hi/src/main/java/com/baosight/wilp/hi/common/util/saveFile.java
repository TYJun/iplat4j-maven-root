package com.baosight.wilp.hi.common.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.xservices.xs.util.UserSession;

import java.io.File;
import java.util.*;

/**
 * 保存标识附件Service
 * 
 * <p>保存标识附件</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  saveFile
 * @ClassName:  saveFile
 * @Package com.baosight.wilp.common.util.saveFile
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年8月21日 下午1:13:50
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class saveFile extends ServiceBase {


	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	/**
	 * 保存医院标识附件
	 *
	 * @param fileObj id 主键
	 *                docId 项目ID
	 *                relationId 附件ID
	 *                fileName 附件名称
	 *                filePath 附件路径
	 *                fileSize 附件大小
	 *                remark 附件说明
	 *                archiveFlag 归档标记
	 *                datagroupCode 账套
	 *                recCreator 记录创建责任者
	 *                recCreateTime 记录创建时间
	 *                recRevisor 记录修改责任者
	 *                recReviseTime 记录修改时间
	 * @Title: saveFile
	 * <p>1.如果入参不为空
	 * <p>2.判断要删除的文件是否为空
	 * <p>3.循环修改删除文件状态
	 * <p>4.删除旧的附件信息
	 * <p>5.增强循环
	 * <p>6.实体转换为参数
	 * <p>7.获取当前登录用户信息
	 * <p>8.调用插入方法
	 * <p>9.物理删除文件
	 * @return: void
	 */
	public static void saveFile(Object fileObj, String id, List<Map<String, Object>> deleteFile, String status) {
		String path = System.getProperty("user.dir");
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (fileObj != null) {
			// 参数赋值
			list = (List<Map<String, Object>>) fileObj;
		}
		//2.判断要删除的文件是否为空
		List<String> filePath = new ArrayList<>();
		if (!deleteFile.isEmpty()) {
			//3.循环修改删除文件状态，将archiveFlag置于2,留存在数据库
			for (Map<String, Object> map : deleteFile) {
				map.put("relationId", id);
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				map.put("recRevisor", (String) staffByUserId.get("name"));
				map.put("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("HIBZ01.updateFile", map);
				filePath.add(path + File.separator + map.get("filePath"));
			}
		}
		Map<String, String> pMap1 = new HashMap<>(4);
		pMap1.put("id", id);
		pMap1.put("fileStatus", status);
		// 4.删除旧的附件信息，通过id和archiveFlag为1
		dao.delete("HIBZ01.deleteFile", pMap1);
		// 5.增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			HiFile att = new HiFile();
			// 6.实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setRelationId(id);
			att.setDataGroupCode(DatagroupUtil.getDatagroupCode());
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			att.setRecCreator((String) staffByUserId.get("name"));
			att.setArchiveFlag("1");
			att.setFileStatus(status);
			// 8.调用插入方法，将archiveFlag 置于1
			dao.insert("HIBZ01.insertFile", att);
			}
		//9.物理删除文件
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


}
