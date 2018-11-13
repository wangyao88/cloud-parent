package com.sxkl.project.cloudgateway.user.controller;

import com.sxkl.project.cloudgateway.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Autowired
    private UserService userService;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(POST("/user/registe"), userService::registe)
                .andRoute(POST("/user/update"), userService::update)
                .andRoute(POST("/user/delete"), userService::delete)
                .andRoute(GET("/user/fingdOne"), userService::fingdOne)
                .andRoute(GET("/user/findAll"), userService::findAll)
                .andRoute(GET("/user/findPage"), userService::findPage);
    }
}
