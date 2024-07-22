package com.nv.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> departmentServiceRoute() {
        return GatewayRouterFunctions.route("department_service")
                .route(RequestPredicates.path("/v1/api/departments/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> employeeServiceRoute() {
        return GatewayRouterFunctions.route("employee_service")
                .route(RequestPredicates.path("/v1/api/employees/**"), HandlerFunctions.http("http://localhost:8082"))
                .route(RequestPredicates.path("/v1/api/kafka-producers/**"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }
}
