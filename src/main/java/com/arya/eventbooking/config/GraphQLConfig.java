package com.arya.eventbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class GraphQLConfig {

    @Bean
    public WebGraphQlInterceptor securityContextInterceptor() {
        return (request, chain) -> {
            var authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            request.configureExecutionInput((exec, builder) ->
                    builder.graphQLContext(ctx ->
                            ctx.put("auth", authentication)
                    ).build()
            );

            return chain.next(request);
        };
    }
}
