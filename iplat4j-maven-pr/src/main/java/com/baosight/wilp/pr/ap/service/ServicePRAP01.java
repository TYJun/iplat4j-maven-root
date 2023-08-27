package com.baosight.wilp.pr.ap.service;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pr.ap.domain.Information;

/**
 * 
 * 获取登录信息 -- 暂未使用
 * 
 * @Title: ServicePRAP01.java
 * @ClassName: ServicePRAP01
 * @Package：com.baosight.zdyyaq.pr.ap.service
 * @author: 张家乾
 * @date: 2021年5月8日 下午1:57:24
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRAP01 extends ServiceBase{

    
    
    /**
     * 注入dao
     */
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    
    /**
     * 
     * 返回登录基础信息
     *
     * @Title: login 
     * @return 
     * @return: ResultData
     */
    public EiInfo login(EiInfo inInfo) {
        //获取用户名参数
        Map attr = inInfo.getAttr();
        String userName = attr.get("name").toString();
        //查询用户信息
        List<Information> param = dao.query("PRAP01.queryLoginHosp", userName);
        List<Information> param2 = dao.query("PRAP01.queryDept", userName);
        for (Information pa : param) {
            for (Information pa2 : param2) {
                pa.setDeptCode(pa2.getDeptCode());
                pa.setDeptName(pa2.getDeptName());
            }
        }
        Information par = param.get(0);
        Map<String, Object> entityToMap = entityToMap(par);
        inInfo.set("map", entityToMap);
        return inInfo;
    }
    
    
    /**
     * 
     * 对象转map
     *
     * @Title: entityToMap 
     * @param object
     * @return 
     * @return: Map<String,Object>
     */
    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()){
          try {
            boolean flag = field.isAccessible();
            field.setAccessible(true);
            Object o = field.get(object);
            map.put(field.getName(), o);
            field.setAccessible(flag);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        return map;
      }
    
}
