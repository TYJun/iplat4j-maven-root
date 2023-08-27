package com.baosight.wilp.hr.pb.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.hr.pb.domain.HRPBSjpz01;
import com.baosight.wilp.hr.pb.utils.*;

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
public class ServiceHRPBSJPZ01 extends ServiceBase {


    /**
     *
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo initLoad = super.initLoad(inInfo, new HRPBSjpz01());
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        //初始化加载数据deptList:班组信息
        
        return initLoad;
    }


    /**
     *
     * grid数据集查询订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#query(EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "HRPBSjpz01.query", new HRPBSjpz01());
        return outInfo;
    }


    /**
     *
     *
     * @Title: queryDept
     * @param inInfo
     * @return
     * @return: EiInfo
     * @author: liutao
     * @date: 2021年9月9日 下午4:17:18
     */
    public EiInfo queryDept(EiInfo inInfo) {
        try {
            EiInfo call = ACServiceUtils.queryServiceDeptFsfy("");
            List<HashMap<String, Object>> listDeptData = (List<HashMap<String, Object>>) call.get("result");
            inInfo.addBlock("deptData").addRows(listDeptData);
        } catch (Exception e) {
             e.printStackTrace();
             inInfo.setStatus(-1);
             inInfo.setMsg("查询失败");
        }
        return inInfo;
    }

    /**
     *
     * 根据食堂编码和餐次查询订餐时间
     *
     * @Title: queryByCanteenMeal
     * @param inInfo
     * @return
     * @return: EiInfo
     * @author: liutao
     * @date: 2021年9月9日 下午4:17:18
     */
    public EiInfo queryByCanteenMeal(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
        // 食堂编号
        String canteenNum = StringUtils.toString(attr.get("canteenNum"));
        String mealNum = StringUtils.toString(attr.get("mealNum"));
        // 构建查询条件
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("canteenNum", canteenNum);
        paramMap.put("mealNum", mealNum);
        List<HRPBSjpz01> canteenTimes = dao.query("HRPBSjpz01.query", paramMap);
        outInfo.set("obj", canteenTimes);
        return outInfo;
    }


    /**
     *
     * 删除数据
     * @param inInfo
     * @return
     * @see ServiceBase#delete(EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        List<Map<String,Object>> result = inInfo.getBlock("result").getRows();
        try {
            //判断当前登录人是否为该班组管理员
            String workNo = UserSession.getLoginName();
            for (int i = 0; i < result.size(); i++) {
                Map<String,Object> map = result.get(i);
                String deptNum = StringUtils.toString(map.get("deptNum"));
                String deptName = StringUtils.toString(map.get("deptName"));
                boolean flag = ACServiceUtils.checkWardenFsfy(workNo,deptNum);
                if(!flag){
                    inInfo.setMsg("非" + deptName + "管理员，无法删除班次配置");
                    inInfo.setStatus(-1);
                    return inInfo;
                }
                map.put("scheduleId", map.get("id"));
                String daySpan = StringUtils.toString(map.get("daySpan"));
                if("0".equals(daySpan)) {
                    map.put("planDate", CalendarUtils.dateFormat(null));
                }else {
                    map.put("planDate", CalendarUtils.getYeastody());
                }
                
                int count = SqlUtils.countByMap("HRPBSjpz01.countUsed",map);
                if(count > 0) {
                    inInfo.setMsg("该班次正在使用中，无法删除！");
                    inInfo.setStatus(-1);
                    return inInfo;
                }
            }
            outInfo = super.delete(inInfo, "HRPBSjpz01.delete");
            inInfo.setStatus(1);
            outInfo.setMsg("操作成功！");
        }catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("操作失败！");
            inInfo.setStatus(-1);
            return inInfo;
        }
        return outInfo;
    }


    /**
     *
     * 新增班次时间配置
     * @param inInfo
     * @return
     * @see ServiceBase#insert(EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            //获取传参
            Map attr = inInfo.getAttr();
            //接收弹窗数据
            ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
            if(submitList != null && submitList.size() > 0) {
                HashMap<String,Object> map = submitList.get(0);

                String startTime = StringUtils.toString(map.get("startTime"));
                String endTime = StringUtils.toString(map.get("endTime"));
                String restStartTime = StringUtils.toString(map.get("restStartTime"));
                String restEndTime = StringUtils.toString(map.get("restEndTime"));
                String deptNum = StringUtils.toString(map.get("deptNum"));
                String deptName = StringUtils.toString(map.get("deptName"));
                //判断当前登录人是否为该班组管理员
                String workNo = UserSession.getLoginName();
                boolean flag = ACServiceUtils.checkWardenFsfy(workNo,deptNum);
                if(!flag){
                    outInfo.setStatus(-1);
                    outInfo.setMsg("非" + deptName + "管理员，无法录入班次配置");
                    return outInfo;
                }
                //判断该排班是否存在记录
                int count = countTimeConfig(startTime,endTime,restStartTime,restEndTime,deptNum);
                if(count > 0) {
                    outInfo.setStatus(-1);
                    outInfo.setMsg("班次时间已存在！");
                }else {
                    map.put("id", UUIDUtils.getUUID32());
                    List list = LocalServiceUtil.listCastByJson(submitList, HRPBSjpz01.class);
                    //将数据填充到result
                    inInfo.addBlock("result").addRows(list);
                    outInfo = super.insert(inInfo, "HRPBSjpz01.insert");
                    outInfo.setStatus(1);
                    outInfo.setMsg("保存成功！");
                }
            }else {
                outInfo.setStatus(-1);
                outInfo.setMsg("数据提交失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            outInfo.setStatus(-1);
            outInfo.setMsg("保存失败！");
        }
        return outInfo;
    }


    /**
     *
     * 统计已有的排班数量
     *
     * @Title: countTimeConfig
     * @param startTime
     * @param endTime
     * @param restStartTime
     * @param restEndTime
     * @param deptNum
     * @return
     * @return: int
     * @author: liutao
     * @date: 2021年9月9日 下午4:18:03
     */
    public int countTimeConfig(String startTime,String endTime,String restStartTime,String restEndTime,String deptNum) {
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("restStartTime", restStartTime);
        paramMap.put("restEndTime", restEndTime);
        paramMap.put("deptNum", deptNum);
        List query = dao.query("HRPBSjpz01.countTimeConfig", paramMap);
        int i = (int) query.get(0);
        return i;
    }


    /**
     *
     * 编辑数据
     * @param inInfo
     * @return
     * @see ServiceBase#update(EiInfo)
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            Map attr = inInfo.getAttr();
            //接收弹窗数据
            ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
            if(submitList != null && submitList.size() > 0) {
                HashMap<String,Object> map = submitList.get(0);

                String startTime = StringUtils.toString(map.get("startTime"));
                String endTime = StringUtils.toString(map.get("endTime"));
                String restStartTime = StringUtils.toString(map.get("restStartTime"));
                String restEndTime = StringUtils.toString(map.get("restEndTime"));
                String deptNum = StringUtils.toString(map.get("deptNum"));
                String deptName = StringUtils.toString(map.get("deptName"));
                //判断当前登录人是否为该班组管理员
                String workNo = UserSession.getLoginName();
                boolean flag = ACServiceUtils.checkWardenFsfy(workNo,deptNum);
                if(!flag){
                    outInfo.setStatus(-1);
                    outInfo.setMsg("非" + deptName + "管理员，无法修改班次配置");
                }
                map.put("scheduleId", map.get("id"));
                String daySpan = StringUtils.toString(map.get("daySpan"));
                if("0".equals(daySpan)) {
                    map.put("planDate", CalendarUtils.dateFormat(null));
                }else {
                    map.put("planDate", CalendarUtils.getYeastody());
                }
                
//                int count = SqlUtils.countByMap("DRPBSjpz01.countUsed",map);
//                if(count > 0) {
//                    inInfo.setMsg("该班次正在使用中，无法修改！");
//                    inInfo.setStatus(-1);
//                    return inInfo;
//                }
                //将数据填充到result
                inInfo.addBlock("result").addRows(submitList);
                //更新数据
                outInfo = super.update(inInfo, "HRPBSjpz01.update");
            }else {
                outInfo.setStatus(-1);
                outInfo.setMsg("数据提交失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outInfo.setStatus(-1);
            outInfo.setMsg("更新失败！");
        }
        return outInfo;
    }

    public EiInfo effect(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            Map attr = inInfo.getAttr();
            //接收弹窗数据
            ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
            if(submitList != null && submitList.size() > 0) {
                for (int i = 0; i < submitList.size(); i++) {
                    HashMap<String,Object> map = submitList.get(i);
                    String startTime = StringUtils.toString(map.get("startTime"));
                    String endTime = StringUtils.toString(map.get("endTime"));
                    String restStartTime = StringUtils.toString(map.get("restStartTime"));
                    String restEndTime = StringUtils.toString(map.get("restEndTime"));
                    String deptNum = StringUtils.toString(map.get("deptNum"));
                    String deptName = StringUtils.toString(map.get("deptName"));
                    //判断当前登录人是否为该班组管理员
                    String workNo = UserSession.getLoginName();
                    boolean flag = ACServiceUtils.checkWardenFsfy(workNo,deptNum);
                    if(!flag){
                        outInfo.setStatus(-1);
                        outInfo.setMsg("非" + deptName + "管理员，无法修改班次状态");
                        return outInfo;
                    }
                    map.put("scheduleId", map.get("id"));
                    String daySpan = StringUtils.toString(map.get("daySpan"));
                    if("0".equals(daySpan)) {
                        map.put("planDate", CalendarUtils.dateFormat(null));
                    }else {
                        map.put("planDate", CalendarUtils.getYeastody());
                    }
                    
                    int count = SqlUtils.countByMap("HRPBSjpz01.countUsed",map);
                    if(count > 0) {
                        inInfo.setMsg("该班次正在使用中，无法修改！");
                        inInfo.setStatus(-1);
                        return inInfo;
                    }
                }

                //将数据填充到result
                inInfo.addBlock("result").addRows(submitList);
                //更新数据
                outInfo = super.update(inInfo, "HRPBSjpz01.update");
            }else {
                outInfo.setStatus(-1);
                outInfo.setMsg("数据提交失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outInfo.setStatus(-1);
            outInfo.setMsg("更新失败！");
        }
        return outInfo;
    }
}
