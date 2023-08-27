/**
 *@Name DormUtil.java
 *@Description TODO
 *@Author jiangzhongmin
 *@Date 2021年5月20日 下午4:16:48
 *@Version 1.0
 **/

package com.baosight.wilp.ms.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.xservices.xs.util.UserSession;

public class DormUtil {
  @SuppressWarnings("unchecked")
	public static Map<String,Object> getUserInfo(String workNo) {
    	//获取用户
        EiInfo info = new EiInfo();
        info.set("workNo", StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo);
        info.set(EiConstant.serviceName, "MTRE01");
        info.set(EiConstant.methodName, "queryPersonnel");
        EiInfo infoLogin =XLocalManager.call(info);
        if(infoLogin.getBlock("person")!=null && infoLogin.getBlock("person").getRowCount() > 0){
        	Map<String,Object> map = infoLogin.getBlock("person").getRow(0);
        	return map;
        } else {
        	return null;
        }
        
        
  }
  
  
  
  /**
   * 前端参数转map
   * @param inInfo
   * @param list
   * @return
   */
  public static Map<String, String> changeToMap(EiInfo inInfo, List<String> list) {
      Map<String, String> map = new HashMap<>();
      Map param = inInfo.getAttr();
      //获取分页信息
		EiBlock result = inInfo.getBlock("result");
		Integer offset = 0;Integer limit = 10;
		if(result != null && !result.getAttr().isEmpty()){
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
		}
		map.put("offset", offset.toString());
		map.put("limit", limit.toString());
      list.forEach(e -> map.put(e, (String)param.get(e)));
      return map;
  }
}

