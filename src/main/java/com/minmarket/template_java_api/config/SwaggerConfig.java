package com.minmarket.template_java_api.config;

import com.minmarket.template_java_api.TemplateJavaApiApplication;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile({"!prod"})
@SwaggerDefinition(tags = {
        @Tag(name = "Minimarket Template Service", description = "Api to template JAVA")})
public class SwaggerConfig {

    @Value("${swagger.api.tittle}")
    private String tittle;

    @Value("${swagger.api.description}")
    private String description;

    @Value("${swagger.api.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${swagger.api.version}")
    private String version;

    @Value("${swagger.contact.name}")
    private String nameContact;

    @Value("${swagger.contact.url}")
    private String urlContact;

    @Value("${swagger.contact.email}")
    private String emailContact;

    @Value("${swagger.licence.name}")
    private String licenceName;

    @Value("${swagger.licence.url}")
    private String licenceURL;

    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(tittle)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage(TemplateJavaApiApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(tittle)
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(nameContact,urlContact, emailContact))
                .license(licenceName)
                .licenseUrl(licenceURL)
                .extensions(Collections.emptyList())
                .build();
    }
}
