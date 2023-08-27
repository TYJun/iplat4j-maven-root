package com.baosight.wilp.pr.gl.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.pr.gl.domain.PRGL02;
import com.baosight.xservices.xs.util.UserSession;


/**
 *
 * 问题池逻辑处理:页面初始化,查询方法,问题认领,问题撤销
 * <p>1.initLoad 页面初始化
 * <p>2.query    查询方法
 * <p>3.claim    问题认领
 * <p>4.reject   问题撤销
 *
 * @Title: ServicePRGL02.java
 * @ClassName: ServicePRGL02
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午3:22:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL02 extends ServiceBase{



    /**
     *
     * 页面初始化方法
     *
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }



    /**
     *
     * 查询方法
     * <p>1.id          问题id
     * <p>2.statusCode  状态编码
     *  
     * @param inInfo
     * @return
     * <p>1.contentType 流程状态
     * <p>2.statusCode  问题状态
     * <p>3.id          问题id
     * <p>4.dangercode  问题编码
     * <p>5.typename    分类名称
     * <p>6.contentdesc 备注
     * <p>7.creatorName 创建人
     * <p>8.createtime  创建时间
     * <p>9.requiredtime整改时间
     * <p>10.requiredesc整改要求
     * <p>11.rejectReason备注
     * <p>12.dangerlevel问题等级
     * 代码逻辑:
     * 1.查询问题信息
     * 2.通过id绑定返回参数
     *
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //1.查询问题信息
        EiInfo outInfo = super.query(inInfo,"PRGL02.query",new PRGL02());
        Map blocks = outInfo.getBlocks();
        EiBlock eiBlock = (EiBlock)blocks.get("result");
        List<Map<String,String>> rows = eiBlock.getRows();
        Map<String,String> map2 = new HashMap();
        //2.通过id绑定返回参数
        for (Map<String, String> map : rows) {
            String dangerid = map.get("id");
            String statusCode = map.get("statusCode");
            map2.put("dangerID", dangerid);
            map2.put("statusCode", statusCode);
            //查询备注信息
            List<Map<String,String>> query = dao.query("PRGL02.queryLog", map2);
            //绑定备注信息
            for (Map<String, String> map3 : query) {
                String rejectReason = map3.get("rejectReason");
                map.put("rejectReason", rejectReason);
            }
        }
        return outInfo;
    }




    /**
     *
     * 问题认领
     *
     * @Title: claim
     * @param inInfo
     * <p>1.id          问题id
     * 代码逻辑：
     * 1.获取参数
     * 2.修改当前问题状态
     * 3.构建日志参数
     * 4.判断操作状态并返回操作信息
     * @return: inInfo
     */
    public EiInfo claim(EiInfo inInfo) {
        //1.获取参数
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        String id  = (String)inInfo.get("id");
        //构建状态参数
        Map stat = new HashMap();
        stat.put("id", id);
        stat.put("statusCode", "10");
        //2.修改当前问题状态
        int update = dao.update("PRGL02.updateStatusCode", stat);
        //3.构建日志参数
        Map map = new HashMap();
        map.put("dangerID", id);
        map.put("logsMan", (String)staffByUserId.get("name"));
        map.put("logsNo", (String)staffByUserId.get("workNo"));
        map.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map.put("rejectStatus", "10");
        map.put("rejectReason", " ");
        map.put("dataGroupCode", dataGroupCode);

        try{
            //保存问题日志
            dao.insert("PRGL02.logs", map);
        }catch (Exception e){
            inInfo.setStatus(-1);
            inInfo.setMsg("系数据异常，请联系管理员");
            return inInfo;
        }
        //4.判断操作状态并返回操作信息
        inInfo.setStatus(1);
        inInfo.setMsg("操作成功");
        return inInfo;
    }


    /**
     *
     * 问题撤销
     *
     * @Title: reject
     * @param inInfo
     * <p>1.id          问题id
     * 代码逻辑:
     * 1.获取参数
     * 2.查询相关参数
     * 3.构建返回信息
     * @return: EiInfo
     */
    public EiInfo reject(EiInfo inInfo) {
        //1.获取参数
        String id = (String)inInfo.get("id");
        //查询common_file中图片信息
        List<Map<String,String>> query = dao.query("PRGL02.queryFileID", id);
        //2.查询相关参数
        try{
            //删除问题信息
            dao.delete("PRGL02.deleteDanger", id);
            //删除日志信息
            dao.delete("PRGL02.deleteDangerLogs", id);
            //删除关联图片信息
            dao.delete("PRGL02.deleteDangerAndPic", id);
            //删除图片表对应图片
            if(!query.isEmpty()){
                Map<String,String> map = query.get(0);
                String fileID = map.get("fileID");
                dao.delete("PRGL02.deleteDangerPic", fileID);
                for (Map<String,String> map2 :query) {
                    String storagePath = map2.get("storagePath");
                    //删除图片
                    File file = new File(storagePath);
                    //判断文件是否存在
                    if (file.exists() == true){
                        file.delete();
                    }
                }
            }
        }catch (Exception e){
            inInfo.setStatus(-1);
            inInfo.setMsg("系数据异常，请联系管理员");
            return inInfo;
        }
        //3.构建返回信息
        inInfo.setStatus(1);
        inInfo.setMsg("撤销成功");
        return inInfo;
    }
}
