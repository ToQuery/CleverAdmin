package com.toquery.cleverweb.modules.system.dao;

import com.toquery.cleverweb.modules.system.entity.SysMenu;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author toquery
 * @version 1
 */
@Repository
public class SysMenuDao {

    private Map<Integer,SysMenu> menuMap = new HashMap<>();

    @PostConstruct
    public void init(){
        menuMap.put(1,new SysMenu("1","1"));
        menuMap.put(2,new SysMenu("2","2"));
        menuMap.put(3,new SysMenu("3","3"));
        menuMap.put(4,new SysMenu("4","4"));
        menuMap.put(5,new SysMenu("5","5"));
        menuMap.put(6,new SysMenu("6","6"));
        menuMap.put(7,new SysMenu("7","7"));
    }

    public Flux<SysMenu> getMenus(){
        return Flux.fromIterable(menuMap.values());
    }



    public Mono<SysMenu> save(SysMenu sysMenu) {
        return null;
    }

    public Mono<SysMenu> getPerson(int id) {
        return  Mono.justOrEmpty(this.menuMap.get(id));
    }
}
