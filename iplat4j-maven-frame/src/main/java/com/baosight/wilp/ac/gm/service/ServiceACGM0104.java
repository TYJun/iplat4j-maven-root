package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 修改物资子界面.
 * <p>
 * 页面初始化, 新增物资, 新增分类.
 * </p>
 *
 * @Title ServiceACGM0104.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 */
@SuppressWarnings("unchecked")
public class ServiceACGM0104 extends ServiceBase {

    final String projectSchema = PrUtils.getConfigure();

    /**
     * 界面初始化
     * 作者：jzm
     * 入参：EiInfo(id 主键)
     * 出参：EiInfo(对应id的物资信息)
     * 处理：
     * 1.从inInfo中读取id参数
     * 2.从数据库中查询该id对应的物资信息
     * 3，返回物资信息
     *
     * @param inInfo
     * @return
     */
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo info1 = new EiInfo();
        info1.set("fkey", "MatImageSizeAndFormat");
        info1.set(EiConstant.serviceId, "S_AC_FW_21");
        EiInfo outInfo1 = XServiceManager.call(info1);
        ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) outInfo1.get("result");
        System.out.println("list的值："+list);
        String config = list.get(0).get("FVALUE").replace(" ", "");
        String maxSize = config.split(";")[0];
        String format = config.split(";")[1];
        /**
         * 1.从inInfo中读取id参数
         */
        HashMap<String, String> map = new HashMap<>();

        /**
         *  2.从数据库中查询该id对应的物资信息
         */
        map.put("projectSchema", projectSchema);
        map.put("id", inInfo.getString("id"));
        List<Map<String, Object>> mats = dao.query("ACGM01.queryMaterialList", map);

        /**
         * 3，返回物资信息
         */
        EiInfo out = new EiInfo();
        if (!mats.isEmpty()) {
            Map<String, Object> map1 = mats.get(0);
            map1.put("price", map1.get("price").toString().replace(",", ""));
            out.setAttr(map1);
        }
        out.set("maxSize",maxSize);
        out.set("format",format);
        return out;
    }

    /**
     * 修改物资信息
     * 作者：jzm
     * 入参：EiInfo(#matName# 物资名称,matClassId 分类id, matSpec 物资规格, matModel 物资型号,
     * unit 计量单位编码, price 最近订购单价 , supplier 供应商, manufacturer 制造商, matTypeCode
     * 物资大类编码
     * 出参：EiInfo(操作结果)
     * 操作：
     * 1.将入参从EiInfo中提出
     * 2.设置当前修改人和修改时间
     * 3.写入数据库
     * 4.返回操作结果
     *
     * @param inInfo
     * @return
     */
    @Override
    @ArchivesLog(model = "AC", sign = "修改物资")
    public EiInfo update(EiInfo inInfo) {
        /**
         * 1.将入参从EiInfo中提出
         */
        Map<String, String> map = inInfo.getAttr();
        String preMatCode=map.get("preMatCode");
        String matCode=map.get("matCode");
        if (!preMatCode.equals(matCode)){
            EiInfo info2 = new EiInfo();
            info2.set("matCode", matCode);
            info2.set(EiConstant.serviceId, "S_AC_FW_17");
            EiInfo outInfo2 = XServiceManager.call(info2);
            ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) outInfo2.get("result");
            if(!list2.isEmpty()){
                outInfo2.setMsg("物资编码不能重复");
                outInfo2.setStatus(-2);
                return outInfo2;
            }
        }
        map.put("projectSchema", projectSchema);
//        String matCode=map.get("matCode");
//        if("".equals(matCode)){
//            // 先查询出当前最新的 rec_create_time 取出该记录的matCode
//            List<Map<String, String>> list = dao.query("ACGM01.queryLastMatCode", map);
//            if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号MA00001
//                matCode = "MA00001";
//            } else {
//                matCode = ServiceACGM01.genMatCode(list.get(0).get("matCode")); // 生成物编码
//            }
//        }

        /**
         *  2.设置当前修改人和修改时间
         */
        String recRevisor = UserSession.getUser().getUsername();
        String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
        map.put("recRevisor", recRevisor);
        map.put("recReviseTime", recReviseTime);
//        map.put("matCode",matCode);

        /**
         *  3.写入数据库
         */
        dao.update("ACGM0104.update", map);

        /**
         * 4.返回操作结果
         */
        inInfo.setMsg("修改成功");
        return inInfo;
    }
}
