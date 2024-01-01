package com.baosight.wilp.util;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @PackageName com.baosight.wilp.util
 * @ClassName MedicalSignBase64Utils
 * @Description base64 工具类
 * @Author chunchen2
 * @Date 2023/3/1 13:45
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/3/1 13:45
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class MedicalSignBase64Utils {

    /**
     * @Title fileToBase64
     * @Author chunchen2
     * @Description // 文件转换成Base64
     * @Date 13:46 2023/3/1
     * @param file
     * @return java.lang.String
     * @throws
     **/
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = new String(Base64.encodeBase64(bytes),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    /**
     * @Title base64ToFile
     * @Author chunchen2
     * @Description // base64转换成文件
     * @Date 13:47 2023/3/1
     * @param outFilePath ： 输出的文件目录
     * @param base64
     * @param outFileName： 输出的文件名
     * @return void
     * @throws
     **/
    public static void base64ToFile(String outFilePath, String base64, String outFileName) {
        File file = null;
        //创建文件目录
        String filePath=outFilePath;
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64);
            file=new File(filePath + File.separator + outFileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String StringToBase64(String str) {
        return java.util.Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }
}
