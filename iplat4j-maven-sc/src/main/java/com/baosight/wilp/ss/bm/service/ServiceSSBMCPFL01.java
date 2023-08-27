package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMCpfl01;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.*;
import com.baosight.xservices.xs.up.utils.XSExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 菜品分类管理service
 * 
 * @Title: ServiceSSBMCPFL01.java
 * @ClassName: ServiceSSBMCPFL01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:17:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPFL01 extends ServiceBase{
	

    /**
     * 
     * 初始化加载
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCpfl01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);
		initLoad.set("AA",UserSession.getLoginName());
		return initLoad;
	}

	/**
	 * 查询菜品分类
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCpfl01.query", new SSBMCpfl01());
		return outInfo;
	}


	/**
	 * 
     * 删除食堂
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			//判断是否有启用的食堂
			boolean flag = true;
			for (int i = 0; i < rows.size(); i++) {
				HashMap<String, Object> hashMap = rows.get(i);
				String isEffective = hashMap.get("statusCode").toString();
				if("1".equals(isEffective)) {
					flag = false;
				}
			}
			if(flag) {
				outInfo = super.delete(inInfo, "SSBMCpfl01.delete");
				outInfo.setMsg("操作成功！");
			}else {
				inInfo.setMsg("所选项中有正在启用的数据！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("操作失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}


	/**
	 * 
     * 新增食堂信息
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//获取传参
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(submitList != null && submitList.size() > 0) {
				HashMap<String,Object> map = submitList.get(0);
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				//修改数据
				map.put("recCreateTime",format);
				map.put("recCreator",UserSession.getLoginName());
				map.put("id",UUIDUtils.getUUID32());
				//调用服务
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("pix", "CL");
				paramMap.put("leng", "6");
				paramMap.put("serialNum", "menuType");
				EiInfo call = LocalServiceUtil.call("SSBMTyTserial", "getSerialIndex", paramMap);
				if(call.getStatus() < 0) {
					inInfo.setStatus(-1);
					inInfo.setMsg("获取餐类编码失败！");
					return inInfo;
				}else {
					//获取菜品分类编码
					String mealTypeNum = call.getAttr().get("serialIndex").toString();;
					map.put("mealTypeNum",mealTypeNum);
					//将数据填充到result
					inInfo.addBlock("result").addRows(submitList);
					outInfo = super.insert(inInfo, "SSBMCpfl01.insert");
					outInfo.setMsg("保存成功！");
				}
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("保存失败！");
		}
		return outInfo;
	}
	

	/**
	 * 
     * 编辑数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(updateList != null && updateList.size() > 0) {
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMCpfl01.update"); 
				outInfo.setMsg("更新成功！");
			}else {
				outInfo.setMsg("数据提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("更新失败！");
		}
		return outInfo;
	}
	private static final int COLUMN_COUNT_LIXU = 3;

	/**
	 * Todo(导入菜品分类)
	 * 1.获取上传的excel文件
	 * 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组
	 * 3.判断食堂编码是否存在，不存在则返回提示不存在，存在则判断登录账号是否为该食堂的管理员
	 * 4.判断食堂名称是否匹配，不匹配则返回提示不匹配
	 * 5.通过序号服务获取菜品分类序号SSBMTyTserial.getSerialIndex
	 * 6.组装菜品分类信息，并保存到数据库菜品分类表
	 * 7.判断数据是否存在重复，有重复的返回重复信息
	 * @Title: uploadFile
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo uploadFile(EiInfo inInfo){
		Map attr = inInfo.getAttr();
		/**
		 * 1.获取上传的excel文件
		 */
		String docUrl = StringUtil.toString(attr.get("docUrl"));
		String fileType = StringUtil.toString(attr.get("fileType"));
		//获取图片存储基础路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）
		//String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String filePath =  docUrl;
		File file = new File(filePath);
		InputStream in = null;
		int errorNum = 0;
		String errorMsg = "";
		String canteenNum = "";
		if (file != null) {
			try {
				in = new FileInputStream(filePath);
				/**
				 * 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组
				 */
				String[][] data = null;
				if(".xls".equals(fileType)){
					data = XSSExcelUtils.getXlsDataByInputStream(in, 0);
				}else if(".xlsx".equals(fileType)){
					data = XSSExcelUtils.getXlsxDataByInputStream(in, 0);
				}
				if (data[0].length < COLUMN_COUNT_LIXU) {
					String msg = "导入失败!当前导入文件的列数为:[" + (data[0].length) + "]列,模板中列数为[" + COLUMN_COUNT_LIXU + "]列,请重新选择正确的导入文件.";
					inInfo.setMsg(msg);
					inInfo.setStatus(-1);
					return inInfo;
				}

				ArrayList<HashMap<String,Object>> cpflList = new ArrayList<HashMap<String,Object>>();
				for (int i = 1; i < data.length; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					/**
					 * 3.判断食堂编码是否存在，不存在则返回提示不存在，存在则判断登录账号是否为该食堂的管理员
					 */
					if(StringUtil.isNotEmpty(data[i][1]) && !canteenNum.equals(data[i][1])){
						map.put("canteenNum",data[i][1]);
						List<SSBMStxx01> query = dao.query("SSBMStxx01.query", map);
						if(query.size() < 1){
							//不存在该食堂
							inInfo.setMsg("未查询到食堂【" + data[i][1] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}else if(StringUtil.isNotEmpty(data[i][2])){
							SSBMStxx01 stxx = query.get(0);
							String[] liaisons = stxx.getLiaisonId().split(",");
							if(Arrays.binarySearch(liaisons,UserSession.getLoginName()) < 0){
								//判断是否为食堂管理员
								inInfo.setMsg("您不是食堂【" + data[i][1] + "】的管理员，请检查");
								inInfo.setStatus(-1);
								return inInfo;
							}
							if(!data[i][2].equals(stxx.getCanteenName())){
								/**
								 * 4.判断食堂名称是否匹配，不匹配则返回提示不匹配
								 * */
								inInfo.setMsg("食堂名称不匹配【" + data[i][2] + "】请检查");
								inInfo.setStatus(-1);
								return inInfo;
							}
						}
						//为变量赋当前值
						canteenNum = data[i][1];
					}
					map.clear();
					/**
					 * 5.通过序号服务获取菜品分类序号SSBMTyTserial。getSerialIndex
					 */
					HashMap<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("pix", "CL");
					paramMap.put("leng", "6");
					paramMap.put("serialNum", "menuType");
					EiInfo call = LocalServiceUtil.call("SSBMTyTserial", "getSerialIndex", paramMap);
					if(call.getStatus() < 0) {
						inInfo.setStatus(-1);
						inInfo.setMsg("获取餐类编码失败！");
						return inInfo;
					}else {
						//获取菜品分类编码
						String mealTypeNum = call.getAttr().get("serialIndex").toString();
						map.put("mealTypeNum",mealTypeNum);
					}
					/**
					 * 6.组装菜品分类信息，并保存到数据库菜品分类表
					 */
					map.put("id",UUIDUtils.getUUID32());
					map.put("mealTypeName",data[i][0]);
					map.put("spuerMealTypeNum",data[i][1]);
					map.put("spuerMealTypeName",data[i][2]);
					map.put("recCreateTime",CalendarUtils.dateTimeFormat(new Date()));
					map.put("recCreator",UserSession.getLoginName());
					map.put("statusCode","1");
					errorNum = i;
					errorMsg = data[i][0] + "-" + data[i][1];
					dao.insert("SSBMCpfl01.insertByUpload",map);
				}
				inInfo.setStatus(1);
				inInfo.setMsg("执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				inInfo.setStatus(-1);
				String rootCauseMessage = ExceptionUtil.getRootCauseMessage(e);
				/**
				 * 7.判断数据是否存在重复，有重复的返回重复信息
				 */
				if(rootCauseMessage.indexOf("Duplicate") > -1){
					inInfo.setMsg("第"+ errorNum + "条记录【" + errorMsg + "】出现重复");
				}else{
					inInfo.setMsg("第"+ errorNum + "条记录【" + errorMsg + "】出现错误" + ExceptionUtil.getRootCauseMessage(e));
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
