package com.example.es.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * @author: key
 * @DATE: 2019/10/21 11 : 46
 * @CLASSNAME Role
 */
@Data

@Document(indexName = "test-role", type = "role", replicas = 0, shards = 3)
public class Role {
    @Id
    private Integer id;

    @Field(type = FieldType.Integer)
    private Integer num;

    @Field(type = FieldType.Integer)
    private Integer pid;

    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Integer)
    private Integer deptid;
    @Field(searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String tips;
    @Field(type = FieldType.Integer)
    private Integer version;
}
