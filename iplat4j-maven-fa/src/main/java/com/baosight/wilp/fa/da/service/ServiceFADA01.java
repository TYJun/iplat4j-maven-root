package com.baosight.wilp.fa.da.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产档案管理逻辑类.
 * 1.查询可用的固资类型.
 * 2.固定资产档案管理初始化方法.
 * 3.固定资产档案管理查询方法.
 * 4.固定资产入账.
 * 5.固定资产删除.
 * 6.科室查询.
 * 7.设备树结构.
 * 8.设备列表.
 * 9.获取用户角色信息.
 * 10.楼补全.
 * 11.层补全.
 * 12.地点补全.
 * 13.固定资产档案发卡.
 * 14.发卡时校验rfid芯片是否存在.
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022/10/9 11:39
 */
public class ServiceFADA01 extends ServiceBase {

    /**
     * 查询可用的固资类型.
     * 1.权限判断，返回用户所在科室.
     * 2.构建分页参数.
     * 3.如果存在初始化方法中存在分页参数则取分页参数.
     * 4.带入分页参数和条件，查询未被冻结的固定资产.
     * 5.将查询到未冻结的固定资产进行回显.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/6 13:29
     * @version v5.0.0
     */
    public EiInfo queryAvailableFaInfo(EiInfo info) {
        // 1.权限判断，返回用户所在科室
        String deptName = OneSelfUtils.specifyDept();
        if (StringUtils.isNotEmpty(deptName)) {
            info.set("deptName", deptName);
        }
        // 2.构建分页参数
        Map<String, Object> pageMap = new HashMap<>(8);
        pageMap.put("offset", 0);
        pageMap.put("limit", 10);
        // 3.如果存在初始化方法中存在分页参数则取分页参数
        if (info.getBlocks().size() > 0) {
            EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
            pageMap = eiBlock.getAttr();
            if (!pageMap.containsKey("offset")) {
                pageMap.put("offset", 0);
            }
            if (!pageMap.containsKey("limit")) {
                pageMap.put("limit", 10);
            }
        }
        // 4.带入分页参数和条件，查询未被冻结的固定资产
        info.set("isCall", true);
        List resultList = dao.query("FADA01.query", info.getAttr(), (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
        int count = dao.count("FADA01.query", info.getAttr());
        pageMap.put("count", count);
        // 5.将查询到未冻结的固定资产进行回显
        info.setRows("result", resultList);
        info.setAttr(pageMap);
        return info;
    }

    /**
     * 固定资产档案管理初始化方法.
     * 1.调用本地查询方法.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/5/26 11:29
     * @version v5.0.0
     */
    @Override
    public EiInfo initLoad(EiInfo info) {
        return confirmedQuery(info);
    }

    /**
     * 固定资产档案管理查询方法.
     * 1.权限判断，返回用户所在科室.
     * 2.构建分页参数.
     * 3.带入分页参数和条件，查询固定资产信息.
     * 4.将查询到的固定资产信息进行回显.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/5/26 11:33
     * @version v5.0.0
     */
    @Override
    public EiInfo query(EiInfo info) {
        EiInfo outInfo = super.query(info, "FADA01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultB", "resultB");
        return outInfo;
    }


    /**
     * 固定资产档案管理删除方法.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/1 16:15
     * @version v5.0.0
     */
    public EiInfo remove(EiInfo info) {
        if (info.get("faInfoId") != null) {
            List<String> faInfoIdList = (List<String>) info.get("faInfoId");
            int count = dao.delete("FADA01.remove", faInfoIdList);
            if (count == 0) {
                info.setMsg("删除失败");
            } else {
                info.setMsg("删除成功，删除" + count + "条数据");
            }
        }
        return info;
    }

    /**
     * 科室查询.
     * 1.获取参数,处理参数.
     * 2.调用微服务接口S_AC_FW_05，获取科室信息.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/10 15:09
     * @version v5.0.0
     */
    public EiInfo queryDept(EiInfo info) {
        EiBlock block = info.getBlock("inqu_status");
        Map<String, String> map = new HashMap<>();
        if (block != null) {
            List<Map<String, String>> list = block.getRows();
            if (CollectionUtils.isNotEmpty(list)) {
                map = list.get(0);
            }
        }
        List<Map<String, String>> maps = dao.query("FADA01.queryDept", map);
        info.setRows("dept", maps);
        return info;
    }

    /**
     * 查询资产表中存在的科室
     *
     * @param info
     * @return
     */
    public EiInfo queryFaInfoDept(EiInfo info) {
        EiBlock block = info.getBlock("inqu_status");
        Map<String, String> map = new HashMap<>();
        if (block != null) {
            List<Map<String, String>> list = block.getRows();
            if (CollectionUtils.isNotEmpty(list)) {
                map = list.get(0);
            }
        }
        List<Map<String, String>> maps = dao.query("FADA01.queryFaInfoDept", map);
        info.setRows("dept", maps);
        return info;
    }

    /**
     * 科室查询.
     * 1.获取参数,处理参数.
     * 2.调用微服务接口S_AC_FW_05，获取科室信息.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/10 15:09
     * @version v5.0.0
     */
    public EiInfo queryDeptNoPage(EiInfo info) {
        // 1.获取参数,处理参数
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
        // 2.调用微服务接口S_AC_FW_05，获取科室信息
        map.remove("limit");
        EiInfo queryDept = BaseDockingUtils.getDeptAll(map);
        List<Map<String, String>> result = (List<Map<String, String>>) queryDept.getAttr().get("result");
        queryDept.addBlock("dept").addRows(result);
        return queryDept;
    }

    /**
     * 获取用户角色信息.
     * 1、获取inInfo传来的参数，同时放入pMap中.
     * 2、以pMap作为参数执行 DMXX01.getUserRole 进行工单状态信息的查询.
     * 3、将获取的list信息作为role置于 EiInfo 中并返回.
     *
     * @Title: getUserRole
     * @Param EiInfo
     * @return: EiInfo
     * @version v5.0.0
     * @todo 通过框架获取用户角色【暂无对应接口】
     */
    public EiInfo getUserRole(EiInfo info) {
        /*
         * 1、获取inInfo传来的参数，同时放入pMap中.
         */
        Map<String, String> pMap = new HashMap<>();
        // 通过PlatApplicationContext 获取 platSchema 属性的值。
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        pMap.put("workNo", info.getString("workNo"));
        pMap.put("dataGroupCode", info.getString("dataGroupCode"));
        pMap.put("platSchema", platSchema);
        /*
         * 2、以pMap作为参数执行 DMXX01.getUserRole 进行工单状态信息的查询.
         */
        List<Map<String, String>> list = dao.query("DMXX01.getUserRole", pMap);
        EiInfo outInfo = new EiInfo();
        /*
         * 3、将获取的list信息作为role置于 EiInfo 中并返回.
         */
        outInfo.set("role", list == null || list.size() == 0 ? null : list);
        return outInfo;
    }

    /**
     * 楼补全.
     * 1、获取传过来的参数.
     * 2、调用微服务接口S_AC_FW_13，获取楼信息.
     *
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/10 15:11
     */
    public EiInfo selectSpotBuildingName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        String building = inInfo.get("newBuild") == null ? "" : inInfo.getString("newBuild"); // 楼
        /*
         * 2. 调用微服务接口S_AC_FW_13，获取楼信息。
         */
        //调用微服务接口S_AC_FW_13查询楼信息
        List<Map<String, Object>> list = BaseDockingUtils.getBuilding(building);
        return CommonUtils.BuildOutEiInfo("building", list);
    }

    /**
     * 层补全.
     * 1、获取传过来的参数.
     * 2、调用微服务接口S_AC_FW_14，获取层信息.
     *
     * @throws
     * @Title: selectSpotFloorName
     * @Description: 层补全
     * @param: @param inInfo
     * building ： 层
     * floor ： 层
     * @param: @return
     * @return: EiInfo
     * floor ： 层
     */
    public EiInfo selectSpotFloorName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        String building = "";
        String floor = "";
        // 判断楼是否为空，是与否从不同的地方取值.
        if (inInfo.get("newBuild") == null) {
            building = inInfo.getMsg();
        } else {
            building = inInfo.getString("newBuild");
        }
        floor = inInfo.getString("newFloor"); // 层
        /*
         * 2、调用微服务接口S_AC_FW_14，获取层信息.
         */
        List<Map<String, Object>> list = BaseDockingUtils.getFloor(building, floor);
        return CommonUtils.BuildOutEiInfo("floor", list);
    }

    /**
     * 地点补全.
     * 1.获取传过来的参数.
     * 2.调用微服务接口S_AC_FW_15，获取地点信息(房间、地点编码、地点名称).
     *
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/10/10 15:20
     * @version v5.0.0
     */
    public EiInfo selectSpotRoomName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        String building = "";
        String floor = "";
        String room = "";
        // 判断楼和层是否为空，是与否从不同的地方取值.
        if (inInfo.get("newBuild") == null || inInfo.get("newFloor") == null) {
            floor = inInfo.getMsg();
            building = inInfo.getName();
        } else {
            building = inInfo.getString("newBuild");
            floor = inInfo.getString("newFloor");
        }
        room = inInfo.getString("newGoodsLocation");
        /*
         * 2、调用微服务接口S_AC_FW_15，获取地点信息(房间、地点编码、地点名称).
         */
        List<Map<String, Object>> list = BaseDockingUtils.getRoom(building, floor, room);
        for (Map<String, Object> pMap : list) {
            pMap.put("reqSpotName", pMap.get("room"));
            pMap.put("spotNum", pMap.get("spot_num"));
            pMap.put("spotName", pMap.get("spot_name"));
        }
        return CommonUtils.BuildOutEiInfo("room", list);
    }

    /**
     * 固定资产档案发卡.
     * 1.构建参数集合.
     * 2.获取前端参数.
     * 3.如果该固定资产已经绑定，则将别的资产rfid清空.
     * 4.如果芯片数量为一，再进行发卡否则不予发卡.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/15 15:34
     * @version v5.0.0
     */
    public EiInfo billing(EiInfo info) {
        // 1.构建参数集合
        Map<String, Object> params = new HashMap<>(8);
        // 2.获取前端参数
        Object rfidCodeObj = info.get("rfidCode");
        if (rfidCodeObj == null) {
            return info;
        }
        List<String> rfidCode = (List<String>) rfidCodeObj;
        if (rfidCode.size() > 1) {
            info.setStatus(-1);
            info.setMsg("资产无法绑定多个芯片!");
            return info;
        }
        // 3.如果该固定资产已经绑定，则将别的资产rfid清空
        if (StringUtils.isNotEmpty(info.getString("oldFaInfoId"))) {
            dao.update("FADA01.updateRfidByFaInfoId", info.getAttr());
        }
        // 4.如果芯片数量为一，再进行发卡否则不予发卡

        params.put("faInfoId", info.getString("faInfoId"));
        params.put("rfidCode", rfidCode.get(0));
        int count = dao.update("FADA01.billing", params);
        if (count == 0) {
            info.setMsg("发卡失败");
        } else {
            info.setMsg("发卡成功");
        }
        return info;
    }

    /**
     * 发卡时回显rfid芯片信息.
     * 1.获取前端发卡的rfid.
     * 2.查询rfid芯片.
     * 3.判断rfid芯片是否注册并返回结果.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/15 11:37
     * @version v5.0.0
     */
    public EiInfo existsRFID(EiInfo info) {
        // 1.获取前端发卡的rfid
        List<String> rfidCodeList = (List<String>) info.get("rfidCode");
        // 2.查询rfid芯片
        List<Map<String, Object>> rfidList = dao.query("FADA01.existsRFID", rfidCodeList.get(0));
        // 3.判断rfid芯片是否注册并返回结果
        if (CollectionUtils.isNotEmpty(rfidList)) {
            info.set("faInfoId", rfidList.get(0).get("faInfoId"));
            info.set("existsRFID", false);
        } else {
            info.set("faInfoId", "");
            info.set("existsRFID", true);
        }
        return info;
    }


    // 另起炉灶

    /**
     * 查询待审批资产信息
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/2 17:13
     */
    public EiInfo unconfirmedQuery(EiInfo info) {
        info.setCell("inqu_status", 0, "inAccountStatus", "unconfirmed");
        EiInfo outInfo = super.query(info, "FADA01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultA", "resultA");
        return outInfo;
    }

    /**
     * 查询已审批资产信息
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/2 17:14
     */
    public EiInfo confirmedQuery(EiInfo info) {
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            Map<String, String> row = eiBlock.getRow(0);
            String deptName = row.get("deptName");
            if (StringUtils.isNotEmpty(deptName)) {
                String[] split = deptName.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
                }
                String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
                info.setCell("inqu_status", 0, "deptName", param);
            }
        }
        EiInfo outInfo = super.query(info, "FADA01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultB", "resultB");
<<<<<<< .mine
        //页面显示资产原值总计、资产数量总计数据
        EiBlock infoBlock = info.getBlock("inqu_status");
        Map inquStatus = new HashMap<>();
        if (infoBlock!=null){
            inquStatus = infoBlock.getRow(0);
        }
        List<Map<String,Object>> query =(List<Map<String,Object>>)dao.query("FADA01.querySUMFaInfoDOInfo", inquStatus);
||||||| .r17529
        List<Map<String,Object>> query =(List<Map<String,Object>>)dao.query("FADA01.querySUMFaInfoDOInfo", null);
=======
        List<Map<String, Object>> query = (List<Map<String, Object>>) dao.query("FADA01.querySUMFaInfoDOInfo", null);
>>>>>>> .r17702
        if (info.getBlocks().size() > 0) {
            if (info.getBlock("resultB") != null) {
                if ("50".equals(info.getBlock("resultB").get("limit"))) {
                    outInfo.addBlock("resultB").set(EiConstant.limitStr, 50);
                } else if ("100".equals(info.getBlock("resultB").get("limit"))) {
                    outInfo.addBlock("resultB").set(EiConstant.limitStr, 100);
                } else if ("500".equals(info.getBlock("resultB").get("limit"))) {
                    outInfo.addBlock("resultB").set(EiConstant.limitStr, 500);
                } else if ("1000".equals(info.getBlock("resultB").get("limit"))) {
                    outInfo.addBlock("resultB").set(EiConstant.limitStr, 1000);
                }
            }
        }
        // 1.获取参数,处理参数
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
        // 2.调用微服务接口S_AC_FW_05，获取科室信息
        map.remove("limit");
        List<Map<String, String>> maps = dao.query("FADA01.queryDept", map);
        outInfo.setRows("dept", maps);
        outInfo.setRows("inqu_status", query);
        return outInfo;
    }

    /**
     * 查询已审批资产信息
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/2 17:14
     */
    public EiInfo confirmedQueryC(EiInfo info) {
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            Map<String, String> row = eiBlock.getRow(0);
            String deptName = row.get("deptName");
            if (StringUtils.isNotEmpty(deptName)) {
                String[] split = deptName.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = "dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
                }
                String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
                info.setCell("inqu_status", 0, "deptName", param);
            }
        }
        EiInfo outInfo = super.query(info, "FADA01.queryFaInfoDOInfo", new FaInfoDO(), false, null, null, "resultC", "resultC");
        // 1.获取参数,处理参数
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
        // 2.调用微服务接口S_AC_FW_05，获取科室信息
        map.remove("limit");
        List<Map<String, String>> maps = dao.query("FADA01.queryDept", map);
        info.setRows("dept", maps);
        outInfo.addBlock("dept").addRows(maps);
        if (info.getBlocks().size() > 0) {
            if (info.getBlock("resultC") != null) {
                if ("50".equals(info.getBlock("resultC").get("limit"))) {
                    outInfo.addBlock("resultC").set(EiConstant.limitStr, 50);
                } else if ("100".equals(info.getBlock("resultC").get("limit"))) {
                    outInfo.addBlock("resultC").set(EiConstant.limitStr, 100);
                } else if ("500".equals(info.getBlock("resultC").get("limit"))) {
                    outInfo.addBlock("resultC").set(EiConstant.limitStr, 500);
                } else if ("1000".equals(info.getBlock("resultC").get("limit"))) {
                    outInfo.addBlock("resultC").set(EiConstant.limitStr, 1000);
                }
            }
        }
        return outInfo;
    }

    /**
     * 资产批量审批
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/4 11:44
     */
    public EiInfo batchRecorded(EiInfo info) {
        // 获取批量审批集合
        if (info.get("faInfo") != null) {
            List<Map<String, String>> faInfo = (List<Map<String, String>>) info.get("faInfo");
            StringBuilder msgStr = new StringBuilder("总计审批" + faInfo.size() + "条" + "\n");
            List<Map<String, String>> deptNumAndInstallLocationNum = new ArrayList<>();
            List<Map<String, String>> failMsgList;
            List<Map<String, String>> successMsgList;
            List<String> idList = new ArrayList<>();
            // 对批量审批集合进行过滤分组
            Map<String, List<Map<String, String>>> collect = faInfo.stream().collect(Collectors.groupingBy(map -> map.get("deptNum") + "&" + map.get("installLocationNum")));
            collect.forEach((k, v) -> deptNumAndInstallLocationNum.add(new HashMap<String, String>(2) {{
                put("deptNum", k.split("&")[0]);
                put("installLocationNum", k.split("&")[1]);
            }}));
            // 查询过滤后的科室地点是否正在盘点
            List<Map<String, String>> takingStockList = dao.query("FADA01.queryTakingStockMsg", deptNumAndInstallLocationNum);
            // 获取返回科室地点组合字段
            for (Map<String, String> takingStockMap : takingStockList) {
                failMsgList = collect.get(takingStockMap.get("deptNumAndInstallLocationNum"));
                msgStr.append(takingStockMap.get("deptName") + takingStockMap.get("installLocationName") + "正在盘库，审批失败" + failMsgList.size() + "条；" + "\n");
                collect.remove(takingStockMap.get("deptNumAndInstallLocationNum"));
            }
            // 统计出过滤的科室地点及数量
            Iterator<Map.Entry<String, List<Map<String, String>>>> key = collect.entrySet().iterator();
            while (key.hasNext()) {
                Map.Entry<String, List<Map<String, String>>> next = key.next();
                for (Map<String, String> map : next.getValue()) {
                    // 再通过组合字段过滤出可以进行入库的
                    idList.add(map.get("id"));
                }
                msgStr.append(next.getValue().get(0).get("deptName") + next.getValue().get(0).get("installLocationName") + "，审批成功" + next.getValue().size() + "条；" + "\n");
            }
            if (CollectionUtils.isNotEmpty(idList)) {
                dao.update("FADA01.batchRecorded", idList);
                dao.insert("FADA01.batchInsertFaModify", idList);
            }
            // 统计总量
            info.setMsg(msgStr.toString());
        } else {
            info.setMsg("请刷新一下页面稍后再试");
            info.setStatus(-1);
        }
        return info;
    }

    /**
     * 批量删除
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/5 9:44
     */
    public EiInfo batchRemove(EiInfo info) {
        // 传入id不为空，进行批量删除操作
        if (info.get("idsList") != null) {
            List<String> idsList = (List<String>) info.get("idsList");
            int delete = dao.delete("FADA01.batchRemove", idsList);
            info.setMsg("成功删除" + delete + "条数据");
        } else {
            info.setMsg("请刷新一下页面稍后再试");
            info.setStatus(-1);
        }
        return info;
    }

    /**
     * 批量驳回
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/5 11:22
     */
    public EiInfo batchRevocation(EiInfo info) {
        if (info.get("idsList") != null) {
            List<String> idsList = (List<String>) info.get("idsList");
            int update = dao.update("FADA01.batchRevocation", idsList);
            info.setMsg("成功驳回" + update + "条数据");
        } else {
            info.setMsg("请刷新一下页面稍后再试");
            info.setStatus(-1);
        }
        return info;
    }

    /**
     * 供应商
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/19 13:36
     */
    public EiInfo querySupplier(EiInfo info) {
        // 1.调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "supplier");
        // 获取blockId
        String blockId = info.getBlockIds();
        // 2.如果blockId相等
        if (blockId.equals("inqu_status,supplier")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("supplier");
            // 3.获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 4.获取集合中的数据
            String name = (String) listMap.get(0).get("supplierName");
            // 设置supplierName
            map.put("supplierName", name);
            // 实例化list
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 5.调用改造供应商接口并返回
            EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
            // 6.如果供应商信息
            if (outInfo.getBlock("supplier") != null) {
                // 7.获取供应商信息
                list = outInfo.getBlock("supplier").getRows();
                // 8.如果list为空
                if (list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 9.遍历供应商信息
                for (Map<String, Object> map2 : list) {
                    // 将key：supplierCode改为key：supplierNum
                    map2.put("surpNum", map2.get("supplierCode"));
                    map2.put("surpName", map2.get("supplierName"));
                }
                // 获取供应商信息总数
                count = (int) outInfo.getBlock("supplier").getAttr().get("count");
                // 10.返回封装的方法：构建返回结果EiInfo
                return CommonUtils.BuildOutEiInfo(info, "supplier", CommonUtils.createBlockMeta(list.get(0)), list, count);
            } else {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
        }
        // 实例化list
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 11.调用改造供应商接口并返回
        EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
        // 12.如果供应商信息
        if (outInfo.getBlock("supplier") != null) {
            // 13.获取供应商信息
            list = outInfo.getBlock("supplier").getRows();
            // 14.如果list为空
            if (list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 15.遍历供应商信息
            for (Map<String, Object> map2 : list) {
                // 将key：supplierCode改为key：supplierNum
                map2.put("surpNum", map2.get("supplierCode"));
                map2.put("surpName", map2.get("supplierName"));
            }
            // 获取供应商信息总数
            count = (int) outInfo.getBlock("supplier").getAttr().get("count");
            // 16.返回封装的方法：构建返回结果EiInfo
            return CommonUtils.BuildOutEiInfo(info, "supplier", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
        }
    }

    /**
     * 销账
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/18 23:15
     */
    public EiInfo writeOff(EiInfo info) {
        if (info.get("faInfoId") != null) {
            List<Map<String, Object>> faInfoId = (List<Map<String, Object>>) info.get("faInfoId");
            int update = dao.update("FADA01.writeOff", faInfoId);
            // 报废抛帐
            dao.insert("FAAP01.scrapAccount", faInfoId);
            info.setMsg("成功销账" + update + "条");
        }
        return info;
    }

    /**
     * 写入芯片信息
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2023/3/15 14:46
     */
    public EiInfo readInRFID(EiInfo info) {
        if (info.get("rfid") != null) {
            List<Map<String, Object>> rfid = (List<Map<String, Object>>) info.get("rfid");
            if (CollectionUtils.isNotEmpty(rfid)) {
                dao.insert("FADA01.readInRFID", rfid);
                // 先将存在的芯片删除
                dao.update("FADA01.clearRFID", rfid);
                // 更新芯片信息
                for (int i = 0; i < rfid.size(); i++) {
                    dao.update("FADA01.updateRFIDByPrint", rfid.get(i));
                }
            }
        }
        return info;
    }

    /**
     * 查询资产类别
     *
     * @param info
     * @return
     */
    public EiInfo queryGoodsClassifyName(EiInfo info) {
        List list = dao.query("FADA01.queryGoodsClassifyName", new HashMap<>());
        info.setRows("goodsClassifyCode", list);
        return info;
    }

    /**
     * 查询类组
     *
     * @param info
     * @return
     */
    public EiInfo queryGoodsGoodsTypeName(EiInfo info) {
        List list = dao.query("FADA01.queryGoodsGoodsTypeName", new HashMap<>());
        info.setRows("goodsTypeCode", list);
        return info;
    }
}