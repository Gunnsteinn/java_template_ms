package com.template.app.commons.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Autowired(required = false)
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI openAPI() {
        String title = (buildProperties != null) ? String.format("%s.%s", buildProperties.getGroup(), buildProperties.getName()) : "Default Title";
        String version = (buildProperties != null) ? buildProperties.getVersion() : "1.0.0";
        String description = (buildProperties != null) ? buildProperties.get("description") : "Default Description";

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description));
    }
}
