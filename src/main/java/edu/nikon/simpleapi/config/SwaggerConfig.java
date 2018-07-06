package edu.nikon.simpleapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ServletContext context;

    @Autowired
    public SwaggerConfig(ServletContext context) {
        this.context = context;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.none())
                .paths(PathSelectors.none())
                .build()
                .apiInfo(apiInfo())
                .pathProvider(new RelativePathProvider(context) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/api";
                    }
                });
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple REST API")
                .description("Simple REST API")
                .contact("Nikon Petrunin (https://github.com/nikon-petr/simple-api)")
                .version("1.0")
                .build();
    }
}
