package com.ssh.spring.config.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ConfigServerProperties.TENANTS)
public class ConfigServerProperties {

    public static final String TENANTS = "tenants";

    private TenantProperties commonContext;

    private TenantProperties flinkContext;

    public TenantProperties getCommonContext() {
        return commonContext;
    }

    public void setCommonContext(TenantProperties commonContext) {
        this.commonContext = commonContext;
    }

    public TenantProperties getFlinkContext() {
        return flinkContext;
    }

    public void setFlinkContext(TenantProperties flinkContext) {
        this.flinkContext = flinkContext;
    }

}
