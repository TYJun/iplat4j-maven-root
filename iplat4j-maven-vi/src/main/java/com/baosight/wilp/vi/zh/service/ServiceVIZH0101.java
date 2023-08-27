package com.baosight.wilp.vi.zh.service;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.vi.common.domain.ViVistingInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceVIZH0101 extends ServiceBase {
    // 初始化
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
                //查询附件信息
                viFile = dao.query("VIDJ01.queryViAttachFile", new HashMap<String, Object>() {{
                    put("visitingId", visitingId);
                }});
                inInfo.setRows("inqu_status", inquList);
                inInfo.setRows("result", rowList);
                inInfo.set("result", rowList);
                inInfo.setRows("resultFile", viFile);
                inInfo.setRows("resultA", inquList);
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
                    inInfo.setRows("resultA", inquList);
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

    public EiInfo queryTabA(EiInfo info) {
        String billNo = info.getString("billNo");
        try {
            List<Map<String, String>> list = dao.query("CPZH01.queryDetail1", new HashMap<String, String>() {{
                put("billNo", billNo);
            }});
            info.setStatus(EiConstant.STATUS_SUCCESS);
            info.setRows("resultA", list);
        } catch (Exception e) {
            info.setMsg(e.getMessage());
            info.setStatus(EiConstant.STATUS_FAILURE);
        }
        return info;
    }

    public EiInfo queryTabB(EiInfo info) {
        String billNo = info.getString("billNo");
        try {
            List<Map<String, String>> list = dao.query("CPZH01.queryDetail2", new HashMap<String, String>() {{
                put("billNo", billNo);
            }});
            info.setStatus(EiConstant.STATUS_SUCCESS);
            info.setRows("resultB", list);
        } catch (Exception e) {
            info.setMsg(e.getMessage());
            info.setStatus(EiConstant.STATUS_FAILURE);
        }
        return info;
    }

    /**
     * @param inInfo querySql 查询sql
     *               countSql 统计sql
     *               resultBlock blockId
     *               blockMeta EiBlockMeta
     * @Title: queryTabGrid
     * @Description: TabGrid查询方法
     * @return: EiInfo
     */
    private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
                                EiBlockMeta blockMeta) {
        // 调用封装方法构造map
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
        // 查询数据
        List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
        // 获取总数
        int count = dao.count(countSql, map);
        // 数据返回
        return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
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
