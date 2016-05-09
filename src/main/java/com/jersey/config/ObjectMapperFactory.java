package com.jersey.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by lupus on 09.05.16.
 */
public class ObjectMapperFactory {

    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper()
                .registerModule(new Hibernate4Module());
    }
    public static ObjectMapper create() {
        return objectMapper;
    }
}
