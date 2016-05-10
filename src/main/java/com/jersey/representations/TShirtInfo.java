package com.jersey.representations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 09.05.16.
 */
@Entity
public class TShirtInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    Boolean hasTShirtReceived = false;
    @NotNull
    String TShirtSize;
     public TShirtInfo() {

     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasTShirtReceived() {
        return hasTShirtReceived;
    }

    public void setHasTShirtReceived(Boolean hasTShirtReceived) {
        this.hasTShirtReceived = hasTShirtReceived;
    }

    public String getTShirtSize() {
        return TShirtSize;
    }

    public void setTShirtSize(String TShirtSize) {
        this.TShirtSize = TShirtSize;
    }
}
