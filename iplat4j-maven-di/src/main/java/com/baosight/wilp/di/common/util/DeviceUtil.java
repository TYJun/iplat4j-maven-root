package com.baosight.wilp.di.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.di.common.domain.ResultData;

/**
 * 
 * 工具类：已不用
 * 
 * @Title: DeviceUtil.java
 * @ClassName: DeviceUtil
 * @Package：com.baosight.wilp.di.common.util
 * @author: LENOVO
 * @date: 2021年8月11日 下午4:16:30
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class DeviceUtil {

//	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
//	private static final Base64.Decoder decoder = Base64.getDecoder();
//	private static final Base64.Encoder encoder = Base64.getEncoder();
//	public static List<String> list;
//	public static String basePath;

//	public static EiInfo changeToEiInfo(List list, String resultName) {
//		if (StringUtils.isBlank(resultName)) {
//			resultName = "result";
//		}
//		EiInfo outInfo = new EiInfo();
//
//		EiBlock eiBlock = new EiBlock(new EiBlockMeta());
//		eiBlock.setRows(list);
//		HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
//		hashMap.put(resultName, eiBlock);
//		outInfo.setBlocks(hashMap);
//		if (!CollectionUtils.isEmpty(list)) {
//			outInfo.setMsg("查询成功");
//		} else {
//			outInfo.setMsg("未获取到数据");
//		}
//		return outInfo;
//
//	}
//
//	public static EiInfo changeToEiInfo(List list) {
//		return changeToEiInfo(list, null);
//	}
//
//	public static EiInfo changeToEiInfo(EiInfo info, List list, String resultName) {
//		if (StringUtils.isBlank(resultName)) {
//			resultName = "result";
//		}
//		EiBlock eiBlock = new EiBlock(new EiBlockMeta());
//		eiBlock.setRows(list);
//		HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
//		hashMap.put(resultName, eiBlock);
//		info.setBlocks(hashMap);
//		if (!CollectionUtils.isEmpty(list)) {
//			info.setMsg("查询成功");
//		} else {
//			info.setMsg("未获取到数据");
//		}
//		return info;
//
//	}
//
//	public static Map<String, String> changeToMap(EiInfo inInfo, List<String> list) {
//		Map<String, String> map = new HashMap<>();
//		Map param = inInfo.getAttr();
//		list.forEach(e -> map.put(e, (String) param.get(e)));
//		return map;
//	}
//
//	public static ResultData putResponse(List<Map<String, Object>> taskList, String respCode, String respMsg) {
//		ResultData resultData = new ResultData();
//		resultData.setRespCode(respCode);
//		resultData.setRespMsg(respMsg);
//		resultData.setList(taskList);
//		return resultData;
//	}
//
//	public static String createTop() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
//		String head = sdf.format(new Date());
//		return head;
//	}
//
//	public static String getNextCreateTime(String cycle, String unit, String startTime) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		int code = Integer.parseInt(cycle);
//		try {
//			Date date = sdf.parse(startTime);
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			switch (unit) {
//			case "h":
//				calendar.add(Calendar.HOUR, code);
//				break;
//			case "d":
//				calendar.add(Calendar.DAY_OF_YEAR, code);
//				break;
//			case "m":
//				calendar.add(Calendar.MONTH, code);
//				break;
//			}
//			String result = sdf.format(calendar.getTime());
//			return result;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return startTime;
//	}
//
//	public static boolean checkHoliday() {
//		String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		if (!CollectionUtils.isEmpty(list) && list.contains(dateStr)) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * base64图片转字节数组
//	 * 
//	 * @param img
//	 * @return
//	 */
//	public static byte[] castToImg(String img) {
//		// base64转化为字节数组
//		return decoder.decode(img);
//	}
//
//	public static String castToBase64(String url) {
//		byte[] b = getByte(url);
//		return encoder.encodeToString(b);
//	}
//
//	public static byte[] getByte(String picUrl) {
//		File file = new File(picUrl);
//		try (FileInputStream fis = new FileInputStream(file);
//				ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//			int len;
//			byte[] buffer = new byte[1024];
//			while ((len = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, len);
//			}
//			return baos.toByteArray();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 图片保存公共方法
//	 * 
//	 * @param path
//	 * @param taskNo
//	 * @param node
//	 * @param type
//	 * @param pics
//	 */
//	public static void dealPic(String showPath, String savePath, String item_id, String task_id, String pics) {
//		File files = new File(savePath);
//		if (!files.isDirectory()) {
//			files.mkdir();
//		}
//		String picId = UUID.randomUUID().toString();
//		File file = new File(savePath + picId + ".jpg");
//		Map<String, String> map = new HashMap<>();
//		map.put("id", picId);
//		map.put("item_id", item_id);
//		map.put("task_id", task_id);
//		map.put("path", showPath + picId);
//		dao.insert("DVDT0101.addPic", map);
//		try (FileOutputStream outStream = new FileOutputStream(file)) {
//			outStream.write(castToImg(pics));
//			;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}
