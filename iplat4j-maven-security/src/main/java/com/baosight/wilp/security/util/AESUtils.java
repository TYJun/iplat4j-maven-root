package com.baosight.wilp.security.util;

import com.baosight.wilp.utils.UUID;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

/**
 * @PackageName com.baosight.wilp.security.util
 * @ClassName AESUtils
 * @Description AES加密工具类
 * @Author chunchen2
 * @Date 2023/2/20 17:34
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/20 17:34
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class AESUtils {

    private static final String AESKEY = "$$BONA$20221228$AESKEY";

    public static String newSessionAESKey() {
        String tKey = UUID.fastUUID().toString(true).substring(0, 16);

        getRequest().getSession().setAttribute(AESKEY, tKey);
        return tKey;
    }

    public static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        return request;
    }

    public static String getSessionAESKey() {

        Object pKey= getRequest().getSession().getAttribute(AESKEY);
        if(pKey==null) {
            return newSessionAESKey();
        }

        return pKey.toString();
    }

    public static String decodeSessionAESData(String tData) {
        try
        {
            KeyGenerator pKeyGenerator = KeyGenerator.getInstance("AES");
            pKeyGenerator.init(256);
            Cipher pCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            pCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getSessionAESKey().getBytes(), "AES"));
            byte[] bpBuffer = getHexBytes(tData);
            bpBuffer = pCipher.doFinal(bpBuffer);
            tData = new String(bpBuffer,"utf-8");
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        return tData;
    }

    public static byte[] getHexBytes(String tHexString) {
        byte[] bpBuffer = new byte[tHexString.length()/2];
        for(int iCyc=0;iCyc<tHexString.length();iCyc++)
        {
            byte bValue = 0;
            switch(tHexString.charAt(iCyc))
            {
                case '0':
                    bValue=0x00;
                    break;
                case '1':
                    bValue=0x01;
                    break;
                case '2':
                    bValue=0x02;
                    break;
                case '3':
                    bValue=0x03;
                    break;
                case '4':
                    bValue=0x04;
                    break;
                case '5':
                    bValue=0x05;
                    break;
                case '6':
                    bValue=0x06;
                    break;
                case '7':
                    bValue=0x07;
                    break;
                case '8':
                    bValue=0x08;
                    break;
                case '9':
                    bValue=0x09;
                    break;
                case 'a':
                    bValue=0x0a;
                    break;
                case 'b':
                    bValue=0x0b;
                    break;
                case 'c':
                    bValue=0x0c;
                    break;
                case 'd':
                    bValue=0x0d;
                    break;
                case 'e':
                    bValue=0x0e;
                    break;
                case 'f':
                    bValue=0x0f;
                    break;
            }
            if(iCyc%2==0)
            {
                bpBuffer[iCyc/2]=0;
                bpBuffer[iCyc/2]=(byte) (bValue<<4);
            }
            else
            {
                bpBuffer[iCyc/2]=(byte) (bpBuffer[iCyc/2]|bValue);
            }
        }
        return bpBuffer;
    }
}
