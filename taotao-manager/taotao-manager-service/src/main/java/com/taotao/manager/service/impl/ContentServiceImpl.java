package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.ContentMapper;
import com.taotao.manager.model.Content;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = ContentService.class)
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public PageInfo<Content> getListByCategoryId(Long categoryId, Integer page, Integer rows) {
        Content content = new Content();
        content.setCategoryId(categoryId);
        PageHelper.startPage(page, rows);
        List<Content> list = contentMapper.select(content);
        return new PageInfo<Content>(list);
    }
}
