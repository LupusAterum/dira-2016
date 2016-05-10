package com.jersey.mappers;

import org.springframework.beans.BeanUtils;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lupus on 10.05.16.
 */

public class ErrorMessage {

    /** contains the same HTTP Status code returned by the server */

    int status;

    /** application specific error code */

    int code;

    /** message describing the error*/

    String message;

    /** link point to page where the error message is documented */

    String link;

    /** extra information that might useful for developers */

    String developerMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ErrorMessage(AppException ex){
        this.code = ex.getCode();
        this.status = ex.getStatus();
        this.link = ex.getLink();
        this.developerMessage = ex.getDeveloperMessage();
        this.message = ex.getMessage();
    }

    public ErrorMessage(NotFoundException ex){
        this.status = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
        this.link = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";
    }

    public ErrorMessage() {}
}
