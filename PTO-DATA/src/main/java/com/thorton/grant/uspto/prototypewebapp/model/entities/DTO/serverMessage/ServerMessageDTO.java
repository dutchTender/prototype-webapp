package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.serverMessage;

import java.io.Serializable;

public class ServerMessageDTO implements Serializable {

    private String error;

    private String logout;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}
