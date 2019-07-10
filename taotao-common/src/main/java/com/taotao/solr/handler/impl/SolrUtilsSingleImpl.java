package com.taotao.solr.handler.impl;

import com.taotao.solr.handler.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

/**
 * 单机版实现
 */
public class SolrUtilsSingleImpl implements SolrUtils{

    private SolrClient solrClient;

    @Override
    public void addDoc(SolrInputDocument doc) throws IOException, SolrServerException {
        solrClient.add(doc);
        solrClient.commit();
    }

    @Override
    public void addDocs(List<SolrInputDocument> docs) throws IOException, SolrServerException {
        solrClient.add(docs);
        solrClient.commit();
    }

    @Override
    public QueryResponse query(SolrQuery solrQuery) throws IOException, SolrServerException {
        QueryResponse response = solrClient.query(solrQuery);
        return response;
    }

    @Override
    public UpdateResponse deleteByIds(List<String> ids) throws IOException, SolrServerException {
        return null;
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }
}
