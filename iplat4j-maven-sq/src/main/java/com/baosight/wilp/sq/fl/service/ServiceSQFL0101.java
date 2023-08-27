package com.baosight.wilp.sq.fl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.fl.domain.SQFL01;

/**
 * 新增人员分组逻辑处理: 页面初始化方法，新增人员分组，页面查询 
 * <p>新增人员分组add
 * <p>页面初始化方法initload
 * <p>页面查询 query
 * @Title: ServiceSQFL0101.java
 * @ClassName: ServiceSQFL0101
 * @Package：com.baosight.wilp.sq.fl.service
 * @author: zhangjiaqian
 * @date: 2021年7月27日 下午1:49:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQFL0101 extends ServiceBase{
    /**
     * <p>
     * 1.取消EF:EFGrid blockId="result"加载页面时会出现空白行
     * <p>
     * @title: initLoad 
     * @Description: 页面初始化方法
     * @param EiInfo
     * 
     * @return EiInfo
     * <author>  // 修改人
	 * <time>    // 修改时间
	 * <version> // 版本
	 * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // 1.取消EF:EFGrid blockId="result"加载页面时会出现空白行
        EiInfo outInfo = new EiInfo();
        EiBlock resultBlock = outInfo.addBlock(EiConstant.resultBlock);
        resultBlock.addBlockMeta(new SQFL01().eiMetadata);
        return outInfo;
    }



    /**
     * 
     * TODO(新增人员分组)
     * @Title: add 
     * @param inInfo
     * id               主键                 
     * perGroupNo       人员组编号   
     * perGroupName     人员组名称
     * createTime       创建时间
     * wrokNO           人员工号
     * name             人员名称
     * deptNum          科室编号
     * deptName         科室名称
     * groupId          分组id
     * @return 
     * @return: EiInfo
     * id               主键                 
     * perGroupNo       人员组编号   
     * perGroupName     人员组名称
     * createTime       创建时间
     * wrokNO           人员工号
     * name             人员名称
     * deptNum          科室编号
     * deptName         科室名称
     * groupId          分组id
     * <author>  // 修改人
	 * <time>    // 修改时间
	 * <version> // 版本
	 * <desc>    // 描述修改内容
     */
    public EiInfo add(EiInfo inInfo) {
        List<Map> data = (List)inInfo.get("data");
        //分组编码
        String perGroupNo = (String)inInfo.get("perGroupNo");
        //分组名
        String perGroupName = (String)inInfo.get("perGroupName");
        //获取当前时间
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //查询分组是否存在
        Map m = new HashMap();
        m.put("perGroupNo", perGroupNo);
        m.put("perGroupName", perGroupName);
        List<Integer> query = dao.query("SQFL01.queryGroupNo", m);
        Integer integer = query.get(0);
        if(integer!=0) {
            inInfo.setStatus(-1);
            inInfo.setMsg("( " + perGroupNo + "---" + perGroupName + " ),该分组已存在");
            return inInfo;
        }

        Map param = new HashMap();
        //分组id
        String groupId = UUID.randomUUID().toString();
        //保存人员组信息
        for (Map map : data) {
            //人员组编号
            param.put("perGroupNo", perGroupNo);
            //人员组名称
            param.put("perGroupName", perGroupName);
            //主键
            param.put("id", UUID.randomUUID().toString());
            //人员工号
            param.put("workNo", map.get("workNo"));
            //人员名称
            param.put("name", map.get("name"));
            //科室编号
            param.put("deptNum", map.get("deptNum"));
            //科室名称
            param.put("deptName", map.get("deptName"));
            //创建时间
            param.put("createTime", createTime);
            //人员分组id
            param.put("groupId", groupId);
            //查询
            dao.insert("SQFL01.insetPerGroup", param);
        }
        //返回状态码
        inInfo.setStatus(1);
        //返回msg
        inInfo.setMsg("保存成功");
        return inInfo;
    }

    
    /**
     * 页面查询
     * 
     * @Title: query 
     * @param inInfo
     * perGroup         人员分组编号
     * @return
     * groupID          人员分组id
     * perGroupNo       人员组编号
     * perGroupName     人员组名称
     * name             人员名称    
     * workNo           人员工号
     * deptNum          科室编号
     * deptName         科室名称
     * <author>  // 修改人
	 * <time>    // 修改时间
	 * <version> // 版本
	 * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //人员分组编号
        String perGroup = ServiceSQFL0103.perGroup;
        //创建查询block
        EiBlock block = new EiBlock("inqu_status");
        block.setCell(0, "perGroupNo", perGroup);
        inInfo.addBlock(block);
        //封装查询参数
        EiInfo outInfo = super.query(inInfo, "SQFL01.queryGroup", new SQFL01());
        //返回
        return outInfo;
    }
}
