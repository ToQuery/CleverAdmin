package com.toquery.cleverweb.modules.system.handlers;

import com.toquery.cleverweb.modules.system.dao.SysMenuDao;
import com.toquery.cleverweb.modules.system.entity.SysMenu;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author toquery
 * @version 1
 */
@Service
public class SystemMenuHandler {

    @Resource
    private SysMenuDao sysMenuDao;


    public Mono<ServerResponse> getMenu(ServerRequest request) {
        int id = Integer.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<SysMenu> personMono = sysMenuDao.getPerson(id);
        return personMono
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person)))
                .switchIfEmpty(notFound);

    }
}
