package com.baosight.wilp.ms.pl.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import org.apache.commons.collections.CollectionUtils;
import com.baosight.wilp.ms.common.util.PackageOarameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author znl
 * @title: ServiceMSPL01
 * @projectName iplat_v5_monitor
 * @description: 点位参数配置页面（页面初始化方法  查询列表   添加数据  删除参数
 * 修改报警规则参数绑定   修改数据    修改参数分类绑定 修改参数分类绑定）
 * @date 2021/8/9 10:08
 */
public class ServiceMSPL01 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:18
     * 1、通过inInfo获取查询的参数 2、通过dao.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo 4、返回inInfo参数
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo = super.initLoad(inInfo, new MSPL01());
        return inInfo;
    }

    /**
     * @title: 查询列表
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:15
     * 1、通过inInfo获取查询的参数 2、通过super.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进outInfo 4、返回outInfo参数
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**
         * 通过inInfo获取查询的参数
         */
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        /**
         * 判断传入参数是否为空
         */
        if (o.get("paramEnableStatus") != null) {
            String paramEnableStatus = o.get("paramEnableStatus").toString();
            if (paramEnableStatus.trim().length() == 0) {
                o.remove("paramEnableStatus");
            }
        }
        if (o.get("name") != null) {
            String name = o.get("name").toString();
            if (name.trim().length() == 0) {
                o.remove("name");
            }
        }
        if (o.get("tag") != null) {
            String tag = o.get("tag").toString();
            if (tag.trim().length() == 0) {
                o.remove("tag");
            }
        }
        if (o.get("isSync") != null) {
            String isSync = o.get("isSync").toString();
            if (isSync.trim().length() == 0) {
                o.remove("isSync");
            }
        }
        if (o.get("alarmEnableStatus") != null) {
            String alarmEnableStatus = o.get("alarmEnableStatus").toString();
            if (alarmEnableStatus.trim().length() == 0) {
                o.remove("alarmEnableStatus");
            }
        }
        if (o.get("relation") != null) {
            String relation = o.get("relation").toString();
            if (relation.trim().length() == 0) {
                o.remove("relation");
            }
        }
        /**
         * 通过super.query方法把查询参数传入
         */
        EiInfo outInfo = super.query(inInfo, "MSPL01.query", new MSPL01());
        return outInfo;

    }

    /**
     * @title: 添加数据
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:15
     * 1、通过inInfo获取提交的参数   2、通过判断前端传进来的type是否是add
     * 3、如果是add新增数据否则修改数据
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        /**
         * 通过inInfo获取提交的参数
         */
        MSPL01 mspl01 = new MSPL01();
        Map map = getMap(null);
        /**
         * 判断传入参数是否为空
         */
        if (StringUtil.isNotEmpty(inInfo.get("dimension").toString())) {
            mspl01.setDimension(inInfo.getAttr().get("dimension").toString());
            mspl01.setDimensionName(map.get(inInfo.getAttr().get("dimension").toString()).toString());
            map = getMap(inInfo.getAttr().get("dimension").toString());
        }

        if (StringUtil.isNotEmpty(inInfo.get("unit").toString())) {
            mspl01.setUnit(inInfo.getAttr().get("unit").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("unit").toString())) {
            mspl01.setUnitName(map.get(inInfo.getAttr().get("unit").toString()).toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("type").toString())) {
            mspl01.setType(inInfo.getAttr().get("type").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("tag").toString())) {
            //判断tag点是否存在
            if(getListByTag(inInfo.get("tag").toString())){
                inInfo.setMsg("该参数Tag点已存在系统，请修改");
                return inInfo;
            }
            mspl01.setTag(inInfo.getAttr().get("tag").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("isSync").toString())) {
            mspl01.setIsSync(inInfo.get("isSync").toString());
        } else {
            mspl01.setParamEnableStatus("0");
        }
        if (StringUtil.isNotEmpty(inInfo.get("name").toString())) {
            mspl01.setName(inInfo.get("name").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("description").toString())) {
            mspl01.setDescription(inInfo.get("description").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("trueValueLabel").toString())) {
            mspl01.setTrueValueLabel(inInfo.get("trueValueLabel").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("falseValueLabel").toString())) {
            mspl01.setFalseValueLabel(inInfo.get("falseValueLabel").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("alarmValue").toString())) {
            mspl01.setAlarmValue(inInfo.get("alarmValue").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("alarmEnableStatus").toString())) {
            mspl01.setAlarmEnableStatus(inInfo.get("alarmEnableStatus").toString());
        } else {
            mspl01.setAlarmEnableStatus("0");
        }

        if (StringUtil.isNotEmpty(inInfo.get("paramEnableStatus").toString())) {
            mspl01.setParamEnableStatus(inInfo.get("paramEnableStatus").toString());
        } else {
            mspl01.setParamEnableStatus("0");
        }
        if (StringUtil.isNotEmpty(inInfo.get("deadTime").toString())) {
            mspl01.setDeadTime(inInfo.get("deadTime").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("parentId").toString())) {
            mspl01.settParamClassifyId(inInfo.get("parentId").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue01").toString())) {
            mspl01.setEnumValue01(inInfo.get("enumValue01").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue01Label").toString())) {
            mspl01.setEnumValue01Label(inInfo.get("enumValue01Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue02").toString())) {
            mspl01.setEnumValue02(inInfo.get("enumValue02").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue02Label").toString())) {
            mspl01.setEnumValue02Label(inInfo.get("enumValue02Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue03").toString())) {
            mspl01.setEnumValue03(inInfo.get("enumValue03").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue03Label").toString())) {
            mspl01.setEnumValue03Label(inInfo.get("enumValue03Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue04").toString())) {
            mspl01.setEnumValue04(inInfo.get("enumValue04").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("isWriteLog").toString())) {
            mspl01.setIsWriteLog(inInfo.get("isWriteLog").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue04Label").toString())) {
            mspl01.setEnumValue04Label(inInfo.get("enumValue04Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue05").toString())) {
            mspl01.setEnumValue05(inInfo.get("enumValue05").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue05Label").toString())) {
            mspl01.setEnumValue05Label(inInfo.get("enumValue05Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue06").toString())) {
            mspl01.setEnumValue05(inInfo.get("enumValue06").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue06Label").toString())) {
            mspl01.setEnumValue05Label(inInfo.get("enumValue06Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("significantBit").toString())) {
            mspl01.setSignificant_bit(inInfo.get("significantBit").toString());
        }

        /**
         * 根据type判断是增加还是更新
         * see新增
         */
        if (inInfo.getAttr().get("types").toString().equals("add")) {
            mspl01.setId(UUIDUtils.getUUID());
            PackageOarameters.setRows(inInfo, mspl01);
            EiInfo outInfo = super.insert(inInfo, "MSPL01.insert");
            WebSocketService.TAG_CONFIG_CACHE.remove(mspl01.getTag());
            GatherServer.TAG_CONFIG_CACHE.remove(mspl01.getTag());
            outInfo.setMsg("新增成功");
            return outInfo;
        } else {
            if (StringUtil.isNotEmpty(inInfo.getAttr().get("id").toString())) {
                mspl01.setId(inInfo.getAttr().get("id").toString());
            }
            PackageOarameters.setRows(inInfo, mspl01);
            EiInfo outInfo = super.update(inInfo, "MSPL01.update");
            WebSocketService.TAG_CONFIG_CACHE.remove(mspl01.getTag());
            GatherServer.TAG_CONFIG_CACHE.remove(mspl01.getTag());
            outInfo.setMsg("修改成功");
            return outInfo;
        }
    }

    /**
     * @title: 删除参数
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author 孔帅博
     * @date 2021-09-08 15:16
     * 1、从inInfo获取传入需要删除的id，然后进行删除数据
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        List<?> rows = inInfo.getBlock("result").getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            EiInfo outInfo = super.delete(inInfo, "MSPL01.delete");
            outInfo.setMsg("删除成功");
            return outInfo;
        } else {
            inInfo.setStatus(-1);
            inInfo.setMsg("请先选择一条记录！");
            return inInfo;
        }

    }

    /**
     * @title: 修改报警规则参数绑定
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author 孔帅博
     * @date 2021-09-08 15:17
     * 从inInfo获取传入需要修改的id和填入的parentIds，然后进行修改数据数据
     */
    public EiInfo updateTmsar(EiInfo inInfo) {
        /**
         * 从inInfo获取传入需要修改的id和填入的parentIds，然后进行修改数据数据
         */
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        if (result != null) {
            List rows = result.getRows();
            for (int i = 0; i < rows.size(); i++) {
                MSPL01 mspl01 = new MSPL01();
                HashMap o = (HashMap) rows.get(i);
                mspl01.setTmsar01Id(inInfo.get("parentIds").toString());
                mspl01.setId((String) o.get("id"));
                PackageOarameters.setRows(inInfo, mspl01);
                EiInfo outInfo = super.update(inInfo, "MSPL01.updateTmsar");
                outInfo.setMsg("修改成功");
            }
            return inInfo;
        }
//        MSPL01 mspl01 = new MSPL01();
//        mspl01.setTmsar01Id(inInfo.get("parentIds").toString());
//        mspl01.setId(inInfo.get("id").toString());
//        PackageOarameters.setRows(inInfo, mspl01);
//        EiInfo outInfo = super.update(inInfo, "MSPL01.updateTmsar");
//        outInfo.setMsg("修改成功");
//        return outInfo;
        return inInfo;
    }

    /**
     * @title: 修改数据
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author 孔帅博
     * @date 2021-09-08 15:17
     * 从inInfo获取传入需要修改的数据，然后进行修改数据数据
     */
    public EiInfo updateMSPL(EiInfo inInfo) {
        MSPL01 mspl01 = new MSPL01();
        Map map = getMap(null);
        /**
         * 判断查询参数是否为空
         */
        if (StringUtil.isNotEmpty(inInfo.get("dimension").toString())) {
            mspl01.setDimension(inInfo.getAttr().get("dimension").toString());
            mspl01.setDimensionName(map.get(inInfo.getAttr().get("dimension").toString()).toString());
            map = getMap(inInfo.getAttr().get("dimension").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("unit").toString())) {
            mspl01.setUnit(inInfo.getAttr().get("unit").toString());
            mspl01.setUnitName(map.get(inInfo.getAttr().get("unit").toString()).toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("type").toString())) {
            mspl01.setType(inInfo.getAttr().get("type").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("tag").toString())) {
            mspl01.setTag(inInfo.getAttr().get("tag").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("isSync").toString())) {
            mspl01.setIsSync(inInfo.get("isSync").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("name").toString())) {
            mspl01.setName(inInfo.getAttr().get("name").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("description").toString())) {
            mspl01.setDescription(inInfo.get("description").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("trueValueLabel").toString())) {
            mspl01.setTrueValueLabel(inInfo.get("trueValueLabel").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("falseValueLabel").toString())) {
            mspl01.setFalseValueLabel(inInfo.get("falseValueLabel").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("alarmValue").toString())) {
            mspl01.setAlarmValue(inInfo.get("alarmValue").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("alarmEnableStatus").toString())) {
            mspl01.setAlarmEnableStatus(inInfo.get("alarmEnableStatus").toString());
        }

        if (StringUtil.isNotEmpty(inInfo.get("paramEnableStatus").toString())) {
            mspl01.setParamEnableStatus(inInfo.get("paramEnableStatus").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("deadTime").toString())) {
            mspl01.setDeadTime(inInfo.get("deadTime").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue01").toString())) {
            mspl01.setEnumValue01(inInfo.get("enumValue01").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue01Label").toString())) {
            mspl01.setEnumValue01Label(inInfo.get("enumValue01Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("isWriteLog").toString())) {
            mspl01.setIsWriteLog(inInfo.get("isWriteLog").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue02").toString())) {
            mspl01.setEnumValue02(inInfo.get("enumValue02").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue02Label").toString())) {
            mspl01.setEnumValue02Label(inInfo.get("enumValue02Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue03").toString())) {
            mspl01.setEnumValue03(inInfo.get("enumValue03").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue03Label").toString())) {
            mspl01.setEnumValue03Label(inInfo.get("enumValue03Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue04").toString())) {
            mspl01.setEnumValue04(inInfo.get("enumValue04").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue04Label").toString())) {
            mspl01.setEnumValue04Label(inInfo.get("enumValue04Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue05").toString())) {
            mspl01.setEnumValue05(inInfo.get("enumValue05").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue05Label").toString())) {
            mspl01.setEnumValue05Label(inInfo.get("enumValue05Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue06").toString())) {
            mspl01.setEnumValue05(inInfo.get("enumValue06").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("enumValue06Label").toString())) {
            mspl01.setEnumValue05Label(inInfo.get("enumValue06Label").toString());
        }
        if (StringUtil.isNotEmpty(inInfo.get("significantBit").toString())) {
            mspl01.setSignificant_bit(inInfo.get("significantBit").toString());
        }
        mspl01.setId(inInfo.getAttr().get("id").toString());
        PackageOarameters.setRows(inInfo, mspl01);
        EiInfo outInfo = super.update(inInfo, "MSPL01.updateTmspl");
        outInfo.setMsg("修改成功");
        String tag = inInfo.getAttr().get("tag").toString().trim();
        WebSocketService.TAG_CONFIG_CACHE.remove(tag);
        GatherServer.TAG_CONFIG_CACHE.remove(tag);
        return outInfo;

    }
    /**
     * 参数状态改为启用
     * @param inInfo
     * @return
     */
    public EiInfo editOpen (EiInfo inInfo) {
        MSPL01 mspl01 = new MSPL01();
        Map map = new HashMap();
        EiInfo outInfo = null;
        List<Map> list2 = (List<Map>) inInfo.getAttr().get("list");
        for (int i = 0; i <list2.size(); i++) {
            /**
             * 获取前台传的id
             */
            String id = list2.get(i).get("id").toString();
            map.put("id", id);
            /**
             * 将id塞入EiInfo里然后下面进行sql条件查询
             */
            PackageOarameters.setRows(inInfo, map);
            outInfo = super.update(inInfo, "MSPL01.updateTmspl2");
        }
        outInfo.setMsg("提交成功");
        return outInfo;
    }
    /**
     * 参数状态改为启用
     * @param inInfo
     * @return
     */
    public EiInfo editOpen1 (EiInfo inInfo) {
        MSPL01 mspl01 = new MSPL01();
        Map map = new HashMap();
        EiInfo outInfo = null;
        List<Map> list2 = (List<Map>) inInfo.getAttr().get("list");
        for (int i = 0; i <list2.size(); i++) {
            /**
             * 获取前台传的id
             */
            String id = list2.get(i).get("id").toString();
            map.put("id", id);
            /**
             * 将id塞入EiInfo里然后下面进行sql条件查询
             */
            PackageOarameters.setRows(inInfo, map);
            outInfo = super.update(inInfo, "MSPL01.updateTmspl3");
        }
        outInfo.setMsg("提交成功");
        return outInfo;
    }

    /**
     * @title: 修改参数分类绑定
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author 孔帅博
     * @date 2021-09-08 15:17
     * 从inInfo获取传入需要修改的id和填入的值parentId数据，然后进行修改数据数据
     */
    public EiInfo updateParameClassifyId(EiInfo inInfo) {
        MSPL01 mspl01 = new MSPL01();
        mspl01.settParamClassifyId(inInfo.get("parentId").toString());
        mspl01.setId(inInfo.get("id").toString());
        PackageOarameters.setRows(inInfo, mspl01);
        EiInfo outInfo = super.update(inInfo, "MSPL01.updateParameClassifyId");
        outInfo.setMsg("修改成功");
        return outInfo;
    }


    /**
     * @return Map
     * @throws
     * @description: 查询计量常量map
     * @author znl
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/6 16:33
     * 1、如果传入的值没有则判断是第一分类查询否则为第二类查询
     */
    public Map getMap(String a) {
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

    /**
     * 根据tag点判断该tag是否存在表里
     * @Title: getListByTag
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param tmsdc01Name ： 设备名称
     * @param:  @return
     * @return: tmsdc01Id
     * @throws
     */
    private Boolean getListByTag(String tag) {
        List<String> tags = dao.query("MSDC01.getListByTag", tag);
        if(tags.isEmpty()) {
            return false;
        }
        return true;
    }

}
