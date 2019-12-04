package io.github.toquery.cleverweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//(scanBasePackages = {"io.github.toquery.framework.system", "io.github.toquery.framework.security"})
//@EnableAppRepositoryRest
//@EntityScan(basePackages = {"io.github.toquery.framework.security.domain"})
//@EnableAppJpaRepositories(basePackages = {"io.github.toquery.framework.system", "io.github.toquery.framework.security"})
public class CleverWebApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(CleverWebApplication.class)
      //.beanNameGenerator(new PackageBeanNameGenerator())
      .run(args);
    // SpringApplication.run(CleverWebApplication.class, args);
  }
}

