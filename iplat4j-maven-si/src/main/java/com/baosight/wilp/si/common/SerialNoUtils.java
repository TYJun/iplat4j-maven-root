package com.baosight.wilp.si.common;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 单号生成工具类
 * @ClassName: SerialNoUtils
 * @Package com.baosight.wilp.rm.common
 * @date: 2022年09月13日 13:19
 */
public class SerialNoUtils {

    /**锁对象**/
    private static final Lock lock = new ReentrantLock();

    private static Map<String, String> serialNoCache = new ConcurrentHashMap<>();

    /**
     * 生成序列号
     * @Title: generateNumberSerialNo
     * @param name name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @return java.lang.String
     * @throws
     **/
    public static String generateNumberSerialNo(String name, String prefix) {
        return generateNumberSerialNo(name, prefix, 12);
    }

    /**
     * 生成序列号
     * @Title: generateNumberSerialNo
     * @param name name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @param suffixLength suffixLength : 后缀长度
     * @return java.lang.String
     * @throws
     **/
    public static String generateNumberSerialNo(String name, String prefix, int suffixLength) {
        return generateSerialNo(name, prefix, null, suffixLength);
    }

    /**
     * 生成序列号
     * @Title: generateSerialNo
     * @param name name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @return java.lang.String
     * @throws
     **/
    public static synchronized String generateSerialNo(String name, String prefix) {
        if("OW".equals(prefix)){
            //System.out.println("进入时间：" + System.currentTimeMillis());
            try {Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
        }

        String serialNo = generateSerialNo(name, prefix, DateUtils.DATE8_PATTERN);
        //if("OW".equals(prefix)){System.out.println("获取完成时间：" + System.currentTimeMillis());}
        return serialNo;
    }

    /**
     * 生成序列号
     * @Title: generateSerialNo
     * @param name name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @param dateFormat dateFormat : 时间格式字符串
     * @return java.lang.String
     * @throws
     **/
    public static synchronized String generateSerialNo(String name, String prefix, String dateFormat) {
        return generateSerialNo(name, prefix, dateFormat, 4);
    }

    /**
     * 生成序列号
     * <p>
     *     1.查询cu_model_number表中的序号
     *     2.判断单号是否存在，不存在，构建一个初始工单号; 存在，更新单号
     *     3.更新单号
     * </p>
     * @Title: generateSerialNo
     * @param name name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @param dateFormat dateFormat : 时间格式字符串
     * @param suffixLength suffixLength : 后缀长度
     * @return java.lang.String
     * @throws
     **/
    public static synchronized String generateSerialNo(String name, String prefix, String dateFormat, int suffixLength) {
        String serialNo = "", op = "add"; String lastSerialNo = "";
        /**1.查询cu_model_number表中的序号**/
        if(serialNoCache.containsKey(name)) {
            lastSerialNo = serialNoCache.get(name);
        } else {
            lastSerialNo = querySerialNo(name);
        }

        /**2.判断单号是否存在，不存在，构建一个初始工单号; 存在，更新单号**/
        String dateStr = StringUtils.isBlank(dateFormat) ? "" : DateUtils.curDateStr(dateFormat);
        if (StringUtils.isBlank(lastSerialNo)) {
            serialNo = prefix + dateStr + buildSuffix(suffixLength);
        } else {
            op = "edit";
            serialNo = prefix + buildSuffixByDate(dateStr, dateFormat, lastSerialNo, prefix.length());
        }
        serialNoCache.put(name, serialNo);

        /**3.更新单号**/
        updateSerialNo(op, name, serialNo);
        return serialNo;
    }

    /**
     * 构建更新后缀
     * @Title: buildSuffixByDate
     * @param dateFormat dateFormat : 时间格式字符串
     * @param dateStr dateStr : 时间字符串
     * @param lastSerialNo lastSerialNo : 之前最大序列号
     * @param prefixLength prefixLength : 前缀长度
     * @return java.lang.String
     * @throws
     **/
    private static String buildSuffixByDate(String dateStr, String dateFormat, String lastSerialNo, int prefixLength) {
        String suffix = "";
        String suffixStr = "1" + lastSerialNo.substring((prefixLength + dateStr.length()));
        if(StringUtils.isBlank(dateFormat)) {
            //没有时间
            return String.valueOf(Long.valueOf(suffixStr)+1L).substring(1);
        } else {
            String date = lastSerialNo.substring(prefixLength, prefixLength + dateFormat.length());
            if(dateStr.equals(date)) {
                //有时间,且当前时间有生成过单号
                return dateStr + String.valueOf(Long.parseLong(suffixStr)+1L).substring(1);
            } else {
                //有时间,但当前时间没有生成过单号
                return dateStr + buildSuffix(lastSerialNo.length()-prefixLength-dateFormat.length());
            }
        }
    }

    /**
     * 生成指定长度的后缀
     * @Title: buildSuffix
     * @param length length : 后缀长度
     * @return java.lang.String
     * @throws
     **/
    private static String buildSuffix(int length) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        Long value = Long.valueOf(sb.toString()) + 1L;
        return String.valueOf(value).substring(1);
    }

    public static final String OPERATE_TYPE_ADD = "add";

    /**
     * 获取指定类型的最大序列号
     * @Title: querySerialNo
     * @param type: 序列号名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static String querySerialNo (String type) {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<String> list = dao.query("SITY01.querySerialNo", type);
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(list.get(0))) {
            return "";
        } else {
            return list.get(0);
        }
    }

    /**
     * 保存序列号
     * @Title: updateSerialNo
     * @param op op: 操作类型
     * @param type type: 序列号名称
     * @param serialNo serialNo: 序列号
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static void updateSerialNo(String op, String type, String serialNo) {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        //封装参数
        Map<String, String> map = new HashMap<>(4);
        map.put("serialNo", serialNo);
        map.put("type", type);
        //保存数据
        if(OPERATE_TYPE_ADD.equals(op)){
            map.put("createTime", DateUtils.curDateTimeStr19());
            dao.insert("SITY01.insertSerialNo", map);
        } else {
            map.put("updateTime", DateUtils.curDateTimeStr19());
            dao.update("SITY01.updateSerialNo", map);
        }
    }

}
