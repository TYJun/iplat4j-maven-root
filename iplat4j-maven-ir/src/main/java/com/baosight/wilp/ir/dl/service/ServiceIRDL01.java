package com.baosight.wilp.ir.dl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.sm.dl.service
 * @ClassName ServiceIRDL01
 * @Description 安全登录模块, 登录失败次数限制
 * @Author chunchen2
 * @Date 2021/11/12 10:49
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/12 10:49
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRDL01 extends ServiceEPBase {

    public static int limitCount;
    public static int limitMinutes;

    static{
        String limitMinutesStr = PlatApplicationContext.getProperty("iplat.login.limit.minutes");
        limitMinutes = (null == limitMinutesStr || "".equalsIgnoreCase(limitMinutesStr.trim())) ? 15 :
                Integer.parseInt(limitMinutesStr);

        String limitCountStr = PlatApplicationContext.getProperty("iplat.login.limit.count");
        limitCount = (null ==limitCountStr || "".equalsIgnoreCase(limitCountStr.trim())) ? 5 : Integer.parseInt(limitCountStr);
    }

    /**
     * @Title LoginFailureHandle
     * @Author chunchen2
     * @Description // 登录失败处理类
     *              1. 用户登录次数 +1
     *              表：xs_user_login_count
     * @Date 10:52 2021/11/12
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo loginFailureHandle(EiInfo eiInfo){

        // 1. 首先根据用户名查询该用户登录失败的信息
        String loginName = eiInfo.getString("loginName");
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        Map param = new HashMap();
        param.put("platSchema", platSchema);
        param.put("loginName", loginName);
        List<Map<String, String>> userLoginInfoLists = dao.query("IRDL01.queryUserLoginInfos", param);

        // 2. 登录失败次数 +1
        if(null != userLoginInfoLists && userLoginInfoLists.size() > 0) {
            Map<String, String> userLoginInfoMap = userLoginInfoLists.get(0);
            int failureCount = Integer.parseInt(userLoginInfoMap.get("failureCount"));
            String failureCreateTime = userLoginInfoMap.get("failureCreateTime");
            long failureTime = 0;
            try {
                if(null != failureCreateTime){
                    failureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(failureCreateTime).getTime();
                }
            } catch (ParseException e) {
            }

            // 如果失败次数为5，直接将次数重新置为1,重置登录失败的时间
            if(failureCount >= limitCount){
                failureCount = 0;
                param.put("failureCreateTime", new Date());
            }

            // 超过限制时间进行登录，直接重新计数，这里会有一个临界值的bug，待处理
            if((new Date().getTime() - failureTime) > 60 * 1000 * limitMinutes){
                failureCount = 0;
                param.put("failureCreateTime", new Date());
            }

            param.put("failureCount", failureCount + 1);
            dao.update("IRDL01.updateUserLoginInfos", param);
        } else { // 数据库不存在该账号的登录失败信息，插入初始记录
            param.put("failureCreateTime", new Date());
            dao.insert("IRDL01.insertUserLoginInfos", param);
        }

        return eiInfo;
    }

    /**
     * @Title queryLoginFailureCount
     * @Author chunchen2
     * @Description //查询当前用户的登录失败的信息
     * @Date 12:56 2021/11/12
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryLoginFailureInfo(EiInfo eiInfo){

        int loginFailureCount = 0;
        String failureCreateTime = null;
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        Map queryParam = new HashMap();
        queryParam.put("platSchema", platSchema);
        queryParam.put("loginName", eiInfo.getString("loginName"));
        List<Map<String, String>> userLoginInfoLists = dao.query("IRDL01.queryUserLoginInfos", queryParam);

        if(null != userLoginInfoLists && userLoginInfoLists.size() > 0){
            loginFailureCount = Integer.parseInt(userLoginInfoLists.get(0).get("failureCount"));
            failureCreateTime = userLoginInfoLists.get(0).get("failureCreateTime");
        }

        eiInfo.set("loginFailureCount", loginFailureCount);
        eiInfo.set("failureCreateTime", failureCreateTime);

        return eiInfo;
    }

    /**
     * @Title updateUserLoginFailureInfo
     * @Author chunchen2
     * @Description // 修改用户登录失败的记录信息
     * @Date 13:50 2021/11/12
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateUserLoginFailureInfo(EiInfo eiInfo){
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        Map updateParam = new HashMap();
        updateParam.put("platSchema", platSchema);
        updateParam.put("loginName", eiInfo.getString("loginName"));
        updateParam.put("loginFailureCount", eiInfo.getString("loginFailureCount"));

        String failureCreateTime = eiInfo.getString("failureCreateTime");
        if(null != failureCreateTime){
            updateParam.put("failureCreateTime", failureCreateTime);
        }

        int ret = dao.delete("IRDL01.updateUserLoginInfos", updateParam);

        eiInfo.set("result", ret > 0 ? "修改成功" : "修改失败");

        return eiInfo;
    }

    /**
     * @Title clearUserLoginFailureInfo
     * @Author chunchen2
     * @Description //清除用户登录失败的记录
     * @Date 13:30 2021/11/12
     * @param
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo clearUserLoginFailureInfo(EiInfo eiInfo){
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        Map queryParam = new HashMap();
        queryParam.put("platSchema", platSchema);
        queryParam.put("loginName", eiInfo.getString("loginName"));
        int ret = dao.delete("IRDL01.deleteUserLoginInfos", queryParam);

        eiInfo.set("result", ret > 0 ? "删除成功" : "删除失败");

        return eiInfo;
    }

}
