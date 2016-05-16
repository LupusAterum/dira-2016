package com.jersey.representations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lupus on 09.05.16.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @OneToOne
    private InvoiceData invoiceData;
    @NotNull
    private String companyName;
    @NotNull
    private String jobPosition;
    @NotNull
    private String role;
    @NotNull
    private String mealType;

    @ManyToMany
            (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_workshops_signed")
    @OrderColumn(name = "uws_id")
    private Collection<Workshop> workshopsSignedFor = new ArrayList<>();

    @NotNull
    @OneToOne
            (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TShirtInfo tShirtInfo;

    public User() {

    }

    public User(String name, String surname, InvoiceData invoiceData,
                TShirtInfo tShirtInfo, String companyName,
                String jobPosition, String role, String mealType,
                Collection<Workshop> workshopsSignedFor) {
        this.name = name;
        this.surname = surname;
        this.invoiceData = invoiceData;
        this.tShirtInfo = tShirtInfo;
        this.companyName = companyName;
        this.jobPosition = jobPosition;
        this.role = role;
        this.mealType = mealType;
        this.workshopsSignedFor = workshopsSignedFor;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public InvoiceData getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(InvoiceData invoiceData) {
        this.invoiceData = invoiceData;
    }

    public TShirtInfo gettShirtInfo() {
        return tShirtInfo;
    }

    public void settShirtInfo(TShirtInfo tShirtInfo) {
        this.tShirtInfo = tShirtInfo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Collection<Workshop> getWorkshopsSignedFor() {
        return workshopsSignedFor;
    }

    public void setWorkshopsSignedFor(Collection<Workshop> workshopsSignedFor) {
        this.workshopsSignedFor = workshopsSignedFor;
    }
}
