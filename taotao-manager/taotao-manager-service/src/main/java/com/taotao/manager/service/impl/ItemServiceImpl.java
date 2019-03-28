package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

@Component
@Service(interfaceClass = ItemService.class)
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public int addItem(Item item) throws Exception {
        return itemMapper.addItem(item);
    }

}
