package com.ssh.spring.config.server.config;

import com.ssh.spring.config.server.support.TenantConfigServerBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableConfigurationProperties(ConfigServerProperties.class)
public class ConfigServerConfiguration {

    private final ConfigServerProperties properties;

    public ConfigServerConfiguration(ConfigServerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> commonRegistration() {
        return createTenantContext(properties.getCommonContext());
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> flinkRegistration() {
        return createTenantContext(properties.getFlinkContext());
    }

    protected ServletRegistrationBean<DispatcherServlet> createTenantContext(TenantProperties tenantProperties) {
        WebApplicationContext context = new TenantConfigServerBuilder(tenantProperties).createTenant();
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>();
        registration.addUrlMappings("/" + tenantProperties.getTenantId() + "/*");
        registration.setServlet(servlet);
        registration.setLoadOnStartup(1);
        registration.setName("dispatcher-servlet-" + tenantProperties.getTenantId());
        return registration;
    }

}
