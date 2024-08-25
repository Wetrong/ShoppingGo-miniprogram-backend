package com.wetron.entity;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/4/25 7:03
 */
public class Goods { //商品类
    private int goodsId;
    private String goodsName;
    private String goodsTitle;
    private String imgUrl;
    private double goodsPrice;
    private int goodsSaleVol;
    public Goods() {
    }

    public Goods(int goodsId, String goodsTitle, String imgUrl, double goodsPrice, int goodsSaleVol) {
        this.goodsId = goodsId;
        this.goodsTitle = goodsTitle;
        this.imgUrl = imgUrl;
        this.goodsPrice = goodsPrice;
        this.goodsSaleVol = goodsSaleVol;
        try {   //将title截断当做name
            byte[] strBytes = goodsTitle.getBytes("GBK");
            if(strBytes.length <= 18) {
                this.goodsName = goodsTitle;
            } else {
                this.goodsName = new String(
                        Arrays.copyOf(strBytes, 18), "GBK");
            }
        } catch (UnsupportedEncodingException e) { throw new RuntimeException(e); }
    }

    public Goods(int goodsId, String goodsTitle, double goodsPrice, int goodsSaleVol) {
        this.goodsId = goodsId;
        this.goodsTitle = goodsTitle;
        this.goodsPrice = goodsPrice;
        this.goodsSaleVol = goodsSaleVol;
    }

    @Override
    public String toString() {
        return "{\"goods_id\": " + goodsId +
                ", \"goods_name\":\"" + goodsName + "\"" +
                ", \"goods_title\": \"" + goodsTitle + "\"" +
                ", \"img_url\": \"" + imgUrl + "\"" +
                ", \"goods_price\": " + goodsPrice +
                ", \"sale_volume\": " + goodsSaleVol +
                "}";
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public int getGoodsSaleVol() {
        return goodsSaleVol;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsSaleVol(int goodsSaleVol) {
        this.goodsSaleVol = goodsSaleVol;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
