package com.example.es.demo.dao;

import com.example.es.demo.model.CheckItems;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: key
 * @DATE: 2019/10/21 10 : 16
 * @CLASSNAME CheckItemsMapper
 */
@Repository
public interface CheckItemsMapper {
   List<CheckItems> getSelectList();

}
