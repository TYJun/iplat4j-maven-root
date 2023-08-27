package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.cs.common.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对接前端APP接口
 *  一、APP获取地点信息的接口.
 *  二、APP获取科室列表.
 *  三、APP获取所有的保洁事项.
 *  四、APP查询所有未确认/待完工的列表.
 *  五、APP查询工单列表接口.
 *  六、APP查询保洁工单详情.
 *  七、APP工单事项完工接口.
 *  八、APP查询工单状态相应信息接口.
 *
 * @Title: AppLinkService.java
 * @ClassName: AppLinkService
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021/12/6 18:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class AppLinkService {
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
     * @Title getSoptInfo
     * @param request
     * dataGroupCode : 院区账套，暂未启用
     * @param response
     * @return
     */
    public ResultData getSoptInfo(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        // 获取前端传来的dataGroupCode 账套值.
        String dataGroupCode = request.getParameter("dataGroupCode"); // 院区账套，暂未启用.
        inInfo.set("dataGroupCode", dataGroupCode);
        // 实例化三个参数，building、floor、spot 用来分别接收查询得到的列表.
        List<Map<String, Object>> building = new ArrayList();
        List<Map<String, Object>> floor = new ArrayList();
        List<Map<String, Object>> spot = new ArrayList();
        try {
            /*
             *  2、调用基础框架微服务接口S_AC_FW_13查询楼.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set(EiConstant.serviceId, "S_AC_FW_13");
            EiInfo buildingInfo = XServiceManager.call(inInfo);
            // 对调用结果进行判断.
            if (buildingInfo.getStatus() < 0) {
                System.out.println("调用微服务S_AC_FW_13获取楼失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(buildingInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                /*
                 * 3、对获取到的building列表进行遍历.
                 *     用它其中每一个楼的信息去调用基础框架微服务接口S_AC_FW_14获取楼中的每一层信息.
                 *     将获取结果进行判断，若状态码<0 值判断为该接口获取楼失败.
                 *     若状态码不<0则取出获取结果存值列表部分.
                 */
                building = (List<Map<String, Object>>) buildingInfo.get("result");
                int buildingSize = building.size();
                for (int i = 0; i < buildingSize; i++) {
                    String buildingStr = (String) building.get(i).get("building");
                    // 调用基础框架微服务接口S_AC_FW_14获取楼中的每一层信息.
                    inInfo.set("building", buildingStr);
                    inInfo.set(EiConstant.serviceId, "S_AC_FW_14");
                    EiInfo floorInfo = XServiceManager.call(inInfo);
                    // 对调用结果进行判断.
                    if (floorInfo.getStatus() < 0) {
                        System.out.println("调用微服务S_AC_FW_14获取层失败！");
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
                        floor = (List<Map<String, Object>>) floorInfo.get("result");
                        int floorSize = floor.size();
                        for (int j = 0; j < floorSize; j++) {
                            String floorStr = (String) floor.get(j).get("floor");
                            // 调用基础框架微服务接口S_AC_FW_15获取楼层中的相关地点信息.
                            inInfo.set("floor", floorStr);
                            inInfo.set(EiConstant.serviceId, "S_AC_FW_15");
                            EiInfo spotInfo = XServiceManager.call(inInfo);
                            // 对调用结果进行判断.
                            if (spotInfo.getStatus() < 0) {
                                System.out.println("调用微服务S_AC_FW_15获取地点信息失败！");
                                resultData.setRespCode("201");
                                resultData.setRespMsg(floorInfo.getMsg());
                                resultData.setSuccess(false);
                            } else {
                                /*
                                 * 5、（因前端格式的需求，需要对取出的结果的键值key以其他键值替换,故仍需要此步骤）.
                                 *     对获取到的spot列表进行遍历.
                                 *     将获取的地点编码和地点名称重新用两个键值"value"和"text"接收.
                                 */
                                spot = (List<Map<String, Object>>) spotInfo.get("result");
                                int spotSize = spot.size();
                                for (int z = 0; z < spotSize; z++) {
                                    spot.get(z).put("value",spot.get(z).get("spot_num"));
                                    spot.get(z).put("text",spot.get(z).get("spot_name"));
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
                System.out.println("need" + (endTime - startTime) + "ms");
            }
            // 捕异常.
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
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
    public ResultData getDeptInfo(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
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
                System.out.println("调用微服务S_AC_FW_05获取科室信息失败！");
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
                System.out.println("need" + (endTime - startTime) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }

    /**
     *  三、APP获取所有的保洁事项.
     *  1、对接口入参进行接收并对部分参数进行实例化.
     *  2、调用本地服务接口CSSX01.selectItemToApp查询保洁事项列表.
     *      将获取结果进行判断，若状态码<0 值判断为该接口获取保洁事项列表失败.
     *      若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getItemInfo
     * @param request
     * itemName : 事项名称
     * itemCode : 事项编码
     * typeName : 事项分类名称
     * @param response
     * @return
     */
    public ResultData getItemInfo(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> itemList = new ArrayList();
        String itemName = request.getParameter("itemName"); // 事项名称
        String itemCode = request.getParameter("itemCode"); // 事项编码
        String typeName = request.getParameter("typeName"); // 事项分类名称
        try {
            /*
             *  2、调用本地服务接口CSSX01.selectItemToApp查询保洁事项列表.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取保洁事项列表失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set("itemName", itemName);
            inInfo.set("itemCode", itemCode);
            inInfo.set("typeName", typeName);
            // 调用本地服务查询保洁事项列表
            inInfo.set(EiConstant.serviceName, "CSSX01");
            inInfo.set(EiConstant.methodName, "selectItemToApp");
            EiInfo itemInfo = XLocalManager.call(inInfo);
            // 将获取结果进行判断.
            if (itemInfo.getStatus() < 0) {
                System.out.println("调用本地服务CSSX01.selectItemToApp获取保洁事项信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(itemInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (itemInfo.getBlock("clean_item_list") != null){
                    itemList = (List<Map<String, Object>>) itemInfo.getBlock("clean_item_list").getRows();
                }
                resultData.setObj(itemList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
                long endTime = System.currentTimeMillis();
                System.out.println("need" + (endTime - startTime) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }

    /**
     *  四、APP查询所有未确认/待完工的列表.
     *   1、对接口入参进行接收并对部分参数进行实例化.
     *   2、调用本地服务接口CSRE01.queryUnFinishTaskList查询未确认和未完工工单列表.
     *      将获取结果进行判断，若状态码<0 值判断为该接口获取确认和未完工工单列表失败.
     *      若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getUnFinishTaskList
     * @param request
     * workNo : 工号
     * type : 类别，与前端商量好的数值，此处1为前端"待完工"列，2为前端"完工列"，3为前端"我的工单"列.
     * dataGroupCode : 院区账套，暂未启用
     * page : 当前页数
     * rows : 行数
     * recCreateTimeS : 查询时间起
     * recCreateTimeE : 查询时间止
     * statusCode : 工单状态
     * @param response
     * @return
     */
    public ResultData getUnFinishTaskList(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> UnFinishTaskList = new ArrayList();
        String workNo = request.getParameter("workNo"); // 工号
        String type = request.getParameter("type"); // 类别，与前端商量好的数值，此处1为前端"待完工"列，2为前端"完工列"，3为前端"我的工单"列.
        String dataGroupCode = request.getParameter("dataGroupCode"); // 院区账套，暂未启用
        String page = request.getParameter("page"); // 当前页数
        String rows = request.getParameter("rows"); // 行数
        String recCreateTimeS = request.getParameter("recCreateTimeS"); // 查询时间起
        String recCreateTimeE = request.getParameter("recCreateTimeE"); // 查询时间止
        String statusCode = request.getParameter("statusCode"); // 工单状态
        try {
            /*
             *  2、调用本地服务接口CSRE01.queryUnFinishTaskList查询未确认和未完工工单列表.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取确认和未完工工单列表失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set("workNo", workNo);
            inInfo.set("type", type);
            inInfo.set("dataGroupCode", dataGroupCode);
            inInfo.set("page", page);
            inInfo.set("rows", rows);
            inInfo.set("recCreateTimeS", recCreateTimeS);
            inInfo.set("recCreateTimeE", recCreateTimeE);
            inInfo.set("statusCode", statusCode);
            // 调用本地服务CSRE01.queryUnFinishTaskList查询未确认和未完工工单列表
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "queryUnFinishTaskList");
            EiInfo UnFinishTaskInfo = XLocalManager.call(inInfo);
            if (UnFinishTaskInfo.getStatus() < 0) {
                System.out.println("调用本地服务CSRE01.queryUnFinishTaskList获取未确认和未完工工单列表失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(UnFinishTaskInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (UnFinishTaskInfo.getBlock("result") != null){
                    UnFinishTaskList = (List<Map<String, Object>>) UnFinishTaskInfo.getBlock("result").getRows();
                }
                resultData.setObj(UnFinishTaskList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }

    /**
     *  五、APP查询工单列表接口.
     *   1、对接口入参进行接收并对部分参数进行实例化.
     *   2、调用本地服务接口CSRE01.queryTaskList查询所有工单列表.
     *      将获取结果进行判断，若状态码<0 值判断为该接口获取所有工单列表失败.
     *      若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getTaskList
     * @param request
     * workNo : 工号
     * type : 类别，与前端商量好的数值，此处1为前端"待完工"列，2为前端"完工列"，3为前端"我的工单"列.
     * dataGroupCode : 院区账套，暂未启用
     * page : 当前页数
     * rows : 行数
     * recCreateTimeS : 查询时间起
     * recCreateTimeE : 查询时间止
     * statusCode : 工单状态
     * @param response
     * @return
     */
    public ResultData getTaskList(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> taskList = new ArrayList();
        String workNo = request.getParameter("workNo"); // 工号
        String type = request.getParameter("type"); // 类别，与前端商量好的数值，此处1为前端"待完工"列，2为前端"完工列"，3为前端"我的工单"列.
        String dataGroupCode = request.getParameter("dataGroupCode"); // 院区账套，暂未启用.
        String page = request.getParameter("page"); // 当前页数.
        String rows = request.getParameter("rows"); // 行数
        String recCreateTimeS = request.getParameter("recCreateTimeS"); // 查询时间起
        String recCreateTimeE = request.getParameter("recCreateTimeE"); // 查询时间止
        String statusCode = request.getParameter("statusCode"); // 工单状态
        try {
            /*
             *  2、调用本地服务接口CSRE01.queryTaskList查询所有工单列表.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取所有工单列表失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set("workNo", workNo);
            inInfo.set("type", type);
            inInfo.set("dataGroupCode", dataGroupCode);
            inInfo.set("page", page);
            inInfo.set("rows", rows);
            inInfo.set("recCreateTimeS", recCreateTimeS);
            inInfo.set("recCreateTimeE", recCreateTimeE);
            inInfo.set("statusCode", statusCode);
            // 调用本地服务查询所有工单列表
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "queryTaskList");
            EiInfo taskInfo = XLocalManager.call(inInfo);
            if (taskInfo.getStatus() < 0) {
                System.out.println("调用本地服务CSRE01.queryTaskList获取所有工单列表失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(taskInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (taskInfo.getBlock("result") != null){
                    taskList = (List<Map<String, Object>>) taskInfo.getBlock("result").getRows();
                }
                resultData.setObj(taskList);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }

    /**
     *  六、APP查询保洁工单详情.
     *   1、对接口入参进行接收并对部分参数进行实例化.
     *   2、调用本地服务接口CSRE01.queryTaskInfo查询工单详情.
     *      将获取结果进行判断，若状态码<0 值判断为该接口获取工单详情失败.
     *      若状态码不<0则取出获取结果存值列表部分.
     *
     * @Title getTaskDetailInfo
     * @param request
     * taskId ： 工单ID
     * @param response
     * @return
     */
    public ResultData getTaskDetailInfo(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> taskDetail = new ArrayList();
        String taskId = request.getParameter("taskId"); // 工单ID
        try {
            /*
             *  2、调用本地服务接口CSRE01.queryTaskInfo查询工单详情.
             *      将获取结果进行判断，若状态码<0 值判断为该接口获取工单详情失败.
             *      若状态码不<0则取出获取结果存值列表部分.
             */
            inInfo.set("taskId", taskId);
            // 调用本地服务CSRE01.queryTaskInfo查询工单详情
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "queryTaskInfo");
            EiInfo taskDetailInfo = XLocalManager.call(inInfo);
            if (taskDetailInfo.getStatus() < 0) {
                System.out.println("调用本地服务CSRE01.queryTaskInfo获取工单详情失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(taskDetailInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (taskDetailInfo.getBlock("taskDetailInfo") != null){
                    taskDetail = (List<Map<String, Object>>) taskDetailInfo.getBlock("taskDetailInfo").getRows();
                }
                resultData.setObj(taskDetail);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }


    /**
     *  七、APP工单事项完工接口.
     *   1、对接口入参进行接收并对部分参数进行实例化.
     *   2、调用本地服务接口CSFS0101.finishMethod实现完工接口.
     *      将获取结果进行判断，若状态码不等于200则判断为执行改接口失败.
     *      若状态码等于200则成功执行该接口的完工操作.
     *
     * @Title finishGDByApp
     * @param request
     * workNo ： 工号
     * taskId ： 工单ID
     * itemCode ：事项编码
     * statusCode ：工单状态
     * @param response
     * @return
     */
    public ResultData finishGDByApp(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对接口入参进行接收并对部分参数进行实例化.
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        String workNo = request.getParameter("workNo"); // 工号
        String taskId = request.getParameter("taskId"); // 工单ID
        String itemCode = request.getParameter("itemCode"); // 事项编码
        String statusCode = request.getParameter("statusCode"); // 工单状态
        /*
         *  2、调用本地服务接口CSFS0101.finishMethod实现完工接口.
         *      将获取结果进行判断，若状态码不等于200则判断为执行改接口失败.
         *      若状态码等于200则成功执行该接口的完工操作.
         */
        try {
            inInfo.set("workNo", workNo);
            inInfo.set("taskId", taskId);
            inInfo.set("itemCode", itemCode);
            inInfo.set("statusCode", statusCode);
            // 调用本地服务查询工单详情
            inInfo.set(EiConstant.serviceName, "CSFS0101");
            inInfo.set(EiConstant.methodName, "finishMethod");
            EiInfo updatetInfo = XLocalManager.call(inInfo);
            if (!"200".equals(updatetInfo.getMsgKey())) {
                System.out.println("调用本地服务CSFS0101.finishMethod对工单执行完工操作失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(updatetInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                //resultData.setObj(updatetInfo);
                resultData.setRespCode("200");
                resultData.setRespMsg("操作成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }


    /**
     *  八、APP查询工单状态相应信息接口.
     * （查询所有工单状态）
     *   1、对参数进行实例化(无入参).
     *   2、调用本地服务接口CSRE01.queryStatusCodeInfo查询工单状态相应信息.
     *      将获取结果进行判断，若状态码<0则判断为查询工单状态相应信息失败.
     *      若状态码>0则取出获取结果存值列表部分.
     *
     * @Title getAllStatusCodeInfo
     * @param request
     * @param response
     * @return
     */
    public ResultData getAllStatusCodeInfo(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 1、对参数进行实例化(无入参).
         */
        ResultData resultData = new ResultData();
        EiInfo inInfo = new EiInfo();
        List<Map<String, Object>> AllStatusCode = new ArrayList();
        try {
            /*
             *  2、调用本地服务接口CSRE01.queryStatusCodeInfo查询工单状态相应信息.
             *      将获取结果进行判断，若状态码<0则判断为查询工单状态相应信息失败.
             *      若状态码>0则取出获取结果存值列表部分.
             */
            // 调用本地服务查询工单状态相应信息
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "queryStatusCodeInfo");
            EiInfo AllStatusCodeInfo = XLocalManager.call(inInfo);
            if (AllStatusCodeInfo.getStatus() < 0) {
                System.out.println("调用本地服务CSRE01.queryStatusCodeInfo查询工单状态相应信息失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg(AllStatusCodeInfo.getMsg());
                resultData.setSuccess(false);
            } else {
                // 对取值进行判空，以防出现异常被捕获导致接口调用失败的情况.
                if (AllStatusCodeInfo.getBlock("statusInfo") != null){
                    AllStatusCode = (List<Map<String, Object>>) AllStatusCodeInfo.getBlock("statusInfo").getRows();
                }
                resultData.setObj(AllStatusCode);
                resultData.setRespCode("200");
                resultData.setRespMsg("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        System.out.println(resultData);
        return resultData;
    }


}