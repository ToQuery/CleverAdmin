package io.github.toquery.cleverweb.rest;

import io.github.toquery.cleverweb.entity.ExampleArticle;
import io.github.toquery.cleverweb.service.ExampleArticleService;
import io.github.toquery.framework.crud.controller.AppBaseCrudController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author toquery
 * @version 1
 */
@Slf4j
@RestController
@RequestMapping("/example/article")
public class ExampleArticleController extends AppBaseCrudController<ExampleArticleService, ExampleArticle, Long> {

    public ExampleArticleController() {
        log.info("ExampleArticleController");
    }

    @RequestMapping("/")
    public String test() {
        return "ok";
    }
}
