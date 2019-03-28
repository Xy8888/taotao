package com.taotao.manager.service;

import com.taotao.manager.model.Item;

public interface ItemService extends BaseService<Item>{

    /***
     * 增加商品信息
     * @param item
     * @return
     * @throws Exception
     */
    int addItem(Item item) throws  Exception;

}
