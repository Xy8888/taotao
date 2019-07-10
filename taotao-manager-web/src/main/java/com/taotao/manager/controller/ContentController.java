package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Content;
import com.taotao.manager.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容管理
 * @author Cgy
 * 2019-04-12
 */
@Controller
public class ContentController {

    @Reference(interfaceClass = ContentService.class)
    private ContentService contentService;

    /**
     *  根据categoryId查询
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/content")
    public Map<String, Object> getContentList(
            @RequestParam(value = "categoryId", required = false, defaultValue = "0")Long categoryId,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "20")Integer rows){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        PageInfo<Content> pageInfo = contentService.getListByCategoryId(categoryId, page, rows);
        dataMap.put("total", pageInfo.getTotal());
        dataMap.put("rows", pageInfo.getList());
        return dataMap;
    }

    /**
     * 新增内容
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/content")
    public String add(Content content) {
        int acount = contentService.addContent(content);
        if (acount > 0) {
            return "success";
        }
        return "fail";
    }

    /**
     *  修改内容
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/content/edit")
    public Map<String, Object> update(Content content) {
        int mcount = contentService.updateContent(content);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (mcount > 0) {
            dataMap.put("status", 200);
        } else {
            dataMap.put("status", 500);
        }
        return dataMap;
    }

    /**
     * 根据选中的id集合删除内容
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/content/delete")
    public Map<String, Object> deleteByIds(
            @RequestParam(value = "ids", required = false, defaultValue = "null")List<Object> ids) throws Exception {
        int dcount = contentService.deleteByIds(ids);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (dcount > 0) {
            dataMap.put("status", 200);
        } else {
            dataMap.put("status", 500);
        }
        return dataMap;
    }
}
