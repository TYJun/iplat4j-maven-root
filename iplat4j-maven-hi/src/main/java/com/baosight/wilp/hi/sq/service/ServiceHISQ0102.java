package com.baosight.wilp.hi.sq.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hi.common.domain.HiApply;
import com.baosight.wilp.hi.common.domain.HiFile;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为医院标识申请编辑管理
 * <p>1.初始化查询 initLoad
 *
 * @Title: ServiceHISQ0102.java
 * @ClassName: ServiceHISQ0102
 * @Package：com.baosight.wilp.hi.sq.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午2:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHISQ0102 extends ServiceBase {
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
	 * 医院标识申请管理编辑弹出界面
	 * 通过操作类型
	 * 进行操作
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 1.获取参数(id,type)
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		String status = inInfo.getAttr().get("status").toString();
		//2、判断id是否为空
		if (StringUtils.isNotBlank(id)) {
			//2.1、不为空时，判断是否为编辑或查看
			if ("edit".equals(type) || "see".equals(type)) {
				//是。获取id
				Map<String, String> pMap = new HashMap<>(4);
				pMap.put("id", inInfo.getString("id"));
				EiBlock row = new EiBlock("inqu_status");
				//通过id基础数据回显(标识名称、标识中文内容、标识英文内容、数量、安装地点说明、申请科室、管理员、申请理由、备注)
				List<HiApply> underlyingDate = dao.query("HISQ01.query", pMap);
				//通过id回显文件信息
				Map<String, String> pMap1 = new HashMap<>(4);
				pMap1.put("id", inInfo.getString("id"));
				List<HiFile> fileDate = dao.query("HIBZ01.queryId", pMap1);
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
//		//改变文件的状态
//		Map<String, String> pMap11 = new HashMap<>(4);
//		pMap11.put("id", inInfo.getString("id"));
//		pMap11.put("fileStatus", "03");
//		dao.update("HIBZ01.updatefileStatus", pMap11);
		//3、数据返回
		return  inInfo;
	}


}
