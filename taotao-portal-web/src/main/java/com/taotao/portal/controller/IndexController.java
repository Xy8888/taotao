package com.taotao.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.model.Content;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Reference(interfaceClass = ContentService.class)
    private ContentService contentService;

    @Value("${INDEX_BIG_AD_CID}")
    private long INDEX_BIG_AD_CID;

    /**
     *  首页跳转以及大广告位展示
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/index")
    public String index(Model model) throws Exception {
        String json = contentService.getBigAd(INDEX_BIG_AD_CID);
        model.addAttribute("ad1", json);
        return "index";
    }
}
