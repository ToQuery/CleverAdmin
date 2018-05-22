package com.toquery.cleverweb.ldap;
import com.toquery.cleverweb.modules.ldap.dao.PersonRepository;
import com.toquery.cleverweb.modules.ldap.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author toquery
 * @version 1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapPersonServiceTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findAll() throws Exception {
        List<Person>  list = (List<Person>) personRepository.findAll();

        list .forEach(System.out::println);
    }
}