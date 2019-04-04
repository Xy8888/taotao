package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.manager.mapper.ItemDescMapper;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.model.Item;
import com.taotao.manager.model.ItemDesc;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
//import org.springframework.stereotype.Service;

@Component
@Service(interfaceClass = ItemService.class)
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public int add(Item item, String desc) {
        // 补全数据
        long id = System.currentTimeMillis() + (long) (Math.random() * 100000);
        item.setId(id);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        int acount = itemMapper.insertSelective(item);

        // 增加详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        acount = itemDescMapper.insertSelective(itemDesc);
        return acount;
    }
}
