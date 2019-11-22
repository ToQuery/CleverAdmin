package io.github.toquery.cleverweb;

import io.github.toquery.framework.dao.EnableAppJpaRepositories;
import io.github.toquery.framework.data.rest.annotation.EnableAppRepositoryRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EnableAppRepositoryRest
// todo 可优化 @EntityScan
@EntityScan //(basePackages = {"io.github.toquery.cleverweb.entity"})
@EnableAppJpaRepositories // (basePackages = {"io.github.toquery.framework.security"})
public class CleverWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleverWebApplication.class, args);
    }

}

