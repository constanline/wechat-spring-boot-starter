package com.magicalyang.wechat.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Constanline
 */
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 设置序列化时间时，不采用timestamp；默认是对java.util.date进行timestamp转换的
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置时区
        OBJECT_MAPPER.setTimeZone(TimeZone.getDefault());
        // 设置输出时间格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    public static <T> T toModel(String content, Class<T> valueType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(content, valueType);
    }

    public static Map<String, Object> toMap(Object value) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        try {
            return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(value), javaType);
        } catch (JsonProcessingException ex) {
            return new HashMap<>();
        }
    }

    public static String toJson(Object value) {
        try{
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }
}

