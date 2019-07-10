package com.taotao.search.service;


import com.taotao.page.Page;
import com.taotao.manager.model.Item;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ItemService {

    void inportSolrData() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, SolrServerException;

    Page<Item> query(String q, Integer page) throws IOException, SolrServerException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    void importItem2Index(long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, SolrServerException;

    void deleteItemIndex(List list) throws IOException, SolrServerException;
}
