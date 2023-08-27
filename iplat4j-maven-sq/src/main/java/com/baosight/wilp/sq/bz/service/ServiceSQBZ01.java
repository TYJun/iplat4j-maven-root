package com.baosight.wilp.sq.bz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.sq.bz.domain.SQBZ01;
import com.baosight.wilp.sq.bz.domain.SQBZ03;
import com.baosight.wilp.sq.common.TyepCode;
import com.baosight.wilp.sq.util.PMUtils;
import com.baosight.wilp.utils.DatagroupUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务品质管理考核标准逻辑处理,页面初始化方法,查询方法,删除方法,更新方法,考核主题
 * <p>页面初始化方法   initLoad
 * <p>查询方法  query
 * <p>删除方法  delete
 * <p>更新方法  update
 * <p>考核主题  standardName
 *
 * @Title: ServiceSQBZ01.java
 * @ClassName: ServiceSQBZ01
 * @Package：com.baosight.wilp.sq.bz.service
 * @author: zhangjiaqian
 * @date: 2021年7月19日 下午2:02:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQBZ01 extends ServiceBase {


	/**
	 * 页面初始化方法
	 *
	 * @param inInfo id                       主键
	 *               standardName             标准编码
	 *               standardCode             标准名称
	 *               remark                   备注
	 *               creator                  创建人
	 *               createTime               创建时间
	 *               modifier                 修改人
	 *               modifyTime               修改时间
	 *               assessDeptName           调查对象
	 *               assessWorkNoLeader       评价负责人
	 *               assessWorkNameLeader     评价人
	 *               assessContactTel         负责人电话
	 *               assessTypeCode           问卷类型编码
	 *               assessMail               负责人邮箱
	 *               assessTypeName           问卷类型名
	 * @return id                       主键
	 * standardName             标准编码
	 * standardCode             标准名称
	 * remark                   备注
	 * creator                  创建人
	 * createTime               创建时间
	 * modifier                 修改人
	 * modifyTime               修改时间
	 * assessDeptName           调查对象
	 * assessWorkNoLeader       评价负责人
	 * assessWorkNameLeader     评价人
	 * assessContactTel         负责人电话
	 * assessTypeCode           问卷类型编码
	 * assessMail               负责人邮箱
	 * assessTypeName           问卷类型名
	 * @Title initLoad
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SQBZ01.query", new SQBZ01());
		return outInfo;
	}


	/**
	 * 查询方法
	 *
	 * @param inInfo id                       主键
	 *               standardName             标准编码
	 *               standardCode             标准名称
	 *               remark                   备注
	 *               creator                  创建人
	 *               createTime               创建时间
	 *               modifier                 修改人
	 *               modifyTime               修改时间
	 *               assessDeptName           调查对象
	 *               assessWorkNoLeader       评价负责人
	 *               assessWorkNameLeader     评价人
	 *               assessContactTel         负责人电话
	 *               assessTypeCode           问卷类型编码
	 *               assessMail               负责人邮箱
	 *               assessTypeName           问卷类型名
	 * @return id                       主键
	 * standardName             标准编码
	 * standardCode             标准名称
	 * remark                   备注
	 * creator                  创建人
	 * createTime               创建时间
	 * modifier                 修改人
	 * modifyTime               修改时间
	 * assessDeptName           调查对象
	 * assessWorkNoLeader       评价负责人
	 * assessWorkNameLeader     评价人
	 * assessContactTel         负责人电话
	 * assessTypeCode           问卷类型编码
	 * assessMail               负责人邮箱
	 * assessTypeName           问卷类型名
	 * @Title query
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		int offset, limit;
		// 判断是否分页
		if (inInfo.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			// 获取分页组件中的起始条目与每页大小
			EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
		Map<String, Object> map = (Map<String, Object>) inqu_status.getRows().get(0);
		List list = dao.query("SQBZ01.query", map, offset, limit);
		int count = dao.count("SQBZ01.query", map);
		inInfo.setRows("result", list);
		// 处理分页
		EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
		result.setAttr(new HashMap<>());
		if(result.getAttr().isEmpty()){
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return inInfo;
	}


	/**
	 * 修改方法
	 *
	 * @param inInfo id                       主键
	 *               standardName             标准编码
	 *               standardCode             标准名称
	 *               remark                   备注
	 *               creator                  创建人
	 *               createTime               创建时间
	 *               modifier                 修改人
	 *               modifyTime               修改时间
	 *               assessDeptName           调查对象
	 *               assessWorkNoLeader       评价负责人
	 *               assessWorkNameLeader     评价人
	 *               assessContactTel         负责人电话
	 *               assessTypeCode           问卷类型编码
	 *               assessMail               负责人邮箱
	 *               assessTypeName           问卷类型名
	 * @return id                       主键
	 * standardName             标准编码
	 * standardCode             标准名称
	 * remark                   备注
	 * creator                  创建人
	 * createTime               创建时间
	 * modifier                 修改人
	 * modifyTime               修改时间
	 * assessDeptName           调查对象
	 * assessWorkNoLeader       评价负责人
	 * assessWorkNameLeader     评价人
	 * assessContactTel         负责人电话
	 * assessTypeCode           问卷类型编码
	 * assessMail               负责人邮箱
	 * assessTypeName           问卷类型名
	 * @Title update
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		return super.update(inInfo);
	}


	/**
	 * 删除方法
	 *
	 * @param inInfo id
	 * @return Status       状态码
	 * Msg          状态信息
	 * @Title delete
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		//获取id
		Object object = inInfo.get("id");
		if (null == object || "" == object) {
			inInfo.setStatus(-1);
			inInfo.setMsg("删除失败，请联系管理员");
			return inInfo;
		}
		//查询数据
		List<Map> code = dao.query("SQBZ01.queryStandardCode", (String) object);
		if (null != code || code.size() != 0) {
			Map map = code.get(0);
			String object2 = map.get("count(1)").toString();
			int parseInt = Integer.parseInt(object2);
			if (parseInt != 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("标准已在问卷中使用，无法删除");
				return inInfo;
			}
		}
		//查询数据
		List<Map> query = dao.query("SQBZ01.queryProjectID", (String) object);
		if (null == query || query.size() == 0) {
			//删除标准
			dao.delete("SQBZ01.deleteStandard", (String) object);
		} else {
			//删除标准
			dao.delete("SQBZ01.deleteStandard", (String) object);
			//删除项目
			dao.delete("SQBZ01.deleteProject", (String) object);
			//删除项目标准
			for (Map map : query) {
				dao.delete("SQBZ01.deleteProjectInstance", map);
			}
		}
		//返回状态码
		inInfo.setStatus(1);
		//返回前台Msg
		inInfo.setMsg("删除成功");
		return inInfo;
	}


	/**
	 * @param inInfo datagroupcode            账套
	 * @return standardName             标准名称
	 * standardCode             标准编码
	 * @Title: standardName
	 * @return: EiInfo
	 */
	public EiInfo standardName(EiInfo inInfo) {
		//获取账套
		String datagroupcode = DatagroupUtil.getDatagroupCode();
		Map map = new HashMap();
		//封装数据
		map.put("datagroupcode", datagroupcode);
		//查询数据
		List<SQBZ01> list = dao.query("SQBZ01.queryStandardName", map);
		//构建返回信息
		inInfo.addBlock("standardName").addRows(list);
		inInfo.getBlock("standardName").setBlockMeta(new SQBZ01().eiMetadata);
		//返回
		return inInfo;
	}

	/**
	 * 删除问卷标准中的所有的信息
	 * 1.接收传参及处理参数
	 * 2.查询问卷下所有的项目并遍历删除
	 * 3.删除成功返回提示信息
	 * 4.对于可能存在的异常进行捕获，并返回提示语
	 * @Title deleteAll
	 * @param info
	 * @return EiInfo
	 * @author zhaowei
	 * @date 2022/3/25 14:50
	 */
	public EiInfo deleteAll(EiInfo info) {
		try {
			/**1.接收传参及处理参数*/
			// 接收传参
			String standardId = info.getString("standardId");
			// 处理参数
			Map<String, Object> map = new HashMap<>();
			map.put("standardId", standardId);
			/**2.查询问卷下所有的项目并遍历删除*/
			// 通过问卷Id查询到所有项目
			List<Map<String, Object>> ProjectList = dao.query("SQBZ0101.queryProjectByStandardId", map);
			// 遍历获取的项目
			for (Map<String, Object> ProjectMap : ProjectList) {
				// 通过项目Id查询所有的问题
				List<Map<String, Object>> instanceList = dao.query("SQBZ0101.queryInstanceByProjectId", ProjectMap);
				// 遍历获取到的问题
				for (Map<String, Object> instanceMap : instanceList) {
					// 删除该题目下的所有选项
					dao.delete("SQBZ010101.deleteSqInstanceItem", instanceMap);
					// 删除题目
					dao.delete("SQBZ010101.deleteSqInstance", instanceMap);
				}
				// 删除项目
				dao.delete("SQBZ0101.deleteProject", ProjectMap);
			}
			// 删除问卷信息
			dao.delete("SQBZ01.deleteStandard", map);
			/**3.删除成功返回提示信息*/
			info.setStatus(1);
			info.setMsg("操作成功！");
		} catch (Exception e){
			/**4.对于可能存在的异常进行捕获，并返回提示语*/
			info.setMsg("操作失败！" + e.getMessage());
			info.setStatus(-1);
		}
		return info;
	}

	/**
	 * 问卷类型
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/14 17:04
	 */
	public EiInfo sqType(EiInfo inInfo) {
		//创建集合
		List<Map<String, String>> param = new ArrayList<Map<String, String>>();
		try {
			//获取小代码
			param = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.sq.sqType");
			//封装参数
			inInfo.addBlock("sqType").addRows(param);
			inInfo.getBlock("sqType").setBlockMeta(new SQBZ03().eiMetadata);
		} catch (Exception e) {
			e.printStackTrace();
			return inInfo;
		}
		//返回
		return inInfo;
	}


	public EiInfo queryAdmin(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "contAdmin");
		map.put("userName", map.get("name"));
		// 2.获取blockId
		String blockId = info.getBlockIds();
		// 3.如果blockId相等
		if (blockId.equals("inqu_status,person")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("person");
			// 4.获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 5.获取集合中的数据
			String name = (String) listMap.get(0).get("name");
			// 设置userName
			map.put("userName", name);
			// 实例化List
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 6.调用改造人员接口并返回
			EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
			// 7.如果存在人员信息
			if (outInfo.getBlock("person") != null) {
				// 8.获取人员信息
				list = outInfo.getBlock("person").getRows();
				// 9.如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 获取人员信息总数
				count = (int) outInfo.getBlock("person").getAttr().get("count");
				// 10.返回封装的方法：构建返回结果EiInfo
				return CommonUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 11.调用改造人员接口并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 12.调用改造人员接口并返回
		EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "contAdmin");
		// 13.如果存在人员信息
		if (outInfo.getBlock("contAdmin") != null) {
			// 14.获取人员信息
			list = outInfo.getBlock("contAdmin").getRows();
			// 如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 获取人员信息总数
			count = (int) outInfo.getBlock("contAdmin").getAttr().get("count");
			// 15.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "contAdmin", PMUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}

}
