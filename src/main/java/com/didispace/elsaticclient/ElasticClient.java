package com.didispace.elsaticclient;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticClient {

    public static RestHighLevelClient client;

    public ElasticClient(){
        if (client==null){
            client=new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http"),new HttpHost("localhost",9201,"http")));
        }
    }

    public void Dispose(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
