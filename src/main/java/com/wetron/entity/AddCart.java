package com.wetron.entity;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/17 16:38
 */
public class AddCart {
    private int id = 0;//加购关系id
    private int goods_id;//商品id
    private int customer_id = 0;//用户id
    private int quantity;//加购数量
    private String goods_name;//加购商品名
    private String img_url;//加购商品图片
    private double goods_price;//加购商品价格
    private short is_selected;//是否被选中

    //构造方法：加购ID、商品ID、消费者ID、加购数量，POST使用
    public AddCart(int id, int goods_id, int customer_id, int quantity) {
        this.id = id;
        this.goods_id = goods_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
    }

    //构造方法：商品ID、加购数量、商品名、商品图片URL、商品价格，GET使用
    public AddCart(int id, int goods_id, int quantity, String goods_name, String img_url, double goods_price, short is_selected) {
        this.id = id;
        this.goods_id = goods_id;
        this.quantity = quantity;
        this.goods_name = goods_name;
        this.img_url = img_url;
        this.goods_price = goods_price;
        this.is_selected = is_selected;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "{" +
                "\"addcart_id\":" + id +
                ", \"goods_id\":" + goods_id +
                ", \"customer_id\":" + customer_id +
                ", \"quantity\":" + quantity +
                ", \"goods_name\":\"" + goods_name + '\"' +
                ", \"img_url\":\"" + img_url + '\"' +
                ", \"goods_price\":" + goods_price +
                ", \"is_selected\":" + is_selected +
                '}';
    }
}
