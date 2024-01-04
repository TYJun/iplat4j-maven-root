 package com.baosight.wilp.ac.jk.util;

 import org.apache.commons.codec.binary.Base64;

 import java.security.*;
 import java.util.HashMap;

 /**
  * Todo(这里用一句话描述这个方法的作用)
  *
  * @Title: RSAUtils
  * @ClassName: RSAUtils.java
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
 public class RSAUtils {
      private static final String KEY_ALGORITHM = "RSA";
      private static final int KEY_SIZE = 2048;// 设置长度


      /**
       * 生成公、私钥
       *
       * @return
       */
      public static HashMap<String,String> createRSAKeys() {
          HashMap<String,String> map = new HashMap<String,String>();
          try {
              KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
              keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
              KeyPair keyPair = keyPairGenerator.generateKeyPair();

              PublicKey publicKey = keyPair.getPublic();
              PrivateKey privateKey = keyPair.getPrivate();

              // 获取公、私钥值
              String publicKeyValue = Base64.encodeBase64String(publicKey.getEncoded());
              String privateKeyValue = Base64.encodeBase64String(privateKey.getEncoded());

              // 存入
              map.put("publicKey", publicKeyValue);
              map.put("privateKey", privateKeyValue);

              System.out.println(map);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return map;
      }


      public static void main(String[] args) {
          createRSAKeys();
      }
 }
