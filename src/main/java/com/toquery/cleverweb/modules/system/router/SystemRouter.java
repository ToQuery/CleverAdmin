package com.toquery.cleverweb.modules.system.router;

import com.toquery.cleverweb.modules.test.HelloWorldHandler;
import com.toquery.cleverweb.modules.system.handlers.SystemMenuHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;

/**
 * @author toquery
 * @version 1
 */
@Configuration
public class SystemRouter {
    @Resource
    private HelloWorldHandler helloWorldHandler;

    @Resource
    private SystemMenuHandler systemMenuHandler;

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld)
                .andRoute(RequestPredicates.GET("/menus/{id}"), systemMenuHandler::getMenu);
    }
}