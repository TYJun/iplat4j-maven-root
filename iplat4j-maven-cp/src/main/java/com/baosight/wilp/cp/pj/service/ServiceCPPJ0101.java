/**
 *@Name ServiceMTEV01.java
 *@Description TODO
 *@Author jiangzhongmin
 *@Date 2021年4月23日 下午3:18:10
 *@Version 1.0
 **/

package com.baosight.wilp.cp.pj.service;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.util.CPUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投诉评价管理评价子页面Service
 * 
 * <p>1.页面加载</p>
 * <p>2.评价</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPPJ0101.java
 * @ClassName:  ServiceCPPJ0101
 * @Package com.baosight.wilp.cp.pj.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年4月10日 下午4:20:35
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPPJ0101 extends ServiceBase{

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param  inInfo
	 * id  主键
	 * @return
	 * complaintDate 发生日期
	 * complaintName投诉人
	 * complaintPhone电话
	 * complaintDept部门/单位
	 * complaintEmail邮箱
	 * complaintType投诉类别
	 * complaintWay投诉方式
	 * complaintContent投诉内容
	 * teskEval投诉服务评价
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new CpBill().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 调用查询方法
			List<HashMap<String, String>> list = dao.query("CPPJ01.queryId", map);

			// 数据返回
			block.addRows(list);
			// 特殊数据回显
			EiColumn column1 = new EiColumn("complaintType_textField");
			EiColumn column2 = new EiColumn("complaintWay_textField");
			//String surpNum=list.get(0).getSurpNum();
			block.addMeta(column1);
			block.addMeta(column2);
			//String contdeptNUm = list.get(0).get("contdeptNUm");
			block.setCell(0, "complaintType_textField", list.get(0).get("complaintType"));
			block.setCell(0, "complaintWay_textField", list.get(0).get("complaintWay"));
		}
		inInfo.addBlock(block);
		// 返回参数
		inInfo.set("inqu_status-0-type",inInfo.getString("type"));
		return inInfo;
    }
	
	/**
	 * 评价接口
	 * <p>
	 * 	1.调用本地服务CPDJ01.updateWXGD更新投诉工单信息</br>
	 * </p>
	 * 
	 * <p>Title: update</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#update(EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		 
		String loginName=StringUtils.isBlank((String)inInfo.get("workNo"))?
	            UserSession.getUser().getUsername():(String)inInfo.get("workNo");
	    //获取当前登录人信息，作为操作人信息
		inInfo.set(EiConstant.serviceName, "CPDJ01");
		inInfo.set(EiConstant.methodName, "updateWXGD");
		EiInfo outInfo = XLocalManager.call(inInfo);

		inInfo.addMsg("操作成功");
		inInfo.setMsgKey("200");
        return inInfo;
      	
    }
}

