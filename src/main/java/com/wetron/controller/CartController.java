package com.wetron.controller;

import com.wetron.entity.AddCart;
import com.wetron.entity.Goods;
import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/17 19:13
 */

@RestController
public class CartController {
    //将商品加购
    @PostMapping("/addCart")
    public String addCart(
            @RequestParam("goods_id") String goods_id,
            @RequestParam("customer_id") String customer_id) {
        int result;
        String sqlStr;
        System.out.println("商品：" + goods_id + "用户：" + customer_id);
        sqlStr = "SELECT * FROM addcart WHERE goods_id LIKE \"" + goods_id + "\" " +
                "AND customer_id LIKE \"" + customer_id + "\"";
        List<AddCart> list = JdbcConnect.sqlQuery(sqlStr, "addcart");
        if (list.size() != 0) {
            sqlStr = "UPDATE addcart SET quantity=quantity+1 " +
                    "WHERE goods_id LIKE \"" + goods_id + "\" " +
                    "AND customer_id LIKE \"" + customer_id + "\"";
        } else {
            sqlStr = "INSERT INTO addcart (id, customer_id, goods_id, quantity) " +
                    "SELECT MAX(id) + 1," + customer_id + "," + goods_id + ",1 FROM addcart";

        }
        result = JdbcConnect.sqlUpdate(sqlStr);//表示修改成功的行数
        return result==0 ? "ERROR" : "(" + customer_id + "," + goods_id + ",1)";
    }

    //获取购物车信息
    @GetMapping("/getCart")
    public String getCart(@RequestParam("customer_id") String customer_id) {
        List<AddCart> list;
        StringBuffer result = new StringBuffer();
        String sqlStr;
        sqlStr = "SELECT a.id addcart_id,g.id goods_id,g.name goods_name,g.price,a.quantity,g.img_url,a.is_selected" +
                " FROM goods g INNER JOIN addcart a ON g.id=a.goods_id" +
                " WHERE customer_id = " + customer_id;
        //执行查询语句，将结果存入list
        list = JdbcConnect.sqlQuery(sqlStr, "getcart");
        //将每一项的img_url分割确保为一个链接字符串
        for (AddCart item : list) {
            item.setImg_url(item.getImg_url().split(",")[0]);
        }

        return StringProcess.ListToString(list);
    }

    //更新购物车
    @PostMapping("/updateCart")
    public String updateCart(String oprate, String goods_id, String customer_id) {
        int result;
        String sqlStr = "UPDATE addcart SET quantity = quantity" + oprate + "1 " +
                "WHERE goods_id = " + goods_id + " AND customer_id = " + customer_id;
        result = JdbcConnect.sqlUpdate(sqlStr);
        return result==0 ? "UPDATE quantity ERROR" : "UPDATE quantity SUCCESS";
    }

    //删除购物车内某一项目
    @PostMapping("/deleteCartItem")
    public String deleteCartItem(String goods_id, String customer_id) {
        System.out.println("删除"+goods_id + "*" + customer_id);
        String sqlStr = "DELETE FROM addcart WHERE goods_id=" + goods_id
                + " AND customer_id=" + customer_id;
        int result = JdbcConnect.sqlUpdate(sqlStr);
        return result==0 ? "DELETE ERROR" : "DELETE SUCCESS";
    }
    //修改选中项目状态
        @PostMapping("/selectChange")
    public String selectChange(Integer addcart_id, Boolean is_all_selected) {
        int result;
        String sqlStr;
        if (addcart_id != null) {
            sqlStr = "UPDATE addcart SET is_selected = CASE WHEN is_selected = 1 THEN 0 ELSE 1 END " +
                    "WHERE id="+addcart_id;
            System.out.println("修改加购id为"+ addcart_id + "的选中状态");
        } else {
            sqlStr = "UPDATE addcart SET is_selected = " + (is_all_selected==true ? 1 : 0);
        }


        result = JdbcConnect.sqlUpdate(sqlStr);
        return result==0 ? "UPDATE selected ERROR" : "UPDATE selected SUCCESS "+result;
    }
}
