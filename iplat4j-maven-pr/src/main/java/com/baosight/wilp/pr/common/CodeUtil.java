package com.baosight.wilp.pr.common;


import com.baosight.wilp.pr.common.TyepCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 问题分类编码转换
 * <p>1.code问题分类编码转换
 * 
 * @Title: CodeUtil.java
 * @ClassName: CodeUtil
 * @Package：com.baosight.wilp.pr.common
 * @author: ha'ha'ha
 * @date: 2021年10月25日 下午2:12:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class CodeUtil {
    
    /**
     * 
     * 问题分类编码转换
     *
     * @Title: code 
     * @param param 问题分类编码、或者分类名称
     * @return 
     * @return: String
     */
    public static String code(String param){
        //校验中文字符
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(param);
        Boolean co = true;
        //如果是中文字符co则赋值为false
        if (!m.find()) {
            co = false;
        }
        String code = null;
        List<Map<String,String>> paramCode = new ArrayList<>();
            try{
                //查询小代码配置信息
                paramCode = (List<Map<String,String>>) TyepCode.dealUseDay("WILP.im.saftyType");
                //co为true则返回问题分类名称
                if(co){
                    for (Map<String, String> map:paramCode) {
                        String par = map.get("label");
                        if(par.equals(param)){
                            code = map.get("value");
                        }
                    }
                }
            }catch (Exception e){
                return null;
            }
            //co为false则返回问题分类编码
            if(!co){
                for (Map<String, String> map:paramCode) {
                    String par = map.get("value");
                    if(par.equals(param)){
                        code = map.get("label");
                    }
                }

            }
        //返回问题分类参数
        return code;
    }
}
