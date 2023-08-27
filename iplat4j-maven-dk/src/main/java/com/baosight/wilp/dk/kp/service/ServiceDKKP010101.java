package com.baosight.wilp.dk.kp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 获取卡片项目类：查询保养具体项目初始化、查询保养具体项目的信息
 * <p>1.查询保养具体项目初始化   initLoad
 * <p>2.查询保养具体项目的信息   query
 * 
 * @Title: ServiceDKKP010101.java
 * @ClassName: ServiceDKKP010101
 * @Package：com.baosight.wilp.dk.kp.service
 * @author: LENOVO
 * @date: 2021年9月10日 下午4:40:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKKP010101 extends ServiceBase{

    /**
     * 查询保养具体项目初始化
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 查询保养具体项目的信息
	 * <p>1.将传递参数分装map
	 * <p>2.使用map参数查询获取保养项目list和count
	 * <p>3.将返回的保养项目list和count添加到EiiNFO并返回客户端
	 * @param
	 * *id            项目id
	 * itemName      项目名称
	 * @return 
	 * id                 项目id
	 * content            保养项目
	 * projectDesc        项目描述
	 * actualValueUnit    实际值单位
	 */
    @Override
	public EiInfo query(EiInfo inInfo) {
    	//1.将传递参数分装map
    	String idString=(String) inInfo.getAttr().get("id");
        String[] param = {"id","itemName"};
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
        List<String> idList  = new ArrayList<String>();
        //判断id不为空
        if(!idString.isEmpty() || idString!="") {
           String[] idArr=idString.split(",");
           idList = Arrays.asList(idArr);
        }
        map.put("id", idList);
        //2.使用map参数查询获取保养项目list和count
        List<Map<String, String>> list = dao.query("DKKP01.queryItemAll", map);
        int count = dao.count("DKKP01.queryItemAllCount", map);
        //3.将返回的保养项目list和count添加到EiiNFO并返回客户端
        return DeviceEiUtil.buildResult(inInfo, list, count, "result");
    }
    
    
}
