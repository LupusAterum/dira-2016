package com.jersey.mappers;

import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * Created by lupus on 10.05.16.
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setCode(666);
        errorMessage.setMessage(ex.getMessage().split("at")[0]);
        errorMessage.setDeveloperMessage(null);
        errorMessage.setLink(null);
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(JsonMappingException ex, ErrorMessage errorMessage) {

        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
