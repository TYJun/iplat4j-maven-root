package com.baosight;

import com.baosight.iplat4j.config.ApplicationProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.baosight")
@ServletComponentScan("com.baosight.iplat4j.core.web.servlet")
@ImportResource(locations = {"classpath*:spring/framework/platApplicationContext*.xml", "classpath*:spring/framework/applicationContext*.xml"})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
@EnableScheduling
@EnableAsync
public class ApplicationBoot extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationBoot.class);
    }

}