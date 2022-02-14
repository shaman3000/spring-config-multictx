package com.ssh.spring.config.server.support;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Properties;

public class TenantConfigServerEnvironment extends StandardEnvironment {

    public TenantConfigServerEnvironment(Properties properties) {
        super(createPropertySources(properties));
    }

    protected static MutablePropertySources createPropertySources(Properties properties) {
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(new PropertiesPropertySource("startupArguments", properties));
        return propertySources;
    }

}
