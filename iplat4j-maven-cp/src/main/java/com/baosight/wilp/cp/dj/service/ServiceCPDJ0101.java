package com.baosight.wilp.cp.dj.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.domain.CpFile;
import com.baosight.wilp.cp.util.CPUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
/**
 * 该页面为投诉登记管理
 * 主要包含对投诉信息的新增功能
 * 投诉管理模块：初始化查询、保存弹窗信息、、TabGrid查询方法 queryTabGrid
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveContent
 * <p>3.保存图片和投诉信息  insert
 * <p>4.vue前端保存接口 saveCon
 * <p>5.TabGrid查询方法 queryTabGrid
 * <p>6.获取小代码值 getComplaintType
 * <p>7.上传图片回显 showTempPic
 * @Title: ServiceCPDJ0101.java
 * @ClassName: ServiceCPDJ0101
 * @Package：com.baosight.wilp.cp.dj.service
 * @author: liangyongfei
 * @date: 2022年4月20日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCPDJ0101 extends ServiceBase {
	/**
	 * @param inInfo id 主键
	 *               type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * 投诉登记管理弹出界面
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getString("id");
		String type = inInfo.getString("type");
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new CpBill().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 调用查询方法,查询列表信息
			List<HashMap<String, String>> list = dao.query("CPDJ01.queryId", map);
			map.put("billNo", inInfo.getString("billNo"));
			//调用查询方法，查询图片
			List<Map<String,String>> list1=dao.query("CPDJ01.showPic",map);
			list1.forEach(map8 ->{
				map8.put("base64", CommonUtils.imageToBase64Str(map8.get("path")));
				map8.put("path","");
			});

			inInfo.set("list", list);
			inInfo.set("list1", list1);
			// 数据返回
			block.addRows(list);
			block.addRows(list1);
			inInfo.set("type",type);
			inInfo.set("complaintContent",list.get(0).get("complaintContent"));
		}else{
			// 获取当前人信息
			Map<String, Object> userInfo = CPUtils.getUserInfo(null);
			if(userInfo !=null){
//				inInfo.set("inqu_status-0-deptName", userInfo.get("deptName"));
//				inInfo.set("inqu_status-0-deptName_textField", userInfo.get("deptName"));
//				inInfo.set("inqu_status-0-deptName_valueField", userInfo.get("deptName"));
				inInfo.set("inqu_status-0-complaintName", userInfo.get("name"));
				inInfo.set("inqu_status-0-complaintPhone", userInfo.get("contactTel"));
				inInfo.set("inqu_status-0-complaintDept", userInfo.get("deptName"));
				inInfo.set("inqu_status-0-recCreator", userInfo.get("workNo"));
			}
		}
		inInfo.addBlock(block);
		// 返回参数
		inInfo.set("inqu_status-0-type",inInfo.getString("type"));
		return inInfo;
	}


	/**
     * @Title: saveContent
     * @Description: 保存弹窗信息
     * @param inInfo
     * complaint_date 发生日期
     * complaint_name 投诉人
     * complaint_phone 电话
	 * complaint_dept 部门/单位
     * complaint_email 邮箱
     * complaint_type 投诉类别
     * complaint_way 投诉方式
     * complaint_content 投诉内容
     * @return EiInfo
	 * id 主键
	 * billNo 投诉工单号
	 * RecCreateName 创建人名字
	 * RecCreateTime 登记时间
     */
	public EiInfo saveContent(EiInfo inInfo) {
		// 获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);

		// 保存项目
		CpBill cpBill = new CpBill();
		cpBill.fromMap(param);//fromMap实体类
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 如果参数为空
		if (StringUtils.isBlank(cpBill.getId())) {
			cpBill.setId(UUID.randomUUID().toString());
			cpBill.setBillNo(createBillNo());
			cpBill.setRecCreator((String)staffByUserId.get("workNo"));
			cpBill.setRecCreateName((String)staffByUserId.get("name"));
			cpBill.setStatusCode("00");
			// 保存项目
			dao.insert("CPDJ01.insert", cpBill);
		} else {
			if ("deal".equals((String) param.get("type"))){
				cpBill.setDeptNum((String) inInfo.get("deptNum"));
				cpBill.setDeptName((String) inInfo.get("deptName"));
				cpBill.setStatusCode("1");
				dao.update("CPDJ01.update", cpBill);
			} else {
				// 更新项目
				cpBill.setRecRevisor((String)staffByUserId.get("workNo"));
				cpBill.setRecRevisor((String)staffByUserId.get("name"));
				cpBill.setStatusCode("00");
				dao.update("CPDJ01.update", cpBill);
			}
		}
		//页面返回
		inInfo.set("billNo",cpBill.getBillNo());
		return inInfo;

	}

	public EiInfo completed(EiInfo inInfo) {
		String billNo = inInfo.getString("billNo");
		String deptNum = inInfo.getString("deptNum");
		String deptName = inInfo.getString("deptName");
		dao.update("CPDJ0101.completed",new HashMap<String,String>(){{
			put("billNo",billNo);
			put("deptNum",deptNum);
			put("deptName",deptName);
		}});
		return inInfo;
	}

	/**
	 * @param
	 * @Title: createBillNo
	 * @Description: 生成投诉编号
	 * <p>1.加锁
	 * <p>2.调用时间接口
	 * <p>3.调用查询方法
	 * <p>4.如果结果为空
	 * <p>5.获取最大投诉单号
	 * <p>6.对最大投诉单号进行拆分
	 * 通过获取当前时间
	 * 判断今天是否存在投诉编号，不存在就返回新的投诉单号
	 * 存在投诉编号，对投诉编号进行累加
	 * @return: String
	 * String 合同编号
	 */
	private String createBillNo() {
		// 调用时间接口
		String date = DateUtils.curDateStr8();
		// 调用查询方法
		List<String> list = dao.query("CPDJ0101.createBillNo","CP" + date);
		// 如果结果为空
		if (list == null || list.size() == 0 || list.get(0) == null) {
			// 返回合同号
			return "CP" + date + "0001";
		} else {
			// 获取最大合同号
			String maxNo = list.get(0);
			// 对最大合同号进行拆分
			maxNo = maxNo.substring(2);
			// 返回合同号
			return "CP" + (Long.parseLong(maxNo) + 1L) + "";
		}

	}

	/**
	 * @Title: insert
	 * @Description: 插入
	 * PC前端通过接口讲所填写的信息插入
	 * @param
	 * @return: String
	 * billNo 投诉工单
	 */

	@Override
	public EiInfo insert(EiInfo inInfo) {
		// 保存工单
		inInfo.set(EiConstant.serviceName, "CPDJ0101");
		inInfo.set(EiConstant.methodName, "saveContent");
		EiInfo outInfo = XLocalManager.call(inInfo);

		// 保存图片
		inInfo.set("billNo",outInfo.getString("billNo"));
		inInfo.set(EiConstant.serviceName, "CPDJ01");
		inInfo.set(EiConstant.methodName, "savePicture");

		outInfo = XLocalManager.call(inInfo);
		outInfo.set("type",inInfo.getString("type"));
		outInfo.addMsg("保存工单操作成功");
		outInfo.setMsgKey("200");

		Thread thread = new Thread(){
			@Override
			public void run() {
				Boolean flag;
				// 保存以后给工会发送信息，根据人员权限来，不根据配置表来
				if ("deal".equals(inInfo.getString("type"))){
					flag = CPUtils.pushMsg(inInfo.getString("deptNum"),inInfo.getString("deptName"),"deal");
				} else {
					flag = CPUtils.pushMsg("","工会","");
				}
			}
		};
		thread.run();
		return outInfo;
	}


	/**
	 * @Title: savaCon
	 * @Description: 保存提交的投诉工单信息
	 * Vue前端通过提交按钮讲所填写的信息报错到这里
	 * 生成工单号，返回回去
	 * @param  inInfo
	 * complaintDate 发生日期
	 * complaintPhone 电话
	 * complaintName 投诉人
	 * complaintEmail 邮箱
	 * complaintType 投诉类别
	 * complaintDept 部门/单位
	 * complaintWay 投诉方式
	 * complaintContent 投诉内容
	 * @return: String
	 * id 主键
	 * billNo  投诉工单号
	 * RecCreateName 创建人名字
	 * RecCreateTime 创建时间
	 */

	public EiInfo saveCon(EiInfo inInfo) {
		HashMap<Object, Object> map = new HashMap<>();
		String billNo = createBillNo();
		String workNo = inInfo.getAttr().get("workNo").toString();
		String complaintDate = inInfo.getAttr().get("complaintDate").toString();
		String complaintPhone = inInfo.getAttr().get("complaintPhone").toString();
		String complaintName = inInfo.getAttr().get("complaintName").toString();
		String complaintEmail = inInfo.getAttr().get("complaintEmail").toString();
		String complaintType = inInfo.getAttr().get("complaintType").toString();
		String complaintDept = inInfo.getAttr().get("complaintDept").toString();
		String complaintWay = inInfo.getAttr().get("complaintWay").toString();
		String complaintContent = inInfo.getAttr().get("complaintContent").toString();
		map.put("workNo",workNo);
		map.put("complaintDate",complaintDate);
		map.put("complaintPhone",complaintPhone);
		map.put("complaintName",complaintName);
		map.put("complaintEmail",complaintEmail);
		map.put("complaintType",complaintType);
		map.put("complaintDept",complaintDept);
		map.put("complaintWay",complaintWay);
		map.put("complaintContent",complaintContent);
		inInfo.setBlocks(map);

		CpBill htgl09 = new CpBill();
		htgl09.fromMap(map);//fromMap实体类
		htgl09.setBillNo((String)map.get("billNo_textField"));
		htgl09.setRecCreator((String)map.get("recCreator_textField"));
		htgl09.setRecCreateTime((String)map.get("recCreateTime_textField"));
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 如果参数为空
		if (StringUtils.isBlank(htgl09.getId())) {
			htgl09.setId(UUID.randomUUID().toString());
			htgl09.setBillNo(billNo);
			htgl09.setRecCreateName((String)staffByUserId.get("name"));
			htgl09.setRecCreateTime(DateUtils.curDateTimeStr19());
			htgl09.setStatusCode("0");
			// 保存项目
			dao.insert("CPDJ01.insert", htgl09);
		} else {
			htgl09.setRecRevisor(UserSession.getUser().getUsername());
			htgl09.setRecReviseTime(DateUtils.curDateTimeStr19());
			// 更新项目
			dao.update("CPDJ01.update", htgl09);
		}
            //页面返回
		inInfo.set("billNo",htgl09.getBillNo());
		return inInfo;


	}


	/**
     * @Title: queryTabGrid 
     * @Description: TabGrid查询方法
     * @param inInfo
     * querySql 查询sql
     * countSql 统计sql
     * resultBlock blockId 
     * blockMeta EiBlockMeta
     * @return: EiInfo
     */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
			EiBlockMeta blockMeta) {
	    // 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		// 查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		// 获取总数
		int count = dao.count(countSql, map);
		// 数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}

	/**
	 * 获取小代码的值
	 *
	 * @Title: getComplaintType
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 */

	public EiInfo getComplaintType(EiInfo inInfo){
		//方法后面括号是参数类型 参数。再加参数是就是：参数类型 参数，参数类型 参数
		//wilp.cp.complaintType:小代码设置里的代码分类编号
		List<Map<String, String>> list = CommonUtils.getDataCode("wilp.cp.complaintType");
		inInfo.set("list",list);//将list放进出参inInfo
		return inInfo;
	}


	/**
	 * PC端登记任务工单上传图片时回显
	 * <p>获取页面参数,将参数中的图片路径，转换成图片base64码，然后返回页面</p>
	 * @Title: showTempPic
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param info
	 * 		picList：图片信息集合
	 * 			path ： 图片保存路径
	 * @param:  @return
	 * @return: EiInfo
	 * 		 base64 ： 图片base64码
	 * @throws
	 */
	public EiInfo showTempPic(EiInfo info) {
		Object object = info.get("picList");
		List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		list.forEach(map ->{
			map.put("base64", CommonUtils.imageToBase64Str(CPUtils.getFilePath(map.get("docId"))));
		});
		info.set("list", list);
		return info;
	}

	public EiInfo cleanPic(EiInfo info) {
		String billNo = info.getString("billNo");
		dao.delete("CPDJ0101.cleanPic",billNo);
		return info;
	}

	//查找科室信息
	public EiInfo queryDeptName (EiInfo inInfo){

		List<com.baosight.wilp.cp.dj.domain.CPDJ01> list = dao.query("CPDJ01.queryDeptName", new HashMap<>());
		inInfo.addBlock("dept").addRows(list);
		inInfo.getBlock("dept").setBlockMeta(new com.baosight.wilp.cp.dj.domain.CPDJ01().eiMetadata);
		return inInfo;

	}

	/**
	 * 投诉工单更新
	 *
	 * <p>根据投诉的id指派处理的科室</p>
	 *
	 * @Title: updateWXGD
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 	id:投诉主表的id
	 * 	statusCode：任务状态
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo updateCLKS(EiInfo inInfo) {
		//获取参数
		String id = CPUtils.isEmpty(inInfo.get("id"));
		String statusCode = CPUtils.isEmpty(inInfo.get("statusCode"));
		//封装参数
		Map<String, String> map = new HashMap<>();
		map.put("id",id);
		map.put("statusCode",statusCode);
		//数据更新
		dao.update("CPDJ01.updateCLKS", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("msg", "200");
		return outInfo;
	}
	/**
	 * 查询人员工号的接口
	 *
	 * <p>查询基础框架里人员工号</p>
	 *
	 * @Title: queryWork
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 *
	 *
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo queryNumAndName(EiInfo inInfo) {

		//封装参数
		Map<String, String> map = new HashMap<>();

		//数据更新
		List<Map<String, String>> list = dao.query("CPDJ01.queryNumAndName", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("list", list);
		outInfo.set("msg", "200");
		return outInfo;
	}
	/**
	 * 查询根据工号人员姓名接口
	 *
	 * <p>查询基础框架里人员姓名</p>
	 *
	 * @Title: queryName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 *
	 *
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo queryDeptNamegzf(EiInfo inInfo) {
		//获取参数
		String deptName = (String) inInfo.getAttr().get("deptName");

		//封装参数
		Map<String, String> map = new HashMap<>();
		map.put("deptName",deptName);

		//数据更新
		List<Map<String, String>> list = dao.query("CPDJ01.queryDeptNamegzf", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("list", list);
		outInfo.set("msg", "200");
		return outInfo;
	}
	/**
	 * 查询科室
	 *
	 * <p>根据投诉的id指派处理的科室</p>
	 *
	 * @Title: queryDepts
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 	id:投诉主表的id
	 * 	statusCode：任务状态
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo queryDepts(EiInfo inInfo) {
		//获取参数
		String workNo = (String) inInfo.getAttr().get("workNo");

		//封装参数
		Map<String, String> map = new HashMap<>();
		map.put("workNo",workNo);

		//数据更新
		List<Map<String, String>> list = dao.query("CPDJ01.queryDepts", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("list", list);
		outInfo.set("msg", "200");
		return outInfo;
	}

}

