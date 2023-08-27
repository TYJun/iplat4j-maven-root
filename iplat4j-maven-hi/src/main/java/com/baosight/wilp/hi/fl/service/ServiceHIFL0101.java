package com.baosight.wilp.hi.fl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hi.common.domain.HiClassify;
import com.baosight.wilp.hi.common.util.HIUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 医院标识分类子页面Service
 * 
 * <p>页面加载</p>
 * <p>医院标识分类保存</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHIFL0101.java
 * @ClassName:  ServiceHIFL0101
 * @Package com.baosight.wilp.hi.fl.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年8月19日 上午11:28:50
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHIFL0101 extends ServiceBase {

	/**
	 * 页面加载
	 *
	 * <p>当页面是编辑页面时回显页面数据</p>
	 * 1、获取类型
	 * 2、判断类型，是否是编辑
	 * 3、编辑时，显示数据
	 *
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		//1、获取类型
		String type = inInfo.getString("type");
		//2、判断类型是否是edit
		if("edit".equals(type)){
			//是编辑时，获取id
			Map<String, String> pMap = new HashMap<>(4);
			pMap.put("id", inInfo.getString("id"));
			//通过id，查询医院标识分类信息
	        List<HiClassify> list = dao.query("HIFL01.query", pMap);
	        HiClassify hiClassify = list.get(0);
	        inInfo.getAttr().putAll(hiClassify.toMap());
		}
		return inInfo;
    }
	
	/**
	 * 医院标识分类保存接口
	 *
	 * <p>根据操作类型判断是新增，还是修改</p>
	 * 1、获取获取类型
	 * 2、判断是否是新增，新增时生成单号，将信息保存到数据库
	 * 3、不是新增时，更新数据到数据库
	 *
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo save(EiInfo inInfo){
		//1、获取类型
		String type = inInfo.getString("type");
		HiClassify fl = new HiClassify();
		fl.fromMap(inInfo.getAttr());
		//获取参数
		String classifyName = inInfo.getString("classifyName");
		String parentId = inInfo.getString("parentId");
		String errorMsy = "";
		//校验参数是否填写
		if(StringUtils.isBlank(classifyName)){
			errorMsy = errorMsy + "医院标识分类名称不能为空\r\n";
		}
		if(StringUtils.isBlank(parentId)){
			errorMsy = errorMsy + "上级分类不能为空\r\n";
		}
		if(errorMsy.length()>0){
			inInfo.setMsg(errorMsy);
			return  inInfo;
		}
		//判断类型是否为新增
		if("add".equals(type)){
			//是新增时，生成单号和信息，保存到数据库
			fl.setId(UUID.randomUUID().toString());
			//生成单号
			fl.setClassifyNum(HIUtils.generationSerialNo("hospital_hi_fl", "HIFL", "1"));
			fl.setRecCreateTime(DateUtils.curDateTimeStr19());
			fl.setRecCreator(UserSession.getUser().getUsername());
			//插入到数据库
			dao.insert("HIFL01.insert", fl);
		} else {
			//是编辑时，更新信息和修改时间、修改人，保存到数据库
			fl.setRecReviseTime(DateUtils.curDateTimeStr19());
			fl.setRecRevisor(UserSession.getUser().getUsername());
			//更新到数据库
			dao.update("HIFL01.update", fl);
		}
		inInfo.setMsg("保存成功");
		return inInfo;
	}



}
