package com.baosight.wilp.cp.dj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.domain.CpFile;
import com.baosight.wilp.cp.util.CPUtils;
import com.baosight.wilp.utils.UUID;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该页面为投诉登记管理
 * 主要包含对投诉登记的新增、删除、编辑、查看、提交功能
 * 投诉登记模块：初始化查询、投诉工单查询、投诉工单提交、投诉工单删除
 * <p>1.初始化查询 initLoad
 * <p>2.投诉数据查询 query
 * <p>3.投诉提交 examine
 * <p>4.投诉删除 delete
 * <p>5.投诉作废 invalid
 * @Title: ServiceCPDJ01.java
 * @ClassName: ServiceCPDJ01
 * @Package：com.baosight.wilp.cp.dj.service
 * @author: liangyongfei
 * @date: 2022年4月10日 下午1:29:57
 * @version: V1.0
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCPDJ01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 投诉登记管理界面
     * 通过投诉单号、发生日期起、发生日期止条件查询
     * 回显投诉单号、单子状态、发生日期、投诉人、投诉人电话、部门/单位、投诉类型、投诉方式、投诉内容。
     * @param inInfo
	 * billNo 投诉单号
	 * complaintDateS 发生日期起
	 * complaintDateE 发生日期止
     * @return EiInfo
     * id 主键
	 * billNo 合同号
	 * statusCode 单子状态
	 * complaintDate 发生日期
	 * complaintName 投诉人
	 * complaintPhone 投诉人电话
	 * complaintDept 部门/单位
	 * complaintType 投诉类型
	 * complaintWay  投诉方式
	 * complaintContent 投诉内容
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}
	
	 /**
     * @Title: query
     * @Description: 投诉数据查询
	  * 投诉登记管理界面
	  * 通过投诉单号、发生日期起、发生日期止条件查询
	  * 回显投诉单号、单子状态、发生日期、投诉人、投诉人电话、部门/单位、投诉类型、投诉方式、投诉内容。
	  * @param inInfo
	  * billNo 投诉单号
	  * complaintDateS 发生日期起
	  * complaintDateE 发生日期止
	  * @return EiInfo
	  * id 主键
	  * billNo 合同号
	  * statusCode 单子状态
	  * complaintDate 发生日期
	  * complaintName 投诉人
	  * complaintPhone 投诉人电话
	  * complaintDept 部门/单位
	  * complaintType 投诉类型
	  * complaintWay  投诉方式
	  * complaintContent 投诉内容
	  */
	 //回显方法
	@Override
	public EiInfo query(EiInfo inInfo) {
		// 判断当前用户是否是工会成员
		List<String> labor = CPUtils.isRole("ZGXSGH");
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String workNo = (String) staffByUserId.get("workNo");
		if (CollectionUtils.isNotEmpty(labor)){
//			inInfo.set("inqu_status-0-labor","labor");
			inInfo.set("labor","labor");
			if (inInfo.getBlocks().size() == 0){
				inInfo.set("inqu_status-0-statusCode","00");
			}
		} else {
//			inInfo.set("inqu_status-0-labor","user");
			inInfo.set("inqu_status-0-recCreator",workNo);
			inInfo.set("inqu_status-0-statusCode","");
		}
		return super.query(inInfo,"CPDJ01.query",new CpBill());
	}

	/**
     * @Title: examine
     * @Description: 提交
     * 投诉登记管理界面
     * 通过投诉主键 id
     * 将符合条件的信息提交
	 * 提交到工单状态改为待处理
     * @param inInfo
     * id 合同主键
     * @return: EiInfo
	 * status_code 投诉状态
     */
	public EiInfo examine(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//  获取参数
		String examineName = (String)staffByUserId.get("name");
		String examineTime = DateUtils.curDateTimeStr19();
		// 赋值map
		map.put("id",id);
		map.put("checkMaker", examineName);
		map.put("checkTime", examineTime);
		// 调用更新方法
		dao.update("CPDJ01.examine",map);
		// 返回参数
		return inInfo;

	}

	/**
	 * 撤回
	 */
	public EiInfo revocation(EiInfo info){
		String billNo = info.getString("billNo");
		dao.update("CPDJ01.revocation", new HashMap<String,String>(){{
			put("billNo",billNo);
		}});
		return info;
	}

	/**
	 * @Title: invalid
	 * @Description: 作废
	 * 投诉登记管理界面
	 * 通过投诉主键，将符合条件的信息作废
	 * @param inInfo
	 * id 合同主键
	 * @return: EiInfo
	 * status_code  投诉状态
	 *
	 */
	public EiInfo invalid(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//  获取参数
		String examineName = (String)staffByUserId.get("name");
		String examineTime = DateUtils.curDateTimeStr19();
		// 赋值map
		map.put("id",id);
		map.put("checkMaker", examineName);
		map.put("checkTime", examineTime);
		// 调用更新方法
		dao.update("CPDJ01.invalid",map);
		// 返回参数
		return inInfo;

	}
	/**
	 * @Title: obsolate
	 * @Description: 作废
	 * VUE前端投诉工单作废接口
	 * 通过投诉工单号，将符合条件的信息作废
	 * @param inInfo
	 * billNo 投诉工单
	 * @return: EiInfo
	 * status_code  投诉状态
	 */

	public EiInfo obsolete(EiInfo inInfo) {
		HashMap<Object, Object> map = new HashMap<>();
		// 获取vue前端传过来的参数
		String billNo = inInfo.getAttr().get("billNo").toString();
		//赋值map
		map.put("billNo",billNo);
		//传到前端
		inInfo.setBlocks(map);
		CpBill htgl00 = new CpBill();
		htgl00.fromMap(map);//fromMap实体类
		htgl00.setBillNo((String)map.get("billNo_textField"));
		htgl00.setBillNo(billNo);
		htgl00.setStatusCode("5");
		// 更新项目
        dao.update("CPDJ01.obsolete",map);

		return inInfo;


	}
	/**
     * @Title: delete
     * @Description: 删除
     * 投诉登记管理界面
     * 通过投诉主键，将符合条件的信息删除
     * @param inInfo
     * id 投诉主键
     * @return: EiInfo
     */
	@Override
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		super.delete(inInfo,"CPDJ01.deletePic");
		return super.delete(inInfo, "CPDJ01.delete");
	}

	/**
	 * 投诉工单更新
	 * <p>根据指定投诉工单号更新任务状态</p>
	 * @Title: updateWXGD
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 	billNo:投诉单号
	 * 	statusCode：任务状态
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo updateWXGD(EiInfo inInfo) {
		//获取参数
		String billNo = CPUtils.isEmpty(inInfo.get("billNo"));
		String statusCode = CPUtils.isEmpty(inInfo.get("statusCode"));
		String teskEval = CPUtils.isEmpty(inInfo.get("teskEval")).trim();
		//实例map
		Map<String, String> map = new HashMap<>();
		//赋值map
		map.put("billNo",billNo);
		map.put("statusCode",statusCode);
		map.put("teskEval",teskEval);
		//数据更新
		dao.update("CPDJ01.updateWXGD", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("billNo", billNo);
		return outInfo;
	}
	/**
	 * 保存图片
	 *
	 * <p>1.获取参数</br>
	 * 	 2.获取图片的保存路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）</br>
	 * 	 3.遍历图片List</br>
	 *   5.pc端上传，直接保存图片信息到cp_file表</br>
	 * </p>
	 *
	 * @Title: savePicture
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		billNo: 投诉单号
	 * 		picList ： 图片集合List
	 * 		fileName:图片名称
	 * 		path： 图片路径（PC端使用）
	 * @param:  @return
	 * @param:  @throws IOException
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo savePicture(EiInfo inInfo) throws IOException {
		//获取参数
		Object object = inInfo.get("picList");

		List<Map<String,String>> picList = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		//获取操作人
	/*	String recCreator = CPUtils.isEmpty(inInfo.get("recCreator"), UserSession.getUser().getUsername());		*//* 创建人*//*
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		*//* 创建时间*/
		//获取图片存储基础路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）
		String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String destPath = docRootDir+ "/cp/img/";
		//遍历图片List
		picList.forEach(map -> {
			//{"fileName":"a.jpg","docId":"","base64":"","type":"RE||FS"}
			map.put("id", UUID.randomUUID().toString());
		/*	map.put("recCreator", recCreator);
			map.put("recCreateTime", recCreateTime);*/

			//判断图片是pc端上传还是app端上传
			if(StringUtils.isBlank(map.get("docId"))){//App端
				//app端上传，先将图片base64码转成图片文件保存，再保存图片信息到cp_file表
				String[] files = map.get("fileName").split(".");
				String path = destPath + Calendar.getInstance().getTimeInMillis()+ (files.length > 1 ? files[1] : ".png");
				CommonUtils.base64StrToImage(map.get("base64"), path);//存储图片
				map.put("path", path);
			} else {
				map.put("path", CPUtils.getFilePath(map.get("docId")));
			}
			map.put("billNo", inInfo.getString("billNo"));
			dao.insert("CPDJ01.insertPic", map);
		});
		inInfo.setMsg("图片上传操作成功");
		return inInfo;
	}

	/**
	 * 回显上传的图片
	 *
	 * <p>根据图片的路径，获取到图片文件，再将图片文件转成base64码</p>
	 *
	 * @Title: showPic
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param info
	 * 		billNo ： 工单号
	 * @param:  @return
	 * @return: EiInfo
	 * 		base64 ： 图片base64码
	 * @throws
	 */

	public EiInfo showPic(EiInfo info) {
		//实例pMap
		Map<String,String> pMap = new HashMap<>(4);
		//赋值pMap
		pMap.put("billNo", info.getString("billNo"));
		//显示图片
		List<Map<String,String>> list=dao.query("CPDJ01.showPic",pMap);
		//转换为base64
		list.forEach(map ->{
			map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
			map.put("path","");
		});
		//页面返回
		info.set("list", list);
		return info;
	}

	/**
	 * 通过用户角色查询科室
	 * @author zhaowei
	 * @date 2023/6/12 16:31
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 */
	public EiInfo queryDeptByRole(EiInfo info){
		// 通过权限查找员工工号
//		List<Map<String,String>> list = dao.query("CPDJ01.queryDeptByRole",new HashMap<String,String>(){{
//			put("role","ZGXSGLY");
//		}});
		List<Map<String,String>> list = dao.query("CPPZ01.queryCpDept", new HashMap<>());
		info.addBlock("dept").addRows(list);
		return info;
	}
}
