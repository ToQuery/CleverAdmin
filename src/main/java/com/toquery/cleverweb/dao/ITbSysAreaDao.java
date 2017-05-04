package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author toquery
 * @version 1
 */
@RepositoryRestResource(collectionResourceRel = "area", path = "area")
public interface ITbSysAreaDao extends JpaRepository<TbSysArea, String> {

}
