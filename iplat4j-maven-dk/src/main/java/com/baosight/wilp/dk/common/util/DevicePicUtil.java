package com.baosight.wilp.dk.common.util;

import com.baosight.bpm.util.StringUtil;
import org.apache.commons.net.util.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * 图片保存工具类
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
        //文件字符串不为空
        if (StringUtil.isEmpty(fileStr)) {
            return false;
        } else {
            //获取base64对象
            Base64 decoder = new Base64();
            //输出流对象
            OutputStream out = null;
            byte[] decode = null;
            try {
                //字符串包含逗号，解析字符串
                if(fileStr.contains(",")) {
                    fileStr = fileStr.split(",")[1];
                    decode = decoder.decode(fileStr);
                    //循环decode长度并判断小于0则添加256字节
                    for (int i = 0; i < decode.length; i++) {
                        if (decode[i] < 0) {
                            decode[i] += 256;
                        }
                    }
                //字符串不包含逗号，解析字符串
                }else {
                    decode = decoder.decode(fileStr);
                    //循环decode长度并判断小于0则添加256字节
                    for (int i = 0; i < decode.length; i++) {
                        if (decode[i] < 0) {
                            decode[i] += 256;
                        }
                    }
                }
                //创建文件并将解析的字符串写到文件里
                File file=new File(imgFilePath);
                file.createNewFile();
                out = new FileOutputStream(file);
                out.write(decode);
                //关闭输出流
                out.flush();
                out.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                //关闭输出流
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
