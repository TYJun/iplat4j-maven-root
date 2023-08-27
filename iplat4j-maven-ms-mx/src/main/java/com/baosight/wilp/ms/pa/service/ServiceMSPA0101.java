package com.baosight.wilp.ms.pa.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;

import com.baosight.wilp.ms.pa.domain.*;

import com.baosight.wilp.ms.common.util.PackageOarameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wzy
 * 参数报警人首页页面直接修改功能（内含邮箱正则表达式）
 * @title: ServiceMSPA0101
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/313:05
 */
public class ServiceMSPA0101 extends ServiceEPBase {

    /**
     * 验证输入的邮箱格式是否符合
     * @param email
     * @return 是否合法
     * @author Wzy
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @updateTime 2021/8/19 20:02
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }


    /**
     * 参数报警人首页直接修改方法
     * 1.判断电话为不为空  如果等于空 返回给前台展示
     * 2.判断电话必须为11位 如果不足或超过 返回给前台展示
     * 3.判断邮箱为不为空  如果不等于空 然后判断emailFormat（）方法是不是返回true
     * 4.判断id为不为空 作为sql条件 进行修改
     * @param inInfo
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @return
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        MSPA01 mspa01 = new MSPA01();
        EiInfo outInfo = new EiInfo();
        String tel = inInfo.getAttr().get("tel").toString();        //电话
        String email = inInfo.getAttr().get("email").toString();    //邮箱
        if (tel.equals("") || tel.equals(null)) {               //判断电话为不为空  如果等于空 返回给前台展示
            outInfo.setMsg("电话不可为空");
            return outInfo;
        }
        if (tel.length() != 11) {                             //判断电话必须为11位 如果不足或超过 返回给前台展示
            outInfo.setMsg("电话必须是11位");
            return outInfo;
        }
        if (!email.equals("")) {                            //判断邮箱为不为空  如果不等于空 然后判断emailFormat（）方法是不是返回true
            if (!emailFormat(email)) {
                outInfo.setMsg("邮箱不对");
                return outInfo;
            }
        }
        if (inInfo.getAttr().get("id") != null) {               //判断id为不为空 作为sql条件
            mspa01.setId(inInfo.getAttr().get("id").toString());
        }
        if (inInfo.getAttr().get("tel") != null) {              //判断电话为不为空 作为sql条件
            mspa01.setTel(inInfo.getAttr().get("tel").toString());
        }
        if (inInfo.getAttr().get("email") != null) {             //判断邮箱为不为空 作为sql条件
            mspa01.setEmail(inInfo.getAttr().get("email").toString());
        }
        PackageOarameters.setRows(inInfo, mspa01);
        /**
         * 调用修改语句
         */
        outInfo = super.update(inInfo, "MSPA0101.update");
        outInfo.setMsg("修改成功");
        return outInfo;
    }
}
