package com.admin.common.util.json;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }
    /**
     * object transform json string
     *
     * @param o object
     * @return json string
     */
    public static String objectToJson(Object o) {

        try {
            if(objectMapper.canSerialize(o.getClass())){
                return objectMapper.writeValueAsString(o);
            }else{
                return null;
            }
        } catch (IOException e) {
            logger.error("object:{} to json error:{}.", o, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * json string transform object
     *
     * @param json
     * @param className
     * @param defaultInstance when json string is empty,whether build classes instance,true:build;false:not build
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> className, boolean defaultInstance) {
        if (StringUtils.isEmpty(json)) {
            if (defaultInstance) {
                try {
                    return className.newInstance();
                } catch (Exception e) {
                    logger.error("instance class:{} error:{}.", className, ExceptionUtils.getStackTrace(e));
                    return null;
                }
            } else {
                return null;
            }
        } else {
            try {
                return objectMapper.readValue(json, className);
            } catch (IOException e) {
                logger.error("json:{} to object error:{}.", json, ExceptionUtils.getStackTrace(e));
                return null;
            }
        }
    }
}
