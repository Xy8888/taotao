package com.taotao.manager.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.manager.model.Item;

public interface ItemMapper extends Mapper<Item>{

    int addItem(Item item);

    int modifyItem(Item item);

    Item getItemById(int id);

    int deleteById(int id);
}
