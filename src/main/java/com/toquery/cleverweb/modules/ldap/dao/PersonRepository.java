package com.toquery.cleverweb.modules.ldap.dao;

import com.toquery.cleverweb.modules.ldap.entity.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

/**
 * @author toquery
 * @version 1
 */
public interface PersonRepository extends CrudRepository<Person, Name> {

}