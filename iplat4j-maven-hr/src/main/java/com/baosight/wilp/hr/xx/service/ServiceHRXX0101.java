package com.baosight.wilp.hr.xx.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.common.SerialNoUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 *人员信息新增编辑页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存、编辑人员信息  insert
 * @Title: ServiceHRXX0101.java
 * @ClassName: ServiceHRXX0101
 * @Package：com.baosight.wilp.hr.xx.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRXX0101 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * id  主键
	 * @return
	 * 		info
	 * @see ServiceBase#query(EiInfo)
	 */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {
		// 如果传过来的type值为edit则是编辑页面回显数据
		String type = info.getString("type");
		if(type.contains("edit")) {
			Map map = new HashMap();
			map.put("id", info.getString("id") == null ? "" : info.getString("id"));
			// 获取id 查询主表数据
			List<HrMan> list = dao.query("HRXX01.query", map);
			if (CollectionUtils.isEmpty(list)) {
				return info;
			}
			// 获取attr中属性放入info中
			info.setAttr(list.get(0).toMap());
			info.set("type",type);
			// 查询tab页信息,履历信息和证书信息
			List list1 = dao.query("HRXX02.query", map);
			List list2 = dao.query("HRXX03.query", map);
			info.addRows("resultC", list1);
			info.addRows("resultD", list2);
		}
		com.baosight.iplat4j.core.web.threadlocal.UserSession.setOutRequestProperty("type", info.getString("type"));
		return info;
	}

	/**
	 * 保存、编辑人员信息
	 * insert
	 * @param info
	 * id ：主键
	 * name  ：登录人姓名
	 * dataGroupCode ：账套
	 * serviceDeptNum ：服务科室
	 * deptNum     ： 所属科室
	 * realName ：姓名
	 * workNo ： 工号
	 * sex ：性别
	 * birthPlace ：籍贯
	 * kampong  ： 民族
	 * manCode ： 身份证号
	 * jobCode ：岗位
	 * schoolingCode ：学历
	 * salary ：基本工资
	 * birthDate ： 出生日期
	 * politicalStatus ：政治面貌
	 * preInTime ：预入职日期
	 * phone ：联系电话
	 * familyAddress  ：家庭地址
	 * health ：健康状况
	 * company： 公司名称
	 * memo ：备注
	 * @return info
	 */
	@SuppressWarnings("all")
	public EiInfo insert(EiInfo info) {
		// 获取前台传来的数据
		Map<String, Object> userInfo = HrUtils.getUserInfo(UserSession.getUser().getUsername());
		String name =userInfo.get("name").toString();
		String dataGroupCode = userInfo.get("dataGroupCode").toString();
		String serviceDeptNum = info.getString("serviceDeptNum");
		String deptNum = info.getString("deptNum");
		String managementDeptNum = info.getString("managementDeptNum");
		String workNo = "";
		if("add".equals(info.getString("type"))){
			workNo = SerialNoUtils.generateNumberSerialNo("hr_user", "TH", 5);
		}
		String realName = info.getString("realName");
		String sex = info.getString("sex");
		String birthPlace = info.getString("birthPlace");
		String kampong = info.getString("kampong");
		String manCode = info.getString("manCode");
		String maritalStatus = info.getString("maritalStatus");
		String schoolingCode = info.getString("schoolingCode");
		String highestDegree = info.getString("highestDegree");
		String highestEducational = info.getString("highestEducational");
		String jobCode = info.getString("jobCode");
		String politicalStatus = info.getString("politicalStatus");
		String salary = StringUtils.isBlank(info.getString("salary")) ? "0":info.getString("salary");
		String birthDate = info.getString("birthDate");
		String preInTime = info.getString("preInTime");
		String emergency = info.getString("emergency");
		String emergencyPhone = info.getString("emergencyPhone");
		String personnelCategory = info.getString("personnelCategory");
		String phone = info.getString("phone");
		String health = info.getString("health");
		String familyAddress = info.getString("familyAddress");
		String company = info.getString("company");
		String memo = info.getString("memo");
		Map map = new HashMap<>();
		// 获取附件集合
		List<Map<String,String>> fileArray = (List<Map<String, String>>) info.get("fileArray");

		// 封装数据
		map.put("serviceDeptNum", serviceDeptNum);
		map.put("deptNum", deptNum);
		map.put("managementDeptNum", managementDeptNum);
		map.put("workNo", workNo);
		map.put("realName", realName);
		map.put("sex", sex);
		map.put("birthPlace", birthPlace);
		map.put("kampong", kampong);
		map.put("manCode", manCode);
		map.put("maritalStatus", maritalStatus);
		map.put("schoolingCode", schoolingCode);
		map.put("highestDegree", highestDegree);
		map.put("highestEducational", highestEducational);
		map.put("jobCode", jobCode);
		map.put("politicalStatus", politicalStatus);
		map.put("salary", salary);
		map.put("birthDate", birthDate);
		map.put("preInTime", preInTime);
		map.put("emergency", emergency);
		map.put("emergencyPhone", emergencyPhone);
		map.put("personnelCategory", personnelCategory);
		map.put("phone", phone);
		map.put("health", health);
		map.put("familyAddress", familyAddress);
		map.put("company", company);
		map.put("memo", memo);
		// 状态为新建状态
		map.put("statusCode","01");
		// 如果type的值为edit时则是编辑页面
		if ("edit".equals(info.getString("type")) || "in_edit".equals(info.getString("type"))){
			String id =info.getString("id");
			map.put("id",id);
			// 是否存在重复工号
			int i =super.count("HRXX01.queryWorkNoEdit",map);
			if( i>0 ){
				info.setStatus(-1);
				info.setMsg("工号已存在,请重输");
				return info;
			}
			// 封装参数,删除之前的附件，保存人员信息
			map.put("updatorName", name);
			map.put("updateDate", DateUtils.curDateTimeStr19());
			dao.delete("HRXX02.delete",map.get("id"));
			dao.update("HRXX01.update",map);
		}else {
			// 新增页面，查看工号是否存在
			int i =super.count("HRXX01.queryWorkNo",workNo);
			if( i>0 ){
				info.setStatus(-1);
				info.setMsg("工号已存在,请重输");
				return info;
			}
			// 生成id，封装参数
			String id =UUID.randomUUID().toString();
			map.put("id",id);
			map.put("creatorName", name);
			map.put("createDate", DateUtils.curDateTimeStr19());
			dao.insert("HRXX01.insert",map);
			// 入职登记插入履历表中
			HrUtils.insertHistory((String)map.get("id"),"入职登记",dao);
		}
		// 如果证书集合不为空则遍历集合保存到数据库中
		if(!fileArray.isEmpty()) {
			fileArray.forEach(file -> {
				file.put("id", UUID.randomUUID().toString());
				String  id = (String) map.get("id");
				file.put("manId", id);
				dao.insert("HRXX02.insert",file);
			});
		}
		return info;
	}

	public EiInfo queryDept(EiInfo inInfo) {
		List<Map<String, String>> list = dao.query("HRXX01.queryDept", PlatApplicationContext.getProperty("projectSchema"));
		inInfo.addRows("h_dept", list);
		return inInfo;
	}

}
