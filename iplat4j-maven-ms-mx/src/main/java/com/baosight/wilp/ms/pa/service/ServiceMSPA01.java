package com.baosight.wilp.ms.pa.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.pa.domain.*;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wzy
 * 参数报警人配置页面（页面初始化方法、查询、删除）
 * @title: ServiceMSPA01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/217:44
 */
public class ServiceMSPA01 extends ServiceBase {

    /**
     * @title 定义一个map泛型集合
     * @description
     * @author Wzy
     * @updateTime 2021/8/9 10:27
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @throws
     */
    private static Map<String, String> map = new HashMap<>();

    /**
     * @title 静态代码快 给map塞键值
     * @description
     * @author Wzy
     * @updateTime 2021/9/7 14:01
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @throws
     */
    static {
        map.put("1", "低报警");
        map.put("2", "低低报警");
        map.put("3", "高报警");
        map.put("4", "高高报警");
    }

    /**
     * 页面初始化方法
     * @param inInfo
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @return
     * @author Wzy
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo, new MSPA01());
    }

    /**
     * 参数报警人首页查询功能
     *  1.通过相应的xml来返回EiInfo对象 然后塞入不同实体类
     *  2.判断是否点击树状查询
     *  3.判断是否是查询全部和点击树状查询
     *  4.判断工号长度为0
     *  5.判断姓名长度为0
     *  6.调用xml查询数据
     *  7.通过小代码获得的所有报警类别的数据（数字） 然后通过handerl方法生成汉字的
     *  8.给页面设置总条数
     * @param inInfo
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @return
     * @author Wzy
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**
         * 1.通过相应的xml来返回EiInfo对象 然后塞入不同实体类
         */
        Map map = inInfo.getBlocks();
        EiBlock eiBlock = (EiBlock) map.get("inqu_status");
        List<Map> list = eiBlock.getRows();
        String parentId = list.get(0).get("parentId").toString();
        String Staffid = list.get(0).get("Staffid").toString();
        String Name_ = list.get(0).get("Name_").toString();
        /**
         * 判断是否点击树状查询
         */
        if (parentId.equals("root")) {
            list.get(0).put("parentId", "");
        }
        /**
         *判断是否是查询全部和点击树状查询
         */
        if (parentId.trim().length() == 0) {
            list.get(0).remove("parentId");
        }
        /**
         * 判断工号长度为0
         */
        if (Staffid.trim().length() == 0) {
            list.get(0).remove("Staffid");
        }
        /**
         * 判断姓名长度为0
         */
        if (Name_.trim().length() == 0) {
            list.get(0).remove("Name_");
        }
        /**
         * 调用xml查询数据
         */
        EiInfo outInfo = super.query(inInfo, "MSPA01.query", new MSPA01());
        EiInfo outInfo2 = super.query(inInfo, "MSPA01.query2", new T_Param_Classify());
        Map blockes = outInfo2.getBlocks();
        EiBlock result2 = (EiBlock) blockes.get("result");
        List<Map> rowes = result2.getRows();
        Map attr = result2.getAttr();
        Map blocks = outInfo.getBlocks();
        EiBlock result = (EiBlock) blocks.get("result");
        List<Map> rows = result.getRows();
        for (int i = 0; i < rows.size(); i++) {         //把所有数据循环出来
            /**
             * 通过小代码获得的所有报警类别的数据（数字） 然后通过handerl方法生成汉字的
             */
            String grade_filter = (String) rows.get(i).get("grade_filter");
            String grades = handerl(grade_filter);
            /**
             * 给页面设置总条数
             */
            attr.put("count", rows.size());
            rows.get(i).put("grade_filter", grades);
            if (!rowes.equals(null) || !rowes.equals("")) {
                rows.get(i).put("names", rowes.get(0).get("classify_name"));
            } else {
                rows.get(i).put("names", " ");
            }
        }
        /**
         * 如果没有数据  然后塞个总数
         */
        if (rows.size() == 0) {
            attr.put("count", rows.size());
        }
        return outInfo;
    }

    /**
     * 处理报警等级方法
     * @title 判断报警等级不为空   然后调用静态代码块然后产生汉字  中间以空格隔开
     * @description
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author Wzy
     * @updateTime 2021/9/7 14:06
     */
    private String handerl(String grade_filter) {
        if (StringUtils.isNotBlank(grade_filter)) {
            char[] chars = grade_filter.toCharArray();
            StringBuilder b = new StringBuilder();
            for (char c : chars) {
                b.append(map.get(String.valueOf(c))).append(" ");
            }
            return b.toString();
        }
        return null;
    }

    /**
     * 参数报警人首页删除功能
     * 调用delete方法  并且反射xml  然后返回EiInfo对象
     * @param inInfo
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @return
     * @author Wzy
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = super.delete(inInfo, "MSPA01.delete");
        return outInfo;
    }


}
