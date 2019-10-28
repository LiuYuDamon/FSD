package com.damon.fsd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.SpringGroupingStrategy;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnablePluginRegistries(value = SpringGroupingStrategy.class)
public class SwaggerConfig {

    private final String basePackage = "com.jzm.api";

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage(basePackage )) // 当前包路径
                .paths(PathSelectors.any()) //
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder() //
                .title("Assignment6")// 标题
                .description("Assignment6") // 描述
                .contact(new Contact("Yu Liu", "http://localhost:8088/swagger-ui.html", "lyuliu@cn.ibm.com"))// 创建人
                .version("V1.0")// 版本号
                .build();
    }

}
