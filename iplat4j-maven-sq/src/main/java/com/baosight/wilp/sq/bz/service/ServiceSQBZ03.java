package com.baosight.wilp.sq.bz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.bz.domain.SQBZ02;
import com.baosight.wilp.sq.common.UtilCode;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 考核标准逻辑处理，页面初始化方法，查询方法，保存方法，删除项目分类方法
 * <p>页面初始化方法 initLoad
 * <p>查询方法  query
 * <p>保存方法  save
 * <p>删除项目分类方法 delete
 * @Title: ServiceSQBZ03.java
 * @ClassName: ServiceSQBZ03
 * @Package：com.baosight.wilp.sq.bz.service
 * @author: zhangjiaqian
 * @date: 2021年7月19日 下午4:56:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQBZ03 extends ServiceBase{

    
    
    /**
     * id全局变量，用于区分查询
     */
    private String idParam = null;
    /**
     * projectCode用于绑定项目分项
     */
    private String projectCodeParam = null;
    
    
    
    /**
     * 
     * 页面初始化方法
     * @Title initLoad
     * @param inInfo
     * projectId            项目外键
     * projectCode          项目编码
     * instanceCode         检查标准编码
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              创建人
     * createTime           创建时间
     * modifier             修改人
     * modiftTime           修改时间
     * pointType            计分方式
     * orderNumber          排序
     * @return
     * projectId            项目外键
     * projectCode          项目编码
     * instanceCode         检查标准编码
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              创建人
     * createTime           创建时间
     * modifier             修改人
     * modiftTime           修改时间
     * pointType            计分方式
     * orderNumber          排序
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取id
        String id = (String)inInfo.get("id");
        //获取项目编码
        String projectCode = (String)inInfo.get("projectCode");
        idParam = id;
        projectCodeParam = projectCode;
        //创建对象
        EiInfo outInfo = new EiInfo();
        //判断id是否为空
        if(!id.isEmpty()) {
            //创建集合
            Map param = new HashMap();
            //封装数据
            param.put("id", id);
            //查询数据
            List<SQBZ02> query = dao.query("SQBZ03.query", param);
            EiBlock eiBlock = new EiBlock(new EiBlockMeta());
            eiBlock.setRows(query);
            HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
            hashMap.put("result", eiBlock);
            //返回
            outInfo.setBlocks(hashMap);
        }else {
            //构建返回信息
            outInfo = super.query(inInfo, "SQBZ03.query", new SQBZ02());
        }
        //返回
        return outInfo;
    }
    
    
    
    /**
     * 
     * 查询方法
     * @Title query
     * @param inInfo
     * projectId            项目外键
     * projectCode          项目编码
     * instanceCode         检查标准编码
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              创建人
     * createTime           创建时间
     * modifier             修改人
     * modiftTime           修改时间
     * pointType            计分方式
     * orderNumber          排序
     * @return
     * projectId            项目外键
     * projectCode          项目编码
     * instanceCode         检查标准编码
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              创建人
     * createTime           创建时间
     * modifier             修改人
     * modiftTime           修改时间
     * pointType            计分方式
     * orderNumber          排序
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiBlock block = new EiBlock("inqu_status");
        block.setCell(0, "id", idParam);
        inInfo.addBlock(block);
        EiInfo outInfo = super.query(inInfo, "SQBZ03.query", new SQBZ02());
        return outInfo;
    }



    /**
     * 
     * 保存方法
     *
     * @Title: save 
     * @return inInfo
     * id                   主键
     * projectId            项目外键
     * instanceCode         检查标准编码    
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              记录创建人
     * createTime           创建时间
     * pType                积分类型
     * orderNumber          排序
     * @return: EiInfo
     * id                   主键
     * projectId            项目外键
     * instanceCode         检查标准编码    
     * instanceName         检查标准名称
     * instanceRemark       检查标准备注
     * point                分数
     * creator              记录创建人
     * createTime           创建时间
     * pType                积分类型
     * orderNumber          排序
     */
    public EiInfo save(EiInfo inInfo) {
        List<Map> checkRows = (List)inInfo.get("checkRows");
        if(checkRows.size() == 0 || checkRows == null) {
            inInfo.setStatus(-1);
            inInfo.setMsg("数据错误，请联系管理员");
            return inInfo;
        }
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取当前时间
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //遍历前端传递的参数
        for (Map map : checkRows) {
            //取出id
            String id = (String)map.get("id");
            //如果id不为空，则是更新操作
            if(StringUtils.isNotEmpty(id)) {
                //循环取值保存到数据库中
                map.put("modifier", staffByUserId.get("name"));
                map.put("modifyTime", time);
                String pointType = (String)map.get("pointType");
                if(pointType.equals("打分")) {
                    map.put("pType", "0");
                }else if(pointType.equals("判断")) {
                    map.put("pType", "1");
                }
                //计数
                int update = dao.update("SQBZ03.updateProjectInstance", map);
                //判断
                if(update == 0) {
                    inInfo.setStatus(-1);
                    inInfo.setMsg(map.get("projectName")+"保存失败");
                    return inInfo;
                }
                //返回信息
                inInfo.setStatus(1);
                inInfo.setMsg("保存成功");
            }else {
                String projectInstanceCode = UtilCode.projectInstanceCode();
                //否则是新增操作
                map.put("id", UUID.randomUUID().toString());
                map.put("instanceCode", projectInstanceCode);
                map.put("creator", staffByUserId.get("name"));
                map.put("createTime", time);
                map.put("projectId", idParam);
                map.put("projectCode", projectCodeParam);
                String pointType = (String)map.get("pointType");
                if(pointType.equals("打分")) {
                    map.put("pType", "0");
                }else if(pointType.equals("判断")) {
                    map.put("pType", "1");
                }
                dao.insert("SQBZ03.insertProjectInstance", map);
                inInfo.setStatus(1);
                inInfo.setMsg("保存成功");
            }
        }
        //返回
        return inInfo;
    }



    /**
     * 
     * 删除项目分类方法
     *
     * @Title: delete 
     * @return inInfo
     * checkRows        行集合
     * @return: EiInfo
     * Status           返回状态码
     * Msg              msg
     */
    public EiInfo delete(EiInfo inInfo) {
        //获取行集合
        List<Map> checkRows = (List)inInfo.get("checkRows");
        //判断
        if(checkRows.size() == 0 || checkRows == null) {
            inInfo.setStatus(-1);
            inInfo.setMsg("数据错误，请联系管理员");
            return inInfo;
        }
        for (Map map : checkRows) {
            String id = (String)map.get("id");
            if(StringUtils.isNotEmpty(id)) {
                //删除项目分类
                dao.delete("SQBZ03.deleteProjectInstance", id);
            }else {
                inInfo.setStatus(-1);
                inInfo.setMsg("参数错误，请联系管理员");
                return inInfo;
            }
        }
        //封装信息
        inInfo.setStatus(1);
        inInfo.setMsg("删除 成功");
        //返回
        return inInfo;
    }
}