package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.model.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = ItemCatService.class)
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> getItemCatByParentId(Long id) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(id);
        return itemCatMapper.select(itemCat);
    }
}
