package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PasswordSetDTO {


    @NotNull
    @NotEmpty
    private String password;


    @NotNull
    @NotEmpty
    private String password_confirm;



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
