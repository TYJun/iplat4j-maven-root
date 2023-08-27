package com.baosight.wilp.dm.fm.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.wilp.common.pr.UUIDUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.common.ys.domain.CarriageUser;
import com.baosight.wilp.dm.utils.XSSExcelUtils;
import com.baosight.xservices.xs.up.utils.XSExcelUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dm.fm.domain.DMFM01;


/**
 * TODO(这里用一句话描述这个方法的作用)
 *
 *      1.初始化页面加载已有的宿舍的费用管理
 *      2.查询已有的宿舍费用管理数据
 *      3.导入宿舍费用
 *      4.宿舍费用导入
 *
 * @Title: ServiceDMFM01.java
 * @ClassName: ServiceDMFM01
 * @Package：com.baosight.wilp.dm.fm.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:33:16
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMFM01 extends ServiceBase{
	
    /**
     * TODO(初始化页面加载已有的宿舍的费用管理)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}
	
   /**
     * TODO(查询已有的宿舍费用管理数据)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "DMFM01.query", new DMFM01());
		return outInfo;
    }
    
    /**
     * TODO(导入宿舍费用)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
//    @Scheduled(cron = "30 * * * * *？")
    public EiInfo queryintelligentElecFee(EiInfo inInfo) {
//    	List<?> rows = inInfo.getBlock("result").getRows();
    	Map<String, String> map = new HashMap<String, String>();
		//数据查询宿舍信息表
		List<Map<String, Object>> list = dao.query("DMFM01.queryintelligentElecFee",null);
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> map2 : list) {
			    //插入信息进宿舍费用管理信息表
				dao.insert("DMFM01.insert", map2);
//				EiInfo outInfo = super.insert("DMFM01.insert", map2);
//				System.out.println("插入成功");
//				outInfo.setMsg("插入成功");
//				return outInfo;
			}
		}else {
			inInfo.setStatus(-1);
			inInfo.setMsg("无电费信息");
			return inInfo;
		}
		return inInfo;
    }
	private static final int COLUMN_COUNT_LIXU = 13;
	/**
	 * TODO(宿舍费用导入)
	 * 1.获取上传的excel文件
	 * 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组
	 * 3.判断宿舍在基础信息中是否存在
	 * 4.判断运送人员角色
	 * 5.组装运送人员信息，并保存到数据库运送人员信息表
	 * @title uploadFile
	 * @param resquest 请求入参 { 无}
	 * @return
	 */
	public EiInfo uploadFile(EiInfo inInfo){
		Map attr = inInfo.getAttr();
		String loginName = StringUtils.isNotBlank((String) inInfo.get("loginName"))?(String) inInfo.get("loginName"): com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName();
		/**1.获取上传的excel文件*/
		String docUrl = StringUtil.toString(attr.get("docUrl"), false);
		String fileType = StringUtil.toString(attr.get("fileType"), false);
		String filePath =  docUrl;
		File file = new File(filePath);
		InputStream in = null;
		int errorNum = 0;
		String errorMsg = "";
		String roomName = "";
		//String id = "";
		//String roleCode = "";
		if (file != null) {
			try {
				in = new FileInputStream(filePath);
				/** 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组*/
				String[][] data = null;
				//判断导入文件类型
				if(".xls".equals(fileType)){
					data = XSExcelUtils.getDataByInputStream(in, 0);
				}else if(".xlsx".equals(fileType)){
					data = XSSExcelUtils.getDataByInputStream(in, 0);
				}
				//判断输入文件的行是否大于标准的行数
				if (data[0].length < COLUMN_COUNT_LIXU) {
					String msg = "导入失败!当前导入文件的列数为:[" + (data[0].length) + "]列,模板中列数为[" + COLUMN_COUNT_LIXU + "]列,请重新选择正确的导入文件.";
					inInfo.setMsg(msg);
					inInfo.setStatus(-1);
					return inInfo;
				}

				ArrayList<HashMap<String,Object>> cpflList = new ArrayList<HashMap<String,Object>>();
				//遍历集合拿到数据
				for (int i = 1; i < data.length; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					errorNum = i;
					errorMsg = data[i][0] + "-" + data[i][1];
					//参数文件非空判断
					if(StringUtil.isEmpty(data[i][0]) || StringUtil.isEmpty(data[i][1]) || StringUtil.isEmpty(data[i][2])
							|| StringUtil.isEmpty(data[i][3])|| StringUtil.isEmpty(data[i][4])|| StringUtil.isEmpty(data[i][5])||
							StringUtil.isEmpty(data[i][6])|| StringUtil.isEmpty(data[i][7])|| StringUtil.isEmpty(data[i][8])||
							StringUtil.isEmpty(data[i][9])|| StringUtil.isEmpty(data[i][10])|| StringUtil.isEmpty(data[i][11])
							|| StringUtil.isEmpty(data[i][12])     ){
						inInfo.setMsg("第【" + errorNum + "】行参数缺失，请检查");
						inInfo.setStatus(-1);
						return inInfo;
					}


					/**3.判断宿舍在基础信息中是否存在*/
					String index = data[i][4];
					if(!roomName.equals(index)){
						map.put("roomName",index);
						//查询数据库中宿舍信息表
						List<Map<String,String>> query = dao.query("DMFM01.queryRoomName", map);
						if(CollectionUtils.isEmpty(query)){
							//存在运送人员工号
							inInfo.setMsg("宿舍不存在【" + data[i][4] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}
						List<Map<String,String>> queryList = dao.query("DMFM01.queryRoomFee", map);
						if(CollectionUtils.isNotEmpty(queryList)){
							//存在宿舍
							inInfo.setMsg("宿舍以存在【" + data[i][4] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}
						//为变量赋当前值
						roomName = data[i][4];
					}

					map.clear();

					if(!roomName.equals(index)){
						map.put("roomName",index);
						//查询数据库中宿舍费用表
						List<Map<String,String>> query = dao.query("DMFM01.queryRoomFee", map);
						if(CollectionUtils.isNotEmpty(query)){
							//存在宿舍
							inInfo.setMsg("宿舍以存在【" + data[i][4] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}
						//为变量赋当前值
						roomName = data[i][4];
					}

					map.put("roomName",index);
					List<Map<String,String>> query = dao.query("DMFM01.queryRoomName", map);
					for(int j = 0; j< query.size();j++) {
						String id = query.get(j).get("id");
						map.put("roomId", id);
					}
					map.put("id",UUIDUtils.getUUID32());
					/**5.组装运送人员信息，并保存到数据库运送人员信息表*/
					map.put("buildingCode",data[i][0]);
					map.put("floorCode",data[i][1]);
					map.put("roomNo",data[i][2]);
					map.put("rent",data[i][3]);
					map.put("roomName",data[i][4]);
					map.put("lastElec",data[i][5]);
					map.put("lastWater",data[i][6]);
					map.put("lastWaterfee",data[i][7]);
					map.put("lastElecfee",data[i][8]);
					map.put("waterFee",data[i][9]);
					map.put("elecFee",data[i][10]);
					map.put("monthElec",data[i][11]);
					map.put("monthWater",data[i][12]);
					map.put("creator", loginName);
					map.put("createrTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					dao.insert("DMFM01.insertFee",map);
				}
				inInfo.setStatus(1);
				inInfo.setMsg("执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				inInfo.setStatus(-1);
				String rootCauseMessage = ExceptionUtil.getRootCauseMessage(e);
				if(rootCauseMessage.indexOf("Duplicate") > -1){
					inInfo.setMsg("第"+ errorNum + "条记录【" + errorMsg + "】出现重复，请检查");
				}else{
					inInfo.setMsg("第"+ errorNum + "条记录【" + errorMsg + "】出现错误，请检查" + ExceptionUtil.getRootCauseMessage(e));
				}
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(in != null){
						in = null;
					}
				}
			}
		}
		return inInfo;
	}
}

