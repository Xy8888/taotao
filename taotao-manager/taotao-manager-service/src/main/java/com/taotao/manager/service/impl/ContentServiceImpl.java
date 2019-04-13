package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.Constant;
import com.taotao.manager.mapper.ContentMapper;
import com.taotao.manager.model.Content;
import com.taotao.manager.service.ContentService;
import com.taotao.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Service(interfaceClass = ContentService.class)
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *  新增，并删除缓存
     * @param content
     * @return
     */
    @Override
    public int addContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        int acount = contentMapper.insertSelective(content);
        if (acount > 0) {
            delCache(content.getCategoryId());
        }
        return acount;
    }

    /**
     *  更新，并删除缓存
     * @param content
     * @return
     */
    @Override
    public int updateContent(Content content) {
        content.setUpdated(new Date());
        int mcount = super.updateSelective(content);
        if (mcount > 0) {
            delCache(content.getCategoryId());
        }
        return mcount;
    }

    /**
     *  根据id集合删除所有，并删除缓存
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Object> ids) {
        Content content = contentMapper.selectByPrimaryKey(Long.parseLong(ids.get(0).toString()));
        int dcount = super.deleteByIds(ids);
        if (content != null) {
            delCache(content.getCategoryId());
        }
        return dcount;
    }

    @Override
    public PageInfo<Content> getListByCategoryId(Long categoryId, Integer page, Integer rows) {
        Content content = new Content();
        content.setCategoryId(categoryId);
        PageHelper.startPage(page, rows);
        List<Content> list = contentMapper.select(content);
        return new PageInfo<Content>(list);
    }

    /**
     * 根据cid查询，先从redis缓存查，若没有再从数据库查
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public String getBigAd(long cid) throws Exception {
        if (redisUtil.exists(Constant.RedisKey.INDEX_BIG_CONTENT + cid)) {
            return redisUtil.get(Constant.RedisKey.INDEX_BIG_CONTENT + cid);
        }
        Content searchContent = new Content();
        searchContent.setCategoryId(cid);
        List<Content> contents = contentMapper.select(searchContent);
        List<Map<String, Object>> listMap =  new ArrayList<Map<String, Object>>();
        for (Content content : contents) {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap.put("srcB", content.getPic());
            dataMap.put("height", 240);
            dataMap.put("alt", "");
            dataMap.put("width", 670);
            dataMap.put("src", content.getPic2());
            dataMap.put("widthB", 550);
            dataMap.put("href", content.getUrl());
            dataMap.put("heightB", 240);
            listMap.add(dataMap);
        }
        String json = objectMapper.writeValueAsString(listMap);
        redisUtil.add(Constant.RedisKey.INDEX_BIG_CONTENT + cid, json);
        return json;
    }

    /**
     * 根据cid删除redis缓存
     * @param cid
     */
    private void delCache(Long cid) {
        redisUtil.del(Constant.RedisKey.INDEX_BIG_CONTENT + cid);
    }
}
