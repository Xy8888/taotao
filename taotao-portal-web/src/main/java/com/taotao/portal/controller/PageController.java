package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    /**
     *  通用页面跳转方法
     * @param pageName
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{pageName}")
    public String page(@PathVariable(value = "pageName")String pageName) {
        return pageName;
    }
}
