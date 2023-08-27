package com.baosight.wilp.ss.bm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * @PackageName com.baosight.wilp.ss.bm.utils
 * @ClassName ABCPayGlobalConfig
 * @Description TODO
 * @Author rck
 * @Date 2021/6/10 10:54
 * @Version 1.0
 * @History 修改记录1
 * <author> rck
 * <time> 2021/6/10 10:54
 * <version>
 */
public class ABCPayGlobalConfig implements Serializable {

    static {
        try {
            Properties props = new Properties();
            InputStream in = AbcConfig.class.getClassLoader().getResourceAsStream("abcPayConfig.properties");
            props.load(in);
            AbcConfig abcConfig1 = new AbcConfig(props);
            ABCPayGlobalConfig.setAbcConfig(abcConfig1);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AbcConfig abcConfig;

    public static AbcConfig getAbcConfig() {
        return abcConfig;
    }

    public static void setAbcConfig(AbcConfig abcConfig) {
        ABCPayGlobalConfig.abcConfig = abcConfig;
    }
}
