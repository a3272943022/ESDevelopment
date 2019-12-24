package com.example.es.demo.dao;

import com.example.es.demo.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: key
 * @DATE: 2019/10/21 13 : 32
 * @CLASSNAME RoleMapper
 */
@Repository
public interface RoleMapper {
    List<Role> getRoleListAll();
}
