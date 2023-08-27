package com.baosight.wilp.hi.bz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.wilp.hi.common.domain.HiStandard;
import com.baosight.wilp.hi.common.util.HIUtils;
import com.baosight.wilp.hi.common.util.saveFile;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 标识标准子页面Service
 * 
 * <p>页面加载</p>
 * <p>标识标准基础信息保存</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHIBZ0101.java
 * @ClassName:  ServiceHIBZ0101
 * @Package com.baosight.wilp.hi.bz.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年8月21日 下午1:13:50
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHIBZ0101 extends ServiceBase {

	/**
	 * 医院标准管理新增页面加载
	 *
	 * <p>当页面是编辑页面时回显页面数据</p>
	 * 1、获取参数（类型）
	 * 2、判断是否是编辑或查看
	 * 3、是。回显数据
	 * 4、否。返回
	 *
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		//1、获取参数(类型、add/edit/see)
		String type = inInfo.getString("type");
		//2、判断是否为编辑或查看
		if("edit".equals(type) || "see".equals(type)){
			//2.1、是。回显数据
			Map<String, String> pMap = new HashMap<>(4);
			//获取id
			pMap.put("id", inInfo.getString("id"));
			//通过id，获取标准信息(标准名称、标识分类、备注)
	        List<HiStandard> standardInformation = dao.query("HIBZ01.query", pMap);
	        //通过id，获取附件信息
			List<HiFile> standardFail = dao.query("HIBZ01.queryId", pMap);
	        HiStandard hiStandard = standardInformation.get(0);
	        inInfo.getAttr().putAll(hiStandard.toMap());
			EiBlock block = new EiBlock("resultB");
			block.setRows(standardFail);
			inInfo.setBlock(block);
		}
		//2.2、返回
		return inInfo;
    }
	
	/**
	 * 医院标识标准保存接口
	 *
	 * <p>根据操作类型判断是新增，还是修改</p>
	 * 1、获取参数（类型）
	 * 2、判断是否为新增
	 * 3、是。将数据插入到数据库
	 * 4、否。更新数据
	 * 5、保存附件
	 * 6、返回数据
	 *
	 * @Title: save
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 */
	public EiInfo save(EiInfo inInfo){
		//1、获取参数(类型)
		String type = inInfo.getString("type");
		HiStandard fl = new HiStandard();
		fl.fromMap(inInfo.getAttr());
       	fl.setClassifyName((String) inInfo.getAttr().get("classifyId_textField"));
		Object fileObj = inInfo.getAttr().get("file");
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.getAttr().get("deleteFile");
		/*
		* 获取参数
		* standardName：标识名称
		* classifyName：标识分类
		* */
		String standardName = inInfo.getString("standardName");
		String classifyName = (String) inInfo.getAttr().get("classifyId_textField");
		String errorMsg = "";
		/*
		* 1、判断如果标识名称为空时，提示：标识名称不能为空
		* 2、判断如果标识分类为空时，提示：标识分类不能为空
		* */
		if(StringUtils.isBlank(standardName)){
			errorMsg = errorMsg + "标识名称不能为空\r\n";
		}
		if(StringUtils.isBlank(classifyName)){
			errorMsg = errorMsg+"标识分类不能为空\r\n";
		}
		if(errorMsg.length()>0){
			inInfo.setMsg(errorMsg);
			return inInfo;
		}
		//2、判断是否为新增
		if("add".equals(type)){
			//2.1、是。将数据保存到数据库
			fl.setId(UUID.randomUUID().toString());
			//生成标准编码
			fl.setStandardNum(HIUtils.generationSerialNo("hospital_hi_bz", "HIBZ", "1"));
			fl.setRecCreateTime(DateUtils.curDateTimeStr19());
			fl.setRecCreator(UserSession.getUser().getUsername());
			dao.insert("HIBZ01.insert", fl);
		} else {
			//2.2、否。更新数据
			fl.setRecReviseTime(DateUtils.curDateTimeStr19());
			fl.setRecRevisor(UserSession.getUser().getUsername());
			dao.update("HIBZ01.update", fl);
		}
		//3、保存附件
		saveFile.saveFile(fileObj, fl.getId(), deleteFile, null);
		inInfo.setMsg("保存成功");
		//4、返回
		return inInfo;
	}

}
