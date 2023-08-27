package com.baosight.wilp.dk.sj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保养实绩管理类：保养实绩初始化查询、保养实绩管理
 * <p>1.保养实绩初始化查询     initLoad
 * <p>2.保养实绩管理                 query
 * 
 * @Title: ServiceDKSJ01.java
 * @ClassName: ServiceDKSJ01
 * @Package：com.baosight.wilp.dk.sj.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午2:56:24
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKSJ01 extends ServiceBase{

    /**
     * 保养实绩初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param info
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
        return query(info);
    }
	/**
	 * 保养实绩管理
	 * <p>1.将传递参数分装map
	 * <p>2.使用map参数查询获取保养分类list和count
	 * <p>3.将返回的保养分类list和count添加到EiiNFO并返回客户端
	 * @param
	 * *taskCode        任务编码
	 * taskName        任务名称
	 * status          状态
	 * spotName        设备名称
	 * startTimeS      开始时间
	 * startTimeE      结束时间
	 * @return         
	 * taskCode        任务编码
     * taskName        任务名称
     * status          状态
     * spotName        设备名称
     * startTimeS      开始时间
     * startTimeE      结束时间
     * machineName     设备名称
     * machineCode     设备代码
     * jobContent      作业说明
     * deptName        责任单位部门 
     * name            责任人
	 */
    @Override
	public EiInfo query(EiInfo info) {
        String userId=UserSession.getUserId();
    	//1.将传递参数分装map
        Map<String, String> map=new HashMap<String, String>();
        String schemeDept = (String) info.getAttr().get("schemeDept");
        //2.将传参名称保存到数组里并获取传参值到map里
        String[] param = {"taskCode","taskName","status","schemeDept","startTimeS","startTimeE"};
        Map<String, Object> buildParam = DeviceEiUtil.buildParam(info, Arrays.asList(param), "result");
        //3.判断基准科室不为空
        if(schemeDept!=null) {
        	if(schemeDept.equals("--请选择--")) {
            	buildParam.put("schemeDept", "");
            }
        }
        buildParam.put("userId", userId);
        //4.使用map参数查询获取保养分类list和count
        List<Map<String,String>> list=dao.query("DKSJ01.query",buildParam);
        int count = dao.count("DKSJ01.count",buildParam);
        //5.将返回的保养分类list和count添加到EiiNFO并返回客户端
        return DeviceEiUtil.buildResult(info, list, count, "result");
    }
	
}
