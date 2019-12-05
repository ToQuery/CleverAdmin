package io.github.toquery.cleverweb.service.impl;

import io.github.toquery.cleverweb.dao.ExampleArticleDao;
import io.github.toquery.cleverweb.entity.ExampleArticle;
import io.github.toquery.cleverweb.service.ExampleArticleService;
import io.github.toquery.framework.curd.service.impl.AppBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExampleArticleServiceImpl extends AppBaseServiceImpl<Long, ExampleArticle, ExampleArticleDao> implements ExampleArticleService {
    @Override
    public Map<String, String> getQueryExpressions() {
        return null;
    }
}
