package com.baosight.wilp.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

// 创建编号工具类
public class CreateNoUtil extends ServiceBase {
	// 获取dao
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	// 生成36位UUID
	public static String CreateUUID36(){
		return UUID.randomUUID().toString();
	}

	// 生成32位UUID
	public static String CreateUUID32(){
		return UUID.randomUUID().toString().replace("-","");
	}

	// 项目阶段编码生成方法
	public static String CreateStageNo(ProjectEnum projectEnum){
		String stageNo = "";
		// switch case判断分支
		switch (projectEnum.getIndex()){
			// 当projectEnum.getIndex()==1
			case 1:
				// sql语句
				List<Map<String,Object>> stageList = dao.query("PMPG01.queryStageCodeMax", projectEnum.getName() + DateUtilsTools.getDatePlus());
				// 判断是否存在编号
				if(CollectionUtils.isEmpty(stageList)){
					stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "001";
				}else {
					// 获取编码
					String stageCode = (String) stageList.get(0).get("stageCode");
					// 获取后缀
					String substring = stageCode.substring(12);
					// 后缀+1
					Integer maxNo = Integer.parseInt(substring) + 1;
					// 后缀位数补位
					if(maxNo < 10){
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "00" + String.valueOf(maxNo);
					}else if(maxNo < 100){
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "0" + String.valueOf(maxNo);
					}else {
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + String.valueOf(maxNo);
					}
				}
				break;
			case 2:
				// sql语句
				List<Map<String,Object>> typeList = dao.query("PMPG02.queryTypeCodeMax", projectEnum.getName() + DateUtilsTools.getDatePlus());
				// 判断是否存在编号
				if(CollectionUtils.isEmpty(typeList)){
					stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "001";
				}else {
					// 获取编码
					String typeCode = (String) typeList.get(0).get("typeCode");
					// 获取后缀
					String substring = typeCode.substring(12);
					// 后缀+1
					Integer maxNo = Integer.parseInt(substring) + 1;
					// 后缀位数补位
					if(maxNo < 10){
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "00" + String.valueOf(maxNo);
					}else if(maxNo < 100){
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + "0" + String.valueOf(maxNo);
					}else {
						stageNo = projectEnum.getName() + DateUtilsTools.getDatePlus() + String.valueOf(maxNo);
					}
				}
				break;
			case 3:

				break;
		}

		// 返回编号
		return stageNo;
	}
}
