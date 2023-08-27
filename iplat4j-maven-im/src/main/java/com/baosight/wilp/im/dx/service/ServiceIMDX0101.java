package com.baosight.wilp.im.dx.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.wilp.im.dx.domain.ImObjectSpot;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 
 * 巡检对象管理弹窗页面：页面初始化、保存对象地点、地点信息服务调用
 * <p>1.基准初始化查询  initLoad
 * <p>2.基准查询   query
 * <p>3.对象删除   delete
 * 
 * @Title: ServiceIMDX0101.java
 * @ClassName: ServiceIMDX0101
 * @Package：com.baosight.wilp.im.dx.service
 * @author: zhangjiaqina
 * @date: 2021年9月15日 下午1:38:53
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMDX0101 extends ServiceBase{


    /**
     * 
     * 页面初始化
     * <p>1.获取参数
     * <p>2.校验参数
     * <p>3.通过参数判断是参数回调还是新增页面
     * 
     * @param inInfo
     * id  对象id
     * @return
     * id            对象id
     * spotId        地点id
     * spotCode      地点编码
     * spotName      地点名称
     * objName       巡检对象
     * objRemark     巡检对象备注
     * createMan     创建人
     * createTime    创建时间
     * modifyMan     修改人
     * modifyTime    修改时间
     * 
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取选中行id
        String id = (String)inInfo.get("id");
        //如果id不为空则是参数回显，通过id查询对应的对象信息
        if(!StringUtils.isEmpty(id)) {
            Map param = new HashMap();
            param.put("id", id);
            List<ImObjectSpot> query = dao.query("IMDX01.query", param);
            //校验查询参数是否为空
            if(CollectionUtils.isEmpty(query)) {
                return inInfo;
            }
            //封装返回参数
            inInfo.setAttr(query.get(0).toMap());
            return inInfo;
        }
        //id为空直接返回
        return super.initLoad(inInfo);
    }


    
    /**
     * 
     * 保存对象地点
     * <p>1.获取参数
     * <p>2.通过id校验是新增还是修改
     * 
     * @param inInfo
     * objectName       对象名
     * spotId1          地点id
     * spotNum          地点编码
     * spotName         地点名称
     * remark           备注
     * dataGroupCode    院区编码
     * id               对象id
     * @return
     * 返回状态信息
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo insert(EiInfo inInfo) {
        String spotId = null;
        //对象名
        String objectName = (String)inInfo.get("objectName");
        //地点id
        String spotId1 = (String)inInfo.get("spotId");
        //地点编码
        String spotNum = (String)inInfo.get("spotNum");
        //地点名称
        String spotName = (String)inInfo.get("spotName");
        //备注
        String remark = (String)inInfo.get("remark");
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //获取当前时间
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取id
        String id = (String)inInfo.get("id");
        //判断是新增还是修改
        if(StringUtils.isEmpty(id)) {
            //处理id
            String idPamram = UUID.randomUUID().toString().replace("-", "");
            if(!StringUtils.isEmpty(spotId1)) {
                spotId = spotId1.replace("-", "");
            }
            //构建保存参数
            Map param = new HashMap();
            param.put("id", idPamram);
            param.put("spotId", spotId);
            param.put("spotCode", spotNum);
            param.put("spotName", spotName);
            param.put("objName", objectName);
            param.put("objRemark", remark);
            param.put("createMan", (String)staffByUserId.get("name"));
            param.put("createTime", createTime);
            param.put("dataGroupCode", dataGroupCode);
            //保存参数
            dao.insert("IMDX01.insert", param);
            inInfo.setStatus(1);
            inInfo.setMsg("保存成功");
        }else {
            if(!StringUtils.isEmpty(spotId1)) {
                spotId = spotId1.replace("-", "");
            }
            //构建修改参数
            Map param = new HashMap();
            param.put("id", id);
            param.put("spotId", spotId);
            param.put("spotCode", spotNum);
            param.put("spotName", spotName);
            param.put("objName", objectName);
            param.put("objRemark", remark);
            param.put("modifyMan", (String)staffByUserId.get("name"));
            param.put("modifyTime", createTime);
            dao.update("IMDX01.update", param);
            //保存参数
            inInfo.setStatus(1);
            inInfo.setMsg("保存成功");
        }
        return inInfo;
    }
    

    
    
    /**
     * 
     * 地点信息服务调用
     *
     * @Title: querySpot 
     * @param info
     * @return 
     *      id          ：   地点ID
     *      spotNum     ：   地点编码
     *      spotName    ：   地点
     *      deptId      ：   所属科室ID
     *      deptNum     ：   所属科室编码
     *      deptName    ：   所属科室名称
     *      building    ：   楼
     *      floor       ：   层
     *      room        ：   房间
     *      remark      ：   备注 
     */
    public EiInfo querySpot(EiInfo info) {
        // 实例化info
        EiInfo outinfo = new EiInfo();
        // 实例化block
        EiBlock block = new EiBlock("spot");
        // 调用分页接口，构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
        // 获取blockId
        String blockId = info.getBlockIds();
        // 如果blockId相等
        if(blockId.equals("inqu_status,spot")) {
            // 如果blockId不为空
            if(info.getBlock("inqu_status").getRows().size()>0) {
                // 获取block中的数据的集合
                List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
                // 获取集合中的数据
                String spotName = (String) listMap.get(0).get("spotName");
                // 调用远程接口返回集合
                List<Map<String, Object>> spotAll = BaseDockingUtils.getSpotBySpotName(spotName);
                // 遍历集合
                for (Map<String, Object> map2 : spotAll) {
                    // 将spotId加入集合
                    map2.put("spotId", map2.get("id"));
                }
                // 对返回集合进行分页
                List startPage = startPage(spotAll,Integer.valueOf(map.get("offset").toString()),Integer.valueOf(map.get("limit").toString()));
                int count=spotAll==null?0:spotAll.size();
                return DeviceEiUtil.buildResult(info, startPage, count, "spot");
            }
        }
//        // 调用改造地点接口
//        EiInfo spotAll = BaseDockingUtils.getSpotAllPage(map, "spot");
//        // 获取block中的数据的集合
//        List<Map<String, Object>> list = (List) spotAll.getBlock("spot").getRows();
//        // 实例化list集合
//        List<Map<String, Object>> replaceList = new ArrayList<>();
//        // 遍历集合
//        for (Map<String, Object> map2 : list) {
//            // 将spotId加入集合
//            map2.put("spotId", map2.get("id"));
//            // 将集合报错到list中
//            replaceList.add(map2);
//        }
//        // 调用分页
//        List startPage = startPage(list,Integer.valueOf(map.get("offset").toString()),Integer.valueOf(map.get("limit").toString()));
//        int count=list==null?0:list.size();
//        return DeviceEiUtil.buildResult(info, startPage, count, "spot");
        List<Map<String, Object>> spotAll = BaseDockingUtils.getSpotBySpotName("");
        // 遍历集合
        for (Map<String, Object> map2 : spotAll) {
            // 将spotId加入集合
            map2.put("spotId", map2.get("id"));
        }
        // 对返回集合进行分页
        List startPage = startPage(spotAll,Integer.valueOf(map.get("offset").toString()),Integer.valueOf(map.get("limit").toString()));
        int count=spotAll==null?0:spotAll.size();
        return DeviceEiUtil.buildResult(info, startPage, count, "spot");
    }
    
    
    
    /**
     * @Title: startPage 
     * @Description: 分页
     * @param list : 返回List数据
     * @param pageNum : 起始页
     * @param pageSize : 分页数limit
     * @return: List : 返回List数据
     */
    public static List startPage(List list,Integer pageNum,Integer pageSize) {
        // 集合为空
        if(CollectionUtils.isEmpty(list)) {
            // 返回
            return null;
        }
        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        // 总数取余limit
        if(count % pageSize == 0) {
            // 页数取除结果
            pageCount = count / pageSize;
        }else {
            // 页数取除结果+1
            pageCount = (count / pageSize) + 1;
        }
        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;
        // 如果起始数+limit大于总数
        if((pageNum+pageSize) > count) {
            // 开始索引就是起始数
            fromIndex = pageNum;
            // 结束索引就是总数
            toIndex = count;
        }else {
            // 开始索引是起始数
            fromIndex = pageNum;
            // 结束索引是起始数+limit
            toIndex = pageNum + pageSize;
        }
        // 将list进行分割
        List pageList = list.subList(fromIndex, toIndex);
        // 返回集合
        return pageList;
    }
}
