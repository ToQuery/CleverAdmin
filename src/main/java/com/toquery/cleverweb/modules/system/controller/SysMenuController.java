package com.toquery.cleverweb.modules.system.controller;

import com.toquery.cleverweb.modules.system.dao.SysMenuDao;
import com.toquery.cleverweb.modules.system.entity.SysMenu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;


/**
 * @author toquery
 * @version 1
 */
@RestController
public class SysMenuController {

    @Resource
    private SysMenuDao sysMenuDao;

    @GetMapping("/menus")
    public Flux<SysMenu> getAllTweets() {
        return sysMenuDao.getMenus();
    }

    @PostMapping("/menus")
    public Mono<SysMenu> createTweets(@Valid @RequestBody SysMenu sysMenu) {
        return sysMenuDao.save(sysMenu);
    }


}
