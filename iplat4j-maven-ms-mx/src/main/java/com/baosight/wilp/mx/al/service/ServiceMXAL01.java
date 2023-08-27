package com.baosight.wilp.mx.al.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.mx.al.domain.MXAL01;
import org.apache.commons.collections.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报警信息页面（页面初始化方法、多功能查询方法）
 * @author Wzy
 * @title: ServiceCMAL01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/129:56
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceMXAL01 extends ServiceBase {

    /**
     *  报警信息页面 的页面初始化方法
     *
     * @param inInfo
     * @return
     * @author Wzy
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {

        return super.initLoad(inInfo, new MXAL01());
    }


    /**
     * 报警信息页面 的 查询功能
     * 1.获取一些前台的属性（有的是小代码的）
     * 2.然后把一些不是inqu_status前缀的属性加入到这个里面去
     * 3.然后判断树状的最上面的全部搜索 如果是root  全部搜索
     * 4.然后塞入对应的值
     * 5.调用对应的sql语句 然后根据sql语句里的判断然后返回sql语句
     * 6.然后给grade进行映射 然后给对应的名字
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        Set List2 = new HashSet<>();  //树状存储
        Map map = new HashMap();
        Set SelectSet = new LinkedHashSet<>();
        Boolean bean = false;     //搜索授权
        Boolean Highest = false; //最高权限
        List arrayList = new ArrayList<>();    //用来存放组信息
        Map<Object, Object> hashMap = new HashMap<>();
        String userUuid = UserSession.getUserUuid();  //获取登录ID
        hashMap.put("id", userUuid);
        //------------------------------------------
        List<Map> listID = dao.query("MSPL02.relevance22", hashMap);   //通过ID调用组信息
//        for (int i = 0; i < listID.size(); i++) {
//            if (listID.get(i).get("groupCname").toString().equals("管理员组")) {
//                Highest = true;
//                break;
//            } else {
//                bean = false;
//                arrayList.add(listID.get(i).get("groupId").toString());
//            }
//        }
        Highest = true;
        String grade = inInfo.get("grade").toString();//告警等级
        String process_start_time = inInfo.get("process_start_time").toString();//开始时间(确认情况)
        String process_end_time = inInfo.get("process_end_time").toString();//结束时间(处理情况)
        String status = inInfo.get("status").toString();//告警状态
        Map m4 = inInfo.getBlocks();
        EiBlock eiBlock = (EiBlock) m4.get("inqu_status");
        List<Map> list = eiBlock.getRows();
        EiBlock eiBlock1 = (EiBlock) inInfo.getBlocks().get("inqu_status");
        List<Map> list1 = eiBlock1.getRows();
        String start_time = list1.get(0).get("start_time").toString();//开始报警时间
        String device_name = list1.get(0).get("device_name").toString();//告警设备
        String item = list1.get(0).get("item").toString();//告警项
        String end_time = list1.get(0).get("end_time").toString();//结束报警时间
        String parentId = list1.get(0).get("parentId").toString();//树状点位
        if (start_time.trim().length() == 0) {      //判断开始报警时间的长度等于0
            list1.get(0).remove("start_time");
        } else {
            map.put("start_time", start_time);
            bean = false;
        }
        if (device_name.trim().length() == 0) {    //判断告警设备的长度等于0
            list1.get(0).remove("device_name");
        } else {
            map.put("device_name", device_name);
            bean = false;
        }
        if (item.trim().length() == 0) {     //判断告警项的长度等于0
            list1.get(0).remove("item");
        } else {
            map.put("item", item);
            bean = false;
        }
        if (end_time.trim().length() == 0) {   //判断结束报警时间的长度等于0
            list1.get(0).remove("end_time");
        } else {
            map.put("end_time", end_time);
            bean = false;
        }
        if (parentId.equals("root")) {    //判断树状点位的长度等于0
            list.get(0).put("parentId", "");
        } else {
            map.put("parentId", parentId);
        }
        list.get(0).put("grade", grade);//告警等级
        list.get(0).put("process_start_time", process_start_time);//开始时间(确认情况)
        list.get(0).put("process_end_time", process_end_time);//结束时间(处理情况)
        list.get(0).put("status", status);//告警状态
        if (process_end_time.equals("2") && status.equals("1")) {
            list.get(0).put("status", "");
        }
        //--------------------------------------------------------------------
        //bean.equals(false)判断是否有搜索条件
        //Highest.equals(false)判断没有管理员条件执行
        if (bean.equals(false) && Highest.equals(false)) {
            //    List SelectList = new ArrayList<>();
            if (arrayList.size() > 0) {    //判断所属分组大于0
                for (int i = 0; i < arrayList.size(); i++) {  //循环组信息
                    hashMap.put("id", arrayList.get(i));
                    //查询关联信息
                    List<Map> listTreeID = dao.query("MSPL02.relevanceSelect", hashMap);
                    if (listTreeID.size() > 0) {
                        for (int j = 0; j < listTreeID.size(); j++) {
                            if (listTreeID.get(j).get("t_param_id") != null) {
                                if (parentId.equals("root")) {
                                    SelectSet.add(listTreeID.get(j).get("t_param_id"));
                                } else {
                                    SelectSet.add(parentId);    //储存关联字段
                                }
                            }
                        }
                    }
                }
            }
        }
        //bean.equals(false)判断是否有搜索条件
        //Highest.equals(false)判断没有管理员条件执行
        if (bean.equals(false) && Highest.equals(false)) {
            if (SelectSet.size() > 0) {     //
                for (Object hash : SelectSet) {     //循环关联字段
                    map.put("parentId", hash);
                    List<Map> listTree = dao.query("MXAL01.selectTree", map);    //获取菜单信息
                    if (listTree.size() > 0) {
                        for (int i = 0; i < listTree.size(); i++) {
                            List2.add(listTree.get(i));  //储存菜单信息
                        }
                    }
                }
                ArrayList<Object> arrayListTree = new ArrayList<>();
                for (Object hash : List2) {   //循环菜单信息
                    arrayListTree.add(hash);    //排序整理数据
                }
                Map m = inInfo.getBlocks();
                EiBlock eiBlocks = (EiBlock) m.get("result");
                eiBlocks.setRows(arrayListTree);
                List<Map> list2 = eiBlocks.getRows();
                if (list2.size() > 0 && list2 != null) {  //判断集合不为空 代码健壮
                    for (int i = 0; i < list2.size(); i++) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        /**
                         * 然后对时间进行处理
                         */
                        if (list2.get(i).get("end_time") != null) {
                            String end_time2 = list2.get(i).get("end_time").toString();
                            try {
                                Date d = simpleDateFormat.parse(end_time2);
                                list2.get(i).put("end_time", simpleDateFormat.format(d));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        if (list2.get(i).get("start_time") != null) {
                            String start_time2 = list2.get(i).get("start_time").toString();
                            try {
                                Date d = simpleDateFormat.parse(start_time2);
                                list2.get(i).put("start_time", simpleDateFormat.format(d));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        /**
                         * 判断告警等级 然后从小代码数字转成中文
                         */
                        if (list2.get(i).get("grade") != null) {
                            String grades = list2.get(i).get("grade").toString();
                            if (grades.equals("0")) {
                                list2.get(i).put("grade", "通讯异常");
                            } else if (grades.equals("1")) {
                                list2.get(i).put("grade", "一级告警");
                            } else if (grades.equals("2")) {
                                list2.get(i).put("grade", "二级告警");
                            } else if (grades.equals("3")) {
                                list2.get(i).put("grade", "三级告警");
                            } else if (grades.equals("4")) {
                                list2.get(i).put("grade", "四级告警");
                            }
                        }
                    }
                }
            }
        } else {
            EiInfo outInfo = super.query(inInfo, "MXAL01.select", new MXAL01());
            Map m = outInfo.getBlocks();
            EiBlock eiBlocks = (EiBlock) m.get("result");
            List<Map> list2 = eiBlocks.getRows();
            if (CollectionUtils.isNotEmpty(list2)) {       //判断集合不为空 代码健壮
                for (int i = 0; i < list2.size(); i++) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    /**
                     * 然后对时间进行处理
                     */
                    if (list2.get(i).get("end_time") != null) {
                        String end_time2 = list2.get(i).get("end_time").toString();
                        try {
                            Date d = simpleDateFormat.parse(end_time2);
                            list2.get(i).put("end_time", simpleDateFormat.format(d));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (list2.get(i).get("start_time") != null) {
                        String start_time2 = list2.get(i).get("start_time").toString();
                        try {
                            Date d = simpleDateFormat.parse(start_time2);
                            list2.get(i).put("start_time", simpleDateFormat.format(d));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    /**
                     * 判断告警等级 然后从小代码数字转成中文
                     */
                    if (list2.get(i).get("grade") != null) {
                        String grades = list2.get(i).get("grade").toString();
                        if (grades.equals("0")) {
                            list2.get(i).put("grade", "通讯异常");
                        } else if (grades.equals("1")) {
                            list2.get(i).put("grade", "一级告警");
                        } else if (grades.equals("2")) {
                            list2.get(i).put("grade", "二级告警");
                        } else if (grades.equals("3")) {
                            list2.get(i).put("grade", "三级告警");
                        } else if (grades.equals("4")) {
                            list2.get(i).put("grade", "四级告警");
                        }
                    }
                }
            }
            return outInfo;
        }
        //---------------------------------------------------------------------------------
        /**
         * 塞入分页配置
         */
        if (inInfo.getBlocks().get("result") != null) {
            EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
            Map attr = result.getAttr();
            attr.put("count", result.getRows().size());
        }
        return inInfo;
    }

    /**
     * 选择已处理/已确认然后填充修改人、时间等
     * @param eiInfo
     * @return
     */
    public EiInfo isCheck(EiInfo eiInfo){
        String loginName = UserSession.getLoginName();
        Map m=new HashMap();
        String type = eiInfo.getAttr().get("type").toString();
        String processValue = Optional.ofNullable(eiInfo.getAttr().get("process_value")).orElse("").toString();
        if (type.equals("checks")){
            m.put("Ids",eiInfo.getAttr().get("id"));
            m.put("loginName",loginName);
            m.put("processValue",processValue);
            EiInfo e=new EiInfo();
            PackageOarameters.setRows(e,m);
            EiInfo update = super.update(e,"MXAL01.selectIsCheck");
            update.setMsg("修改成功");
            return update;
        }else{
            if (StringUtil.isEmpty(processValue)) {
                eiInfo.setStatus(-1);
                eiInfo.setMsg("请输入处理说明！");
                return eiInfo;
            }
            m.put("Ids",eiInfo.getAttr().get("id"));
            m.put("loginName",loginName);
            m.put("processValue",processValue);
            EiInfo e=new EiInfo();
            PackageOarameters.setRows(e,m);
            EiInfo update = super.update(e,"MXAL01.selectIsCheck2");
            update.setMsg("修改成功2");
            return update;
        }
    }
}
