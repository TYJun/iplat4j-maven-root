//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bonawise.smp.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class MealGlobalConfig implements Serializable {
    private static final long serialVersionUID = 7733998032337892011L;
    private static SmpConfig smpConfig;

    static {
        try {
            Properties properties = new Properties();
            ClassLoader loader = MealGlobalConfig.class.getClassLoader();
            InputStream in = loader.getResourceAsStream("application.properties");
            properties.load(in);
            SmpConfig config = new SmpConfig(properties);
            setSmpConfig(config);
        } catch (Exception var4) {
            System.out.println("装配application.properties配置文件出错！" + var4.getMessage());
            var4.printStackTrace();
        }

    }

    public MealGlobalConfig() {
    }

    public static SmpConfig getSmpConfig() {
        return smpConfig;
    }

    public static void setSmpConfig(SmpConfig smpConfig) {
        MealGlobalConfig.smpConfig = smpConfig;
    }

    public static void main(String[] args) throws IOException {
    }
}
