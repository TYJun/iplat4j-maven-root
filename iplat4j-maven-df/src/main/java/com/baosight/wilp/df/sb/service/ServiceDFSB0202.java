package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.df.sb.domain.DFSB03;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;

/**
 * <p>页面初始化</p>
 * <p>页面查询</p>
 *
 * @Title: ServiceDFSB0202.java
 * @ClassName: ServiceDFSB0202
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年9月2日 下午5:09:54
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB0202 extends ServiceBase {

       /**
        * 页面初始化方法
        * @Title: initLoad
        * @param info
        * id id
        * @return
		* Id                  id
        * workNo              工号
        * machineCode         特种设备编码
        * machineId           特种设备Id
        * machineName         特种设备名称
        * innerMachineCode    特种设备内部编码
        * checkType           检查类型
        * statusCode          工单状态
        * thisCheckDate       本次检验时间
        * thisFinishDate      本次检验完工时间
        * nextCheckDate       下次检验时间
        * @see ServiceBase#initLoad(EiInfo)
        */
		public EiInfo initLoad(EiInfo info) {
			//获取id
			String id = (String)info.getAttr().get("id");
			info.set("id",id);
			//返回
			return this.edit(info);
			//return info;
		}
        /**
        *
        * (设备检验页面编辑方法)
        * <p>1.获取设备信息，查询数据回显数据</p>
        * @Title: edit
        * @param info
		* Id id
		* workNo              工号
		* machineCode         特种设备编码
		* machineId           特种设备Id
		* machineName         特种设备名称
		* innerMachineCode    特种设备内部编码
		* checkType           检查类型
		* statusCode          工单状态
		* thisCheckDate       本次检验时间
		* thisFinishDate      本次检验完工时间
		* nextCheckDate       下次检验时间
        * @return: EiInfo
		* Id id
		* workNo              工号
		* machineCode         特种设备编码
		* machineId           特种设备Id
		* machineName         特种设备名称
		* innerMachineCode    特种设备内部编码
		* checkType           检查类型
		* statusCode          工单状态
		* thisCheckDate       本次检验时间
		* thisFinishDate      本次检验完工时间
		* nextCheckDate       下次检验时间
        */
		public EiInfo edit(EiInfo info) {
			//创建map
			Map<String, String> map = new HashMap<String, String>();
			//map中存数据
			map.put("id",info.getString("id"));
			//查询数据
			List<Map<String, Object>> list = dao.query("DFSB02.queryId", map);
			//创建eiInfo对象
			EiInfo eiInfo = new EiInfo();
			if(list.size() > 0) {
				eiInfo.setAttr(list.get(0));
			}
			//查询上传文件
			Map<String, String> map1 = new HashMap<>();
			//获取设备id
			String relateId = (String)info.get("id");
			//封装数据
			map1.put("relateId", relateId);
			map1.put("module","1");
			//查询附件
			List<Map<String,String>> list1 = dao.query("DFSB02.queryFile",map1);
			//放入行中
			eiInfo.addRows("result", list1);
			//返回
			return eiInfo;

		}


		/**
		 * 更新设备检验工单方法
		 * <p>1.通过id获取设备所有检验信息</p>
		 * <p>2.执行更新方法，更新数据</p>
		 * @param info
			 * Id id
			 * workNo              工号
			 * machineCode         特种设备编码
			 * machineId           特种设备Id
			 * machineName         特种设备名称
			 * innerMachineCode    特种设备内部编码
			 * checkType           检查类型
			 * statusCode          工单状态
			 * thisCheckDate       本次检验时间
			 * thisFinishDate      本次检验完工时间
			 * nextCheckDate       下次检验时间
			 * @return
			 *  Id                 id
			 * workNo              工号
			 * machineCode         特种设备编码
			 * machineId           特种设备Id
			 * machineName         特种设备名称
			 * innerMachineCode    特种设备内部编码
			 * checkType           检查类型
			 * statusCode          工单状态
			 * thisCheckDate       本次检验时间
			 * thisFinishDate      本次检验完工时间
			 * nextCheckDate       下次检验时间
		 * @see ServiceBase#update(EiInfo)
		 */
		public EiInfo update(EiInfo info) {
			//创建map
			Map<String, String> map = new HashMap<String, String>();
			   //获取id
			   String id = info.getString("id");
			   //获取工单号
			   String taskNo = info.getString("taskNo");
			   //特种设备编码
			   String machineCode = info.getString("machineCode");
			   //特种设备id
			   String machineId = info.getString("machineId");
			   //特种设备名称
			   String machineName = info.getString("machineName");
			   //特种设备内部编码
			   String innerMachineCode = info.getString("innerMachineCode");
			   //获取检验类型
			   String checkType = info.getString("checkType");
			   //获取状态编码
			   String statusCode = info.getString("statusCode");
			   //获取本次检验时间
			   String thisCheckDate = info.getString("thisCheckDate");
			   //获取本次检验完工时间
			   String thisFinishDate = info.getString("thisFinishDate");
			   //获取下去检验时间
			   String nextCheckDate = info.getString("nextCheckDate");

			   map.put("id", id);
			   map.put("taskNo", taskNo);
			   map.put("machineCode", machineCode);
			   map.put("statusCode", statusCode);
			   map.put("machineId", machineId);
			   map.put("machineName", machineName);
			   map.put("innerMachineCode", innerMachineCode);
			   map.put("checkType", checkType);
			   map.put("thisCheckDate", thisCheckDate);
			   map.put("thisFinishDate", thisFinishDate);
			   map.put("nextCheckDate", nextCheckDate);
			   //执行update SQL
			   dao.insert("DFSB02.update",map);
			//获取文件集合
			Object fileObj = info.get("fileList");
			//调用保存附件的方法
			saveFile(fileObj,id);
			   //返回
			return info;
		}

		/**
		 * 保存附件方法
		 * <p>1.获取前台传来的文件集合</p>
		 * <p>2.对附件字段赋值</p>
		 * <p>3.将字段保存插入附件表中</p>
		 * @MethodName:saveFile
		 * @param：fileObj
		 * id				设备id
		 * fileName:   		文件名
		 * filePath:		文件路径
		 * fileModule：  	文件类型
		 * relateId：		设备id关联附件id
		 * fileType：       附件类型
		 * dataGroupCode：	账套
		 * recCreator：		创建人
		 * recCreateTime：	创建时间
		 * @return：void
		 * @param：
		 * 	 fileName:   		文件名
		 * 	 filePath:		文件路径
		 * 	 fileModule：  	文件类型
		 * 	 relateId：		设备id关联附件id
		 * 	 fileType：       附件类型
		 * 	 dataGroupCode：	账套
		 * 	 recCreator：		创建人
		 * 	 recCreateTime：	创建时间
		 */
		@SuppressWarnings("unchecked")
		public void saveFile(Object fileObj, String id) {
			//创建集合
			List<Map<String,Object>> list = new ArrayList<>();
			//判断集合是否为空
			if(fileObj != null){
				list = (List<Map<String, Object>>) fileObj;
			}
			//删除旧的附件信息
			HashMap<String, String> pMap = new HashMap<>();
			pMap.put("id",id);
			pMap.put("type","1");
			pMap.put("module","1");
			dao.delete("DFSB0101.deleteFile", pMap);
			//获取账套
			String dataGroupCode = DatagroupUtil.getDatagroupCode()==null ? " " :DatagroupUtil.getDatagroupCode();
			//循环新增
			for (Map<String, Object> map : list) {
				//创建实体类对象
				DFSB03 att = new DFSB03();
				//将获取的数据封装到实体对象中
				att.fromMap(map);
				//生成id
				att.setId(UUID.randomUUID().toString());
				//与设备id关联
				att.setRelateId(id);
				//所属模块
				att.setFileModule("1");
				//附件类型
				att.setFileType("1");
				//账套
				att.setDataGroupCode(dataGroupCode);
				//创建人
				att.setRecCreator(UserSession.getUser().getUsername());
				//创建时间
				att.setRecCreateTime(DateUtils.curDateTimeStr19());
				//执行插入
				dao.insert("DFSB0101.insertFile",att);
			}
		}
}
