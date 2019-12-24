package com.example.es.demo.server;

import com.example.es.demo.model.CheckItems;

import java.util.List;
import java.util.Map;

/**
 * @author: key
 * @DATE: 2019/10/21 10 : 24
 * @CLASSNAME CheckItemsServer
 */
public interface CheckItemsServer {
    List<CheckItems> getSelectList();
}
