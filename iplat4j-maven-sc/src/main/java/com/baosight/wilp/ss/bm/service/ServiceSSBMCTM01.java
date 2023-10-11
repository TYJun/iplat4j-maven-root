package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ss.bm.config.FontConfig;
import com.baosight.wilp.ss.bm.domain.SSBMBqgl01;
import com.baosight.wilp.ss.bm.domain.SSBMCtm01;
import com.baosight.wilp.ss.bm.utils.*;
import com.baosight.xservices.xs.util.UserSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;


/**
 * 
 * 床头码管理service
 * 
 * @Title: ServiceSSBMCTM01.java
 * @ClassName: ServiceSSBMCTM01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:12:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCTM01 extends ServiceBase {
	//文件下载目录
	private static final String picPath = "mealDownload";
	

	/**
	 * 
	 * 初始化查询
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCtm01());
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		// hosArea:院区
		paramMap.put("mealgroupTypeCode", "hosArea");
		List<HashMap<String, Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("hosArea").addRows(listHosArea);
		
		// building:楼
		paramMap.put("mealgroupTypeCode", "building");
		List<HashMap<String, Object>> listBuilding = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("building").addRows(listBuilding);
		
		// listFloor:层
		paramMap.put("mealgroupTypeCode", "floor");
		List<HashMap<String, Object>> listFloor = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("floor").addRows(listFloor);
		
		// qrDept:二维码科室
		List<SSBMBqgl01> listQrDept = dao.query("SSBMBqgl01.query", paramMap);
		initLoad.addBlock("qrDept").addRows(listQrDept);
		
		// qrcodeSalt:二维码前言
		paramMap.put("mealgroupTypeCode", "qrcodeSalt");
		List<HashMap<String, Object>> listQrcode = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("qrcodeSalt").addRows(listQrcode);

		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		// grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCtm01.query", new SSBMCtm01());
		return outInfo;
	}
	

	/**
	 * 
	 * 导出二维码
	 *
	 * @Title: exportQr 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:12:58
	 */
	public EiInfo exportQr(EiInfo inInfo) {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		EiInfo outInfo = new EiInfo();
		OutputStream outputStream = null;
		Map attr = inInfo.getAttr();
		String qrcodeSalt = StringUtil.toString(attr.get("qrcodeSalt"));
		try {
			//真实路径
	        String filePath = request.getSession().getServletContext().getRealPath("");
	        StringBuffer dirPath = new StringBuffer();
	        String destPath = filePath + "MEAL/image/code2.jpg";
	        String imgPath = filePath + "MEAL/image/logo.jpg";
	        //String imgPath = "";
			//获取二维码前言
	        //attr.put("dirPath", filePath + "mealDownload" + File.separatorChar + UUIDUtils.getUUID8() + File.separatorChar);
			dirPath.append(filePath + "mealDownload" + File.separatorChar + UUIDUtils.getUUID8());
			File aaa = FileUtil.createDir(dirPath.toString());
			System.out.println(dirPath.toString());
			//outputStream = new FileOutputStream(filePath + picPath + File.separatorChar);
			//ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
			List<SSBMCtm01> list = dao.query("SSBMCtm01.query", attr);
			for (int i = 0; i < list.size(); i++) {
				Map<String,String> pMap = new HashMap<String,String>();
				SSBMCtm01 entity = list.get(i);
				//二维码路径
				String address = qrcodeSalt.replace("#addNum#", entity.getAddNum());
				address = address.replace("#areaName#", entity.getAreaName());
				address = address.replace("#areaCode#", entity.getAreaCode());
				//楼信息
				pMap.put("building",entity.getBuildingName());
				//层信息
				pMap.put("floor", entity.getFloorName());
				//科室信息
				pMap.put("deptName", entity.getDeptName());
				//床位信息
				pMap.put("room",entity.getBedNo());
				//图片文件
				String path = entity.getAddNum() + ".jpg";
				File file = new File(dirPath.toString() , path);
				//获取图片流
				BufferedImage encode = QRcodeUtil.encode(address, imgPath, true, destPath, pMap);
				ImageIO.write(encode, "JPG", file);
				System.out.println("-----------------当前字体"+FontConfig.getSimheiFont(Font.BOLD,17) + path);
			}
			//将图片添加到zip文件
			String zipPath = "床头码" + CalendarUtils.dateTimeShortFormat(new Date()) + ".zip";
			ZipUtils.toZip(dirPath.toString(),filePath + "mealDownload" + File.separatorChar + zipPath);
			HashMap<String,String> map = new HashMap<String, String>();
			//mealPicUpload/9dbb200ad82048f696dcfff635c3c42e.zip
			map.put("url","mealDownload" + File.separatorChar +zipPath);
			outInfo.addBlock("dept").addRow(map);
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setStatus(-1);
			outInfo.setMsg("执行异常！");
		}
		return outInfo;
	}
	
	/**
	 * 
	 * 查询楼
	 *
	 * @Title: queryBuilding 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:21
	 */
	public EiInfo queryBuilding(EiInfo inInfo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//获取传参
		String areaCode = StringUtils.toString(inInfo.get("areaCode"));
		paramMap.put("areaCode", areaCode);
		List<HashMap<String, Object>> listBuilding = dao.query("SSBMCtm01.getQrBuilding", paramMap);
		inInfo.addBlock("building").addRows(listBuilding);
		return inInfo;
	}
	
	/**
	 * 
	 * 查询楼
	 *
	 * @Title: queryBuilding 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:21
	 */
	public EiInfo queryBuildingByAreaCode(EiInfo inInfo) {
	    HashMap<String, Object> paramMap = new HashMap<String, Object>();
	    //获取传参
	    String multiCode = StringUtils.toString(inInfo.get("multiCode"));
	    paramMap.put("mealgroupTypeCode", "building");
	    paramMap.put("paramValue1", multiCode);
	    List<HashMap<String, Object>> listBuilding = dao.query("SSBMStxx01.getMealTypeData", paramMap);
	    inInfo.addBlock("building").addRows(listBuilding);
	    return inInfo;
	}
	
	/**
	 * 
	 * 查询层
	 *
	 * @Title: queryFloor 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:36
	 */
	public EiInfo queryFloor(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//获取传参
		Map attr = inInfo.getRow("inqu_status",0);
		List<HashMap<String,Object>> listFloor  = dao.query("SSBMCtm01.getQrFloor", attr);
		outInfo.addBlock("floor").addRows(listFloor);
		return outInfo;
	}
	
	/**
	 * 
	 * 查询科室
	 * @Title: queryDept 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:53
	 */
	public EiInfo queryDept(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		//获取传参
		List<HashMap<String,Object>> listDept  = dao.query("SSBMCtm01.getQrDept", attr);
		outInfo.addBlock("dept").addRows(listDept);
		return outInfo;
	}
	
	/**
	 * 
	 * 查询院区
	 * @Title: queryDept 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:53
	 */
	public EiInfo queryHosArea(EiInfo inInfo) {
	    EiInfo outInfo = new EiInfo();
	    //获取传参
	    Map attr = inInfo.getRow("inqu_status",0);
	    if(attr == null || attr.isEmpty()) {
	        attr = new HashMap<String,Object>();
	    }
	    attr.put("mealgroupTypeCode", "hosArea");
	    List<HashMap<String,Object>> listDept  = dao.query("SSBMStxx01.getMealTypeData", attr);
	    outInfo.addBlock("hosArea").addRows(listDept);
	    return outInfo;
	}
	
	/**
	 * 
	 * 查询病区
	 * @Title: queryDept 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:13:53
	 */
	public EiInfo queryQrArea(EiInfo inInfo) {
	    EiInfo outInfo = new EiInfo();
	    //获取传参
        Map attr = inInfo.getAttr();
        //attr.put("paramValue1", attr.get("areaCode"));
        //attr.put("areaCode", "");
	    List<HashMap<String,Object>> listDept  = dao.query("SSBMBqgl01.query", attr);
	    outInfo.addBlock("qrDept").addRows(listDept);
	    return outInfo;
	}
	

	/**
	 * 
	 * 删除数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo, "SSBMCtm01.delete");
	}


	/**
	 * 
	 * 新增床头码数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
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
				
				//根据地点编码查询
//				String addNum = map.get("addNum").toString();
//				HashMap<String,Object> param = new HashMap<String,Object>();
//				param.put("addNum", addNum);
//				List<SSBMCtm01> queryList = dao.query("SSBMCtm01.query", param);
//				if(queryList.size()>0) {
//					inInfo.setMsg("地点编码重复!");
//					inInfo.setStatus(-1);
//					return inInfo;
//				}
					
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				//修改数据
				map.put("recCreateTime",format);
				map.put("id",UUIDUtils.getUUID32());
				map.put("recCreator",UserSession.getUser().getUsername());
				
				//将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				outInfo = super.insert(inInfo, "SSBMCtm01.insert");
				outInfo.setMsg("保存成功！");
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("保存失败！");
			inInfo.setStatus(-1);
			return inInfo;
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
			// 接收弹窗数据
			ArrayList<HashMap<String, Object>> updateList = (ArrayList<HashMap<String, Object>>) attr.get("submit");
			if (updateList != null && updateList.size() > 0) {
				HashMap<String,Object> map = updateList.get(0);
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				//修改时间，修改人
				map.put("recReviseTime",format);
				map.put("recRevisor",UserSession.getUser().getUsername());
				// 将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				// 更新数据
				outInfo = super.update(inInfo, "SSBMCtm01.update");
				outInfo.setMsg("更新成功！");
			} else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			String rootCauseMessage = ExceptionUtil.getRootCauseMessage(e);
			if(rootCauseMessage.indexOf("Duplicate") > -1 && rootCauseMessage.indexOf("idx_add_num") > -1){
				inInfo.setMsg("地点编码出现重复，请检查");
			}else{
				inInfo.setMsg("更新失败！");
			}
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
}
