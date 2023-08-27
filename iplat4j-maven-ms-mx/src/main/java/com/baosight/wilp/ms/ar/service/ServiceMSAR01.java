package com.baosight.wilp.ms.ar.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.ar.domain.MSAR01;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.xservices.xs.up.utils.ProjectUtils;
import com.baosight.xservices.xs.up.utils.XSExcelUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author znl
 * @title: ServiceMSAR01
 * @projectName iplat_v5_monitor
 * @description: 报警规则页面
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/2 13:58
 */
public class ServiceMSAR01 extends ServiceBase {

    private static final int COLUMN_COUNT_LIXU = 7;

    /**
     * @description: 页面初始化
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     * @date 2021/8/4 18:05
     * 1、通过inInfo获取查询的参数
     * 2、通过super.initLoad方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo
     * 4、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo = super.initLoad(inInfo, new MSAR01());
        return inInfo;
    }

    /**
     * @description: 查询列表
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     * @date 2021/8/4 18:05
     * 1、通过inInfo获取查询的参数
     * 2、通过super.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进outInfo
     * 4、返回outInfo参数
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        if (o.get("code") != null) {
            String code = o.get("code").toString();
            if (code.trim().length() == 0) {
                o.remove("code");
            }
        }
        if (o.get("description") != null) {
            String description = o.get("description").toString();
            if (description.trim().length() == 0) {
                o.remove("description");
            }
        }
        if (o.get("name") != null) {
            String description = o.get("name").toString();
            if (description.trim().length() == 0) {
                o.remove("name");
            }
        }
        if (o.get("tag") != null) {
            String description = o.get("tag").toString();
            if (description.trim().length() == 0) {
                o.remove("tag");
            }
        }
        EiInfo outInfo = super.query(inInfo, "MSAR01.query", new MSAR01());
        return outInfo;

    }

    /**
     * @description: 添加数据
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     *  @date 2021/8/4 18:05
     * 1、通过inInfo获取提交的参数
     * 2、通过判断前端传进来的type是否是add
     * 如果是add新增数据否则修改数据
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        MSAR01 msar01 = new MSAR01();
        String lower = "";
        String lowerLower = "";
        String upper = "";
        String upperUpper = "";
        msar01.setDescription(inInfo.getAttr().get("description").toString());
        Map map = getMap(null);
        msar01.setDimension(inInfo.getAttr().get("dimension").toString());
        msar01.setDimensionName(map.get(inInfo.getAttr().get("dimension").toString()).toString());
        map = getMap(inInfo.getAttr().get("dimension").toString());
        msar01.setUnit(inInfo.getAttr().get("unit").toString());
        msar01.setUnitName(map.get(inInfo.getAttr().get("unit").toString()).toString());
        msar01.setRemarks(inInfo.getAttr().get("remarks").toString());
        if (StringUtil.isNotEmpty(inInfo.getAttr().get("lower").toString())) {
            msar01.setLower(inInfo.getAttr().get("lower").toString());
            lower = inInfo.getAttr().get("lower").toString();              //低报警
        }
        if (StringUtil.isNotEmpty(inInfo.getAttr().get("lowerLower").toString())) {
            msar01.setLowerLower(inInfo.getAttr().get("lowerLower").toString());
            lowerLower = inInfo.getAttr().get("lowerLower").toString();        //低低报警
        }
        if (StringUtil.isNotEmpty(inInfo.getAttr().get("upper").toString())) {
            msar01.setUpper(inInfo.getAttr().get("upper").toString());
            upper = inInfo.getAttr().get("upper").toString();              //高报警
        }
        if (StringUtil.isNotEmpty(inInfo.getAttr().get("upperUpper").toString())) {
            msar01.setUpperUpper(inInfo.getAttr().get("upperUpper").toString());
            upperUpper = inInfo.getAttr().get("upperUpper").toString();    //高高报警
        }

        /**
         * 根据type判断是增加还是更新
         * see新增
         */
        if (inInfo.getAttr().get("type").toString().equals("add")) {
            Double lower2 = null;
            Double lowerLower2 = null;
            Double upper2 = null;
            Double upperUpper2 = null;
            if (lower != "") {
                lower2 = Double.valueOf(lower);
            }
            if (lowerLower != "") {
                lowerLower2 = Double.valueOf(lowerLower);
            }
            if (upper != "") {
                upper2 = Double.valueOf(upper);
            }
            if (upperUpper != "") {
                upperUpper2 = Double.valueOf(upperUpper);
            }
            if (lower2 != null && lowerLower2 != null) {
                if (lower2 <= lowerLower2) {
                    outInfo.setMsg("低报警不得小于低低报警");
                    return outInfo;
                }
            }
            if (upper2 != null && upperUpper2 != null) {
                if (upper2 >= upperUpper2) {
                    outInfo.setMsg("高报警不得大于高高报警");
                    return outInfo;
                }
            }

            if (lower2 != null && upper2 != null) {
                if (lower2 > upper2) {
                    outInfo.setMsg("低报警不得大于高报警");
                    return outInfo;
                }
            }
            String code = inInfo.getAttr().get("dimension").toString() + inInfo.getAttr().get("unit").toString() + PackageOarameters.getData();
            msar01.setCode(code);
            msar01.setCreateBy(UserSession.getUser().getUsername());
            msar01.setCreateDate(PackageOarameters.getData());
            msar01.setId(UUIDUtils.getUUID());
            PackageOarameters.setRows(inInfo, msar01);
            outInfo = super.insert(inInfo, "MSAR01.insert");
            outInfo.setMsg("新增成功");
            WebSocketService.TAG_CONFIG_CACHE.clear();
            return outInfo;
        } else {
            Double lower2 = null;
            Double lowerLower2 = null;
            Double upper2 = null;
            Double upperUpper2 = null;
            if (lower != "") {
                lower2= Double.valueOf(lower);
               /* String[] l = lower.split("\\.");
                lower2 = Integer.valueOf(l[0]);*/
            }
            if (lowerLower != "") {
                lowerLower2= Double.valueOf(lowerLower);
              /*  String[] lo = lowerLower.split("\\.");
                lowerLower2 = Integer.valueOf(lo[0]);*/
            }
            if (upper != "") {
                upper2= Double.valueOf(upper);
               /* String[] up = upper.split("\\.");
                upper2 = Integer.valueOf(up[0]);*/
            }
            if (upperUpper != "") {
                upperUpper2= Double.valueOf(upperUpper);
               /* String[] u = upperUpper.split("\\.");
                upperUpper2 = Integer.valueOf(u[0]);*/
            }

            if (lower2 != null && lowerLower2 != null) {
                if (lower2 <= lowerLower2) {
                    outInfo.setMsg("低报警不得小于低低报警");
                    return outInfo;
                }
            }
            if (upper2 != null && upperUpper2 != null) {
                if (upper2 >= upperUpper2) {
                    outInfo.setMsg("高报警不得大于高高报警");
                    return outInfo;
                }
            }
            if (lower2 != null && upper2 != null) {
                if (lower2 > upper2) {
                    outInfo.setMsg("低报警不得大于高报警");
                    return outInfo;
                }
            }
            String code = inInfo.getAttr().get("dimension").toString() + inInfo.getAttr().get("unit").toString() + PackageOarameters.getData();
            msar01.setCode(code);
            if (StringUtil.isNotEmpty(inInfo.getAttr().get("id").toString())) {
                msar01.setId(inInfo.getAttr().get("id").toString());
            }
            msar01.setUpdateBy(UserSession.getUser().getUsername());
            msar01.setUpdateDate(PackageOarameters.getData());
            PackageOarameters.setRows(inInfo, msar01);
            outInfo = super.update(inInfo, "MSAR01.update");
            outInfo.setMsg("修改成功");
            WebSocketService.TAG_CONFIG_CACHE.clear();
            return outInfo;
        }
    }

//    /**
//     * @description: 报警导入
//     * @param inInfo
//     * @return EiInfo
//     * @throws
//     * @author znl
//     * @date 2021/8/4 18:05
//     * 1、只能导入xls的excel，框架限制，导入成功则插入数据，否则回滚
//     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
//     */
//    public EiInfo importForm(EiInfo inInfo) throws IOException {
//        MultipartFile file = (MultipartFile) inInfo.get("file");
//        String msg = "";
//        int status = 0;
//        if (file != null) {
//            InputStream in = file.getInputStream();
//            String[][] data = XSExcelUtils.getDataByInputStream(in, 0);
//            if (data[0].length < COLUMN_COUNT_LIXU) {
//                msg = "!:[" + (data[0].length) + "],[" + COLUMN_COUNT_LIXU + "],.";
//                inInfo.setMsg(msg);
//                inInfo.setStatus(-1);
//                return inInfo;
//            }
//            for (int i = 1; i < data.length; i++) {
//                Map map = new HashMap();
//                String applyid = data[i][0];
//                if (applyid.contains("_")) {
//                    Map<String, String> formMap = ProjectUtils.spilterResource(applyid);
//                    if (!ProjectUtils.isProjectResource(formMap)) {
//                        continue;
//                    } else {
//                        applyid = formMap.get(ProjectUtils.KEY_RESOURCE_NAME);
//                    }
//                }
//
//                MSAR01 msar01 = new MSAR01();
//                Map map1 = getMap(null);
//                msar01.setDimension(data[i][0]);
//                if (!map1.containsKey(msar01.getDimension())) {
//                    status = -1;
//                    break;
//                }
//                msar01.setDimensionName(map1.get(msar01.getDimension()).toString());
//
//                map1 = getMap(msar01.getDimension());
//                msar01.setUnit(data[i][1]);
//                if (!map1.containsKey(msar01.getUnit())) {
//                    status = -2;
//                    break;
//                }
//                msar01.setUnitName(map1.get(msar01.getUnit()).toString());
//                msar01.setDescription(data[i][2]);
//                if (StringUtil.isNotEmpty(data[i][3])) {
//                    msar01.setLower(data[i][3]);
//                }
//                if (StringUtil.isNotEmpty(data[i][4])) {
//                    msar01.setLowerLower(data[i][4]);
//                }
//                if (StringUtil.isNotEmpty(data[i][5])) {
//                    msar01.setUpper(data[i][5]);
//                }
//                if (StringUtil.isNotEmpty(data[i][6])) {
//                    msar01.setUpperUpper(data[i][6]);
//                }
//                String code = msar01.getDimension() + msar01.getUnit() + PackageOarameters.getData();
//                msar01.setCode(code);
//                msar01.setCreateBy(UserSession.getUser().getUsername());
//                msar01.setCreateDate(PackageOarameters.getData());
//                msar01.setId(UUIDUtils.getUUID());
//                try {
//                    dao.insert("MSAR01.insert", msar01);
//                } catch (Exception e) {
//
//                    inInfo.setStatus(-1);
//                    inInfo.setMsg(ExceptionUtil.getRootCauseMessage(e));
//                    return inInfo;
//                }
//            }
//        }
//        if (status == -1) {
//            msg = "无此计量量纲，请重新填写！";
//        }
//        if (status == -2) {
//            msg = "无此计量单位，请重新填写！";
//        }
//        inInfo.setStatus(status);
//        inInfo.setMsg(msg);
//        return inInfo;
//    }

    /**
     * @description: 删除报警规则
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     * @date 2021/8/6 12:19
     * 1、从inInfo获取传入需要删除的id，然后进行删除数据
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        List<?> rows = inInfo.getBlock("result").getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            super.delete(inInfo, "MSAR01.update2");
            EiInfo outInfo = super.delete(inInfo, "MSAR01.delete");
            outInfo.setMsg("删除成功");
            return outInfo;
        } else {
            inInfo.setStatus(-1);
            inInfo.setMsg("请先选择一条记录！");
            return inInfo;
        }

    }

    /**
     * @description: 查询计量常量map
     * @param a
     * @return Map
     * @throws
     * @author znl
     * @date 2021/8/6 16:33
     * 1、查询小代码里面配置的常量进行匹配数据
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    public Map getMap(String a) {     //a计量常量
        Map map = new HashMap();
        if (StringUtil.isNotEmpty(a)) {
            a = "ms.ar.dimension." + a;
        } else {
            a = "ms.ar.dimension";
        }
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_ED_02");
        inInfo.set("codeset", a);
        EiInfo outInfo = XServiceManager.call(inInfo);
        List<HashMap> list = (List) outInfo.get("list");
        list.forEach(it -> {
            map.put(it.get("value").toString(), it.get("label").toString());
        });
        return map;
    }
}
