package com.wetron.utils;

import com.wetron.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/4/26 3:13
 */
public class JdbcConnect {
    //数据库驱动名
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/netmall?serverTimezone=UTC";
    //数据库用户名
    static final String USER = "root";
    //数据库密码
    static final String PASSWORD = "681571";

    static Connection conn = null;
    static Statement stmt = null;
    private static void initJdbc() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName(DB_DRIVER);
        //建立连接
        conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        //创建语句对象
        stmt = conn.createStatement();
    }
    public static int sqlUpdate(String sqlStr) {
        int result;
        try {
            initJdbc();     //准备工作
            result = stmt.executeUpdate(sqlStr);//执行SQL语句并返回结果,表示被修改的行数
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {   //关闭语句和连接
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
    public static List sqlQuery(String sqlStr, String tableName) {
        List result = new ArrayList();;//存储检索到的商品的列表
        ResultSet rs;
        try {
            initJdbc();
            rs = stmt.executeQuery(sqlStr);
            System.out.println("执行完毕");
            switch (tableName) {
                case "goods":
                    result = new ArrayList<Goods>();
                    while (rs.next()) {
                        //用结果集内容生成Goods对象
                        Goods goods = new Goods(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img_url"),
                                rs.getDouble("price"),
                                rs.getInt("sale_volume"));
                        result.add(goods);  //result存储Goods列表
                    }
                    break;
                case  "getcart":
                    result = new ArrayList<AddCart>();
                    while (rs.next()) {
                        //用结果集内容生成AddCart对象
                        AddCart addCart = new AddCart(
                                rs.getInt("addcart_id"),
                                rs.getInt("goods_id"),
                                rs.getInt("quantity"),
                                rs.getString("goods_name"),
                                rs.getString("img_url"),
                                rs.getDouble("price"),
                                rs.getShort("is_selected"));
                        result.add(addCart);  //result存储addCart列表
                    }
                    break;
                case  "addcart":
                    result = new ArrayList<AddCart>();
                    while (rs.next()) {
                        //用结果集内容生成AddCart对象
                        AddCart addCart = new AddCart(
                                rs.getInt("id"),
                                rs.getInt("customer_id"),
                                rs.getInt("goods_id"),
                                rs.getInt("quantity"));
                        result.add(addCart);  //result存储addCart列表
                    }
                    break;
                case "user":
                    result = new ArrayList<User>();
                    while (rs.next()) {
                        //用结果集内容生成User对象
                        User user = new User(
                                rs.getInt("id"),
                                rs.getByte("identity"),
                                rs.getString("user_name"),
                                rs.getString("phone_number"),
                                rs.getString("avatar_url"),
                                rs.getString("address")
                        );
                        result.add(user);  //result存储Goods列表
                    }
                    break;
                case "order":
                    result = new ArrayList<Order>();
                    while (rs.next()) {

                        //用结果集内容生成Order对象
                        Order order = new Order();
                        result.add(order);  //result存储Goods列表
                    }
                    break;
                case "market_poster":
                    result = new ArrayList<String>();
                    while (rs.next()) {
                        result.add(rs.getString("img_url"));
                    }
                    break;
                case "message":
                    List<Message> msgList = new ArrayList<Message>();
                    while (rs.next()) {
                        int findI = Message.searchItem(msgList,"merchant_id",rs.getInt("merchant_id"));
                        if (findI > -1) {   //findI是merchan_id == 指定值的下标
                            msgList.get(findI).setContent(rs.getString("content"));//更新内容，保持最新
                            msgList.get(findI).setTime(rs.getTimestamp("time"));
                        } else {    //findI没找到
                            Message msg = new Message(
                                    rs.getInt("merchant_id"),
                                    rs.getString("user_name"),
                                    rs.getString("avatar_url"),
                                    rs.getString("content"),
                                    rs.getTimestamp("time")
                            );
                            msgList.add(msg);
                        }
                    }
                    result = msgList;
                    break;
                case "msg_item":
                    result = new ArrayList<Message>();
                    while (rs.next()) {
                        Message msg = new Message(
                                rs.getInt("merchant_id"),
                                rs.getString("content"),
                                rs.getTimestamp("time"),
                                rs.getString("msg_type")
                        );
                        result.add(msg);
                    }
                    break;
                default:
                    break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return result;
    }

}
