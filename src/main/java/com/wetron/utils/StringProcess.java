package com.wetron.utils;

import com.wetron.entity.Message;

import java.util.List;

/**
 * @description:TODO
 * @author: Wetron
 * @create: 2024/5/19 10:17
 */
public class StringProcess {
    //仅限传入List<User>, List<Goods>, List<Order>, List<AddCart>
    public static String ListToString(List list) {
        StringBuffer result = new StringBuffer();
        result.append("[");
        for (int i = 0; i < list.size(); i++) {
            //往返回结果字符串里添加对应商品的toString
            result.append(list.get(i).toString());
            if (i < list.size()-1) {
                result.append(",\n");
            }
        }
        result.append("]");
        return result.toString();
    }
    public static String StrListToString(List<String> list) {
        StringBuffer result = new StringBuffer();
        result.append("[");
        for (int i = 0; i < list.size(); i++) {
            //往返回结果字符串里添加对应对象的toString
            result.append("\"");
            result.append(list.get(i).toString());
            result.append("\"");
            if (i < list.size()-1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
}
