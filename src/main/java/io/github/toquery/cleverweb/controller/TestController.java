package io.github.toquery.cleverweb.controller;

import io.github.toquery.framework.webmvc.controller.AppBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author toquery
 * @version 1
 */

@RestController("/test")
public class TestController extends AppBaseController {

    @RequestMapping("/index")
    public String test(){
        request.getAttributeNames();
        return "ok";
    }
}
