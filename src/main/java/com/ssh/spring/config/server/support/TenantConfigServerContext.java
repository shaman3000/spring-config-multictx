package com.ssh.spring.config.server.support;

import com.ssh.spring.config.server.config.TenantProperties;
import com.ssh.spring.config.tenant.ConfigServerTenant;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class TenantConfigServerContext extends AnnotationConfigWebApplicationContext {

    public TenantConfigServerContext(TenantProperties tenantProperties) {
        register(ConfigServerTenant.class);
        setBeanName("config-server-context-" + tenantProperties.getTenantId());
        setDisplayName("Config Server - " + tenantProperties.getTenantId());
    }

}
