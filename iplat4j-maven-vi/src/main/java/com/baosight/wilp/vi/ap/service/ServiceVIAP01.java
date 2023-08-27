package com.baosight.wilp.vi.ap.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.vi.common.domain.ViVistingInfo;
import com.baosight.wilp.vi.utils.ViUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceVIAP01 extends ServiceBase {
    // 科室下拉框查询
    public EiInfo queryDept(EiInfo info) {
        List<Map<String, String>> list = dao.query("VIAP01.queryDept", new HashMap<String, Object>() {{
            put("deptName", info.getString("deptName"));
        }});
        EiInfo outInfo = new EiInfo();
        outInfo.set("result", list);
        return outInfo;
    }

    // 科室与人员是否匹配（提交检验）
    public EiInfo deptMateName(EiInfo info) {
        String workName = info.getString("workName");
        EiInfo staffByDept = BaseDockingUtils.getStaffByDept(info);
        List<Map<String, Object>> result = (List<Map<String, Object>>) staffByDept.get("result");
        if (CollectionUtils.isNotEmpty(result)) {
            for (Map map : result) {
                if (workName.equals(map.get("name"))) {
                    info.set("flag", true);
                    info.set("workNo", map.get("workNo"));
                    return info;
                }
            }
        }
        info.set("flag", false);
        return info;
    }

    // 查询访客信息
    public EiInfo queryVisitMsg(EiInfo info) {
        String visitType = info.getString("visitType");
        String visitApp = info.getString("visitApp");
        String name = "";
        String cardId = "";
        String deptNum = "";
        String workNo = "";
        String superMan = "";
        String queryType = "";
        switch (visitType){
            case "doctor":
                if (ViUtils.getDataCode("WILP.vi.lookOneself")) {
                    Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
                    name = (String) staffByUserId.get("name");
                    cardId = (String) staffByUserId.get("cardId");
                    deptNum = (String) staffByUserId.get("deptNum");
                    workNo = (String) staffByUserId.get("workNo");
                }
                name = StringUtils.isNotEmpty(info.getString("name")) ? info.getString("name") : name;
                cardId = StringUtils.isNotEmpty(info.getString("cardId"))? info.getString("cardId"):cardId;
                deptNum = StringUtils.isNotEmpty(info.getString("deptNum")) ? info.getString("deptNum") : deptNum;
                workNo = StringUtils.isNotEmpty(info.getString("workNo")) ? info.getString("workNo") : workNo;
                if ("14802".equals(workNo)){
                    superMan = "superMan";
                    deptNum = "";
                    name = "";
                    cardId = "";
                }
                break;
            case "visitor":
                queryType = "APP";
                if (visitApp == null || "".equals(visitApp)) {
                    info.set("statusCode", 404);
                    info.setMsg("查无记录");
                    return info;
                }
                break;
        }
        String batNo = info.getString("batNo");
        String auditorStep = info.getString("auditorStep");
        // 查询主表信息
        String finalName = name;
        String finalcardId = cardId;
        String finalDeptNum = deptNum;
        String finalSuperMan = superMan;
        String finalQueryType = queryType;
        List<ViVistingInfo> inquList = dao.query("VIDJ01.queryViVistingInfo", new HashMap<String, Object>() {{
            put("queryType", finalQueryType);
            put("visitApp", visitApp);
            put("visitingId", batNo);
            put("auditorStep", auditorStep);
            put("nterviewerName", finalName);
            put("cardId",finalcardId);
            put("deptNum", finalDeptNum);
            put("superMan", finalSuperMan);
        }});
        if (CollectionUtils.isNotEmpty(inquList)) {
            // 查询访客信息
            List<Map<String,Object>> rowList = dao.query("VIDJ01.queryViVistingInfoDetail", new HashMap<String, Object>() {{
                put("visitingId", inquList.get(0).getBatNo());
            }});
            info.set("ApprovalList", inquList);
            info.set("ViVistingInfo", inquList.get(0));
            List<Map<String, Object>> removeList = new ArrayList<>();
            for (int i = 0; i < rowList.size(); i++) {
                if (rowList.get(i).get("fileName") == null) {
                    removeList.add(rowList.get(i));
                    rowList.remove(i);
                    i--;
                }
            }
            // 处理base64
            Map<Object, List<Map<String, Object>>> fileName = rowList.stream().collect(Collectors.groupingBy(map -> map.get("fileName")));
            List<Map<String, Object>> base64List = new ArrayList<>();
            Map<String, Object> base64Map = new HashMap<>();
            fileName.forEach((o, maps) -> {
                List<String> list = new ArrayList<>();
                maps.forEach(stringObjectMap -> {
                    list.add((String) stringObjectMap.get("fileContent"));
                });
                base64Map.put((String) fileName.get(o).get(0).get("visitingId"), list);
            });
            // 处理数据
            Map<Object, List<Map<String, Object>>> visitingIdList = rowList.stream().collect(Collectors.groupingBy(map -> map.get("visitingId")));
            visitingIdList.forEach((o, maps) -> {
                for (String key : base64Map.keySet()) {
                    if (key.equals((String) o)) {
                        visitingIdList.get(o).get(0).put("base64List", base64Map.get(key));
                        base64List.add(visitingIdList.get(o).get(0));
                    }
                }
            });
            base64List.addAll(removeList);
            info.set("ViVistingDetailInfo", base64List);
            info.set("statusCode", 200);
            info.setMsg("最近一条记录");
        } else {
            info.set("statusCode", 404);
            info.setMsg("查无记录");
        }
        return info;
    }
}
