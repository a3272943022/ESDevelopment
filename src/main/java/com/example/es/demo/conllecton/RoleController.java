package com.example.es.demo.conllecton;

import com.example.es.demo.datamodule.RoleRepository;
import com.example.es.demo.model.Role;
import com.example.es.demo.server.RoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: key
 * @DATE: 2019/10/21 13 : 29
 * @CLASSNAME RoleController
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleServer roleServer;
    @Autowired
    private RoleRepository roleRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 获取
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public Object getRoleList() {
        return roleServer.getRoleListAll();
    }

    /**
     * 把数据库里的数据导入es中
     */
    @RequestMapping("/save")
    public String save() {
        List<Role> list = roleServer.getRoleListAll();
        Iterable<Role> companies = roleRepository.saveAll(list);
        return companies.toString();
    }

}
