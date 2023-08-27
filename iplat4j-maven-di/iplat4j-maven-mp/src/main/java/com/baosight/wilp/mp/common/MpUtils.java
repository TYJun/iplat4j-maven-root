package com.baosight.wilp.mp.common;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购工具类
 * @ClassName: MpUtils
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年08月25日 15:50
 *
 * 1.本地服务调用
 * 2.微服务调用
 * 3.将对象转成String
 * 4.将对象转成List
 * 5.将对象转成Date
 * 6.double数据的四则运算
 * 7.初始化页面时间查询条件
 */
public class MpUtils {

    /**
     * 本地服务调用
     * @Title: invoke
     * @param inInfo inInfo : 参数EiInfo
     * @param serviceName serviceName : 本地服务类名
     * @param methodName methodName : 本地服务方法名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName) {
        inInfo.set(EiConstant.serviceName, serviceName);
        inInfo.set(EiConstant.methodName, methodName);
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }

    /**
     * 本地服务调用
     * @Title: invoke
     * @param inInfo inInfo : 参数EiInfo
     * @param serviceName serviceName : 本地服务类名
     * @param methodName methodName : 本地服务方法名称
     * @param paramNames paramNames : 参数名称集合
     * @param args args : 参数
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName, List<String> paramNames, Object ... args) {
        //参数赋值
        if(paramNames != null){
            for (int i = 0; i < paramNames.size(); i++) {
                inInfo.set(paramNames.get(i), args[i]);
            }
        }
        return invoke(inInfo, serviceName, methodName);
    }

    /**
     * 本地服务调用
     * @Title: invoke
     * @param serviceName serviceName : 本地服务类名
     * @param methodName methodName : 本地服务方法名称
     * @param paramNames paramNames : 参数名称集合
     * @param args args : 参数
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(String serviceName, String methodName, List<String> paramNames, Object ... args) {
        return invoke(new EiInfo(), serviceName, methodName, paramNames, args);
    }

    /**
     * 微服务调用
     * @Title: invoke
     * @param inInfo inInfo : 参数EiInfo
     * @param serviceId serviceId : 微服务ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(EiInfo inInfo, String serviceId) {
        inInfo.set(EiConstant.serviceId, serviceId);
        EiInfo outInfo = XServiceManager.call(inInfo);
        return outInfo;
    }

    /**
     * 微服务调用
     * @Title: invoke
     * @param inInfo inInfo : 参数EiInfo
     * @param serviceId serviceId : 微服务ID
     * @param paramNames paramNames : 参数名称集合
     * @param args args : 参数
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(EiInfo inInfo, String serviceId, List<String> paramNames, Object ... args) {
        //参数赋值
        if(paramNames != null){
            for (int i = 0; i < paramNames.size(); i++) {
                inInfo.set(paramNames.get(i), args[i]);
            }
        }
        return invoke(inInfo, serviceId);
    }

    /**
     * 微服务调用
     * @Title: invoke
     * @param serviceId serviceId : 微服务ID
     * @param paramNames paramNames : 参数名称集合
     * @param args args : 参数
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(String serviceId, List<String> paramNames, Object ... args) {
        return invoke(new EiInfo(), serviceId, paramNames, args);
    }

    /**
     * 将对象转成String
     * @Title: toString
     * @param obj obj : 需要转换的数据
     * @return java.lang.String
     * @throws
     **/
    public static String toString(Object obj) {
        return toString(obj, "");
    }

    /**
     * 将对象转成String
     * @Title: toString
     * @param obj obj : 需要转换的数据
     * @param defaultValue defaultValue : 默认值
     * @return java.lang.String
     * @throws
     **/
    public static String toString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (StringUtils.isBlank(obj.toString())) {
            return defaultValue;
        }
        return obj.toString();
    }

    /**
     * 将字符串转成date
     * @Title: toDate
     * @param dateStr dateStr : 时间字符串
     * @return java.util.Date
     * @throws
     **/
    public static Date toDate(String dateStr) {
        if(StringUtils.isBlank(dateStr)) {
            return null;
        }
        return DateUtils.toDateTime(dateStr);
    }

    /**
     * 将object转成List
     * @Title: toList
     * @param object object : 需要转换的数据
     * @return java.util.Collection<? extends java.lang.Object>
     * @throws
     **/
    public static <T> List<T> toList(Object object, Class<T> clazz) {
        if(object == null){ return new ArrayList<>(); }
        try {
            return JSON.parseArray(JSON.toJSONString(object), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    /**
     * 将对象转成boolean
     * @Title: toBoolean
     * @param obj obj
     * @return java.lang.Boolean
     * @throws
     **/
    public static Boolean toBoolean(Object obj) {
        String objStr = toString(obj);
        if(StringUtils.isBlank(objStr)) {
            return false;
        }
        String trueFlag1 = "true",trueFlag2 = "Y";
        if(trueFlag1.equalsIgnoreCase(objStr) || trueFlag2.equalsIgnoreCase(objStr)) {
            return true;
        }
        return NumberUtils.toInteger(obj, 0) > 0 ? true : false;
    }

    /**
     * double数字相加
     * @Title: doubleAdd
     * @param value1 value1
     * @param value2 value2
     * @return java.lang.Double
     * @throws
     **/
    public static Double doubleAdd(Double value1, Double value2) {
        BigDecimal b1 = NumberUtils.toBigDecimal(value1, BigDecimal.ZERO);
        BigDecimal b2 = NumberUtils.toBigDecimal(value2, BigDecimal.ZERO);
        return b1.add(b2).doubleValue();
    }

    /**
     * double数字相减
     * @Title: doubleSub
     * @param value1 value1
     * @param value2 value2
     * @return java.lang.Double
     * @throws
     **/
    public static Double doubleSub(Double value1, Double value2) {
        BigDecimal b1 = NumberUtils.toBigDecimal(value1, BigDecimal.ZERO);
        BigDecimal b2 = NumberUtils.toBigDecimal(value2, BigDecimal.ZERO);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * double数字相乘
     * @Title: doubleMult
     * @param value1 value1
     * @param value2 value2
     * @return java.lang.Double
     * @throws
     **/
    public static Double doubleMult(Double value1, Double value2) {
        BigDecimal b1 = NumberUtils.toBigDecimal(value1, BigDecimal.ZERO);
        BigDecimal b2 = NumberUtils.toBigDecimal(value2, BigDecimal.ZERO);
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 初始化页面时间查询条件
     * @Title: initQueryTime
     * @param info info : 参数EiInfo
     * @param beginTimeColumn beginTimeColumn : 开始时间字段名
     * @param endTimeColumn endTimeColumn : 结束时间字段名
     * @return void
     * @throws
     **/
    public static void initQueryTime(EiInfo info, String beginTimeColumn, String endTimeColumn) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        info.setCell(MpConstant.QUERY_BLOCK, 0, beginTimeColumn, DateUtils.toDateStr(cal.getTime()));
        info.setCell(MpConstant.QUERY_BLOCK, 0, endTimeColumn, DateUtils.curDateStr());
    }

    /**
     * 获取用户的用户组编码
     * @Title: getUserGroups
     * @return java.lang.String
     * @throws
     **/
    public static String getUserGroups(@Nullable String workNo) {
        //查询用户所属用户组
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("loginName", workNo);
        eiInfo.set("groupType", "NORMAL");
        EiInfo outInfo = invoke(eiInfo, "MPTY02", "getParentGroupsByLoginName");
        //返回处理
        if(outInfo == null || outInfo.getRow(MpConstant.RESULT_BLOCK, 0) == null) {
            return "";
        }
        List<Map> rows = outInfo.getBlock(MpConstant.RESULT_BLOCK).getRows();
        return StringUtils.join(rows.stream().map(row -> toString(row.get("groupEname")))
                .collect(Collectors.toList()), ",");
    }

    /**
     * 判断用户角色中知否包含指定角色
     * @Title: containRole
     * @param workNo workNo : 工号
     * @param role role : 角色编码
     * @return boolean
     * @throws
     **/
    public static boolean containRole(@Nullable String workNo, @Nullable String ... role) {
        String userGroups = getUserGroups(workNo);
        return StringUtils.isNotBlank(userGroups) &&
                Arrays.stream(userGroups.split(",")).anyMatch(roleCode -> Arrays.toString(role).contains(roleCode));
    }

    /**
     * 获取管理科室对应的业务科室编码字符串
     * @Title: getBusinessDept
     * @param deptNum deptNum
     * @return java.lang.String
     * @throws
     **/
    public static String getBusinessDept(String deptNum, boolean hasSelf) {
        EiInfo invoke = MpUtils.invoke("MPTY01", "queryBusinessDept", Arrays.asList("parentDeptCode"), deptNum);
        if(invoke.getStatus() == -1 || invoke.getRow("businessDept", 0) == null) {
            return hasSelf ? deptNum : "";
        } else {
            List<Map<String, String>> businessDept = invoke.getBlock("businessDept").getRows();
            List<String> deptCodes = businessDept.stream().map(map -> map.get("deptNum")).collect(Collectors.toList());
            if(hasSelf) { deptCodes.add(deptNum); }
            return StringUtils.join(deptCodes, ",");
        }
    }


}
