package com.taotao.solr.handler;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

/**
 * solr操作工具类
 */
public interface SolrUtils {

    void addDoc(SolrInputDocument doc) throws IOException, SolrServerException;

    void addDocs(List<SolrInputDocument> docs) throws IOException, SolrServerException;

    QueryResponse query(SolrQuery solrQuery) throws IOException, SolrServerException;
}
