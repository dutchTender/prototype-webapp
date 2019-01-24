package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO;

import com.thorton.grant.uspto.prototypewebapp.interfaces.registration.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;




public class RegistrationDTO {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;


    @NotNull
    @NotEmpty
    private String firstName;


    @NotNull
    @NotEmpty
    private String lastName;


    private String suffix;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String password_confirm;




    private String viewType;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
}
