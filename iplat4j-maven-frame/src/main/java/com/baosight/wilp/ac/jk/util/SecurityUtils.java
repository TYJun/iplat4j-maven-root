package com.baosight.wilp.ac.jk.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/**
 * Todo(这里用一句话描述这个方法的作用)
 *
 * @Title: SecurityUtils
 * @ClassName: SecurityUtils.java
 * @Package：com.baosight.wilp.ss.bm.utils
 * @author: xiajunqing
 * @date: 2021/11/20 13:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public final class SecurityUtils {
    private static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String RSA_TYPE = "RSA/ECB/PKCS1Padding";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * PUBLIC_KEY公钥
     */
    private static String PUBLIC_KEY = "";
    /***
     * PRIVATE_KEY私钥
     */
    private static String PRIVATE_KEY = "";

    static {
        try {
            //读取配置文件
            Properties properties = new Properties();
            ClassLoader loader = SecurityUtils.class.getClassLoader();
            InputStream in = loader.getResourceAsStream("application.properties");
            properties.load(in);
            //加载公钥和私钥
            setPublicKey(properties.getProperty("public_key"));
            setPrivateKey(properties.getProperty("private_key"));
        } catch (IOException e) {
            System.out.println("装配application.properties配置文件出错！"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getPublicKey() {
        return PUBLIC_KEY;
    }

    public static void setPublicKey(String publicKey) {
        PUBLIC_KEY = publicKey;
    }

    public static String getPrivateKey() {
        return PRIVATE_KEY;
    }

    public static void setPrivateKey(String privateKey) {
        PRIVATE_KEY = privateKey;
    }

    /**
     * TODO(获取RSA公钥 根据钥匙字段)
     *
     * @Title: getPublicKey
     * @param key
     * @return
     * @return: PublicKey
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.decodeBase64(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO(获取RSA私钥 根据钥匙字段)
     *
     * @Title: getPrivateKey
     * @param key
     * @return
     * @return: PrivateKey
     */
    private static PrivateKey getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.decodeBase64(key);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO(私钥 签名)
     *
     * @Title: sign
     * @param requestData加密的字符串
     * @return
     * @return: String
     */
    public static String sign(String requestData) {
        String signature = null;
        byte[] signed = null;
        try {
            PrivateKey privateKey = getPrivateKey(PRIVATE_KEY);
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(requestData.getBytes());
            signed = Sign.sign();
            signature = Base64.encodeBase64String(signed);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * TODO(公钥验证签名 )
     *
     * @Title: verifySign
     * @param data私钥加密的字符串
     * @param sign私钥签名的加密字符串
     * @return
     * @return: boolean
     */
    public static boolean verify(String key,String data, String sign) {
        boolean verifySignSuccess = false;
        try {
            PublicKey publicKey = getPublicKey(key);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data.getBytes());

            verifySignSuccess = signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verifySignSuccess;
    }

    /**
     * TODO(加密)
     *
     * @Title: encrypt
     * @param clearText需要加密的字符串
     * @return
     * @return: String
     */
    public static String encrypt(String clearText) {
        String encryptedBase64 = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Key key = getPrivateKey(PRIVATE_KEY);
            Cipher cipher = Cipher.getInstance(RSA_TYPE);

            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
//            encryptedBase64 = Base64.encodeBase64String(encryptedBytes);
            byte[] data = clearText.getBytes("UTF-8");
            int inputLen = data.length;
            int offSet = 0;
            byte[] cache;
            int i = 0;
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            encryptedBase64 = Base64.encodeBase64String(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("加密失败!"+e.toString());
        }finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
        }
        return encryptedBase64;
    }

    /**
     * TODO(公钥解密)
     *
     * @Title: decrypt
     * @param encryptedBase64加密后的字符串
     * @return
     * @return: String
     */
    public static String decrypt(String key,String encryptedBase64) {
        String decryptedString = "";
        try {
            PublicKey publicKey = getPublicKey(key);
            final Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] encryptedBytes = Base64.decodeBase64(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败!"+e.toString());
        }
        return decryptedString;
    }

    public static void main(String[] args) {
        SecurityUtils.test();
    }

    public static void test() {
        String publicKey = SecurityUtils.getPublicKey();
        String privateKey = SecurityUtils.getPrivateKey();
        System.err.println("公钥：" + publicKey);
        System.err.println("私钥：" + privateKey);

        long startTime = System.currentTimeMillis();
        //String encrypt = SecurityUtils.encrypt("45278.61");
        String encrypt = SecurityUtils.encrypt("这是一段RAS非对称加密解密代码");
        long endTime = System.currentTimeMillis();
        System.out.println("加密后：" + encrypt);
        System.out.println("加密need " + (endTime - startTime) + "ms");

        String sign = SecurityUtils.sign(encrypt);
        System.out.println("签名：" + sign);

        long startTime1 = System.currentTimeMillis();
        String decrypt = SecurityUtils.decrypt(publicKey , encrypt);
        long endTime1 = System.currentTimeMillis();
        System.out.println("解密后：" + decrypt);
        System.out.println("解密need " + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        boolean verify = SecurityUtils.verify(publicKey , encrypt ,sign);
        long endTime2 = System.currentTimeMillis();
        System.out.println("验签：" + verify);
        System.out.println("验签need " + (endTime2 - startTime2) + "ms");
    }
}
