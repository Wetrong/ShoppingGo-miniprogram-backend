package com.wetron.controller;

import com.wetron.entity.Message;
import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/27 20:55
 */
@RestController
public class MessageController {
    @GetMapping("get_msg")
    public String getMsg(int customer_id) {
        List<Message> list;
        String sqlStr = "SELECT m.merchant_id,u.user_name,u.avatar_url,m.content,m.time " +
                "FROM message m INNER JOIN user u ON m.merchant_id=u.id " +
                "WHERE m.customer_id=" + customer_id;
        list = JdbcConnect.sqlQuery(sqlStr,"message");

        List<Message> reverseList = new ArrayList<>();
        for (Message msg:list) {    //按时间倒序排列消息列表
            reverseList.add(0,msg);
        }
        return StringProcess.ListToString(reverseList);
    }
    @GetMapping("get_msg_item")
    public String getMsgItem(int customer_id, int merchant_id) {
        List<Message> list;
        String sqlStr = "SELECT * FROM message " +
                "WHERE customer_id=" + customer_id + " and merchant_id=" + merchant_id;
        list = JdbcConnect.sqlQuery(sqlStr,"msg_item");
        return StringProcess.ListToString(list);
    }
}
