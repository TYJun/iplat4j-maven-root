package com.baosight.wilp.di.yc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.di.yc.domain.DIYC01;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 巡检异常处理：初始化查询、异常查询、保存异常方法
 * <p>1.初始化查询 initLoad
 * <p>2.异常查询 query
 * <p>3.保存异常方法 update
 * @Title: ServiceDIYC0101.java
 * @ClassName: ServiceDIYC0101
 * @Package：com.baosight.wilp.di.yc.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午2:34:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIYC0101 extends ServiceBase {
    
    /**
     * 初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * type  操作类型
     * taskCode   任务编号
     * @return
     * type  操作类型
     * taskCode  任务编号
     * exceptionResult  异常处理
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // TODO Auto-generated method stub
         return super.query(inInfo, "DIYC01.queryException", new DIYC01());
    }
	
	
    /**
     * 异常查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * type  操作类型
     * taskCode   任务编号
     * @return
     * type  操作类型
     * taskCode  任务编号
     * exceptionResult  异常处理
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo query(EiInfo inInfo) {
	    EiInfo outInfo = super.query(inInfo, "DIYC01.queryException", new DIYC01());
		return outInfo;
	}
	
	
	/**
	 * 保存异常方法
	 * &lt;p&gt;Title: update&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>判断是否执行成功
	 * @param inInfo
	 * rejectreason 处理异常内容
	 * itemID  任务id
	 * @return
	 * 修改成功，失败则回滚操作
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
	    String rejectreason = (String)inInfo.getString("rejectreason");
	    String itemID = (String)inInfo.getString("itemID");
	    String solutionType = inInfo.getString("solutionType");
	    String dealMan = inInfo.getString("dealMan");
		String finishTime = inInfo.getString("finishTime");
	    System.out.println(rejectreason);
	    Map map = new HashMap();
	    map.put("solutionType",solutionType);
	    map.put("rejectreason", rejectreason);
	    map.put("itemID", itemID);
	    map.put("finishMan", dealMan);
	    map.put("finishTime",finishTime);
	    //判断是否执行成功
	    int update = dao.update("DIYC0101.updateTaskitem", map);
	    if(0 == update) {
	        inInfo.setMsg("系统错误，请联系管理员");
	        inInfo.setStatus(-1);
	        return inInfo;
	    }
	    inInfo.setMsg("修改成功");
	    return inInfo;
	}




	/**
	 * @Title: queryPerson
	 * @Description: 接口改造(人员)2021-08-04
	 * @param info
	 * userName : 用户名
	 * @return info
	 * workNo : 工号
	 * userName : 用户名
	 */
	public EiInfo queryPerson(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "person");
		//调用微服务接口
		map.put("datagroupCode", BaseDockingUtils.getUserGroupByWorkNo(null));
		map.put("userName",map.get("name"));
		EiInfo person=BaseDockingUtils.getStaffAllPage(map, "person");
		person.setBlockInfoValue("dept", "showCount", "true");
		return person;
	}

}
