package com.taotao.manager.service;

import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Content;

public interface ContentService extends BaseService<Content> {
    PageInfo<Content> getListByCategoryId(Long categoryId, Integer page, Integer rows);
}
