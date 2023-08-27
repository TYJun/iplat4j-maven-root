package com.baosight.wilp.im.jz.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.im.jz.domain.ImDevicePacket;
import com.baosight.wilp.im.jz.domain.ImClassfyparam;

/**
 * 
 * 巡检卡片包编辑：跳转设备包初始化、保存巡检设备包
 * <p>1.跳转设备包初始化 initLoad
 * <p>2.保存巡检设备包 upda
 * @Title: ServiceDIJZ0202.java
 * @ClassName: ServiceDIJZ0202
 * @Package：com.baosight.wilp.di.jz.service
 * @author: ha'ha'ha
 * @date: 2021年6月1日 下午3:11:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ0202 extends ServiceBase{

    //全局缓存
    private String DIJZID = null;


    /**
     * 跳转设备包初始化
     * <p>1.查询设备包信息
     * <p>2.设置查询参数
     * <p>3.封装查询分页
     * <p>4.查询巡检包项目信息
     * <p>5.获取inqu模块展示数据
     * <p>6.给result区域赋值
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * id  设备包id
     * @return
     * id          设备id
     * paramKey    设备代码
     * paramName   设备名称
     * classifyName  安装地点
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        String id = (String)inInfo.get("id");
        //查询巡检包信息
        List<ImDevicePacket> query = dao.query("IMJZ0202.query", id);

        //设置查询参数
        EiInfo outInfo = new EiInfo();
        EiBlock resultBlock = outInfo.addBlock(EiConstant.resultBlock);
        resultBlock.addBlockMeta(new ImClassfyparam().eiMetadata);
        Map map = new HashMap();
        DIJZID = id;
        map.put("id", id);
        List list = new ArrayList();
        list.add(map);
        EiBlock eiBlock= outInfo.addBlock("result");
        eiBlock.setRows(list);
        Map<String,EiBlock> param = new HashMap<String,EiBlock>();
        param.put("inqu_status", eiBlock);
        inInfo.setBlocks(param);

        //封装查询分页
        Map map2 = new HashMap();
        map2.put("id", id);
        
        
        //查询巡检包项目信息
        List<ImDevicePacket> query2 = dao.query("IMJZ0202.queryDetail", map2);

        if(null == query2 || 0 == query2.size()) {
            inInfo.setMsg("系统错误，请联系管理员");
            inInfo.setStatus(-1);
            return inInfo;
        }

        //获取inqu模块展示数据
        EiBlock addBlock2 = outInfo.addBlock("inqu");
        addBlock2.addBlockMeta(new ImDevicePacket().eiMetadata);
        outInfo.getBlock("inqu").setRows(query);

        ImDevicePacket diDevicePacket = query.get(0);
        outInfo.set("dangerwhere", diDevicePacket.getPacketName());
        outInfo.set("requiredesc", diDevicePacket.getMemo());
        
        //给result区域赋值
        EiBlock result = outInfo.addBlock("result");
        result.addBlockMeta(new ImDevicePacket().eiMetadata);
        outInfo.getBlock("result").setRows(query2);
        
        
        return outInfo;
    }




    /**
     * 
     * 保存巡检设备包
     * <p>设备列表为空，请选择后再添加
     * <p>去除前端添加的重复数据
     * <p>先删除设备包明细表中信息
     * <p>添加巡检设备包信息
     * <p>保存设备包内容
     * @Title: insr 
     * @param inInfo
     * modifyMan      修改人
     * modifyTime     修改时间
     * id             主键
     * packet_id      设备包id
     * machine_code   设备编号
     * machine_name   设备名称
     * fixed_place    地址
     * @return 
     * 保存成功，失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo upda(EiInfo inInfo) {
        String dIJZID = DIJZID;
        List<Map<String,String>> data = (List<Map<String,String>>)inInfo.get("data");
        String userName = UserSession.getLoginName();
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();

        if(null == data || 0 == data.size()) {
            inInfo.setMsg("设备列表为空，请选择后再添加");
            inInfo.setStatus(-1);
            return inInfo;
        }

        //去除前端添加的重复数据
        List<Map<String, String>> removeDuplicate = removeDuplicate(data);
        
        //先删除设备包明细表中信息
        int delete = dao.delete("IMJZ0202.deleteDetail", dIJZID);

        //添加巡检设备包信息
        Map map = new HashMap();
        map.put("modifyMan", userName);
        map.put("modifyTime", createTime);
        map.put("id", DIJZID);
        dao.update("IMJZ0202.updateInspection", map);
        
        Map mapDetail = new HashMap();
        //保存设备包内容
        for (Map<String, String> map2 : removeDuplicate) {
            UUID randomUUID = UUID.randomUUID();
            mapDetail.put("packet_id", DIJZID);
            mapDetail.put("machine_code", map2.get("paramKey"));
            mapDetail.put("machine_name", map2.get("paramName"));
            mapDetail.put("fixed_place", map2.get("classifyName"));
            dao.insert("IMJZ0202.insertPackDetail", mapDetail);
        }
        DIJZID = null;
        return inInfo;
    }


    /**
     * 
     * map 去除重复数据
     *<p>设备代码相同则去除重复
     * @Title: removeDuplicate 
     * @param map
     *  paramKey   设备代码  
     * @return 
     * @return: Map
     */
    private List<Map<String,String>> removeDuplicate(List<Map<String,String>> data) {
        //设备代码相同则去除重复
        for  (int i = 0;i < data.size() - 1; i++){
            for  (int j = data.size() - 1 ; j > i; j--){
                Map<String, String> map = data.get(i);
                String string = map.get("paramKey");
                Map<String, String> map2 = data.get(j);
                String string2 = map2.get("paramKey");
                if(string.equals(string2)) {
                    data.remove(j);
                }
            }
        }
        return data;
    }

}
