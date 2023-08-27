/**
 *@Name ServiceMTEV01.java
 *@Description TODO
 *@Author jiangzhongmin
 *@Date 2021年4月23日 下午3:18:10
 *@Version 1.0
 **/

package com.baosight.wilp.cp.pj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.util.CPUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投诉评价管理页面Service
 * 
 * <p>1.页面加载</p>
 * <p>2.页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPPJ01.java
 * @ClassName:  ServiceCPPJ01
 * @Package com.baosight.wilp.cp.pj.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年4月10日 下午4:13:54
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPPJ01 extends ServiceBase{

	/**
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * 投诉评价管理界面
	 * 通过投诉单号、发生日期起、发生日期止条件查询
	 * 回显投诉单号、单子状态、发生日期、投诉人、投诉人电话、部门/单位、投诉类型、投诉方式、投诉内容、处理人、处理日期、回访情况、回访人、回访日期。
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
	 * dealWorkName 处理人
	 * dealDate 处理日期
	 * returnDesc 回访情况
	 * returnWorkName 回访人
	 * returnDate 回访日期
	 */
    @Override
	public EiInfo initLoad(EiInfo inInfo) {
        return this.query(inInfo);
    }
	/**
	 * @Title: query
	 * @Description: 投诉数据查询
	 * 投诉评价管理界面
	 * 通过投诉单号、发生日期起、发生日期止条件查询
	 * 回显投诉单号、单子状态、发生日期、投诉人、投诉人电话、部门/单位、投诉类型、投诉方式、投诉内容、处理人、处理日期、回访情况、回访人、回访日期。
	 * @param info
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
	 * dealWorkName 处理人
	 * dealDate 处理日期
	 * returnDesc 回访情况
	 * returnWorkName 回访人
	 * returnDate 回访日期
	 */
    @Override
	public EiInfo query(EiInfo info){
		//定义分页和查询数
		Integer offset = 0;
		Integer limit = 10;
		//获取参数
		EiBlock block = info.getBlock("inqu_status");
		EiBlock eiBlock = info.getBlock("result");
		//获取分页和查询数
		if (eiBlock!=null){
			offset = (Integer) eiBlock.getAttr().get("offset");
			limit = (Integer) eiBlock.getAttr().get("limit");
		}
		//实例map
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> userInfo = CPUtils.getUserInfo(null);
		if (block!=null){
			//获取参数
			map = block.getRow(0);
		}
		map.put("recCreator",userInfo.get("workNo"));
		// 查询数据 (投诉单号、单子状态、发生日期、投诉人、投诉人电话、部门/单位、投诉类型、投诉方式、投诉内容)
		List<Map<String,String>> result = dao.query("CPPJ01.query", map, offset ,limit);
		// 计算行数
		int count = super.count("CPPJ01.count", map);
		info.setRows("result",result);
		// 处理分页
		EiBlock page = (EiBlock) info.getBlocks().get("result");
		page.setAttr(new HashMap<>());
		if(page.getAttr().isEmpty()){
			//分页为空封装分页信息
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			page.setAttr(rAttr);
		} else {
			page.getAttr().put("count", count);
		}
		return info;
	}


}