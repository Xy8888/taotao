package com.taotao.search.service;


import com.taotao.page.Page;
import com.taotao.manager.model.Item;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ItemService {

    void inportSolrData() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, SolrServerException;

    Page<Item> query(String q, Integer page) throws IOException, SolrServerException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
