package io.github.toquery.cleverweb.rest;

import io.github.toquery.cleverweb.entity.ExampleArticle;
import io.github.toquery.cleverweb.service.ExampleArticleService;
import io.github.toquery.framework.curd.controller.AppBaseCurdController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author toquery
 * @version 1
 */

@RestController
@RequestMapping("/example/article")
public class ExampleArticleController extends AppBaseCurdController<ExampleArticleService, ExampleArticle, Long> {

}
