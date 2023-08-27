package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.df.common.util.ParamKeyCode;
import com.baosight.wilp.df.sb.domain.DFSB03;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * (特种设备检验编辑页面)
 * <p></p>
 * <p></p>
 * <p></p>
 * @Title: ServiceDFSB0201.java
 * @ClassName: ServiceDFSB0201
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年8月16日 下午5:24:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB0201 extends ServiceBase {
	//创建锁对象
	private static final Lock lock = new ReentrantLock();
	/**
	 * 
	 * 页面初始化
	 * @param info
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		return super.initLoad(info);
	}
	
	/**
	 * 
	 * (根据类型生成我需要的工单号)
	 * <p>1.通过调用生成头的方法生成工单头</p>
	 * <p>2.通过拼接生成工单号</p>
	 * <p>3.调用更新方法为下次生成做准备</p>
	 * @Title: createTaskNo 
	 * @param ：type  taskNo
	 * @return: String
	 */
    @SuppressWarnings("unchecked")
	public String createTaskNo() {   
        
		lock.lock();
	//	taskNo赋null值
		String taskNo=null;
        try {
            //查询数据
            List<String> list = dao.query("DFSB02.queryTaskNumber", "equipment");
            //生成头
            String head = ParamKeyCode.createTop();
            //判断
            if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(list.get(0))
                || !head.equals(list.get(0).substring(2, 8))) {
            	taskNo = "GD" + head + "001";
            	//返回
                return taskNo;
            }
            //计数
            int num = Integer.parseInt(list.get(0).substring(8)) + 1;
            int zeroLength = list.get(0).length() - (head.length() + 2 + String.valueOf(num).length());
            String zero = "";
            //循环递增
            for (int i = 0; i < Math.abs(zeroLength); i++) {
                zero = "0" + zero;
            }
            //工单号
            taskNo = "GD" + head + zero + num;
            return taskNo;
        } finally {
            //更新工单号
        	EiInfo inInfo=new EiInfo();
        	inInfo.set("taskNo",taskNo);
        	updateTaskNo(inInfo);
        	//闭锁
            lock.unlock();
        }

    }
  
    /**
     * 
     * (更新任务号，为下次生成新任务号做准备)
     *
     * @Title: updateTaskNo 
     * @param ：taskNo:工单号
     * type：类型
     * updateTime：更新时间
     * @return 
     * @return: EiInfo
     */
   	public EiInfo updateTaskNo(EiInfo inInfo) {
   	        //获取工单号
           String taskNo = inInfo.getString("taskNo") == null ? "" : inInfo.getString("taskNo");	//工单号
          //创建map
           Map<String, String> map = new HashMap<>();
           //封装数据
           map.put("taskNo", taskNo);
           map.put("type", "equipment");
           map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
         //更新工单号
           dao.insert("DFSB02.updateTaskNo", map);
           //返回
           EiInfo outInfo = new EiInfo();
		   //返回
           outInfo.set("taskNo", taskNo);
           return outInfo;
   	}
    
    /**
     * 
     * (检验登记)
     * <p>1.获取前台传来的数据</p>
	 * <p>2.修改的数据执行更新方法存数据库中</p>
     * @Title: insert 
     * @param :info
	 * id                   主键
	 * machineId            设备id
	 * machineCode			设备编码
	 * machineName		 	设备名称
	 * innerMachineCode	  	设备内部编码
	 * checkType		 	检验类型
	 * statusCode		  	工单状态
	 * thisCheckDate	  	本次检查日期
	 * thisFinishDate	   	本次完成日期
	 * nextCheckDate	    下次检查日期
	 * recCreator			创建人
	 * recCreateTime		创建时间
     * @return: EiInfo
	 * id                   主键
	 * machineId            设备id
	 * machineCode			设备编码
	 * machineName		 	设备名称
	 * innerMachineCode	  	设备内部编码
	 * checkType		 	检验类型
	 * statusCode		  	工单状态
	 * thisCheckDate	  	本次检查日期
	 * thisFinishDate	   	本次完成日期
	 * nextCheckDate	    下次检查日期
	 * recCreator			创建人
	 * recCreateTime		创建时间
     */
	public EiInfo insert(EiInfo info) {
		//创建map集合
		Map<String, String> map = new HashMap<String, String>();
		   //生成id
	       String id = UUID.randomUUID().toString();
	       //设备id
	       String machineId = info.getString("machineId");
	       //设备编码
	       String machineCode = info.getString("machineCode");
	       //设备名称
	       String machineName = info.getString("machineName");
	       //设备内部编码
	       String innerMachineCode = info.getString("innerMachineCode");
	       //检验类型
	       String checkType = info.getString("checkType");
	       //工单状态
	       String statusCode = info.getString("statusCode");
	       //本次检查日期
	       String thisCheckDate = info.getString("thisCheckDate");
	       //本次检验完工日期
	       String thisFinishDate = info.getString("thisFinishDate");
	       //下次检查日期
	       String nextCheckDate = info.getString("nextCheckDate");
		   //创建人
		   String recCreator = info.get("recCreator") == null ? UserSession.getUser().getUsername() : info.getString("recCreator");
		   //创建时间
		   String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		   //将获取的数据封装到map中
			map.put("id", id);
			map.put("taskNo", createTaskNo());
			map.put("machineId", machineId);
			map.put("machineCode", machineCode);
			map.put("machineName", machineName);
			map.put("innerMachineCode", innerMachineCode);
			map.put("checkType", checkType);
			map.put("statusCode", statusCode);
			map.put("thisCheckDate", thisCheckDate);
			map.put("thisFinishDate", thisFinishDate);
			map.put("nextCheckDate", nextCheckDate);
			map.put("recCreateTime", recCreateTime);
			map.put("recCreator", recCreator);
			map.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
		//获取文件集合
			Object fileObj = info.get("fileList");
			//调用保存附件的方法
			saveFile(fileObj,id);
	        //执行插入方法
			dao.insert("DFSB02.insert",map);
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
			//创建实体类
			DFSB03 att = new DFSB03();
			//获取的数据封装map中
			att.fromMap(map);
			//生成id
			att.setId(UUID.randomUUID().toString());
			//设备关联id
			att.setRelateId(id);
			//附件所属模块
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
