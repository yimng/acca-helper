package com.thinkgem.jeesite.freetek.api.swagger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * api doc -- springfox swagger configuration
 */
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
	
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    
    @Value("${productName}")
	private String productName = "产品名称";

    @Value("${version}")
	private String version = "0.0.0";

    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN)) // and by paths
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }
    
    private ApiInfo apiInfo() {
        Contact contact = new Contact("look", "http://www.freetek.cc", "lukejun@freetek.cc");
        ApiInfo apiInfo = new ApiInfo(productName,//大标题
                "API文档",//小标题
                version,//版本
                "www.baidu.com",
                contact,//作者
                "",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }    
}
