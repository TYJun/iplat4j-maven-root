package com.baosight.wilp.utils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @PackageName com.baosight.wilp.security.common
 * @ClassName CommonUtils
 * @Description 添加一些通用的工具类
 * @Author chunchen2
 * @Date 2023/6/5 17:00
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/6/5 17:00
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class SecurityCommonUtils {

    public static Set<Pattern> xssPatternSet =  new LinkedHashSet();

    static {
        List<String> xssPatternRegular = new ArrayList<>();
        xssPatternRegular.add("script");

        xssPatternSet.clear();
        Iterator<String> it = xssPatternRegular.iterator();

        while(it.hasNext()) {
            String regexStr = "\\b(?i)" + it.next() + "\\b";
            Pattern pattern = Pattern.compile(regexStr);
            xssPatternSet.add(pattern);
        }
    }

}
