package com.ssh.spring.config.server.support;

import com.ssh.spring.config.server.config.TenantProperties;
import com.ssh.spring.config.tenant.ConfigServerTenant;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.DefaultBootstrapContext;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Properties;

public class TenantConfigServerBuilder {

    private final TenantProperties properties;

    public TenantConfigServerBuilder(TenantProperties properties) {
        this.properties = properties;
    }

    public StandardEnvironment createEnvironment() {
        final Properties args = new Properties() {{
            put("spring.config.location", "classpath:/contexts/" + properties.getTenantId() + "/");
        }};
        final StandardEnvironment env = new StandardEnvironment() {{
            setActiveProfiles(properties.getActiveProfiles());
            getPropertySources().addFirst(new PropertiesPropertySource("startupArguments", args));
        }};
        return env;
    }

    public ConfigurableApplicationContext createTenant() {
        final String tenantId = properties.getTenantId();
        final StandardEnvironment env = createEnvironment();
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {{
            register(ConfigServerTenant.class);
            setEnvironment(env);
            setBeanName("config-server-" + tenantId);
            setDisplayName("Config Server [" + tenantId + "]");
        }};
        final DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        final ConfigurableBootstrapContext bootstrapContext = new DefaultBootstrapContext();
        ConfigDataEnvironmentPostProcessor.applyTo(env, resourceLoader, bootstrapContext, env.getActiveProfiles());
        return context;
    }

}
