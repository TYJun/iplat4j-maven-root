package com.baosight.wilp.sq.bz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.bz.domain.SQBZ03;
import com.baosight.wilp.sq.common.TyepCode;
import com.baosight.wilp.sq.common.UtilCode;
import com.baosight.wilp.utils.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 录入标准逻辑处理 ,保存考核主题,页面初始化方法,问卷类型
 * <p>页面初始化方法   initLoad
 * <p>保存考核主题  add
 * <p>问卷类型  sqType
 * @Title: ServiceSQBZ04.java
 * @ClassName: ServiceSQBZ04
 * @Package：com.baosight.wilp.sq.bz.service
 * @author: zhangjiaqian
 * @date: 2021年7月20日 上午11:06:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQBZ04 extends ServiceBase{

    
    /**
     * 
     * 页面初始化方法
     * @Title initLoad
     * @param inInfo
     * id                       主键
     * standardCode             标准编码   
     * standardName             标准名称  
     * remark                   备注
     * creator                  记录创建人   
     * createTime               创建时间
     * asseccDeptName           调查对象
     * assessWorkNameleader     评价负责人名
     * assessMail               负责人邮箱    
     * assessTypeCode           问卷类型编码
     * assessTypeName           问卷类型名
     * datagroupcode            账套
     * @return EiInfo
     * id                       主键
     * standardCode             标准编码   
     * standardName             标准名称  
     * remark                   备注
     * creator                  记录创建人   
     * createTime               创建时间
     * asseccDeptName           调查对象
     * assessWorkNameleader     评价负责人名
     * assessMail               负责人邮箱    
     * assessTypeCode           问卷类型编码
     * assessTypeName           问卷类型名
     * datagroupcode            账套
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取id
        String id = (String)inInfo.get("id");
        //创建对象
        EiInfo initLoad = new EiInfo();
        //判断id是否为空
        if( null == id || "" == id) {
            initLoad = super.initLoad(inInfo);
        }else {
            List<Map> query = dao.query("SQBZ04.queryStandard", id);      
            if(query.isEmpty()) {
                initLoad.setStatus(-1);
                initLoad.setMsg("数据错误，请联系管理员");
                return initLoad;
            }
            Map map = query.get(0);
            initLoad.set("inqu_status-standardName", map.get("standardName"));
            initLoad.set("inqu_status-asseccDeptName", map.get("assessDeptName"));
            initLoad.set("inqu_status-assessWorkNameleader", map.get("assessWorkNameLeader"));
            initLoad.set("sqType", map.get("assessTypeCode"));
            initLoad.set("inqu_status-assessMail", map.get("assess_Mail"));
            initLoad.set("inqu_status-remark", map.get("remark"));
            initLoad.set("id", id);
        }
        return initLoad;
    }
    
    
    
    /**
     * 
     * 保存考核主题
     * @Title add
     * @param inInfo
     * staffByUserId            获取用户人信息
     * id                       主键
     * standardCode             标准编码   
     * standardName             标准名称  
     * remark                   备注
     * creator                  记录创建人   
     * createTime               创建时间
     * asseccDeptName           调查对象
     * assessWorkNameleader     评价负责人名
     * assessMail               负责人邮箱    
     * assessTypeCode           问卷类型编码
     * assessTypeName           问卷类型名
     * datagroupcode            账套
     * @return EiInfo
     * id                       主键
     * standardCode             标准编码   
     * standardName             标准名称  
     * remark                   备注
     * creator                  记录创建人   
     * createTime               创建时间
     * asseccDeptName           调查对象
     * assessWorkNameleader     评价负责人名
     * assessMail               负责人邮箱    
     * assessTypeCode           问卷类型编码
     * assessTypeName           问卷类型名
     * datagroupcode            账套
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo add(EiInfo inInfo) {
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取当前时间
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //账套
        String datagroupcode = DatagroupUtil.getDatagroupCode();
        //获取参数
        Map param = new HashMap();
        param.put("assessTypeCode", inInfo.get("assessTypeCode"));
        param.put("assessTypeName", inInfo.get("assessTypeName"));
        param.put("standardName", inInfo.get("standardName"));
        param.put("asseccDeptName", inInfo.get("asseccDeptName"));
        param.put("assessWorkNameleader", inInfo.get("assessWorkNameleader"));
        param.put("assessMail", inInfo.get("assessMail"));
        param.put("remark", inInfo.get("remark"));
        param.put("standardCode", UtilCode.dangerCode());
        param.put("creator", staffByUserId.get("name"));
        param.put("createTime", createTime);
        param.put("datagroupcode", datagroupcode);
        String id = (String)inInfo.get("id");
        if(StringUtils.isEmpty(id)) {
            dao.insert("SQBZ04.insetStandard", param);
        }else {
            param.put("id", id);
            dao.update("SQBZ04.updateStandard", param);
        }
        //封装返回信息
        inInfo.setMsg("保存成功");
        inInfo.setStatus(1);
        //返回
        return inInfo;
    }

    
    /**
     * 
     * 问卷类型
     *
     * @Title: sqType 
     * @param inInfo
     * param        问卷类型
     * @return 
     * param        问卷类型
     * @return: EiInfo
     */
    public EiInfo sqType(EiInfo inInfo) {
        //创建集合
        List<Map<String,String>> param = new ArrayList<Map<String,String>>();
        try {
            //获取小代码
            param = (List<Map<String, String>>)TyepCode.dealUseDay("WILP.sq.sqType");
            //封装参数
            inInfo.addBlock("sqType").addRows(param);
            inInfo.getBlock("sqType").setBlockMeta(new SQBZ03().eiMetadata);
        } catch (Exception e) {
            e.printStackTrace();
            return inInfo;
        }
        //返回
        return inInfo;
    }

}
