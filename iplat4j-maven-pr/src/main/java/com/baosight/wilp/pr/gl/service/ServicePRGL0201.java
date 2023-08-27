package com.baosight.wilp.pr.gl.service;

import java.text.SimpleDateFormat;
import java.util.*;


import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.pr.TimeUtil;
import com.baosight.wilp.pr.gl.domain.PRGL0201;



/**
 *
 * 问题处理：
 * <p>1.initLoad                页面初始化
 * <p>2.showTempPic             PC端整前图片回显方法
 * <p>3.clshowAfterTempPicaim   PC端整改后图片回显方法
 * <p>4.insert                  整改问题保存方法
 *
 * @Title: ServicePRGL0201.java
 * @ClassName: ServicePRGL0201
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:00:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL0201 extends ServiceBase{


    /**
     *
     * 页面初始化方法
     *
     * @param inInfo
     * <p>1.id              问题id
     * @return
     * <p>1.id              整改问题id
     * <p>2.dangerwhere     问题地点
     * <p>3.contentdesc     描述
     * <p>4.dangerlevel     问题等级
     * <p>5.requiredtime    整改时间
     * <p>6.localtypecode   问题分类编码
     * <p>6.localTypeName   问题分类名称
     * <p>7.requiredesc     整改要求
     * <p>8.dangerid        整改内容id
     * 代码逻辑：
     * 1.获取参数
     * 2.校验参数
     * 3.获取查询参数
     * 4.封装参数并返回前端
     *
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //1.获取参数
        //定义变量
        List<PRGL0201> query = null;
        //获取问题id
        Object obj = inInfo.get("id");
        //2.校验参数
        if(null == obj) {
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }else {
            //通过校验后用id查询问题信息
            String id = obj.toString();
            //idParam = id;
            Map map = new HashMap();
            map.put("id", id);
            query = dao.query("PRGL0201.query", map);
        }
        if(null == query || query.size() == 0) {
            return inInfo;
        }
        //3.获取查询参数
        PRGL0201 prgl0201 = query.get(0);

        //获取result2模块展示整改前图片
        EiBlock addBlock = inInfo.addBlock("result2");
        addBlock.addBlockMeta(new PRGL0201().eiMetadata);
        inInfo.getBlock("result2").setRows(query);

        //4.封装参数并返回前端
        inInfo.set("id",prgl0201.getId());
        inInfo.set("localtypecode",prgl0201.getLocaltypecode());
        inInfo.set("dangerwhere", prgl0201.getDangerwhere());
        inInfo.set("localTypeName", prgl0201.getLocalTypeName());
        inInfo.set("contentdesc", prgl0201.getContentdesc());
        inInfo.set("requiredtime", prgl0201.getRequiredtime());
        inInfo.set("requiredesc", prgl0201.getRequiredesc());

        return inInfo;
    }

    /**
     * PC端整前图片回显方法
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
        List<PRGL0201> query = dao.query("PRGL0201.queryPic", id);
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
     * PC端整改后图片回显方法
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
    public EiInfo showAfterTempPic(EiInfo info) {
        Object object = info.get("picList");
        List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
        list.forEach(map ->{
            map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
        });
        info.set("list", list);
        return info;
    }

    /**
     *
     * 整改问题保存方法
     *
     * @param inInfo
     * <p>1.id              问题id
     * <p>2.localtypecode   分类编码
     * <p>3.localTypeName   分类名称
     * <p>4.writeman        整改人
     * <p>5.time            整改时间
     * <p>6.contentdesc     整改内容
     * <p>7.picList         整改图片
     * 代码逻辑：
     * 1.获取参数
     * 2.遍历保存图片
     * 3.构建整改信息
     * 4.关联整改后图片
     * 5.修改问题表问题状态
     * 6.记录问题日志
     * 7.返回信息
     * @return
     * inInfo
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        //1.获取参数
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取当前问题id
        String id = (String)inInfo.get("id");
        //获取当前问题编码
        String localtypecode = (String)inInfo.get("localtypecode");
        //获取当前问题类型
        String localTypeName = (String)inInfo.get("localTypeName");
        //整改执行人
        String writeman = (String)inInfo.get("writeman");
        //完成时间
        String time = (String)inInfo.get("time");
        //整改实绩
        String contentdesc = (String)inInfo.get("contentdesc");
        //转换时间格式
        String timeYMD = TimeUtil.timeYMD(time);
        //问题修改时间
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();

        //获取图片参数
        Object object = inInfo.get("picList");
        List<Map<String,String>> picList = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
        Map<String, String> mapPic = new HashMap<>();
        //图片id
        String picId = UUID.randomUUID().toString();
        mapPic.put("picId",picId);
        //2.遍历保存图片
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

        //整改问题id
        String zgID = UUID.randomUUID().toString();

        //3.构建整改信息
        PRGL0201 param = new PRGL0201();
        param.setId(zgID);
        param.setDangerid(id);
        param.setContentdesc(contentdesc);
        param.setFinishTime(timeYMD);
        param.setWriteMan(writeman);
        param.setWriteTime(updateTime);
        param.setLocaltypecode(localtypecode);
        param.setLocalTypeName(localTypeName);
        //保存整改问题
        dao.insert("PRGL0201.insert", param);

        //4.关联整改后图片
        Map paramPic = new HashMap();
        paramPic.put("dangerResultID", zgID);
        paramPic.put("fileID", picId);
        dao.insert("PRGL0201.picParam", paramPic);

        //5.修改问题表问题状态
        Map map = new HashMap();
        map.put("statusCode", "30");
        map.put("id", id);
        //修改问题状态
        int update = dao.update("PRGL0201.updateStatusCode", map);

        //6.记录问题日志
        Map log = new HashMap();
        log.put("dangerID", id);
        log.put("logsTime", updateTime);
        log.put("logsMan", (String)staffByUserId.get("name"));
        log.put("logsNo", (String)staffByUserId.get("workNo"));
        log.put("rejectStatus", "30");
        log.put("rejectReason", " ");
        log.put("dataGroupCode", dataGroupCode);
        dao.insert("PRGL0201.logs", log);
        //7.返回信息
        return inInfo;
    }
}
