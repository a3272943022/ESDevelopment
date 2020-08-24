package com.example.es.demo.datamodule;

import com.example.es.demo.model.CheckItems;
import com.example.es.demo.model.Role;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: key
 * @DATE: 2019/10/21 13 : 26
 * @CLASSNAME RoleRepository
 */
public interface CheckItemsRepository extends ElasticsearchRepository<CheckItems, String> {
    
}
