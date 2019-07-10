package com.taotao.search.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.page.Page;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.model.Item;
import com.taotao.search.service.ItemService;
import com.taotao.solr.handler.SolrUtils;
import com.taotao.solr.transfer.ModelTransForDoc;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Service(interfaceClass = ItemService.class)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SolrUtils solrUtils;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void inportSolrData() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, SolrServerException {
        int page = 1;
        PageInfo<Item> pageInfo = null;
        do {
            PageHelper.startPage(page, 100);
            List<Item> itemList = itemMapper.select(null);
            List<SolrInputDocument> docs = new ArrayList<>();
            for (Item item : itemList) {
                docs.add(ModelTransForDoc.model2Doc(item));
            }
            pageInfo = new PageInfo<Item>(itemList);
            solrUtils.addDocs(docs);
            page ++;
        } while (page < pageInfo.getLastPage());
    }

    @Override
    public Page<Item> query(String q, Integer page) throws IOException, SolrServerException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SolrQuery solrQuery = new SolrQuery();
        if (StringUtils.isNotBlank(q)) {
            solrQuery.setQuery("item_title:" + q);
        } else {
            solrQuery.setQuery("*:*");
        }
        //分页处理
        int size = 30;
        int start = (page - 1) * size;
        //排序
        solrQuery.setSort("item_price", SolrQuery.ORDER.asc);
        //数据高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<span style=\"color:red;\">");
        solrQuery.setHighlightSimplePost("</span>");
        //高亮数据处理
        QueryResponse queryResponse = solrUtils.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highMap = queryResponse.getHighlighting();
        //总记录数
        long allFound = results.getNumFound();
        //定义一个集合接收所有数据
        List<Item> items = new ArrayList<Item>((int) allFound);
        //高亮数据剥离
        for (SolrDocument result : results) {
            Map<String, List<String>> hmap = highMap.get(result.get("id"));
            if (hmap != null) {
                List<String> item_titles = hmap.get("item_title");
                if (item_titles != null && item_titles.size() > 0) {
                    result.addField("item_title", item_titles.get(0));
                }
            }
            items.add(ModelTransForDoc.doc2Model(result, Item.class));
        }
        //封装page对象
        Page<Item> pageInfo = new Page<Item>(allFound, page, size, items);
        return pageInfo;
    }

    @Override
    public void importItem2Index(long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, SolrServerException {
        Item item = itemMapper.selectByPrimaryKey(id);
        SolrInputDocument doc = ModelTransForDoc.model2Doc(item);
        solrUtils.addDoc(doc);
    }

    @Override
    public void deleteItemIndex(List ids) throws IOException, SolrServerException {
        List<String> newIds = new ArrayList<>(ids.size());
        for (int i = 0; i < ids.size(); i++) {
            newIds.add(i, String.valueOf(ids.get(i)));
        }
        UpdateResponse response = solrUtils.deleteByIds(newIds);
    }
}
