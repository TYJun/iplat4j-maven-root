package com.baosight.wilp.fa.sq.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceFASQ00 extends ServiceBase {
    // 初始化方法
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.setCell("inqu_status",0,"deptName",inInfo.getString("deptName"));
        inInfo.setCell("info",0,"deptNum",inInfo.getString("deptNum"));
        inInfo.setCell("info",0,"deptName",inInfo.getString("deptName"));
        return inInfo;
    }

    // 查询方法
    @Override
    public EiInfo query(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        int offset = 0, limit = 50;
        if (inInfo.getBlock("resultPerson").getAttr().containsKey("offset") && inInfo.getBlock("resultPerson").getAttr().containsKey("limit")) {
            offset = (int) inInfo.getBlock("resultPerson").getAttr().get("offset");
            limit = (int) inInfo.getBlock("resultPerson").getAttr().get("limit");
        }
        if (inInfo.getRow("inqu_status",0) != null){
            map = inInfo.getRow("inqu_status",0);
        }
        List list = dao.query("FASQ01.queryAcPersonnel", map , offset, limit);
        int count = dao.count("FASQ01.queryAcPersonnel", map);
        inInfo.getBlock("resultPerson").getAttr().put("count",count);
        inInfo.setRows("resultPerson", list);
        return inInfo;
    }

    // 添加人员方法
    public EiInfo addPersonnel(EiInfo inInfo){
        String roleCode = inInfo.getString("roleCode");
        String roleName = inInfo.getString("roleName");
        String workNo = inInfo.getString("workNo");
        String name = inInfo.getString("name");
        String deptNum = inInfo.getString("deptNum");
        String deptName = inInfo.getString("deptName");
        Map<String, String> map = new HashMap<>();
        map.put("roleCode",roleCode);
        map.put("roleName",roleName);
        map.put("workNo",workNo);
        map.put("name",name);
        map.put("deptNum",deptNum);
        map.put("deptName",deptName);
        List list = dao.query("FASQ01.queryFaPermissions", map);
        if (CollectionUtils.isNotEmpty(list)){
            inInfo.setStatus(-1);
            inInfo.setMsg("该用户已授权，请勿重复授权");
        } else {
            // 插入固定资产权限表
            dao.insert("FASQ01.insertFaPermissions",map);
            // 查询xs_user中的ID作为MEMBER_ID
            List<Map<String,String>> memberIdList = dao.query("FASQ01.queryMemberId", map);
            // 查询xs_user_group中的ID作为PARENT_ID
            List<Map<String,String>> parentIdList = dao.query("FASQ01.queryParentId", map);
            if (CollectionUtils.isNotEmpty(memberIdList) && CollectionUtils.isNotEmpty(parentIdList)){
                map.put("memberId",memberIdList.get(0).get("memberId"));
                map.put("parentId",parentIdList.get(0).get("parentId"));
                map.put("recCreateTime", DateUtils.curDateTimeStr14());
                // 删除权限表权限
                dao.delete("FASQ01.deleteGroupMember",map);
                // 插入系统框架权限表
                dao.insert("FASQ01.insertGroupMember",map);
                // 清除缓存
                EiInfo eiInfo = new EiInfo();
                eiInfo.set(EiConstant.serviceId, "S_XS_101");
                EiInfo outInfo = XServiceManager.call(eiInfo);
            }
        }
        return inInfo;
    }
}
