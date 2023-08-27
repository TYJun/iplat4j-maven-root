package com.baosight.wilp.di.jz.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 巡检新增设备包：跳转设备包初始化、保存巡检设备包
 * <p>1.跳转设备包初始化 initLoad
 * <p>2.保存巡检设备包 insr
 * @Title: ServiceDIJZ0201.java
 * @ClassName: ServiceDIJZ0201
 * @Package：com.baosight.wilp.di.jz.service
 * @author: zhangjiaqian
 * @date: 2021年5月31日 上午9:31:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIJZ0201 extends ServiceBase{


    /**
     * 注入dao
     */
    private static Dao dao1 = (Dao) PlatApplicationContext.getBean("dao");
    

    /**
     * 
     * 跳转设备包初始化
     * 
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
//        EiInfo outInfo = new EiInfo();
//        EiBlock resultBlock = outInfo.addBlock(EiConstant.resultBlock);
//        resultBlock.addBlockMeta(new DfClassfyparam().eiMetadata);
//        return outInfo;
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        // TODO Auto-generated method stub
         return inInfo;
    }

    /**
     * 
     * 保存巡检设备包
     * <p>获取参数
     * <p>生成设备编码
     * <p>生成UUID
     * <p>保存设备包
     * <p>循环保存设备包内容
     * @Title: insr 
     * @param inInfo
     * packet_name    设备包名称
     * memo           备注
     * createMan      创建人
     * createTime     创建时间
     * STATUS         状态
     * packet_code    设备包编号
     * id             主键
     * packet_id      设备包id
     * device_id      设备id
     * machine_code   设备编号
     * machine_name   设备名称
     * fixed_place    地址
     * models         规格型号
     * @return 
     * 保存成功，失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo insr(EiInfo inInfo) {
        //获取参数
        //设备包名
        String dangerwhere = (String)inInfo.get("dangerwhere");
        //备注
        String requrieDesc = (String)inInfo.get("requrieDesc");
        List<Map<String,String>> data = (List<Map<String,String>>)inInfo.get("data");
        String userName = UserSession.getLoginName();
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        //生成设备编码
        String code = dangerCode();
        //生成UUID
        UUID randomUUID = UUID.randomUUID();
        
        Map packMap = new HashMap();
        packMap.put("packet_name", dangerwhere);
        packMap.put("memo", requrieDesc);
        packMap.put("createMan", userName);
        packMap.put("createTime", createTime);
        packMap.put("STATUS", "0");
        packMap.put("packet_code",  code);
        packMap.put("id",  randomUUID.toString());

        //保存设备包
        dao.insert("DIJZ0201.insertPack", packMap);
        Map mapDetail = new HashMap();
        //循环保存设备包内容
        for (Map<String, String> map : data) {
            mapDetail.put("packet_id", randomUUID.toString());
            mapDetail.put("device_id", map.get("deviceId"));
            mapDetail.put("machine_code", map.get("paramKey"));
            mapDetail.put("machine_name", map.get("paramName"));
            mapDetail.put("fixed_place", map.get("classifyName"));
            mapDetail.put("models", map.get("models"));
            dao.insert("DIJZ0201.insertPackDetail", mapDetail);
        }
        inInfo.setMsg("保存成功");
        inInfo.setStatus(1);
        return inInfo;
    }
    
    
    

    /**
     * 
     * 生成巡检设备包编号
     *<p>查询当天是否生成过编号
     * @Title: dangerCode 
     * @return 
     * @return: String
     */
    public static String dangerCode() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(new Date());
        String replace = format.replace("-", "");
        String substring = replace.substring(2, replace.length());
        //查询当天是否生成过编号
        List query = dao1.query("DIJZ0201.dangerCodeCount", "DP"+substring);
        String count = query.get(0).toString();
        Integer valueOf = Integer.valueOf(count);
        Integer par = valueOf + 1;
        String string = par.toString();
        String code = "DP"+ substring + "00" + string;
        return code;
    }

}
