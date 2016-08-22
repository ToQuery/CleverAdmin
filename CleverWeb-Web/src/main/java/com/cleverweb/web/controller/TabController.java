package com.cleverweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ToQuery on 2016-08-21.
 */
@Controller
public class TabController {
    /**
     * 进入tab标签
     *
     * @return
     */
    @RequestMapping(value = "/tab")
    public String tab() {
        return "system/index/tab";
    }
}
