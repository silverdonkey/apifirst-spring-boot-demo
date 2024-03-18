package de.nikoconsulting.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "de.nikoconsulting.demo.config.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("OpenAPI 3.0 Spring Boot 3 Demo")
                                .description("This is a sample Spring Boot 3 Server based on the OpenAPI 3.0 specification.\n" +
                                        "Some useful links: \n- [The Project repository](https://github.com/silverdonkey/apifirst-spring-boot-demo) \n- [The source API definition for the Demo](https://github.com/silverdonkey/apifirst-spring-boot-demo/blob/master/src/main/resources/service-api.yaml)")
                                .contact(
                                        new Contact()
                                                .email("orozov@web.de")
                                )
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                                )
                                .version("1.0.0")
                )
        ;
    }
}