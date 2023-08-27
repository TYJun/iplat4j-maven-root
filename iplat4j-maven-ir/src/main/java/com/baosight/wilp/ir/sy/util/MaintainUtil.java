package com.baosight.wilp.ir.sy.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @PackageName com.baosight.wilp.ir.sy.util
 * @ClassName MaintainUtil
 * @Description 复写框架的MaintainUtil
 * @Author chunchen2
 * @Date 2022/1/12 15:20
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/12 15:20
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class MaintainUtil {

    private static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 生成任务号的头
     *
     * @return
     */
    public static String createTop() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String head = sdf.format(new Date());
        return head;
    }

    public static EiInfo getGroup(String loginName) {
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("loginName", loginName);
        eiInfo.set(EiConstant.serviceId, "S_XS_55");
        return XServiceManager.call(eiInfo);

    }

    /**
     * 前端参数转map
     *
     * @param inInfo
     * @param list
     * @return
     */
    public static Map<String, Object> changeToMap(EiInfo inInfo, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        Map param = inInfo.getAttr();
        // 获取分页信息
        EiBlock result = inInfo.getBlock("result");
        Integer offset = 0;
        Integer limit = 10;
        if (result != null && !result.getAttr().isEmpty()) {
            offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
            limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
        }
        map.put("offset", offset.toString());
        map.put("limit", limit.toString());
        list.forEach(e -> map.put(e, (String) param.get(e)));
        return map;
    }

    /**
     * 将查询出的数据以eiinfo的格式还回去
     *
     * @param list
     * @return
     */
    public static EiInfo changeToEiInfo(List list) {
        EiInfo outInfo = new EiInfo();

        EiBlock eiBlock = new EiBlock(new EiBlockMeta());
        eiBlock.setRows(list);
        HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
        hashMap.put("result", eiBlock);
        outInfo.setBlocks(hashMap);
        if (!CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("查询成功");
        } else {
            outInfo.setMsg("未获取到数据");
        }
        return outInfo;
    }

    /**
     * 封装接口调用的请求 调用该方法前需要eiInfo.set(k,v)将参数传好
     *
     * @param serviceName
     * @param methodName
     * @param paramInfo
     * @return
     */
    public static EiInfo putRequest(String serviceName, String methodName, EiInfo eiInfo) {
        eiInfo.set(EiConstant.serviceName, serviceName);
        eiInfo.set(EiConstant.methodName, methodName);
        EiInfo outInfo = XLocalManager.call(eiInfo);
        return outInfo;
    }

    public static EiInfo changeToEiInfo(Map map) {
        EiInfo info = new EiInfo();
        info.setAttr(map);
        return info;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getUserInfo(String workNo) {
        // 获取用户
        EiInfo info = new EiInfo();
        info.set("workNo", StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo);
        info.set(EiConstant.serviceName, "MTRE01");
        info.set(EiConstant.methodName, "getUserInformation");
        EiInfo infoLogin = XLocalManager.call(info);
        if (infoLogin.getBlock("user") != null && infoLogin.getBlock("user").getRowCount() > 0) {
            Map<String, Object> map = infoLogin.getBlock("user").getRow(0);
            // 获取用户角色（普通用户组）
            EiInfo eiInfo = new EiInfo("role");
            eiInfo.set(EiConstant.serviceId, "S_XS_14");
            eiInfo.set("loginName", info.get("workNo"));
            eiInfo.set("groupType", "NORMAL");
            EiInfo normalGroupInfo = XServiceManager.call(eiInfo);
            String role = getRoleStr(normalGroupInfo);
            // 获取用户角色（管理员用户组）
            eiInfo.set("groupType", "MANAGERGROUP");
            EiInfo ManagerGroupInfo = XServiceManager.call(eiInfo);
            role = role + getRoleStr(ManagerGroupInfo);
            map.put("role", role.length() > 0 ? role.substring(0, role.length() - 1) : "");
            return map;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static String getRoleStr(EiInfo eiInfo) {
        StringBuilder sb = new StringBuilder();
        if (eiInfo.getStatus() == 1) {
            List<Map<String, String>> list = (List<Map<String, String>>) eiInfo.get("result");
            list.forEach(map -> sb.append(map.get("groupEname")).append(","));
        }
        return sb.toString();
    }

    /**
     * base64图片转字节数组
     *
     * @param img
     * @return
     */
    public static byte[] castToImg(String img) {
        // base64转化为字节数组
        return decoder.decode(img);
    }

    /**
     * 获取指定日期所在周 @Title: getWeekDay @Description:
     * TODO(这里用一句话描述这个方法的作用) @param: @param date @param: @return @return:
     * String[] @throws
     */
    public static String[] getWeekDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); // 获取周开始基准
        int current = calendar.get(Calendar.DAY_OF_WEEK); // 获取当天周内天数
        calendar.add(Calendar.DAY_OF_WEEK, min - current); // 当天-基准，获取周开始日期
        String firstTime = DateUtils.toDateStr10(calendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, 6); // 开始+6，获取周结束日期
        String lastTime = DateUtils.toDateStr10(calendar.getTime());
        return new String[] { firstTime, lastTime };

    }

    public static String[] getMonthDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取第一天和最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstTime = DateUtils.toDateStr10(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String lastTime = DateUtils.toDateStr10(calendar.getTime());
        return new String[] { firstTime, lastTime };
    }
}
