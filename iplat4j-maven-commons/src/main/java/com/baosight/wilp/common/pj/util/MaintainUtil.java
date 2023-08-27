package com.baosight.wilp.common.pj.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.common.ys.domain.ResultData;

import net.sf.json.JSONObject;

/**
 * 维修工具类
 * 
 * @author testzw
 *
 */
public class MaintainUtil {
    private static Dao dao = (Dao)PlatApplicationContext.getBean("dao");
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    public static String basePath;
    /**
     * 生成任务号的头
     * 
     * @return
     */
    public static String createTop() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String head = sdf.format(new Date());
        return head;
    }

    /**
     * 保存流程记录
     */
    @Async
    public static void saveNodeMsg(Map<String, String> map) {
        EiInfo info = new EiInfo();
        String loginName =map.get("loginName");
        map.put("loginName", loginName);
        map.put("id", UUID.randomUUID().toString());
        info.set("paramMap", map);
        info.set(EiConstant.serviceName, "MTRG01");
        info.set(EiConstant.methodName, "saveNodeMsg");
        XLocalManager.call(info);
    }

    public static EiInfo getGroup(String loginName) {
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("loginName", loginName);
        eiInfo.set(EiConstant.serviceId, "S_XS_55");
        return XServiceManager.call(eiInfo);

    }

    /**
     * 保存流程过程信息
     */
    @Async
    public static void saveDealMsg(Map<String, String> map) {
        EiInfo info = new EiInfo();
        String loginName =map.get("loginName");
        map.put("loginName", loginName);
        map.put("id", UUID.randomUUID().toString());
        info.set("paramMap", map);
        info.set(EiConstant.serviceName, "MTRG01");
        info.set(EiConstant.methodName, "saveDealMsg");
        XLocalManager.call(info);
    }

    /**
     * 前端参数转map
     * 
     * @param inInfo
     * @param list
     * @return
     */
    public static Map<String, String> changeToMap(EiInfo inInfo, List<String> list) {
        Map<String, String> map = new HashMap<>();
        Map param = inInfo.getAttr();
        list.forEach(e -> map.put(e, (String)param.get(e)));
        return map;
    }

    /**
     * 将查询出的数据以eiinfo的格式还回去
     * 
     * @param list
     * @return
     */
    public static EiInfo changeToEiInfo(List list) {
        EiInfo outInfo = new EiInfo();

        EiBlock eiBlock = new EiBlock(new EiBlockMeta());
        eiBlock.setRows(list);
        HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
        hashMap.put("result", eiBlock);
        outInfo.setBlocks(hashMap);
        if (!CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("查询成功");
        } else {
            outInfo.setMsg("未获取到数据");
        }
        return outInfo;
    }

    /**
     * 封装接口调用的请求 调用该方法前需要eiInfo.set(k,v)将参数传好
     * 
     * @param serviceName
     * @param methodName
     * @param paramInfo
     * @return
     */
    public static EiInfo putRequest(String serviceName, String methodName, EiInfo eiInfo) {
        eiInfo.set(EiConstant.serviceName, serviceName);
        eiInfo.set(EiConstant.methodName, methodName);
        EiInfo outInfo = XLocalManager.call(eiInfo);
        return outInfo;
    }

    public static ResultData putResponse(Object obj, String respCode, String respMsg) {
        ResultData resultData = new ResultData();
        resultData.setRespCode(respCode);
        resultData.setRespMsg(respMsg);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("obj", obj);
        resultData.setMap(resultMap);
        return resultData;
    }

    public static EiInfo changeToEiInfo(Map map) {
        EiInfo info = new EiInfo();
        info.setAttr(map);
        return info;
    }

    public static Map<String, String> getUserInfo(String userName) {
        EiInfo info = new EiInfo();
        info.set("name", userName);
        info.set(EiConstant.serviceName, "YSAP01");
        info.set(EiConstant.methodName, "getUserInformation");
        EiInfo infoLogin = XLocalManager.call(info);
        Map<String, String> map = (Map<String, String>)infoLogin.get("map");
        return map;
    }

    /**
     * base64图片转字节数组
     * 
     * @param img
     * @return
     */
    public static byte[] castToImg(String img) {
        // base64转化为字节数组
        return decoder.decode(img);
    }

    public static String castToBase64(String url) {
        byte[] b = getByte(url);
        return encoder.encodeToString(b);
    }

    /**
     * 图片保存公共方法
     * 
     * @param path
     * @param taskNo
     * @param node
     * @param type
     * @param pics
     */
    public static void dealPic(String showPath, String savePath, String taskNo, String node, String type, String pics) {
        // 最多允许存放3张图片
        List<String> impList = new ArrayList<String>();
        if (type.equals("more")) {
            JSONObject js = JSONObject.fromObject(pics);
            impList.add(js.get("pic1").toString());
            impList.add(js.get("pic2").toString());
            impList.add(js.get("pic3").toString());
        } else if (type.equals("one")) {
            impList.add(pics);
        }

        for (int i = 0; i < impList.size(); i++) {
            String impStr = impList.get(i);
            if (StringUtils.isBlank(impStr)) {
                continue;
            }

            File files = new File(savePath);
            if (!files.isDirectory()) {
                files.mkdir();
            }
            String picId = UUID.randomUUID().toString();
            File file = new File(savePath + picId + ".jpg");
            EiInfo info = new EiInfo();
            info.set("id", picId);
            info.set("taskNo", taskNo);
            info.set("type", node);
            info.set("path", showPath + picId);
            MaintainUtil.putRequest("MTRG0101", "uploadPic", info);
            try (FileOutputStream outStream = new FileOutputStream(file)) {
                outStream.write(MaintainUtil.castToImg(impStr));;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * app和pc获取账套的公共方法
     * 
     * @param workNo
     * @param type
     */
    public static String getDatagroupCode(EiInfo info) {
        if (StringUtils.isBlank((String)info.get("type"))) {
            return DatagroupUtil.getDatagroupCode();
        }
        return (String)info.get("groupCode");
    }

    public static byte[] getByte(String picUrl) {
        File file = new File(picUrl);
        try (FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String, String>> getWorkList(String roles, String taskNo) {
        List<String> workList = new ArrayList<>();
        String[] roleArray = roles.split(",");
        for (int i = 0; i < roleArray.length; i++) {
            switch (roleArray[i]) {
                case "WXR":
                    List<String> WXR = dao.query("MTMS01.queryWXR", taskNo);
                    workList.addAll(WXR);
                    break;
                case "WXZZ":
                    List<String> WXZZ = dao.query("MTMS01.queryWXZZ", taskNo);
                    workList.addAll(WXZZ);
                    break;
                case "BXR":
                    List<String> BXR = dao.query("MTMS01.queryBXR", taskNo);
                    workList.addAll(BXR);
                    break;
                case "DDZX":
                    List<String> DDZX = dao.query("MTMS01.queryDDZX", taskNo);
                    workList.addAll(DDZX);
                    break;
            }
        }
        workList = workList.stream().filter(s -> !StringUtils.isBlank(s)).collect(Collectors.toList());
        List<Map<String, String>> list = new ArrayList<>();
        workList.forEach(e -> {
            Map<String, String> map = new HashMap<>();
            map.put("workNo", e);
            map.put("phone", (String)dao.query("MTMS01.queryTel", e).get(0));
            list.add(map);
        });

        return list;
    }

    public static String castComment(String comment, String taskNo) {
        if (comment.indexOf("$req_tel_num$") != -1) {
            String temp = (String)dao.query("MTMS01.getReqTelNum", taskNo).get(0);
            comment = comment.replace("$req_tel_num$", temp);
        }
        if (comment.indexOf("$report_name$") != -1) {
            String temp = (String)dao.query("MTMS01.getReportName", taskNo).get(0);
            comment = comment.replace("$report_name$", temp);
        }
        if (comment.indexOf("$req_spot_name$") != -1) {
            String temp = (String)dao.query("MTMS01.getReqSpotName", taskNo).get(0);
            comment = comment.replace("$req_spot_name$", temp);
        }
        if (comment.indexOf("$comments$") != -1) {
            String temp = (String)dao.query("MTMS01.getComments", taskNo).get(0);
            comment = comment.replace("$comments$", temp);
        }
        if (comment.indexOf("$maintain_accept_man$") != -1) {
            String temp = (String)dao.query("MTMS01.getAcceptMan", taskNo).get(0);
            comment = comment.replace("$maintain_accept_man$", temp);
        }
        if (comment.indexOf("$maintain_send_man$") != -1) {
            String temp = (String)dao.query("MTMS01.getSendMan", taskNo).get(0);
            comment = comment.replace("$maintain_send_man$", temp);
        }
        if (comment.indexOf("$maintain_agree_man$") != -1) {
            String temp = (String)dao.query("MTMS01.getAgreeMan", taskNo).get(0);
            comment = comment.replace("$maintain_agree_man$", temp);
        }
        if (comment.indexOf("$agree_man_phone$") != -1) {
            String temp = (String)dao.query("MTMS01.getAgreeManTel", taskNo).get(0);
            comment = comment.replace("$agree_man_phone$", temp);
        }
        if (comment.indexOf("$maintain_finish_man$") != -1) {
            String temp = (String)dao.query("MTMS01.getFinishMan", taskNo).get(0);
            comment = comment.replace("$maintain_finish_man$", temp);
        }
        if (comment.indexOf("$maintain_comments$") != -1) {
            String temp = (String)dao.query("MTMS01.getMaintainComments", taskNo).get(0);
            comment = comment.replace("$maintain_comments$", temp);
        }
        return comment;
    }

}
