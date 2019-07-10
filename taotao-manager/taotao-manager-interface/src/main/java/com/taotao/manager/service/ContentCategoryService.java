package com.taotao.manager.service;

import com.taotao.manager.model.ContentCategory;

import java.util.List;

public interface ContentCategoryService extends BaseService<ContentCategory> {
    List<ContentCategory> getListByParentId(Long parentId);

    ContentCategory add(ContentCategory contentCategory);

    int updateContentCategory(ContentCategory contentCategory);

    int deleteContentCategory(ContentCategory contentCategory) throws Exception;
}
