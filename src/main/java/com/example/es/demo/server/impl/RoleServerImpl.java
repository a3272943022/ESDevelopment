package com.example.es.demo.server.impl;

import com.example.es.demo.dao.RoleMapper;
import com.example.es.demo.model.Role;
import com.example.es.demo.server.RoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: key
 * @DATE: 2019/10/21 13 : 31
 * @CLASSNAME RoleServerImpl
 */
@Service
public class RoleServerImpl implements RoleServer {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleListAll() {
        return roleMapper.getRoleListAll();
    }
}
