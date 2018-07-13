package edu.nikon.simpleapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple REST API")
                .description("Simple REST API")
                .contact(new Contact(
                        "Nikon Petrunin",
                        "https://github.com/nikon-petr/simple-api",
                        "p.nikwv@gmail.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket organizationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("organization")
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.nikon.simpleapi.api.organization"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket officeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("office")
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.nikon.simpleapi.api.office"))
                .build();
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.nikon.simpleapi.api.user"))
                .build();
    }

    @Bean
    public Docket catalogApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("catalog")
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.nikon.simpleapi.api.catalog"))
                .build();
    }
}
