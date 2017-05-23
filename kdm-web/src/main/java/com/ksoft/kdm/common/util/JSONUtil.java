package com.ksoft.kdm.common.util;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjianbo
 * @date 2017/5/23
 */
public class JSONUtil {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T toBean(String json, Class<T> clz) {

        return (T)JSON.parse(json);
    }

    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        return JSON.parseObject(json,Map.class);
    }

    public static Map<String, Object> toMap(String json) {
        return JSON.parseObject(json);
    }

    public static <T> List<T> toList(String json, Class<T> clz) {
       return JSON.parseObject(json,List.class);
    }

    public static void main(String[] args) {
    }
}