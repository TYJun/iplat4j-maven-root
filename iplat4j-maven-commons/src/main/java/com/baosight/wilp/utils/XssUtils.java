package com.baosight.wilp.utils;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @PackageName com.baosight.wilp.security.util
 * @ClassName XssUtils
 * @Description Xss工具类
 * @Author chunchen2
 * @Date 2023/7/17 13:08
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/4/11 11:08
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class XssUtils {

    /**
     * @Title handle
     * @Author chunchen2
     * @Description // 过滤、转义处理字符串
     * @Date 14:08 2023/7/17
     * @param orignStr
     * @return java.lang.String
     * @throws
     **/
    public static String handle(String orignStr) {
        orignStr = filter(orignStr);
        orignStr = escape(orignStr);
        return orignStr;
    }

    /**
     * @Title filter
     * @Author chunchen2
     * @Description // 字符串过滤xss 敏感字符
     * @Date 13:53 2023/7/17
     * @param orignStr
     * @return java.lang.String
     * @throws
     **/
    public static String filter(String orignStr){
        if(null == orignStr) {
            return orignStr;
        }

        // 过滤处理敏感字符
        orignStr = orignStr.replace("<img", "")
                .replaceAll("(?i)alert","")
                .replace("(?i)onerror","")
                .replace("(?i)onfocus","")
                .replace("(?i)onload","")
                .replace("(?i)onmouseover","");

        // 此处是需要判断字符是否是需要判断两边是单词的
        Iterator<Pattern> it = SecurityCommonUtils.xssPatternSet.iterator();
        Matcher matcher;

        while(it.hasNext()){
            Pattern pattern = it.next();
            matcher = pattern.matcher(orignStr);
            if(matcher.find()) {
                String key = pattern.pattern().replace("\\b", "");
                orignStr = orignStr.replaceAll(key,"");
            }
        }

        return orignStr;
    }

    public static String escape(String value) {
        if (value == null)
            return null;

        // 对一般字符进行转义处理
        StringBuffer result = new StringBuffer(value.length());
        for (int i = 0; i < value.length(); i++) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
//                    case '"':
//                        result.append("&quot;");
//                        break;
                case '\'':
                    result.append("&#39;");
                    break;
                case '%':
                    result.append("&#37;");
                    break;
                case ';':
                    result.append("&#59;");
                    break;
//                case '&':
//                    result.append("&amp;");
//                    break;
//                case '+':
//                    result.append("&#43;");
//                    break;
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        return result.toString();
    }
}
