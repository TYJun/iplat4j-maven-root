package com.baosight.wilp.df.kb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;
import com.baosight.wilp.df.kb.constant.DfKbConstant;
import org.apache.commons.tools.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 设备生命周期看板Service
 * @ClassName: ServiceDFKB01
 * @Package com.baosight.wilp.df.kb
 * @date: 2022年06月10日 10:50
 */
public class ServiceDFKB01 extends ServiceBase {

    /**
     * 根节点
     **/
    private static final String ROOT_NODE = "root";

    /**
     * @Title: initLoad
     * @Description: 初始化查询参数
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //页面查询参数
        Map<String, String> queryParams = new HashMap<>(4);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        queryParams.put("beginDate", DateUtils.toDateStr10(cal.getTime()));
        queryParams.put("endDate", DateUtils.curDateStr10());

        inInfo.set("data", queryParams);
        return inInfo;
    }

    /**
     * @Title: queryDeviceTypeTree
     * @Description: 查询设备分类
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDeviceTypeTree(EiInfo inInfo) {
        List<Map<String, Object>> result = new ArrayList<>();
        //数据查询
        List<Map<String, Object>> deviceTypeList = dao.query("DFKB01.getDeviceTypeList", new HashMap<>(0));
        //树结构数据构建
        result = buildTreeData(deviceTypeList);
        //数据返回
        inInfo.set("data", result);
        return inInfo;
    }

    /**
     * @Title: buildTreeData
     * @Description: 树结构数据构建
     * @param deviceTypeList deviceTypeList
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    private List<Map<String, Object>> buildTreeData(List<Map<String, Object>> deviceTypeList) {
        List<Map<String, Object>> result = new ArrayList<>();
        // 把所有的数据都放到map中
        Map<String, Map<String, Object>> treeMap = new HashMap<>(deviceTypeList.size());
        for (Map<String, Object> deviceTypeMap : deviceTypeList) {
            // 元素的id为键，元素本身为值
            treeMap.put(deviceTypeMap.get("id").toString(), deviceTypeMap);
            // 将所有顶层节点存入result中
            if (ROOT_NODE.equals(deviceTypeMap.get("parentId"))) {
                result.add(deviceTypeMap);
            }
        }

        // 遍历数据，将对象放入父级节点的childList属性中
        for (Map<String, Object> deviceTypeMap : deviceTypeList) {
            Map<String, Object> parentMap = treeMap.get(deviceTypeMap.get("parentId"));
            if (parentMap != null) {
                // 有父节点，校验父节点下childList是否存在，然后将子节点放入
                if (parentMap.get("children") == null) {
                    parentMap.put("children", new ArrayList<>());
                }
                // 添加到父节点的ChildList集合下
                List<Map<String, Object>> children = (List<Map<String, Object>>) parentMap.get("children");
                children.add(deviceTypeMap);
            }
        }
        return result;
    }

    /**
     * @Title: queryDevice
     * @Description: 查询设备
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDevice(EiInfo inInfo) {
        //获取参数
        Map<String, Object> params = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("deviceTypeId", "deviceName"));
        //查询
        List<Map<String, String>> list = dao.query("DFKB01.queryDeviceList", params);
        inInfo.set("data", list);
        return inInfo;
    }

    /**
     * @Title: queryDeviceLifeCycle
     * @Description: 查询设备生命周期流程数据
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDeviceLifeCycle(EiInfo inInfo) {
        List<Map<String, String>> list = new ArrayList<>();
        //获取参数
        Map<String, Object> params = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("beginDate", "endDate", "nodeName", "deviceId"));
        /**1.获取设备资产信息**/
        List<Map<String, String>> deviceList = queryDeviceInfo(params);
        list.addAll(deviceList);

        /**2.获取设备巡检信息**/
        List<Map<String, String>> inspectList = queryDeviceInspect(params);
        list.addAll(inspectList);

        /**3.获取设备保养信息**/
        List<Map<String, String>> maintainList = queryDeviceMaintain(params);
        list.addAll(maintainList);

        /**4.获取设备保洁信息**/
        if(hasQuery(params, DfKbConstant.LIFE_CYCLE_CLEAN)) {
            List<Map<String, String>> cleanList = dao.query("DFKB01.queryDeviceClean", params);
            list.addAll(cleanList);
        }

        /**5.获取设备润滑信息**/
        if(hasQuery(params, DfKbConstant.LIFE_CYCLE_LUBRICATE)) {
            List<Map<String, String>> lubricateList = dao.query("DFKB01.queryDeviceLubricate", params);
            list.addAll(lubricateList);
        }

        /**6.排序（按时间升序排列）**/
        list.sort((m1, m2) -> {
            Date date1 = DateUtils.toDate(m1.get("date"));
            Date date2 = DateUtils.toDate(m2.get("date"));
            return date1.equals(date2) ? 0 : date2.after(date1) ? -1 : 1;
        });

        inInfo.set("data", list);
        return inInfo;
    }
    /**
     * @Title: queryDeviceInfo
     * @Description: 获取设备信息
     * @param params params : 参数集合
     * @return void
     * @throws
     **/
    private List<Map<String, String>> queryDeviceInfo(Map<String, Object> params) {
        List<Map<String, String>> list = new ArrayList<>();
        if(hasQuery(params, DfKbConstant.LIFE_CYCLE_INFO)) {
            List<Map<String, String>> deviceList = dao.query("DFKB01.queryDeviceInfo", params);
            //数据处理
            if(!DFUtils.isEmpty(deviceList)) {
                //数据转换
                Map<String, String> deviceMap = deviceList.get(0);
                for (int i=0; i<3; i++) {
                    Map<String, String> map = new HashMap<>(8);
                    map.put("id", UUID.randomUUID().toString());
                    map.put("date", deviceMap.get("date"+i));
                    map.put("cycle", deviceMap.get("cycle"+i));
                    map.put("desc", deviceMap.get("desc"+i));
                    list.add(map);
                }

                //数据过滤
                if(!DFUtils.isEmpty(params.get("beginDate"))) {
                    list = list.stream().filter(map -> {
                        Date date = DateUtils.toDate(map.get("date"));
                        Date beginDate = DateUtils.toDate(params.get("beginDate").toString());
                        return !date.before(beginDate);
                    }).collect(Collectors.toList());
                }
                if(!DFUtils.isEmpty(params.get("endDate"))) {
                    list = list.stream().filter(map -> {
                        Date date = DateUtils.toDate(map.get("date"));
                        Date endDate = DateUtils.toDate(params.get("endDate").toString());
                        return !date.after(endDate);
                    }).collect(Collectors.toList());
                }
            }
        }
        return list;
    }

    /**
     * @Title: queryDeviceInspect
     * @Description: 获取设备巡检信息
     * @param params params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> queryDeviceInspect(Map<String, Object> params) {
        List<Map<String, String>> list = new ArrayList<>();
        if(hasQuery(params, DfKbConstant.LIFE_CYCLE_INSPECT) && DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)) {
            params.put("beginDate", params.get("beginDate")+"00:00:00");
            params.put("endDate", params.get("endDate")+"23:59:59");
            list = dao.query("DFKB01.queryDeviceInspect", params);
        }
        return list;
    }

    /**
     * @Title: queryDeviceMaintain
     * @Description: 获取设备保养信息
     * @param params params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> queryDeviceMaintain(Map<String, Object> params) {
        List<Map<String, String>> list = new ArrayList<>();
        if(hasQuery(params, DfKbConstant.LIFE_CYCLE_MAINTAIN) && DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)) {
            params.put("beginDate", params.get("beginDate")+"00:00:00");
            params.put("endDate", params.get("endDate")+"23:59:59");
            list = dao.query("DFKB01.queryDeviceInspect", params);
        }
        return list;
    }

    /**
     * @Title: hasQuery
     * @Description: 是否查询数据
     * @param params params ： 参数对象
     *       nodeName ： 事项集合
     * @param nodeCode nodeCode ： 事项编码
     * @return boolean
     * @throws
     **/
    private boolean hasQuery(Map<String, Object> params, String nodeCode) {
        List<String> nodes = (List<String>) params.get("nodeName");
        //为空，参与查询
        if(CollectionUtils.isEmpty(nodes)) {
            return true;
        }

        //包含,参与查询;不包含,不参与查询
        return nodes.contains(nodeCode);
    }
}
