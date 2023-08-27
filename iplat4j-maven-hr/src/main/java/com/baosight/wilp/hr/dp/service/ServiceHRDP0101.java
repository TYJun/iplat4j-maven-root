package com.baosight.wilp.hr.dp.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.dp.domain.HrJobChangeBill;
import com.baosight.wilp.hr.xx.domain.HrMan;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存调派信息 insert
 * <p>3.保存调派人员信息 saveJobMan
 *
 * @Title: ServiceHRDP0101.java
 * @ClassName: ServiceHRDP0101
 * @Package：com.baosight.wilp.hr.dp.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRDP0101 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
	 * id  主键
	 * type 1.获取type类型，
	 * 	    2.如果类型是edit则是编辑页面回显数据
     * @return
     * 		info
     * @see ServiceBase#query(EiInfo)
     */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {
		// 1.获取type类型，
		// 2.如果类型是edit则是编辑页面回显数据
			if(info.getString("type").equals("edit")) {
				Map map = new HashMap();
				map.put("id", info.getString("id") == null ? "" : info.getString("id"));
				map.put("billNo", info.getString("billNo") == null ? "" : info.getString("billNo"));
				List<HrJobChangeBill> list = dao.query("HRDP01.query", map);
				if (CollectionUtils.isEmpty(list)) {
					return info;
				}
				// 获取attr中属性放入info中
				info.setAttr(list.get(0).toMap());
				info.set("type","edit");
				// 查询tab页信息
				List list1 = dao.query("HRDP02.query", map);
				info.addRows("result", list1);
			}
			// 返回
		return info;
	}

	/** 保存调派工单
	 * insert
	 * @param info
	 * type 类型
	 * id 主键
	 * name 登录人
	 * billTime  申请日期
	 * arriveTime 到岗日期
	 * leaveTime 离岗日期
	 * deptNum 申请科室
	 * shiftTimeSection 支援时段
	 * shiftFirstTime 上班时间
	 * changeCode 支援类别
	 * supportStation 支援岗位
	 * numbers 支援人数
	 * becauseMemo 申请原因
	 * billNo 工单号
	 * rows  人员配置信息
	 * @return
	 */
	@SuppressWarnings("all")
	public EiInfo insert(EiInfo info) {
		//  获取前台传来的参数
		Map<String, Object> useInfo = HrUtils.getUserInfo(UserSession.getUser().getUsername());
		String id = StringUtils.isBlank(info.getString("id"))? UUID.randomUUID().toString() : info.getString("id");
		String name =(String) useInfo.get("name")==null ? "" : (String) useInfo.get("name");
        String billTime = info.getString("billTime");
        String arriveTime = info.getString("arriveTime");
        String leaveTime = info.getString("leaveTime");
        String deptNum = info.getString("deptNum");
        String shiftTimeSection = info.getString("shiftTimeSection");
        String shiftFirstTime = info.getString("shiftFirstTime");
        String changeCode = info.getString("changeCode");
        String supportStation = info.getString("supportStation");
        String numbers = info.getString("numbers");
        String becauseMemo = info.getString("becauseMemo");
		List<Map<String,String>> rows =(List<Map<String, String>>)info.get("rows");
		String billNo =StringUtils.isBlank(info.getString("billNo")) ? DateUtils.curDateTimeStr14() : info.getString("billNo");

		// 封装参数
		Map map =new HashMap();
		map.put("id",id);
		map.put("billTime",billTime);
		map.put("arriveTime",arriveTime);
		map.put("leaveTime",leaveTime);
		map.put("deptNum",deptNum);
		map.put("shiftTimeSection",shiftTimeSection);
		map.put("shiftFirstTime",shiftFirstTime);
		map.put("changeCode",changeCode);
		map.put("supportStation",supportStation);
		map.put("numbers",numbers);
		map.put("becauseMemo",becauseMemo);
		map.put("billNo",billNo);
		// 状态01为新建状态
		map.put("statusCode","01");
		// 判断type类型 如果类型是eidt则是编辑页面否则是新增页面
		if (info.getString("type").equals("edit")){
			map.put("recReviseTime",DateUtils.curDateTimeStr19());
			//更新主表
			dao.update("HRDP01.update",map);
			//根据单号删除调派人员信息到人员表中信息
			dao.delete("HRDP02.delete",billNo);
			//保存调派人员信息到人员表中
			saveJobMan(rows,billNo);
			info.setMsg("更新成功");
			info.setStatus(1);
			return info;
		}else {
			// 新增处理，将封装好的数据保存到调派主表和调派人员表中
			map.put("creatorName",name);
			map.put("createDate",DateUtils.curDateTimeStr19());
			dao.insert("HRDP01.insert",map);
			saveJobMan(rows,billNo);
			info.setMsg("新增成功");
			info.setStatus(1);
			return info;
		}
	}

	/**
	 * 保存调派人员信息
	 * saveJobMan
	 * @param
	 * rows ：调派人员
	 * billNo ：调派工单号
	 */
	public void saveJobMan(List<Map<String, String>> rows, String billNo) {
		//  遍历调派人员信息保存到调派人员表中
		rows.forEach(e -> {
			e.put("id", UUID.randomUUID().toString());
			e.put("billNo", billNo);
			dao.insert("HRDP02.insert", e);
		});
	}

}
