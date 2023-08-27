package com.baosight.wilp.cp.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CPUtils {
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    private static SimpleDateFormat nationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static String defaultBlankValue = "";


    /**
     * List转EiBlock
     * @Title: ObjectToBlock
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param data
     * @param:  @param blockId
     * @param:  @return
     * @return: EiBlock
     * @throws
     */
    public static EiBlockMeta createBlockMeta(Map<String,Object> map) {
        EiBlockMeta eiBlockMeta = new EiBlockMeta();
        if(!map.isEmpty()){
            for(String key : map.keySet()){
                eiBlockMeta.addMeta(new EiColumn(key));
            }
        }
        return eiBlockMeta;
    }

    /**
     * 获取院区
     * @Title: getDataGroupCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: String   ： 院区
     * @throws
     */
    public static String getDataGroupCode (String workNo) {
        workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
        //调用微服务S_AU_FW_03
        String userGroup = BaseDockingUtils.getUserGroupByWorkNo(workNo);
        return StringUtils.isBlank(userGroup) ? "" : userGroup;
    }

    /**
     * 获取用户信息
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: Map<String,Object>
     *        id       ：  员工ID
     *    workNo    ：  员工工号
     *    name      ：  员工姓名
     *    gender    ：  员工性别
     *    contactTel ：  联系电话
     *        deptId    :  科室ID
     *    deptNum       :  科室编码
     *    deptName   :  科室名称
     *    dataGroupCode : 院区
     * @throws
     */
    public static Map<String, Object> getUserInfo (String workNo) {
        //获取用户信息
        workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
        //调用微服务S_AC_FW_02
        Map<String,Object> map = BaseDockingUtils.getStaffByWorkNo(workNo);
        //获取用户院区
        map.put("dataGroupCode", getDataGroupCode(workNo));
        return map;
    }



    /**
     * 空处理
     * @Title: isEmpty
     * @Description: TODO(这里用一句话描述这个方法的作用)
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

    /*
     * 获取当前员工的工号，获取在配置表中绑定的科室
     * @author zhaowei
     * @date 2023/6/27 17:06
     * @return java.lang.String
     */
    public static List<Map<String,String>> isAdmin(){
        Map<String, Object> userInfo = CPUtils.getUserInfo(null);
        List<Map<String,String>> list = dao.query("CPCL01.queryCpWorkerBindDept", new HashMap<String, String>() {{
            put("workNo", (String) userInfo.get("workNo"));
        }});
        return list;
    }

    // 判断当前登录人是否是管理员
    public static List<String> isRole(String role){
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        String workNo = (String) staffByUserId.get("workNo");
        Map<String, String> map = new HashMap<>();
        map.put("role",role);
        map.put("workNo",workNo);
        List<Map<String,String>> list = dao.query("CPDJ01.queryDeptByRole", map);
        List<String> listStr = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map map1 : list) {
                String deptNum = (String) map1.get("deptNum");
                listStr.add(deptNum);
            }
        }
        return listStr;
    }

    // 梅州企业微信
    // 企业微信工会
    public static Boolean pushMsg(String deptNo,String deptName,String type){
        //获取app编码
        String appCode = "AP00002";
        List<String> workNoList = new ArrayList<>();
        List<String> paramList = new ArrayList<>();
        String smsTemp = "";
        List<Map<String,String>> list = dao.query("CPDJ01.queryManByRole", new HashMap<String, String>() {{
            put("deptNo", deptNo);
            put("deptName", deptName);
        }});
        for (Map map : list) {
            workNoList.add((String) map.get("workNo"));
        }
        switch (type){
            case "deal":
                smsTemp = "职工心声邮箱有一条新的心声请前往处理";
                break;
            default:
                smsTemp = "职工心声邮箱有一条新的心声请前往分配";
        }
        paramList.add(smsTemp);
        if (workNoList.isEmpty()){
            return false;
        } else {
            //发送企业微信
            return BaseDockingUtils.pushWxMsg(workNoList, paramList,"TP00001", appCode);
        }
    }
}