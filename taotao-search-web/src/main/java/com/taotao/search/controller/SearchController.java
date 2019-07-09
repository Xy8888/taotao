package com.taotao.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.page.Page;
import com.taotao.manager.model.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class SearchController {

    @Reference(interfaceClass = ItemService.class)
    private ItemService itemService;

    @GetMapping(value = {"/search", "/index"})
    public String search(@RequestParam(value="q",required=false,defaultValue="")String q,
                         @RequestParam(value="page",required=false,defaultValue="1")Integer page,Model model) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, SolrServerException {
            Page<Item> pageInfo = itemService.query(q, page);
            model.addAttribute("query", q);
            model.addAttribute("totalPages", pageInfo.getTotal());
            model.addAttribute("page", pageInfo);
        return "search";
    }

}
