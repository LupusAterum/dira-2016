package com.jersey.config;

/**
 * Created by lupus on 09.05.16.
 */

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.jersey.mappers.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class JerseyInitialization extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public JerseyInitialization() {
        this.register(new JacksonJsonProvider(ObjectMapperFactory.create()));
        register(AppExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(MethodArgumentNotValidException.class);
        register(JsonMappingExceptionMapper.class);
        register(ConstraintViolationExceptionMapper.class);
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        this.packages(true, "com.jersey.resources");
    }
}