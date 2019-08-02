package io.github.toquery.cleverweb;

import io.github.toquery.framework.dao.EnableAppJpaRepositories;
import io.github.toquery.framework.data.rest.annotation.EnableAppRepositoryRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAppRepositoryRest
//@EntityScan(basePackages = {"io.github.toquery.framework.security.domain"})
@EnableAppJpaRepositories // (basePackages = {"io.github.toquery.framework.security"})
public class CleverWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleverWebApplication.class, args);
    }

}

