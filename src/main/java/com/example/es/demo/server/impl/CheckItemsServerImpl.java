package com.example.es.demo.server.impl;

import com.example.es.demo.dao.CheckItemsMapper;
import com.example.es.demo.model.CheckItems;
import com.example.es.demo.server.CheckItemsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: key
 * @DATE: 2019/10/21 10 : 25
 * @CLASSNAME CheckItemsServerImpl
 */
@Service
public class CheckItemsServerImpl implements CheckItemsServer {
    @Autowired
    private CheckItemsMapper checkItemsMapper;

    @Override
    public List<CheckItems> getSelectList() {
        return checkItemsMapper.getSelectList();
    }
}
