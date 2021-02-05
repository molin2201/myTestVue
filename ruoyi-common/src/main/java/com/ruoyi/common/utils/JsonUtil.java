package com.ruoyi.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public  final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //排序
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        //多的字段不校验
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //跳过空字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //若POJO对象的属性值为""，序列化时不进行显示
        // objectMapper.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        //  针对于Date类型，文本格式化
        //    objectMapper.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //默认关闭，当JSON字段为""(EMPTY_STRING)时，解析为普通的POJO对象抛出异常。开启后，该POJO的属性值为null。
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
    }

    private JsonUtil() {
    }

    public static String toStr(Object obj){
        if(obj == null ){
            return null;
        }
        return obj.toString();
    }
    public static String obj2json(Object obj) {
        try {
            if(obj!=null){
                return objectMapper.writeValueAsString(obj);
            }
        }catch(JsonProcessingException ex){
            logger.error("转Json异常",ex);
        }
        return null;
    }


    /**
     * 转换为 JavaBean
     *
     * @param jsonString
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T json2pojo(String jsonString, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * 字符串转换为 Map<String, Object>
     *
     * @param jsonString
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, Map.class);
    }



    /**
     * 深度转换 JSON 成 Map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) throws Exception {
        return json2MapRecursion(json, objectMapper);
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }

        List<Object> list = mapper.readValue(json, List.class);

        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str, mapper);
                }
            }
        }

        return list;
    }

    /**
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     * @throws Exception
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }

        Map<String, Object> map = mapper.readValue(json, Map.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }

    /**
     * 将 JSON 数组转换为集合
     *
     * @param jsonArrayStr
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> list = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
        return list;
    }

    /**
     * 获取泛型的 Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将 Map 转换为 JavaBean
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 将 Map 转换为 JSON
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将 JSON 对象转换为 JavaBean
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    /**
     * 将对象转换为字符串数组
     *
     * @param obj
     * @return
     */
    public static String[] obj2ArrayString(Object obj) {
        Object[] objs = objectMapper.convertValue(obj, Object[].class);
        String[] arr = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            arr[i] = objs[i].toString();
        }
        return arr;
    }

    /**
     * 将对象转换为字符串数组
     *
     * @param obj
     * @return
     */
    public static int[] obj2ArrayInt(Object obj) {
        Object[] objs = objectMapper.convertValue(obj, Object[].class);
        int[] arr = new int[objs.length];
        for (int i = 0; i < objs.length; i++) {
            arr[i] = Integer.parseInt(objs[i].toString());
        }
        return arr;
    }

    public static JsonNode objToJsonNode (Object obj) throws IOException {
        return objectMapper.readTree(objectMapper.writeValueAsString(obj));
    }

}
