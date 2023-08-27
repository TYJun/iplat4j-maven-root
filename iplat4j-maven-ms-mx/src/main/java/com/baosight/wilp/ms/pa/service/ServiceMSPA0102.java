package com.baosight.wilp.ms.pa.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.pa.domain.*;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baosight.wilp.ms.common.util.PackageOarameters;

/**
 * 参数报警人 新增页面（初始化方法、新增功能）
 * @title: ServiceMSPA0101
 * @author Wzy
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/313:05
 */
public class ServiceMSPA0102 extends ServiceEPBase {

    /**
     * 参数报警人 新增 弹窗初始化方法
     *
     * @param inInfo
     * @return
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author Wzy
     */
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

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
     * 参数报警人页面的新增方法
     * 1.首先在前台判断一些input框不为空 然后添加相应的实体类里面(UserSession工具类然后获取创建人)
     * 2.然后报警等级过滤是由四个可以选择的 然后给他全都通过小代码转成数字  然后循环员工工号 判断工号是否与数据库中的一致
     * 3.如果一致就不增加 直接跳出
     * 4.pid是树状互联查询框的id  如果是要指定某个树状框  首先判断pid等不等于空 然后再进行增加 往指定的树状框增加
     * 5.如果pid等于空 那就不给pid这个属性
     * 6.判断一些input框是否填入  然后给出相应提示
     * @param inInfo
     * @return
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author Wzy
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String loginName = UserSession.getLoginName();
        EiInfo outInfo = null;
        MSPA01 mspa01 = new MSPA01();
        outInfo = new EiInfo();
        String email = inInfo.getAttr().get("email").toString();
        /**
         * 1.首先在后台判断一些input框不为空 然后添加相应的实体类里面(UserSession工具类然后获取创建人)
         */
        if (!email.equals("")) {
            if (!emailFormat(email)) {
                outInfo.setMsg("邮箱不对");
                return outInfo;
            }
        }
        if (inInfo.getAttr().get("grade_filter").equals("")) {          //判断复选框有没有选中
            outInfo.setMsg("复选框必选");
            return outInfo;
        }
        mspa01.setCreate_by(loginName);     //创建人
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = format.format(date);
        mspa01.setCreate_date(dates);       //创建时间
        mspa01.setStaffid(inInfo.getAttr().get("staffid").toString());      //工号
        mspa01.setName_(inInfo.getAttr().get("name_").toString());          //名字
        Map<String, String> map2 = new HashMap<>();
        map2.put("pid",inInfo.getAttr().get("pid").toString());
        List<Map> list = this.dao.query("MSPA0102.select", map2);
        for (int i = 0; i < list.size(); i++) { //遍历目前数据库所有的员工信息 然后下面进行判断
            if (inInfo.getAttr().get("staffid").equals("")) {       //判断input框的工号不为空
                break;
            }
            if (list.get(i).get("staffid").equals(inInfo.getAttr().get("staffid").toString())) {        //判断工号有没有重复
                outInfo.setMsg("新增失败");
                return outInfo;
            }
        }
        mspa01.setId(UUIDUtils.getUUID());          //报警人id
        mspa01.setTel(inInfo.getAttr().get("tel").toString());      //报警人电话
        mspa01.setEmail(inInfo.getAttr().get("email").toString());  //报警人邮箱
        /**
         * 判断前台复选框报警等级过滤然后进行处理 用，隔开 然后进行塞入实体类
         */
        String grade_filter = inInfo.getAttr().get("grade_filter").toString();
        String[] split = grade_filter.split(",");
        String g = StringUtils.join(split);
        mspa01.setGrade_filter(g);
        String pid = inInfo.getAttr().get("pid").toString();
        /**
         * 判断是否是指定一个树状进行新增
         */
        if (pid.equals("root")) {
            pid = "";
        }
        /**
         * pid是树状互联查询框的id  如果是要指定某个树状框  首先判断pid等不等于空 然后再进行增加 往指定的树状框增加
         */
        if (!pid.equals(null) && !pid.equals("")) {
            mspa01.setT_param_classify_id(pid);
            PackageOarameters.setRows(inInfo, mspa01);
            outInfo = super.insert(inInfo, "MSPA0102.insert");
            outInfo.setMsg("新增成功");
            return outInfo;
        }
        PackageOarameters.setRows(inInfo, mspa01);
        outInfo = super.insert(inInfo, "MSPA0102.insert2");
        outInfo.setMsg("新增成功");
        return outInfo;
    }
}
