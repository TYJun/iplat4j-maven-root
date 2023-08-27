package com.baosight.wilp.hr.pb.utils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ACServiceUtils {

    /**
     * queryServiceDept:查询服务科室
     * @param  datagroupCode:院区
     * @return EiInfo outInfo
     * */
    public static EiInfo queryServiceDept(String datagroupCode)  throws PlatException{
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_12");
        if(StringUtil.isNotBlank(datagroupCode)){
            inInfo.set("datagroupCode",datagroupCode);
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        if(outInfo.getStatus() < 0){
            throw new PlatException(outInfo.getMsg());
        }
        return outInfo;
    }


    /**
     * queryDeptInfoByUser:查询服务科室
     * @param  workNo:工号
     * @return EiInfo outInfo
     * */
    public static EiInfo queryDeptInfoByUser(String workNo)  throws PlatException{
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_07");
        if(StringUtil.isNotBlank(workNo)){
            inInfo.set("workNo",workNo);
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        if(outInfo.getStatus() < 0){
            throw new PlatException(outInfo.getMsg());
        }
        return outInfo;
    }

    /**
     * queryStaffInfo:通过员工ID或者员工工号查询员工详情
     * @param  workNo:工号
     * @return EiInfo outInfo
     * */
    public static EiInfo queryWorkInfo(String workNo)  throws PlatException{
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_02");
        if(StringUtil.isNotBlank(workNo)){
            inInfo.set("workNo",workNo);
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        if(outInfo.getStatus() < 0){
            throw new PlatException(outInfo.getMsg());
        }
        return outInfo;
    }

    /**
     * Todo(判断工号是否为该科室的管理员)
     *
     * @Title: checkWarden
     * @author xiajunqing
     * @date: 2022/1/14 14:42
     * @Param workNo
     * @return:
     */
    public static boolean checkWarden(String workNo,String deptNum){
        //判断当前登录人是否为该班组管理员
        EiInfo ei = ACServiceUtils.queryDeptInfoByUser(workNo);
        HashMap<String, Object> hashMap = (HashMap<String, Object>) ei.get("result");
        return deptNum.equals(StringUtils.toString(hashMap.get("deptNum")));
    }
    
    
    /**
     * queryServiceDept:查询服务科室
     * @param  datagroupCode:院区
     * @return EiInfo outInfo
     * */
    public static EiInfo queryServiceDeptFsfy(String datagroupCode)  throws Exception{
        EiInfo inInfo = new EiInfo();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("datagroupCode", datagroupCode);
        List queryForList = SqlUtils.queryForListMap("HRPBSjpz01.queryServiceDept", paramMap);
        //List queryForList = SqlUtils.queryForListByString("DRPBSjpz01.queryServiceDept", datagroupCode);
        inInfo.set("result", queryForList);
        return inInfo;
    }
    
    /**
     * Todo(判断工号是否为该科室的管理员)
     *
     * @Title: checkWarden
     * @author xiajunqing
     * @date: 2022/1/14 14:42
     * @Param workNo
     * @return:
     */
    public static boolean checkWardenFsfy(String workNo,String deptNum) throws Exception{
        //判断当前登录人是否为该班组管理员
        EiInfo ei = ACServiceUtils.queryDeptInfoByUserFsfy(workNo);
        HashMap<String, Object> hashMap = (HashMap<String, Object>) ei.get("result");
//        return deptNum.equals(StringUtils.toString(hashMap.get("deptNum")));
        return true;
    }
    

    /**
     * queryDeptInfoByUser:查询服务科室
     * @param  workNo:工号
     * @return EiInfo outInfo
     * */
    public static EiInfo queryDeptInfoByUserFsfy(String workNo) throws Exception{
        EiInfo inInfo = new EiInfo();
        HashMap<String, Object> map = (HashMap<String, Object>)SqlUtils.queryForObjectByString("HRPBSjpz01.queryDeptInfoByUser", workNo);
        inInfo.set("result", map);
        return inInfo;
    }
    
    /**
     * queryStaffInfo:通过员工ID或者员工工号查询员工详情
     * @param  workNo:工号
     * @return EiInfo outInfo
     * */
    public static EiInfo queryWorkInfoFsfy(String workNo)  throws Exception{
        EiInfo inInfo = new EiInfo();
        HashMap<String, Object> map = (HashMap<String, Object>)SqlUtils.queryForObjectByString("HRPBSjpz01.queryWorkInfo", workNo);
        inInfo.set("result", map);
        return inInfo;
    }
}
