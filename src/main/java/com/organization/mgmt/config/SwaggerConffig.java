package com.organization.mgmt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConffig {
    @Bean
    public OpenAPI muCustCOnfig() {
        return new OpenAPI().info(
                        new Info()
                                .title("Organization management Api")
                                .description("by CRB")
                )
                .servers(List.of(new Server().url("http://localhost:8080").description("Main server"),
                        new Server().url("http://localhost:9090").description("Custom server")))
                ;
    }
}
