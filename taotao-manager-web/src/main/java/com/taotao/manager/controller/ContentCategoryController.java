package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.manager.model.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/content")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父级id查询
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/category")
    public List<ContentCategory> queryContentCategoryByParentId(@RequestParam(value = "id", required = true, defaultValue = "0") Long parentId) {
        List<ContentCategory> list = contentCategoryService.getListByParentId(parentId);
        return list;
    }

    /**
     * 新增广告位
     * @param contentCategory
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/category/add")
    public ContentCategory add(ContentCategory contentCategory) {
        contentCategory = contentCategoryService.add(contentCategory);
        return contentCategory;
    }

    /**
     * 更新
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/category/update")
    public String update(ContentCategory contentCategory) {
        int mcount = contentCategoryService.updateContentCategory(contentCategory);
        return "success";
    }

    /**
     *  删除
     * @param contentCategory
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/category/delete")
    public String delete(ContentCategory contentCategory) {
        int dcount = contentCategoryService.deleteContentCategory(contentCategory);
        return "success";
    }
}
