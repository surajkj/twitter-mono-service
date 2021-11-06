package com.twitter.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("v1") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentation for twitter APIs")
                        .version(appVersion)
                        .description("All APIs are documented here for twitter. You can try out the APIs from here ")
                        .termsOfService("https://surajkj.com")
                        .license(new License().name("Private").url("https://surajkj.com")));
    }

}
