package com.wetron.controller;

import com.wetron.entity.AddCart;
import com.wetron.entity.Goods;
import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/17 19:10
 */

@RestController
public class GetGoodsController {

    private static String getGoodsList(String sqlStr) {
        List<Goods> list;
        //执行查询语句，将结果存入list
        list = JdbcConnect.sqlQuery(sqlStr, "goods");
        //将每一项的img_url分割确保为一个链接字符串
        for (Goods item : list) {
            item.setImgUrl(item.getImgUrl().split(",")[0]);
        }
        return StringProcess.ListToString(list);
    }

    //获取首页商品推荐
    @GetMapping("/recomGoods")
    public String recomGoods(String count) {
        String sqlStr = "SELECT * FROM goods ORDER BY sale_volume DESC LIMIT " + count;
        return getGoodsList(sqlStr);
    }

    //通过关键词搜索商品
    @GetMapping("/searchGoods")
    public String searchGoods(String keyword) {
        String sqlStr;
        //查找包含关键词的商品
        if (keyword.equals("all")) {
            sqlStr = "SELECT * FROM goods";
        } else {
            sqlStr = "SELECT * FROM goods WHERE name like '%" + keyword + "%'";
        }
        return getGoodsList(sqlStr);
    }

    //获取商品详情页信息
    @GetMapping("/getGoodsInfo")
    public String getGoodsInfo(String goodsId) {
        List<Goods> list;
        String sqlStr = "SELECT * FROM goods WHERE id = " + goodsId;
        //执行查询语句
        list = JdbcConnect.sqlQuery(sqlStr, "goods");
        //未查到该商品信息
        if (list.size() == 0) {
            return "[]";
        }
        return list.get(0).toString();
    }
}

