package com.baosight.wilp.pr.gl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.pr.DangerCode;
import com.baosight.wilp.pr.gl.domain.PRGL01;
import com.baosight.xservices.xs.util.UserSession;


/**
 * 
 * 安全上报逻辑处理:页面初始化,查询方法,安全问题登记,PC端登记任务工单上传图片时回显,时间转换
 * 
 * <p>1.initLoad        页面初始化    
 * <p>2.query           查询方法  -- 未使用
 * <p>3.insert          安全问题登记      
 * <p>4.showTempPic     PC端登记任务工单上传图片时回显 
 * <p>5.time            时间转换
 * 
 * @Title: ServicePRGL01.java
 * @ClassName: ServicePRGL01
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月23日 下午2:51:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL01 extends ServiceBase {


    
    /**
     * 
     * 初始化页面加载方法
     * 
     * @param inInfo
     * @return inInfo
     * 
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo);
    }


    
    
    /**
     * 
     * 查询图片id方法 -- 未使用
     * 
     * @param inInfo
     * @return info2
     * 
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo info2 = super.query(inInfo, "PRGL01.query", new PRGL01());
        return info2;
    }

    
    
    /**
     * @param inInfo
     * problemCategory: 问题大类编码
     * problemCategoryText: 问题大类名称
     * problemLocation: 问题地点
     * problemlevel: 问题等级
     * problemDesciption: 问题描述
     * time: 要求整改时间
     * requiredesc: 整改要求
     * 代码逻辑：
     * 1.获取参数
     * 2.构建基本信息保存到数据库
     * 3.关联问题图片信息
     * 4.记录日志参数
     * 5.返回状态信息
     *
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        //1.获取参数
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //问题大类编码
        String problemCategory = (String)inInfo.get("problemCategory");
        //问题大类名称
        String problemCategoryText = (String)inInfo.get("problemCategoryText");
        //问题地点
        String problemLocation = (String)inInfo.get("problemLocation");
        //问题等级
        String problemlevel = (String)inInfo.get("problemlevel");
        //问题描述
        String problemDesciption = (String)inInfo.get("problemDesciption");
        //要求整改时间
        String time = (String)inInfo.get("time");
        //处理后的要求整改时间
        String par = time(time);
        //整改要求
        String requiredesc = (String)inInfo.get("requiredesc");
        //生成问题编号
        String dangerCode = DangerCode.dangerCode();
        //生成问题id
        String problemId = UUID.randomUUID().toString();
        //2.构建基本信息保存到数据库
        Map<String, String> map = new HashMap<>();
        map.put("id", problemId);
        map.put("localTypeCode", problemCategory);
        map.put("localTypeName", problemCategoryText);
        map.put("dangerWhere", problemLocation);
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map.put("createMan", (String)staffByUserId.get("name"));
        map.put("createNo", (String)staffByUserId.get("workNo"));
        map.put("requiredTime", par);
        map.put("dangerLevel", problemlevel);
        map.put("requiredesc", requiredesc);
        map.put("contentDesc", problemDesciption);
        map.put("dangerCode", dangerCode);
        map.put("statusCode", "00");
        map.put("dataGroupCode", dataGroupCode);
        //保存问题信息
        dao.insert("PRGL01.insert", map);

        //获取图片参数
        Object object = inInfo.get("picList");
        List<Map<String,String>> picList = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
        Map<String, String> mapPic = new HashMap<>();
        //图片id
        String picId = UUID.randomUUID().toString();
        mapPic.put("picId",picId);
        //遍历保存图片
        for (Map<String,String> picMap : picList){
            //获取图片路径
            String storagePath = picMap.get("path");
            //获取图片名称
            String fileName = storagePath.substring(storagePath.lastIndexOf("/")+1);
            //构建保存参数
            mapPic.put("storagePath",storagePath);
            mapPic.put("fileName",fileName);
            dao.insert("PRGL01.insertPic",mapPic);
        }

        //3.关联问题图片信息
        //问题id关联图片
        Map param = new HashMap();
        param.put("dangerID", problemId);
        param.put("fileID", picId);
        //将图片通过id绑定问题
        dao.insert("PRGL01.picParam", param);
        
        //4.记录日志参数
        map.put("dangerID", problemId);
        map.put("rejectStatus", "00");
        map.put("rejectReason", " ");
        //保存问题日志
        dao.insert("PRGL01.logs", map);
        //5.返回状态信息
        inInfo.setStatus(1);
        inInfo.setMsg("操作成功");
        return inInfo;
    }


    /**
     * PC端登记任务工单上传图片时回显
     *
     * <p>获取页面参数,将参数中的图片路径，转换成图片base64码，然后返回页面</p>
     *
     * @Title: showTempPic
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param info
     * 		picList：图片信息集合
     * 			path ： 图片保存路径
     * @param:  @return
     * @return: EiInfo
     * 		 base64 ： 图片base64码
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo showTempPic(EiInfo info) {
        //获取图片参数
        Object object = info.get("picList");
        List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
        //循环转换图片base64
        list.forEach(map ->{
            map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
        });
        //返回图片信息list
        info.set("list", list);
        return info;
    }



    /**
     * 
     * 时间转换
     * param: 时间参数
     * 
     * @Title: time 
     * @return: format
     */
    public String time(String param) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = df.parse(param);
        } catch (ParseException e) {
             e.printStackTrace();
        }
        //加一天
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(parse);
        calendar.add(calendar.DATE,1);
        Date time =calendar.getTime();
        String format = df.format(time);
        
        return format;
    }
    
    
}
