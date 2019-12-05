package io.github.toquery.cleverweb.dao;

import io.github.toquery.cleverweb.entity.ExampleArticle;
import io.github.toquery.framework.dao.repository.AppJpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleArticleDao extends AppJpaBaseRepository<ExampleArticle, Long> {
}
