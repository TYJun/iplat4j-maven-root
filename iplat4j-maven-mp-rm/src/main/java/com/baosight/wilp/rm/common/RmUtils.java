package com.baosight.wilp.rm.common;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用工具类
 * @ClassName: RmUtils
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年08月25日 15:50
 *
 * 1.本地服务调用
 * 2.微服务调用
 * 3.将对象转成String
 * 4.将对象转成日期
 * 5.将对象转成List
 * 6.double四则运算
 * 7.初始化页面时间查询条件
 * 8.判断是否是空EiInfo
 * 9.添加库存量和领用预约量
 */
public class RmUtils {

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
     * @param serviceName serviceName : 本地服务类名
     * @param methodName methodName : 本地服务方法名称
     * @param map map : 参数Map
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo invoke(String serviceName, String methodName, Map map) {
        EiInfo inInfo = new EiInfo();
        inInfo.setAttr(map);
        return invoke(inInfo, serviceName, methodName);
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
     * @param obj obj
     * @return java.lang.String
     * @throws
     **/
    public static String toString(Object obj) {
        return toString(obj, "");
    }

    /**
     * 将对象转成String
     * @Title: toString
     * @param obj obj
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
     * @param dateStr dateStr
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
        info.setCell(RmConstant.QUERY_BLOCK, 0, beginTimeColumn, DateUtils.toDateStr(cal.getTime()));
        info.setCell(RmConstant.QUERY_BLOCK, 0, endTimeColumn, DateUtils.curDateStr());
    }

    /**
     * 将object转成List
     * @Title: toList
     * @param object object
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
        return mathSub(value1, value2).doubleValue();
    }

    /**
     * 数字相减
     * @Title: doubleSub
     * @param value1 value1
     * @param value2 value2
     * @return java.math.BigDecimal
     * @throws
     **/
    public static BigDecimal mathSub(Object value1, Object value2) {
        BigDecimal b1 = NumberUtils.toBigDecimal(value1, BigDecimal.ZERO);
        BigDecimal b2 = NumberUtils.toBigDecimal(value2, BigDecimal.ZERO);
        return b1.subtract(b2);
    }

    /**
     * double数字相乘
     * @Title: doubleMult
     * @param value1 value1
     * @param value2 value2
     * @return java.lang.Double
     * @throws
     **/
    public static Double doubleMult(Object value1, Object value2) {
        BigDecimal b1 = NumberUtils.toBigDecimal(value1, BigDecimal.ZERO);
        BigDecimal b2 = NumberUtils.toBigDecimal(value2, BigDecimal.ZERO);
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断是否是空EiInfo
     * @Title: isBlackInfo
     * @param inInfo inInfo
     * @param blockId blockId
     * @return boolean
     * @throws
     **/
    public static boolean isBlackInfo(EiInfo inInfo, String blockId) {
        if(inInfo.getBlock(blockId) == null) {
            return true;
        }

        if(inInfo.getBlock(blockId).getRowCount() == 0) {
            return true;
        }
        return false;
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
        EiInfo outInfo = invoke(eiInfo, "RMTY03", "getParentGroupsByLoginName");
        //返回处理
        if(isBlackInfo(outInfo, RmConstant.RESULT_BLOCK)) {
            return "";
        }
        List<Map> rows = outInfo.getBlock(RmConstant.RESULT_BLOCK).getRows();
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
     * 添加库存量和领用预约量
     * <p>
     *     1.判断是否需要库存量。是,调用对接接口获取物资库存信息
     *     2.获取领用预约量
     *     3.判断是否存在预约量和库存量，不存在，结束;存在，赋值
     * </p>
     * @Title: assignNum
     * @param rows rows : 源数据
     * @param claimService claimService : 领用实现service
     * @param ext : 扩展参数：对指定字段进行赋值
     * @return void
     * @throws
     **/
    public static void assignNum(List<Map> rows, RmClaimService claimService, Object ... ext) {
        /**1.判断是否需要库存量。是,调用对接接口获取物资库存信息**/
        List<String> matNumList = rows.stream().map(row -> RmUtils.toString(row.get("matNum"))).collect(Collectors.toList());
        EiInfo invoke = RmUtils.invoke("RMJK03", "dockMatStock", Arrays.asList("matNumList"), matNumList);

        /**2.获取领用预约量**/
        List<Map<String, Object>> reserveNums = null;
        if(claimService != null) {
            reserveNums = claimService.queryReserveNums(matNumList);
        }

        /**3.判断是否存在预约量和库存量，不存在，结束;存在，赋值**/
        if(isBlackInfo(invoke, RmConstant.RESULT_BLOCK) && CollectionUtils.isEmpty(reserveNums)) {
            for (Map<String, Object> row : rows) {
                row.put("stockNum", "0.00");
                row.put("reserveNum", "0.00");
            }
            return;
        } else {
            for (Map<String, Object> row : rows) {
                row.put("stockNum","0.00"); row.put("reserveNum","0.00");
                //存在存库
                if(!isBlackInfo(invoke, RmConstant.RESULT_BLOCK)) {
                    List<Map> stockList = invoke.getBlock(RmConstant.RESULT_BLOCK).getRows();
                    for (Map stockMap : stockList) {
                        if(row.get("matNum").equals(stockMap.get("matNum"))) {
                            row.put("stockNum", stockMap.get("stockNum"));
                        }
                    }
                }


                //存在预约量
                if (CollectionUtils.isNotEmpty(reserveNums)) {
                    reserveNums.forEach(map -> {
                        if(row.get("matNum").equals(map.get("matNum"))) {
                            row.put("reserveNum", map.get("totalNum"));
                        }
                    });
                }
                if (row.containsKey("eiMetadata")) { row.remove("eiMetadata");}
                
                //处理扩展字段{name:xx, field1:xx, field2:xx}
                if(ext != null && ext.length > 0) {
                    for (Object o : ext) {
                        Map<String, String> map = (Map<String, String>) o;
                        BigDecimal b1 = RmUtils.mathSub(row.get(map.get("field1")), row.get(map.get("field2")));
                        if(map.containsKey("field3")) {
                            BigDecimal b2 = new BigDecimal(String.valueOf(row.get(map.get("field3"))));
                            row.put(map.get("name"), b1.compareTo(b2) <= 0 ? b1.doubleValue() : b2.doubleValue());
                        } else {
                            row.put(map.get("name"), b1.doubleValue());
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取物资分类树
     *
     * @throws
     * @Title: getMatTypeTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param map
     * @param: @return
     * @return: EiInfo
     * id ： 当前节点的id
     * text : 分类名称
     * pId ： 上级节点的id
     * leaf : 是否有子节点
     */
    public static List<Map<String, String>> getMatTypeTree(Map<String, String> map) {
        //调用微服务接口S_AC_FW_18查询物资分类信息
        List<Map<String, String>> list = BaseDockingUtils.getMatType(map);

        //过滤出编码为 3 开头的物资
        List<Map<String, String>> matList =
                list.stream().filter(m -> m.get("matClassCode").startsWith("3"))
                        .filter(x-> !"301".equals(x.get("matClassCode")))
                        .filter(x-> !"302".equals(x.get("matClassCode")))
                        .filter(x-> !"3".equals(x.get("matClassCode"))).collect(Collectors.toList());

        return matList;
    }

}
