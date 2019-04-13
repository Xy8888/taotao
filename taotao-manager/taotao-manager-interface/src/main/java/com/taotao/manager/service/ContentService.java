package com.taotao.manager.service;

import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Content;

public interface ContentService extends BaseService<Content> {
    PageInfo<Content> getListByCategoryId(Long categoryId, Integer page, Integer rows);

    String getBigAd(long index_big_ad_cid) throws Exception;

    int addContent(Content content);

    int updateContent(Content content);
}
