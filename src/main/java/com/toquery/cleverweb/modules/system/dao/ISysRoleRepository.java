package com.toquery.cleverweb.modules.system.dao;

import com.toquery.cleverweb.modules.system.entity.SysRolePO;
import com.toquery.cleverweb.modules.system.entity.SysUserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author toquery
 * @version 1
 */
@RepositoryRestResource(path="roles")
public interface ISysRoleRepository extends JpaRepository<SysRolePO, String> {
}