package com.sxkl.project.cloudnote.common;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringBeanManager {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static Map<BeanType, Object> beanMaps = Maps.newHashMap();

    private void init() {
        beanMaps.put(BeanType.MONO,mongoTemplate);
    }

    public static MongoTemplate getMongoTemplate() {
        return (MongoTemplate)beanMaps.get(BeanType.MONO);
    }

    private enum BeanType{
        MONO;
    }
}
