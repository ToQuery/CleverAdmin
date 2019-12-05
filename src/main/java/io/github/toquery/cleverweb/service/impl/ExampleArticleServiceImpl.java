package io.github.toquery.cleverweb.service.impl;

import io.github.toquery.cleverweb.dao.ExampleArticleDao;
import io.github.toquery.cleverweb.entity.ExampleArticle;
import io.github.toquery.cleverweb.service.ExampleArticleService;
import io.github.toquery.framework.crud.service.impl.AppBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ExampleArticleServiceImpl extends AppBaseServiceImpl<Long, ExampleArticle, ExampleArticleDao> implements ExampleArticleService {

    public ExampleArticleServiceImpl() {
        log.info("ExampleArticleServiceImpl");
    }

    @Override
    public Map<String, String> getQueryExpressions() {
        return null;
    }
}
