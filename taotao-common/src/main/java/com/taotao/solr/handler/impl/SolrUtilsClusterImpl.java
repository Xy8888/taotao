package com.taotao.solr.handler.impl;

import com.taotao.solr.handler.SolrUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;
/**
 * 集群版实现
 */
public class SolrUtilsClusterImpl implements SolrUtils {

    private CloudSolrClient solrClient;

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

    public void setSolrClient(CloudSolrClient solrClient) {
        this.solrClient = solrClient;
    }
}
