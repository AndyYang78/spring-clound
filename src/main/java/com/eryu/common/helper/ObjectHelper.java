package com.eryu.common.helper;

import com.eryu.exception.ParameterException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json字符串与对象工具
 * Created by yangtao on 2017/7/21.
 */
public interface ObjectHelper {

    /**
     * 子类注入ObjectMapper后（保证单例）添加该方法
     *
     * @return Json工具
     */
    ObjectMapper getObjectMapper();

    /**
     * 字符串转对象
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    default <T> T toEntity(String json, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(json, valueType);
        } catch (IOException e) {
            throw new ParameterException(40000, "数据转换失败！");
        }
    }

    /**
     * 任意对象转Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    default String toString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (IOException e) {
            throw new ParameterException(40000, "数据转换失败！");
        }
    }

    /**
     * 字符串转List
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    default <T> List<T> toList(String json, Class<T> valueType) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(List.class, valueType);
        List<T> list;
        try {
            list = getObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            throw new ParameterException(30000, "数据转换失败！");
        }
        return list;
    }

    /**
     * 字符串转Map
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    default <T> Map<String, T> toHashMap(String json, Class<T> valueType) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(HashMap.class, String.class, valueType);
        HashMap<String, T> map;
        try {
            map = getObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            throw new ParameterException(30000, "数据转换失败！");
        }
        return map;
    }
}
