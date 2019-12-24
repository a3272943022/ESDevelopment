package com.example.es.demo;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
/*        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").build();
        TransportClient client = new PreBuiltTransportClient(settings);*/
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("user", "kimchy")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
                    .endObject();

            //关闭资源
           /* client.close();*/
        } catch (Exception e) {

        }
    }

    /**
     * 创建索引，添加一条数据进去
     */
    @Test
    void save() {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            IndexResponse response = client.prepareIndex("twitter", "_doc", "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
            //关闭资源
            client.close();
        } catch (Exception e) {

        }
    }

}
