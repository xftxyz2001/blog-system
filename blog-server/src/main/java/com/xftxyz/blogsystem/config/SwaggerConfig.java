package com.xftxyz.blogsystem.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.google.common.base.Predicates;

// import javax.servlet.ServletContext;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
public class SwaggerConfig {
    // @Autowired
    // private ApplicationContext applicationContext;

    // private Contact contact = new Contact("nihao", "localhost:8080/swagger-ui.html", "jsxwysda@163.com");

    // @Bean
    // Docket docket() {
    //     @SuppressWarnings("unused")
    //     ServletContext servletContext = applicationContext.getBean(ServletContext.class);
    //     return new Docket(DocumentationType.SWAGGER_2)
    //             .select()
    //             .apis(RequestHandlerSelectors.any())
    //             .paths(Predicates.not(PathSelectors.regex("/error.*")))
    //             .build()
    //             .apiInfo(apiInfo());
    // }

    // private ApiInfo apiInfo() {
    //     return new ApiInfoBuilder()
    //             .title("可心的swagger文档")
    //             .description("我想认真读一本书")
    //             .version("v3.0")
    //             .termsOfServiceUrl("https://blog.csdn.net/weixin_58993861?type=blog")
    //             .contact(contact)
    //             .build();
    // }
}