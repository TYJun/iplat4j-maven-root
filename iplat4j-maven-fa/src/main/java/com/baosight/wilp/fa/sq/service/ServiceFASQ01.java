package com.baosight.wilp.fa.sq.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceFASQ01 extends ServiceBase {
    // 初始化查询
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, Object> queryMap = new HashMap<>(4);
        queryMap.put("parentEname", "root");
        List<Map<String, String>> treeList = dao.query("FASQ01.queryFaPermissionsTree", queryMap);
        inInfo.set("treeList", treeList);
        return inInfo;
    }

    // 查询
    @Override
    public EiInfo query(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        int offset = 0, limit = 50;
        if (inInfo.getBlock("result").getAttr().containsKey("offset") && inInfo.getBlock("result").getAttr().containsKey("limit")) {
            offset = (int) inInfo.getBlock("result").getAttr().get("offset");
            limit = (int) inInfo.getBlock("result").getAttr().get("limit");
        }
        if (inInfo.getRow("inqu_status",0) != null){
            map = inInfo.getRow("inqu_status",0);
        }
        List list = dao.query("FASQ01.queryFaPermissions", map , offset, limit);
        int count = dao.count("FASQ01.queryFaPermissions", map);
        inInfo.getBlock("result").getAttr().put("count",count);
        inInfo.setRows("result", list);
        return inInfo;
    }

    // 查询系统配置权限
    public EiInfo queryFaPermissionsTree(EiInfo inInfo) {
        // 1.设置回显的最大条数，默认为10,现在设置为1000
        inInfo.set("result-limit", 1000);
        // 2.获取选中的树节点，初始为root
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        Map<String, Object> queryMap = new HashMap<>(4);
        queryMap.put("parentEname", pEname);
        if (StringUtils.isEmpty(pEname)) {
            return inInfo;
        } else {
            List<Map<String, String>> list = new ArrayList<>();
            List<Map<String, String>> treeList = dao.query("FASQ01.queryFaPermissionsTree", queryMap);
            // 3.通过树节点查询子节点
            if ("root".equals(pEname)) {
                list = treeList.stream().collect(Collectors.toList());
            } else {
                list = dao.query("FASQ01.queryFaPermissionsTreeDetail", queryMap);
            }
            EiInfo outInfo = new EiInfo();
            // 4.将查询到的子节点跟父节点绑定
            EiBlock outBlock = outInfo.addBlock(pEname);
            outBlock.addRows(list);
            return outInfo;
        }
    }

    // 查询系统业务科室
    public EiInfo queryDeptTree(EiInfo inInfo) {
        // 1.设置回显的最大条数，默认为10,现在设置为1000
        inInfo.set("result-limit", 1000);
        // 2.获取选中的树节点，初始为root
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        String deptName = inInfo.getString("deptName");
        Map<String, Object> queryMap = new HashMap<>(4);
        queryMap.put("deptName", deptName);
        List<Map<String, String>> treeList = dao.query("FASQ01.queryDeptTree", queryMap);
        // 3.通过树节点查询子节点
        if (StringUtils.isEmpty(pEname)) {
            pEname = "root";
        }
        EiInfo outInfo = new EiInfo();
        // 4.将查询到的子节点跟父节点绑定
        EiBlock outBlock = outInfo.addBlock(pEname);
        outBlock.addRows(treeList);
        return outInfo;
    }

    // 删除用户授权
    public EiInfo deleteRoles(EiInfo inInfo){
        String workNo = inInfo.getString("workNo");
        String roleCode = inInfo.getString("roleCode");
        String deptNum = inInfo.getString("deptNum");
        Map<String, String> map = new HashMap<>();
        map.put("workNo",workNo);
        map.put("roleCode",roleCode);
        map.put("deptNum",deptNum);
        // 查询xs_user中的ID作为MEMBER_ID
        List<Map<String,String>> memberIdList = dao.query("FASQ01.queryMemberId", map);
        // 查询xs_user_group中的ID作为PARENT_ID
        List<Map<String,String>> parentIdList = dao.query("FASQ01.queryParentId", map);
        if (CollectionUtils.isNotEmpty(memberIdList) && CollectionUtils.isNotEmpty(parentIdList)){
            map.put("memberId",memberIdList.get(0).get("memberId"));
            map.put("parentId",parentIdList.get(0).get("parentId"));
            // 删除权限表权限
            dao.delete("FASQ01.deleteGroupMember",map);
            // 删除资产权限
            dao.delete("FASQ01.deleteRoles",map);
            // 清除缓存
            EiInfo eiInfo = new EiInfo();
            eiInfo.set(EiConstant.serviceId, "S_XS_101");
            EiInfo outInfo = XServiceManager.call(eiInfo);
        }
        return inInfo;
    }

}
