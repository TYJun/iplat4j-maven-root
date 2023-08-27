package com.baosight.wilp.vi.dj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.vi.common.domain.ViVistingInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceVIDJ0102 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // 获取参数
        String visitingId = inInfo.getString("id");
        String type = inInfo.getString("type");
        List<ViVistingInfo> inquList = new ArrayList<>();
        List<Map<String, Object>> rowList = new ArrayList<>();
        List viFile = new ArrayList();
        switch (type) {
            case "PC":
                // 查询主表信息
                inquList = dao.query("VIDJ01.queryViVistingInfo", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                // 查询访客信息
                rowList = dao.query("VIZH01.queryViVistingInfoDetail", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                // 使用docId返回base64
                rowList.forEach(map -> {
                    List<String> base64List = new ArrayList<>();
                    String docIdStr = (String) map.get("docId");
                    if (!"".equals(docIdStr) && docIdStr != null) {
                        String[] split = docIdStr.split(",");
                        for (String docId : split) {
                            String base64 = "";
                            base64 = CommonUtils.imageToBase64Str(getFilePath(docId));
                            base64List.add(base64);
                        }
                        map.put("base64List", base64List);
                    }
                });
                // 查询文件信息
                List fileList = dao.query("VIDJ01.queryViAttachFile", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                inInfo.setRows("inqu_status", inquList);
                inInfo.setRows("result", rowList);
                inInfo.set("result", rowList);
                break;
            case "APP":
                // 查询主表信息
                inquList = dao.query("VIDJ01.queryViVistingInfo", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                // 查询访客信息
                rowList = dao.query("VIZH01.queryViVistingInfoDetailApp", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                if (CollectionUtils.isNotEmpty(rowList)) {
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
                    inInfo.set("result", base64List);
                    inInfo.setRows("result", base64List);
                }
                //查询附件信息
                viFile = dao.query("VIDJ01.queryViAttachFile", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                inInfo.setRows("inqu_status", inquList);
                inInfo.setRows("resultFile", viFile);
                break;
        }
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }

    // 审批
    public EiInfo submit(EiInfo info) {
        String id = info.getString("id");
        String type = info.getString("type");
        String auditorMan = info.getString("auditorMan");
        String auditorMemo = info.getString("auditorMemo");
        String auditorClientType = StringUtils.isNotEmpty(info.getString("auditorClientType")) ? info.getString("auditorClientType") : "APP";
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("id", id);
        map.put("visitingId", id);
        map.put("auditorIp", "");
        map.put("auditorMan", auditorMan);
        map.put("auditTime", DateUtils.curDateTimeStr19());
        map.put("auditorClientType", auditorClientType);
        map.put("auditorMemo", auditorMemo);
        map.put("desc", auditorMemo);
        switch (type) {
            case "pass":
                map.put("auditorStep", "1");
                map.put("auditFlag", "1");
                break;
            case "reject":
                map.put("auditorStep", "-1");
                map.put("auditFlag", "-1");
                break;
        }
        dao.update("VIDJ01.submit", map);
        dao.insert("VIDJ01.insertViAuditlog", map);
        return info;
    }

    public static String getFilePath(String docId) {
        //获取文件管理器
        PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
        //获取附件信息
        Map<String, String> docMap = fileUpLoadManager.getDocById(docId);
        //获取路径
        if (docMap == null || docMap.isEmpty()) {
            return "";
        } else {
            return docMap.get("realPath") + docMap.get("chgName");
        }
    }
}
