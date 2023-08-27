package com.baosight.wilp.hi.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hi.common.domain.HiApply;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.wilp.hi.common.domain.HiIcon;
import com.baosight.wilp.hi.common.util.HIUtils;
import com.baosight.wilp.hi.common.util.saveFile;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 该页面为医院标识信息管理页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveIdentifyingInformation
 * <p>3.保存制作状态 saveMakeStatus
 * <p>4.TabGrid查询方法 queryTabGrid
 *
 * @Title: ServiceHIXX0101.java
 * @ClassName: ServiceHIXX0101
 * @Package：com.baosight.wilp.hi.xx.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHIXX0101 extends ServiceBase {
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
	 * <p>5.特殊数据回显</>
	 * 医院标识管理弹出界面
	 * 通过操作类型
	 * 进行操作
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 1、获取参数(id,type)
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 2、判断id是否为空
		if (StringUtils.isNotBlank(id)) {
			//2.1、判断是否为编辑或查看
			if ("edit".equals(type) || "see".equals(type)) {
				//是，获取id
				Map<String, String> pMap = new HashMap<>(4);
				pMap.put("id", inInfo.getString("id"));
				//通过id，查询标识申请信息
				List<HiApply> list = dao.query("HIXX01.query", pMap);
				//通过id，查询标识申请附件
				List<HiFile> list1 = dao.query("HIXX01.queryId", pMap);
				HiApply hiApply = list.get(0);
				inInfo.addBlock(String.valueOf(hiApply));
				EiBlock block = new EiBlock("resultD");
				block.setRows(list1);
				inInfo.setBlock(block);
			}
		}
		//3、返回
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
	 *               managerName 管理员
	 *               deptName 使用科室
	 *               remark 备注
	 * @return EiInfo
	 * @Title: saveIdentifyingInformation
	 * @Description: 保存弹窗信息
	 * <p>1.获取表单参数
	 * <p>2.获取tab/grid参数
	 * <p>3.保存项目
	 * <p>4.获取当前登录用户信息
	 * <p>5.如果参数为空
	 * <p>6.保存项目
	 * <p>7.更新项目
	 * <p>8.保存附件
	 * <p>9.保存制作状态
	 */
	public EiInfo saveIdentifyingInformation(EiInfo inInfo){
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 2.获取tab/grid参数
		Object fileObj = inInfo.getAttr().get("file");
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.getAttr().get("deleteFile");
        //参数校验
		/*
		* 1、获取参数
		* iconZhContent：标识中文内容
		* iconEnContent：标识英文内容
		* classifyId：标识分类
		* spotName：安装地点
		* iconAmount：数量
		* spotDesc：安装地点说明
		*
		* */
		String   iconZhContent = (String) param.get("iconZhContent");
		String   iconEnContent = (String) param.get("iconEnContent");
		String   classifyId = (String) param.get("classifyId");
		String   spotName = (String) inInfo.getAttr().get("spotName");
		String   iconAmount = (String) param.get("iconAmount");
		String   spotDesc = (String) param.get("spotDesc");
		String   errorMsy = "";
		/*
		* 1、如果标识中文内容为空时，提示标识中文内容不能为空
		* 2、如果标识英文内容为空时，提示标识英文内容不能为空
		* 3、如果标识分类为空时，提示标识分类不能为空
		* 4、如果安装地点为空时，提示安装地点不能为空
		* 5、如果数量为空时，提示数量不能为空
		* 6、如果安装地点你说明为空时，提示安装地点不能为空*/
//        if(StringUtils.isBlank(iconZhContent)){
//        	errorMsy = errorMsy + "标识中文内容不能为空\r\n";
//		}
//        if(StringUtils.isBlank(iconEnContent)){
//        	errorMsy  = errorMsy + "标识英文内容不能为空\r\n";
//		}
        if(StringUtils.isBlank(classifyId)){
        	errorMsy = errorMsy + "标识分类不能为空\r\n";
		}
        if(StringUtils.isBlank(spotName)){
        	errorMsy = errorMsy + "安装地点不能为空\r\n";
		}
        if(StringUtils.isBlank(iconAmount)){
        	errorMsy = errorMsy + "数量不能为空\r\n";
		}
        if(StringUtils.isBlank(spotDesc)){
        	errorMsy = errorMsy + "安装地点说明不能为空\r\n";
		}
        if(errorMsy.length()>0){
        	inInfo.setMsg(errorMsy);
        	return  inInfo;
		}
		// 3.保存项目
		HiIcon standard = new HiIcon();
		standard.fromMap(param);
		String applyNo = (String) inInfo.getAttr().get("applyNo");
		standard.setApplyNo(applyNo);
		String iconName = (String) inInfo.getAttr().get("iconName");
		standard.setIconName(iconName);
		standard.setManagerName((String) param.get("managerName_textField"));
		standard.setDeptName((String) param.get("deptName_textField"));
		standard.setSpotName((String) inInfo.getAttr().get("spotName"));
		standard.setClassifyName((String) param.get("classifyId_textField"));
		//4.获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 5.如果参数为空
		if (StringUtils.isBlank(standard.getId())) {
			standard.setId(UUID.randomUUID().toString());
			standard.setIconNum(HIUtils.generationSerialNo("hospital_hi_xx", "HIXX", "1"));
			standard.setRecCreator((String) staffByUserId.get("name"));
			standard.setRecCreateTime(DateUtils.curDateTimeStr19());
			//00为新建状态
			standard.setStatus("00");
			// 6.保存项目
			dao.insert("HIXX01.insert", standard);
		} else {
			String id = standard.getId();
			//通过ID查询申请单号
			List<Map<String,String>> ApplicationNumber = dao.query("HIXX0101.queryApplicationNumber",id);
			standard.setApplyNo(ApplicationNumber.get(0).get("apply_no"));
			standard.setRecRevisor((String) staffByUserId.get("name"));
			standard.setRecReviseTime(DateUtils.curDateTimeStr19());
			//00为新建状态
			standard.setStatus("00");
			// 7.更新项目
			dao.update("HIXX01.update", standard);
		}
		//8.保存附件
		saveFile.saveFile(fileObj, standard.getId(), deleteFile, null);
		//9.保存制作状态
		saveMakeStatus(applyNo);

		return inInfo;
	}

	/**
	 * @param
	 * @Title: saveMakeStatus
	 * @Description: 保存制作状态
	 * <p>1.获取参数(申请单号)
	 * <p>2.更新数据
	 * 通过获取申请单号
	 * 将符合条件的状态为制作完成
	 * @return: String
	 */
	private void saveMakeStatus(String applyNo){
		//1、获取参数(applyNo:申请单号)
		 HashMap<String,String> makeStatus = new HashMap<>();
		 makeStatus.put("applyNo",applyNo);
		 //2、通过申请单号，更新制作状态
		 dao.update("HIXX0101.makeStatus",makeStatus);
	}


	/**
	 * @param inInfo querySql 查询sql
	 *               countSql 统计sql
	 *               resultBlock blockId
	 *               blockMeta EiBlockMeta
	 * @Title: queryTabGrid
	 * @Description: TabGrid查询方法
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
	 * @Title: querySpot
	 * @Description: 接口改造(地点)2021-08-04
	 * @param info
	 * spotName : 科室地点
	 * @return info
	 * spotName : 科室地点
	 */
	public EiInfo querySpot(EiInfo info) {
		// 构建参数map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
		// 调用远程接口返回集合
		List<Map<String, Object>> spotList = BaseDockingUtils.getSpotByDeptId("");
		spotList.forEach(pMap -> pMap.put("spotId", pMap.get("id")));
		//将数据进行逻辑分页
		EiInfo spot = CommonUtils.BuildOutEiInfoWithLogicalPage(spotList, map, "spot");
		spot.setBlockInfoValue("dept", "showCount", "true");
		return spot;
	}

}
