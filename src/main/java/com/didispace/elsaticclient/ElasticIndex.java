package com.didispace.elsaticclient;

import com.didispace.elsaticclient.Config.CustomActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ElasticIndex {
    /**
     * 进行所擒的请求
     */
    public void CreateTest() throws IOException {
        IndexRequest request=new IndexRequest("posts");
        request.id("1");
        String jsonstring="{"+"\"user\":\"kimchy\",\"postDate\":\"2013-01-30\",\"message\":\"trying out Elasticsearch\"}";
        request.source(jsonstring, XContentType.JSON);

        //路由
        request.routing("routing");

        //超时
        request.timeout(TimeValue.timeValueSeconds(1));
        //超时已等待分片作为string
        request.timeout("is");
        //将策略刷新为实例
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        //刷新策略为String
        request.setRefreshPolicy("wait_for");
        //版本
        request.version(2);
        //版本类型
        request.versionType(VersionType.EXTERNAL);
        //值提供的操作类型
        request.opType(DocWriteRequest.OpType.CREATE);
        //使用String创建索引
        request.opType("create");
        //在索引文档之前执行的包含管道的名称
        request.setPipeline("pipeline");

        ElasticClient elasticClient=new ElasticClient();

        //进行索引的创建(同步)
        IndexResponse indexResponse=elasticClient.client.index(request, RequestOptions.DEFAULT);

        //创建监听器
        CustomActionListener listener=new CustomActionListener();
        //异步执行
        elasticClient.client.indexAsync(request, RequestOptions.DEFAULT, listener);

    }

    /**
     * 进行 文档信息插入
     * @throws IOException
     */
    public void CreateTest2() throws IOException {
        XContentBuilder builder= XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user","kimchy");
            builder.field("postDate",new Date());
            builder.field("message","trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest=new IndexRequest("posts").id("1").source(builder);
    }

    /**
     * 进行 索引文档的创建
     */
    public void CreateTest3(){
        IndexRequest indexRequest=new IndexRequest("posts").id("1").source("user","kimchy","postDate",new Date(),"message","trying out Elasticsearch");
    }

    /**
     * 进行 索引文旦的创建4
     */
    public void CreateTest4(){
        Map<String,Object> jsonMap=new HashMap<>();
        jsonMap.put("user","kimchy");
        jsonMap.put("postDate",new Date());
        jsonMap.put("message","trying out Elasticsearch");
        IndexRequest indexRequest=new IndexRequest("posts").id("1").source(jsonMap);
    }

    public void CreateTest5() {

    }
}
