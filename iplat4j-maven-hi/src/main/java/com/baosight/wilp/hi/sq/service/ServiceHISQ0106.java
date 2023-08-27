package com.baosight.wilp.hi.sq.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.hi.common.domain.HiApply;
import com.baosight.wilp.hi.common.domain.HiApproval;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 该页面为医院标识审核页面
 * <p>1.初始化查询 initLoad
 * <p>2.审核通过 evaluationPass
 * <p>3.审核驳回 evaluationOverrule
 *
 * @Title: ServiceHISQ0103.java
 * @ClassName: ServiceHISQ0103
 * @Package：com.baosight.wilp.hi.sq.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午3:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHISQ0106 extends ServiceBase {
	/**
	 * @param inInfo id 主键
	 *               type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * <p>1.获取参数</>
	 * <p>2.如果参数存在</>
	 * <p>3.调用查询方法</>
	 * <p>4.数据返回</>
	 * 标识申请审核弹出界面
	 * 通过操作类型
	 * 进行操作
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 1.获取参数(id,type)
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		//2、判断id是否为空
		if (StringUtils.isNotBlank(id)) {
			//2.1、不为空时，判断是否为编辑或查看
			if ("edit".equals(type) || "see".equals(type)) {
				//是。获取id
				Map<String, String> pMap = new HashMap<>(4);
				pMap.put("id", inInfo.getString("id"));
				//通过id回显文件信息
				List<HiFile> fileDate = dao.query("HIBZ01.queryId", pMap);
				pMap.put("fileStatus", "07");
				EiBlock row = new EiBlock("inqu_status");
				//通过id基础数据回显(标识名称、标识中文内容、标识英文内容、数量、安装地点说明、申请科室、管理员、申请理由、备注)
				List<HiApply> underlyingDate = dao.query("HISQ01.queryAudit", pMap);
				//通过id回显特殊信息，包括安装地点、标识分类、安装日期、申请科室、管理员
				List<Map<String,String>> specialDate = dao.query("HISQ01.querySpecialDate",pMap);
				row.setRows(underlyingDate);
				inInfo.setAttr(specialDate.get(0));
				EiBlock block = new EiBlock("resultD");
				block.setRows(fileDate);
				inInfo.setBlock(block);
				inInfo.setBlock(row);
			}
		}

		//3、数据返回
		return  inInfo;

	}
	/**
	 * @param inInfo id 主键
	 *               iconName 标识名称
	 *               iconZhContent 标识中文内容
	 *               iconEnContent 标识英文内容
	 *               classifyName 标识分类
	 *               spotName 安装地点
	 *               iconAmount 数量
	 *               spotDesc 安装地点说明
	 *               installDate 安装日期
	 *               applyReason 申请理由
	 *               managerName 管理员
	 *               applyDeptName 申请科室
	 *               remark 备注
	 *
	 * @return EiInfo
	 * @Title: evaluationPass
	 * @Description: 审核通过
	 * <p>1.获取表单参数
	 * <p>2.赋值Map
	 * <p>3.保存审批信息
	 * <p>4.保存审批状态、制作状态
	 * <p>5.返回
	 * 审核通过后，将审批状态改为审核通过，并将制作状态置于待制作
	 */


	public EiInfo evaluationPass (EiInfo inInfo) {
		//1、 获取参数(id,emo,status)
		String id = inInfo.getAttr().get("id").toString();
		String emo = inInfo.getAttr().get("emo").toString();
		String status  = inInfo.getAttr().get("status").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要审核的记录");
		}else {
			//01：新建，03：审核通过
           if("01".equals(status)){
           	inInfo.setMsg("请先将医院标识申请提交");
		   }else if("09".equals(status)){
           	inInfo.setMsg("审核通过的工单不可再次审核");
		   }else {
			   // 实例化map
			   HashMap<String, String> map = new HashMap<String, String>();
			   // 赋值map
			   map.put("id", id);
			   map.put("emo", emo);
			   EiBlock block = inInfo.getBlock("inqu_status");
			   Map<String, Object> param = block.getRow(0);
			   HiApproval approvalRecords = new HiApproval();
			   approvalRecords.setId(UUID.randomUUID().toString());
			   approvalRecords.setApplyNo((String) param.get("applyNo"));
			   approvalRecords.setStatus((String) param.get("status"));
			   Map<String, Object> staffByUser = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			   approvalRecords.setApprover((String) staffByUser.get("workNo"));
			   approvalRecords.setApproverName((String) staffByUser.get("name"));
			   approvalRecords.setApprovalTime(DateUtils.curDateTimeStr19());
			   //2、保存审批信息
			   dao.insert("HISQ0101.insert", approvalRecords);
			   //3、保存审批状态、制作状态
			   dao.update("HISQ01.evaluationPass3", map);
		   }
		}
		//改变文件的状态
		Map<String, String> pMap1 = new HashMap<>(4);
		pMap1.put("id", inInfo.getString("id"));
		pMap1.put("fileStatus", "11");
		dao.update("HIBZ01.updatefileStatus", pMap1);
			//4、返回参数
			return inInfo;

	}
	/**
	 * @param inInfo id 主键
	 *               iconName 标识名称
	 *               iconZhContent 标识中文内容
	 *               iconEnContent 标识英文内容
	 *               classifyName 标识分类
	 *               spotName 安装地点
	 *               iconAmount 数量
	 *               spotDesc 安装地点说明
	 *               installDate 安装日期
	 *               applyReason 申请理由
	 *               managerName 管理员
	 *               applyDeptName 申请科室
	 *               remark 备注
	 *
	 * @return EiInfo
	 * @Title: evaluationOverrule
	 * @Description: 审核驳回
	 * <p>1.获取表单参数
	 * <p>2.赋值Map
	 * <p>3.保存审批信息
	 * <p>4.保存审批状态
	 * <p>5.返回
	 * 审核驳回后，将审批状态改为审核驳回
	 */

	public EiInfo evaluationOverrule (EiInfo inInfo) {
		// 1、获取参数(id、emo)
		String id = inInfo.getAttr().get("id").toString();
		String emo = inInfo.getAttr().get("emo").toString();
		String status = inInfo.getAttr().get("status").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要审核的记录");
		}else {
			//01：新建，03：审核通过
			if("01".equals(status)){
				inInfo.setMsg("请先将医院标识申请提交");
			}else if("09".equals(status)){
				inInfo.setMsg("审核通过的工单不可再次审核");
			}else {
				// 实例化map
				HashMap<String, String> map = new HashMap<String, String>();
				// 赋值map
				map.put("id", id);
				map.put("emo", emo);
				EiBlock block = inInfo.getBlock("inqu_status");
				Map<String, Object> param = block.getRow(0);
				HiApproval approvalRecords = new HiApproval();
				approvalRecords.setId(UUID.randomUUID().toString());
				approvalRecords.setApplyNo((String) param.get("applyNo"));
				approvalRecords.setStatus((String) param.get("status"));
				approvalRecords.setRejectReason((String) param.get("emo"));
				Map<String, Object> staffByUser = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				approvalRecords.setApprover((String) staffByUser.get("workNo"));
				approvalRecords.setApproverName((String) staffByUser.get("name"));
				approvalRecords.setApprovalTime(DateUtils.curDateTimeStr19());
				//2、保存审批信息
				dao.insert("HISQ0101.insert", approvalRecords);
				//3、保存审批状态
				dao.update("HISQ01.evaluationOverrule3", map);
			}
		}
		//4、返回参数
		return inInfo;

	}


}
