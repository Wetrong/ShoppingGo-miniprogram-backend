package com.wetron.entity;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/17 16:47
 */
public class User {
    private int id;
    private short identity;
    private String user_name;
    private String phone_number;
    private String avatar_url;
    private String address;

    public User() {
    }

    public User(int id, short identity, String user_name, String phone_number, String avatar_url, String address) {
        this.id = id;
        this.identity = identity;
        this.user_name = user_name;
        this.phone_number = phone_number;
        this.avatar_url = avatar_url;
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"identity\":" + identity +
                ", \"user_name\":\"" + user_name + '\"' +
                ", \"phone_number\":\"" + phone_number + '\"' +
                ", \"avatar_url\":\"" + avatar_url + '\"' +
                ", \"address\":\"" + address + '\"' +
                '}';
    }
}
