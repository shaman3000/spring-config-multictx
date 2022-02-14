package com.ssh.spring.config.server.config;

import com.ssh.spring.config.server.support.TenantConfigServerBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.ConfigurableApplicationContext;
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
/*
        Properties args = new Properties() {{
            put("spring.config.name", "application-" + tenantProperties.getTenantId());
            put("spring.config.location", "classpath:/config-server/");
        }};
        TenantConfigServerEnvironment env = new TenantConfigServerEnvironment(args);
        env.setActiveProfiles(tenantProperties.getActiveProfiles());
        TenantConfigServerContext context = new TenantConfigServerContext(tenantProperties);

        context.setEnvironment(env);

        //
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        ConfigurableBootstrapContext bootstrapContext = new DefaultBootstrapContext();
        ConfigDataEnvironmentPostProcessor.applyTo(environment, resourceLoader, bootstrapContext, activeProfiles);
        System.out.println( env.getProperty("spring.cloud.config.server.native.searchLocations") );
*/

        ConfigurableApplicationContext context = new TenantConfigServerBuilder(tenantProperties).createTenant();
        DispatcherServlet servlet = new DispatcherServlet((WebApplicationContext) context);
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>();
        registration.addUrlMappings("/" + tenantProperties.getTenantId() + "/*");
        registration.setServlet(servlet);
        registration.setLoadOnStartup(1);
        registration.setName("dispatcher-servlet-" + tenantProperties.getTenantId());
        return registration;
    }


}
