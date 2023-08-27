package com.baosight.wilp.dm.xf.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.ys.domain.ResultData;
import com.baosight.wilp.dm.common.DMResultData;
import com.baosight.wilp.dm.fy.service.ServiceDMFY01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对接前端APP接口

 *
 * @Title: AppLinkService.java
 * @ClassName: AppLinkService
 * @Package：com.baosight.wilp.dm.xf.service
 * @author: fangzekai
 * @date: 2022/01/21 09:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class AppLinkService {
    private static final Logger logger = LoggerFactory.getLogger(AppLinkService.class);
    /**
     *  一、APP获取地点信息的接口.
     *  返回了楼、层、地点。
     * 1、对接口入参进行接收并对部分参数进行实例化.
     * 2、调用基础框架微服务接口S_AC_FW_13查询楼.
     *     将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
     *     若状态码不<0则取出获取结果存值列表部分.
     * 3、对获取到的building列表进行遍历.
     *     用它其中每一个楼的信息去调用基础框架微服务接口S_AC_FW_14获取楼中的每一层信息.
     *     将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
     *     若状态码不<0则取出获取结果存值列表部分.
     * 4、对获取到的floor列表进行遍历.
     *     结合最初的层和它其中每一个层的信息去调用基础框架微服务接口S_AC_FW_15获取楼层中的相关地点信息.
     *     将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
     *     若状态码不<0则取出获取结果存值列表部分.
     * 5、（因前端格式的需求，需要对取出的结果的键值key以其他键值替换,故仍需要此步骤）.
     *     对获取到的spot列表进行遍历.
     *     将获取的地点编码和地点名称重新用两个键值"value"和"text"接收.
     *
     * @Title getCanSelectRoom
     * @param request
     * dataGroupCode : 院区账套，暂未启用
     * @param response
     * @return
     */
    public DMResultData getCanSelectRoom(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        EiInfo inInfo = new EiInfo();
        // 获取前端传来的workNo工号 .
        String workNo = request.getParameter("workNo"); // 工号.
        inInfo.set("workNo", workNo);
        // 实例化三个参数，building、floor、spot 用来分别接收查询得到的列表.
        List<Map<String, Object>> building = new ArrayList();
        List<Map<String, Object>> floor = new ArrayList();
        List<Map<String, Object>> spot = new ArrayList();
        try {
            /*
             *  2、调用本地服务DMXF01.queryCanSelectRoomBuilding查询可选房的楼.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set(EiConstant.serviceName, "DMXF01");
            inInfo.set(EiConstant.methodName, "queryCanSelectRoomBuilding");
            EiInfo buildingInfo = XLocalManager.call(inInfo);
            // 对调用结果进行判断.
            if (buildingInfo.getStatus() < 0) {
                logger.info("调用本地服务DMXF01.queryCanSelectRoomBuilding获取楼失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(buildingInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                /*
                 * 3、对获取到的building列表进行遍历.
                 *     用它其中每一个楼的信息去调用本地服务DMXF01.queryCanSelectRoomFloor获取楼中查询可选房的层..
                 *     将获取结果进行判断，若状态码<0 值判断为该接口获取层失败.
                 *     若状态码不<0则取出获取结果存值列表部分.
                 */
                building = (List<Map<String, Object>>) buildingInfo.getBlock("building").getRows();
                int buildingSize = building.size();
                for (int i = 0; i < buildingSize; i++) {
                    String buildingStr = (String) building.get(i).get("building");
                    // 调用本地服务DMXF01.queryCanSelectRoomFloor获取楼中查询可选房的层.
                    inInfo.set("building", buildingStr);
                    inInfo.set(EiConstant.serviceName, "DMXF01");
                    inInfo.set(EiConstant.methodName, "queryCanSelectRoomFloor");
                    EiInfo floorInfo = XLocalManager.call(inInfo);
                    // 对调用结果进行判断.
                    if (floorInfo.getStatus() < 0) {
                        logger.info("调用本地服务DMXF01.queryCanSelectRoomFloor获取楼中查询可选房的层失败！");
                        resultData.setRespCode("201");
                        resultData.setRespMsg(floorInfo.getMsg());
                        resultData.setSuccess(false);
                    } else {
                        /*
                         * 4、对获取到的floor列表进行遍历.
                         *     结合最初的层和它其中每一个层的信息去调用基础框架微服务接口S_AC_FW_15获取楼层中的相关地点信息.
                         *     将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
                         *     若状态码不<0则取出获取结果存值列表部分.
                         */
                        floor = (List<Map<String, Object>>) floorInfo.getBlock("floor").getRows();
                        int floorSize = floor.size();
                        for (int j = 0; j < floorSize; j++) {
                            String floorStr = (String) floor.get(j).get("floor");
                            // 调用本地服务DMXF01.queryCanSelectRoomNo获取楼层中可选房的房号.
                            inInfo.set("floor", floorStr);
                            inInfo.set(EiConstant.serviceName, "DMXF01");
                            inInfo.set(EiConstant.methodName, "queryCanSelectRoomNo");
                            EiInfo spotInfo = XLocalManager.call(inInfo);
                            // 对调用结果进行判断.
                            if (spotInfo.getStatus() < 0) {
                                logger.info("调用本地服务DMXF01.queryCanSelectRoomNo获取楼层中可选房的房号失败！");
                                resultData.setRespCode("201");
                                resultData.setRespMsg(floorInfo.getMsg());
                                resultData.setSuccess(false);
                            } else {
                                /*
                                 * 5、（因前端格式的需求，需要对取出的结果的键值key以其他键值替换,故仍需要此步骤）.
                                 *     对获取到的spot列表进行遍历.
                                 *     将获取的地点编码和地点名称重新用两个键值"value"和"text"接收.
                                 */
                                spot = (List<Map<String, Object>>) spotInfo.getBlock("roomNo").getRows();
                                int spotSize = spot.size();
                                for (int z = 0; z < spotSize; z++) {
                                    spot.get(z).put("value",spot.get(z).get("roomId"));
                                    spot.get(z).put("text",spot.get(z).get("roomNo"));
                                }
                                // 前端需要,对键值与数据进行处理.
                                floor.get(j).put("value", String.format("%03d", i + 1) + String.format("%03d", j + 1));
                                floor.get(j).put("children", spot);
                                floor.get(j).put("text", floor.get(j).get("floor"));
                            }
                        }
                        // 前端需要,对键值与数据进行处理.
                        building.get(i).put("value", String.format("%03d", i + 1) + "000");
                        building.get(i).put("children", floor);
                        building.get(i).put("text",building.get(i).get("building"));
                    }
                }
                /*
                 *  6、将结果进行返回.
                 */
                resultData.setObj(building);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
                long endTime = System.currentTimeMillis();
                logger.info("need" + (endTime - startTime) + "ms");
            }
            // 捕异常.
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }

    /**
     *  二、APP获取科室列表.
     *  1、对接口入参进行接收并对部分参数进行实例化.
     *  2、调用基础框架微服务接口S_AC_FW_05查询科室列表.
     *      将获取结果进行判断，若状态码<0 值判断为该接口获取科室列表失败.
     *      若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getDeptInfo
     * @param request
     * deptNum : 科室编码
     * deptName : 科室名称
     * datagroupCode : 院区账套
     * @param response
     * @return
     */
    public DMResultData getDeptInfo(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> deptList = new ArrayList();
        String deptNum = request.getParameter("deptNum"); // 科室编码
        String deptName = request.getParameter("deptName"); // 科室名称
        String datagroupCode = request.getParameter("datagroupCode"); // 院区账套
        try {
            /*
             *  2、调用基础框架微服务接口S_AC_FW_05查询科室列表.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取科室列表失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set("deptNum", deptNum);
            inInfo.set("deptName", deptName);
            inInfo.set("datagroupCode", datagroupCode);
            // 调用基础框架微服务S_AC_FW_05查询科室列表
            inInfo.set(EiConstant.serviceId, "S_AC_FW_05");
            EiInfo deptInfo = XServiceManager.call(inInfo);
            // 将获取结果进行判断
            if (deptInfo.getStatus() < 0) {
                logger.info("调用微服务S_AC_FW_05获取科室信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(deptInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (deptInfo.get("result")!= null){
                    deptList = (List<Map<String, Object>>) deptInfo.get("result");
                }
                resultData.setObj(deptList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
                long endTime = System.currentTimeMillis();
                logger.info("need" + (endTime - startTime) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }


    /**
     *  宿舍大屏查询所有宿舍楼.
     *   1、对部分参数进行实例化.
     *   2、调用本地服务DMXX01.queryRoomBuildingInfo查询所有宿舍楼相关信息.
     *   将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
     *   若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getAllBuildingInfo
     * @param request
     * @param response
     * @return
     */
    public DMResultData getAllBuildingInfo(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        List<Map<String, Object>> allBuildingInfo = new ArrayList();
        String building = request.getParameter("building"); // 楼
        EiInfo inInfo = new EiInfo();
        try {
            /*
             *  2、调用本地服务DMXX01.queryRoomBuildingInfo查询所有宿舍楼相关信息.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            // 调用本地服务DMXX01.queryRoomBuildingInfo查询所有宿舍楼相关信息
            inInfo.set("building", building);
            inInfo.set(EiConstant.serviceName, "DMXX01");
            inInfo.set(EiConstant.methodName, "queryRoomBuildingInfo");
            EiInfo RoomBuildingInfo = XLocalManager.call(inInfo);
            if (RoomBuildingInfo.getStatus() < 0) {
                logger.info("调用本地服务DMXX01.queryRoomBuildingInfo查询所有宿舍楼相关信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(RoomBuildingInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (RoomBuildingInfo.getBlock("buildingInfo") != null){
                    allBuildingInfo = (List<Map<String, Object>>) RoomBuildingInfo.getBlock("buildingInfo").getRows();
                }
                resultData.setObj(allBuildingInfo);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }

    /**
     *  宿舍大屏查询宿舍楼中所有的楼层.
     *   1、对部分参数进行实例化.
     *   2、调用本地服务DMXX01.queryFloorByBuilding查询所有宿舍楼层相关信息.
     *   将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
     *   若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getFloorByBuilding
     * @param request
     * @param response
     * @return
     */
    public DMResultData getFloorByBuilding(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        List<Map<String, Object>> floorList = new ArrayList();
        String building = request.getParameter("building"); // 楼
        EiInfo inInfo = new EiInfo();
        try {
            /*
             *  2、调用本地服务DMXX01.queryFloorByBuilding查询所有宿舍楼层相关信息.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            // 调用本地服务DMXX01.queryFloorByBuilding查询所有宿舍楼层相关信息.
            inInfo.set("building", building);
            inInfo.set(EiConstant.serviceName, "DMXX01");
            inInfo.set(EiConstant.methodName, "queryFloorByBuilding");
            EiInfo floorInfo = XLocalManager.call(inInfo);
            if (floorInfo.getStatus() < 0) {
                logger.info("调用本地服务DMXX01.queryFloorByBuilding查询所有宿舍楼层相关信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(floorInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (floorInfo.getBlock("floorInfo") != null){
                    floorList = (List<Map<String, Object>>) floorInfo.getBlock("floorInfo").getRows();
                }
                resultData.setObj(floorList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }

    /**
     *  宿舍大屏查询宿舍楼层中的宿舍信息以及相关入住信息.
     *   1、对部分参数进行实例化.
     *   2、调用本地服务DMXX01.getRoomAboutInfo查询宿舍楼层中的宿舍信息以及相关入住信息.
     *   将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
     *   若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getFloorByBuilding
     * @param request
     * @param response
     * @return
     */
    public DMResultData getRoomAboutInfo(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        List<Map<String, Object>> roomList = new ArrayList();
        List<Map<String, Object>> DetailRZList = new ArrayList();
        String building = request.getParameter("building"); // 楼
        String floor = request.getParameter("floor"); // 层
        EiInfo inInfo = new EiInfo();
        try {
            /*
             *  2、调用本地服务DMXX01.queryFloorRoom查询宿舍楼层中的宿舍信息.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            // 调用本地服务DMXX01.queryFloorRoom查询宿舍楼层中的宿舍信息.
            inInfo.set("building", building);
            inInfo.set("floor", floor);
            inInfo.set(EiConstant.serviceName, "DMXX01");
            inInfo.set(EiConstant.methodName, "queryFloorRoom");
            EiInfo roomInfo = XLocalManager.call(inInfo);
            if (roomInfo.getStatus() < 0) {
                logger.info("调用本地服务DMXX01.queryFloorByBuilding查询所有宿舍楼层相关信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(roomInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (roomInfo.getBlock("roomInfo") != null){
                    // 获取整个房间列表，循环遍历里面每个房间，查询房间内的入住信息
                    roomList = (List<Map<String, Object>>) roomInfo.getBlock("roomInfo").getRows();
                    for (int i = 0; i < roomList.size(); i++) {
                        /*
                         *  3、调用本地服务DMXX01.queryDetailRZInfo查询某房间的入住信息.
                         *      将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
                         *      若状态码不<0则取出获取结果存值列表部分.
                         */
                        // 调用本地服务DMXX01.queryFloorRoom查询宿舍楼层中的宿舍信息.
                        String roomId = (String) roomList.get(i).get("roomId");
                        inInfo.set("roomId",roomId);
                        inInfo.set(EiConstant.serviceName, "DMXX01");
                        inInfo.set(EiConstant.methodName, "queryDetailRZInfo");
                        EiInfo detailInfo = XLocalManager.call(inInfo);
                        if (detailInfo.getStatus() < 0) {
                            logger.info("调用本地服务DMXX01.queryDetailRZInfo查询某房间的入住信息失败！");
                            resultData.setRespCode("201");
                            resultData.setRespMsg(detailInfo.getMsg());
                            resultData.setSuccess(false);
                        } else {
                            // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                            if (detailInfo.getBlock("detailInfo") != null){
                                DetailRZList =  (List<Map<String, Object>>) detailInfo.getBlock("detailInfo").getRows();
                                /*
                                 * 4、（因前端格式的需求，需要对取出的结果的键值key以其他键值替换,故需要此步骤）.
                                 */
                                roomList.get(i).put("detail", DetailRZList);
                            }
                        }
                    }
                }
                resultData.setObj(roomList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }


    /**
     *  获取退宿人员列表.
     *   1、对部分参数进行实例化.
     *   2、调用本地服务DMTS02.getTSPeopleList查询获取所有退宿人员列表.
     *  将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
     *  若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getTSPeopleList
     * @param request
     * @param response
     * @return
     */
    public DMResultData getTSPeopleList(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对部分参数进行实例化.
         */
        DMResultData resultData = new DMResultData();
        List<Map<String, Object>> tsList = new ArrayList();
        String manName = request.getParameter("manName"); // 人名
        EiInfo inInfo = new EiInfo();
        try {
            /*
             *  2、调用本地服务DMTS02.getTSPeopleList查询获取所有退宿人员列表.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            // 调用本地服务DMTS02.getTSPeopleList查询获取退宿人员列表.
            inInfo.set("manName", manName);
            inInfo.set(EiConstant.serviceName, "DMTS02");
            inInfo.set(EiConstant.methodName, "getTSPeopleList");
            EiInfo tsPeopleInfo = XLocalManager.call(inInfo);
            if (tsPeopleInfo.getStatus() < 0) {
                logger.info("调用本地服务DMTS02.getTSPeopleList获取退宿人员列表失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(tsPeopleInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (tsPeopleInfo.getBlock("tsPeopleList") != null){
                    tsList = (List<Map<String, Object>>) tsPeopleInfo.getBlock("tsPeopleList").getRows();
                }
                resultData.setObj(tsList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        logger.info(resultData);
        return resultData;
    }


}