package com.jersey.mappers;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by lupus on 10.05.16.
 */
@Provider
public class MethodArgumentNotValidExceptionMapper implements ExceptionMapper<MethodArgumentNotValidException> {

    @Override
    public Response toResponse(MethodArgumentNotValidException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setCode(777);
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        errorMessage.setLink(null);
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(MethodArgumentNotValidException ex, ErrorMessage errorMessage) {

            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
