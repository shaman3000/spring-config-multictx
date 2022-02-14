package com.ssh.spring.config.server;

import com.ssh.spring.config.server.config.ConfigServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.server.config.ConfigServerAutoConfiguration;

@SpringBootApplication(exclude = ConfigServerAutoConfiguration.class)
@EnableConfigurationProperties(ConfigServerProperties.class)
public class ConfigServer {

	public static final String CONFIG_SERVER = "config-server";

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServer.class)
				.properties("spring.config.name=" + CONFIG_SERVER,
							"spring.application.name=" + CONFIG_SERVER)
				.run(args);
	}

}
