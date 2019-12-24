package com.example.es.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: key
 * @DATE: 2019/10/21 10 : 09
 * @CLASSNAME CheckItems
 */
@Data
@Document(indexName = "test-checkitems", type = "checkitems", replicas = 0, shards = 3)
public class CheckItems {
    @Id
    private long id;
    @Field(type = FieldType.Long)
    private long reportId;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String name;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String stname;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String stunit;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String techTarget;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String checkResult;
    @Field(type = FieldType.Long)
    private long itemResult;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String remark;
    @Field(type = FieldType.Double)
    private double standardMultiple;
    @Field(type = FieldType.Long)
    private long flag;
    @Field(type = FieldType.Integer)
    private Integer factorId;
    @Field(type = FieldType.Integer)
    private Integer riskLevel;
    @Field(type = FieldType.Integer)
    private Integer edit;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String formatStandard;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String formatResult;

}
