package com.baosight.wilp.im.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.util.Base64;

import com.baosight.bpm.util.StringUtil;

/**
 * 
 * 图片保存工具类:base64转文件
 * <p>base64转文件 basa64ToFile
 * 
 * @Title: DevicePicUtil.java
 * @ClassName: DevicePicUtil
 * @Package：com.baosight.wilp.di.common.util
 * @author: zhangjiaqian
 * @date: 2021年6月8日 下午7:30:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class DevicePicUtil {

    
    
    /**
     * base64转文件
     * 
     * @param fileStr:base64字符串(完整)
     * @param imgFilePath:文件保存路径
     * @return boolean
     */
    public static boolean basa64ToFile(String fileStr, String imgFilePath) {
        if (StringUtil.isEmpty(fileStr)) {
            return false;
        } else {
            Base64 decoder = new Base64();
            OutputStream out = null;
            byte[] decode = null;
            try {
                if(fileStr.contains(",")) {
                    fileStr = fileStr.split(",")[1];
                    decode = decoder.decode(fileStr);
                    //循环图片流并补充内容
                    for (int i = 0; i < decode.length; i++) {
                        if (decode[i] < 0) {
                            decode[i] += 256;
                        }
                    }
                }else {
                    decode = decoder.decode(fileStr);
                    //循环图片流并补充内容
                    for (int i = 0; i < decode.length; i++) {
                        if (decode[i] < 0) {
                            decode[i] += 256;
                        }
                    }
                }
                //写入文件
                File file=new File(imgFilePath);
                file.createNewFile();
                out = new FileOutputStream(file);
                out.write(decode);
                out.flush();
                out.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
