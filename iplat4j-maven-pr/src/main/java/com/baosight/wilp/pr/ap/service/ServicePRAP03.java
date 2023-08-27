package com.baosight.wilp.pr.ap.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.pr.common.CodeUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.pr.DangerCode;
import com.baosight.wilp.common.pr.FileUtil;
import com.baosight.wilp.common.pr.NullUtil;
import com.baosight.wilp.common.pr.UUIDUtils;
import com.baosight.wilp.pr.gl.domain.PRGL0201;


/**
 * 
 * 问题认领,问题整改,保存问题图片方法,问题挂账,整改意见,安全问题上报,参数转换,参数转换
 * <p>1.claim问题认领
 * <p>2.insert问题整改
 * <p>3.uploadPic保存问题图片方法
 * <p>4.ins问题挂账
 * <p>5.update3整改意见
 * <p>6.saftyUp安全问题上报
 * <p>7.strParam参数转换
 * <p>8.strParam2参数转换
 * 
 * @Title: ServicePRAP03.java
 * @ClassName: ServicePRAP03
 * @Package：com.baosight.zdyyaq.pr.ap.service
 * @author: zhangjiaqian
 * @date: 2021年5月11日 下午2:41:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("unchecked")
public class ServicePRAP03 extends ServiceBase{


    /**
     * 注入dao
     */
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");


    /**
     * 
     * 问题认领
     * workNo:工号
     * id:问题id
     * statusCode:问题状态
     * 代码逻辑:
     * 1.获取参数
     * 2.参数校验
     * 3.校验问题状态是否已经被领取
     * 4.构建状态参数
     * 5.修改当前问题状态
     * 6.构建日志参数
     * 7.保存问题日志
     * 8.判断数据库返回参数并，做出对应的返回状态
     *
     * @Title: claim 
     * @return: inInfo
     */
    public EiInfo claim(EiInfo inInfo) {
        //1.获取参数
        //获取工号
        String workNo = (String)inInfo.get("workNo");
        //获取问题id
        String id = (String)inInfo.get("id");
        //获取问题状态
        String statusCode = (String)inInfo.get("statusCode");
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();

        //2.参数校验
        int orNull = NullUtil.orNull(workNo,id,statusCode);
        if(-1 == orNull) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("系统错误请联系管理员");
            return inInfo;
        }

        //3.校验问题状态是否已经被领取
        if("10".equals(statusCode)) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("任务已被领取，请勿重复操作");
        }else {
            //4.构建状态参数
            Map<String,String> stat = new HashMap<String,String>();
            stat.put("id", id);
            stat.put("statusCode", "10");
            //5.修改当前问题状态
            int update = dao.update("PRAP03.updateStatusCode", stat);
            //6.构建日志参数
            Map<String,String> map = new HashMap<String,String>();
            map.put("dangerID", id);
            map.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
            map.put("logsMan", (String)staffByUserId.get("name"));
            map.put("logsNo", (String)staffByUserId.get("workNo"));
            map.put("rejectStatus", "10");
            map.put("rejectReason", " ");
            map.put("dataGroupCode", dataGroupCode);
            //7.保存问题日志
            dao.insert("PRAP03.logs", map);
            //8.判断数据库返回参数并，做出对应的返回状态
            if(0 != update) {
                inInfo.setMsgKey("200");
                inInfo.setMsg("操作成功");
            }else {
                inInfo.setMsgKey("199");
                inInfo.setMsg("系统错误请联系管理员");
            }
        }
        return inInfo;
    }



    /**
     * 
     * 问题整改
     * writeman: 用户工号
     * writename: 用户姓名 
     * contentdesc: 整改评价
     * pics: 图片base64
     * localTypeCode: 问题类型
     * id: 问题id
     * 代码逻辑:
     * 1.获取参数
     * 2.参数校验
     * 3.保存图片
     * 4.构建整改信息
     * 5.关联图片信息
     * 6.判断是单张图片还是多张图片
     * 7.关联整改后图片
     * 8.记录问题日志
     * 9.返回状态信息
     *
     * @Title: insert 
     * @return: ResultData
     */
    public EiInfo insert(EiInfo inInfo) {
        //1.获取参数
        //获取文件根目录
        String docRootDir = PlatApplicationContext.getProperty("docRootDir");
        String destPath = docRootDir+ "/pr/img/";
        //获取用户工号
        String writeman = (String)inInfo.get("writeman");
        //用户姓名
        String writename = (String)inInfo.get("writename");
        //获取整改评价
        String contentdesc = (String)inInfo.get("contentdesc");
        //获取图片base64
        String pic = (String)inInfo.get("pics");
        //获取问题类型
        String localTypeName = (String)inInfo.get("localTypeCode");
        //获取问题id
        String id = (String)inInfo.get("id");
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();

        //2.参数校验
        int orNull = NullUtil.orNull(writeman,contentdesc,localTypeName);
        if(-1 == orNull) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("提交请填写完整信息");
            return inInfo;
        }
        String[] split = pic.split(",");
        ArrayList<String> strParam = strParam(split);
        
        //3.保存图片
        Map<String,Object> uploadPic = uploadPic(strParam,destPath);
        //获取图片方法返回信息
        String pa = (String)uploadPic.get("i");
        //-1表示异常，返回
        if(null != pa) {
            if("-1".equals(pa)) {
                inInfo.setMsgKey("199");
                inInfo.setMsg("图片保存失败，请联系管理员");
                return inInfo;
            }
        }

        //获取整改当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        //问题修改时间
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        //整改问题id
        String zgID = UUID.randomUUID().toString();
        //获取问题编码
        String localTypeCode = CodeUtil.code(localTypeName);

        //4.构建整改信息
        PRGL0201 param = new PRGL0201();
        param.setId(zgID);
        param.setDangerid(id);
        param.setContentdesc(contentdesc);
        param.setFinishTime(format);
        param.setWriteMan(writename);
        param.setWriteTime(updateTime);
        param.setLocaltypecode(localTypeCode);
        param.setLocalTypeName(localTypeName);

        //保存整改问题
        dao.insert("PRAP03.insert", param);

        //修改问题表问题状态
        Map<String,String> map = new HashMap<String,String>();
        map.put("statusCode", "30");
        map.put("id", id);
        //修改问题状态
        dao.update("PRAP03.updateStatusCode", map);

        //5.关联图片信息
        List<String> picIds = (List<String>)uploadPic.get("picIds");
        //图片id
        String flieId = UUID.randomUUID().toString();
        Map mapPic = new HashMap();
        //6.判断是单张图片还是多张图片
        if(picIds.size()>1) {
            //多张图片保存方法
            for (int i = 0; i < picIds.size(); i++) {
                String string = picIds.get(i);
                mapPic.put("fileName", string + ".jpg");
                mapPic.put("storagePath", destPath + string + ".jpg");
                mapPic.put("fileId",flieId);
                // 保存图片信息
                dao.insert("PRAP03.insertPic", mapPic);
            }
        }else{
            //单张图片保存方法
            Object picId = uploadPic.get("picIds");
            String strParam2 = strParam2(picId);
            mapPic.put("fileName", strParam2 + ".jpg");
            mapPic.put("storagePath", destPath + strParam2 + ".jpg");
            mapPic.put("fileId",flieId);
            // 保存图片信息
            dao.insert("PRAP03.insertPic", mapPic);
        }

        //7.关联整改后图片
        Map paramPic = new HashMap();
        paramPic.put("dangerResultID", zgID);
        paramPic.put("fileID", flieId);
        dao.insert("PRAP03.picParam", paramPic);

        //8.记录问题日志
        Map<String,String> log = new HashMap<String,String>();
        log.put("dangerID", id);
        log.put("logsTime", updateTime);
        log.put("logsMan", writename);
        log.put("logsNo", writeman);
        log.put("rejectStatus", "30");
        log.put("rejectReason", " ");
        log.put("dataGroupCode", dataGroupCode);
        dao.insert("PRAP03.logs", log);
        //9.返回状态信息
        inInfo.setMsgKey("200");
        inInfo.setMsg("保存成功");
        return inInfo;
    }



    /**
     * 
     * 保存问题图片
     * param: 图片base64
     * savaPath: 保存地址
     * 代码逻辑:
     * 1.获取参数
     * 2.保存图片
     * 3.封装参数返回
     * @Title: uploadPic 
     * @return: EiInfo
     */
    public Map<String,Object> uploadPic(ArrayList<String> param,String savaPath) {
        //1.获取参数
        //图片名称
        String picId = null;
        //方法执行状态
        String i = null;
        //返回图片idlist
        ArrayList<String> fileIdList = null;
        //2.保存图片
        try {
            //真实路径
            if(param.size()>1) {
                //保存多张base64图片
                fileIdList = FileUtil.savePic(savaPath, picId, param);
            }else {
                //保存单张base64图片
                picId = UUIDUtils.getUUID32();
                String id = picId.substring(0,8);
                fileIdList = FileUtil.savePic(savaPath, id, param);
            }
        } catch (Exception e) {
            //抛异常返回-1状态信息
            e.printStackTrace();
            i = "-1";
        }
        //3.封装参数返回
        Map map = new HashMap();
        map.put("picIds", fileIdList);
        map.put("i", i);
        return map;
    }
    
    
    
    /**
     * 
     * 问题挂账
     * currentUser: 用户工号
     * currentName: 用户姓名
     * parameter: 参数JSON
     * 代码逻辑：
     * 1.获取参数
     * 2.记录问题日志
     * 3.修改问题状态
     * 4.封装参数并返回
     *
     * @Title: ins 
     * @return: ResultData
     */
    public EiInfo ins(EiInfo inInfo) {
        //1.获取参数
        //获取工号
        String workNo = (String)inInfo.get("currentUser");
        //获取姓名
        String name = (String)inInfo.get("currentName");
        //获取参数
        String parameter = (String)inInfo.get("parameter");
        //获取参数json
        JSONObject json = JSONObject.parseObject(parameter);
        //获取问题id
        String dangerid = (String)json.get("dangerID");
        //获取挂账原因
        String rejectreason = (String)json.get("pauseMemo");
        //获取问题id
        String id = (String)json.get("id");
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();

        //2.记录问题日志
        Map<String,String> log = new HashMap<String,String>();
        log.put("dangerID", id);
        log.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        log.put("logsMan", name);
        log.put("logsNo", workNo);
        log.put("rejectStatus", "20");
        log.put("rejectReason", rejectreason);
        log.put("dataGroupCode", dataGroupCode);
        dao.insert("PRAP03.logs", log);

        //3.修改问题状态
        //构建状态参数
        Map stat = new HashMap();
        stat.put("id", id);
        stat.put("statusCode", "20");
        //修改当前问题状态
        int update = dao.update("PRAP03.updateStatusCode", stat);
        //判断状态信息
        if(1 != update) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("信息保存失败，请联系管理员");
            return inInfo;
        }
        //4.封装参数并返回
        inInfo.setMsgKey("200");
        inInfo.setMsg("保存成功");
        return inInfo;
    }
    
    
    
    /**
     * 
     * 整改意见
     * parameter: 参数JSON
     * currentUser: 用户工号
     * 代码逻辑：
     * 1.获取参数
     * 2.参数校验
     * 3.根据前端传参校验参数
     * 4.返回操作状态
     *
     * @Title: update3 
     * @return: inInfo
     */
    public EiInfo update3(EiInfo inInfo) {
        //1.获取参数
        //获取参数JSON
        String parameter = (String)inInfo.get("parameter");
        JSONObject json = JSONObject.parseObject(parameter);
        //获取评价ixnx
        String String = (String)json.get("param");
        //获取问题id
        String id = (String)json.get("dangerID");
        //获取评价信息
        String opinion = (String)json.get("memo");
        //获取整改信息id
        String dangerResultId = (String)json.get("dangerResultID");
        //用户名
        String currentUser = (String)inInfo.get("currentUser");
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(currentUser);
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //2.参数校验
        if(dangerResultId.isEmpty() || id.isEmpty()){
            inInfo.setMsgKey("199");
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }

        //3.根据前端传参校验参数
        //2代表整改被确认
        if("2".equals(String)) {
            //保存整改确认信息
            Map<String,String> map = new HashMap<String,String>();
            map.put("opinion", opinion);
            map.put("dangerresultid", dangerResultId);
            //修改问题表问题状态
            int update = dao.update("PRAP03.agree", id);
            //保存整改信息到整改表
            dao.insert("PRAP03.evaluation", map);
            //判断操作状态并返回
            if(0 == update) {
                inInfo.setMsgKey("199");
                inInfo.setMsg("系统错误，请联系管理员");
                return inInfo;
            }
            
            //记录问题日志
            Map<String,String> log = new HashMap<String,String>();
            log.put("dangerID", id);
            log.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
            log.put("logsMan", (String)staffByUserId.get("name"));
            log.put("logsNo", currentUser);
            log.put("rejectStatus", "99");
            log.put("rejectReason", " ");
            log.put("dataGroupCode", dataGroupCode);
            dao.insert("PRAP03.logs", log);
        }else {
            //整改被驳回
            //构建日志参数
            Map map2 = new HashMap();
            map2.put("dangerID", id);
            map2.put("logsMan", (String)staffByUserId.get("name"));
            map2.put("logsNo", currentUser);
            map2.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
            map2.put("rejectStatus", "01");
            map2.put("rejectReason", " ");
            map2.put("dataGroupCode", dataGroupCode);
            map2.put("rejectReason", opinion);
            //保存问题日志
            dao.insert("PRAP03.logs", map2);
            //修改问题状态
            dao.update("PRAP03.turnDownAgree", id);

            //查询图片信息
            List<PRGL0201> query = dao.query("PRAP03.afterQueryPic", id);
            //参数非空校验
            if(!query.isEmpty()) {
                PRGL0201 prgl0201 = query.get(0);
                String fileID = prgl0201.getId();
                //删除图片表信息
                dao.delete("PRAP03.deleteCommonPic", fileID);
                //删除整改图片关联表信息
                dao.delete("PRAP03.deleteResultPic", fileID);
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
            //删除整改信息
            dao.delete("PRAP03.deleteDangerResult", dangerResultId);
        }
        //4.返回操作状态
        inInfo.setMsgKey("200");
        inInfo.setMsg("操作成功");
        return inInfo;
    }
    
    
    
    /**
     * 
     * 安全问题上报
     * problemCategory: 问题类别
     * problemLocation: 问题地点
     * problemlevel: 问题等级
     * problemDesciption: 问题描述
     * requiredesc: 整改要求
     * pics: 图片base64
     * workNo: 工号
     * time: 整改时间
     * 代码逻辑：
     * 1.获取基本信息
     * 2.参数校验
     * 3.处理图片
     * 4.保存问题信息
     * 5.关联图片信息
     * 6.关联问题信息
     * 7.记录问题日志
     * 8.构建返回状态信息
     *
     * @Title: insert 
     * @return 
     * @return: inInfo
     */
    public EiInfo saftyUp(EiInfo inInfo) {

        //1.获取基本信息
        //问题类别
        String problemCategory = (String)inInfo.get("problemCategory");
        //问题地点
        String problemLocation = (String)inInfo.get("problemLocation");
        //问题等级
        String problemlevel = (String)inInfo.get("problemlevel");
        //问题描述
        String problemDesciption = (String)inInfo.get("problemDesciption");
        //整改要求
        String requiredesc = (String)inInfo.get("requiredesc");
        //图片
        String pics = (String)inInfo.get("pics");
        //工号
        String workNo = (String)inInfo.get("workNo");
        //整改时间
        String time = (String)inInfo.get("time");
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //图片保存地址
        String docRootDir = PlatApplicationContext.getProperty("docRootDir");
        String destPath = docRootDir+ "/pr/img/";

        //2.参数校验
        int orNull = NullUtil.orNull(problemCategory,problemLocation,problemlevel,problemDesciption,requiredesc);
        if(-1 == orNull) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("提交请填写完整信息");
            return inInfo;
        }

        //3.处理图片
        String[] split = pics.split(",");
        ArrayList<String> strParam = strParam(split);
        
        //保存图片
        Map<String,Object> uploadPic = uploadPic(strParam,destPath);
        //获取保存图片返回参数
        String pa = (String)uploadPic.get("i");
        //校验返回参数，并返回参数信息
        if(null != pa) {
            if("-1".equals(pa)) {
                inInfo.setMsgKey("199");
                inInfo.setMsg("图片保存失败，请联系管理员");
                return inInfo;
            }
            
        }
        //4.保存问题信息
        //生成问题编号
        String dangerCode = DangerCode.dangerCode();
        //生成问题id
        String problemId = UUID.randomUUID().toString();
        //获取问题类型
        String problemCategoryText = CodeUtil.code(problemCategory);
        Map<String, String> map = new HashMap<>();
        map.put("id", problemId);
        map.put("localTypeCode", problemCategory);
        map.put("localTypeName", problemCategoryText);
        map.put("dangerWhere", problemLocation);
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map.put("createMan", (String)staffByUserId.get("name"));
        map.put("createNo", workNo);
        map.put("requiredTime", time);
        map.put("dangerLevel", problemlevel);
        map.put("requiredesc", requiredesc);
        map.put("contentDesc", problemDesciption);
        map.put("dangerCode", dangerCode);
        map.put("statusCode", "00");
        map.put("dataGroupCode", dataGroupCode);
        //保存问题信息
        dao.insert("PRAP03.insertSafty", map);

        //5.关联图片信息
        List<String> picIds = (List<String>)uploadPic.get("picIds");
        //生成图片flieId
        String flieId = UUID.randomUUID().toString();
        Map mapPic = new HashMap();
        //保存图片信息到图片表
        if(picIds.size()>1) {
            //多张图片保存到图片表
            for (int i = 0; i < picIds.size(); i++) {
                String string = picIds.get(i);
                mapPic.put("fileName", string + ".jpg");
                mapPic.put("storagePath", destPath + string + ".jpg");
                mapPic.put("fileId",flieId);
                // 保存图片信息
                dao.insert("PRAP03.insertPic", mapPic);
            }
        }else{
            //单张图片保存到图片表
            Object picId = uploadPic.get("picIds");
            String strParam2 = strParam2(picId);
            mapPic.put("fileName", strParam2 + ".jpg");
            mapPic.put("storagePath", destPath + strParam2 + ".jpg");
            mapPic.put("fileId",flieId);
            // 保存图片信息
            dao.insert("PRAP03.insertPic", mapPic);
        }

        //6.关联问题信息
        //问题id关联图片
        Map param = new HashMap();
        //文件dangerID
        param.put("dangerID", problemId);
        //图片fileID
        param.put("fileID", flieId);
        //图片flieId和问题dangerID关联在图片信息关联表中
        dao.insert("PRAP03.picParamDanger", param);

        //7.记录问题日志
        Map<String,String> log = new HashMap<String,String>();
        log.put("dangerID", problemId);
        log.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        log.put("logsMan", (String)staffByUserId.get("name"));
        log.put("logsNo", workNo);
        log.put("rejectStatus", "00");
        log.put("rejectReason", " ");
        log.put("dataGroupCode", dataGroupCode);
        dao.insert("PRAP03.logs", log);

        //8.构建返回状态信息
        inInfo.setMsgKey("200");
        inInfo.setMsg("操作成功");
        return inInfo;
    }
    
    
    
    /**
     * 
     * 参数转换
     * param: String字符串
     * 
     * @Title: strParam 
     * @param param
     * @return: par
     */
    public static ArrayList<String> strParam(String[] param) {
        ArrayList<String> par = new ArrayList<String>();
        //过滤字符串中多余符号
        for (int i = 0; i < param.length; i++) {
            String string = param[i];
                String str = string.replace("[\"", "");
                String str2 = str.replace("\"]","");
                String str3 = str2.replace("\"","");
                par.add(str3);
        }
        return par;
    }
    


    /**
     * 
     * 参数转换
     * param: String字符串
     * 
     * @Title: strParam 
     * @param param
     * @return: replace2
     */
    public static String strParam2(Object param) {
        //截取String字符串首尾多余字符
        String string = param.toString();
        String replace = string.replace("[", "");
        String replace2 = replace.replace("]", "");
        return replace2;
    }
}
