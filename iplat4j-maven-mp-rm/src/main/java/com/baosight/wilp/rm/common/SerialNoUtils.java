package com.baosight.wilp.rm.common;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 单号生成工具类
 * @ClassName: SerialNoUtils
 * @Package com.baosight.wilp.rm.common
 * @date: 2022年09月13日 13:19
 */
public class SerialNoUtils {

    /**
     * 锁对象
     **/
    private static final Lock lock = new ReentrantLock();

    /**
     * 生成序列号
     *
     * @param name   name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @return java.lang.String
     * @throws
     * @Title: generateNumberSerialNo
     **/
    public static String generateNumberSerialNo(String name, String prefix) {
        return generateNumberSerialNo(name, prefix, 12);
    }

    /**
     * 生成序列号
     *
     * @param name         name : 序列号标识(名称)
     * @param prefix       prefix : 前缀
     * @param suffixLength suffixLength : 后缀长度
     * @return java.lang.String
     * @throws
     * @Title: generateNumberSerialNo
     **/
    public static String generateNumberSerialNo(String name, String prefix, int suffixLength) {
        return generateSerialNo(name, prefix, null, suffixLength);
    }

    /**
     * 生成序列号
     *
     * @param name   name : 序列号标识(名称)
     * @param prefix prefix : 前缀
     * @return java.lang.String
     * @throws
     * @Title: generateSerialNo
     **/
    public static String generateSerialNo(String name, String prefix) {
        return generateSerialNo(name, prefix, DateUtils.DATE8_PATTERN);
    }

    /**
     * 生成序列号
     *
     * @param name       name : 序列号标识(名称)
     * @param prefix     prefix : 前缀
     * @param dateFormat dateFormat : 时间格式字符串
     * @return java.lang.String
     * @throws
     * @Title: generateSerialNo
     **/
    public static String generateSerialNo(String name, String prefix, String dateFormat) {
        return generateSerialNo(name, prefix, dateFormat, 4);
    }

    /**
     * 生成序列号
     * <p>
     * 1.查询cu_model_number表中的序号
     * 2.判断单号是否存在，不存在，构建一个初始工单号; 存在，更新单号
     * 3.更新单号
     * </p>
     *
     * @param name         name : 序列号标识(名称)
     * @param prefix       prefix : 前缀
     * @param dateFormat   dateFormat : 时间格式字符串
     * @param suffixLength suffixLength : 后缀长度
     * @return java.lang.String
     * @throws
     * @Title: generateSerialNo
     **/
    public static String generateSerialNo(String name, String prefix, String dateFormat, int suffixLength) {
        String serialNo = "", op = "add";
        lock.lock();
        try {
            /**1.查询cu_model_number表中的序号**/
            EiInfo invoke = RmUtils.invoke("RMTY02", "querySerialNo", Arrays.asList("type"), name);
            String lastSerialNo = invoke.getString("serialNo");

            /**2.判断单号是否存在，不存在，构建一个初始工单号; 存在，更新单号**/
            String dateStr = StringUtils.isBlank(dateFormat) ? "" : DateUtils.curDateStr(dateFormat);
            if (StringUtils.isBlank(lastSerialNo)) {
                serialNo = prefix + dateStr + buildSuffix(suffixLength);
            } else {
                op = "edit";
                serialNo = prefix + buildSuffixByDate(dateStr, dateFormat, lastSerialNo, prefix.length());
            }

            /**3.更新单号**/
            RmUtils.invoke("RMTY02", "updateSerialNo", Arrays.asList("serialNo", "op", "type"), serialNo, op, name);
        } finally {
            lock.unlock();
        }
        return serialNo;
    }

    /**
     * 构建更新后缀
     *
     * @param dateFormat   dateFormat : 时间格式字符串
     * @param dateStr      dateStr : 时间字符串
     * @param lastSerialNo lastSerialNo : 之前最大序列号
     * @param prefixLength prefixLength : 前缀长度
     * @return java.lang.String
     * @throws
     * @Title: buildSuffixByDate
     **/
    private static String buildSuffixByDate(String dateStr, String dateFormat, String lastSerialNo, int prefixLength) {
        String suffix = "";
        String suffixStr = "1" + lastSerialNo.substring((prefixLength + dateStr.length()));
        if (StringUtils.isBlank(dateFormat)) {
            //没有时间
            return String.valueOf(Long.valueOf(suffixStr) + 1L).substring(1);
        } else {
            String date = lastSerialNo.substring(prefixLength, prefixLength + dateFormat.length());
            if (dateStr.equals(date)) {
                //有时间,且当前时间有生成过单号
                return dateStr + String.valueOf(Long.parseLong(suffixStr) + 1L).substring(1);
            } else {
                //有时间,但当前时间没有生成过单号
                return dateStr + buildSuffix(lastSerialNo.length() - prefixLength - dateFormat.length());
            }
        }
    }

    /**
     * 生成指定长度的后缀
     *
     * @param length length : 后缀长度
     * @return java.lang.String
     * @throws
     * @Title: buildSuffix
     **/
    private static String buildSuffix(int length) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        Long value = Long.valueOf(sb.toString()) + 1L;
        return String.valueOf(value).substring(1);
    }

}
