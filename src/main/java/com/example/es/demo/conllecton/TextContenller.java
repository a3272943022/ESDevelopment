package com.example.es.demo.conllecton;

import com.example.es.demo.datamodule.CheckItemsRepository;
import com.example.es.demo.model.CheckItems;
import com.example.es.demo.server.CheckItemsServer;
import com.google.gson.Gson;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: key
 * @DATE: 2019/10/21 09 : 54
 * @CLASSNAME TextContenller
 */
@RestController
@RequestMapping(value = "/text")
public class TextContenller {
    @RequestMapping(value = "api")
    public Object getText() {
        return "你好";
    }

    @Autowired
    private CheckItemsServer checkItemsServer;
    @Autowired
    private CheckItemsRepository checkItemsRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 查询（数据源）
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public Object getCheckItemsList() {
        return checkItemsServer.getSelectList();
    }

    /**
     * 把数据库里的数据导入es中
     */
    @RequestMapping("/save")
    public String save() {
        List<CheckItems> list = checkItemsServer.getSelectList();
        Iterable<CheckItems> companies = checkItemsRepository.saveAll(list);
        return companies.toString();
    }

    /**
     * 批量插入数据
     *
     * @return
     */
    public Object bulkRequest() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();

        List<Map> maps = new ArrayList<>();

        for (Map map : maps) {
            IndexRequest indexRequest = new IndexRequest("test-checkitems", "checkitems");
            indexRequest.source(map);
            bulkRequest.add(indexRequest);
        }
        //  BulkResponse bulkResponse=elasticsearchTemplate.bulk(buliRequest);

        return null;
    }

    /**
     * 搜索
     *
     * @return
     */
    @RequestMapping(value = "/search")
    public Object getSearch(@RequestParam("keyword") String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.matchQuery("name", keyword));

        Pageable pageable = PageRequest.of(0, 20);
        Iterable<CheckItems> companies = checkItemsRepository.search(queryBuilder, pageable);
        Gson gson = new Gson();
        return gson.toJson(companies);
    }

    /**
     * get获取es上的数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getid", method = RequestMethod.GET)
    public Object getSearchId() {
        GetResponse getResponse = elasticsearchTemplate.getClient().prepareGet("test-checkitems", "checkitems", "61").get();
        System.out.println(getResponse);
        return getResponse;
    }

    /**
     * 通过 XContentFactory  方式添加数据
     *
     * @return
     */
    @RequestMapping(value = "/add")
    public Object getsaveXContentFactory() {
        //通过json 数据添加
        String json = "{\"fields\":{},\"id\":\"61\",\"type\":\"checkitems\",\"index\":\"test-checkitems\",\"version\":1,\"" +
                "source\":{\"techTarget\":\"≤0.10\",\"itemResult\":1,\"flag\":0,\"riskLevel\":0,\"formatStandard\":\"0.10\"," +
                "\"reportId\":8,\"edit\":null,\"remark\":null,\"checkResult\":\"1\",\"factorId\":314,\"stunit\":\"mg/kg\"," +
                "\"formatResult\":\"1\",\"standardMultiple\":0.0,\"name\":\"甲胺磷\",\"id\":61,\"stname\":\"GB2763-2005《食品中农药的最大残留限量》\"}," +
                "\"sourceAsBytes\":\"eyJpZCI6NjEsInJlcG9ydElkIjo4LCJuYW1lIjoi55Sy6IO" +
                "656O3Iiwic3RuYW1lIjoiR0IyNzYzLTIwMDXjgIrpo5/lk4HkuK3lhpzoja/nmoTmnIDlpKfmrovnlZnpmZDph4/jgI" +
                "siLCJzdHVuaXQiOiJtZy9rZyIsInRlY2hUYXJnZXQiOiLiiaQwLjEwIiwiY2hlY2tSZXN1bHQiOiIxIiwiaXRlbVJlc3VsdCI6MSwicmVtYXJr" +
                "IjpudWxsLCJzdGFuZGFyZE11bHRpcGxlIjowLjAsImZsYWciOjAsImZhY3RvcklkIjozMTQsInJpc2tMZXZlbCI6MCwiZWRpdCI6bnVsbCwiZm9ybWF0U3Rh" +
                "bmRhcmQiOiIwLjEwIiwiZm9ybWF0UmVzdWx0IjoiMSJ9\",\"seqNo\":0,\"sourceAsMap\":{\"techTarget\":\"≤0.10\",\"itemResult\":1,\"flag\"" +
                ":0,\"riskLevel\":0,\"formatStandard\":\"0.10\",\"reportId\":8,\"edit\":null,\"remark\":null,\"checkResult\":\"1\",\"factorId\":314" +
                ",\"stunit\":\"mg/kg\",\"formatResult\":\"1\",\"standardMultiple\":0.0,\"name\":\"甲胺磷\",\"id\":61,\"stname\":\"GB2763-2005《食品中农药的" +
                "最大残留限量》\"},\"exists\":true,\"sourceAsString\":\"{\\\"id\\\":61,\\\"reportId\\\":8,\\\"name\\\":\\\"甲胺磷\\\",\\\"stname\\\":\\\"GB2763-20" +
                "05《食品中农药的最大残留限量》\\\",\\\"stunit\\\":\\\"mg/kg\\\",\\\"techTarget\\\":\\\"≤0.10\\\",\\\"checkResult\\\":\\\"1\\\",\\\"itemResult\\\":1,\\\"" +
                "remark\\\":null,\\\"standardMultiple\\\":0.0,\\\"flag\\\":0,\\\"factorId\\\":314,\\\"riskLevel\\\":0,\\\"edit\\\":null,\\\"formatStandard\\" +
                "\":\\\"0.10\\\",\\\"formatResult\\\":\\\"1\\\"}\",\"sourceInternal\":{\"fragment\":true},\"sourceEmpty\":false,\"primaryTerm\":1,\"sourceAsBytesRe" +
                "f\":{\"fragment\":true},\"fragment\":false}";
        IndexResponse response = elasticsearchTemplate.getClient().prepareIndex("test-checkitems", "checkitems")
                .setSource(json, XContentType.JSON).get();
        System.out.println(response);
        //通过
        try {
            IndexResponse indexResponse = elasticsearchTemplate.getClient().prepareIndex("test-checkitems", "checkitems")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("", "")
                            .endObject()).get();
            System.out.println(indexResponse);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取索引-对单挑数据进行修改
     *
     * @return
     */
    @RequestMapping(value = "/getUpdate")
    public Object getUpdate() {
        try {
            UpdateRequest updateRequest = new UpdateRequest("test-checkitems", "checkitems", "61")
                    .doc(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("stname", "修改内容")
                    );
            //使用需要修改的内容，提交到es
            UpdateResponse updateResponse = elasticsearchTemplate.getClient().update(updateRequest).get();
        } catch (Exception e) {

        }
        return null;

    }

    /**
     * 修改索引-在添加字段
     *
     * @return
     */
    @RequestMapping("getUpdateInsertField")
    public Object getUpdateInsertField() {
        try {
            IndexRequest request = new IndexRequest("", "", "")
                    .source(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("新字段名称", "值")
                            .endObject());

            UpdateRequest updateRequest = new UpdateRequest().upsert(request);
            UpdateResponse updateResponse = elasticsearchTemplate.getClient().update(updateRequest).get();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Elasticsearch使用java API 查询时间范围内匹配某个关键字并对查询结果内按某个字段进行GroupBy操作，
     * 我们业务需求是求某个手机前缀在某个时间范围内每个imsi对应的总数
     */
    public Object searchByPrefix(List<String> prefixList, String fromTime, String endTime) {
        for (String prefix : prefixList) {
            QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("time").from(fromTime).to(endTime))
                    .must(QueryBuilders.wildcardQuery("stname", prefix));

        }
        return null;
    }

    /**
     * 模糊、分页、排序查询
     *
     * @return
     */
    public Object getss() {
        // CheckItems  checkItems =new CheckItems();
        Integer id = 0;
        String name = "";
        Integer pageindex = 1;
        Integer pageSize = 10;
        //检索条件
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        if (id != 0)
            bqb.must(QueryBuilders.matchPhraseQuery("id", id));
        if (name != null)
            bqb.must(QueryBuilders.matchPhraseQuery("name", name));
        //排序条件
        FieldSortBuilder fsb = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        //分页条件
        pageindex = pageindex == 0 ? 1 : pageindex;
        pageSize = pageSize == 0 ? 10 : pageSize;
        Pageable pageable = PageRequest.of(pageindex - 1, pageSize);
        //构建查询
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(bqb)
                .withSort(fsb)
                .withPageable(pageable)
                .build();
        Page<CheckItems> searchResponse = checkItemsRepository.search(query);

        return searchResponse.getContent();
    }

    /**
     * 整合使用方法
     * 查询
     * 添加
     * 修改
     * @return
     */
    public Object getccs() {
        //检索条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("name", ""));
        //排序
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        //分页
        Pageable pageable = PageRequest.of(1, 10);
        //构建查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(fieldSortBuilder)
                .withPageable(pageable).build();
        Page<CheckItems> page = checkItemsRepository.search(searchQuery);


        //方法二
        BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
        boolQueryBuilder1.should(QueryBuilders.matchQuery("name", ""));
        Pageable pageable1 = PageRequest.of(0, 20);
        Page<CheckItems> checkItems = checkItemsRepository.search(boolQueryBuilder1, pageable1);
        //

        //使用、get 查询单条数据
        GetResponse response = elasticsearchTemplate.getClient().prepareGet("test-checkitems", "checkitems", "1").get();
        //使用索引添加数据
        IndexResponse indexResponse = elasticsearchTemplate.getClient().prepareIndex("test-checkitems", "checkitems").setSource("这里传输json数据就好", XContentType.JSON).get();
        //使用索引添加数据
        try {
            IndexResponse indexResponse1 = elasticsearchTemplate.getClient().prepareIndex("test-checkitems", "checkitems")
                    .setSource(
                            XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name","")
                                    .endObject()).get();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
        //修改内容
        UpdateRequest  updateRequest=new UpdateRequest("test-checkitems","checkitems","61");
        try {
            updateRequest.doc(XContentFactory.jsonBuilder().startObject()
                    .field("", "")
                    .endObject());
            UpdateResponse  updateResponse=elasticsearchTemplate.getClient().update(updateRequest).get();


        //给索引添加，字段
        IndexRequest  indexRequest =new IndexRequest("test-checkitems","checkitems");
                    indexRequest.source(XContentFactory.jsonBuilder()
                            .startObject().field("","").endObject());
                UpdateRequest updateRequest1=new UpdateRequest().upsert(indexRequest);
                UpdateResponse updateResponse1=elasticsearchTemplate.getClient().update(updateRequest1).get();
        }catch (Exception e){

        }


        return null;
    }
}
