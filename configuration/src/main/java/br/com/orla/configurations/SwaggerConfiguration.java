package br.com.orla.configurations;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    static final String API_BASE_PACKAGE = "br.com.orla.api.interfaces";

    @Autowired
    private SwaggerApiDocs swaggerApiDocs;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(getApiBasePackage())
                .paths(getApiPaths())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));

    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(getApiPaths())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = {authorizationScope};
        final SecurityReference securityReference = new SecurityReference("Authorization", authorizationScopes);

        return Collections.singletonList(securityReference);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                swaggerApiDocs.getTitles(),
                swaggerApiDocs.getDescription(),
                "",
                swaggerApiDocs.getTermsOfServiceUrl(),
                new Contact(swaggerApiDocs.getContactName(),
                        swaggerApiDocs.getContactUrl(),
                        swaggerApiDocs.getContactEmail()),
                swaggerApiDocs.getLicense(),
                swaggerApiDocs.getLicenseUrl(),
                Collections.emptyList());
    }

    private Predicate<String> getApiPaths() {
        final String apiV1Path = getApiV1Path();
        return PathSelectors.ant(apiV1Path);
    }

    private String getApiV1Path() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append("/**");

        return builder.toString();
    }

    private Predicate<RequestHandler> getApiBasePackage() {
        return RequestHandlerSelectors.basePackage(API_BASE_PACKAGE);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
