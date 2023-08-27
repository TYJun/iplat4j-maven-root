package com.baosight.wilp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @PackageName com.baosight.wilp.config
 * @ClassName EntityConfig
 * @Description TODO
 * @Author chunchen2
 * @Date 2023/2/22 16:48
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 16:48
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Configuration
public class EntityConfig {

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(5 * 60 * 1000);
        httpRequestFactory.setConnectTimeout(60 *1000);
        httpRequestFactory.setReadTimeout(5 * 60 * 1000);
        return new RestTemplate(httpRequestFactory);

    }

}
