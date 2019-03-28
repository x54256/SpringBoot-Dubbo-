package com.dist.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * api文档
 * @author yujx
 */
@Configuration
@EnableSwagger2
public class ApiDocConfig {

    @Value("${swagger_enable}")
    private Boolean enable;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("Dist API")
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dist.search.controller"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dist城市设计api文档")
                .description("API Document管理")
                .termsOfServiceUrl("NO terms of service")
                .version("1.0")
                .contact(new Contact("dist", "", "xxx@dist.com.cn"))
                .build();
    }
}