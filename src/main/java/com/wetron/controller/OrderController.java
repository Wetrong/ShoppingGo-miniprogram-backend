package com.wetron.controller;

import com.wetron.entity.AddCart;
import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/18 5:50
 */
@RestController
public class OrderController {
    //获得订单结算信息
    @GetMapping("/getOrderCheck")
    public String getOrder(
            @RequestParam("customer_id") String customer_id,
            @RequestParam("id_list") String id_list
    ) {
        List<AddCart> list;
        StringBuffer result = new StringBuffer();
        String sqlStr;
        sqlStr = "SELECT g.id goods_id,g.name goods_name,g.price,a.quantity,g.img_url,a.is_selected" +
                " FROM goods g INNER JOIN addcart a ON g.id=a.goods_id" +
                " WHERE a.customer_id = " + customer_id + " AND a.goods_id IN " + id_list;
        System.out.println(sqlStr);
        //执行查询语句，将结果存入list
        list = JdbcConnect.sqlQuery(sqlStr, "getcart");

        return StringProcess.ListToString(list);
    }
}
