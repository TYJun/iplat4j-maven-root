package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMCpfl01;
import com.baosight.wilp.ss.bm.domain.SSBMCpxx01;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.domain.SSBMTyTmealPic;
import com.baosight.wilp.ss.bm.utils.*;
import com.baosight.xservices.xs.up.utils.XSExcelUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *  菜品信息管理service
 * 
 * @Title: ServiceSSBMCPXX01.java
 * @ClassName: ServiceSSBMCPXX01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:54:23
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPXX01 extends ServiceBase{
	
	private static final String picPath = "sc/img/";
	

	/**
	 * 初始化查询
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCpxx01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listCanteenData = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenData);
		//mealOperate:食堂业务
		paramMap.put("mealgroupTypeCode", "mealOperate");
		List<HashMap<String,Object>> listMealOperate = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealOperate").addRows(listMealOperate);
		//orderCrowd:订餐人群
		paramMap.put("mealgroupTypeCode", "dc");
		List<HashMap<String,Object>> listOrderCrowd = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("orderCrowd").addRows(listOrderCrowd);
		//comboRuleType:菜品属性
		paramMap.put("mealgroupTypeCode", "mealComboRuleType");
		List<HashMap<String,Object>> listComboRuleType = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("comboRuleType").addRows(listComboRuleType);
		//menuType:菜品类型
		/*		List<HashMap<String,Object>> listMenuType = dao.query("SSBMCpfl01.getMenuTypeData", paramMap);
				initLoad.addBlock("menuType").addRows(listMenuType);*/
		//默认菜品数量 defaultDishesNum
		EiInfo callCode = LocalServiceUtil.callCode1("S_ED_02","wilp.sc.jc.quantity", "defaultDishesNum");
		List<HashMap<String,Object>> list = (List<HashMap<String, Object>>) callCode.get("list");
		initLoad.addBlock("defaultDishesNum").addRows(list);
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCpxx01.query", new SSBMCpxx01());
		List<HashMap<String,Object>> result = outInfo.getBlock("result").getRows();
		for (int i = 0; i < result.size(); i++) {
			HashMap<String,Object> ssbmCpxx01 = result.get(i);
			ssbmCpxx01.put("imgStr",FileUtil.fileToBase64(StringUtil.toString(ssbmCpxx01.get("menuPicUrl"))));
		}
		return outInfo;
	}


	/**
	 * 
     * 删除菜品信息
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			//判断是否有启用的数据
			boolean flag = true;
			for (int i = 0; i < rows.size(); i++) {
				HashMap<String, Object> hashMap = rows.get(i);
				String isEffective = hashMap.get("statusCode").toString();
				if("1".equals(isEffective)) {
					flag = false;
				}
			}
			if(flag) {
				for (int i = 0; i < rows.size(); i++) {
					HashMap<String, Object> hashMap = rows.get(i);
					String id = hashMap.get("id").toString();
					//删除菜品组成
					dao.delete("SSBMCpxx02.deleteByRelationId", id);
					//删除关联图片
					dao.delete("SSBMTyTmealPic.deleteByMealId", id);
				}				//删除菜品
				outInfo = super.delete(inInfo, "SSBMCpxx01.delete");
				outInfo.setMsg("操作成功！");
			}else {
				inInfo.setMsg("所选项中有正在生效的数据！");
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
     * 新增菜品信息
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		try {
			//获取传参
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			//接收菜品组成数据
			ArrayList<HashMap<String,Object>> descriptionList = (ArrayList<HashMap<String, Object>>) attr.get("description");
			//接收图片数据
			ArrayList<HashMap<String,Object>> fileIdList = (ArrayList<HashMap<String, Object>>) attr.get("pics");
			if(submitList != null && submitList.size() > 0) {
				HashMap<String,Object> map = submitList.get(0);
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = smf.format(new Date());
				//修改数据
				map.put("recCreateTime",format);
				map.put("id",UUIDUtils.getUUID32());
				map.put("menuNum",UUIDUtils.getUUID32());
				//map.put("menuFee",0);
				if(descriptionList !=null && descriptionList.size() > 0){
					//保存菜品组成
					for (int i = 0; i < descriptionList.size(); i++) {
						HashMap<String, Object> descriptionMap = descriptionList.get(i);
						descriptionMap.put("id", UUIDUtils.getUUID32());
						descriptionMap.put("relationId", map.get("id"));
					}
					EiInfo descriptionInfo = new EiInfo();
					descriptionInfo.addBlock("result").addRows(descriptionList);
					super.insert(descriptionInfo, "SSBMCpxx02.insert");
				}
				if(fileIdList != null && fileIdList.size() > 0) {
					//String savaPath = request.getSession().getServletContext().getRealPath("")+picPath+File.separatorChar;
					ArrayList<HashMap<String,Object>> mealPicList = new ArrayList<HashMap<String,Object>>();
					//整理图片数据#picId#, #fileId#, #mealId#, #fileUrl#, #fileType#
			        for (int i = 0; i < fileIdList.size(); i++) {
			        	HashMap<String,Object> paramObject = new HashMap<String, Object>();
			        	paramObject.put("picId", fileIdList.get(i));
			        	paramObject.put("fileId", fileIdList.get(i)+"jpg");
			        	paramObject.put("mealId", map.get("id"));
						String docRootDir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + picPath;
			        	String fileUrl = docRootDir + fileIdList.get(i) + ".jpg";
			        	paramObject.put("fileUrl", fileUrl);
			        	paramObject.put("fileType", 0);
			        	mealPicList.add(paramObject);
					}
			        //保存图片信息到sc_pic表
			        EiInfo mealPicInfo = new EiInfo();
			        mealPicInfo.addBlock("result").addRows(mealPicList);
					super.insert(mealPicInfo, "SSBMTyTmealPic.insert");
					//图片url保存到菜品信息
					map.put("menuPicUrl", mealPicList.get(0).get("fileUrl"));
				}
				//将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				//保存菜品信息
				outInfo = super.insert(inInfo, "SSBMCpxx01.insert");
				outInfo.setMsg("保存成功！");
			}else {
				outInfo.setMsg("数据提交失败！");
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
	 * @see ServiceBase#update(EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			//接受菜品组成数据
			ArrayList<HashMap<String,Object>> descriptionList = (ArrayList<HashMap<String, Object>>) attr.get("description");
			//接收图片数据
			ArrayList<HashMap<String,Object>> fileIdList = (ArrayList<HashMap<String, Object>>) attr.get("pics");
			if(updateList != null && updateList.size() > 0) {
				HashMap<String,Object> map = updateList.get(0);
				if(descriptionList !=null && descriptionList.size() > 0){
					//更新菜品组成
					for (int i = 0; i < descriptionList.size(); i++) {
						HashMap<String, Object> descriptionMap = descriptionList.get(i);
						descriptionMap.put("id", UUIDUtils.getUUID32());
						descriptionMap.put("relationId", map.get("id"));
					}
					EiInfo descriptionInfo = new EiInfo();
					descriptionInfo.addBlock("result").addRows(descriptionList);
					super.delete(descriptionInfo, "SSBMCpxx02.deleteByRelationId");
					super.insert(descriptionInfo, "SSBMCpxx02.insert");
				}
				if(fileIdList != null && fileIdList.size() > 0) {
					//更新图片信息
					ArrayList<HashMap<String,Object>> mealPicList = new ArrayList<HashMap<String,Object>>();
					//整理图片数据#picId#, #fileId#, #mealId#, #fileUrl#, #fileType#
			        for (int i = 0; i < fileIdList.size(); i++) {
			        	HashMap<String,Object> paramObject = new HashMap<String, Object>();
			        	paramObject.put("picId", fileIdList.get(i));
			        	paramObject.put("fileId", fileIdList.get(i)+".jpg");
			        	paramObject.put("mealId", map.get("id"));
			        	//String fileUrl = File.separatorChar + picPath + File.separatorChar + fileIdList.get(i) + ".jpg";
						String docRootDir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + picPath;
						String fileUrl = docRootDir + fileIdList.get(i) + ".jpg";
			        	paramObject.put("fileUrl", fileUrl);
			        	paramObject.put("fileType", 0);
			        	mealPicList.add(paramObject);
					}
			        //保存图片信息到sc_pic表
			        EiInfo mealPicInfo = new EiInfo();
			        mealPicInfo.addBlock("result").addRows(mealPicList);
			        super.delete(mealPicInfo, "SSBMTyTmealPic.deleteByMealId");
					super.insert(mealPicInfo, "SSBMTyTmealPic.insert");
					//图片url保存到菜品信息
					map.put("menuPicUrl", mealPicList.get(0).get("fileUrl"));
				}else {
					//没有图片
					map.put("menuPicUrl","");
				}
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMCpxx01.update"); 
				outInfo.setMsg("更新成功！");
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	
	/**
	 * 
	 * 启用/停用菜品
	 *
	 * @Title: effect 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:58:18
	 */
	public EiInfo effect(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		//接收数据
		ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
		if(updateList != null && updateList.size() > 0) {
			//将数据填充到result
			inInfo.addBlock("result").addRows(updateList);
			//更新数据
			outInfo = super.update(inInfo, "SSBMCpxx01.update"); 
			outInfo.setMsg("更新成功！");
		}else {
			inInfo.setMsg("数据提交失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	

	/**
	 * 
	 * 上传菜品图片
	 *
	 * @Title: uploadPic 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:58:50
	 */
	public EiInfo uploadPic(EiInfo inInfo) {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		EiInfo outInfo = new EiInfo();
		try {
			//获取图片base64
			Map attr = inInfo.getAttr();
			String pic = attr.get("pics").toString();
			//处理图片
	        ArrayList<String> imgList=new ArrayList<String>();
	        imgList.add(pic);
	        
	        /*String savaPath = request.getSession().getServletContext().getRealPath("");
	        System.out.println(savaPath);
	        File file = new File(savaPath);
	        System.out.println(file.getParent());*/
	        
	        //附件路径
			String docRootDir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + picPath;
			System.out.println("附件路径"+docRootDir);
	        //String savaPath = request.getSession().getServletContext().getRealPath("")+picPath+File.separatorChar;
	        //保存base64图片
	        ArrayList<HashMap<String,Object>> fileIdList = FileUtil.savePic(docRootDir, imgList);
	        //System.out.println("**************************************保存文件："+fileIdList);
	        outInfo.set("fileIdList", fileIdList);
	        outInfo.set("showPath", "sc/img");// /smp_meal/picUpload/faa3f10f74ea4acf88389f53ef332812.jpg
	       
	        outInfo.setMsg("上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("提交失败！");
		}
		return outInfo;
	}
	

	/**
	 * 
	 * getPic加载图片信息
	 *
	 * @Title: getPic 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:00:13
	 */
	public EiInfo getPic(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//文件目录
			String dir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + picPath;
			Map attr = inInfo.getAttr();
			String mealId = attr.get("mealId").toString();
			//获取图片信息
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mealId", mealId);
			List<SSBMTyTmealPic> mealPicList = dao.query("SSBMTyTmealPic.query", paramMap);
			if (mealPicList != null && mealPicList.size() > 0) {
				for (int i = 0; i < mealPicList.size(); i++) {
					SSBMTyTmealPic pic = mealPicList.get(i);
					pic.setImgStr(FileUtil.fileToBase64(pic.getFileUrl()));
				}
			}
			outInfo.set("mealPics", mealPicList);
			outInfo.setMsg("图片信息获取成功！");
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("图片信息获取失败！");
		}
		return outInfo;
	}
	

	/**
	 * 
	 * deletePic:删除图片信息
	 *
	 * @Title: deletePic 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:03:05
	 */
	public EiInfo deletePic(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		try {
			Map attr = inInfo.getAttr();
			String imgId = StringUtil.toString(attr.get("imgId"));
			//String filePath = request.getSession().getServletContext().getRealPath("")+picPath+File.separatorChar;
			String docRootDir = PlatApplicationContext.getProperty("docRootDir") + File.separatorChar + picPath;
			//删除数据库表
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("picId", imgId);
			int delete = dao.delete("SSBMTyTmealPic.delete", paramMap);
			//删除图片文件
			FileUtil.deleteFile(docRootDir+imgId+".jpg");
			outInfo.set("deleteNo", delete);
			outInfo.setMsg("执行成功！");
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("执行失败！");
		}
		return outInfo;
	}


	public EiInfo uploadFile1(EiInfo inInfo){
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
					data = XSSExcelUtils.getXlsDataByInputStream(in, 0);
				}
				inInfo.setStatus(1);
				inInfo.setMsg("执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				inInfo.setStatus(-1);
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

	/**
	 * 
	 * 查询菜品分类
	 *
	 * @Title: getMenuTypeData 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:04:00
	 */
	public EiInfo getMenuTypeData(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//获取传参
		Map attr = inInfo.getRow("inqu_status",0);
		List<HashMap<String,Object>> listMenuType  = dao.query("SSBMCpfl01.getMenuTypeData", attr);
		outInfo.addBlock("menuType").addRows(listMenuType);
		return outInfo;
	}
	

	/**
	 * 
	 *     弹窗查询菜品分类
	 *
	 * @Title: getMenuTypeDataWindow 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:04:19
	 */
	public EiInfo getMenuTypeDataWindow(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//获取传参
		Map attr=inInfo.getAttr();
		List<HashMap<String,Object>> listMenuType  = dao.query("SSBMCpfl01.getMenuTypeData", attr);
		outInfo.addBlock("menuType").addRows(listMenuType);
		return outInfo;
	}

	/**
	 * 导入excel的最大列数
	 */
	private static final int COLUMN_COUNT_LIXU = 7;

	/**
	 * Todo(导入菜品信息)
	 * 1.获取上传的excel文件
	 * 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组
	 * 3.判断食堂编码是否存在，不存在则返回提示不存在，存在则判断登录账号是否为该食堂的管理员
	 * 4.判断食堂名称是否匹配，不匹配则返回提示不匹配
	 * 5.判断菜品分类是否存在，不存在则返回提示不存在
	 * 6.组装菜品信息，并保存到数据库菜品信息表
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
		String filePath =  docUrl;
		File file = new File(filePath);
		InputStream in = null;
		int errorNum = 0;
		String errorMsg = "";
		String canteenNum = "";
		String menuType = "";
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
					errorNum = i;
					errorMsg = data[i][0] + "-" + data[i][1];
					//参数非空判断
					if(StringUtil.isEmpty(data[i][0]) || StringUtil.isEmpty(data[i][1]) || StringUtil.isEmpty(data[i][2])
						|| StringUtil.isEmpty(data[i][3]) || StringUtil.isEmpty(data[i][4]) || StringUtil.isEmpty(data[i][5])
						|| StringUtil.isEmpty(data[i][6]) || StringUtil.isEmpty(data[i][7]) ){
						inInfo.setMsg("第【" + errorNum + "】行参数缺失，请检查");
						inInfo.setStatus(-1);
						return inInfo;
					}
					/**
					 * 3.判断食堂编码是否存在，不存在则返回提示不存在，存在则判断登录账号是否为该食堂的管理员
					 */
					if(!canteenNum.equals(data[i][2])){
						map.put("canteenNum",data[i][2]);
						List<SSBMStxx01> query = dao.query("SSBMStxx01.query", map);
						if(query.size() < 1){
							//不存在该食堂
							inInfo.setMsg("未查询到食堂【" + data[i][1] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}else if(StringUtil.isNotEmpty(data[i][1])){
							SSBMStxx01 stxx = query.get(0);
							String[] liaisons = stxx.getLiaisonId().split(",");
							if(Arrays.binarySearch(liaisons,UserSession.getLoginName()) < 0){
								//判断是否为食堂管理员
								inInfo.setMsg("您不是食堂【" + data[i][2] + "】的管理员，请检查");
								inInfo.setStatus(-1);
								return inInfo;
							}
							if(!data[i][1].equals(stxx.getCanteenName())){
								/**
								 * 4.判断食堂名称是否匹配，不匹配则返回提示不匹配
								 * */
								inInfo.setMsg("食堂名称不匹配【" + data[i][1] + "】请检查");
								inInfo.setStatus(-1);
								return inInfo;
							}
						}
						//为变量赋当前值
						canteenNum = data[i][1];
					}
					map.clear();
					/**
					 * 5.判断菜品分类是否存在，当菜品分类或食堂发生变化是查询，不存在则返回提示不存在
					 */
					String menuTypeNum = "";
					if( !menuType.equals(data[i][3]) || !canteenNum.equals(data[i][2]) ){
						map.put("canteenNum",data[i][2]);
						map.put("mealTypeName",data[i][3]);
						List<SSBMCpfl01> query = dao.query("SSBMCpfl01.queryByCanteen", map);
						if(query.size() < 1){
							//不存在该食堂
							inInfo.setMsg("食堂"+data[i][1]+"未查询到分类【" + data[i][3] + "】请检查");
							inInfo.setStatus(-1);
							return inInfo;
						}
						menuTypeNum = query.get(0).getMealTypeNum();
						//为变量赋当前值
						menuType = data[i][3];
					}
					/**
					 * 6.组装菜品信息，并保存到数据库菜品信息表
					 */
					map.put("id",UUIDUtils.getUUID32());
					map.put("menuNum",UUIDUtils.getUUID32());
					map.put("menuName",data[i][0]);
					map.put("canteenNum",data[i][2]);
					map.put("menuTypeNum",menuTypeNum);
					map.put("menuTypeName",data[i][3]);
					map.put("menuPrice",data[i][4]);
					map.put("menuSupply",data[i][5]);
					map.put("menuFee",data[i][6]);
					map.put("statusCode",data[i][7]);
					map.put("operateCode","meal");
					map.put("operateName","普餐");
					map.put("recCreateTime",CalendarUtils.dateTimeFormat(new Date()));
					map.put("recCreator",UserSession.getLoginName());

					dao.insert("SSBMCpxx01.insertByUpload",map);
				}
				inInfo.setStatus(1);
				inInfo.setMsg("执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				inInfo.setStatus(-1);
				String rootCauseMessage = ExceptionUtil.getRootCauseMessage(e);
				/**
				 * 7.判断数据是否存在重复，有重复的返回重复信息
				 * */
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
