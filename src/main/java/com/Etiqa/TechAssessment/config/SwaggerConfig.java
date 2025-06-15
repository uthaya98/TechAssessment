package com.Etiqa.TechAssessment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
                .info(new Info()
                    .title("Customer & Product API")
                    .description("Spring Boot 3.3.x assessment with Jasper, Kafka, and Swagger")
                    .version("v1.0"));
    }

}
