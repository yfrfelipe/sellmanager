package br.com.sellmanager.main;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    private static final Logger LOG = LogManager.getLogger(SwaggerConfig.class);

    @PostConstruct
    public void init() {
        LOG.info("Swagger Config initialized.");
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/*"))
                .apis(RequestHandlerSelectors.basePackage("br.com.sellmanager.control"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Sell Manager REST API")
                .description("\"Spring Boot REST API for Sell manager\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Yrineu Rodrigues", "https://providermanager.com.br/about/", "yfrfelipe@gmail.com"))
                .build();
    }

    @Override
    protected void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html");
        registry.addResourceHandler("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**");
        registry.addResourceHandler("classpath:/META-INF/resources/webjars/");
    }
}
