package com.taotao.manager.service;

import com.taotao.manager.model.Item;

import java.util.List;

public interface ItemService extends BaseService<Item>{

    int add(Item item, String desc) throws Exception;

    int deleteByIds(List<Object> ids) throws Exception;

    int updateItem(Item item, String desc) throws Exception;
}
