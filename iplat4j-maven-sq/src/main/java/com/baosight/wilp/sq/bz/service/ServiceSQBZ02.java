package com.baosight.wilp.sq.bz.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.bz.domain.SQBZ02;
import com.baosight.wilp.sq.common.UtilCode;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 
 * 考核项目逻辑处理，页面初始化方法，查询方法，删除标准方法
 * <p>页面初始化方法initLoad
 * <p>查询方法 query
 * <p>删除标准方法 delete
 * @Title: ServiceSQBZ02.java
 * @ClassName: ServiceSQBZ02
 * @Package：com.baosight.wilp.sq.bz.service
 * @author: zhangjiaqian
 * @date: 2021年7月19日 下午4:54:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQBZ02 extends ServiceBase{


    /**
     * id全局变量，用于区分查询
     */
    private String idParam = null;



/**
 * 页面初始化方法
 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
 * &lt;p&gt;Description: &lt;/p&gt;
 * @param inInfo
 * id                   主键
 * projectCode          项目编码
 * projectName          项目名称
 * projectRemark        项目备注
 * creator              记录创建人
 * createTime           创建时间
 * modifier             记录修改人
 * modifyTime           记录修改时间
 * standardId           主题外键
 * orderNumber          菜单顺序
 * @return    EiInfo
 * id                   主键
 * projectCode          项目编码
 * projectName          项目名称
 * projectRemark        项目备注
 * creator              记录创建人
 * createTime           创建时间
 * modifier             记录修改人
 * modifyTime           记录修改时间
 * standardId           主题外键
 * orderNumber          菜单顺序
 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
 */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取id
        String id = (String)inInfo.get("id");
        idParam = id;
        //创建EiInfo对象
        EiInfo outInfo = new EiInfo();
        //判断id是否为空
        if(!id.isEmpty()) {
            Map param = new HashMap();
            //封装数据
            param.put("id", id);
            //查询数据
            List<SQBZ02> query = dao.query("SQBZ02.query", param);
            //构建eiBlock
            EiBlock eiBlock = new EiBlock(new EiBlockMeta());
            eiBlock.setRows(query);
            HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
            hashMap.put("result", eiBlock);
            //返回信息
            outInfo.setBlocks(hashMap);
        }else {
            //构建返回信息
            outInfo = super.query(inInfo, "SQBZ02.query", new SQBZ02());
        }
        //返回
        return outInfo;
    }



/**
 * 查询方法
 * &lt;p&gt;Title: query&lt;/p&gt;  
 * &lt;p&gt;Description: &lt;/p&gt;
 * @param inInfo
 * id                   主键
 * projectCode          项目编码
 * projectName          项目名称
 * projectRemark        项目备注
 * creator              记录创建人
 * createTime           创建时间
 * modifier             记录修改人
 * modifyTime           记录修改时间
 * standardId           主题外键
 * orderNumber          菜单顺序
 * @return    EiInfo
 * id                   主键
 * projectCode          项目编码
 * projectName          项目名称
 * projectRemark        项目备注
 * creator              记录创建人
 * createTime           创建时间
 * modifier             记录修改人
 * modifyTime           记录修改时间
 * standardId           主题外键
 * orderNumber          菜单顺序
 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
 */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //构建block块
        EiBlock block = new EiBlock("inqu_status");
        block.setCell(0, "id", idParam);
        //封装信息
        inInfo.addBlock(block);

        //构建返回信息
        //返回
        Map map = new HashMap<>();
        map.put("id", idParam);
        List outInfo = dao.query("SQBZ02.query", map);
        inInfo.getBlock("result").addRows(outInfo);
        return inInfo;

    }



    /**
     * 
     * 保存方法
     *
     * @Title: save 
     * @return inInfo
     *  id                  主键
     *  staffByUserId       登陆人工号
     * projectCode          项目编码
     * projectName          项目名称
     * projectRemark        项目备注
     * creator              记录创建人
     * createTime           创建时间
     * standardId           主题外键
     * orderNumber          菜单顺序
     * @return: EiInfo
     *  id                  主键
     * projectCode          项目编码
     * projectName          项目名称
     * projectRemark        项目备注
     * creator              记录创建人
     * createTime           创建时间
     * standardId           主题外键
     * orderNumber          菜单顺序
     */
    public EiInfo save(EiInfo inInfo) {
       //获取集合
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
                int update = dao.update("SQBZ02.updateProject", map);
                if(update == 0) {
                    //返回
                    inInfo.setStatus(-1);
                    inInfo.setMsg(map.get("projectName")+"保存失败");
                    return inInfo;
                }
                //feng'zhu'a'na'g
                inInfo.setStatus(1);
                inInfo.setMsg("保存成功");
            }else {
                //获取项目编码
                String projectCode = UtilCode.projectCode();
                //否则是新增操作
                map.put("id", UUID.randomUUID().toString());
                map.put("projectCode", projectCode);
                map.put("creator", staffByUserId.get("name"));
                map.put("createTime", time);
                map.put("standardId", idParam);
                dao.insert("SQBZ02.insertProject", map);
                inInfo.setStatus(1);
                inInfo.setMsg("保存成功");
            }
        }
        //返回
        return inInfo;
    }



    /**
     * 
     * 删除标准方法
     *
     * @Title: delete 
     * @param  inInfo
     * checkRows           获取行集合
     * @return 
     * Status              返回状态码
     * Msg                  msg
     * @return: EiInfo
     */
    public EiInfo delete(EiInfo inInfo) {
        //获取行集合
        List<Map> checkRows = (List)inInfo.get("checkRows");
        //判断行是否为空
        if(checkRows.size() == 0 || checkRows == null) {
            inInfo.setStatus(-1);
            inInfo.setMsg("数据错误，请联系管理员");
            return inInfo;
        }
        //集合遍历 
        for (Map map : checkRows) {
           //获取id
            String id = (String)map.get("id");
            if(StringUtils.isNotEmpty(id)) {
                //删除项目
                dao.delete("SQBZ02.deleteProject", id);
                //删除项目标准
                dao.delete("SQBZ02.deleteProjectInstance", id);
            }else {
                //封装返回
                inInfo.setStatus(-1);
                inInfo.setMsg("参数错误，请联系管理员");
                return inInfo;
            }
        }
        //封装返回信息
        inInfo.setStatus(1);
        inInfo.setMsg("删除 成功");
        //返回inInfo
        return inInfo;
    }


}
