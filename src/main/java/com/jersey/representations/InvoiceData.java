package com.jersey.representations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 09.05.16.
 */
@Entity
public class InvoiceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nameOnInvoice;
    @NotNull
    private String taxIdentificationNumber;
    @NotNull
    private String street;
    @NotNull
    private String buildingNo;
    private String aptNo;
    @NotNull
    private String postcode;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String stateOrProvince;


    public InvoiceData(String nameOnInvoice, String taxIdentificationNumber, String street,
                       String buildingNo, String aptNo, String postcode, String city,
                       String country, String stateOrProvince) {
        this.nameOnInvoice = nameOnInvoice;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.street = street;
        this.buildingNo = buildingNo;
        this.aptNo = aptNo;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.stateOrProvince = stateOrProvince;
    }
    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOnInvoice() {
        return nameOnInvoice;
    }

    public void setNameOnInvoice(String nameOnInvoice) {
        this.nameOnInvoice = nameOnInvoice;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getAptNo() {
        return aptNo;
    }

    public void setAptNo(String aptNo) {
        this.aptNo = aptNo;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public InvoiceData() {

    }


}
