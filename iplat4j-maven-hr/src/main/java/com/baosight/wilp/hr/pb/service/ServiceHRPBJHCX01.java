package com.baosight.wilp.hr.pb.service;


import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.hr.pb.domain.HRPBJhgl01;
import com.baosight.wilp.hr.pb.domain.HRPBSjpz01;
import com.baosight.wilp.hr.pb.utils.CalendarUtils;
import com.baosight.wilp.hr.pb.utils.UUIDUtils;
import com.baosight.wilp.hr.pb.utils.WeekDateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

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
public class ServiceHRPBJHCX01 extends ServiceBase {


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
        String date = CalendarUtils.dateFormat(new Date());
        LinkedHashMap linkmap = null;
        try {
            linkmap = WeekDateUtils.getWeekMap(date);
            initLoad.addBlock("planDate").addRow(linkmap);
            List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
            Set<String> set = linkmap.keySet();
            for (String key : set) {
                int index = Integer.parseInt(key);
                Map<String,Object> map = new HashMap<>();
                map.put("typeName" ,"第" + (index + 1) + "周");
                map.put("typeCode" ,"Week" + index);
                listMap.add(map);
            }
            initLoad.addBlock("planWeek").addRows(listMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        EiBlock block = inInfo.getBlock("inqu_status");
        String weekAbc = "";
        String planDate = "";
        try {
            if(block != null) {
                //从查询条件中获取周次
                weekAbc = block.getCellStr(0, "weekAbc");
                weekAbc = weekAbc.split("Week")[1];
                //从查询条件中排班日期
                planDate = block.getCellStr(0, "planDate");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            date = format.parse(planDate);
            //设置查询条件的起止日期
            Date weekFirst = WeekDateUtils.getWeekFirstDay(Integer.parseInt(weekAbc), date);
            Date weekLast = WeekDateUtils.getWeekLastDay(Integer.parseInt(weekAbc), date);
            block.setCell(0,"weekFristDay",format.format(weekFirst));
            block.setCell(0,"weekLastDay",format.format(weekLast));
            //获取日期对应月份该周次的起止日期
            inInfo = super.query(inInfo, "HRPBJhgl01.queryFsfy", new HRPBJhgl01());
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setStatus(-1);
        }
        return inInfo;
    }

    /**
     *
     * 删除订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#delete(EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        try {
            outInfo = super.delete(inInfo, "HRPBSjpz01.delete");
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
     * 新增订餐时间
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
                String canteenNum = StringUtils.toString(map.get("canteenNum"));
                String mealNum = StringUtils.toString(map.get("mealNum"));
                //判断当前登录人是否为该班组管理员
                String loginName = UserSession.getLoginName();

                //判断该班组对应班次是否存在记录
                int count = countCanteenMeal(canteenNum,mealNum);
                if(count > 0) {
                    outInfo.setMsg("班次时间已存在！");
                }else {
                    map.put("id", UUIDUtils.getUUID32());
                    //将数据填充到result
                    inInfo.addBlock("result").addRows(submitList);
                    outInfo = super.insert(inInfo, "HRPBSjpz01.insert");
                    outInfo.setMsg("保存成功！");
                }
            }else {
                outInfo.setMsg("数据提交失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            outInfo.setMsg("保存失败！");
        }
        return outInfo;
    }


    /**
     *
     * 查询指定食堂指定餐次订餐时间的记录数
     *
     * @Title: countCanteenMeal
     * @param canteenNum
     * @param mealNum
     * @return
     * @return: int
     * @author: liutao
     * @date: 2021年9月9日 下午4:18:03
     */
    public int countCanteenMeal(String canteenNum,String mealNum) {
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        //初始化加载数据canteenData:食堂名称
        paramMap.put("canteenNum", canteenNum);
        paramMap.put("mealNum", mealNum);
        List query = dao.query("HRPBSjpz01.count", paramMap);
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
                //将数据填充到result
                inInfo.addBlock("result").addRows(submitList);
                //更新数据
                outInfo = super.update(inInfo, "HRPBSjpz01.update");
                outInfo.setMsg("更新成功！");

            }else {
                outInfo.setMsg("数据提交失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            outInfo.setMsg("更新失败！");
        }
        return outInfo;
    }
}
