package com.baosight.wilp.ac.jk.util;

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
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(datagroupCode)){
            inInfo.set("datagroupCode",datagroupCode);
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        if(outInfo.getStatus() < 0){
            throw new PlatException(outInfo.getMsg());
        }
        return outInfo;
    }

    /**
     * queryDeptInfoBySpot:building
     * @param  spotNum:地点编码
     * @return EiInfo outInfo
     * */
    public static EiInfo queryDeptInfoBySpot(String spotNum,String spotName)  throws PlatException{
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_08");
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(spotNum)){
            inInfo.set("spotNum",spotNum);

        }
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(spotName)){
            inInfo.set("spotName",spotName);
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        if(outInfo.getStatus() < 0){
            throw new PlatException(outInfo.getMsg());
        }
        return outInfo;
    }

    /**
     * getRoom:room
     * @param  building:楼
     * @return EiInfo outInfo
     * */
    public static EiInfo getRoom(String building,String floor,String room)  throws PlatException{
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_15");
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(building)){
            inInfo.set("building",building);

        }
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(floor)){
            inInfo.set("floor",floor);
        }
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(room)){
            inInfo.set("room",room);
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
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(workNo)){
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
        if(!com.baosight.wilp.ac.we.utils.StringUtil.isBlank(workNo)){
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
    
}
