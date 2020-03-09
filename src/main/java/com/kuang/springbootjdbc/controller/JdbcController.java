package com.kuang.springbootjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //查询数据库的所有信息
    //没有实体类，数据库中的东西，怎么获取？ Map
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql="select * from user";
        List<Map<String, Object>> List_maps = jdbcTemplate.queryForList(sql);
        return List_maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into mybatis.user(id,name,pwd) values (6,'小兰','12345')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }


    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id ){
        String sql="update mybatis.user set name =?,pwd=? where id ="+id;
        //封装
        Object[] objects = new Object[2];
        objects[0]="小明2";
        objects[1]="ddddd";
        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql="delete from mybatis.user where id =?";
        jdbcTemplate.update(sql,id);
        return "update-ok";
    }



}


