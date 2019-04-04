package com.taotao.manager.service;

import com.taotao.manager.model.ItemCat;

import java.util.List;

public interface ItemCatService extends BaseService<ItemCat> {

    /**
     *  根据父类id查询所有子类
     * @param id
     * @return
     */
    List<ItemCat> getItemCatByParentId(Long id);
}
