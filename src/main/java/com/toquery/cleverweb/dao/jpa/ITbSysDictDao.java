package com.toquery.cleverweb.dao.jpa;

import com.toquery.cleverweb.entity.po.TbSysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author toquery
 * @version 1
 */
@RepositoryRestResource(collectionResourceRel = "list", path = "dict")
public interface ITbSysDictDao extends JpaRepository<TbSysDict, String> {

}
