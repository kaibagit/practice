package com.kaiba.demo.elasticsearch;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

/**
 * Created by luliru on 2017/3/14.
 */
public class ClientTest {

    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = TransportClient.builder()
                .build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        queryDocument(client);
    }

    private static void createIndex(TransportClient client){
        HashMap<String, Object> json = new HashMap<>();
        json.put("first_name","Shuang");
        json.put("last_name", "Peng");
        json.put("age", 24);
        json.put("about", "I love coding");
        IndexResponse response = client
                .prepareIndex("tseg","students","1")
                .setSource(json).get();

        System.out.println(response);
    }

    private static void getDocuments(TransportClient client){
        GetResponse response2 = client.prepareGet("tseg", "students", "1").get();
        Map<String, Object> res = response2.getSource();
        for (Map.Entry<String, Object> entry: res.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void deleteDocument(TransportClient client){
        // 用来删除对应的 document
        DeleteResponse response3 =
                client.prepareDelete("tesg","students","1").get();
        // 用来删除对应的 index
        DeleteIndexResponse response4 =
                client.admin().indices().prepareDelete("facebook").execute().actionGet();
    }

    private static void queryDocument(TransportClient client){
        QueryBuilder qb1 = termQuery("about", "love");

        SearchResponse response = client.prepareSearch("tseg").setTypes("students")
                .setQuery(qb1)
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        System.out.println(response.getHits().totalHits());
        for (SearchHit hit : response.getHits()){
            Map<String, Object> pmap = hit.getSource();
            System.out.println(pmap);
        }

        QueryBuilder qb2 = boolQuery()
                .must(termQuery("about", "love"))
                .mustNot(termQuery("about", "cooking"))
                .should(termQuery("last_name", "Peng"));

        response = client.prepareSearch("tseg").setTypes("students")
                .setQuery(qb2)
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        System.out.println(response.getHits().totalHits());
        for (SearchHit hit : response.getHits()){
            Map<String, Object> pmap = hit.getSource();
            System.out.println(pmap);
        }
    }
}
