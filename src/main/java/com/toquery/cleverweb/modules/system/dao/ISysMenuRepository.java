package com.toquery.cleverweb.modules.system.dao;

import com.toquery.cleverweb.modules.system.entity.SysMenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author toquery
 * @version 1
 */
@RepositoryRestResource(path="menus")
public interface ISysMenuRepository extends JpaRepository<SysMenuPO, String> {
}