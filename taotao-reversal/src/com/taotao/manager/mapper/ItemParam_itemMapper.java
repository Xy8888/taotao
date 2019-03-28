package com.taotao.manager.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemParam_itemMapper {
    long countByExample(ItemParam_itemExample example);

    int deleteByExample(ItemParam_itemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemParam_item record);

    int insertSelective(ItemParam_item record);

    List<ItemParam_item> selectByExampleWithBLOBs(ItemParam_itemExample example);

    List<ItemParam_item> selectByExample(ItemParam_itemExample example);

    ItemParam_item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemParam_item record, @Param("example") ItemParam_itemExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemParam_item record, @Param("example") ItemParam_itemExample example);

    int updateByExample(@Param("record") ItemParam_item record, @Param("example") ItemParam_itemExample example);

    int updateByPrimaryKeySelective(ItemParam_item record);

    int updateByPrimaryKeyWithBLOBs(ItemParam_item record);

    int updateByPrimaryKey(ItemParam_item record);
}