package com.baosight.wilp.hi.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.wilp.hi.common.domain.HiIcon;
import com.baosight.wilp.hi.common.domain.HiStandard;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为医院标识信息管理详情页面
 * <p>1.初始化查询 initLoad
 * <p>2.信息回显 query
 *
 * @Title: ServiceHIXX0104.java
 * @ClassName: ServiceHIXX0104
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
public class ServiceHIXX0104 extends ServiceBase {
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
	 * 医院标识信息管理详情弹出界面
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
			//2.1、判断是否为编辑或查看
			if ("edit".equals(type) || "see".equals(type)) {
				//是。获取参数
				Map<String, String> pMap = new HashMap<>(4);
				pMap.put("id", inInfo.getString("id"));
				EiBlock row = new EiBlock("inqu_status");
				//通过id，将基础数据(标识名称、标识中文内容、标识英文内容、安装地点说明、数量、备注)回显
				List<HiIcon> basicDate = dao.query("HIXX01.query", pMap);
				//通过id。回显特殊信息,包括标识分类、安装地点、管理员、使用科室
				List<Map<String,String>> specialDate = dao.query("HIXX01.querySpecialDate",pMap);
				//通过id，查询申请单号
				List<Map<String,String>> applyNo = dao.query("HIXX0101.queryApplyNo",pMap);
				String apply = String.valueOf(applyNo.get(0).get("apply_no"));
				String classifyId = String.valueOf(applyNo.get(0).get("classify_id"));
				pMap.put("applyNo",apply);
				pMap.put("classifyId",classifyId);
				//通过申请单号，查询申请ID
				List<Map<String,String>> applyId = dao.query("HIXX0101.queryApplyId",pMap);
				pMap.put("applyId", String.valueOf(applyId.get(0).get("id")));
				//通过申请id，查询附件信息
				List<HiFile> applyFile = dao.query("HIXX0101.applyFile", pMap);
				//回显标准文件信息，通过id
				List<HiStandard> standardsDate =  dao.query("HICX01.queryStandardNum", pMap);
				row.setRows(basicDate);
				inInfo.setAttr(specialDate.get(0));
				EiBlock block = new EiBlock("resultD");
				EiBlock blockFile = new EiBlock("resultE");
				block.setRows(standardsDate);
				blockFile.setRows(applyFile);
				inInfo.setBlock(block);
				inInfo.setBlock(blockFile);
				inInfo.setBlock(row);
			}
		}
		//3.返回
		return  inInfo;

	}

	public EiInfo query(EiInfo info){
		return info;
	}


}
