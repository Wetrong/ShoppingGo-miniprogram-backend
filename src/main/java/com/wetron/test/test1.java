package com.wetron.test;

import com.wetron.controller.MessageController;
import com.wetron.entity.Goods;
import com.wetron.entity.Message;
import com.wetron.utils.JdbcConnect;
import com.wetron.utils.StringProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/4/26 3:57
 */
public class test1 {
    public static void main(String[] args) {
        List<Message> list;
        String sqlStr = "SELECT *" +
                "FROM message WHERE customer_id=4 and merchant_id=6";
        list = JdbcConnect.sqlQuery(sqlStr,"msg_item");

        System.out.println(StringProcess.ListToString(list));
        //String out = new GetGoodsDetailController().getGoodsInfo("2");
        //System.out.println(out);
        /*String str = "aa,bb,cc";
        System.out.println(str.split(",").toString());*/

        /*try {
            byte[] strByte = new byte[0];
            strByte = "阿帆啊初赛哈喽擦阿帆个".getBytes("GBK");
            byte[] outByte = Arrays.copyOf(strByte,18);
            System.out.println(new String(outByte, "GBK"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }*/
        //System.out.println(ServerInfo.getServerInfo());
    }
}
