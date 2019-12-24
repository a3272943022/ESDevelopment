package com.example.es.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author: key
 * @DATE: 2019/10/21 11 : 31
 * @CLASSNAME Relation
 */
@Data
@Document(indexName = "text-relation", type = "relation", replicas = 0, shards = 3)
public class Relation {
    @Id
    private Integer id;

    @Field(type = FieldType.Integer)
    private Integer menuId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Role> roles;

    @Field(type = FieldType.Integer)
    private Integer isdel;
}
