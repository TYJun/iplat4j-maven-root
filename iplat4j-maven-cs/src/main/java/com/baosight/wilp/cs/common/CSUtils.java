package com.baosight.wilp.cs.common;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 保洁模块的工具类 接口（本地服务）的调用
 * 一、接口（本地服务）调用.
 * 二、获取指定用户的信息.
 * 三、获取指定用户的角色编码.
 * 四、调用本地服务CSRE01.getUserRole获取角色信息.
 * 五、生成单号.
 * 六、生成指定格式的字符串（yyMMdd）.
 * 七、空处理.
 * 
 * @Title: CSUtils.java
 * @ClassName: CSUtils
 * @Package：com.baosight.wilp.cs.common
 * @author: fangzekai
 * @date: 2021年11月18日 上午9:19:26
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class CSUtils {
    
    private static final Lock lock = new ReentrantLock();

    private static String defaultBlankValue = "";

    private static SimpleDateFormat dayFormat = new SimpleDateFormat("YYYY-MM-dd");

    /**
     * 一、接口（本地服务）调用.
     *
     * @Title: invoke
     * @param:  @param param：可以为string、map、EiInfo
     * @param:  @param serviceName：服务名
     * @param:  @param methodName：方法名称
     * @param:  @return
     * @return: EiInfo  ： 调用服务的返回结果
     * @throws
     */
    public static EiInfo invoke (Object param, String serviceName, String methodName) {
        EiInfo inInfo = new EiInfo();
        if(param instanceof EiInfo) {
            inInfo = (EiInfo) param;
        } else if (param instanceof String) {
            inInfo.set((String) param, param);
        } else if (param instanceof Map) {
            for (Object key : ((Map) param).keySet()) {
                inInfo.set((String)key, ((Map) param).get(key));
            }
        }
        inInfo.set(EiConstant.serviceName, serviceName);
        inInfo.set(EiConstant.methodName, methodName);
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }
    

    /**
     * 二、获取指定用户的信息.
     *
     * @Title: getUserInfo
     * @param workNo
     * @return
     * @return: Map<String,Object>
     */
    public static Map<String, Object> getUserInfo(String workNo) {
        // 获取用户
        workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
        Map<String, Object> map = null;
        // 调用微服务接口S_AC_FW_02获取指定人员 的信息
        map = CsBaseDockingUtils.queryPersonnelByWorkNo(workNo);
        map.put("dataGroupCode", CsBaseDockingUtils.getDatagroupCode(workNo));
        // 获取人员的角色信息
        String roles = getUserRole(workNo);
        if(map !=null){
            map.put("role", roles);
        }
        return map;
    }
    
    
    
    /**
     * 三、获取指定用户的角色编码.
     *
     * @Title: getUserRole
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: String  ： 角色编码字符串
     * @throws
     */
     public static String getUserRole(String workNo) {
         Object object = getRole(workNo);
         if(object == null){
             return "";
         } else {
             List<Map<String, String>> list = (List<Map<String, String>>) object;
             Map<String, String> map = list.get(0);
             return map.get("roles");
         }
     }
     
     
     /**
      * 四、调用本地服务CSRE01.getUserRole获取角色信息.
      * 
      * @Title: getRole
      * @Description: 获取指定用户的角色信息
      * @param:  @param workNo : 工号
      * @param:  @return
      * @return: Object  ： 角色
      * @throws
      */
     private static Object getRole(String workNo) {
         String dataGroupCode = "";
         workNo =StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
         EiInfo eiInfo = new EiInfo("role");
         dataGroupCode = getDataGroupCode(workNo);
         //调用本地服务CSRE01.getUserRole获取角色信息
         eiInfo.set("dataGroupCode", dataGroupCode);
         eiInfo.set("workNo", workNo);
         EiInfo outInfo = invoke(eiInfo,"CSRE01","getUserRole");
         Object object = outInfo.get("role");
         return object;
     }

    /**
     * 获取账套（院区）
     *
     * @Title: getDataGroupCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: String ： 账套（院区）
     * @throws
     */
    public static String getDataGroupCode(String workNo) {
        //PC端,获取当前登录人的账套(院区)
        if(StringUtils.isBlank(workNo)){
            return com.baosight.wilp.utils.DatagroupUtil.getDatagroupCode();
        }
        //APP端或非当前登录人
        List<Map<String, String>> list = getDataGroups(workNo);
        if(list == null || list.size() == 0){
            return "";
        }
        //只有一个账套信息，直接返回
        if(list.size() == 1){
            return list.get(0).get("orgEname");
        }
        //查询该账号最近一次的登录账套
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<String> queryList = dao.query("CSRE01.queryLastDataGroupCode", workNo);
        return queryList == null || queryList.size() == 0 ? "" : queryList.get(0);
    }

    /**
     * 获取账套（院区）
     *
     * @Title: getDataGroups
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: List<Map<String, String>> ： 账套（院区）信息集合
     *     groupType : 用户组类型
     *     groupEname : 用户组编码
     *     groupCname : 用户组名称
     *     orgCname : 账套名称
     *     orgEname : 账套编码
     * @throws
     */
    private static List<Map<String, String>> getDataGroups(String workNo) {
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("loginName",workNo);
        eiInfo.set(EiConstant.serviceId,"S_XS_70");
        EiInfo outInfo = XServiceManager.call(eiInfo);
        Object object = outInfo.get("result");
        if(object != null){
            return (List<Map<String, String>>) object;
        }
        return null;
    }
     
     
     /**
      * 五、生成单号.
      * 
      *  1、查询cs_model_number表中的单号.
      *  2、判断工单号是否存在，不存在，构建一个初始工单号.
      *  3、存在,截取工单号的数字部分，然后加1.
      *  4、重新拼接，返回.
      * 
      * @Title: generationSerialNo
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param:  @param type ： 单号在cs_model_number表中的标识符
      * @param:  @param prefix ： 前缀
      * @param:  @return
      * @return: String  ： 单号
      * @throws
      */
     public static String generationSerialNo(String module, String prefix, String way){
         lock.lock();
         String serialNo = "";
         String op = "update";
         try {
             /*
              * 1、查询cs_model_number表中的单号.
              */
             //查询cs_model_number表中的序号
             EiInfo eiInfo = new EiInfo();
             eiInfo.set("type", module);
             EiInfo outInfo = invoke(eiInfo, "CSRE01", "querySerialNo");
             String lastSerialNo = outInfo.getString("serialNo");
             /*
              * 2、判断工单号是否存在，不存在，构建一个初始工单号.
              */
             //判断工单号是否存在，不存在，构建一个初始工单号
             String head = createTop();
             if (StringUtils.isBlank(lastSerialNo)) {
                 serialNo = prefix + ("0".equals(way) ? head + "001" : "000001");
                 op = "insert";
             } else {
                 /*
                  * 3、存在,截取工单号的数字部分，然后加1.
                  */
                 //存在,截取单号的除固定部分之外的数字部分，然后加1
                 int num = Integer.parseInt(lastSerialNo.replace(prefix, "").replace(head, "")) + 1;
                 /*
                  * 4、重新拼接，返回.
                  */
                 //判断生成方式，0=每天都从001开始生成，1= 直接累加
                 if("0".equals(way)){
                     if(!head.equals(lastSerialNo.replace(prefix, "").substring(0, 6))){
                         serialNo = prefix + head + "001";
                     } else {
                         //重新拼接，返回
                         String remianNo = num > 999 ? String.valueOf(num) : String.valueOf(num+1000).substring(1);
                         serialNo = prefix + head + remianNo;
                     }
                 } else {
                     serialNo = prefix + String.valueOf(num+1000000).substring(1);
                 }
             }
             //更新单号
             eiInfo.set("serialNo", serialNo);
             eiInfo.set("op", op);
             invoke(eiInfo, "CSRE01", "updateSerialNo");
         } finally {
             lock.unlock();
         }
         return serialNo;
     }

     /**
      * 六、生成指定格式的字符串（yyMMdd）.
      *
      * @Title: createTop
      * @param:  @return
      * @return: String  
      * @throws
      */
     public static String createTop() {
         SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
         String head = sdf.format(new Date());
         return head;
     }


    /**
     * 七、空处理.
     *
     * @Title: isEmpty
     * @param:  @param object ： 参数
     * @param:  @return
     * @return: String  ： 参数字符串
     * @throws
     */
    public static String isEmpty(Object object) {
        if(object == null){
            return "";
        }
        if(StringUtils.isBlank(object.toString())){
            return "";
        }
        return object.toString();
    }

    /**
     * 根据附件id获取附件路径
     * @Title: getFilePath
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param docId docId ： 附件id
     * @return String ： 附件路径
     * @throws
     **/
    public static String getFilePath(String docId) {
        //获取文件管理器
        PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
        //获取附件信息
        Map<String,String> docMap = fileUpLoadManager.getDocById(docId);
        //获取路径
        if(docMap == null || docMap.isEmpty()){
            return "";
        } else {
            return docMap.get("realPath") + docMap.get("chgName");
        }
    }


    /**
     * 空处理
     * @Title: isEmpty
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param object ： 参数
     * @param:  @param defultValue ： 默认值
     * @param:  @return
     * @return: String  ： 参数字符串
     * @throws
     */
    public static String isEmpty(Object object, String defultValue) {
        if(StringUtils.isNotBlank(defultValue)){
            defaultBlankValue = defultValue;
        }
        if(object == null){
            return defaultBlankValue;
        }
        if(StringUtils.isBlank(object.toString())){
            return defaultBlankValue;
        }
        return object.toString();
    }

}
