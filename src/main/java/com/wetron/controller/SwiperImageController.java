package com.wetron.controller;

import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/19 17:25
 */
@RestController
public class SwiperImageController {
    @GetMapping("getSwiperImages")
    public static String getSwiperImages() {
        List list;
        String sqlStr = "SELECT img_url FROM market_poster WHERE is_show=1";
        list = JdbcConnect.sqlQuery(sqlStr, "market_poster");
        return StringProcess.StrListToString(list);
    }
}
