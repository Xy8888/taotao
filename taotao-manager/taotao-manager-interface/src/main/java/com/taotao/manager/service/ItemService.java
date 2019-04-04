package com.taotao.manager.service;

import com.taotao.manager.model.Item;

public interface ItemService extends BaseService<Item>{

    int add(Item item, String desc);
}
