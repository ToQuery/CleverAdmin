package com.toquery.cleverweb.core.web.router;

import com.toquery.cleverweb.modules.test.handlers.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import javax.annotation.Resource;

/**
 * @author toquery
 * @version 1
 */
@Configuration
public class CleverWebReactorRouter {
    @Resource
    private HelloWorldHandler helloWorldHandler;
    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld);
    }
}