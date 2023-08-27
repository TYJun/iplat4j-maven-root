package com.baosight.wilp.mc.jk.config;

import com.baosight.wilp.mc.jk.service.impl.DeptServiceImpl;
import com.baosight.wilp.mc.jk.service.impl.PersonnelServiceImpl;
import org.apache.cxf.phase.Phase;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class cxfConfig {
    @Bean
    public ServletRegistrationBean disServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/webService/*");
        return servletRegistrationBean;
    }
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new PersonnelServiceImpl());
//        endpoint.getInInterceptors().add(new WsInInterceptor(Phase.RECEIVE));
        endpoint.publish("/PersonnelService");
        return endpoint;
    }

    @Bean
    public Endpoint endpoint1() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new DeptServiceImpl());
        endpoint.publish("/DeptService");
        return endpoint;
    }
}
