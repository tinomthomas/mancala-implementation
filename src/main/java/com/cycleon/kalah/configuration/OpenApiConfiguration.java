package com.cycleon.kalah.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public RouterFunction<ServerResponse> indexRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/api/"),
            req -> ServerResponse.temporaryRedirect(URI.create("/api")).build());
    }

    @Bean
    public OpenAPI customOpenApi(
        @Value("${APPLICATION_VERSION}") String appVersion,
        @Value("${API_DOC_APPLICATION_TITLE}") String appTitle,
        @Value("${API_DOC_APPLICATION_DESCRIPTION}") String appDescription
    ) {
        return new OpenAPI().info(new Info().title(appTitle).description(appDescription).version(appVersion));
    }
}
