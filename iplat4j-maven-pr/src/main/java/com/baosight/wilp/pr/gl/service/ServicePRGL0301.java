package com.baosight.wilp.pr.gl.service;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.pr.gl.domain.PRGL0201;
import com.baosight.wilp.pr.gl.domain.PRGL0301;
import com.baosight.xservices.xs.util.UserSession;


/**
 *
 * 整改确认审核:页面初始化加载,PC端整后图片回显方法,整改驳回,整改同意
 * <p>1.initLoad    页面初始化加载
 * <p>2.showTempPic PC端整后图片回显方法
 * <p>3.update2     整改驳回
 * <p>4.update3     整改同意
 *
 * @Title: ServicePRGL0301.java
 * @ClassName: ServicePRGL0301
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:24:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL0301 extends ServiceBase{




    /**
     *
     * 页面初始化加载方法
     *
     * @param inInfo
     * <p>1.id      问题id
     * @return
     * <p>1.dangerWhere     问题地点
     * <p>2.paramValue1     问题类型
     * <p>3.requiredTime    整改时间
     * <p>4.contentdesc     内容描述
     * <p>5.requireDesc     整改要求
     * <p>6.writeman        整改人
     * <p>7.finishtime      完成时间
     * <p>8.contentdesc2    内容描述2
     * <p>9.id              问题id
     * 代码逻辑：
     * 1.获取id
     * 2.判断id是否为空
     * 3.查询整改信息
     * 4.绑定参数并返回前端
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //1.获取id
        String id = (String)inInfo.get("id");
        //2.判断id是否为空
        if(id.isEmpty()){
            inInfo.setStatus(-1);
            inInfo.setMsg("系统参数错误，请联系管理员");
            return inInfo;
        }
        //3.查询整改信息
        List<PRGL0301> params = dao.query("PRGL0301.query", id);
        if(params.isEmpty()){
            return inInfo;
        }

        //4.绑定参数并返回前端
        PRGL0301 prgl0301 = params.get(0);
        inInfo.set("dangerclassfullname", prgl0301.getDangerclassfullname());
        inInfo.set("dangerWhere", prgl0301.getDangerWhere());
        inInfo.set("paramValue1", prgl0301.getParamValue1());
        inInfo.set("requiredTime", prgl0301.getRequiredTime());
        inInfo.set("contentdesc", prgl0301.getContentdesc());
        inInfo.set("requireDesc", prgl0301.getRequireDesc());
        inInfo.set("writeman", prgl0301.getWriteman());
        inInfo.set("finishtime", prgl0301.getFinishtime());
        inInfo.set("contentdesc2", prgl0301.getContentdesc2());
        inInfo.set("id", prgl0301.getId());
        inInfo.set("dangerid", prgl0301.getDangerid());
        return inInfo;
    }



    /**
     * PC端整后图片回显方法
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
    public EiInfo showTempPic(EiInfo inInfo) {
        String id = (String) inInfo.get("id");
        List<PRGL0201> query = dao.query("PRGL0301.afterQueryPic", id);
        List<String> list =  new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        if(!query.isEmpty()){
            for (PRGL0201 PRGL0201 : query) {
                list.add(CommonUtils.imageToBase64Str(PRGL0201.getStoragePath()));
            }
        }
        inInfo.set("list", list);
        return inInfo;
    }


    /**
     *
     * 整改驳回
     *
     * @Title: update2
     * @param inInfo    
     * <p>1.id          整改id
     * <p>2.dangerid    问题id
     * <p>3.opinion     整改评价
     * 代码逻辑：
     * 1.获取参数
     * 2.参数校验
     * 3.构建日志参数
     * 4.查询并删除图片信息
     * 5.删除整改信息
     * 6.返回状态信息
     * @return  
     * @return: EiInfo
     */
    public EiInfo update2(EiInfo inInfo) {
        //1.获取参数
        //整改id
        String id = (String)inInfo.get("id");
        //问题id
        String dangerid = (String)inInfo.get("dangerid");
        //整改评价
        String opinion = (String)inInfo.get("opinion");
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //2.参数校验
        if(dangerid.isEmpty() || id.isEmpty()){
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }
        //3.构建日志参数
        Map map2 = new HashMap();
        map2.put("dangerID", dangerid);
        map2.put("logsMan", (String)staffByUserId.get("name"));
        map2.put("logsNo", (String)staffByUserId.get("workNo"));
        map2.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map2.put("rejectStatus", "01");
        map2.put("rejectReason", " ");
        map2.put("dataGroupCode", dataGroupCode);
        map2.put("rejectReason", opinion);
        //保存问题日志
        dao.insert("PRGL0301.logs", map2);
        //修改问题状态
        dao.update("PRGL0301.turnDownAgree", dangerid);

        //4.查询并删除图片信息
        //查询图片信息
        List<PRGL0201> query = dao.query("PRGL0301.afterQueryPic", dangerid);
        System.out.println(query);
        //参数非空校验
        if(!query.isEmpty()) {
            PRGL0201 prgl0201 = query.get(0);
            String fileID = prgl0201.getId();
            //删除图片表信息
            dao.delete("PRGL0301.deleteCommonPic", fileID);
            //删除整改图片关联表信息
            dao.delete("PRGL0301.deleteResultPic", fileID);
            //删除图片
            for (PRGL0201 pr0201 : query) {
                String storagePath = pr0201.getStoragePath();
                File file = new File(storagePath);
                //判断文件是否存在
                if (file.exists() == true){
                    file.delete();
                }
            }

        }
        //5.删除整改信息
        dao.delete("PRGL0301.deleteDangerResult", id);
        //6.返回状态信息
        inInfo.setMsg("驳回成功");
        inInfo.setStatus(1);
        return inInfo;
    }








    /**
     *
     * 整改同意
     *
     * @Title: update3
     * @param inInfo
     * <p>1.id          整改id
     * <p>2.dangerid    问题id
     * <p>3.opinion     整改评价
     * 代码逻辑：
     * 1.获取参数
     * 2.修改问题状态
     * 3.构建日志参数
     * 4.返回状态信息
     * @return
     * @return: EiInfo
     */
    public EiInfo update3(EiInfo inInfo) {
        //1.获取参数
        //整改id
        String id = (String)inInfo.get("id");
        //问题id
        String dangerid = (String)inInfo.get("dangerid");
        //整改评价
        String opinion = (String)inInfo.get("opinion");
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //2.修改问题状态
        Map map = new HashMap();
        map.put("opinion", opinion);
        map.put("dangerresultid", id);
        int update = dao.update("PRGL0301.agree", dangerid);
        dao.insert("PRGL0301.evaluation", map);
        //校验返回参数
        if(0 == update) {
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }

        //3.构建日志参数
        Map map2 = new HashMap();
        map2.put("dangerID", dangerid);
        map2.put("logsMan", (String)staffByUserId.get("name"));
        map2.put("logsNo", (String)staffByUserId.get("workNo"));
        map2.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map2.put("rejectStatus", "99");
        map2.put("rejectReason", " ");
        map2.put("dataGroupCode", dataGroupCode);
        //保存问题日志
        dao.insert("PRGL0301.logs", map2);
        //4.返回状态信息
        inInfo.setMsg("整改已同意");
        inInfo.setStatus(1);
        return inInfo;
    }
}
