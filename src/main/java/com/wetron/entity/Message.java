package com.wetron.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/27 20:50
 */
public class Message {
    private int merchant_id;
    private String merchant_name="商户名";
    private String merchant_avatar="商户头像src";
    private String content;
    private Date time;
    private String type = "null";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");

    public Message(int merchant_id, String merchant_name, String merchant_avatar, String content, Date time) {
        this.merchant_id = merchant_id;
        this.merchant_name = merchant_name;
        this.merchant_avatar = merchant_avatar;
        this.content = content;
        this.time = time;
    }
    public Message(int merchant_id, String content, Date time, String type) {
        this.merchant_id = merchant_id;
        this.content = content;
        this.time = time;
        this.type = type;
    }
    //找list中property属性值等于value的记录，返回其序列号下标i
    public static int searchItem(List<Message> list, String property, int value) {
        for (int i=0; i<list.size(); i++) {
            switch (property) {
                case "merchant_id":
                    if(list.get(i).getMerchant_id() == value) {
                        return i;
                    }
                default:
                    break;
            }
        }
        return -1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"merchant_id\":" + merchant_id +
                ", \"username\":\"" + merchant_name + '\"' +
                ", \"avatarSrc\":\"" + merchant_avatar + '\"' +
                ", \"msg\":\"" + content + '\"' +
                ", \"time\":\"" + dateFormat.format(time) + '\"' +
                ", \"type\":\"" + type + '\"' +
                '}';
    }

}
