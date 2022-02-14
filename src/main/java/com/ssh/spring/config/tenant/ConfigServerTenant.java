package com.ssh.spring.config.tenant;

import org.springframework.boot.actuate.autoconfigure.endpoint.jmx.JmxEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableConfigServer
@EnableAutoConfiguration(exclude = JmxEndpointAutoConfiguration.class)
public class ConfigServerTenant {

    @Bean
    public RefreshScope refreshScope(){
        return new RefreshScope();
    }

}
