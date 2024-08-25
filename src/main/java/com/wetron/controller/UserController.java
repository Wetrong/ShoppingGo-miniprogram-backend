package com.wetron.controller;

import com.wetron.entity.User;
import com.wetron.utils.JdbcConnect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/18 13:21
 */

@RestController
public class UserController {
    @GetMapping("/getUserInfo")
    public String getUser(String user_id) {
        List<User> list = new ArrayList<>();
        String sqlStr;
        sqlStr = "SELECT * FROM user WHERE id=" + user_id;
        list = JdbcConnect.sqlQuery(sqlStr, "user");
        return list.get(0).toString();
    }
}
