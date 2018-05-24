package com.toquery.cleverweb.modules.system.controller;

import com.toquery.cleverweb.modules.system.entity.SysMenuPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author toquery
 * @version 1
 */
@RestController
public class SysMenuController {



    @GetMapping("/menus")
    public Flux<SysMenuPO> getAllTweets() {
        return null;
    }

    @PostMapping("/menus")
    public Mono<SysMenuPO> createTweets(@Valid @RequestBody SysMenuPO sysMenu) {
        return null;
    }


}
