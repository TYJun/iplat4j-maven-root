package com.baosight.wilp.common.ys.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.ys.domain.CarriageDataResult;
import com.baosight.wilp.common.ys.domain.ResultData;

import net.sf.json.JSONObject;

public class CarriageUtil {
	
    /**
     * 1.生成任务号的头
     * @return
     */
    public static String createTop() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String head = sdf.format(new Date());
        return head;
    }
    
    /**
     * 2.插入运送历史表
     */
    @Async
    public static void saveCarriageHistory(Map<String, String> map) {
    	EiInfo info = new EiInfo();
        info.set("paramMap", map);
        info.set(EiConstant.serviceName, "YSDJ01");
        info.set(EiConstant.methodName, "saveCarriageHistory");
        XLocalManager.call(info);
    }
    
    /**
     * 3.保持APP图片
     */
    public static void dealPic(String showPath, String savePath, String billNo, String node, String type, String pics) {
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
            info.set("billNo", billNo);
            info.set("type", node);
            info.set("path", showPath + picId);
            MaintainUtil.putRequest("YSAP01", "uploadPic", info);
            try (FileOutputStream outStream = new FileOutputStream(file)) {
                outStream.write(MaintainUtil.castToImg(impStr));;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    // APP报表返回
    public static CarriageDataResult putResponse(Object obj, String respCode, String respMsg) {
    	CarriageDataResult carriageDataResult = new CarriageDataResult();
    	carriageDataResult.setRespCode(respCode);
    	carriageDataResult.setRespMsg(respMsg);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("obj", obj);
        carriageDataResult.setObj(resultMap);
        return carriageDataResult;
    }
    
}
